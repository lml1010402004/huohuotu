package com.android.alilo;

/**
 * 主界面
 * @author wangjx
 *
 */
import java.util.ArrayList;
import java.util.List;
import com.android.adapter.AppsAdapterCZL1;
import com.android.model.ApkItem;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.myUtils.SharedPreferencesUtil;
import com.android.myUtils.MyApplication;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
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
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import com.android.myUtils.Utils;
import android.content.res.TypedArray;
import java.util.Calendar;
import java.util.Locale;
import android.widget.Toast;

public class TestActivityZZLXL extends FragmentActivity {

	ImageView back;
	ImageView imageViewzzlxljs;
	AnimationDrawable zzlxl_js;
	
	//public String[] groups = { ""};
//	public String[][] child = { { "" }};

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
	
	//private int menu_toolbar_image_array00[]={R.drawable.zzl_1_title,R.drawable.zzl_2_title,R.drawable.zzl_qgzqg_title,R.drawable.zzl_zbt_title
	//		,R.drawable.zzl_zsjs_title,R.drawable.zzl_hhtpz_title,R.drawable.zzl_qwdfx_title
	//		};
	//private int menu_toolbar_image_array0[] = {R.drawable.zzl_1_up,
	//		R.drawable.zzl_2_up,R.drawable.zzl_qgzqg_up,R.drawable.zzl_zbt_up,R.drawable.zzl_zsjs_up,R.drawable.zzl_hhtpz_up,R.drawable.zzl_qwdfx_up};
	
	 int array_length = 0;
	    private void initPicsIndexForZzlxl(){
	    	TypedArray array_icons_zzlxl = getResources().obtainTypedArray(R.array.zzltx_pic_index_icon);
	    	array_length = array_icons_zzlxl.length();
	    	menu_toolbar_image_array0 = new int[array_length];
	    	for(int i=0;i<array_length;i++){
	    		menu_toolbar_image_array0[i] = array_icons_zzlxl.getResourceId(i, R.drawable.apk_locked);
	    	}
	    	array_icons_zzlxl.recycle();
	    	TypedArray array_titles_zzlxl = getResources().obtainTypedArray(R.array.zzltx_pic_index_title);
	    	menu_toolbar_image_array00 = new int[array_length];
	    	for(int j=0;j<array_length;j++){
	    		menu_toolbar_image_array00[j] = array_titles_zzlxl.getResourceId(j, R.drawable.game2_11_title);
	    	}
	    	array_titles_zzlxl.recycle();
	    }

