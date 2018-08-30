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
import com.android.myUtils.MyApplication;
import com.android.myUtils.SharedPreferencesUtil;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.content.res.TypedArray;
import android.content.IntentFilter;

public class TestActivityLJSW1 extends Fragment {
	
	View view;
	com.android.customewidget.NestedExpandaleListView expandableListView;
	ListViewAdapterLJSW1 treeViewAdapter;
	private List<String> groupList;// ������Ϣ
	private List<ApkItem> itemGridList1;// ��gridview�б�
	private List<ApkItem> itemGridList2;
	private List<ApkItem> itemGridList3;
	private List<ApkItem> itemGridList0;
	private List<List<ApkItem>> itemList;
	ListViewAdapterLJSW1 adapter;
    //�����ַ���Դ��÷�����Դ�ļ����档
	//public String[] groupsName = {"色彩与形状","火火兔的一天","我们来画,贴,建图案","数数与比较"};

	public String[] groupsName = null;

		
	private void initStringsOfLjsw1(){
		groupsName = getResources().getStringArray(R.array.ljsw34);
	}

//	private int menu_toolbar_image_array00[] = { R.drawable.game_1_title,
//			R.drawable.game_2_title, R.drawable.game_3_title,
//			R.drawable.game_4_title, R.drawable.game_5_title,
//			R.drawable.game_6_title };
//private int menu_toolbar_image_array0[] = { R.drawable.game_1,
//			R.drawable.game_2, R.drawable.game_3, R.drawable.game_4,
//			R.drawable.game_5, R.drawable.game_6 };

     //色彩与形状
	private int[] menu_toolbar_image_array00 = null;
	private int[] menu_toolbar_image_array0 = null;

	//火火兔的一天
	private int[] menu_toolbar_image_array11 = null;
	private int[] menu_toolbar_image_array1 = null;

	//我们画，贴，建
	private int[] menu_toolbar_image_array2 = null;
	private int[] menu_toolbar_image_array22 = null;

		//数数与比较
    private int[] menu_toolbar_image_array3 = null;
    private int[] menu_toolbar_image_array33 = null;


