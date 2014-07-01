package com.scancook.eeb;

import java.util.ArrayList;

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
import android.widget.ListView;


public class ProductsOverview extends Fragment {
	public static ArrayList<String> ProductList =  new ArrayList<String>();
	private ListView mListView;
	private ProductListAdapter mAdapter;
	private Button mFindRecipe;
	private ProductInfoReciver mReciver;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.products_overview, container, false);
		
		mReciver = new ProductInfoReciver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("RecieverScannedInfo.DATA");
		getActivity().registerReceiver(mReciver, filter);
		
	    mListView =(ListView) rootView.findViewById(R.id.listView1);
	    
		mAdapter = new ProductListAdapter(getActivity());
		mListView.setAdapter(mAdapter);
		
		mFindRecipe =(Button) rootView.findViewById(R.id.bt_find_recipe);
		mFindRecipe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),RecipeFoundActivity.class);
				getActivity().startActivity(intent);
				
			}
		});
		
		return rootView;
			
	}
	
	@Override
	public void onDestroy() {
		getActivity().unregisterReceiver(mReciver);
		super.onDestroy();
	}
	
	private class ProductInfoReciver extends BroadcastReceiver {
		@Override
	    public void onReceive(Context context, Intent intent) {
		   if (intent.getAction().equals("RecieverScannedInfo.DATA")) {
			   String scannedProduct = intent.getStringExtra("RESULT");
			   ProductsOverview.ProductList.add(scannedProduct);
			   mAdapter.notifyDataSetChanged();
		   }
	    }
	}
	
	
}