	private void setSystemTime(){
		  Calendar c = Calendar.getInstance(Locale.CHINA);
		  c.set(Calendar.YEAR, 2017);
          c.set(Calendar.MONTH, 8);
          c.set(Calendar.DAY_OF_MONTH, 1);   
		
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setSystemTime();
        setContentView(R.layout.test_activityzzlxl);
         Utils.addActivity(this);
		back = (ImageView)findViewById(R.id.back);
		back.setOnTouchListener(touchListener);
		
		imageViewzzlxljs = (ImageView)findViewById(R.id.imageViewzzlxljs);
		imageViewzzlxljs.setOnTouchListener(touchListener);
		imageViewzzlxljs.setEnabled(true);
		imageViewzzlxljs.setBackgroundResource(R.drawable.imageviewszzlxljs_anim);
		zzlxl_js = (AnimationDrawable) imageViewzzlxljs.getBackground();
		zzlxl_js.start();
		
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
    
	private ArrayList<ApkItem> arraylist;
	AppsAdapterCZL1 appAdapter;
    private void init(){
		initPicsIndexForZzlxl();
    	app_list = (GridView) findViewById(R.id.app_list);
    	//arraylist = new ArrayList<ApkItem>();
    //	ApkItem item = null;
		refreshData();
	//	for(int i=0;i<menu_toolbar_image_array00.length;i++){
	//		item = new ApkItem();
	//		item.setApkImage(menu_toolbar_image_array0[i]);
	//		item.setApkName(menu_toolbar_image_array00[i]);
	//		arraylist.add(item);
	//	}
    	//appAdapter  = new AppsAdapterCZL1(TestActivityZZLXL.this, arraylist);
    	app_list.setAdapter(appAdapter);
    	app_list.setSelector(new ColorDrawable(Color.TRANSPARENT));
    	app_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					if(arraylist.get(0).isIslocked()){
						Toast.makeText(TestActivityZZLXL.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityZZLXL.this,AudioService.class));
					doStartApplicationWithPackageName("air.RabbitDrawLine");
					break;
				case 1:
						if(arraylist.get(1).isIslocked()){
						Toast.makeText(TestActivityZZLXL.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityZZLXL.this,AudioService.class));
					doStartApplicationWithPackageName("air.RabbitFindDiff5");
					break;
				case 2:
						if(arraylist.get(2).isIslocked()){
						Toast.makeText(TestActivityZZLXL.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityZZLXL.this,AudioService.class));
					doStartApplicationWithPackageName("air.RabbitFindDiff");
					break;
				case 3:
						if(arraylist.get(3).isIslocked()){
						Toast.makeText(TestActivityZZLXL.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityZZLXL.this,AudioService.class));
					doStartApplicationWithPackageName("air.RabbitFindDiff2");
					break;
				case 4:
						if(arraylist.get(4).isIslocked()){
						Toast.makeText(TestActivityZZLXL.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityZZLXL.this,AudioService.class));
					doStartApplicationWithPackageName("air.RabbitMatch");
					break;
				case 5:
						if(arraylist.get(5).isIslocked()){
						Toast.makeText(TestActivityZZLXL.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityZZLXL.this,AudioService.class));
					doStartApplicationWithPackageName("air.RabbitFindDiff4");
					break;
				case 6:
						if(arraylist.get(6).isIslocked()){
						Toast.makeText(TestActivityZZLXL.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityZZLXL.this,AudioService.class));
					doStartApplicationWithPackageName("air.RabbitDrawLine2");
					break;
				case 7:
						if(arraylist.get(7).isIslocked()){
						Toast.makeText(TestActivityZZLXL.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityZZLXL.this,AudioService.class));
					doStartApplicationWithPackageName("air.RabbitFindDiff3");
					break;
				case 8:
						if(arraylist.get(8).isIslocked()){
						Toast.makeText(TestActivityZZLXL.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityZZLXL.this,AudioService.class));
					doStartApplicationWithPackageName("air.RabbitFindDiff6");
					break;
				case 9:
						if(arraylist.get(9).isIslocked()){
						Toast.makeText(TestActivityZZLXL.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityZZLXL.this,AudioService.class));
					doStartApplicationWithPackageName("air.HHTNiuniuhua");
					break;
				case 10:
						if(arraylist.get(10).isIslocked()){
						Toast.makeText(TestActivityZZLXL.this, getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					stopService(new Intent(TestActivityZZLXL.this,AudioService.class));
					doStartApplicationWithPackageName("air.RabbitSurrond");
					break;

				default:
					break;
				}
				
			}
		});
    	
    }
    
    
    /*
	 * 根据包名打开对应的应用。
	 * @param packagename
	 */
	private void doStartApplicationWithPackageName(String packagename) {  
	    // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等  
	    PackageInfo packageinfo = null;  
	    try {  
	        packageinfo = getPackageManager().getPackageInfo(packagename, 0);  
	    } catch (NameNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	    if (packageinfo == null) {  
	        return;  
	    }  
	  
	    // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent  
	    Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);  
	    resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
	    resolveIntent.setPackage(packageinfo.packageName);  
	    
	    // 通过getPackageManager()的queryIntentActivities方法遍历  
	    List<ResolveInfo> resolveinfoList = getPackageManager()  
	            .queryIntentActivities(resolveIntent, 0);  
	  
	    ResolveInfo resolveinfo = resolveinfoList.iterator().next();  
	    if (resolveinfo != null) {  
	        // packagename = 参数packname  
	        String packageName = resolveinfo.activityInfo.packageName;  
	        // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]  
	        String className = resolveinfo.activityInfo.name;  
	        // LAUNCHER Intent  
	        Intent intent = new Intent(Intent.ACTION_MAIN);  
	        intent.addCategory(Intent.CATEGORY_LAUNCHER);  
	  
	        // 设置ComponentName参数1:packagename参数2:MainActivity路径  
	        ComponentName cn = new ComponentName(packageName, className);  
	        intent.setComponent(cn);  
	        startActivity(intent);  
	    }  
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
				case R.id.imageViewzzlxljs:
					onTouchChange("imageViewzzlxljs", event.getAction());
					Log.d("wwwwwwwwjx","imageViewjs==");  //20170103_wangjx_添加二级菜单九宫格点击事件
					break;
				default:
					break;
				}
			return false;
		}
	};
	
	void onTouchChange(String methodName, int eventAction) {
		if ("imageViewzzlxljs".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				Intent intent = new Intent(this, ZZLTXJS.class);  //20170105_wangjx_启动intent开启二级菜单专注力训练
				Bundle bundle = new Bundle();
				bundle.putInt("flag", 100);
				intent.putExtras(bundle);	
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
		Intent intent2 = new Intent(TestActivityZZLXL.this, AudioService.class);
			        startService(intent2);

  if(!Utils.isNetWorkAvailable(TestActivityZZLXL.this)){
     	   iv_wifi_status.setVisibility(View.INVISIBLE);
        }else{
     	   iv_wifi_status.setVisibility(View.VISIBLE);
        }

		 if(appAdapter!=null){
			 appAdapter  = null;
        	refreshData();
           app_list.setAdapter(appAdapter);
        }


    }


   private ApkItem item = null;
    private void refreshData(){
    	arraylist = null;
    	arraylist = new ArrayList<ApkItem>();
    	int array0 = SharedPreferencesUtil.getpassApkCountForZZL(TestActivityZZLXL.this);
    	Log.v("jack", "refreshData"+array0);
		//
		for(int j=0;j<array0;j++){
			Log.v("jack", "����������jjjjjjj"+j);
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array0[j]);
			item.setApkName(menu_toolbar_image_array00[j]);
			item.setIslocked(false);
			arraylist.add(item);
		}
	    for(int i=array0;i<menu_toolbar_image_array00.length;i++){
	    	Log.v("jack", "����������iiiiiiiii"+i);
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkName(menu_toolbar_image_array00[i]);
			item.setApkbackgroud(menu_toolbar_image_array0[i]);
			item.setIslocked(true);
			arraylist.add(item);
		}
	    appAdapter  = new AppsAdapterCZL1(TestActivityZZLXL.this, arraylist);
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
					if(Utils.isNetWorkAvailable(TestActivityZZLXL.this)){
						iv_wifi_status.setVisibility(View.VISIBLE);
						showcon_wifi();
						}
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
