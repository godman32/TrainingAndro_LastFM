package gm32.retrofit_lastfm.retrofit.model.album.topAlbum;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopAlbum {

    @SerializedName("topalbums")
    @Expose
    private Topalbums topalbums;

    public Topalbums getTopalbums() {
        return topalbums;
    }

    public void setTopalbums(Topalbums topalbums) {
        this.topalbums = topalbums;
    }

}
