package com.cheese.radio.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;

import com.binding.model.App;
import com.binding.model.util.BaseUtil;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 29283 on 2018/3/11.
 */

public class MyBaseUtil extends BaseUtil {
    private static String macAddress = null;
    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    private static Random random = new Random();

    public static String getMacAddress() {
        if (macAddress != null) return macAddress;

        WifiManager wifiManager =
                (WifiManager) App.getCurrentActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null == wifiManager ? null : wifiManager.getConnectionInfo());

        if (!wifiManager.isWifiEnabled()) {
            //必须先打开，才能获取到MAC地址
            wifiManager.setWifiEnabled(true);
            wifiManager.setWifiEnabled(false);
        }
        if (null != info) {
            macAddress = info.getMacAddress();
        }
        return macAddress;
    }

    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String[] getSort(String[] array) {
        return array;
    }

    public static String getRandoms(int length) {
        String array = "";

        for (int i = 0; i < length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                array += CHARS[random.nextInt(CHARS.length)];
            } else return "abcd1234";
        }
        return array;
    }

    public static String getSign(HashMap<String, String> argParams) {
//        String randomString="Qyw6tX4f";
        String randomString = MyBaseUtil.getRandoms(8);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        String[] paramArray = new String[argParams.size()];
        Iterator<HashMap.Entry<String, String>> iterator = argParams.entrySet().iterator();
        while (iterator.hasNext()) {
            sb.setLength(0);
            HashMap.Entry<String, String> en = iterator.next();
            paramArray[index++] = sb.append(en.getKey()).append("=").append(en.getValue()).toString();
        }
        Arrays.sort(paramArray);
        sb.setLength(0);
        for (int i = 0; i < paramArray.length; ++i) {
            sb.append(paramArray[i]).append("&");
        }
        sb.setLength(sb.length() - 1);
        sb.append(randomString);
        //MD5码必须转换成大写，否则报错.
        return randomString + MD5.getMD5(sb.toString(), "UTF-8").toUpperCase();
    }

    //昵称限制4~20
    public static String getNickError(String nickname) {
        if (TextUtils.isEmpty(nickname)) return "昵称不能为空";
        Pattern p = Pattern.compile("^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+");
        Matcher m = p.matcher(nickname);
        boolean valid = m.matches();
        return valid ? null : "不合法的昵称";
    }

    public static String getNameError(String name) {
        if (TextUtils.isEmpty(name)) return "没有填写名字哦";
        if(name.length()>4)return "名字过长";
        Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]{1,4}");
        Matcher m = p.matcher(name);
        boolean valid = m.matches();
        return valid ? null : "名字应填写中文";
    }

    public static String getNowDay() {
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR) + "";
        String month = calendar.get(Calendar.MONTH) + 1 + "";
        String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        return year + "-" + month + "-" + day;
    }

    public static String getMinute(Integer seconds) {
        return (seconds / 60) + ":" + (seconds % 60);
    }

    public static Integer[] getNowDate() {
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String string = formatter.format(now);
        String[] nowString = string.split("-");
        nowString[2] = nowString[2].substring(0, 2);
        Integer[] nowInteger = new Integer[3];
        for (int i = 0; i < 3; i++) {
            nowInteger[i] = Integer.valueOf(nowString[i]);
        }
        return nowInteger;
    }
}

