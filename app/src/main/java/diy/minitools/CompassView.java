package diy.minitools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class CompassView extends View {

    private final Paint paint = new Paint();
    private float direction = 0;

    public CompassView(Context context) {
        super(context);
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int width, int height) {
        setMeasuredDimension(MeasureSpec.getSize(width), MeasureSpec.getSize(height));
    }

    @Override
    synchronized protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // computes a circle
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int radius;
        if (width <= height)
            radius = width / 2;
        else
            radius = height / 2;
        int centerX = width / 2;
        int centerY = height / 2;

        // prepares to draw
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#CC0000"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.rotate(-direction, centerX, centerY);

        // draws circle
        canvas.drawCircle(centerX, centerY, radius, paint);

        // draws line
        canvas.drawLine(centerX, centerY, centerX, centerY - radius, paint);

        // draws north text
        String north = "N";
        paint.setColor(Color.parseColor("#CCCCCC"));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(64);
        float x = paint.measureText(north);
        canvas.drawText(north, centerX - x / 2, centerY - radius + 64, paint);
    }

    public void updateDirection(float dir) {
        direction = dir;
        invalidate();
    }

}
