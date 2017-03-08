package com.achie.flickrsearch;

import android.os.Bundle;

import com.achie.flickrsearch.base.UiActivity;

public class HomeActivity extends UiActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }
}
