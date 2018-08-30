package com.android.alilo.setting;

import com.android.alilo.R;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.android.alilo.HomePageActivity;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.android.myUtils.Utils;

public class SettingSoundManager extends Activity {
	ImageButton sound_title_back;
	TextView tv_sound_percentage;
	ImageView iv_sound_mute;
	SeekBar sb_sound;

	AudioManager mAudioManager;
	
	int maxVolume;
	int currentVolume;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sound_manager_page);
		 Utils.addActivity(this);
		 mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE); 
		 init();
	}
	
	void init(){
		
		 maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		 currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);  
		 sound_title_back=(ImageButton)findViewById(R.id.sound_title_back);
		 sound_title_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		 tv_sound_percentage=(TextView)findViewById(R.id.tv_sound_percentage);
		 tv_sound_percentage.setText(getSoundPercentage(currentVolume,maxVolume));
		  tv_sound_percentage.setTypeface(HomePageActivity.face);

		 iv_sound_mute=(ImageView)findViewById(R.id.iv_sound_mute);
		 sb_sound=(SeekBar)findViewById(R.id.sb_sound);
		 sb_sound.setMax(maxVolume);
		 sb_sound.setProgress(currentVolume);
		 sb_sound.setOnSeekBarChangeListener(seekBarChangeListener);
	}
	
	String getSoundPercentage(int currenVolume,int maxVolume){
		int soundVolume=(int)((((float)currenVolume)/((float)maxVolume))*100);
		return soundVolume+"%";
	}
	

	OnSeekBarChangeListener seekBarChangeListener=new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if(progress==0)
				iv_sound_mute.setBackgroundResource(R.drawable.ic_settings_sound_mute);
			else
				iv_sound_mute.setBackgroundResource(R.drawable.ic_settings_sound_small);
			
			tv_sound_percentage.setText(getSoundPercentage(progress,maxVolume));
			System.out.println(progress);
			setSystemVolume(progress);
			
		}
	};
	
	void setSystemVolume(int progress){
//		int flag=0;
//		
//		if(progress>currentVolume)
//			flag=1;
//		currentVolume=progress;
		
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0); 
		
//		//降低音量，调出系统音量控制  
//		if(flag == 0){  
//		  mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER,  
//		                            AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);  
//		}  
//		//增加音量，调出系统音量控制  
//		else if(flag == 1){  
//		  mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,  
//		                            AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);  
//		}
		
	}


}
