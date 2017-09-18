package bo.org.groverleopoldo.lastfmapp.models.artists;

import java.util.List;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class Artist {
    private String name;
    private String listeners;
    private String url;
    private List<ArtistImage> image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ArtistImage> getImage() {
        return image;
    }

    public void setImage(List<ArtistImage> image) {
        this.image = image;
    }

    public ArtistImage getImage(String size){
        ArtistImage artistImage = this.image.get(0);
        if (this.image.size() > 0){
            for (ArtistImage image: this.image) {
                if (image.getSize().equalsIgnoreCase(size)){
                    return image;
                }
            }
        }
        return artistImage;
    }
}
