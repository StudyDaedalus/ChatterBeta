package com.blessingsoftware.chatterbeta.Fragment;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.blessingsoftware.chatterbeta.Activity.ChatMsgActivity;
import com.blessingsoftware.chatterbeta.Adapter.ChatListAdapter;
import com.blessingsoftware.chatterbeta.Logs.Logs;
import com.blessingsoftware.chatterbeta.Models.Beans.ChatInfo;
import com.blessingsoftware.chatterbeta.Models.Beans.FriendInfo;
import com.blessingsoftware.chatterbeta.Models.DAO.FriendTable;
import com.blessingsoftware.chatterbeta.Models.Model;
import com.blessingsoftware.chatterbeta.R;

import java.util.List;
import java.util.Map;

import static com.blessingsoftware.chatterbeta.Models.DAO.FriendTable.FRIEND_ID;
import static com.blessingsoftware.chatterbeta.Models.DAO.FriendTable.USER_ID;

public class ChatFragment extends Fragment {

    private View view; //视图
    private ListView chatListView; //聊天列表
    private List<Map<String,Object>> chatListData; //聊天列表数据
    private ChatListAdapter chatListAdapter; //聊天列表适配器
    private ChatInfo chatInfo; //聊天对象
    private int userId;
    private final String MESSAGE_CHANGE="com.blessingsoftware.chatterbeta.MESSAGE_CHANGE";//触发信号
    private LocalBroadcastManager localBroadcastManager;//广播管理者
    private BroadcastReceiver MESSAGE_CHANGE_RECEIVER=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Logs.d("CHAT_FRAGMENT_CHANGE","聊天列表改变");
            //更新聊天列表数据
            chatListData= Model.getInstance().getDbManager().getChatTableDao().getChatList(userId);
            chatInfo.setRecentChatData(chatListData);
            chatListAdapter.notifyDataSetChanged();
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        if(view==null){
            view=inflater.inflate(R.layout.fragment_chat,container,false);
        }
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView(); //初始化视图
        initData(); //初始化数据
        initEvent(); //初始化事件监听器
        localBroadcastManager=LocalBroadcastManager.getInstance(getActivity());//注册广播
        localBroadcastManager.registerReceiver(MESSAGE_CHANGE_RECEIVER,new IntentFilter(MESSAGE_CHANGE));
    }
    @Override
    public void onResume(){
        super.onResume();
        //认证resume时更新列表的数据
        chatListData=Model.getInstance().getDbManager().getChatTableDao().getChatList(userId);
        chatInfo.setRecentChatData(chatListData);
        chatListAdapter.notifyDataSetChanged();
    }
    //初始化视图
    private void initView(){
        chatListView=(ListView)view.findViewById(R.id.chatListView);
    }
    //初始化数据
    private void initData(){
        //获取用户ID
        userId=Model.getInstance().getDbManager().getUserTableDao().getUserID();
        //实例化聊天对象
        chatInfo=new ChatInfo();
        //设置聊天列表数据
        chatListData=Model.getInstance().getDbManager().getChatTableDao().getChatList(userId);
        chatInfo.setRecentChatData(chatListData);
        //设置列表监听器
        chatListAdapter=new ChatListAdapter(getActivity(),chatInfo);
        chatListView.setAdapter(chatListAdapter);
    }
    //初始化事件监听器
    private void initEvent(){
        //设置chatListView的Item单击事件
        chatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ChatMsgActivity.class);
                FriendInfo friendInfo=Model.getInstance().getDbManager().getFriendTableDao().getFriendInfoByID(Integer.parseInt(chatListData.get(position).get(FRIEND_ID).toString()));//可能引用出错！
                intent.putExtra(FriendTable.FRIEND_NAME,friendInfo.getFriendName());
                intent.putExtra(FriendTable.FRIEND_ACCOUNT,friendInfo.getFriendAccount());
                intent.putExtra(FriendTable.FRIEND_SEX,friendInfo.getFriendSex());
                intent.putExtra(FriendTable.FRIEND_HEAD,friendInfo.getFrientHead());
                intent.putExtra(FriendTable.FRIEND_ID,friendInfo.getFriendID());
                intent.putExtra(USER_ID,userId);
                startActivity(intent);
            }
        });
        //设置chatListView的Item长按事件
        chatListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //菜单
                String[] itemTitle=new String[]{"已读","置顶","移除"};
                //转化回final以供匿名内部类调用
                final View listItemView=view;
                final int listItemIndex=position;
                //长按后弹出菜单以供选择
                new AlertDialog.Builder(getActivity()).setItems(itemTitle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            //已读
                            case 0:
                                break;
                            //置顶
                            case 1:
                                //获取选中的数据
                                Map<String,Object>map=chatListData.get(listItemIndex);
                                //删除数据并加到第一项
                                chatListData.remove(listItemIndex);
                                chatListData.add(0,map);
                                //更新chatListView的数据
                                chatListAdapter.notifyDataSetChanged();
                                break;
                            //删除该聊天
                            case 2:
                                //删除数据
                                chatListData.remove(listItemIndex);
                                //更新chatListView中的数据
                                chatListAdapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(),"已移除",Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }).show();
                return true;
            }
        });
    }
}
