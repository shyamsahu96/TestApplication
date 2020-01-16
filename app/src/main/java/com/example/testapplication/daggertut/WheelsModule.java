package com.example.testapplication.daggertut;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class WheelsModule {

    @Provides
    static Rims provideRims() {
        return new Rims();
    }

    @Provides
    static Tires provideTires() {
        Tires tires = new Tires();
        tires.inflateTyres();
        return tires;
    }

    @Provides
    static Wheel provideWheels(Rims rims, Tires tires) {
        return new Wheel(rims, tires);
    }

}
