package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.*;

public class MerlinGame extends JPanel {
    public BufferedImage imgMain;
    public BufferedImage imgLedOn;
    private BufferedImage imgLedOff;
    private BufferedImage offImage;
    private Graphics offGraphics;
    private final LED[] leds = new LED[11];
    
    public MerlinGame() throws Exception {
        super();
        prepareGraphic();
        setPreferredSize(new Dimension(imgMain.getWidth(), imgMain.getHeight()));
        LED.setStatics(offGraphics, imgLedOn, imgLedOff);
        leds[0] = new LED( 140,237);
        leds[1] = new LED( 86,291);
        leds[2] = new LED( 140,291);
        leds[3] = new LED( 194,291);
        leds[4] = new LED( 86,344);
        leds[5] = new LED( 140,345);
        leds[6] = new LED( 194,345);
        leds[7] = new LED( 87,399);
        leds[8] = new LED( 140,399);
        leds[9] = new LED( 194,399);
        leds[10] = new LED( 140,452);
        // Test
        leds[0].setState(LEDSTATE.BLINK);
        leds[1].setState(LEDSTATE.BLINK);
        leds[2].setState(LEDSTATE.BLINK);
        leds[3].setState(LEDSTATE.BLINK);
        leds[4].setState(LEDSTATE.BLINK);
        leds[5].setState(LEDSTATE.BLINK);
        leds[6].setState(LEDSTATE.BLINK);
        leds[7].setState(LEDSTATE.BLINK);
        leds[8].setState(LEDSTATE.BLINK);
        leds[9].setState(LEDSTATE.BLINK);
        leds[10].setState(LEDSTATE.BLINK);

        new Timer().scheduleAtFixedRate (new TimerTask() {
            @Override
            public void run() {
                paintLeds();
                LED.toggleBlink();
                paint();
            }
        },10,100);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int idx = -1;
                int x = e.getX(), y = e.getY();
                if (max(128,x) == min(165,x) && max(226,y) == min(260,y))
                    idx = 0;
                else if (max(73,x) == min(113,x) && max(279,y) == min(314,y))
                    idx = 1;
                else if (max(128,x) == min(165,x) && max(279,y) == min(314,y))
                    idx = 2;
                else if (max(182,x) == min(218,x) && max(279,y) == min(314,y))
                    idx = 3;
                else if (max(73,x) == min(113,x) && max(332,y) == min(368,y))
                    idx = 4;
                else if (max(128,x) == min(165,x) && max(332,y) == min(368,y))
                    idx = 5;
                else if (max(182,x) == min(218,x) && max(332,y) == min(368,y))
                    idx = 6;
                else if (max(73,x) == min(113,x) && max(386,y) == min(422,y))
                    idx = 7;
                else if (max(128,x) == min(165,x) && max(386,y) == min(422,y))
                    idx = 8;
                else if (max(182,x) == min(218,x) && max(386,y) == min(422,y))
                    idx = 9;
                else if (max(128,x) == min(165,x) && max(439,y) == min(476,y))
                    idx = 10;
                else if (max(99,x) == min(132,x) && max(545,y) == min(575,y))
                    idx = 11;
                else if (max(158,x) == min(191,x) && max(546,y) == min(573,y))
                    idx = 12;
                else if (max(105,x) == min(133,x) && max(598,y) == min(626,y))
                    idx = 13;
                else if (max(157,x) == min(184,x) && max(599,y) == min(626,y))
                    idx = 14;
                System.out.println(e.getX()+" -- "+e.getY()+ " == "+idx);
            }
        });

    }

    private void prepareGraphic() throws Exception {
        imgMain = ImageIO.read(Objects.requireNonNull(Utils.getResource("face.png")));
        imgLedOn = ImageIO.read(Objects.requireNonNull(Utils.getResource("led_on.png")));
        imgLedOff = ImageIO.read(Objects.requireNonNull(Utils.getResource("led_off.png")));
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gs.getDefaultConfiguration();
        offImage = gc.createCompatibleImage(imgMain.getWidth(), imgMain.getHeight(), Transparency.OPAQUE);

        offGraphics = offImage.createGraphics();
        offGraphics.drawImage(imgMain, 0, 0, this);
    }

    void paintLeds() {
        for (LED l : leds)
            l.paint();
    }

    @Override
    public void paint(Graphics g) {
        if (g != null)
            g.drawImage(offImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void paint() {
        paint(getGraphics());
    }

    @Override
    public void update(Graphics g) {
    }
}
