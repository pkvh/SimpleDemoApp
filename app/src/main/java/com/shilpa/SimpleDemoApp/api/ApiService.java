package com.shilpa.SimpleDemoApp.api;


import com.shilpa.SimpleDemoApp.model.LoginResponse;

public interface ApiService {
    void authenticateUser(String parentId, String password,Callback<LoginResponse> callback);


    interface Callback<T>{
        void onSuccess(T response);
        void onError(Throwable error);
    }
}
