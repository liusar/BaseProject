package com.liuya.baseproject.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public abstract class BaseFragment extends Fragment implements OnClickListener {
	protected Activity mContext;
	public View rootView;
	protected Activity MenuChangeHome;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		initData(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = initView(inflater);
		rootView.setOnTouchListener(ontouchListener);
		setListener();
		return rootView;
	}

	public View getRootView() {
		return rootView;
	}

	@Override
	public void onClick(View v) {
	}

	OnTouchListener ontouchListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			return true;
		}
	};
	
	/**
	 * 初始化UI
	 * 
	 * @param inflater
	 * @return
	 */
	protected abstract View initView(LayoutInflater inflater);

	/**
	 * 初始化数据
	 * 
	 * @param savedInstanceState
	 */
	protected abstract void initData(Bundle savedInstanceState);

	/**
	 * 设置监听
	 */
	protected abstract void setListener();
	/**
	 * 更新数据
	 */
	public  void UpdateData(Bundle bd)
	{
		
	}
}
