package game.subgame;

import game.*;

import java.util.ArrayList;

import static game.KEY.KEY0;

public class MindBender extends Subgame {
    private ArrayList<Integer> num = new ArrayList<> ();
    private ArrayList<Integer> guess = new ArrayList<> ();
    public MindBender (MerlinGame mg) {
        super (mg);
    }

    @Override
    public void start () {
        merlinGame.leds[0].setState (LEDSTATE.BLINK);
        merlinGame.leds[10].setState (LEDSTATE.BLINK);
        num.clear ();
        guess.clear ();
    }

    private void clear1_9() {
        for (int s=1; s<10; s++)
            merlinGame.leds[s].setState (LEDSTATE.OFF);
    }

    @Override
    public void clicked (KEY id) {
        int v = id.getNumVal ();
        if (v == 0 || v > 9)
            return;
        if (num.isEmpty ()) {
            int r;
            while (v > 0) {
                do
                    r = Utils.random (1,9);
                while (num.contains (r));
                num.add (r);
                v--;
            }
            merlinGame.leds[0].setState (LEDSTATE.OFF);
            ClipHandler.play (ClipHandler.X);
            System.out.println (num);
        } else {
            guess.add (v);
            ClipHandler.play (v);
            if (guess.size () == num.size ()) {
                clear1_9 ();
                for (int s=0; s< guess.size (); s++) {
                    int x = guess.get (s);
                    int pos = num.indexOf (x);
                    if (pos == -1)
                        continue;
                    if (pos == s)
                        merlinGame.leds[s+1].setState (LEDSTATE.BLINK);
                    else
                        merlinGame.leds[s+1].setState (LEDSTATE.ON);
                    //System.out.println (pos+" -- "+s);
                }
                if (guess.toString ().equals (num.toString ()))
                    win (false);
                else
                    lose (false);
                guess.clear ();
            }
        }
    }

    @Override
    public void compTurn () {

    }

    @Override
    public void hitMe () {

    }
}
