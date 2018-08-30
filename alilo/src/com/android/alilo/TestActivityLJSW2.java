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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.content.res.TypedArray;

public class TestActivityLJSW2 extends Fragment {
	View view;

	com.android.customewidget.NestedExpandaleListView expandableListView;
	//public String[] groupName = {"交通常识","养宠物,学思考","观察与联想","观察与注意","在儿童活动室"};
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

//交通常识
	private int[] menu_toolbar_image_array00 = null;
	private int[] menu_toolbar_image_array0 = null;
		//养宠物，学思考
	private int[] menu_toolbar_image_array1 = null;
	private int[] menu_toolbar_image_array11 = null;
		//观察与联想
	private int[] menu_toolbar_image_array2 = null;
	private int[] menu_toolbar_image_array22 = null;
	//观察与注意
	private int[] menu_toolbar_image_array3 = null;
	private int[] menu_toolbar_image_array33 = null;

		private void intPicsData(){
		//交通常识
		TypedArray array_icons_ljsw_jtcs = getResources().obtainTypedArray(R.array.ljsw45_pic_index_jtcs_title);
		 menu_toolbar_image_array00 = new int[array_icons_ljsw_jtcs.length()];
		 for(int i=0;i<array_icons_ljsw_jtcs.length();i++){
			 menu_toolbar_image_array00[i] = array_icons_ljsw_jtcs.getResourceId(i, R.drawable.game2_10_title);
		 }
		 array_icons_ljsw_jtcs.recycle();
		 TypedArray array_titles_ljsw_jtcs = getResources().obtainTypedArray(R.array.ljsw45_pic_index_jtcs_icon);
		 menu_toolbar_image_array0 = new int[array_titles_ljsw_jtcs.length()];
		 for(int ii=0;ii<array_titles_ljsw_jtcs.length();ii++){
			 menu_toolbar_image_array0[ii] = array_titles_ljsw_jtcs.getResourceId(ii, R.drawable.apk_locked);
		 }
		 array_titles_ljsw_jtcs.recycle();

		  //养宠物，学思考
			TypedArray array_icons_ljsw_ycwxsk = getResources().obtainTypedArray(R.array.ljsw45_pic_index_ycwxsk_title);
			 menu_toolbar_image_array11 = new int[array_icons_ljsw_ycwxsk.length()];
			 for(int j=0;j<array_icons_ljsw_ycwxsk.length();j++){
				 menu_toolbar_image_array11[j] = array_icons_ljsw_ycwxsk.getResourceId(j, R.drawable.game2_10_title);
			 }
			 array_icons_ljsw_ycwxsk.recycle();
			 TypedArray array_titles_ljsw_ycwxsk = getResources().obtainTypedArray(R.array.ljsw45_pic_index_ycwxsk_icon);
			 menu_toolbar_image_array1 = new int[array_titles_ljsw_ycwxsk.length()];
			 for(int jj=0;jj<array_titles_ljsw_ycwxsk.length();jj++){
				 menu_toolbar_image_array1[jj] = array_titles_ljsw_ycwxsk.getResourceId(jj, R.drawable.apk_locked);
			 }
			 array_titles_ljsw_ycwxsk.recycle();

			 	 //观察与联想 
				TypedArray array_icons_ljsw_gcylx = getResources().obtainTypedArray(R.array.ljsw45_pic_index_gcylx_title);
				 menu_toolbar_image_array22= new int[array_icons_ljsw_gcylx.length()];
				 for(int j=0;j<array_icons_ljsw_gcylx.length();j++){
					 menu_toolbar_image_array22[j] = array_icons_ljsw_gcylx.getResourceId(j, R.drawable.game2_10_title);
				 }
				 array_icons_ljsw_gcylx.recycle();
				 TypedArray array_titles_ljsw_gcylx = getResources().obtainTypedArray(R.array.ljsw45_pic_index_gcylx_icon);
				 menu_toolbar_image_array2= new int[array_titles_ljsw_gcylx.length()];
				 for(int jj=0;jj<array_titles_ljsw_gcylx.length();jj++){
					 menu_toolbar_image_array2[jj] = array_titles_ljsw_gcylx.getResourceId(jj, R.drawable.apk_locked);
				 }
				 array_titles_ljsw_gcylx.recycle();
				 	   //观察与注意
					TypedArray array_icons_ljsw_gcyzy = getResources().obtainTypedArray(R.array.ljsw45_pic_index_gcyzy_title);
					 menu_toolbar_image_array33 = new int[array_icons_ljsw_gcyzy.length()];
					 for(int j=0;j<array_icons_ljsw_gcyzy.length();j++){
						 menu_toolbar_image_array33[j] = array_icons_ljsw_gcyzy.getResourceId(j, R.drawable.game2_10_title);
					 }
					 array_icons_ljsw_gcyzy.recycle();
					 TypedArray array_titles_ljsw_gcyzy = getResources().obtainTypedArray(R.array.ljsw45_pic_index_gcyzy_icon);
					 menu_toolbar_image_array3= new int[array_titles_ljsw_gcyzy.length()];
					 for(int jj=0;jj<array_titles_ljsw_gcyzy.length();jj++){
						 menu_toolbar_image_array3[jj] = array_titles_ljsw_gcyzy.getResourceId(jj, R.drawable.apk_locked);
					 }
					 array_titles_ljsw_gcyzy.recycle();
		 
	}
	
