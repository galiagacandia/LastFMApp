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
import bo.org.groverleopoldo.lastfmapp.models.artists.Artist;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> implements View.OnClickListener{

    private List<Artist> dataset;
    private View.OnClickListener listener;
    private Context context;

    public ArtistAdapter(List<Artist> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Artist artist = dataset.get(position);

        Uri uri = Uri.parse(artist.getImage("large").getUrl());
        holder.imageSimpleDraweeView.setImageURI(uri);
        holder.nameTextView.setText(artist.getName());
        holder.urlArtistTextView.setText(artist.getUrl());
        holder.listenersTextView.setText(artist.getListeners());
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
        private TextView urlArtistTextView;
        private TextView listenersTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageSimpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.imageSimpleDraweeView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            urlArtistTextView = (TextView) itemView.findViewById(R.id.urlArtistTextView);
            listenersTextView = (TextView) itemView.findViewById(R.id.listenersTextView);

        }
    }
}
