package com.gmail.berezin.serg.wifiscanner.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gmail.berezin.serg.wifiscanner.R;
import com.gmail.berezin.serg.wifiscanner.models.Element;

/**
 * Created by Admin on 23.10.16.
 */

public class NetInfoActivity extends AppCompatActivity {
    private TextView vNetName;
    private TextView vNetSecurity;
    private TextView vNetStrength;
    public static final String POS_NO = "posNo";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_info);
        vNetName = (TextView) findViewById(R.id.tvNetNameNetInfo);
        vNetSecurity = (TextView) findViewById(R.id.tvNetNameNetInfo);
        vNetStrength = (TextView) findViewById(R.id.tvNetLevelNetInfo);
        getInfo();
    }

    private void getInfo() {
        Element net = (Element) getIntent().getParcelableExtra(POS_NO);
        if (net != null) {
            vNetName.setText(net.getTitle());
            vNetSecurity.setText(net.getSecurity());
            vNetStrength.setText(net.getLevel());
        }

    }
}
