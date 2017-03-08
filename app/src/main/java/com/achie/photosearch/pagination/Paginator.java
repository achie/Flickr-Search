package com.achie.photosearch.pagination;

public interface Paginator {

    void loadNext();

    boolean isLoading();

    boolean hasNext();

    int getPage();

    int getPageSize();

}