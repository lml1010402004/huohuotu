package com.android.adapter;

import java.util.List;

import android.content.Context;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.android.alilo.R;
import com.android.model.ApkItem;
import com.android.myUtils.CachePicturesUtils;

public class MyGridViewAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private Context mcontext;
	private List<ApkItem> arraylist;
	ApkItem item  =null;
	
	private int mgroupposition = -1;
	private CachePicturesUtils cacheutils;
	
	//设置图片缓存.
	//private static final  int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
  //  private static final int cacheSize = maxMemory/4;
   // private static final  LruCache<String, Bitmap> mMemoryCache=new LruCache<String, Bitmap>(cacheSize);
	
	public MyGridViewAdapter(Context context,List<ApkItem> itemGridList,int groupposition) {

	//这段代码是给图片设置缓存  ==开始
		//maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
		//cacheSize = maxMemory/4;
	   //  mMemoryCache = new LruCache<String, Bitmap>(cacheSize){

		//		protected int sizeOf(String key, Bitmap value) {
					//重写此方法来衡量每张图的大小，默认返回图片数量
		//			return value.getByteCount()/1024;
		//		}
	   //     };
	     //图片设置缓存  == 结束

	    this.mcontext = context;
	    this.arraylist = itemGridList;
	    this.mgroupposition = groupposition;
		cacheutils = new CachePicturesUtils(context);
	
	}

	@Override
	public int getCount() {
		return arraylist.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arraylist.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int arg2, View convertview, ViewGroup parent) {
		final ViewHolder viewholder;
		if(convertview==null){
			viewholder = new ViewHolder();
			convertview = LayoutInflater.from(mcontext).inflate(R.layout.item_menu, null);
			viewholder.apkImage = (ImageView) convertview.findViewById(R.id.item_image);
			viewholder.apkName = (ImageView) convertview.findViewById(R.id.item_text);
			convertview.setTag(viewholder);
		}else{
			viewholder = (ViewHolder) convertview.getTag();
		}
		item = arraylist.get(arg2);
	if(item.isIslocked()){
			//viewholder.apkImage.setImageResource(item.getApkImage());
		    cacheutils.loadBitmapForApk(item.getApkImage(),viewholder.apkImage);
			viewholder.apkImage.setBackgroundResource(item.getApkbackgroud());
		}else{
           // viewholder.apkImage.setImageResource(item.getApkImage());
			 cacheutils.loadBitmapForApk(item.getApkImage(),viewholder.apkImage);
	}
		//viewholder.apkName.setImageResource(item.getApkName());
       cacheutils.loadBitmapForApk(item.getApkName(),viewholder.apkName);
		return convertview;
	}
	
    
	
	class ViewHolder {
		ImageView apkImage;
		ImageView apkName;
	}

}
