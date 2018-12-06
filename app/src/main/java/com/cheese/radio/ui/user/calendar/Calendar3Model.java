package com.cheese.radio.ui.user.calendar;

import android.databinding.ObservableField;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.binding.model.adapter.AdapterType;
import com.binding.model.adapter.IEventAdapter;
import com.binding.model.layout.recycler.RecyclerModel;
import com.binding.model.model.ModelView;
import com.binding.model.model.inter.Model;
import com.binding.model.util.BaseUtil;
import com.cheese.radio.R;
import com.cheese.radio.base.rxjava.ErrorTransform;
import com.cheese.radio.base.rxjava.RestfulTransformer;
import com.cheese.radio.databinding.ActivityCalendar3Binding;
import com.cheese.radio.inject.api.RadioApi;
import com.cheese.radio.ui.Constant;
import com.cheese.radio.ui.media.course.details.CourseDetailsParams;
import com.cheese.radio.util.calendarutils.Day;
import com.cheese.radio.util.calendarutils.Month;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @name cheese_radio
 * @anthor bangbang QQ:740090077
 * @time 2018/11/21 7:46 PM
 * 只有编译器可能不骗你。
 */
@ModelView(value = R.layout.activity_calendar3,model = true)
public class Calendar3Model extends RecyclerModel<CalendarActivity, ActivityCalendar3Binding, CalendarEntity> {
    @Inject
    Calendar3Model() {
    }

    @Inject
    RadioApi api;
    private CalendarEntity empty = new CalendarEntity();
    private ClassCalendarParams params = new ClassCalendarParams("getClassCalendar2");
    public ObservableField<String> year = new ObservableField<>();
    private SparseArray<List<CalendarEntity>> months = new SparseArray<>();
    //    private List<CalendarEntity> entities = new ArrayList<>();
    private int courseTypeId = -1;

    @Override
    public void attachView(Bundle savedInstanceState, CalendarActivity calendarActivity) {
        super.attachView(savedInstanceState, calendarActivity);
        initRefreshMonth();
        empty.setIndex(1);
        courseTypeId = getT().getIntent().getIntExtra(Constant.courseTypeId, 0);
        if (courseTypeId != 0) params.setCourseTypeId(courseTypeId);
        initCalendar();
        setRcHttp((offset1, refresh) -> api.getClassCalendar(params).compose(new RestfulTransformer<>()).doOnNext(list -> {
            getDataBinding().includeCalendarView.calendarView.setSchemeDate(CalendarHelper.createCalendarMap(list));
            if(!list.isEmpty())
            months.put(list.get(0).getDays()[1], list);
//            entities.clear();
//            entities.addAll(list);
        }).concatMap(Observable::fromIterable).filter(entity -> entity.getDays()[2] == getDataBinding().includeCalendarView.calendarView.getSelectedCalendar().getDay()).toList().toObservable());

        addEventAdapter((position, entity, type, view) -> {
            switch (type) {
                case AdapterType.add:
                    CourseDetailsParams detailsParams = new CourseDetailsParams("bookClass");
                    detailsParams.setClassId(entity.getClassId());
                    addDisposable(api.getBookClass(detailsParams).compose(new RestfulTransformer<>()).subscribe(s -> {
                        entity.setBookId(s.getBookId());
                        Model.dispatchModel("refreshClock");
                        monthEmitter.onNext(3);
                    }, BaseUtil::toast));
                    break;
                case AdapterType.no:
                    CancelBookParams cancelBook = new CancelBookParams("cancelBook");
                    cancelBook.setBookId(Integer.parseInt(entity.getBookId()));
                    addDisposable(api.cancelBook(cancelBook).compose(new ErrorTransform<>()).subscribe((s -> {
                        entity.setBookId(null);
                        Model.dispatchModel("refreshClock");
                        monthEmitter.onNext(3);
                    }), BaseUtil::toast));
                    break;
            }
            return true;
        });
        setMonthItemListener();
    }

    private void initCalendar() {
        getDataBinding().includeCalendarView.calendarView.setWeekStarWithMon();
        getDataBinding().includeCalendarView.calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                monthEmitter.onNext(3);
//                monthEmitter.onComplete();
            }
        });
        getDataBinding().includeCalendarView.calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {
                BaseUtil.toast("没有更多的日子了");
            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                ArrayList<CalendarEntity> list = new ArrayList<>();
                if (months.get(calendar.getMonth()) != null) {
                    for (CalendarEntity entity : months.get(calendar.getMonth())) {
                        //找到选中日期的课程
                        if (entity.getDays()[2] == calendar.getDay()) {
                            list.add(entity);
                        }
                    }
                    if (list.size() != 0)
                        list.add(empty);
                }
                getAdapter().clear();
                getAdapter().setList(IEventAdapter.NO_POSITION, list, AdapterType.refresh);
            }
        });
    }

    private synchronized void refreshData(int year, int month) {

    }

    private void setMonthItemListener() {
        getDataBinding().includeMonths.months.setOnCheckedChangeListener((group, checkedId) -> {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    Calendar selectedCalendar = getDataBinding().includeCalendarView.calendarView.getSelectedCalendar();
                    getDataBinding().includeCalendarView.calendarView.scrollToCalendar(selectedCalendar.getYear(), i + 1, 1, true);
                }
            }
        });

    }

    private void setYear(int year) {
        this.year.set(year + "年");
    }

    private Emitter<Integer> monthEmitter;

    private void initRefreshMonth() {
        Disposable disposable = Observable.<Integer>create(emitter -> monthEmitter = emitter)
                .throttleLast(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    Calendar selectedCalendar = getDataBinding().includeCalendarView.calendarView.getSelectedCalendar();
                    setYear(selectedCalendar.getYear());
                    Timber.i("year:%1s,month:%2s,Calendar:%3s", selectedCalendar.getYear(), selectedCalendar.getMonth(), selectedCalendar.toString());
                    params.setYearMonth(selectedCalendar.getYear(), selectedCalendar.getMonth());
                    View child = getDataBinding().includeMonths.months.getChildAt(selectedCalendar.getMonth() - 1);
                    getDataBinding().includeMonths.months.check(child.getId());
                    getDataBinding().includeMonths.scrollView.smoothScrollBy(child.getLeft() - getDataBinding().includeMonths.scrollView.getWidth() / 2, 0);
                    onHttp(integer);
                });
    }
    public void successBook(String bookId, Integer classId) {
        monthEmitter.onNext(3);
    }
}
