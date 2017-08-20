package com.liuya.baseproject.utils;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

public class JsonParserUtils {
    public final static  String SET = "set";
    public static JSONObject obj;
    /**
     *   解析json字符�? 返回实体 
     * @param <T>
     * @param jsonStr  json字符�?
     * @param obj       要转换的实体�?
     * @return
     * @throws Exception
     */
	public static <T> T getObject(JSONObject json,Class<T> cl) throws Exception {
		Field[] fields = cl.getDeclaredFields();
		Object cla = cl.newInstance();
		T obj=cl.newInstance();
		for (int i = 0; i < fields.length; i++) {
			Class<?> type = fields[i].getType();
			if(!Modifier.toString(fields[i].getModifiers()).equals("public static final")
			   && !Modifier.toString(fields[i].getModifiers()).equals("private static final")){
				if(json.has(fields[i].getName())){
					invokeMethod(cl.getDeclaredMethod(SET+tologe(fields[i].getName()),type), obj,json.get(fields[i].getName()));
				}
			}	
		}
		return obj;
	}

/**
*   
* @param method
* @param obj
* @param args
* @return
* @throws IllegalArgumentException
* @throws IllegalAccessException
* @throws InvocationTargetException
*/
public static Object invokeMethod(Method method, Object obj, Object... args)
		throws IllegalArgumentException, IllegalAccessException,
		InvocationTargetException {
	return method.invoke(obj, args);
}

/**
 * 首字母变大写	
 * @param name
 * @return
 */
public static String tologe(String name){
	String str= name.substring(0,1);
	name = str.toUpperCase()+name.substring(1);
//	Ld.e("name", name);
	return name;
}

public static JSONObject getJsonObj(Map<String, String>map,String messagename){
	String data;
	try {
		data = HttpUtils.post(messagename, null, map);
		Ld.d(messagename+"请求返回值：", data);
		return new JSONObject(data.toLowerCase());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
	
}
