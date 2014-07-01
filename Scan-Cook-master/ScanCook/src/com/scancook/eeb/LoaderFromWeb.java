package com.scancook.eeb;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;

class RecieverScannedInfo extends AsyncTask<String, Void, String> {
	Context mContext;
	private ProgressDialog dialog;
	
	public RecieverScannedInfo(Context context) {
		mContext = context;
		dialog = new ProgressDialog(mContext);
	}

    @Override
    protected void onPreExecute() {
    	dialog.setCancelable(false);
        dialog.setMessage("Please wait");
        dialog.show();
    }
	
	@Override
    protected String doInBackground(String... urls) {
    	DefaultHttpClient http = new DefaultHttpClient();
        HttpGet httpMethod = new HttpGet();
        String responseBody ="";
        
        try {
            httpMethod.setURI(new URI("http://openean.kaufkauf.net/?ean="+ urls[0] + "&cmd=query&queryid=300000000"));
        } 
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpResponse response = null;
        
        try {
            response = http.execute(httpMethod);
        } 
        catch (ClientProtocolException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        if (response == null) {
        	return "";
        
        }
        int responseCode = response.getStatusLine().getStatusCode();
       
        switch(responseCode){
            case 200:
                HttpEntity entity = response.getEntity();
                
                if(entity != null){
			        try {
			            responseBody = EntityUtils.toString(entity);
			        } 
			        catch (ParseException e) {
			            e.printStackTrace();
			        } 
			        catch (IOException e) {
			             e.printStackTrace();
			        }
                }
            break;
        }
        String ScannedProduct = stringParse (responseBody);
        return ScannedProduct;
    }

    @Override
    protected void onPostExecute(String result) {
		Intent intent = new Intent();
		intent.putExtra("RESULT", result);
		intent.setAction("RecieverScannedInfo.DATA");
		mContext.sendBroadcast(intent);
    	if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    
	private String stringParse(String source) {
		String result = "";
		Log.d("STRING_PARSE", source);
		int v_positionName = source.indexOf("name=");
		int v_positionDetailname = source.indexOf("detailname=");
		int v_positionVendor = source.indexOf("vendor=");
		int v_positionDescr = source.indexOf("descr=");
		int v_positionLand = source.indexOf("name_en=");
		String v_name = source.substring(v_positionName + 5,
				v_positionDetailname);
		String v_detailname = source.substring(v_positionDetailname + 12,
				v_positionVendor);
		String v_descr = source.substring(v_positionDescr + 6, v_positionLand);
		result = v_name;
		return result;
	}
    
}