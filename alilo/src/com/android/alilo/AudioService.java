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
 * Ϊ�˿���ʹ���ں�̨�������֣�������ҪService 
 * Service���������ں�̨���һЩ����Ҫ���û������Ķ��� 
 * @author Administrator 
 * 
 */  
public class AudioService extends Service {

    private MediaPlayer mediaPlayer = null;

    private boolean isReady = false;

    @Override
    public void onCreate() {
        //onCreate��Service������������ֻ�����һ��
        super.onCreate();

        //��ʼ��ý�岥����
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
            //��������������Ϊѭ������
            mediaPlayer.setLooping(true);
			}
    //    }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //ÿ�ε���Context��startService���ᴥ��onStartCommand�ص�����
        //����onStartCommand��Service�����������п��ܻᱻ���ö��
        if(isReady && !mediaPlayer.isPlaying()){
            //���ű�������
		//	if(HomePageActivity.screenonoff){
			if(getTopActivity(this).equals("com.android.alilo")){
            mediaPlayer.start();
			}
			//}
            //Toast.makeText(this, "��ʼ���ű�������", Toast.LENGTH_LONG).show();
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //��Service�в�֧��bindService���������Դ˴�ֱ�ӷ���null
        return null;
    }

    @Override
    public void onDestroy() {
        //������Context��stopService��Service�ڲ�ִ��stopSelf����ʱ�ͻᴥ��onDestroy�ص�����
        super.onDestroy();
        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()){
                //ֹͣ��������
                mediaPlayer.stop();
            }
            //�ͷ�ý�岥������Դ
            mediaPlayer.release();
            //Toast.makeText(this, "ֹͣ���ű�������", Toast.LENGTH_LONG).show();
        }


    }


	public static String getTopActivity(Context context) {  
        try {  
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);  
            //��ȡ�������е�task�б�����1��ʾ������е�task��ͨ�������������б���task��Ŀ��������еĿ�ǰ  
            List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);  
  
            if (runningTaskInfos != null && runningTaskInfos.size() != 0) {  
                return (runningTaskInfos.get(0).baseActivity).getPackageName();  
            }  
        } catch (Exception e) {  
          //  logger.error("ջ��Ӧ��:" + e);  
        }  
        return "";  
    } 
}