	//=========交通常识============
//	private int menu_toolbar_image_array00[] = { R.drawable.game2_1_title,
//			R.drawable.game2_2_title, R.drawable.game2_3_title,
//			R.drawable.game2_4_title, R.drawable.game2_5_title };
//	private int menu_toolbar_image_array0[] = { R.drawable.game2_1,
//			R.drawable.game2_2, R.drawable.game2_3, R.drawable.game2_4,
//			R.drawable.game2_5 };
	
//=======养宠物，学思考========
//	private int menu_toolbar_image_array11[] = { R.drawable.game2_7_title,
//			R.drawable.game2_8_title, R.drawable.game2_9_title,
//			R.drawable.game2_7_title, R.drawable.game2_11_title };//这里有个图片重复
//	private int menu_toolbar_image_array1[] = { R.drawable.game2_7,
//			R.drawable.game2_8, R.drawable.game2_9, R.drawable.game2_10,
//			R.drawable.game2_11 };
	
//=======观察与联想======
//	private int menu_toolbar_image_array22[] = { R.drawable.game2_13_title,
//			R.drawable.game2_14_title, R.drawable.game2_15_title,
	//		R.drawable.game2_16_title };
	//private int menu_toolbar_image_array2[] = { R.drawable.game2_13,
	//		R.drawable.game2_14, R.drawable.game2_15, R.drawable.game2_16 };
	
	//=============观察与注意==========这里需要替换图片20170526

	//private int menu_toolbar_image_array33[] = { R.drawable.ljsw_ylcdyt_title,
	//		R.drawable.ljsw_yqdylc_title, R.drawable.ljsw_gcts_title,
	//		R.drawable.ljsw_klxtys_title };
	//private int menu_toolbar_image_array3[] = {R.drawable.ljsw_ylcdyt_up,
	//		R.drawable.ljsw_yqdylc_up, R.drawable.ljsw_gcts_up, R.drawable.ljsw_srjh_up };
	
	
//在儿童活动室
	private int menu_toolbar_image_array44[] = { R.drawable.ljsw45_wjzzk_title,R.drawable.ljsw45_dydtx_title,R.drawable.ljsw45_txlts_title
			,R.drawable.ljsw45_jmwg_title,R.drawable.ljsw45_wlxdp_title };
	private int menu_toolbar_image_array4[] = { R.drawable.ljsw45_wjzzk_up,R.drawable.ljsw45_dydtx_up,R.drawable.ljsw45_txlts_up,R.drawable.ljsw45_jmwg_up
			,R.drawable.ljsw45_wlxdp_up};
	
	

//	private int menu_toolbar_image_array55[] = { R.drawable.game2_13_title
//	 };
//	private int menu_toolbar_image_array5[] = { R.drawable.game2_13 };

