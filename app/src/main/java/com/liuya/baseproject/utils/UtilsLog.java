package com.liuya.baseproject.utils;

public class UtilsLog {

	public static void d(String key, String value) {
		if (true) {
			android.util.Log.d(key, value);
		}
	}

	public static void i(String key, String value) {
		if (true) {
			android.util.Log.i(key, value);
		}
	}

	public static void e(String key, String value) {
		if (true) {
			android.util.Log.e(key, value);
		}
	}

	public static void w(String key, String value) {
		if (true) {
			android.util.Log.w(key, value);
		}
	}

	public static void w(String key, Throwable tr) {
		if (true) {
			android.util.Log.w(key, tr);
		}
	}

	public static void w(String key, String value, Throwable tr) {
		if (true) {
			android.util.Log.w(key, value, tr);
		}
	}

	public static void log(String tag, String info) {
		StackTraceElement[] ste = new Throwable().getStackTrace();
		int i = 1;
		if (true) {
			StackTraceElement s = ste[i];
			android.util.Log.e(tag, String.format("======[%s][%s][%s]=====%s", s.getClassName(),
					s.getLineNumber(), s.getMethodName(), info));
		}
	}
}
