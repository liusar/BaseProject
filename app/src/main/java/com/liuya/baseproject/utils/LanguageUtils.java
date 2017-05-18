package com.liuya.baseproject.utils;

import java.util.Locale;

import com.asiasofti.millionaire.ui.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

/**
 * 切换语言工具类
 * @author liuya
 *
 */

public class LanguageUtils {
	
	public static void changeLanguage(Context context,Activity activity){
		String sta=context.getResources().getConfiguration().locale.getLanguage();
		  if(sta.equals("zh")){
		        Locale.setDefault(Locale.ENGLISH); 
		        Configuration config = context.getResources().getConfiguration();
		                config.locale = Locale.ENGLISH; 
		                context.getResources().updateConfiguration(config
		                    , context.getResources().getDisplayMetrics());
		                Intent intent=new Intent(activity,MainActivity.class);
		                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		                context.startActivity(intent);
		        }else{
		            Locale.setDefault(Locale.CHINESE); 
		            Configuration config = context.getResources().getConfiguration();
		            config.locale = Locale.CHINESE; 
		            context.getResources().updateConfiguration(config
		                , context.getResources().getDisplayMetrics());
		            Intent intent=new Intent(activity,MainActivity.class);
	                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                context.startActivity(intent);
		        }
	}
}
