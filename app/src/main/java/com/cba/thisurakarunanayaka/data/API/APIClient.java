package com.cba.thisurakarunanayaka.data.API;

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
//        builder.connectTimeout(30, TimeUnit.SECONDS) // connect timeout
//                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
//                .readTimeout(30, TimeUnit.SECONDS); // read timeout

        // need to implement the try catch for api calls when get a time out exception

        builder.writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS);

        OkHttpClient okHttpClient = builder.addInterceptor(httpLoggingInterceptor).build();
        //OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }
}
