package com.android.myUtils;

import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.widget.Toast;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.alilo.AudioService;
import com.android.alilo.R;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;

public class Utils {


private static List<Activity> lists = new ArrayList<>();
	public static void addActivity(Activity activity){
		lists.add(activity);
	}
	
	public static void clearActivity(){
		if(lists!=null){
			for(Activity activity:lists){
				activity.finish();
			}
			lists.clear();
		}
	}






/**
	 * check the internet is ok for using.
	 * @param context
	 * @return
	 */
	public static boolean isNetWorkAvailable(Context context){
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	   if(connectivity!=null){
		   NetworkInfo info = connectivity.getActiveNetworkInfo();
		   if(info!=null&&info.isConnected()){
			   if(info.getState()== NetworkInfo.State.CONNECTED){
				   return true;
			   }
		   }
	   }
	 return false;
	}




	
	public static void clickTheTargetApk34(Context parentContext,int groupIndex,int childIndex,boolean islocked){
		//============================3-4岁===================================
		//第一组
		if(groupIndex==0&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTYigui");
		}
		if(groupIndex==0&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTQiqiu");
		}
		if(groupIndex==0&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTuan");
		}
		if(groupIndex==0&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTDream");
		}
		if(groupIndex==0&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTXunzhaoMaxituan");
		}
		if(groupIndex==0&&childIndex==5){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTLearnDraw");
		}
		
		//第二组
		if(groupIndex==1&&childIndex==0){ 
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTZaocan");
		}
		if(groupIndex==1&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTZaochen");
		}
		if(groupIndex==1&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTPlayGame");
		}
		if(groupIndex==1&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTLikeDraw");
		}
		if(groupIndex==1&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWonderfulDream");
		}
		
		//第三组
		if(groupIndex==2&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTToys");
		}
		if(groupIndex==2&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTKaPian");
		}
		if(groupIndex==2&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTBuildingBlocks");
		}
		if(groupIndex==2&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTShouGong");
		}
		if(groupIndex==2&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTFeijiupinliyong");
		}
		
		//第四组
		if(groupIndex==3&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTKantuShushu");
		}
        if(groupIndex==3&&childIndex==1){
        	if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
        	parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTZhaoBuTong");
			
		}
	    if(groupIndex==3&&childIndex==2){
	    	if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
	    	parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTCounting");
			
		}
        if(groupIndex==3&&childIndex==3){
        	if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
        	parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTTortoise");
		}
        if(groupIndex==3&&childIndex==4){
        	if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
        	parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTCard");
		}
        if(groupIndex==3&&childIndex==5){
        	if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
        	parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWanYiWan");
		}
	}
	
	
	/**
	 * 4-5岁 中所有的apk进行点击
	 * @param parentContext
	 * @param groupIndex
	 * @param childIndex
	 */
	public static void clickTheTargetApk45(Context parentContext,int groupIndex,int childIndex,boolean islocked){
		//第一组 交通与常识 
		if(groupIndex==0&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTTraffic");
		}
		if(groupIndex==0&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTTrafficKnow");
		}
		if(groupIndex==0&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTQicheAnquan");
		}
		if(groupIndex==0&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTTuBiao");
		}
		if(groupIndex==0&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTTrafficColor");
		}
		
		//第二组 我们的宠物
		if(groupIndex==1&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTPetShouce");
		}
		if(groupIndex==1&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWodongPet");
		}
		if(groupIndex==1&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.RabbitDrawLine3");
		}
		if(groupIndex==1&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTRenXue");
		}
		if(groupIndex==1&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTQimiaodexdw");
		}
		
		//第三组 观察与联想
		if(groupIndex==2&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWodangjia");
		}
		if(groupIndex==2&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTMangludezm");
		}
		if(groupIndex==2&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTKanyikanzyz");
		}
		if(groupIndex==2&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWoaitianse");//暂未有对应的应用，用此应用暂用。
		}
		if(groupIndex==2&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.RabbitLineUp");//暂未有对应的应用，用此应用暂用。
		}
		
		//第四组 观察与注意RabbitLineUp
		if(groupIndex==3&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTYoulechangdyt");
		}
		if(groupIndex==3&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTYouquylc");
		}
		if(groupIndex==3&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTGuanchatianse");//暂未有对应的应用，用此应用暂用。
		}
		if(groupIndex==3&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTKanlxTianys");
		}
		if(groupIndex==3&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTShengrijuhui");//暂未有对应的应用，用此应用暂用。
		}
		
		//第五组 火火兔仔儿童活动室
		if(groupIndex==4&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWanjuzhaozhao");
		}
		if(groupIndex==4&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTDuoyangdetuxing");
		}
		if(groupIndex==4&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTTongxinglts");
		}
			if(groupIndex==4&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTJimuwangguo");
		}
			if(groupIndex==4&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWolaixuedapei");
		}
	



		//第六组 我们就这么穿衣
		//if(groupIndex==5&&childIndex==0){
	//		if(islocked){
	//			Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
	//			return ;
	//		}
	//		parentContext.stopService(new Intent(parentContext,AudioService.class));
	//		doStartApplicationWithPackageName(parentContext,"air.HHTYigui");//用其他的应用暂用。
	//	}
		
	}
	/**
	 * 5-6岁的包操作。
	 * @param parentContext
	 * @param groupIndex
	 * @param childIndex
	 */
	public static void clickTheTargetApk56(Context parentContext,int groupIndex,int childIndex,boolean islocked){
		//第一组 逻辑游戏
		if(groupIndex==0&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTGuanchaxiaonengshou");
		}
		if(groupIndex==0&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTDajialaipipei");
		}
		if(groupIndex==0&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTKanyikanpyp");
		}
		if(groupIndex==0&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTJingzilideys");
		}
			if(groupIndex==0&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.RabbitLineUp2");
		}

		
		//第二组 在动物园
	if(groupIndex==1&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTGuangdongwuyuan");
		}
		if(groupIndex==1&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTYouqudedongwu");
		}
		if(groupIndex==1&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTShiwufenpei");
		}
			if(groupIndex==1&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.RabbitFunnySeq");
		}

			if(groupIndex==1&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTBianRenDongWu");
		}


		
		//第三组 在农庄里
		if(groupIndex==2&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTNongChangYiTian");
		}
		if(groupIndex==2&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTZhaoYiZhao");
		}
				if(groupIndex==2&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTYouqudenongchang");
		}

				if(groupIndex==2&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTZiXiQiaoYiQiao");
		}
				if(groupIndex==2&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.RabbitLineUp3");
		}
	
		//数与数字
				if(groupIndex==2&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTZiXiQiaoYiQiao");
		}
				if(groupIndex==2&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.RabbitLineUp3");
		}
				if(groupIndex==2&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTZiXiQiaoYiQiao");
		}
				if(groupIndex==2&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.RabbitLineUp3");
		}
				if(groupIndex==3&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTShuShuYuDuiYing");
		}
				if(groupIndex==3&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWoLaiRenShuZi");
		}
				if(groupIndex==3&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWoAiXieShuZi");
		}
				if(groupIndex==3&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTShuZiXiaoShiJie");
		}
				if(groupIndex==3&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTShuZiDeZuHe");
		}

       
	
	}
	
