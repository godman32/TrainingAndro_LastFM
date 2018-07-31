package gm32.retrofit_lastfm.view.track;

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
import gm32.retrofit_lastfm.adapter.SearchTrackAdapter;
import gm32.retrofit_lastfm.adapter.TrackAdapter;
import gm32.retrofit_lastfm.retrofit.ApiClient;
import gm32.retrofit_lastfm.retrofit.ApiInterface;
import gm32.retrofit_lastfm.retrofit.model.track.searchView.SearchTrack;
import gm32.retrofit_lastfm.retrofit.model.track.topTrack.TopTrack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TrackView extends Fragment {

    // inisialisasi variable
    private static final String TAG = MainActivity.class.getSimpleName();
    SearchView searchView;
    RecyclerView recyclerView;
    ApiInterface apiService;

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "51ea829189d51502e27b82fe7d0962be";

    public TrackView() {
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
        final View view= inflater.inflate(R.layout.fragment_track_view, container, false);

        // set variable
        recyclerView =  view.findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        apiService = ApiClient.getClient().create(ApiInterface.class);
        searchView= view.findViewById(R.id.searchView);

        // set event search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if(s.equals("")){
                    setViewTopTrack();
                }
                else {

                    // set Querry for api
                    HashMap<String, String> params = new HashMap<>();
                    params.put("method", "track.search");
                    params.put("track", s);
                    params.put("api_key", API_KEY);
                    params.put("format", "json");


                    // get data from api
                    Call<SearchTrack> call = apiService.getDataSearchTrack(params);
                    call.enqueue(new Callback<SearchTrack>() {
                        @Override
                        public void onResponse(Call<SearchTrack> call, Response<SearchTrack> response) {

                            // set adapter for view
                            recyclerView.setAdapter(new SearchTrackAdapter(response.body().getResults().getTrackmatches().getTrack(),
                                    R.layout.track_item_list, getContext()));
                        }

                        @Override
                        public void onFailure(Call<SearchTrack> call, Throwable t) {
                            Log.e(TAG, t.toString());
                        }
                    });
                }

                return false;
            }
        });

        setViewTopTrack();

        return view;
    }


    public void setViewTopTrack(){
        // set Querry for api
        HashMap<String, String> params = new HashMap<>();
        params.put("method", "geo.gettoptracks");
        params.put("country", "indonesia");
        params.put("api_key", API_KEY);
        params.put("format", "json");


        // get data from api
        Call<TopTrack> call = apiService.getDataTopTrack(params);
        call.enqueue(new Callback<TopTrack>() {
            @Override
            public void onResponse(Call<TopTrack> call, Response<TopTrack> response) {

                // set adapter for view
                recyclerView.setAdapter(new TrackAdapter(response.body().getTracks().getTrack(),
                        R.layout.track_item_list, getContext()));

            }

            @Override
            public void onFailure(Call<TopTrack> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

}
