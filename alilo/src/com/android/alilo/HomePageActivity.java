package com.android.alilo;

/**
 * 主界面
 * @author wangjx
 *
 */

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.os.UserHandle;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.GridView;
import com.android.model.ApkItem;
import android.os.BatteryManager;

import com.android.alilo.setting.Setting;
import com.android.dao.DataBaseUtil;
import com.android.model.UserInfo;
import com.android.myUtils.BaseConfig;
import android.provider.Settings;
import android.widget.AdapterView.OnItemClickListener;
import com.android.adapter.SecondScreenGridviewAdapter;
import android.widget.AdapterView;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import com.android.myUtils.SharedPreferencesUtil;
import android.content.ComponentName;
import com.android.myUtils.Utils;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import com.android.myUtils.MyApplication;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.media.MediaPlayer;
import android.media.AudioManager;
import android.media.SoundPool;
import java.util.HashMap;
import com.android.model.myAnimation;
import android.os.Message;


public class HomePageActivity extends Activity implements OnItemClickListener{
	
	private ViewPager mViewPager;
	private List<View> mViews;
	private List<ImageView> mDots = new ArrayList<ImageView>();
	private MyPagerAdapter myPagerAdapter;

	private LinearLayout mLinearLayout;
	private LayoutInflater mInflater;
	
	private AudioService audioService;
	
	//batteryView
	private ClipDrawable clipDrawable;
	ImageView image_battery;
		
	//imageView 
	ImageView imageViewljsw;
	AnimationDrawable ljsw;
	ImageView imageViewczlxt;
	AnimationDrawable czl;

	boolean jump_to_boyue_launcher_flag = false;
	
	ImageView imageView1;
	ImageView imageView2;
	ImageView imageView3;
	ImageView imageView4;
	ImageView imageView5;
	ImageView imageView6;
	ImageButton setting;

ImageView imageBattery;
	
	ImageView imageView7;
	TextView user_id;
	//wifiView
	ImageView iv_wifi_status; // 连接的wifi图标状态	
	WifiConfiguration con_wifi;
	IntentFilter mFilter;
	IntentFilter mFilter2;	
	
	//userView
	ImageView user;
	ImageView coin;
	
	private DataBaseUtil databaseutil;
	private TextView coin_sum;
	private ImageView age_title;
	private Intent intent;
	public static Typeface face;
	private GridView the_second_app_list;
	private GridView the_third_app_list;
	private GridView the_fourth_app_list;
    private SecondScreenGridviewAdapter sAdapter;
	//	public static boolean screenonoff = false;

	float    x1;
	float y1;


		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		SharedPreferencesUtil.setRunningFirstTime(HomePageActivity.this, false);
		face = Typeface.createFromAsset(getAssets(), "fonts/W7.TTF");
		initView();
//initAnimationsImagesLjsw();
//initAnimationsImagesCZL();
//initAnimationsDurationCzl();



		databaseutil = new DataBaseUtil();
		//battery
		imageBattery = (ImageView)findViewById(R.id.image_battery);
	  // LayerDrawable layerDrawable = (LayerDrawable) imageBattery.getDrawable();
	    //clipDrawable = (ClipDrawable)layerDrawable.findDrawableByLayerId(R.id.clip_drawable);
	    
	    //imageView
		//imageViewlj = (ImageView)findViewById(R.id.imageViewlj);
		imageViewljsw.setOnTouchListener(touchListener);
		imageViewljsw.setEnabled(true);
  

	imageViewljsw.setBackgroundResource(R.drawable.imageviewlj_anim);
		ljsw = (AnimationDrawable) imageViewljsw.getBackground();
	
	ljsw.setOneShot(false);
	

	
		
		//imageViewcz = (ImageView)findViewById(R.id.imageViewcz);
		imageViewczlxt.setOnTouchListener(touchListener);
		imageViewczlxt.setEnabled(true);



	imageViewczlxt.setBackgroundResource(R.drawable.imageviewcz_anim);
	czl = (AnimationDrawable) imageViewczlxt.getBackground();
	czl.setOneShot(false);


	//startAnimation1();
	//	startAnimation2();
		
//		imageView1 = (ImageView)findViewById(R.id.imageView1);
		imageView1.setOnTouchListener(touchListener);
		imageView1.setEnabled(true);
		
		//imageView2 = (ImageView)findViewById(R.id.imageView2);
		imageView2.setOnTouchListener(touchListener);
		imageView2.setEnabled(true);
		
		//imageView3 = (ImageView)findViewById(R.id.imageView3);
		imageView3.setOnTouchListener(touchListener);
		imageView3.setEnabled(true);
		
		//imageView4 = (ImageView)findViewById(R.id.imageView4);
		imageView4.setOnTouchListener(touchListener);
		imageView4.setEnabled(true);
		
		//imageView5 = (ImageView)findViewById(R.id.imageView5);
		imageView5.setOnTouchListener(touchListener);
		imageView5.setEnabled(true);
		
		//imageView6 = (ImageView)findViewById(R.id.imageView6);
		imageView6.setOnTouchListener(touchListener);
		imageView6.setEnabled(true);	
		
		//imageView7.setOnTouchListener(touchListener);
//		imageView7.setEnabled(true);
				
	//	setting = (ImageButton)findViewById(R.id.setting);
	//	setting.setOnTouchListener(touchListener);
	//	setting.setEnabled(true);
		
		//wifi
		iv_wifi_status = (ImageView) findViewById(R.id.iv_wifi_status);
		iv_wifi_status.setVisibility(View.GONE);
		mFilter = new IntentFilter();
		mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		mFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
		mFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		registerReceiver(mReceiver, mFilter);
		handler.post(refreshRunnable);
		
		//user
		user = (ImageView)findViewById(R.id.user);
		user.setOnTouchListener(touchListener);
		user.setEnabled(true);
		
