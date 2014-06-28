package com.scancook.eeb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CookbookListAdapter extends BaseAdapter {

	private Context mContext;
	//fake array
	private String [] FakeString = {"recipe1","recipe2","recipe3"};
	
	
	public CookbookListAdapter(Context context) {
		mContext=context;
	}

	@Override
	public int getCount() {
		// FAKE
		return FakeString.length;
	
	}

	@Override
	public Object getItem(int position) {
		//Fake
		return FakeString[position];
		
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 if (convertView == null) {
	            convertView = LayoutInflater.from(parent.getContext()).inflate
	            		(R.layout.single_item_listview, null);
	        }
		 TextView name = (TextView)convertView.findViewById(R.id.tv_item_name);
		 name.setText((String) getItem(position));
		 
		 return convertView;
	}

	

	

}
