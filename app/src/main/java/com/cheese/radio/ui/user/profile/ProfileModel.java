package com.cheese.radio.ui.user.profile;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.database.Observable;
import android.databinding.ObservableField;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.Event;
import com.binding.model.model.inter.Model;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.ErrorTransform;
import com.cheese.radio.base.rxjava.RestfulFlowTransformer;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityProfileBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.user.UserEntity;
import com.cheese.radio.ui.user.edit.EditNameModel;
import com.cheese.radio.util.TimePickTool;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.name;
import static com.cheese.radio.ui.Constant.GALLERY_REQUSET_CODE;
import static com.cheese.radio.ui.Constant.GALLERY_REQUSET_CODE_KITKAT;

/**
 * Created by 29283 on 2018/3/9.
 */
@ModelView(value = R.layout.activity_profile, event = R.id.ProfileModel)
public class ProfileModel extends ViewModel<ProfileActivity, ActivityProfileBinding> {

    public ObservableField<String> mDate = new ObservableField<>();
    public ObservableField<String> mSex = new ObservableField<>();
    private TimePickTool pickTool;

    @Inject
    ProfileModel() {
    }

    @Inject
    RadioApi api;
    private ProfileParams params;
    private MyHeadParams headParams = new MyHeadParams("myHead");
    private OptionsPickerView sexPicker;
    private List<String> babySex = new ArrayList<>();
    private List<String> select = new ArrayList<String>();
    private UserEntity userEntity;

    @Override
    public void attachView(Bundle savedInstanceState, ProfileActivity activity) {
        super.attachView(savedInstanceState, activity);
        pickTool = new TimePickTool(mDate, activity);
        initSexPicker();
        params = new ProfileParams("setProperty");

        userEntity = IkeApplication.getUser().getUserEntity();
        mSex.set(userEntity.getSex().equals("M") ? "男孩" : "女孩");
        mDate.set(userEntity.getBirthday());
        getDataBinding().setParams(params.setMsg(IkeApplication.getUser().getUserEntity()));
    }


    public void onSelectClick(View view) {
        pickTool.show();
    }

    public void onPropertyClick(View view) {
        updataUI();
    }

    public void onEditNameClick(View view) {
        ARouterUtil.navigation(name);
    }

    public void updataUI() {
        getDataBinding().setParams(params);
        if (params.isValidName((TextView) getDataBinding().saveAll)) {
            params.setBirthday(mDate.get());
            addDisposable(api.setProperty(params).compose(new ErrorTransform<>()).subscribe(stringInfoEntity -> {
                        BaseUtil.toast(stringInfoEntity.getMessage());
                        if (stringInfoEntity.code() == 0) IkeApplication.getUser().setUserEntity(params);
                        Model.dispatchModel("updataUI");
                    }
            ));
        }
    }

    @Override
    public int onEvent(View view, Event event, Object... args) {
        if (event instanceof EditNameModel) {
            EditNameModel model = (EditNameModel) (EditNameModel) event;
            IkeApplication.getUser().getUserEntity().setNickName(model.name.get());
            params.setNickName(model.name.get());
            updataUI();
        }
        return 1;
    }

    public void onSelectSex(View view) {
        sexPicker.show();
    }

    private void initSexPicker() {
        babySex.add("M");
        babySex.add("F");
        select.add("男孩");
        select.add("女孩");
        sexPicker = new OptionsPickerView.Builder(getT(), (options1, options2, options3, v) -> {
            params.setSex(babySex.get(options2));
            mSex.set(select.get(options2));
        }
        ).build();
        sexPicker.setNPicker(new ArrayList<String>(), select, new ArrayList<String>());
    }

    public void onUploadClick(View view) {
        BaseUtil.checkPermission(this, aBoolean -> {
            if (aBoolean) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    getT().startActivityForResult(intent, GALLERY_REQUSET_CODE_KITKAT);
                } else {
                    getT().startActivityForResult(intent, GALLERY_REQUSET_CODE);
                }
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public void processePictures(String path) {
        File file = new File(path);
        if (file.isFile()) {
            headParams.setInfo(file);
            addDisposable(api.myHead(headParams).compose(new RestfulTransformer<>()).subscribe((myHeadData) -> {
                getDataBinding().setEntity(myHeadData);
            }, BaseUtil::toast));
        }
    }
}
