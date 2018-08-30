package com.android.broadcast;

import com.android.myUtils.MyApplication;
import com.android.myUtils.SharedPreferencesUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBrodcastReceiver extends BroadcastReceiver {

	public final String RECEIVER_BROADCAST = "com.booyue.l1.game.pass";

	// ����Ҫ���������߼�˼ά��ɫ������״��
	private final String[] package_name_ljsw34_scyxz = { "air.HHTYigui",
			"air.HHTQiqiu", "air.HHTuan", "air.HHTDream",
			"air.HHTXunzhaoMaxituan", "air.HHTLearnDraw" };

	// �߼�˼ά34�����õ�һ��
	private final String[] package_name_ljsw34_lsytm = { "air.HHTZaocan",
			"air.HHTZaochen", "air.HHTPlayGame", "air.HHTLikeDraw",
			"air.HHTWonderfulDream" };

	// �߼�˼ά34�����ǻ��������������ǽ�
	private final String[] package_name_ljsw34_tie_hua_jian = { "air.HHTToys",
			"air.HHTKaPian", "air.HHTBuildingBlocks", "air.HHTShouGong",
			"air.HHTFeijiupinliyong" };

	// �߼�˼ά34��������Ƚ�
	private final String[] package_name_ljsw34_ssybj = { "air.HHTKantuShushu",
			"air.HHTZhaoBuTong", "air.HHTCounting", "air.HHTTortoise",
			"air.HHTCard", "air.HHTWanYiWan" };

	// �߼�˼ά45�꽻ͨ��ʶ1
	private final String[] package_name_ljsw45_jtcs1 = { "air.HHTTraffic",
			"air.HHTTrafficKnow", "air.HHTQicheAnquan", "air.HHTTuBiao",
			"air.HHTTrafficColor" };

	// �߼�˼ά45�����ǵĳ���
	private final String[] package_name_ljsw45_wmdcw = { "air.HHTPetShouce",
			"air.HHTWodongPet", "air.RabbitDrawLine3", "air.HHTRenXue",
			"air.HHTQimiaodexdw" };

	// �߼�˼ά45��۲�������
	private final String[] package_name_ljsw45_gcylx = { "air.HHTWodangjia",
			"air.HHTMangludezm", "air.HHTKanyikanzyz", "air.HHTWoaitianse",
			"air.RabbitLineUp"};

	// �߼�˼ά45��۲���ע��
	private final String[] package_name_ljsw45_gcyzy = {
			"air.HHTYoulechangdyt", "air.HHTYouquylc", "air.HHTGuanchatianse",
			"air.HHTKanlxTianys", "air.HHTShengrijuhui" };

	// �߼�˼ά45�������ڶ�ͯ���
	private final String[] package_name_ljsw45_hhtzets = {
			"air.HHTWanjuzhaozhao", "air.HHTDuoyangdetuxing","air.HHTTongxinglts","air.HHTJimuwangguo",
			"air.HHTWolaixuedapei"};



   //�߼�˼ά56�꣬�߼���Ϸ
   private final String[] package_name_ljsw56_ljyx={"air.HHTGuanchaxiaonengshou","air.HHTDajialaipipei",
			"air.HHTKanyikanpyp","air.HHTJingzilideys","air.RabbitLineUp2"};

   //�߼�˼ά56�꣬�ι۶���԰
private final String[] package_name_ljsw56_zdwy={"air.HHTGuangdongwuyuan","air.HHTYouqudedongwu","air.HHTShiwufenpei"
			,"air.RabbitFunnySeq","air.HHTBianRenDongWu"};
			
//�߼�˼άϵͳ56�꣬ũׯ���֪ʶ
	private final String[] package_name_ljsw56_znzl={"air.HHTNongChangYiTian","air.HHTZhaoYiZhao",
			"air.HHTYouqudeNongchang","air.HHTZiXiQiaoYiQiao","air.RabbitLineUp3"};

	//�߼�˼άϵͳ56�꣬��������
	private final String[] package_name_ljsw56_sysz={"air.HHTShuShuYuDuiYing","air.HHTWoLaiRenShuZi",
			"air.HHTWoAiXieShuZi","air.HHTShuZiXiaoShiJie","air.HHTShuZiDeZuHe"};





	// �߼�˼ά45���Ҿ���ô����
	private final String[] package_name_ljsw45_wmjzmcy = { "air.HHTYigui" };

	// �߼�˼ά56���߼���Ϸ
//	private final String[] package_name_ljsw56_ljyx = { "air.HHTGuanchaxiaonengshou","air.HHTDajialaipipei" ,"air.HHTKanyikanpyp","air.HHTJingzilideys","air.RabbitLineUp2"};

	// �߼�˼ά56���ڶ���԰
//	private final String[] package_name_ljsw56_zdwy = { "air.HHTGuangdongwuyuan","air.HHTYouqudedongwu","air.HHTShiwufenpei","air.RabbitFunnySeq","air.HHTBianRenDongWu" };

	// �߼�˼ά56����ũׯ��
//	private final String[] package_name_ljsw56_znzl = { "air.HHTNongChangYiTian" ,"air.HHTZhaoYiZhao","air.HHTYouqudeNongchang","air.HHTZiXiQiaoYiQiao","air.RabbitLineUp3"};

	// �߼�˼ά56�꽻ͨ��ʶ2
//	private final String[] package_name_ljsw56_jtcs2 = { "air.HHTYigui" };

	// �߼�˼ά56�괺���ﶬ
//	private final String[] package_name_ljsw56_cxqd = { "air.HHTYigui" };

	// �߼�˼ά56����������
//	private final String[] package_name_ljsw56_sysz = { "air.HHTShuShuYuDuiYing","air.HHTWoLaiRenShuZi","air.HHTWoAiXieShuZi","air.HHTShuZiXiaoShiJie" ,"air.HHTShuZiDeZuHe"};

	// ������ģ��ϵͳ34��
	private final String[] package_name_czl34 ={
			"org.cocos2d.FashionAnimal", "org.cocos2d.LoveAnimal",
			"org.cocos2d.VGAnimalVoice", "org.cocos2d.ThemeParkDesigner",
			"org.cocos2d.SmallConstructor", "org.cocos2d.littlePainterPark",
			"org.cocos2d.IntristingDrawing", "org.cocos2d.SeaCollect",
			"org.cocos2d.seaWorld", "org.cocos2d.FriendMatchs",
			"org.cocos2d.SmallPersonClearUp", "org.cocos2d.BlockKindergarten",
			"org.cocos2d.toyHouse","org.cocos2d.toyDesigner","org.cocos2d.VGDollhouse",
			"org.cocos2d.SuperMarketCrazy","org.cocos2d.VGSalesPerson"
			,"org.cocos2d.BlockBaker","org.cocos2d.SmallCooking","org.cocos2d.littleArtizan"};

	// ������ģ��ϵͳ45��
	private final String[] package_name_czl45 = { "org.cocos2d.treeInMoon","org.cocos2d.forestElf",
			"org.cocos2d.VoiceForest","org.cocos2d.TheKursaal","org.cocos2d.playgroundRepairman",
			"org.cocos2d.VoiceAmusemetPark","org.cocos2d.TheConcert","org.cocos2d.patingForMusical",
			"org.cocos2d.VGMusicalInstrument","org.cocos2d.PuzzleCastle","org.cocos2d.BlockAdventure",
			"org.cocos2d.paintTheCastle","org.cocos2d.beachFashionShow","org.cocos2d.beachSports",
			"org.cocos2d.BlockPerceive","org.cocos2d.LibraryDance","org.cocos2d.Librarian",
			"org.cocos2d.libraryDesigner","org.cocos2d.exhibitionPainting","org.cocos2d.PuzzleChildRestaruant",
			"org.cocos2d.BlockClothing"};

	// ������ģ��ϵͳ56��
	private final String[] package_name_czl56 = {"org.cocos2d.magicDream","org.cocos2d.toothDanceParty",
			"org.cocos2d.paitingForGrowth","org.cocos2d.DramaCollect","org.cocos2d.ChildrensGift","org.cocos2d.VGAnswerFestivity",
			"org.cocos2d.ChristmasCollect","org.cocos2d.ChristmasGift","org.cocos2d.VGFamilyDrama","org.cocos2d.SmallPerson",
			"org.cocos2d.SpellAnimals","org.cocos2d.museumPainter","org.cocos2d.PuzzleSeasons","org.cocos2d.BlockOverTurnCar",
			"org.cocos2d.WeatherChange","org.cocos2d.GetDrivingLicenseAndAssembleCar","org.cocos2d.maintenanceWorker","org.cocos2d.BlockTrafficCar",
			"org.cocos2d.patingForSchool","org.cocos2d.BlockReadySchool","org.cocos2d.VoiceCompareAndCount"};

	// ����ʽ�۲�
	private final String[] package_name_yds = { "air.HHTShijianGuanchaFa",
			"air.HHTCongZhengtiDaoJubu", "air.HTDongTaiGuanChaFa",
			"air.HHTWaidaoli", "air.HHTDuiBiGuangChaFa",
			"air.HHTCongZuoDaoYou", "air.HHTCongZuoDaoYou" };

//רע��ѵ��
		private final String[] package_name_zzl = {"air.RabbitDrawLine","air.RabbitFindDiff5","air.RabbitFindDiff",
			"air.RabbitFindDiff2","air.RabbitMatch","air.RabbitFindDiff4","air.RabbitDrawLine2","air.RabbitFindDiff3",
			"air.RabbitFindDiff6","air.HHTNiuniuhua","air.RabbitSurrond"};


		//��ѧ�ռ�����ķ������ ������Ƚ�                                                                                                                                                                       
	private final String[] package_name_sxsw_ssybj = {"air.HHTKantuShushu","air.HHTZhaoBuTong","air.HHTCounting","air.HHTTortoise","air.HHTCard","air.HHTWanYiWan"};

	//��ѧ�ռ� ˳�������
	private final String[] package_name_sxsw_fpysx = {"air.HHTPetShouce","air.HHTRenXue","air.HHTWolaixuedapei","air.HHTShiwufenpei","air.RabbitFunnySeq",
			"air.HHTZhaoYiZhao"};

	//��ѧ�ռ� ��������
	private final String[] package_name_sxsw_sysz = {"air.HHTShuShuYuDuiYing","air.HHTWoLaiRenShuZi","air.HHTWoAiXieShuZi",
			"air.HHTShuZiXiaoShiJie","air.HHTShuZiDeZuHe"};

	//��ѧ�ռ� �Ӽ�����
	private final String[] package_name_sxsw_jjjs={"air.HHTJiaFaYunSuanShi","air.HHTXueJiaFa","air.HHTWoHuiYongJiaFa","air.HHTJianFaYunSuanShi","air.HHTShuZiDeBiJiao"};

	private boolean CheckStringGroupHasTargetString(String[] groupString,
			String str) {
		boolean flag = false;
		for (int i = 0; i < groupString.length; i++) {
			if (groupString[i].equals(str)) {
				return true;
			}
		}
		return flag;
	}

	@Override
	public void onReceive(Context arg0, Intent arg1) {
	//	Log.v("jack", "---------------------------���յ��ı����apk�����Ĺ㲥");
Log.v("jack", "------------------------------------------------------------------------------------------" );
	Log.v("jack", "------------------------------------------------------------------------------------------" );
	Log.v("jack", "------------------------------------------------------------------------------------------" );

		if (arg1.getAction().equals(RECEIVER_BROADCAST)) {
			Log.v("jack", "----------------------------------------444444444--------------------------------------------------" );
	Log.v("jack", "----------------------------------------------44444444444--------------------------------------------" );
			String packagenameofpassedapk = arg1.getStringExtra("package");
			insertThePassedApk(arg0, packagenameofpassedapk);
		}
	}

	int oldCount;
	int newCount;

	private void insertThePassedApk(Context context, String packageName) {

	Log.v("jack", "------------------------------------------------------------------------------------------" + packageName);
	Log.v("jack", "------------------------------------------------------------------------------------------" + packageName);
	Log.v("jack", "------------------------------------------------------------------------------------------" + packageName);


		// ====================34��ɫ������״
		if (CheckStringGroupHasTargetString(package_name_ljsw34_scyxz,
				packageName)&&MyApplication.FLAGOFSXKJ ==false) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW34SCYXZ(context);
		//	Log.v("jack", "----------oldCount" + oldCount);
				//	Log.v("jack", "�յ��ҹ㲥"+packageName);
					if(oldCount==0){
			  oldCount=1;
			}
			if (packageName.equals(package_name_ljsw34_scyxz[oldCount-1])) {
				newCount = ++oldCount;
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW34SCYXZ(context,
						newCount);
			} 
			
			if (newCount > MyApplication.default_total_apk_ljsw34scyxz) {
				newCount = MyApplication.default_total_apk_ljsw34scyxz;
			//	Log.v("jack", "----------newCOunt" + newCount);
				SharedPreferencesUtil.setPassApkCountForLJSW34SCYXZ(context,
						newCount);
			}

			if (packageName
					.equals(package_name_ljsw34_scyxz[package_name_ljsw34_scyxz.length - 1])) {
				SharedPreferencesUtil.setPassApkCountForLJSW34LSYTM(context, 1);
			}
			return;

		}
		// ===================34��������̩ķ
		if (CheckStringGroupHasTargetString(package_name_ljsw34_lsytm,
				packageName)&&MyApplication.FLAGOFSXKJ ==false) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW34LSYTM(context);
				if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw34_lsytm[oldCount-1])){
				newCount = ++oldCount;
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW34LSYTM(context,
						newCount);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			if (newCount > MyApplication.default_total_apk_ljsw34lsytm) {
				newCount = MyApplication.default_total_apk_ljsw34lsytm;
			//	Log.v("jack", "----------newCOunt" + newCount);
				SharedPreferencesUtil.setPassApkCountForLJSW34LSYTM(context,
						newCount);
			}
			if (packageName
					.equals(package_name_ljsw34_lsytm[package_name_ljsw34_lsytm.length - 1])) {
				SharedPreferencesUtil.setPassApkCountForLJSW34HTJ(context, 1);
			}
			return ;
		}

		// ====================34�����ǻ��������������ǽ�
		if (CheckStringGroupHasTargetString(package_name_ljsw34_tie_hua_jian,
	packageName)&&MyApplication.FLAGOFSXKJ ==false) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW34HTJ(context);
			if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw34_tie_hua_jian[oldCount-1])){
				newCount = ++oldCount;
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil
				.setPassApkCountForLJSW34HTJ(context, newCount);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			if (newCount > MyApplication.default_total_apk_ljsw34_htj) {
				newCount = MyApplication.default_total_apk_ljsw34_htj;
			//	Log.v("jack", "----------newCOunt" + newCount);
				SharedPreferencesUtil
				.setPassApkCountForLJSW34HTJ(context, newCount);
			}
		
			if (packageName
					.equals(package_name_ljsw34_tie_hua_jian[package_name_ljsw34_tie_hua_jian.length - 1])) {
				SharedPreferencesUtil.setPassApkCountForLJSW34SSYBJ(context, 1);
			}
			return ;
		}

		// =====================34��������Ƚ�
		if (CheckStringGroupHasTargetString(package_name_ljsw34_ssybj,
				packageName)&&MyApplication.FLAGOFSXKJ ==false) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW34SSYBJ(context);
				if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw34_ssybj[oldCount-1])){
				newCount = ++oldCount;
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW34SSYBJ(context,
						newCount);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			
			if (newCount > MyApplication.default_total_apk_ljsw34_ssybj) {
				newCount = MyApplication.default_total_apk_ljsw34_ssybj;
				//Log.v("jack", "----------newCOunt" + newCount);
				SharedPreferencesUtil.setPassApkCountForLJSW34SSYBJ(context,
						newCount);
			}
//			if (packageName
//					.equals(package_name_ljsw34_ssybj[package_name_ljsw34_ssybj.length - 1])) {
//				SharedPreferencesUtil.setPassApkCountForLJSW34LSYTM(context, 1);
//			}//�������η����������һ�����ݲ���Ҫȥˢ����һ������������ˣ���Ϊû����һ�������ˡ�
			return ;
			
		}

		// =====================45�꽻ͨ��ʶ1
		if (CheckStringGroupHasTargetString(package_name_ljsw45_jtcs1,
				packageName)&&MyApplication.FLAGOFSXKJ ==false) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW45JTCS1(context);
			
				if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw45_jtcs1[oldCount-1])){
				newCount = ++oldCount;
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW45JTCS1(context,
						newCount);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			if (newCount > MyApplication.default_total_apk_ljsw45_jtcs1) {
				newCount = MyApplication.default_total_apk_ljsw45_jtcs1;
			//	Log.v("jack", "----------newCOunt" + newCount);
				SharedPreferencesUtil.setPassApkCountForLJSW45JTCS1(context,
						newCount);
			}
	
			if (packageName
					.equals(package_name_ljsw45_jtcs1[package_name_ljsw45_jtcs1.length - 1])) {
				SharedPreferencesUtil.setPassApkCountForLJSW45WMDCW(context, 1);
			}
			return ;
			
		}

		// =====================45��2���ǵĳ���
		if (CheckStringGroupHasTargetString(package_name_ljsw45_wmdcw,
				packageName)&&MyApplication.FLAGOFSXKJ==false) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW45WMDCW(context);
				if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw45_wmdcw[oldCount-1])){
				newCount = ++oldCount;
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW45WMDCW(context,
						newCount);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			if (newCount > MyApplication.default_total_apk_ljsw45_wmdcw) {
				newCount = MyApplication.default_total_apk_ljsw45_wmdcw;
			//	Log.v("jack", "----------newCOunt" + newCount);
				SharedPreferencesUtil.setPassApkCountForLJSW45WMDCW(context,
						newCount);
			}
		
			if (packageName
					.equals(package_name_ljsw45_wmdcw[package_name_ljsw45_wmdcw.length - 1])) {
				SharedPreferencesUtil.setPassApkCountForLJSW45GCYLX(context, 1);
			}
			return ;
		}

		// =====================45��۲�������
		if (CheckStringGroupHasTargetString(package_name_ljsw45_gcylx,
				packageName)&&MyApplication.FLAGOFSXKJ ==false) {
	
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW45GCYLX(context);
			
				if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw45_gcylx[oldCount-1])){
				newCount = ++oldCount;
		//		Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW45GCYLX(context,
						newCount);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			if (newCount > MyApplication.default_total_apk_ljsw45_gcylx) {
				newCount = MyApplication.default_total_apk_ljsw45_gcylx;
			//	Log.v("jack", "----------newCOunt" + newCount);
				SharedPreferencesUtil.setPassApkCountForLJSW45GCYLX(context,
						newCount);
			}
		
			if (packageName
					.equals(package_name_ljsw45_gcylx[package_name_ljsw45_gcylx.length - 1])) {
				SharedPreferencesUtil.setPassApkCountForLJSW45GCYZY(context, 1);
						Log.v("jack","observor and think....packageName="+packageName);
			Log.v("jack","observor and think....packageName="+packageName);
			Log.v("jack","observor and think....packageName="+packageName);
			}
			return ;
		}

		// =====================45��۲���ע��
		if (CheckStringGroupHasTargetString(package_name_ljsw45_gcyzy,
				packageName)&&MyApplication.FLAGOFSXKJ ==false) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW45GCYZY(context);
			
				if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw45_gcyzy[oldCount-1])){
				newCount = ++oldCount;
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW45GCYZY(context,
						newCount);
			}else{
		//		Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
	
			if (newCount > MyApplication.default_total_apk_ljsw45_gcyzy) {
				newCount = MyApplication.default_total_apk_ljsw45_gcyzy;
		//		Log.v("jack", "----------newCOunt" + newCount);
				SharedPreferencesUtil.setPassApkCountForLJSW45GCYZY(context,
						newCount);
			}
		
			
			if (packageName
					.equals(package_name_ljsw45_gcyzy[package_name_ljsw45_gcyzy.length - 1])) {
				SharedPreferencesUtil.setPassApkCountForLJSW45HHTZETS(context, 1);
			}
			return ;
		}

		// ======================45�������ڶ�ͯ��
		if (CheckStringGroupHasTargetString(package_name_ljsw45_hhtzets,
				packageName)&&MyApplication.FLAGOFSXKJ == false) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW45HHTZETS(context);
			
	     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw45_hhtzets[oldCount-1])){
				newCount = ++oldCount;
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW45HHTZETS(context,
						newCount);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			if (newCount > MyApplication.default_total_apk_ljsw45_hhtzets) {
				newCount = MyApplication.default_total_apk_ljsw45_hhtzets;
		//		Log.v("jack", "----------newCOunt" + newCount);
				SharedPreferencesUtil.setPassApkCountForLJSW45HHTZETS(context,
						newCount);
			}
		
		//	if (packageName
		//			.equals(package_name_ljsw45_hhtzets[package_name_ljsw45_hhtzets.length - 1])) {
		//		SharedPreferencesUtil.setPassApkCountForLJSW45WMJZMCY(context, 1);
		//}
			return ;
		
			
		}

		// =====================45�����Ǿ���ô����
		//if (CheckStringGroupHasTargetString(package_name_ljsw45_wmjzmcy,
		//		packageName)&&MyApplication.FLAGOFSXKJ ==false) {
		//	oldCount = SharedPreferencesUtil
		//			.getPassApkCountForLJSW45WMJZMCY(context);
			
		//	     if(oldCount==0){
		//	  oldCount=1;
		//	}
		//	if(packageName.equals(package_name_ljsw45_wmjzmcy[oldCount-1])){
		//		newCount = ++oldCount;
		//		Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
		//		SharedPreferencesUtil.setPassApkCountForLJSW45WMJZMCY(context,
			//			newCount);
		//	}else{
			////	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
		//		return ;
			///}
			
			//if (newCount > MyApplication.default_total_apk_ljsw45_hhtzets) {
		//		newCount = MyApplication.default_total_apk_ljsw45_hhtzets;
		//		//Log.v("jack", "----------newCOunt" + newCount);
		//		SharedPreferencesUtil.setPassApkCountForLJSW45WMJZMCY(context,
		//				newCount);
		//	}
		
	
