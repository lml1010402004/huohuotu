package com.android.startupguideline;

import com.android.myUtils.SharedPreferencesUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.android.alilo.HomePageActivity;

public class FirstActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//判断是否是第一次进入此app.
		boolean flag = SharedPreferencesUtil.getRunningFirstTime(FirstActivity.this);
		if(!flag){
				
			Intent it = new Intent(FirstActivity.this,HomePageActivity.class);
			startActivity(it);
			this.finish();
			return ;
		}else{
		    //SharedPreferencesUtil.setRunningFirstTime(FirstActivity.this,false);
			Intent it1 = new Intent(FirstActivity.this,StartupConnectWifi.class);
			startActivity(it1);
			this.finish();
		}
		
	}
	
}
