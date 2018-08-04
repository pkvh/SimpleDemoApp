package com.shilpa.SimpleDemoApp.login;


import com.shilpa.SimpleDemoApp.base.View.BaseView;

public interface LoginView extends BaseView {
    void onValidationError(String errorType);
    void onValidationSuccess();
    void clearDataField();
}
