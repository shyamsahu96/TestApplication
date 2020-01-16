package com.example.testapplication.daggertut;

import android.util.Log;

import javax.inject.Inject;

public class Remote {
    private static final String TAG = "Remote";

    @Inject
    public Remote() {
    }

    void setListener() {
        Log.d(TAG, "Listening on remote");
    }
}