package com.yihantaiduo.myanotation.yjhretrofit;

import com.yihantaiduo.myanotation.yjhretrofit.annotation.Field;
import com.yihantaiduo.myanotation.yjhretrofit.annotation.GET;
import com.yihantaiduo.myanotation.yjhretrofit.annotation.POST;
import com.yihantaiduo.myanotation.yjhretrofit.annotation.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * 记录请求类型 参数 地址
 */
public class ServiceMethod {
    private final HttpUrl baseUrl;
    private final Call.Factory callFactory;
    private final String httpMethod;
    private final YjhRetrofit retrofit;
    private final boolean hasBody;
    private final String relativeUrl;
    private FormBody.Builder formBodybuild;
    ParameterHandler[] parameterHandlers;
    HttpUrl.Builder urlBuild;

    public ServiceMethod(Builder builder) {
        baseUrl = builder.retrofit.baseUrl;
        callFactory = builder.retrofit.callFactory;
        httpMethod = builder.httpMethod;
        retrofit = builder.retrofit;
        hasBody = builder.hasBody;
        parameterHandlers = builder.parameterHandlers;
        relativeUrl = builder.relativeUrl;
        if (hasBody) {
            formBodybuild = new FormBody.Builder();
        }
    }

    public void addQuerParameter(String key,String value) {
        if (urlBuild == null){
            HttpUrl.Builder urlBuild = baseUrl.newBuilder(relativeUrl);
        }
        urlBuild.addQueryParameter(key,value);
    }

    public void addFileParameter(String key, String value) {
        formBodybuild.add(key,value);
    }

    public static class Builder {
        private final YjhRetrofit retrofit;
        private final Annotation[] methodAnnotations;
        private Annotation[][] parameterAnnotations;
        private String httpMethod;
        private String relativeUrl;
        ParameterHandler[] parameterHandlers;
        private boolean hasBody;

        public Builder(YjhRetrofit retrofit, Method method) {
//            获取方法上的所有注解
            this.retrofit = retrofit;
//            获得方法参数上的所有注解
            this.methodAnnotations = method.getAnnotations();
//            一个参数会有多个注解一个方法会有多个注解
            this.parameterAnnotations = method.getParameterAnnotations();
        }

        public ServiceMethod build() {
            /**
             * 1 解析方法上的注解 只处理POST 和GET
             */
            for (Annotation annotation : methodAnnotations) {
                if (annotation instanceof POST) {
//记录请求方式
                    this.httpMethod = "POST";
                    this.relativeUrl = ((POST) annotation).value();
                    this.hasBody = true;
                } else if (annotation instanceof GET) {
                    this.httpMethod = "GET";
                    this.relativeUrl = ((GET) annotation).value();
                    this.hasBody = false;
                }
            }
            /**
             * 解析方法参数的注解
             */
            int length = parameterAnnotations.length;
            parameterHandlers = new ParameterHandler[length];
            for (int i = 0; i < length; i++) {
//                一个参数上的所有注解
                Annotation[] parameterAnnotation = parameterAnnotations[i];
//                处理参数上的每个注解
                for (Annotation annotation : parameterAnnotation) {
                    if (annotation instanceof Field) {
                        if (hasBody) {
                            String value = ((Field) annotation).value();
                            parameterHandlers[i] = new ParameterHandler.FileParameteHander(value);
                        }
                    } else if (annotation instanceof Query) {
                        if (hasBody) {
                            throw new RuntimeException("不能使用在post请求");
                        }
                        String value = ((Query) annotation).value();
                        parameterHandlers[i] = new ParameterHandler.QueryParameterHander(value);
                    }
                }
            }
            return new ServiceMethod(this);
        }
    }

    public Object invoke(Object[] args) {
        /**
         * 1.处理请求的地址与参数
         */
        for (int i = 0; i < parameterHandlers.length; i++) {
            ParameterHandler parameterHandler = parameterHandlers[i];
            parameterHandler.apply(this,args[i].toString());
        }
        HttpUrl url;
        if (urlBuild == null){
            urlBuild = baseUrl.newBuilder(relativeUrl);
        }
        url = urlBuild.build();
        FormBody formBody =null;
        if (formBodybuild !=null){
            formBody = formBodybuild.build();
        }
        Request build = new Request.Builder().url(url).method(httpMethod, formBody).build();
        Call call = callFactory.newCall(build);
        return call;
    }
}
