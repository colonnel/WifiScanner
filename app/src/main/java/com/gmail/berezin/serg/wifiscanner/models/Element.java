package com.gmail.berezin.serg.wifiscanner.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Element implements Parcelable {
    private String title;
    private String security;
    private String level;


    public Element(String title, String security, String level) {
        this.title = title;
        this.security = security;
        this.level = level;
    }

    protected Element(Parcel in) {
        title = in.readString();
        security = in.readString();
        level = in.readString();
    }

    public static final Creator<Element> CREATOR = new Creator<Element>() {
        @Override
        public Element createFromParcel(Parcel in) {
            return new Element(in);
        }

        @Override
        public Element[] newArray(int size) {
            return new Element[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getSecurity() {
        return security;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(security);
        dest.writeString(level);
    }
}
