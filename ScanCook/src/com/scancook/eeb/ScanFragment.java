package com.scancook.eeb;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;







//from external scanner:
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanFragment extends Fragment {
private Button mScanButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.scanner_view, container, false);
	    mScanButton = (Button) rootView.findViewById(R.id.bt_scan); 
		mScanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),ScannedResultActivity.class);
				getActivity().startActivity(intent);
				//(new IntentIntegrator(getActivity())).
				//initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);
			}
		});
		return rootView;
	}
	
}
