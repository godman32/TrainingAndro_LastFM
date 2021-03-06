package gm32.retrofit_lastfm.retrofit.model.album.detailsAlbum;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import gm32.retrofit_lastfm.retrofit.model.album.detailsAlbum.Track;

public class Tracks {

    @SerializedName("track")
    @Expose
    private List<Track> track = null;

    public List<Track> getTrack() {
        return track;
    }

    public void setTrack(List<Track> track) {
        this.track = track;
    }

}