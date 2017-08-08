package com.atasoyh.lastfmartistfinder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Album implements LastFMDisplayableInterface {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image")
    @Expose
    private List<Image> images = null;
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("playcount")
    @Expose
    private String playcount;
    @SerializedName("tracks")
    @Expose
    private Tracks tracks;
    @SerializedName("tags")
    @Expose
    private Tags tags;
    @SerializedName("wiki")
    @Expose
    private Wiki wiki;

    public String getName() {
        return name;
    }

    @Override
    public String getArtistName() {
        return artist;
    }

    @Override
    public String getImageUrl() {
        return getLargeImageUrl();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Image> getImage() {
        return images;
    }

    public void setImage(List<Image> image) {
        this.images = image;
    }


    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getLargeImageUrl() {
        return getImageURLForSize("large");
    }

    public String getMegaImageUrl() {
        return getImageURLForSize("mega");
    }

    private String getImageURLForSize(String sizeType) {
        if (images == null) return null;
        for (Image image : images) {
            if (sizeType.equals(image.getSize()))
                return image.getText();
        }
        return null;
    }

}