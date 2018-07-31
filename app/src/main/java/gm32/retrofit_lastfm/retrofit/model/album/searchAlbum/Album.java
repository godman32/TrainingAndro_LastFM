package gm32.retrofit_lastfm.retrofit.model.album.searchAlbum;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import gm32.retrofit_lastfm.retrofit.model.album.Image;

public class Album {

    @SerializedName("name")
    private String name;
    @SerializedName("artist")
    private String artist;
    @SerializedName("url")
    private String url;
    @SerializedName("image")
    private List<Image> image = null;
    @SerializedName("streamable")
    private String streamable;
    @SerializedName("mbid")
    private String mbid;

    public String getName() {
        return name;
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
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

}
