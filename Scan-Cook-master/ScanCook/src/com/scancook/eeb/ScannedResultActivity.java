package com.scancook.eeb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ScannedResultActivity extends Activity {
	private TextView mWeightAdjustment;
	private TextView mRecipeDisplay;
	private Button mPlusButton;
	private Button mMinusButton;
	private int mCurrentWeight=100;
	private int mStep=10;
	private ImageView mThumbs1;
	private ImageView mThumbs2;
	private ImageView mThumbs3;
	private boolean mThumbs1on=false;
	private boolean mThumbs2on=false;
	private boolean mThumbs3on=false;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scanned_result);
		
		String scannedProduct = getIntent().getStringExtra("RESULT");
		mRecipeDisplay = (TextView) findViewById(R.id.textView1);
		mRecipeDisplay.setText(scannedProduct);
		
		//take buttons from layout
		mWeightAdjustment =(TextView) findViewById(R.id.tv_weight);
		mWeightAdjustment.setText(mCurrentWeight+"g");
		
		mPlusButton = (Button) findViewById(R.id.bt_plus);
		mMinusButton = (Button) findViewById(R.id.bt_minus);
		mThumbs1 = (ImageView) findViewById(R.id.iv_thumbs1);
		mThumbs2 = (ImageView) findViewById(R.id.iv_thumbs2);
		mThumbs3 = (ImageView) findViewById(R.id.iv_thumbs3);
		
		
		mPlusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCurrentWeight = mCurrentWeight +mStep;
				mWeightAdjustment.setText(mCurrentWeight+"g");
				
			}
		});
		mMinusButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCurrentWeight = mCurrentWeight -mStep;
				mWeightAdjustment.setText(mCurrentWeight+"g");
				
			}
		});
		mThumbs1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!mThumbs1on){
					mThumbs1.setImageResource(R.drawable.im_thumbs_up);
					mThumbs1on=true;
				} else{
					mThumbs1.setImageResource(R.drawable.im_thumbs_up_grey);
					mThumbs2.setImageResource(R.drawable.im_thumbs_up_grey);
					mThumbs3.setImageResource(R.drawable.im_thumbs_up_grey);
					mThumbs1on=false;
					
				}
				
			}
		});
		mThumbs2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					mThumbs2.setImageResource(R.drawable.im_thumbs_up);
					mThumbs1.setImageResource(R.drawable.im_thumbs_up);
					mThumbs3.setImageResource(R.drawable.im_thumbs_up_grey);
					mThumbs2on=true;
					mThumbs1on=true;
					mThumbs3on=false;
			}
				
		});
		mThumbs3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					mThumbs2.setImageResource(R.drawable.im_thumbs_up);
					mThumbs1.setImageResource(R.drawable.im_thumbs_up);
					mThumbs3.setImageResource(R.drawable.im_thumbs_up);
					mThumbs3on=true;
					mThumbs2on=true;
					mThumbs1on=true;
			} 			
				
				
			
		});
		
	}
	
	
}
