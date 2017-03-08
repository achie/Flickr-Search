package com.achie.photosearch.pagination;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewPaginationListener extends RecyclerView.OnScrollListener {

    private Paginator paginator;

    public RecyclerViewPaginationListener(Paginator paginator) {
        this.paginator = paginator;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (!paginator.hasNext()) {
            return;
        }

        RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
        if (!(lm instanceof LinearLayoutManager)) {
            throw new IllegalArgumentException(lm.getClass().getName() + " is not supported");
        }
        int visibleItemCount = lm.getChildCount();
        int totalItemCount = lm.getItemCount();

        int firstVisibleIndex = ((LinearLayoutManager) lm).findFirstVisibleItemPosition();
        int lastVisibleIndex = firstVisibleIndex + visibleItemCount;

        int paginationTriggerIndex = totalItemCount - Math.round(paginator.getPageSize() / 4);
        boolean hasReachedTriggerIndex = lastVisibleIndex > paginationTriggerIndex;
        if (hasReachedTriggerIndex) {
            paginator.loadNext();
        }
    }

}
