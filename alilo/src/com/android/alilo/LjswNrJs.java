package com.android.alilo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.android.myUtils.Utils;

public class LjswNrJs extends Activity {
	
	ImageView back;
	ImageView imageView_noPick;
	
	/////////////////////20170118_wangjx_��ӵ�һ�ν���������浯�����ݽ���
    public static final String DATA = "data";  
    public static final String CONTENT = "content";  
      
    public static int nIsReminder = 1;//�Ƿ����ѣ�0�����ѣ�1���ѣ��״ν�����Ҫ����  
    public static int  REMINDER_NO = 0;  
    public static int  REMINDER_YES  = 1;  
    /////////////////////////////////////////////////////////////////////
	
	@Override      
	public void onCreate(Bundle savedInstanceState) {         
		super.onCreate(savedInstanceState);          
		setContentView(R.layout.ljswjs_dialog);   
		sendBroadcast(new Intent("android.intent.action.open.bg_music"));
		 Utils.addActivity(this);
		back = (ImageView)findViewById(R.id.back);
		back.setOnTouchListener(touchListener);
	//	back.setVisibility(View.GONE);
		
		/////////////////////20170118_wangjx_��ӵ�һ�ν���������浯�����ݽ���		
		 CheckBox checkBox = (CheckBox)this.findViewById(R.id.checkbox);  
	        //�󶨼�����  
	        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  
	        {  
	            @Override  
	            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)  
	            {  
	                if (isChecked)  
	                {  
	                    nIsReminder = REMINDER_NO;	                      
	                    //��ת����  
	                    Intent intent = new Intent(LjswNrJs.this,TestActivityLJSW.class);  
	                    startActivity(intent);  
	                    LjswNrJs.this.finish(); 
	                }  
	                else   
	                {  
	                    nIsReminder = REMINDER_YES;  
	                }  
	            }  
	            
	        });  
		
	}
	

	public void next_activity(View view)  
    {  
        //��������  
        SharedPreferences.Editor sharedata = getSharedPreferences(DATA, 0).edit();  
        sharedata.putInt(CONTENT, nIsReminder);  
        sharedata.commit();  
          
        //��ת����  
        Intent intent = new Intent(LjswNrJs.this,TestActivityLJSW.class);  
        startActivity(intent);  
        LjswNrJs.this.finish();  
    }  
	/////////////////////////////////////////////////////////////////////
	
    OnTouchListener touchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (v.getId()) {			
				case R.id.back: 
					//LjswNrJs.this.finish(); 
				Intent it = new Intent(LjswNrJs.this,TestActivityLJSW.class);
					startActivity(it);
					LjswNrJs.this.finish(); 
					break;
				default:
					break;
				}
			return false;
		}
	};
	
	void onTouchChange(String methodName, int eventAction) {
		if ("back".equals(methodName)) {
			if (eventAction == MotionEvent.ACTION_DOWN) {
				Log.d("wwwwwwwwjx","ACTION_DOWN=======");      
	        }else if (eventAction == MotionEvent.ACTION_UP){
	        	Log.d("wwwwwwwwjx","ACTION_UP=======");      
			}else if (eventAction == MotionEvent.ACTION_MOVE) {
				Log.d("wwwwwwwwjx","ACTION_MOVE=======");
			}
		}
	};
	
}
