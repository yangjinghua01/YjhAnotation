package com.yihantaiduo.myanotation.inject;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class InjectUtils {
    public static void injectView(Activity activity) throws IllegalAccessException {
        Class<? extends Activity> cla = activity.getClass();
        Field[] declaredFields = cla.getDeclaredFields();
        for (Field declaredField : declaredFields) {
           if ( declaredField.isAnnotationPresent(InjectView.class)){
               InjectView annotation = declaredField.getAnnotation(InjectView.class);
               int id = annotation.value();
               View viewById = activity.findViewById(id);
               declaredField.setAccessible(true);
               declaredField.set(activity,viewById);
           }
        }
    }
}
