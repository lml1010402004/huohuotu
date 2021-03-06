package com.android.alilo.setting;

import java.util.List;

import com.android.alilo.R;
import com.android.alilo.setting.WifiAdmin;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import com.android.myUtils.Utils;
import android.os.Handler;
import android.os.Message;
import android.widget.ToggleButton;



public class SettingWifiManager extends Activity {

	ImageButton wifi_title_back; // 头部返回按键

	ToggleButton tb_wifi_switch; // wifi开关

	RelativeLayout rl_wifi_connect; // 连接wifi信息行

	TextView tv_wifi_connect_name; // 连接的wifi名称
	TextView tv_wifi_connect_info; // 连接状态
	ImageView iv_wifi_status; // 连接的wifi图标状态
	ImageButton ib_wifi_content_next; // 连接wifi的下一步按键

	ListView wifi_scanResult;

	private List<ScanResult> list;
	private ScanResult mScanResult;
	private ScanResult mConScanResult;
	private StringBuffer sb = new StringBuffer();
	private WifiAdmin mWifiAdmin;
	WifiConfiguration con_wifi;
	WifiConfiguration configuration;

	Dialog dialog;
	EditText et_wifi_pwd;
	Button bt_wifi_pws_cancel;
	Button bt_wifi_pws_con;
	Button bt_wifi_pws_forget;

	IntentFilter mFilter;

	int mPosition = 0;

