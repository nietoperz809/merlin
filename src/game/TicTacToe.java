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
                if (merlinGame.leds[b].getInitialState() == ls) {
                    cnt++;
                    if (cnt == 3)
                        return true;
                }
            }
        }
        return false;
    }


    @Override
    public void start() {
        merlinGame.leds[10].setState(LEDSTATE.BLINK);
    }

    @Override
    public void clicked(KEY id) {
        merlinGame.leds[10].setState(LEDSTATE.OFF);
        if (id == KEY.COMPTURN) {

        } else {
            int v = id.getNumVal();
            if (v >= 1 && v <= 9) {
                if (merlinGame.leds[v].getInitialState() == LEDSTATE.OFF) {
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
