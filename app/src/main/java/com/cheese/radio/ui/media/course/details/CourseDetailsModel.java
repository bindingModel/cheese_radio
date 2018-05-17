package com.cheese.radio.ui.media.course.details;

import android.os.Bundle;
import android.view.View;

import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityCourseDetailsBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.util.MyBaseUtil;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/4/1.
 */
@ModelView(R.layout.activity_course_details)
public class CourseDetailsModel extends ViewHttpModel<CourseDetailsActivity, ActivityCourseDetailsBinding, CourseDetailsData> {
    @Inject
    CourseDetailsModel() {
    }

    String text="I/chatty: uid=10408(u0_a408) com.cheese.radio identical 1 line\n" +
            "I/System.out: waiting for debugger to settle...\n" +
            "I/System.out: waiting for debugger to settle...\n" +
            "I/System.out: waiting for debugger to settle...\n" +
            "I/chatty: uid=10408(u0_a408) com.cheese.radio identical 2 lines\n" +
            "I/System.out: waiting for debugger to settle...\n" +
            "I/System.out: debugger has settled (1399)\n" +
            "W/zygote64: Verification of com.cheese.radio.ui.user.User com.cheese.radio.ui.IkeApplication.getUser() took 118.675ms\n" +
            "I/MultiDex: VM with version 2.1.0 has multidex support\n" +
            "            Installing application\n" +
            "            VM has multidex support, MultiDex support library is disabled.\n" +
            "I/InstantRun: starting instant run server: is main process\n" +
            "I/ARouter::: ARouter openLog[ ] \n" +
            "I/ARouter::: ARouter init start.[ ] \n" +
            "I/ARouter::: Run with debug mode or new install, rebuild router map.[ ] \n" +
            "I/ARouter::: VM with name 'Android' has multidex support\n" +
            "D/ARouter::: Found InstantRun support\n" +
            "I/ARouter::: Thread production, name is [ARouter task pool No.1, thread No.1][ ] \n" +
            "I/ARouter::: Thread production, name is [ARouter task pool No.1, thread No.2][ ] \n" +
            "I/ARouter::: Thread production, name is [ARouter task pool No.1, thread No.3][ ] \n" +
            "I/ARouter::: Thread production, name is [ARouter task pool No.1, thread No.4][ ] \n" +
            "I/ARouter::: Thread production, name is [ARouter task pool No.1, thread No.5][ ] \n" +
            "I/ARouter::: Thread production, name is [ARouter task pool No.1, thread No.6][ ] \n" +
            "I/ARouter::: Thread production, name is [ARouter task pool No.1, thread No.7][ ] \n" +
            "I/ARouter::: Thread production, name is [ARouter task pool No.1, thread No.8][ ] \n" +
            "W/zygote64: Skipping duplicate class check due to unrecognized classloader\n" +
            "I/ARouter::: Thread production, name is [ARouter task pool No.1, thread No.9][ ] \n" +
            "E/ARouter: Scan map file in dex files made error.\n" +
            "           java.io.IOException: No original dex files found for dex location /data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_resources_apk.apk\n" +
            "               at dalvik.system.DexFile.openDexFileNative(Native Method)\n" +
            "               at dalvik.system.DexFile.openDexFile(DexFile.java:353)\n" +
            "               at dalvik.system.DexFile.<init>(DexFile.java:100)\n" +
            "               at dalvik.system.DexFile.<init>(DexFile.java:86)\n" +
            "               at com.alibaba.android.arouter.utils.ClassUtils$1.run(ClassUtils.java:77)\n" +
            "               at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1162)\n" +
            "               at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:636)\n" +
            "               at java.lang.Thread.run(Thread.java:764)\n" +
            "D/ARouter::: Filter 7 classes by packageName <com.alibaba.android.arouter.routes>\n" +
            "I/ARouter::: Find router map finished, map size = 7, cost 411 ms.[ ] \n" +
            "I/zygote64: Do partial code cache collection, code=30KB, data=20KB\n" +
            "            After code cache collection, code=30KB, data=20KB\n" +
            "            Increasing code cache capacity to 128KB\n" +
            "I/ARouter::: Load root element finished, cost 15 ms.[ ] \n" +
            "D/ARouter::: LogisticsCenter has already been loaded, GroupIndex[2], InterceptorIndex[1], ProviderIndex[2][ ] \n" +
            "I/ARouter::: ARouter init success![ ] \n" +
            "W/ARouter::: ARouter::No postcard![ ] \n" +
            "W/ARouter::: ARouter::No postcard![ ] \n" +
            "D/ARouter::: The group [arouter] starts loading, trigger by [/arouter/service/interceptor][ ] \n" +
            "D/ARouter::: The group [arouter] has already been loaded, trigger by [/arouter/service/interceptor][ ] \n" +
            "I/ARouter::: ARouter init over.[ ] \n" +
            "I/RouterInterceptor: init router interceptor\n" +
            "I/ARouter::: ARouter interceptors init over.[ ] \n" +
            "D/NetworkSecurityConfig: No Network Security Config specified, using platform default\n" +
            "D/PgyerSDK: Looking for exceptions in: /data/user/0/com.cheese.radio/files\n" +
            "D/PgyerSDK: Current handler class = com.android.internal.os.RuntimeInit$KillApplicationHandler\n" +
            "I/UMConfigure: common version is 1.5.0\n" +
            "I/UMConfigure: common type is 0\n" +
            "E/UMLog: ┌───────────────────问题─────────────────────────────────────────────────────────────────────────────\n" +
            "         │     不能在非Application的onCreate方法中进行初始化\n" +
            "         ├───────────────────解决方案─────────────────────────────────────────────────────────────────────────────\n" +
            "         │     目前只能在Application的onCreate方法中进行初始化，如何正确初始化请详见地址：https://developer.umeng.com/docs/66632/detail/67292?um_channel=sdk\n" +
            "         └────────────────────────────────────────────────────────────────────────────────────────────────────────────────\n" +
            "I/UMConfigure: channel is Umeng\n" +
            "D/UMLog: 统计SDK常见问题索引贴 详见链接 http://developer.umeng.com/docs/66650/cate/66650\n" +
            "I/UMConfigure: ---->>>> init analytics is OK ~~\n" +
            "I/UMLog: ---->>>> init analytics is OK ~~\n" +
            "I/zygote64: Rejecting re-init on previously-failed class java.lang.Class<com.umeng.socialize.handler.UMLWHandler$1>: java.lang.NoClassDefFoundError: Failed resolution of: Lcom/laiwang/sdk/openapi/ILWAPI$IILaiwangApiCallback;\n" +
            "                at java.lang.Class java.lang.Class.classForName(java.lang.String, boolean, java.lang.ClassLoader) (Class.java:-2)\n" +
            "                at java.lang.Class java.lang.Class.forName(java.lang.String, boolean, java.lang.ClassLoader) (Class.java:453)\n" +
            "                at java.lang.Class java.lang.Class.forName(java.lang.String) (Class.java:378)\n" +
            "                at com.umeng.socialize.handler.UMSSOHandler com.umeng.socialize.a.a.a(java.lang.String) (SocialRouter.java:187)\n" +
            "                at void com.umeng.socialize.a.a.b() (SocialRouter.java:177)\n" +
            "                at void com.umeng.socialize.a.a.<init>(android.content.Context) (SocialRouter.java:119)\n" +
            "                at void com.umeng.socialize.UMShareAPI.<init>(android.content.Context) (UMShareAPI.java:50)\n" +
            "                at com.umeng.socialize.UMShareAPI com.umeng.socialize.UMShareAPI.get(android.content.Context) (UMShareAPI.java:89)\n" +
            "                at void com.umeng.socialize.UMShareAPI.init(android.content.Context, java.lang.String) (UMShareAPI.java:97)\n" +
            "                at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)\n" +
            "                at void com.umeng.commonsdk.UMConfigure.init(android.content.Context, java.lang.String, java.lang.String, int, java.lang.String) (UMConfigure.java:504)\n" +
            "                at void com.cheese.radio.ui.IkeApplication.onCreate() (IkeApplication.java:55)\n" +
            "                at void android.app.Instrumentation.callApplicationOnCreate(android.app.Application) (Instrumentation.java:1119)\n" +
            "                at void android.app.ActivityThread.handleBindApplication(android.app.ActivityThread$AppBindData) (ActivityThread.java:5872)\n" +
            "                at void android.app.ActivityThread.-wrap1(android.app.ActivityThread, android.app.ActivityThread$AppBindData) (ActivityThread.java:-1)\n" +
            "                at void android.app.ActivityThread$H.handleMessage(android.os.Message) (ActivityThread.java:1689)\n" +
            "                at void android.os.Handler.dispatchMessage(android.os.Message) (Handler.java:105)\n" +
            "                at void android.os.Looper.loop() (Looper.java:171)\n" +
            "                at void android.app.ActivityThread.main(java.lang.String[]) (ActivityThread.java:6684)\n" +
            "                at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)\n" +
            "                at void com.android.internal.os.Zygote$MethodAndArgsCaller.run() (Zygote.java:246)\n" +
            "                at void com.android.internal.os.ZygoteInit.main(java.lang.String[]) (ZygoteInit.java:783)\n" +
            "            Caused by: java.lang.ClassNotFoundException: Didn't find class \"com.laiwang.sdk.openapi.ILWAPI$IILaiwangApiCallback\" on path: DexPathList[[zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/base.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_dependencies_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_resources_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_0_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_1_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_2_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_3_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_4_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_5_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_6_apk.\n" +
            "                at java.lang.Class dalvik.system.BaseDexClassLoader.findClass(java.lang.String) (BaseDexClassLoader.java:93)\n" +
            "                at java.lang.Class java.lang.ClassLoader.loadClass(java.lang.String, boolean) (ClassLoader.java:379)\n" +
            "                at java.lang.Class java.lang.ClassLoader.loadClass(java.lang.String) (ClassLoader.java:312)\n" +
            "                at java.lang.Class java.lang.Class.classForName(java.lang.String, boolean, java.lang.ClassLoader) (Class.java:-2)\n" +
            "                at java.lang.Class java.lang.Class.forName(java.lang.String, boolean, java.lang.ClassLoader) (Class.java:453)\n" +
            "                at java.lang.Class java.lang.Class.forName(java.lang.String) (Class.java:378)\n" +
            "                at com.umeng.socialize.handler.UMSSOHandler com.umeng.socialize.a.a.a(java.lang.String) (SocialRouter.java:187)\n" +
            "                at void com.umeng.socialize.a.a.b() (SocialRouter.java:177)\n" +
            "                at void com.umeng.socialize.a.a.<init>(android.content.Context) (SocialRouter.java:119)\n" +
            "                at void com.umeng.socialize.UMShareAPI.<init>(android.content.Context) (UMShareAPI.java:50)\n" +
            "                at com.umeng.socialize.UMShareAPI com.umeng.socialize.UMShareAPI.get(android.content.Context) (UMShareAPI.java:89)\n" +
            "                at void com.umeng.socialize.UMShareAPI.init(android.content.Context, java.lang.String) (UMShareAPI.java:97)\n" +
            "                at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)\n" +
            "                at void com.umeng.commonsdk.UMConfigure.init(android.content.Context, java.lang.String, java.lang.String, int, java.lang.String) (UMConfigure.java:504)\n" +
            "                at void com.cheese.radio.ui.IkeApplication.onCreate() (IkeApplication.java:55)\n" +
            "                at void android.app.Instrumentation.callApplicationOnCreate(android.app.Application) (Instrumentation.java:1119)\n" +
            "                at void android.app.ActivityThread.handleBindApplication(android.app.ActivityThread$AppBindData) (ActivityThread.java:5872)\n" +
            "                at void android.app.ActivityThread.-wrap1(android.app.ActivityThread, android.app.ActivityThread$AppBindData) (ActivityThread.java:-1)\n" +
            "                at void android.app.ActivityThread$H.handleMessage(android.os.Message) (ActivityThread.java:1689)\n" +
            "                at void android.os.Handler.dispatchMessage(android.os.Message) (Handler.java:105)\n" +
            "                at void android.os.Looper.loop() (Looper.java:171)\n" +
            "                at void android.app.ActivityThread.main(java.lang.String[]) (ActivityThread.java:6684)\n" +
            "                at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)\n" +
            "                at void com.android.internal.os.Zygote$MethodAndArgsCaller.run() (Zygote.java:246)\n" +
            "                at void com.android.internal.os.ZygoteInit.main(java.lang.String[]) (ZygoteInit.java:783)\n" +
            "I/zygote64: Rejecting re-init on previously-failed class java.lang.Class<com.umeng.socialize.handler.UMLWHandler$1>: java.lang.NoClassDefFoundError: Failed resolution of: Lcom/laiwang/sdk/openapi/ILWAPI$IILaiwangApiCallback;\n" +
            "                at java.lang.Class java.lang.Class.classForName(java.lang.String, boolean, java.lang.ClassLoader) (Class.java:-2)\n" +
            "                at java.lang.Class java.lang.Class.forName(java.lang.String, boolean, java.lang.ClassLoader) (Class.java:453)\n" +
            "                at java.lang.Class java.lang.Class.forName(java.lang.String) (Class.java:378)\n" +
            "                at com.umeng.socialize.handler.UMSSOHandler com.umeng.socialize.a.a.a(java.lang.String) (SocialRouter.java:187)\n" +
            "                at void com.umeng.socialize.a.a.b() (SocialRouter.java:177)\n" +
            "                at void com.umeng.socialize.a.a.<init>(android.content.Context) (SocialRouter.java:119)\n" +
            "                at void com.umeng.socialize.UMShareAPI.<init>(android.content.Context) (UMShareAPI.java:50)\n" +
            "                at com.umeng.socialize.UMShareAPI com.umeng.socialize.UMShareAPI.get(android.content.Context) (UMShareAPI.java:89)\n" +
            "                at void com.umeng.socialize.UMShareAPI.init(android.content.Context, java.lang.String) (UMShareAPI.java:97)\n" +
            "                at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)\n" +
            "                at void com.umeng.commonsdk.UMConfigure.init(android.content.Context, java.lang.String, java.lang.String, int, java.lang.String) (UMConfigure.java:504)\n" +
            "                at void com.cheese.radio.ui.IkeApplication.onCreate() (IkeApplication.java:55)\n" +
            "                at void android.app.Instrumentation.callApplicationOnCreate(android.app.Application) (Instrumentation.java:1119)\n" +
            "                at void android.app.ActivityThread.handleBindApplication(android.app.ActivityThread$AppBindData) (ActivityThread.java:5872)\n" +
            "                at void android.app.ActivityThread.-wrap1(android.app.ActivityThread, android.app.ActivityThread$AppBindData) (ActivityThread.java:-1)\n" +
            "                at void android.app.ActivityThread$H.handleMessage(android.os.Message) (ActivityThread.java:1689)\n" +
            "                at void android.os.Handler.dispatchMessage(android.os.Message) (Handler.java:105)\n" +
            "                at void android.os.Looper.loop() (Looper.java:171)\n" +
            "                at void android.app.ActivityThread.main(java.lang.String[]) (ActivityThread.java:6684)\n" +
            "                at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)\n" +
            "                at void com.android.internal.os.Zygote$MethodAndArgsCaller.run() (Zygote.java:246)\n" +
            "                at void com.android.internal.os.ZygoteInit.main(java.lang.String[]) (ZygoteInit.java:783)\n" +
            "            Caused by: java.lang.ClassNotFoundException: Didn't find class \"com.laiwang.sdk.openapi.ILWAPI$IILaiwangApiCallback\" on path: DexPathList[[zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/base.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_dependencies_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_resources_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_0_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_1_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_2_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_3_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_4_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_5_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_6_apk.\n" +
            "                at java.lang.Class dalvik.system.BaseDexClassLoader.findClass(java.lang.String) (BaseDexClassLoader.java:93)\n" +
            "                at java.lang.Class java.lang.ClassLoader.loadClass(java.lang.String, boolean) (ClassLoader.java:379)\n" +
            "                at java.lang.Class java.lang.ClassLoader.loadClass(java.lang.String) (ClassLoader.java:312)\n" +
            "                at java.lang.Class java.lang.Class.classForName(java.lang.String, boolean, java.lang.ClassLoader) (Class.java:-2)\n" +
            "                at java.lang.Class java.lang.Class.forName(java.lang.String, boolean, java.lang.ClassLoader) (Class.java:453)\n" +
            "                at java.lang.Class java.lang.Class.forName(java.lang.String) (Class.java:378)\n" +
            "                at com.umeng.socialize.handler.UMSSOHandler com.umeng.socialize.a.a.a(java.lang.String) (SocialRouter.java:187)\n" +
            "                at void com.umeng.socialize.a.a.b() (SocialRouter.java:177)\n" +
            "                at void com.umeng.socialize.a.a.<init>(android.content.Context) (SocialRouter.java:119)\n" +
            "                at void com.umeng.socialize.UMShareAPI.<init>(android.content.Context) (UMShareAPI.java:50)\n" +
            "                at com.umeng.socialize.UMShareAPI com.umeng.socialize.UMShareAPI.get(android.content.Context) (UMShareAPI.java:89)\n" +
            "                at void com.umeng.socialize.UMShareAPI.init(android.content.Context, java.lang.String) (UMShareAPI.java:97)\n" +
            "                at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)\n" +
            "                at void com.umeng.commonsdk.UMConfigure.init(android.content.Context, java.lang.String, java.lang.String, int, java.lang.String) (UMConfigure.java:504)\n" +
            "                at void com.cheese.radio.ui.IkeApplication.onCreate() (IkeApplication.java:55)\n" +
            "                at void android.app.Instrumentation.callApplicationOnCreate(android.app.Application) (Instrumentation.java:1119)\n" +
            "                at void android.app.ActivityThread.handleBindApplication(android.app.ActivityThread$AppBindData) (ActivityThread.java:5872)\n" +
            "                at void android.app.ActivityThread.-wrap1(android.app.ActivityThread, android.app.ActivityThread$AppBindData) (ActivityThread.java:-1)\n" +
            "                at void android.app.ActivityThread$H.handleMessage(android.os.Message) (ActivityThread.java:1689)\n" +
            "                at void android.os.Handler.dispatchMessage(android.os.Message) (Handler.java:105)\n" +
            "                at void android.os.Looper.loop() (Looper.java:171)\n" +
            "                at void android.app.ActivityThread.main(java.lang.String[]) (ActivityThread.java:6684)\n" +
            "                at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)\n" +
            "                at void com.android.internal.os.Zygote$MethodAndArgsCaller.run() (Zygote.java:246)\n" +
            "                at void com.android.internal.os.ZygoteInit.main(java.lang.String[]) (ZygoteInit.java:783)\n" +
            "            Rejecting re-init on previously-failed class java.lang.Class<com.umeng.socialize.handler.UMLWHandler$1>: java.lang.NoClassDefFoundError: Failed resolution of: Lcom/laiwang/sdk/openapi/ILWAPI$IILaiwangApiCallback;\n" +
            "                at java.lang.Class java.lang.Class.classForName(java.lang.String, boolean, java.lang.ClassLoader) (Class.java:-2)\n" +
            "                at java.lang.Class java.lang.Class.forName(java.lang.String, boolean, java.lang.ClassLoader) (Class.java:453)\n" +
            "                at java.lang.Class java.lang.Class.forName(java.lang.String) (Class.java:378)\n" +
            "                at com.umeng.socialize.handler.UMSSOHandler com.umeng.socialize.a.a.a(java.lang.String) (SocialRouter.java:187)\n" +
            "I/zygote64:     at void com.umeng.socialize.a.a.b() (SocialRouter.java:177)\n" +
            "                at void com.umeng.socialize.a.a.<init>(android.content.Context) (SocialRouter.java:119)\n" +
            "                at void com.umeng.socialize.UMShareAPI.<init>(android.content.Context) (UMShareAPI.java:50)\n" +
            "                at com.umeng.socialize.UMShareAPI com.umeng.socialize.UMShareAPI.get(android.content.Context) (UMShareAPI.java:89)\n" +
            "                at void com.umeng.socialize.UMShareAPI.init(android.content.Context, java.lang.String) (UMShareAPI.java:97)\n" +
            "                at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)\n" +
            "                at void com.umeng.commonsdk.UMConfigure.init(android.content.Context, java.lang.String, java.lang.String, int, java.lang.String) (UMConfigure.java:504)\n" +
            "                at void com.cheese.radio.ui.IkeApplication.onCreate() (IkeApplication.java:55)\n" +
            "                at void android.app.Instrumentation.callApplicationOnCreate(android.app.Application) (Instrumentation.java:1119)\n" +
            "                at void android.app.ActivityThread.handleBindApplication(android.app.ActivityThread$AppBindData) (ActivityThread.java:5872)\n" +
            "                at void android.app.ActivityThread.-wrap1(android.app.ActivityThread, android.app.ActivityThread$AppBindData) (ActivityThread.java:-1)\n" +
            "                at void android.app.ActivityThread$H.handleMessage(android.os.Message) (ActivityThread.java:1689)\n" +
            "                at void android.os.Handler.dispatchMessage(android.os.Message) (Handler.java:105)\n" +
            "                at void android.os.Looper.loop() (Looper.java:171)\n" +
            "                at void android.app.ActivityThread.main(java.lang.String[]) (ActivityThread.java:6684)\n" +
            "                at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)\n" +
            "                at void com.android.internal.os.Zygote$MethodAndArgsCaller.run() (Zygote.java:246)\n" +
            "                at void com.android.internal.os.ZygoteInit.main(java.lang.String[]) (ZygoteInit.java:783)\n" +
            "            Caused by: java.lang.ClassNotFoundException: Didn't find class \"com.laiwang.sdk.openapi.ILWAPI$IILaiwangApiCallback\" on path: DexPathList[[zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/base.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_dependencies_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_resources_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_0_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_1_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_2_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_3_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_4_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_5_apk.apk\", zip file \"/data/app/com.cheese.radio-KgItqVIyvs6eVqoCSfcQGg==/split_lib_slice_6_apk.\n" +
            "                at java.lang.Class dalvik.system.BaseDexClassLoader.findClass(java.lang.String) (BaseDexClassLoader.java:93)\n" +
            "                at java.lang.Class java.lang.ClassLoader.loadClass(java.lang.String, boolean) (ClassLoader.java:379)\n" +
            "                at java.lang.Class java.lang.ClassLoader.loadClass(java.lang.String) (ClassLoader.java:312)\n" +
            "                at java.lang.Class java.lang.Class.classForName(java.lang.String, boolean, java.lang.ClassLoader) (Class.java:-2)\n" +
            "                at java.lang.Class java.lang.Class.forName(java.lang.String, boolean, java.lang.ClassLoader) (Class.java:453)\n" +
            "                at java.lang.Class java.lang.Class.forName(java.lang.String) (Class.java:378)\n" +
            "                at com.umeng.socialize.handler.UMSSOHandler com.umeng.socialize.a.a.a(java.lang.String) (SocialRouter.java:187)\n" +
            "                at void com.umeng.socialize.a.a.b() (SocialRouter.java:177)\n" +
            "                at void com.umeng.socialize.a.a.<init>(android.content.Context) (SocialRouter.java:119)\n" +
            "                at void com.umeng.socialize.UMShareAPI.<init>(android.content.Context) (UMShareAPI.java:50)\n" +
            "                at com.umeng.socialize.UMShareAPI com.umeng.socialize.UMShareAPI.get(android.content.Context) (UMShareAPI.java:89)\n" +
            "                at void com.umeng.socialize.UMShareAPI.init(android.content.Context, java.lang.String) (UMShareAPI.java:97)\n" +
            "                at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)\n" +
            "                at void com.umeng.commonsdk.UMConfigure.init(android.content.Context, java.lang.String, java.lang.String, int, java.lang.String) (UMConfigure.java:504)\n" +
            "                at void com.cheese.radio.ui.IkeApplication.onCreate() (IkeApplication.java:55)\n" +
            "                at void android.app.Instrumentation.callApplicationOnCreate(android.app.Application) (Instrumentation.java:1119)\n" +
            "                at void android.app.ActivityThread.handleBindApplication(android.app.ActivityThread$AppBindData) (ActivityThread.java:5872)\n" +
            "                at void android.app.ActivityThread.-wrap1(android.app.ActivityThread, android.app.ActivityThread$AppBindData) (ActivityThread.java:-1)\n" +
            "                at void android.app.ActivityThread$H.handleMessage(android.os.Message) (ActivityThread.java:1689)\n" +
            "                at void android.os.Handler.dispatchMessage(android.os.Message) (Handler.java:105)\n" +
            "                at void android.os.Looper.loop() (Looper.java:171)\n" +
            "                at void android.app.ActivityThread.main(java.lang.String[]) (ActivityThread.java:6684)\n" +
            "                at java.lang.Object java.lang.reflect.Method.invoke(java.lang.Object, java.lang.Object[]) (Method.java:-2)\n" +
            "                at void com.android.internal.os.Zygote$MethodAndArgsCaller.run() (Zygote.java:246)\n" +
            "                at void com.android.internal.os.ZygoteInit.main(java.lang.String[]) (ZygoteInit.java:783)\n" +
            "I/UMConfigure: ---->>>> init share appkey is OK ~~\n" +
            "I/UMLog: ---->>>> init share appkey is OK ~~\n" +
            "I/UMConfigure: current appkey is 5ac4c20fb27b0a077b0001e6, last appkey is 5ac4c20fb27b0a077b0001e6\n" +
            "E/UMLog_Social: 您使用的SDK版本为：6.9.0\n" +
            "E/ContentCatcherManager: failed to get ContentCatcherService.\n" +
            "I/zygote64: Do partial code cache collection, code=61KB, data=38KB\n" +
            "I/zygote64: After code cache collection, code=61KB, data=38KB\n" +
            "            Increasing code cache capacity to 256KB\n" +
            "D/AccessibilityManager: AccessibilityManager status: mPackageName = com.cheese.radio, mOptimizeEnabled = true, mIsEnabled = false, mIsUiAutomationEnabled = false, mIsInterestedPackage =false\n" +
            "D/OpenGLRenderer: HWUI GL Pipeline\n" +
            "I/System.out: true\n" +
            "I/Adreno: QUALCOMM build                   : 9c9b012, I92eb381bc9\n" +
            "          Build Date                       : 12/31/17\n" +
            "          OpenGL ES Shader Compiler Version: EV031.22.00.01\n" +
            "          Local Branch                     : \n" +
            "          Remote Branch                    : refs/tags/AU_LINUX_ANDROID_LA.UM.6.4.R1.08.00.00.309.049\n" +
            "          Remote Branch                    : NONE\n" +
            "          Reconstruct Branch               : NOTHING\n" +
            "I/vndksupport: sphal namespace is not configured for this process. Loading /vendor/lib64/hw/gralloc.msm8998.so from the current namespace instead.\n" +
            "I/Adreno: PFP: 0x005ff087, ME: 0x005ff063\n" +
            "I/OpenGLRenderer: Initialized EGL, version 1.4\n" +
            "D/OpenGLRenderer: Swap behavior 2\n" +
            "V/ReflectUtil: no such method method:get$change\n" +
            "               no such method method:get$change\n" +
            "V/ReflectUtil: no such method method:getSerialVersionUID\n" +
            "               no such method method:getSerialVersionUID\n" +
            "I/zygote64: Do full code cache collection, code=123KB, data=75KB\n" +
            "            After code cache collection, code=104KB, data=55KB\n" +
            "W/ARouter::: ARouter::No postcard![ ] \n" +
            "             ARouter::No postcard![ ] \n" +
            "D/ARouter::: The group [cheese] starts loading, trigger by [/cheese/login][ ] \n" +
            "D/ARouter::: The group [cheese] has already been loaded, trigger by [/cheese/login][ ] \n" +
            "I/System.out: true\n" +
            "I/vndksupport: sphal namespace is not configured for this process. Loading /vendor/lib64/hw/gralloc.msm8998.so from the current namespace instead.\n" +
            "I/Timeline: Timeline: Activity_launch_request time:231882295\n" +
            "I/Toast: Show toast from OpPackageName:com.cheese.radio, PackageName:com.cheese.radio\n" +
            "E/ContentCatcherManager: failed to get ContentCatcherService.\n" +
            "D/MobclickAgent: Report policy : ReportAtLaunch\n" +
            "D/UMLog: 当前发送策略为：启动时发送。详见链接 https://developer.umeng.com/docs/66632/detail/66976?um_channel=sdk\n" +
            "I/zygote64: Do partial code cache collection, code=120KB, data=83KB\n" +
            "I/zygote64: After code cache collection, code=120KB, data=83KB\n" +
            "I/zygote64: Increasing code cache capacity to 512KB\n" +
            "D/MobclickAgent: constructMessage:{\"sessions\":[{\"id\":\"E04995C135C958107C7E388E0E11147C\",\"start_time\":\"1526517791734\",\"end_time\":\"1526517878096\",\"duration\":86362,\"pages\":[{\"page_name\":\"com.cheese.radio.ui.user.login.LoginActivity\",\"duration\":8402},{\"page_name\":\"com.cheese.radio.ui.user.login.LoginActivity\",\"duration\":15174},{\"page_name\":\"com.cheese.radio.ui.startup.StartUpActivity\",\"duration\":294},{\"page_name\":\"com.cheese.radio.ui.user.login.LoginActivity\",\"duration\":31562},{\"page_name\":\"com.cheese.radio.ui.user.register.one.RegisterOneActivity\",\"duration\":3939},{\"page_name\":\"com.cheese.radio.ui.startup.StartUpActivity\",\"duration\":97}]}],\"sdk_version\":\"7.5.0\",\"device_id\":\"f0fff4e96fecefcd\",\"device_model\":\"MI 6\",\"version\":2,\"appkey\":\"5ac4c20fb27b0a077b0001e6\",\"channel\":\"Umeng\"}\n" +
            "I/MobclickAgent: Start new session: 3DE5C978E6DB1008182172D3D78F5907\n" +
            "I/MobclickAgent: onResume called before onPause\n" +
            "I/MobclickAgent: Extend current session: 3DE5C978E6DB1008182172D3D78F5907\n" +
            "D/MobclickAgent: Report policy : ReportAtLaunch\n" +
            "D/UMLog: 当前发送策略为：启动时发送。详见链接 https://developer.umeng.com/docs/66632/detail/66976?um_channel=sdk\n" +
            "I/MobclickAgent: Send message to server. status code is: 200\n" +
            "I/MobclickAgent: send log:succeed\n" +
            "I/System.out: true\n" +
            "E/ContentCatcherManager: failed to get ContentCatcherService.\n" +
            "I/MobclickAgent: Send message to server. status code is: 200\n" +
            "I/MobclickAgent: send log:succeed\n" +
            "E/hello: {_miniTarget=16, freeSapce=88.0GB/118.4GB, _language=zh-CN, jailBroken=2, _api_key=305092bc73c180b55c26012a94809131, freeSdc=88.0GB/118.4GB, _packageName=com.cheese.radio, protrait=1, battery=75%, deviceId=getting faild, deviceName=Xiaomi, version=1.1, resolution=1080x1920, versionCode=2, network=MOBILE, freeRam=2.2GB/6.0GB, osVersion=8.0.0, _appName=芝士电台, osType=2, sdkVersion=2.8.1, deviceModel=MI 6, agKey=14a0fc2fba7de48e4f1d4e0dd819b86f}\n" +
            "I/zygote64: Compiler allocated 6MB to compile void android.view.ViewRootImpl.performTraversals()\n" +
            "Disconnected from the target VM, address: 'localhost:8601', transport: 'socket'\n";
    @Override
    public void accept(CourseDetailsData courseDetailsData) throws Exception {
        getDataBinding().setEntity(courseDetailsData);
//        MyBaseUtil.setWebView(getDataBinding().webview,courseDetailsData.getClassDesc());
        MyBaseUtil.setWebView(getDataBinding().webview,courseDetailsData.getClassDesc());
    }


    private CourseDetailsParams params = new CourseDetailsParams("classInfo");
    private Integer classId;
    @Inject
    RadioApi api;

    @Override
    public void attachView(Bundle savedInstanceState, CourseDetailsActivity courseDetailsActivity) {
        super.attachView(savedInstanceState, courseDetailsActivity);
        classId = getT().getIntent().getIntExtra(Constant.classId, 0);
        if (classId != 0) {
            params.setClassId(classId);
            setRcHttp((offset1, refresh) -> api.getClassInfo(params).compose(new RestfulTransformer<>()));
        }

    }

    public void onEnrollClick(View view) {
        params.setMethod("bookClass");
        addDisposable(api.getBookClass(params).compose(new RestfulTransformer<>()).subscribe(
                s -> getDataBinding().enroll.setText(s), BaseUtil::toast)
        );
    }
}
