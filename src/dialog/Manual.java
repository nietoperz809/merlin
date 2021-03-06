package dialog;

import game.MerlinGame;
import game.PredefSongs;
import game.Utils;
import game.subgame.MusicMachine;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.*;
import java.awt.*;
import java.util.HashMap;

import static game.KEY.KEY2;

public class Manual extends JFrame {
    private JPanel contentPane;
    private JButton dismissButton;
    private JTextPane textPane1;
    private final HashMap<String, String> linkMap = new HashMap<> ();

    public Manual () {
        setContentPane (contentPane);
        getRootPane ().setDefaultButton (dismissButton);
        dismissButton.addActionListener (e -> dispose ());

        linkMap.put ("TTT", "TicTacToe.html");
        linkMap.put ("BACK", "main.html");
        linkMap.put ("ECH", "Echo.html");
        linkMap.put ("BLA", "BlackJack13.html");
        linkMap.put ("MAG", "MagicSquare.html");
        linkMap.put ("MIN", "MindBender.html");
        linkMap.put ("MUS", "MusicMachine.html");
    }

    public static void main (MerlinGame mg) {
        Manual dialog = new Manual ();
        StyledDocument doc = dialog.textPane1.getStyledDocument ();
        SimpleAttributeSet center = new SimpleAttributeSet ();
        StyleConstants.setAlignment (center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes (0, doc.getLength (), center, false);
        dialog.textPane1.setContentType ("text/html");
        dialog.loadPage ("main.html");
        dialog.textPane1.setEditable (false);

        dialog.textPane1.addHyperlinkListener (e -> {
            if (e.getEventType () == HyperlinkEvent.EventType.ACTIVATED) {
                String dst = e.getDescription ().substring (1);
                if (dst.equals ("SONG")) {
                    dialog.playSongFromHtml (e, mg);
                } else {
                    dialog.loadPage (dialog.linkMap.get (dst));
                }
            }
        });

        dialog.pack ();
        dialog.setResizable (false);
        dialog.setLocationRelativeTo (null);
        dialog.setVisible (true);
    }

    private void playSongFromHtml (HyperlinkEvent e, MerlinGame mg) {
        Element ele = e.getSourceElement ();
        int st = ele.getStartOffset ();
        int en = ele.getEndOffset ();
        try {
            String txt = ele.getDocument ().getText (st, en - st);
            int[] song = PredefSongs.getSong (txt);
            if (mg.getSubGame () instanceof MusicMachine == false) {
                mg.beginGame (new MusicMachine (mg), KEY2);
            }
            MusicMachine ms = ((MusicMachine) mg.getSubGame ());
            ms.loadSong (song);
            ms.compTurn ();
        } catch (BadLocationException ex) {
            ex.printStackTrace ();
        }
    }

    private void loadPage (String name) {
        textPane1.setText (Utils.getResourceText (name));
        textPane1.setCaretPosition (0);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$ ();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$ () {
        contentPane = new JPanel ();
        contentPane.setLayout (new BorderLayout (0, 0));
        contentPane.setMinimumSize (new Dimension (350, 500));
        contentPane.setPreferredSize (new Dimension (350, 500));
        dismissButton = new JButton ();
        dismissButton.setText ("Dismiss");
        contentPane.add (dismissButton, BorderLayout.SOUTH);
        final JScrollPane scrollPane1 = new JScrollPane ();
        scrollPane1.setMinimumSize (new Dimension (400, 500));
        scrollPane1.setPreferredSize (new Dimension (400, 500));
        contentPane.add (scrollPane1, BorderLayout.CENTER);
        textPane1 = new JTextPane ();
        scrollPane1.setViewportView (textPane1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$ () {
        return contentPane;
    }

}
