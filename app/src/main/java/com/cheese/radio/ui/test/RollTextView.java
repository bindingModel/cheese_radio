package com.cheese.radio.ui.test;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.binding.model.App;

/**
 * Rabies
 *
 * @author USER
 * Date:   2019-01-23
 * Time:   11:23
 */
public class RollTextView extends View {
    private String oldText = "";
    private String newText = "";
    private int offset = 0;
    float height = 0;
    float width = 0;
    private Paint oldTextPaint, newTextPaint;
    private int direction = 1;

    public RollTextView(Context context) {
        this(context, null);
    }

    public RollTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        oldTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        oldTextPaint.setColor(Color.BLACK);
        oldTextPaint.setTypeface(Typeface.DEFAULT);
        oldTextPaint.setTextSize(App.dipTopx(12));
        oldTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        oldTextPaint.setAntiAlias(true);
        oldText = TextUtils.isEmpty(oldText) ? "" : oldText;
        newTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        newTextPaint.setColor(Color.BLACK);
        newTextPaint.setTypeface(Typeface.DEFAULT);
        newTextPaint.setTextSize(App.dipTopx(12));
        newTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        newTextPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            // Parent has told us how big to be. So be it.
            height = heightSize;
        } else {
            Paint.FontMetrics fontMetrics = newTextPaint.getFontMetrics();
            height = fontMetrics.bottom - fontMetrics.top;
        }
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = newTextPaint.measureText(newText);
        }
        setMeasuredDimension((int) width, (int) height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = 0;
        int size = Math.max(oldText.length(), newText.length());
        Paint.FontMetricsInt fontMetrics = newTextPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom - fontMetrics.top) / 2;

        for (int i = 0; i < size; i++) {
            String oldValue = i < oldText.length() ? String.valueOf(oldText.charAt(i)) : "";
            String newValue = i < newText.length() ? String.valueOf(newText.charAt(i)) : "";
            if (TextUtils.equals(oldValue, newValue)) {
                oldTextPaint.setAlpha(255);
                drawText(canvas, x, oldValue, baseline, oldTextPaint);
                x += Math.max(oldTextPaint.measureText(newValue), oldTextPaint.measureText(oldValue));
                continue;
            }
            if (!TextUtils.isEmpty(oldValue)) {
                oldTextPaint.setAlpha((int) (255 * offset / height));
                drawText(canvas, x, oldValue, (int) (offset - height) * direction + baseline, oldTextPaint);
            }
            if (!TextUtils.isEmpty(newValue)) {
                newTextPaint.setAlpha((int) (255 * (1 - offset / height)));
                drawText(canvas, x, newValue, direction * offset + baseline, newTextPaint);
            }
            x += Math.max(oldTextPaint.measureText(newValue), oldTextPaint.measureText(oldValue));
        }


     /*   String oldValue = String.valueOf(oldText.charAt(0));
        String newValue = String.valueOf(newText.charAt(0));
        oldTextPaint.setAlpha((int) (255 * offset / height));
        newTextPaint.setAlpha((int) (255 * (1 - offset / height)));
        drawText(canvas, x, oldValue, (int) (offset - height), oldTextPaint);
        drawText(canvas, x, newValue, offset, newTextPaint);*/
      /*  for (int i = 0; i < size; i++) {


        }*/

    }

    private void drawText(Canvas canvas, int x, String newValue, int offset, Paint paint) {
        canvas.drawText(newValue, x, offset, paint);
    }

    public void startAnimation() {
        ValueAnimator animator = ObjectAnimator.ofFloat(height, 0);
        animator.setDuration(333);
        animator.addUpdateListener(it -> {
            float value = (float) it.getAnimatedValue();
            offset = (int) value;
            postInvalidate();
        });
        animator.start();
    }

    public void setNewText(String newText) {
        setNewText(newText, 1);
    }

    /**
     * @param newText
     * @param direction 1 or -1
     */
    public void setNewText(String newText, int direction) {
        this.oldText = this.newText;
        this.newText = newText;
        this.direction = direction;
        if (newTextPaint.measureText(newText) > getMeasuredWidth()) requestLayout();
        startAnimation();
    }
}
