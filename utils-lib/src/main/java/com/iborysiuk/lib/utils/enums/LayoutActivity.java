package com.iborysiuk.lib.utils.enums;

import android.support.annotation.LayoutRes;

import com.iborysiuk.lib.utils.R;

/**
 * Created by Yuriy Borysiuk on 9/15/2016.
 */

public enum LayoutActivity {
    DEFAULT(R.layout.activity_fragment_container),
    TOOLBAR(R.layout.activity_fragment_toolbar_container),
    DRAWER(R.layout.activity_fragment_drawer_container);

    @LayoutRes
    private int layoutRes;

    LayoutActivity(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    @LayoutRes
    public int layout() {
        return layoutRes;
    }

}
