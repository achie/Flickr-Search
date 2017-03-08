package com.achie.photosearch.search;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.achie.photosearch.PhotoSearchApp;
import com.achie.photosearch.R;
import com.achie.photosearch.base.UiActivity;
import com.achie.photosearch.di.AppComponent;
import com.achie.photosearch.di.DaggerUiComponent;
import com.achie.photosearch.di.UiComponent;
import com.achie.photosearch.di.UiModule;

import javax.inject.Inject;

public class SearchPhotosActivity extends UiActivity
        implements SearchPhotosContract.View {

    @Inject SearchPhotosContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUiComponent().inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_photos, menu);
        initSearchView(menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initSearchView(Menu menu) {
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(
                menu.findItem(R.id.action_search_photos));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.onSearchTextChange(newText);
                return true;
            }
        });
    }

    protected UiComponent initUiComponent() {
        AppComponent appComponent = ((PhotoSearchApp) getApplication()).getComponent();
        return DaggerUiComponent.builder()
                .appComponent(appComponent)
                .uiModule(new UiModule())
                .build();
    }
}
