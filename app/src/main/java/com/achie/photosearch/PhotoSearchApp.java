package com.achie.photosearch;

import android.app.Application;

import com.achie.photosearch.di.AppComponent;
import com.achie.photosearch.di.AppModule;
import com.achie.photosearch.di.DaggerAppComponent;

public class PhotoSearchApp extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(getBaseContext()))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }

}
