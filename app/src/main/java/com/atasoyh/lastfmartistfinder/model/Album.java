package com.atasoyh.lastfmartistfinder.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Album implements LastFMDisplayableInterface {

    @JsonProperty("name")
    private String name;
    @JsonProperty("artist")
    private String artist;
    @JsonProperty("url")
    private String url;
    @JsonProperty("image")
    private List<Image> image = null;
    @JsonProperty("streamable")
    private String streamable;
    @JsonProperty("mbid")
    private String mbid;
    @JsonProperty("listeners")
    private String listeners;
    @JsonProperty("playcount")
    private String playcount;
    @JsonProperty("tags")
    private Tags tags;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
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

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("artist")
    public String getArtist() {
        return artist;
    }

    @JsonProperty("artist")
    public void setArtist(String artist) {
        this.artist = artist;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("image")
    public List<Image> getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(List<Image> image) {
        this.image = image;
    }

    @JsonProperty("streamable")
    public String getStreamable() {
        return streamable;
    }

    @JsonProperty("streamable")
    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    @JsonProperty("mbid")
    public String getMbid() {
        return mbid;
    }

    @JsonProperty("mbid")
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    @JsonProperty("listeners")
    public String getListeners() {
        return listeners;
    }

    @JsonProperty("listeners")
    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    @JsonProperty("playcount")
    public String getPlaycount() {
        return playcount;
    }

    @JsonProperty("playcount")
    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    @JsonProperty("tags")
    public Tags getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getLargeImageUrl() {
        return getImageURLForSize("large");
    }

    public String getMegaImageUrl() {
        return getImageURLForSize("mega");
    }

    private String getImageURLForSize(String sizeType) {
        if (image == null) return null;
        for (Image image : image) {
            if (sizeType.equals(image.getSize()))
                return image.getText();
        }
        return null;
    }

}