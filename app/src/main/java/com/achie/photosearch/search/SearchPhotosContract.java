package com.achie.photosearch.search;

import android.support.v7.widget.RecyclerView;

import com.achie.photosearch.model.Photo;

import java.util.List;

public class SearchPhotosContract {


    public interface View {

        void showToast(int messageId);

        void initPhotosListView(RecyclerView.OnScrollListener onScrollListener);

        void clearPhotoList();

        void setEmptyView(int descriptionResId, int iconResId);

        void addPhotosToList(List<Photo> photos);

        void showProgress();

        void hideProgress();
    }

    public interface Presenter {

        void bindView(View homeView);

        void unbindView();

        void onSearchTextChange(String newText);

        void onCreate(SearchPhotosContract.View view);

        void onPhotoClick(Photo photo);
    }
}
