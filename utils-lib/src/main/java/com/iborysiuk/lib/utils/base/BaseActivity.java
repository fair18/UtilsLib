package com.iborysiuk.lib.utils.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iborysiuk.lib.utils.R;
import com.iborysiuk.lib.utils.annotations.ConfigLayoutView;
import com.iborysiuk.lib.utils.utils.Navigator;

import java.lang.annotation.Annotation;

/**
 * Created by Yuriy Borysiuk on 9/12/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public BaseActivity() {
        Navigator.init(getSupportFragmentManager(), R.id.container);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        Navigator.get().initFragment(getRootFragment());
    }

    @NonNull
    protected abstract Fragment getRootFragment();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Navigator.remove();
    }

    @Override
    public void onBackPressed() {
        if (!Navigator.get().isEmpty()) Navigator.get().goBack();
        else super.onBackPressed();
    }

    private void setContentView() {
        final int defaultLayout = R.layout.activity_fragment_layout;
        final Annotation annotation = getClass().getAnnotation(ConfigLayoutView.class);
        if (annotation == null)
            setContentView(defaultLayout);
        else {
            int layout = ((ConfigLayoutView) annotation).value();
            setContentView(layout != View.NO_ID ? layout : defaultLayout);
        }
    }
}
