package game;

public class MagicSquare extends Subgame {
    private final int[][] squareLogic = {
            {1, 2, 4, 5}, {1, 2, 3}, {2, 3, 5, 6},
            {1, 4, 7}, {2, 4, 5, 6, 8}, {3, 6, 9},
            {4, 5, 7, 8}, {7, 8, 9}, {5, 6, 8, 9}
    };

    public MagicSquare(MerlinGame ml) {
        super(ml);
    }

    @Override
    public void clicked(KEY id) {
        if (id.getNumVal() >= 1 && id.getNumVal() <= 9) {
            ClipHandler.play(id.getNumVal());
            toggleMSquare(id.getNumVal() - 1);
        }
    }

    private boolean isMSquare() {
        for (int s = 1; s < 10; s++) {
            if (s == 5)
                continue;
            if (merlinGame.leds[s].getInitialState() != LEDSTATE.BLINK)
                return false;
        }
        return merlinGame.leds[5].getInitialState() == LEDSTATE.OFF;
    }

    private void toggleMSquare(int idx) {
        int[] arr = squareLogic[idx];
        for (int s : arr)
            merlinGame.leds[s].toggleBlinkState();
        if (isMSquare()) {
            win();
        }
    }

    @Override
    public void start() {
        for (int s = 1; s < 10; s++) {
            if (Math.random() < 0.5)
                merlinGame.leds[s].setState(LEDSTATE.OFF);
            else
                merlinGame.leds[s].setState(LEDSTATE.BLINK);
        }
    }
}
