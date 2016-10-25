package com.example.andresmontoya.androidchat.login;

/**
 * Created by andres.montoya on 24/10/2016.
 */

public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();
    void handleSignUp();
    void handleSignIn();
    void navigateToMainScreen();
    void loginError(String error);
    void newUserSuccess();
    void newUserError(String error);

}
