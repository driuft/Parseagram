package com.example.parseagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        //Register parse models
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("dHp9L3P1Ak5x7vqF6fz3hUiK3SYVeRuxiwH2cbmF")
                .clientKey("FIFQEzBltqSe4qhdYlofworLT0Xn5U0aDTfJS39w")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