//			if (packageName
//					.equals(package_name_ljsw45_wmjzmcy[package_name_ljsw45_wmjzmcy.length - 1])) {
//				SharedPreferencesUtil.setPassApkCountForLJSW45WMJZMCY(context, 1);
//			}
//			return ;//45�����һ��������غ��ǲ���Ҫ����һ������������ݸ���UI������
			
	//	}

		// =====================56��1�߼���Ϸ
		if (CheckStringGroupHasTargetString(package_name_ljsw56_ljyx,
				packageName)&&MyApplication.FLAGOFSXKJ ==false) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW56LJYX(context);
			     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw56_ljyx[oldCount-1])){
				newCount = ++oldCount;
				//Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW56LJYX(context,
						newCount);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			if (newCount > MyApplication.default_total_apk_ljsw56_ljyx) {
				newCount = MyApplication.default_total_apk_ljsw56_ljyx;
				SharedPreferencesUtil.setPassApkCountForLJSW56LJYX(context,
						newCount);
			}
	
			
			if (packageName
					.equals(package_name_ljsw56_ljyx[package_name_ljsw56_ljyx.length - 1])) {
				SharedPreferencesUtil.setPassApkCountForLJSW56ZDWY(context, 1);
			}
		
		}

		// ======================56��2�ڶ���԰
		if (CheckStringGroupHasTargetString(package_name_ljsw56_zdwy,
				packageName)	&&MyApplication.FLAGOFSXKJ == false) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW56ZDWY(context);
			
			     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw56_zdwy[oldCount-1])){
				newCount = ++oldCount;
				//Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW56ZDWY(context,
						newCount);
			}else{
				//Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			if (newCount > MyApplication.default_total_apk_ljsw56_zdwy) {
				newCount = MyApplication.default_total_apk_ljsw56_zdwy;
			//	Log.v("jack", "----------newCOunt" + newCount);
				SharedPreferencesUtil.setPassApkCountForLJSW56ZDWY(context,
						newCount);
			}
			
			if (packageName
					.equals(package_name_ljsw56_zdwy[package_name_ljsw56_zdwy.length - 1])) {
				SharedPreferencesUtil.setPassApkCountForLJSW56ZNZL(context, 1);
			}
			return ;
			
		}

		// ======================56��3��ũׯ��
		if (CheckStringGroupHasTargetString(package_name_ljsw56_znzl,
				packageName)&&MyApplication.FLAGOFSXKJ == false) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW56ZNZL(context);
			
			     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw56_znzl[oldCount-1])){
				newCount = ++oldCount;
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW56ZNZL(context,
						newCount);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			if (newCount > MyApplication.default_total_apk_ljsw56_znzl) {
				newCount = MyApplication.default_total_apk_ljsw56_znzl;
				SharedPreferencesUtil.setPassApkCountForLJSW56ZNZL(context,
						newCount);
			}
		
			if (packageName
					.equals(package_name_ljsw56_znzl[package_name_ljsw56_znzl.length - 1])) {
				SharedPreferencesUtil.setPassApkCountForLJSW56SYSZ(context, 1);
			}
			return ;
			
			
		}

		// ======================56��4��ͨ��ʶ2
