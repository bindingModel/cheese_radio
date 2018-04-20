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

import com.bigkoo.pickerview.OptionsPickerView;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.model.inter.Event;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.databinding.ActivityEnrollBinding;
import com.cheese.radio.inject.component.ActivityComponent;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.user.product.list.ProductsEntity;
import com.cheese.radio.ui.user.product.place.ClassPlaceEntity;
import com.cheese.radio.util.CityPickTool;
import com.cheese.radio.util.TimePickTool;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.products;

/**
 * Created by 29283 on 2018/3/10.
 */
@ModelView(value = R.layout.activity_enroll,event = R.id.EnrollModel, model = true)
public class EnrollModel extends ViewModel<EnrollActivity, ActivityEnrollBinding>  {

    @Inject
    EnrollModel() {
    }

    public ObservableField<String> mDate = new ObservableField<String>("");
    public ObservableField<String> mCity = new ObservableField<>("");
    public ObservableField<String> mAge = new ObservableField<>("");
    public ObservableField<String> mSex = new ObservableField<>("");
    public ObservableField<String> course = new ObservableField<>("");
    public ObservableInt currentItem=new ObservableInt();
    private EnroolParams params = new EnroolParams("createOrder");

    private int checkId=-1;
    private OptionsPickerView agePicker, sexPicker;
    private ArrayList<String> babyAge = new ArrayList<>();
    private ArrayList<String> babySex = new ArrayList<>();
    private CityPickTool cityPickTool;
    private TimePickTool timePickSelect;

    @Override
    public void attachView(Bundle savedInstanceState, EnrollActivity enrollActivity) {
        super.attachView(savedInstanceState, enrollActivity);

        setData();
        initAgePicker();
        initSexPicker();
        cityPickTool = new CityPickTool(mCity, getT());
        timePickSelect = new TimePickTool(mDate, getT());
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
        sexPicker = new OptionsPickerView.Builder(getT(), (options1, options2, options3, v) -> mSex.set(babySex.get(options2))
        ).build();
        sexPicker.setNPicker(new ArrayList<String>(), babySex, new ArrayList<String>());
    }

//    public void getProductId(Object obj) {
//        ProductsEntity entity = obj instanceof ProductsEntity ? ((ProductsEntity) obj) : null;
//        if (entity != null) {
//            getDataBinding().setProductEntity(entity);
//            params.setProductId(entity.getId());
//        }
//
//    }
    @Override
    public int onEvent(View view, Event event, Object... args) {
        ProductsEntity productsEntity = event instanceof ProductsEntity ? ((ProductsEntity) event) : null;
        if (productsEntity != null) {
            getDataBinding().setProductEntity(productsEntity);
            params.setProductId(productsEntity.getId());
        }
        ClassPlaceEntity placeEntity = event instanceof ClassPlaceEntity ? ((ClassPlaceEntity) event) : null;
        if (placeEntity != null) {
            getDataBinding().setPlaceEntity(placeEntity);
            params.setProductId(placeEntity.getId());
        }
        return 1;
    }
    public void onEnrollClick(View view){
        //调用下订单
    }
    public void onClassADClick(View view){
        ARouterUtil.navigation(ActivityComponent.Router.place);
    }

    //隐藏虚拟键盘
    public static void HideKeyboard(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );
        if ( imm.isActive( ) ) {
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );

        }
    }


}
