package game;

import java.awt.*;
import java.awt.image.BufferedImage;

class Led {
    private final int x;
    private final int y;
    private static Graphics g;
    private static BufferedImage on;
    private static BufferedImage off;
    private LEDSTATE currentState = LEDSTATE.OFF;
    private LEDSTATE initialState = LEDSTATE.OFF;
    private static boolean blinkflag = false;

    public static void setStatics(Graphics g, BufferedImage on, BufferedImage off) {
        Led.g = g;
        Led.on = on;
        Led.off = off;
    }

    public Led(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setState(LEDSTATE s) {
        initialState = s;
        currentState = s;
    }

    public LEDSTATE getInitialState() {
        return initialState;
    }

    public void toggleBlinkState() {
        if (currentState == LEDSTATE.BLINK)
            currentState = LEDSTATE.OFF;
        else if (currentState == LEDSTATE.OFF)
            currentState = LEDSTATE.BLINK;
    }

    public static void toggleBlink() {
        blinkflag = !blinkflag;
    }

    public void paint() {
        switch (currentState) {
            case ON:
                g.drawImage(on, x, y, null);
                break;
            case OFF:
                g.drawImage(off, x, y, null);
                break;
            case BLINK:
                if (blinkflag)
                    g.drawImage(off, x, y, null);
                else
                    g.drawImage(on, x, y, null);
                break;
        }
    }
}
