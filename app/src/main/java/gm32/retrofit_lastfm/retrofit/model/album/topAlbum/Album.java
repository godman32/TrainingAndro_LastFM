package gm32.retrofit_lastfm.retrofit.model.album.topAlbum;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import gm32.retrofit_lastfm.retrofit.model.album.Artist;
import gm32.retrofit_lastfm.retrofit.model.album.Image;
import gm32.retrofit_lastfm.retrofit.model.album.topAlbum.Attr;

public class Album {

    @SerializedName("name")
    private String name;
    @SerializedName("playcount")
    private String playcount;
    @SerializedName("mbid")
    private String mbid;
    @SerializedName("url")
    private String url;
    @SerializedName("artist")
    private Artist artist;
    @SerializedName("image")
    private List<Image> image = null;
    @SerializedName("@attr")
    private Attr attr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

}