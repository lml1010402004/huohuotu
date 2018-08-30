package com.android.alilo.setting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.alilo.HomePageActivity;
import android.content.IntentFilter;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.alilo.MyUtils;
import com.android.alilo.R;
import com.android.model.UserInfo;
import com.android.myUtils.BaseConfig;
import com.android.myUtils.Utils;

public class SettingBabyBasicInfo extends Activity implements OnClickListener {

	private RelativeLayout root_view;
	private EditText baby_name;
	private ImageButton confirm_baby_info;
	private TextView baby_birthday;
	private RadioGroup radiogroup;
	private static int gender =0;
	private UserInfo userInfo;
	private ImageView iv_wifi_status,image_battery;
	private ClipDrawable clipDrawable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settingbaby);
		 Utils.addActivity(this);
		init();
		setupUI(findViewById(R.id.root_view));
		initView();
		initWifi();
	}
	
	
	IntentFilter mFilter;
	private void initWifi(){
		mFilter = new IntentFilter();
		mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		mFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
		mFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		registerReceiver(wifiReceiver, mFilter);
		
		 IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
	        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
			intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
			intentFilter.addAction(Intent.ACTION_BATTERY_LOW);		
	        registerReceiver(batteryChangedReceiver, intentFilter);
	}
	
    ///////////////////begin_20161223_wangjx_��ȡϵͳwifi��ʾ   
	BroadcastReceiver wifiReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (WifiManager.WIFI_STATE_CHANGED_ACTION
					.equals(intent.getAction())) {// �������wifi�Ĵ���ر�
				int wifiState = intent.getIntExtra(
						WifiManager.EXTRA_WIFI_STATE, 0);
				switch (wifiState) {
				case WifiManager.WIFI_STATE_ENABLED:
					iv_wifi_status.setVisibility(View.VISIBLE);
					showcon_wifi();
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
	 */
	WifiConfiguration con_wifi;
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
		unregisterReceiver(wifiReceiver);
		unregisterReceiver(batteryChangedReceiver);
		
	}
	
	private void initView(){
		iv_wifi_status = (ImageView) findViewById(R.id.iv_wifi_status);
		 image_battery = (ImageView) findViewById(R.id.image_battery);
		 LayerDrawable layerDrawable = (LayerDrawable) image_battery.getDrawable();
		 clipDrawable = (ClipDrawable)layerDrawable.findDrawableByLayerId(R.id.clip_drawable);
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
            }if(Intent.ACTION_BATTERY_LOW.equals(intent.getAction())){
            	
            }
        }     
    };
    
    private int calculateLevel(int progress) {
        int leftOffest = MyUtils.dip2px(this, 2);
        int powerLength = MyUtils.dip2px(this, 26.5f);// 40 px in hdpi
        int totalLength = MyUtils.dip2px(this, 30.5f);// 49 px in hdpi
        int level = (leftOffest + powerLength * progress / 100) * 10000 / totalLength;
        return level;
    }
	
	
	@SuppressLint("SimpleDateFormat") private void init(){
		root_view = (RelativeLayout) findViewById(R.id.root_view);
		root_view.setOnClickListener(this);
		baby_name = (EditText) findViewById(R.id.baby_name);
		baby_name.setTypeface(HomePageActivity.face);
		confirm_baby_info = (ImageButton) findViewById(R.id.confirm_baby_info);
		confirm_baby_info.setOnClickListener(this);
		baby_birthday = (TextView) findViewById(R.id.baby_birthday);
		baby_birthday.setTypeface(HomePageActivity.face);
		baby_birthday.setOnClickListener(this);
      baby_name.setText(BaseConfig.readProduct(SettingBabyBasicInfo.this, "name"));
		baby_birthday.setText(BaseConfig.readProduct(SettingBabyBasicInfo.this, "age"));


		radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
		radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				switch (arg1) {
				case R.id.radio_female:
					gender = 0;
				BaseConfig.saveProduct(SettingBabyBasicInfo.this, "gender",gender+"");
					break;
				case R.id.radio_male:
					gender = 1;
				BaseConfig.saveProduct(SettingBabyBasicInfo.this, "gender",gender+"");
					break;
				default:
					break;
				}
			}
		});
		
		iv_wifi_status = (ImageView) findViewById(R.id.iv_wifi_status);
		image_battery = (ImageView) findViewById(R.id.image_battery);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.confirm_baby_info:
			if(userInfo!=null){
			    updateLabel();
				Toast.makeText(SettingBabyBasicInfo.this, getString(R.string.set_successfully), Toast.LENGTH_SHORT).show();
		    	
			}
			SettingBabyBasicInfo.this.finish();
			break;
		case R.id.baby_birthday:
			new DatePickerDialog(SettingBabyBasicInfo.this,
					d,
					c.get(Calendar.YEAR),
					c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH)
					).show();
			break;

		default:
			break;
		}
	}
	
	   //获取一个日历对象
    Calendar c = Calendar.getInstance(Locale.CHINA);
	  //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);   
            //将页面TextView的显示更新为最新时间
         //   long when = c.getTimeInMillis();

    		//if (when / 1000 < Integer.MAX_VALUE) {
    			//SystemClock.setCurrentTimeMillis(when);
    		//}  
    		updateLabel();
        }       
    };
//    Calendar calendar = Calendar.getInstance();
    @SuppressLint("SimpleDateFormat") private void updateLabel() {
    	baby_birthday.setText(fmtDateAndTime.format(c.getTime()));
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    	SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
    	int year = Integer.parseInt(sdf.format(c.getTime()));
    	int month = Integer.parseInt(sdf1.format(c.getTime()));
    	long time = System.currentTimeMillis();
    	final Calendar mCalendar = Calendar.getInstance();
    	mCalendar.setTimeInMillis(time);
    	int curyear = mCalendar.get(Calendar.YEAR);
    	int curmonth = mCalendar.get(Calendar.MONTH);
    	int real_age = curyear-year;
    	if(real_age<=0){
				Toast.makeText(SettingBabyBasicInfo.this, getString(R.string.setting_age_error), Toast.LENGTH_SHORT).show();
    		return ;
    	}
    	//if(curmonth<month){
    	//	real_age= real_age;
    	//}
    	
		//String created = calendar.get(Calendar.YEAR)+"年"+calendar.get(Calendar.MONTH)+"月";
    	BaseConfig.saveProduct(SettingBabyBasicInfo.this, "name",baby_name.getText().toString() );
    	BaseConfig.saveProduct(SettingBabyBasicInfo.this, "age",real_age+"" );
    	BaseConfig.saveProduct(SettingBabyBasicInfo.this, "gender",gender+"");
	
        }
	
	public static void hideSoftKeyboard(Activity activity){
		InputMethodManager inputmethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputmethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}
	
	
	public void setupUI(View view){
		if(!(view instanceof EditText)){
			view.setOnTouchListener(new OnTouchListener() {
				@SuppressLint("ClickableViewAccessibility") @Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					hideSoftKeyboard(SettingBabyBasicInfo.this);
					return false;
				}
			});
		}
		if(view instanceof ViewGroup){
			for(int i=0;i<((ViewGroup)view).getChildCount();i++){
				View innerView = ((ViewGroup) view).getChildAt(i);
				setupUI(innerView);
                 
			}
		}
	}
	
	   //获取日期格式器对象
 DateFormat fmtDateAndTime = DateFormat.getDateInstance();

	
}
