package com.cheese.radio.ui.user.calendar;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binding.model.adapter.AdapterHandle;
import com.binding.model.adapter.AdapterType;
import com.binding.model.adapter.IEventAdapter;
import com.binding.model.model.ModelView;
import com.binding.model.model.ViewHttpModel;
import com.binding.model.model.inter.Event;
import com.binding.model.model.inter.Model;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.arouter.ARouterUtil;
import com.cheese.radio.base.rxjava.ErrorTransform;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityCalendar2Binding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.course.details.CourseDetailsParams;
import com.cheese.radio.util.MyBaseUtil;
import com.cheese.radio.util.calendarutils.Day;
import com.cheese.radio.util.calendarutils.Month;
import com.cheese.radio.util.views.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import static com.cheese.radio.inject.component.ActivityComponent.Router.enroll;

/**
 * @name cheese_radio
 * @class name：com.cheese.radio.ui.user.calendar
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/11 10:26 PM
 * @change
 * @chang time
 * @class describe
 */
@ModelView(value = R.layout.activity_calendar2, event = R.id.calendarModer, model = true)
public class Calendar2Model extends ViewHttpModel<CalendarActivity, ActivityCalendar2Binding, List<CalendarEntity>> {
    private static boolean status = true;
    private boolean isFirst = true;

    @Inject
    Calendar2Model() {
    }

    @Inject
    RadioApi api;

    @Override
    public void onNext(List<CalendarEntity> calendarEntities) {
        calendarView.setTipsDays(calendarEntities);
        list.clear();
        list.addAll(calendarEntities);
        if (isFirst) {
            String start = MyBaseUtil.formatDate(MyBaseUtil.getYear(), MyBaseUtil.getMonth() - 3);
            String end = MyBaseUtil.formatDate(MyBaseUtil.getYear(), MyBaseUtil.getMonth() + 9);
            initCalendarView(start, end, list);
            isFirst = false;
        }
        calendarView.setTipsDays(calendarEntities);
    }

    private CalendarEntity empty = new CalendarEntity();
    private CalendarView calendarView;
    private String selectDay;
    private List<CalendarEntity> list = new ArrayList<>();
    private List<Month> months;
    private int selectMonth = 0;
    public final ObservableList<CalendarEntity> theDayClass = new ObservableArrayList<>();
    private ClassCalendarParams params = new ClassCalendarParams("getClassCalendar2");
    private Calendar now = Calendar.getInstance();
    private Day currentDay;
    private View currentView;
    @Override
    public void attachView(Bundle savedInstanceState, CalendarActivity calendarActivity) {
        super.attachView(savedInstanceState, calendarActivity);

        int courseTypeId = getT().getIntent().getIntExtra(Constant.courseTypeId, 0);
        if (courseTypeId != 0) params.setCourseTypeId(courseTypeId);
        refreshUI();
        empty.setIndex(1);
    }

    @Override
    public int onEvent(View view, Event event, Object... args) {
//        onHttp(1);
        return 1;
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

    private void updataUI(List<CalendarEntity> tipsDays) {
        boolean isHaveSelectDay = false;
        if (selectDay == null) {
            if (tipsDays != null) {
                for (int i = tipsDays.size() - 1; i >= 0; i--) {
                    CalendarEntity tipsDay = tipsDays.get(i);
                    if (tipsDay != null) {
                        if (tipsDay.isBook() || tipsDay.isCanBook()) {
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
        getDataBinding().calendarView.setOnSelectView((day, view) -> {
            this.currentDay=day;
            this.currentView=view;
        });
        calendarView.setDateStartString(dateStartString);
        calendarView.setDateEndString(dateEndString);
        updataUI(tipsDays);


//        calendarView.setTipsDays(tipsDays);

        calendarView.setOnCalendarViewListener(new CalendarView.OnCalendarViewListener() {

            @Override
            public void onPagerChanged(List<Month> months, int position) {
                boolean isInit = false;//因为第一次触发  viewPager.setCurrentItem(i)的时候是在onCreate方法内的

                if (Calendar2Model.this.months == null) {
                    Calendar2Model.this.months = months;
                    addMonthItem(getDataBinding().linearLayoutMonth);
                    isInit = true;
                }
                Month month = months.get(position);
                params.setYearMonth(month.getYear(), month.getMonth());
                onHttp(1);
                getDataBinding().textViewYear.setText(month.getYear() + "年");
                updateMonth(month.getYear(), month.getMonth(), isInit);
                selectThisMonthDay(month.getYear(), month.getMonth());
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
                if (list.size() != 0)
                    list.add(empty);
                theDayClass.clear();
                theDayClass.addAll(list);
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

    private void refreshUI() {
        calendarView = getDataBinding().calendarView;
        setRcHttp((offset1, refresh) -> api.getClassCalendar(params).compose(new RestfulTransformer<>()));
    }

    public boolean eventAdapter(int position, CalendarEntity entity, @AdapterHandle int type, View view) {
        switch (type) {
            case AdapterType.add:
                CourseDetailsParams detailsParams = new CourseDetailsParams("bookClass");
                detailsParams.setClassId(entity.getClassId());
                addDisposable(api.getBookClass(detailsParams).compose(new RestfulTransformer<>()).subscribe(s -> {
                    entity.setBookId(s.getBookId());
//                    theDayClass.notifyChange();
                    Model.dispatchModel("refreshClock");
                    if(currentDay!=null && currentView !=null){
                        currentDay.setTipsType(1);
                        calendarView.selectDaySmallCircleColor(currentDay,(TextView) currentView);
                    }

                }, BaseUtil::toast));
                break;
            case AdapterType.no:
                CancelBookParams cancelBook = new CancelBookParams("cancelBook");
                cancelBook.setBookId(Integer.parseInt(entity.getBookId()));
                addDisposable(api.cancelBook(cancelBook).compose(new ErrorTransform<>()).subscribe((s -> {
                    entity.setBookId(null);
//                    theDayClass.notifyChange();
                    Model.dispatchModel("refreshClock");
                    if(currentDay!=null && currentView !=null){
                        currentDay.setTipsType(2);
                        calendarView.selectDaySmallCircleColor(currentDay,(TextView) currentView);
                    }
                }), BaseUtil::toast));
                break;
        }
        return true;
    }

    public IEventAdapter getEventAdapter() {
        IEventAdapter<CalendarEntity> eventAdapter = this::eventAdapter;
        return eventAdapter;
    }

    public void successBook(String bookId, Integer classId) {
        for (int i = 0; i < theDayClass.size(); i++) {
            if (theDayClass.get(i).getClassId() == classId) {
                theDayClass.get(i).setBookId((bookId));
//                theDayClass.notifyChange();
            }
        }
    }


}








