package com.cheese.radio.ui.user.enroll;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


import com.alipay.sdk.app.PayTask;
import com.bigkoo.pickerview.OptionsPickerView;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.Event;
import com.binding.model.model.inter.Model;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityEnrollBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.user.enroll.params.CreateOrderParams;
import com.cheese.radio.ui.user.product.list.ProductsEntity;
import com.cheese.radio.ui.user.product.place.ClassPlaceEntity;
import com.cheese.radio.util.CityPickTool;
import com.cheese.radio.util.TimePickTool;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static com.cheese.radio.inject.component.ActivityComponent.Router.products;

/**
 * Created by 29283 on 2018/3/10.
 */
@ModelView(value = R.layout.activity_enroll, event = R.id.EnrollModel, model = true)
public class EnrollModel extends ViewModel<EnrollActivity, ActivityEnrollBinding> {

    @Inject
    EnrollModel() {
    }

    @Inject
    RadioApi api;
    public ObservableField<String> mDate = new ObservableField<String>("");
    public ObservableField<String> mCity = new ObservableField<>("");
    public ObservableField<String> mAge = new ObservableField<>("");
    public ObservableField<String> mSex = new ObservableField<>("");
    public ObservableField<String> course = new ObservableField<>("");
    public ObservableField<String> mPrice = new ObservableField<>("");
    public ObservableInt currentItem = new ObservableInt();
    private CreateOrderParams params = new CreateOrderParams("createOrder");

    private int checkId = -1;
    private OptionsPickerView agePicker, sexPicker;
    private ArrayList<String> babyAge = new ArrayList<>();
    private ArrayList<String> babySex = new ArrayList<>();
    private CityPickTool cityPickTool;
    private TimePickTool timePickSelect;

    @Override
    public void attachView(Bundle savedInstanceState, EnrollActivity enrollActivity) {
        super.attachView(savedInstanceState, enrollActivity);
        if (!IkeApplication.isLogin(true)) finish();
        setData();
        initAgePicker();
        initSexPicker();
        cityPickTool = new CityPickTool(mCity, getT());
        timePickSelect = new TimePickTool(mDate, getT());
        getDataBinding().setParams(params);
    }


    public void onSelectCityClick(View view) {
        HideKeyboard(view);
        cityPickTool.onSelectCity();
    }

    public void onSelectBirthClick(View view) {
        HideKeyboard(view);
        timePickSelect.show();
    }

    public void onSelectAgeClick(View view) {
        HideKeyboard(view);
        agePicker.show();
    }

    public void onSelectSexClick(View view) {
        HideKeyboard(view);
        sexPicker.show();
    }

    public void onSelectCourseClick(View view) {
        HideKeyboard(view);
        ARouterUtil.navigation(products);
    }

    private void setData() {//给条件选择器的容器填充数据
        babyAge.add("4~5");
        babyAge.add("6~8");
        babySex.add("男");
        babySex.add("女");
    }

    private void initAgePicker() {//初始化条件选择器
        agePicker = new OptionsPickerView.Builder(getT(), (options1, options2, options3, v) -> mAge.set(babyAge.get(options2))
        ).build();
        agePicker.setNPicker(new ArrayList<String>(), babyAge, new ArrayList<String>());
    }

    private void initSexPicker() {
        sexPicker = new OptionsPickerView.Builder(getT(), (options1, options2, options3, v) -> {
            mSex.set(babySex.get(options2));
            params.setSex(mSex.get().equals("男")?"F":"M");}
        ).build();
        sexPicker.setNPicker(new ArrayList<String>(), babySex, new ArrayList<String>());
    }


    @Override
    public int onEvent(View view, Event event, Object... args) {
        ProductsEntity productsEntity = event instanceof ProductsEntity ? ((ProductsEntity) event) : null;
        if (productsEntity != null) {
            getDataBinding().setProductEntity(productsEntity);
            params.setProductId(productsEntity.getId());
            mPrice.set(String.valueOf(productsEntity.getPrice()));

        }
        ClassPlaceEntity placeEntity = event instanceof ClassPlaceEntity ? ((ClassPlaceEntity) event) : null;
        if (placeEntity != null) {
            getDataBinding().setPlaceEntity(placeEntity);
            params.setFieldId(placeEntity.getId());
        }
        return 1;
    }

    public void onEnrollClick(View view) {
        params.setAddress(mCity.get());
        params.setPayType(currentItem.get());
        //productId在Event里处理了
        params.setAgeRange(mAge.get());
        //phone在XML里处理了
        //name在XML里处理了

        params.setBirthday(mDate.get());
        //        //创建订单
        if (params.isLegal(view))
            addDisposable(api.createOrder(params).compose(new RestfulTransformer<>()).subscribe(s -> {
                orderPay("");
            }, BaseUtil::toast));
//{"code":"0","data":{"prepareId":"pp20180424-373469","payOrderCode":"20180424-373469"}}支付成功后的状态
    }

    public void orderPay(String orderNo) {

        //zfb
        addDisposable(Observable.create(
                (ObservableOnSubscribe<PayResult>) e -> e.onNext(new PayResult(new PayTask(getT()).payV2("", true)))
        ).observeOn(AndroidSchedulers.mainThread())
                .filter(payResult -> {
                    boolean success = "9000".equals(payResult.getResultStatus());
                    if (!success) BaseUtil.toast(getT(), "支付失败");
                    return success;
                }).subscribeOn(Schedulers.newThread()).subscribe(payResult12 -> {
                    BaseUtil.toast("支付成功");
                    Model.dispatchModel("paySuccess");
                }));

    }

    public void onClassADClick(View view) {
        ARouterUtil.navigation(ActivityComponent.Router.place);
    }

    //隐藏虚拟键盘
    public static void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

        }
    }


}
