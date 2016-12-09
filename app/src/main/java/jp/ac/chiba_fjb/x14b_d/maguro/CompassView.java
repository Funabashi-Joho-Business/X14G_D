package jp.ac.chiba_fjb.x14b_d.maguro;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by oikawa on 2016/12/09.
 */

public class CompassView extends AppCompatImageView {


	private List<Point> mPoints = new ArrayList<Point>();

	public CompassView(Context context) {
		super(context);
	}

	public CompassView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Test();
	}

	public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		Test();
	}
	void Test(){
		addPoint(10,30);
		addPoint(20,50);
		addPoint(-120,80);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		int size = Math.min(canvas.getWidth(),canvas.getHeight());
		int px = (canvas.getWidth()-size)/2;
		int py = (canvas.getHeight()-size)/2;

		int cx = px + size / 2;
		int cy = py + size / 2;

		Paint paint = new Paint();
		paint.setColor(Color.argb(200, 255, 0, 0));

		for(Point p : mPoints){
			canvas.drawCircle(cx+p.x,cy+p.y,16, paint);
		}


//		Rect rect = new Rect(px,py,px+size,+py+size);
//		canvas.drawRect(rect, paint);
	}
	void addPoint(int x,int y){
		mPoints.add(new Point(x,y));
	}
	void clearPoint(){
		mPoints.clear();
	}
}
