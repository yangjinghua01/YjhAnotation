package com.yihantaiduo.myanotation.click.inject;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.yihantaiduo.myanotation.click.annotation.Click;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClickUtils {
    public static void ClickInject(Activity activity) throws InvocationTargetException, IllegalAccessException {
//        拿到反射的类
        Class<? extends Activity> aClass = activity.getClass();
//        获得反射类的所有方法
        Method[] methods = aClass.getMethods();
//        遍历方法数组
        for (int i = 0; i < methods.length; i++) {
//            如果该方法被@click修饰了
            if (methods[i].isAnnotationPresent(Click.class)){
//                拿到注解的值
                Click annotation = methods[i].getAnnotation(Click.class);
                int[] value = annotation.value();
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("TAH", "onClick: " );
                    }
                };
                int finalI = i;
                Object tag = Proxy.newProxyInstance(View.OnClickListener.class.getClassLoader(), new Class[]{View.OnClickListener.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Log.e("TAG", "invoke: "+method.getName() );
                        Log.e("TAG", "method: " + methods[finalI].getName());
                        return methods[finalI].invoke(activity,args);
                    }
                });

                for (int j = 0; j < value.length; j++) {
                    View viewById = activity.findViewById(value[j]);
                    Method method = null;
                    try {
                        method = viewById.getClass().getMethod("setOnClickListener", View.OnClickListener.class);
                        Log.e("TAG", "ClickInject: 循环遍历得到的"+method.getName() );
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    method.invoke(viewById,tag);
                }

            }
        }
    }
}
