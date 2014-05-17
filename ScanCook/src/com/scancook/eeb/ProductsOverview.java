package com.scancook.eeb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


public class ProductsOverview extends Fragment {
	private ListView mListView;
	private ProductListAdapter mAdapter;
	private Button mFindRecipe;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.products_overview, container, false);
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
	
	
}
