package com.example.testapplication.daggertut;

import android.util.Log;

import javax.inject.Inject;

@ActivityScope
public class Car {
    private static final String TAG = "Car";
    private Engine engine;
    private Wheel wheel;
    private Driver driver;

    @Inject
    public Car(Driver driver, Engine engine, Wheel wheel) {
        this.driver = driver;
        this.engine = engine;
        this.wheel = wheel;
    }

    void drive() {
        engine.start();
        Log.d(TAG, "The driver " + driver + " " + driver.name + " is driving " + this);
    }

    @Inject
    void setListener(Remote remote) {
        remote.setListener();
    }
}
