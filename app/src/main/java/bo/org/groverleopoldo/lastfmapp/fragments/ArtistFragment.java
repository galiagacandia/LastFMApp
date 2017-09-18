package bo.org.groverleopoldo.lastfmapp.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import bo.org.groverleopoldo.lastfmapp.adapters.ArtistAdapter;
import bo.org.groverleopoldo.lastfmapp.models.artists.SearchArtistResponse;
import bo.org.groverleopoldo.lastfmapp.services.LastFMService;
import bo.org.groverleopoldo.lastfmapp.services.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistFragment extends Fragment {

    private EditText artistSearchEditText;
    private Button artistSearchButton;
    private RecyclerView artistsRecyclerView;
    private ProgressBar progressBar;
    private View rootView;

    public ArtistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_artist, container, false);

        artistSearchEditText = (EditText) rootView.findViewById(R.id.artistSearchEditText);
        artistSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO){
                    searchArtist(artistSearchEditText.getText().toString().trim());
                }
                return true;
            }
        });

        artistSearchButton = (Button) rootView.findViewById(R.id.artistSearchButton);
        artistSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchArtist(artistSearchEditText.getText().toString().trim());
            }
        });

        artistsRecyclerView = (RecyclerView) rootView.findViewById(R.id.artistsRecyclerView);
        artistsRecyclerView.setHasFixedSize(true);
        artistsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        progressBar = (ProgressBar) rootView.findViewById(R.id.artistsProgressBar);

        return rootView;
    }

    public void searchArtist(String artist){
        progressBar.setVisibility(View.VISIBLE);
        LastFMService service = ServiceGenerator.createService(LastFMService.class);
        Call<SearchArtistResponse> call = service.searchArtist(BuildConfig.LAST_FM_API_KEY, artist);

        call.enqueue(new Callback<SearchArtistResponse>() {
            @Override
            public void onResponse(Call<SearchArtistResponse> call, Response<SearchArtistResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()){
                    ArtistAdapter adapter = new ArtistAdapter(response.body().getResults().getArtistmatches().getArtist(), getContext());
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TextView urlTextView = (TextView) view.findViewById(R.id.urlArtistTextView);
                            Uri web = Uri.parse(urlTextView.getText().toString());
                            Intent intent = new Intent(Intent.ACTION_VIEW, web);
                            startActivity(intent);
                        }
                    });
                    artistsRecyclerView.setAdapter(adapter);
                } else {
                    mostrarMensaje("Ocurrio un problema al cargar los datos del servidor");
                }
            }

            @Override
            public void onFailure(Call<SearchArtistResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                mostrarMensaje("Error: " + t.getMessage());
            }
        });
    }

    private void mostrarMensaje(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }
}
