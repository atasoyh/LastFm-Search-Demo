
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


public class Artist  implements LastFMDisplayableInterface  {

    @JsonProperty("name")
    private String name;
    @JsonProperty("listeners")
    private String listeners;
    @JsonProperty("mbid")
    private String mbid;
    @JsonProperty("url")
    private String url;
    @JsonProperty("streamable")
    private String streamable;
    @JsonProperty("image")
    private List<Image> image = null;
    @JsonProperty("ontour")
    private String ontour;
    @JsonProperty("stats")
    private Stats stats;
    @JsonProperty("similar")
    private Similar similar;
    @JsonProperty("tags")
    private Tags tags;
    @JsonProperty("bio")
    private Bio bio;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @Override
    public String getArtistName() {
        return name;
    }

    @Override
    public String getImageUrl() {
        return getLargeImageUrl();
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("listeners")
    public String getListeners() {
        return listeners;
    }

    @JsonProperty("listeners")
    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    @JsonProperty("mbid")
    public String getMbid() {
        return mbid;
    }

    @JsonProperty("mbid")
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("streamable")
    public String getStreamable() {
        return streamable;
    }

    @JsonProperty("streamable")
    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    @JsonProperty("image")
    public List<Image> getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(List<Image> image) {
        this.image = image;
    }

    @JsonProperty("ontour")
    public String getOntour() {
        return ontour;
    }

    @JsonProperty("ontour")
    public void setOntour(String ontour) {
        this.ontour = ontour;
    }

    @JsonProperty("stats")
    public Stats getStats() {
        return stats;
    }

    @JsonProperty("stats")
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @JsonProperty("similar")
    public Similar getSimilar() {
        return similar;
    }

    @JsonProperty("similar")
    public void setSimilar(Similar similar) {
        this.similar = similar;
    }

    @JsonProperty("tags")
    public Tags getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    @JsonProperty("bio")
    public Bio getBio() {
        return bio;
    }

    @JsonProperty("bio")
    public void setBio(Bio bio) {
        this.bio = bio;
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
