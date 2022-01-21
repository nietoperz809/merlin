package game.subgame;

import game.*;

public class BlackJack extends Subgame{
    int userPoints, compPoints;

    public BlackJack (MerlinGame mg) {
        super (mg);
    }

    @Override
    public void start () {
        userPoints = Utils.random (1,10);
        do
            compPoints = Utils.random (1,10);
        while (userPoints == compPoints);
        merlinGame.leds[userPoints].setState (LEDSTATE.BLINK);
        merlinGame.leds[compPoints].setState (LEDSTATE.ON);
    }

    @Override
    public void clicked (KEY id) {

    }

    @Override
    public void compTurn () {
        while (compPoints < 10) {
            compPoints += setFreeLed (LEDSTATE.ON);
            ClipHandler.play (ClipHandler.BUZZ);
            Utils.delay (500);
        }
        if (compPoints > 13 || compPoints < userPoints)
            lose (true);
        else if (compPoints == userPoints)
            tie (true);
        win (true);
    }

    @Override
    public void hitMe () {
        int x = setFreeLed (LEDSTATE.BLINK);
        ClipHandler.play (0);
        userPoints += x;
        if (userPoints > 13)
            lose(true);
    }

    private int setFreeLed (LEDSTATE ls) {
        int x;
        do
            x = Utils.random (1,10);
        while (merlinGame.leds[x].getState () != LEDSTATE.OFF);
        merlinGame.leds[x].setState (ls);
        return x;
    }
}
