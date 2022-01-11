package game;

import java.awt.*;
import java.awt.image.BufferedImage;

class Led {
    private final int x;
    private final int y;
    private static Graphics g;
    private static BufferedImage on;
    private static BufferedImage off;
    private LEDSTATE state = LEDSTATE.OFF;
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
        state = s;
    }

    public LEDSTATE getState() {
        return state;
    }

    public void toggleBlinkState() {
        if (state == LEDSTATE.BLINK)
            state = LEDSTATE.OFF;
        else if (state == LEDSTATE.OFF)
            state = LEDSTATE.BLINK;
    }

    public static void toggleBlink() {
        blinkflag = !blinkflag;
    }

    public void paint() {
        switch (state) {
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