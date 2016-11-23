package com.gmail.berezin.serg.wifiscanner.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gmail.berezin.serg.wifiscanner.R;
import com.gmail.berezin.serg.wifiscanner.models.Element;

import static android.content.ContentValues.TAG;


public class AdapterElements extends ArrayAdapter<Object> {
    private Activity context;
    private Element[] nets;
    private LayoutInflater inflater;

    public AdapterElements(Activity context, Element[] nets) {
        super(context, R.layout.items, nets);
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.nets = nets;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.items, null);

        TextView tvSsid = (TextView) item.findViewById(R.id.tvSSID);
        tvSsid.setText(nets[position].getTitle());

        TextView tvSecurity = (TextView) item.findViewById(R.id.tvSecurity);
        tvSecurity.setText(nets[position].getSecurity());

        TextView tvStrength = (TextView) item.findViewById(R.id.tvStrength);
        String level = nets[position].getLevel();
        try {
            int i = Integer.parseInt(level);
            if (i > -50) {
                tvStrength.setText(R.string.level_hi);
                tvStrength.setTextColor(Color.GREEN);
            } else if (i <= -50 && i > -80) {
                tvStrength.setText(R.string.level_middle);
                tvStrength.setTextColor(Color.MAGENTA);
            } else if (i <= -80) {
                tvStrength.setText(R.string.level_low);
                tvStrength.setTextColor(Color.RED);
            }
        } catch (NumberFormatException e) {
            Log.d(TAG, e.getMessage());
        }

        return item;

    }


}




