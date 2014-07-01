package com.scancook.eeb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProductListAdapter extends BaseAdapter {
	private Context mContext;
	
	
	public ProductListAdapter(Context context) {
		mContext=context;
	}

	@Override
	public int getCount() {
		return ProductsOverview.ProductList.size();
	
	}

	@Override
	public Object getItem(int position) {
		return ProductsOverview.ProductList.get(position);
		
	}

	@Override
	public long getItemId(int position) {
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