	//private int[] arrays;
	private void initPicsIndexOfLjsw1(){
		
		//色彩与形状 
		 TypedArray array_icons_ljsw_scyxz = getResources().obtainTypedArray(R.array.ljsw34_pic_index_scyxz_title);
		 menu_toolbar_image_array00 = new int[array_icons_ljsw_scyxz.length()];
		 for(int i=0;i<array_icons_ljsw_scyxz.length();i++){
			 menu_toolbar_image_array00[i] = array_icons_ljsw_scyxz.getResourceId(i, R.drawable.apk_locked);
		 }
		 array_icons_ljsw_scyxz.recycle();
        TypedArray array_titles_ljsw_scyxz = getResources().obtainTypedArray(R.array.ljsw34_pic_index_scyxz_icon);
		 menu_toolbar_image_array0 = new int[array_titles_ljsw_scyxz.length()];
		 for(int ii=0;ii<array_titles_ljsw_scyxz.length();ii++){
			 menu_toolbar_image_array0[ii] = array_titles_ljsw_scyxz.getResourceId(ii, R.drawable.game2_11_title);
		 }
		 array_titles_ljsw_scyxz.recycle();

		 	 //火火兔的一天
		 TypedArray array_icons_ljsw_hhtdyt = getResources().obtainTypedArray(R.array.ljsw34_pic_index_hhtdyt_icon);
		 menu_toolbar_image_array1 = new int[array_icons_ljsw_hhtdyt.length()];
		 for(int j=0;j<array_icons_ljsw_hhtdyt.length();j++){
			 menu_toolbar_image_array1[j] = array_icons_ljsw_hhtdyt.getResourceId(j, R.drawable.game2_1);
		 }
		 array_icons_ljsw_hhtdyt.recycle();
		 TypedArray array_titles_ljsw_hhtdyt = getResources().obtainTypedArray(R.array.ljsw34_pic_index_hhtdyt_title);
		 menu_toolbar_image_array11 = new int[array_titles_ljsw_hhtdyt.length()];
		 for(int jj=0;jj<array_icons_ljsw_hhtdyt.length();jj++ ){
			 menu_toolbar_image_array11[jj] = array_titles_ljsw_hhtdyt.getResourceId(jj, R.drawable.game2_1_title);
		 }
		 array_titles_ljsw_hhtdyt.recycle();

		  //我们画，我们贴，我们建
		 TypedArray array_titles_ljsw_wmhtj = getResources().obtainTypedArray(R.array.ljsw34_pic_index_wmhtj_icon);
		 menu_toolbar_image_array2 = new int[array_titles_ljsw_wmhtj.length()];
		 for(int k = 0;k<array_titles_ljsw_wmhtj.length();k++){
			 menu_toolbar_image_array2[k] = array_titles_ljsw_wmhtj.getResourceId(k, R.drawable.game2_1_title);
		 }
		 array_titles_ljsw_wmhtj.recycle();
		 TypedArray array_icon_ljsw_wmhtj = getResources().obtainTypedArray(R.array.ljsw34_pic_index_wmhtj_title);
		 menu_toolbar_image_array22 = new int[array_icon_ljsw_wmhtj.length()];
		 for(int kk=0;kk<array_icon_ljsw_wmhtj.length();kk++){
			 menu_toolbar_image_array22[kk] = array_icon_ljsw_wmhtj.getResourceId(kk, R.drawable.game2_1);
		 }
		 array_icon_ljsw_wmhtj.recycle();

		  //数数与比较
		 TypedArray array_titles_ljsw_ssybj = getResources().obtainTypedArray(R.array.ljsw34_pic_index_ssybj_icon);
		 menu_toolbar_image_array3 = new int[array_titles_ljsw_wmhtj.length()];
		 for(int h = 0;h<array_titles_ljsw_ssybj.length();h++){
			 menu_toolbar_image_array3[h] = array_titles_ljsw_wmhtj.getResourceId(h, R.drawable.game2_1_title);
		 }
		 array_titles_ljsw_wmhtj.recycle();
		 TypedArray array_icon_ljsw_ssybj = getResources().obtainTypedArray(R.array.ljsw34_pic_index_ssybj_title);
		 menu_toolbar_image_array33 = new int[array_icon_ljsw_wmhtj.length()];
		 for(int hh=0;hh<array_icon_ljsw_ssybj.length();hh++){
			 menu_toolbar_image_array33[hh] = array_icon_ljsw_wmhtj.getResourceId(hh, R.drawable.game2_1);
		 }
		 array_icon_ljsw_wmhtj.recycle();

		  
	}
	
	

	//private int menu_toolbar_image_array11[] = { R.drawable.game_7_title,
	//		R.drawable.game_8_title, R.drawable.game_9_title,
	//		R.drawable.game_10_title, R.drawable.game_11_title };
	//private int menu_toolbar_image_array1[] = { R.drawable.game_7,
	//		R.drawable.game_8, R.drawable.game_9, R.drawable.game_10,
	//		R.drawable.game_11 };
	
	
	
//我们画，我们贴，我们建
//	private int menu_toolbar_image_array22[] = { R.drawable.game_13_title,
//			R.drawable.game_14_title, R.drawable.game_15_title,
//	R.drawable.game_16_title,R.drawable.game_37_title};
//	private int menu_toolbar_image_array2[] = { R.drawable.game_13,
//			R.drawable.game_14, R.drawable.game_15, R.drawable.game_16,R.drawable.ljsw_fjply_up };
	
	
	
	
//数数与比较
//	private int menu_toolbar_image_array33[] = { R.drawable.game_19_title,
//			R.drawable.game_20_title, R.drawable.game_21_title,
//			R.drawable.game_22_title, R.drawable.game_23_title,
//			R.drawable.game_24_title };
//	private int menu_toolbar_image_array3[] = { R.drawable.game_19,
//			R.drawable.game_20, R.drawable.game_21, R.drawable.game_22,
//			R.drawable.game_23, R.drawable.game_24 };
	
	
	
	
	
	public TestActivityLJSW1() {
		// Required empty public constructor
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	IntentFilter intentFilter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.test_activityljsw1, container, false);
		expandableListView = (NestedExpandaleListView) view
				.findViewById(R.id.expandableListView);
		expandableListView.setGroupIndicator(null);
		
