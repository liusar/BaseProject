package com.liuya.baseproject.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;





import com.asiasofti.millionaire.entity.LoginResult;

import android.content.SharedPreferences;

/**
 * ShareReference 操作相关
 * @author Administrator
 *
 */
public class SPF {
	/**經度*/
	public final static String KEY_LONGITUDE="longitude";
	/**維度*/
	public final static String KEY_LATITUDE="latitude";
	/**会员编号*/
	public final static String KEY_USERID="id";
	/**用户名*/
	public final static String KEY_USERNAME="username";
	/**用户密码*/
	public final static String KEY_USERPASSWORD="password";
	/**会员昵称*/
	public final static String KEY_NICKNAME="nickname";
	/**头像url*/
	public final static String KEY_HEADPORTRAIT="photourl";
	/**真实姓名*/
	public final static String KEY_REALNAME="realname";
	/**身份证号*/
	public final static String KEY_CARDID="cardid";
	/**用于登录的手机号*/
	public final static String KEY_BINDPHONE="bindphone";
	/**余额*/
	public final static String KEY_BANLANCE="balance";
	/**身份证url*/
	public final static String KEY_IDCARD="idcard";
	/**微信号*/
	public final static String KEY_WEIXIN="weixin";
	/**QQ号*/
	public final static String KEY_QQ="qq";
	/**email*/
	public final static String KEY_EMAIL="email";
	/**注册时间*/
	public final static String KEY_REGISTRIONTIME="registriontime";
	/**最后登录时间*/
	public final static String KEY_LASTLOGINTIME="lastlogintime";
	/**是否消费者（1=是，0否）*/
	public final static String KEY_XIAOFEIZHE="isxiaofeizhe";
	/**是否经销商（1=是，0否）*/
	public final static String KEY_JINGXIAOSHANG="isjingxiaoshang";
	/**是否维修商（1=是，0否）*/
	public final static String KEY_WEIXIUSHANG="isweixiushang";
	/**是否维修商（1=是，0否）*/
	public final static String KEY_CARDIDSTATE="cardidstate";
	/**维修商id*/
	public final static String KEY_WXSID="pitpersonid";
	/**经销商id*/
	public final static String KEY_JXSID="distributorid";
	/**维修商认证状态*/
	public final static String KEY_WXS_STATE="pitpersonstate";
	/**经销商认证状态*/
	public final static String KEY_JXS_STATE="distributorstate";
	/**经销商编码*/
	public final static String KEY_JXS_CODE="distributorcode";
	/**当前定位地址*/
	public final static String KEY_LOC_STREET="locationstreet";
	/**
	 * 加盟
	 */
	//	private final static String KEY_ISRAISEUSER="israiseuser";
	//	private final static String KEY_PORTRAITPATH="headportraitpath";
	//	private final static String KEY_CURRENT_ADDRESS="currentaddress";

	public static SharedPreferences sp;
	public static String getUserId(){
		return sp.getString(KEY_USERID, "0");
	}
	public static void setUserId(String userid){
		sp.edit().putString(KEY_USERID, userid).commit();
	}
	/**
	 * 是否登陆
	 * @return
	 */
	public static boolean isLogin(){
		return sp.getString(KEY_USERID,"0").length()>1;
	}
	
	/**
	 * 是否是车主
	 * @return
	 */
	public static boolean isCZ(){
		return sp.getString(KEY_XIAOFEIZHE,"0").equals("1");
	}
	
	/**
	 * 是否是经销商
	 * @return
	 */
	public static boolean isJXS(){
		return sp.getString(KEY_JINGXIAOSHANG,"0").equals("1");
	}
	
	/**
	 * 是否维修商
	 * @return
	 */
	public static boolean isWXS(){
		return sp.getString(KEY_WEIXIUSHANG,"0").equals("1");
	}
	/**
	 * 是否进行实名认证
	 * @return
	 */
	//	public static boolean  isAuthentication()
	//	{
	//		 return "123".indexOf(SPF.getRealnamecertification())<0;
	//	}
	/**经度*/
	public static void setLongitude(String longitude)
	{
		sp.edit().putString(KEY_LONGITUDE, longitude).commit();
	}
	public static String getLongitude(){
		return sp.getString(KEY_LATITUDE, "0");
	}

	/**纬度*/
	public static void setLatitude(String latitude)	{
		sp.edit().putString(KEY_LATITUDE, latitude).commit();
	}
	public static String getLatitude(){
		return sp.getString(KEY_LONGITUDE, "0");
	}
	
	/**用名户名*/
	public static void setUsername(String username){
		sp.edit().putString(KEY_USERNAME, username).commit();
	}
	public static String getUsername(){
		return sp.getString(KEY_USERNAME, "");
	}
	
	/**用名密码*/
	public static void setPassword(String password){
		sp.edit().putString(KEY_USERPASSWORD, password).commit();
	}
	public static String getPassword(){
		return sp.getString(KEY_USERPASSWORD, "");
	}
	/**用户头像*/
	public static void setPhotourl(String path)
	{
		sp.edit().putString(KEY_HEADPORTRAIT, path).commit();
	}
	public static String getPhotourl(){
		return sp.getString(KEY_HEADPORTRAIT, "");
	}

	/**用户昵称*/
	public static void setNickname(String nickname) {
		sp.edit().putString(KEY_NICKNAME, nickname).commit();
	}
	public static String getNickname() {
		return sp.getString(KEY_NICKNAME, "");
	}
	
	/**用户微信号*/
	public static void setWeixin(String weixin) {
		sp.edit().putString(KEY_WEIXIN, weixin).commit();
	}
	public static String getWeixin() {
		return sp.getString(KEY_WEIXIN, "");
	}
	
