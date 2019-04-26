package com.cheese.radio.util;

import android.text.TextUtils;

import com.binding.model.data.encrypt.des.BASE64Decoder;
import com.binding.model.data.encrypt.des.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import timber.log.Timber;

public class PasswordEncrypt {
    private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding";
//    private static final String ALGORITHM_STR = "AES/CTR/NoPadding";
    private static final String ALGORITHM = "AES";
    private static final String secretKey = "474ce47b42922b58";
    private static final String UTF8="UTF-8";

    public static String encrypt(String password) {
        if(TextUtils.isEmpty(password))return null;
        String aesValue = aesEncrypt(password);//aes
//        String base64Value = base64Encrypt(aesValue);//base64
//        String urlEncoder = urlEncoder(aesValue);
//        Timber.i("加密：password:%1s\naes=%2s\nbase64=%3s\nurlEncoder=%4s",password,aesValue,base64Value,urlEncoder);
//        Timber.i("加密：password:%1s\naes=%2s\nurlEncoder=%3s",password,aesValue,urlEncoder);
        return aesValue;
    }

    private static String urlEncoder(String base64Value) {
        if(TextUtils.isEmpty(base64Value))return null;
        try {
            return URLEncoder.encode(base64Value,UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String aesEncrypt(byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return new BASE64Encoder().encode(cipher.doFinal(data));
    }


    public static String aesEncrypt(String data) {
        try {
            if(TextUtils.isEmpty(data))return null;
            return aesEncrypt(data.getBytes());
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES解密
     * @param base64Data
     * @return
     * @throws Exception
     */
    public static String decryptData(String base64Data) throws Exception{
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(base64Data)));
    }


    public static String base64Encrypt(byte[] data){
        return (new BASE64Encoder()).encodeBuffer(data);
    }

    public static String base64Encrypt(String data){
        try {
            if(TextUtils.isEmpty(data))return null;
            return base64Encrypt(data.getBytes(UTF8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        if(str == null)return null;
        try {
            b = str.getBytes(UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, UTF8);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
