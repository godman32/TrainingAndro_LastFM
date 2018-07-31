package gm32.retrofit_lastfm.retrofit.model.album.searchAlbum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchAlbum {

    @SerializedName("results")
    private Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

}
