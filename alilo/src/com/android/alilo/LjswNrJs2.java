package com.android.alilo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.CompoundButton;
import com.android.myUtils.Utils;

public class LjswNrJs2 extends Activity {
	
	ImageView back;
	ImageView imageView_noPick;
	CheckBox checkbox;
	
	
	@Override      
	public void onCreate(Bundle savedInstanceState) {         
		super.onCreate(savedInstanceState);          
		setContentView(R.layout.ljswjs_dialog);   
		 Utils.addActivity(this);
		back = (ImageView)findViewById(R.id.back);
		back.setOnTouchListener(touchListener);
		checkbox = (CheckBox) findViewById(R.id.checkbox);
		checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				LjswNrJs2.this.finish();
				
			}
		});

	}
	
    OnTouchListener touchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (v.getId()) {			
				case R.id.back:
					LjswNrJs2.this.finish();
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
