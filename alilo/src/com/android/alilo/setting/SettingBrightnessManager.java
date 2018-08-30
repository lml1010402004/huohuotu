package com.android.alilo.setting;

import com.android.alilo.R;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.provider.Settings.SettingNotFoundException;
import com.android.alilo.HomePageActivity;
import com.android.myUtils.Utils;

public class SettingBrightnessManager extends Activity {
	ImageButton brightness_title_back;
	SeekBar sb_brightness;
	private TextView show_brightness_value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brightness_manager_page);
		 Utils.addActivity(this);
		init();
	}

	void init() {
		show_brightness_value = (TextView) findViewById(R.id.show_brightness_value);
		show_brightness_value.setTypeface(HomePageActivity.face);
		int brightness=Settings.System.getInt(getContentResolver(),
				Settings.System.SCREEN_BRIGHTNESS, 175);
		Log.v("jack", "系统当前的的brightness的值："+brightness);
		brightness_title_back = (ImageButton) findViewById(R.id.brightness_title_back);
		brightness_title_back.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		sb_brightness = (SeekBar) findViewById(R.id.sb_brightness);
		sb_brightness.setProgress(brightness-100);
		sb_brightness.setOnSeekBarChangeListener(seekBarChangeListener);

	try {
			int value = Settings.System.getInt(getContentResolver(),
						Settings.System.SCREEN_BRIGHTNESS);
			show_brightness_value.setText("亮度值："+value);
		} catch (SettingNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	OnSeekBarChangeListener seekBarChangeListener=new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			int value = progress+100;
			show_brightness_value.setText("亮度值："+value);
			saveScreenBrightness(progress+100);
		}
	};

	/**
	 * 保存当前屏幕亮度值 100--255
	 */
	private void saveScreenBrightness(int paramInt) {
		try {
			if(paramInt<100){
				paramInt=100;
			}
			Settings.System.putInt(getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS, paramInt);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}


}
