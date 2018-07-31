package gm32.retrofit_lastfm.retrofit.model.track.topTrack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopTrack {

    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

}