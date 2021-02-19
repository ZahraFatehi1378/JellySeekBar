package com.example.seekbar;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class JellySeekBar extends View {

    private final Paint paint = new Paint();
    private final Paint paint2 = new Paint();
    private final Paint txtPaint = new Paint();
    private float chosenX;
    private int startRange = 0, endRange = 100;
    private float goUp, goDown;
    private boolean up = true;
    private String circleColor = "#adcbe3", mainColor = "#4b86b4", signColor = "#011f4b";
    private final float margin = 50;
    private int borderGoUp;
    private int borderGoDown;
    private final ArrayList<Bubble> bubbles;
    private final Random random = new Random();
    private int signFirstPos;
    private int flag = 0;
    private SeekBarLocation seekBarLocation;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public JellySeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setColor(Color.parseColor(mainColor));

        //  paint = new Paint(Paint.ANTI_ALIAS_FLAG)
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(10);

        bubbles = new ArrayList<>();

        txtPaint.setTextSize(40);
        txtPaint.setTypeface(getResources().getFont(R.font.dolce_vita));
        txtPaint.setTextAlign(Paint.Align.CENTER);

        System.out.println(signFirstPos);

    }


    @SuppressLint({"ResourceAsColor", "DrawAllocation"})
    @Override
    protected void onDraw(Canvas canvas) {

        if (flag == 0) {
            chosenX = (margin + 53) + ((float) (signFirstPos - startRange) / (endRange - startRange)) * (getWidth() - 2 * (margin + 53));
            flag = 1;
        }
        paint2.setColor(Color.parseColor(mainColor));
        paint.setColor(Color.parseColor(mainColor));
        txtPaint.setColor(Color.parseColor(signColor));

        canvas.drawRoundRect(new RectF(margin - 8, (getHeight() >> 1) - 20, getWidth() - margin + 8, (getHeight() >> 1) + 20), 100, 100, paint);


        updateBubbles();
        drawBubble(canvas);

        if (chosenX <= 200) {
            if (!up) {//make it bigger
                makeCircleBigger(canvas, "left");

            } else {//make it smaller
                makeCircleSmaller(canvas, "left");
            }
        } else if (chosenX >= getWidth() - 200) {
            if (!up) {//make it bigger
                makeCircleBigger(canvas, "right");

            } else {//make it smaller
                makeCircleSmaller(canvas, "right");
            }
        } else {
            if (!up) {//make it bigger
                makeCircleBigger(canvas, "");

            } else {//make it smaller
                makeCircleSmaller(canvas, "");
            }

        }
    }

    private void drawCircleBorder(Canvas canvas, float borderChange, String s) {

        final Path path = new Path();

        float startX = chosenX - 100;
        float startY = (float) (getHeight() / 2);

        borderChange = setInterpolator(borderChange);
        if (s.equals("left")) {
            path.moveTo(margin, startY - (chosenX - margin - 50) * 20 / 100);
            path.cubicTo(startX + 50, startY - (chosenX - margin - 50) * 20 / 100,
                    startX + 20, (float) (startY - (chosenX - margin - 50) * 20 / 100 + 20 - borderChange * 1.5 - 60),
                    startX + 100, (float) (startY - borderChange * 1.5 - 60));
            path.lineTo(startX + 100, startY - 20);
            canvas.drawPath(path, paint);
            if (chosenX == (margin + 53)) {
                final RectF arcBounds = new RectF(margin - 8, (float) (startY - borderChange * 1.5 - 60),
                        chosenX + 70, (float) (startY + borderChange * 1.5 + 60));
                // Draw the arc
                canvas.drawArc(arcBounds, 180F, 180F, true, paint);
            }
        } else {
            path.moveTo(startX - 50, startY - 20);
            path.cubicTo(startX + 50, startY - 20,
                    startX + 20, (float) (startY - borderChange * 1.5 - 60),
                    startX + 100, (float) (startY - borderChange * 1.5 - 60));
            path.lineTo(startX + 100, startY - 20);
            canvas.drawPath(path, paint);
        }

        if (s.equals("right")) {
            path.moveTo(startX + 100, (float) (startY - borderChange * 1.5 - 60));
            path.cubicTo(startX + 180, (float) ((startY - (getWidth() - chosenX - 100) * 20 / 100) + 20 - borderChange * 1.5 - 60),
                    startX + 150, startY - (getWidth() - chosenX - 100) * 20 / 100,
                    getWidth() - margin, startY - (getWidth() - chosenX - 100) * 20 / 100);
            path.lineTo(startX + 100, startY - 20);
            canvas.drawPath(path, paint);
            if (chosenX == getWidth() - margin - 53) {
                final RectF arcBounds = new RectF(chosenX - 70, (float) (startY - borderChange * 1.5 - 60),
                        getWidth() - margin + 8, (float) (startY + borderChange * 1.5 + 60));
                // Draw the arc
                canvas.drawArc(arcBounds, 180F, 180f, true, paint);
            }
        } else {
            path.moveTo(startX + 100, (float) (startY - borderChange * 1.5 - 60));
            path.cubicTo(startX + 180, (float) (startY - borderChange * 1.5 - 60),
                    startX + 150, startY - 20,
                    startX + 200 + 50, startY - 20);
            path.lineTo(startX + 100, startY - 20);
            canvas.drawPath(path, paint);
        }

    }


    private void drawBubble(Canvas canvas) {
        for (Bubble bubble : bubbles) {
            paint2.setAlpha(bubble.getAlpha());
            canvas.drawCircle(bubble.getX(), (getHeight() >> 1) - bubble.getY(), bubble.getR(), paint2);
        }
    }

    private void updateBubbles() {
        Iterator<Bubble> it = bubbles.iterator();
        while (it.hasNext()) {
            Bubble bubble = it.next();
            long bubbleStayDuration = 400;
            bubble.setR(15 - (int) ((float) (System.currentTimeMillis() - bubble.getCreatedTime()) / bubbleStayDuration * 15));
            bubble.setAlpha(255 - (int) ((float) (System.currentTimeMillis() - bubble.getCreatedTime()) / bubbleStayDuration * 255));
            if (bubble.getAlpha() < 1) {
                it.remove();
            }
            invalidate();
        }

    }

    private void addBubbles(int x, int y, int r) {
        Bubble bubble = new Bubble(x, y, r, 255, System.currentTimeMillis());
        bubbles.add(bubble);
    }


    private void makeCircleSmaller(Canvas canvas, String s) {

        goDown = setInterpolator(goDown);
        drawCircleBorder(canvas, borderGoDown, s);
        paint.setColor(Color.parseColor(circleColor));
        canvas.drawCircle(chosenX, (getHeight() >> 1) - 15 - goDown, 30 + goDown / 3, paint);

        //draw txt
        txtPaint.setTextSize(40 + goDown / 3);
        canvas.drawText(setText(chosenX), chosenX, (getHeight() >> 1) - goDown, txtPaint);

        if (goDown < 5)
            goDown = 0;

    }

    private void makeCircleBigger(Canvas canvas, String s) {
        goUp = setInterpolator(goUp);
        drawCircleBorder(canvas, borderGoUp, s);
        paint.setColor(Color.parseColor(circleColor));
        canvas.drawCircle(chosenX, (getHeight() >> 1) - 15 - goUp, 30 + goUp / 3, paint);

        //draw txt
        txtPaint.setTextSize(30 + goUp / 2);
        canvas.drawText(setText(chosenX), chosenX, (getHeight() >> 1) - goUp, txtPaint);
    }


    private float setInterpolator(float x) {
        x = (float) ((float) ((float) Math.pow(2, (-10 * x / 100)) * Math.sin(2 * 3.1415926535
                * (x / 100 - (0.3 / 4)))) + 0.5) * 100;
        return x;
    }


    @SuppressLint("DefaultLocale")
    private String setText(Float chosenX) {

        int x = (int) (startRange + (endRange - startRange) * (chosenX - (margin + 53)) /
                (getWidth() - 2 * (margin + 53)));
        if (x > endRange)
            x = endRange;
        if (x < startRange)
            x = startRange;

        seekBarLocation.setX(x);

        return String.valueOf(x);

    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startAnimation();
            chosenX = event.getX();
            if (chosenX < margin + 53)
                chosenX = margin + 53;
            else if (chosenX > getWidth() - margin - 53)
                chosenX = getWidth() - margin - 53;
            up = false;
            return true;
        } else if ((event.getAction() == MotionEvent.ACTION_MOVE)) {
            chosenX = event.getX();
            if (chosenX < margin + 53)
                chosenX = margin + 53;
            else if (chosenX > getWidth() - margin - 53)
                chosenX = getWidth() - margin - 53;
            up = false;
            this.invalidate();
            addBubbles((int) chosenX, random.nextInt(100), random.nextInt(10));
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            startAnimation();
            up = true;
            this.invalidate();
            return false;
        } else {
            return false;
        }
    }

    public void setRange(int startRange, int endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }


    public void setColor(String circleColor, String mainColor, String fontColor) {
        this.mainColor = mainColor;
        this.circleColor = circleColor;
        this.signColor = fontColor;
        invalidate();
    }


    public void startAnimation() {

        //for circle
        ValueAnimator animator = ValueAnimator.ofFloat(0, 60);
        animator.setDuration(500);

        animator.addUpdateListener(valueAnimator -> {
            goUp = ((Float) valueAnimator.getAnimatedValue()).intValue();
            goDown = 60 - goUp;
            invalidate();
        });
        animator.start();

        ValueAnimator animator2 = ValueAnimator.ofFloat(0, 60);
        animator2.setDuration(500);

        animator2.addUpdateListener(valueAnimator -> {
            borderGoUp = ((Float) valueAnimator.getAnimatedValue()).intValue();
            borderGoDown = 60 - borderGoUp;
            invalidate();
        });
        animator2.start();
    }


    public void setSignFirstPos(int x) {
        signFirstPos = x;
    }

    public int getPos() {
        return (int) (startRange + (endRange - startRange) * (chosenX - (margin + 53)) /
                (getWidth() - 2 * (margin + 53)));
    }

    public void setFontForNum(Typeface typeface) {
        txtPaint.setTypeface(typeface);
        invalidate();
    }

    public void getSeekBarLocation(SeekBarLocation seekBarLocation) {
        this.seekBarLocation = seekBarLocation;
    }
}
