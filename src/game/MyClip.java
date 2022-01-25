package game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.util.concurrent.CountDownLatch;

class MyClip {
    private String _name;

    public MyClip () {

    }

    public void setResourceName (String name) {
        _name = name;
    }

    private Clip _init (String name) throws Exception {
        Clip cl = AudioSystem.getClip ();
        cl.open (AudioSystem.getAudioInputStream (Utils.getResource (_name)));
        return cl;
    }

    public void play () {
        try {
            _init (_name).start ();
        } catch (Exception e) {
            System.err.println (e.getMessage ());
        }
    }

    public void playSync () {
        CountDownLatch syncLatch = new CountDownLatch (1);
        try {
            Clip cl = _init (_name);
            cl.addLineListener (e -> {
                if (e.getType () == LineEvent.Type.STOP) {
                    syncLatch.countDown ();
                }
            });
            cl.start ();
            syncLatch.await ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}