		coin = (ImageView)findViewById(R.id.coin);	
		coin_sum = (TextView) findViewById(R.id.coin_sum);
		coin_sum.setTypeface(face);
		mFilter = new IntentFilter();
		mFilter.addAction("android.intent.action.open.bg_music"); //20170412_wangjx_注册广播_安卓系统发送
		mFilter.addAction("android.intent.action.close.bg_music"); //20170412_wangjx_注册广播_安卓系统发送
		mFilter.addAction("killAllProgress");
		mFilter.addAction("android.intent.action.f2.button");
		mFilter.addAction("startLauncher3FromAlilo");
		registerReceiver(keycodeBR, mFilter);

//here means monitor the screen state of on and off.

		mFilter = new IntentFilter();
		mFilter.addAction(Intent.ACTION_SCREEN_OFF);
		mFilter.addAction(Intent.ACTION_SCREEN_ON);
		registerReceiver(screenOnOff, mFilter);

//在这里加入检测授权功能  开始
       IntentFilter mmFilter = new IntentFilter();
	   mmFilter.addAction("com.booyue.service");
		registerReceiver(authorizedStates, mmFilter);
		
		if(SharedPreferencesUtil.getAuthrized(HomePageActivity.this)){
			
		}else{
			Intent intent = new Intent("com.booyue.user");
			intent.putExtra("flag", 16);
			sendBroadcast(intent);
		}

//检测授权功能 结束
     //   Intent intent = new Intent(this, AudioService.class);
     //   startService(intent);
               
	//	mFilter2 = new IntentFilter();
	//	mFilter2.addAction("com.booyue.l1.score.update");
	//	registerReceiver(keycodeBR2, mFilter2);
		ProtectWakeEnable=new File(MyApplication.WRITEPROTECTEYEFILEPATH);
		//启动语音服务
		startAiSpeech();
		startProctedEysService();
	
		//getUserInfoandShow();
	//openProtect();

    }	

	  @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
    	//if(audioIntent!=null){
    	//	stopService(audioIntent);
    	//	audioIntent = null;
    	//}


    	
    }

Intent audioIntent = null ;
	    private BroadcastReceiver screenOnOff = new BroadcastReceiver(){
    	public void onReceive(Context arg0, Intent arg1) {
			
Log.v("jack", "monitor the screen is changed.....");
    		if(arg1.getAction().equals(Intent.ACTION_SCREEN_ON)){
				//screenonoff = true;
    			//Intent its = new Intent("android.intent.action.open.bg_music");
    		  //	sendBroadcast(its);

				if(serviceIntent!=null){
                     stopService(serviceIntent);
					 serviceIntent = null;
                     serviceIntent = new Intent(HomePageActivity.this,MonitorService.class);
    				 startService(serviceIntent);
    			}else {
    				serviceIntent = new Intent(HomePageActivity.this,MonitorService.class);
    				startService(serviceIntent);
    			}
if(AliloisForeGround(HomePageActivity.this)){
	if(audioIntent!=null){
startService(audioIntent);
	}else{
	audioIntent = new Intent(HomePageActivity.this,AudioService.class);
	startService(audioIntent);
	}
			//startService(new Intent(HomePageActivity.this,AudioService.class));
}
    		}


    		if(arg1.getAction().equals(Intent.ACTION_SCREEN_OFF)){
	if(serviceIntent!=null){
                     stopService(serviceIntent);
					 serviceIntent = null;
                    
    			}

				//screenonoff = false;
				if(audioIntent!=null){
    		   stopService(audioIntent);
			 
				}else{
				      audioIntent = new Intent(HomePageActivity.this,AudioService.class);
        stopService(audioIntent);
		audioIntent = null;
				}
    		}
    	};
    };

