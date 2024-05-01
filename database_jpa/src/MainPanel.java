import javax.swing.*;

public class MainPanel extends JPanel {
    public MainPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new ArtistPanel());
        add(new AlbumPanel());
        add(new SongPanel());
        add(new SearchPanel());
    }
}
