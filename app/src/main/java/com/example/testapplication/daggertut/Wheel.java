package com.example.testapplication.daggertut;

import javax.inject.Inject;

public class Wheel {

    private Rims rims;
    private Tires tires;

    public Wheel(Rims rims, Tires tires) {
        this.rims = rims;
        this.tires = tires;
    }
}
