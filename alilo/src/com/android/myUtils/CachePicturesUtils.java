package com.android.myUtils;

import com.android.alilo.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

public class CachePicturesUtils {

	//设置图片缓存
		public  static int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
	    public  static int cacheSize = maxMemory/6;
	    public  static LruCache<String, Bitmap> mMemoryCache=new LruCache<String, Bitmap>(cacheSize);
	    private Context mcontext;
	    public CachePicturesUtils(Context context) {
	    	this.mcontext = context;
		}
	    
	    public  void loadBitmapForApk(int resId,ImageView imageview){
	    	final String imagekey = String.valueOf(resId);
	    	final Bitmap bitmap = getBitmapFromMemCache(imagekey);
	    	if(bitmap!=null){
	    		imageview.setImageBitmap(bitmap);
	    	}else{
	    		imageview.setImageResource(resId);
	    		BitmapWorkerTask task = new BitmapWorkerTask(imageview);
	    		task.execute(resId);
	    	}
	    }
	    
	    
	    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
	    	
	    	private ImageView imageview;
	    	
	    	public BitmapWorkerTask(ImageView imageview) {
	    		this.imageview = imageview;
			}
	    	@Override
			protected void onPreExecute() {
				super.onPreExecute();
				imageview.setImageResource(R.drawable.ljsw45_dydtx_up);
			}
			@Override
			protected void onPostExecute(Bitmap result) {
				super.onPostExecute(result);
				imageview.setImageBitmap(result);
			}
			
			// 在后台加载图片。
	    	@Override
	    	protected Bitmap doInBackground(Integer... params) {
	    		final Bitmap bitmap = decodeSampledBitmapFromResource(
	    				mcontext.getResources(), params[0], 100, 100);
	    		addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
	    		return bitmap;
	    	}
	    }
	    
	    
	    public void addBitmapToMemoryCache(String key,Bitmap bitmap){
	    	if(getBitmapFromMemCache(key)==null){
	    		mMemoryCache.put(key, bitmap);
	    	}
	    }
	    
	    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	            int reqWidth, int reqHeight) {
	    	// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
	        final BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inJustDecodeBounds = true;
	        BitmapFactory.decodeResource(res, resId, options);
	        // 调用上面定义的方法计算inSampleSize值
	        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
	        // 使用获取到的inSampleSize值再次解析图片
	        options.inJustDecodeBounds = false;
	        return BitmapFactory.decodeResource(res, resId, options);
	    }
	    
	    public static int calculateInSampleSize(BitmapFactory.Options options,
	    		int reqWidth, int reqHeight) {
	    	// 源图片的高度和宽度
	    	final int height = options.outHeight;
	    	final int width = options.outWidth;
	    	int inSampleSize = 1;
	    	if (height > reqHeight || width > reqWidth) {
	    		// 计算出实际宽高和目标宽高的比率
	    		final int heightRatio = Math.round((float) height / (float) reqHeight);
	    		final int widthRatio = Math.round((float) width / (float) reqWidth);
	    		// 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
	    		// 一定都会大于等于目标的宽和高。
	    		inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
	    	}
	    	return inSampleSize;
	    }
	    
	    public static Bitmap getBitmapFromMemCache(String key){
	    	return mMemoryCache.get(key);
	    }

}
