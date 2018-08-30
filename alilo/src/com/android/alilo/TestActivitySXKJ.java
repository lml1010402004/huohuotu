package com.android.alilo;

/**
 * ������
 * @author wangjx
 *
 */

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ExpandableListView; 
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;

import com.android.adapter.ListViewAdapterLJSW1;
import com.android.customewidget.NestedExpandaleListView;
import com.android.model.ApkItem;
import com.android.myUtils.SharedPreferencesUtil;
import com.android.myUtils.Utils;
import com.android.myUtils.MyApplication;


public class TestActivitySXKJ extends FragmentActivity {
	private ImageView image_battery ;
	private ClipDrawable clipDrawable;
	private ImageView iv_wifi_status;
	  WifiConfiguration con_wifi;
	private ImageView back;
	private ImageView imageViewsxykjjs;
	private NestedExpandaleListView expandableListView;
	
	
	private String[] groupsName = null;
	private List<ApkItem> itemGridList0;
	private List<ApkItem> itemGridList1;
	private List<ApkItem> itemGridList2;
	private List<ApkItem> itemGridList3;
	private List<List<ApkItem>> itemList;
	private ListViewAdapterLJSW1 adapter;
		IntentFilter mFilter;





	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
	
		setContentView(R.layout.test_activitysxkj);
		 Utils.addActivity(this);
		init();
		mFilter = new IntentFilter();
		mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		mFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
		mFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		registerReceiver(mReceiver, mFilter);
	}
	
	private void initStringOfSXKJ(){
		groupsName = getResources().getStringArray(R.array.sxkj);
	}
	
	//我们画，贴，建
	private int[] menu_toolbar_image_array00 = null;
	private int[] menu_toolbar_image_array0 = null;
	
	//数数与比较
	private int[] menu_toolbar_image_array11 = null;
	private int[] menu_toolbar_image_array1 = null;
	
	//农庄里的知识
	private int[] menu_toolbar_image_array22 = null;
	private int[] menu_toolbar_image_array2 = null;
	
	//数与数字
	private int[] menu_toolbar_image_array33 = null;
	private int[] menu_toolbar_image_array3 = null;
	
	private List<String> groupList = null;

		void onTouchChange(String methodName, int eventAction) {
		if ("imageViewsxykjjs".equals(methodName)) {
			
			if (eventAction == MotionEvent.ACTION_DOWN) {
				Intent intent = new Intent(this, SxkjJs.class);  //20170105_wangjx_����intent���������˵�רע��ѵ��
				Bundle bundle = new Bundle();
				bundle.putInt("flag", 100);
				intent.putExtras(bundle);
				startActivity(intent);                                 //20170105_wangjx_����intent���������˵�רע��ѵ��
	        }else if (eventAction == MotionEvent.ACTION_UP){
			}
		}
		if("back".equals(methodName)){
		   this.finish();
		}
		
	};

			///////////////////begin_20161222_wangjx_��ȡϵͳ�����ʾ
	    private int calculateLevel(int progress) {
	        int leftOffest = MyUtils.dip2px(this, 2);
	        int powerLength = MyUtils.dip2px(this, 26.5f);// 40 px in hdpi
	        int totalLength = MyUtils.dip2px(this, 30.5f);// 49 px in hdpi
	        int level = (leftOffest + powerLength * progress / 100) * 10000 / totalLength;
	        return level;
	    }

		  private BroadcastReceiver batteryChangedReceiver = new BroadcastReceiver() {

	        public void onReceive(Context context, Intent intent) {
	            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
	                int level = intent.getIntExtra("level", 0);
	                int scale = intent.getIntExtra("scale", 100);
	                // 0 - 100
	                int power = level * 100 / scale;
	                // setLevel(int level): level �ķ�Χ�� 0 -10000
	                clipDrawable.setLevel(calculateLevel(power));
	            }
	        }     
	    };
	
	AnimationDrawable sxkj_js;
	private void init(){
		iniPics();//初始化UI资源
		initStringOfSXKJ();
		image_battery = (ImageView) findViewById(R.id.image_battery);
		 LayerDrawable layerDrawable = (LayerDrawable) image_battery.getDrawable();
		 clipDrawable = (ClipDrawable)layerDrawable.findDrawableByLayerId(R.id.clip_drawable);
		iv_wifi_status = (ImageView) findViewById(R.id.iv_wifi_status);
		back = (ImageView) findViewById(R.id.back);
		back.setOnTouchListener(touchListener);
		imageViewsxykjjs = (ImageView) findViewById(R.id.imageViewsxykjjs);
        imageViewsxykjjs.setOnTouchListener(touchListener);
		imageViewsxykjjs.setEnabled(true);
		imageViewsxykjjs.setBackgroundResource(R.drawable.imageviewsxykjjs_anim);
		sxkj_js = (AnimationDrawable) imageViewsxykjjs.getBackground();
		sxkj_js.start();
		
		expandableListView = (NestedExpandaleListView) findViewById(R.id.expandableListView);
		expandableListView.setGroupIndicator(null);
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2,
					long arg3) {
				return true;
			}
		});
		
		groupList = new ArrayList<>();
		for(int i=0;i<groupsName.length;i++){
			groupList.add(groupsName[i]);
		}
		itemGridList1 = new ArrayList<>();
		itemGridList2 = new ArrayList<>();
		itemGridList3 = new ArrayList<>();
		itemGridList0 = new ArrayList<>();
		
		restoreData();
		
		itemList = new ArrayList<>();
		itemList.add(itemGridList0);
		itemList.add(itemGridList1);
		itemList.add(itemGridList2);
		itemList.add(itemGridList3);
		
		adapter = new ListViewAdapterLJSW1(TestActivitySXKJ.this,groupList, itemList, 4);
		expandableListView.setAdapter(adapter);
		for(int i=0;i<adapter.getGroupCount();i++){
			expandableListView.expandGroup(i);
		}
		
	}
	
	private void restoreData(){
		ApkItem item = null;

		//数学空间 -数数与比较
		int array0 = SharedPreferencesUtil.getpassApkCountForSXYKJssybj(TestActivitySXKJ.this);
		for(int j=0;j<array0;j++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array0[j]);
			item.setApkName(menu_toolbar_image_array00[j]);
			item.setIslocked(false);
			itemGridList0.add(item);
		}
		for(int jj=array0;jj<menu_toolbar_image_array00.length;jj++){
			item = new ApkItem();
		item.setApkImage(MyApplication.apk_locked);
			item.setApkName(menu_toolbar_image_array00[jj]);
		item.setApkbackgroud(menu_toolbar_image_array0[jj]);
			item.setIslocked(true);
			itemGridList0.add(item);
		}
		
		//数学空间 -分配与顺序
		int array1 = SharedPreferencesUtil.getpassApkCountForSXYKJfpysx(TestActivitySXKJ.this);
		for(int k =0;k<array1;k++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array1[k]);
			item.setApkName(menu_toolbar_image_array11[k]);
			item.setIslocked(false);
			itemGridList1.add(item);
		}
		for(int kk=array1;kk<menu_toolbar_image_array1.length;kk++){
			item = new ApkItem();
				item.setApkImage(MyApplication.apk_locked);
		//item.setApkImage(menu_toolbar_image_array1[kk]);
			item.setApkName(menu_toolbar_image_array11[kk]);
		item.setApkbackgroud(menu_toolbar_image_array1[kk]);

			item.setIslocked(true);
			itemGridList1.add(item);
		}

		int array3 = SharedPreferencesUtil.getpassApkCountForSXYKJsysz(TestActivitySXKJ.this);
		for(int n = 0;n<array3;n++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array3[n]);
			item.setApkName(menu_toolbar_image_array33[n]);
			item.setIslocked(false);
			itemGridList2.add(item);
		}
		for(int nn=array3;nn<menu_toolbar_image_array3.length;nn++){
			item = new ApkItem();
				item.setApkImage(MyApplication.apk_locked);
			//item.setApkImage(menu_toolbar_image_array3[nn]);
			item.setApkName(menu_toolbar_image_array33[nn]);
			item.setApkbackgroud(menu_toolbar_image_array3[nn]);
			item.setIslocked(true);
			itemGridList2.add(item);
		}


		//数学空间-数与数字
		int array2 = SharedPreferencesUtil.getpassApkCountForSXYKJjjjs(TestActivitySXKJ.this);
		for(int m = 0;m<array2;m++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array2[m]);
			item.setApkName(menu_toolbar_image_array22[m]);
		    item.setIslocked(false);
			itemGridList3.add(item);
		}
		for(int mm =array2;mm<menu_toolbar_image_array2.length;mm++){
			item = new ApkItem();
				item.setApkImage(MyApplication.apk_locked);
			//item.setApkImage(menu_toolbar_image_array2[mm]);
			item.setApkName(menu_toolbar_image_array22[mm]);
			item.setApkbackgroud(menu_toolbar_image_array2[mm]);

			item.setIslocked(true);
			itemGridList3.add(item);
		}
		//数学空间里面-加减计算
		
		
	}
	
	/**
	 * 这个模块里面的UI资源是在逻辑思维系统里面出现过的，故引用的资源也是来自于同一个地方。
	 */
	private void iniPics(){
		//数数与比较
		TypedArray array_icons_sxkj_ycwxsk = getResources().obtainTypedArray(R.array.sxsw_fpysx_pic_index_icon);
		menu_toolbar_image_array1 = new int[array_icons_sxkj_ycwxsk.length()];
		for(int i=0;i<array_icons_sxkj_ycwxsk.length();i++){
			menu_toolbar_image_array1[i] = array_icons_sxkj_ycwxsk.getResourceId(i, R.drawable.apk_locked);
		}
		array_icons_sxkj_ycwxsk.recycle();
		TypedArray array_titles_sxkj_htj = getResources().obtainTypedArray(R.array.sxsw_fpysx_pic_index_title);
		menu_toolbar_image_array11= new int[array_titles_sxkj_htj.length()];
		for(int ii = 0;ii<array_titles_sxkj_htj.length();ii++){
			menu_toolbar_image_array11[ii] = array_titles_sxkj_htj.getResourceId(ii, R.drawable.game2_1_title);
		}
		array_titles_sxkj_htj.recycle();
		
		//分配与顺序
		TypedArray array_icons_sxkj_ssybj = getResources().obtainTypedArray(R.array.sxsw_ssybj_pic_index_icon);
		menu_toolbar_image_array0 = new int[array_icons_sxkj_ssybj.length()];
		for(int j =0;j<array_icons_sxkj_ssybj.length();j++){
			menu_toolbar_image_array0[j] = array_icons_sxkj_ssybj.getResourceId(j, R.drawable.game2_1_title);
		}
         array_icons_sxkj_ssybj.recycle();
        TypedArray array_titles_sxkj_ssybj = getResources().obtainTypedArray(R.array.sxsw_ssybj_pic_index_title);
		menu_toolbar_image_array00= new int[array_titles_sxkj_ssybj.length()];
		for(int jj=0;jj<array_titles_sxkj_ssybj.length();jj++){
			menu_toolbar_image_array00[jj] = array_titles_sxkj_ssybj.getResourceId(jj, R.drawable.game2_1_title);
		}
		array_titles_sxkj_ssybj.recycle();
		
		//数与数字sxsw_jjjs_pic_index_icon    sxsw_jjjs_pic_index_title
		TypedArray array_icons_sxkj_nzldzs = getResources().obtainTypedArray(R.array.sxsw_jjjs_pic_index_icon);
		menu_toolbar_image_array2 = new int[array_icons_sxkj_nzldzs.length()];
		for(int k=0;k<array_icons_sxkj_nzldzs.length();k++){
			menu_toolbar_image_array2[k] = array_icons_sxkj_nzldzs.getResourceId(k, R.drawable.game2_1_title);
		}
		array_icons_sxkj_nzldzs.recycle();
		TypedArray array_titles_sxkj_nzldzs = getResources().obtainTypedArray(R.array.sxsw_jjjs_pic_index_title);
		menu_toolbar_image_array22 = new int[array_titles_sxkj_nzldzs.length()];
		for(int kk=0;kk<array_titles_sxkj_nzldzs.length();kk++){
			menu_toolbar_image_array22[kk] = array_titles_sxkj_nzldzs.getResourceId(kk, R.drawable.game2_11_title);
		}
		array_titles_sxkj_nzldzs.recycle();
		
		//加法计算sxsw_sysz_pic_index_icon   sxsw_sysz_pic_index_title
		TypedArray array_icons_sxkj_sysz = getResources().obtainTypedArray(R.array.sxsw_sysz_pic_index_icon);
		menu_toolbar_image_array3 = new int[array_icons_sxkj_sysz.length()];
		for(int h=0;h<array_icons_sxkj_sysz.length();h++){
			menu_toolbar_image_array3[h] = array_icons_sxkj_sysz.getResourceId(h, R.drawable.game2_1_title);
		}
		array_icons_sxkj_sysz.recycle();
		TypedArray array_titles_sxkj_sysz = getResources().obtainTypedArray(R.array.sxsw_sysz_pic_index_title);
		menu_toolbar_image_array33 = new int[array_titles_sxkj_sysz.length()];
		for(int hh=0;hh<array_titles_sxkj_sysz.length();hh++){
			menu_toolbar_image_array33[hh] = array_titles_sxkj_sysz.getResourceId(hh, R.drawable.game2_11_title);
		}
		array_titles_sxkj_sysz.recycle();
	}

	@Override
	protected void onResume() {
		super.onResume();
			MyApplication.FLAGOFSXKJ = true;
		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryChangedReceiver, intentFilter);
		if(adapter!=null){

	itemGridList1.clear();
		itemGridList2.clear();
		itemGridList3.clear();
		itemGridList0.clear();
		restoreData();
		adapter.notifyDataSetChanged();

		}

		 Intent intent2 = new Intent(TestActivitySXKJ.this, AudioService.class);
	        startService(intent2);
	        if(!Utils.isNetWorkAvailable(TestActivitySXKJ.this)){
	     	   iv_wifi_status.setVisibility(View.INVISIBLE);
	        }else{
	     	   iv_wifi_status.setVisibility(View.VISIBLE);
	        }
	}

	BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (WifiManager.WIFI_STATE_CHANGED_ACTION
					.equals(intent.getAction())) {// �������wifi�Ĵ���ر�
				int wifiState = intent.getIntExtra(
						WifiManager.EXTRA_WIFI_STATE, 0);
				switch (wifiState) {
				case WifiManager.WIFI_STATE_ENABLED:
					if(Utils.isNetWorkAvailable(TestActivitySXKJ.this)){
						iv_wifi_status.setVisibility(View.VISIBLE);
						showcon_wifi();
						}
					break;
				case WifiManager.WIFI_STATE_DISABLED:
					iv_wifi_status.setVisibility(View.GONE);
				case WifiManager.WIFI_STATE_DISABLING:
					iv_wifi_status.setVisibility(View.GONE);
					break;

				}
			} else if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent
					.getAction())) {
				Parcelable parcelableExtra = intent
						.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
				if (null != parcelableExtra) {
					NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
					if (networkInfo.isConnected()) {						
						int level = Math
								.abs(((WifiManager) getSystemService(WIFI_SERVICE))
										.getConnectionInfo().getRssi());
						iv_wifi_status.setImageLevel(level);

					}
				}
			} else if (intent.getAction().equals(
					WifiManager.RSSI_CHANGED_ACTION)) {
				int mWifiRssi = intent.getIntExtra(WifiManager.EXTRA_NEW_RSSI,
						-200);
				int level = Math.abs(mWifiRssi);
				iv_wifi_status.setImageLevel(level);
			}
		}
	};

		OnTouchListener touchListener = new OnTouchListener() {
		@SuppressLint("ClickableViewAccessibility") @Override
		public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
			case R.id.imageViewsxykjjs:
					onTouchChange("imageViewsxykjjs", event.getAction());
					break;
				case R.id.back:
					onTouchChange("back", event.getAction());
				//	finish();
					break;	
				default:
					break;
				}
			return false;
		}
	};

		void showcon_wifi() {
		if (con_wifi != null) {
			int level = Math.abs(((WifiManager) getSystemService(WIFI_SERVICE))
					.getConnectionInfo().getRssi());
			iv_wifi_status.setImageLevel(level);

		}
	}

	   @Override
protected void onDestroy() {
	super.onDestroy();
	unregisterReceiver(mReceiver);
	unregisterReceiver(batteryChangedReceiver);
}


}
