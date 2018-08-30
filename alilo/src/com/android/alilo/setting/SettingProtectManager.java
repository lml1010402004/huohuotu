package com.android.alilo.setting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.android.alilo.R;
import com.android.myUtils.SharedPreferencesUtil;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.android.alilo.HomePageActivity;
import com.android.alilo.MonitorService;
import com.android.myUtils.MyApplication;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.Context;
import com.android.myUtils.Utils;

public class SettingProtectManager extends Activity {
	
	ImageView open_nocheck;
	ImageView close_nocheck;
	private File ProtectWakeEnable=null;
	boolean ischeck = false;
	private SeekBar setting_protect_eye_seekbar;
	private int temp_protect_time;
	private TextView show_eye_protect_time;
	private int  protect_eye_flag = -1; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.protect_manager_page);
		Utils.addActivity(this);
		init();
	}
	
	void init() {
		show_eye_protect_time = (TextView) findViewById(R.id.show_eye_protect_time);
		show_eye_protect_time.setTypeface(HomePageActivity.face);
		ImageButton project_title_back = (ImageButton) findViewById(R.id.project_title_back);
		project_title_back.setOnClickListener(clickListener);

		TextView project_confirm_text = (TextView) findViewById(R.id.project_confirm_text);
		project_confirm_text.setOnClickListener(clickListener);
		project_confirm_text.setTypeface(HomePageActivity.face);
		open_nocheck = (ImageView)findViewById(R.id.open_nocheck);
		open_nocheck.setOnClickListener(clickListener);
		//open_nocheck.setOnTouchListener(touchListener);
		open_nocheck.setEnabled(true);
	     
		close_nocheck = (ImageView)findViewById(R.id.close_nocheck);
		close_nocheck.setOnClickListener(clickListener);
		//close_nocheck.setOnTouchListener(touchListener);
		close_nocheck.setEnabled(true);
		
		ProtectWakeEnable=new File(MyApplication.WRITEPROTECTEYEFILEPATH);
		//ProtectWakeEnable=new File("/sys/class/display/HDMI/enable");
		Log.v("6666666666666666666666666","================================" + ProtectWakeEnable );		
		setting_protect_eye_seekbar  = (SeekBar) findViewById(R.id.seekBar1);
		setting_protect_eye_seekbar.setOnSeekBarChangeListener(seekBarChangeListener);
		setting_protect_eye_seekbar.setProgress(SharedPreferencesUtil.getProtectEyesTime(SettingProtectManager.this)-8);
		setting_protect_eye_seekbar.setMax(60);

		SharedPreferences sharedata = this.getSharedPreferences("protect_eye", Context.MODE_PRIVATE);
	    int flag = sharedata.getInt("flag", 0);
		Log.v("jack", "flag的值="+flag);
	    if(flag ==0){
		//初始化打开距离护眼开关
		open_nocheck.setImageResource(R.drawable.open_check);
		close_nocheck.setImageResource(R.drawable.close_nocheck);
		openProtect();
	    }else{
	    	open_nocheck.setImageResource(R.drawable.open_nocheck);
	    	close_nocheck.setImageResource(R.drawable.close_check);
	        closeProtect();
	    }

	}		
	
	OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			
		}
		
		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
	
			  arg1 = arg1+8;
			   show_eye_protect_time.setText("定时:"+arg1+"mins");
			    temp_protect_time = arg1;
		}
	};
		
	// 打开护眼	
	public void openProtect() {
	
		try {
            FileInputStream in = new FileInputStream(ProtectWakeEnable);
            int i = in.read() - '0';
            in.close();
            FileOutputStream fos = new FileOutputStream(ProtectWakeEnable);
            fos.write('1');
            fos.flush();
            fos.close();
            Log.d("openProtect","innnnnnnnnnnn==");
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
            	e.printStackTrace();
		}

	}

	// 关闭护眼
	public void closeProtect() {	
		Log.d("closeProtect","outttttttttttttt==");
		try {
            FileInputStream in = new FileInputStream(ProtectWakeEnable);
            int i = in.read() - '0';
            in.close();
            FileOutputStream fos = new FileOutputStream(ProtectWakeEnable);
            fos.write('0');
            fos.flush();
            fos.close();
            Log.d("closeProtect","innnnnnnnnnnn==");
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}

	}
	

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.project_title_back:
				finish();
				break;
			case R.id.project_confirm_text:
						SharedPreferencesUtil.setProtectEyesTime(SettingProtectManager.this, temp_protect_time);
				if(protect_eye_flag==0){
					
					SharedPreferences shareData = getSharedPreferences("protect_eye", Context.MODE_PRIVATE);
					Editor editor = shareData.edit();
					editor.putInt("flag", 0);
						Log.v("jack", "flag的值=0");
					editor.commit();
					
				  if(HomePageActivity.serviceIntent!=null){
					Log.v("jack", "进入护眼设置模式"+"重新设置的时间为"+temp_protect_time);
				stopService(HomePageActivity.serviceIntent);
				//重新开启护眼模式
				Log.v("jack", "重新开启护眼模式服务");
				}
		
				HomePageActivity.serviceIntent = new Intent(SettingProtectManager.this,MonitorService.class);
				startService(HomePageActivity.serviceIntent);
				openProtect();
				}else if(protect_eye_flag ==1){
					SharedPreferences shareData1 = getSharedPreferences("protect_eye", Context.MODE_PRIVATE);
					Editor editor1 = shareData1.edit();
					editor1.putInt("flag", 1);
					Log.v("jack", "flag的值=1");
					editor1.commit();

						if(HomePageActivity.serviceIntent!=null){
				Log.v("jack", "进入护眼设置模式"+"重新设置的时间为"+temp_protect_time);
				stopService(HomePageActivity.serviceIntent);
				//重新开启护眼模式
				Log.v("jack", "重新开启护眼模式服务");
                HomePageActivity.serviceIntent = null;
				}
					
					closeProtect();
				}

				finish();
				break;
			case R.id.open_nocheck:

				open_nocheck.setImageResource(R.drawable.open_check);
				close_nocheck.setImageResource(R.drawable.close_nocheck);
				protect_eye_flag = 0;
			
					
			//	open_nocheck.setImageResource(R.drawable.open_check);
				//close_nocheck.setImageResource(R.drawable.close_nocheck);
			//	openProtect();		
			//	SharedPreferences shareData = getSharedPreferences("protect_eye", Context.MODE_PRIVATE);
			//	Editor editor = shareData.edit();
			//	editor.putInt("flag", 0);
				//	Log.v("jack", "flag的值=0");
				//editor.commit();
				break;
			case R.id.close_nocheck:
			    close_nocheck.setImageResource(R.drawable.close_check);
				open_nocheck.setImageResource(R.drawable.open_nocheck);
				protect_eye_flag = 1;


			//	close_nocheck.setImageResource(R.drawable.close_check);
			//	open_nocheck.setImageResource(R.drawable.open_nocheck);
			//	closeProtect();
			//	SharedPreferences shareData1 = getSharedPreferences("protect_eye", Context.MODE_PRIVATE);
			//	Editor editor1 = shareData1.edit();
			//	editor1.putInt("flag", 1);
			//	Log.v("jack", "flag的值=1");
			//	editor1.commit();
				break;				
			default:
				break;
			}
		}
	};
	
}
