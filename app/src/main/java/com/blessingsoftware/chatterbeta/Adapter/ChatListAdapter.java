package com.blessingsoftware.chatterbeta.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blessingsoftware.chatterbeta.Models.Beans.ChatInfo;
import com.blessingsoftware.chatterbeta.Models.DAO.ChatTable;
import com.blessingsoftware.chatterbeta.Models.DAO.FriendTable;
import com.blessingsoftware.chatterbeta.R;

public class ChatListAdapter extends BaseAdapter {

    private Context context;
    private ChatInfo chatInfo;

    public ChatListAdapter(Context context,ChatInfo chatInfo){
        this.context=context;
        this.chatInfo=chatInfo;
    }

    @Override
    public int getCount(){
        return chatInfo.getRecentChatData().size();
    }

    @Override
    public Object getItem(int position){
        return chatInfo.getRecentChatData().get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView=View.inflate(context,R.layout.listview_row_chat,null);
        //获取视图
        ImageView ivFriendHead=(ImageView)convertView.findViewById(R.id.lvRow_friendHead);
        TextView tvFriendName=(TextView)convertView.findViewById(R.id.lvRow_friendName);
        TextView tvLastChatRecord=(TextView)convertView.findViewById(R.id.lvRow_lastChatContent);//有问题哦
        TextView tvLastChatTime=(TextView)convertView.findViewById(R.id.lvRow_lastChatTime);
        ImageView ivExpandRelation=(ImageView)convertView.findViewById(R.id.lvRow_expand);
        //视图数据
        ivFriendHead.setImageResource(R.drawable.head);
        tvFriendName.setText(chatInfo.getRecentChatData().get(position).get(FriendTable.FRIEND_NAME).toString());
        tvLastChatRecord.setText(chatInfo.getRecentChatData().get(position).get(ChatTable.CHAT_MSG_CONTENT).toString());
        ivExpandRelation.setImageResource(R.drawable.ic_menu_emoticons);
        String chatMsgTime=chatInfo.getRecentChatData().get(position).get(ChatTable.CHAT_MSG_TIME).toString();
        tvLastChatTime.setText(chatMsgTime.substring(chatMsgTime.indexOf(" ")+1,chatMsgTime.lastIndexOf(":")));
        return convertView;
    }
}
