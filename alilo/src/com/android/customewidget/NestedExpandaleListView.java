package com.android.customewidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by liang on 2016/8/21.
 */
public class NestedExpandaleListView extends ExpandableListView {
    public NestedExpandaleListView(Context context,AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        //�����¼���ĸ߶ȴ��ݻ�ȥ
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
