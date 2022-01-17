package game;

public class TicTacToe extends Subgame {

    static private int winPos[][] = {
            {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
            {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
            {1,5,9}, {3,5,7}
    };

    TicTacToe(MerlinGame ml) {
        super(ml);
    }

    private boolean checkWin(LEDSTATE ls) {
        for (int[] a : winPos) {
            int cnt = 0;
            for (int b : a) {
                if (merlinGame.leds[b].getState() == ls) {
                    cnt++;
                    if (cnt == 3)
                        return true;
                }
            }
        }
        return false;
    }

    private int getNewPos (LEDSTATE ls) {
        for (int[] a : winPos) {
            if (merlinGame.leds[a[0]].getState () == ls &&
                    merlinGame.leds[a[1]].getState () == ls &&
                    merlinGame.leds[a[2]].getState () == LEDSTATE.OFF)
                return a[2];
            if (merlinGame.leds[a[0]].getState () == ls &&
                    merlinGame.leds[a[2]].getState () == ls &&
                    merlinGame.leds[a[1]].getState () == LEDSTATE.OFF)
                return a[1];
            if (merlinGame.leds[a[1]].getState () == ls &&
                    merlinGame.leds[a[2]].getState () == ls &&
                    merlinGame.leds[a[0]].getState () == LEDSTATE.OFF)
                return a[0];
            }
        return -1; // not found
    }

    private int getProbablyGoodPos(LEDSTATE ls) {
        for (int[] a : winPos) {
            if (merlinGame.leds[a[0]].getState () == ls &&
                    merlinGame.leds[a[1]].getState () == LEDSTATE.OFF &&
                    merlinGame.leds[a[2]].getState () == LEDSTATE.OFF)
                return a[2];
            if (merlinGame.leds[a[1]].getState () == ls &&
                    merlinGame.leds[a[0]].getState () == LEDSTATE.OFF &&
                    merlinGame.leds[a[2]].getState () == LEDSTATE.OFF)
                return a[0];
            if (merlinGame.leds[a[2]].getState () == ls &&
                    merlinGame.leds[a[1]].getState () == LEDSTATE.OFF &&
                    merlinGame.leds[a[0]].getState () == LEDSTATE.OFF)
                return a[1];
        }
        return -1;
    }

    private int getRandomPos()
    {
        for (int s=1; s<10; s++)
            if (merlinGame.leds[s].getState () == LEDSTATE.OFF)
                return s;
        return -1;
    }

    @Override
    public void start() {
        merlinGame.leds[10].setState(LEDSTATE.BLINK);
    }

    @Override
    public void clicked(KEY id) {
        merlinGame.leds[10].setState(LEDSTATE.OFF);
        if (id == KEY.COMPTURN) {
            int found = getNewPos (LEDSTATE.ON); // winner pos
            if (found == -1)
                found = getNewPos (LEDSTATE.BLINK); // defense pos
            if (found == -1)
                found = getProbablyGoodPos (LEDSTATE.ON); // build oxo pos
            if (found == -1)
                found = getRandomPos ();
            if (found == -1) {
                tie();
            } else {
                ClipHandler.play (ClipHandler.O);
                merlinGame.leds[found].setState (LEDSTATE.ON);
            }
            if (checkWin (LEDSTATE.ON) == true) {
                lose();
            }
        } else {
            int v = id.getNumVal();
            if (v >= 1 && v <= 9) {
                if (merlinGame.leds[v].getState() == LEDSTATE.OFF) {
                    merlinGame.leds[v].setState(LEDSTATE.BLINK);
                    ClipHandler.play(ClipHandler.X);
                    if (checkWin(LEDSTATE.BLINK) == true) {
                        win();
                    }
                } else {
                    ClipHandler.play(ClipHandler.BUZZ);
                }
            }
        }
        //System.out.println (id);
    }
}
