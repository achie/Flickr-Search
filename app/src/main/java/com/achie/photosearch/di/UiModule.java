package com.achie.photosearch.di;

import com.achie.photosearch.search.SearchPhotosPresenter;
import com.achie.photosearch.search.SearchPhotosContract;

import dagger.Module;
import dagger.Provides;

@Module
public class UiModule {

    @Provides
    SearchPhotosContract.Presenter provideSearchPhotosPresenter(SearchPhotosPresenter presenter) {
        return presenter;
    }

}
