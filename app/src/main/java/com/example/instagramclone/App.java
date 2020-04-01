package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("RdsQy0ti78FX3eK2BKDU95sElQjlOiF1gUeLxfwU")
                // if defined
                .clientKey("KyRxL2Ha8NThfDqwlEFlyPHC5t1AgW28aoYFo4iT")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
