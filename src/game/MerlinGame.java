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

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MerlinGame extends JPanel {
    private final LED[] leds = new LED[11];
    public BufferedImage imgMain;
    public BufferedImage imgLedOn;
    int[][] squareLogic = {
            {1, 2, 4, 5}, {1, 2, 3}, {2, 3, 5, 6},
            {1, 4, 7}, {2, 4, 5, 6, 8}, {3, 6, 9},
            {4, 5, 7, 8}, {7, 8, 9}, {5, 6, 8, 9}
    };
    private BufferedImage imgLedOff;
    private BufferedImage offImage;
    private Graphics offGraphics;
    private int lastClick = -1;
    private int currentGame = -1;

    public MerlinGame() throws Exception {
        super();
        prepareGraphic();
        setPreferredSize(new Dimension(imgMain.getWidth(), imgMain.getHeight()));
        LED.setStatics(offGraphics, imgLedOn, imgLedOff);
        leds[0] = new LED(140, 237);
        leds[1] = new LED(86, 291);
        leds[2] = new LED(140, 291);
        leds[3] = new LED(194, 291);
        leds[4] = new LED(86, 344);
        leds[5] = new LED(140, 345);
        leds[6] = new LED(194, 345);
        leds[7] = new LED(87, 399);
        leds[8] = new LED(140, 399);
        leds[9] = new LED(194, 399);
        leds[10] = new LED(140, 452);
        // start state
        leds[0].setState(LEDSTATE.BLINK);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                paintLeds();
                LED.toggleBlink();
                render();
            }
        }, 10, 100);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int idx = -1;
                int x = e.getX(), y = e.getY();
                if (max(128, x) == min(165, x) && max(226, y) == min(260, y))
                    idx = 0;
                else if (max(73, x) == min(113, x) && max(279, y) == min(314, y))
                    idx = 1;
                else if (max(128, x) == min(165, x) && max(279, y) == min(314, y))
                    idx = 2;
                else if (max(182, x) == min(218, x) && max(279, y) == min(314, y))
                    idx = 3;
                else if (max(73, x) == min(113, x) && max(332, y) == min(368, y))
                    idx = 4;
                else if (max(128, x) == min(165, x) && max(332, y) == min(368, y))
                    idx = 5;
                else if (max(182, x) == min(218, x) && max(332, y) == min(368, y))
                    idx = 6;
                else if (max(73, x) == min(113, x) && max(386, y) == min(422, y))
                    idx = 7;
                else if (max(128, x) == min(165, x) && max(386, y) == min(422, y))
                    idx = 8;
                else if (max(182, x) == min(218, x) && max(386, y) == min(422, y))
                    idx = 9;
                else if (max(128, x) == min(165, x) && max(439, y) == min(476, y))
                    idx = 10;
                else if (max(99, x) == min(132, x) && max(545, y) == min(575, y))
                    idx = 11;
                else if (max(158, x) == min(191, x) && max(546, y) == min(573, y))
                    idx = 12;
                else if (max(105, x) == min(133, x) && max(598, y) == min(626, y))
                    idx = 13;
                else if (max(157, x) == min(184, x) && max(599, y) == min(626, y))
                    idx = 14;
                if (idx > -1)
                    buttonClicked(idx);
            }
        });
    }

    private void buttonClicked(int id) {
       // System.out.println("Click: " + id);
        switch (id) {
            case 11:
                allLedsOff();
                leds[0].setState(LEDSTATE.BLINK);
                ClipHandler.play(ClipHandler.MON);
                currentGame = -1;
                break;
            case 5:
                if (currentGame == 5) {
                    ClipHandler.play(5);
                    toggleMSquare(squareLogic[4]);
                    return;
                }
                if (lastClick == 11) {
                    leds[0].setState(LEDSTATE.OFF);
                    ClipHandler.play(ClipHandler.BEGIN);
                    currentGame = 5;
                    startMSquare();
                }
                return;
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                if (currentGame == 5) {
                    if (id == 10 || id == 0)
                        return;
                    ClipHandler.play(id);
                    toggleMSquare(squareLogic[id - 1]);
                    return;
                }
                if (lastClick == 11) {
                    ClipHandler.play(ClipHandler.BUZZ);
                    return;
                }
                break;
            default:
                return;
        }
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

    private boolean isMSquare() {
        for (int s = 1; s < 10; s++) {
            if (s == 5)
                continue;
            if (leds[s].getState() != LEDSTATE.BLINK)
                return false;
        }
        if (leds[5].getState() == LEDSTATE.OFF)
            return true;
        return false;
    }

    private void toggleMSquare(int[] arr) {
        for (int s : arr)
            leds[s].toggleBlinkState();
        if (isMSquare()) {
            ClipHandler.play(ClipHandler.WIN);
            currentGame = -1;
            lastClick = -1;
        }
    }

    private void startMSquare() {
        for (int s = 1; s < 10; s++) {
            if (Math.random() < 0.5)
                leds[s].setState(LEDSTATE.OFF);
            else
                leds[s].setState(LEDSTATE.BLINK);
        }
    }

    private void allLedsOff() {
        for (int s = 0; s < 11; s++)
            leds[s].setState(LEDSTATE.OFF);
    }

    private void paintLeds() {
        for (LED l : leds)
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
