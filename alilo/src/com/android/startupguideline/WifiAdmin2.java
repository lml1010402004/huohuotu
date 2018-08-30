package com.android.startupguideline;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.util.Log;
import android.widget.Toast;

public class WifiAdmin2 {
    // ����WifiManager����   
    private WifiManager mWifiManager;  
    // ����WifiInfo����     
    private WifiInfo mWifiInfo;    
    // ɨ��������������б�     
    private List<ScanResult> mWifiList;    
    // ���������б�     
    private List<WifiConfiguration> mWifiConfiguration;    
    // ����һ��WifiLock     
    WifiLock mWifiLock;  
   
    // ������     
    public WifiAdmin2(Context context) {    
        // ȡ��WifiManager����     
        mWifiManager = (WifiManager) context    
                .getSystemService(Context.WIFI_SERVICE);    
        // ȡ��WifiInfo����     
        mWifiInfo = mWifiManager.getConnectionInfo();    
    }    
  
    // ��WIFI   
    public void openWifi(Context context) {  
        if (!mWifiManager.isWifiEnabled()) {  
            mWifiManager.setWifiEnabled(true);
        }else if (mWifiManager.getWifiState() == 2) {
    		//Toast.makeText(context,"亲，wifi正在开启，不用再开了。", Toast.LENGTH_SHORT).show();
		}else{
        	//Toast.makeText(context,"亲，wifi已经开启，不用再开了",Toast.LENGTH_SHORT).show();
        }
    }  
  
    // �ر�wifi
    public void closeWifi(Context context) {    
        if (mWifiManager.isWifiEnabled()) {    
            mWifiManager.setWifiEnabled(false);    
        }else if(mWifiManager.getWifiState() == 1){  
           // Toast.makeText(context,"亲，Wifi已经关闭，不用再关了", Toast.LENGTH_SHORT).show();  
        }else if (mWifiManager.getWifiState() == 0) {  
           // Toast.makeText(context,"亲，Wifi正在关闭，不用再关了", Toast.LENGTH_SHORT).show();  
        }else{  
           // Toast.makeText(context,"请重新关闭", Toast.LENGTH_SHORT).show();  
        }  
    }    
    
    // ��鵱ǰWIFI״̬     
    public void checkState(Context context) {    
        if (mWifiManager.getWifiState() == 0) {  
            //Toast.makeText(context,"Wifi正在关闭", Toast.LENGTH_SHORT).show();  
        } else if (mWifiManager.getWifiState() == 1) {  
           // Toast.makeText(context,"Wifi已经关闭", Toast.LENGTH_SHORT).show();  
        } else if (mWifiManager.getWifiState() == 2) {  
          //  Toast.makeText(context,"Wifi已经开启", Toast.LENGTH_SHORT).show();  
        } else if (mWifiManager.getWifiState() == 3) {  
          //  Toast.makeText(context,"Wifi已经开启2017/5/19", Toast.LENGTH_SHORT).show();  
        } else {  
          //  Toast.makeText(context,"wifi没有开启",Toast.LENGTH_SHORT).show();  
        }    
    }    
    
    // ����WifiLock     
    public void acquireWifiLock() {  
        mWifiLock.acquire();  
    }  
  
    // ����WifiLock     
    public void releaseWifiLock() {    
        // �ж�ʱ������     
        if (mWifiLock.isHeld()) {    
            mWifiLock.acquire();    
        }    
    }    
    
    // ����һ��WifiLock     
    public void creatWifiLock() {    
        mWifiLock = mWifiManager.createWifiLock("Test");    
    }    
    
    // �õ����úõ�����     
    public List<WifiConfiguration> getConfiguration() {    
        return mWifiConfiguration;    
    }    
    
    // ָ�����úõ������������     
    public void connectConfiguration(int index) {    
        // �����������úõ�������������     
        if (index > mWifiConfiguration.size()) {    
            return;    
        }    
        // �������úõ�ָ��ID������     
        mWifiManager.enableNetwork(mWifiConfiguration.get(index).networkId,    
                true);    
    }    
  
