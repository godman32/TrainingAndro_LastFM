package gm32.retrofit_lastfm.retrofit.model.track.searchView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchTrack {

    @SerializedName("results")
    @Expose
    private Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

}
