package gm32.retrofit_lastfm.retrofit;

import java.util.Map;

import gm32.retrofit_lastfm.retrofit.model.album.detailsAlbum.DetailAlbum;
import gm32.retrofit_lastfm.retrofit.model.album.searchAlbum.SearchAlbum;
import gm32.retrofit_lastfm.retrofit.model.album.topAlbum.TopAlbum;
import gm32.retrofit_lastfm.retrofit.model.track.searchView.SearchTrack;
import gm32.retrofit_lastfm.retrofit.model.track.topTrack.TopTrack;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface ApiInterface {
    @GET("/2.0/")
    Call<TopAlbum> getDataAlbum(@QueryMap Map<String, String> filters);

    @GET("/2.0/")
    Call<SearchAlbum> getSearchAlbum(@QueryMap Map<String, String> filters);

    @GET("/2.0/")
    Call<DetailAlbum> getDetailAlbum(@QueryMap Map<String, String> filters);

    @GET("/2.0/")
    Call<TopTrack> getDataTopTrack(@QueryMap Map<String, String> filters);

    @GET("/2.0/")
    Call<SearchTrack> getDataSearchTrack(@QueryMap Map<String, String> filters);
}
