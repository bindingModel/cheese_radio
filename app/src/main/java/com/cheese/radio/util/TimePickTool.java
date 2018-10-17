package com.cheese.radio.util;

import android.content.Context;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.cheese.radio.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 29283 on 2018/3/24.
 */

public class TimePickTool {
    private TimePickerView pvCustomTime;

    private TimePickerView.OnTimeSelectListener listener;

    private Context context;

    public TimePickTool( Context context,TimePickerView.OnTimeSelectListener listener) {
        this.context = context;
        this.listener=listener;
        initCustomTimePicker();
    }

    public void show(){
        pvCustomTime.show();
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
        Integer[] nowDay=MyBaseUtil.getNowDate();
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(nowDay[0]-10, nowDay[1]-1, nowDay[2]);
        Calendar endDate = Calendar.getInstance();
        endDate.set(nowDay[0], nowDay[1]-1, nowDay[2]);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerView.Builder(context, listener)
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
//        return SimpleDateFormat.getDateInstance().format(date);
    }
}
