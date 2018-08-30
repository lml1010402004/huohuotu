package com.android.alilo;

import java.util.ArrayList;
import java.util.List;

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

import com.android.adapter.AppsAdapterCZL1;
import com.android.model.ApkItem;
import com.android.myUtils.MyApplication;
import com.android.myUtils.SharedPreferencesUtil;

public class TestActivityCZLXT2 extends Fragment {
	View view;
	ExpandableListView expandableListView;
//	ListViewAdapterCZL1 treeViewAdapter;
	AppsAdapterCZL1 appAdapter;
	private ArrayList<ApkItem> arraylist;
	
//	private int menu_toolbar_image_array00[]={R.drawable.czl_xgj_title,R.drawable.czl_slldxjl_title,
	//		R.drawable.czl_hhtylc_title,R.drawable.czl_yydhz_title,R.drawable.czl_cbdmx_title,R.drawable.czl_stydh_title,
//			R.drawable.czl_fkpqrz_title,R.drawable.czl_tsgldwh_title,R.drawable.czl_fzzlh_title};
	//private int menu_toolbar_image_array0[] = {R.drawable.czl_xgj_up,
	//		R.drawable.czl_slldxjl_up, R.drawable.czl_hhtylc_up,
	//		R.drawable.czl_yydhz_up,  R.drawable.czl_cbdmx_up,
	//		R.drawable.czl_stydh_up, R.drawable.czl_fkpqrz_up,
	//		R.drawable.czl_tsgldwh_up, 
	//		R.drawable.czl_fzzlh_up};
	
	private GridView app_list;
     private int[] menu_toolbar_image_array0 ;
	private int[] menu_toolbar_image_array00;
	
	//public String[] groups = { ""};
	//public String[][] child = { { "" }};
 	private ApkItem item = null;
	
    public TestActivityCZLXT2() {
        // Required empty public constructor
    }

