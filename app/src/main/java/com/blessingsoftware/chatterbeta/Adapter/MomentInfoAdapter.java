package com.blessingsoftware.chatterbeta.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blessingsoftware.chatterbeta.Logs.Logs;
import com.blessingsoftware.chatterbeta.Models.Beans.MomentInfo;
import com.blessingsoftware.chatterbeta.R;

import java.util.List;

public class MomentInfoAdapter extends BaseAdapter {
    protected Context context;
    protected List<MomentInfo> momentEntities;

    public MomentInfoAdapter(Context c, List<MomentInfo> m){
        context = c;
        momentEntities = m;
    }

    @Override
    public int getCount() {
        return momentEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return momentEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Logs.d("Adapter","渲染朋友圈");
        if (convertView == null){
            Logs.d("Adapter", "getView() position="+position+" convertView="+convertView);
            convertView = View.inflate(context, R.layout.layout_moment_content, null);
        }
        Logs.d("Adapter",parent.getChildCount()+" ");
        //设置显示数据
        MomentInfo info = momentEntities.get(position);
        Logs.d("Adapter", info.getFriendID()+" "+info.getFriendName());
        //获得View对象
        ImageView headImg = (ImageView) convertView.findViewById(R.id.friend_moment_head);
        TextView friendName = (TextView) convertView.findViewById(R.id.moment_friend_name);
        TextView friendMoment = (TextView) convertView.findViewById(R.id.moment_friend_moment);
        TextView publishTime = (TextView) convertView.findViewById(R.id.moment_publish_time);
//        ImageView  publishImg = (ImageView) convertView.findViewById(R.id.moment_publish_img1);
        //设置数据
        headImg.setImageResource(R.drawable.head); //暂时先用固定的
        friendMoment.setText(info.getPublishText());
        friendName.setText(info.getFriendName());
        publishTime.setText(info.getPublishTime());

        return convertView;
    }
}