	/**
	 * 根据包名打开对应的应用。
	 * @param packagename
	 */
	public static void doStartApplicationWithPackageName(Context parentContext,String packagename) {  
	    // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等  
	    PackageInfo packageinfo = null;  
	    Log.v("jack", "packagename：："+packagename);
	    try {  
	        packageinfo = parentContext.getPackageManager().getPackageInfo(packagename, 0);  
	    } catch (NameNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	    
	    if (packageinfo == null) {  
	    	Log.v("jack", "packageinfo为空");
	        return;  
	    }  
	  
	    // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent  
	    Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);  
	    resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
	    resolveIntent.setPackage(packageinfo.packageName);  
	    
	    // 通过getPackageManager()的queryIntentActivities方法遍历  
	    List<ResolveInfo> resolveinfoList = parentContext.getPackageManager()  
	            .queryIntentActivities(resolveIntent, 0);  
	  
	    ResolveInfo resolveinfo = resolveinfoList.iterator().next();  
	    if (resolveinfo != null) {  
	        // packagename = 参数packname  
	        String packageName = resolveinfo.activityInfo.packageName;  
	        // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]  
	        String className = resolveinfo.activityInfo.name;  
	        // LAUNCHER Intent  
	        Intent intent = new Intent(Intent.ACTION_MAIN);  
	        intent.addCategory(Intent.CATEGORY_LAUNCHER);  
	  
	        // 设置ComponentName参数1:packagen;ame参数2:MainActivity路径  
	        ComponentName cn = new ComponentName(packageName, className);  
	        intent.setComponent(cn);  
	        Log.v("jack", "要执行启动activity...");
		    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        parentContext.startActivity(intent);  
	    }  
	}

//数学空间的点击逻辑
	public static void clickTheTargetApkSXKJ(Context parentContext,int groupIndex,int childIndex,boolean islocked){
		//数数与比较
		if(groupIndex==0&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTKantuShushu");
		}
		if(groupIndex==0&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTZhaoBuTong");
		}
		if(groupIndex==0&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTCounting");
		}
		if(groupIndex==0&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTTortoise");
		}
		if(groupIndex==0&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTCard");
		}
		if(groupIndex==0&&childIndex==5){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWanYiWan");
		}
		
		//养宠物，学思考
		if(groupIndex==1&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTPetShouce");
		}
		if(groupIndex==1&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTRenXue");
		}
		if(groupIndex==1&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWolaixuedapei");
		}
		if(groupIndex==1&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTShiwufenpei");
		}
		if(groupIndex==1&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.RabbitFunnySeq");
		}
		if(groupIndex==1&&childIndex==5){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTZhaoYiZhao");
		}
		
		
		
		
		//数学思维里面的数与数字
		if(groupIndex==2&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTShuShuYuDuiYing");
		}
		
		//数学思维里面的数与数字
		if(groupIndex==2&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWoLaiRenShuZi");
		}
		if(groupIndex==2&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWoAiXieShuZi");
		}
		if(groupIndex==2&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTShuZiXiaoShiJie");
		}
		if(groupIndex==2&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTShuZiDeZuHe");
		}
		
		
		//数学空间，加减运算
		if(groupIndex==3&&childIndex==0){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTJiaFaYunSuanShi");
		}
		if(groupIndex==3&&childIndex==1){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTXueJiaFa");
		}
		if(groupIndex==3&&childIndex==2){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTWoHuiYongJiaFa");
		}
			if(groupIndex==3&&childIndex==3){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTJianFaYunSuanShi");
		}
				if(groupIndex==3&&childIndex==4){
			if(islocked){
				Toast.makeText(parentContext, parentContext.getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
				return ;
			}
			parentContext.stopService(new Intent(parentContext,AudioService.class));
			doStartApplicationWithPackageName(parentContext,"air.HHTShuZiDeBiJiao");
		}

	
	}

	
}
