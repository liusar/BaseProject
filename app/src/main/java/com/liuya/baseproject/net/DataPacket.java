package com.liuya.baseproject.net;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class DataPacket<T> implements Serializable {

	private static final long serialVersionUID = 8035765008025338989L;
	/**
	 * 数据包结构, 从服务端拉取回来的结构
	 */
	//状态码
	//private String statusCode;
	//总页数
	private int totalPage;
	//总记录条数
	private int totalCount;
	//本页条数
	private int curPageSize;
	//是否有下一页
	private boolean hasNextPage;
	//是否有上一页
	private boolean hasPriorPage;
	//当页页号
	private int curPageNo;
	//下一页号
	private int nextPageNo;
	//上一页号
	private int priorPageNo;
	//时间戳
	private int timeStamp;
	//数据包版本号
	private String version;
	//数据内容,List形式, 可以是 chapter或book
	private List<T> content = new ArrayList<T>();// 书或章节等List形式的内容
//	@Deprecated
//	public String getStatusCode() {
//		return statusCode;
//	}
//	@Deprecated
//	public void setStatusCode(String statusCode) {
//		this.statusCode = statusCode;
//	}
	public List<T> getContent() {
		return content;
	}
	@SuppressWarnings("unchecked")
	public void setContent(Object content) {
		this.content = (List<T>) content;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public boolean isHasPriorPage() {
		return hasPriorPage;
	}
	public void setHasPriorPage(boolean hasPriorPage) {
		this.hasPriorPage = hasPriorPage;
	}
	public int getCurPageSize() {
		return curPageSize;
	}
	public void setCurPageSize(int curPageSize) {
		this.curPageSize = curPageSize;
	}
	public int getCurPageNo() {
		return curPageNo;
	}
	public void setCurPageNo(int curPageNo) {
		this.curPageNo = curPageNo;
	}
	public int getNextPageNo() {
		return nextPageNo;
	}
	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}
	public int getPriorPageNo() {
		return priorPageNo;
	}
	public void setPriorPageNo(int priorPageNo) {
		this.priorPageNo = priorPageNo;
	}
	public int getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public DataPacket(){
		
	}
	
	//字段定义
	public static final String FIELD_TOTALPAGE = "totalPage";
	public static final String FIELD_TOTALCOUNT = "totalCount";
	public static final String FIELD_CURPAGESIZE = "curPageSize";
	public static final String FIELD_HASNEXTPAGE="hasNextPage";
	public static final String FIELD_HASPRIORPAGE="hasPriorPage";
	public static final String FIELD_CURPAGENO="curPageNo";
	public static final String FIELD_NEXTPAGENO="nextPageNo";
	public static final String FIELD_PRIORPAGENO="priorPageNo";
	public static final String FIELD_VERSION="curversion";
	public static final String FIELD_CONTENT="content";
	
	public DataPacket(JSONObject json,String version) throws JSONException{
		if (json == null){
			setVersion(version);
			setHasNextPage(false);
			setHasPriorPage(false);
			setCurPageNo(1);
			setNextPageNo(0);
			setPriorPageNo(0);
			setTimeStamp(0);
		}else{
			setCurPageNo(json.getInt(FIELD_CURPAGENO));
			setCurPageSize(json.getInt(FIELD_CURPAGESIZE));
			setHasNextPage(json.getInt(FIELD_HASNEXTPAGE) == 1);
			setHasPriorPage(json.getInt(FIELD_HASPRIORPAGE) == 1);
			setNextPageNo(json.getInt(FIELD_NEXTPAGENO));
			setPriorPageNo(json.getInt(FIELD_PRIORPAGENO));
			setTotalCount(json.getInt(FIELD_TOTALCOUNT));
			setTotalPage(json.getInt(FIELD_TOTALPAGE));
			setVersion(version);

		}
	}
}
