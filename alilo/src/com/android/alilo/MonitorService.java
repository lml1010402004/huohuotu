package com.android.alilo;

import java.util.Timer;
import java.util.TimerTask;

import com.android.myUtils.SharedPreferencesUtil;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.provider.Settings;

public class MonitorService extends Service {

    private Timer mTimer = new Timer(true);
	private TimerTask mTimerTask;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mTimerTask = new TimerTask() {
			@Override
			public void run() {
        
				Intent it = new Intent(MonitorService.this,
						NoticeEyeProtectDialog.class);
				it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(it);
                Settings.System.putInt(getContentResolver(),
						"config_disablehomewhensleep", 1);
			
			}
		};
		int time = SharedPreferencesUtil.getProtectEyesTime(MonitorService.this);//得到的是分钟

		mTimer.schedule(mTimerTask,  time*60*1000,  time*60*1000);
		return super.onStartCommand(intent, flags, startId);
	}


	  @Override
    public void onDestroy() {
     	mTimer.cancel();
        super.onDestroy();
    }



}
