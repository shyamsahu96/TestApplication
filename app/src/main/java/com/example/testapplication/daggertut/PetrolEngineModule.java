package com.example.testapplication.daggertut;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PetrolEngineModule {

    @Binds
    public abstract Engine provideEngine(PetrolEngine engine);

}
