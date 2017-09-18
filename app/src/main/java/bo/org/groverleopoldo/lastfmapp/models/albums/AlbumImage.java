package bo.org.groverleopoldo.lastfmapp.models.albums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class AlbumImage {
    @SerializedName("#text")
    private String url;
    private String size;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
