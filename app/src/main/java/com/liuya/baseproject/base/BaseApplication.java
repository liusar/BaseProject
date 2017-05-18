package com.liuya.baseproject.base;


import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liuya.baseproject.R;
import com.liuya.baseproject.net.NetConst;
import com.liuya.baseproject.utils.SPF;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class BaseApplication extends Application {

	private static final String TAG="BaseApplication";
	private static BaseApplication mApp;
//	private IWXAPI api;

	@Override
	public void onCreate() {
		super.onCreate();
//		SDKInitializer.initialize(this);
		mApp = (BaseApplication)getApplicationContext();
		toastMgr.builder.init(mApp);
		SPF.sp=getSharedPreferences("UserInfo", MODE_PRIVATE);
//		initIWXAPI(this);
		TelephonyManager tel=(TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//		setversionCode();
		NetConst.IMEI=tel.getDeviceId();
		//创建默认的ImageLoader配置参数  
		ImageLoaderConfiguration configuration = ImageLoaderConfiguration
				.createDefault(this);  
		//Initialize ImageLoader with configuration.  
		ImageLoader.getInstance().init(configuration);
	}
	
	

//	public  void initIWXAPI(Context context)
//	{
//		api=WXAPIFactory.createWXAPI(context, WeiChatConfig.App_ID,false);//参数false是设置不�?测签名，发包�?好改成true
//		api.registerApp(WeiChatConfig.App_ID);
//	}
//	private void setversionCode() {
//		int versionCode = -1;
//		try {
//			versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
//		} catch (NameNotFoundException e) {
//			e.printStackTrace();
//		}
//		NetConst.version_code = versionCode +"";
//	}

	public static BaseApplication getSelf() {
		return mApp;
	}
//	public IWXAPI getWeiChatApi() {
//		return api;
//	}
	/**
	 * toast singleton，用来统�?显示toast，这样就可以实现toast的快速刷�?
	 * 
	 */
	public enum toastMgr {
		builder;

		private View v;
		private TextView tv;
		private Toast toast;

		private void init(Context c) {
			// v = Toast.makeText(c, "", Toast.LENGTH_SHORT).getView();
			v = LayoutInflater.from(c).inflate(R.layout.toast, null);
			tv = (TextView) v.findViewById(R.id.tv_toast);
			toast = new Toast(c);
			toast.setView(v);
		}

		public void display(CharSequence text, int duration) {
			if (text.length() != 0) {
				tv.setText(text);
				toast.setDuration(duration);
				toast.setGravity(Gravity.BOTTOM, 0, 180);
				toast.show();
			}
		}

		public void display(int Resid, int duration) {
			if (Resid != 0) {
				tv.setText(Resid);
				toast.setDuration(duration);
				toast.show();
			}
		}

		public void display(CharSequence text, int duration, int position, int yOffset) {
			if (text.length() != 0) {
				tv.setText(text);
				toast.setDuration(duration);
				toast.setGravity(position, 0, yOffset);
				toast.show();
			}
		}
	}

}
