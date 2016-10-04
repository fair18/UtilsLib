package com.iborysiuk.lib.sample;

import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.iborysiuk.lib.utils.annotations.ActivityView;
import com.iborysiuk.lib.utils.annotations.ConfigNavigationDrawer;
import com.iborysiuk.lib.utils.annotations.ConfigToolbar;
import com.iborysiuk.lib.utils.base.AbstractActivity;
import com.iborysiuk.lib.utils.utils.Navigator;

@ActivityView(R.layout.activity_fragment_drawer_container)
@ConfigToolbar
@ConfigNavigationDrawer(value = R.menu.drav1_menu, headerLayout = R.layout.nav_header)
public class MainActivity extends AbstractActivity {

    @NonNull
    @Override
    protected Fragment getRootFragment() {
        return MainFragment.newInstance();
    }

    @Override
    protected void selectDrawerItem(@NonNull MenuItem menuItem) {
        @IdRes int itemId = menuItem.getItemId();
        if (itemId == Menu.NONE) return;

        final Class fragmentClass;
        switch (itemId) {
            case R.id.main_fragment:
                fragmentClass = MainFragment.class;
                break;
            case R.id.second_fragment:
                fragmentClass = SecondFragment.class;
                break;
            default:
                fragmentClass = MainFragment.class;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Navigator.get().initFragment((Fragment) fragmentClass.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 300);
    }
}
