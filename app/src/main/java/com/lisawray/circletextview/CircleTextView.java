package com.lisawray.circletextview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.DimenRes;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by lisawray on 5/29/15.
 */
public class CircleTextView extends View {

    private int maxLines;
    private TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private CharSequence charSequence = "";
    private int[] leftIndents;
    private int[] rightIndents;
    private StaticLayout layout;

    public CircleTextView(Context context) {
        super(context);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public int getRightIndent(int line, int width, int height) {
        return getIndent(line, width, height);
    }

    public int getLeftIndent(int line, int width, int height) {
        return getIndent(line, width, height);
    }

    public int getIndent(int line, int width, int height) {
        int r = (int) (getSmallestDimension(width, height) / 2f);
        float y = (r - line * getLineHeight());
        return (int) (width / 2f - Math.sqrt((r * r) - y * y));
    }

    public void setTextSize(@DimenRes int dimenResId) {
        textPaint.setTextSize(getPixels(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(dimenResId)));
    }

    int getPixels(int unit, float size) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return (int) TypedValue.applyDimension(unit, size, metrics);
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        maxLines = (int) Math.floor(getSmallestDimension(w, h) / getLineHeight());
        leftIndents = new int[maxLines];
        rightIndents = new int[maxLines];
        for (int line = 0; line < maxLines; line++) {
            leftIndents[line] = getLeftIndent(line, w, h);
            rightIndents[line] = getRightIndent(line, w, h);
        }
        makeNewLayout();
    }

    public void setText(CharSequence charSequence) {
        this.charSequence = charSequence;
        makeNewLayout();
    }

    private void makeNewLayout() {
        StaticLayout.Builder builder = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), textPaint, getMeasuredWidth());
        builder.setIndents(leftIndents, rightIndents);
        builder.setMaxLines(maxLines);
        layout = builder.build();
    }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        layout.draw(canvas);
    }

    private int getSmallestDimension(int width, int height) {
        return Math.min(width, height);
    }

    public float getLineHeight() {
        return textPaint.getFontMetrics().bottom - textPaint.getFontMetrics().top;
    }

    public TextPaint getTextPaint() {
        return textPaint;
    }
}
