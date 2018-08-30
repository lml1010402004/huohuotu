package com.android.customewidget;

import android.widget.ImageView;

public class ViewHolder {
	
	public com.android.customewidget.MyImageView mimageview;
	public ImageView nameImage;
	
	public ImageView getMimageview() {
		return mimageview;
	}
	public void setMimageview(com.android.customewidget.MyImageView mimageview) {
		this.mimageview = mimageview;
	}
	public ImageView getNameImage() {
		return nameImage;
	}
	public void setNameImage(ImageView nameImage) {
		this.nameImage = nameImage;
	}
	
	

}
