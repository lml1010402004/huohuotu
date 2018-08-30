package com.android.startupguideline;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;


public class BaseActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	}
	
	
	
	@Override
	protected void onResume() {
		super.onResume();
		registerBatteryMonitor();
		registerWifiMonitor();
		
	}
	
	private void registerBatteryMonitor(){
	        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
	        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
			intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
			intentFilter.addAction(Intent.ACTION_BATTERY_LOW);		
	        registerReceiver(batteryChangedReceiver, intentFilter);
	}
	
	IntentFilter mFilter;
	
	private void registerWifiMonitor(){
		mFilter = new IntentFilter();
		mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		mFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
		mFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		registerReceiver(mReceiver, mFilter);
	}
	
	
	BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (WifiManager.WIFI_STATE_CHANGED_ACTION
					.equals(intent.getAction())) {
				int wifiState = intent.getIntExtra(
						WifiManager.EXTRA_WIFI_STATE, 0);
				switch (wifiState) {
				case WifiManager.WIFI_STATE_ENABLED:
					//iv_wifi_status.setVisibility(View.VISIBLE);
					//showcon_wifi();
					break;
				case WifiManager.WIFI_STATE_DISABLED:
					//iv_wifi_status.setVisibility(View.GONE);
				case WifiManager.WIFI_STATE_DISABLING:
					//iv_wifi_status.setVisibility(View.GONE);
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
						Log.v("jack", "wifi的Level："+level);
						Log.v("jack", "wifi的Level："+level);
						Log.v("jack", "wifi的Level："+level);
						
						//iv_wifi_status.setImageLevel(level);

					}
				}
			} else if (intent.getAction().equals(
					WifiManager.RSSI_CHANGED_ACTION)) {
				int mWifiRssi = intent.getIntExtra(WifiManager.EXTRA_NEW_RSSI,
						-200);
				int level = Math.abs(mWifiRssi);
				Log.v("jack", "wifi的Level43333333333："+level);
				Log.v("jack", "wifi的Level43333333333："+level);
				Log.v("jack", "wifi的Level43333333333："+level);
				//iv_wifi_status.setImageLevel(level);
			}

		}

	};
	
	protected int level;
	protected int scale;
	protected int power;
    private BroadcastReceiver batteryChangedReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
            	Log.d("ccccccccccccccccchanged","ACTION_BATTERY_CHANGED==");
                level = intent.getIntExtra("level", 0);
                scale = intent.getIntExtra("scale", 100);
                // 0 - 100
                power = level * 100 / scale;
                Log.v("jack", "收到的电池数据是"+level+":::"+scale+"::::"+power);
                Log.v("jack", "收到的电池数据是"+level+":::"+scale+"::::"+power);
                Log.v("jack", "收到的电池数据是"+level+":::"+scale+"::::"+power);
                // setLevel(int level): level �ķ�Χ�� 0 -10000
//                clipDrawable.setLevel(calculateLevel(power));
            }if(Intent.ACTION_BATTERY_LOW.equals(intent.getAction())){
            	
            }
        }     
    };
	
    
    protected void onDestroy() {
    	super.onDestroy();
    	unregisterReceiver(batteryChangedReceiver);//unregister th battery receiver.
    	unregisterReceiver(mReceiver);//unregister the wifi receiver.
    	
    };
	

}
