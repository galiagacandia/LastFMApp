package bo.org.groverleopoldo.lastfmapp.models.songs;

import java.util.List;

/**
 * Created by Grover Leopoldo on 17/9/2017.
 */

public class Song {
    private String name;
    private String artist;
    private String url;
    private String listeners;
    private List<SongImage> image;

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

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public List<SongImage> getImage() {
        return image;
    }

    public void setImage(List<SongImage> image) {
        this.image = image;
    }

    public SongImage getImage(String size){
        SongImage songImage = this.image.get(0);
        if (this.image.size() > 0){
            for (SongImage image: this.image) {
                if (image.getSize().equalsIgnoreCase(size)){
                    return image;
                }
            }
        }
        return songImage;
    }
}
