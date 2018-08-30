package com.android.alilo;

/**
 * ������
 * @author wangjx
 *
 */

import java.util.ArrayList;
import java.util.List;
import com.android.adapter.ListViewAdapterLJSW1;
import com.android.customewidget.NestedExpandaleListView;
import com.android.model.ApkItem;
import com.android.myUtils.SharedPreferencesUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import com.android.myUtils.MyApplication;
import android.content.res.TypedArray;

public class TestActivityLJSW3 extends Fragment {
	View view;
	com.android.customewidget.NestedExpandaleListView expandableListView;
//	public String[] groupName = {"逻辑游戏","参观动物园","农庄里的知识","数与数字"};
	private List<String> groupList;// ������Ϣ
	private List<ApkItem> itemGridList1;// ��gridview�б�
	private List<ApkItem> itemGridList2;
	private List<ApkItem> itemGridList3;
	private List<ApkItem> itemGridList0;
	private List<ApkItem> itemGridList4;
	private List<ApkItem> itemGridList5;
	private List<List<ApkItem>> itemList;
	private ListViewAdapterLJSW1 adapter;
    private String[] groupName = null;

		//逻辑游戏
	private int[] menu_toolbar_image_array0 = null;
	private int[] menu_toolbar_image_array00 = null;
		//参观动物园
	private int[] menu_toolbar_image_array1 = null;
	private int[] menu_toolbar_image_array11 = null;
		//农庄里的知识
	private int[] menu_toolbar_image_array2 = null;
	private int[] menu_toolbar_image_array22 = null;
	//数与数字
	private int[] menu_toolbar_image_array3 = null;
	private int[] menu_toolbar_image_array33 = null;


		private void initPics(){
		//逻辑游戏
		 TypedArray array_icons_ljsw_ljyx = getResources().obtainTypedArray(R.array.ljsw56_pic_index_ljyx_title);
		 menu_toolbar_image_array00 = new int[array_icons_ljsw_ljyx.length()];
		 for(int i=0;i<array_icons_ljsw_ljyx.length();i++){
			 menu_toolbar_image_array00[i] = array_icons_ljsw_ljyx.getResourceId(i, R.drawable.apk_locked);
		 }
		 array_icons_ljsw_ljyx.recycle();
		 TypedArray array_titles_ljsw_ljyx = getResources().obtainTypedArray(R.array.ljsw56_pic_index_ljyx_icon);
		 menu_toolbar_image_array0 = new int[array_titles_ljsw_ljyx.length()];
		 for(int ii=0;ii<array_titles_ljsw_ljyx.length();ii++){
			 menu_toolbar_image_array0[ii] = array_titles_ljsw_ljyx.getResourceId(ii, R.drawable.game2_11);
		 }
		 array_titles_ljsw_ljyx.recycle();

		 	//参观动物园
		 TypedArray array_icons_ljsw_cgdwy= getResources().obtainTypedArray(R.array.ljsw56_pic_index_cgdwy_icon);
		 menu_toolbar_image_array1= new int[array_icons_ljsw_cgdwy.length()];
		 for(int i=0;i<array_icons_ljsw_cgdwy.length();i++){
			 menu_toolbar_image_array1[i] = array_icons_ljsw_cgdwy.getResourceId(i, R.drawable.apk_locked);
		 }
		 array_icons_ljsw_cgdwy.recycle();
		 TypedArray array_titles_ljsw_cgdwy = getResources().obtainTypedArray(R.array.ljsw56_pic_index_cgdwy_title);
		 menu_toolbar_image_array11 = new int[array_titles_ljsw_cgdwy.length()];
		 for(int ii=0;ii<array_titles_ljsw_cgdwy.length();ii++){
			 menu_toolbar_image_array11[ii] = array_titles_ljsw_cgdwy.getResourceId(ii, R.drawable.game2_11_title);
		 }
		 array_titles_ljsw_cgdwy.recycle();

		 	 //农庄里的知识
		 TypedArray array_icons_ljsw_nzldzs= getResources().obtainTypedArray(R.array.ljsw56_pic_index_nzldzs_icon);
		 menu_toolbar_image_array2 = new int[array_icons_ljsw_nzldzs.length()];
		 for(int i=0;i<array_icons_ljsw_nzldzs.length();i++){
			 menu_toolbar_image_array2[i] = array_icons_ljsw_nzldzs.getResourceId(i, R.drawable.apk_locked);
		 }
		 array_icons_ljsw_nzldzs.recycle();
		 TypedArray array_titles_ljsw_ngldzs = getResources().obtainTypedArray(R.array.ljsw56_pic_index_nzldzs_title);
		 menu_toolbar_image_array22 = new int[array_titles_ljsw_ngldzs.length()];
		 for(int ii=0;ii<array_titles_ljsw_ngldzs.length();ii++){
			 menu_toolbar_image_array22[ii] = array_titles_ljsw_ngldzs.getResourceId(ii, R.drawable.game2_11_title);
		 }
		 array_titles_ljsw_ngldzs.recycle();
		 //数与数字
		 TypedArray array_icons_ljsw_sysz= getResources().obtainTypedArray(R.array.ljsw56_pic_index_sysz_icon);
		 menu_toolbar_image_array3 = new int[array_icons_ljsw_sysz.length()];
		 for(int i=0;i<array_icons_ljsw_sysz.length();i++){
			 menu_toolbar_image_array3[i] = array_icons_ljsw_sysz.getResourceId(i, R.drawable.apk_locked);
		 }
		 array_icons_ljsw_sysz.recycle();
		 TypedArray array_titles_ljsw_sysz = getResources().obtainTypedArray(R.array.ljsw56_pic_index_sysz_title);
		 menu_toolbar_image_array33 = new int[array_titles_ljsw_sysz.length()];
		 for(int ii=0;ii<array_titles_ljsw_sysz.length();ii++){
			 menu_toolbar_image_array33[ii] = array_titles_ljsw_sysz.getResourceId(ii, R.drawable.game2_11_title);
		 }
		 array_titles_ljsw_sysz.recycle();
		
		
	}


