package com.shilpa.SimpleDemoApp.login;


public interface LoginPresenter {
    void requestLogin(String parentId, String password);
    void validateLogin(String parentId, String password);
}
