package com.android.myUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.android.alilo.R;

public class BaseConfig{
	
	private static final String USERINFORMATION = "userinformation";
	//static UserInfo info = null;
	static String str ;
	public static String readProduct(Context context,String key){
		SharedPreferences preferences = context.getSharedPreferences(USERINFORMATION,
				context.MODE_PRIVATE);
		if(key.equals("name")){
	 str= preferences.getString(key, preferences.getString("name", context.getResources().getString(R.string.zhihuibaobao)));
		}
		if(key.equals("age")){
			str= preferences.getString(key, preferences.getString("age", "5"));
		}
		if(key.equals("gender")){
			str= preferences.getString(key, "1");
		}
		return str;
	}
	public static void saveProduct(Context context,String key,String value) {
		@SuppressWarnings("static-access")
		SharedPreferences preferences = context.getSharedPreferences(USERINFORMATION,
				context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

}
