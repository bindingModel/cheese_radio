package com.cheese.radio.util.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binding.model.App;
import com.cheese.radio.R;
import com.cheese.radio.ui.IkeApplication;
import com.cheese.radio.ui.user.calendar.CalendarEntity;
import com.cheese.radio.util.calendarutils.Day;
import com.cheese.radio.util.calendarutils.Month;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.cheese.radio.util.calendarutils.CalendarUtil.getDays;


/**
 * Created by xihuan22 on 2017/7/17.
 */

public class CalendarView extends LinearLayout {
    private Context context;

    private boolean canCancel = false;
    private int itemLayoutId;    //使用-1可以变成内存布局,这样可以提升一点加载速度,不过意义不是很大
    private String dateStartString;
    private String dateEndString;
    private int[] dateStart;//日历开始年、月
    private int[] dateEnd;//日历结束年、月
    private final int[] selectDay = new int[3];
    private final List<CalendarEntity> tipsDays = new ArrayList<CalendarEntity>();
    private final Set<CalendarEntity> tipsDaysSet = new HashSet<>();
    private int calendarView_textColorUnChoose;
    private int calendarView_textColorChoose;
    private int calendarView_textColorTips;
    private int calendarView_margin;
    private Drawable grayCircleDrawable;
    private Drawable greenCircleDrawable;
    private Drawable yellowCircleDrawable;
    private final List<Month> months = new ArrayList<Month>();
    private LayoutInflater layoutInflater;
    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;

    private OnCalendarViewListener onCalendarViewListener;

    private int selectMonthPosition = 0;
    private int selectDayPosition = 0;

