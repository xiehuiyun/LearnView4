package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice10MatrixSkewView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Matrix matrix;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice10MatrixSkewView(Context context) {
        super(context);
    }

    public Practice10MatrixSkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10MatrixSkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        matrix = new Matrix();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        matrix.postSkew(0, 0.5f);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point1.x, point1.y - bitmap.getHeight() / 2, paint);
        canvas.restore();

        float[] src = {point2.x, point2.y,     point2.x + bitmap.getWidth(), point2.y,
                point2.x, point2.y + bitmap.getHeight(), point2.x + bitmap.getWidth(), point2.y + bitmap.getHeight()};

        float[] dest = {point2.x-10, point2.y+50,       point2.x + bitmap.getWidth() + 120, point2.y-50,
                point2.x+30, point2.y + bitmap.getHeight()+60,        point2.x + bitmap.getWidth() -70, point2.y + bitmap.getHeight()-46};

        canvas.save();
        matrix.reset();
        matrix.setPolyToPoly(src,0,dest,0,4);
//        matrix.postSkew(-0.5f, 0, point2.x + bitmap.getWidth() / 2, point2.y + bitmap.getHeight() / 2);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
