package com.android.startupguideline;

import android.app.Activity;
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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.alilo.HomePageActivity;
import com.android.alilo.MyUtils;
import com.android.alilo.R;
import android.view.WindowManager.LayoutParams;
import android.view.WindowManager;
import com.android.myUtils.Utils;

public class StartupManagePassword extends Activity implements OnClickListener {

	private Button startup_jump_over;
	private Button password0, password1, password2, password3, password4,
			password5, password6, password7, password8, password9;
	private ImageView password_list_imageview;
	private ImageView parents_imageview;

	private StringBuilder passwordString = new StringBuilder();
	private static int inputCount = 0;

	ImageView iv_wifi_status; // 检测wifi状态

	private ImageView image_battery;
	private ClipDrawable clipDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		setContentView(R.layout.guideline_managerpassword);
		 Utils.addActivity(this);
		init();
		initWifi();
	}

	// /////////////////begin_20161223_wangjx_��ȡϵͳwifi��ʾ
	BroadcastReceiver wifiReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (WifiManager.WIFI_STATE_CHANGED_ACTION
					.equals(intent.getAction())) {// �������wifi�Ĵ���ر�
				int wifiState = intent.getIntExtra(
						WifiManager.EXTRA_WIFI_STATE, 0);
				switch (wifiState) {
				case WifiManager.WIFI_STATE_ENABLED:
				if(Utils.isNetWorkAvailable(StartupManagePassword.this)){
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

	IntentFilter mFilter;

	private void initWifi() {
		mFilter = new IntentFilter();
		mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		mFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
		mFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		registerReceiver(wifiReceiver, mFilter);
	}

	private void init() {
		startup_jump_over = (Button) findViewById(R.id.startup_jump_over);
		startup_jump_over.setOnClickListener(this);
		image_battery = (ImageView) findViewById(R.id.image_battery);
		LayerDrawable layerDrawable = (LayerDrawable) image_battery
				.getDrawable();
		clipDrawable = (ClipDrawable) layerDrawable
				.findDrawableByLayerId(R.id.clip_drawable);
		iv_wifi_status = (ImageView) findViewById(R.id.iv_wifi_status);
		password0 = (Button) findViewById(R.id.password_button0);
		password1 = (Button) findViewById(R.id.password_button1);
		password2 = (Button) findViewById(R.id.password_button2);
		password3 = (Button) findViewById(R.id.password_button3);
		password4 = (Button) findViewById(R.id.password_button4);
		password5 = (Button) findViewById(R.id.password_button5);
		password6 = (Button) findViewById(R.id.password_button6);
		password7 = (Button) findViewById(R.id.password_button7);
		password8 = (Button) findViewById(R.id.password_button8);
		password9 = (Button) findViewById(R.id.password_button9);

		password0.setOnClickListener(this);
		password1.setOnClickListener(this);
		password2.setOnClickListener(this);
		password3.setOnClickListener(this);
		password4.setOnClickListener(this);
		password5.setOnClickListener(this);
		password6.setOnClickListener(this);
		password7.setOnClickListener(this);
		password8.setOnClickListener(this);
		password9.setOnClickListener(this);
		password_list_imageview = (ImageView) findViewById(R.id.password_list_imageview);
		parents_imageview = (ImageView) findViewById(R.id.parents_imageview);

	}

	Intent intent;

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.startup_jump_over:
			intent = new Intent(StartupManagePassword.this,
					HomePageActivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.password_button0:
			passwordString = passwordString.append("0");
			refreshPasswordPoint();
			checkAndRedirect();
			break;
		case R.id.password_button1:
			passwordString = passwordString.append("1");
			refreshPasswordPoint();
			checkAndRedirect();
			break;
		case R.id.password_button2:
			passwordString = passwordString.append("2");
			refreshPasswordPoint();
			checkAndRedirect();
			break;
		case R.id.password_button3:
			passwordString = passwordString.append("3");
			refreshPasswordPoint();
			checkAndRedirect();
			break;
		case R.id.password_button4:
			passwordString = passwordString.append("4");
			refreshPasswordPoint();
			checkAndRedirect();
			break;
		case R.id.password_button5:
			passwordString = passwordString.append("5");
			refreshPasswordPoint();
			checkAndRedirect();
			break;
		case R.id.password_button6:
			passwordString = passwordString.append("6");
			refreshPasswordPoint();
			checkAndRedirect();
			break;
		case R.id.password_button7:
			passwordString = passwordString.append("7");
			refreshPasswordPoint();
			checkAndRedirect();
			break;
		case R.id.password_button8:
			passwordString = passwordString.append("8");
			refreshPasswordPoint();
			checkAndRedirect();
			break;
		case R.id.password_button9:
			passwordString = passwordString.append("9");
			refreshPasswordPoint();
			checkAndRedirect();
			break;

		default:
			break;
		}
	}

	String firstTimePassword = new String();

	private void checkAndRedirect() {
		if (passwordString.length() == 4) {
			inputCount = ++inputCount;
			if (inputCount == 1) {
				parents_imageview
						.setImageResource(R.drawable.parentsmanagerpasswordagain);
			}
			if (inputCount == 2) {
				if (!firstTimePassword.toString().equals(
						passwordString.toString())) {
					parents_imageview
							.setImageResource(R.drawable.parentsmanagerpassword);
					Toast.makeText(StartupManagePassword.this, "两次密码不一样!",
							Toast.LENGTH_SHORT).show();
					passwordString = null;
					passwordString = new StringBuilder();
					firstTimePassword = "";
					inputCount = 0;
				} else {
					intent = new Intent(StartupManagePassword.this,
							HomePageActivity.class);
					startActivity(intent);
					// 这里进行密码的保存 暂时先不写，因为还不知道怎么用这个值。
					Toast.makeText(StartupManagePassword.this, "密码设置成功!",
							Toast.LENGTH_SHORT).show();
					this.finish();
					return;
				}
			}
			Log.v("jack", "count的值" + inputCount);

			firstTimePassword = passwordString.toString();
			// 判断正确与否，然后清空。
			passwordString = null;
			passwordString = new StringBuilder();
			password_list_imageview.setImageResource(R.drawable.password1);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter intentFilter = new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED);
		intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
		intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
		intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
		registerReceiver(batteryChangedReceiver, intentFilter);

 if(!Utils.isNetWorkAvailable(StartupManagePassword.this)){
	    	   iv_wifi_status.setVisibility(View.INVISIBLE);
	       }else{
	    	   iv_wifi_status.setVisibility(View.VISIBLE);
	       }

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(batteryChangedReceiver);
		unregisterReceiver(wifiReceiver);
	}

	// �����仯�㲥
	private BroadcastReceiver batteryChangedReceiver = new BroadcastReceiver() {

		public void onReceive(Context context, Intent intent) {
			if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
				Log.d("ccccccccccccccccchanged", "ACTION_BATTERY_CHANGED==");
				int level = intent.getIntExtra("level", 0);
				int scale = intent.getIntExtra("scale", 100);
				// 0 - 100
				int power = level * 100 / scale;
				// setLevel(int level): level �ķ�Χ�� 0 -10000
				clipDrawable.setLevel(calculateLevel(power));
			}
			if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction())) {

			}
		}
	};

	// ////////////////
	private int calculateLevel(int progress) {
		int leftOffest = MyUtils.dip2px(this, 2);
		int powerLength = MyUtils.dip2px(this, 26.5f);// 40 px in hdpi
		int totalLength = MyUtils.dip2px(this, 30.5f);// 49 px in hdpi
		int level = (leftOffest + powerLength * progress / 100) * 10000
				/ totalLength;
		return level;
	}

	int length = -1;

	private void refreshPasswordPoint() {
		length = passwordString.length();
		if (length == -1) {
			password_list_imageview.setImageResource(R.drawable.password1);
		}
		if (length == 1) {
			password_list_imageview.setImageResource(R.drawable.password2);
		}
		if (length == 2) {
			password_list_imageview.setImageResource(R.drawable.password3);
		}
		if (length == 3) {
			password_list_imageview.setImageResource(R.drawable.password4);
		}
		if (length == 4) {
			password_list_imageview.setImageResource(R.drawable.password5);

		}

	}

}
