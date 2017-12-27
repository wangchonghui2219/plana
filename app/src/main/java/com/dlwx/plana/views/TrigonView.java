package com.dlwx.plana.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/9/19/019.
 */

public class TrigonView extends View {


    public TrigonView(Context context) {
        super(context);
    }


    public TrigonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setColor(0xffFD5473);
        //实例化路径
        Path path = new Path();
        path.moveTo(0, 0);// 此点为多边形的起点
        path.lineTo(60, 0);
        path.lineTo(60, 160);
        path.lineTo(-20, 200);
        path.lineTo(60, 80);
        path.close(); // 使这些点构成封闭的多边形
        p.setAntiAlias(true);
        canvas.drawPath(path, p);

    }
}