	public TestActivityLJSW2() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.test_activityljsw2, container, false);
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
		
		initData();
		return view;
	}

	void initData() {
		intPicsData();
		groupName = getResources().getStringArray(R.array.ljsw45);
		groupList = new ArrayList<>();
		for (int i = 0; i < groupName.length; i++) {
			groupList.add(groupName[i]);
		}

        itemGridList0 = null;
		itemGridList1 = null;
		itemGridList2 = null;
		itemGridList3 = null;
		itemGridList4 = null;
		itemGridList5 = null;
		itemList = null;

		
		itemGridList0 = new ArrayList<>();
		itemGridList1 = new ArrayList<>();
		itemGridList2 = new ArrayList<>();
		itemGridList3 = new ArrayList<>();
		itemGridList4 = new ArrayList<>();
		itemGridList5 = new ArrayList<>();
		
		ApkItem item = null;
		
		//=================== passed1 represents the passed apk .
		int passed0 = SharedPreferencesUtil.getPassApkCountForLJSW45JTCS1(getActivity());
		if(passed0==-1){
			passed0 = MyApplication.default_passed_apk_ljsw45_jtcs1;
		}
		for(int j = 0;j<passed0;j++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array0[j]);
			item.setApkName(menu_toolbar_image_array00[j]);
			item.setIslocked(false);
			itemGridList0.add(item);
		}
		for(int jj =passed0;jj<menu_toolbar_image_array00.length;jj++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkbackgroud(menu_toolbar_image_array0[jj]);
			item.setApkName(menu_toolbar_image_array00[jj]);
			item.setIslocked(true);
			itemGridList0.add(item);
		}
		//=================================
		
	    //=============passed1 represents the passed apk .<WMDCW>
		int passed1 = SharedPreferencesUtil.getPassApkCountForLJSW45WMDCW(getActivity());
		if(passed1==-1){
			passed1 = 0;
		}
		for(int k = 0;k<passed1;k++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array1[k]);
			item.setApkName(menu_toolbar_image_array11[k]);
			item.setIslocked(false);
			itemGridList1.add(item);
		}
		for(int kk=passed1;kk<menu_toolbar_image_array11.length;kk++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkbackgroud(menu_toolbar_image_array1[kk]);
			item.setApkName(menu_toolbar_image_array11[kk]);
			item.setIslocked(true);
			itemGridList1.add(item);
		}
		
		//=================passed2 reprents the count of passed apk.<GCYLX>
		int passed2 = SharedPreferencesUtil.getPassApkCountForLJSW45GCYLX(getActivity());
		if(passed2==-1){
			passed2 = 0;
		}
		for(int m = 0;m<passed2;m++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array2[m]);
			item.setApkName(menu_toolbar_image_array22[m]);
			item.setIslocked(false);
			itemGridList2.add(item);
			}
		
		for(int mm=passed2;mm<menu_toolbar_image_array22.length;mm++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkbackgroud(menu_toolbar_image_array2[mm]);
			item.setApkName(menu_toolbar_image_array22[mm]);
			item.setIslocked(true);
			itemGridList2.add(item);
		}
		
		
		//==================passed3 indicates the passed apk of GCYZY
		int passed3 = SharedPreferencesUtil.getPassApkCountForLJSW45GCYZY(getActivity());
		if(passed3==-1){
			passed3 = 0;
		}
		for(int n = 0;n<passed3;n++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array3[n]);
			item.setApkName(menu_toolbar_image_array33[n]);
			item.setIslocked(false);
			itemGridList3.add(item);
		}
		for(int nn=passed3;nn<menu_toolbar_image_array33.length;nn++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkbackgroud(menu_toolbar_image_array3[nn]);
			item.setApkName(menu_toolbar_image_array33[nn]);
			item.setIslocked(true);
			itemGridList3.add(item);
 		}
		
		
		//========== passed4 represents the passed apks of <HHTZETS>
		int passed4 = SharedPreferencesUtil.getPassApkCountForLJSW45HHTZETS(getActivity());
		if(passed4==-1){
			passed4 = 0;
		}
		for(int x = 0;x<passed4;x++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array4[x]);
			item.setApkName(menu_toolbar_image_array44[x]);
			item.setIslocked(false);
			itemGridList4.add(item);
		}
		for(int xx=passed4;xx< menu_toolbar_image_array44.length;xx++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkbackgroud(menu_toolbar_image_array4[xx]);
			item.setApkName(menu_toolbar_image_array44[xx]);
			item.setIslocked(true);
			itemGridList4.add(item);
		}
		
		//================ passed5 represents the passed apk of WMJZMCY.
	//	int passed5 = SharedPreferencesUtil.getPassApkCountForLJSW45WMJZMCY(getActivity());
	//	if(passed5==-1){
	//		passed5= 0;
	//	}
