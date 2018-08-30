package com.android.alilo.setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
//import android.util.ArrayMap;
import android.util.Log;

public class WifiAdmin {

	// 定义一个WifiManager对象
	private WifiManager mWifiManager;
	// 定义一个WifiInfo对象
	private WifiInfo mWifiInfo;
	// 扫描出的网络连接列表
	private List<ScanResult> mWifiList;
	// 网络连接列表
	private List<WifiConfiguration> mWifiConfigurations;
	WifiLock mWifiLock;

	public WifiAdmin(Context context) {
		// 取得WifiManager对象
		mWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		// 取得WifiInfo对象
		mWifiInfo = mWifiManager.getConnectionInfo();
		Log.v("jack", "wifiManager已经启动了");
	}

	// 打开wifi
	public void openWifi() {
		if (!mWifiManager.isWifiEnabled()) {
			Log.v("jack","open wifi");
			mWifiManager.setWifiEnabled(true);
		}
	}
	
	 // 添加一个网络并连接   
    public void addNetwork(WifiConfiguration wcg) {  
     int wcgID = mWifiManager.addNetwork(wcg);  
     boolean b =  mWifiManager.enableNetwork(wcgID, true);  
     System.out.println("a--" + wcgID); 
     System.out.println("b--" + b); 
    }  

	// 关闭wifi
	public void closeWifi() {
		if (mWifiManager.isWifiEnabled()) {
			Log.d("jack","close wifi");
			mWifiManager.setWifiEnabled(false);
		}
	}

	public boolean wifiState() {
		return mWifiManager.isWifiEnabled();
	}

	// 检查当前wifi状态
	public int checkState() {
		Log.d("jack","state wifi");
		return mWifiManager.getWifiState();
	}

	// 锁定wifiLock
	public void acquireWifiLock() {
		mWifiLock.acquire();
	}

	// 解锁wifiLock
	public void releaseWifiLock() {
		// 判断是否锁定
		if (mWifiLock.isHeld()) {
			mWifiLock.acquire();
		}
	}

	// 创建一个wifiLock
	public void createWifiLock() {
		mWifiLock = mWifiManager.createWifiLock("test");
	}

	// 得到配置好的网络
	public List<WifiConfiguration> getConfiguration() {
		return mWifiConfigurations;
	}

	// 指定配置好的网络进行连接
	public void connetionConfiguration(int index) {
		if (index > mWifiConfigurations.size()) {
			return;
		}
		// 连接配置好指定ID的网络
		mWifiManager.enableNetwork(mWifiConfigurations.get(index).networkId,
				true);
	}

	public void startScan() {
		mWifiManager.startScan();
		Log.v("jack", "mWifiManager正在扫描");
		// 得到扫描结果
		mWifiList =getLastWifiList();// mWifiManager.getScanResults();
		// 得到配置好的网络连接
		mWifiConfigurations = mWifiManager.getConfiguredNetworks();
	}

	// 得到网络列表
	public List<ScanResult> getWifiList() {
		return mWifiList;
	}

