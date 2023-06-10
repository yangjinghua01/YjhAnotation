package com.yihantaiduo.myanotation.yjhretrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.HttpUrl;

public class YjhRetrofit {
    final Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();
    final Call.Factory callFactory;
    final HttpUrl baseUrl;

    public YjhRetrofit(Call.Factory callFactory, HttpUrl baseUrl) {
        this.callFactory = callFactory;
        this.baseUrl = baseUrl;
    }
    public <T> T create(final Class<T> service){
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //解析这个method上的所有注解信息
                ServiceMethod serviceMethod = loadSerViceMethod(method);
                return serviceMethod.invoke(args);
            }
        });
    }

    private ServiceMethod loadSerViceMethod(Method method){
        ServiceMethod result = serviceMethodCache.get(method);
        if (result!=null){
            return result;
        }
        synchronized (serviceMethodCache) {
            serviceMethodCache.get(method);
            if (result == null) {
                result = new ServiceMethod.Builder(this, method).build();
                serviceMethodCache.put(method, result);
            }

        }
        return result;
    }
}
