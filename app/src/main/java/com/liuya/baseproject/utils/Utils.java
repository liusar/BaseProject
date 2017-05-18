package com.liuya.baseproject.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


import com.liuya.baseproject.R;
import com.liuya.baseproject.base.BaseApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Utils {

	/**
	 * toast （默�? 时间Toast.LENGTH_LONG�?
	 * 
	 * @param c
	 * @param msg
	 *            内容
	 */
	public static void toast(Context c, String msg) {
		toast(c, msg, true);
	}

	/**
	 * toast
	 * 
	 * @param c
	 * @param msg
	 *            内容
	 * @param duration
	 *            时间
	 */
	public static void toast(Context c, String msg, int duration) {
		toast(c, msg, true, duration);
	}

	/**
	 * toast 时间（默�? 时间Toast.LENGTH_LONG�?
	 * 
	 * @param c
	 * @param msg
	 *            内容
	 * @param show
	 *            是否显示
	 */
	public static void toast(Context c, String msg, boolean show) {
		toast(c, msg, show, Toast.LENGTH_SHORT);
	}

	/**
	 * toast
	 * 
	 * @param c
	 * @param msg
	 *            内容
	 * @param show
	 *            是否显示
	 * @param duration
	 *            时间
	 */
	public static void toast(Context c, String msg, boolean show, int duration) {
		if (!show)
			return;
		BaseApplication.toastMgr.builder.display(msg, duration);
	}

	/**
	 * toast 时间（默�? 时间Toast.LENGTH_LONG�?
	 * 
	 * @param c
	 * @param resid
	 *            内容资源id
	 */
	public static void toast(Context c, int resid) {
		toast(c, resid, true);
	}

	/**
	 * toast
	 * 
	 * @param c
	 * @param resid
	 *            内容资源id
	 * @param duration
	 *            时间
	 */
	public static void toast(Context c, int resid, int duration) {
		toast(c, resid, true, duration);
	}

	/**
	 * toast 时间（默�? 时间Toast.LENGTH_LONG�?
	 * 
	 * @param c
	 * @param resid
	 *            内容资源id
	 * @param show
	 *            是否显示
	 */
	public static void toast(Context c, int resid, boolean show) {
		toast(c, resid, show, Toast.LENGTH_LONG);
	}

	/**
	 * toast
	 * 
	 * @param c
	 * @param resid
	 *            内容资源id
	 * @param show
	 *            是否显示
	 * @param duration
	 *            时间
	 */
	public static void toast(Context c, int resid, boolean show, int duration) {
		if (!show)
			return;
		BaseApplication.toastMgr.builder.display(resid, duration);
	}

	public static void toast(Context c, String msg, boolean show, int duration, int position,
			int yOffset) {
		if (!show)
			return;
		BaseApplication.toastMgr.builder.display(msg, duration, position, yOffset);
	}

	/**
	 * 判断是否联网
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetConn(Context context) {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = connectivityManager.getActiveNetworkInfo();
			if (info != null && info.isAvailable()) {
				String name = info.getTypeName();
				UtilsLog.e("chat", "联网方式" + name);
				return true;
			} else {
				UtilsLog.e("chat", "断网");
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/* 判断GPS是否�?启，GPS或�?�AGPS�?启一个就认为是开启的
	 * @param context
	 * @return true 表示�?�?
	 */
	public static final boolean isOPenGPS(final Context context) {
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		// 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确�?��?�度快）
		boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (gps) {
			return true;
		}
		return false;
	}

	/**
	 * 强制帮用户打�?GPS(�?要权�?)
	 * 
	 * @param context
	 */
	public static final void openGPS(Context context) {
		Intent GPSIntent = new Intent();
		GPSIntent.setClassName("com.android.settings",
				"com.android.settings.widget.SettingsAppWidgetProvider");
		GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
		GPSIntent.setData(Uri.parse("custom:3"));
		try {
			PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
		} catch (CanceledException e) {
			e.printStackTrace();
		}
	}

	public static File getTempPath() {
		File tempFile = null;
		if (checkSDCard()) {
			File dirFile = new File(Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/asia/res/cache");
			tempFile = new File(dirFile, System.currentTimeMillis() + ".jpg");
			if (!tempFile.getParentFile().exists()) {
				tempFile.getParentFile().mkdirs();
			}
		}else{
			tempFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),System.currentTimeMillis() + ".jpg");
		}
		return tempFile;
	}

	public static boolean checkSDCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public static Intent createShotIntent(File tmpFile) {
		if (isCameraCanUse()) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			if (tmpFile != null) {
				Uri uri = Uri.fromFile(tmpFile);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			}
			return intent;

		} else {
			return null;
		}
	}

	public static Bitmap compressForupload(String imagePath) throws IOException,
	FileNotFoundException {
		ExifInterface exifInterface = new ExifInterface(imagePath);
		String orientation = exifInterface
				.getAttribute(ExifInterface.TAG_ORIENTATION);
		Bitmap bitmap = null;
		Bitmap newBitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(imagePath, options);
		int bitmapHeight;
		int bitmapWidth;
		if (options.outWidth > options.outHeight) {
			bitmapHeight = options.outWidth;
			bitmapWidth = options.outHeight;
		} else {
			bitmapHeight = options.outHeight;
			bitmapWidth = options.outWidth;
		}
		if (bitmapHeight > 840 || bitmapWidth > 480) {
			float scaleX = (float) bitmapHeight / 840;
			float scaleY = (float) bitmapWidth / 480;
			int sizeX = (int) (scaleX+0.5f);
			int sizeY = (int) (scaleY+0.5f);
			int size = sizeX < sizeY ? sizeX : sizeY;
			options.inJustDecodeBounds = false;
			options.inSampleSize = size; 
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			bitmap = BitmapFactory.decodeFile(imagePath, options);
		} else {
			bitmap = BitmapFactory.decodeFile(imagePath);
		}
		if ("6".equals(orientation)) {
			Matrix matrix = new Matrix();
			matrix.postRotate(90);
			newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
			bitmap.recycle();
		} else {
			newBitmap = bitmap;
		}
		bitmap=null;
		return compressImage(newBitmap);
	}

	public static Bitmap compressImage(Bitmap image) {  

		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中  
		int options = 100;  
		while ( baos.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩         
			baos.reset();//重置baos即清空baos  
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中  
			options -= 10;//每次都减少10  
		}  
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中  
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片  
		return bitmap;  
	} 

	public static boolean isCameraCanUse() {
		boolean canUse = true;
		Camera mCamera = null;
		try {
			mCamera = Camera.open();
		} catch (Exception e) {
			canUse = false;
		}
		if (canUse) {
			if (null != mCamera) {
				mCamera.release();
				mCamera = null;
			}
		}
		return canUse;
	}

	/**一秒内不能连续点击两下*/
	private static long lastClickTime;
	public synchronized static boolean isFastClick() {
		long time = System.currentTimeMillis();   
		if ( time - lastClickTime < 1000) {   
			return false;   
		}
		lastClickTime = time;   
		return true;   
	}

	/**手动定位*/
