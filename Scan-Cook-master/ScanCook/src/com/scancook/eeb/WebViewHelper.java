package com.scancook.eeb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

// from Huachong, scanner working
public class WebViewHelper extends Activity {
		private WebView webview;
		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        Intent i = getIntent();
	        String url = i.getStringExtra("URL");
	        setContentView(R.layout.activity_web_app);
	        final Activity activity = this;
	        webview = (WebView) findViewById(R.id.webview01);
	        webview.getSettings().setJavaScriptEnabled(true);
	        webview.setWebViewClient(new WebViewClient() {
	        	public void onReceivedError(WebView view,int errorCode, String description, String failingUrl) {
	        		Toast.makeText(activity,"Activity failed!" + description,Toast.LENGTH_SHORT).show();
	        	}
	        });
	        webview.loadUrl(url);
	    }
	}

