package com.cheese.radio.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import com.cheese.radio.ui.IkeApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by 29283 on 2018/3/11.
 */

public class MyBaseUtil {

    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    private static Random random = new Random();

    public static String getMacAddress() {

        String macAddress =null;
        WifiManager wifiManager =
                (WifiManager) IkeApplication.getApp().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null== wifiManager ?null: wifiManager.getConnectionInfo());

        if(!wifiManager.isWifiEnabled())
        {
            //必须先打开，才能获取到MAC地址
            wifiManager.setWifiEnabled(true);
            wifiManager.setWifiEnabled(false);
        }
        if(null!= info) {
            macAddress = info.getMacAddress();
        }
        return macAddress;
    }

    public static String getTimestamp(){
       return String.valueOf(System.currentTimeMillis());
    }

    public static String[] getSort(String[] array){
        return array;
    }

    public static String getRandoms(int length){
         String array = "";

        for(int i=0;i<length;i++){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
              array +=CHARS[random.nextInt(CHARS.length)];
            }
            else return "abcd1234";
        }
        return array;
    }

    public static String getSign(HashMap<String,String> argParams){
        String randomString =MyBaseUtil.getRandoms(8);
        StringBuilder sb=new StringBuilder();
        int index =0;
        String[] paramArray=new String[argParams.size()];
        Iterator<HashMap.Entry<String,String>> iterator=argParams.entrySet().iterator();
        while (iterator.hasNext()){
            sb.setLength(0);
            HashMap.Entry<String,String> en =iterator.next();
            paramArray[index++] =sb.append(en.getKey()).append("=").append(en.getValue()).toString();
        }

        Arrays.sort(paramArray);
        sb.setLength(0);
        for(int i=0;i<paramArray.length;++i){
            sb.append(paramArray[i]).append("&");
        }
        sb.setLength(sb.length()-1);
        sb.append(randomString);
        //MD5码必须转换成大写，否则报错.
        return randomString+MD5.getInstance().getMD5(sb.toString()).toUpperCase();
    }


}
