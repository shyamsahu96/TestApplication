package com.example.testapplication.daggertut;

//Assume that they are from a Library

import android.util.Log;

public class Tires {

    private static final String TAG = "Tires";

    public Tires() {
    }

    void inflateTyres() {
        Log.d(TAG, "Tires have been inflated...");
    }
}
