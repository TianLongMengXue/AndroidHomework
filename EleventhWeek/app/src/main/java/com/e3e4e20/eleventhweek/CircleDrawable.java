package com.e3e4e20.eleventhweek;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/*
 * Filename: com.e3e4e20.eleventhweek
 * Description:
 * Version: 1.0
 * Created: 2019/11/15 15:11 星期五
 * Revision: none
 * Compiler:
 * Author: DreamSnow·Draco
 * Company:
 * */
public class CircleDrawable extends Drawable {

    /**
     * 显示图片
     */
    private Bitmap mBitmap;
    /**
     * BitmapShader
     */
    private BitmapShader mBitmapShader;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 圆心
     */
    private float cx, cy;
    /**
     * 半径
     */
    private float radius;

    private int size;

    public CircleDrawable(Bitmap bitmap) {
        this.mBitmap = bitmap;
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(mBitmapShader);


        size = Math.min(bitmap.getWidth(), bitmap.getHeight());

        cx = size / 2;
        cy = size / 2;
        radius = size / 2;


    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, mPaint);
    }

    @Override
    public int getIntrinsicHeight() {
        return size;
    }

    @Override
    public int getIntrinsicWidth() {
        return size;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
