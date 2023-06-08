package com.yihantaiduo.myanotation.myself;

import android.util.Log;

import com.yihantaiduo.myanotation.inject.InjectView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class CheckImpl {
    public static void checkInputUti(Class c) throws IllegalAccessException {
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            if (method.isAnnotationPresent(checkInput.class)) {
                checkInput annotation = method.getAnnotation(checkInput.class);
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    String name = parameters[0].getName();
                    System.out.println(name);
                }

            }
        }//程序继续执行

    }
}
