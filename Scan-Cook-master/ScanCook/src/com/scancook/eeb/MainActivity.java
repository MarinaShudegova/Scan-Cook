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
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements TabListener {
	
	private String[] mTabs = {"Scan", "Products","Cookbook"};
	private ActionBar mActionBar;
	private ScanbookPageAdapter mPageadapter;
	private ViewPager mViewPager;
	private ProductsOverviewReciver mReciver;
	
	
	/**Helena's code:
	  String link = "http://scancook.aldebaran.uberspace.de/conn2.php?produktname=16";
      URL url = new URL(link);

      String data  = URLEncoder.encode("username", "UTF-8") 
      + "=" + URLEncoder.encode("scancook", "UTF-8"); 
      {
      data += "&" + URLEncoder.encode("password", "UTF-8") 
      + "=" + URLEncoder.encode("HeJeHuArMa2014!", "UTF-8");
      URLConnection conn = url.openConnection(); 
      conn.setDoOutput(true);
      OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
      wr.write( data ); 
     BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
     for (String s; (s=reader.readLine()) != null;)
      
     System.out.println(s);
     
     helena's code bis hier von oben**/
     
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
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				getActionBar().setSelectedNavigationItem(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		mReciver = new ProductsOverviewReciver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("GoToOverview");
		registerReceiver(mReciver, filter);
	}
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scan = IntentIntegrator.parseActivityResult(requestCode,resultCode, intent);
		String scannedCode = scan.getContents();
		new RecieverScannedInfo(this).execute(scannedCode);
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
	
	private class ProductsOverviewReciver extends BroadcastReceiver {
		@Override
	    public void onReceive(Context context, Intent intent) {
		   if (intent.getAction().equals("GoToOverview")) {
			   mViewPager.setCurrentItem(1);//TODO:
		   }
	    }
	}

}
