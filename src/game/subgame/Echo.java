package game.subgame;

import game.*;

import java.util.ArrayList;
import java.util.Random;

public class Echo extends Subgame {
    private final ArrayList<Integer> seq = new ArrayList<> ();
    private final ArrayList<Integer> input = new ArrayList<> ();
    private int errors = 0;

    public Echo (MerlinGame mg) {
        super (mg);
    }

    @Override
    public void start () {
        merlinGame.leds[0].setState (LEDSTATE.BLINK);
        merlinGame.leds[10].setState (LEDSTATE.BLINK);
        seq.clear ();
        input.clear ();
        errors = 0;
    }

    @Override
    public void clicked (KEY id) {
        int v = id.getNumVal ();
        if (v >= 0 && v <= 9) {
            if (seq.isEmpty ()) { // generate sequence
                int before = -1;
                int z;
                for (int s = 0; s < v; s++) {
                    do
                        z = Utils.random (1, 9); 
                    while (z == before);
                    seq.add (z);
                    before = z;
                }
                merlinGame.leds[0].setState (LEDSTATE.OFF);
                ClipHandler.play (ClipHandler.X);
            } else { // user plays
                if (v != seq.get (input.size ())) {
                    ClipHandler.play (ClipHandler.BUZZ);
                    errors++;
                    if (errors == 2) {
                        input.clear ();
                        errors = 0;
                        lose (false);
                    }
                    return;
                }
                merlinGame.leds[v].setState (LEDSTATE.ON);
                ClipHandler.playWait (v);
                merlinGame.leds[v].setState (LEDSTATE.OFF);
                input.add (v);
                errors = 0;
                if (input.size () == seq.size ()) {
                    if (input.toString ().equals (seq.toString ())) {
                        win (false);
                    } else {
                        lose (false);
                    }
                    input.clear ();
                }
            }
        }
    }

    @Override
    public void compTurn () {
        if (!seq.isEmpty ()) {
            for (int t : seq) { // play sequence
                merlinGame.leds[t].setState (LEDSTATE.ON);
                ClipHandler.playWait (t);
                //Utils.delay ();
                merlinGame.leds[t].setState (LEDSTATE.OFF);
            }
        }
    }

    @Override
    public void hitMe () {

    }
}
