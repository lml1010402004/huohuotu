package com.android.startupguideline;

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
import android.content.IntentFilter;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.alilo.HomePageActivity;
import com.android.alilo.MyUtils;
import com.android.alilo.R;
import com.android.model.UserInfo;
import android.view.WindowManager;
import com.android.myUtils.Utils;
import com.android.myUtils.BaseConfig;

public class StartupSetBabyInfo extends Activity implements OnClickListener{
	
	private Button startup_next_step,startup_jump_over;
	private TextView startup_baby_birthday;
	private EditText startup_baby_name;
//	private UserInfo userInfo;
	private int gender =0;
	
	
	private ImageView image_battery;
	private ClipDrawable clipDrawable;
	
	protected void onResume() {
		super.onResume();
	    	 IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
	        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
			intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
			intentFilter.addAction(Intent.ACTION_BATTERY_LOW);		
	        registerReceiver(batteryChangedReceiver, intentFilter);
	        initWifi();

			  if(!Utils.isNetWorkAvailable(StartupSetBabyInfo.this)){
		    	   iv_wifi_status.setVisibility(View.INVISIBLE);
		       }else{
		    	   iv_wifi_status.setVisibility(View.VISIBLE);
		       }
		
	};
	
    // �����仯�㲥
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
    
    
    protected void onDestroy() {
    	super.onDestroy();
    	unregisterReceiver(batteryChangedReceiver);
    	unregisterReceiver(wifiReceiver);
    	
    };
	
	
	IntentFilter mFilter;
	private void initWifi(){
		mFilter = new IntentFilter();
		mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		mFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
		mFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		registerReceiver(wifiReceiver, mFilter);
	}
	
	ImageView iv_wifi_status; //检测wifi状态
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
					if(Utils.isNetWorkAvailable(StartupSetBabyInfo.this)){
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guideline_setting_baby_info);
		 Utils.addActivity(this);
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		setupUI(findViewById(R.id.root_view));
		init();
	}
	
	
	private void init(){
		startup_next_step = (Button) findViewById(R.id.startup_next_step);
		startup_next_step.setOnClickListener(this);
		
		startup_baby_birthday = (TextView) findViewById(R.id.startup_baby_birthday);
		startup_baby_name = (EditText) findViewById(R.id.startup_baby_name);
		startup_baby_birthday.setOnClickListener(this);
		startup_jump_over = (Button) findViewById(R.id.startup_jump_over);
		startup_jump_over.setOnClickListener(this);
		iv_wifi_status = (ImageView) findViewById(R.id.iv_wifi_status);
		 image_battery = (ImageView) findViewById(R.id.image_battery);
		 LayerDrawable layerDrawable = (LayerDrawable) image_battery.getDrawable();
		 clipDrawable = (ClipDrawable)layerDrawable.findDrawableByLayerId(R.id.clip_drawable);
		
	}
	
	
	public void setupUI(View view){
		if(!(view instanceof EditText)){
			view.setOnTouchListener(new OnTouchListener() {
				@SuppressLint("ClickableViewAccessibility") @Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					hideSoftKeyboard(StartupSetBabyInfo.this);
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
	
	public static void hideSoftKeyboard(Activity activity){
		InputMethodManager inputmethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputmethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}


	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.startup_next_step:
			Intent intent = new Intent(StartupSetBabyInfo.this,StartupManagePassword.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.startup_baby_birthday:
			new DatePickerDialog(StartupSetBabyInfo.this,
					d,
					c.get(Calendar.YEAR),
					c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH)
					).show();			
			
			break;
		case R.id.startup_jump_over:
			Intent it = new Intent(StartupSetBabyInfo.this,HomePageActivity.class);
			startActivity(it);
			this.finish();
			break;

		default:
			break;
		}
	}
	
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
            long when = c.getTimeInMillis();

    		if (when / 1000 < Integer.MAX_VALUE) {
    			SystemClock.setCurrentTimeMillis(when);
    		}  
    		updateLabel();
        }       
    };
    
    
	   //获取日期格式器对象
DateFormat fmtDateAndTime = DateFormat.getDateInstance();
    private void updateLabel() {
    	startup_baby_birthday.setText(fmtDateAndTime.format(c.getTime()));
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    	SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
    	int year = Integer.parseInt(sdf.format(c.getTime()));
    	int month = Integer.parseInt(sdf1.format(c.getTime()));
    	
    	Calendar calendar = Calendar.getInstance();
		String created = calendar.get(Calendar.YEAR)+"年"+calendar.get(Calendar.MONTH)+"月";
    	
    //	userInfo = new UserInfo();
    	int realage =  2017-year;
			
    	if(realage<0){
    		Toast.makeText(StartupSetBabyInfo.this, getString(R.string.the_format_age_error), Toast.LENGTH_SHORT).show();
    	    return ;
    	}

        BaseConfig.saveProduct(StartupSetBabyInfo.this, "name",startup_baby_name.getText().toString() );
		BaseConfig.saveProduct(StartupSetBabyInfo.this, "age",realage+"" );
		BaseConfig.saveProduct(StartupSetBabyInfo.this, "gender",gender+"");

  
        }
    
	   //获取一个日历对象
       Calendar c = Calendar.getInstance(Locale.CHINA);
	
	
}
