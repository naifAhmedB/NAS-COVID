package com.example.nas_covid.presenter;

import com.example.nas_covid.base.BasePresenter;
import com.example.nas_covid.view.HomeView;

/**
 * Created by DhytoDev on 3/21/17.
 */

public interface HomePresenter extends BasePresenter<HomeView> {
    void getCurrentUser();
    void signOut();
    void onStart();
    void onStop();
}
