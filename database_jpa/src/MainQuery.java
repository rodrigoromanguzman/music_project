import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class MainQuery {
    public static void main(String[] args){
        List<String> artist = null;
        try(
                EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                        "dev.lpa.music"
                );
                EntityManager em = emf.createEntityManager();


        ){
            var transaction = em.getTransaction();
            transaction.begin();
            artist = getArtistJPQL(em,"%En%");
            artist.forEach(System.out::println);
            transaction.commit();

        }catch(Exception e)
        {
            e.printStackTrace();

        }

    }
    private static List<String> getArtistJPQL(EntityManager em,String matchedValue){
        String jpql = "SELECT a from Artist a WHERE a.artistName LIKE :partialName";
        var query = em.createQuery(jpql,String.class);
        query.setParameter("partialName",matchedValue);
        return query.getResultList();
    }
}
