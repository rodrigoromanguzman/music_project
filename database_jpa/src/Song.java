import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "song_id")
    private int songId;

    @Column(name = "song_title")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="album_id")
    private Album album;

    public Song(){}

    public Song(String title){
        this.title = title;
    }
    public Song(String title, Album album){
        this.title = title;
        this.album = album;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", title='" + title + '\'' +
                ", album=" + album +
                '}';
    }
}
