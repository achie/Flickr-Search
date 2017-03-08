package com.achie.photosearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchPhotosResponse {

    @JsonProperty("photos")
    private PaginatedPhotos paginatedPhotos;

    public PaginatedPhotos getPaginatedPhotos() {
        return paginatedPhotos;
    }

    public void setPaginatedPhotos(PaginatedPhotos paginatedPhotos) {
        this.paginatedPhotos = paginatedPhotos;
    }

    public static class PaginatedPhotos {

        @JsonProperty("page")
        private int page;

        @JsonProperty("pages")
        private int totalPages;

        @JsonProperty("perpage")
        private int perPage;

        @JsonProperty("total")
        private long total;

        @JsonProperty("stat")
        private String status;

        @JsonProperty("photo")
        private List<Photo> mPhotos;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<Photo> getPhotos() {
            return mPhotos;
        }

        public void setPhotos(List<Photo> photos) {
            mPhotos = photos;
        }
    }

}
