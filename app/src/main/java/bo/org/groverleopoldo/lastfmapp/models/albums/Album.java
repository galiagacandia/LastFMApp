package bo.org.groverleopoldo.lastfmapp.models.albums;

import java.util.List;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class Album {
    private String name;
    private String artist;
    private String url;
    private List<AlbumImage> image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<AlbumImage> getImage() {
        return image;
    }

    public void setImage(List<AlbumImage> image) {
        this.image = image;
    }

    public AlbumImage getImage(String size){
        AlbumImage albumImage = this.image.get(0);
        if (this.image.size() > 0){
            for (AlbumImage image: this.image) {
                if (image.getSize().equalsIgnoreCase(size)){
                    return image;
                }
            }
        }
        return albumImage;
    }
}
