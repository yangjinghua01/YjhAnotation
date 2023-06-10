package com.yihantaiduo.myanotation.yjhretrofit;

import androidx.annotation.Keep;

public abstract class ParameterHandler {
    abstract void apply(ServiceMethod serviceMethod, String value);
    static class  QueryParameterHander extends ParameterHandler{
        String Key;

        public QueryParameterHander(String key) {
            Key = key;
        }

        @Override
        void apply(ServiceMethod serviceMethod, String value) {
            serviceMethod.addQuerParameter(Key,value);
        }
    }
    static class FileParameteHander extends ParameterHandler{
        String Key;
        public FileParameteHander(String key) {
            Key = key;
        }

        @Override
        void apply(ServiceMethod serviceMethod, String value) {
            serviceMethod.addFileParameter(Key,value);
        }
    }
}