/*		if (CheckStringGroupHasTargetString(package_name_ljsw56_jtcs2,
				packageName)) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW56JTCS2(context);
			
			     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw56_jtcs2[oldCount-1])){
				newCount = ++oldCount;
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW56JTCS2(context,
						newCount);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
		
			if (newCount > MyApplication.default_total_apk_ljsw56_jtcs2) {
				newCount = MyApplication.default_total_apk_ljsw56_jtcs2;
				SharedPreferencesUtil.setPassApkCountForLJSW56JTCS2(context,
						newCount);
			}
		
			if (packageName
					.equals(package_name_ljsw56_jtcs2[package_name_ljsw56_jtcs2.length - 1])) {
				SharedPreferencesUtil.setPassApkCountForLJSW56CXQD(context, 1);
			}
			return ;
		} */

		// ======================56��5�����ﶬ
	/*	if (CheckStringGroupHasTargetString(package_name_ljsw56_cxqd,
				packageName)) {
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW56CXQD(context);
			
			     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw56_cxqd[oldCount-1])){
				newCount = ++oldCount;
				//Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW56CXQD(context,
						newCount);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			if (newCount > MyApplication.default_total_apk_ljsw56_cxqd) {
				newCount = MyApplication.default_total_apk_ljsw56_cxqd;
				SharedPreferencesUtil.setPassApkCountForLJSW56CXQD(context,
						newCount);
			}
			
			if (packageName
					.equals(package_name_ljsw56_cxqd[package_name_ljsw56_cxqd.length - 1])) {
				SharedPreferencesUtil.setPassApkCountForLJSW56SYSZ(context, 1);
			}
			return ;
		}
		*/

		// ======================56��6��������
		if (CheckStringGroupHasTargetString(package_name_ljsw56_sysz,
				packageName)&&MyApplication.FLAGOFSXKJ==false) {
			Log.v("jack", "sysz=packagename"+packageName);
			Log.v("jack", "sysz=packagename"+packageName);
			Log.v("jack", "sysz=packagename"+packageName);
			oldCount = SharedPreferencesUtil
					.getPassApkCountForLJSW56SYSZ(context);
		
		     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_ljsw56_sysz[oldCount-1])){
				newCount = ++oldCount;
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				SharedPreferencesUtil.setPassApkCountForLJSW56SYSZ(context,
						newCount);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			
			if (newCount > MyApplication.default_total_apk_ljsw56_sysz) {
				newCount = MyApplication.default_total_apk_ljsw56_sysz;
				SharedPreferencesUtil.setPassApkCountForLJSW56SYSZ(context,
						newCount);
			}
		
		}

	

		// ========================������34��
		if (CheckStringGroupHasTargetString(package_name_czl34, packageName)) {
			oldCount = SharedPreferencesUtil.getPassApkCountForCZL34(context);
				Log.v("jack", "�յ��ҹ㲥"+packageName);
			     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_czl34[oldCount-1])){
				newCount = ++oldCount;
				SharedPreferencesUtil.setPassApkCountForCZL34(context, newCount);
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				Log.v("jack", "�յ��ҹ㲥"+packageName);
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			
			if (newCount > MyApplication.default_total_apk_czl34) {
				newCount = MyApplication.default_total_apk_czl34;
				SharedPreferencesUtil.setPassApkCountForCZL34(context, newCount);
			}
			
		}
		// ========================������45��
		if (CheckStringGroupHasTargetString(package_name_czl45, packageName)) {
			oldCount = SharedPreferencesUtil.getPassApkCountForCZL45(context);
			Log.v("jack","the packagename="+packageName);
			Log.v("jack","the packagename="+packageName);
			Log.v("jack","the packagename="+packageName);
			
			     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_czl45[oldCount-1])){
				newCount = ++oldCount;
				SharedPreferencesUtil.setPassApkCountForCZL45(context, newCount);
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			if (newCount > MyApplication.default_total_apk_czl45) {
				newCount = MyApplication.default_total_apk_czl45;
				SharedPreferencesUtil.setPassApkCountForCZL45(context, newCount);
			}
			
		}
		// ========================������56��
		if (CheckStringGroupHasTargetString(package_name_czl56, packageName)) {
			oldCount = SharedPreferencesUtil.getPassApkCountForCZL56(context);
					Log.v("jack","the packagename56="+packageName);
			Log.v("jack","the packagename=56"+packageName);
			Log.v("jack","the packagename=56"+packageName);
			     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_czl56[oldCount-1])){
				newCount = ++oldCount;
				SharedPreferencesUtil.setPassApkCountForCZL56(context, newCount);
				//Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
			}else{
				//Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
			if (newCount > MyApplication.default_total_apk_czl56) {
				newCount = MyApplication.default_total_apk_czl56;
				Log.v("jack","the packagename=56-----"+packageName);
			Log.v("jack","the packagename=56--------"+packageName);
				SharedPreferencesUtil.setPassApkCountForCZL56(context, newCount);
			}
			
		}

		// ========================����ʽ�۲�
		if (CheckStringGroupHasTargetString(package_name_yds, packageName)) {
		//	Log.v("jack", "���������ǹ۲�����" + packageName);
			oldCount = SharedPreferencesUtil.getpassApkCountForYDSGC(context);
			     if(oldCount==0){
			  oldCount=1;
			}
			
			if(packageName.equals(package_name_yds[oldCount-1])){
				newCount = ++oldCount;
				SharedPreferencesUtil.setpassApkCountForYDSGC(context, newCount);
			//	Log.v("jack", "����������ϲ�����������Ҫ�ٴβ��뵱���µ����ݼ�¼");
			}else{
			//	Log.v("jack", "������������ϲ�������������Ҫ�ٴβ��뵱���µ����ݼ�¼");
				return ;
			}
		//	Log.v("jack", "���������ǹ۲�����" + packageName + "�������µĹ�����" + newCount);
			if (newCount > MyApplication.default_total_apk_ydsgc) {
				newCount = MyApplication.default_total_apk_ydsgc;
				SharedPreferencesUtil.setpassApkCountForYDSGC(context, newCount);
			}
		}

		// ===========רע����ѵ=============
     	if (CheckStringGroupHasTargetString(package_name_zzl, packageName)) {
			oldCount = SharedPreferencesUtil.getpassApkCountForZZL(context);
			
			     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_zzl[oldCount-1])){
				newCount = ++oldCount;
				SharedPreferencesUtil.setpassApkCountForZZL(context, newCount);
			}else{
				return ;
			}
			if (newCount > MyApplication.default_total_apk_zzl) {
				newCount = MyApplication.default_total_apk_zzl;
				SharedPreferencesUtil.setpassApkCountForZZL(context, newCount);
			}
		}
