package com.iborysiuk.lib.utils.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.iborysiuk.lib.utils.R;
import com.iborysiuk.lib.utils.annotations.ActivityView;
import com.iborysiuk.lib.utils.annotations.ConfigNavigationDrawer;
import com.iborysiuk.lib.utils.annotations.ConfigToolbar;
import com.iborysiuk.lib.utils.utils.Navigator;

import java.lang.annotation.Annotation;

import static com.iborysiuk.lib.utils.enums.LayoutActivity.DEFAULT;
import static com.iborysiuk.lib.utils.enums.LayoutActivity.DRAWER;
import static com.iborysiuk.lib.utils.enums.LayoutActivity.TOOLBAR;

/**
 * Created by Yuriy Borysiuk on 9/12/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @LayoutRes
    private int MAIN_LAYOUT_RES;

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;


    public BaseActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        MAIN_LAYOUT_RES = getLayout();
        if (DRAWER.layout() == MAIN_LAYOUT_RES) setTheme(R.style.AppTheme_TranslucentStatus);
        Navigator.init(getSupportFragmentManager(), R.id.container);

        super.onCreate(savedInstanceState);
        setContentView(MAIN_LAYOUT_RES != View.NO_ID ? MAIN_LAYOUT_RES : DEFAULT.layout());
        setupToolbarOrNavDrawer(MAIN_LAYOUT_RES);

        Navigator.get().initFragment(getRootFragment());
    }

    @NonNull
    protected abstract Fragment getRootFragment();

    @Override
    public void finish() {
        Navigator.remove();
        super.finish();
    }

    @Override
    public void onBackPressed() {
        if (!Navigator.get().isEmpty()) Navigator.get().goBack();
        else super.onBackPressed();
    }

    @LayoutRes
    private int getLayout() {
        final Annotation annotation = getClass().getAnnotation(ActivityView.class);
        return annotation == null ? DEFAULT.layout() : ((ActivityView) annotation).value();
    }

    private void setupToolbarOrNavDrawer(@LayoutRes int layoutRes) {
        if (layoutRes == TOOLBAR.layout()) {
            initToolbar();

        } else if (layoutRes == DRAWER.layout()) {
            initToolbar();
            initDrawer();
        }
    }

    private void initToolbar() {
        final Annotation annotation = getClass().getAnnotation(ConfigToolbar.class);
        if (annotation == null) return;

        final ConfigToolbar config = (ConfigToolbar) annotation;
        mToolbar = (Toolbar) findViewById(config.id() != View.NO_ID
                ? config.id()
                : R.id.toolbar);
        if (mToolbar == null) return;

        //setup mToolbar configuration
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(config.title() != View.NO_ID ? config.title() : R.string.empty_title);
        mToolbar.setNavigationIcon(getNavigationIcon(config.hasArrow()));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (config.hasArrow()) onBackPressed();
            }
        });
    }

    private void initDrawer() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(mDrawerToggle);
        setupDrawerContent(initNavigationView());
    }

    private NavigationView initNavigationView() {
        mNavigationView = (NavigationView) findViewById(R.id.nvView);
        final Annotation annotation = getClass().getAnnotation(ConfigNavigationDrawer.class);
        if (annotation == null) return mNavigationView;

        final ConfigNavigationDrawer config = (ConfigNavigationDrawer) annotation;
        if (config.value() != Menu.NONE)
            mNavigationView.inflateMenu(config.value());

        if (config.headerLayout() != View.NO_ID)
            mNavigationView.inflateHeaderView(config.headerLayout());

        return mNavigationView;
    }


    @DrawableRes
    private int getNavigationIcon(boolean hasArrow) {
        return !hasArrow ? R.drawable.ic_menu : R.drawable.ic_arrow_back;
    }

    private void setupDrawerContent(@NonNull NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        menuItem.setChecked(true);
                        mDrawer.closeDrawers();
                        return true;
                    }
                });
    }

    protected void selectDrawerItem(@NonNull MenuItem menuItem) {
    }

    protected NavigationView getNavigationView() {
        return mNavigationView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle != null)
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mDrawerToggle != null) mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        if (mDrawerToggle != null) mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
    }
}
