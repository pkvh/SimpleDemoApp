package com.shilpa.SimpleDemoApp.common;


import com.shilpa.SimpleDemoApp.model.LoginResponseData;
import com.shilpa.SimpleDemoApp.utils.SharedPreferencesManager;

public class DemoAppOperationHandler {

    private static DemoAppOperationHandler demoAppOperationHandler;
    private LoginResponseData loginResponseData;

    private SharedPreferencesManager sharedPreferencesManager;

    private DemoAppOperationHandler() {
        sharedPreferencesManager = SharedPreferencesManager.getInstance();
    }

    public static DemoAppOperationHandler getDemoAppOperationHandler() {
        return getInstance();
    }

    public static DemoAppOperationHandler getInstance() {
        if (demoAppOperationHandler == null) {
            demoAppOperationHandler = new DemoAppOperationHandler();
        }
        return demoAppOperationHandler;
    }


    public LoginResponseData getLoginResponseData() {
        if(loginResponseData == null){
            this.loginResponseData = sharedPreferencesManager.getLoginData();
        }
        return loginResponseData;
    }
    public void setLoginResponseData(LoginResponseData loginResponseData) {
        sharedPreferencesManager.setLoginData(loginResponseData);
        sharedPreferencesManager.setIsLoggedIn(true);
        this.loginResponseData = loginResponseData;
    }

    public void logout(){
        sharedPreferencesManager.clearLoginData();
        sharedPreferencesManager.setIsLoggedIn(false);
        this.loginResponseData = null;
    }


}
