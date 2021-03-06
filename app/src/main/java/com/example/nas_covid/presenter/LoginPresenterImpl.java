package com.example.nas_covid.presenter;

import android.app.Activity;

import android.text.TextUtils;

import com.example.nas_covid.view.LoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;


public class LoginPresenterImpl implements LoginPresenter {
    private FirebaseAuth auth ;
    private LoginView loginView ;

    public LoginPresenterImpl(FirebaseAuth auth) {
        this.auth = auth;
    }
    @Override
    public void login(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            loginView.showValidationError("Email and password can't be empty");
        } else {
            loginView.setProgressVisibility(true);

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) loginView, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            loginView.setProgressVisibility(false);
                            if(!task.isSuccessful()) {
                                loginView.loginError();
                            } else {
                                loginView.loginSuccess();
                            }
                        }
                    });
        }
    }

    @Override
    public void checkLogin() {
        if (auth.getCurrentUser() != null)
            loginView.isLogin(true);
        else
            loginView.isLogin(false);
    }

    @Override
    public void attachView(LoginView view) {
        loginView = view ;
    }

    @Override
    public void detachView() {
        loginView = null ;
    }
}
