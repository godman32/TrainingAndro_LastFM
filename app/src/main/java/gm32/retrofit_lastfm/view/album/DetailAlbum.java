package gm32.retrofit_lastfm.view.album;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

import gm32.retrofit_lastfm.MainActivity;
import gm32.retrofit_lastfm.R;
import gm32.retrofit_lastfm.adapter.TrackAdapter;
import gm32.retrofit_lastfm.adapter.album.DetailAlbumAdapter;
import gm32.retrofit_lastfm.retrofit.ApiClient;
import gm32.retrofit_lastfm.retrofit.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAlbum extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    ApiInterface apiService;
    private final static String API_KEY = "51ea829189d51502e27b82fe7d0962be";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_album);

        findViewById(R.id.toolbar).setBackgroundColor(Color.TRANSPARENT);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        // inisialisasi variable
        recyclerView = findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        Intent intent= getIntent();

        HashMap<String, String> querry = new HashMap<>();
        querry.put("method", "album.getinfo");
        querry.put("album", intent.getStringExtra("album"));
        querry.put("artist", intent.getStringExtra("artist"));
        querry.put("api_key", API_KEY);
        querry.put("format", "json");



        Call<gm32.retrofit_lastfm.retrofit.model.album.detailsAlbum.DetailAlbum> call = apiService.getDetailAlbum(querry);
        call.enqueue(new Callback<gm32.retrofit_lastfm.retrofit.model.album.detailsAlbum.DetailAlbum>() {
            @Override
            public void onResponse(Call<gm32.retrofit_lastfm.retrofit.model.album.detailsAlbum.DetailAlbum> call,
                                   Response<gm32.retrofit_lastfm.retrofit.model.album.detailsAlbum.DetailAlbum> response) {

                //Log.d("Response", new GsonBuilder().setPrettyPrinting().create().toJson(response));
                CollapsingToolbarLayout col= findViewById(R.id.collapsingToolbar);
                col.setTitle(response.body().getAlbum().getName());
                Glide.with(getBaseContext())
                        .load(response.body().getAlbum().getImage().get(response.body().getAlbum().getImage().size()-1).getText())
                        .into((ImageView) findViewById(R.id.toolbarImage));

                recyclerView.setAdapter(new DetailAlbumAdapter(response.body().getAlbum().getTracks().getTrack(),
                        R.layout.track_item_list, getBaseContext()));
            }

            @Override
            public void onFailure(Call<gm32.retrofit_lastfm.retrofit.model.album.detailsAlbum.DetailAlbum> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
