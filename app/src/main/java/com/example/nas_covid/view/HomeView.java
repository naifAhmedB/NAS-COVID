package com.example.nas_covid.view;

import com.example.nas_covid.base.BaseView;
import com.google.firebase.auth.FirebaseUser;


public interface HomeView extends BaseView {
    void setEnabled(boolean isEnabled);
    void setUser(FirebaseUser user);
}
