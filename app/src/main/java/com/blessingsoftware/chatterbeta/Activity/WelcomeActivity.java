package com.blessingsoftware.chatterbeta.Activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blessingsoftware.chatterbeta.Models.Model;
import com.blessingsoftware.chatterbeta.R;
import com.hyphenate.chat.EMClient;

public class WelcomeActivity extends Activity{
    private Handler handler=new Handler() {
        public void handleMessage(Message msg) {
            if (isFinishing()) {
                return;
            }
            isMainOrLogin();
        }
    };

    private ImageView Loading;
    private RelativeLayout welcome_page;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Loading=(ImageView)findViewById(R.id.loading);
        welcome_page=(RelativeLayout)findViewById(R.id.activity_welcome);

        showAnimation();
        handler.sendMessageDelayed(Message.obtain(),3000);
    }
    private void showAnimation(){
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        alphaAnimation.setDuration(1000);
        welcome_page.startAnimation(alphaAnimation);
    }

    private void isMainOrLogin(){
        Model.getInstance().getGlobalTheadPool().execute(new Runnable() {
            @Override
            public void run() {
                if(EMClient.getInstance().isLoggedInBefore()){
                    Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }

    protected void onDestory(){
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

}
