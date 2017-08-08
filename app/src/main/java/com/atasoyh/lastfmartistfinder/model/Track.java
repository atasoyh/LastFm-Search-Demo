package com.atasoyh.lastfmartistfinder.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track implements LastFMDisplayableInterface{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("image")
    @Expose
    private List<Image> images = null;
    @SerializedName("mbid")
    @Expose
    private String mbid;

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


    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
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