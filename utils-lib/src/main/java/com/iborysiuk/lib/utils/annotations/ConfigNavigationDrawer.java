package com.iborysiuk.lib.utils.annotations;

import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.view.Menu;
import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Yuriy Borysiuk on 9/15/2016.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConfigNavigationDrawer {

    /**
     * @return drawer view menu id
     */
    @MenuRes int value() default Menu.NONE;

    /**
     * @return header layout
     */
    @LayoutRes int headerLayout() default View.NO_ID;
}
