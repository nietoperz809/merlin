package game.subgame;

import game.*;

import java.util.ArrayList;

import static game.KEY.KEY0;

public class MusicMachine extends Subgame {

    private ArrayList<KEY> list = new ArrayList<> ();

    public MusicMachine (MerlinGame mg) {
        super(mg);
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
        } else if (id == KEY.COMPTURN) {
            for (KEY k : list) {
                if (k == KEY0)
                    Utils.delay (100);
                else
                    ClipHandler.play (k.getNumVal ());
                Utils.delay (200);
            }
        }
    }
}
