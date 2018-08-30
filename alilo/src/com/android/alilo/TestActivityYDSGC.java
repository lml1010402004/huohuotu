package com.android.alilo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.adapter.AppsAdapterCZL1;
import com.android.model.ApkItem;
import com.android.myUtils.MyApplication;
import com.android.myUtils.SharedPreferencesUtil;
import com.android.myUtils.Utils;
import android.content.res.TypedArray;



public class TestActivityYDSGC extends Activity {
	
	ImageButton back;	
	ImageView imageViewydsgcjs;
	AnimationDrawable ydsgc_js;
	
	
	//wifiView
	ImageView iv_wifi_status; // 连接的wifi图标状态	
	WifiConfiguration con_wifi;
	IntentFilter mFilter;
	
	//batteryView
	private ClipDrawable clipDrawable;
	ImageView image_battery;
	private GridView app_list;
	private int[] menu_toolbar_image_array0 ;
	private int[] menu_toolbar_image_array00;
	
	
	//private int menu_toolbar_image_array00[]={R.drawable.gcl_time_title,R.drawable.gcl_zt_jb_title,
	//		R.drawable.gcl_dt_title,R.drawable.gcl_in_out_title,R.drawable.gcl_db_title,R.drawable.gcl_left_right_title};
	//private int menu_toolbar_image_array0[] = {R.drawable.gcl_time_up,
	//		R.drawable.gcl_zt_jb_up, R.drawable.gcl_dt_up,
	//		R.drawable.gcl_in_out_up,  R.drawable.gcl_db_up,R.drawable.gcl_left_right_up
	//	};

	int array_length = 0;
    private void initPicsIndexForYdsgc(){
    	TypedArray array_icons_ydsgc = getResources().obtainTypedArray(R.array.ydsgc_pic_index_icon);
    	array_length = array_icons_ydsgc.length();
    	menu_toolbar_image_array0 = new int[array_length];
    	for(int i=0;i<array_length;i++){
    		menu_toolbar_image_array0[i] = array_icons_ydsgc.getResourceId(i, R.drawable.apk_locked);
    	}
    	array_icons_ydsgc.recycle();
    	TypedArray array_titles_ydsgc = getResources().obtainTypedArray(R.array.ydsgc_pic_index_title);
    	menu_toolbar_image_array00 = new int[array_length];
    	for(int j=0;j<array_length;j++){
    		menu_toolbar_image_array00[j] = array_titles_ydsgc.getResourceId(j, R.drawable.game2_11_title);
    	}
    	array_titles_ydsgc.recycle();
    	
    }
	
		
	@Override      
	public void onCreate(Bundle savedInstanceState) {         
		super.onCreate(savedInstanceState);          
		setContentView(R.layout.test_activityydsgc);   
		 Utils.addActivity(this);
		back = (ImageButton)findViewById(R.id.back);
		back.setOnTouchListener(touchListener);
		back.setEnabled(true);
		
		imageViewydsgcjs = (ImageView)findViewById(R.id.imageViewydsgcjs);
		imageViewydsgcjs.setOnTouchListener(touchListener);
		imageViewydsgcjs.setEnabled(true);
		imageViewydsgcjs.setBackgroundResource(R.drawable.imageviewsydsgcjs_anim);
		ydsgc_js = (AnimationDrawable) imageViewydsgcjs.getBackground();
		ydsgc_js.start();
		
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
		init();
		
	}
	
