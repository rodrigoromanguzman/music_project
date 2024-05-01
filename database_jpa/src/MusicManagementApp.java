import javax.swing.*;

public class MusicManagementApp {
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->createAndShowGUI());
    }
    private static void createAndShowGUI(){
        JFrame frame = new JFrame("Music Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,400);

        frame.add(new MainPanel());
        frame.setVisible(true);
    }
}