	/**用户qq号*/
	public static void setQq(String qq) {
		sp.edit().putString(KEY_QQ, qq).commit();
	}
	public static String getQq() {
		return sp.getString(KEY_QQ, "");
	}
	
	/**用户邮箱*/
	public static void setEmail(String email) {
		sp.edit().putString(KEY_EMAIL, email).commit();
	}
	public static String getEmail() {
		return sp.getString(KEY_EMAIL, "");
	}
	
	/**用户真实姓名*/
	public static void setRealname(String realname) {
		sp.edit().putString(KEY_REALNAME, realname).commit();
	}
	public static String getRealname() {
		return sp.getString(KEY_REALNAME, "");
	}
	
	/**用户身份证号*/
	public static void setCardid(String cardid) {
		sp.edit().putString(KEY_CARDID, cardid).commit();
	}
	public static String getCardid() {
		return sp.getString(KEY_CARDID, "");
	}
	
	/**用户绑定电话*/
	public static void setBindphone(String bindphone) {
		sp.edit().putString(KEY_BINDPHONE, bindphone).commit();
	}
	public static String getBindphone() {
		return sp.getString(KEY_BINDPHONE, "");
	}
	
	/**用户余额*/
	public static void setBalance(String balance) {
		sp.edit().putString(KEY_BANLANCE, balance).commit();
	}
	public static String getBalance() {
		return sp.getString(KEY_BANLANCE, "");
	}
	
	/**用户身份证*/
	public static String getIdcard() {
		return sp.getString(KEY_IDCARD, "");
	}
	public static void setIdcard(String idcard) {
		sp.edit().putString(KEY_IDCARD, idcard).commit();
	}
	
	/**用户注册时间*/
	public static void setRegistriontime(String registriontime) {
		sp.edit().putString(KEY_REGISTRIONTIME, registriontime).commit();
	}
	public static String getRegistriontime() {
		return sp.getString(KEY_REGISTRIONTIME, "");
	}
	
	/**用户上次登录时间*/
	public static void setLastlogintime(String lastlogintime) {
		sp.edit().putString(KEY_LASTLOGINTIME, lastlogintime).commit();
	}
	public static String getLastlogintime() {
		return sp.getString(KEY_LASTLOGINTIME, "");
	}
	
	/**是不是消费者*/
	public static void setIsxiaofeizhe(String isxiaofeizhe){
		sp.edit().putString(KEY_XIAOFEIZHE, isxiaofeizhe).commit();
	}
	public static String getIsxiaofeizhe() {
		return sp.getString(KEY_XIAOFEIZHE, "");
	}
	
	/**是不是经销商*/
	public static void setIsjingxiaoshang(String isjingxiaoshang){
		sp.edit().putString(KEY_JINGXIAOSHANG, isjingxiaoshang).commit();
	}
	public static String getIsjingxiaoshang() {
		return sp.getString(KEY_JINGXIAOSHANG, "");
	}
	
	/**是不是维修商*/
	public static void setIsweixiushang(String isweixiushang){
		sp.edit().putString(KEY_WEIXIUSHANG, isweixiushang).commit();
	}
	public static String getIsweixiushang() {
		return sp.getString(KEY_WEIXIUSHANG, "");
	}

	/**是不是实名认证*/
	public static void setCardidstate(String cardidstate){
		sp.edit().putString(KEY_CARDIDSTATE, cardidstate).commit();
	}
	public static String getCardidstate() {
		return sp.getString(KEY_CARDIDSTATE, "0");
	}
	
	/**维修商id*/
	public static void setPitpersonid(String pitpersonid){
		sp.edit().putString(KEY_WXSID, pitpersonid).commit();
	}
	public static String getPitpersonid() {
		return sp.getString(KEY_WXSID, "0");
	}
	
	/**经销商id*/
	public static void setDistributorid(String distributorid){
		sp.edit().putString(KEY_JXSID, distributorid).commit();
	}
	
	public static String getDistributorid() {
		return sp.getString(KEY_JXSID, "0");
	}
	
	/**经销商状态*/
	public static void setDistributorstate(String distributorstate){
		sp.edit().putString(KEY_JXS_STATE, distributorstate).commit();
	}
	public static String getDistributorstate() {
		return sp.getString(KEY_JXS_STATE, "0");
	}
	
	/**维修商状态*/
	public static void setPitpersonstate(String pitpersonstate){
		sp.edit().putString(KEY_WXS_STATE, pitpersonstate).commit();
	}
	public static String GetPitpersonstate() {
		return sp.getString(KEY_WXS_STATE, "0");
	}
	
	/**经销商编码*/
	public static void setDistributorcode(String code){
		sp.edit().putString(KEY_JXS_CODE, code).commit();
	}
	public static String GetDistributorcode() {
		return sp.getString(KEY_JXS_CODE, "0");
	}
	
	public static void setUserInfo(LoginResult userinfo)
	{
		Class<? extends LoginResult> cl=userinfo.getClass();
		Field[] field=cl.getDeclaredFields();
		for(Field f:field)
		{
			if(Modifier.toString(f.getModifiers()).equals("private")){				
				try {
					sp.edit().putString(f.getName(),(String) cl.getMethod("get"+JsonParserUtils.tologe(f.getName())).invoke(userinfo)).commit();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**维修商状态*/
	public static void setLocStreet(String locStreet){
		sp.edit().putString(KEY_LOC_STREET, locStreet).commit();
	}
	public static String getLocStreet() {
		return sp.getString(KEY_LOC_STREET, "");
	}
//	public static void setCurrentAddress(String add) {
//		sp.edit().putString(KEY_CURRENT_ADDRESS, add).commit();
//	}
//	public static String getCurrentAddress() {
//		return sp.getString(KEY_CURRENT_ADDRESS, null);
//	}
}
