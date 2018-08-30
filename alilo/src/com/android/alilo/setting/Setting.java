package com.android.alilo.setting;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.provider.Settings;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.alilo.HomePageActivity;
import android.net.ConnectivityManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.alilo.MyUtils;
import com.android.alilo.R;
import com.android.dao.DataBaseUtil;
import com.android.model.UserInfo;
import com.android.myUtils.BaseConfig;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yitoa.viewpagerdemo.ADInfo;
import com.yitoa.viewpagerdemo.CycleViewPager;
import com.yitoa.viewpagerdemo.CycleViewPager.ImageCycleViewListener;
import com.yitoa.viewpagerdemo.ViewFactory;
import android.net.Uri;
import android.view.WindowManager;
import com.android.myUtils.MyApplication;
import com.android.alilo.RestoreFactory;
import com.android.myUtils.Utils;
import com.android.myUtils.MyApplication;
import com.android.myUtils.SharedPreferencesUtil;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.AlertDialog.Builder;
import com.android.alilo.AudioService;


public class Setting extends Activity{
	
	private ImageView back;
	private ImageView wifi;
	private ImageView sound;
	private ImageView protect;
	private ImageView brightness;
	private ImageView update;
	private ImageView timer;
	private ImageView restore_factory_setting;
	private ImageView user;
	private ImageView system_setting;
	private TextView user_id,coin_sum;
	private ImageView iv_wifi_status,image_battery;
	private ClipDrawable clipDrawable;
	
	//跟轮播广告有关
	private List<ImageView> views = new ArrayList<ImageView>();
	private List<ADInfo> infos = new ArrayList<ADInfo>();
	private  CycleViewPager cycleViewPager;
	//private WebView webview;
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	    setContentView(R.layout.setting);
 Utils.addActivity(this);
	    databaseutil = new DataBaseUtil();
		back = (ImageView)findViewById(R.id.back);
		back.setOnClickListener(ClickListener);
	     
	    wifi = (ImageView)findViewById(R.id.wifi);
	    wifi.setOnClickListener(ClickListener);
	    //wifi.setOnTouchListener(touchListener);
	   // wifi.setEnabled(true);
	     
	    sound = (ImageView)findViewById(R.id.sound);
	    sound.setOnClickListener(ClickListener);
	  //  sound.setOnTouchListener(touchListener);
	  //  sound.setEnabled(true);
	     
	    protect = (ImageView)findViewById(R.id.protect);
	    protect.setOnClickListener(ClickListener);
	 //   protect.setOnTouchListener(touchListener);
	  //  protect.setEnabled(true);
	     
	    brightness = (ImageView)findViewById(R.id.brightness);
	    brightness.setOnClickListener(ClickListener);
	 //   brightness.setOnTouchListener(touchListener);
	//    brightness.setEnabled(true);
	     
	    update = (ImageView)findViewById(R.id.update);
	    update.setOnClickListener(ClickListener);
	  //  update.setOnTouchListener(touchListener);
	 //   update.setEnabled(true);

		
	    restore_factory_setting = (ImageView) findViewById(R.id.restore_factory_setting);
	    restore_factory_setting.setOnClickListener(ClickListener);
	   // restore_factory_setting.setEnabled(true);

	   system_setting =(ImageView) findViewById(R.id.system_setting);
system_setting.setOnClickListener(ClickListener);
	     
	    timer = (ImageView)findViewById(R.id.timer);
	    timer.setOnClickListener(ClickListener);
	  //  timer.setOnTouchListener(touchListener);
	 //   timer.setEnabled(true);
	   
	    user = (ImageView) findViewById(R.id.user);
	    user.setOnClickListener(ClickListener);
	    user.setOnTouchListener(touchListener);
	    user.setEnabled(true);
	    user_id = (TextView) findViewById(R.id.user_id);
	    coin_sum = (TextView) findViewById(R.id.coin_sum);
	     user_id.setTypeface(HomePageActivity.face);
	    coin_sum.setTypeface(HomePageActivity.face);

