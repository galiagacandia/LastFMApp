package bo.org.groverleopoldo.lastfmapp.models.artists;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class ResultArtistSearch {
    @SerializedName("opensearch:totalResults")
    private String totalResults;
    private ListArtists artistmatches;

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ListArtists getArtistmatches() {
        return artistmatches;
    }

    public void setArtistmatches(ListArtists artistmatches) {
        this.artistmatches = artistmatches;
    }
}
