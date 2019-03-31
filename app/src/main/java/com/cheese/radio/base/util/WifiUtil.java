package com.cheese.radio.base.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.AuthAlgorithm;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.binding.model.App;

import java.util.List;

/**
 * Created by arvin on 2017/12/29.
 */

public class WifiUtil {
    private final WifiManager wifiManager;
    public static WifiUtil getInstance() {
        return Builder.builder();
    }

    private WifiUtil() {
        this.wifiManager = (WifiManager) App.getCurrentActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    public static boolean isEnable() {
        return getInstance().wifiManager.isWifiEnabled();
    }

    public static int getIpTransAddress(){
        int ip = getIpAddress();
        int a =  ip&0xFF;
        int b = (ip >> 8) & 0xFF;
        int c = (ip >> 16) & 0xFF;
        int d = (ip >> 24) & 0xFF;

        int i= a<<24|b<<16|c<<8|d;
//        Timber.i("InfoFactory:%1s",AgreeUtil.intToIp(i));
        return i;
    }

//     public static String intToIp(int i) {
//        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
//}

    public static int getIpAddress() {
        return getInstance().wifiManager.getConnectionInfo().getIpAddress();
    }

    public static int getIpGroup(){
        return 0xFF000000| getInstance().wifiManager.getConnectionInfo().getIpAddress();
    }


    public static WifiInfo getWifiInfo(){
        return getInstance().wifiManager.getConnectionInfo();
    }

    private static WifiConfiguration IsExsits(String SSID) {
        List<WifiConfiguration> existingConfigs = getInstance().wifiManager.getConfiguredNetworks();
        for (WifiConfiguration existingConfig : existingConfigs) {
            if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                return existingConfig;
            }
        }
        return null;
    }

    public static List<ScanResult> getScanResults() {
        return getInstance().wifiManager.getScanResults();
    }



    public static void enabelNetwork(String SSID,String password,int type){
        int wcgID = getInstance().wifiManager.addNetwork(WifiUtil.connectWifi(SSID, password, type));
        if(getInstance().wifiManager.enableNetwork(wcgID, true)){
            getInstance().wifiManager.saveConfiguration();
        }

    }

    private ScanResult getScanResultsBySSID(String ssid){
        List<ScanResult> list = wifiManager.getScanResults();
        for (ScanResult result : list){
            if (TextUtils.equals(result.SSID, ssid)){
                return result;
            }
        }
        return null;
    }

    public static boolean isWifiEnabled() {
        return getInstance().wifiManager.isWifiEnabled();
    }


    public static class Builder{
        private static WifiUtil wifiUtil;

        public static WifiUtil builder() {
            if(wifiUtil ==  null){
                synchronized (Builder.class){
                    if(wifiUtil == null){
                        wifiUtil = new WifiUtil();
                    }
                }
            }
            return wifiUtil;
        }
    }

    public static boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getCurrentActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiNetworkInfo.isConnected();
    }


    private ScanResult mresult;
    private String ssid;
    private int networkId;
    private WifiConfiguration mConfig;
    private static final int SECURITY_NONE = 0;
    private static final int SECURITY_WEP = 1;
    private static final int SECURITY_PSK = 2;
    private static final int SECURITY_EAP = 3;
    private static final int INVALID_NETWORK_ID = -1;

    private static String convertToQuotedString(String string) {
        return "\"" + string + "\"";
    }

    public WifiConfiguration getConfig(String password,ScanResult result) {
        WifiConfiguration config = new WifiConfiguration();

        if (networkId == INVALID_NETWORK_ID) {
            config.SSID = convertToQuotedString(ssid);
        } else {
            config.networkId = networkId;
        }
        switch (getSecurity(result)) {
            case SECURITY_NONE:
                config.allowedKeyManagement.set(KeyMgmt.NONE);
                break;
            case SECURITY_WEP:
                config.allowedKeyManagement.set(KeyMgmt.NONE);
                config.allowedAuthAlgorithms.set(AuthAlgorithm.OPEN);
                config.allowedAuthAlgorithms.set(AuthAlgorithm.SHARED);
                if (password.length() != 0) {
                    int length = password.length();
                    // WEP-40, WEP-104, and 256-bit WEP (WEP-232?)
                    if ((length == 10 || length == 26 || length == 58)
                            && password.matches("[0-9A-Fa-f]*")) {
                        config.wepKeys[0] = password;
                    } else {
                        config.wepKeys[0] = '"' + password + '"';
                    }
                }
                break;
            case SECURITY_PSK:
                config.allowedKeyManagement.set(KeyMgmt.WPA_PSK);
                if (password.length() != 0) {
                    if (password.matches("[0-9A-Fa-f]{64}")) {
                        config.preSharedKey = password;
                    } else {
                        config.preSharedKey = '"' + password + '"';
                    }
                }
                break;
            default:
                return null;
        }
        mConfig = config;
        return config;
    }


    static int getSecurity(WifiConfiguration config) {
        if (config.allowedKeyManagement.get(KeyMgmt.WPA_PSK)) {
            return SECURITY_PSK;
        }
        if (config.allowedKeyManagement.get(KeyMgmt.WPA_EAP) ||
                config.allowedKeyManagement.get(KeyMgmt.IEEE8021X)) {
            return SECURITY_EAP;
        }
        return (config.wepKeys[0] != null) ? SECURITY_WEP : SECURITY_NONE;
    }

    private static int getSecurity(ScanResult result) {
        if (result.capabilities.contains("WEP")) {
            return SECURITY_WEP;
        } else if (result.capabilities.contains("PSK")) {
            return SECURITY_PSK;
        } else if (result.capabilities.contains("EAP")) {
            return SECURITY_EAP;
        }
        return SECURITY_NONE;
    }


    public static WifiConfiguration connectWifi(String SSID, String Password, int type) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";
        WifiConfiguration tempConfig = IsExsits(SSID);
        if (tempConfig != null) getInstance().wifiManager.removeNetwork(tempConfig.networkId);
        switch (type) {
            case 1:
                config.wepKeys[0] = "";
                config.allowedKeyManagement.set(KeyMgmt.NONE);
                config.wepTxKeyIndex = 0;
                break;
            case 2:
                config.hiddenSSID = true;
                config.wepKeys[0] = "\"" + Password + "\"";
                config.allowedAuthAlgorithms.set(AuthAlgorithm.SHARED);
                config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
                config.allowedKeyManagement.set(KeyMgmt.NONE);
                config.wepTxKeyIndex = 0;
                break;
            case 3:
                config.preSharedKey = "\"" + Password + "\"";
                config.hiddenSSID = true;
                config.allowedAuthAlgorithms.set(AuthAlgorithm.OPEN);
                config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                config.allowedKeyManagement.set(KeyMgmt.WPA_PSK);
                config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                //config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                config.status = WifiConfiguration.Status.ENABLED;
                break;
        }

        return config;
    }
}
