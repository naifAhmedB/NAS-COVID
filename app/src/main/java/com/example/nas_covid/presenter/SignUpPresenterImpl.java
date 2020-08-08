package com.example.nas_covid.presenter;

import android.app.Activity;

import android.text.TextUtils;

import com.example.nas_covid.model.object.User;
import com.example.nas_covid.view.SignUpView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;

public class SignUpPresenterImpl implements SignUpPresenter {

    private SignUpView signUpView ;
    private FirebaseAuth auth ;
    private DatabaseReference reff;
    User user= new User();

    public SignUpPresenterImpl(FirebaseAuth auth) {
        this.auth = auth ;
    }

    @Override
    public void signUp(String email, String password) {
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ) {
            signUpView.showValidationError();
        } else {
            user.setEmail(email);
            user.setPassword(password);
            reff = FirebaseDatabase.getInstance().getReference();
            signUpView.setProgressVisibility(true);
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) signUpView, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            signUpView.setProgressVisibility(false);

                            if (!task.isSuccessful()) {
                                signUpView.signUpError();
                            } else {
                                reff.child("User").push().setValue(user);
                                signUpView.signUpSuccess();
                            }
                        }
                    });
        }
    }

    @Override
    public void attachView(SignUpView view) {
        signUpView = view ;
    }

    @Override
    public void detachView() {
        signUpView = null ;
    }
}
