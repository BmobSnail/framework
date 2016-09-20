package me.bugsrain.test1.ui;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by quan on 16/9/19.
 */

public class MyRefreshView extends PtrClassicFrameLayout {
    public MyRefreshView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRefreshView(Context context) {
        super(context);
    }
}
