package dialog;

import game.MerlinGame;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Manual extends JDialog {
    private JPanel contentPane;
    private JButton dismissButton;
    private JTextPane textPane1;
    private JButton buttonOK;

    public Manual () {
        setContentPane (contentPane);
        setModal (true);
        getRootPane ().setDefaultButton (buttonOK);
        dismissButton.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main (MerlinGame mg) {
        Manual dialog = new Manual ();

        StyledDocument doc = dialog.textPane1.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        InputStream is = game.Utils.getResource ("manual.txt");
        String result = new BufferedReader (new InputStreamReader (is))
                .lines().collect(Collectors.joining("\n"));
        dialog.textPane1.setText (result);
        dialog.textPane1.setCaretPosition (0);

        dialog.setLocationRelativeTo (null);
        dialog.pack ();
        dialog.setVisible (true);
    }
}
