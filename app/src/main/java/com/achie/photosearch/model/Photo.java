package com.achie.photosearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photo {

    @JsonProperty("id")
    private String id;

    @JsonProperty("owner")
    private String owner;

    @JsonProperty("secret")
    private String secret;

    @JsonProperty("server")
    private String server;

    @JsonProperty("farm")
    private String farm;

    @JsonProperty("title")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl(char size) {
        // TODO validate for right size and throw exception if required
        StringBuilder builder = new StringBuilder("https://farm");
        builder.append(farm)
                .append(".staticflickr.com/")
                .append(server)
                .append('/')
                .append(id)
                .append('_')
                .append(secret)
                .append('_')
                .append(size)
                .append(".jpg");

        return builder.toString();
    }
}
