package com.android.alilo.setting;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.alilo.HomePageActivity;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.alilo.R;
import com.android.myUtils.Utils;

@SuppressLint("SimpleDateFormat") public class SettingTimerManager extends Activity {
	
	   //获取时间格式
    DateFormat fmtDateAndTime = DateFormat.getDateInstance();
    DateFormat fmttime = DateFormat.getDateTimeInstance();
    Calendar c = Calendar.getInstance(Locale.CHINA);
    TextView TimeView = null;
    private TextView setTime,setDate,Date_textview,Time_textview;
    private LinearLayout Datelinearlayout;
    private LinearLayout Timelinearlayout,timer_confirm_text ;
    
    //获取系统时间 
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);   
            long when = c.getTimeInMillis();

    		if (when / 1000 < Integer.MAX_VALUE) {
    			SystemClock.setCurrentTimeMillis(when);
    		}  
    		updateLabel();
        }       
    };
    
 
 
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            long when = c.getTimeInMillis();
    		if (when / 1000 < Integer.MAX_VALUE) {
    			SystemClock.setCurrentTimeMillis(when);
    		}
    		updateLabel1();
        }
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.timer_manager_page);
		 Utils.addActivity(this);
		ImageButton timer_title_back = (ImageButton) findViewById(R.id.timer_title_back);
		timer_title_back.setOnClickListener(clickListener);

		TextView timer_confirm_text = (TextView) findViewById(R.id.timer_confirm_text);
		timer_confirm_text.setOnClickListener(clickListener);
		
		Date_textview = (TextView) findViewById(R.id.Date_textview);
		Time_textview = (TextView) findViewById(R.id.Time_textview);
		Date_textview.setTypeface(HomePageActivity.face);
		Time_textview.setTypeface(HomePageActivity.face);

		//setTime = (TextView) findViewById(R.id.setTime);
	//	setDate = (TextView) findViewById(R.id.setDate);
		Date_textview.setText(getSystemDate());
		Time_textview.setText(getSystemHour());
		Datelinearlayout = (LinearLayout) findViewById(R.id.date_linearlayout);
		Timelinearlayout = (LinearLayout) findViewById(R.id.time_linearlayout);
		timer_confirm_text = (TextView) findViewById(R.id.timer_confirm_text);
		timer_confirm_text.setOnClickListener(clickListener);
		Timelinearlayout.setOnClickListener(clickListener);
		Datelinearlayout.setOnClickListener(clickListener);
        
       
    }
    
    private String getSystemDate(){
    	SimpleDateFormat formater = new SimpleDateFormat("yyyy年MM月dd日 ");
    	Date curDate = new Date(System.currentTimeMillis());
    	String str = formater.format(curDate);
    	Log.v("jack", "获取系统的日期"+str);
    	return str;
    }
    
    SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
    @SuppressLint("SimpleDateFormat") private String getSystemHour(){
    	Date curDate = new Date(System.currentTimeMillis());
    	String str = formater.format(curDate);
    	Log.v("jack", "获取系统时间"+str);
    	return str;
    }
    
    OnClickListener clickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.timer_title_back:
				finish();
				break;
			case R.id.timer_confirm_text:
				SystemClock.setCurrentTimeMillis(c.getTimeInMillis());
				finish();
				break;
				
			case R.id.date_linearlayout:
		         new DatePickerDialog(SettingTimerManager.this,
	                        d,
	                        c.get(Calendar.YEAR),
	                        c.get(Calendar.MONTH),
	                        c.get(Calendar.DAY_OF_MONTH)).show();  
				break;
			case R.id.time_linearlayout:
				 new TimePickerDialog(SettingTimerManager.this,
	                        t,
	                        c.get(Calendar.HOUR_OF_DAY),
	                        c.get(Calendar.MINUTE),
	                        true).show();
				break;
			default:
				break;
			}
		}
	};
	String Datestr = "";
	String Timestr = "";
	
	private void updateLabel() {
		if(fmtDateAndTime!=null&&c!=null&&c.getTime()!=null){
			Datestr = fmtDateAndTime.format(c.getTime());
			Date_textview.setText(Datestr);
		   }
        }
	private void updateLabel1() {
		if(fmtDateAndTime!=null&&c!=null&&c.getTime()!=null){
			Timestr = formater.format(c.getTime());
			Time_textview.setText(Timestr);
		}
        }

}