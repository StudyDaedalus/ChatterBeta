package com.blessingsoftware.chatterbeta.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blessingsoftware.chatterbeta.Activity.MomentActivity;
import com.blessingsoftware.chatterbeta.Models.Model;
import com.blessingsoftware.chatterbeta.R;

public class FoundFragment extends Fragment implements View.OnClickListener {
    private LinearLayout enterMoment;
    private View view;
    private int userId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        view=inflater.inflate(R.layout.fragment_found,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initEvent();
    }
    private void initView(){
        enterMoment=(LinearLayout)view.findViewById(R.id.enterMoment);
    }
    private void initData(){
        userId= Model.getInstance().getDbManager().getUserTableDao().getUserID();
    }
    private void initEvent(){
        enterMoment.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.enterMoment:
                Intent moment=new Intent(getActivity(), MomentActivity.class);
                startActivity(moment);
                break;
        }
    }
}
