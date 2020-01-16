package com.example.testapplication;

import android.app.Application;

import com.example.testapplication.daggertut.AppComponent;
import com.example.testapplication.daggertut.DaggerAppComponent;
import com.example.testapplication.daggertut.DriverModule;

public class TestApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.factory().create(new DriverModule("Shyam"));
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