	  int array_length = 0;
    private void initPicsIndexForCZL45(){
    	TypedArray array_icons_czl45= getResources().obtainTypedArray(R.array.czl45_pic_index_icon);
    	array_length = array_icons_czl45.length();
    	menu_toolbar_image_array0 = new int[array_length];
    	for(int i=0;i<array_length;i++){
    		menu_toolbar_image_array0[i] = array_icons_czl45.getResourceId(i, R.drawable.apk_locked);
    	}
    	array_icons_czl45.recycle();
    	TypedArray array_titles_czl45 = getResources().obtainTypedArray(R.array.czl45_pic_index_title);
    	menu_toolbar_image_array00 = new int[array_length];
    	for(int j=0;j<array_length;j++){
    		menu_toolbar_image_array00[j] = array_titles_czl45.getResourceId(j, R.drawable.game2_11_title);
    	}
        array_titles_czl45.recycle();
    	
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	if(appAdapter!=null){
    		refreshData();
    		appAdapter  = new AppsAdapterCZL1(getActivity(), arraylist);
    		app_list.setAdapter(appAdapter);
	//	appAdapter.notifyDataSetChanged();
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
    
    private void refreshData(){
    	arraylist = null;
    	arraylist = new ArrayList<ApkItem>();
    	int array0 = SharedPreferencesUtil.getPassApkCountForCZL45(getActivity());
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
		initPicsIndexForCZL45();
    	app_list = (GridView) view.findViewById(R.id.app_list);
    	
    	refreshData();
    	
    	/*arraylist = new ArrayList<ApkItem>();
    	
    	int array0 = SharedPreferencesUtil.getPassApkCountForCZL45(getActivity());
   
    	
    	for(int i=0;i<array0;i++){
    		item = new ApkItem();
    		item.setApkImage(menu_toolbar_image_array0[i]);
    		item.setApkName(menu_toolbar_image_array00[i]);
    		item.setIslocked(false);
    		arraylist.add(item);
    	}
    	
		for(int ii=array0;ii<menu_toolbar_image_array00.length;ii++){
			item = new ApkItem();
			item.setApkImage(MyApplication.apk_locked);
			item.setApkName(menu_toolbar_image_array00[ii]);
			item.setIslocked(true);
			arraylist.add(item);
		}
		*/
    	
    	appAdapter  = new AppsAdapterCZL1(getActivity(), arraylist);
    	app_list.setSelector(new ColorDrawable(Color.TRANSPARENT));
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
					doStartApplicationWithPackageName("org.cocos2d.treeInMoon");
					break;
				case 1:
					if(arraylist.get(1).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.forestElf");
					break;
				case 2:
					if(arraylist.get(2).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.VoiceForest");
					break;
				case 3:
					if(arraylist.get(3).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.TheKursaal");
					break;
				case 4:
					if(arraylist.get(4).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.playgroundRepairman");
					break;
				case 5:
					if(arraylist.get(5).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.VoiceAmusemetPark");
					break;
				case 6:
					if(arraylist.get(6).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.TheConcert");
					break;
				case 7:
					if(arraylist.get(7).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.patingForMusical");
					break;
				case 8:
					if(arraylist.get(8).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.VGMusicalInstrument");
					break;



	              case 9:
					if(arraylist.get(9).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.PuzzleCastle");
					break;
						case 10:
					if(arraylist.get(10).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.BlockAdventure");
					break;
						case 11:
					if(arraylist.get(11).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.paintTheCastle");
					break;
						case 12:
					if(arraylist.get(12).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.beachFashionShow");
					break;
						case 13:
					if(arraylist.get(13).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.beachSports");
					break;
						case 14:
					if(arraylist.get(14).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.BlockPerceive");
					break;
						case 15:
					if(arraylist.get(15).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.LibraryDance");
					break;
						case 16:
					if(arraylist.get(16).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.Librarian");
					break;
						case 17:
					if(arraylist.get(17).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.libraryDesigner");
					break;
						case 18:
					if(arraylist.get(18).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.exhibitionPainting");
					break;
						case 19:
					if(arraylist.get(19).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.PuzzleChildRestaruant");
					break;
					case 20:
					if(arraylist.get(20).isIslocked()){
						Toast.makeText(getActivity(), getString(R.string.please_pass_the_former_apk), Toast.LENGTH_SHORT).show();
						return ;
					}
					getActivity().stopService(new Intent(getActivity(),AudioService.class));
					doStartApplicationWithPackageName("org.cocos2d.BlockClothing");
					break;
					



				default:
					break;
				}
			}
		});
    }
    
    /*
	 * ���ݰ����򿪶�Ӧ��Ӧ�á�
	 * @param packagename
	 */
	private void doStartApplicationWithPackageName(String packagename) {  
	    // ͨ��������ȡ��APP��ϸ��Ϣ������Activities��services��versioncode��name�ȵ�  
	    PackageInfo packageinfo = null;  
	    try {  
	        packageinfo = getActivity().getPackageManager().getPackageInfo(packagename, 0);  
	    } catch (NameNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	    if (packageinfo == null) {  
	        return;  
	    }  
	  
	    Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);  
	    resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);  
	    resolveIntent.setPackage(packageinfo.packageName);  
	    
	    List<ResolveInfo> resolveinfoList = getActivity().getPackageManager()  
	            .queryIntentActivities(resolveIntent, 0);  
	  
	    ResolveInfo resolveinfo = resolveinfoList.iterator().next();  
	    if (resolveinfo != null) {  
	        // packagename = ����packname  
	        String packageName = resolveinfo.activityInfo.packageName;  
	        // �����������Ҫ�ҵĸ�APP��LAUNCHER��Activity[��֯��ʽ��packagename.mainActivityname]  
	        String className = resolveinfo.activityInfo.name;  
	        // LAUNCHER Intent  
	        Intent intent = new Intent(Intent.ACTION_MAIN);  
	        intent.addCategory(Intent.CATEGORY_LAUNCHER);  
	  
	        // ����ComponentName����1:packagename����2:MainActivity·��  
	        ComponentName cn = new ComponentName(packageName, className);  
	        intent.setComponent(cn);  
	        getActivity().startActivity(intent);  
	    }  
	} 
    
}

