package com.shilpa.SimpleDemoApp.api;

import android.util.Log;

import com.shilpa.SimpleDemoApp.model.LoginResponse;

import retrofit2.Response;


public class ApiServiceImpl implements ApiService {
    private AppApiService appApiService;
    private InternetConnectionTestHelper internetConnectionTestHelper;

    public ApiServiceImpl(InternetConnectionTestHelper internetConnectionTestHelper){
        appApiService = ApiServiceBuilder.buildService(null, AppApiService.class);
        this.internetConnectionTestHelper = internetConnectionTestHelper;
    }

    @Override
    public void authenticateUser(String parentId, String password, final Callback<LoginResponse> callback ) {
        appApiService.authenticateUser(parentId, password).enqueue(new ErrorTransformingCallback<LoginResponse>(internetConnectionTestHelper) {
            @Override
            public void onSuccessResponse(Response<LoginResponse> response) {
                if(response.body() != null) {
                    callback.onSuccess(response.body());
                } else{
                    callback.onError(new Throwable(response.message()));
                }
                Log.e("LoginResponseData", response.body().getStatus());
            }

            @Override
            public void onErrorOccurred(Throwable throwable, Response<LoginResponse> response) {
                callback.onError(throwable);
            }

        });
    }
}
