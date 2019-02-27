package com.blessingsoftware.chatterbeta.Activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blessingsoftware.chatterbeta.Models.Model;
import com.blessingsoftware.chatterbeta.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends Activity implements View.OnClickListener {
    private Button buttonSignUpSubmit;
    private EditText edtAccount;
    private EditText edtName;
    private EditText edtSex;
    private EditText edtLocation;
    private EditText edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        buttonSignUpSubmit=(Button) findViewById(R.id.button_sign_up_submit);
        edtAccount=(EditText)findViewById(R.id.edt_sign_up_account);
        edtName=(EditText)findViewById(R.id.edt_sign_up_name);
        edtSex=(EditText)findViewById(R.id.edt_sign_up_sex);
        edtLocation=(EditText)findViewById(R.id.edt_sign_up_location);
        edtPassword=(EditText)findViewById(R.id.edt_sign_up_password);
        buttonSignUpSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        final String account=edtAccount.getText().toString();
        String name=edtName.getText().toString();
        String location=edtLocation.getText().toString();
        final String sex=edtSex.getText().toString();
        final String password=edtPassword.getText().toString();

        if(TextUtils.isEmpty(account)||TextUtils.isEmpty(name)||TextUtils.isEmpty(location)||TextUtils.isEmpty(sex)||TextUtils.isEmpty(location)){
            Toast.makeText(this,"请输入完整的信息！",Toast.LENGTH_LONG).show();
            return;
        }

        final Map<String,String> data=new HashMap<String, String>();
        data.put("username",account);
        data.put("password",password);
        data.put("location",location);
        data.put("sex",sex);
        data.put("nickname",name);
        data.put("type","2");//注册请求
        Model.getInstance().getGlobalTheadPool().execute(new Runnable() {
            @Override
            public void run() {
                String reDate=SRequest.PostRequest(data);
                try{
                    JSONTokener jsonParser=new JSONTokener(reDate);
                    JSONObject jsonObject=(JSONObject) jsonParser.nextValue();
                    if(Integer.parseInt(jsonObject.getString("error"))==0){
                        getIntent().putExtra("account",account);
                        getIntent().putExtra("password",password);
                        setResult(666,getIntent());
                        finish();
                    }else {
                        Toast.makeText(SignUpActivity.this,"注册失败，请检查后重试！",Toast.LENGTH_LONG).show();
                    }
                    }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