private File ProtectWakeEnable=null;
		// 开启护眼功能模块
	public void openProtect() {
		try {
            FileOutputStream fos = new FileOutputStream(ProtectWakeEnable);
            fos.write('1');
            fos.flush();
            fos.close();

		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
            	e.printStackTrace();
		}
	}

		//检测授权状态的广播
	BroadcastReceiver authorizedStates = new BroadcastReceiver(){
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			if(arg1==null){
			return;
			}
		   String action = arg1.getAction();
		   if(action.equals("com.booyue.service")){
			   int flag = arg1.getIntExtra("flag", -1);
			   if(flag ==49){
		
				   String isAuth = arg1.getStringExtra("isAuth");
				   
				   if("10".equals(isAuth)){
					   SharedPreferencesUtil.setAuthrized(HomePageActivity.this, true);
					   coin_sum.setText("0");
				   }else if("11".equals(isAuth)){
					   SharedPreferencesUtil.setAuthrized(HomePageActivity.this, false);
					   coin_sum.setText("00");
				   }
			   }else if(flag ==21){
				   SharedPreferencesUtil.setAuthrized(HomePageActivity.this, true);
				   coin_sum.setText("0");
			   }
		   }
		}
	};


	/**
	 * 得出宝宝年龄和名字。
	 */
	private void getUserInfoandShow(){
	user_id.setText(BaseConfig.readProduct(HomePageActivity.this, "name")+getString(R.string.space_between_name_age)+BaseConfig.readProduct(HomePageActivity.this, "age")+getString(R.string.bady_age_transfer));
	if(BaseConfig.readProduct(HomePageActivity.this, "gender").equals("1")){
		user.setImageResource(R.drawable.user_title_male);
	 }else{
		 user.setImageResource(R.drawable.user_title);
	 }
	
	}
	
	public static Intent serviceIntent = null;
	//启动护眼提示服务
	private  void startProctedEysService(){
       Settings.System.putInt(getContentResolver(),
							"config_disablehomewhensleep", 0);
	   serviceIntent = new Intent(HomePageActivity.this,MonitorService.class);
		SharedPreferences shareData = this.getSharedPreferences("protect_eye", Context.MODE_PRIVATE);
		int temp_flag =shareData.getInt("flag", 0);
		if(temp_flag ==0){
		
		startService(serviceIntent);
		openProtect();
		}


		//Settings.System.putInt(getContentResolver(),
		//					"config_disablehomewhensleep", 0);
		//serviceIntent = new Intent(HomePageActivity.this,MonitorService.class);
		//startService(serviceIntent);
	}
	
	private void  startAiSpeech(){
		 Intent intent = new Intent();
         intent.setAction("com.booyue.service");
         intent.setPackage("com.booyue.l1");
         startService(intent);//这是就是启动我服务的代码

	}
	
	private void initView() {  
		mViewPager = (ViewPager) findViewById(R.id.view_container);
		user_id = (TextView) findViewById(R.id.user_id);
		user_id.setTypeface(face);
		age_title = (ImageView) findViewById(R.id.age_title);
		mInflater = getLayoutInflater();
		mLinearLayout = (LinearLayout) findViewById(R.id.viewGroup);
		// 初始化页面
		mViews = new ArrayList<View>();
		
		View view1 = mInflater.inflate(R.layout.fragment_fragment_a, null);
		imageViewljsw = (ImageView) view1.findViewById(R.id.imageViewljsw);
		imageViewczlxt = (ImageView) view1.findViewById(R.id.imageViewczlxt);	
		imageView1 = (ImageView) view1.findViewById(R.id.imageView1);
		imageView2 = (ImageView) view1.findViewById(R.id.imageView2);
		imageView3 = (ImageView) view1.findViewById(R.id.imageView3);
		imageView4 = (ImageView) view1.findViewById(R.id.imageView4);
		imageView5 = (ImageView) view1.findViewById(R.id.imageView5);
		imageView6 = (ImageView) view1.findViewById(R.id.imageView6);	
				
		View view2 = mInflater.inflate(R.layout.fragment_fragment_b, null);
		View view3 = mInflater.inflate(R.layout.fragment_fragment_c, null);
		View view4 = mInflater.inflate(R.layout.fragment_fragment_d, null);

        the_second_app_list = (GridView) view2.findViewById(R.id.the_second_app_list);
		the_third_app_list = (GridView) view3.findViewById(R.id.the_second_app_list);
		the_fourth_app_list = (GridView) view4.findViewById(R.id.the_second_app_list);
       initSecondScreenData();
	   initThirdScreenData();
       initFourthScreenData();
		sAdapter = new SecondScreenGridviewAdapter(HomePageActivity.this, mDatas);
		the_second_app_list.setSelector(new ColorDrawable(Color.TRANSPARENT));
		the_second_app_list.setAdapter(sAdapter);
		the_second_app_list.setOnItemClickListener(this);
sAdapter =  new SecondScreenGridviewAdapter(HomePageActivity.this, TDatas);
the_third_app_list.setAdapter(sAdapter);
	the_third_app_list.setSelector(new ColorDrawable(Color.TRANSPARENT));
sAdapter =  new SecondScreenGridviewAdapter(HomePageActivity.this, FDatas);
the_fourth_app_list.setAdapter(sAdapter);
	the_fourth_app_list.setSelector(new ColorDrawable(Color.TRANSPARENT));

		the_third_app_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				
			case 0:
					stopService(new Intent(HomePageActivity.this,AudioService.class));
			doStartApplicationWithPackageName("com.o2interaction.MagicZoo");
				break;
			case 1:
					stopService(new Intent(HomePageActivity.this,AudioService.class));
			doStartApplicationWithPackageName("com.o2interaction.educationMath");
				break;
			case 2:
					stopService(new Intent(HomePageActivity.this,AudioService.class));
			doStartApplicationWithPackageName("com.o2interaction.educationEnglish");
				break;
			default:
				break;
			}
			}
		});
		
		the_fourth_app_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
					switch (arg2) {
			case 0:
						stopService(new Intent(HomePageActivity.this,AudioService.class));
			doStartApplicationWithPackageName("com.qiyi.video.child");
				break;
			case 1:
				stopService(new Intent(HomePageActivity.this,AudioService.class));
			doStartApplicationWithPackageName("com.booyue.l1_hht");
				break;
			case 2:
					stopService(new Intent(HomePageActivity.this,AudioService.class));
			doStartApplicationWithPackageName("com.youkuchild.android");
				break;
			case 3:
				stopService(new Intent(HomePageActivity.this,AudioService.class));
			doStartApplicationWithPackageName("com.booyue.appmarket");
				break;	
			case 4:
				stopService(new Intent(HomePageActivity.this,AudioService.class));
				doStartApplicationWithPackageName("com.tencent.qqlivekid");
				break;
			case 5:
				stopService(new Intent(HomePageActivity.this,AudioService.class));
				doStartApplicationWithPackageName("com.tencent.devicedemo");
				break;

			default:
				break;
			}
				
			}
		});

