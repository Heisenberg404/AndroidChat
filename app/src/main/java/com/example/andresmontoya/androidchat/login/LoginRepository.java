package com.example.andresmontoya.androidchat.login;

/**
 * Created by andres.montoya on 24/10/2016.
 */

public interface LoginRepository {
    void signIn(String email, String password);
    void signUp(String email, String password);
    void checkSession();
}
