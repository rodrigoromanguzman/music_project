import java.util.ArrayList;
import java.util.List;

public interface MusicSearchUtility {
    static List<Album> searchAlbumByName(List<Artist> artists, String albumName){
        List<Album> foundAlbums = new ArrayList<>();
        for(Artist artist: artists){
            artist.getAlbums().stream().filter(album -> album.getAlbumName().equalsIgnoreCase(albumName))
                    .forEach(foundAlbums::add);
        }
        return foundAlbums;
    }

    static List<Song> searchSongByName(List<Artist> artists, String songTitle){
        List<Song> foundSongs = new ArrayList<>();
        for(Artist artist: artists){
            for(Album album: artist.getAlbums()){
                album.getSongs().stream().filter(song -> song.getTitle().equalsIgnoreCase(songTitle))
                        .forEach(foundSongs::add);
            }

        }
        return foundSongs;
    }

}
