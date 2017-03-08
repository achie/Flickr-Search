package com.achie.photosearch.search;

import android.text.TextUtils;

import com.achie.photosearch.R;
import com.achie.photosearch.data.DataManager;
import com.achie.photosearch.model.Photo;
import com.achie.photosearch.model.SearchPhotosResponse;
import com.achie.photosearch.pagination.Paginator;
import com.achie.photosearch.pagination.RecyclerViewPaginationListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchPhotosPresenter implements SearchPhotosContract.Presenter, Paginator {

    private static final int DEFAULT_PAGE_SIZE = 50;
    private static final long QUERY_CHANGE_DELAY_IN_MILLIS = 1000;
    private static final long DELAY_NONE = 0;

    @Inject DataManager dataManager;

    private SearchPhotosContract.View view;

    private boolean isLoading;
    private int lastReturnedPage = 1;

    //TODO handle orientation changes
    private Subscription searchSubscription;
    private int itemsFromLastFetch;
    private String searchText = "";

    @Inject
    public SearchPhotosPresenter() {
    }

    @Override
    public void onCreate(SearchPhotosContract.View view) {
        this.view = view;
        this.view.initPhotosListView(new RecyclerViewPaginationListener(this));
    }

    @Override
    public void bindView(SearchPhotosContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        if (searchSubscription != null) {
            searchSubscription.unsubscribe();
        }
        this.view = null;
    }

    @Override
    public void onSearchTextChange(String newText) {
        if (!searchText.equals(newText)) {
            searchText = newText;
            fetchFirstPage(newText, QUERY_CHANGE_DELAY_IN_MILLIS);
        }
    }

    @Override
    public void loadNext() {
        fetchNextPage(searchText);
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public boolean hasNext() {
        return itemsFromLastFetch == DEFAULT_PAGE_SIZE;
    }

    @Override
    public int getPage() {
        return lastReturnedPage;
    }

    @Override
    public int getPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    private void fetchFirstPage(String query, long delayInMillis) {
        searchPhotos(query, 1, delayInMillis);
    }

    private void fetchNextPage(String query) {
        if (!isLoading) {
            searchPhotos(query, lastReturnedPage + 1, DELAY_NONE);
        }
    }

    private void searchPhotos(String query, int page, long delayInMillis) {
        isLoading = true;
        view.showProgress();
        if (searchSubscription != null) {
            searchSubscription.unsubscribe();
        }
        if (TextUtils.isEmpty(query)) {
            return; // Don't search when the query is empty
        }
        searchSubscription = dataManager
                .getPaginatedPhotos(query, page, DEFAULT_PAGE_SIZE)
                .delaySubscription(delayInMillis, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        photosResponse -> handlePhotosSearchResponse(photosResponse)
                );
    }

    private void handlePhotosSearchResponse(SearchPhotosResponse.PaginatedPhotos paginatedPhotos) {
        isLoading = false;
        List<Photo> photos = paginatedPhotos.getPhotos();
        lastReturnedPage = paginatedPhotos.getPage();
        itemsFromLastFetch = photos.size();
        if (view != null) {
            view.hideProgress();
            if (lastReturnedPage == 1) {
                view.setEmptyView(R.string.photos_search_empty_results, 0);
                view.clearPhotoList();
            }
            view.addPhotosToList(photos);
        }
    }

    private void handlePhotosSearchError(Throwable throwable) {
        isLoading = false;
        if (view == null) {
            return;
        }

        view.hideProgress();
        //TODO handle error
    }

    @Override
    public void onPhotoClick(Photo photo) {
        //TODO
    }
}
