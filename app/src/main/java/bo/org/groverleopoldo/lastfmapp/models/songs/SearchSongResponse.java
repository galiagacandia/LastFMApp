package bo.org.groverleopoldo.lastfmapp.models.songs;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class SearchSongResponse {
    private ResultSongSearch results;

    public ResultSongSearch getResults() {
        return results;
    }

    public void setResults(ResultSongSearch results) {
        this.results = results;
    }
}
