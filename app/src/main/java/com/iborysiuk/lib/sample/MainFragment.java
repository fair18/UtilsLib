package com.iborysiuk.lib.sample;

import android.os.Bundle;

import com.iborysiuk.lib.utils.annotations.ConfigLayoutView;
import com.iborysiuk.lib.utils.annotations.ConfigToolbar;
import com.iborysiuk.lib.utils.base.BaseFragment;

/**
 * Created by Yuriy Borysiuk on 9/13/2016.
 */
@ConfigLayoutView(R.layout.fragment_main)
@ConfigToolbar
public class MainFragment extends BaseFragment {

    public MainFragment() {

    }

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
