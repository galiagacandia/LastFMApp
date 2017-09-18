package bo.org.groverleopoldo.lastfmapp.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bo.org.groverleopoldo.lastfmapp.R;
import bo.org.groverleopoldo.lastfmapp.models.songs.Song;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> implements View.OnClickListener {

    private List<Song> dataset;
    private View.OnClickListener listener;
    private Context context;

    public SongAdapter(List<Song> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Song song = dataset.get(position);

        Uri uri = Uri.parse(song.getImage("large").getUrl());
        holder.imageSongSimpleDraweeView.setImageURI(uri);
        holder.nameSongTextView.setText(song.getName());
        holder.artistSongTextView.setText(song.getArtist());
        holder.listenersSongTextView.setText(song.getListeners());
        holder.urlSongTextView.setText(song.getUrl());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView imageSongSimpleDraweeView;
        private TextView nameSongTextView;
        private TextView artistSongTextView;
        private TextView listenersSongTextView;
        private TextView urlSongTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageSongSimpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.imageSongSimpleDraweeView);
            nameSongTextView = (TextView) itemView.findViewById(R.id.nameSongTextView);
            artistSongTextView = (TextView) itemView.findViewById(R.id.artistSongTextView);
            listenersSongTextView = (TextView) itemView.findViewById(R.id.listenersSongTextView);
            urlSongTextView = (TextView) itemView.findViewById(R.id.urlSongTextView);
        }
    }
}
