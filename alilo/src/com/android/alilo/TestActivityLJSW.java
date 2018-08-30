package com.android.alilo;

/**
 * ������
 * @author wangjx
 *
 */


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
import android.support.v4.app.FragmentTransaction;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import com.android.myUtils.Utils;
import com.android.myUtils.MyApplication;

public class TestActivityLJSW extends FragmentActivity {

	ImageView imageViewage1;
	ImageView imageViewage2;	
	ImageView imageViewage3;
	ImageView back;
	ImageView imageViewljswjs;
	AnimationDrawable lj_js;
	
	
	//wifiView
	ImageView iv_wifi_status; // ���ӵ�wifiͼ��״̬	
	WifiConfiguration con_wifi;
	IntentFilter mFilter;
	
	//batteryView
	private ClipDrawable clipDrawable;
	ImageView image_battery;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activityljsw);
		 Utils.addActivity(this);
        initView();
		
        
		back = (ImageView)findViewById(R.id.back);
		back.setOnTouchListener(touchListener);
		
		imageViewljswjs = (ImageView)findViewById(R.id.imageViewljswjs);
		imageViewljswjs.setOnTouchListener(touchListener);
		imageViewljswjs.setBackgroundResource(R.drawable.imageviewljswjs_anim);
		lj_js = (AnimationDrawable) imageViewljswjs.getBackground();
		lj_js.start();
		
		imageViewage1 = (ImageView)findViewById(R.id.imageViewage1);
		imageViewage1.setOnTouchListener(touchListener);
		
		imageViewage2 = (ImageView)findViewById(R.id.imageViewage2);
		imageViewage2.setOnTouchListener(touchListener);
		
		imageViewage3 = (ImageView)findViewById(R.id.imageViewage3);
		imageViewage3.setOnTouchListener(touchListener);
		
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
    }
    
    OnTouchListener touchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (v.getId()) {			
				case R.id.imageViewage1:
					onTouchChange("imageViewage1", event.getAction());  //20170103_wangjx_��Ӷ����˵����ع���
					break;
				case R.id.imageViewage2:
					onTouchChange("imageViewage2", event.getAction());  //20170103_wangjx_��Ӷ����˵����ع���
					break;
				case R.id.imageViewage3:
					onTouchChange("imageViewage3", event.getAction());  //20170103_wangjx_��Ӷ����˵����ع���
					break;
				case R.id.back:
					onTouchChange("back", event.getAction());  //20170103_wangjx_��Ӷ����˵����ع���
					finish();
					break;
				case R.id.imageViewljswjs:
					onTouchChange("imageViewljswjs", event.getAction());  //20170103_wangjx_��Ӷ����˵��Ź������¼�
					break;
				default:
					break;
				}
			return false;
		}
	};
	
	void onTouchChange(String methodName, int eventAction) {
		if ("imageViewage1".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new TestActivityLJSW1()).commit();
				imageViewage1.setImageResource(R.drawable.age1_after); //20170103_wangjx_���ӵ���¼�
				imageViewage2.setImageResource(R.drawable.age2);
				imageViewage3.setImageResource(R.drawable.age3);
	        }else if (eventAction == MotionEvent.ACTION_UP){
			}
		}else if ("imageViewage2".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new TestActivityLJSW2()).commit();
				imageViewage2.setImageResource(R.drawable.age2_after);//20170103_wangjx_���ӵ���¼�
				imageViewage1.setImageResource(R.drawable.age1);
				imageViewage3.setImageResource(R.drawable.age3);
	        }else if (eventAction == MotionEvent.ACTION_UP){
			}
		}else if ("imageViewage3".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new TestActivityLJSW3()).commit();
				imageViewage3.setImageResource(R.drawable.age3_after); //20170103_wangjx_���ӵ���¼�
				imageViewage1.setImageResource(R.drawable.age1);
				imageViewage2.setImageResource(R.drawable.age2);
	        }else if (eventAction == MotionEvent.ACTION_UP){
			}
		}else if ("imageViewljswjs".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				Intent intent = new Intent(this, LjswNrJs2.class);  //20170105_wangjx_����intent���������˵�רע��ѵ��
				startActivity(intent);                                 //20170105_wangjx_����intent���������˵�רע��ѵ��
				Log.d("wwwwwwwwjx","ACTION_DOWN==");
	        }else if (eventAction == MotionEvent.ACTION_UP){
	        	Log.d("wwwwwwwwjx","ACTION_UP==");
			}
		}
	};

    private void initView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_container, new TestActivityLJSW1()).commit();
    }
    
	///////////////////begin_20161222_wangjx_��ȡϵͳ�����ʾ
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
		 MyApplication.FLAGOFSXKJ = false;

  if(!Utils.isNetWorkAvailable(TestActivityLJSW.this)){
    	   iv_wifi_status.setVisibility(View.INVISIBLE);
       }else{
    	   iv_wifi_status.setVisibility(View.VISIBLE);
       }


    }
    
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(batteryChangedReceiver);
    }
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
            }
        }     
    };
    ///////////////////end_20161222_wangjx_��ȡϵͳ�����ʾ   

    ///////////////////begin_20161223_wangjx_��ȡϵͳwifi��ʾ   
	BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (WifiManager.WIFI_STATE_CHANGED_ACTION
					.equals(intent.getAction())) {// �������wifi�Ĵ���ر�
				int wifiState = intent.getIntExtra(
						WifiManager.EXTRA_WIFI_STATE, 0);
				switch (wifiState) {
				case WifiManager.WIFI_STATE_ENABLED:
			if(Utils.isNetWorkAvailable(TestActivityLJSW.this)){
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
	 * ��������״̬��ʾ
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
    ///////////////////end_20161223_wangjx_��ȡϵͳwifi��ʾ   
}
