package com.liuya.baseproject.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.os.Handler;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuya.baseproject.utils.Ld;
import com.liuya.baseproject.utils.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncBitmapLoader {

	 String localCache_Folder=Environment.getExternalStorageDirectory()+"/asia/res/cache/";
	/**
	 * 内存图片软引用缓冲
	 */
	private SparseArray<SoftReference<Bitmap>> imageCache = null;
	private ExecutorService Pool=Executors.newSingleThreadExecutor();
	private Vector<Integer> waitQueue;
	private  static AsyncBitmapLoader instance;
	private AsyncBitmapLoader() {
		imageCache = new SparseArray<SoftReference<Bitmap>>();
		waitQueue=new Vector<Integer>();
	}
	public static AsyncBitmapLoader getMe()
	{
		if(instance==null)
			instance=new AsyncBitmapLoader();
		instance.ensureFoldersExists();
		return instance;
	}
	public Bitmap loadBitmap(final View v,final String imageURL) {
		if(StringUtils.isNullOrEmpty(imageURL)){
		    Ld.d("AsyncBitmapLoader", "imageURL="+imageURL);
			return null;
		}
		Bitmap bitmap=null;
		// 查找内存缓存
		if (imageCache.indexOfKey(imageURL.hashCode())>=0) {
			SoftReference<Bitmap> reference = imageCache.get(imageURL.hashCode());
			bitmap = reference.get();
			if(bitmap!=null&&!bitmap.isRecycled())
				return bitmap;
		}
		//内存缓存找不到,去本地缓存中找
		String bitmapName = imageURL.hashCode()+".cache";
		final File file = new File(localCache_Folder+bitmapName);
		if(file.exists())
	    bitmap=BitmapFactory.decodeFile(file.getAbsolutePath());
		if(bitmap!=null)
		{
			imageCache.put(imageURL.hashCode(), new SoftReference<Bitmap>(bitmap));
			return bitmap;
		}
		if (!waitQueue.contains(imageURL.hashCode())) {
			waitQueue.add(imageURL.hashCode());
			Pool.execute(new Runnable() {
				Object vTag;
				Bitmap bmp = null;
				@Override
				public void run() {
					if(v!=null)
					vTag=v.getTag();
					InputStream ins = HttpUtils.getStreamFromURL(imageURL);
					if (ins != null)
						bmp = BitmapFactory.decodeStream(ins);
					if (bmp != null) {
						handler.post(new Runnable() {
							@Override
							public void run() {
								if(v!=null&&v.getTag()==vTag)
								{
									if(v instanceof ImageView){
								      ((ImageView)v).setImageBitmap(bmp);
									}else if(v instanceof TextView)
									{
										v.setBackgroundDrawable(new BitmapDrawable(bmp));
									}
								}
							}
						});
						imageCache.put(imageURL.hashCode(), new SoftReference<Bitmap>(bmp));
					}
					waitQueue.remove((Integer)imageURL.hashCode());
					if (!file.exists()) {
						try {
							file.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					FileOutputStream fos = null;
					try {
						fos = new FileOutputStream(file);
						bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (fos != null) {
							try {
								fos.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			});
		}
		return null;
	}
	Handler handler=new Handler();
	private void ensureFoldersExists() {
		File folder=new File(localCache_Folder);
		if(!folder.exists())
			folder.mkdirs();
		folder=null;
	}
	private interface ImageCallBack {
		public void imageLoad(Bitmap bitmap);
	}

}
