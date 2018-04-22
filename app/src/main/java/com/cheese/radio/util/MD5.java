package com.cheese.radio.util;

/**
 * Created by 29283 on 2018/4/17.
 */
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class MD5 {
    public MD5() {
    }

    public static String getMD5(String str, String charset) {
        String ret = null;
        if(true||!TextUtils.isEmpty(str)) {
            try {
                byte[] data = str.getBytes(charset);
                ret = getMD5(data);
            } catch (UnsupportedEncodingException var5) {
                var5.printStackTrace();
            }
        }
        return ret;
    }

    public static String getMD5(byte[] source) {
        String s = null;
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(source);
            byte[] tmp = e.digest();
            char[] str = new char[32];
            int k = 0;

            for(int i = 0; i < 16; ++i) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            s = new String(str);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return s;
    }
}