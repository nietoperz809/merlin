package game.subgame;

import game.*;

import java.util.ArrayList;

import static game.KEY.KEY0;

public class MusicMachine extends Subgame {

    private final ArrayList<KEY> list = new ArrayList<> ();

    public MusicMachine (MerlinGame mg) {
        super(mg);
    }

    public void loadSong (int[] vals) {
        list.clear ();
        for (int n : vals)
            list.add (KEY.get (n));
    }

    @Override
    public void start () {
        merlinGame.leds[10].setState (LEDSTATE.BLINK);
        list.clear ();
    }

    @Override
    public void clicked (KEY id) {
        int v = id.getNumVal ();
        if (v >= 0 && v <= 9) {
            list.add (id);
            ClipHandler.play(v);
        }
    }

    @Override
    public void compTurn () {
        for (KEY k : list) {
            if (k == KEY0) {
                ClipHandler.playWait (ClipHandler.INIT);
                //Utils.delay (150);
            }
            else {
                merlinGame.leds[k.getNumVal ()].setState (LEDSTATE.ON);
                ClipHandler.playWait (k.getNumVal ());
                //Utils.delay (150);
                merlinGame.leds[k.getNumVal ()].setState (LEDSTATE.OFF);
            }
        }
    }

    @Override
    public void hitMe () {

    }
}
