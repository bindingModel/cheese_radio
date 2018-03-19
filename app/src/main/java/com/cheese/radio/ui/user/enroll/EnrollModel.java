package com.cheese.radio.ui.user.enroll;

import android.databinding.ObservableField;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.databinding.ActivityEnrollBinding;
import com.cheese.radio.util.GetJsonDataUtil;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/10.
 */
@ModelView(R.layout.activity_enroll)
public class EnrollModel extends ViewModel<EnrollActivity, ActivityEnrollBinding> {

    @Inject
    EnrollModel() {
    }

    public ObservableField<String> mDate = new ObservableField<String>("");
    public ObservableField<String> mCity = new ObservableField<>("");
    public ObservableField<String> mAge = new ObservableField<>("");
    public ObservableField<String> mSex = new ObservableField<>("");
    public ObservableField<String> course = new ObservableField<>("");

    private ArrayList<CitysBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private boolean isLoaded = false;
    private TimePickerView pvCustomTime;
    private OptionsPickerView agePicker, sexPicker;
    private ArrayList<String> babyAge = new ArrayList<>();
    private ArrayList<String> babySex = new ArrayList<>();

    @Override
    public void attachView(Bundle savedInstanceState, EnrollActivity enrollActivity) {
        super.attachView(savedInstanceState, enrollActivity);
        setData();
        initCustomTimePicker();
        initAgePicker();
        initSexPicker();
    }

    public void onSelectCityClick(View view) {
        if (isLoaded) ShowPickerView();
        else {
            mHandler.sendEmptyMessage(MSG_LOAD_DATA);
            Handler handler = new Handler();
            handler.postDelayed(this::ShowPickerView, 500);

        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        BaseUtil.toast("Begin Parse Data" + Toast.LENGTH_SHORT);
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    BaseUtil.toast("Parse Succeed" + Toast.LENGTH_SHORT);
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    BaseUtil.toast("Parse Failed" + Toast.LENGTH_SHORT);
                    break;

            }
        }
    };

    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(getT(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                mCity.set(tx);
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(getT(), "province.json");//获取assets目录下的json文件数据

        ArrayList<CitysBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
//                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);
//
//                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                        City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    private ArrayList<CitysBean> parseData(String result) {//Gson 解析
        ArrayList<CitysBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                CitysBean entity = gson.fromJson(data.optJSONObject(i).toString(), CitysBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


    private void initCustomTimePicker() {

        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         **/
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(getT(), (date, v) -> mDate.set(getTime(date)))
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
               /*.gravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, v -> {
                    final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                    TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                    tvSubmit.setOnClickListener(v1 -> {
                        pvCustomTime.returnData();
                        pvCustomTime.dismiss();
                    });
                    ivCancel.setOnClickListener(v1 -> pvCustomTime.dismiss());
                })
                .setContentSize(18)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        return format.format(date);
        return SimpleDateFormat.getDateInstance().format(date);
    }

    public void onSelectBirthClick(View view) {
        pvCustomTime.show();
    }

    public void onSelectAgeClick(View view) {//调用选择器的show方法
        agePicker.show();
    }

    public void onSelectSexClick(View view) {
        sexPicker.show();
    }

    private void setData() {//给条件选择器的容器填充数据
        babyAge.add("4~5");
        babyAge.add("6~7");
        babySex.add("男");
        babySex.add("女");
    }

    private void initAgePicker() {//初始化条件选择器
        agePicker = new OptionsPickerView.Builder(getT(), (options1, options2, options3, v) -> mAge.set(babyAge.get(options2))
        ).build();
        agePicker.setNPicker(new ArrayList<String>(), babyAge, new ArrayList<String>());
    }

    private void initSexPicker() {
        agePicker = new OptionsPickerView.Builder(getT(), (options1, options2, options3, v) -> mSex.set(babyAge.get(options2))
        ).build();
        sexPicker.setNPicker(new ArrayList<String>(), babySex, new ArrayList<String>());
    }
}