//		imageView7 = (ImageView) view2.findViewById(R.id.imageView7);
		
		mViews.add(view1);
		mViews.add(view2);
		mViews.add(view3);
		mViews.add(view4);

	
		myPagerAdapter = new MyPagerAdapter(mViews);
		mViewPager.setAdapter(myPagerAdapter);

		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			
					@Override
					public void onPageScrolled(int positions, float v, int i1) {


					}

					@Override
					public void onPageSelected(int position) {


                  if(position==3){
							jump_to_boyue_launcher_flag = true;
						}else{
							jump_to_boyue_launcher_flag = false;
						}

						// for-each循环将所有的dot设置为dot_normal
						for (ImageView imageView : mDots) {
							imageView.setImageResource(R.drawable.pot2);
						}
						// 设置当前显示的页面的dot设置为dot_focused
						mDots.get(position).setImageResource(
								R.drawable.pot);
						if(position==1||position==2||position==3){
						hideComponent();
						}else{
					    showComponent();	
						}
					}

					@Override
					public void onPageScrollStateChanged(int i) {

					}
				});		
		
	}


    private List<ApkItem> mDatas;
	private List<ApkItem> TDatas;
	private List<ApkItem> FDatas;
	private ApkItem mitem;
	private int second_screen_apk_title[] = {R.drawable.bobismallapkimage,R.drawable.bobimiddleapkimage,R.drawable.bobilargeapkimage};
	private int second_screen_apk_image[] = {R.drawable.bobismall,R.drawable.bobimiddle,R.drawable.bobilarge};

    private int third_sereen_apk_title[] = {R.drawable.mofabaike_up,R.drawable.shuxuezhilv_up,R.drawable.ar_english_up};
	private int third_screen_apk_image[] = {R.drawable.mofabaike_title,R.drawable.shuxuezhilv_title,R.drawable.ar_english_title};
	
	private int fourth_sereen_apk_title[] = {R.drawable.aiqiyi_up,R.drawable.hhtjgs_title,R.drawable.hht_xxyk,R.drawable.hht_shangcheng_up,R.drawable.hht_xiaoqieleyuan_up,R.drawable.hht_shipinliaotian_up};
	private int fourth_screen_apk_image[] = {R.drawable.aiqiyi_title,R.drawable.hhtjgs,R.drawable.hht_xxyk_title,R.drawable.hht_shangcheng_title,R.drawable.hht_xiaoqieleyuan_title,R.drawable.hht_shipinliaotian_title};

	/**
	 * 初始化第二屏的apk数据
	 */
	private void initSecondScreenData(){
		mDatas = new ArrayList<>();
		for(int i=0;i<second_screen_apk_title.length;i++){
			mitem = new ApkItem();
			mitem.setApkImage(second_screen_apk_image[i]);
			mitem.setApkName(second_screen_apk_title[i]);
			mDatas.add(mitem);
		}
	}

		/**
	 * 初始化第三屏的apk数据
	 */
	private void initThirdScreenData(){
		TDatas = new ArrayList<>();
		for(int j=0;j<third_screen_apk_image.length;j++){
			mitem = new ApkItem();
			mitem.setApkImage(third_screen_apk_image[j]);
			mitem.setApkName(third_sereen_apk_title[j]);
			TDatas.add(mitem);
		}
	}

	/**
	 * 初始化第四屏apk数据
	 * 
	 */
	private void initFourthScreenData(){
		FDatas = new ArrayList<>();
		for(int i=0;i<fourth_screen_apk_image.length;i++){
			mitem = new ApkItem();
			mitem.setApkImage(fourth_screen_apk_image[i]);
			mitem.setApkName(fourth_sereen_apk_title[i]);
			FDatas.add(mitem);
		}
	}
	
	
	private void hideComponent(){
		coin_sum.setVisibility(View.GONE);
		user.setVisibility(View.GONE);
		coin.setVisibility(View.GONE);
		user_id.setVisibility(View.GONE);
		age_title.setVisibility(View.GONE);
	}
	
	private void showComponent(){
		coin_sum.setVisibility(View.VISIBLE);
		user.setVisibility(View.VISIBLE);
		coin.setVisibility(View.VISIBLE);
		user_id.setVisibility(View.VISIBLE);
		age_title.setVisibility(View.VISIBLE);
	}
	public class MyPagerAdapter extends PagerAdapter {

		private List<View> mViews;// 两个布局的集合

		/*
		 * 通过构造器获得数据
		 */
		public MyPagerAdapter(List<View> mViews) {
			this.mViews = mViews;
		}

		@Override
		public int getCount() {
			return mViews.size();// 显示的布局的数量，我们这里为两个。
		}

		@Override
		public boolean isViewFromObject(View view, Object o) {
			return view == o;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 增加View
			container.addView(mViews.get(position));
			//if(position==1){
			//	Log.v("jack", "进入第二屏");
			//}
			return mViews.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// 删除View
			container.removeView(mViews.get(position));
		}
	}

	/**
	 * 将dp转化为px
	 */
	public int Dp2Px(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}



	
	OnTouchListener touchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.v("direction","direction");
				switch (v.getId()) {
				case R.id.imageViewljsw:
					onTouchChange("imageViewlj", event.getAction());
					break;
				case R.id.imageViewczlxt:
					onTouchChange("imageViewcz", event.getAction());
					break;
				case R.id.imageView1:
					Log.v("jack", "imageview1_sxkj");
					onTouchChange("imageView1", event.getAction());
					break;
				case R.id.imageView2:
					Log.v("jack", "imageview1_ydsgc");
					onTouchChange("imageView2", event.getAction());
					break;
				case R.id.imageView3:
					Log.v("jack", "imageview3_zzltx");
					onTouchChange("imageView3", event.getAction());
					break;
				case R.id.imageView4:
					Log.v("jack", "imageview4_dcclh");
					onTouchChange("imageView4", event.getAction());
					break;
				case R.id.imageView5:
					Log.v("jack", "imageview5_cyqqb");
					onTouchChange("imageView5", event.getAction());
					break;
				case R.id.imageView6:
					Log.v("jack", "imageview6_yycz");
					onTouchChange("imageView6", event.getAction());
					break;
//				case R.id.imageView7:
//					Log.v("jack", "imageview7");
//					onTouchChange("imageView7", event.getAction());
//					break;
				case R.id.setting:
					onTouchChange("setting", event.getAction());
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
		if ("imageViewlj".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {	
		        /*SharedPreferences sharedata = getSharedPreferences(LjswNrJs.DATA, 0);  
		        int nData = sharedata.getInt(LjswNrJs.CONTENT, LjswNrJs.nIsReminder);  
		        if (LjswNrJs.REMINDER_YES == nData)  
		        {  
		            Intent intent = new Intent(HomePageActivity.this,LjswNrJs.class);  
		            startActivity(intent);  
		        }  
		        else if (LjswNrJs.REMINDER_NO == nData)  
		        {  
		            Intent intent = new Intent(HomePageActivity.this,TestActivityLJSW.class);  
		            startActivity(intent);  
		        } */ 
		        ////////////////////////////////////////////////////////////////////////////
	        }else if (eventAction == MotionEvent.ACTION_UP){
		        SharedPreferences sharedata = getSharedPreferences(LjswNrJs.DATA, 0);  
		        int nData = sharedata.getInt(LjswNrJs.CONTENT, LjswNrJs.nIsReminder);  
		        if (LjswNrJs.REMINDER_YES == nData)  
		        {  
		        	Log.v("jack", "REMINDER_YES");
		             intent = new Intent(HomePageActivity.this,LjswNrJs.class);  
		            startActivity(intent);  
		        }  
		        else if (LjswNrJs.REMINDER_NO == nData)  
		        {  
		             intent = new Intent(HomePageActivity.this,TestActivityLJSW.class);  
		            startActivity(intent);  
		        }  
			}else if (eventAction == MotionEvent.ACTION_MOVE) {
			}
		}else if ("imageViewcz".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				//
	        }else if (eventAction == MotionEvent.ACTION_UP){
	        	 SharedPreferences sharedata = getSharedPreferences(CZLJS.DATA, 0);  
			        int nData = sharedata.getInt(CZLJS.CONTENT, CZLJS.nIsReminder);  
			        if (CZLJS.REMINDER_YES == nData)  
			        {  
			             intent = new Intent(HomePageActivity.this,CZLJS.class);  
			            startActivity(intent);  
			        }  
			        else if (CZLJS.REMINDER_NO == nData)  
			        {  
			             intent = new Intent(HomePageActivity.this,TestActivityCZLXT.class);  
			            startActivity(intent);  
			        }
//					Intent intent = new Intent(this, TestActivityCZLXT.class);  //20170105_wangjx_启动intent开启二级菜单创造力系统
//					startActivity(intent);                                 //20170105_wangjx_启动intent开启二级菜单创造力系统
				}
		}else if ("imageView1".equals(methodName)) {  //数学与空间
			if (eventAction == MotionEvent.ACTION_DOWN) {
//				Intent intent = new Intent(this, TestActivitySXKJ.class);  //20170105_wangjx_启动intent开启二级菜单数学空间
//				startActivity(intent);                                 //20170105_wangjx_启动intent开启二级菜单数学空间
	        }else if (eventAction == MotionEvent.ACTION_UP){

 	             SharedPreferences sharedata = getSharedPreferences(SxkjJs.DATA, 0);  
			        int nData = sharedata.getInt(SxkjJs.CONTENT, SxkjJs.nIsReminder);  
	        	
			        if (SxkjJs.REMINDER_YES == nData)  
			        {  
			        	intent = new Intent(HomePageActivity.this,SxkjJs.class);  
			            startActivity(intent);  
			        }  
			        else if (CZLJS.REMINDER_NO == nData)  
			        {  
			        	intent = new Intent(HomePageActivity.this,TestActivitySXKJ.class);  
			            startActivity(intent);  
			        }
					
				
				}
		}else if ("imageView2".equals(methodName)) { //引导式观察
			if (eventAction == MotionEvent.ACTION_DOWN) {
				//Intent intent = new Intent(this, TestActivityYDSGC.class);  //20170320_wangjx_启动intent开启二级菜单引导式观察
				//startActivity(intent);                                 //20170320_wangjx_启动intent开启二级菜单引导式观察
	        }else if (eventAction == MotionEvent.ACTION_UP){
	        	 SharedPreferences sharedata = getSharedPreferences(CZLJS.DATA, 0);  
			        int nData = sharedata.getInt(YDSGCJS.CONTENT, YDSGCJS.nIsReminder);  
	        	
			        if (CZLJS.REMINDER_YES == nData)  
			        {  
			             intent = new Intent(HomePageActivity.this,YDSGCJS.class);  
			            startActivity(intent);  
			        }  
			        else if (CZLJS.REMINDER_NO == nData)  
			        {  
			             intent = new Intent(HomePageActivity.this,TestActivityYDSGC.class);  
			            startActivity(intent);  
			        }
				}
		}else if ("imageView3".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				//Intent intent = new Intent(this, TestActivityZZLXL.class);  //20170105_wangjx_启动intent开启二级菜单专注力训练
				//startActivity(intent);                                 //20170105_wangjx_启动intent开启二级菜单专注力训练
	        }else if (eventAction == MotionEvent.ACTION_UP){
	       	 SharedPreferences sharedata = getSharedPreferences(CZLJS.DATA, 0);  
		        int nData = sharedata.getInt(ZZLTXJS.CONTENT, ZZLTXJS.nIsReminder);  
     	
		        if (CZLJS.REMINDER_YES == nData)  
		        {  
		             intent = new Intent(HomePageActivity.this,ZZLTXJS.class);  
		            startActivity(intent);  
		        }  
		        else if (CZLJS.REMINDER_NO == nData)  
		        {  
		             intent = new Intent(HomePageActivity.this,TestActivityZZLXL.class);  
		            startActivity(intent);  
		        }
	        	
			}
		}else if ("imageView4".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {               //多彩创意画
			//	Toast.makeText(HomePageActivity.this, getString(R.string.not_opened_now), Toast.LENGTH_SHORT).show();
	        }else if (eventAction == MotionEvent.ACTION_UP){
				stopService(new Intent(HomePageActivity.this,AudioService.class));
				doStartApplicationWithPackageName("org.cocos2d.creativePainting");
	        	
			}
		}else if ("imageView5".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {  //创意七巧板
				//Toast.makeText(HomePageActivity.this,  getString(R.string.not_opened_now), Toast.LENGTH_SHORT).show();
	        }else if (eventAction == MotionEvent.ACTION_UP){
	        	stopService(new Intent(HomePageActivity.this,AudioService.class));
				doStartApplicationWithPackageName("org.cocos2d.creativeTangram");
			}
		}else if ("imageView6".equals(methodName)) {      //音乐创造
			if (eventAction == MotionEvent.ACTION_DOWN) {
			//	Toast.makeText(HomePageActivity.this,  getString(R.string.not_opened_now), Toast.LENGTH_SHORT).show();
	        }else if (eventAction == MotionEvent.ACTION_UP){
	        	stopService(new Intent(HomePageActivity.this,AudioService.class));
				doStartApplicationWithPackageName("org.cocos2d.musicMaking");
			}
		}else if ("imageView7".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
	        }else if (eventAction == MotionEvent.ACTION_UP){
//				Intent intent = new Intent(this, TestActivityPDTL.class);  //20170105_wangjx_启动intent开启二级菜单判断推理
//				startActivity(intent);                                 //20170105_wangjx_启动intent开启二级菜单判断推理
			}
		}else if ("setting".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) { 
//				Intent intent = new Intent(this, Setting.class);  //20170209_wangjx_启动intent开启设置界面
//				startActivity(intent);        //20170209_wangjx_启动intent开启设置界面
	        }else if (eventAction == MotionEvent.ACTION_UP){
	        	// TODO Auto-generated method stub
	        //	Intent intent = new Intent(this, Setting.class);  //20170209_wangjx_启动intent开启设置界面
			//	startActivity(intent);        //20170209_wangjx_启动intent开启设置界面
			}
		}else if ("user".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				
	        }else if (eventAction == MotionEvent.ACTION_UP){
//	        	Intent intent = new Intent(this,ConfigureUserInfo.class);
//	        	startActivity(intent);
intent = new Intent(this, Setting.class);  //20170209_wangjx_����intent�������ý���
				startActivity(intent);  

			}
		}										
	};

	    private boolean AliloisForeGround(Context context){
    	ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesss = activityManager.getRunningAppProcesses();
        for(RunningAppProcessInfo appProcess:appProcesss){
        	if(appProcess.importance==RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
        		if(appProcess.processName.equals("com.android.alilo")){
        			return true;
        		}
        	}else{
        		return false;
        	}
        }
         return false;
    }
	
	///////////////////begin_20161222_wangjx_读取系统电池显示
    private int calculateLevel(int progress) {
        int leftOffest = MyUtils.dip2px(this, 2);
        int powerLength = MyUtils.dip2px(this, 26.5f);// 40 px in hdpi
        int totalLength = MyUtils.dip2px(this, 30.5f);// 49 px in hdpi
        int level = (leftOffest + powerLength * progress / 100) * 10000 / totalLength;
        return level;
    }


			@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
				Log.v("direction","===");
        if(event.getAction() == MotionEvent.ACTION_DOWN) {  
            //当手指按下的时候  
            x1 = event.getX();  
            y1 = event.getY();  
			Log.v("direction",x1+"==="+y1);
        }  
        if(event.getAction() == MotionEvent.ACTION_UP) {  
            //当手指离开的时候  
          float  x2 = event.getX();  
           float y2 = event.getY();  
            if(y1 - y2 > 50) {  
                //Toast.makeText(HomePageActivity.this, "向上滑", Toast.LENGTH_SHORT).show();  
            } else if(y2 - y1 > 50) {  
                //Toast.makeText(HomePageActivity.this, "向下滑", Toast.LENGTH_SHORT).show();  
            } else if(x1 - x2 > 30&&jump_to_boyue_launcher_flag) {  
               // Toast.makeText(HomePageActivity.this, "向左滑", Toast.LENGTH_SHORT).show(); 
			   //here call the target app.
				//if(jump_to_boyue_launcher_flag){
					//===================2017-12-17add================
					  if(SharedPreferencesUtil.getPageCount(this)>0){
				 doStartApplicationWithPackageName("com.booyue.launcher");
			sendBroadcast(new Intent("android.intent.action.close.bg_music"));

				//Intent intentz = new Intent(Intent.ACTION_MAIN);  
				//intentz.addCategory(Intent.CATEGORY_LAUNCHER);              
				//ComponentName cn = new ComponentName("com.booyue.launcher", "com.booyue.launcher.MainActivity");              
				//intentz.setComponent(cn);  
				//startActivity(intentz); 
				overridePendingTransition(R.anim.slide_right_in,R.anim.slide_left_out);
				//}
					  }

            } else if(x2 - x1 > 50) {  
                //Toast.makeText(HomePageActivity.this, "向右滑", Toast.LENGTH_SHORT).show();  
            }  
        }  
		
		return super.dispatchTouchEvent(event);
	}



	
    @Override
    protected void onResume() {
        super.onResume();
		//=============================================
		//==================add 2017-12-16===========================
	// 通过循环动态的添加点。
	Log.v("jack","this is HomePageActivity method of resume...");
		mDots = null;
		mDots = new ArrayList<ImageView>();
	    mLinearLayout.removeAllViews();
		int tempPageCount =  SharedPreferencesUtil.getPageCount(this);
		for (int i = 0; i <(4+tempPageCount); i++) {
//for (int i = 0; i < 10; i++) {
			ImageView imageView = new ImageView(this);
			int width = Dp2Px(this, 10);
			int heigth = Dp2Px(this, 10);
			int margin = Dp2Px(this, 10);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					width, heigth);
			params.setMargins(5, 5, 5, 5);// 设置margin,也就是外边距。
			imageView.setLayoutParams(params);// 传入参数params设置宽和高
			imageView.setImageResource(R.drawable.pot2);// 设置图片
			mLinearLayout.addView(imageView);// 将图片添加到布局中
			// 将dot添加到dots集合中
			mDots.add(imageView);
		}
		
		 if(jump_to_boyue_launcher_flag){
   // mViewPager.setCurrentItem(3);
	mDots.get(3).setImageResource(R.drawable.pot);
	}else{
	mDots.get(0).setImageResource(R.drawable.pot);// 设置启动后显示的第一个点
	}
        //=============================================
		//=============================================
		//invoke the method to enable the home key.
