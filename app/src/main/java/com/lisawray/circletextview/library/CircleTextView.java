package com.lisawray.circletextview.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lisawray on 5/29/15.
 */
public class CircleTextView extends TextView {

    int width;
    int height;
    private int maxLines;

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

    public int getRightIndent(int line) {
        return getIndent(line);
    }

    public int getLeftIndent(int line) {
        return getIndent(line);
    }

    public int getIndent(int line) {
        int r = (int) (getSmallestDimension() / 2f);
        int y = (r - line * getLineHeight());
        return (int) (width / 2f - Math.sqrt((r * r) - y * y));
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        maxLines = (int) Math.floor(getSmallestDimension() / getLineHeight());
        int[] leftIndents = new int[maxLines];
        int[] rightIndents = new int[maxLines];
        for (int line = 0; line < maxLines; line++) {
            leftIndents[line] = getLeftIndent(line);
            rightIndents[line] = getRightIndent(line);
        }
        setIndents(leftIndents, rightIndents);
        setMaxLines(maxLines);
    }

    private int getSmallestDimension() {
        return Math.min(width, height);
    }
}
