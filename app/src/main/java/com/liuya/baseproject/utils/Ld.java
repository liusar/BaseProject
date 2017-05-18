package com.liuya.baseproject.utils;

import android.util.Log;

public class Ld {
	public static boolean DEBUG = true;
	/**
	 * 输出调试期消�?,程序发布时关闭该 调试信息
	 * @param tag
	 * @param msg
	 */
	public static void d(String tag,String msg){
		if (DEBUG){
			Log.d(tag, ""+msg);
		}
	}
	public static void d(String tag,Integer msg){
		if (DEBUG){
			Log.d(tag, msg!=null?msg.toString():"null");
		}
	}
	public static void d(String tag,Long msg){
		if (DEBUG){
			Log.d(tag, msg!=null?msg.toString():"null");
		}
	}
	public static void d(String tag,Object msg){
		if (DEBUG){
			Log.d(tag, msg!=null?msg.toString():"null");
		}
	}
	
	public static void e(String tag,String msg){
		if (DEBUG){
			Log.e(tag, ""+msg);
		}
	}
	public static void e(String tag,Integer msg){
		if (DEBUG){
			Log.e(tag, msg!=null?msg.toString():"null");
		}
	}
	public static void e(String tag,Long msg){
		if (DEBUG){
			Log.e(tag, msg!=null?msg.toString():"null");
		}
	}
	public static void e(String tag,Object msg){
		if (DEBUG){
			Log.e(tag, msg!=null?msg.toString():"null");
		}
	}
}
