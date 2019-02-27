package com.blessingsoftware.chatterbeta.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blessingsoftware.chatterbeta.ServerRequest.SRequest;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;


import com.blessingsoftware.chatterbeta.Logs.Logs;
import com.blessingsoftware.chatterbeta.Models.Beans.UserInfo;
import com.blessingsoftware.chatterbeta.Models.Model;
import com.blessingsoftware.chatterbeta.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUserAccount;
    private EditText edtUserPassword;
    private Button buttonSignIn;
    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserAccount=(EditText) findViewById(R.id.edt_username);
        edtUserPassword=(EditText) findViewById(R.id.edt_password);
        buttonSignIn=(Button) findViewById(R.id.btn_sign_in);
        buttonSignUp=(Button) findViewById(R.id.btn_sign_up);
        InitListener();
    }

    private void InitListener(){
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivityForResult(intent,666);
            }
        });
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case 666:
                if(resultCode==66){
                    Toast.makeText(this,"注册成功！",Toast.LENGTH_LONG).show();
                    edtUserAccount.setText(data.getStringExtra("account"));
                    edtUserPassword.setText(data.getStringExtra("password"));
                }
        }
    }

    private void Login(){
        final String account=edtUserAccount.getText().toString();
        final String password=edtUserPassword.getText().toString();
        if(TextUtils.isEmpty(account)||TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入账号密码！", Toast.LENGTH_LONG).show();
            return;
        }
        Model.getInstance().getGlobalTheadPool().execute(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().login(account, password, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Model.getInstance().getGlobalTheadPool().execute(new Runnable() {
                            @Override
                            public void run() {
                                Map<String, String> data = new HashMap<String, String>();
                                data.put("username", account);
                                data.put("type", "1");
                                String reDate = SRequest.PostRequest(data);
                                Logs.d("POST", reDate);
                                try {
                                    JSONTokener jsonParser = new JSONTokener(reDate);
                                    JSONObject jsonObject = (JSONObject) jsonParser.nextValue();

                                    UserInfo userInfo = new UserInfo();
                                    userInfo.setUserID(jsonObject.getInt("id"));
                                    userInfo.setUserAccount(jsonObject.getString("username"));
                                    userInfo.setUserName(jsonObject.getString("nickname"));
                                    userInfo.setUserSign(jsonObject.getString("sign"));
                                    userInfo.setUserHead(jsonObject.getString("head"));
                                    userInfo.setUserSex(jsonObject.getString("sex"));
                                    userInfo.setUserLocation(jsonObject.getString("location"));
                                    Model.getInstance().getDbManager().getUserTableDao().addUserAccount(userInfo);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(int i, String s) {
                        Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }

                /*EMClient.getInstance.login(account,password,new EMCallback(){
                    @Override
                    public void onSuccess(){
                        Model.getInstance().getGlobalTheadPool().execute(new Runnable() {
                            @Override
                            public void run() {
                                Map<String,String > data=new HashMap<String, String>();
                                data.put("username",account);
                                data.put("type","1");
                                String reDate=SRequest.PostRequest(data);
                                Logs.d("POST",reDate);
                                try {
                                    JSONTokener jsonParser = new JSONTokener(reDate);
                                    JSONObject jsonObject = (JSONObject) jsonParser.nextValue();

                                    UserInfo userInfo = new UserInfo();
                                    userInfo.setUserID(jsonObject.getInt("id"));
                                    userInfo.setUserAccount(jsonObject.getString("username"));
                                    userInfo.setUserName(jsonObject.getString("nickname"));
                                    userInfo.setUserSign(jsonObject.getString("sign"));
                                    userInfo.setUserHead(jsonObject.getString("head"));
                                    userInfo.setUserSex(jsonObject.getString("sex"));
                                    userInfo.setUserLocation(jsonObject.getString("location"));
                                    Model.getInstance().getDbManager().getUserTableDao().addUserAccount(userInfo);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
            }
            @Override
                    public void onError(int i,String s){
                        Toast.makeText(LoginActivity.this,"登录失败！",Toast.LENGTH_LONG).show();
            }
            @Override
                    public void onProgress(int i,String s){}
        });
            }
        });
    }*/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                moveTaskToBack(true);
                break;
        }
        return true;
    }

}
