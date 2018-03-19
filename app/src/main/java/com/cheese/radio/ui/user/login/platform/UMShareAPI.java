////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//package com.cheese.radio.ui.user.login.platform;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.os.Bundle;
//import android.text.TextUtils;
//import com.umeng.socialize.bean.SHARE_MEDIA;
//import com.umeng.socialize.common.SocializeConstants;
//import com.umeng.socialize.common.QueuedWork.DialogThread;
//import com.umeng.socialize.common.QueuedWork.UMAsyncTask;
//import com.umeng.socialize.controller.SocialRouter;
//import com.umeng.socialize.handler.UMSSOHandler;
//import com.umeng.socialize.net.ActionBarRequest;
//import com.umeng.socialize.net.ActionBarResponse;
//import com.umeng.socialize.net.RestAPI;
//import com.umeng.socialize.net.analytics.SocialAnalytics;
//import com.umeng.socialize.uploadlog.UMLog;
//import com.umeng.socialize.utils.ContextUtil;
//import com.umeng.socialize.utils.Log;
//import com.umeng.socialize.utils.SocializeSpUtils;
//import com.umeng.socialize.utils.SocializeUtils;
//import com.umeng.socialize.utils.UrlUtil;
//import java.lang.ref.WeakReference;
//import java.lang.reflect.Method;
//
//public class UMShareAPI {
//    private static UMShareAPI singleton = null;
//    private SocialRouter router;
//    private UMShareConfig mDefaultShareConfig = new UMShareConfig();
//
//    private UMShareAPI(Context context) {
//        ContextUtil.setContext(context.getApplicationContext());
//        this.router = new SocialRouter(context.getApplicationContext());
//        (new UMShareAPI.InitThread(context.getApplicationContext())).execute();
//    }
//
//    public static UMShareAPI get(Context context) {
//        if(singleton == null || singleton.router == null) {
//            singleton = new UMShareAPI(context);
//        }
//
//        singleton.router.setmContext(context);
//        return singleton;
//    }
//
//    public static void init(Context context, String appkey) {
//        SocializeConstants.APPKEY = appkey;
//        get(context);
//    }
//
//    public void doOauthVerify(final Activity activity, final SHARE_MEDIA platform, final UMAuthListener listener) {
//        UMLog.putAuth();
//        singleton.router.setmContext(activity);
//        if(!Config.DEBUG || this.judgePlatform(activity, platform)) {
//            if(activity != null) {
//                (new DialogThread<Void>(activity) {
//                    protected Void doInBackground() {
//                        if(UMShareAPI.this.router == null) {
//                            UMShareAPI.this.router = new SocialRouter(activity);
//                        }
//
//                        UMShareAPI.this.router.doOauthVerify(activity, platform, listener);
//                        return null;
//                    }
//                }).execute();
//            } else {
//                Log.d("UMerror", "doOauthVerify activity is null");
//            }
//
//        }
//    }
//
//    public void deleteOauth(final Activity context, final SHARE_MEDIA platform, final UMAuthListener listener) {
//        if(context != null) {
//            singleton.router.setmContext(context);
//            (new DialogThread<Void>(context) {
//                protected Object doInBackground() {
//                    if(UMShareAPI.this.router != null) {
//                        UMShareAPI.this.router.deleteOauth(context, platform, listener);
//                    }
//
//                    return null;
//                }
//            }).execute();
//        } else {
//            Log.d("UMerror", "deleteOauth activity is null");
//        }
//
//    }
//
//    public void getPlatformInfo(final Activity context, final SHARE_MEDIA platform, final UMAuthListener listener) {
//        if(context != null) {
//            UMLog.putAuth();
//            if(Config.DEBUG) {
//                if(!this.judgePlatform(context, platform)) {
//                    return;
//                }
//
//                UrlUtil.getInfoPrint(platform);
//            }
//
//            singleton.router.setmContext(context);
//            (new DialogThread<Void>(context) {
//                protected Object doInBackground() {
//                    if(UMShareAPI.this.router != null) {
//                        UMShareAPI.this.router.getPlatformInfo(context, platform, listener);
//                    }
//
//                    return null;
//                }
//            }).execute();
//        } else {
//            Log.d("UMerror", "getPlatformInfo activity argument is null");
//        }
//
//    }
//
//    public boolean isInstall(Activity context, SHARE_MEDIA platform) {
//        if(this.router != null) {
//            return this.router.isInstall(context, platform);
//        } else {
//            this.router = new SocialRouter(context);
//            return this.router.isInstall(context, platform);
//        }
//    }
//
//    public boolean isAuthorize(Activity context, SHARE_MEDIA platform) {
//        if(this.router != null) {
//            return this.router.isAuthorize(context, platform);
//        } else {
//            this.router = new SocialRouter(context);
//            return this.router.isAuthorize(context, platform);
//        }
//    }
//
//    public boolean isSupport(Activity context, SHARE_MEDIA platform) {
//        if(this.router != null) {
//            return this.router.isSupport(context, platform);
//        } else {
//            this.router = new SocialRouter(context);
//            return this.router.isSupport(context, platform);
//        }
//    }
//
//    public String getversion(Activity context, SHARE_MEDIA platform) {
//        if(this.router != null) {
//            return this.router.getSDKVersion(context, platform);
//        } else {
//            this.router = new SocialRouter(context);
//            return this.router.getSDKVersion(context, platform);
//        }
//    }
//
//    public void doShare(Activity activity, final ShareAction share, final UMShareListener listener) {
//        UMLog.putShare();
//        final WeakReference<Activity> mWeakAct = new WeakReference(activity);
//        if(Config.DEBUG) {
//            if(!this.judgePlatform(activity, share.getPlatform())) {
//                return;
//            }
//
//            UrlUtil.sharePrint(share.getPlatform());
//        }
//
//        if(mWeakAct.get() != null && !((Activity)mWeakAct.get()).isFinishing()) {
//            singleton.router.setmContext(activity);
//            (new DialogThread<Void>((Context)mWeakAct.get()) {
//                protected Void doInBackground() {
//                    if(mWeakAct.get() != null && !((Activity)mWeakAct.get()).isFinishing()) {
//                        if(UMShareAPI.this.router != null) {
//                            UMShareAPI.this.router.share((Activity)mWeakAct.get(), share, listener);
//                        } else {
//                            UMShareAPI.this.router = new SocialRouter((Context)mWeakAct.get());
//                            UMShareAPI.this.router.share((Activity)mWeakAct.get(), share, listener);
//                        }
//
//                        return null;
//                    } else {
//                        return null;
//                    }
//                }
//            }).execute();
//        } else {
//            Log.d("UMerror", "Share activity is null");
//        }
//
//    }
//
//    private boolean judgePlatform(Activity activity, SHARE_MEDIA share_media) {
//        Method[] bfs = activity.getClass().getDeclaredMethods();
//        boolean isHave = false;
//        Method[] var5 = bfs;
//        int var6 = bfs.length;
//
//        for(int var7 = 0; var7 < var6; ++var7) {
//            Method bm = var5[var7];
//            if(bm.getName().equals("onActivityResult")) {
//                isHave = true;
//            }
//        }
//
//        if(!isHave) {
//            Log.url("您的activity中没有重写onActivityResult方法", "https://at.umeng.com/CCiOHv?cid=476");
//        }
//
//        String result;
//        if(share_media == SHARE_MEDIA.QQ) {
//            result = UmengTool.checkQQByself(activity);
//            if(result.contains("没有")) {
//                if(result.contains("没有在AndroidManifest.xml中检测到")) {
//                    UmengTool.showDialogWithURl(activity, result, "https://at.umeng.com/iqmK1D?cid=476");
//                } else if(result.contains("android.permission.WRITE_EXTERNAL_STORAGE")) {
//                    UmengTool.showDialogWithURl(activity, result, "https://at.umeng.com/19HTvC?cid=476");
//                } else if(result.contains("qq应用id")) {
//                    UmengTool.showDialogWithURl(activity, result, "https://at.umeng.com/WT95za?cid=476");
//                } else if(result.contains("qq的id配置")) {
//                    UmengTool.showDialogWithURl(activity, result, "https://at.umeng.com/8Tfmei?cid=476");
//                } else {
//                    UmengTool.showDialog(activity, result);
//                }
//
//                return false;
//            } else {
//                Log.um(UmengTool.checkQQByself(activity));
//                return true;
//            }
//        } else if(share_media == SHARE_MEDIA.WEIXIN) {
//            result = UmengTool.checkWxBySelf(activity);
//            if(result.contains("不正确")) {
//                if(result.contains("WXEntryActivity配置不正确")) {
//                    UmengTool.showDialogWithURl(activity, result, "https://at.umeng.com/9D49bu?cid=476");
//                } else {
//                    UmengTool.showDialog(activity, result);
//                }
//
//                UmengTool.checkWx(activity);
//                return false;
//            } else {
//                Log.um(UmengTool.checkWxBySelf(activity));
//                return true;
//            }
//        } else if(share_media == SHARE_MEDIA.SINA) {
//            if(UmengTool.checkSinaBySelf(activity).contains("不正确")) {
//                UmengTool.checkSina(activity);
//                return false;
//            } else {
//                Log.um(UmengTool.checkSinaBySelf(activity));
//                return true;
//            }
//        } else if(share_media == SHARE_MEDIA.FACEBOOK) {
//            if(UmengTool.checkFBByself(activity).contains("没有")) {
//                UmengTool.checkFacebook(activity);
//                return false;
//            } else {
//                Log.um(UmengTool.checkFBByself(activity));
//                return true;
//            }
//        } else {
//            if(share_media == SHARE_MEDIA.VKONTAKTE) {
//                Log.um(UmengTool.checkVKByself(activity));
//            }
//
//            if(share_media == SHARE_MEDIA.LINKEDIN) {
//                Log.um(UmengTool.checkLinkin(activity));
//            }
//
//            if(share_media == SHARE_MEDIA.KAKAO) {
//                Log.um(UmengTool.checkKakao(activity));
//            }
//
//            return true;
//        }
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(this.router != null) {
//            this.router.onActivityResult(requestCode, resultCode, data);
//        } else {
//            Log.v("auth fail", "router=null");
//        }
//
//        Log.um("onActivityResult =" + requestCode + "  resultCode=" + resultCode);
//    }
//
//    public UMSSOHandler getHandler(SHARE_MEDIA name) {
//        return this.router != null?this.router.getHandler(name):null;
//    }
//
//    public void release() {
//        this.router.release();
//    }
//
//    public void onSaveInstanceState(Bundle bundle) {
//        this.router.onSaveInstanceState(bundle);
//    }
//
//    public void fetchAuthResultWithBundle(Activity context, Bundle bundle, UMAuthListener listener) {
//        this.router.fetchAuthResultWithBundle(context, bundle, listener);
//    }
//
//    public void setShareConfig(UMShareConfig config) {
//        this.router.setShareConfig(config);
//    }
//
//    private static class InitThread extends UMAsyncTask<Void> {
//        private Context mContext;
//        private boolean isToday = false;
//
//        public InitThread(Context context) {
//            this.mContext = context;
//            String umId = SocializeSpUtils.getUMId(context);
//            if(!TextUtils.isEmpty(umId)) {
//                Config.UID = umId;
//            }
//
//            String umEk = SocializeSpUtils.getUMEk(context);
//            if(!TextUtils.isEmpty(umEk)) {
//                Config.EntityKey = umEk;
//            }
//
//            long lastTime = SocializeSpUtils.getTime(context);
//            this.isToday = SocializeUtils.isToday(lastTime);
//        }
//
//        protected Void doInBackground() {
//            boolean isNewInstall = this.isNewInstall();
//            Log.y("----sdkversion:6.4.5---\n 如有任何错误，请开启debug模式:在设置各平台APPID的地方添加代码：Config.DEBUG = true\n所有编译问题或者设置问题，请参考文档：https://at.umeng.com/0fqeCy?cid=476");
//            if(TextUtils.isEmpty(Config.EntityKey) || TextUtils.isEmpty(Config.UID) || !this.isToday) {
//                ActionBarRequest request = new ActionBarRequest(this.mContext, isNewInstall);
//                ActionBarResponse response = RestAPI.queryShareId(request);
//                if(response != null && response.isOk()) {
//                    this.setInstalled();
//                    Config.EntityKey = response.mEntityKey;
//                    Config.SessionId = response.mSid;
//                    Config.UID = response.mUid;
//                    SocializeSpUtils.putUMId(this.mContext, Config.UID);
//                    SocializeSpUtils.putUMEk(this.mContext, Config.EntityKey);
//                    SocializeSpUtils.putTime(this.mContext);
//                }
//            }
//
//            SocialAnalytics.dauStats(this.mContext, isNewInstall);
//            return null;
//        }
//
//        private boolean isNewInstall() {
//            SharedPreferences sp = this.mContext.getSharedPreferences("umeng_socialize", 0);
//            return sp.getBoolean("newinstall", false);
//        }
//
//        public void setInstalled() {
//            SharedPreferences sp = this.mContext.getSharedPreferences("umeng_socialize", 0);
//            Editor ed = sp.edit();
//            ed.putBoolean("newinstall", true);
//            ed.commit();
//        }
//    }
//}
