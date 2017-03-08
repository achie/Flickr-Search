package com.achie.photosearch.api;

import com.achie.photosearch.model.SearchPhotosResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FlickrApi {

    String API_KEY = "222d18b4826d974fb67f60e98f4c62fd";
    String URL_SEARCH = "/services/rest/?" +
            "method=flickr.photos.search&format=json&nojsoncallback=1&" +
            "api_key=" + API_KEY;

    String PARAM_SEARCH_TEXT = "text";
    String PARAM_PAGE = "page";
    String PARAM_PER_PAGE = "per_page";

    @GET(URL_SEARCH)
    Observable<SearchPhotosResponse> searchPhotos(
            @Query(PARAM_SEARCH_TEXT) String searchText,
            @Query(PARAM_PAGE) int page,
            @Query(PARAM_PER_PAGE) int perPage
    );
}
