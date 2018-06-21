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
import com.cheese.radio.BuildConfig;
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
import com.cheese.radio.util.MyBaseUtil;
import com.cheese.radio.util.TimePickTool;
import com.cheese.radio.util.rxview.RxView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
        initView();
        initAgePicker();
        initSexPicker();
        cityPickTool = new CityPickTool(mCity, getT());
        timePickSelect = new TimePickTool(getT(), ((date, v) -> mDate.set(MyBaseUtil.getTime(date))));
        getDataBinding().setParams(params);
        Model.dispatchModel("refreshUI");
        iwxapi = WXAPIFactory.createWXAPI(enrollActivity, enrollActivity.getString(R.string.wechat_AppID), false);

    }

    private void initView() {
        RxView.setViewEnable(false);
        addDisposable(
                RxView.bindView(getDataBinding().enrollBtn).throttleFirst(2, TimeUnit.SECONDS)
                        .subscribe((o -> onEnrollClick(null))));
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
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.productId, params.getProductId());
        ARouterUtil.navigation(products, bundle);
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
            params.setSex(mSex.get().equals("男") ? "F" : "M");
        }
        ).build();
        sexPicker.setNPicker(new ArrayList<String>(), babySex, new ArrayList<String>());
    }


    @Override
    public int onEvent(View view, Event event, Object... args) {
        ProductsEntity productsEntity = event instanceof ProductsEntity ? ((ProductsEntity) event) : null;
        if (productsEntity != null) {
            getDataBinding().setProductEntity(productsEntity);
            params.setProductId(productsEntity.getId());
            mPrice.set(String.valueOf(productsEntity.getPriceText()));

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
        if (!params.isLeagal(view)) return;
        if (currentItem.get() == 0)
            orderWXPay();
        else orderAliPay();
//{"code":"0","data":{"prepareId":"pp20180424-373469","payOrderCode":"20180424-373469"}}支付成功后的状态
    }

    private IWXAPI iwxapi;

    public void orderWXPay() {
        //wx
        addDisposable(api.createWXOrder(params).compose(new RestfulTransformer<>()).subscribe((bean -> {
            PayReq req = new PayReq();
            req.appId = getT().getResources().getString(R.string.wechat_AppID);
            req.partnerId = bean.getPartnerId();
            req.prepayId = bean.getPrepareId();
            req.nonceStr = bean.getNonceStr();
            req.timeStamp = bean.getTimestamp();
//        req.packageValue = bean.getPa();
            req.packageValue = "Sign=WXPay";
            req.sign = bean.getPaySign();
            Boolean ans = iwxapi.sendReq(req);
        }), BaseUtil::toast));


//        BaseUtil.toast(ans.toString());
        //zfb


//        Model.dispatchModel("refreshUI");
    }

    private void orderAliPay() {
//        String date="alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2018052360186186&biz_content=%7B%22out_trade_no%22%3A%2220180606-552254%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E8%8A%9D%E5%A3%AB%E8%AE%A2%E5%8D%9520180606-552254%22%2C%22timeout_express%22%3A%2224h%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2F111.231.237.11%3A8081%2F1.0%2FaliPayment&sign=XVzF5L5x%2ByRKfDYbHylGB9ZI3H8sGcSie4VmkoJfTvqtT0DAMqVvh3qLHb5QRLP6mWAp9vREGAWXO9MPVWjgltC7kqHhZW22qDbakgZ3NOOKehm0Duuf%2BZw%2FZ3W%2B3Q9E16CRnUioin99hQzcCQzoGVNmgRmzvUvXnkmaFZLKzCUEau1Et8Gi%2B1j0FbfUkRAUcXmnBjju45232uUFnltbTsYFh996r7yqDtj97fgTi8TL%2BV62eSJvRpPV63iIjMcuvedipQKhf1ZThZ4CD5z6PM12%2BcLc6ZmROYGipB3LKJBbVZKikPq4AMXDCaMGZki0Dvc9fjU%2BcD6Phzj8SRExoQ%3D%3D&sign_type=RSA2×tamp=2018-06-06+10%3A12%3A15&version=1.0";
        addDisposable(api.createAliOrder(params)
                .map(s -> new PayResult(new PayTask(getT()).payV2(s.getData().getAlipay(), true)))
                .filter(payResult -> {
                    boolean success = "9000".equals(payResult.getResultStatus());
                    if (!success) BaseUtil.toast(getT(), "支付失败");
                    return success;
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(payResult -> {
                    BaseUtil.toast("支付成功");
                    Model.dispatchModel("paySuccess");
                }, BaseUtil::toast));
    }

    private void paySuccess() {
        Model.dispatchModel("refreshUI");
        getT().finish();
    }

    public void onClassADClick(View view) {
        HideKeyboard(view);
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.placeId, params.getFieldId());
        ARouterUtil.navigation(ActivityComponent.Router.place, bundle);
    }

    //隐藏虚拟键盘
    public static void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

        }
    }


}
