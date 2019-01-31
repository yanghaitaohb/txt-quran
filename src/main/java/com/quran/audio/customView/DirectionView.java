package com.quran.audio.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: haitao
 * create at: 2019/1/16
 */
public class DirectionView extends View {
    /**
     * 圆环使用
     */
    private Paint mRingPaint;
    private Paint mCententPaint; //绘制中心实线的画布
    /**
     * 圆环半径 根据view的宽度计算
     */
    private int mRadius = 200;
    /**
     * 圆环的中心点 -- 画圆环和旋转画布时需要使用
     */
    private int x, y;
    /**
     * 圆环大小 矩形
     */
    private RectF mRectf;
    private Context mContext;
    /**
     * 圆环 宽度
     */
    private final int mHeartPaintWidth = 50;
    /**
     * 圆环动画开始时 画弧的偏移量
     */
    private int mAnimAngle = -1;

    public DirectionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        init();
    }

    public DirectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
        init();
    }

    public DirectionView(Context context) {
        this(context, null);
        this.mContext = context;
        init();
    }

    private void init() {
        mRingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRingPaint.setStrokeWidth(mHeartPaintWidth);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        mCententPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCententPaint.setColor(Color.BLACK);
        mCententPaint.setStrokeWidth(3);
    }

    /**
     * canvas抗锯齿开启需要
     */
    private DrawFilter mDrawFilter;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        x = w / 2;
        y = h / 2;
        mRadius = w / 2 - mHeartPaintWidth * 3; //因为制定了Paint的宽度，因此计算半径需要减去这个
        mRectf = new RectF(x - mRadius, y - mRadius, x + mRadius, y + mRadius);
    }

    public float rotate = 0;
    public float meccaRotate = 0;
    public boolean isMecca = false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(mDrawFilter);//在canvas上抗锯齿
        //先绘制竖线
        mCententPaint.setColor(Color.RED);
        mCententPaint.setStrokeWidth(10);
        canvas.drawLine(x, mRectf.top + 25, x, mRectf.top - 70, mCententPaint);
        mCententPaint.setColor(Color.BLACK);
        mCententPaint.setStrokeWidth(3);
        //绘制中心线
        canvas.drawLine(x, y - 80, x, y + 80, mCententPaint);
        canvas.drawLine(x - 80, y, x + 80, y, mCententPaint);
        canvas.rotate(rotate, x, y);// 旋转的时候一定要指明中心
        for (int i = 0; i < 360; i += 3) {
            canvas.drawArc(mRectf, i, 1, false, mRingPaint);
        }
        mCententPaint.setTextSize(50);
        if (isMecca) {
            mCententPaint.setColor(Color.GREEN);
            canvas.rotate(meccaRotate, x, y);// 旋转的时候一定要指明中心
            canvas.rotate(-rotate-meccaRotate, x, mRectf.top + 75);// 旋转的时候一定要指明中心
            canvas.drawText("M", x - 25, mRectf.top + 25 + 75, mCententPaint);
            canvas.rotate(rotate+meccaRotate, x, mRectf.top + 75);// 旋转的时候一定要指明中心
            canvas.rotate(-meccaRotate, x, y);// 旋转的时候一定要指明中心
        }
        mCententPaint.setColor(Color.RED);
        canvas.rotate(-rotate, x, mRectf.top + 75);// 旋转的时候一定要指明中心
        canvas.drawText("N", x - 25, mRectf.top + 25 + 75, mCententPaint);
        canvas.rotate(rotate, x, mRectf.top + 75);// 旋转的时候一定要指明中心

        mCententPaint.setColor(Color.BLACK);
        canvas.rotate(90, x, y);// 旋转的时候一定要指明中心
        canvas.rotate(-90 - rotate, x, mRectf.top + 75);
        canvas.drawText("E", x - 25, mRectf.top + 25 + 75, mCententPaint);
        canvas.rotate(90 + rotate, x, mRectf.top + 75);

        canvas.rotate(90, x, y);
        canvas.rotate(-180 - rotate, x, mRectf.top + 75);
        canvas.drawText("S", x - 25, mRectf.top + 25 + 75, mCententPaint);
        canvas.rotate(180 + rotate, x, mRectf.top + 75);

        canvas.rotate(90, x, y);
        canvas.rotate(-270 - rotate, x, mRectf.top + 75);
        canvas.drawText("W", x - 25, mRectf.top + 25 + 75, mCententPaint);
        canvas.rotate(270 + rotate, x, mRectf.top + 75);

        canvas.rotate(90, x, y);
        mCententPaint.setTextSize(30);
        for (int i = 0; i < 360; i += 3) {
            if (i == 0 || i == 30 || i == 60 || i == 90 || i == 120 || i == 150 || i == 180 || i == 210 || i == 240 || i == 270 || i == 300 || i == 330) {
                canvas.drawText("" + i, x - 15, mRectf.top - mHeartPaintWidth, mCententPaint);
                canvas.rotate(30, x, y);// 旋转的时候一定要指明中心 } }
            }
        }
    }
}