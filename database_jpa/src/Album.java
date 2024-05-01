import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album implements Comparable<Album>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private int albumId;

    @Column(name = "album_name")
    private String albumName;

    @OneToMany(mappedBy = "album",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Song> songs = new ArrayList<>();

    public void addSong(String title){
        songs.add(new Song(title,this));
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void removeSong(Song song){
        songs.remove(song);
        song.setAlbum(null);
    }

    public Album() {
    }

    public Album(int albumId) {
        this.albumId = albumId;
    }

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public Album(int albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }


    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Override
    public String toString(){
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Album o) {
        return this.albumName.compareTo(o.getAlbumName());
    }
}
