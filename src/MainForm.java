import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame{
    private final JPanel mainPanel;

    MainForm(String title) throws Exception {
        super (title);
        mainPanel = new JPanel();
        mainPanel.setLayout (new BorderLayout());
        MerlinGame game = new MerlinGame();
        mainPanel.add (game, BorderLayout.CENTER);
    }

    public static void main(String[] args) throws Exception {
        MainForm frame = new MainForm("Merlin!");
        frame.setContentPane(frame.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
