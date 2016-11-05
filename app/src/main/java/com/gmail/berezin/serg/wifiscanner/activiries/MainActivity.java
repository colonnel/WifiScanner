package com.gmail.berezin.serg.wifiscanner.activiries;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gmail.berezin.serg.wifiscanner.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button vButtonChekConnect;
    Button vButtonGetInfo;
    Button vButtonScanNets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vButtonChekConnect = (Button) findViewById(R.id.btnCheckConnect);
        vButtonGetInfo = (Button) findViewById(R.id.btnGetInfoMain);
        vButtonScanNets = (Button) findViewById(R.id.btnScanNets);
        vButtonChekConnect.setOnClickListener(this);
        vButtonGetInfo.setOnClickListener(this);
        vButtonScanNets.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCheckConnect:
                checkConnection();
                return;
            case R.id.btnGetInfoMain:
                Intent intent = new Intent(MainActivity.this, CurrentNetActivity.class);
                startActivity(intent);
                return;
            case R.id.btnScanNets:
                Intent intent1 = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intent1);
                return;
        }
    }

    private void checkConnection() {
        WifiManager mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (mWifiManager.isWifiEnabled()) {
            Toast.makeText(this, "WiFi is enable.", Toast.LENGTH_SHORT);
        } else {
            Toast.makeText(this, "WiFi is disable! Pleas enable", Toast.LENGTH_SHORT);
        }
    }
}
