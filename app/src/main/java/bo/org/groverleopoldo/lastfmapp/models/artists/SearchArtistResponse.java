package bo.org.groverleopoldo.lastfmapp.models.artists;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class SearchArtistResponse {
    private ResultArtistSearch results;

    public ResultArtistSearch getResults() {
        return results;
    }

    public void setResults(ResultArtistSearch results) {
        this.results = results;
    }
}
