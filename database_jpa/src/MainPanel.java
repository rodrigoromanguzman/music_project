import jakarta.persistence.EntityManager;

import javax.swing.*;

public class MainPanel extends JPanel {
    EntityManager entityManager;
    public MainPanel(EntityManager entityManager){
        this.entityManager = entityManager;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new ArtistPanel(this.entityManager));
        add(new AlbumPanel(this.entityManager));
//        add(new SongPanel());
        add(new SearchPanel(this.entityManager));
    }
}
