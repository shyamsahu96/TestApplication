package com.example.testapplication.daggertut;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DriverModule.class)
public interface AppComponent {

    //Driver getDriver(); //This method is needed if there was a dependency on this module.
    // Subcomponents can access the objects from the Modules mentioned in the component itself.

    CarComponent.Factory getCarComponentFactory();//Arguments should be Module names if they have argument constructors present in them.

    @Component.Factory
    interface Factory {
        AppComponent create(DriverModule driverModule);
    }
}
