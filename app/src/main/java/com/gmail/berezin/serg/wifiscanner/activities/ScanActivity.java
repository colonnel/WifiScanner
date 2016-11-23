package com.gmail.berezin.serg.wifiscanner.activities;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.gmail.berezin.serg.wifiscanner.R;
import com.gmail.berezin.serg.wifiscanner.adapters.AdapterElements;
import com.gmail.berezin.serg.wifiscanner.models.Element;

import java.util.List;

public class ScanActivity extends AppCompatActivity {
    private Element[] nets;
    private WifiManager mWifiManager;
    private List<ScanResult> mWifiList;
    private AdapterElements mAdapterElements;
    private static final String TAG = "myLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detectWifi();
                Snackbar.make(view, "Scanning...", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method scans networks via wifi
     */
    private void detectWifi() {
        this.mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        this.mWifiManager.startScan();
        this.mWifiList = this.mWifiManager.getScanResults();

        Log.d(TAG, mWifiList.toString());

        this.nets = new Element[mWifiList.size()];
        for (int i = 0; i < mWifiList.size(); i++) {
            String item = mWifiList.get(i).toString();
            String[] vectorItem = item.split(",");
            String item_essid = vectorItem[0];
            String item_capabilities = vectorItem[2];
            String item_level = vectorItem[3];
            String ssid = item_essid.split(": ")[1];
            String security = item_capabilities.split(": ")[1];
            String level = item_level.split(": ")[1];
            nets[i] = new Element(ssid, security, level);
        }

        mAdapterElements = new AdapterElements(this, nets);
        ListView netList = (ListView) findViewById(R.id.listItem);
        netList.setAdapter(mAdapterElements);

    }

}
