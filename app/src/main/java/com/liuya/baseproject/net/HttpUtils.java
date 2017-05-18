package com.liuya.baseproject.net;

import com.liuya.baseproject.utils.AESUtils;
import com.liuya.baseproject.utils.Ld;
import com.liuya.baseproject.utils.SPF;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtils {
	private static int BUFFER_SIZE = 8192;
	public static final String PACKET_DATA = "data";
	public static final String PACKET_HEADER = "header";
	public static final int METHOD_GET = 0;
	public static final int METHOD_POST = 1;
	
	private static String InputStreamToString(InputStream in, String encoding) throws Exception {
		BufferedInputStream bufferInput = new BufferedInputStream(in);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		BufferedOutputStream bufferOut = new BufferedOutputStream(outStream);
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = bufferInput.read(data, 0, BUFFER_SIZE)) != -1){
			bufferOut.write(data, 0, count);
		}
		data = null;
		bufferOut.flush();
		String content = new String(outStream.toByteArray(), encoding);
		if(bufferInput!=null){
			bufferInput.close();
		}
		if(outStream!=null){
			outStream.close();
		}
		if(bufferOut!=null){
			bufferOut.close();
		}
		return AESUtils.Decrypt(content);
	}
	/**
	 * 返回全部数据的post方法,比如获取响应的文件头时使用
	 * @param type  请求的url类型
	 * @param headers  请求头
	 * @param params   请求参数
	 * @return
	 * @throws Exception
	 */
	public static String post(String type,Map<String,String> headers,Map<String,String> params) throws Exception{
		HttpResponse response = connectNet(type, headers, params,METHOD_POST);
		if(response.getStatusLine().getStatusCode()==200)
		{
			if(!checkDeviceId(response))
			{
				SPF.setUserId("0");
				if(checkRequestLogin(type));
				return NetConst.RELOGINRESPONSE;
			}
			InputStream in=response.getEntity().getContent();
			return InputStreamToString(in, "UTF8");
		}else
			throw new Exception(response.getStatusLine().getReasonPhrase());
	}

	/**
	 * 常规get方法,传入url,header信息,返回字符串
	 * @param type
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String get(String type,Map<String,String> headers,Map<String,String> params) throws Exception{
		HttpResponse response = connectNet(type, headers, params,METHOD_GET);
		if(response.getStatusLine().getStatusCode()==200)
		{
			if(!checkDeviceId(response))
			{
				SPF.setUserId("0");
				if(checkRequestLogin(type))
				return NetConst.RELOGINRESPONSE;
			}
			InputStream in=response.getEntity().getContent();
			return InputStreamToString(in, "UTF8");
		}else
			throw new Exception(response.getStatusLine().getReasonPhrase());
	}
	

	/**
	 * @author czw
	 * HttpClient方式联网，返回HttpResponse对象;
	 * @param type 联网请求类型,
	 * @param headers 头信息
	 * @param params 参数
	 * @return HttpResponse对象
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	private static HttpResponse connectNet(String type, Map<String,String> headers,
			Map<String,String> params,int Method) throws IOException, ClientProtocolException {
		if (params != null)
			params.put("type", type);
		else {
			params = new HashMap<String, String>();
			params.put("type", type);
		}
		addCommonParams(params);
		String data=buildUrl(params);
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
		HttpUriRequest request = null;
		if (Method == METHOD_GET)
		{
			String url = NetConst.url+"?"+PACKET_DATA+"="+data;
			Ld.e("url",url );
			request = new HttpGet(NetConst.url+"?"+PACKET_DATA+"="+data);}
		else
		{
			request = new HttpPost(NetConst.url);
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair(PACKET_DATA, data));
			((HttpPost)request).setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
		}
		if (headers != null)
			for (String key : headers.keySet()) {
				request.addHeader(key, headers.get(key));
			}
		HttpResponse response = client.execute(request);
		return response;
	}
	
	/**
	 * get方式拼接url地址
	 * @throws UnsupportedEncodingException 
	 */
	private static String buildUrl(Map<String, String> map)
			throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		String value;

		for (String key : map.keySet()) {
			value = map.get(key);
			if (isNullOrEmpty(value)) {
				continue;
			} /*else {
				value = URLEncoder.encode(value, "UTF-8");
			}*/
			sb.append(key).append("=").append(value).append("&");
		}
		sb.deleteCharAt(sb.length() - 1);
		Ld.d("data", sb.toString());
		String data2 = AESUtils.ReplacePlusSymbol(AESUtils.Encrypt(sb.toString()));
		Ld.e("data2", data2);
		return AESUtils.ReplacePlusSymbol(AESUtils.Encrypt(sb.toString()));
	}
	/**
	 * 添加一些公共的参数
	 * @param params
	 */
	private static void addCommonParams(Map<String,String> params)
	{
		params.put(NetConst.PLATFORM, "android");
		params.put(NetConst.USERID,SPF.getUserId());
	}
	public static boolean isNullOrEmpty(String text) {
		if (text == null || "".equals(text.trim()) || text.trim().length() == 0
				|| "null".equals(text.trim())) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断响应头中的设备id是否与本机的设备id一致。
	 * @param res
	 * @return
	 */
	public static boolean checkDeviceId(HttpResponse res){
		
		Header[] id=res.getHeaders("EquipmentID");
		if(id.length==0)
			return true;
		if(id!=null)
		{
			String imei=id[0].getValue();
			Ld.d("IMEI int Server:", imei);
			Ld.d("IMEI of This device:", NetConst.IMEI);
			if("".equals(imei)||NetConst.IMEI.equals(imei))
			{
				return true;
			}
		}
		return false;
	}
	
	public static InputStream getStreamFromURL(String imageURL) {
		InputStream in = null;
		int i = 0;
		while (i < 3) {
			try {
				URL url = new URL(imageURL);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				in = connection.getInputStream();
				if (in != null)
					break;
				else
					i++;
			} catch (Exception e) {
				i++;
				e.printStackTrace();
			}
		}
		return in;

	}
	public static boolean checkRequestLogin(String url)
	{
		for(String u:noNeedLoginUrls)
		{
			if(u.equals(url))
				return false;
		}
		return true;
	}
	private final static List<String> noNeedLoginUrls=new ArrayList<String>();
	static{
		noNeedLoginUrls.add("Login");
		noNeedLoginUrls.add("SendVerificationCode");
		noNeedLoginUrls.add("GetArticle");
		noNeedLoginUrls.add("GetNearVehicles");
		noNeedLoginUrls.add("GetNearChargingpies");
		noNeedLoginUrls.add("GetNearPits");
		noNeedLoginUrls.add("GetVehicleInfoByID");
		noNeedLoginUrls.add("GetPitsInfoByID");
		noNeedLoginUrls.add("GetChargingPieInfoByID");
		noNeedLoginUrls.add("GetLocationByGPS");
		noNeedLoginUrls.add("AppAutoUpdate");
		noNeedLoginUrls.add("GetAddressSuggestion");
		noNeedLoginUrls.add("AppStratingImages");
	}
}
