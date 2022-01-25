package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.util.concurrent.CountDownLatch;

class MyClip {
    private Clip cl;
    private AudioInputStream ais;
    private String _name;

    public MyClip () {

    }

    public void init (String name) {
        _name = name;
    }

    private void _init (String name) throws Exception {
        cl = AudioSystem.getClip ();
        ais = AudioSystem.getAudioInputStream (Utils.getResource (_name));
    }

    public void play () {
        try {
            _init (_name);
            cl.open (ais);
            cl.stop ();
            cl.setMicrosecondPosition (0);
            cl.start ();
        } catch (Exception e) {
            System.err.println (e.getMessage ());
        }
    }

    public void playSync () {
        CountDownLatch syncLatch = new CountDownLatch (1);
        try {
            _init (_name);
            //cl = AudioSystem.getClip ();
            cl.open (ais);
            cl.addLineListener (e -> {
                if (e.getType () == LineEvent.Type.STOP) {
                    syncLatch.countDown ();
                }
            });
            cl.start ();
            syncLatch.await ();
            //cl.close ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}

public class ClipHandler {
    public static final int MON = 11;
    public static final int BEGIN = 12;
    public static final int WIN = 13;
    public static final int BUZZ = 14;
    public static final int INIT = 15;
    public static final int LOSE = 16;
    public static final int X = 17;
    public static final int O = 18;
    public static final int TIE = 19;
    private static final MyClip[] clips = new MyClip[20];   // 4 = fail sound

    static {
        for (int s = 0; s < clips.length; s++) {
            clips[s] = new MyClip ();
        }
        for (int s = 0; s < 11; s++) {
            String name = "m" + s + ".wav";
            clips[s].init (name);
        }
        clips[WIN].init ("mWin.wav");
        clips[MON].init ("mOn.wav");
        clips[BUZZ].init ("mBuzz.wav");
        clips[INIT].init ("init.wav");
        clips[BEGIN].init ("mBegin.wav");
        clips[LOSE].init ("mLose.wav");
        clips[X].init ("mX.wav");
        clips[O].init ("mO.wav");
        clips[TIE].init ("mTie.wav");
    }

    public static void playWait (int num) {
        clips[num].playSync ();
    }

    public static void play (int num) {
        clips[num].play ();
    }
}
