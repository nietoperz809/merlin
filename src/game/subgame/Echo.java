package game.subgame;

import game.*;

import java.util.ArrayList;
import java.util.Random;

public class Echo extends Subgame {
    private int errors = 0;
    private final ArrayList<Integer> seq = new ArrayList<> ();
    private final ArrayList<Integer> input = new ArrayList<> ();
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
        if (id == KEY.COMPTURN) {
            if (!seq.isEmpty ()) {
                for (int t : seq) { // play sequence
                    merlinGame.leds[t].setState (LEDSTATE.ON);
                    ClipHandler.playWait (t);
                    //Utils.delay ();
                    merlinGame.leds[t].setState (LEDSTATE.OFF);
                }
            }
            return;
        }
        int v = id.getNumVal ();
        if (v >= 0 && v <= 9) {
            if (seq.isEmpty ()) { // generate sequence
                Random rnd = new Random();
                int before = -1;
                int z;
                for (int s=0; s<v; s++) {
                    do
                        z = rnd.nextInt (9)+1;
                    while (z == before);
                    seq.add (z);
                    before = z;
                }
                merlinGame.leds[0].setState (LEDSTATE.OFF);
                ClipHandler.play(ClipHandler.X);
            } else { // user plays
                if (v != seq.get(input.size ())) {
                    ClipHandler.play (ClipHandler.BUZZ);
                    errors++;
                    if (errors == seq.size()) {
                        input.clear ();
                        errors = 0;
                        lose (false);
                    }
                    return;
                }
                merlinGame.leds[v].setState (LEDSTATE.ON);
                ClipHandler.playWait (v);
                merlinGame.leds[v].setState (LEDSTATE.OFF);
                input.add(v);
                if (input.size () == seq.size()) {
                    if (input.toString ().equals (seq.toString ())) {
                        win(false);
                    } else {
                        lose (false);
                    }
                    input.clear ();
                }
            }
        }
    }
}
