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
import bo.org.groverleopoldo.lastfmapp.adapters.SongAdapter;
import bo.org.groverleopoldo.lastfmapp.models.songs.SearchSongResponse;
import bo.org.groverleopoldo.lastfmapp.services.LastFMService;
import bo.org.groverleopoldo.lastfmapp.services.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongsFragment extends Fragment {

    private EditText songSearchEditText;
    private Button songSearchButton;
    private RecyclerView songsRecyclerView;
    private ProgressBar songsProgressBar;
    private View rootView;

    public SongsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_songs, container, false);

        songSearchEditText = (EditText) rootView.findViewById(R.id.songSearchEditText);
        songSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO){
                    searchSong(songSearchEditText.getText().toString().trim());
                }
                return true;
            }
        });

        songSearchButton = (Button) rootView.findViewById(R.id.songSearchButton);
        songSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchSong(songSearchEditText.getText().toString().trim());
            }
        });

        songsRecyclerView = (RecyclerView) rootView.findViewById(R.id.songsRecyclerView);
        songsRecyclerView.setHasFixedSize(true);
        songsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        songsProgressBar = (ProgressBar) rootView.findViewById(R.id.songsProgressBar);

        return rootView;
    }

    public void searchSong(String song){
        songsProgressBar.setVisibility(View.VISIBLE);
        LastFMService service = ServiceGenerator.createService(LastFMService.class);
        Call<SearchSongResponse> call = service.searchSong(BuildConfig.LAST_FM_API_KEY, song);
        call.enqueue(new Callback<SearchSongResponse>() {
            @Override
            public void onResponse(Call<SearchSongResponse> call, Response<SearchSongResponse> response) {
                songsProgressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    SongAdapter adapter = new SongAdapter(response.body().getResults().getTrackmatches().getTrack(), getContext());
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TextView urlTextView = (TextView) view.findViewById(R.id.urlSongTextView);
                            Uri web = Uri.parse(urlTextView.getText().toString());
                            Intent intent = new Intent(Intent.ACTION_VIEW, web);
                            startActivity(intent);
                        }
                    });
                    songsRecyclerView.setAdapter(adapter);
                } else {
                    mostrarMensaje("Ocurrio un problema al cargar los datos del servidor");
                }
            }

            @Override
            public void onFailure(Call<SearchSongResponse> call, Throwable t) {
                songsProgressBar.setVisibility(View.GONE);
                mostrarMensaje("Error: " + t.getMessage());
            }
        });
    }

    private void mostrarMensaje(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }
}
