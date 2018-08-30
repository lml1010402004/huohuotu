package com.android.alilo;

import java.util.ArrayList;
import java.util.List;

import com.android.adapter.AppsAdapterCZL1;  
import com.android.alilo.R;
import com.android.model.ApkItem;
import com.android.myUtils.MyApplication;
import com.android.myUtils.SharedPreferencesUtil;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.content.res.TypedArray;
import android.widget.Toast;

public class TestActivityCZLXT3 extends Fragment {
	View view;
	ExpandableListView expandableListView;
//	ListViewAdapterCZL1 treeViewAdapter;
	AppsAdapterCZL1 appAdapter;
	private ArrayList<ApkItem> arraylist;
	private ApkItem item = null;
	
	//private int menu_toolbar_image_array00[]={R.drawable.czl_sqmjqts_title,R.drawable.czl_etjlsj_title,
	//		R.drawable.czl_etjzlw_title,R.drawable.czl_qsjgsd_title,R.drawable.czl_hhtdlw_title,R.drawable.czl_xxrbgz_title,
	//		R.drawable.czl_pdwzfz_title,R.drawable.czl_kpffl_title,R.drawable.czl_kjzpqc_title,R.drawable.czl_jtbzxkp_title,
	//		R.drawable.czl_hhwpsxx_title};
	//private int menu_toolbar_image_array0[] = {R.drawable.czl_sqmjqts_up,
	//		R.drawable.czl_etjlsj_up, R.drawable.czl_etjzlw_up,
	//		R.drawable.czl_qsjgsd_up,  R.drawable.czl_hhtdlw_up,
	//		R.drawable.czl_xxrbgz_up, R.drawable.czl_pdwzfz_up,
	//		R.drawable.czl_kpffl_up,  R.drawable.czl_kjzpqc_up,
	//		R.drawable.czl_jtbzxkp_up, R.drawable.czl_hhwpsxx_up
	//	};
	private GridView app_list;
	private int[] menu_toolbar_image_array0 ;
	private int[] menu_toolbar_image_array00;
	//public String[] groups = { ""};
	//public String[][] child = { { "" }};
	
    public TestActivityCZLXT3() {
        // Required empty public constructor
    }

