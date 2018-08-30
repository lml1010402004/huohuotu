package com.android.alilo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.view.WindowManager;
import android.provider.Settings;
import android.view.WindowManager.LayoutParams;
import android.os.PowerManager;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import java.util.List;
import com.android.myUtils.Utils;
import com.android.myUtils.MyApplication;
import android.os.PowerManager;

public class NoticeEyeProtectDialog extends Activity{
	
	private TextView show_second_textview;

	private Timer mTimer = new Timer(true);
	private TimerTask mTimerTask;
	private MyHandler mHandler = new MyHandler();
	private  PowerManager.WakeLock wl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
       wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, this.getClass().getName());
		 wl.acquire();

			//	getWindow().addPrivateFlags(WindowManager.LayoutParams.FLAG_HOMEKEY_DISPATCHED); 
			getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Settings.System.putInt(getContentResolver(),
							"config_disablehomewhensleep", 1);

		setContentView(R.layout.noticeeyedialog);
		 Utils.addActivity(this);
		getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		this.setFinishOnTouchOutside(false);
		init();
		MyApplication.flag_home = true;
	}
	
	private void init(){
		if(HomePageActivity.serviceIntent!=null)
		this.stopService(HomePageActivity.serviceIntent);
		show_second_textview = (TextView) findViewById(R.id.show_second_textview);
		show_second_textview.setText("倒计时：");
		mTimerTask = new TimerTask() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 1001;
				mHandler.sendMessage(msg);
			}
		};
		mTimer.schedule(mTimerTask, 0, 1000);
	}
	int count = 60*5;
Intent intents ;
	private class  MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1001:
				if(count==0){
		if(HomePageActivity.serviceIntent!=null){
				stopService(HomePageActivity.serviceIntent);
				//重新开启护眼模式
				Log.v("jack", "重新开启护眼模式服务");
				}
				if(intents!=null){
				  stopService(intents);
				}else{

				intents= new Intent(NoticeEyeProtectDialog.this,MonitorService.class);
					startService(intents);
				}
					mTimer.cancel();
					Log.v("jack", "杀掉桌面倒计时程序");
					Settings.System.putInt(getContentResolver(),
							"config_disablehomewhensleep", 0);
				
					NoticeEyeProtectDialog.this.finish();

				}
				count = count-1;
				if(count>=0)
				show_second_textview.setText(""+count);
				break;

			default:
				break;
			}
		}
	}

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
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		MyApplication.flag_home = false;
		if (mTimer!=null) {
			mTimer.cancel();
		}
	PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
					if(powerManager.isScreenOn()){
						if(!AliloisForeGround(NoticeEyeProtectDialog.this)){
							Intent its = new Intent("android.intent.action.close.bg_music");
				    		sendBroadcast(its);
							Log.v("jack","broadcast===="+"android.intent.action.close.bg_music");
							Log.v("jack","broadcast===="+"android.intent.action.close.bg_music");
						}
					}
					wl.release();
	}


}
	
