package com.achie.photosearch.data;

import com.achie.photosearch.api.FlickrApi;
import com.achie.photosearch.model.SearchPhotosResponse;

import rx.Observable;

public class DataManager {

    private FlickrApi flickrApi;

    public DataManager(FlickrApi flickrApi) {
        this.flickrApi = flickrApi;
    }

    public Observable<SearchPhotosResponse.PaginatedPhotos> getPaginatedPhotos(
            String searchText, int page, int perPage) {
        return flickrApi.searchPhotos(searchText, page, perPage)
                .flatMap(response -> Observable.just(response.getPaginatedPhotos()));
    }

}
