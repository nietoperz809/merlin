package game.subgame;

import game.ClipHandler;
import game.KEY;
import game.MerlinGame;
import game.Utils;

public abstract class Subgame {
    protected final MerlinGame merlinGame;
    public  Subgame (MerlinGame mg) {
        merlinGame = mg;
    }
    public abstract void start ();
    public abstract void clicked (KEY id);
    public abstract void compTurn();
    public abstract void hitMe();

    private void reset()
    {
        Utils.delay (100);
        merlinGame.currentGame = KEY.NOKEY;
        merlinGame.lastClick = KEY.NOKEY;
    }

    protected void win (boolean reset) {
        if (reset)
            reset();
        Utils.delay (200);
        ClipHandler.play(ClipHandler.WIN);
    }

    protected void tie (boolean reset) {
        if (reset)
            reset();
        Utils.delay (200);
        ClipHandler.play (ClipHandler.TIE);
    }

    protected void lose (boolean reset) {
        if (reset)
            reset();
        Utils.delay (200);
        ClipHandler.play(ClipHandler.LOSE);
    }
}
