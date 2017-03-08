package com.achie.photosearch.di;

import com.achie.photosearch.search.SearchPhotosActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {UiModule.class})
public interface UiComponent {

    void inject(SearchPhotosActivity activity);

}
