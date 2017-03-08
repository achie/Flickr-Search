package com.achie.photosearch.search;

import com.achie.photosearch.data.DataManager;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchPhotosPresenter implements SearchPhotosContract.Presenter {

    @Inject DataManager dataManager;

    private SearchPhotosContract.View view;
    private Subscription mSubscription;

    @Inject
    public SearchPhotosPresenter() {
    }

    @Override
    public void bindView(SearchPhotosContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Override
    public void onSearchTextChange(String newText) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mSubscription = dataManager.getPaginatedPhotos(newText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }
}