	boolean isUpdate = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifi_manager_page);
		 Utils.addActivity(this);
		mWifiAdmin = new WifiAdmin(this);
		init();
	}

	void init() {
		wifi_title_back = (ImageButton) findViewById(R.id.wifi_title_back);
		tb_wifi_switch = (ToggleButton) findViewById(R.id.switch_wifi);
		rl_wifi_connect = (RelativeLayout) findViewById(R.id.rl_wifi_connect);
		rl_wifi_connect.setVisibility(View.GONE);
		tv_wifi_connect_name = (TextView) findViewById(R.id.tv_wifi_connect_name);
		tv_wifi_connect_info = (TextView) findViewById(R.id.tv_wifi_connect_info);
		iv_wifi_status = (ImageView) findViewById(R.id.iv_wifi_status);

		if (mWifiAdmin.wifiState()) {
			tb_wifi_switch.setChecked(true);

		} else {
			tb_wifi_switch.setChecked(false);
		}

		wifi_title_back.setOnClickListener(clickListener);
		tb_wifi_switch.setOnCheckedChangeListener(checkedChangeListener);

		wifi_scanResult = (ListView) findViewById(R.id.wifi_scanResult);
		wifi_scanResult.setAdapter(baseAdapter);
		wifi_scanResult.setOnItemClickListener(itemClickListener);

		mFilter = new IntentFilter();
		mFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		mFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
		mFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		mFilter.addAction("refreshwifibutton");
		registerReceiver(mReceiver, mFilter);
		handler.post(refreshRunnable);
	}
	OnCheckedChangeListener checkedChangeListener=new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			
			if (!isChecked) {
			//	tb_wifi_switch.setClickable(false);
				mWifiAdmin.closeWifi();
				rl_wifi_connect.setVisibility(View.GONE);
wifi_scanResult.setVisibility(View.INVISIBLE);
//tb_wifi_switch.setClickable(true);

			} else {
//Toast.makeText(SettingWifiManager.this,getResources().getString(R.string.loading) , Toast.LENGTH_LONG).show();
		    	tb_wifi_switch.setClickable(false);
				rl_wifi_connect.setVisibility(View.VISIBLE);
                wifi_scanResult.setVisibility(View.VISIBLE);

				mWifiAdmin.openWifi();
			
			//	Toast.makeText(SettingWifiManager.this, "isChecked"+isChecked, Toast.LENGTH_LONG).show();
				handler.post(refreshRunnable);

			}
		}
	};

	BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (WifiManager.WIFI_STATE_CHANGED_ACTION
					.equals(intent.getAction())) {// 这个监听wifi的打开与关闭
				int wifiState = intent.getIntExtra(
						WifiManager.EXTRA_WIFI_STATE, 0);
				switch (wifiState) {
				case WifiManager.WIFI_STATE_ENABLED:
					tb_wifi_switch.setChecked(true);
					showcon_wifi();
					break;
				case WifiManager.WIFI_STATE_DISABLED:
				case WifiManager.WIFI_STATE_DISABLING:
					rl_wifi_connect.setVisibility(View.GONE);
					tb_wifi_switch.setChecked(false);
					break;
					

				}
			} else if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent
					.getAction())) {
				Parcelable parcelableExtra = intent
						.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
				if (null != parcelableExtra) {
					NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
					rl_wifi_connect.setVisibility(View.VISIBLE);
					tv_wifi_connect_info.setText(getSummary(networkInfo
							.getDetailedState()));
					if (networkInfo.isConnected()) {

						String s = String.valueOf('"');
						if (!networkInfo.getExtraInfo().contains(s))
							tv_wifi_connect_name.setText(networkInfo
									.getExtraInfo());
						else
							tv_wifi_connect_name.setText(networkInfo
									.getExtraInfo().substring(
											1,
											networkInfo.getExtraInfo()
													.lastIndexOf('"')));
						con_wifi = mWifiAdmin.getWifiConfiguration();
						int level = Math
								.abs(((WifiManager) getSystemService(WIFI_SERVICE))
										.getConnectionInfo().getRssi());
						if (con_wifi != null && con_wifi.preSharedKey != null
								&& con_wifi.preSharedKey.equals("*"))
							iv_wifi_status
									.setImageResource(R.drawable.wifi_signal_lock);
						else
							iv_wifi_status
									.setImageResource(R.drawable.wifi_signal_nolock);
						iv_wifi_status.setImageLevel(level);

					} else {
						if (networkInfo.getDetailedState().ordinal() == 8) {
							rl_wifi_connect.setVisibility(View.GONE);
							getWifiListInfo();
							baseAdapter.notifyDataSetChanged();
						}
					}
				}
			} else if (intent.getAction().equals(
					WifiManager.RSSI_CHANGED_ACTION)) {
				int mWifiRssi = intent.getIntExtra(WifiManager.EXTRA_NEW_RSSI,
						-200);
				int level = Math.abs(mWifiRssi);
				iv_wifi_status.setImageLevel(level);
			}else if(intent.getAction().equals("refreshwifibutton")){
			  Message msg = new Message();
				msg.what = 1001;
				myhandler.sendMessage(msg);

			}

		}

	};

	/**
	 * 根据连接状态显示
	 */
	void showcon_wifi() {
		if (con_wifi != null) {
			rl_wifi_connect.setVisibility(View.VISIBLE);
			tv_wifi_connect_name.setText(con_wifi.SSID.substring(1,
					con_wifi.SSID.lastIndexOf('"')));
			tv_wifi_connect_info.setText(R.string.wifi_con);
			int level = Math.abs(((WifiManager) getSystemService(WIFI_SERVICE))
					.getConnectionInfo().getRssi());
			if (con_wifi.preSharedKey != null
					&& con_wifi.preSharedKey.equals("*"))
				iv_wifi_status.setImageResource(R.drawable.wifi_signal_lock);
			else
			iv_wifi_status.setImageResource(R.drawable.wifi_signal_nolock);
			iv_wifi_status.setImageLevel(level);

		} else
			rl_wifi_connect.setVisibility(View.GONE);

		getWifiListInfo();
		baseAdapter.notifyDataSetChanged();
	}

	String getSummary(DetailedState state) {
		String[] formats = getResources().getStringArray(R.array.wifi_status);
		int index = state.ordinal();

		if (index >= formats.length || formats[index].length() == 0) {
			return null;
		}
		return String.format(formats[index]);
	}

	OnItemClickListener itemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			// TODO Auto-generated method stub
			mPosition = position;
			mConScanResult = list.get(position);
			 configuration = mWifiAdmin
					.IsExsits(mConScanResult.SSID);
			if (configuration != null) {
				showPWSDialog(mConScanResult, true);
			} else {
				int security = getSecurity(mConScanResult);
				if (security != 0)
					showPWSDialog(mConScanResult, false);
				else {
					configuration = mWifiAdmin.CreateWifiInfo(
							mConScanResult.SSID, "", 0);
					con_wifi = configuration;
					showConinfo();
					boolean b = mWifiAdmin.addNetWork(configuration);

				}

			}

			// }
		}
	};
	int num = 0;
	Handler handler = new Handler();
	myHandler myhandler = new myHandler();
		private class  myHandler extends  Handler {
		// TODO Auto-generated constructor stub
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 1001:
			//	Toast.makeText(SettingWifiManager.this, "正在打开wifi...", Toast.LENGTH_LONG).show();
				break;

			default:
				break;
			}
		}
	}

	Runnable refreshRunnable = new Runnable() {
		public void run() {
			
			//getWifiListInfo();
				getWifiListInfo();
			baseAdapter.notifyDataSetChanged();
		 handler.postDelayed(this, 1000);
        tb_wifi_switch.setClickable(true);
		}
	};

	void showConinfo() {

		rl_wifi_connect.setVisibility(View.VISIBLE);
		String s = String.valueOf('"');
		if (!con_wifi.SSID.contains(s))
			tv_wifi_connect_name.setText(con_wifi.SSID);
		else
			tv_wifi_connect_name.setText(con_wifi.SSID.substring(1,
					con_wifi.SSID.lastIndexOf('"')));
		tv_wifi_connect_info.setText(R.string.wifi_conning);
	}

	public void showPWSDialog(ScanResult scanResult, boolean iscon) {

		View diaView = View.inflate(this, R.layout.wifi_pws_dialog, null);

		TextView tv_wifi_ssid = (TextView) diaView
				.findViewById(R.id.tv_wifi_ssid);
		tv_wifi_ssid.setText(scanResult.SSID);

		et_wifi_pwd = (EditText) diaView.findViewById(R.id.et_wifi_pwd);

		bt_wifi_pws_cancel = (Button) diaView
				.findViewById(R.id.bt_wifi_pws_cancel);
		bt_wifi_pws_cancel.setOnClickListener(clickListener);
		bt_wifi_pws_con = (Button) diaView.findViewById(R.id.bt_wifi_pws_con);
		bt_wifi_pws_con.setOnClickListener(clickListener);

		bt_wifi_pws_forget = (Button) diaView
				.findViewById(R.id.bt_wifi_pws_forget);
		bt_wifi_pws_forget.setOnClickListener(clickListener);
		
		if(iscon){
			et_wifi_pwd.setVisibility(View.GONE);
			bt_wifi_pws_forget.setVisibility(View.VISIBLE);
		}else{
			et_wifi_pwd.setVisibility(View.VISIBLE);
			bt_wifi_pws_forget.setVisibility(View.GONE);
		}

		dialog = new Dialog(this, R.style.WifiPwdDialog);
		dialog.setContentView(diaView);
		dialog.setCanceledOnTouchOutside(false);

		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.width = (int) (display.getWidth());
		dialog.getWindow().setAttributes(lp);

		dialog.show();
	}

	BaseAdapter baseAdapter = new BaseAdapter() {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (list == null)
				return 0;
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			mScanResult = list.get(position);
			LayoutInflater inflater = getLayoutInflater();
			LinearLayout layout = (LinearLayout) inflater.inflate(
					R.layout.wifi_scan_list_item, null);
			TextView tv_scan_wifi_name = (TextView) layout
					.findViewById(R.id.tv_scan_wifi_name);
			tv_scan_wifi_name.setText(mScanResult.SSID);

			ImageView iv_scan_wifi_status = (ImageView) layout
					.findViewById(R.id.iv_scan_wifi_status);

			if (getSecurity(mScanResult) == 0)
				iv_scan_wifi_status.setImageResource(R.drawable.wifi_signal_nolock);
			else
				iv_scan_wifi_status
						.setImageResource(R.drawable.wifi_signal_lock);

			iv_scan_wifi_status.setImageLevel(Math.abs(mScanResult.level));

			return layout;
		}

	};

	public int getSecurity(ScanResult result) {
		if (result.capabilities.contains("WEP")) {
			return 1;
		} else if (result.capabilities.contains("EAP")) {
			return 2;
		} else if (result.capabilities.contains("PSK")) {
			return 3;
		}
		return 0;
	}

	OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.wifi_title_back:
				finish();
				break;
			case R.id.bt_wifi_pws_cancel:
				dialog.cancel();
				
				break;
			case R.id.bt_wifi_pws_forget:
				mWifiAdmin.removeNetWork(configuration.networkId);
				dialog.cancel();
				break;
			case R.id.bt_wifi_pws_con:
					if(et_wifi_pwd.getText().toString().length()<8){
					Toast.makeText(SettingWifiManager.this,getResources().getString(R.string.wifi_pwd_length_less), Toast.LENGTH_SHORT).show();
					return ;
				}
				if (configuration == null) {
					configuration = mWifiAdmin.createNewWifiInfo(
							mConScanResult.SSID, et_wifi_pwd.getText()
									.toString(), getSecurity(mConScanResult));
				}
				con_wifi = configuration;
				showConinfo();
				boolean b = mWifiAdmin.addNetWork(configuration);
				dialog.cancel();
				break;

			default:
				break;
			}
		}
	};

	private void getWifiListInfo() {
		mWifiAdmin.startScan();
		List<ScanResult> tmpList = mWifiAdmin.getWifiList();
		if (tmpList == null) {
			list.clear();
		} else {
			list = tmpList;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mReceiver);
	}

}

