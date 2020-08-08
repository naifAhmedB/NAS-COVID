package com.example.nas_covid.view;


import com.example.nas_covid.base.BaseView;

/**
 * Created by DhytoDev on 3/5/17.
 */

public interface SignUpView extends BaseView {
    void showValidationError();
    void signUpSuccess();
    void signUpError();
    void setProgressVisibility(boolean visibility);
}
