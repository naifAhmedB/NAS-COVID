package com.example.nas_covid.presenter;


import com.example.nas_covid.base.BasePresenter;
import com.example.nas_covid.view.LoginView;

public interface LoginPresenter extends BasePresenter<LoginView> {
    void login(String email, String password);
    void checkLogin();
}
