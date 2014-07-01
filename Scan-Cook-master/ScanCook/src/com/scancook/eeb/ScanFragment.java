package com.scancook.eeb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;

public class ScanFragment extends Fragment {
	Context mContext;
	private Button mScanButton;
	private TextView mWeightAdjustment;
	private TextView mRecipeDisplay;
	private Button mPlusButton;
	private Button mMinusButton;
	private Button mNextButton;
	private int mCurrentWeight=100;
	private int mStep=10;
	private ImageView mThumbs1;
	private ImageView mThumbs2;
	private ImageView mThumbs3;
	private boolean mThumbs1on=false;
	private ProductInfoReciver mReciver;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.scanned_result, container, false);
		mContext = getActivity();
		mReciver = new ProductInfoReciver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("RecieverScannedInfo.DATA");
		mContext.registerReceiver(mReciver, filter);
	    mScanButton = (Button) rootView.findViewById(R.id.bt_scan); 
		mScanButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				(new IntentIntegrator(getActivity())).
				initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);
			}
		});
		
		mRecipeDisplay = (TextView) rootView.findViewById(R.id.tv_product);
		
		//take buttons from layout
		mWeightAdjustment =(TextView) rootView.findViewById(R.id.tv_weight);
		mWeightAdjustment.setText(mCurrentWeight+"g");
		
		mPlusButton = (Button) rootView.findViewById(R.id.bt_plus);
		mMinusButton = (Button) rootView.findViewById(R.id.bt_minus);
		mThumbs1 = (ImageView) rootView.findViewById(R.id.iv_thumbs1);
		mThumbs2 = (ImageView) rootView.findViewById(R.id.iv_thumbs2);
		mThumbs3 = (ImageView) rootView.findViewById(R.id.iv_thumbs3);
		
		mNextButton = (Button) rootView.findViewById(R.id.bt_next);
		
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
					mThumbs1on=true;
			}
				
		});
		mThumbs3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					mThumbs2.setImageResource(R.drawable.im_thumbs_up);
					mThumbs1.setImageResource(R.drawable.im_thumbs_up);
					mThumbs3.setImageResource(R.drawable.im_thumbs_up);
					mThumbs1on=true;
			} 			
				
				
			
		});
		
		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("GoToOverview");
                getActivity().sendBroadcast(intent);
			}
		});	
		
		return rootView;
	}
	
	@Override
	public void onDestroy() {
		mContext.unregisterReceiver(mReciver);
		super.onDestroy();
	}
	
	private class ProductInfoReciver extends BroadcastReceiver {
		@Override
	    public void onReceive(Context context, Intent intent) {
		   if (intent.getAction().equals("RecieverScannedInfo.DATA")) {
				String scannedProduct = intent.getStringExtra("RESULT");
				mRecipeDisplay.setText(scannedProduct);
		   }
	    }
	}	
	
}
