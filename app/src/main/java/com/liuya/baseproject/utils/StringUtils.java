package com.liuya.baseproject.utils;


import com.liuya.baseproject.base.BaseApplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {

	/**
	 * 判断是否为空
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isNullOrEmpty(String text) {
		if (text == null || "".equals(text.trim()) || text.trim().length() == 0
				|| "null".equals(text.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 对流转化成字符串
	 * 
	 * @param is
	 * @return
	 */
	public static String getStringByStream(InputStream is) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line + "\n");
			}
			return buffer.toString().replaceAll("\n\n", "\n");
		} catch (OutOfMemoryError o) {
			System.gc();
		} catch (Exception e) {
		}
		return null;
	}
	
	public static int dip2px(float dpValue) {
		final float scale = BaseApplication.getSelf().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(float pxValue) {
		final float scale = BaseApplication.getSelf().getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	/**
	 * 判定输入的是否是汉字
	 * 
	 * @param c
	 *            被校验的字符
	 * @return true代表是汉字
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
	
	/**
	 * 电话号码验证
	 * 
	 * @param phoneNumber
	 *            手机号码
	 * @return
	 */
	public static boolean validatePhoneNumber(String phoneNumber) {
		Pattern pattern = Pattern
				.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = pattern.matcher(phoneNumber);
		return m.matches();
	}

}
