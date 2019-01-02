package com.example.admin.mydemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MaveView extends View {

    private Path mAbovePath, mBelowWavePath;
    private Paint mAboveWavePaint, mBelowWavePaint;

    private DrawFilter mDrawFilter;

    private float φ;
    private double ω;
    private float y, y2;

    private OnWaveAnimationListener mWaveAnimationListener;

    public MaveView(Context context) {
        super(context);
        //初始化
        init();
    }

    public MaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化
        init();
    }

    public MaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化
        init();
    }


    protected void init() {

        //初始化路径
        mAbovePath = new Path();
        mBelowWavePath = new Path();
        //初始化画笔
        //上方波浪
        mAboveWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAboveWavePaint.setAntiAlias(true);
        mAboveWavePaint.setStyle(Paint.Style.FILL);
        mAboveWavePaint.setColor(Color.BLUE);
        //下方波浪
        mBelowWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBelowWavePaint.setAntiAlias(true);
        mBelowWavePaint.setStyle(Paint.Style.FILL);
        mBelowWavePaint.setColor(Color.BLUE);
        mBelowWavePaint.setAlpha(60);
        //画布抗锯齿
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.setDrawFilter(mDrawFilter);

        mAbovePath.reset();
        mBelowWavePath.reset();

        φ -= 0.1f;
        ω = 2 * Math.PI / getWidth();
        mAbovePath.moveTo(getLeft(), getBottom() - 15);
//        mBelowWavePath.moveTo(getLeft(), getBottom() + 15);
        for (float x = 0; x <= getWidth(); x++) {
            /**
             *  y=Asin(ωx+φ)+k
             *  A—振幅越大，波形在y轴上最大与最小值的差值越大
             *  ω—角速度， 控制正弦周期(单位角度内震动的次数)
             *  φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
             *  k—偏距，反映在坐标系上则为图像的上移或下移。
             */
            y = (float) (30 * Math.cos(ω * x + φ) + 30);
//            y2 = (float) (30 * Math.sin(ω * x + φ) + 30);
            mAbovePath.lineTo(x, y);
//            mBelowWavePath.lineTo(x, y2);
            if (x == getWidth() / 2)
                mWaveAnimationListener.OnWaveAnimation(y);
        }
        //回调 把y坐标的值传出去(在activity里面接收让小机器人随波浪一起摇摆)
        mAbovePath.lineTo(getRight(), getBottom() - 15);
//        mBelowWavePath.lineTo(getRight(), getBottom() + 15);
        canvas.drawPath(mAbovePath, mAboveWavePaint);
//        canvas.drawPath(mBelowWavePath, mBelowWavePaint);
        postInvalidateDelayed(20);
    }

    public void setOnWaveAnimationListener(final OnWaveAnimationListener onWaveAnimationListener) {
        this.mWaveAnimationListener = onWaveAnimationListener;
    }

    public interface OnWaveAnimationListener {
        void OnWaveAnimation(float y);
    }
}