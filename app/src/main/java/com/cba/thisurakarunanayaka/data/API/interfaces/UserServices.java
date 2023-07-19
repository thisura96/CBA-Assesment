package com.cba.thisurakarunanayaka.data.API.interfaces;

import com.cba.thisurakarunanayaka.data.API.Model.UserRequest;
import com.cba.thisurakarunanayaka.data.API.Model.Response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserServices {
    @POST("/")
    Call<UserResponse> getUserResponse(@Body UserRequest signageRequest);
}
