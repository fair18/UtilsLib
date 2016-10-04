package com.iborysiuk.lib.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.iborysiuk.lib.utils.annotations.ConfigToolbar;
import com.iborysiuk.lib.utils.annotations.FragmentView;
import com.iborysiuk.lib.utils.base.AbstractFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yuriy Borysiuk on 9/13/2016.
 */
@FragmentView(R.layout.fragment_second)
@ConfigToolbar(title = R.string.title_second_fragment)
public class SecondFragment extends AbstractFragment {

    @BindView(R.id.textView)
    TextView textView;

    public SecondFragment() {

    }

    public static SecondFragment newInstance() {

        Bundle args = new Bundle();

        SecondFragment fragment = new SecondFragment();
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Hello me");
            }
        }, 500);
    }
}
