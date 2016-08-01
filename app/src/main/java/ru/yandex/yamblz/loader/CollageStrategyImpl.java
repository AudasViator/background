package ru.yandex.yamblz.loader;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.List;

public class CollageStrategyImpl implements CollageStrategy {
    private static final int COLLAGE_SIZE = 1500;

    @Override
    public Bitmap create(List<Bitmap> bitmaps) {
        if (bitmaps == null || bitmaps.size() == 0) {
            return null;
        }

        int pictureSize = (int) (COLLAGE_SIZE / Math.floor(Math.sqrt(bitmaps.size())));
        int realCollageSize = (int) (pictureSize * Math.ceil(Math.sqrt(bitmaps.size())));

        Bitmap collage = Bitmap.createBitmap(realCollageSize, realCollageSize, Bitmap.Config.ARGB_4444);

        Canvas canvas = new Canvas(collage);
        int top = 0, left = 0;
        for (Bitmap currentBitmap : bitmaps) {
            if (left >= realCollageSize) {
                left = 0;
                top += pictureSize;
            }

            int currentBitmapSize;
            if (currentBitmap.getHeight() < currentBitmap.getWidth()) {
                currentBitmapSize = currentBitmap.getHeight();
            } else {
                currentBitmapSize = currentBitmap.getWidth();
            }

            canvas.drawBitmap(currentBitmap, new Rect(0, 0, currentBitmapSize, currentBitmapSize),
                    new Rect(left, top, left + pictureSize, top + pictureSize), null);
            left += pictureSize;
        }

        canvas.save();
        return collage;
    }
}
