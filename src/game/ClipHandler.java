package game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.util.concurrent.CountDownLatch;

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
            clips[s].setResourceName (name);
        }
        clips[WIN].setResourceName ("mWin.wav");
        clips[MON].setResourceName ("mOn.wav");
        clips[BUZZ].setResourceName ("mBuzz.wav");
        clips[INIT].setResourceName ("init.wav");
        clips[BEGIN].setResourceName ("mBegin.wav");
        clips[LOSE].setResourceName ("mLose.wav");
        clips[X].setResourceName ("mX.wav");
        clips[O].setResourceName ("mO.wav");
        clips[TIE].setResourceName ("mTie.wav");
    }

    public static void playWait (int num) {
        clips[num].playSync ();
    }

    public static void play (int num) {
        clips[num].play ();
    }
}
