package com.achie.photosearch.search;

public class SearchPhotosContract {


    public interface View {

        void showToast(int messageId);

    }

    public interface Presenter {

        void bindView(View homeView);

        void unbindView();

        void onSearch(String text);

        void onSearchTextChange(String newText);
    }
}
