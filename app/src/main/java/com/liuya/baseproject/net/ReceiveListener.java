package com.liuya.baseproject.net;

import android.os.Bundle;


public interface ReceiveListener<T> {

	/**
	 * 
	 * @param packet 数据对象
	 * @param extra  附加数据, 比如vip
	 */
	public void onReceive(T packet, Bundle extra);
	/**
	 * 处理接收失败 或 没有数据
	 * status 请参考 JoytingDataConst中 RECEIVE_STATUS_***** 的定义
	 * message 文字说明
	 */
	public void onReceiveFailed(int status, String message);

}