	// 查看扫描结果
	public StringBuffer lookUpScan() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mWifiList.size(); i++) {
			sb.append("Index_" + new Integer(i + 1).toString() + ":");
			// 将ScanResult信息转换成一个字符串包
			// 其中把包括：BSSID、SSID、capabilities、frequency、level
			sb.append((mWifiList.get(i)).toString()).append("\n");
		}
		return sb;
	}

	public String getMacAddress() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
	}

	public String getBSSID() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
	}

	public int getIpAddress() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
	}

	public WifiInfo getWifiInfo() {
		return mWifiInfo;
	}

	// 得到连接的ID
	public int getNetWordId() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
	}

	// 得到wifiInfo的所有信息
	public String getWifiInfoString() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
	}

	// 添加一个网络并连接
	public boolean addNetWork(WifiConfiguration configuration) {
		int wcgId = mWifiManager.addNetwork(configuration);
		mWifiManager.saveConfiguration();
		return mWifiManager.enableNetwork(wcgId, true);
	}
	
	public void removeNetWork(int netId){
		mWifiManager.removeNetwork(netId);
	}

	// 断开指定ID的网络
	public void disConnectionWifi(int netId) {
		mWifiManager.disableNetwork(netId);
		mWifiManager.disconnect();
	}
	/**
	 * 除去空SSID、和重复的wifi集合
	 * @return
	 */
	public List<ScanResult> getLastWifiList(){
		Map<String, ScanResult> apMap = new HashMap<String, ScanResult>();
		List<ScanResult> wifiList =new ArrayList<ScanResult>();
		 final List<ScanResult> results = mWifiManager.getScanResults();
	        if (results != null) {
	            for (ScanResult result : results) {
	                if (result.SSID == null || result.SSID.length() == 0 ||
	                        result.capabilities.contains("[IBSS]")) {
	                    continue;
	                }

	                boolean found = false;
	                ScanResult scanResult=apMap.get(result.SSID);
	                if (scanResult!=null && update(result,scanResult)){
	                	result.level=scanResult.level;
	                        found = true;
	                }
	                WifiConfiguration wifiConfiguration=getWifiConfiguration();
	                
	                if(wifiConfiguration!=null){
	                	if(getWifiConfigSSID(wifiConfiguration).equals(result.SSID))
	                		found=true;
	                }
	                if (!found) {
	                	wifiList.add(result);
	                    apMap.put(result.SSID, result);
	                }
	            }
	        }
	        
	        return wifiList;
	}
	
	String getWifiConfigSSID(WifiConfiguration configuration){
		String s=String.valueOf('"');
		if (!configuration.SSID.contains(s))
			return configuration.SSID;
		else{
			String ssid=configuration.SSID.substring(
							1,
							configuration.SSID
									.lastIndexOf('"'));
			return ssid;
		}
	}
	
    boolean update(ScanResult result,ScanResult scanResult) {
        if (result.SSID.equals(scanResult.SSID) && result.capabilities.equals(scanResult.capabilities)) {
//            if (WifiManager.compareSignalLevel(result.level, mRssi) > 0) {
//                int oldLevel = getLevel();
//                mRssi = result.level;
//                if (getLevel() != oldLevel) {
//                    notifyChanged();
//                }
//            }
//            // This flag only comes from scans, is not easily saved in config
//            if (security == SECURITY_PSK) {
//                pskType = getPskType(result);
//            }
//            refresh();
            return true;
        }
        return false;
    }
    
	public WifiConfiguration getWifiConfiguration() {
		List<WifiConfiguration> list_WifiConfiguration =getConfiguration();
		if(list_WifiConfiguration==null) return null;
		for (int i = 0; i < list_WifiConfiguration.size(); i++) {
			WifiConfiguration wifiConfiguration = list_WifiConfiguration.get(i);
			if (wifiConfiguration.status == 0) 
				return wifiConfiguration;
		
		}
		return null;
	}

	/*
	 * public WifiConfiguration CreateWifiInfo(String SSID, String Password, int
	 * Type) { WifiConfiguration config = new WifiConfiguration();
	 * config.allowedAuthAlgorithms.clear(); config.allowedGroupCiphers.clear();
	 * config.allowedKeyManagement.clear();
	 * config.allowedPairwiseCiphers.clear(); config.allowedProtocols.clear();
	 * config.SSID = "\"" + SSID + "\"";
	 * 
	 * WifiConfiguration tempConfig = this.IsExsits(SSID); if (tempConfig !=
	 * null) { mWifiManager.removeNetwork(tempConfig.networkId); }
	 * 
	 * if (Type == 0) // WIFICIPHER_NOPASS { config.wepKeys[0] = "";
	 * config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
	 * config.wepTxKeyIndex = 0; }else if (Type == 1) // WIFICIPHER_WEP {
	 * config.hiddenSSID = true; config.wepKeys[0] = "\"" + Password + "\"";
	 * config.allowedAuthAlgorithms
	 * .set(WifiConfiguration.AuthAlgorithm.SHARED);
	 * config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
	 * config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
	 * config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
	 * config.allowedGroupCiphers .set(WifiConfiguration.GroupCipher.WEP104);
	 * config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
	 * config.wepTxKeyIndex = 0; }else if (Type == 2) // WIFICIPHER_WPA PSK {
	 * config.preSharedKey = "\"" + Password + "\""; config.hiddenSSID = true;
	 * config.allowedAuthAlgorithms .set(WifiConfiguration.AuthAlgorithm.OPEN);
	 * config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
	 * config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
	 * config.allowedPairwiseCiphers
	 * .set(WifiConfiguration.PairwiseCipher.TKIP); //
	 * config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
	 * config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
	 * config.allowedPairwiseCiphers
	 * .set(WifiConfiguration.PairwiseCipher.CCMP); config.status =
	 * WifiConfiguration.Status.ENABLED; }else if(Type==3){//WPA EAP
	 * config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_EAP);
	 * config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
	 * config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
	 * config.preSharedKey = "\"" + Password + "\""; } return config; }
	 */

	public WifiConfiguration CreateWifiInfo(String mSSID, String Password,
			int Type) {
		// 创建一个新的WifiConfiguration
		WifiConfiguration configuration = new WifiConfiguration();

		// 如果加密模式为WEP
		if (Type == 1) {
			Log.d("wwwwwwwwjx11111111","WEP password wifi");
			configuration.SSID = "\"" + mSSID + "\"";
			configuration.preSharedKey = "\"" + Password + "\""; // 该热点的密码
			configuration.hiddenSSID = true;
			configuration.status = WifiConfiguration.Status.ENABLED;
			configuration.allowedAuthAlgorithms
					.set(WifiConfiguration.AuthAlgorithm.OPEN);
			configuration.allowedGroupCiphers
					.set(WifiConfiguration.GroupCipher.WEP40);
			configuration.allowedGroupCiphers
					.set(WifiConfiguration.GroupCipher.WEP104);
			configuration.allowedKeyManagement
					.set(WifiConfiguration.KeyMgmt.NONE);
			configuration.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.TKIP);
			configuration.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.CCMP);
			configuration.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		}
		// 如果加密模式为WPA EPA
		else if (Type == 2) {
			Log.d("wwwwwwwwjx2222222","WPA EPA password wifi");
			configuration.SSID = "\"" + mSSID + "\"";
			configuration.preSharedKey = "\"" + Password + "\""; // 该热点的密码
			configuration.hiddenSSID = true;
			configuration.status = WifiConfiguration.Status.ENABLED;
			configuration.allowedAuthAlgorithms
					.set(WifiConfiguration.AuthAlgorithm.OPEN);
			configuration.allowedGroupCiphers
					.set(WifiConfiguration.GroupCipher.TKIP);
			configuration.allowedGroupCiphers
					.set(WifiConfiguration.GroupCipher.CCMP);
			configuration.allowedKeyManagement
					.set(WifiConfiguration.KeyMgmt.WPA_EAP);
			configuration.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.TKIP);
			configuration.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.CCMP);
			configuration.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		}
		// 如果加密模式为WPA PSK
		else if (Type == 3) {
			Log.d("wwwwwwwwjx33333333","WPA PSK password wifi");
			configuration.SSID = "\"" + mSSID + "\"";
			configuration.preSharedKey = "\"" + Password + "\""; // 该热点的密码
			configuration.hiddenSSID = true;
			configuration.status = WifiConfiguration.Status.ENABLED;
			configuration.allowedAuthAlgorithms
					.set(WifiConfiguration.AuthAlgorithm.OPEN);
			configuration.allowedGroupCiphers
					.set(WifiConfiguration.GroupCipher.TKIP);
			configuration.allowedGroupCiphers
					.set(WifiConfiguration.GroupCipher.CCMP);
			configuration.allowedKeyManagement
					.set(WifiConfiguration.KeyMgmt.WPA_PSK);
			configuration.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.TKIP);
			configuration.allowedPairwiseCiphers
					.set(WifiConfiguration.PairwiseCipher.CCMP);
			configuration.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		}
		// 无加密
		else {
			Log.d("wwwwwwwwjx4444444","no password wifi");
			configuration.allowedAuthAlgorithms.clear();
			configuration.allowedGroupCiphers.clear();
			configuration.allowedKeyManagement.clear();
			configuration.allowedPairwiseCiphers.clear();
			configuration.allowedProtocols.clear();
			configuration.SSID = "\"" + mSSID + "\"";
			//configuration.wepKeys[0] = ""; //20160516_修改无密码wifi一直显示连接中
			configuration.allowedKeyManagement
					.set(WifiConfiguration.KeyMgmt.NONE);
			//configuration.wepTxKeyIndex = 0;
		}
		mWifiManager.saveConfiguration();
		
		return configuration;
	}
	
    /**
     * 配置wifi
     * 
     * @param SSID
     * @param Password
     * @param Type
     * @return
     */
    public WifiConfiguration createNewWifiInfo(String SSID,
            String Password, int Type)
    {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";

        WifiConfiguration tempConfig = IsExsits(SSID);
        if (tempConfig != null) {
            mWifiManager.removeNetwork(tempConfig.networkId);
        }

        if (Type == 1) // WIFICIPHER_NOPASS
        {
            config.wepKeys[0] = "";
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 2) // WIFICIPHER_WEP
        {
            config.hiddenSSID = true;
            config.wepKeys[0] = "\"" + Password + "\"";
            config.allowedAuthAlgorithms
                    .set(WifiConfiguration.AuthAlgorithm.SHARED);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers
                    .set(WifiConfiguration.GroupCipher.WEP104);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 3) // WIFICIPHER_WPA
        {
            config.preSharedKey = "\"" + Password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms
                    .set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers
                    .set(WifiConfiguration.PairwiseCipher.TKIP);
            // config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers
                    .set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status = WifiConfiguration.Status.ENABLED;
        }
        return config;
    }

	public WifiConfiguration IsExsits(String SSID) {
		List<WifiConfiguration> existingConfigs = mWifiManager
				.getConfiguredNetworks();
		for (WifiConfiguration existingConfig : existingConfigs) {
			if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
				return existingConfig;
			}
		}
		return null;
	}

}