	    image_battery = (ImageView) findViewById(R.id.image_battery);
		LayerDrawable layerDrawable = (LayerDrawable) image_battery.getDrawable();
		clipDrawable = (ClipDrawable)layerDrawable.findDrawableByLayerId(R.id.clip_drawable);
	    iv_wifi_status = (ImageView) findViewById(R.id.iv_wifi_status);
	    initWifi();
	    initBatteryMonitor();
	    initADs();

		   //start the internet access.
       if(Utils.isNetWorkAvailable(Setting.this)){
	       new myTask().execute(MyApplication.access_ads_url);
	    }else{
	    	
 	//if without network.should set the default value for this.
		  loadLocalViews();

	    }
		     
		
	}


   private String[] imageUrlsss = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg"};



	  private void loadLocalViews(){
		//if without network.should set the default value for this.
   	try {
		//	String[] picNames = getAssets().list("ad");
			imageUrls = new String[imageUrlsss.length];
			linkUrls=new String[imageUrlsss.length];
			for(int i=0;i<1;i++){
				//Log.v("jack", "加载图片的路径"+picNames[i]);
				imageUrls[i] = imageUrlsss[i];
				linkUrls[i] = MyApplication.DEFAULTLINKOFALILO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
   	loadViews();
   }


	
	
	private String[] imageUrls = null;
	private String[] linkUrls = null;
	
	@SuppressLint("SetJavaScriptEnabled") private void initADs(){
		configImageLoader();
		cycleViewPager =(CycleViewPager) getFragmentManager().findFragmentById(R.id.fragment_cycle_viewpager_content);
	//	webview = (WebView) findViewById(R.id.webview);
	//	WebSettings webSettings = webview.getSettings();
	//	webSettings.setDomStorageEnabled(true);
	//	webSettings.setLoadWithOverviewMode(true);
	//	webSettings.setUseWideViewPort(true);
	//	webSettings.setJavaScriptEnabled(true);
	//	webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
	
	}
	
	private void loadViews(){
		for(int i = 0; i < imageUrls.length; i ++){
			ADInfo info = new ADInfo();
			info.setUrl(imageUrls[i]);
	//Log.v("jack", "imageUrls:"+imageUrls[i]);
			info.setContent("图片-->" + i );
			infos.add(info);
		}
		
		// 将最后一个ImageView添加进来
		views.add(ViewFactory.getImageView(this, infos.get(infos.size() - 1).getUrl()));
		for (int i = 0; i < infos.size(); i++) {
			views.add(ViewFactory.getImageView(this, infos.get(i).getUrl()));
		}
		// 将第一个ImageView添加进来
		views.add(ViewFactory.getImageView(this, infos.get(0).getUrl()));
		
		// 设置循环，在调用setData方法前调用
		cycleViewPager.setCycle(true);

		// 在加载数据前设置是否循环
if(views!=null&&infos!=null){
		cycleViewPager.setData(Setting.this,views, infos, mAdCycleViewListener);
}
		//设置轮播
		cycleViewPager.setWheel(true);

	    // 设置轮播时间，默认5000ms
		cycleViewPager.setTime(2000);
		//设置圆点指示图标组居中显示，默认靠右
		cycleViewPager.setIndicatorCenter();
	}
	
	int index = -1;
	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {
		@Override
		public void onImageClick(ADInfo info,  int position, View imageView) {
			if (cycleViewPager.isCycle()) {
				index = position - 1;

                Intent intent = new Intent();
				intent.setAction("android.intent.action.VIEW");
				Uri content_url = Uri.parse(linkUrls[index]);
				intent.setData(content_url);
				intent.setClassName("com.android.chrome", "com.google.android.apps.chrome.Main");
				startActivity(intent);
stopService(new Intent(Setting.this,AudioService.class));
			//	webview.setVisibility(View.VISIBLE);
			//	loadUrl(webview,linkUrls[index]);
			}
		}
	};
	
	//private void loadUrl(final WebView view,final String url){
	//	view.loadUrl(url);
	//}
	
	/**
	 * 配置ImageLoder
	 */
	private void configImageLoader() {
		// 初始化ImageLoader
		@SuppressWarnings("deprecation")
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(options)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);		
	}
	
	
	private void initBatteryMonitor(){
		    IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
	        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
			intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
			intentFilter.addAction(Intent.ACTION_BATTERY_LOW);		
	        registerReceiver(batteryChangedReceiver, intentFilter);
	        initWifi();
	}
	
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(wifiReceiver);
		unregisterReceiver(batteryChangedReceiver);
       // webview.removeAllViews();
       // webview.destroy();
	};
	
    private BroadcastReceiver batteryChangedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                // 0 - 100
                int power = level * 100 / scale;
                // setLevel(int level): level �ķ�Χ�� 0 -10000
                clipDrawable.setLevel(calculateLevel(power));
            }if(Intent.ACTION_BATTERY_LOW.equals(intent.getAction())){
            	
            }
        }     
    };
    
	//////////////////
    private int calculateLevel(int progress) {
        int leftOffest = MyUtils.dip2px(this, 2);
        int powerLength = MyUtils.dip2px(this, 26.5f);// 40 px in hdpi
        int totalLength = MyUtils.dip2px(this, 30.5f);// 49 px in hdpi
        int level = (leftOffest + powerLength * progress / 100) * 10000 / totalLength;
        return level;
    }
	
	
	IntentFilter mFilter;
	private void initWifi(){
		mFilter = new IntentFilter();
		mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		mFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
		mFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		registerReceiver(wifiReceiver, mFilter);
	}
	
	BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (WifiManager.WIFI_STATE_CHANGED_ACTION
					.equals(intent.getAction())) {
				int wifiState = intent.getIntExtra(
						WifiManager.EXTRA_WIFI_STATE, 0);
				switch (wifiState) {
				case WifiManager.WIFI_STATE_ENABLED:
if(Utils.isNetWorkAvailable(Setting.this)){
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
	
	WifiConfiguration con_wifi;
	void showcon_wifi() {
		if (con_wifi != null) {
			int level = Math.abs(((WifiManager) getSystemService(WIFI_SERVICE))
					.getConnectionInfo().getRssi());
			iv_wifi_status.setImageLevel(level);

		}
	}
	
	/**
	 * 获取宝宝信息和现实信息
	 */
	private void getUserInfoandShow(){
	user_id.setText(BaseConfig.readProduct(Setting.this, "name")+getString(R.string.space_between_name_age)+BaseConfig.readProduct(Setting.this, "age")+getString(R.string.bady_age_transfer));
		if(BaseConfig.readProduct(Setting.this, "gender").equals("1")){
			user.setImageResource(R.drawable.user_title_male);
		 }else{
			 user.setImageResource(R.drawable.user_title);
		 }
	
	}
	
	Intent intents;
	OnClickListener ClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.wifi:
	             intents = new Intent(Setting.this, SettingWifiManager.class); 
				startActivity(intents);                               
				wifi.setImageResource(R.drawable.ic_setting_wifi);
				break;
			case R.id.sound:
				intents = new Intent(Setting.this, SettingSoundManager.class); 
				startActivity(intents);                              
				sound.setImageResource(R.drawable.ic_setting_sound);
				break;
			case R.id.protect:
				protect.setImageResource(R.drawable.ic_setting_protect);
				intents = new Intent(Setting.this, SettingProtectManager.class);  
				startActivity(intents);    
				break;
			case R.id.brightness:
               intents = new Intent(Setting.this, SettingBrightnessManager.class);  
				startActivity(intents);                               
				brightness.setImageResource(R.drawable.ic_setting_brightness);
				break;
			case R.id.update:
				update.setImageResource(R.drawable.ic_setting_update);
				Intent mIntent = new Intent( );
	            ComponentName comp = new ComponentName("com.adups.fota", "com.adups.fota.GoogleOtaClient");//20170411_wangjx_����intent����fota���� 
	            mIntent.setComponent(comp);                  //20170411_wangjx_����intent����fota����  	
	            startActivity(mIntent);
				break;
			case R.id.timer:
				intents = new Intent(Setting.this, SettingTimerManager.class);  //20170210_wangjx_����intent����ʱ�书��
				startActivity(intents);                                 //20170210_wangjx_����intent����ʱ�书��
				timer.setImageResource(R.drawable.ic_setting_timer);
				break;
			case R.id.back:
			//	Intent it = new Intent(Setting.this,HomePageActivity.class);
			//	startActivity(it);
			Setting.this.finish();
				break;
case R.id.restore_factory_setting:
		Intent ients = new Intent(Setting.this,RestoreFactory.class);
				startActivity(ients);
	
				break;

	case R.id.system_setting:
				Intent application =  new Intent(Settings.ACTION_APPLICATION_SETTINGS);  
		        startActivity(application);
				
				break;





			default:
				break;
			}
		}
	};
	
   DataBaseUtil databaseutil;
   protected void onResume() {
	   super.onResume();
	   int totalScore =  databaseutil.getTotalScore(Setting.this);
       if(totalScore>0){
       coin_sum.setText(totalScore+"");
       }else{
    	   coin_sum.setText(0+"");
       }
       getUserInfoandShow();

     if(!Utils.isNetWorkAvailable(Setting.this)){
    	   iv_wifi_status.setVisibility(View.INVISIBLE);
       }else{
    	   iv_wifi_status.setVisibility(View.VISIBLE);
       }
       
   };
   
    OnTouchListener touchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (v.getId()) {			
				case R.id.wifi:
					onTouchChange("wifi", event.getAction());  //20170209_wangjx_������ò˵�
					break;
				case R.id.sound:
					onTouchChange("sound", event.getAction()); //20170209_wangjx_������ò˵�
					break;
				case R.id.protect:
					onTouchChange("protect", event.getAction());  //20170209_wangjx_������ò˵�
					break;
				case R.id.brightness:
					onTouchChange("brightness", event.getAction());  //20170209_wangjx_������ò˵�
					break;
				case R.id.update:
					onTouchChange("update", event.getAction());  //20170209_wangjx_������ò˵�
					break;
				case R.id.timer:
					onTouchChange("timer", event.getAction());  //20170209_wangjx_������ò˵�
					break;
				case R.id.user:
					onTouchChange("user", event.getAction());
					break;
				default:
					break;
				}
			return false;
		}
	};
	
	void onTouchChange(String methodName, int eventAction) {
		if ("wifi".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				wifi.setImageResource(R.drawable.ic_setting_wifi_after);
	        }else if (eventAction == MotionEvent.ACTION_UP){
				Intent intent = new Intent(this, SettingWifiManager.class);  //20170210_wangjx_����intent����wifi����
				startActivity(intent);                                 //20170210_wangjx_����intent����wifi����
				wifi.setImageResource(R.drawable.ic_setting_wifi);
			}
		}else if ("sound".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				sound.setImageResource(R.drawable.ic_setting_sound_after);
	        }else if (eventAction == MotionEvent.ACTION_UP){
				Intent intent = new Intent(this, SettingSoundManager.class);  //20170209_wangjx_����intent������������
				startActivity(intent);                                 //20170209_wangjx_����intent������������
				sound.setImageResource(R.drawable.ic_setting_sound);
			}
		}else if ("protect".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				protect.setImageResource(R.drawable.ic_setting_protect_after);
	        }else if (eventAction == MotionEvent.ACTION_UP){
				protect.setImageResource(R.drawable.ic_setting_protect);
				Intent intent = new Intent(this, SettingProtectManager.class);  //20170209_wangjx_����intent�������ȹ���
				startActivity(intent);                                 //20170209_wangjx_����intent�������ȹ���
			}
		}else if ("update".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				update.setImageResource(R.drawable.ic_setting_update_after);
	        }else if (eventAction == MotionEvent.ACTION_UP){
				update.setImageResource(R.drawable.ic_setting_update);
				Intent mIntent = new Intent( );
	            ComponentName comp = new ComponentName("com.adups.fota", "com.adups.fota.GoogleOtaClient");//20170411_wangjx_����intent����fota���� 
	            mIntent.setComponent(comp);                  //20170411_wangjx_����intent����fota����  	
	            startActivity(mIntent);
			}
		}else if ("brightness".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				brightness.setImageResource(R.drawable.ic_setting_brightness_after);
	        }else if (eventAction == MotionEvent.ACTION_UP){
				Intent intent = new Intent(this, SettingBrightnessManager.class);  //20170209_wangjx_����intent�������ȹ���
				startActivity(intent);                                 //20170209_wangjx_����intent�������ȹ���
				brightness.setImageResource(R.drawable.ic_setting_brightness);
			}
		}else if ("timer".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				timer.setImageResource(R.drawable.ic_setting_timer_after);
	        }else if (eventAction == MotionEvent.ACTION_UP){
				Intent intent = new Intent(this, SettingTimerManager.class);  //20170210_wangjx_����intent����ʱ�书��
				startActivity(intent);                                 //20170210_wangjx_����intent����ʱ�书��
				timer.setImageResource(R.drawable.ic_setting_timer);
			}
		}else if("user".equals(methodName)){
			if(eventAction==MotionEvent.ACTION_DOWN){
			}else if(eventAction==MotionEvent.ACTION_UP){
				Intent intent = new Intent(this,SettingBabyBasicInfo.class);
				startActivity(intent);
			}
		}
	};
	
	private class myTask extends AsyncTask<String, Void, String>{
		@Override
		protected String doInBackground(String... arg0) {
			HttpURLConnection con= null;
			InputStream is = null;
			StringBuilder sbd = new StringBuilder();
			try {
				URL url = new URL(arg0[0]);
				con = (HttpURLConnection) url.openConnection();
				con.setConnectTimeout(5*1000);
				con.setReadTimeout(5*1000);
				con.setRequestMethod("GET");
				if(con.getResponseCode()==200){
					is = con.getInputStream();
					int next = 0;
					byte[] bt = new byte[1024];
					while((next = is.read(bt))>0){
						sbd.append(new String(bt,0,next));
					}
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(is!=null){
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(con!=null){
						con.disconnect();
					}
				}
			}
			return sbd.toString();
		}
		
		/**
		 * parse the json data and init them for webview to load.
		 */
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if(!result.equals("")){
			JSONObject obj = JSON.parseObject(result);

			if(!obj.equals("")){
			   content = obj.getJSONObject("content");
			}
//下面两个if判断是为了在有网络的时候，但是又没有获取到返回数据时候要用的，如果没有这样判断，返回的数据为空，继续执行下去就会报错。
			//	if(Utils.isNetWorkAvailable(Setting.this)){
			//       if(!content.equals("")){
		//			if(!content.getJSONArray("bannerList").equals("")){
			//	    new myTask().execute(MyApplication.access_ads_url);
			//		}
			//	}
				//return;
	//		}
			if(!Utils.isNetWorkAvailable(Setting.this)){
				loadLocalViews();
				return ;
			}
			if(content==null){
			   return ;
			}
			if(content.getJSONArray("bannerList").equals("")){
			return ;
			}
			JSONArray json_array = content.getJSONArray("bannerList");
			imageUrls = new String[json_array.size()];
			linkUrls = new String[json_array.size()];
			for(int i=0;i<json_array.size();i++){
				JSONObject son_son_obj = json_array.getJSONObject(i);
				String path = son_son_obj.getString("path");
				String link = son_son_obj.getString("link");
				linkUrls[i] = link;
				imageUrls[i] = path;
			}
			if(imageUrls.length>0){
				loadViews();
			}
			
		  }else{
			 new myTask().execute(MyApplication.access_ads_url);
		  }
		}
	}

		JSONObject content =null;

}
