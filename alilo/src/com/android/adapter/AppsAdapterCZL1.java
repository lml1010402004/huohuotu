package com.android.adapter;

import java.util.ArrayList;
import java.util.List;

import com.android.alilo.R;
import com.android.model.ApkItem;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.android.myUtils.CachePicturesUtils;

public class AppsAdapterCZL1 extends BaseAdapter {
	
	private Context mcontext;
	private List<ApkItem> mapps;
	public ViewHolder viewholder;
	private int clickTemp = -1;
	ApkItem item ;
		private CachePicturesUtils cacheutils;
	
	//标识选择的Item
	public void setSeclection(int position) {
	     clickTemp = position;
	}


	public AppsAdapterCZL1(Context context, ArrayList<ApkItem> apps) {
		this.mcontext = context;
		this.mapps = apps;
		 cacheutils = new CachePicturesUtils(context);
	}

	@Override
	public int getCount() {
		return mapps.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mapps.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
	
	public void setSelection(int position){
		clickTemp = position;
	}

	@SuppressLint("InflateParams") @Override
	public View getView( int arg4, View arg1, ViewGroup arg2) {
		if (arg1 == null) {
			arg1 = LayoutInflater.from(mcontext).inflate(
					R.layout.item_menu, null);
			viewholder = new ViewHolder();
			viewholder.apkImage = (ImageView) arg1.findViewById(R.id.item_image);
			viewholder.apkName = (ImageView) arg1.findViewById(R.id.item_text);
			arg1.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) arg1.getTag();
		}
	item	= mapps.get(arg4);
			if(item.isIslocked()){
		//	holder.apkImage.setImageResource(item.getApkImage());
		//	holder.apkImage.setBackgroundResource(item.getApkbackgroud());
		    cacheutils.loadBitmapForApk(item.getApkImage(),viewholder.apkImage);
			viewholder.apkImage.setBackgroundResource(item.getApkbackgroud());
		}else{
	//		holder.apkImage.setImageResource(item.getApkImage());
			cacheutils.loadBitmapForApk(item.getApkImage(),viewholder.apkImage);
		}
	//	holder.mimageview.setImageResource(item.getApkImage());
	//	holder.apkName.setImageResource(item.getApkName());
		cacheutils.loadBitmapForApk(item.getApkName(),viewholder.apkName);
		return arg1;
	}
class ViewHolder {
		ImageView apkImage;
		ImageView apkName;
	}
	
}
