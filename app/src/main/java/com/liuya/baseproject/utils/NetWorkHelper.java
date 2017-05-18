package com.liuya.baseproject.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;

public class NetWorkHelper {

	private static String LOG_TAG = "NetWorkHelper";

	public static Uri uri = Uri.parse("content://telephony/carriers");

	/**
	 * 判断是否有网络连接
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity == null) {
			Ld.d(LOG_TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].isConnected()) {
						Ld.d(LOG_TAG, "network is available");
						return true;
					}
				}
			}
		}
		Ld.d(LOG_TAG, "network is not available");
		return false;
	}
	

	/**
	 * 判断网络是否为漫�?
	 */
	public static boolean isNetworkRoaming(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Ld.d(LOG_TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null
					&& info.getType() == ConnectivityManager.TYPE_MOBILE) {
				TelephonyManager tm = (TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE);
				if (tm != null && tm.isNetworkRoaming()) {
					Ld.d(LOG_TAG, "network is roaming");
					return true;
				} else {
					Ld.d(LOG_TAG, "network is not roaming");
				}
			} else {
				Ld.d(LOG_TAG, "not using mobile network");
			}
		}
		return false;
	}

	/**
	 * 判断MOBILE网络是否可用
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static boolean isMobileDataEnable(Context context) throws Exception {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean isMobileDataEnable = false;
		isMobileDataEnable = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
		return isMobileDataEnable;
	}

	
	/**
	 * 判断wifi 是否可用
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static boolean isWifiDataEnable(Context context) throws Exception {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean isWifiDataEnable = false;
		isWifiDataEnable = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		return isWifiDataEnable;
	}

}
