import jakarta.persistence.EntityManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPanel extends JPanel {
    private  JTextField searchField;
    private JButton searchButton;
    private EntityManager entityManager;
    public SearchPanel(EntityManager entityManager){
        this.entityManager = entityManager;
        setBorder(BorderFactory.createTitledBorder("Search Songs"));
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(e->searchSongs(searchField.getText()));
        add(searchField);
        add(searchButton);
    }

    private void searchSongs(String searchText){
        if(searchText.isEmpty()){
            JOptionPane.showConfirmDialog(this, "Please enter a song name","No search text",JOptionPane.WARNING_MESSAGE);
            return;
        }
        String searchLower = searchText.toLowerCase();
        List<Song> matchedSongs = entityManager.createQuery("SELECT s FROM Song s",Song.class)
                .getResultList()
                .stream()
                .filter(song -> song.getTitle().toLowerCase().contains(searchLower))
                .collect(Collectors.toList());
        if(matchedSongs.isEmpty()){
            JOptionPane.showConfirmDialog(this, "No song found matching "+searchText,"No matches",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JTextArea textArea = new JTextArea(10,30);
            textArea.setText(matchedSongs.stream().map(Song::getTitle).collect(Collectors.joining("\n")));
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(350,150));
            JOptionPane.showMessageDialog(this,scrollPane,"Search Results",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
