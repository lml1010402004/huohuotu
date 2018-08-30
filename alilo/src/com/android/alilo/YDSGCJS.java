package com.android.alilo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.android.myUtils.Utils;

public class YDSGCJS extends Activity{
	
	ImageView back;
	ImageView imageView_noPick;

	// ///////////////////20170118_wangjx_添加第一次进入二级界面弹出内容介绍
	public static final String DATA = "data";
	public static final String CONTENT = "content";

	public static int nIsReminder = 1;// 是否提醒：0不提醒，1提醒，首次进入需要提醒
	public static int REMINDER_NO = 0;
	public static int REMINDER_YES = 1;

	// ///////////////////////////////////////////////////////////////////
	Bundle bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ydsgc_dialog);
		 Utils.addActivity(this);
		back = (ImageView) findViewById(R.id.back);
		back.setOnTouchListener(touchListener);
	//	back.setVisibility(View.GONE);
bundle = this.getIntent().getExtras();
		// ///////////////////20170118_wangjx_添加第一次进入二级界面弹出内容介绍
		CheckBox checkBox = (CheckBox) this.findViewById(R.id.checkbox);
		// 绑定监听器
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
                  if(bundle!=null){
						int flag = bundle.getInt("flag");
						if(flag==100){
						YDSGCJS.this.finish();
						return;
						}
					}



					nIsReminder = REMINDER_NO;
					// 跳转界面
					Intent intent = new Intent(YDSGCJS.this,
							TestActivityYDSGC.class);
					startActivity(intent);
					YDSGCJS.this.finish();
				} else {
					nIsReminder = REMINDER_YES;
				}
			}

		});
		
	}
	
	OnTouchListener touchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.back:
			Intent it = new Intent(YDSGCJS.this,TestActivityYDSGC.class);
					startActivity(it);
					YDSGCJS.this.finish(); 
				break;
			default:
				break;
			}
			return false;
		}
	};
	
	

}
