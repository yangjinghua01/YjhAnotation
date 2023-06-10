package com.yihantaiduo.myanotation.api;



import com.yihantaiduo.myanotation.yjhretrofit.annotation.Field;
import com.yihantaiduo.myanotation.yjhretrofit.annotation.GET;
import com.yihantaiduo.myanotation.yjhretrofit.annotation.POST;
import com.yihantaiduo.myanotation.yjhretrofit.annotation.Query;

import okhttp3.Call;

public interface YjhWratherApi {
    @POST("/v3/weather/weatherInfo")
    Call postWeather(@Field("city") String city, @Field("key") String key);


    @GET("/v3/weather/weatherInfo")
    Call getWeather(@Query("city") String city, @Query("key") String key);
}
