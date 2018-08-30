package com.android.alilo;

import java.io.IOException;

import android.app.Service;  
import android.content.Intent;  
import android.media.MediaPlayer;   
import android.os.IBinder;  
import java.util.List;
import android.content.Context;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.ActivityManager;
//import android.app.ActivityManager.RunningAppProcessInfo;
/** 
 * 为了可以使得在后台播放音乐，我们需要Service 
 * Service就是用来在后台完成一些不需要和用户交互的动作 
 * @author Administrator 
 * 
 */  
public class AudioService extends Service {

    private MediaPlayer mediaPlayer = null;

    private boolean isReady = false;

    @Override
    public void onCreate() {
        //onCreate在Service的生命周期中只会调用一次
        super.onCreate();

        //初始化媒体播放器
        mediaPlayer = MediaPlayer.create(this, R.raw.bg_music);
        if(mediaPlayer == null){
            return;
        }

        mediaPlayer.stop();
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.release();
                stopSelf();
                return false;
            }
        });

        try{
            mediaPlayer.prepare();
            isReady = true;
        } catch (IOException e) {
            e.printStackTrace();
            isReady = false;
        }

        if(isReady){
		//	if(HomePageActivity.screenonoff){
            //将背景音乐设置为循环播放
            mediaPlayer.setLooping(true);
			}
    //    }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //每次调用Context的startService都会触发onStartCommand回调方法
        //所以onStartCommand在Service的生命周期中可能会被调用多次
        if(isReady && !mediaPlayer.isPlaying()){
            //播放背景音乐
		//	if(HomePageActivity.screenonoff){
			if(getTopActivity(this).equals("com.android.alilo")){
            mediaPlayer.start();
			}
			//}
            //Toast.makeText(this, "开始播放背景音乐", Toast.LENGTH_LONG).show();
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //该Service中不支持bindService方法，所以此处直接返回null
        return null;
    }

    @Override
    public void onDestroy() {
        //当调用Context的stopService或Service内部执行stopSelf方法时就会触发onDestroy回调方法
        super.onDestroy();
        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()){
                //停止播放音乐
                mediaPlayer.stop();
            }
            //释放媒体播放器资源
            mediaPlayer.release();
            //Toast.makeText(this, "停止播放背景音乐", Toast.LENGTH_LONG).show();
        }


    }


	public static String getTopActivity(Context context) {  
        try {  
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);  
            //获取正在运行的task列表，其中1表示最近运行的task，通过该数字限制列表中task数目，最近运行的靠前  
            List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);  
  
            if (runningTaskInfos != null && runningTaskInfos.size() != 0) {  
                return (runningTaskInfos.get(0).baseActivity).getPackageName();  
            }  
        } catch (Exception e) {  
          //  logger.error("栈顶应用:" + e);  
        }  
        return "";  
    } 
}