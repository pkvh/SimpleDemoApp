package com.shilpa.SimpleDemoApp.api;


import com.shilpa.SimpleDemoApp.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AppApiService {
    @FormUrlEncoded
    @POST("webservice/login/")
    Call<LoginResponse> authenticateUser(@Field("userName") String userName, @Field("password") String password);

}
