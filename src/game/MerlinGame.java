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

import static game.KEY.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class MerlinGame extends JPanel {
    public final Led[] leds = new Led[11];
    private BufferedImage imgMain;
    private BufferedImage imgLedOn;
    private BufferedImage imgLedOff;
    private BufferedImage offImage;
    private Graphics offGraphics;
    public KEY lastClick = NOKEY;
    public int currentGame = -1;
    private MagicSquare magicSquare = new MagicSquare(this);

    public MerlinGame() throws Exception {
        super();
        prepareGraphic();
        setPreferredSize(new Dimension(imgMain.getWidth(), imgMain.getHeight()));
        Led.setStatics(offGraphics, imgLedOn, imgLedOff);
        leds[0] = new Led(140, 237);
        leds[1] = new Led(86, 291);
        leds[2] = new Led(140, 291);
        leds[3] = new Led(194, 291);
        leds[4] = new Led(86, 344);
        leds[5] = new Led(140, 345);
        leds[6] = new Led(194, 345);
        leds[7] = new Led(87, 399);
        leds[8] = new Led(140, 399);
        leds[9] = new Led(194, 399);
        leds[10] = new Led(140, 452);
        // start state
        leds[0].setState(LEDSTATE.BLINK);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                paintLeds();
                Led.toggleBlink();
                render();
            }
        }, 10, 100);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                KEY idx = NOKEY;
                int x = e.getX(), y = e.getY();
                if (max(128, x) == min(165, x) && max(226, y) == min(260, y))
                    idx = KEY0;
                else if (max(73, x) == min(113, x) && max(279, y) == min(314, y))
                    idx = KEY1;
                else if (max(128, x) == min(165, x) && max(279, y) == min(314, y))
                    idx = KEY2;
                else if (max(182, x) == min(218, x) && max(279, y) == min(314, y))
                    idx = KEY3;
                else if (max(73, x) == min(113, x) && max(332, y) == min(368, y))
                    idx = KEY4;
                else if (max(128, x) == min(165, x) && max(332, y) == min(368, y))
                    idx = KEY5;
                else if (max(182, x) == min(218, x) && max(332, y) == min(368, y))
                    idx = KEY6;
                else if (max(73, x) == min(113, x) && max(386, y) == min(422, y))
                    idx = KEY7;
                else if (max(128, x) == min(165, x) && max(386, y) == min(422, y))
                    idx = KEY8;
                else if (max(182, x) == min(218, x) && max(386, y) == min(422, y))
                    idx = KEY9;
                else if (max(128, x) == min(165, x) && max(439, y) == min(476, y))
                    idx = KEY10;
                else if (max(99, x) == min(132, x) && max(545, y) == min(575, y))
                    idx = NEWGAME;
                else if (max(158, x) == min(191, x) && max(546, y) == min(573, y))
                    idx = SAMEGAME;
                else if (max(105, x) == min(133, x) && max(598, y) == min(626, y))
                    idx = HITME;
                else if (max(157, x) == min(184, x) && max(599, y) == min(626, y))
                    idx = COMPTURN;
                if (idx != NOKEY)
                    buttonClicked(idx);
            }
        });
    }

    private void buttonClicked (KEY id) {
        if (id == NEWGAME) {
            allLedsOff();
            leds[0].setState(LEDSTATE.BLINK);
            ClipHandler.play(ClipHandler.MON);
            currentGame = -1;
        }
        if (lastClick == NEWGAME) {
            if (id == KEY5) {
                leds[0].setState(LEDSTATE.OFF);
                ClipHandler.play(ClipHandler.BEGIN);
                currentGame = 5;
                lastClick = NOKEY;
                magicSquare.start();
            } else {
                ClipHandler.play(ClipHandler.BUZZ);
            }
            return;
        }
        if (currentGame == 5)
            magicSquare.clicked(id);
        lastClick = id;
    }

    private void prepareGraphic() throws Exception {
        imgMain = ImageIO.read(Objects.requireNonNull(Utils.getResource("face.png")));
        imgLedOn = ImageIO.read(Objects.requireNonNull(Utils.getResource("led_on.png")));
        imgLedOff = ImageIO.read(Objects.requireNonNull(Utils.getResource("led_off.png")));
        offImage = GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getDefaultScreenDevice().
                getDefaultConfiguration().
                createCompatibleImage(imgMain.getWidth(), imgMain.getHeight(), Transparency.OPAQUE);
        offGraphics = offImage.createGraphics();
        offGraphics.drawImage(imgMain, 0, 0, null);
    }

    private void allLedsOff() {
        for (int s = 0; s < 11; s++)
            leds[s].setState(LEDSTATE.OFF);
    }

    private void paintLeds() {
        for (Led l : leds)
            l.paint();
    }

    @Override
    public void paint(Graphics g) {
        if (g != null)
            g.drawImage(offImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void render() {
        paint(getGraphics());
    }

    @Override
    public void update(Graphics g) {
    }
}
