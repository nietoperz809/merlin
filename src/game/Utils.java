package game;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class Utils
{
    public static void delay(int ms)
    {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
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
