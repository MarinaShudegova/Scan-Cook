package com.scancook.eeb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class CookbookFragment extends Fragment {
	private ListView mListView;
	private CookbookListAdapter mAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.cookbook, container, false);
		 mListView =(ListView) rootView.findViewById(R.id.lv_cookbook);
		    
			mAdapter = new CookbookListAdapter(getActivity());
			mListView.setAdapter(mAdapter);
			
			mListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent intent = new Intent(getActivity(),
							RecipeFoundActivity.class);
					getActivity().startActivity(intent);
					
				}
			});
	     return rootView;
	}

}
