package com.example.nas_covid.presenter;

import android.app.Activity;


import com.example.nas_covid.service.Utils;
import com.example.nas_covid.view.HomeView;
import com.example.nas_covid.view.activity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;


public class HomePresenterImpl implements HomePresenter {

    private FirebaseAuth auth ;
    private FirebaseAuth.AuthStateListener authListener ;
    private HomeView homeView ;
    private Activity context ;

    public HomePresenterImpl(FirebaseAuth auth, final Activity context) {
        this.auth = auth;
        this.context = context ;

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser() ;

                if (user == null) {
                    Utils.setIntent(context, LoginActivity.class);
                    context.finish();
                }
            }
        };
    }

    @Override
    public void getCurrentUser() {
        if (auth.getCurrentUser() != null)
            homeView.setUser(auth.getCurrentUser());

    }

    @Override
    public void signOut() {
        auth.signOut();
    }

    @Override
    public void onStart() {
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        auth.removeAuthStateListener(authListener);
    }

    @Override
    public void attachView(HomeView view) {
        homeView = view ;
    }

    @Override
    public void detachView() {
        homeView = null ;
    }
}
