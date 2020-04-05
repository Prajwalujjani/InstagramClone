package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("kpbEsiZqg67OV5WmNVUdFJV4syUHmEtYsKHngWqj")
                // if defined
                .clientKey("1XYARHsgW0IIooV91nsvL5O2ASyqTQzj2K40eYBY")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
