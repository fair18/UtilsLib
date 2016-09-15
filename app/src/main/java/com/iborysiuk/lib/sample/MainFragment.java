package com.iborysiuk.lib.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.iborysiuk.lib.utils.annotations.ConfigToolbar;
import com.iborysiuk.lib.utils.annotations.FragmentView;
import com.iborysiuk.lib.utils.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yuriy Borysiuk on 9/13/2016.
 */
@FragmentView(R.layout.fragment_main)
@ConfigToolbar
public class MainFragment extends BaseFragment {

    @BindView(R.id.textView)
    TextView textView;

    public MainFragment() {

    }

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
