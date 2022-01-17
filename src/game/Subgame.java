package game;

abstract class Subgame {
    MerlinGame merlinGame;
    Subgame (MerlinGame mg) {
        merlinGame = mg;
    }
    abstract void start();
    abstract void clicked (KEY id);

    void win() {
        Utils.delay (100);
        ClipHandler.play(ClipHandler.WIN);
        merlinGame.currentGame = KEY.NOKEY;
        merlinGame.lastClick = KEY.NOKEY;
    }

    void lose() {
        Utils.delay (100);
        ClipHandler.play(ClipHandler.LOSE);
        merlinGame.currentGame = KEY.NOKEY;
        merlinGame.lastClick = KEY.NOKEY;
    }
}
