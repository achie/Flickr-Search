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

    @JsonProperty("ispublic")
    private String isPublic;

    @JsonProperty("isfriend")
    private String isFriend;

    @JsonProperty("isfamily")
    private String isFamily;

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

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(String isFriend) {
        this.isFriend = isFriend;
    }

    public String getIsFamily() {
        return isFamily;
    }

    public void setIsFamily(String isFamily) {
        this.isFamily = isFamily;
    }
}
