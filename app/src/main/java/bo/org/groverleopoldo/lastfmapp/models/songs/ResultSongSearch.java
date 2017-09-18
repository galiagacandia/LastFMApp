package bo.org.groverleopoldo.lastfmapp.models.songs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class ResultSongSearch {
    @SerializedName("opensearch:totalResults")
    private String totalResults;
    private ListSong trackmatches;

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ListSong getTrackmatches() {
        return trackmatches;
    }

    public void setTrackmatches(ListSong trackmatches) {
        this.trackmatches = trackmatches;
    }
}
