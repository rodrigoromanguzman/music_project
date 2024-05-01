import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.lang.reflect.Array;
import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try(
                var sessionFactory = Persistence.createEntityManagerFactory(
                        "dev.lpa.music");
                EntityManager entityManager = sessionFactory.createEntityManager();
                ){
            var transaction = entityManager.getTransaction();
            transaction.begin();
            Artist artist = entityManager.find(Artist.class,212);
            System.out.println(artist);
            artist.addAlbumWithSongs("Test Album 2", Arrays.asList("Song Test 1","Song Test 2"));
            entityManager.merge(artist);
            transaction.commit();
            //Connexion exitosa
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}