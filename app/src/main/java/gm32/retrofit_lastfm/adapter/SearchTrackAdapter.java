package gm32.retrofit_lastfm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import gm32.retrofit_lastfm.R;
import gm32.retrofit_lastfm.retrofit.model.track.searchView.Track;


public class SearchTrackAdapter  extends RecyclerView.Adapter<SearchTrackAdapter.TrackViewHolder> {

    private List<Track> track;
    private int rowLayout;
    private Context context;

    public SearchTrackAdapter(List<Track> track, int rowLayout, Context context) {
        this.track = track;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, final int position) {
        //Log.d("Judul",albums.get(position).getName());
        holder.judul.setText(track.get(position).getName());
        holder.artis.setText(track.get(position).getArtist());

        if(!track.get(position).getImage().get(Integer.valueOf(track.get(position).getImage().size()-2)).getText().equals("")){
            Glide.with(context)
                    .load(track.get(position).getImage().get(Integer.valueOf(track.get(position).getImage().size()-2)).getText())
                    .into( holder.gambar);
        }
        else{
            Glide.with(context)
                    .load(R.mipmap.blackpink)
                    .into( holder.gambar);
        }
    }

    @Override
    public int getItemCount() {
        return track.size();
    }

    public static class TrackViewHolder extends RecyclerView.ViewHolder{
        LinearLayout albumLayout;
        TextView judul;
        TextView artis;
        TextView play_count;
        ImageView gambar;

        public TrackViewHolder(View v) {
            super(v);
            albumLayout= v.findViewById(R.id.album_layout);
            judul= v.findViewById(R.id.judul);
            artis= v.findViewById(R.id.penyanyi);
            play_count= v.findViewById(R.id.jumlah_putar);
            gambar = v.findViewById(R.id.gambar);
        }
    }
}
