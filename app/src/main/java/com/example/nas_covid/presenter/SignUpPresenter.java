package com.example.nas_covid.presenter;


import com.example.nas_covid.base.BasePresenter;
import com.example.nas_covid.view.SignUpView;

public interface SignUpPresenter extends BasePresenter<SignUpView> {
    void signUp(String email, String password);
}
