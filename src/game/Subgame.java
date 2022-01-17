package game;

abstract class Subgame {
    MerlinGame merlinGame;
    Subgame (MerlinGame mg) {
        merlinGame = mg;
    }
    abstract void start();
    abstract void clicked (KEY id);

    private void reset()
    {
        Utils.delay (100);
        merlinGame.currentGame = KEY.NOKEY;
        merlinGame.lastClick = KEY.NOKEY;
    }

    void win() {
        reset();
        ClipHandler.play(ClipHandler.WIN);
    }

    void tie() {
        reset();
        ClipHandler.play (ClipHandler.TIE);
    }

    void lose() {
        reset();
        ClipHandler.play(ClipHandler.LOSE);
    }
}
