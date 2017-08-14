package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Camera camera;
    Matrix matrix;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        camera = new Camera();
        matrix = new Matrix();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x1 = point1.x + bitmap.getWidth() / 2;
        float y1 = point1.y + bitmap.getHeight() / 2;
        float x2 = point2.x + bitmap.getWidth() / 2;
        float y2 = point2.y + bitmap.getHeight() / 2;


        //Canvas 的几何变换顺序是反的，所以要把移动到中心的代码写在下面，把从中心移动回来的代码写在上面。
        //方法一：matrix的顺序也是下往上读，所以在camera.getMatrix()之前应当设置好matrix的post和pre方法。

        camera.save();
        matrix.reset();
        camera.rotateX(30);
        camera.getMatrix(matrix);
        matrix.postTranslate(x1, y1);
        matrix.preTranslate(-x1, -y1);
        camera.restore();
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();


        //方法二：
        canvas.save();
        camera.save();
        camera.rotateY(30);
        canvas.translate(x2, y2);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-x2, -y2);
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
