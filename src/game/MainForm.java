package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainForm extends JFrame{
    private final JPanel mainPanel;

    MainForm(String title) throws Exception {
        super (title);
        mainPanel = new JPanel();
        mainPanel.setLayout (new BorderLayout());
        MerlinGame game = new MerlinGame();
        mainPanel.add (game, BorderLayout.CENTER);

        JMenuBar mb = new JMenuBar ();
        JMenuItem m1 = new JMenuItem(new AbstractAction("№") {
            public void actionPerformed(ActionEvent e) {
                game.showNumbering();
            }
        });
        JMenuItem m2 = new JMenuItem(new AbstractAction("ℑ") {
            public void actionPerformed(ActionEvent e) {
                dialog.Manual.main (game);
            }
        });
        mb.add (m1);
        mb.add (m2);
        setJMenuBar (mb);
    }

    public static void main(String[] args) throws Exception {
        MainForm frame = new MainForm("Merlin!");
        frame.setResizable(false);
        frame.setContentPane(frame.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
