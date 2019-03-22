package com.blessingsoftware.chatterbeta.Ui;

import android.app.Instrumentation;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toolbar;

import com.blessingsoftware.chatterbeta.R;

public class CustomToolbar extends Toolbar {
    private View overflowButton;
    private OnOverflowClickListener onOverflowClickListener;

    public CustomToolbar(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar);
        this.setTitle(ta.getString(R.styleable.CustomToolbar_titleText));
        this.setTitleTextColor(Color.WHITE);
        this.setNavigationIcon(R.drawable.back);
        this.inflateMenu(R.menu.toolbar_custom_overflow);
        this.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    public void run(){
                        Instrumentation inst=new Instrumentation();
                        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                    }
                }.start();
            }
        });
        overflowButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onOverflowClickListener==null){
                    return;
                }
                onOverflowClickListener.onOverflowClick();
            }
        });
    }
    public void setOverflowImg(int iconRes){
        this.getMenu().getItem(0).setIcon(iconRes);
    }
    public void setOnOverflowTitle(String title){
        this.getMenu().getItem(0).setTitle(title);
    }
    public void setOnOverflowClickListener(OnOverflowClickListener onOverflowClickListener){
        this.onOverflowClickListener=onOverflowClickListener;
        //this.OnOverflowClickListener=onOverflowClickListener;
    }
    public interface OnOverflowClickListener{
        public void onOverflowClick();
    }
}
