package com.anthonycaliendo.instagramphotoviewer.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

/**
 * Adapted from:
 *      http://stackoverflow.com/questions/26112150/android-create-circular-image-with-picasso/26112408#26112408
 */
class CircleTransformation implements Transformation {

    @Override
    public Bitmap transform(final Bitmap sourceImage) {
        int size = Math.min(sourceImage.getWidth(), sourceImage.getHeight());

        int x = (sourceImage.getWidth() - size) / 2;
        int y = (sourceImage.getHeight() - size) / 2;

        final Bitmap squaredBitmap = Bitmap.createBitmap(sourceImage, x, y, size, size);
        if (squaredBitmap != sourceImage) {
            sourceImage.recycle();
        }

        final Bitmap circledBitmap = Bitmap.createBitmap(size, size, sourceImage.getConfig());
        final Canvas canvas        = new Canvas(circledBitmap);
        final Paint paint          = new Paint();
        final BitmapShader shader  = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

        paint.setShader(shader);
        paint.setAntiAlias(true);

        float radius = size / 2f;
        canvas.drawCircle(radius, radius, radius, paint);

        squaredBitmap.recycle();
        return circledBitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}