DisableHomeKeyForMainActivity();
//open the background music defaultly.
//Intent intent2s = new Intent(HomePageActivity.this, AudioService.class);
			  //      startService(intent2s);
			 // sendBroadcast(new Intent("android.intent.action.open.bg_music"));

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
		intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
		intentFilter.addAction(Intent.ACTION_BATTERY_LOW);		
        registerReceiver(batteryChangedReceiver, intentFilter);
        //在此方法里面更新总积分。
       int totalScore =  databaseutil.getTotalScore(HomePageActivity.this);
    //   Log.v("jack", "totalScore:"+totalScore);
       if(totalScore>=0){
       coin_sum.setText(totalScore+"");
       }else{
    	 if(SharedPreferencesUtil.getAuthrized(HomePageActivity.this)){
    	   coin_sum.setText(0+"");
    	   }else{
    		   coin_sum.setText("00");
    	   }
       }
    

   if(!Utils.isNetWorkAvailable(HomePageActivity.this)){
    	   iv_wifi_status.setVisibility(View.INVISIBLE);
       }else{
    	   iv_wifi_status.setVisibility(View.VISIBLE);
       }
getUserInfoandShow();
//context.sendBroadcastAsUser(newIntent, new UserHandle(UserHandle.USER_CURRENT));
	   	//打开背景乐

	sendBroadcast(new Intent("android.intent.action.open.bg_music"));
