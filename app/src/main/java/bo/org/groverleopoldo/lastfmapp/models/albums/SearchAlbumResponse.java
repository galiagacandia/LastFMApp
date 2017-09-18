package bo.org.groverleopoldo.lastfmapp.models.albums;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class SearchAlbumResponse {
    private ResultAlbumSearch results;

    public ResultAlbumSearch getResults() {
        return results;
    }

    public void setResults(ResultAlbumSearch results) {
        this.results = results;
    }
}
