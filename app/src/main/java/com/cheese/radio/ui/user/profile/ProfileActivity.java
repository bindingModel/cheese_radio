package com.cheese.radio.ui.user.profile;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.binding.model.util.FileUtil;
import com.cheese.radio.base.cycle.BaseActivity;

import static com.cheese.radio.inject.component.ActivityComponent.Router.home;
import static com.cheese.radio.inject.component.ActivityComponent.Router.profile;

/**
 * Created by 29283 on 2018/3/9.
 */
@Route(path =profile)
public class ProfileActivity extends BaseActivity<ProfileModel> {


    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(intent == null)return;
        Uri uri = intent.getData();
        String path = FileUtil.getRealPathFromURI(this, uri);
        if (TextUtils.isEmpty(path)) path = FileUtil.getImageAbsolutePath(this, uri);
        if(TextUtils.isEmpty(path))return;
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
        vm.processePictures(path);
//        ,getImageThumbnail(path,(int)dm.density*80,(int)dm.density*80)
    }
}
