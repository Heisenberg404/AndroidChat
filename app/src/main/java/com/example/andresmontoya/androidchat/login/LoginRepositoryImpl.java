package com.example.andresmontoya.androidchat.login;

import android.util.Log;

import com.example.andresmontoya.androidchat.domain.FirebaseHelper;

/**
 * Created by andres.montoya on 24/10/2016.
 */

public class LoginRepositoryImpl implements LoginRepository {
    private FirebaseHelper helper;

    public LoginRepositoryImpl(){
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void signIn(String email, String password) {

        Log.e("LoginRepositoryImpl", "signIn");
    }

    @Override
    public void signUp(String email, String password) {

        Log.e("LoginRepositoryImpl", "signUp");
    }

    @Override
    public void checkSession() {
        Log.e("LoginRepositoryImpl", "check session");
    }

    
}
