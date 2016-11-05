package com.gmail.berezin.serg.wifiscanner.activiries;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.berezin.serg.wifiscanner.R;
import com.gmail.berezin.serg.wifiscanner.Utils;

public class CurrentNetActivity extends AppCompatActivity {
    private TextView vSsid;
    private TextView vMac;
    private TextView vSpeed;
    private TextView vIp;
    private TextView vFrequency;
    private TextView vRssi;
    private ImageView vHiddenSsid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_net);
        vSsid = (TextView) findViewById(R.id.tvNameInfo);
        vMac = (TextView) findViewById(R.id.tvMacInfo);
        vSpeed = (TextView) findViewById(R.id.tvSpeedInfo);
        vIp = (TextView) findViewById(R.id.tvIpInfo);
        vFrequency = (TextView) findViewById(R.id.tvFrequencyInfo);
        vRssi = (TextView) findViewById(R.id.tvRssiInfo);
        vHiddenSsid = (ImageView) findViewById(R.id.ivHiddenSsid);
        Button vButtonGetInfo = (Button) findViewById(R.id.btnGetInfo);
        vButtonGetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCurrentNetInfo();
            }
        });

    }

    private void getCurrentNetInfo() {
        WifiManager mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (mWifiManager != null) {
            if (mWifiManager.isWifiEnabled()) {
                WifiInfo currentNetInfo = mWifiManager.getConnectionInfo();
                String ssid = currentNetInfo.getSSID(); //service set identifier (SSID) of the current 802.11 network
                String macAddress = currentNetInfo.getMacAddress();
                int linkSpeed = currentNetInfo.getLinkSpeed(); //current link speed
                int ip = currentNetInfo.getIpAddress();
                String ipString = Utils.formatIp(ip);
                boolean hiddenSsid = currentNetInfo.getHiddenSSID();
                int rssi = currentNetInfo.getRssi(); //received signal strength indicator of the current 802.11 network, in dBm
                //fill forms
                vSsid.setText(ssid);
                if (hiddenSsid) {
                    vHiddenSsid.setImageResource(R.drawable.ic_block_helper_black_24dp);
                } else {
                    vHiddenSsid.setImageResource(R.drawable.ic_check_circle_outline_black_24dp);
                }
                vMac.setText(macAddress);
                vSpeed.setText(linkSpeed + " Mbps");
                vIp.setText(ipString);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int frequency = currentNetInfo.getFrequency(); //current frequency
                    vFrequency.setVisibility(View.VISIBLE);
                    vFrequency.setText(frequency + " Mhz");
                }
                vRssi.setText(rssi + " dBm");
            }
        }
    }
    private void checkConnection(WifiManager wifiManager){
        if (wifiManager.isWifiEnabled()){
            Toast.makeText(CurrentNetActivity.this, "Enable WiFi!", Toast.LENGTH_SHORT);
        }
    }
}
