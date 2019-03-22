package com.blessingsoftware.chatterbeta.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.blessingsoftware.chatterbeta.Adapter.MomentInfoAdapter;
import com.blessingsoftware.chatterbeta.Models.Beans.MomentInfo;
import com.blessingsoftware.chatterbeta.Models.Model;
import com.blessingsoftware.chatterbeta.R;
import com.blessingsoftware.chatterbeta.Ui.CustomToolbar;

import java.util.List;

public class MomentActivity extends Activity {
    CustomToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment);

        toolbar = (CustomToolbar) findViewById(R.id.moment_toolbar);
        toolbar.setTitle("朋友圈");

        ListView lv_moment = (ListView) findViewById(R.id.lv_moment_info);
        List<MomentInfo> momentEntities = Model.getInstance().getDbManager().getMomentTableDao().getAllMomentInfo();
        MomentInfoAdapter momentInfoAdapter = new MomentInfoAdapter(MomentActivity.this, momentEntities);
        lv_moment.addHeaderView(this.getLayoutInflater().inflate(R.layout.layout_moment_heading,null),
                null,false);
        //设置Adapter显示
        lv_moment.setAdapter(momentInfoAdapter);
    }
}
