package bo.org.groverleopoldo.lastfmapp.models.albums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class ResultAlbumSearch {
    @SerializedName("opensearch:totalResults")
    private String totalResults;
    private ListAlbums albummatches;

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ListAlbums getAlbummatches() {
        return albummatches;
    }

    public void setAlbummatches(ListAlbums albummatches) {
        this.albummatches = albummatches;
    }
}
