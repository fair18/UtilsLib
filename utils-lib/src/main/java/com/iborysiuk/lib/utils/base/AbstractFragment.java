package com.iborysiuk.lib.utils.base;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.iborysiuk.lib.utils.R;
import com.iborysiuk.lib.utils.annotations.ConfigToolbar;
import com.iborysiuk.lib.utils.annotations.FragmentView;
import com.iborysiuk.lib.utils.utils.Navigator;

import java.lang.annotation.Annotation;

import static android.support.v7.widget.Toolbar.*;

/**
 * Created by Yuriy Borysiuk on 9/12/2016.
 */

public abstract class AbstractFragment extends Fragment {

    private Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        final Annotation annotation = getClass().getAnnotation(ConfigToolbar.class);
        if (annotation == null) return;

        final ConfigToolbar config = (ConfigToolbar) annotation;
        mToolbar = (Toolbar) getActivity().findViewById(config.id() != View.NO_ID
                ? config.id()
                : R.id.toolbar);
        if (mToolbar == null) return;

        //setup mToolbar configuration
        mToolbar.setTitle(config.title() != View.NO_ID ? config.title() : R.string.empty_title);
        mToolbar.inflateMenu(config.menu() != View.NO_ID ? config.menu() : R.menu.empty_menu);

    }

    @LayoutRes
    private int getLayout() {
        int layout = -1;
        final Annotation annotation = getClass().getAnnotation(FragmentView.class);
        if (annotation != null)
            layout = ((FragmentView) annotation).value();

        if (layout == View.NO_ID)
            throw new Resources.NotFoundException("Layout recourse not found");
        return layout;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        final Animation animation = Navigator.get().getFragmentAnimation(getContext(), enter);
        if (animation == null) return super.onCreateAnimation(transit, enter, nextAnim);
        return animation;
    }

    protected void addOnMenuItemClickListener(OnMenuItemClickListener listener) {
        if (mToolbar != null) mToolbar.setOnMenuItemClickListener(listener);

    }
}
