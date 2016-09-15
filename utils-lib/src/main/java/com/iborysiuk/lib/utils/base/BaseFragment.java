package com.iborysiuk.lib.utils.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.iborysiuk.lib.utils.annotations.FragmentView;
import com.iborysiuk.lib.utils.utils.Navigator;

import java.lang.annotation.Annotation;

/**
 * Created by Yuriy Borysiuk on 9/12/2016.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
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
}
