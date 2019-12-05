package com.servicel.system.drawables;


import android.animation.*;
import android.animation.Animator.*;
import android.animation.ValueAnimator.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;

import android.animation.Animator.AnimatorListener;
import com.servicel.system.*;


public class CircularProgressBar extends ProgressBar{
	private static final String TAG = "CircularProgressBar";
	private static final int STROKE_WIDTH = 20;
	private int mStrokeWidth = STROKE_WIDTH;
	private final RectF mCircleBounds = new RectF();
	private final Paint mProgressColorPaint = new Paint();
	private final Paint mBackgroundColorPaint = new Paint();
	private boolean mHasShadow = true;
	private int mShadowColor = Color.BLACK;
	private int mTitleSize,mSubtitleSize;
	

	public interface ProgressAnimationListener{
		public void onAnimationStart();
		public void onAnimationFinish();
		public void onAnimationProgress(int progress);
	}

	public CircularProgressBar(Context context) {
		super(context);
		init(null, 0);
	}

	public CircularProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
	}

	public CircularProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}

	public void init(AttributeSet attrs, int style){
		//so that shadow shows up properly for lines and arcs
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);

		TypedArray a = getContext().obtainStyledAttributes(attrs,
														   R.styleable.CircularProgressBar, style, 0);

		String color;
		Resources res = getResources();
		
		this.mTitleSize = a.getInt(R.styleable.CircularProgressBar_cpb_titleSize, 21);
		this.mSubtitleSize = a.getInt(R.styleable.CircularProgressBar_cpb_subTitleSize, 14);
		

		this.mHasShadow = a.getBoolean(R.styleable.CircularProgressBar_cpb_hasShadow, true);

		color = a.getString(R.styleable.CircularProgressBar_cpb_progressColor);
		if(color==null)
			mProgressColorPaint.setColor(res.getColor(R.color.colorAccent));
		else
			mProgressColorPaint.setColor(Color.parseColor(color));

		color = a.getString(R.styleable.CircularProgressBar_cpb_backgroundColor);
		if(color==null)
			mBackgroundColorPaint.setColor(res.getColor(R.color.primary_dark));
		else
			mBackgroundColorPaint.setColor(Color.parseColor(color));

		mStrokeWidth = a.getInt(R.styleable.CircularProgressBar_cpb_strokeWidth, STROKE_WIDTH);

		a.recycle();
		
		mProgressColorPaint.setAntiAlias(true);
		mProgressColorPaint.setStyle(Paint.Style.STROKE);
		mProgressColorPaint.setStrokeWidth(mStrokeWidth);

		mBackgroundColorPaint.setAntiAlias(true);
		mBackgroundColorPaint.setStyle(Paint.Style.STROKE);
		mBackgroundColorPaint.setStrokeWidth(mStrokeWidth);
		
		
		
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		canvas.drawArc(mCircleBounds, 0, 360 , false, mBackgroundColorPaint);
		int prog = getProgress();
		float scale = getMax() > 0 ? (float)prog/getMax() *360: 0;
		canvas.drawArc(mCircleBounds, 270, scale , false, mProgressColorPaint);
		super.onDraw(canvas);
	}

	@Override
	protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
		final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
		final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
		final int min = Math.min(width, height);
		setMeasuredDimension(min+2*STROKE_WIDTH, min+2*STROKE_WIDTH);

		mCircleBounds.set(STROKE_WIDTH, STROKE_WIDTH, min+STROKE_WIDTH, min+STROKE_WIDTH);
	}

	@Override
	public synchronized void setProgress(int progress) {
		super.setProgress(progress);

		// the setProgress super will not change the details of the progress bar
		// anymore so we need to force an update to redraw the progress bar
		invalidate();
	}

	public void animateProgressTo(final int start, final int end, final ProgressAnimationListener listener){
		if(start!=0)
			setProgress(start);

		final ObjectAnimator progressBarAnimator = ObjectAnimator.ofFloat(this, "animateProgress", start, end);
		progressBarAnimator.setDuration(1000);
				progressBarAnimator.setInterpolator(new AnticipateOvershootInterpolator(2f, 1.5f));
//		progressBarAnimator.setInterpolator(new LinearInterpolator());

		progressBarAnimator.addListener(new AnimatorListener() {
				@Override
				public void onAnimationCancel(final Animator animation) {
				}

				@Override
				public void onAnimationEnd(final Animator animation) {
					CircularProgressBar.this.setProgress(end);
					if(listener!=null)
						listener.onAnimationFinish();
				}

				@Override
				public void onAnimationRepeat(final Animator animation) {
				}

				@Override
				public void onAnimationStart(final Animator animation) {
					if(listener!=null)
						listener.onAnimationStart();
				}
			});

		progressBarAnimator.addUpdateListener(new AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(final ValueAnimator animation) {
					int progress = ((Float) animation.getAnimatedValue()).intValue();
					if(progress!=CircularProgressBar.this.getProgress()){
						Log.d(TAG, progress + "");
						CircularProgressBar.this.setProgress(progress);
						if(listener!=null)
							listener.onAnimationProgress(progress);					
					}
				}
			});
		progressBarAnimator.start();
	}


	public synchronized void setHasShadow(boolean flag){
		this.mHasShadow = flag;
		invalidate();
	}

	public synchronized void setShadow(int color){
		this.mShadowColor = color;
		invalidate();
	}

	public boolean getHasShadow(){
		return mHasShadow;
	}
	
	public synchronized void setBackColor(String color){
		this.mBackgroundColorPaint.setColor(Color.parseColor(color));
		invalidate();
	}
	public synchronized void setProgressColor(String color){
		this.mProgressColorPaint.setColor(Color.parseColor(color));
		invalidate();
	}
	public synchronized void fillCircle(boolean fill){
		if(fill==true){
		this.mProgressColorPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		invalidate();
		}
		else{
			this.mProgressColorPaint.setStyle(Paint.Style.STROKE);
		}
	}
}
