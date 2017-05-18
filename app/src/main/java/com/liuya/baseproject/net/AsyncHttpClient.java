package com.liuya.baseproject.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import com.liuya.baseproject.utils.Ld;

import org.json.JSONObject;

import java.util.Map;

public class AsyncHttpClient extends AsyncTask<Map<String,String>,Void, JSONObject> {

	private String url,message;

	private PostRunnable postRun;

	private ProgressDialog pro;
	
	public AsyncHttpClient(String url,PostRunnable postRun)
	{
		this.url=url;
		this.postRun=postRun;
	}

	public AsyncHttpClient(Context context,String url,String message,PostRunnable postRun){
		this.url=url;
		this.postRun=postRun;
		this.message = message;
		pro=new ProgressDialog(context);
		pro.setCanceledOnTouchOutside(false);
//		pro.setCancelable(false);
		pro.setMessage(message);
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		if(pro !=null){
			pro.show();
		}
	}
	
	@Override
	protected JSONObject doInBackground(Map<String, String>... Pm) {
		if(isCancelled()){
			return null;
		}
		
		Map<String, String> Params = null;
		Map<String, String> Headers = null;
		if (Pm != null) {
			if (Pm.length == 1) {
				Params = Pm[0];
			} else if (Pm.length == 2) {
				Params = Pm[0];
				Headers = Pm[1];
			}
		}
		try {
			String data = HttpUtils.post(url, Headers, Params);
			Ld.d(url+"请求返回值：", data);
			return new JSONObject(data.toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	protected void onPostExecute(JSONObject result) {
		if(isCancelled()){
			return;
		}
		if(pro !=null&&pro.isShowing()){
			pro.dismiss();
		}
		postRun.run(result);
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}
	
	public interface PostRunnable{
		public void run(JSONObject result);
	}

}
