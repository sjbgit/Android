package com.example.sbunke.testlist2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


public class TestDrawPieChart1 extends Activity {
    /** Called when the activity is first created. */
    float values[]={300,400,100,500};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_draw_pie_chart1);
        LinearLayout linear=(LinearLayout) findViewById(R.id.llPieChartLayout);
        values=calculateData(values);
        linear.addView(new MyGraphview(this,values));

    }
    private float[] calculateData(float[] data) {
        // TODO Auto-generated method stub
        float total=0;
        for(int i=0;i<data.length;i++)
        {
            total+=data[i];
        }
        for(int i=0;i<data.length;i++)
        {
            data[i]=360*(data[i]/total);
        }
        return data;

    }

    //http://stackoverflow.com/questions/4397192/draw-pie-chart-in-android?lq=1
    public class MyGraphview extends View
    {
        private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        private float[] value_degree;
        private int[] COLORS={Color.BLUE,Color.GREEN,Color.GRAY,Color.CYAN,Color.RED};
        RectF rectf = new RectF (10, 10, 400, 400);
        int temp=0;
        public MyGraphview(Context context, float[] values) {

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
}
