package game;

public class MagicSquare {
    private MerlinGame merlinGame;
    private final int[][] squareLogic = {
            {1, 2, 4, 5}, {1, 2, 3}, {2, 3, 5, 6},
            {1, 4, 7}, {2, 4, 5, 6, 8}, {3, 6, 9},
            {4, 5, 7, 8}, {7, 8, 9}, {5, 6, 8, 9}
    };

    public MagicSquare(MerlinGame ml) {
        merlinGame = ml;
    }

    public void clicked(int id)
    {

    }

    public boolean isMSquare() {
        for (int s = 1; s < 10; s++) {
            if (s == 5)
                continue;
            if (merlinGame.leds[s].getState() != LEDSTATE.BLINK)
                return false;
        }
        return merlinGame.leds[5].getState() == LEDSTATE.OFF;
    }

    public void toggleMSquare (int idx) {
        int[] arr = squareLogic[idx];
        for (int s : arr)
            merlinGame.leds[s].toggleBlinkState();
        if (isMSquare()) {
            ClipHandler.play(ClipHandler.WIN);
            merlinGame.currentGame = -1;
            merlinGame.lastClick = -1;
        }
    }

    public void startMSquare() {
        for (int s = 1; s < 10; s++) {
            if (Math.random() < 0.5)
                merlinGame.leds[s].setState(LEDSTATE.OFF);
            else
                merlinGame.leds[s].setState(LEDSTATE.BLINK);
        }
    }

}