	//逻辑游戏
	//private int menu_toolbar_image_array00[] = { R.drawable.ljsw56_gcxns_title,
	//		R.drawable.ljsw56_djlpp_title, R.drawable.ljsw56_kykpyp_title,
	//		R.drawable.ljsw56_jzldys_title};
	//private int menu_toolbar_image_array0[] = { R.drawable.ljsw56_gcxns_up,
	//		R.drawable.ljsw56_djlpp_up, R.drawable.ljsw56_kykpyp_up, R.drawable.ljsw56_jzldys_up
	//		 };

	
	//参观动物园
	//private int menu_toolbar_image_array11[] = {R.drawable.ljsw56_gdwy_title,
	//		R.drawable.ljsw56_yqddw_title, R.drawable.ljsw56_swfp_title
	//		};
	//private int menu_toolbar_image_array1[] = { R.drawable.ljsw56_gdwy_up,
	//		R.drawable.ljsw56_yqddw_up, R.drawable.ljsw56_swfp_up };

	
	//农庄里的知识
	//@SuppressWarnings("unused")
	//private int menu_toolbar_image_array22[] = { R.drawable.game_14_title,
	//		R.drawable.game_15_title, R.drawable.game_16_title,
	//		R.drawable.game_19_title };
	//private int menu_toolbar_image_array2[] = { R.drawable.game_14,
	//		R.drawable.game_15, R.drawable.game_16, R.drawable.game_19 };

	//数与数字
	//private int menu_toolbar_image_array33[] = { R.drawable.game_14_title,
	//		R.drawable.game_15_title, R.drawable.game_16_title,
	//		R.drawable.game_19_title };
	//private int menu_toolbar_image_array3[] = { R.drawable.game_14,
	//		R.drawable.game_15, R.drawable.game_16, R.drawable.game_19 };

//	private int menu_toolbar_image_array44[] = { R.drawable.game_14_title,
//			R.drawable.game_15_title, R.drawable.game_16_title,
//			R.drawable.game_19_title };
//	private int menu_toolbar_image_array4[] = { R.drawable.game_14,
//			R.drawable.game_15, R.drawable.game_16, R.drawable.game_19 };
//
//	private int menu_toolbar_image_array55[] = { R.drawable.game_14_title,
//			R.drawable.game_15_title, R.drawable.game_16_title,
//			R.drawable.game_19_title };
//	private int menu_toolbar_image_array5[] = { R.drawable.game_14,
//			R.drawable.game_15, R.drawable.game_16, R.drawable.game_19 };

