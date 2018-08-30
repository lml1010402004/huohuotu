package com.android.alilo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import android.os.Handler;
import android.os.Message;
import com.android.myUtils.Utils;

public class SafetyMachinesm extends Activity{

	public static Activity mAcitivty;
	
	private ImageView show_battery_level_imageview;
private TextView power_percentage;
	int power;
		int index;
		private myHandler mHandler = new myHandler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.safemachinesm);
		 Utils.addActivity(this);
			mAcitivty = this;
			power_percentage = (TextView)findViewById(R.id.power_percentage);
			power_percentage.setTypeface(HomePageActivity.face);
		show_battery_level_imageview = (ImageView) findViewById(R.id.show_battery_imageview);
		Bundle mybundle = this.getIntent().getExtras();
		 index = mybundle.getInt("index");
	power = mybundle.getInt("power");
	//	showTargetBatteryLevelWithIndex(index);
	power_percentage.setText(power+"%");
	Message msg = new Message();
		msg.what = 1001;
		mHandler.sendMessage(msg);


	}


		private class myHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1001:
				showTargetBatteryLevelWithIndex(index);
				break;

			default:
				break;
			}
		}
	}
	
    private void showTargetBatteryLevelWithIndex(int index){
    	switch (index) {
		case 0:
				power_percentage.setText(power+"%");
			show_battery_level_imageview.setImageResource(R.drawable.battery_notice0);
	
			break;
		case 1:
			power_percentage.setText(power+"%");
			show_battery_level_imageview.setImageResource(R.drawable.battery_notice1);
		
			break;
		case 2:
			power_percentage.setText(power+"%");
			show_battery_level_imageview.setImageResource(R.drawable.battery_notice2);

			break;
		case 3:
			power_percentage.setText(power+"%");
			show_battery_level_imageview.setImageResource(R.drawable.battery_notice3);
		
			break;
		case 4:
			power_percentage.setText(power+"%");
			show_battery_level_imageview.setImageResource(R.drawable.battery_notice4);
		
			break;
		case 5:
				power_percentage.setText(power+"%");
			show_battery_level_imageview.setImageResource(R.drawable.battery_notice5);
	
			break;
		default:
			break;
		}
    }
	
	

}
