package com.liuya.baseproject.net;

public class NetConst {
	// http连接的header名字
	public static final String PLATFORM = "Client";//系统平台
	public static final String USERID = "UID";//注册用户的用户id
	public static String IMEI="0";
	public static final String RELOGINRESPONSE = "{\"state\": \"login\",\"message\": \"需要重新登陆\",\"content\": {}}";

	public static final String HTTP_HEADER_VERSION = "c_version";//版本
	public static final String HTTP_HEADER_PACKETVERSION = "curversion";//当前版本
	public static final String HTTP_HEADER_QQOPENID = "qqopenid";
	public static final String HTTP_HEADER_MACADDRESS = "machineid";
	public static final String HTTP_HEADER_USERNAME= "username";
	public static final String HTTP_HEADER_VIP= "uservip";
	
	public static final String NUM = "20";

	public static String url="http://login.aiwir.com/API/app/RequestControler.ashx";
	public static String version_code= "1.6";
	// 数据接口返回的状态
	// 获取数据成功 [成功时的默认状态,暂未使用]
	public static final int DATA_RECEIVE_STATUS_SUCCESS = 0;
	// 服务端数据无变化,未返回数
	public static final int DATA_RECEIVE_STATUS_UNCHANGED = 1;
	// 参数错误
	public static final int DATA_RECEIVE_STATUS_PARAMS_ERROR = -1;
	// 获取数据失败
	public static final int DATA_RECEIVE_STATUS_FAIL = -2;
	// 头信息有
	public static final int DATA_RECEIVE_STATUS_HEAD_ERROR = -3;
	// 未登陆
	public static final int DATA_RECEIVE_STATUS_NOTLOGIN = -4;
	// 连接超时
	public static final int DATA_RECEIVE_STATUS_SOCKET_TIMEOUT = -5;
	// 未知错误
	public static final int DATA_RECEIVE_STATUS_UNKNOWN_ERROR = -6;
}
