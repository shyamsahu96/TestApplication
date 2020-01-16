package com.example.testapplication.daggertut;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

public class DieselEngine implements Engine {

    private static final String TAG = "DieselEngine";

    private int horsePower, engineCapacity;

    @Inject
    public DieselEngine(@Named("Horse Power") int horsePower, @Named("Engine Capacity") int engineCapacity) {
        this.horsePower = horsePower;
        this.engineCapacity = engineCapacity;
    }

    @Override
    public void start() {
        Log.d(TAG, "Diesel Engine Started... HorsePower : "
                + horsePower
                + "\n Engine Capacity: "
                + engineCapacity);
    }
}
