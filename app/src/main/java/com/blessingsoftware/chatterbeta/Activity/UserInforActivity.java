package com.blessingsoftware.chatterbeta.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.blessingsoftware.chatterbeta.Models.Beans.UserInfo;
import com.blessingsoftware.chatterbeta.Models.DAO.UserTable;
import com.blessingsoftware.chatterbeta.Models.Model;
import com.blessingsoftware.chatterbeta.R;
import com.blessingsoftware.chatterbeta.Ui.CustomToolbar;

public class UserInforActivity extends AppCompatActivity {

    private int userId;
    private UserInfo userInfor;

    private TextView userName, userAccount, userLocation, userSex, userSign;
    private CustomToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infor);

        initView();
        initData();
        initEvent();

    }

    public void initView() {

        toolbar = (CustomToolbar) findViewById(R.id.userInfor_toolbar);
        toolbar.setTitle("详细信息");

        userName = (TextView) findViewById(R.id.userInfor_userName);
        userAccount = (TextView) findViewById(R.id.userInfor_userAccount);
        userLocation = (TextView) findViewById(R.id.userInfor_userLocation);
        userSex = (TextView) findViewById(R.id.userInfor_userSex);
        userSign = (TextView) findViewById(R.id.userInfor_userSign);
    }

    public void initData() {

        userId = getIntent().getIntExtra(UserTable.USER_ID, -1);
        userInfor = Model.getInstance().getDbManager().getUserTableDao().getUserInfo();

        userName.setText(userInfor.getUserName());
        userAccount.setText(userInfor.getUserAccount());
        userLocation.setText(userInfor.getUserLocation());
        userSex.setText(userInfor.getUserSex());
        userSign.setText(userInfor.getUserSign());

    }


    public void initEvent(){}
}
