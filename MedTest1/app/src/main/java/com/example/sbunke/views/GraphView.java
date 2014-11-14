package com.example.sbunke.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by sbunke on 11/13/2014.
 */
//http://stackoverflow.com/questions/4397192/draw-pie-chart-in-android?lq=1
public class GraphView extends View
{
    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private float[] value_degree;
    private int[] COLORS={Color.RED,Color.GREEN,Color.YELLOW};
    //http://developer.android.com/reference/android/graphics/RectF.html#bottom
    RectF rectf = new RectF (50, 50, 500, 500);
    int temp=0;
    public GraphView(Context context, float[] values) {

        super(context);
        value_degree=new float[values.length];
        for(int i=0;i<values.length;i++)
        {
            value_degree[i]=values[i];
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        for (int i = 0; i < value_degree.length; i++) {//values2.length; i++) {
            if (i == 0) {
                paint.setColor(COLORS[i]);
                canvas.drawArc(rectf, 0, value_degree[i], true, paint);
            }
            else
            {
                temp += (int) value_degree[i - 1];
                paint.setColor(COLORS[i]);
                canvas.drawArc(rectf, temp, value_degree[i], true, paint);
            }
        }
    }

}