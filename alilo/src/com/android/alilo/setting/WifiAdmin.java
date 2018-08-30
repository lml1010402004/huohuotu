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

	// ����һ��WifiManager����
	private WifiManager mWifiManager;
	// ����һ��WifiInfo����
	private WifiInfo mWifiInfo;
	// ɨ��������������б�
	private List<ScanResult> mWifiList;
	// ���������б�
	private List<WifiConfiguration> mWifiConfigurations;
	WifiLock mWifiLock;

	public WifiAdmin(Context context) {
		// ȡ��WifiManager����
		mWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		// ȡ��WifiInfo����
		mWifiInfo = mWifiManager.getConnectionInfo();
		Log.v("jack", "wifiManager�Ѿ�������");
	}

	// ��wifi
	public void openWifi() {
		if (!mWifiManager.isWifiEnabled()) {
			Log.v("jack","open wifi");
			mWifiManager.setWifiEnabled(true);
		}
	}
	
	 // ���һ�����粢����   
    public void addNetwork(WifiConfiguration wcg) {  
     int wcgID = mWifiManager.addNetwork(wcg);  
     boolean b =  mWifiManager.enableNetwork(wcgID, true);  
     System.out.println("a--" + wcgID); 
     System.out.println("b--" + b); 
    }  

	// �ر�wifi
	public void closeWifi() {
		if (mWifiManager.isWifiEnabled()) {
			Log.d("jack","close wifi");
			mWifiManager.setWifiEnabled(false);
		}
	}

	public boolean wifiState() {
		return mWifiManager.isWifiEnabled();
	}

	// ��鵱ǰwifi״̬
	public int checkState() {
		Log.d("jack","state wifi");
		return mWifiManager.getWifiState();
	}

	// ����wifiLock
	public void acquireWifiLock() {
		mWifiLock.acquire();
	}

	// ����wifiLock
	public void releaseWifiLock() {
		// �ж��Ƿ�����
		if (mWifiLock.isHeld()) {
			mWifiLock.acquire();
		}
	}

	// ����һ��wifiLock
	public void createWifiLock() {
		mWifiLock = mWifiManager.createWifiLock("test");
	}

	// �õ����úõ�����
	public List<WifiConfiguration> getConfiguration() {
		return mWifiConfigurations;
	}

	// ָ�����úõ������������
	public void connetionConfiguration(int index) {
		if (index > mWifiConfigurations.size()) {
			return;
		}
		// �������ú�ָ��ID������
		mWifiManager.enableNetwork(mWifiConfigurations.get(index).networkId,
				true);
	}

	public void startScan() {
		mWifiManager.startScan();
		Log.v("jack", "mWifiManager����ɨ��");
		// �õ�ɨ����
		mWifiList =getLastWifiList();// mWifiManager.getScanResults();
		// �õ����úõ���������
		mWifiConfigurations = mWifiManager.getConfiguredNetworks();
	}

	// �õ������б�
	public List<ScanResult> getWifiList() {
		return mWifiList;
	}

	// �鿴ɨ����
	public StringBuffer lookUpScan() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mWifiList.size(); i++) {
			sb.append("Index_" + new Integer(i + 1).toString() + ":");
			// ��ScanResult��Ϣת����һ���ַ�����
			// ���аѰ�����BSSID��SSID��capabilities��frequency��level
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

	// �õ����ӵ�ID
	public int getNetWordId() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
	}

	// �õ�wifiInfo��������Ϣ
	public String getWifiInfoString() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
	}

	// ���һ�����粢����
	public boolean addNetWork(WifiConfiguration configuration) {
		int wcgId = mWifiManager.addNetwork(configuration);
		mWifiManager.saveConfiguration();
		return mWifiManager.enableNetwork(wcgId, true);
	}
	
	public void removeNetWork(int netId){
		mWifiManager.removeNetwork(netId);
	}

	// �Ͽ�ָ��ID������
	public void disConnectionWifi(int netId) {
		mWifiManager.disableNetwork(netId);
		mWifiManager.disconnect();
	}
	/**
	 * ��ȥ��SSID�����ظ���wifi����
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
		// ����һ���µ�WifiConfiguration
		WifiConfiguration configuration = new WifiConfiguration();

		// �������ģʽΪWEP
		if (Type == 1) {
			Log.d("wwwwwwwwjx11111111","WEP password wifi");
			configuration.SSID = "\"" + mSSID + "\"";
			configuration.preSharedKey = "\"" + Password + "\""; // ���ȵ������
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
		// �������ģʽΪWPA EPA
		else if (Type == 2) {
			Log.d("wwwwwwwwjx2222222","WPA EPA password wifi");
			configuration.SSID = "\"" + mSSID + "\"";
			configuration.preSharedKey = "\"" + Password + "\""; // ���ȵ������
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
		// �������ģʽΪWPA PSK
		else if (Type == 3) {
			Log.d("wwwwwwwwjx33333333","WPA PSK password wifi");
			configuration.SSID = "\"" + mSSID + "\"";
			configuration.preSharedKey = "\"" + Password + "\""; // ���ȵ������
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
		// �޼���
		else {
			Log.d("wwwwwwwwjx4444444","no password wifi");
			configuration.allowedAuthAlgorithms.clear();
			configuration.allowedGroupCiphers.clear();
			configuration.allowedKeyManagement.clear();
			configuration.allowedPairwiseCiphers.clear();
			configuration.allowedProtocols.clear();
			configuration.SSID = "\"" + mSSID + "\"";
			//configuration.wepKeys[0] = ""; //20160516_�޸�������wifiһֱ��ʾ������
			configuration.allowedKeyManagement
					.set(WifiConfiguration.KeyMgmt.NONE);
			//configuration.wepTxKeyIndex = 0;
		}
		mWifiManager.saveConfiguration();
		
		return configuration;
	}
	
    /**
     * ����wifi
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