    public CalendarView(Context context, String dateStart, String dateEnd) {
        super(context);
        this.context = context;
        this.dateStart = convertDay(dateStart);
        this.dateEnd = convertDay(dateEnd);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initAttrs(attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttrs(attrs);
    }

    protected void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.calendarView);
        try {
            dateStartString = typedArray.getString(R.styleable.calendarView_dateStart);
            dateEndString = typedArray.getString(R.styleable.calendarView_dateEnd);
        } finally {
            typedArray.recycle();
        }
    }

    //一定返回长度大于2的数据或者为null的数据
    public int[] convertDay(String string) {
        int[] res = null;
        if (string == null) {
            return res;
        }
        try {
            String[] strings = string.split("-");
            if (strings.length >= 2) {
                res = new int[strings.length];
                for (int i = 0; i < strings.length; i++) {
                    res[i] = Integer.valueOf(strings[i]);
                }
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean isOneDay(String string, Day day) {
        boolean res = false;
        if (string == null || day == null) {
            return res;
        }
        try {
            String[] strings = string.split("-");
            if (strings.length >= 3) {
                int[] theDay = new int[strings.length];
                for (int i = 0; i < strings.length; i++) {
                    theDay[i] = Integer.valueOf(strings[i]);
                }
                if (theDay[0] == day.getSolar()[0] && theDay[1] == day.getSolar()[1] && theDay[2] == day.getSolar()[2]) {
                    res = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    public Day isOneMonth(String string, int year, int month) {
        if (string == null) {
            return null;
        }
        try {
            String[] strings = string.split("-");
            if (strings.length >= 3) {
                int[] theDay = new int[strings.length];
                for (int i = 0; i < strings.length; i++) {
                    theDay[i] = Integer.valueOf(strings[i]);
                }
                if (theDay[0] == year && theDay[1] == month) {
                    Day day = new Day();
                    day.setSolar(theDay[0], theDay[1], theDay[2]);
                    return day;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void selectTheDay(Day day) {
        if (day == null || day.getSolar() == null) {
            return;
        }
        int positionMonth = -1;
        int position = -1;
        Day selectDay = null;//months内部具体的某天
        for (int i = 0; i < months.size(); i++) {
            if (months.get(i) != null && months.get(i).getDays() != null) {
                List<Day> days = months.get(i).getDays();
                for (int m = 0; m < days.size(); m++) {
                    Day theDay = days.get(m);
                    if (theDay != null && theDay.getSolar() != null) {
                        int[] solar = day.getSolar();
                        int[] theSolar = theDay.getSolar();
                        if (solar[0] == theSolar[0] && solar[1] == theSolar[1] && solar[2] == theSolar[2]) {
                            positionMonth = i;
                            position = m;
                            selectDay = theDay;
                        }
                    }
                }
            }
        }
        if (selectDay != null) {
            LinearLayout linearLayoutRoot = myPagerAdapter.getLinearLayoutRoot(positionMonth);
            if (linearLayoutRoot != null) {
                if (linearLayoutRoot.getTag() != null) {
                    List<View> calendarview_day_items = (List<View>) linearLayoutRoot.getTag();
                    if (calendarview_day_items != null && calendarview_day_items.size() > position) {
                        View view = calendarview_day_items.get(position);
                        TextView textViewSolar = (TextView) view.findViewById(R.id.textViewSolar);
                        LinearLayout linearLayoutBack = (LinearLayout) view.findViewById(R.id.linearLayoutBack);
                        setChooseClick(positionMonth, position, selectDay, textViewSolar, linearLayoutBack);
                    }
                }
            }

        }

    }


    public void setSelectDay(String day) {
        selectDay[0] = 0;
        selectDay[1] = 0;
        selectDay[2] = 0;
        int[] theDay = convertDay(day);
        if (theDay != null && theDay.length >= 3) {
            selectDay[0] = theDay[0];
            selectDay[1] = theDay[1];
            selectDay[2] = theDay[2];
        }
    }

    public void setTipsDays(List<CalendarEntity> tipsDays) {
//        this.tipsDays.clear();
    Integer initialSize=this.tipsDays.size();
        if (tipsDays != null) {
            for (int i = 0; i < tipsDays.size(); i++) {
                CalendarEntity tipsDay = tipsDays.get(i);
                if (tipsDay != null) {
                    int[] theDay = convertDay(tipsDay.getDay());
                    tipsDay.setDays(theDay);
                    if(tipsDaysSet.add(tipsDay))
                    this.tipsDays.add(tipsDay);

                }
            }
        }

        if(myPagerAdapter!=null && tipsDays!=null &&tipsDays.size()!=initialSize)
        myPagerAdapter.notifyDataSetChanged();
        int i = 0;
    }

    /**
     * 把onCalendarViewListener放在该方法前面,可以在initView执行过程中就触发onPagerChanged
     */
    public void initView(int itemLayoutId) {
        if (itemLayoutId == 0) {
            this.itemLayoutId = R.layout.calendarview_day_item;
        } else {
            this.itemLayoutId = itemLayoutId;
        }

        removeAllViews();
        grayCircleDrawable = getResources().getDrawable(R.drawable.calendarview_circle_gray);
        greenCircleDrawable = getResources().getDrawable(R.drawable.calendarview_circle_green);
        yellowCircleDrawable = getResources().getDrawable(R.drawable.calendarview_circle_yellow);
        calendarView_textColorUnChoose = ContextCompat.getColor(context, R.color.calendarView_textColorUnChoose);
        calendarView_textColorChoose = ContextCompat.getColor(context, R.color.calendarView_textColorChoose);
        //TIPS选中时的字体颜色
        calendarView_textColorTips = ContextCompat.getColor(context, R.color.calendarView_textColorUnChoose);
        calendarView_margin = (int) context.getResources().getDimension(R.dimen.calendarView_margin);


        dateStart = convertDay(dateStartString);
        dateEnd = convertDay(dateEndString);
        this.setOrientation(VERTICAL);
        updateMonths();
        layoutInflater = LayoutInflater.from(context);
        View calendarview_week_layout = layoutInflater.inflate(R.layout.calendarview_week_layout, this, false);
        this.addView(calendarview_week_layout);

        viewPager = new ViewPager(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (getItemWidth() * 6.013f));
        layoutParams.topMargin = getTopMargin();
        layoutParams.bottomMargin = getBottomMargin();

        viewPager.setLayoutParams(layoutParams);
        this.addView(viewPager);
        //预加载页面数量
        viewPager.setOffscreenPageLimit(1);
        myPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        Month month = new Month();
        month.setYear(selectDay[0]);
        month.setMonth(selectDay[1]);
        goToMonth(month, true);

    }

    private void updateMonths() {
        months.clear();
        if (dateStart != null && dateEnd != null && (dateStart[0] < dateEnd[0] || (dateStart[0] == dateEnd[0] && dateStart[1] <= dateEnd[1]))) {
            int startYear = dateStart[0];
            int endYear = dateEnd[0];
            int startMonth = dateStart[1];
            int endMonth = dateEnd[1];
            for (int i = startYear; i <= endYear; i++) {
                if (i == startYear) {
                    for (int m = startMonth; m <= 12; m++) {
                        Month month = new Month();
                        month.setYear(i);
                        month.setMonth(m);
                        months.add(month);
                    }
                } else if (i == endYear) {
                    for (int m = 1; m <= endMonth; m++) {
                        Month month = new Month();
                        month.setYear(i);
                        month.setMonth(m);
                        months.add(month);
                    }
                } else {
                    for (int m = 1; m <= 12; m++) {
                        Month month = new Month();
                        month.setYear(i);
                        month.setMonth(m);
                        months.add(month);
                    }
                }
            }
        }

    }

    public boolean isCanCancel() {
        return canCancel;
    }

    public void setCanCancel(boolean canCancel) {
        this.canCancel = canCancel;
    }

    public String getDateStartString() {
        return dateStartString;
    }

    public void setDateStartString(String dateStartString) {
        this.dateStartString = dateStartString;
    }

    public String getDateEndString() {
        return dateEndString;
    }

    public void setDateEndString(String dateEndString) {
        this.dateEndString = dateEndString;
    }

    public List<Month> getMonths() {
        return months;
    }

    public void goToMonth(Month month, boolean isInit) {
        if (month != null) {
            for (int i = 0; i < months.size(); i++) {
                Month theMonth = months.get(i);
                if (theMonth != null && theMonth.getYear() == month.getYear() && theMonth.getMonth() == month.getMonth()) {
                    if (isInit && i == 0) {
                        if (months.get(0) != null) {
                            months.get(0).setSelect(true);
                        }
                        if (onCalendarViewListener != null) {
                            onCalendarViewListener.onPagerChanged(months, 0);
                        }
                    } else {
                        viewPager.setCurrentItem(i);
                    }
                    break;
                }
            }
        }
    }

    //行间距
    private int getHorizontalMargin() {
        return (int) (App.getScreenWidth() * 0.045f);
    }

    private int getTopMargin() {
        return (int) (App.getScreenWidth() * 0.001f);
    }

    private int getBottomMargin() {
        return (int) (App.getScreenWidth() * 0.012f);
    }


    private int getItemWidth() {
        return (int) (App.getScreenWidth() * 0.13f);
    }


    private int getItemVerticalMargin(int lines) {
        int res = 0;
        if (lines < 6) {
            res = getItemWidth() / 6;
        }
        return res;
    }


    public class MyPagerAdapter extends PagerAdapter {
        private SparseArray<LinearLayout> views = new SparseArray<>();

        @Override
        public int getCount() {
            return months.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            views.remove(position);

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            LinearLayout linearLayout = getMonthView(context, position);
            views.put(position, linearLayout);
            container.addView(linearLayout);
            return linearLayout;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
        public LinearLayout getLinearLayoutRoot(int position) {
            return views.get(position);
        }

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if (months.get(position) != null) {
                months.get(position).setSelect(true);
            }
            if (onCalendarViewListener != null) {
                onCalendarViewListener.onPagerChanged(months, position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private LinearLayout getMonthView(final Context context, final int positionMonth) {
        List<View> calendarview_day_items = new ArrayList<View>();

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        Month month = months.get(positionMonth);
        if (month != null) {
            List<Day> days = month.getDays();
            if (days == null ||true) {
                days = getDays(month.getYear(), month.getMonth(), selectDay, tipsDays);
            }
            int lines = 5;
            if (days != null && days.size() != 0) {
                lines = days.size() / 7;
            }
            for (int i = 0; i < lines; i++) {
                LinearLayout linearLayoutChild = new LinearLayout(context);
                LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getItemWidth());
                layoutParams.topMargin = getItemVerticalMargin(lines);
                linearLayoutChild.setLayoutParams(layoutParams);
                linearLayoutChild.setOrientation(LinearLayout.HORIZONTAL);
                for (int m = 0; m < 7; m++) {
                    View calendarview_day_item;
                    final LinearLayout linearLayoutBack;
                    final TextView textViewSolar;
                    // final TextView textViewLunar;
                    if (itemLayoutId != -1) {
                        calendarview_day_item = layoutInflater.inflate(R.layout.calendarview_day_item, null);
                        LayoutParams theLayoutParams = new LayoutParams(getItemWidth(), getItemWidth());
                        if (m == 0) {
                            theLayoutParams.leftMargin = getHorizontalMargin();
                        }
                        calendarview_day_item.setLayoutParams(theLayoutParams);
                        linearLayoutBack = (LinearLayout) calendarview_day_item.findViewById(R.id.linearLayoutBack);
                        textViewSolar = (TextView) calendarview_day_item.findViewById(R.id.textViewSolar);
                        // textViewLunar= (TextView) calendarview_day_item.findViewById(R.id.textViewLunar);
                    } else {
                        LinearLayout linearLayout1 = new LinearLayout(context);
                        LayoutParams layoutParams1 = new LayoutParams(getItemWidth(), getItemWidth());
                        if (m == 0) {
                            layoutParams1.leftMargin = getHorizontalMargin();
                        }
                        linearLayout1.setLayoutParams(layoutParams1);
                        linearLayout1.setOrientation(VERTICAL);

                        linearLayoutBack = new LinearLayout(context);
                        linearLayoutBack.setId(R.id.linearLayoutBack);
                        LayoutParams layoutParams2 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        layoutParams2.leftMargin = calendarView_margin;
                        layoutParams2.rightMargin = calendarView_margin;
                        layoutParams2.topMargin = calendarView_margin;
                        layoutParams2.bottomMargin = calendarView_margin;
                        linearLayoutBack.setLayoutParams(layoutParams2);
                        linearLayoutBack.setGravity(Gravity.CENTER);
                        linearLayoutBack.setOrientation(VERTICAL);

                        textViewSolar = new TextView(context);
                        textViewSolar.setId(R.id.textViewSolar);
                        LayoutParams layoutParams3 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        textViewSolar.setTextColor(calendarView_textColorUnChoose);
                        textViewSolar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                        textViewSolar.setLayoutParams(layoutParams3);
                        linearLayoutBack.addView(textViewSolar);
                        linearLayout1.addView(linearLayoutBack);
                        calendarview_day_item = linearLayout1;
                    }
                    final int position = i * 7 + m;
                    if (days != null && days.size() > position && days.get(position) != null) {
                        final Day day = days.get(position);
                        if (day != null) {
                            if (day.getType() == 0 || day.getType() == 2) {
                                textViewSolar.setTextColor(0xffb4b4b4);
                            }
                            textViewSolar.setText(String.valueOf(day.getSolar()[2]));
                            /** if (!TextUtils.isEmpty(day.getSolarHoliday())) {//阳历节日
                             textViewLunar.setText(day.getSolarHoliday());
                             } else if (!TextUtils.isEmpty(day.getLunarHoliday())) {//农历节日
                             textViewLunar.setText(day.getLunarHoliday());
                             } else if (!TextUtils.isEmpty(day.getTerm())) {//节气
                             textViewLunar.setText(day.getTerm());
                             } else {
                             //  textViewLunar.setText(day.getLunar()[1]);//农历日期
                             }**/
                            //设置特殊日
                            if (day.getTipsType() == 1) {
                                //报名成功
//                                textViewSolar.setText("付");
                                greenCircleDrawable.setBounds(0, 0, greenCircleDrawable.getIntrinsicWidth(), (int) (greenCircleDrawable.getMinimumHeight()));
                                textViewSolar.setCompoundDrawables(null, null, null, greenCircleDrawable);
                            }
                            if (day.getTipsType() == 2) {
                                //满员
//                                textViewSolar.setText("付");
                                yellowCircleDrawable.setBounds(0, 0, yellowCircleDrawable.getIntrinsicWidth(), (int) (yellowCircleDrawable.getMinimumHeight()));
                                textViewSolar.setCompoundDrawables(null, null, null, yellowCircleDrawable);
                            }
                            if (day.getTipsType() == 3) {
                                //满员
//                                textViewSolar.setText("付");
                                grayCircleDrawable.setBounds(0, 0, grayCircleDrawable.getIntrinsicWidth(), (int) (grayCircleDrawable.getMinimumHeight()));
                                textViewSolar.setCompoundDrawables(null, null, null, grayCircleDrawable);
                            }
                            linearLayoutBack.setBackgroundResource(0);
                            if (day.getType() == 0) {
                                linearLayoutBack.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (positionMonth - 1 >= 0) {
                                            goToMonth(months.get(positionMonth - 1), false);
                                        }
                                    }
                                });

                            } else if (day.getType() == 2) {
                                linearLayoutBack.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (positionMonth + 1 < months.size()) {
                                            goToMonth(months.get(positionMonth + 1), false);
                                        }

                                    }
                                });
                            } else {
                                if (day.isChoose()) {
                                    textViewSolar.setTextColor(calendarView_textColorChoose);
                                    //选中时背景色
                                    linearLayoutBack.setBackgroundResource(R.drawable.calendarview_circle_yellow_background);
                                    textViewSolar.setCompoundDrawables(null, null, null, null);
                                    if (onCalendarViewListener != null) {
                                        onCalendarViewListener.selectDay(linearLayoutBack, day);
                                    }
                                    selectMonthPosition = positionMonth;
                                    selectDayPosition = position;
                                }
                                linearLayoutBack.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        setChooseClick(positionMonth, position, day, textViewSolar, linearLayoutBack);
                                    }
                                });
                            }
                        }
                    }
                    calendarview_day_items.add(calendarview_day_item);
                    linearLayoutChild.addView(calendarview_day_item);
                }
                linearLayout.addView(linearLayoutChild);
            }
            month.setDays(days);
        }
        linearLayout.setTag(calendarview_day_items);
        return linearLayout;
    }

    public void setChooseClick(int positionMonth, int position, Day day, TextView textViewSolar, LinearLayout linearLayoutBack) {
        if (selectMonthPosition >= 0 && selectDayPosition >= 0 && months.get(selectMonthPosition) != null && months.get(selectMonthPosition).getDays() != null && months.get(selectMonthPosition).getDays().get(selectDayPosition) != null) {
            Day oldDay = months.get(selectMonthPosition).getDays().get(selectDayPosition);
            if (positionMonth == selectMonthPosition && position == selectDayPosition) {
                if (day.isChoose()) {
                    if (canCancel) {
                        if (day.getTipsType() == 1) {
                            greenCircleDrawable.setBounds(0, 0, greenCircleDrawable.getIntrinsicWidth(), (int) (greenCircleDrawable.getMinimumHeight()));
                            textViewSolar.setCompoundDrawables(null, null, null, greenCircleDrawable);
                            textViewSolar.setTextColor(calendarView_textColorTips);
                        } else if (day.getTipsType() == 2) {
                            yellowCircleDrawable.setBounds(0, 0, yellowCircleDrawable.getIntrinsicWidth(), (int) (yellowCircleDrawable.getMinimumHeight()));
                            textViewSolar.setCompoundDrawables(null, null, null, yellowCircleDrawable);
                            textViewSolar.setTextColor(calendarView_textColorTips);
                        } else if (day.getTipsType() == 3) {
                            grayCircleDrawable.setBounds(0, 0, grayCircleDrawable.getIntrinsicWidth(), (int) (grayCircleDrawable.getMinimumHeight()));
                            textViewSolar.setCompoundDrawables(null, null, null, grayCircleDrawable);
                            textViewSolar.setTextColor(calendarView_textColorTips);
                        } else {
                            textViewSolar.setCompoundDrawables(null, null, null, null);
                            textViewSolar.setTextColor(calendarView_textColorUnChoose);
                        }
                        linearLayoutBack.setBackgroundResource(0);
                        day.setChoose(false);
                        selectDay[0] = 0;
                        selectDay[1] = 0;
                        selectDay[2] = 0;
                        if (onCalendarViewListener != null) {
                            onCalendarViewListener.noThingSelect(linearLayoutBack);
                        }
                    }
                } else {
                    textViewSolar.setTextColor(calendarView_textColorChoose);
                    //选中时的背景色
                    linearLayoutBack.setBackgroundResource(R.drawable.calendarview_circle_yellow_background);
                    textViewSolar.setCompoundDrawables(null, null, null, null);
                    day.setChoose(true);
                    selectDay[0] = day.getSolar()[0];
                    selectDay[1] = day.getSolar()[1];
                    selectDay[2] = day.getSolar()[2];
                    if (onCalendarViewListener != null) {
                        onCalendarViewListener.selectDay(linearLayoutBack, day);
                    }
                }
            } else {
                LinearLayout oldLinearLayoutRoot = myPagerAdapter.getLinearLayoutRoot(selectMonthPosition);
                if (oldLinearLayoutRoot != null) {
                    if (oldLinearLayoutRoot.getTag() != null) {
                        List<View> calendarview_day_items = (List<View>) oldLinearLayoutRoot.getTag();
                        if (calendarview_day_items != null && calendarview_day_items.size() > selectDayPosition) {
                            View view = calendarview_day_items.get(selectDayPosition);
                            LinearLayout theLinearLayoutBack = (LinearLayout) view.findViewById(R.id.linearLayoutBack);
                            TextView theTextViewSolar = (TextView) view.findViewById(R.id.textViewSolar);
                            //之前选中的view
                            if (oldDay.getTipsType() == 1) {
                                greenCircleDrawable.setBounds(0, 0, greenCircleDrawable.getIntrinsicWidth(), (int) (greenCircleDrawable.getMinimumHeight()));
                                theTextViewSolar.setCompoundDrawables(null, null, null, greenCircleDrawable);
                                theTextViewSolar.setTextColor(calendarView_textColorTips);
                            } else if (oldDay.getTipsType() == 2) {
                                yellowCircleDrawable.setBounds(0, 0, yellowCircleDrawable.getIntrinsicWidth(), (int) (yellowCircleDrawable.getMinimumHeight()));
                                theTextViewSolar.setCompoundDrawables(null, null, null, yellowCircleDrawable);
                                theTextViewSolar.setTextColor(calendarView_textColorTips);
                            } else if (oldDay.getTipsType() == 3) {
                                grayCircleDrawable.setBounds(0, 0, grayCircleDrawable.getIntrinsicWidth(), (int) (grayCircleDrawable.getMinimumHeight()));
                                theTextViewSolar.setCompoundDrawables(null, null, null, grayCircleDrawable);
                                theTextViewSolar.setTextColor(calendarView_textColorTips);
                            } else {
                                theTextViewSolar.setCompoundDrawables(null, null, null, null);
                                theTextViewSolar.setTextColor(calendarView_textColorUnChoose);
                            }
                            theLinearLayoutBack.setBackgroundResource(0);
                        }
                    }
                }
                oldDay.setChoose(false);
                textViewSolar.setTextColor(calendarView_textColorChoose);
//                if (day.getTipsType() == 1)
                //选中的变化
                linearLayoutBack.setBackgroundResource(R.drawable.calendarview_circle_yellow_background);
                yellowCircleDrawable.setBounds(0, 0, yellowCircleDrawable.getIntrinsicWidth(), (int) (yellowCircleDrawable.getMinimumHeight()));
                textViewSolar.setCompoundDrawables(null, null, null, yellowCircleDrawable);
                day.setChoose(true);
                selectDay[0] = day.getSolar()[0];
                selectDay[1] = day.getSolar()[1];
                selectDay[2] = day.getSolar()[2];
                if (onCalendarViewListener != null) {
                    onCalendarViewListener.selectDay(linearLayoutBack, day);
                }
                selectMonthPosition = positionMonth;
                selectDayPosition = position;
            }
        }
    }

    public void setOnCalendarViewListener(OnCalendarViewListener onCalendarViewListener) {
        this.onCalendarViewListener = onCalendarViewListener;
    }

    public interface OnCalendarViewListener {

        void onPagerChanged(List<Month> months, int position);

        void selectDay(LinearLayout linearLayout, Day day);

        void noThingSelect(LinearLayout linearLayout);

    }


}
