package com.example.andresmontoya.androidchat.login;

/**
 * Created by andres.montoya on 24/10/2016.
 */

public interface LoginPresenter {

    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);

}
