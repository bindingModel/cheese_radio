package com.cheese.radio.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.Image;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.binding.model.App;
import com.binding.model.util.BaseUtil;
import com.binding.model.view.web.callback.JsBridgeCallback;
import com.bumptech.glide.load.ImageHeaderParserUtils;
import com.cheese.radio.ui.media.play.PlayActivity;
import com.cheese.radio.ui.startup.welcome.WelcomeActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
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
        if (name.length() > 4) return "名字过长";
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

    public static Integer getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static Integer getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static String formatDate(int year, int month) {
        if (month > 12) {
            month -= 12;
            year += 1;
        } else if (month < 1) {
            month += 12;
            year -= 1;
        }
        return year + "-" + month;
    }

    //00:00
    public static String getMinute(Integer seconds) {
        long second = seconds % 60;
        long minutes = (seconds / 60) % 60;
        long hour = seconds / 3600;
        if (hour > 0) {
            return String.format(Locale.getDefault(), "%d:%02d:%02d", hour, minutes, second);
        } else {
            return String.format(Locale.getDefault(), "%02d:%02d", minutes, second);
        }
    }

    public static String stringForTime(long timeMs) {
        long totalSecond = timeMs / 1000;
        long second = totalSecond % 60;
        long minutes = (totalSecond / 60) % 60;
        long hour = totalSecond / 3600;
        if (hour > 0) {
            return String.format(Locale.getDefault(), "%d:%02d:%02d", hour, minutes, second);
        } else {
            return String.format(Locale.getDefault(), "%02d:%02d", minutes, second);
        }
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

    //强制收起虚拟键盘
    public static void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

        }
    }

    public static File compressImage(File file) {
        int degree = getExifOrientation(file.getPath());
        return compressImage(BitmapFactory.decodeFile(file.getPath()), degree);
    }

    public static File compressImage(Bitmap bitmap, int degree) {
        bitmap = rotaingImageView(degree, bitmap);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (options > 0 && baos.toByteArray().length / 1024 > 100) {  //100kb
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            long length = baos.toByteArray().length;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String filename = format.format(date);
        File file = new File(Environment.getExternalStorageDirectory(), filename + ".png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        recycleBitmap(bitmap);
        return file;
    }

    //释放资源
    private static void recycleBitmap(Bitmap... bitmaps) {
        if (bitmaps == null) {
            return;
        }
        for (Bitmap bm : bitmaps) {
            if (null != bm && !bm.isRecycled()) {
                bm.recycle();
            }
        }
    }

    //获取图片的旋转角度
    public static int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }
        }
        return degree;
    }

    //旋转图片
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);

    }

    public static WebView setWebView(WebView webView, String text) {
//        webView.loadUrl("file:///android_asset/test.html");//加载asset文件夹下html
        //   webView.loadUrl("http://139.196.35.30:8080/OkHttpTest/apppackage/test.html");//加载url

        //       使用webview显示html代码
        webView.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);

        //  webView.addJavascriptInterface(this,"android");//添加js监听 这样html就能调用客户端
//        webView.setWebChromeClient(webChromeClient);
//        webView.setWebViewClient(webViewClient);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js
        webSettings.setTextZoom(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        webSettings.setTextSize(WebSettings.TextSize.SMALLER);
        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.

        //支持屏幕缩放
//        webSettings.setSupportZoom(true);
//        webSettings.setBuiltInZoomControls(true);
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        //不显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);
        return webView;
    }

    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
//        return SimpleDateFormat.getDateInstance().format(date);
    }

    public static void setFullScreenView(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 5.0 以上全透明状态栏
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏 加下面几句可以去除透明状态栏的灰色阴影,实现纯透明
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            //6.0 以上可以设置状态栏的字体为黑色.使用下面注释的这行打开亮色状态栏模式,实现黑色字体,白底的需求用这句
            window.setStatusBarColor(Color.WHITE);
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(Color.TRANSPARENT);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static void setWhiteStatus(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 5.0 以上全透明状态栏
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏 加下面几句可以去除透明状态栏的灰色阴影,实现纯透明
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            //6.0 以上可以设置状态栏的字体为黑色.使用下面注释的这行打开亮色状态栏模式,实现黑色字体,白底的需求用这句
            window.setStatusBarColor(Color.WHITE);
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(Color.TRANSPARENT);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static void checkActivity(Activity activity) {
        if (activity instanceof PlayActivity ||
                activity instanceof WelcomeActivity) setFullScreenView(activity.getWindow());
        else setWhiteStatus(activity.getWindow());
    }

}