//}
	Log.v("jack","OMG");
	Log.v("jack","OMG");
	Log.v("jack","OMG");
	Log.v("jack","OMG");
	sendBroadcast(new Intent("killAllProgress"));
			  // if(isForeGround(HomePageActivity.this)){
        	  	//kill all process except the alilo and the speech service progress.
	
//try {
		//		Thread.sleep(30);
		//	} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
	//	}
	//	killAllProgress(HomePageActivity.this);
    //    }

	if(mViewPager!=null&&!jump_to_boyue_launcher_flag){
	mViewPager.setCurrentItem(0);
	}else if(jump_to_boyue_launcher_flag){
    mViewPager.setCurrentItem(3);
	mDots.get(3).setImageResource(R.drawable.pot);
	}


if(ljsw==null){
		ljsw = (AnimationDrawable) imageViewljsw.getBackground();
		ljsw.start();
}else{
ljsw.start();
}
if(czl==null){
	czl = (AnimationDrawable) imageViewczlxt.getBackground();
	czl.start();
}else{
czl.start();
}

    }





	 /**
     * check the foreground progress is alilo or not.
     * @param context
     * @return
     */
    private static boolean isForeGround(Context context){
    	ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    	List<RunningAppProcessInfo> appProcesss = activityManager.getRunningAppProcesses();
    	for(RunningAppProcessInfo appProcess:appProcesss){
    		if(appProcess.processName.equals("com.android.alilo")){
    			if(appProcess.importance==RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
    				return true;
    			}else{
    				return false;
    			}
    		}
    	}
		return false;
    	
    }


  /**
     * kill other progress except for alilo or l1 speech service.
     * @param context
     */
    private void killAllProgress(Context context){
    	ActivityManager activityManager =(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    	//得出所有正在运行的程序
    	List<ActivityManager.RunningAppProcessInfo> runServiceInfoList = activityManager.getRunningAppProcesses();
    	for(ActivityManager.RunningAppProcessInfo runServiceInfo:runServiceInfoList){
    		String pkgName = runServiceInfo.processName;
    		if(pkgName.equals("com.android.alilo")||pkgName.equals("com.booyue.l1")||pkgName.equals("/system/bin/dex2oat")||pkgName.equals("com.android.detcontai")||pkgName.equals("com.booyue.appmarket")){
    			
    		}else{
    			activityManager.killBackgroundProcesses(pkgName);
				//=============================add 2017-12-16===============================
			if(pkgName.equals("com.tencent.devicedemo")||pkgName.equals("com.tencent.qqlivekid:cache")||pkgName.equals("com.tencent.qqlivekid:services")||pkgName.equals("com.tencent.qqlivekid")||pkgName.equals("com.qiyi.video.child")||pkgName.equals("com.youkuchild.android")){
    				try {
                        forceStopPackage(pkgName, this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
    			}
				
    		}
    	}
    }


    /**
     * 强制停止应用程序
     *
     * @param pkgName
     */
    private void forceStopPackage(String pkgName, Context context) throws Exception {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        Class activityManagerClass = Class.forName("android.app.ActivityManager");
        Method method = activityManagerClass.getMethod("forceStopPackage", String.class);
        method.invoke(am, pkgName);
    }


    
    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver(batteryChangedReceiver);
if(MyApplication.flag_home){
}else{
	EnableHomeKeyForMainActivity();
}
	//ljsw.stop();
	//	czl.stop();


    }

//使home键在主界面无效
		private void DisableHomeKeyForMainActivity(){
		Settings.System.putInt(getContentResolver(),
				"config_disablehomeinhomepageactivity", 0);
	}

//使home键在主界面有效
private void EnableHomeKeyForMainActivity(){
		Settings.System.putInt(getContentResolver(),
				"config_disablehomeinhomepageactivity", 1);
	}

 private static int index =0;
 private static int powerTotal = 0;
    // 电量变化广播
    private BroadcastReceiver batteryChangedReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
         
      switch (intent.getAction()) {
			case Intent.ACTION_BATTERY_CHANGED:
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
		        powerTotal = intent.getExtras().getInt("level");
				boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;
				if(isCharging){
					Intent intentt = new Intent(HomePageActivity.this,SafetyMachinesm.class);
					Bundle bundle = new Bundle();
					bundle.putInt("index", index);
					bundle.putInt("power", powerTotal);
					intentt.putExtras(bundle);
					startActivity(intentt);
					//Settings.System.putInt(getContentResolver(),
						//	"config_disablehomewhensleep", 1);
				}
				else{
				
					//	if(SafetyMachinesm.mAcitivty!=null){
			//SafetyMachinesm.mAcitivty.finish();
			
		//	}
					//Settings.System.putInt(getContentResolver(),
					//		"config_disablehomewhensleep", 0);
				}


				//Log.d("ccccccccccccccccchanged","ACTION_BATTERY_CHANGED==");
                //int level = intent.getIntExtra("level", 0);
               // int scale = intent.getIntExtra("scale", 100);
                // 0 - 100
               // int percent = level * 100 / scale;
                // setLevel(int level): level �ķ�Χ�� 0 -10000
                //clipDrawable.setLevel(calculateLevel(power));
           Bundle extras = intent.getExtras();
                int current = extras.getInt("level");//获得当前电量
                int total = extras.getInt("scale");//获得总电量
                int percent = current * 100 / total;

          int level = 0;
             if (percent >= 80) {
            level = 5;
        } else if (percent >= 60) {
            level = 4;
        } else if (percent >= 40) {
            level = 3;
        } else if(percent >= 20){
            level = 2;
        }else {
            level = 1;
        }
				imageBattery.setImageLevel(level);
				imageBattery.setImageResource(R.drawable.battery_level);

				break;
			case Intent.ACTION_BATTERY_LOW:
				
				break;
			case Intent.ACTION_BATTERY_OKAY:
				
				break;
			case Intent.ACTION_POWER_CONNECTED:
//for testing...
				Intent intentt = new Intent(HomePageActivity.this,SafetyMachinesm.class);
				Bundle bundle = new Bundle();
				bundle.putInt("index", index);
		    	bundle.putInt("power", powerTotal);
				intentt.putExtras(bundle);
				startActivity(intentt);
					Settings.System.putInt(getContentResolver(),
						"config_disablehomewhensleep", 1);
				break;
			case Intent.ACTION_POWER_DISCONNECTED:
					Settings.System.putInt(getContentResolver(),
							"config_disablehomewhensleep", 0);
				if(SafetyMachinesm.mAcitivty!=null){
			SafetyMachinesm.mAcitivty.finish();
			
			}
				break;
			default:
				break;
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
				if(Utils.isNetWorkAvailable(HomePageActivity.this)){
					iv_wifi_status.setVisibility(View.VISIBLE);
					showcon_wifi();
					}
					break;
				case WifiManager.WIFI_STATE_DISABLED:
					iv_wifi_status.setVisibility(View.GONE);
					//Log.d("2222222222222222222222222222222wwwwwwwwjx","WIFI_STATE_DISABLED==");
				case WifiManager.WIFI_STATE_DISABLING:
					iv_wifi_status.setVisibility(View.GONE);
					//Log.d("3333333333333333333333333333333wwwwwwwwjx","WIFI_STATE_DISABLING==");
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
		unregisterReceiver(keycodeBR);
		//unregisterReceiver(keycodeBR2);
		unregisterReceiver(batteryChangedReceiver);
		unregisterReceiver(screenOnOff);
	}
    ///////////////////end_20161223_wangjx_读取系统wifi显示   
	
	 Intent intent2;
    ///////////////////begin_20170412_wangjx
	BroadcastReceiver keycodeBR = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			final String action = intent.getAction();
				Log.v("HomePageActivity","action.."+action);
			if ("android.intent.action.open.bg_music".equals(action)) {
					Log.d("wwwwwwwwjx11111111111111111","hello_bg_music");
			      intent2  = new Intent(context, AudioService.class);
			        context.startService(intent2);
				}
				if("android.intent.action.close.bg_music".equals(action)){
			//	intent2 = new Intent(context,AudioService.class);
			if(intent2!=null)
				context.stopService(intent2);
			}
			if("killAllProgress".equals(action)){
			//	killAllProgress(HomePageActivity.this);
		new Thread(new Runnable() {
					public void run() {
							killAllProgress(HomePageActivity.this);
							 Utils.clearActivity();
							 //here ,call the method to kill the background progress three times .
							 try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
							 killAllProgress(HomePageActivity.this);
							 			 try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
							 killAllProgress(HomePageActivity.this);



					}
				}).start();
			}
		
			if("startLauncher3FromAlilo".equals(action)){
				Log.v("HomePageActivity","start calculator2......222222222.");
			      doStartApplicationWithPackageName("com.yitoa.l1factorytest");//do not start it first,after the test apk has been prepared ,through it to call calculator2.
			}
				if("android.intent.action.f2.button".equals(action)){
				int value = intent.getIntExtra("name", -2);
				int value1 = intent.getIntExtra("value",-2);
				if(value==132&&value1==0){
					//播放语音；
					Log.v("jack", "receive the protect eyes broadcast 111");
					playNoticeAudio();
				}
			}


		 	}

	};	

