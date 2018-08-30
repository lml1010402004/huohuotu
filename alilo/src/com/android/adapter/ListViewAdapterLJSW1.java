package com.android.adapter;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.renderscript.Type;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.android.alilo.R;
import com.android.model.ApkItem;
import com.android.myUtils.Utils;
import com.android.alilo.HomePageActivity;

/**
 * �����Ƕ�BaseExpandableAdapter��������ȫ���������ʹ�á�
 * 
 * @author yitoa Jack
 */
public class ListViewAdapterLJSW1 extends BaseExpandableListAdapter {
	private Context mContext;
	public static final int ItemHeight = 40;// ÿ��ĸ߶�
	//public static final int PaddingLeft = 36;// ÿ��ĸ߶�
	private int age_group = 0;
    private	MyGridViewAdapter gridViewAdapter;


	/**
	 * ÿ����������ֵļ���
	 */
	private List<String> groupList;

	/**
	 * ���з������������� GridView ���ݼ���
	 */
	private List<List<ApkItem>> itemList;

	private GridView gridView;

	public ListViewAdapterLJSW1(Context context, List<String> groupList,
			List<List<ApkItem>> itemList, int age_group) {
		mContext = context;
		this.groupList = groupList;
		this.itemList = itemList;
		this.age_group = age_group;
	}

	@Override
	public int getGroupCount() {
		return groupList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupList.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return itemList.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@SuppressLint("ResourceAsColor") @Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView textView = getTextView(this.mContext);
		textView.setBackgroundResource(R.drawable.ljsw_theme_title);
		textView.setTextSize(20);
	//	textView.setHeight(20);
		textView.setTypeface(Typeface.SANS_SERIF);
		textView.setText(getGroup(groupPosition).toString());
		textView.setPadding(2, 0, 0, 0);
		textView.setTextColor(mContext.getResources().getColor(R.color.themetitlecolor));
		textView.setLeft(3);
	    textView.setTypeface(HomePageActivity.face);
		return textView;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (null == convertView) {
			convertView = View.inflate(mContext, R.layout.expandablelist_item,
					null);
		}
		// ��Ϊ convertView �Ĳ��־���һ�� GridView��
		// ���Կ�������ת��Ϊ GridView
		gridView = (GridView) convertView;
		// ���� GridView ������
		gridViewAdapter = new MyGridViewAdapter(mContext,
				itemList.get(groupPosition),groupPosition);
		gridView.setAdapter(gridViewAdapter);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ApkItem item =itemList.get(groupPosition).get(position);
				boolean flag = item.isIslocked();
			if (age_group == 1) {
					Utils.clickTheTargetApk34(mContext, groupPosition, position,flag);
				} else if (age_group == 2) {
					Utils.clickTheTargetApk45(mContext, groupPosition, position,flag);
				} else if(age_group==3) {
					Utils.clickTheTargetApk56(mContext, groupPosition, position,flag);
				}else if(age_group==4){
					Utils.clickTheTargetApkSXKJ(mContext, groupPosition, position, flag);    
				
				}
			}
		});
		return convertView;
	}
	
	
	
	public static boolean flag = false;

	static public TextView getTextView(Context context) {
		@SuppressWarnings("deprecation")
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, ItemHeight);
		TextView textView = new TextView(context);
		textView.setLayoutParams(lp);
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		return textView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}