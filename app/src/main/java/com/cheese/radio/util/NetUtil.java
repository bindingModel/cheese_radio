package com.cheese.radio.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.binding.model.App;
import com.cheese.radio.ui.CheeseApplication;

public class NetUtil {
    static WifiManager wifiManager;
    private static String macAddress = CheeseApplication.getUser().getMac();

    public static String getMacAddress() {

        if (!TextUtils.isEmpty(macAddress)) return macAddress;
        if (wifiManager == null) wifiManager =
                (WifiManager) App.getCurrentActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null == wifiManager ? null : wifiManager.getConnectionInfo());

        if (!wifiManager.isWifiEnabled()) {
            //必须先打开，才能获取到MAC地址
            wifiManager.setWifiEnabled(true);
            wifiManager.setWifiEnabled(false);
        }

        if (null != info) {
            CheeseApplication.getUser().setMac(info.getMacAddress());
            macAddress = CheeseApplication.getUser().getMac();
            return macAddress;
        }

        return null;
    }

    public static void checkNetType(Context context) {
        if (wifiManager == null)  wifiManager =
                (WifiManager) App.getCurrentActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null == wifiManager ? null : wifiManager.getConnectionInfo());
        if (!wifiManager.isWifiEnabled())
            new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setTitle("温馨提示")
                    .setMessage("你没有开启无线网络，这将会消耗你大量的流量")
                    .setPositiveButton("去打开", (dialog1, which) -> {
                        wifiManager.setWifiEnabled(true);
                    })
                    .setNegativeButton("任性！", null)
                    .show()
                    .setOnDismissListener(DialogInterface::dismiss);
    }
}