    private void refreshData(){
    	arraylist = null;
    	arraylist = new ArrayList<ApkItem>();
    	int array0 = SharedPreferencesUtil.getpassApkCountForYDSGC(TestActivityYDSGC.this);
    	Log.v("jack", "refreshData"+array0);
		//读取没有枷锁的图标。
		for(int j=0;j<array0;j++){
		//	Log.v("jack", "进来几次了jjjjjjj"+j);
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array0[j]);
			item.setApkName(menu_toolbar_image_array00[j]);
			item.setIslocked(false);
			arraylist.add(item);
		}
	    for(int i=array0;i<menu_toolbar_image_array00.length;i++){
	    	//Log.v("jack", "进来几次了iiiiiiiii"+i);
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkbackgroud(menu_toolbar_image_array0[i]);
			item.setApkName(menu_toolbar_image_array00[i]);
			item.setIslocked(true);
			arraylist.add(item);
		}
	    appAdapter  = new AppsAdapterCZL1(TestActivityYDSGC.this, arraylist);
    }

	
	private ApkItem item = null;
	private ArrayList<ApkItem> arraylist;
	AppsAdapterCZL1 appAdapter;
	private void init(){
		initPicsIndexForYdsgc();
		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryChangedReceiver, intentFilter);
		app_list = (GridView) findViewById(R.id.app_list);
		refreshData();
    	/*arraylist = new ArrayList<ApkItem>();
    
    	int array0 = SharedPreferencesUtil.getpassApkCountForYDSGC(TestActivityYDSGC.this);
    	for(int i=0;i<array0;i++){
    		item = new ApkItem();
    		item.setApkImage(menu_toolbar_image_array0[i]);
    		item.setApkName(menu_toolbar_image_array00[i]);
    		item.setIslocked(false);
    		arraylist.add(item);
    	}
    	for(int ii=array0;ii<menu_toolbar_image_array00.length;ii++){
    		item = new ApkItem();
    		item.setApkImage(MyApplication.apk_locked);
    		item.setApkName(menu_toolbar_image_array00[ii]);
    		item.setIslocked(true);
    		arraylist.add(item);
    	}*/
    	
    	appAdapter  = new AppsAdapterCZL1(TestActivityYDSGC.this, arraylist);
    	app_list.setSelector(new ColorDrawable(Color.TRANSPARENT));//this code makes the background transparent when click any item.
    	app_list.setAdapter(appAdapter);
    	app_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					if(arraylist.get(0).isIslocked()){
						Toast.makeText(TestActivityYDSGC.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityYDSGC.this,AudioService.class));
					Utils.doStartApplicationWithPackageName(TestActivityYDSGC.this,"air.HHTShijianGuanchaFa");
					break;
				case 1:
					if(arraylist.get(1).isIslocked()){
						Toast.makeText(TestActivityYDSGC.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityYDSGC.this,AudioService.class));
					Utils.doStartApplicationWithPackageName(TestActivityYDSGC.this,"air.HHTCongZhengtiDaoJubu");
					break;
				case 2:
					if(arraylist.get(2).isIslocked()){
						Toast.makeText(TestActivityYDSGC.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityYDSGC.this,AudioService.class));
					Utils.doStartApplicationWithPackageName(TestActivityYDSGC.this,"air.HHTDongTaiGuanChaFa");
					break;
				case 3:
					if(arraylist.get(3).isIslocked()){
						Toast.makeText(TestActivityYDSGC.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityYDSGC.this,AudioService.class));
					Utils.doStartApplicationWithPackageName(TestActivityYDSGC.this,"air.HHTWaidaoli");
					break;
				case 4:
					if(arraylist.get(4).isIslocked()){
						Toast.makeText(TestActivityYDSGC.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityYDSGC.this,AudioService.class));
					Utils.doStartApplicationWithPackageName(TestActivityYDSGC.this,"air.HHTDuiBiGuangChaFa");
					break;
				case 5:
					if(arraylist.get(5).isIslocked()){
						Toast.makeText(TestActivityYDSGC.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityYDSGC.this,AudioService.class));
					Utils.doStartApplicationWithPackageName(TestActivityYDSGC.this,"air.HHTCongZuoDaoYou");
					break;
				//case 6:
				//	if(arraylist.get(6).isIslocked()){
			//			Toast.makeText(TestActivityYDSGC.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
			//			return ;
			//		}
			//		stopService(new Intent(TestActivityYDSGC.this,AudioService.class));
			//		Utils.doStartApplicationWithPackageName(TestActivityYDSGC.this,"air.HHTCongZuoDaoYou");
			//		break;
				default:
					break;
				}
			}
		});
	}
	
	
	OnTouchListener touchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.imageViewydsgcjs:
					onTouchChange("imageViewydsgcjs", event.getAction());
					break;
				case R.id.back:
					onTouchChange("back", event.getAction());
					finish();
					break;	
				default:
					break;
				}
			return false;
		}
	};
	
	void onTouchChange(String methodName, int eventAction) {
		if ("imageViewydsgcjs".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				Intent intent = new Intent(this, YDSGCJS.class);  //20170105_wangjx_启动intent开启二级菜单专注力训练
                Bundle bundle = new Bundle();
				bundle.putInt("flag", 100);
				intent.putExtras(bundle);

				startActivity(intent);                                 //20170105_wangjx_启动intent开启二级菜单专注力训练
	        }else if (eventAction == MotionEvent.ACTION_UP){
			}
		}
		if("back".equals(methodName)){
			this.finish();
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
        if(appAdapter!=null){
        	Log.v("jack", "进入到YDSGC的onResume方法");
        	refreshData();
        	app_list.setAdapter(appAdapter);
        	//appAdapter.notifyDataSetChanged();
        }
		Intent intent2 = new Intent(TestActivityYDSGC.this, AudioService.class);
			       startService(intent2);
  if(!Utils.isNetWorkAvailable(TestActivityYDSGC.this)){
     	   iv_wifi_status.setVisibility(View.INVISIBLE);
        }else{
     	   iv_wifi_status.setVisibility(View.VISIBLE);
        }


    }
    
    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(batteryChangedReceiver);
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
					if(Utils.isNetWorkAvailable(TestActivityYDSGC.this)){
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
		unregisterReceiver(batteryChangedReceiver);
	}
    ///////////////////end_20161223_wangjx_读取系统wifi显示   
}
