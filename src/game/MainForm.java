package game;

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
//       for(int s = 0; s<100; s++)
//           System.out.println(Math.random()<0.5);

        MainForm frame = new MainForm("Merlin!");
        frame.setResizable(false);
        frame.setContentPane(frame.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
