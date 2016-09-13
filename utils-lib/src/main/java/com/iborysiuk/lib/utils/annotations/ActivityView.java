package com.iborysiuk.lib.utils.annotations;

import android.support.annotation.LayoutRes;
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
public @interface ActivityView {

    /**
     * @return final value of recourse layout
     */
    @LayoutRes int value() default View.NO_ID;
}
