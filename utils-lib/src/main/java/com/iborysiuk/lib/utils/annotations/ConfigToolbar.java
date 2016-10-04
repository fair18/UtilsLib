package com.iborysiuk.lib.utils.annotations;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Yuriy Borysiuk on 9/12/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConfigToolbar {

    /**
     * @return toolbar recourse id
     */
    @IdRes int id() default View.NO_ID;

    /**
     * @return toolbar title string recourse id
     */
    @StringRes int title() default View.NO_ID;


}
