package gm32.retrofit_lastfm.retrofit.model.album.topAlbum;


import com.google.gson.annotations.SerializedName;

public class Attr {

    @SerializedName("rank")
    private String rank;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
