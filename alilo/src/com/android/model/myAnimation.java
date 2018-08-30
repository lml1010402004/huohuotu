package com.android.model;

import android.widget.ImageView;

public class myAnimation {
	
	private ImageView mImageView ;
	private int[] mImageRes;
	private int[] durations;
	
	public myAnimation(ImageView pImageView,int[] pImageRes,int[] durations) {

		this.mImageRes = pImageRes;
		this.durations = durations;
		this.mImageView = pImageView;
        mImageView.setImageResource(mImageRes[1]);
    //    while(true){
        	play(1);
     //   }
		
	}
	
	private void play(final int pImageNo){
		mImageView.postDelayed(new Runnable() {
		public void run() {
		
				mImageView.setImageResource(mImageRes[pImageNo]);
				if(pImageNo==mImageRes.length-1){
					return;
				}else{
					play(pImageNo+1);
				}

			}
	}, durations[pImageNo-1]);
	}

}
