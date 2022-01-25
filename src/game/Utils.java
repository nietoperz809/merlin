package game;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Utils
{
    private static final Random rnd = new Random ();

    public static int random (int min, int max) {
        return rnd.nextInt(max - min + 1) + min;
    }

    public static void delay(int ms)
    {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    public static String getResourceText (String name) {
        InputStream is = Utils.getResource (name);
        return new BufferedReader (new InputStreamReader (Objects.requireNonNull (is)))
                .lines ().collect (Collectors.joining ("\n"));
    }

    public static InputStream getResource (String name)
    {
        InputStream is = ClassLoader.getSystemResourceAsStream (name);
        if (is == null)
        {
            System.out.println ("could not load: "+name);
            return null;
        }
        return new BufferedInputStream (is);
    }
}
