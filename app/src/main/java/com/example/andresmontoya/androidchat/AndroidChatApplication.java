package com.example.andresmontoya.androidchat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by andres.montoya on 24/10/2016.
 */

public class AndroidChatApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
