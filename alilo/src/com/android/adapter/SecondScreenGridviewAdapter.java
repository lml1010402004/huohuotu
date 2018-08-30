package com.android.adapter;

import java.util.List;

import com.android.alilo.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.android.model.ApkItem;

public class SecondScreenGridviewAdapter extends BaseAdapter{
	
	private Context context;
	private List<ApkItem> list;
	LayoutInflater layoutInflater;
//	private ImageView mImageView;
	//private ApkItem item;
	private ViewHolder holder;
	
	public SecondScreenGridviewAdapter(Context context,List<ApkItem> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@SuppressLint("ViewHolder") @Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {

         if(arg1==null){
			arg1 = layoutInflater.inflate(R.layout.item_menu_second_screen, null);
			holder = new ViewHolder();
			holder.image = (ImageView) arg1.findViewById(R.id.item_image);
			holder.title = (ImageView) arg1.findViewById(R.id.item_text);
			arg1.setTag(holder);
		}else{
			holder = (ViewHolder) arg1.getTag();
		}
	ApkItem item = list.get(arg0);
		holder.image.setImageResource(item.getApkImage());
		holder.title.setImageResource(item.getApkName());
		return arg1;

	}

	class ViewHolder {
		
		ImageView image;
		ImageView title;

		
	}

}
