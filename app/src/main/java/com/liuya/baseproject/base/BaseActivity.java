package com.liuya.baseproject.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.liuya.baseproject.R;
import com.liuya.baseproject.utils.ConfirmDialog;
import com.liuya.baseproject.utils.DialogUtils;
import com.liuya.baseproject.view.LoadingDialog;

import org.json.JSONObject;

public abstract class BaseActivity extends FragmentActivity {

    private String TAG = "BaseActivity";

    public JSONObject mDataJson;		//包含网络请求所有结果
    public LoadingDialog mLoadingDialog;

    public boolean isCustomerTtitle ;	//是否自定义标题栏
    public boolean isBackShow ;			//是否显示返回箭头
    private String mTtitle ;				//标题栏

    private Button mBackBtn	;
    private TextView mTitleTxtv;

    FrameLayout mTitleBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isCustomerTtitle){
            //自定义标题
            requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        }
    }

    /**
     * 是否自定义标题 , 该方法必须在子Activity的 super.onCreate()之前调用, 否则无效
     * @param customerTtitle
     */
    public void setCustomerTitle(boolean backShow, boolean customerTtitle , String title){
        isCustomerTtitle = customerTtitle;
        isBackShow = backShow;
        mTtitle = title;
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title){
        mTtitle = title;
        if (mTitleTxtv!=null)mTitleTxtv.setText(mTtitle);
    }

    /**
     * activity初始化
     *
     */
    public void init(){

        if (isCustomerTtitle){
            //设置标题为某个layout
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        }
        mBackBtn = (Button)findViewById(R.id.titlebar_back_btn);
        if (isBackShow){
            mBackBtn.setVisibility(View.VISIBLE);
            mBackBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseActivity.this.finish();
                }
            });
        }else{
            if(mBackBtn!=null)mBackBtn.setVisibility(View.GONE);
        }

        String title = this.getTitle().toString();

        mTitleTxtv = (TextView)findViewById(R.id.titlebar_title_txtv);
        if(mTitleTxtv!=null)mTitleTxtv.setText(mTtitle);
        //setSearchViewStyle(homeSearch);
        //setSearchViewStyle(categorySearch);
        fetchIntent();
        initData();
        initViews();
    }

    /**
     * 以下三个函数不需要再子类调用, 只需要在子类的
     * onCrate()中调用:super.init()方法即可
     * 基类函数,初始化界面
     */
    abstract public void initViews();

    /**
     * 基类函数, 初始化数据
     */
    abstract public void initData();

    /**
     * 基类函数, 绑定事件
     */
    abstract public void fetchIntent();

    public void showToast(String msg){
        DialogUtils.showToast(this, msg);
    }

    public void showLoadingToast(){
        showLoadingToast(null);
    }

    public void showLoadingToast(String title){
        mLoadingDialog = new LoadingDialog(this , title);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.show();
    }

    public void showConfirmDialog(String message , String title , final ConfirmDialog.ConfirmDialogListener confirmDialogListener , final int actionType){
        ConfirmDialog.Builder builder = new ConfirmDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //设置你的操作事项
                if(confirmDialogListener!=null)confirmDialogListener.clickOk(actionType);
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    public void hideLoadingToast(){
        if(mLoadingDialog !=null){
            mLoadingDialog.dismiss();
        }
    }


    public void showToastUnLogin(){
        showToast(getString(R.string.toast_person_unlogin));
    }

    public void  toLoginPage(){
//        Intent loginIntent = new Intent(this , SPLoginActivity_.class);
//        startActivity(loginIntent);
    }

}
