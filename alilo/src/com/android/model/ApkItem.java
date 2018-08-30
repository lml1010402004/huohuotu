package com.android.model;

public class ApkItem {
	int apkImage;
	int apkName;
	boolean islocked ;
	int position;
	int apkbackgroud;
	
	public int getApkbackgroud() {
		return apkbackgroud;
	}
	public void setApkbackgroud(int apkbackgroud) {
		this.apkbackgroud = apkbackgroud;
	}
	
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public boolean isIslocked() {
		return islocked;
	}
	public void setIslocked(boolean islocked) {
		this.islocked = islocked;
	}
	public int getApkImage() {
		return apkImage;
	}
	public void setApkImage(int apkImage) {
		this.apkImage = apkImage;
	}
	public int getApkName() {
		return apkName;
	}
	public void setApkName(int apkName) {
		this.apkName = apkName;
	}

}
