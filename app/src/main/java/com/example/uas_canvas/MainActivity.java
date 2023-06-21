package com.example.uas_canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImgView;
    Bitmap mBitmap;
    Canvas mCanvas;
    private int mColorBackground;
    Paint mCirclePaint = new Paint();
    Paint mHeadPaint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgView = findViewById(R.id.my_img_view);
        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
        mCirclePaint.setColor(getResources().getColor(R.color.black));
        mHeadPaint.setColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int vWidth = mImgView.getWidth();
        int vHeight = mImgView.getHeight();

        mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        mImgView.setImageBitmap(mBitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mColorBackground);

        drawHead();
        drawRightEye();
        drawLeftEye();
        drawEyeConnector();

    }

    private void drawHead() {
        int centerX = mCanvas.getWidth() / 2;
        int centerY = mCanvas.getHeight() / 2;
        int radiusX = Math.min(centerX, centerY) / 2;
        int radiusY = Math.min(centerX, centerY) / 3;

        RectF rectF = new RectF(centerX - radiusX, centerY - radiusY, centerX + radiusX, centerY + radiusY);
        mCanvas.drawOval(rectF, mHeadPaint);
    }

    private void drawRightEye() {
        int centerX = mCanvas.getWidth() / 2;
        int centerY = mCanvas.getHeight() / 2;
        int radius = Math.min(centerX, centerY) / 12;

        int eyeX = (int) (centerX + (radius * 3));
        int eyeY = centerY;

        mCanvas.drawCircle(eyeX, eyeY, radius, mCirclePaint);
    }
    private void drawLeftEye() {
        int centerX = mCanvas.getWidth() / 2;
        int centerY = mCanvas.getHeight() / 2;
        int radius = Math.min(centerX, centerY) / 12;

        int eyeX = (int) (centerX - (radius * 3));
        int eyeY = centerY;

        mCanvas.drawCircle(eyeX, eyeY, radius, mCirclePaint);
    }

    private void drawEyeConnector() {
        int centerX = mCanvas.getWidth() / 2;
        int centerY = mCanvas.getHeight() / 2;
        int radius = Math.min(centerX, centerY) / 12;

        int rightEyeX = (int) (centerX + (radius * 3));
        int rightEyeY = centerY;

        int leftEyeX = (int) (centerX - (radius * 3));
        int leftEyeY = centerY;

        mCanvas.drawCircle(rightEyeX, rightEyeY, radius, mCirclePaint);
        mCanvas.drawCircle(leftEyeX, leftEyeY, radius, mCirclePaint);

        int lineStartX = leftEyeX + radius;
        int lineEndX = rightEyeX - radius;
        int lineY = centerY;

        mCirclePaint.setStrokeWidth(15);
        mCanvas.drawLine(lineStartX, lineY, lineEndX, lineY, mCirclePaint);
    }

    private void startAnimation() {

        Animation flipAnimation = AnimationUtils.loadAnimation(this, R.anim.flip_animation);
        mImgView.startAnimation(flipAnimation);
    }

}