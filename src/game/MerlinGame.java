package game;

import game.subgame.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static game.KEY.*;

public class MerlinGame extends JPanel {
    public final Led[] leds = new Led[11];
    public KEY lastClick = NOKEY;
    public KEY lastGame = NOKEY;
    public KEY currentGame = NOKEY;
    private BufferedImage imgMain;
    private BufferedImage imgLedOn;
    private BufferedImage imgLedOff;
    private BufferedImage offImage;
    private Graphics offGraphics;
    private Subgame subGame;

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

        ClipHandler.playWait (ClipHandler.INIT);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                paintLeds();
                Led.toggleBlink();
                render();
            }
        }, 10, 100);

        new ClickDecoder(this);
    }

    public void buttonClicked(KEY id) {
        if (id == NEWGAME) {
            allLedsOff();
            leds[0].setState(LEDSTATE.BLINK);
            ClipHandler.play(ClipHandler.MON);
            currentGame = NOKEY;
            lastClick = NEWGAME;
            return;
        } else if (id == SAMEGAME) {
            lastClick = NEWGAME;
            allLedsOff ();
            buttonClicked(lastGame);
        }
        if (lastClick == NEWGAME) {
            switch (id) {
                case KEY1:
                    beginGame (new TicTacToe (this), id);
                    break;
                case KEY2:
                    beginGame (new MusicMachine (this), id);
                    break;
                case KEY3:
                    beginGame (new Echo (this), id);
                    break;
                case KEY4:
                    beginGame (new BlackJack (this), id);
                    break;
                case KEY5:
                    beginGame (new MagicSquare (this), id);
                    break;
                case KEY6:
                    beginGame (new MindBender (this), id);
                    break;
                default:
                    ClipHandler.play (ClipHandler.LOSE);
                    break;
            }
            return;
        }
        if (subGame != null) {
            if (id == COMPTURN)
                subGame.compTurn ();
            else if (id == HITME)
                subGame.hitMe ();
            else
                subGame.clicked (id);
        }
        lastClick = id;
    }

    private void beginGame(Subgame sub, KEY id) {
        subGame = sub;
        leds[0].setState(LEDSTATE.OFF);
        ClipHandler.play(ClipHandler.BEGIN);
        currentGame = id;
        lastGame = id;
        lastClick = NOKEY;
        sub.start();
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
