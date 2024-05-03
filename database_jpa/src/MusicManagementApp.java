import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;

public class MusicManagementApp {
    private static EntityManagerFactory entityManagerFactory;
    public static void main(String[] args){
        entityManagerFactory = Persistence.createEntityManagerFactory("dev.lpa.music");
        SwingUtilities.invokeLater(()->createAndShowGUI());
    }
    private static void createAndShowGUI(){
        JFrame frame = new JFrame("Music Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,600);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        frame.add(new MainPanel(entityManager));
        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                if(entityManager.isOpen()){
                    entityManager.close();
                }
                if(entityManagerFactory.isOpen()){
                    entityManagerFactory.close();
                }
                System.exit(0);
            }
        }
        );
    }
}