	  int array_length = 0;
    private void initPicsIndexForCZL56(){
    	TypedArray array_icons_czl56= getResources().obtainTypedArray(R.array.czl56_pic_index_icon);
    	array_length = array_icons_czl56.length();
    	menu_toolbar_image_array0 = new int[array_length];
    	for(int i=0;i<array_length;i++){
    		menu_toolbar_image_array0[i] = array_icons_czl56.getResourceId(i, R.drawable.apk_locked);
    	}
    	array_icons_czl56.recycle();
    	TypedArray array_titles_czl56 = getResources().obtainTypedArray(R.array.czl56_pic_index_title);
    	menu_toolbar_image_array00 = new int[array_length];
    	for(int j=0;j<array_length;j++){
    		menu_toolbar_image_array00[j] = array_titles_czl56.getResourceId(j, R.drawable.game2_11_title);
    	}
        array_titles_czl56.recycle();
    	
    }
    
    
    //===开始=====下面这个两个方法的配合使用，使原本fragment再次回到当前界面，而不执行onResume方法，进而刷新不了数据的问题引刃而解。感谢 http://blog.csdn.net/wangshihui512/article/details/50850679
    @Override
    public void onResume() {
    	super.onResume();
    	if(appAdapter!=null){
    		refreshData();
    		appAdapter  = new AppsAdapterCZL1(getActivity(), arraylist);
    		app_list.setAdapter(appAdapter);
		//appAdapter.notifyDataSetChanged();
    	}
getActivity().sendBroadcast(new Intent("android.intent.action.open.bg_music"));

    }
    
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
    	// TODO Auto-generated method stub
    	super.setUserVisibleHint(isVisibleToUser);
    	if(isVisibleToUser&&isResumed()){
    		onResume();
    	}
    }
    //=====结束=====
    
    /**
     * 给界面切换时刷新数据显示.
     */
    private void refreshData(){
    	arraylist = null;
    	arraylist = new ArrayList<ApkItem>();
    	int array0 = SharedPreferencesUtil.getPassApkCountForCZL56(getActivity());
		//读取没有枷锁的图标。
		for(int j=0;j<array0;j++){
			item = new ApkItem();
			item.setApkImage(menu_toolbar_image_array0[j]);
			item.setApkName(menu_toolbar_image_array00[j]);
			item.setIslocked(false);
			arraylist.add(item);
		}
	    for(int i=array0;i<menu_toolbar_image_array00.length;i++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkbackgroud(menu_toolbar_image_array0[i]);
			item.setApkName(menu_toolbar_image_array00[i]);
			item.setIslocked(true);
			arraylist.add(item);
		}
    }
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.test_activityczlxt1, container, false);
        init();
        return view;
    }
    
    private void init(){
		initPicsIndexForCZL56();
    	app_list = (GridView) view.findViewById(R.id.app_list);
    	
    	refreshData();//刷新数据的方法
    	
    	appAdapter  = new AppsAdapterCZL1(getActivity(), arraylist);
    	app_list.setSelector(new ColorDrawable(Color.TRANSPARENT));//this code makes the background transparent when click any item.
    	app_list.setAdapter(appAdapter);
    	app_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					if(arraylist.get(0).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.magicDream");
					break;
				case 1:
					if(arraylist.get(1).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.toothDanceParty");
					
					break;
				case 2:
					if(arraylist.get(2).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.paitingForGrowth");
					break;
				case 3:
					if(arraylist.get(3).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.DramaCollect");
					                                   
					break;
				case 4:
					if(arraylist.get(4).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.ChildrensGift");
					break;
				case 5:
					if(arraylist.get(5).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.VGAnswerFestivity");
					break;
				case 6:
					if(arraylist.get(6).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.ChristmasCollect");
					break;
				case 7:
					if(arraylist.get(7).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.ChristmasGift");
					break;
				case 8:
					if(arraylist.get(8).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.VGFamilyDrama");
					break;
				case 9:
					if(arraylist.get(9).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.SmallPerson");
					break;
				case 10:
					if(arraylist.get(10).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.SpellAnimals");
					break;


							case 11:
					if(arraylist.get(11).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.museumPainter");
					break;
							case 12:
					if(arraylist.get(12).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.PuzzleSeasons");
					break;
							case 13:
					if(arraylist.get(13).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.BlockOverTurnCar");
					break;
							case 14:
					if(arraylist.get(14).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.WeatherChange");
					break;
							case 15:
					if(arraylist.get(15).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.GetDrivingLicenseAndAssembleCar");
					break;
							case 16:
					if(arraylist.get(16).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.maintenanceWorker");
					break;
							case 17:
					if(arraylist.get(17).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.BlockTrafficCar");
					break;
							case 18:
					if(arraylist.get(18).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.patingForSchool");
					break;
							case 19:
					if(arraylist.get(19).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.BlockReadySchool");
					break;
					case 20:
					if(arraylist.get(20).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.VoiceCompareAndCount");
					break;










				default:
					break;
				}
			}
    		
		});
    }
    
    /*
	 * 根据包名打开对应的应用。
	 * @param packagename
	 */
	private void doStartApplicationWithPackageName(String packagename){  
	    // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等  
	    PackageInfo packageinfo = null;  
	    try {  
	        packageinfo = getActivity().getPackageManager().getPackageInfo(packagename, 0);  
	    } catch (NameNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	    if (packageinfo == null) {  
	        return;  
	    }  
	  
	    // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent  
	    Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);  
	    resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
	    resolveIntent.setPackage(packageinfo.packageName);  
	    
	    // 通过getPackageManager()的queryIntentActivities方法遍历  
	    List<ResolveInfo> resolveinfoList = getActivity().getPackageManager()  
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
	  
	        // 设置ComponentName参数1:packagename参数2:MainActivity路径  
	        ComponentName cn = new ComponentName(packageName, className);  
	        intent.setComponent(cn);  
	        getActivity().startActivity(intent);  
	    }  
	}
    
    
}

