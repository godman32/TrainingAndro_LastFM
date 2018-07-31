package gm32.retrofit_lastfm.adapter.album;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import gm32.retrofit_lastfm.MainActivity;
import gm32.retrofit_lastfm.R;
import gm32.retrofit_lastfm.retrofit.model.album.searchAlbum.Album;
import gm32.retrofit_lastfm.view.album.DetailAlbum;


public class SearchAlbumAdapter extends RecyclerView.Adapter<SearchAlbumAdapter.AlbumViewHolder> {

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Album> albums;
    private int rowLayout;
    private Context context;

    public SearchAlbumAdapter(List<gm32.retrofit_lastfm.retrofit.model.album.searchAlbum.Album> album, int rowLayout, Context context) {
        //convertAlbumToAlbumDetail(album);
        this.albums = album;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AlbumViewHolder holder, final int position) {
        //Log.d("Judul",albums.get(position).getName());
        Animation hyperspaceJump = AnimationUtils.loadAnimation(context, R.anim.coba2);
        holder.albumLayout.startAnimation(hyperspaceJump);

        holder.judul.setText(albums.get(position).getName());
        holder.artis.setText(albums.get(position).getArtist());
        holder.play_count.setText("Albums");

        if(!albums.get(position).getImage().get(Integer.valueOf(albums.get(position).getImage().size()-2)).getText().equals("")){
            Glide.with(context)
                    .load(albums.get(position).getImage().get(Integer.valueOf(albums.get(position).getImage().size()-2)).getText())
                    .into( holder.gambar);
        }
        else{
            Glide.with(context)
                    .load(R.mipmap.blackpink)
                    .into( holder.gambar);
        }

        holder.albumLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation hyperspaceJump = AnimationUtils.loadAnimation(context, R.anim.shake);
                holder.albumLayout.startAnimation(hyperspaceJump);

                Intent inten= new Intent(context, DetailAlbum.class);
                inten.putExtra("album",albums.get(position).getName());
                inten.putExtra("artist",albums.get(position).getArtist());
                context.startActivity(inten);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder{

        // inisialisai variable view
        LinearLayout albumLayout;
        TextView judul;
        TextView artis;
        TextView play_count;
        ImageView gambar;

        public AlbumViewHolder(View v) {

            super(v);
            // set variable view
            albumLayout= v.findViewById(R.id.album_layout);
            judul= v.findViewById(R.id.judul);
            artis= v.findViewById(R.id.penyanyi);
            play_count= v.findViewById(R.id.jumlah_putar);
            gambar = v.findViewById(R.id.gambar);
        }
    }

    /*public void convertAlbumToAlbumDetail(List<gm32.retrofit_lastfm.retrofit.model.album.searchAlbum.Album> album){
        Integer max=0;
        for (gm32.retrofit_lastfm.retrofit.model.album.searchAlbum.Album a: album){
            if (max >= 1){
                Log.d("Breaks","jalan");
                break;
            }
            else{
                HashMap<String, String> querry = new HashMap<>();
                querry.put("method", "album.getinfo");
                querry.put("album", a.getName());
                querry.put("artist", a.getArtist());
                querry.put("api_key", "51ea829189d51502e27b82fe7d0962be");
                querry.put("format", "json");


                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<DetailAlbum> call = apiService.getDetailAlbum(querry);
                call.enqueue(new Callback<DetailAlbum>() {
                    @Override
                    public void onResponse(Call<DetailAlbum> call, Response<DetailAlbum> response) {
                        albums.add(response.body().getAlbum());
                    }

                    @Override
                    public void onFailure(Call<DetailAlbum> call, Throwable t) {
                        Log.e(TAG, t.toString());
                    }
                });
            }
            max++;
        }
    }*/

}