	public TestActivityLJSW3() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.test_activityljsw3, container, false);
		init();
		return view;
	}

	void init() {
         initPics();

//initing the string arrays of groupname.
		groupName = getResources().getStringArray(R.array.ljsw56);
		expandableListView = (NestedExpandaleListView) view
				.findViewById(R.id.expandableListView);
		expandableListView.setGroupIndicator(null);
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1,
					int arg2, long arg3) {
				return true;
			}
		});

		groupList = new ArrayList<>();
		for (int i = 0; i < groupName.length; i++) {
			groupList.add(groupName[i]);
		}

		itemGridList0 = new ArrayList<>();
		itemGridList1 = new ArrayList<>();
		itemGridList2 = new ArrayList<>();
		itemGridList3 = new ArrayList<>();
		itemGridList4 = new ArrayList<>();
		itemGridList5 = new ArrayList<>();
		
		
		restoreData();//ˢ�¹���UI������
		

		itemList = new ArrayList<>();
		itemList.add(itemGridList0);
		itemList.add(itemGridList1);
		itemList.add(itemGridList2);
		itemList.add(itemGridList3);
		itemList.add(itemGridList4);
		itemList.add(itemGridList5);

		adapter = new ListViewAdapterLJSW1(getActivity(),
				groupList, itemList,3);
		expandableListView.setAdapter(adapter);
		for (int i = 0; i < adapter.getGroupCount(); i++) {
			expandableListView.expandGroup(i);
		}
	}
	
	//
	private void restoreData(){
		ApkItem item = null;
//逻辑游戏
		int array0 = SharedPreferencesUtil.getPassApkCountForLJSW56LJYX(getActivity());

		for (int j=0;j<array0;j++) {
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array0[j]);
			item.setApkName(menu_toolbar_image_array00[j]);
			item.setIslocked(false);
			itemGridList0.add(item);
		}
		for (int jj = array0; jj < menu_toolbar_image_array00.length; jj++) {
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array0[jj]);
			item.setApkName(menu_toolbar_image_array00[jj]);
			item.setIslocked(true);
			itemGridList0.add(item);
		}

		//参观动物园
		int array1 = SharedPreferencesUtil.getPassApkCountForLJSW56ZDWY(getActivity());
		for (int k=0;k<array1;k++) {
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array1[k]);
			item.setApkName(menu_toolbar_image_array11[k]);
			item.setIslocked(false);
			itemGridList1.add(item);
		}
		for (int kk = array1; kk < menu_toolbar_image_array11.length; kk++) {
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
				item.setApkbackgroud(menu_toolbar_image_array1[kk]);
			item.setApkName(menu_toolbar_image_array11[kk]);
			item.setIslocked(true);
			itemGridList1.add(item);
		}
//		for (int m = 0; m < menu_toolbar_image_array44.length; m++) {
//			item = new ApkItem();
//			item.setApkImage(menu_toolbar_image_array4[m]);
//			item.setApkName(menu_toolbar_image_array44[m]);
//			itemGridList4.add(item);
//		}
//		for (int m = 0; m < menu_toolbar_image_array55.length; m++) {
//			item = new ApkItem();
//			item.setApkImage(menu_toolbar_image_array5[m]);
//			item.setApkName(menu_toolbar_image_array55[m]);
//			itemGridList5.add(item);
//		}

//农庄里的知识

//农庄里的知识
		int array2 = SharedPreferencesUtil.getPassApkCountForLJSW56ZNZL(getActivity());
		for(int m = 0 ;m<array2;m++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array2[m]);
			item.setApkName(menu_toolbar_image_array22[m]);
			item.setIslocked(false);
			itemGridList2.add(item);
		}
		for (int mm = array2; mm < menu_toolbar_image_array22.length; mm++) {
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
				item.setApkbackgroud(menu_toolbar_image_array2[mm]);
			item.setApkName(menu_toolbar_image_array22[mm]);
			item.setIslocked(true);
			itemGridList2.add(item);
		}

	//数与数字
		int array3 = SharedPreferencesUtil.getPassApkCountForLJSW56SYSZ(getActivity());
		for(int n = 0;n<array3;n++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array3[n]);
			item.setApkName(menu_toolbar_image_array33[n]);
			item.setIslocked(false);
			itemGridList3.add(item);
		}
		for (int nn = array3; nn < menu_toolbar_image_array33.length; nn++) {
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
				item.setApkbackgroud(menu_toolbar_image_array3[nn]);
			item.setApkName(menu_toolbar_image_array33[nn]);
			item.setIslocked(true);
			itemGridList3.add(item);
		}


	}
	
	
	
	@Override
	public void onResume() {
			super.onResume();
		if(adapter!=null){
			itemGridList0.clear();
			itemGridList1.clear();
			itemGridList2.clear();
			itemGridList3.clear();
			itemGridList4.clear();
			itemGridList5.clear();
			restoreData();
		adapter.notifyDataSetChanged();
		}
	}
	
}
