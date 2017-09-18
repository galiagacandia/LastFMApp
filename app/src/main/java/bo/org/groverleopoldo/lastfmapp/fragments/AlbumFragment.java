package bo.org.groverleopoldo.lastfmapp.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import bo.org.groverleopoldo.lastfmapp.BuildConfig;
import bo.org.groverleopoldo.lastfmapp.R;
import bo.org.groverleopoldo.lastfmapp.adapters.AlbumAdapter;
import bo.org.groverleopoldo.lastfmapp.models.albums.SearchAlbumResponse;
import bo.org.groverleopoldo.lastfmapp.services.LastFMService;
import bo.org.groverleopoldo.lastfmapp.services.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment {

    private EditText albumSearchEditText;
    private Button albumSearchButton;
    private RecyclerView albumsRecyclerView;
    private ProgressBar progressBar;
    private View rootView;

    public AlbumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_album, container, false);

        albumSearchEditText = (EditText) rootView.findViewById(R.id.albumSearchEditText);
        albumSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO){
                    searchAlbum(albumSearchEditText.getText().toString().trim());
                }
                return true;
            }
        });

        albumSearchButton = (Button) rootView.findViewById(R.id.albumSearchButton);
        albumSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchAlbum(albumSearchEditText.getText().toString().trim());
            }
        });

        albumsRecyclerView = (RecyclerView) rootView.findViewById(R.id.albumsRecyclerView);
        albumsRecyclerView.setHasFixedSize(true);
        albumsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar = (ProgressBar) rootView.findViewById(R.id.albumsProgressBar);

        return rootView;
    }

    public void searchAlbum(String album){
        progressBar.setVisibility(View.VISIBLE);
        LastFMService service = ServiceGenerator.createService(LastFMService.class);
        Call<SearchAlbumResponse> call = service.searchAlbum(BuildConfig.LAST_FM_API_KEY, album);
        call.enqueue(new Callback<SearchAlbumResponse>() {
            @Override
            public void onResponse(Call<SearchAlbumResponse> call, Response<SearchAlbumResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    AlbumAdapter adapter = new AlbumAdapter(response.body().getResults().getAlbummatches().getAlbum(), getContext());
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TextView urlTextView = (TextView) view.findViewById(R.id.urlAlbumTextView);
                            Uri web = Uri.parse(urlTextView.getText().toString());
                            Intent intent = new Intent(Intent.ACTION_VIEW, web);
                            startActivity(intent);
                        }
                    });
                    albumsRecyclerView.setAdapter(adapter);
                } else {
                    mostrarMensaje("Ocurrio un problema al cargar los datos del servidor");
                }
            }

            @Override
            public void onFailure(Call<SearchAlbumResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                mostrarMensaje("Error: " + t.getMessage());
            }
        });
    }

    private void mostrarMensaje(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }

}
