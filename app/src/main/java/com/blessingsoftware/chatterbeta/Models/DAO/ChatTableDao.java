package com.blessingsoftware.chatterbeta.Models.DAO;

/*

    FINISH at Dec,3rd,2018
    written by CAT from Nov,30th,2018 to Dec,3rd,2018

 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.blessingsoftware.chatterbeta.Models.Beans.ChatInfo;
import com.blessingsoftware.chatterbeta.Models.DB.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatTableDao {

    private DBHelper dbHelper;

    public ChatTableDao(DBHelper helper){
        dbHelper=helper;
    }

    public List<Map<String,Object>> getChatList(int userID){
        //创建数组？ -11.30
        List<Map<String,Object>> chatList=new ArrayList<Map<String, Object>>();
        Map<String,Object> map;
        SQLiteDatabase db=dbHelper.getReadableDatabase();//创建可以获得的数据库，因为DBHelper未完成所以爆红，应该。 -11.30
        //查询最后一条聊天记录的时间 -11.30
        String lastChatTimeSql="SELECT DISTINCT friend_id,max(chat_msg_time) as chat_msg_time FROM chat_msg WHERE user_id=? GROUP BY friend_id ORDER BY chat_msg_time DESC;";
        Cursor lastChatTimeCursor=db.rawQuery(lastChatTimeSql,new String[]{""+userID});

        while(lastChatTimeCursor.moveToNext()){
            //获取好友的ID以及最后聊天的时间 -11.30
            int friendID=lastChatTimeCursor.getInt(lastChatTimeCursor.getColumnIndex(ChatTable.FRIEND_ID));
            String lastChatTime=lastChatTimeCursor.getString(lastChatTimeCursor.getColumnIndex(ChatTable.CHAT_MSG_TIME));
            //根据好友查询最后一次聊天时间，获取好友昵称、头像、聊天内容、最后聊天时间（不保证正确，需要后续修改） -11.30
            String chatListSql="SELECT chat.user_id,chat.friend_id,friend_name,friend_head, "
                    +"friend_expand_relation,chat_msg_content,chat_msg_time "
                    +"FROM user_info user,friend_info friend,chat_msg chat "
                    +"WHERE user.user_id=chat.user_id AND friend.friend_id=chat.friend_id "
                    +"AND chat.friend_id=? AND chat_msg_time=?;";
            Cursor chatListCursor=db.rawQuery(chatListSql,new String[]{""+friendID,lastChatTime});
            while (chatListCursor.moveToNext()){
                map=new HashMap<String,Object>();
                map.put(FriendTable.FRIEND_ID,chatListCursor.getString(chatListCursor.getColumnIndex(FriendTable.FRIEND_ID)));
                map.put(FriendTable.FRIEND_HEAD,chatListCursor.getString(chatListCursor.getColumnIndex(FriendTable.FRIEND_HEAD)));//开始补上 -12.3
                map.put(FriendTable.FRIEND_NAME,chatListCursor.getString(chatListCursor.getColumnIndex(FriendTable.FRIEND_NAME)));
                map.put(FriendTable.FRIEND_EXPAND_RELATION,chatListCursor.getString(chatListCursor.getColumnIndex(FriendTable.FRIEND_EXPAND_RELATION)));
                map.put(ChatTable.CHAT_MSG_CONTENT,chatListCursor.getString(chatListCursor.getColumnIndex(ChatTable.CHAT_MSG_CONTENT)));
                map.put(ChatTable.CHAT_MSG_TIME,chatListCursor.getString(chatListCursor.getColumnIndex(ChatTable.CHAT_MSG_TIME)));
                chatList.add(map);
            }
        }
        db.close();
        return chatList;
    }

    public ChatInfo getChatMsgInfo(int userID,int friendID){

        ChatInfo chatInfo=new ChatInfo();
        List<Map<String,Object>>chatMsgList=new ArrayList<>();
        Map<String,Object>map;

        chatInfo.setUserID(userID);
        chatInfo.setFriendID(friendID);

        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String Sql="SELECT chat.user_id,user_name,user_head,chat.friend_id,friend_name,friend_head, "
                +"friend_expand_relation,chat_msg_content,chat_msg_time,chat_msg_type,show_time_flag "
                +"FROM chat_msg chat,user_info user,friend_info friend "
                +"WHERE chat.user_id =user.user_id AND friend.friend_id =chat.friend_id AND "
                +"chat.user_id=? AND chat.friend_id=? ORDER BY chat_msg_time;";
        Cursor cursor=db.rawQuery(Sql,new String[]{""+userID,""+friendID});
        if(cursor.moveToNext()) {
            chatInfo.setUserName(cursor.getString(cursor.getColumnIndex(UserTable.USER_NAME)));
            chatInfo.setUserHead(cursor.getString(cursor.getColumnIndex(UserTable.USER_HEAD)));
            chatInfo.setFriendName(cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_NAME)));
            chatInfo.setFriendHead(cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_HEAD)));
            chatInfo.setFriendExpandRelation(cursor.getString(cursor.getColumnIndex(FriendTable.FRIEND_EXPAND_RELATION)));

            map = new HashMap<String, Object>();
            map.put(ChatTable.CHAT_MSG_CONTENT, cursor.getString(cursor.getColumnIndex(ChatTable.CHAT_MSG_CONTENT)));
            map.put(ChatTable.CHAT_MSG_TIME, cursor.getString(cursor.getColumnIndex(ChatTable.CHAT_MSG_TIME)));
            map.put(ChatTable.CHAT_MSG_TYPE, cursor.getString(cursor.getColumnIndex(ChatTable.CHAT_MSG_TYPE)));
            map.put(ChatTable.SHOW_TIME_FLAG, cursor.getString(cursor.getColumnIndex(ChatTable.SHOW_TIME_FLAG)));
            chatMsgList.add(map);
        }else {
            String friendSql="SELECT friend_name,friend_head FROM friend_info WHERE friend_id=?";
            Cursor friendInfoCursor=db.rawQuery(friendSql,new String[]{""+friendID});
            if(friendInfoCursor.moveToNext()){
                chatInfo.setFriendName(friendInfoCursor.getString(friendInfoCursor.getColumnIndex(FriendTable.FRIEND_NAME)));
                chatInfo.setFriendHead(friendInfoCursor.getString(friendInfoCursor.getColumnIndex(FriendTable.FRIEND_HEAD)));
            }
            chatInfo.setChatMsgData(chatMsgList);
            return chatInfo;
        }
        while (cursor.moveToNext()){
            map=new HashMap<String, Object>();
            map.put(ChatTable.CHAT_MSG_CONTENT,cursor.getString(cursor.getColumnIndex(ChatTable.CHAT_MSG_CONTENT)));
            map.put(ChatTable.CHAT_MSG_TIME,cursor.getString(cursor.getColumnIndex(ChatTable.CHAT_MSG_TIME)));
            map.put(ChatTable.CHAT_MSG_TYPE,cursor.getString(cursor.getColumnIndex(ChatTable.CHAT_MSG_TYPE)));
            map.put(ChatTable.SHOW_TIME_FLAG,cursor.getString(cursor.getColumnIndex(ChatTable.SHOW_TIME_FLAG)));
            chatMsgList.add(map);
        }
        chatInfo.setChatMsgData(chatMsgList);
        db.close();
        return chatInfo;
    }

    public void insertNewChatMsg(int userID,int friendID,String msgContent,String time,String type,int showTimeFlag){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String chatSql="INSERT INTO chat_msg(user_id,friend_id,chat_msg_content,chat_msg_time,chat_msg_type,show_time_flag)"
                +" values("+userID+", "+friendID+", '"+msgContent+"', '"+time+"', '"+type+"', '"+showTimeFlag+")";
        db.execSQL(chatSql);
        db.close();
    }
}
