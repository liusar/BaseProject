package com.liuya.baseproject.entity;

import java.io.Serializable;

public class LoginResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**用户ID*/
	private String id;
	/**用户名*/
	private String username;
	/**密码*/
	private String password;
	/**昵称*/
	private String nickname;
	/**真实姓名*/
	private String realname;
	/**身份证号*/
	private String cardid;
	/**绑定手机号*/
	private String bindphone;
	/*头像地址*/
	private String photourl;
	/**微信号*/
	private String weixin;
	/**QQ号*/
	private String qq;
	/**邮箱*/
	private String email;
	/**最后登录时间*/
	private String lastlogintime;
	/**是否消费者（1=是，0否）*/
	private String isxiaofeizhe;
	/**是否维修商（1=是，0否）*/
	private String isweixiushang;
	/**是否经销商（1=是，0否）*/
	private String isjingxiaoshang;
	/**身份证状态*/
	private String cardidstate;
	/**维修商id*/
	private String pitpersonid;
	/**经销商id*/
	private String distributorid;
	/**维修商认证状态*/
	private String pitpersonstate;
	/**经销商认证状态*/
	private String distributorstate;
	/**经销商认证编码*/
	private String distributorcode;
	/**余额*/
	private String balance;
	
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getDistributorcode() {
		return distributorcode;
	}
	public void setDistributorcode(String distributorcode) {
		this.distributorcode = distributorcode;
	}
	public String getPitpersonstate() {
		return pitpersonstate;
	}
	public void setPitpersonstate(String pitpersonstate) {
		this.pitpersonstate = pitpersonstate;
	}
	public String getDistributorstate() {
		return distributorstate;
	}
	public void setDistributorstate(String distributorstate) {
		this.distributorstate = distributorstate;
	}
	public String getPitpersonid() {
		return pitpersonid;
	}
	public void setPitpersonid(String pitpersonid) {
		this.pitpersonid = pitpersonid;
	}
	public String getDistributorid() {
		return distributorid;
	}
	public void setDistributorid(String distributorid) {
		this.distributorid = distributorid;
	}
	public String getCardidstate() {
		return cardidstate;
	}
	public void setCardidstate(String cardidstate) {
		this.cardidstate = cardidstate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getBindphone() {
		return bindphone;
	}
	public void setBindphone(String bindphone) {
		this.bindphone = bindphone;
	}
	public String getPhotourl() {
		return photourl;
	}
	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public String getIsxiaofeizhe() {
		return isxiaofeizhe;
	}
	public void setIsxiaofeizhe(String isxiaofeizhe) {
		this.isxiaofeizhe = isxiaofeizhe;
	}
	public String getIsweixiushang() {
		return isweixiushang;
	}
	public void setIsweixiushang(String isweixiushang) {
		this.isweixiushang = isweixiushang;
	}
	public String getIsjingxiaoshang() {
		return isjingxiaoshang;
	}
	public void setIsjingxiaoshang(String isjingxiaoshang) {
		this.isjingxiaoshang = isjingxiaoshang;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
