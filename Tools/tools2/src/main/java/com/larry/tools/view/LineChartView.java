package com.larry.tools.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class LineChartView extends View {

    // private String[] StrDate = { "7-11", "7-12", "7-13", "7-14", "7-15",
    // "7-16",
    // "7-17" };// x轴的坐标显示
    // private String[] YValue = { "", "10", "20", "30", "40", "50" };// y轴的显示刻度
    // private int[] YDataValue = { 15, 23, 10, 36, 45, 40, 12 };// Y轴的点的值
    // private String Title = "图标的标题";// 图标的标题
    private String[] StrDate;// x轴的坐标显示
    private String[] YValue;// y轴的显示刻度
    private int[] YDataValue;// Y轴的点的值m
    private String Title;// 图标的标题

    private int width;// 屏幕的宽度
    private int height;
    private float maxValue;// 传入数据的最大值
    private int dataNum;// 数据总数

    private int mChartHeight;// 折线图的高
    private int mChartWidth;// 折线图的
    private int startX = dip2px(getContext(), 10);// 开始绘制的x坐标
    private int startY = dip2px(getContext(), 5);// 开始绘制的y坐标
    private int chartMarginBottom = dip2px(getContext(), 30);// 折线图距离父控件底部距离
    private int chartMarginHorizontal = dip2px(getContext(), 12);// 折线图距离父控件左右的距离
    private int valueAlignLeft = dip2px(getContext(), 0);// value参数文本距离左边距离
    private int dateAlignLeft = dip2px(getContext(), 0);// date参数文本距离左边距离
    private int valueAlignBottom = dip2px(getContext(), 5);// value参数文本距离底部距离
    private int dateAlignBottom = dip2px(getContext(), 10);// date参数文本距离底部距离
    private int xAddedNum = dip2px(getContext(), 60);// 绘制折线图时每次移动的x轴距离
    private int yAddedNum;// 绘制折线图时每次移动的y轴距离
    private boolean isDrawFirst;// 是否是第一次绘制
    private float circleFilledRadius = dip2px(getContext(), 5);// 圆半径

    private float firstX;// 第一个点的x轴坐标
    private float firstY;// 第一个点的y轴坐标

    private Path path;
    private int YScale = 40;
    private float xinit;
    private float maxXinit;
    private float minXinit;

    private Paint mPaintBg;// 报表背景画笔
    private Paint mPaintCoveredBg;// 用于画数据覆盖部分
    private Paint mPaintChartLine;// 用于画报表的网格
    private Paint mPaintDataLine;// 用于画数据连线
    private Paint mPaintTextDate;// 用于画日期文本
    private Paint mPaintFilledCircle;// 用于画实心圆
    private Paint mPaintTextValue;// 用于画数据访问量值

    public LineChartView(Context context) {
        super(context);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 当总数据已经传入，即不为空时，根据总数据中数据个数设定view的总宽
        if (YDataValue.length != 0) {
            width = (YDataValue.length - 1) * xAddedNum + chartMarginHorizontal * 2;
            getMaxValue(YDataValue);
        }
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    /**
     * 用于得到总数据中最大数据
     *
     * @param yDataValue2 总数据
     */
    private void getMaxValue(int[] yDataValue2) {
        maxValue = 0;
        dataNum = 0;
        for (int key : yDataValue2) {
            if (key > maxValue) {
                maxValue = key;
            }
            dataNum++;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaintBg = new Paint();
        mPaintCoveredBg = new Paint();
        // mPaintCircle = new Paint();
        mPaintChartLine = new Paint();
        mPaintDataLine = new Paint();
        mPaintTextDate = new Paint();
        mPaintTextValue = new Paint();
        mPaintFilledCircle = new Paint();

        mPaintBg.setColor(Color.GREEN);
        mPaintBg.setStyle(Paint.Style.FILL);
        mPaintCoveredBg.setColor(Color.WHITE);
        mPaintCoveredBg.setStyle(Paint.Style.FILL);

        mPaintFilledCircle.setColor(Color.BLACK);
        mPaintFilledCircle.setStyle(Paint.Style.FILL);
        mPaintFilledCircle.setAntiAlias(true);

        mPaintChartLine.setColor(Color.BLUE);
        // mPaintChartLine.setStyle(Paint.Style.STROKE);
        mPaintChartLine.setAntiAlias(true);

        mPaintDataLine.setColor(Color.YELLOW);
        mPaintDataLine.setStyle(Paint.Style.STROKE);
        mPaintDataLine.setStrokeWidth(dip2px(getContext(), 5));
        mPaintDataLine.setAntiAlias(true);

        mPaintTextDate.setColor(Color.GREEN);
        mPaintTextDate.setTextSize(dip2px(getContext(), 10));
        mPaintTextDate.setTextAlign(Paint.Align.CENTER);
        mPaintTextDate.setAntiAlias(true);

        mPaintTextValue.setColor(Color.RED);
        mPaintTextValue.setTextSize(dip2px(getContext(), 12));
        mPaintTextValue.setTextAlign(Paint.Align.CENTER);
        mPaintTextValue.setAntiAlias(true);
        // 重绘
        // invalidate();

        path = new Path();

        isDrawFirst = true;
        mChartHeight = height - chartMarginBottom;
        yAddedNum = mChartHeight / 4;
        mChartWidth = width - chartMarginHorizontal * 2;
        // 画边框
        // canvas.drawRect(startX, startY, startX + mChartWidth,
        // startY + mChartHeight, paint1);
        for (int key : YDataValue) {
            float value = key;
            if (isDrawFirst) {
                // 当第一次绘制时得到第一个点的横纵坐标
                firstX = startX;
                firstY = startY + (1f - value / ((int) maxValue * 1.5f)) * mChartHeight;
                path.moveTo(firstX, firstY);
                isDrawFirst = false;
            }
            // 每循环一次，将path线性相位一次
            path.lineTo(startX, startY + (1f - value / ((int) maxValue * 1.5f))
                    * mChartHeight);
            startX += xAddedNum;
        }
        // 重新给startX赋值
        startX = dip2px(getContext(), 10);
        // 画出折线
        canvas.drawPath(path, mPaintDataLine);
        // 画出折线以下部分的颜色
        path.lineTo(startX + mChartWidth, startY + mChartHeight);
        path.lineTo(startX, startY + mChartHeight);
        path.lineTo(firstX, firstY);
        canvas.drawPath(path, mPaintCoveredBg);

        // 画出每个点的圆圈，和对应的文本
        for (int key = 0; key < StrDate.length; key++) {
            String date = StrDate[key];
            float value = YDataValue[key];
            // 画值圆点
            canvas.drawCircle(startX, startY + (1f - value / ((int) maxValue * 1.5f))
                    * mChartHeight, circleFilledRadius, mPaintFilledCircle);
            // 画X轴的值文本
            canvas.drawText(date + "", startX + dateAlignLeft, height
                    - dateAlignBottom, mPaintTextDate);
            // 画X轴的圆点
            canvas.drawCircle(startX, startY + 4 * yAddedNum, circleFilledRadius,
                    mPaintFilledCircle);
            // 画值文字
            canvas.drawText(value + "", startX + valueAlignLeft, startY
                    + (1f - value / ((int) maxValue * 1.5f)) * mChartHeight
                    - valueAlignBottom, mPaintTextValue);

            startX += xAddedNum;
        }
        // 在次使startX回到初始值
        startX = dip2px(getContext(), 10);
        /**
         * 画出网格
         */
        // 竖线
        canvas.drawLine(startX, startY, startX, startY + mChartHeight,
                mPaintChartLine);

        // 横线
        canvas.drawLine(startX, startY + 4 * yAddedNum, startX + mChartWidth,
                startY + 4 * yAddedNum, mPaintChartLine);
        path.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 如果不用滑动就可以展示所有数据，就不让滑动
        if (YScale * YValue.length <= width) {
            return false;
        }

        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:
                float dis = event.getX() - startX;
                if (xinit + dis > maxXinit) {
                    xinit = maxXinit;
                } else if (xinit + dis < minXinit) {
                    xinit = minXinit;
                } else {
                    xinit = (int) (xinit + dis);
                }
                invalidate();
                break;
        }
        return true;
    }

    public void setDataInfo(String[] strings, String[] strings2, int[] is,
                            String string) {
        StrDate = strings;
        YValue = strings2;
        YDataValue = is;
        Title = string;

    }

    public static int dip2px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
