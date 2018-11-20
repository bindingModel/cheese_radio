package com.cheese.radio.base.view.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.binding.model.App;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;

public class CheeseMonthView extends MonthView {
    private Paint currentMonthTextPaint;
    private Paint notCurrentMonthTexrPaint;
    private Paint circlePaint;
    private int textBlack = 0xFF333333;
    private int textWhite = 0xFFFFFFFF;
    private int textGray = 0xFF999999;
    private int yellow = 0xFFFCCA44;
    private int green = 0xFF6DD100;
    private int gray = 0xFFBFBFBF;

    public CheeseMonthView(Context context) {
        super(context);
        initColor();
        initPaint();
    }

    /**
     * 绘制选中的日子
     *
     * @param canvas    canvas
     * @param calendar  日历日历calendar
     * @param x         日历Card x起点坐标
     * @param y         日历Card y起点坐标
     * @param hasScheme hasScheme 非标记的日期
     * @return 返回true 则绘制onDrawScheme，因为这里背景色不是互斥的，所以返回true
     */
    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        float width = x + mItemWidth / 2;
        float height = y + mItemHeight / 2 - mesureTextHeght() / 2 + currentMonthTextPaint.getFontMetrics().bottom;
        int position = 0;
        if (hasScheme) {
            for (int i = 0; i < calendar.getSchemes().size(); i++) {
                position = Math.max(position, calendar.getSchemes().get(i).getType());
            }
            circlePaint.setColor(getCircleColor(position));
        } else {
            circlePaint.setColor(yellow);
        }
        canvas.drawCircle(width, height, (float) (currentMonthTextPaint.measureText("30") * 1.5), circlePaint);

        return false;
    }

    /**
     * 绘制标记的事件日子
     *
     * @param canvas   canvas
     * @param calendar 日历calendar
     * @param x        日历Card x起点坐标
     * @param y        日历Card y起点坐标
     */
    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
//        canvas.drawText(String.valueOf(calendar.getDay()), x, y, textPaint);
        float width = x + mItemWidth / 2;
        float height = y + mItemHeight / 2 + mesureTextHeght()/2;
        int position = 0;
        for (int i = 0; i < calendar.getSchemes().size(); i++) {
            position = Math.max(position, calendar.getSchemes().get(i).getType());
        }
        circlePaint.setColor(getCircleColor(position));
        canvas.drawCircle(width, height, mesureTextHeght()/4, circlePaint);
    }

    /**
     * 绘制文本
     *
     * @param canvas     canvas
     * @param calendar   日历calendar
     * @param x          日历Card x起点坐标
     * @param y          日历Card y起点坐标
     * @param hasScheme  是否是标记的日期
     * @param isSelected 是否选中
     */
    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        currentMonthTextPaint.setColor(isSelected?textWhite:textBlack);
        canvas.drawText(String.valueOf(calendar.getDay()), x + mItemWidth / 2, y + mItemHeight / 2, calendar.isCurrentMonth() ? currentMonthTextPaint : notCurrentMonthTexrPaint);
    }

    private void initPaint() {
        currentMonthTextPaint = new Paint();
        currentMonthTextPaint.setAntiAlias(true);
        currentMonthTextPaint.setTextAlign(Paint.Align.CENTER);
        currentMonthTextPaint.setColor(textBlack);
        currentMonthTextPaint.setFakeBoldText(true);
        currentMonthTextPaint.setTextSize(App.dipTopx(12));

        notCurrentMonthTexrPaint = new Paint();
        notCurrentMonthTexrPaint.setAntiAlias(true);
        notCurrentMonthTexrPaint.setTextAlign(Paint.Align.CENTER);
        notCurrentMonthTexrPaint.setColor(textGray);
        notCurrentMonthTexrPaint.setFakeBoldText(true);
        notCurrentMonthTexrPaint.setTextSize(App.dipTopx(12));

        circlePaint = new Paint();
    }

    private void initColor() {
//        indigoBlue = ContextCompat.getColor(AppUtil.peekActivity(), R.color.colorPrimary);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private float mesureTextHeght() {
        Paint.FontMetrics fontMetrics = currentMonthTextPaint.getFontMetrics();
//        return fontMetrics.descent - fontMetrics.ascent;
        return fontMetrics.bottom - fontMetrics.top;
    }

    private int getCircleColor(int position) {
        switch (position) {
            case 1:
                return gray;
            case 2:
                return yellow;
            case 3:
                return green;
        }
        return 0xFF000000;
    }

}
