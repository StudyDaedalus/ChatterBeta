package com.blessingsoftware.chatterbeta.Fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blessingsoftware.chatterbeta.Activity.SystemSetting;
import com.blessingsoftware.chatterbeta.Activity.UserInforActivity;
import com.blessingsoftware.chatterbeta.Models.Beans.UserInfo;
import com.blessingsoftware.chatterbeta.Models.DAO.UserTable;
import com.blessingsoftware.chatterbeta.Models.Model;
import com.blessingsoftware.chatterbeta.R;

public class MeFragment extends Fragment {
    private View view;
    private ImageView ivUserHead;
    private TextView tvUserName,tvUserAccount;
    private LinearLayout layoutUserInfor;
    private LinearLayout laySetting;
    private int userId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view=inflater.inflate(R.layout.fragment_me,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initEvent();
    }
    //用户基本信息
    private void initView(){
        ivUserHead=(ImageView)view.findViewById(R.id.userInfor_userHead);
        tvUserName=(TextView)view.findViewById(R.id.userInfor_userName);
        tvUserAccount=(TextView)view.findViewById(R.id.userInfor_userAccount);
        laySetting=(LinearLayout)view.findViewById(R.id.lay_setting);
        layoutUserInfor=(LinearLayout)view.findViewById(R.id.lineLayoutEditMyInfo);
    }
    //获取用户ID
    private void initData(){
        userId= Model.getInstance().getDbManager().getUserTableDao().getUserID();
        UserInfo userInfo=Model.getInstance().getDbManager().getUserTableDao().getUserInfo();
        if(userInfo==null){
            Toast.makeText(getActivity(),"网络连接不顺，请检查网络设置！",Toast.LENGTH_SHORT).show();
        }else{
            tvUserName.setText(userInfo.getUserName());
            tvUserName.setText("账号："+userInfo.getUserAccount());
        }
    }
    //个人信息的点击事件
    private void initEvent(){
        layoutUserInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UserInforActivity.class);
                intent.putExtra(UserTable.USER_ID,1);
                startActivity(intent);
            }
        });
        //按钮的监听事件
        laySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SystemSetting.class);
                startActivity(intent);
            }
        });
    }
}
