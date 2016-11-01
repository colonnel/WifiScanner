package com.gmail.berezin.serg.wifiscanner;

public class Utils {
    /**
     * Method formats ip  address
     * @param ip
     * @return String
     */
    public static String formatIp(int ip) {
        String ipString = String.format("%d.%d.%d.%d", (ip & 0xff),
                (ip >> 8 & 0xff),
                (ip >> 16 & 0xff),
                (ip >> 24 & 0xff));
        return ipString;
    }
}
