package com.android.myUtils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

	// sharedpreference ���洴����3-4��Ĺ�����
	public static void setPassApkCountForCZL34(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_CZL", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountczl34", count);
		editor.commit();
	}

	// sharedpreference ��ȡ��������3-4��Ĺ�����
	public static int getPassApkCountForCZL34(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences("HHT_PASS_APK_CZL",
				context.MODE_PRIVATE);
		count = sp.getInt("passcountczl34", MyApplication.default_passed_apk_czl34);
		return count;
	}

	// sharedpreference ���洴����4-5��Ĺ�����
	public static void setPassApkCountForCZL45(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_CZL", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountczl45", count);
		editor.commit();
	}

	// sharedpreference ��ȡ��������4-5��Ĺ�����
	public static int getPassApkCountForCZL45(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences("HHT_PASS_APK_CZL",
				context.MODE_PRIVATE);
		count = sp.getInt("passcountczl45", MyApplication.default_passed_apk_czl45);
		return count;
	}

	// sharedpreference ���洴����5-6��Ĺ�����
	public static void setPassApkCountForCZL56(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_CZL", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountczl56", count);
		editor.commit();
	}

	// sharedpreference ��ȡ��������4-5��Ĺ�����
	public static int getPassApkCountForCZL56(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences("HHT_PASS_APK_CZL",
				context.MODE_PRIVATE);
		count = sp.getInt("passcountczl56", MyApplication.default_passed_apk_czl56);
		return count;
	}

	// sharedpreference �����߼�˼ά3-4��ɫ������״�Ĺ�������
	public static void setPassApkCountForLJSW34SCYXZ(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW34", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW34_SCYXZ", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά3-4��ɫ������״�Ĺ�����
	public static int getPassApkCountForLJSW34SCYXZ(Context context) {
		int count = 1;//Ĭ����5��������
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW34", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW34_SCYXZ", MyApplication.default_passed_apk_ljsw34scyxz);
		return count;
	}

	// sharedpreference �����߼�˼ά3-4��������̩ĸ�Ĺ�����.
	public static void setPassApkCountForLJSW34LSYTM(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW34", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW34_LSYTM", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά3-4��������̩ĸ�Ĺ�����
	public static int getPassApkCountForLJSW34LSYTM(Context context) {
		int count = 0;
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW34", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW34_LSYTM", MyApplication.default_passed_apk_ljsw34lsytm);
		return count;
	}

	// sharedpreference �����߼�˼ά3-4�������������ǻ������ǽ��Ĺ�����.
	public static void setPassApkCountForLJSW34HTJ(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW34", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW34_HTJ", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά3-4�����ǻ��������������ǽ��Ĺ�����
	public static int getPassApkCountForLJSW34HTJ(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW34", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW34_HTJ", MyApplication.default_passed_apk_ljsw34_htj);
		return count;
	}

	// sharedpreference �����߼�˼ά3-4�������������ǻ������ǽ��Ĺ�����.
	public static void setPassApkCountForLJSW34SSYBJ(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW34", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW34_SSYBJ", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά3-4�����ǻ��������������ǽ��Ĺ�����
	public static int getPassApkCountForLJSW34SSYBJ(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW34", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW34_SSYBJ", MyApplication.default_passed_apk_ljsw34_ssybj);
		return count;
	}

	// sharedpreference �����߼�˼ά4-5�꽻ͨ��ʶ1�Ĺ�����.
	public static void setPassApkCountForLJSW45JTCS1(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW45", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW45_JTCS1", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά4-5�꽻ͨ��ʶ1�Ĺ�����
	public static int getPassApkCountForLJSW45JTCS1(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW45", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW45_JTCS1", MyApplication.default_passed_apk_ljsw45_jtcs1);
		return count;
	}

	// sharedpreference �����߼�˼ά4-5�����ǵĳ���Ĺ�����.
	public static void setPassApkCountForLJSW45WMDCW(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW45", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW45_WMDCW", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά4-5�����ǵĳ���Ĺ�����
	public static int getPassApkCountForLJSW45WMDCW(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW45", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW45_WMDCW", MyApplication.default_passed_apk_ljsw45_wmdcw);
		return count;
	}

	// sharedpreference �����߼�˼ά4-5��۲�������Ĺ�����.
	public static void setPassApkCountForLJSW45GCYLX(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW45", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW45_GCYLX", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά4-5��۲�������Ĺ�����
	public static int getPassApkCountForLJSW45GCYLX(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW45", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW45_GCYLX", MyApplication.default_passed_apk_ljsw45_gcylx);
		return count;
	}

	// sharedpreference �����߼�˼ά4-5��۲���ע��Ĺ�����.
	public static void setPassApkCountForLJSW45GCYZY(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW45", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW45_GCYZY", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά4-5��۲���ע��Ĺ�����
	public static int getPassApkCountForLJSW45GCYZY(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW45", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW45_GCYZY", MyApplication.default_passed_apk_ljsw45_gcyzy);
		return count;
	}

	// sharedpreference �����߼�˼ά4-5�������ڶ�ͯ��ҵĹ�����.
	public static void setPassApkCountForLJSW45HHTZETS(Context context,
			int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW45", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW45_HHTZETS", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά4-5�������ڶ�ͯ��ҵĹ�����
	public static int getPassApkCountForLJSW45HHTZETS(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW45", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW45_HHTZETS", MyApplication.default_passed_apk_ljsw45_hhtzets);
		return count;
	}

	// sharedpreference �����߼�˼ά4-5�����Ǿ���ô���µĹ�����.
	public static void setPassApkCountForLJSW45WMJZMCY(Context context,
			int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW45", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW45_WMJZMCY", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά4-5���������Ǿ���ô���µĹ�����
//	public static int getPassApkCountForLJSW45WMJZMCY(Context context) {
//		int count = -1;
//		SharedPreferences sp = context.getSharedPreferences(
//				"HHT_PASS_APK_LJSW45", context.MODE_PRIVATE);
//		count = sp.getInt("passcountLJSW45_WMJZMCY", MyApplication.default_passed_apk_ljsw45_wmjzmcy);
//		return count;
//	}

	// sharedpreference �����߼�˼ά5-6���߼���Ϸ�Ĺ�����
	public static void setPassApkCountForLJSW56LJYX(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW56", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW56_WMJZMCY", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά5-6���߼���Ϸ�Ĺ�����
	public static int getPassApkCountForLJSW56LJYX(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW56", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW56_WMJZMCY", MyApplication.default_passed_apk_ljsw56_ljyx);
		return count;
	}

	// sharedpreference �����߼�˼ά5-6���ڶ���԰�Ĺ�����
	public static void setPassApkCountForLJSW56ZDWY(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW56", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW56_ZDWY", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά5-6���ڶ���԰�Ĺ�����
	public static int getPassApkCountForLJSW56ZDWY(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW56", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW56_ZDWY", MyApplication.default_passed_apk_ljsw56_zdwy);
		return count;
	}

	// sharedpreference �����߼�˼ά5-6����ũׯ��Ĺ�����
	public static void setPassApkCountForLJSW56ZNZL(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW56", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW56_ZNZL", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά5-6����ũׯ��Ĺ�����
	public static int getPassApkCountForLJSW56ZNZL(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW56", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW56_ZNZL", MyApplication.default_passed_apk_ljsw56_znzl);
		return count;
	}

	// sharedpreference �����߼�˼ά5-6����ũׯ��Ĺ�����
	//public static void setPassApkCountForLJSW56JTCS2(Context context, int count) {
	//	SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
	//			"HHT_PASS_APK_LJSW56", context.MODE_PRIVATE);
	//	SharedPreferences.Editor editor = HHTSharedpreferences.edit();
	//	editor.putInt("passcountLJSW56_JTCS2", count);
	//	editor.commit();
//	}

	// sharedpreference ��ȡ���߼�˼ά5-6����ũׯ��Ĺ�����
	//public static int getPassApkCountForLJSW56JTCS2(Context context) {
	//	int count = -1;
	//	SharedPreferences sp = context.getSharedPreferences(
	//			"HHT_PASS_APK_LJSW56", context.MODE_PRIVATE);
	//	count = sp.getInt("passcountLJSW56_JTCS2", MyApplication.default_passed_apk_ljsw56_jtcs2);
	//	return count;
//	}

	// sharedpreference �����߼�˼ά5-6�괺���ﶬ�Ĺ�����
	//public static void setPassApkCountForLJSW56CXQD(Context context, int count) {
	//	SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
	//			"HHT_PASS_APK_LJSW56", context.MODE_PRIVATE);
	//	SharedPreferences.Editor editor = HHTSharedpreferences.edit();
	//	editor.putInt("passcountLJSW56_CXQD", count);
	//	editor.commit();
//	}

	// sharedpreference ��ȡ���߼�˼ά5-6�괺���ﶬ�Ĺ�����
//	public static int getPassApkCountForLJSW56CXQD(Context context) {
//		int count = -1;
//		SharedPreferences sp = context.getSharedPreferences(
//				"HHT_PASS_APK_LJSW56", context.MODE_PRIVATE);
//		count = sp.getInt("passcountLJSW56_CXQD", MyApplication.default_passed_apk_ljsw56_cxqd);
	//	return count;
//	}

	// sharedpreference ��ȡ���߼�˼ά5-6���������ֵĹ�����

	public static void setPassApkCountForLJSW56SYSZ(Context context, int count) {
		SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW56", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcountLJSW56_SYSZ", count);
		editor.commit();
	}

	// sharedpreference ��ȡ���߼�˼ά5-6���������ֵĹ�����
	public static int getPassApkCountForLJSW56SYSZ(Context context) {
		int count = -1;
		SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_LJSW56", context.MODE_PRIVATE);
		count = sp.getInt("passcountLJSW56_SYSZ", MyApplication.default_passed_apk_ljsw56_sysz);
		return count;
	}
	
	//sharedpreference �б��滤�۹��������ʱ��ֵ��
	public static void setProtectEyesTime(Context context,int mins){
		SharedPreferences EyesSharedpreferences = context.getSharedPreferences("HHT_PROTECT_TIME", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = EyesSharedpreferences.edit();
		editor.putInt("eyesprotecttime",mins);
		editor.commit();
	}
	
	//ȡ�����õĻ���ʱ��ֵ��
	public static int getProtectEyesTime(Context context){
		int count = 20;
		SharedPreferences sp = context.getSharedPreferences("HHT_PROTECT_TIME", context.MODE_PRIVATE);
		count = sp.getInt("eyesprotecttime", MyApplication.default_eyes_protected_time);
		return  count;

	}
	
	//����ʽ�۲�
    public static void setpassApkCountForYDSGC(Context context,int count){
    	SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_YDSGC", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcount_YDSGC", count);
		editor.commit();
    }
    
    //ȡ������ʽ�۲�ĵ�ֵ
    public static int getpassApkCountForYDSGC(Context context){
    	int count = -1;
    	SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_YDSGC", context.MODE_PRIVATE);
		count = sp.getInt("passcount_YDSGC", MyApplication.default_passed_apk_ydsgc);
		return count;
    }


	 //����ʽ�۲�
    public static void setpassApkCountForZZL(Context context,int count){
    	SharedPreferences HHTSharedpreferences = context.getSharedPreferences(
				"HHT_PASS_APK_ZZL", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = HHTSharedpreferences.edit();
		editor.putInt("passcount_zzl", count);
		editor.commit();
    }
    
    //ȡ������ʽ�۲�ĵ�ֵ
    public static int getpassApkCountForZZL(Context context){
    	int count = -1;
    	SharedPreferences sp = context.getSharedPreferences(
				"HHT_PASS_APK_ZZL", context.MODE_PRIVATE);
		count = sp.getInt("passcount_zzl", MyApplication.default_passed_apk_zzl);
		return count;
    }


 //设置数学与空间的里面的插入逻辑
   //public static void setpassApkCountForSXYKJ(Context context,int count){
    //	SharedPreferences SXYKJSharedpreference = context.getSharedPreferences("HHT_PASS_APK_SXYKJ", context.MODE_PRIVATE);
   // 	SharedPreferences.Editor editor = SXYKJSharedpreference.edit();
    //	editor.putInt("passcount_sxykj", count);
    //	editor.commit();
   // }

 //设置数学与空间 分配与顺序
  /*  public static void setpassApkCountForSXYKJycwxsk(Context context,int count){
    	SharedPreferences SXYKJSharedpreference = context.getSharedPreferences("HHT_PASS_APK_SXYKJ", context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = SXYKJSharedpreference.edit();
    	editor.putInt("passcount_sxykj_ycwxsk", count);
    	editor.commit();
    	
    }
    
	*/
   public static int getpassApkCountForSXYKJfpysx(Context context){
    	int count =-1;
    	SharedPreferences sp = context.getSharedPreferences("HHT_PASS_APK_SXYKJ", context.MODE_PRIVATE);
    	count = sp.getInt("passcount_sxykj_fpysx",MyApplication.default_passed_apk_sxkj_fpysx);
    	return count;
    }


	   public static void setpassApkCountForSXYKJfpysx(Context context,int count){
    	SharedPreferences SXYKJSharedpreference = context.getSharedPreferences("HHT_PASS_APK_SXYKJ", context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = SXYKJSharedpreference.edit();
    	editor.putInt("passcount_sxykj_fpysx", count);
    	editor.commit();
    	
    }
    
	
 /*   public static int getpassApkCountForSXYKJycwxsk(Context context){
    	int count =-1;
    	SharedPreferences sp = context.getSharedPreferences("HHT_PASS_APK_SXYKJ", context.MODE_PRIVATE);
    	count = sp.getInt("passcount_sxykj_ycwxsk",MyApplication.default_passed_apk_sxkj);
    	return count;
    }
	*/

    //数学与空间里面的数数与比较
    public static void setpassApkCountForSXYKJssybj(Context context,int count){
    	SharedPreferences SXKJSharedpreference = context.getSharedPreferences("HHT_PASS_APK_SXYKJ",context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = SXKJSharedpreference.edit();
    	editor.putInt("passcount_sxykj_ssybj", count);
    	editor.commit();
    }
    public static int getpassApkCountForSXYKJssybj(Context context){
    	int count = -1;
    	SharedPreferences sp = context.getSharedPreferences("HHT_PASS_APK_SXYKJ",context.MODE_PRIVATE);
    	count = sp.getInt("passcount_sxykj_ssybj", MyApplication.default_passed_apk_sxkj_ssybj);
    	return count;
    }
    
    //数学与空间里面的加减计算
    public static void setpassApkCountForSXYKJjjjs(Context context,int count){
    	SharedPreferences SXKJSharedpreference = context.getSharedPreferences("HHT_PASS_APK_SXYKJ", context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = SXKJSharedpreference.edit();
    	editor.putInt("passcount_sxykj_jjjs", count);
    	editor.commit();
    }
    public static int getpassApkCountForSXYKJjjjs(Context context){
    	int count = -1;
    	SharedPreferences sp = context.getSharedPreferences("HHT_PASS_APK_SXYKJ", context.MODE_PRIVATE);
    	count = sp.getInt("passcount_sxykj_jjjs", MyApplication.default_passed_apk_sxkj_jjjs);
    	return count;
    }
    
    //数学与空间里面的数与数字
    public static void setpassApkCountForSXYKJsysz(Context context,int count){
    	SharedPreferences SXKJSharedpreference = context.getSharedPreferences("HHT_PASS_APK_SXYKJ", context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = SXKJSharedpreference.edit();
    	editor.putInt("passcount_sxykj_sysz", count);
    	editor.commit();
    }
    public static int getpassApkCountForSXYKJsysz(Context context){
    	int count = -1;
    	SharedPreferences sp = context.getSharedPreferences("HHT_PASS_APK_SXYKJ", context.MODE_PRIVATE);
    	count = sp.getInt("passcount_sxykj_sysz", MyApplication.default_passed_apk_sxkj_jjjs);
    	return count;
    }
    



    
 /*   public static int getpassApkCountForSXYKJ(Context context){
    	int count =-1;
    	SharedPreferences sp = context.getSharedPreferences("HHT_PASS_APK_SXYKJ", context.MODE_PRIVATE);
    	count = sp.getInt("passcount_sxykj",MyApplication.default_passed_apk_sxkj);
    	return count;
    }
	*/

    
    /**
     * 设置app是否是第一次进入。
     * @param context
     */
    public static void setRunningFirstTime(Context context,boolean flag){
    	SharedPreferences HHTRequiringShowGuide = context.getSharedPreferences("TheOriginalStartup", context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = HHTRequiringShowGuide.edit();
    	editor.putBoolean("TheFirstTimeRunning", flag);
    	editor.commit();
    }
    
    public static boolean  getRunningFirstTime(Context context){
    	boolean flag = false;
    	SharedPreferences sp = context.getSharedPreferences("TheOriginalStartup", context.MODE_PRIVATE);
    	flag = sp.getBoolean("TheFirstTimeRunning", true);
    	return flag;
    }

	   //检测授权广播
    public static void setAuthrized(Context context,boolean flag){
    	SharedPreferences HHTRequiringShowGuide = context.getSharedPreferences("TheOriginalStartup", context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = HHTRequiringShowGuide.edit();
    	editor.putBoolean("isAuth", flag);
    	editor.commit();
    }
    
    public static boolean  getAuthrized(Context context){
    	boolean flag = false;
    	SharedPreferences sp = context.getSharedPreferences("TheOriginalStartup", context.MODE_PRIVATE);
    	flag = sp.getBoolean("isAuth", false);
    	return flag;
    }

 /**
     * 设置页面
     *
     * @param pageCount
     */
    public static void setPageCount(Context context,int pageCount) {
    	SharedPreferences sp = context.getSharedPreferences("booyueLauncherPageCount", context.MODE_PRIVATE);
    	SharedPreferences.Editor editor = sp.edit();
    	editor.putInt("pageCount", pageCount);
        editor.commit();
    }
    

    /**
     * 获取页面
     *
     * @return
     */
    public static int getPageCount(Context context) {
        SharedPreferences sp = context.getSharedPreferences("booyueLauncherPageCount", context.MODE_PRIVATE);
      int count =sp.getInt("pageCount", 0);
      return count;
    }

    
    

}
