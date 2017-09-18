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
import bo.org.groverleopoldo.lastfmapp.models.albums.Album;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> implements View.OnClickListener{

    private List<Album> dataset;
    private View.OnClickListener listener;
    private Context context;

    public AlbumAdapter(List<Album> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Album album = dataset.get(position);

        Uri uri = Uri.parse(album.getImage("large").getUrl());
        holder.imageSimpleDraweeView.setImageURI(uri);
        holder.nameTextView.setText(album.getName());
        holder.artistTextView.setText(album.getArtist());
        holder.urlAlbumTextView.setText(album.getUrl());
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

        private SimpleDraweeView imageSimpleDraweeView;
        private TextView nameTextView;
        private TextView artistTextView;
        private TextView urlAlbumTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageSimpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.imageSimpleDraweeView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            artistTextView = (TextView) itemView.findViewById(R.id.artistTextView);
            urlAlbumTextView = (TextView) itemView.findViewById(R.id.urlAlbumTextView);
        }
    }
}
