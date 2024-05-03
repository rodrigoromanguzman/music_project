import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.awt.*;

public class AlbumPanel extends JPanel {
    private JTextField albumNameField, artistIdField;
    private JButton addAlbumButton;
    private JLabel resultLabel;
    private EntityManager entityManager;

    public AlbumPanel(EntityManager entityManager){
        this.entityManager = entityManager;
        setBorder(BorderFactory.createTitledBorder("Add Album"));
        albumNameField = new JTextField(20);
        artistIdField = new JTextField(20);
        addAlbumButton = new JButton("Add Album");
        resultLabel = new JLabel(" ");
        resultLabel.setForeground(new Color(0,128,0));

        addAlbumButton.addActionListener(e->addAlbum(albumNameField.getText(),artistIdField.getText()));
        add(new JLabel("Album Name:"));
        add(albumNameField);
        add(new JLabel("Artist Id:"));
        add(artistIdField);
        add(addAlbumButton);
        add(resultLabel);


    }
    private void addAlbum(String albumName, String artistId){
        if(albumName.isEmpty()|| artistId.isEmpty()){
            resultLabel.setText("Album name and Artist ID must be provided");
            return;
        }
        try{
            System.out.println("The artist before parse");
            System.out.println(artistId);
            int id = Integer.parseInt(artistId);
            entityManager.getTransaction().begin();
            Artist artist = entityManager.find(Artist.class,id);
            if(artist==null){
                resultLabel.setText("No artist found with Id: "+id);
                entityManager.getTransaction().rollback();
            }else{
                artist.addAlbum(albumName);
                entityManager.getTransaction().commit();
                resultLabel.setText("Album added: "+albumName+" to artist Id: "+id);
            }
        }catch (Exception ex){
            resultLabel.setText("Failed to add album: "+ ex.getMessage());
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
        }

    }
}
