package com.example.nas_covid.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.nas_covid.R;
import com.example.nas_covid.base.BaseActivity;
import com.example.nas_covid.presenter.LoginPresenter;
import com.example.nas_covid.presenter.LoginPresenterImpl;
import com.example.nas_covid.service.Utils;
import com.example.nas_covid.view.LoginView;
import com.google.firebase.auth.FirebaseAuth;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {

    private FirebaseAuth auth ;
    private LoginPresenter loginPresenter ;
    @BindView(R.id.progressBar)
    ProgressBar progressBar ;
    @BindView(R.id.email)
    EditText email ;
    @BindView(R.id.password)
    EditText password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance() ;
        loginPresenter = new LoginPresenterImpl(auth);

        loginPresenter.attachView(this);
        loginPresenter.checkLogin();
    }

    @OnClick(R.id.btn_login) void onLoginButtonClick() {
        String email_text = email.getText().toString().trim();
        String password_text = password.getText().toString().trim();
        loginPresenter.login(email_text, password_text);
    }

    @OnClick(R.id.btn_signup) void onSignUpButtonClick() {
        Utils.setIntent(this, SignUpActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }

    @Override
    public void showValidationError(String message) {
        Utils.showMessage(this, message);
    }

    @Override
    public void loginSuccess() {
        Utils.showMessage(this, "Login Success !");
        Utils.setIntent(this, HomeActivity.class);
    }

    @Override
    public void loginError() {
        Utils.showMessage(this, "Login Error ! ");
    }

    @Override
    public void setProgressVisibility(boolean visibility) {
        if (visibility)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void isLogin(boolean isLogin) {
        if (isLogin) {
            Utils.setIntent(this, HomeActivity.class);
            finish();
        }
    }
    @Override
    public Context getContext() {
        return this;
    }
}
