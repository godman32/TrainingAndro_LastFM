package gm32.retrofit_lastfm.retrofit.model.album.searchAlbum;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Albummatches {

    @SerializedName("album")
    private List<Album> album = null;

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

}