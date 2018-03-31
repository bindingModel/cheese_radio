package com.cheese.radio.ui.user.calendar;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binding.model.model.ModelView;
import com.binding.model.model.PopupRecyclerModel;
import com.binding.model.model.ViewHttpModel;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.RestfulFlowTransformer;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityCalendarBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.util.MyBaseUtil;
import com.cheese.radio.util.calendarutils.Day;
import com.cheese.radio.util.calendarutils.Month;
import com.cheese.radio.util.calendarutils.TipsDay;
import com.cheese.radio.util.views.CalendarView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 29283 on 2018/3/26.
 */
@ModelView(R.layout.activity_calendar)
public class CalendarModel extends ViewHttpModel<CalendarActivity, ActivityCalendarBinding, CalendarEntity> {
    @Inject
    CalendarModel() {
    }
    @Inject RadioApi api;
    @Override
    public void accept(CalendarEntity calendarEntity) throws Exception {

    }

    private CalendarView calendarView;
    private String selectDay;
    private List<CalendarEntity> list = new ArrayList<>();
    private List<Month> months;
    private int selectMonth = 0;

    private ClassCalendarParams params=new ClassCalendarParams("getClassCalendar","2018-03");


    @Override
    public void attachView(Bundle savedInstanceState, CalendarActivity activity) {
        super.attachView(savedInstanceState, activity);
        calendarView = getDataBinding().calendarView;
        api.getClassCalendar(params).compose(new RestfulTransformer<>()).subscribe(calendarEntities ->{
            calendarView.setTipsDays(calendarEntities);
                list.addAll(calendarEntities);
            initCalendarView("2018-01-01", "2018-05", list);});

    }


    private Day getSelectDayFromMonth(int year, int month) {
        Day day = null;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                CalendarEntity calendarEntity = list.get(i);
                if (calendarEntity != null) {
                    String dayString = calendarEntity.getDay();
                    day = calendarView.isOneMonth(dayString, year, month);
                    if (day != null) {
                        break;
                    }
                }
            }
        }
        if (day == null) {//如果没有找到先看看是不是当前月,如果是当前月选中当前月的日期
            String nowDay = MyBaseUtil.getNowDay();
            day = calendarView.isOneMonth(nowDay, year, month);
        }
        if (day == null) {//如果不是当前月,默认选中当前月的第一天
            day = new Day();
            day.setSolar(year, month, 1);
        }
        return day;
    }

    /**
     * 载入当前月的
     */
    private void selectThisMonthDay(int year, int month) {
        Day day = getSelectDayFromMonth(year, month);
        calendarView.selectTheDay(day);
    }

    private void initCalendarView(String dateStartString, String dateEndString, List<CalendarEntity> tipsDays) {
        calendarView.setDateStartString(dateStartString);
        calendarView.setDateEndString(dateEndString);
        boolean isHaveSelectDay = false;
        if (selectDay == null) {
            if (tipsDays != null) {
                for (int i = tipsDays.size() - 1; i >= 0; i--) {
                    CalendarEntity tipsDay = tipsDays.get(i);
                    if (tipsDay != null) {
                        if (tipsDay.isSelect()) {
                            selectDay = tipsDay.getDay();
                            calendarView.setSelectDay(selectDay);
                            isHaveSelectDay = true;
                            break;
                        } else {
                            if (i == 0) {
                                selectDay = tipsDay.getDay();
                                calendarView.setSelectDay(selectDay);
                                isHaveSelectDay = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (!isHaveSelectDay) {
                selectDay = MyBaseUtil.getNowDay();
                calendarView.setSelectDay(selectDay);
            }
        } else {
            calendarView.setSelectDay(selectDay);
        }


        calendarView.setTipsDays(tipsDays);

        calendarView.setOnCalendarViewListener(new CalendarView.OnCalendarViewListener() {

            @Override
            public void onPagerChanged(List<Month> months, int position) {
                boolean isInit = false;//因为第一次触发  viewPager.setCurrentItem(i)的时候是在onCreate方法内的
                if (CalendarModel.this.months == null) {
                    CalendarModel.this.months = months;
                    addMonthItem(getDataBinding().linearLayoutMonth);
                    isInit = true;
                }
                Month month = months.get(position);
                if (month != null) {
                    getDataBinding().textViewYear.setText(month.getYear() + "年");
                    updateMonth(month.getYear(), month.getMonth(), isInit);
                    selectThisMonthDay(month.getYear(), month.getMonth());
                }
            }
            //日历上点击目标时间时触发
            @Override
            public void selectDay(LinearLayout linearLayout, Day day) {

            }

            @Override
            public void noThingSelect(LinearLayout linearLayout) {

            }
        });
        calendarView.initView(-1);//需要在init之前setOnCalendarViewListener
    }

    private void moveToSelectMonth(int newSelectMonth, boolean isInit) {

        int calendarview_month_item_unselect = ContextCompat.getColor(getT(), R.color.text_gray);
        int calendarview_month_item_select = ContextCompat.getColor(getT(), R.color.text_yellow);

        if (months != null) {
            months.get(selectMonth).getTextView().setTextColor(calendarview_month_item_unselect);
            months.get(newSelectMonth).getTextView().setTextColor(calendarview_month_item_select);
            selectMonth = newSelectMonth;
            final int calendar_model_item_width = (int) getT().getResources().getDimension(R.dimen.activity_account_monthpay_function_month_item_width);
            if (isInit) {
                getDataBinding().horizontalScrollView.post(() -> getDataBinding().horizontalScrollView.smoothScrollTo(selectMonth * calendar_model_item_width, 0));
            } else {
                getDataBinding().horizontalScrollView.smoothScrollTo(selectMonth * calendar_model_item_width, 0);
            }
        }
    }

    private void addMonthItem(LinearLayout linearLayout) {
        if (months != null) {
            linearLayout.removeAllViews();
            LayoutInflater layoutInflater = LayoutInflater.from(getT());
            for (int i = 0; i < months.size(); i++) {
                final Month month = months.get(i);
                View activity_account_monthpay_function_month_item = layoutInflater.inflate(R.layout.activity_calendar_month_item, linearLayout, false);
                TextView textViewMonth = (TextView) activity_account_monthpay_function_month_item.findViewById(R.id.textViewMonth);
                textViewMonth.setText(month.getMonth() + "月");
                if (month.isSelect()) {
                    selectMonth = i;
                }
                month.setTextView(textViewMonth);
                textViewMonth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToTheMonth(month);
                    }
                });
                linearLayout.addView(activity_account_monthpay_function_month_item);
            }
        }
    }

    private void updateMonth(int year, int month, boolean isInit) {
        if (months != null) {
            int selectMonth = 0;
            for (int i = 0; i < months.size(); i++) {
                if (months.get(i).getYear() == year && months.get(i).getMonth() == month) {
                    selectMonth = i;
                    break;
                }
            }
            moveToSelectMonth(selectMonth, isInit);
        }

    }

    private void goToTheMonth(Month month) {
        calendarView.goToMonth(month, false);
    }
}
