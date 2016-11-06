package com.gmail.berezin.serg.wifiscanner.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gmail.berezin.serg.wifiscanner.R;
import com.gmail.berezin.serg.wifiscanner.models.Element;


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
            vNetName.setText(getString(R.string.network_name_ssid_info) + net.getTitle());
            vNetSecurity.setText("Security: " + net.getSecurity());
            vNetStrength.setText("Strength: " + net.getLevel());
        }

    }
}
