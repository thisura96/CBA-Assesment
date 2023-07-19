package com.cba.thisurakarunanayaka.data.API;

import com.cba.thisurakarunanayaka.data.API.interfaces.UserServices;
import com.cba.thisurakarunanayaka.utilities.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS);

        OkHttpClient okHttpClient = builder.addInterceptor(httpLoggingInterceptor).build();
        //OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static UserServices getUserServices(){
        UserServices userServices = getRetrofit().create(UserServices.class);
        return userServices;
    }
}
