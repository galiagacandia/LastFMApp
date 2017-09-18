package bo.org.groverleopoldo.lastfmapp.services;

import bo.org.groverleopoldo.lastfmapp.models.albums.SearchAlbumResponse;
import bo.org.groverleopoldo.lastfmapp.models.artists.SearchArtistResponse;
import bo.org.groverleopoldo.lastfmapp.models.songs.SearchSongResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public interface LastFMService {

    @GET("?method=artist.search&format=json")
    Call<SearchArtistResponse> searchArtist(@Query("api_key") String apiKey, @Query("artist") String artist);

    @GET("?method=album.search&format=json")
    Call<SearchAlbumResponse> searchAlbum(@Query("api_key") String apiKey, @Query("album") String album);

    @GET("?method=track.search&format=json")
    Call<SearchSongResponse> searchSong(@Query("api_key") String apiKey, @Query("track") String song);
}
