package com.shilpa.SimpleDemoApp.base.View;

public interface BaseView {

    void showLoading();
    void hideLoading();
    void showToast(String message);
    void showProgress();
    void hideProgress();

    void onRequestFailure(String message);
    void onRequestSuccess();
}