private SoundPool mSoundPool;
private HashMap<Integer,Integer> soundmap;
	
private void playNoticeAudio(){
	mSoundPool = new SoundPool(1,AudioManager.STREAM_ALARM,0);
	soundmap = new HashMap<Integer,Integer>();
	soundmap.put(1, mSoundPool.load(this, R.raw.sound_warnning, 1));
	try{
	Thread.sleep(1000);
	}catch(Exception e){
		e.printStackTrace();
	}
	mSoundPool.play(soundmap.get(1), 1, 1, 0, 0, 1);

}
    ///////////////////end_20170412_wangjx
	
	
	///////////////////begin_20170427_wangjx_接收服务更新sql广播
	BroadcastReceiver keycodeBR2 = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			final String action = intent.getAction();
			if ("com.booyue.l1.score.update".equals(action)) {
                    //在这里要进行积分读取的更新。（有两种情况需要对积分进行改变。一种是接收到广播，一种是第一次进入要读取数据库来更新这个积分。）
				}
			}
	};	
	///////////////////end_20170427_wangjx

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		switch (arg2) {
		case 0:
			stopService(new Intent(HomePageActivity.this,AudioService.class));
			doStartApplicationWithPackageName("air.com.bobi.BobiPhoneS");
			
			break;
		case 1:
			stopService(new Intent(HomePageActivity.this,AudioService.class));
			doStartApplicationWithPackageName("air.com.bobi.BobiPhoneM");
			break;
		case 2:
			stopService(new Intent(HomePageActivity.this,AudioService.class));
			doStartApplicationWithPackageName("air.com.bobi.BobiPhoneL");
			break;
//		case 3:
//			stopService(new Intent(HomePageActivity.this,AudioService.class));
//			doStartApplicationWithPackageName("com.booyue.l1_hht");
//			break;
 //   	case 4:
//			stopService(new Intent(HomePageActivity.this,AudioService.class));
//			doStartApplicationWithPackageName("com.qiyi.video.child");
			
//			break;
//		case 5:
//			stopService(new Intent(HomePageActivity.this,AudioService.class));
//			doStartApplicationWithPackageName("com.youkuchild.android");
//			break;


		default:
			break;
		}
		
	}

		private void doStartApplicationWithPackageName(String packagename) {  
	
	    PackageInfo packageinfo = null;  
	    try {  
	        packageinfo = getPackageManager().getPackageInfo(packagename, 0);  
	    } catch (NameNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	    if (packageinfo == null) {  
	        return;  
	    }  
	  
	
	    Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);  
	    resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
	    resolveIntent.setPackage(packageinfo.packageName);  
	    
	
	    List<ResolveInfo> resolveinfoList = getPackageManager()  
	            .queryIntentActivities(resolveIntent, 0);  
	  
	    ResolveInfo resolveinfo = resolveinfoList.iterator().next();  
	    if (resolveinfo != null) {  
	
	        String packageName = resolveinfo.activityInfo.packageName;  

	        String className = resolveinfo.activityInfo.name;  
	        // LAUNCHER Intent  
	        Intent intent = new Intent(Intent.ACTION_MAIN);  
	        intent.addCategory(Intent.CATEGORY_LAUNCHER);  
	  
	        ComponentName cn = new ComponentName(packageName, className);  
	        intent.setComponent(cn);  
	        startActivity(intent);  
	    }  
	}


}