//	public static void startLocation(final Context mContext,boolean showDialog){
//		final ProgressDialog pro=new ProgressDialog(mContext);
//		if(showDialog){
//			pro.setCanceledOnTouchOutside(false);
////			pro.setCancelable(false);
//			pro.setMessage("正在定位，请稍候...");
//			pro.show();
//		}
//		LocationClient mLocClient = null;
//		mLocClient = new LocationClient(mContext);
//		LocationClientOption option = new LocationClientOption();
//		option.setOpenGps(true);// 打开gps
//		option.setCoorType("bd09ll"); // 设置坐标类型
//		option.setScanSpan(1000);
//		option.setIsNeedAddress(true);
//		option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//		mLocClient.setLocOption(option);
//		// 开启定位图层
//		mLocClient.start();
//		mLocClient.registerLocationListener(new BDLocationListener() {
//
//			@Override
//			public void onReceiveLocation(BDLocation location) {
//				// TODO Auto-generated method stub
//				Ld.e("定位地址：",location.getAddrStr());
//				Ld.e("Latitude",location.getLatitude());
//				Ld.e("Longitude",location.getLongitude());
//				SPF.setLatitude(location.getLatitude() + "");
//				SPF.setLongitude(location.getLongitude() + "");
//				SPF.setLocStreet(location.getStreet()+"");
//				if(pro!=null&&pro.isShowing()){
//					pro.dismiss();
//					toast(mContext, "定位成功");
//				}
//			}
//		});
//	}


	/**保存成dataCode。txt文件到本地*/
	public static void fileSave(String oAuth_1,Context context){  

		//保存在本地  
		try {  
			// 通过openFileOutput方法得到一个输出流，方法参数为创建的文件名（不能有斜杠），操作模式  
			FileOutputStream fos = context.openFileOutput("dataCode.txt",Context.MODE_PRIVATE);  
			ObjectOutputStream oos = new ObjectOutputStream(fos);  
			oos.writeObject(oAuth_1);// 写入  
			fos.close(); // 关闭输出流  
			//Toast.makeText(WebviewTencentActivity.this, "保存oAuth_1成功",Toast.LENGTH_LONG).show();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
			//Toast.makeText(WebviewTencentActivity.this, "出现异常1",Toast.LENGTH_LONG).show();  
		} catch (IOException e) {  
			e.printStackTrace();  
			//Toast.makeText(WebviewTencentActivity.this, "出现异常2",Toast.LENGTH_LONG).show();  
		}  

		//保存在sd卡  
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){  

			File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录  
			File sdFile = new File(sdCardDir, "dataCode.txt");  
			try {  
				FileOutputStream fos = new FileOutputStream(sdFile);  
				ObjectOutputStream oos = new ObjectOutputStream(fos);  
				oos.writeObject(oAuth_1);// 写入  
				fos.close(); // 关闭输出流  
			} catch (FileNotFoundException e) {  
				e.printStackTrace();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
			//Toast.makeText(WebviewTencentActivity.this, "成功保存到sd卡", Toast.LENGTH_LONG).show();  
		}  
	}  

	//获取屏幕的大小
	public static Screen getScreenPix(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		return new Screen(dm.widthPixels,dm.heightPixels);
	}
	public static class Screen{

		public int widthPixels;
		public int heightPixels;

		public Screen(){

		}

		public Screen(int widthPixels,int heightPixels){
			this.widthPixels=widthPixels;
			this.heightPixels=heightPixels;
		}

		@Override
		public String toString() {
			return "("+widthPixels+","+heightPixels+")";
		}

	}

	public static void startAnim(Activity activity){
		activity.overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);
	}

	/**
	 * 显示软键盘
	 * 
	 * @param context
	 * @param editText
	 */
	public static void showSoftKeyBroad(Context context, EditText editText) {
		InputMethodManager mgr = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		// only will trigger it if no physical keyboard is open
		mgr.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
	}
	
    //显示虚拟键盘
    public static void ShowKeyboard(View v)
    {
        InputMethodManager imm = ( InputMethodManager ) v.getContext( ).getSystemService( Context.INPUT_METHOD_SERVICE );     
      
      imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);    
  
    }
	
	/**
	 * 隐藏软键盘
	 * 
	 * @param activity
	 *            要隐藏软键盘的activity
	 */
	public static void hideSoftKeyBoard(Activity activity) {
		final View v = activity.getWindow().peekDecorView();
		if (v != null && v.getWindowToken() != null) {
			try {
				((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE))
						.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}