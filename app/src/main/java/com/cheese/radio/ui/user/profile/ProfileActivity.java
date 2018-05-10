package com.cheese.radio.ui.user.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.binding.model.util.FileUtil;
import com.cheese.radio.base.cycle.BaseActivity;

import static com.cheese.radio.inject.component.ActivityComponent.Router.home;
import static com.cheese.radio.inject.component.ActivityComponent.Router.profile;
import static com.cheese.radio.ui.Constant.REQUEST_CAMERA;
import static com.cheese.radio.ui.Constant.REQUEST_PHOTO;

/**
 * Created by 29283 on 2018/3/9.
 */
@Route(path = profile)
public class ProfileActivity extends BaseActivity<ProfileModel> {


    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CAMERA && resultCode == -1) {
            vm.processePictures(null);
        }
        else if (requestCode ==REQUEST_PHOTO && resultCode == -1){
            Uri uri = intent.getData();
            String path = FileUtil.getRealPathFromURI(this, uri);
            if (TextUtils.isEmpty(path)) path = FileUtil.getImageAbsolutePath(this, uri);
            if (TextUtils.isEmpty(path)) return;
            vm.processePictures(path);
        }
        if (intent == null) return;

    }

    public String getFileUriParent() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test";
    }
}
