package game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import static game.Utils.delay;

public class ClipHandler {
    private static final Clip[] clips = new Clip[20];   // 4 = fail sound

    public static final int MON = 11;
    public static final int BEGIN = 12;
    public static final int WIN = 13;
    public static final int BUZZ = 14;
    public static final int INIT = 15;
    public static final int LOSE = 16;
    public static final int X = 17;
    public static final int O = 18;
    public static final int TIE = 19;

    static {
        try {
            for (int s = 0; s < clips.length; s++)
                clips[s] = AudioSystem.getClip();
            for (int s = 0; s < 11; s++) {
                String name = "m" + s + ".wav";
                clips[s].open(AudioSystem.getAudioInputStream(Utils.getResource(name)));
            }
            clips[WIN].open(AudioSystem.getAudioInputStream(Utils.getResource("mWin.wav")));
            clips[MON].open(AudioSystem.getAudioInputStream(Utils.getResource("mOn.wav")));
            clips[BUZZ].open(AudioSystem.getAudioInputStream(Utils.getResource("mBuzz.wav")));
            clips[INIT].open(AudioSystem.getAudioInputStream(Utils.getResource("init.wav")));
            clips[BEGIN].open(AudioSystem.getAudioInputStream(Utils.getResource("mBegin.wav")));
            clips[LOSE].open(AudioSystem.getAudioInputStream(Utils.getResource("mLose.wav")));
            clips[X].open(AudioSystem.getAudioInputStream(Utils.getResource("mX.wav")));
            clips[O].open(AudioSystem.getAudioInputStream(Utils.getResource("mO.wav")));
            clips[TIE].open(AudioSystem.getAudioInputStream(Utils.getResource("mTie.wav")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void playWait (int num) {
        play (num);
        while(clips[num].getMicrosecondLength() != clips[num].getMicrosecondPosition())
        {
        }
    }

    public static void play(int num) {
        try {
            clips[num].stop();
            clips[num].setMicrosecondPosition(0);
            clips[num].start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
