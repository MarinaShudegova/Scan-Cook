package com.scancook.eeb;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements TabListener {
	private String[] mTabs = {"Scan", "Products","Cookbook"};
	private ActionBar mActionBar;
	private ScanbookPageAdapter mPageadapter;
	private ViewPager mViewPager;
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main_activity);
		mViewPager = (ViewPager) findViewById(R.id.pager);
		//action bar working
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//adding actionbar names
		for (String tab_name : mTabs) {
            mActionBar.addTab(mActionBar.newTab().setText(tab_name).setTabListener(this));
		}
		mPageadapter = new ScanbookPageAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mPageadapter);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scan = IntentIntegrator.parseActivityResult(requestCode,
				resultCode, intent);
		if (scan != null) {
			Intent i = new Intent(this, WebViewHelper.class);
			i.putExtra("URL", "http://openean.kaufkauf.net/?ean="
					+ scan.getContents() + "&cmd=query&queryid=300000000");
			startActivity(i);
			/*text.setText(convertStreamToString(getInputStreamFromUrl("http://openean.kaufkauf.net/?ean="
					+ scan.getContents() + "&cmd=query&queryid=300000000")));*/
		}
	}
	public static InputStream getInputStreamFromUrl(String url) {
		InputStream content = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(new HttpGet(url));
			content = response.getEntity().getContent();
		} catch (Exception e) {
		}
		return content;
	}

	public static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}




	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
		
		
	}


	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
