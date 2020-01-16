package com.example.testapplication.daggertut;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {WheelsModule.class, DieselEngineModule.class})
public interface CarComponent {

    Car getCar();

    void inject(DaggerTutActivity daggerTutActivity);

    @Subcomponent.Factory
    interface Factory {
        CarComponent create(@BindsInstance @Named("Horse Power") int horsePower, @BindsInstance @Named("Engine Capacity") int engineCapacity);
    }
}