//		for(int y = 0;y<passed5;y++ ){
//			item = new ApkItem();
//			item.setApkImage(menu_toolbar_image_array5[y]);
//			item.setApkName(menu_toolbar_image_array55[y]);
//			item.setIslocked(false);
//			itemGridList5.add(item);
//		}
//		for(int yy=passed5;yy<menu_toolbar_image_array55.length;yy++){
//			item = new ApkItem();
//			item.setApkImage(MyApplication.apk_locked);
//			item.setApkName(menu_toolbar_image_array55[yy]);
//			item.setIslocked(true);
//			itemGridList5.add(item);
//		}
		
		itemList = new ArrayList<>();
		
		itemList.add(itemGridList0);
		itemList.add(itemGridList1);
		itemList.add(itemGridList2);
		itemList.add(itemGridList3);
		itemList.add(itemGridList4);
		//itemList.add(itemGridList5);
		
		adapter = new ListViewAdapterLJSW1(getActivity(),
				groupList, itemList,2);
		expandableListView.setAdapter(adapter);
		for (int i = 0; i < adapter.getGroupCount(); i++) {
			expandableListView.expandGroup(i);
		}
		
	}
	
	/**
	 * refresh UI data.
	 */
	/*private void resotreUIData(){
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
		
		ApkItem item = null;
		
		//=================== passed1 represents the passed apk .
		int passed0 = SharedPreferencesUtil.getPassApkCountForLJSW45JTCS1(getActivity());
		if(passed0==-1){
			passed0 = MyApplication.default_passed_apk_ljsw45_jtcs1;
		}
		for(int j = 0;j<passed0;j++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array0[j]);
			item.setApkName(menu_toolbar_image_array00[j]);
			item.setIslocked(false);
			itemGridList0.add(item);
		}
		for(int jj =passed0;jj<menu_toolbar_image_array00.length;jj++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkName(menu_toolbar_image_array00[jj]);
			item.setIslocked(true);
			itemGridList0.add(item);
		}
		//=================================
		
	    //=============passed1 represents the passed apk .<WMDCW>
		int passed1 = SharedPreferencesUtil.getPassApkCountForLJSW45WMDCW(getActivity());
		if(passed1==-1){
			passed1 = 0;
		}
		for(int k = 0;k<passed1;k++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array1[k]);
			item.setApkName(menu_toolbar_image_array11[k]);
			item.setIslocked(false);
			itemGridList1.add(item);
		}
		for(int kk=passed1;kk<menu_toolbar_image_array11.length;kk++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkName(menu_toolbar_image_array11[kk]);
			item.setIslocked(true);
			itemGridList1.add(item);
		}
		
		//=================passed2 reprents the count of passed apk.<GCYLX>
		int passed2 = SharedPreferencesUtil.getPassApkCountForLJSW45GCYLX(getActivity());
		if(passed2==-1){
			passed2 = 0;
		}
		for(int m = 0;m<passed2;m++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array2[m]);
			item.setApkName(menu_toolbar_image_array22[m]);
			item.setIslocked(false);
			itemGridList2.add(item);
			}
		
		for(int mm=passed2;mm<menu_toolbar_image_array22.length;mm++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkName(menu_toolbar_image_array22[mm]);
			item.setIslocked(true);
			itemGridList2.add(item);
		}
		
		
		//==================passed3 indicates the passed apk of GCYZY
		int passed3 = SharedPreferencesUtil.getPassApkCountForLJSW45GCYZY(getActivity());
		if(passed3==-1){
			passed3 = 0;
		}
		for(int n = 0;n<passed3;n++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array3[n]);
			item.setApkName(menu_toolbar_image_array33[n]);
			item.setIslocked(false);
			itemGridList3.add(item);
		}
		for(int nn=passed3;nn<menu_toolbar_image_array33.length;nn++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkName(menu_toolbar_image_array33[nn]);
			item.setIslocked(true);
			itemGridList3.add(item);
 		}
		
		
		//========== passed4 represents the passed apks of <HHTZETS>
		int passed4 = SharedPreferencesUtil.getPassApkCountForLJSW45HHTZETS(getActivity());
		if(passed4==-1){
			passed4 = 0;
		}
		for(int x = 0;x<passed4;x++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array4[x]);
			item.setApkName(menu_toolbar_image_array44[x]);
			item.setIslocked(false);
			itemGridList4.add(item);
		}
		for(int xx=passed4;xx< menu_toolbar_image_array44.length;xx++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkName(menu_toolbar_image_array44[xx]);
			item.setIslocked(true);
			itemGridList4.add(item);
		}
		
		//================ passed5 represents the passed apk of WMJZMCY.
		int passed5 = SharedPreferencesUtil.getPassApkCountForLJSW45WMJZMCY(getActivity());
		if(passed5==-1){
			passed5= 0;
		}
		for(int y = 0;y<passed5;y++ ){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array5[y]);
			item.setApkName(menu_toolbar_image_array55[y]);
			item.setIslocked(false);
			itemGridList5.add(item);
		}
		for(int yy=passed5;yy<menu_toolbar_image_array55.length;yy++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkName(menu_toolbar_image_array55[yy]);
			item.setIslocked(true);
			itemGridList5.add(item);
		}
		
		itemList = new ArrayList<>();
		
		itemList.add(itemGridList0);
		itemList.add(itemGridList1);
		itemList.add(itemGridList2);
		itemList.add(itemGridList3);
		itemList.add(itemGridList4);
		itemList.add(itemGridList5);
	}*/
	
	@Override
	public void onResume() {
		super.onResume();
		initData();
		if(adapter!=null){
		adapter.notifyDataSetChanged();
		}
	}
	
}
