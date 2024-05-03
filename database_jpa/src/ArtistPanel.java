import jakarta.persistence.EntityManager;

import javax.swing.*;

public class ArtistPanel extends JPanel {
    private JTextField artistNameField;
    private JButton addArtistButton;
    private JLabel resultLabel;
    private EntityManager entityManager;
    public ArtistPanel(EntityManager entityManager){
        this.entityManager = entityManager;
        setBorder(BorderFactory.createTitledBorder("Add Artist"));
        artistNameField = new JTextField(20);
        addArtistButton = new JButton("Add Artist");
        resultLabel = new JLabel(" ");
        addArtistButton.addActionListener(e->addArtist(artistNameField.getText()));
        add(artistNameField);
        add(addArtistButton);
        add(resultLabel);
    }

    private void addArtist(String name){
        if(name == null || name.trim().isEmpty()){
            resultLabel.setText("Artist name cannot be empty");
            return;
        }
        try{
            entityManager.getTransaction().begin();
            Artist artist = new Artist(name);
            entityManager.persist(artist);
            entityManager.getTransaction().commit();
            resultLabel.setText("Artist added successfully: "+name);
        }catch (Exception ex){
            entityManager.getTransaction().rollback();
            resultLabel.setText("Failed to add artist: "+ex.getMessage());

        }

    }
}
