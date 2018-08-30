package com.android.dao;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import android.content.ContentValues;

import com.android.alilo.R;

public class DataBaseUtil {
	
	private static final String DATA_PATH = "/data/data/com.booyue.l1/databases/info.db";
	
	public SQLiteDatabase database;

	@SuppressLint("ShowToast") public SQLiteDatabase open(Context context) {
		//Log.v("alilo", "DATA_PATH IS"+DATA_PATH );
		File file = new File(DATA_PATH);
		if(!file.exists()){
		//	Log.v("jack", "jack ,running here means the file path does not exsit......");
			Toast.makeText(context, context.getString(R.string.total_score_unexist), Toast.LENGTH_SHORT);
			return null;
		}
//		Log.v("alilo", "it is opening the database now .........");
		return database = SQLiteDatabase.openDatabase(DATA_PATH, null, SQLiteDatabase.OPEN_READWRITE);
	}
	
	public void close(){
		if(database!=null){
			database.close();
		}
	}
	
	public int getTotalScore(Context context){
		int score = 0;
	   SQLiteDatabase data_base =open(context);
	   if(data_base==null){
		//   Log.v("alilo", "THE DATABASE IS NULL....");
		   return -1;
	  }
	//   Log.v("jack", "THE DATABASE OPENED SUCCESSFULLY");
	   String sql = "SELECT   total FROM  score";
	  Cursor mcursor = data_base.rawQuery(sql, null);
		if(mcursor==null){
			Log.v("alilo", "THE CURSOR IS A NEGATIVE VALUE....");
			return -1;
		}
		
     	//Log.v("alilo", "the cursor is ok to read");
     	while(mcursor.moveToNext()){
     		jj= mcursor.getColumnIndex("total");
     		score = mcursor.getInt(jj);
     	}
			Log.v("alilo", "jjjjjjjjjjjjjjjjjjjjjjj::"+jj);

	//	Log.v("alilo", "get the score..."+score);
		close();
		return score;
	}
	int jj ;


		public void updateTheTotalScore(Context context,String key){
		SQLiteDatabase data_base = open(context);
		if(data_base==null){
			return ;
		}
		ContentValues values = new ContentValues();
	     values.put(key, 0);
		data_base.update("score", values, "id=?", new String[]{"1"});
		close();
	}
	
}
