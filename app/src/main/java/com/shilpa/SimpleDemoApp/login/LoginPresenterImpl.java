package com.shilpa.SimpleDemoApp.login;


import android.text.TextUtils;
import android.util.Log;

import com.shilpa.SimpleDemoApp.api.ApiService;
import com.shilpa.SimpleDemoApp.api.ApiServiceImpl;
import com.shilpa.SimpleDemoApp.api.InternetConnectionTestHelper;
import com.shilpa.SimpleDemoApp.api.NetworkTestHelper;
import com.shilpa.SimpleDemoApp.common.Constants;
import com.shilpa.SimpleDemoApp.common.DemoAppOperationHandler;
import com.shilpa.SimpleDemoApp.model.LoginResponse;


public class LoginPresenterImpl implements LoginPresenter {
    LoginView loginView;
    ApiService apiService;
    NetworkTestHelper networkTestHelper;
    InternetConnectionTestHelper internetConnectionTestHelper;
    DemoAppOperationHandler appOperationHandler;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        networkTestHelper = new NetworkTestHelper();
        internetConnectionTestHelper = new InternetConnectionTestHelper(networkTestHelper);
        apiService = new ApiServiceImpl(internetConnectionTestHelper);
        appOperationHandler = DemoAppOperationHandler.getDemoAppOperationHandler();
    }

    @Override
    public void requestLogin(String parentId, String password) {
        if (networkTestHelper.isNetworkConnected()) {
            apiService.authenticateUser(parentId, password, new ApiService.Callback<LoginResponse>() {
                @Override
                public void onSuccess(LoginResponse response) {
                    if (response.getStatus().equals("success")) {
                        Log.e("userId", response.getLoginResponseData().get(0).getUserId());
                        Log.e("apiKey", response.getLoginResponseData().get(0).getApiKey());
                        Log.e("status", response.getLoginResponseData().get(0).getStatus());
                        Log.e("message", response.getLoginResponseData().get(0).getMessage());
                        appOperationHandler.setLoginResponseData(response.getLoginResponseData().get(0));
                        loginView.onRequestSuccess();
                    } else {
                        Log.e("Failed", response.getLoginResponseData().get(0).getMessage());
                        loginView.onRequestFailure(response.getLoginResponseData().get(0).getMessage());
                    }
                }

                @Override
                public void onError(Throwable error) {
                    loginView.clearDataField();
                    loginView.onRequestFailure(error.getLocalizedMessage());
                }
            });
        } else {
            loginView.onRequestFailure(networkTestHelper.ERROR_INTERNET_NOT_CONNECTED);
        }
    }

    @Override
    public void validateLogin(String parentId, String password) {
        //TODO Implement other validation logic
        if (TextUtils.isEmpty(parentId.trim())) {
            loginView.onValidationError(Constants.ErrorType.EMPTY_PARENT_ID);
            return;
        } else if (TextUtils.isEmpty(password.trim())) {
            loginView.onValidationError(Constants.ErrorType.EMPTY_PASSWORD);
            return;
        } else {
            loginView.onValidationSuccess();

        }
    }
}
