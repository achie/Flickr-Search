package com.achie.photosearch.search;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.achie.photosearch.PhotoSearchApp;
import com.achie.photosearch.R;
import com.achie.photosearch.base.UiActivity;
import com.achie.photosearch.di.AppComponent;
import com.achie.photosearch.di.DaggerUiComponent;
import com.achie.photosearch.di.UiComponent;
import com.achie.photosearch.di.UiModule;
import com.achie.photosearch.model.Photo;
import com.achie.photosearch.view.EmptySupportingRecyclerView;
import com.achie.photosearch.view.RecyclerItemDecoration;

import java.util.List;

import javax.inject.Inject;

public class SearchPhotosActivity extends UiActivity
        implements SearchPhotosContract.View, PhotosListAdapter.PhotoClickListener {

    @Inject SearchPhotosContract.Presenter presenter;

    private RecyclerView.LayoutManager layoutManager;
    private EmptySupportingRecyclerView recyclerView;
    private PhotosListAdapter photosListAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUiComponent().inject(this);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        recyclerView = (EmptySupportingRecyclerView) findViewById(R.id.recycler_view);
        photosListAdapter = new PhotosListAdapter(this, this);

        presenter.onCreate(this);
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

    @Override
    public void initPhotosListView(RecyclerView.OnScrollListener onScrollListener) {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerItemDecoration(this));
        recyclerView.setAdapter(photosListAdapter);
        recyclerView.addOnScrollListener(onScrollListener);
    }

    @Override
    public void clearPhotoList() {
        photosListAdapter.clearPhotos();
    }

    @Override
    public void setEmptyView(int descriptionResId, int iconResId) {
        View emptyView = getEmptyView(R.string.photos_search_empty_results, iconResId);
        recyclerView.setEmptyView(emptyView);
    }

    private View getEmptyView(int descriptionResId, int iconResId) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.empty_view_stub);
        View emptyView;
        if (viewStub != null) {
            emptyView = viewStub.inflate();
        } else {
            emptyView = findViewById(R.id.xorgs_empty_view);
        }

        if (emptyView != null) {
            TextView tvDescription = (TextView) emptyView.findViewById(R.id.empty_view_text);
            String description = getString(descriptionResId);
            if (tvDescription != null && !TextUtils.isEmpty(description)) {
                tvDescription.setText(description);
            }
        }

        return emptyView;
    }

    @Override
    public void addPhotosToList(List<Photo> photos) {
        photosListAdapter.addPhotos(photos);
    }

    @Override
    public void onPhotoClick(Photo photo) {
        presenter.onPhotoClick(photo);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    protected UiComponent initUiComponent() {
        AppComponent appComponent = ((PhotoSearchApp) getApplication()).getComponent();
        return DaggerUiComponent.builder()
                .appComponent(appComponent)
                .uiModule(new UiModule())
                .build();
    }
}
