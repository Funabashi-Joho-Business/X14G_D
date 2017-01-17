package jp.ac.chiba_fjb.x14b_d.maguro;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by oikawa on 2016/12/09.
 */

public class CompassView extends AppCompatImageView {

	private float mBaseLength = 40.0f;
	private List<float[]> mPoints = new ArrayList<float[]>();

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
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		int size = Math.min(canvas.getWidth(),canvas.getHeight());
		int px = (canvas.getWidth()-size)/2;
		int py = (canvas.getHeight()-size)/2;
		int cx = canvas.getWidth()/2;
		int cy = canvas.getHeight()/2;

		Paint paint = new Paint();
		paint.setColor(Color.argb(200, 255, 0, 0));


		for(float[] p : mPoints){
			double r = (p[1]-90.0f)*Math.PI/180.0f;
			int x = (int) (cx + p[0]/mBaseLength*Math.cos(r)*size/4);
			int y = (int) (cy + p[0]/mBaseLength*Math.sin(r)*size/4);
			canvas.drawCircle(x,y,16, paint);
		}

		//中心点
		Paint paint2 = new Paint();
		paint2.setColor(Color.argb(200, 0, 255, 0));
		canvas.drawCircle(cx,cy,16, paint2);

	}
	void addPoint(float[] value){
		mPoints.add(value);
	}
	void clearPoint(){
		mPoints.clear();
	}
}
