package com.example.andresmontoya.androidchat.login;

/**
 * Created by andres.montoya on 24/10/2016.
 */

public interface LoginInteractor {
    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);

}
