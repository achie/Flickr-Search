package com.achie.photosearch.di;

import android.content.Context;

import com.achie.photosearch.data.DataManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Context getApplicationContext();

    DataManager getDataManager();

    ObjectMapper getObjectMapper();

}
