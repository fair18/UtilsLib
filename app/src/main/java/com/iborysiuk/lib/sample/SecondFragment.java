package com.iborysiuk.lib.sample;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.iborysiuk.lib.utils.annotations.ConfigToolbar;
import com.iborysiuk.lib.utils.annotations.FragmentView;
import com.iborysiuk.lib.utils.base.AbstractFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yuriy Borysiuk on 9/13/2016.
 */
@FragmentView(R.layout.fragment_second)
@ConfigToolbar(title = R.string.title_second_fragment, menu = R.menu.drav2_menu)
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
        configMenu();
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

    private void configMenu() {
        addOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.id1) {
                    Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

}
