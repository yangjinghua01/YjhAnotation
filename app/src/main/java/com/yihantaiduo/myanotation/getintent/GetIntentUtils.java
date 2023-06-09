package com.yihantaiduo.myanotation.getintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * intent 跳转接受参数
 */
public class GetIntentUtils {
    public static void getintentUtils(Activity activity){
        Class<? extends Activity> aClass = activity.getClass();
        Intent intent = activity.getIntent();
        Bundle extras = activity.getIntent().getExtras();
        if (extras == null){
            return;
        }
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(GetIntent.class)){
                GetIntent annotation = declaredField.getAnnotation(GetIntent.class);
                String key = annotation.key();
                Object o = extras.get(key);
                if (extras.containsKey(key)){
//                    获得数组元素类型
                    Class<?> componentType = declaredField.getType().getComponentType();
//                    当前属性是数组并且是parcelable的子类
                    if (declaredField.getType().isArray()&& Parcelable.class.isAssignableFrom(componentType)){
                        Object[] objs = (Object[]) o;
                       Object[] objects = Arrays.copyOf(objs,objs.length,(Class<? extends Object[]>)declaredField.getType());
                       objs = objects;
                    }
                    declaredField.setAccessible(true);
                    try{
                        declaredField.set(activity,o);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
