package gm32.retrofit_lastfm.view.album;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.HashMap;

import gm32.retrofit_lastfm.MainActivity;
import gm32.retrofit_lastfm.R;
import gm32.retrofit_lastfm.adapter.album.AlbumAdapter;
import gm32.retrofit_lastfm.adapter.album.SearchAlbumAdapter;
import gm32.retrofit_lastfm.retrofit.ApiClient;
import gm32.retrofit_lastfm.retrofit.ApiInterface;
import gm32.retrofit_lastfm.retrofit.model.album.searchAlbum.SearchAlbum;
import gm32.retrofit_lastfm.retrofit.model.album.topAlbum.TopAlbum;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AlbumView extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    SearchView searchView;
    ApiInterface apiService;
    private final static String API_KEY = "51ea829189d51502e27b82fe7d0962be";

    // TODO - insert your themoviedb.org API KEY here


    public AlbumView() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_album_view, container, false);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        // inisialisasi variable
        recyclerView = view.findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        searchView= view.findViewById(R.id.searchView);

        //set view albums
        setViewTopTrack();

        // set event search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                // set querry for api
                HashMap<String, String> querry = new HashMap<>();
                querry.put("method", "album.search");
                querry.put("album", s);
                querry.put("api_key", API_KEY);
                querry.put("format", "json");

                // check search word
                if(!s.equals("")){

                    // get data searchalbum from api
                    Call<SearchAlbum> call = apiService.getSearchAlbum(querry);
                    call.enqueue(new Callback<SearchAlbum>() {
                        @Override
                        public void onResponse(Call<SearchAlbum> call, Response<SearchAlbum> response) {
                            //Log.d("RESPONSE", new GsonBuilder().setPrettyPrinting().create().toJson(response));

                            // set view
                            // set adapter
                            recyclerView.setAdapter(new SearchAlbumAdapter(response.body().getResults().getAlbummatches().getAlbum(),
                                    R.layout.album_item_list, getContext()));
                        }

                        @Override
                        public void onFailure(Call<SearchAlbum> call, Throwable t) {
                            Log.e(TAG, t.toString());
                        }
                    });
                }
                else{

                    // set view albums to top track
                    setViewTopTrack();
                }


                return false;
            }
        });


        return  view;
    }

    public void setViewTopTrack(){
        HashMap<String, String> querry = new HashMap<>();
        querry.put("method", "artist.gettopalbums");
        querry.put("artist", "cher");
        querry.put("api_key", API_KEY);
        querry.put("format", "json");

        Call<TopAlbum> call = apiService.getDataAlbum(querry);
        call.enqueue(new Callback<TopAlbum>() {
            @Override
            public void onResponse(Call<TopAlbum> call, Response<TopAlbum> response) {
                int statusCode = response.code();
                recyclerView.setAdapter(new AlbumAdapter(response.body().getTopalbums().getAlbum(),
                        R.layout.album_item_list, getContext()));
            }

            @Override
            public void onFailure(Call<TopAlbum> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
