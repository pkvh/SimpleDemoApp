package com.shilpa.SimpleDemoApp.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.shilpa.SimpleDemoApp.application.DemoAppApplication;
import com.shilpa.SimpleDemoApp.model.LoginResponseData;

public class SharedPreferencesManager {

    private  static final String SCHOOL_APP_PREFERENCES = "school-app-preference";

    private static final String PREF_KEY_USER_LOGGED_IN = "user-logged-in";
    private static final String PREF_KEY_API_KEY = "api-key";
    private static final String PREF_KEY_USER_ID = "user-id";

    private SharedPreferences sharedPrefs;
    private static SharedPreferencesManager sharedPreferencesManagerInstance;
    private Context context;

    private SharedPreferencesManager(){
        context = DemoAppApplication.getContext();
        sharedPrefs = context.getApplicationContext().getSharedPreferences(SCHOOL_APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesManager getInstance(){
        if(sharedPreferencesManagerInstance == null)
            sharedPreferencesManagerInstance = new SharedPreferencesManager();
        return sharedPreferencesManagerInstance;
    }

    public boolean isLoggedIn(){
        return sharedPrefs.getBoolean(PREF_KEY_USER_LOGGED_IN, false);
    }

    public void setIsLoggedIn(boolean value){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(PREF_KEY_USER_LOGGED_IN, value);
        editor.apply();
    }

    public void setLoginData(LoginResponseData loginData){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(PREF_KEY_API_KEY, loginData.getApiKey());
        editor.putString(PREF_KEY_USER_ID, loginData.getUserId());
        editor.apply();
    }

    public void clearLoginData(){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(PREF_KEY_API_KEY, null);
        editor.putString(PREF_KEY_USER_ID, null);
        editor.apply();
    }

    public LoginResponseData getLoginData(){
        LoginResponseData  loginResponseData = new LoginResponseData();
        loginResponseData.setApiKey(sharedPrefs.getString(PREF_KEY_API_KEY, null));
        loginResponseData.setUserId(sharedPrefs.getString(PREF_KEY_USER_ID, null));
        return loginResponseData ;
    }

}
