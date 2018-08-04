package com.shilpa.SimpleDemoApp.home;


import com.shilpa.SimpleDemoApp.common.DemoAppOperationHandler;

public class HomePresenterImpl implements HomePresenter {
    HomeView homeView;
    DemoAppOperationHandler appOperationHandler;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
        appOperationHandler = DemoAppOperationHandler.getDemoAppOperationHandler();
    }

    @Override
    public void callLogOut() {
      //  appOperationHandler.logout();
       // homeView.logOutFromApp();

    }
}
