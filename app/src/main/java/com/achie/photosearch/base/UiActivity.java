package com.achie.photosearch.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.achie.photosearch.R;

public abstract class UiActivity extends AppCompatActivity {

    private Toolbar activityTitleBar;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutResId() != 0) {
            setContentView(getLayoutResId());
            initToolbar();
        }
    }

    @Override
    protected void onDestroy() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        super.onDestroy();
    }

    /**
     * Return the Resource ID of the layout or 0 if a layout shouldn't be set using the
     * {@code setContentView()} method in {@code onCreate()}.
     *
     * @return the Resource ID of the layout or 0 if a layout shouldn't be set
     */
    protected int getLayoutResId() {
        return 0;
    }

    protected void initToolbar() {
        if (usesDefaultToolbar()) {
            activityTitleBar = (Toolbar) findViewById(R.id.toolbar_main);
            setSupportActionBar(activityTitleBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(showUpNavigation());
        }
    }

    /**
     * Returns true if the default toolbar is used. If toolbar is not present return false.
     * The default is set to true and the child classes may override this method if
     * the toolbar is being customized.
     *
     * @return true if the default toolbar is used or else false.
     */
    protected boolean usesDefaultToolbar() {
        return true;
    }

    protected boolean showUpNavigation() {
        return false;
    }

    public void showProgressDialog(boolean isCancelable) {
        showProgressDialog(R.string.loading, isCancelable);
    }

    public void showProgressDialog(@StringRes int messageResId, boolean isCancelable) {
        showProgressDialog(0, messageResId, isCancelable);
    }

    public void showProgressDialog(@StringRes int titleResId,
                                   @StringRes int messageResId, boolean isCancelable) {
        String title = titleResId != 0 ? getString(titleResId) : null;
        String message = messageResId != 0 ? getString(messageResId) : null;
        progressDialog = ProgressDialog.show(this, title, message, true, isCancelable);
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void showToast(int resourceId) {
        showToast(getString(resourceId));
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
