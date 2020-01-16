package com.example.testapplication.daggertut;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.testapplication.R;
import com.example.testapplication.TestApplication;

import javax.inject.Inject;

public class DaggerTutActivity extends AppCompatActivity {
    @Inject
    Car car1, car2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dagger_tut);
        CarComponent component = ((TestApplication) getApplication()).getAppComponent()
                .getCarComponentFactory().create(100, 150);
        component.inject(this);
        super.onCreate(savedInstanceState);

        car1.drive();
        car2.drive();
    }
}