//==================��ѧ˼ά ������Ƚ�===================
		if(CheckStringGroupHasTargetString(package_name_sxsw_ssybj, packageName)&&MyApplication.FLAGOFSXKJ ==true){
		

			oldCount = SharedPreferencesUtil.getpassApkCountForSXYKJssybj(context);
			if(packageName.equals(package_name_sxsw_ssybj[oldCount-1])){

				newCount = ++oldCount;
				SharedPreferencesUtil.setpassApkCountForSXYKJssybj(context, newCount);
			}else{
				return ;
			}
			if (newCount > MyApplication.default_total_apk_sxkj_ssybj) {
				newCount = MyApplication.default_total_apk_sxkj_ssybj;
				SharedPreferencesUtil.setpassApkCountForSXYKJssybj(context, newCount);
			}
					if (packageName
					.equals(package_name_sxsw_ssybj[package_name_sxsw_ssybj.length - 1])) {
				SharedPreferencesUtil.setpassApkCountForSXYKJfpysx(context, 1);
			}
			return ;

			
		}

//===============================������˳��=====================================
				if(CheckStringGroupHasTargetString(package_name_sxsw_fpysx, packageName)&&MyApplication.FLAGOFSXKJ ==true){

//Log.v("jack", "================="+packageName);
//Log.v("jack", "================="+packageName);
//Log.v("jack", "================="+packageName);
			oldCount = SharedPreferencesUtil.getpassApkCountForSXYKJfpysx(context);
			if(packageName.equals(package_name_sxsw_fpysx[oldCount-1])){
				newCount = ++oldCount;
SharedPreferencesUtil.setpassApkCountForSXYKJfpysx(context,newCount);
			}else{
				return ;
			}
			if (newCount > MyApplication.default_total_apk_sxkj_fpysx) {
				newCount = MyApplication.default_total_apk_sxkj_fpysx;
			SharedPreferencesUtil.setpassApkCountForSXYKJfpysx(context,newCount);

			}

			if (packageName
					.equals(package_name_sxsw_fpysx[package_name_sxsw_fpysx.length - 1])) {
				SharedPreferencesUtil.setpassApkCountForSXYKJsysz(context, 1);
			//	Log.v("jack", "====666666666666============="+packageName);
			}
			return ;

		
		}
		
	
		
		//==================��ѧ˼ά ��������====================
		if(CheckStringGroupHasTargetString(package_name_sxsw_sysz, packageName)&&MyApplication.FLAGOFSXKJ ==true){
			oldCount = SharedPreferencesUtil.getpassApkCountForSXYKJsysz(context);
			     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_sxsw_sysz[oldCount-1])){
				newCount = ++oldCount;
				SharedPreferencesUtil.setpassApkCountForSXYKJsysz(context, newCount);
			}else{
				return ;
			}
			if (newCount > MyApplication.default_total_apk_sxkj_sysz) {
				newCount = MyApplication.default_total_apk_sxkj_sysz;
				SharedPreferencesUtil.setpassApkCountForSXYKJsysz(context, newCount);
			}
			if (packageName
					.equals(package_name_sxsw_sysz[package_name_sxsw_sysz.length - 1])) {
				SharedPreferencesUtil.setpassApkCountForSXYKJjjjs(context, 1);
			}
			return ;
		}
		
		//====================��ѧ˼ά �Ӽ�����===================
		if(CheckStringGroupHasTargetString(package_name_sxsw_jjjs, packageName)){
			oldCount = SharedPreferencesUtil.getpassApkCountForSXYKJjjjs(context);
			     if(oldCount==0){
			  oldCount=1;
			}
			if(packageName.equals(package_name_sxsw_jjjs[oldCount-1])){
				newCount = ++oldCount;
				SharedPreferencesUtil.setpassApkCountForSXYKJjjjs(context, newCount);
			}else{
				return ;
			}
			if (newCount > MyApplication.default_total_apk_sxkj_jjjs) {
				newCount = MyApplication.default_total_apk_sxkj_jjjs;
				SharedPreferencesUtil.setpassApkCountForSXYKJjjjs(context, newCount);
			}
			
		}


	}

}