    public void startScan(Context context) {  
        mWifiManager.startScan();  
        //�õ�ɨ����   
    	List<ScanResult> results = mWifiManager.getScanResults();   
    	// �õ����úõ���������   
	mWifiConfiguration = mWifiManager.getConfiguredNetworks();   
	if (results == null) {  
		if(mWifiManager.getWifiState()==3){  
		    	Toast.makeText(context,"当前区域没有无线局域网",Toast.LENGTH_SHORT).show();  
        	}else if(mWifiManager.getWifiState()==2){  
            		Toast.makeText(context,"wifi正在开启,请稍后扫描", Toast.LENGTH_SHORT).show();  
        	}else{
			Toast.makeText(context,"WiFi没有开启.", Toast.LENGTH_SHORT).show();  
       		}  
	} else {
		mWifiList = new ArrayList();
	    	for(ScanResult result : results){
	    		if (result.SSID == null || result.SSID.length() == 0
	    				|| result.capabilities.contains("[IBSS]")) {
	    			continue;
	    		}
	    		boolean found = false;
	    		Log.i("MainActivity","result= "+result.SSID +" capabilities= "+result.capabilities);
	    		for(ScanResult item:mWifiList){
	    			Log.i("MainActivity","item= "+item.SSID +" capabilities="+item.capabilities);
	    			if(item.SSID.equals(result.SSID)&&item.capabilities.equals(result.capabilities)){
	    				Log.i("MainActivity","found true");
	    				found = true;
	    				break;
	    			}
	    		}
	    		if(!found){
	    			mWifiList.add(result);
	    		}
	    	}
	    }
    }  
  
    // �õ������б�   
    public List<ScanResult> getWifiList() {  
        return mWifiList;  
    }  
  
    // �鿴ɨ����   
    public StringBuilder lookUpScan() {  
        StringBuilder stringBuilder = new StringBuilder();  
        for (int i = 0; i < mWifiList.size(); i++) {  
            stringBuilder  
                    .append("Index_" + new Integer(i + 1).toString() + ":");  
            // ��ScanResult��Ϣת����һ���ַ�����     
            // ���аѰ�����BSSID��SSID��capabilities��frequency��level    
            stringBuilder.append((mWifiList.get(i)).toString());  
            stringBuilder.append("/n");  
        }  
        return stringBuilder;  
    } 
  
    // �õ�MAC��ַ  
    public String getMacAddress() {  
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();  
    }  
  
    // �õ�������BSSID   
    public String getBSSID() {  
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();  
    }  
  
    // �õ����ӵ�IP  
    public int getIPAddress() {  
        return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();  
    }  
  
    // �õ����ӵ�ID   
    public int getNetworkId() {  
        return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();  
    }  
  
    // �õ�WifiInfo��������Ϣ��   
    public String getWifiInfo() {  
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();  
    }  
  
    // ���һ�����粢����   
    public void addNetwork(WifiConfiguration wcg) {  
     int wcgID = mWifiManager.addNetwork(wcg);  
     boolean b =  mWifiManager.enableNetwork(wcgID, true);  
     System.out.println("a--" + wcgID); 
     System.out.println("b--" + b); 
    }  
  
    // �Ͽ�ָ��ID������   
    public void disconnectWifi(int netId) {  
        mWifiManager.disableNetwork(netId);  
        mWifiManager.disconnect();  
    }
    public void removeWifi(int netId) {
        disconnectWifi(netId);
        mWifiManager.removeNetwork(netId);
    }
  
//����wifi�ȵ�ġ�
  
    public WifiConfiguration CreateWifiInfo(String SSID, String Password, int Type)  
    {  
          WifiConfiguration config = new WifiConfiguration();    
           config.allowedAuthAlgorithms.clear();  
           config.allowedGroupCiphers.clear();  
           config.allowedKeyManagement.clear();  
           config.allowedPairwiseCiphers.clear();  
           config.allowedProtocols.clear();  
           config.SSID = "\"" + SSID + "\"";    
           
          WifiConfiguration tempConfig = this.IsExsits(SSID);            
          if(tempConfig != null) {   
              mWifiManager.removeNetwork(tempConfig.networkId);   
          } 
           
          if(Type == 1) //WIFICIPHER_NOPASS 
          {  
               config.wepKeys[0] = "";  
               config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);  
               config.wepTxKeyIndex = 0;  
          }  
          if(Type == 2) //WIFICIPHER_WEP 
          {  
              config.hiddenSSID = true; 
              config.wepKeys[0]= "\""+Password+"\"";  
              config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);  
              config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);  
              config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);  
              config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);  
              config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);  
              config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);  
              config.wepTxKeyIndex = 0;  
          }  
          if(Type == 3) //WIFICIPHER_WPA 
          {  
          config.preSharedKey = "\""+Password+"\"";  
          config.hiddenSSID = true;    
          config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);    
          config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);                          
          config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);                          
          config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);                     
          //config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);   
          config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP); 
          config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP); 
          config.status = WifiConfiguration.Status.ENABLED;    
          } 
           return config;  
    }  
     
    private WifiConfiguration IsExsits(String SSID)   
    {   
        List<WifiConfiguration> existingConfigs = mWifiManager.getConfiguredNetworks();   
           for (WifiConfiguration existingConfig : existingConfigs)    
           {   
             if (existingConfig.SSID.equals("\""+SSID+"\""))   
             {   
                 return existingConfig;   
             }   
           }   
        return null;    
    } 
}
