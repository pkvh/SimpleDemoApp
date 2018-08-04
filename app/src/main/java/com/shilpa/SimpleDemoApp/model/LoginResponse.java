package com.shilpa.SimpleDemoApp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    private String status;
    @SerializedName("message")
    private List<LoginResponseData> loginResponseData = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LoginResponseData> getLoginResponseData() {
        return loginResponseData;
    }
    public void setLoginResponseData(List<LoginResponseData> loginResponseData) {
        this.loginResponseData = loginResponseData;
    }

}
