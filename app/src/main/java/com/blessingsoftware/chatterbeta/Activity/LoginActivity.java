package com.blessingsoftware.chatterbeta.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blessingsoftware.chatterbeta.Models.Model;
import com.blessingsoftware.chatterbeta.R;

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
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
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
                EMClient.getInstance.login(account,password,new EMCallback)
            }
        });
    }

}