		//===========================
		//make the group can not expandable.
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2,
					long arg3) {
				return true;
			}
		});
		//===========================
		initData();
    	
		return view;
	}

	void initData() {
		initStringsOfLjsw1();
		initPicsIndexOfLjsw1();
		groupList = new ArrayList<>();
		for (int i = 0; i < groupsName.length; i++) {
			groupList.add(groupsName[i]);
		}

		itemGridList0 = null;
		itemGridList1 = null;
		itemGridList2 = null;
		itemGridList3 = null;
		itemList = null;



		itemGridList0 = new ArrayList<>();
		itemGridList1 = new ArrayList<>();
		itemGridList2 = new ArrayList<>();
		itemGridList3 = new ArrayList<>();
		ApkItem item = null;
		
		//==============array0 indicates the apks which are already passed .<SCYXZ>
		int array0 = SharedPreferencesUtil.getPassApkCountForLJSW34SCYXZ(getActivity());
		for(int j=0;j<array0;j++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array0[j]);
			item.setApkName(menu_toolbar_image_array00[j]);
			item.setIslocked(false);
			itemGridList0.add(item);
		}
		for(int jj = array0;jj<menu_toolbar_image_array00.length;jj++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
		    item.setApkbackgroud(menu_toolbar_image_array0[jj]);
			item.setApkName(menu_toolbar_image_array00[jj]);
			item.setIslocked(true);
			itemGridList0.add(item);
		}
		//===============array111 indicates the apk passed .<LSYTM>
		int array111 = SharedPreferencesUtil.getPassApkCountForLJSW34LSYTM(getActivity());
		for (int k = 0; k < array111; k++) {
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array1[k]);
			item.setApkName(menu_toolbar_image_array11[k]);
			item.setIslocked(false);
			itemGridList1.add(item);
		}
		
		for(int kk=array111;kk<menu_toolbar_image_array11.length;kk++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkbackgroud(menu_toolbar_image_array1[kk]);
			item.setApkName(menu_toolbar_image_array11[kk]);
			item.setIslocked(true);
			itemGridList1.add(item);
		}

		//=====================array222 represents the apks passed .<HTJ>
		int array222 = SharedPreferencesUtil.getPassApkCountForLJSW34HTJ(getActivity());
		for(int m =0;m<array222;m++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array2[m]);
			item.setApkName(menu_toolbar_image_array22[m]);
			item.setIslocked(false);
			itemGridList2.add(item);
		}
		
		for(int mm = array222;mm<menu_toolbar_image_array22.length;mm++ ){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkbackgroud(menu_toolbar_image_array2[mm]);
			item.setApkName(menu_toolbar_image_array22[mm]);
			item.setIslocked(true);
			itemGridList2.add(item);
		}
		//=============================array333 represents the count of passed apk.<array333>
	    int array333 = SharedPreferencesUtil.getPassApkCountForLJSW34SSYBJ(getActivity());
	    for(int n = 0;n<array333;n++){
	    	item = new ApkItem();
	    	item.setApkImage(menu_toolbar_image_array3[n]);
	    	item.setApkName(menu_toolbar_image_array33[n]);
	    	item.setIslocked(false);
	    	itemGridList3.add(item);
	    }
	    for(int nn=array333;nn<menu_toolbar_image_array33.length;nn++){
	    	item = new ApkItem();
	    	item.setApkImage(MyApplication.apk_locked);
			item.setApkbackgroud(menu_toolbar_image_array3[nn]);
	    	item.setApkName(menu_toolbar_image_array33[nn]);
	    	item.setIslocked(true);
	    	itemGridList3.add(item);
	    }
	    //========= intergrate the whole apk resource together.
		itemList = new ArrayList<>();
		itemList.add(itemGridList0);
		itemList.add(itemGridList1);
		itemList.add(itemGridList2);
		itemList.add(itemGridList3);
		adapter = new ListViewAdapterLJSW1(getActivity(),
				groupList, itemList,1);
		expandableListView.setAdapter(adapter);
		for (int i = 0; i < adapter.getGroupCount(); i++) {
			expandableListView.expandGroup(i);
		}
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		initData();
		if(adapter!=null){
			adapter.notifyDataSetChanged();
		}
	}
}
