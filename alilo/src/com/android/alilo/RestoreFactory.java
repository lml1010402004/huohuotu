package com.android.alilo;

import com.android.myUtils.BaseConfig;
import com.android.myUtils.MyApplication;
import com.android.myUtils.SharedPreferencesUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import com.android.dao.DataBaseUtil;
import android.content.ComponentName;
import com.android.myUtils.Utils;

public class RestoreFactory extends Activity implements OnClickListener{
	
	private ImageButton back;
	private Button cancle_button;
	private Button confirm_button;
	private DataBaseUtil data_base;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restore_factory);
		 Utils.addActivity(this);
		init();
	}
	
	private void init(){
		back = (ImageButton) findViewById(R.id.restore_back);
		cancle_button = (Button) findViewById(R.id.cancle_button);
		confirm_button = (Button) findViewById(R.id.confirm_button);
			back.setOnClickListener(this);
		cancle_button.setOnClickListener(this);
		confirm_button.setOnClickListener(this);
		data_base = new DataBaseUtil();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.restore_back:
			this.finish();
			break;
		case R.id.cancle_button:
			this.finish();
			break;
		case R.id.confirm_button:
			//clear the startguide line module.
		//	SharedPreferencesUtil.setRunningFirstTime(RestoreFactory.this, true);
			Log.v("jack", "设置的是否是第一次进入的标识志"+true);
			//clear the user info.
		//	BaseConfig.saveProduct(RestoreFactory.this, "name",getResources().getString(R.string.zhihuibaobao));
		//	BaseConfig.saveProduct(RestoreFactory.this, "age", "5");
		//	BaseConfig.saveProduct(RestoreFactory.this, "gender", "1");
			//restore the user original info.34 year old
		//	SharedPreferencesUtil.setPassApkCountForLJSW34SCYXZ(RestoreFactory.this, MyApplication.default_passed_apk_ljsw34scyxz);
		//	SharedPreferencesUtil.setPassApkCountForLJSW34HTJ(RestoreFactory.this, MyApplication.default_passed_apk_ljsw34_htj);
			//SharedPreferencesUtil.setPassApkCountForLJSW34LSYTM(RestoreFactory.this, MyApplication.default_passed_apk_ljsw34lsytm);
		//	SharedPreferencesUtil.setPassApkCountForLJSW34SSYBJ(RestoreFactory.this, MyApplication.default_passed_apk_ljsw34_ssybj);
			//45 year old.
		//	SharedPreferencesUtil.setPassApkCountForLJSW45GCYLX(RestoreFactory.this, MyApplication.default_passed_apk_ljsw45_gcylx);
		//	SharedPreferencesUtil.setPassApkCountForLJSW45GCYZY(RestoreFactory.this, MyApplication.default_passed_apk_ljsw45_gcyzy);
		//	SharedPreferencesUtil.setPassApkCountForLJSW45HHTZETS(RestoreFactory.this, MyApplication.default_passed_apk_ljsw45_hhtzets);
		//	SharedPreferencesUtil.setPassApkCountForLJSW45JTCS1(RestoreFactory.this, MyApplication.default_passed_apk_ljsw45_jtcs1);
		//	SharedPreferencesUtil.setPassApkCountForLJSW45WMDCW(RestoreFactory.this, MyApplication.default_passed_apk_ljsw45_wmdcw);
		//	SharedPreferencesUtil.setPassApkCountForLJSW45WMJZMCY(RestoreFactory.this, MyApplication.default_passed_apk_ljsw45_wmjzmcy);
			//56 year old.
		//	SharedPreferencesUtil.setPassApkCountForLJSW56CXQD(RestoreFactory.this, MyApplication.default_passed_apk_ljsw56_cxqd);
		//	SharedPreferencesUtil.setPassApkCountForLJSW56JTCS2(RestoreFactory.this, MyApplication.default_passed_apk_ljsw56_jtcs2);
		//	SharedPreferencesUtil.setPassApkCountForLJSW56LJYX(RestoreFactory.this, MyApplication.default_passed_apk_ljsw56_ljyx);
		//	SharedPreferencesUtil.setPassApkCountForLJSW56SYSZ(RestoreFactory.this, MyApplication.default_passed_apk_ljsw56_zdwy);
		//	SharedPreferencesUtil.setPassApkCountForLJSW56ZDWY(RestoreFactory.this, MyApplication.default_passed_apk_ljsw56_zdwy);
		//	SharedPreferencesUtil.setPassApkCountForLJSW56ZNZL(RestoreFactory.this, MyApplication.default_passed_apk_ljsw56_znzl);
			//34 创造力
	//		SharedPreferencesUtil.setPassApkCountForCZL34(RestoreFactory.this, MyApplication.default_passed_apk_czl34);
			//45 创造力
		//	SharedPreferencesUtil.setPassApkCountForCZL45(RestoreFactory.this, MyApplication.default_passed_apk_czl45);
			//56 创造力
		//	SharedPreferencesUtil.setPassApkCountForCZL56(RestoreFactory.this, MyApplication.default_passed_apk_czl56);
				//专注力
		//	SharedPreferencesUtil.setpassApkCountForZZL(RestoreFactory.this, MyApplication.default_passed_apk_zzl);
			//更新积分为0
		//	data_base.updateTheTotalScore(RestoreFactory.this, "total");

	    	//关机
		//	Intent intent = new Intent(Intent.ACTION_REBOOT);
        //    intent.putExtra(Intent.EXTRA_KEY_CONFIRM, false);
           //其中false换成true,会弹出是否关机的确认窗口
       //       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      //        startActivity(intent);

           sendBroadcast(new Intent("android.intent.action.MASTER_CLEAR"));

			break;

		default:
			break;
		}
	}

	
	
}
