package com.blessingsoftware.chatterbeta.Adapter;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blessingsoftware.chatterbeta.Activity.FriendInforActivity;
import com.blessingsoftware.chatterbeta.Models.Beans.ChatInfo;
import com.blessingsoftware.chatterbeta.Models.DAO.ChatTable;
import com.blessingsoftware.chatterbeta.Models.DAO.FriendTable;
import com.blessingsoftware.chatterbeta.Models.DAO.UserTable;
import com.blessingsoftware.chatterbeta.R;

import org.w3c.dom.Text;

public class ChatMsgListAdapter extends BaseAdapter implements View.OnClickListener,View.OnLongClickListener {
    private Context context;//上下文
    private ChatInfo chatInfo;//聊天对象
    //适配器的构造方法
    public ChatMsgListAdapter(Context context,ChatInfo chatInfo){
        this.context=context;
        this.chatInfo=chatInfo;
    }
    public int getCount(){
        return chatInfo.getChatMsgData().size();
    }
    @Override
    public Object getItem(int position){
        return chatInfo.getChatMsgData().get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    //根据不同的对象获取不同的图像
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //判断消息是接收的还是发送的
        String flag=chatInfo.getChatMsgData().get(position).get(ChatTable.CHAT_MSG_TYPE).toString();
        if(flag.equals(ChatTable.CHAT_MSG_TYPE_SEND)){
            //发送的消息在界面右侧进行实例化
            convertView=View.inflate(context, R.layout.listview_row_chat_msg_right,null);
            //获取具体内容并设置气泡
            TextView tvContent=(TextView)convertView.findViewById(R.id.rightMsg_content);
            tvContent.setText(chatInfo.getChatMsgData().get(position).get(ChatTable.CHAT_MSG_CONTENT).toString());
            tvContent.setFocusable(true);
            tvContent.setOnLongClickListener(this);
            //判断显示时间的属性
            int showTimeFlag=Integer.parseInt(chatInfo.getChatMsgData().get(position).get(ChatTable.SHOW_TIME_FLAG).toString());
            if(showTimeFlag==ChatTable.SHOW_DATE){
                TextView tvLastMsgTime=(TextView)convertView.findViewById(R.id.rightMsg_lastTime);
                String msgTime=chatInfo.getChatMsgData().get(position).get(ChatTable.CHAT_MSG_TIME).toString();
                String date=msgTime.substring(0,msgTime.indexOf(" "));
                tvLastMsgTime.setText(date);
                tvLastMsgTime.setVisibility(View.VISIBLE);
            }else if(showTimeFlag==ChatTable.SHOW_TIME){
                //获取显示时间的组件
                TextView tvLastMsgTime=(TextView)convertView.findViewById(R.id.rightMsg_lastTime);
                //截取消息时间
                String msgTime=chatInfo.getChatMsgData().get(position).get(ChatTable.CHAT_MSG_TIME).toString();
                //截取日期
                String time=msgTime.substring(msgTime.indexOf(" ")+1,msgTime.lastIndexOf(":"));
                //将截取到的字符数组转化为字符串
                String timeResult=String.valueOf(time);
                //设置组件属性
                tvLastMsgTime.setText(timeResult);
                tvLastMsgTime.setVisibility(View.VISIBLE);
            }
            //获取头像的监听事件
            ImageView ivUserHead=(ImageView)convertView.findViewById(R.id.rightMsg_userHead);
            ivUserHead.setOnClickListener(this);
        }else if(flag.equals(ChatTable.CHAT_MSG_TYPE_RECEIVER)){
            //接收的消息在界面左侧进行实例化
            convertView=View.inflate(context,R.layout.listview_row_chat_msg_left,null);
            //获取具体内容并设置气泡
            TextView tvContent=(TextView)convertView.findViewById(R.id.leftMsg_content);
            tvContent.setText(chatInfo.getChatMsgData().get(position).get(ChatTable.CHAT_MSG_CONTENT).toString());
            tvContent.setFocusable(true);
            tvContent.setOnLongClickListener(this);
            //判断是否显示时间
            int showTimeFlag=Integer.parseInt(chatInfo.getChatMsgData().get(position).get(ChatTable.SHOW_TIME_FLAG).toString());
            if(showTimeFlag==ChatTable.SHOW_DATE){
                //获取时间显示组件
                TextView tvLastMsgTime=(TextView)convertView.findViewById(R.id.leftMsg_lastTime);
                //截取消息时间
                String msgTime=chatInfo.getChatMsgData().get(position).get(ChatTable.CHAT_MSG_TIME).toString();
                //截取日期
                String date=msgTime.substring(0,msgTime.indexOf(" "));
                //将截取到的字符数组转化为字符串
                String dateResult=String.valueOf(date);
                //设置组件属性
                tvLastMsgTime.setText(dateResult);
                tvLastMsgTime.setVisibility(View.VISIBLE);
            }else if(showTimeFlag==ChatTable.SHOW_TIME){
                //获取时间显示组件
                TextView tvLastMsgTime=(TextView)convertView.findViewById(R.id.leftMsg_lastTime);
                //截取消息时间
                String msgTime=chatInfo.getChatMsgData().get(position).get(ChatTable.CHAT_MSG_TIME).toString();
                //截取日期,注意报错！
                String time=msgTime.substring(msgTime.indexOf(" "+1),msgTime.lastIndexOf(":"));
                //将截取到的字符数组转化为字符串
                String timeResult=String.valueOf(time);
                //设置组件属性
                tvLastMsgTime.setText(timeResult);
                tvLastMsgTime.setVisibility(View.VISIBLE);
            }
            //获取头像的监听事件
            ImageView ivUserHead=(ImageView)convertView.findViewById(R.id.leftMsg_userHead);
            ivUserHead.setOnClickListener(this);
        }
        return convertView;
    }
    //头像点击事件
    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.leftMsg_userHead:
                intent=new Intent(context, FriendInforActivity.class);
                intent.putExtra(UserTable.USER_ID,chatInfo.getUserID());
                intent.putExtra(FriendTable.FRIEND_ID,chatInfo.getFriendID());
                context.startActivity(intent);
                break;
            case R.id.rightMsg_userHead:
                intent=new Intent(context,FriendInforActivity.class);
                intent.putExtra(UserTable.USER_ID,chatInfo.getUserID());
                intent.putExtra(FriendTable.FRIEND_ID,chatInfo.getFriendID());
                context.startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onLongClick(View v){
        switch(v.getId()){
            case R.id.leftMsg_content:
                Toast.makeText(context,"U Hold this.",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rightMsg_content:
                Toast.makeText(context,"U Hold this.",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
