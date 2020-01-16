package com.example.testapplication.daggertut;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DieselEngineModule {

    @Binds
    public abstract Engine provideEngine(DieselEngine engine);

}