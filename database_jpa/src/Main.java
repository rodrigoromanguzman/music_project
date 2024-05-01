import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

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
//          entityManager.persist(new Artist("J Sebastian"));
//            Artist artist = new Artist(215,"JS Bach");
            Artist artist = entityManager.find(Artist.class,207);
            System.out.println(artist);
            artist.addAlbum("The best of Robert");
            System.out.println(artist);



//            System.out.println(artist);
//            artist.removeDuplicates();
//            System.out.println(artist);
//            artist.getAlbums().size();
//            System.out.println(artist);

//            System.out.println(artist);
//            entityManager.merge(artist);

//            entityManager.remove(artist);
            transaction.commit();
            //Connexion exitosa
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}