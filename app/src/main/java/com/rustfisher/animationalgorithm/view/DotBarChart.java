package com.rustfisher.animationalgorithm.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;

/**
 * 柱形图或点图
 * Created by Rust on 2018/8/11.
 */
public class DotBarChart extends View {
    private static final String TAG = "rustAppDotBar";

    public enum TYPE {
        DOT(1), BAR(2);
        int code;

        TYPE(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static TYPE getTypeByCode(int code) {
            switch (code) {
                case 1:
                    return TYPE.DOT;
                case 2:
                    return TYPE.BAR;
            }
            return TYPE.BAR;
        }
    }

    private TYPE mShowType = TYPE.BAR;
    private int mViewHeight;
    private int mViewWidth;
    int mDotColor = Color.WHITE;
    float mDotSizeMaxPx = 20;   // 圆点最大尺寸
    float mDotSizeMinPx = 4;    // 圆点最小尺寸
    float mBarHalfWidMinPx = 3; // 柱形宽度一半的最小值

    private float mShowDataMax = 17;
    private float mShowDataMin = 0;

    private Paint mDataPaint;

    private SparseIntArray mMarkIndexColorMap = new SparseIntArray();
    private float[] mDataInDraw = new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public DotBarChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotBarChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initChart();
    }

    private void initChart() {
        mDataPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDataPaint.setStyle(Paint.Style.FILL);
        mDataPaint.setStrokeWidth(4);
    }

    public float[] getDataInDraw() {
        return mDataInDraw;
    }

    public float getDataMax() {
        return mShowDataMax;
    }

    public void setShowDataMax(float max) {
        this.mShowDataMax = max;
    }

    public void setShowDataMin(float min) {
        this.mShowDataMin = min;
    }

    public void setShowType(TYPE type) {
        this.mShowType = type;
        invalidate();
    }

    public TYPE getShowType() {
        return mShowType;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h - getPaddingTop() - getPaddingBottom();
        mViewWidth = w - getPaddingStart() - getPaddingEnd();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawData(canvas);
    }

    public void setData(int[] inputData) {
        mDataInDraw = new float[inputData.length];
        for (int i = 0; i < inputData.length; i++) {
            mDataInDraw[i] = inputData[i];
        }
        invalidate();
    }

    public void setData(float[] inputData) {
        mDataInDraw = inputData.clone();
        invalidate();
    }

    public void setData(int[] data, SparseIntArray indexColorMap) {
        mDataInDraw = new float[data.length];
        for (int i = 0; i < data.length; i++) {
            mDataInDraw[i] = data[i];
        }
        if (null != indexColorMap) {
            mMarkIndexColorMap = indexColorMap.clone();
        } else {
            mMarkIndexColorMap.clear();
        }
        invalidate();
    }

    private void drawData(Canvas canvas) {
        int dataCount = mDataInDraw.length;
        float xStep = mViewWidth / (dataCount * 1.0f + 1); // 每个数据占的x宽度
        mDataPaint.setStyle(Paint.Style.FILL);
        float perDataDy = (float) (mViewHeight - 15) / (mShowDataMax - mShowDataMin);
        switch (mShowType) {
            case DOT:
                float dotSize = mDotSizeMinPx;
                if (xStep > mDotSizeMinPx) {
                    dotSize = xStep - 3;
                    dotSize = Math.min(dotSize, mDotSizeMaxPx);
                }
                perDataDy = (mViewHeight - dotSize) / (mShowDataMax - mShowDataMin);
                mDataPaint.setStrokeWidth(dotSize);

                for (int i = 0; i < mDataInDraw.length; i++) {
                    mDataPaint.setColor(mMarkIndexColorMap.get(i, mDotColor));
                    canvas.drawCircle(xStep * (i + 1), calDataYCoordinate(mDataInDraw[i], perDataDy) - dotSize, dotSize / 2, mDataPaint);
                }
                break;
            case BAR:
                float barHalfWidPx = mBarHalfWidMinPx;
                if (xStep > 2 * mBarHalfWidMinPx) {
                    barHalfWidPx = xStep / 2 - 2;
                }

                for (int i = 0; i < mDataInDraw.length; i++) {
                    mDataPaint.setColor(mMarkIndexColorMap.get(i, mDotColor));
                    float barTopMiddle = xStep * (i + 1);
                    canvas.drawRect(barTopMiddle - barHalfWidPx, calDataYCoordinate(mDataInDraw[i], perDataDy), barTopMiddle + barHalfWidPx, mViewHeight, mDataPaint);
                }
                break;
        }
    }

    private float calDataYCoordinate(float dataValue, float perDataDy) {
        return mViewHeight - dataValue * perDataDy;
    }

}
