package com.example.nas_covid.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import com.example.nas_covid.R;
import com.example.nas_covid.base.BaseActivity;
import com.example.nas_covid.presenter.HomePresenterImpl;
import com.example.nas_covid.view.HomeView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements HomeView {

    private FirebaseAuth auth ;
    private HomePresenterImpl homePresenter ;

    @BindView(R.id.email_text)
    EditText emailText ;
    @BindView(R.id.username_text)
    EditText usernameText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        homePresenter = new HomePresenterImpl(auth, this);

        homePresenter.attachView(this);
        homePresenter.getCurrentUser() ;
    }

    @OnClick(R.id.sign_out_button) void signOutClick() {
        homePresenter.signOut();
    }

    @Override
    protected void onStart() {
        super.onStart();
        homePresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        homePresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.detachView();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setEnabled(boolean isEnabled) {

    }

    @Override
    public void setUser(FirebaseUser user) {
        emailText.setText(user.getEmail());
        usernameText.setText(user.getDisplayName());
    }
}
