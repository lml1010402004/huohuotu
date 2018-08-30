package com.android.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.myUtils.SharedPreferencesUtil;
public class SystemLauncherPageCountBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context arg0, Intent intent) {
		 if ("com.booyue.action.APP_LAUNCHER_PAGE_COUNT".equals(intent.getAction())) {
	            int pageCount = intent.getIntExtra("pageCount", 1);
	            SharedPreferencesUtil.setPageCount(arg0, pageCount);
	        }
		
	}

}
