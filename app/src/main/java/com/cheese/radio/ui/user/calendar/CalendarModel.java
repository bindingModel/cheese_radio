package com.cheese.radio.ui.user.calendar;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binding.model.model.ModelView;
import com.binding.model.model.PopupRecyclerModel;
import com.binding.model.model.ViewHttpModel;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.RestfulFlowTransformer;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityCalendarBinding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.home.CanBookParams;
import com.cheese.radio.util.MyBaseUtil;
import com.cheese.radio.util.calendarutils.Day;
import com.cheese.radio.util.calendarutils.Month;
import com.cheese.radio.util.calendarutils.TipsDay;
import com.cheese.radio.util.views.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.enroll;

/**
 * Created by 29283 on 2018/3/26.
 */
@ModelView(value = R.layout.activity_calendar, event = R.id.calendarModer, model = true)
public class CalendarModel extends ViewHttpModel<CalendarFragment, ActivityCalendarBinding, List<CalendarEntity>> {
    private static boolean status = true;
    private boolean isFirst = true;

    @Inject
    CalendarModel() {
    }

    @Inject
    RadioApi api;

    @Override
    public void accept(List<CalendarEntity> calendarEntities) throws Exception {
        calendarView.setTipsDays(calendarEntities);
        list.clear();
        list.addAll(calendarEntities);
        if (isFirst) {

            initCalendarView("2017-12", "2018-12", list);
            isFirst = false;
        }

//        calendarView.setTipsDays(calendarEntities);
    }

    private CalendarView calendarView;
    private String selectDay;
    private List<CalendarEntity> list = new ArrayList<>();
    private List<Month> months;
    private int selectMonth = 0;
    public final ObservableField<ArrayList<CalendarEntity>> theDayClass = new ObservableField<>();
    private ClassCalendarParams params = new ClassCalendarParams("getClassCalendar");
    private Calendar now = Calendar.getInstance();

    @Override
    public void attachView(Bundle savedInstanceState, CalendarFragment calendarFragment) {
        super.attachView(savedInstanceState, calendarFragment);
        refreshUI();
//        MyBaseUtil.getNowDate();
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

    private void updataUI(List<CalendarEntity> tipsDays){
        boolean isHaveSelectDay = false;
        if (selectDay == null) {
            if (tipsDays != null) {
                for (int i = tipsDays.size() - 1; i >= 0; i--) {
                    CalendarEntity tipsDay = tipsDays.get(i);
                    if (tipsDay != null) {
                        if (tipsDay.isBook()||tipsDay.isCanBook()) {
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
    }
    private void initCalendarView(String dateStartString, String dateEndString, List<CalendarEntity> tipsDays) {
        calendarView.setDateStartString(dateStartString);
        calendarView.setDateEndString(dateEndString);
        updataUI(tipsDays);



//        calendarView.setTipsDays(tipsDays);

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
                    params.setYearMonth(month.getYear(), position);
                    onHttp(1);
                }
            }

            //日历上点击目标时间时触发
            @Override
            public void selectDay(LinearLayout linearLayout, Day day) {
                Iterator<CalendarEntity> iterator = list.iterator();
                ArrayList<CalendarEntity> list = new ArrayList<>();
                while (iterator.hasNext()) {
                    CalendarEntity entity = iterator.next();
                    //找到选中日期的课程
                    if (equalNumbers(entity.getDays(), day.getSolar())) {
                        list.add(entity);
                    }
                }
                theDayClass.set(list);
            }

            @Override
            public void noThingSelect(LinearLayout linearLayout) {

            }
        });
        calendarView.initView(-1);//需要在init之前setOnCalendarViewListener
    }

    private void moveToSelectMonth(int newSelectMonth, boolean isInit) {

        int calendarview_month_item_unselect = ContextCompat.getColor(getT().getContext(), R.color.text_gray);
        int calendarview_month_item_select = ContextCompat.getColor(getT().getContext(), R.color.text_yellow);

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
            LayoutInflater layoutInflater = LayoutInflater.from(getT().getContext());
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


    private boolean equalNumbers(int[] a, int[] b) {
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    //-----------------------------------------------------------------------
    public void onClick(View view) {
        ARouterUtil.navigation(enroll);
    }

    public void refreshUI() {
        addDisposable(api.getCanBook(new CanBookParams("canBook")).compose(new RestfulTransformer<>()).
                subscribe(canBookData -> {
                            IkeApplication.getUser().setCanBookCheck(status = canBookData.isResult());
                            if (status) {
                                getDataBinding().clock.setVisibility(View.GONE);
                                calendarView = getDataBinding().calendarView;
                                setRcHttp((offset1, refresh) -> api.getClassCalendar(params).compose(new RestfulTransformer<>()));
                            }
                        }
                ));

    }
}
