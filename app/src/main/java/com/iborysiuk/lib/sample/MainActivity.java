package com.iborysiuk.lib.sample;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.iborysiuk.lib.utils.base.BaseActivity;


public class MainActivity extends BaseActivity {

    @NonNull
    @Override
    protected Fragment getRootFragment() {
        return MainFragment.newInstance();
    }
}
