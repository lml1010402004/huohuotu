package com.android.alilo;

/**
 * 主界面
 * @author wangjx
 *
 */

import com.android.adapter.ListViewAdapterLJSW1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import com.android.myUtils.Utils;

public class TestActivityPDTL extends FragmentActivity {

	ImageView back;
	ImageView imageViewsxykjjs;
	AnimationDrawable sxykj_js;

	ExpandableListView expandableListView;
	ListViewAdapterLJSW1 treeViewAdapter;
	public String[] groups = { ""};
	public String[][] child = { { "" }};
	
	//wifiView
	ImageView iv_wifi_status; // 连接的wifi图标状态	
	WifiConfiguration con_wifi;
	IntentFilter mFilter;
	
	//batteryView
	private ClipDrawable clipDrawable;
	ImageView image_battery;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activitypdtl);
         Utils.addActivity(this);
		back = (ImageView)findViewById(R.id.back);
		back.setOnTouchListener(touchListener);
		
		imageViewsxykjjs = (ImageView)findViewById(R.id.imageViewsxykjjs);
		imageViewsxykjjs.setOnTouchListener(touchListener);
		imageViewsxykjjs.setBackgroundResource(R.drawable.imageviewsxykjjs_anim);
		sxykj_js = (AnimationDrawable) imageViewsxykjjs.getBackground();
		sxykj_js.start();
			
		//battery
		ImageView imageBattery = (ImageView)findViewById(R.id.image_battery);
	    LayerDrawable layerDrawable = (LayerDrawable) imageBattery.getDrawable();
	    clipDrawable = (ClipDrawable)layerDrawable.findDrawableByLayerId(R.id.clip_drawable);
	
		//wifi
		iv_wifi_status = (ImageView) findViewById(R.id.iv_wifi_status);
		iv_wifi_status.setVisibility(View.GONE);
		mFilter = new IntentFilter();
		mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		mFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
		mFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		registerReceiver(mReceiver, mFilter);
		handler.post(refreshRunnable);
		
//		treeViewAdapter = new ListViewAdapterLJSW1(this,ListViewAdapterLJSW1.PaddingLeft >> 1);
//		expandableListView = (ExpandableListView)this.findViewById(R.id.expandableListView);
//		expandableListView.setGroupIndicator(null);
//		List<ListViewAdapterLJSW1.TreeNode> treeNode = treeViewAdapter.GetTreeNode();
//		for (int i = 0; i < groups.length; i++)
//		{
//			ListViewAdapterLJSW1.TreeNode node = new ListViewAdapterLJSW1.TreeNode();
//			//node.parent = groups[i];
//			for (int ii = 0; ii < child[i].length; ii++)
//			{
//				node.childs.add(child[i][ii]);
//			}
//			treeNode.add(node);
//		}
//		treeViewAdapter.UpdateTreeNode(treeNode);
//		expandableListView.setAdapter(treeViewAdapter);
//		for(int i=0;i<treeViewAdapter.getGroupCount();i++){
//			expandableListView.expandGroup(i);
//		}
    }
		
    OnTouchListener touchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (v.getId()) {			
				case R.id.back:
					onTouchChange("back", event.getAction());
					Log.d("wwwwwwwwjx","innnnnnnnnnnnImageButton2==");  //20170103_wangjx_添加二级菜单返回功能
					finish();
					break;
				case R.id.imageViewsxykjjs:
					onTouchChange("imageViewsxykjjs", event.getAction());
					Log.d("wwwwwwwwjx","imageViewjs==");  //20170103_wangjx_添加二级菜单九宫格点击事件
					break;				
				default:
					break;
				}
			return false;
		}
	};
	
	void onTouchChange(String methodName, int eventAction) {
		if ("imageViewsxykjjs".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				Intent intent = new Intent(this, LjswNrJs2.class);  //20170105_wangjx_启动intent开启二级菜单专注力训练
				startActivity(intent);                                 //20170105_wangjx_启动intent开启二级菜单专注力训练
	        }else if (eventAction == MotionEvent.ACTION_UP){
			}
		}
	};
	
	///////////////////begin_20161222_wangjx_读取系统电池显示
    private int calculateLevel(int progress) {
        int leftOffest = MyUtils.dip2px(this, 2);
        int powerLength = MyUtils.dip2px(this, 26.5f);// 40 px in hdpi
        int totalLength = MyUtils.dip2px(this, 30.5f);// 49 px in hdpi
        int level = (leftOffest + powerLength * progress / 100) * 10000 / totalLength;
        return level;
    }
	
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryChangedReceiver, intentFilter);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(batteryChangedReceiver);
    }
    // 电量变化广播
    private BroadcastReceiver batteryChangedReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                // 0 - 100
                int power = level * 100 / scale;
                // setLevel(int level): level 的范围是 0 -10000
                clipDrawable.setLevel(calculateLevel(power));
            }
        }     
    };
    ///////////////////end_20161222_wangjx_读取系统电池显示   

    ///////////////////begin_20161223_wangjx_读取系统wifi显示   
	BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (WifiManager.WIFI_STATE_CHANGED_ACTION
					.equals(intent.getAction())) {// 这个监听wifi的打开与关闭
				int wifiState = intent.getIntExtra(
						WifiManager.EXTRA_WIFI_STATE, 0);
				switch (wifiState) {
				case WifiManager.WIFI_STATE_ENABLED:
					iv_wifi_status.setVisibility(View.VISIBLE);
					Log.d("1111111111111111111111111111111wwwwwwwwjx","WIFI_STATE_ENABLED==");
					showcon_wifi();
					break;
				case WifiManager.WIFI_STATE_DISABLED:
					iv_wifi_status.setVisibility(View.GONE);
					Log.d("2222222222222222222222222222222wwwwwwwwjx","WIFI_STATE_DISABLED==");
				case WifiManager.WIFI_STATE_DISABLING:
					iv_wifi_status.setVisibility(View.GONE);
					Log.d("3333333333333333333333333333333wwwwwwwwjx","WIFI_STATE_DISABLING==");
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

	/**
	 * 根据连接状态显示
	 */
	void showcon_wifi() {
		if (con_wifi != null) {
			int level = Math.abs(((WifiManager) getSystemService(WIFI_SERVICE))
					.getConnectionInfo().getRssi());
			iv_wifi_status.setImageLevel(level);

		}
	}
	int num = 0;
	Handler handler = new Handler();
	Runnable refreshRunnable = new Runnable() {
		public void run() {
			handler.postDelayed(this, 100);
		}
	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}
    ///////////////////end_20161223_wangjx_读取系统wifi显示   
}
