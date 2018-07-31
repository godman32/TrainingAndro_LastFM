package gm32.retrofit_lastfm.retrofit.model.album.topAlbum;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Topalbums {

    @SerializedName("album")
    private List<Album> album = new ArrayList<>();
    @SerializedName("@attr")
    private Attr_ attr;

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

    public Attr_ getAttr() {
        return attr;
    }

    public void setAttr(Attr_ attr) {
        this.attr = attr;
    }

}
