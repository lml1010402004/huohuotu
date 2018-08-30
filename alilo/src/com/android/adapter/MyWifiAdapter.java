package com.android.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyWifiAdapter extends BaseAdapter{
	
	private Context context;
	private List<ScanResult> arraylist;
	
   public MyWifiAdapter(Context context,ArrayList<ScanResult> array) {
	// TODO Auto-generated constructor stub
	   this.context = context;
	   this.arraylist = array;
}
	
	
	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		return null;
	}

}
