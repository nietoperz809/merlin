package game;

import java.util.HashMap;

public class PredefSongs {
    static HashMap<String, int[]> map = new HashMap<>();

    static {
        map.put ("AROVIN'", new int[] {6,7,0,5,7,6,
                0,4,6,5,4,3,3,4,5,6,9,7,5,4,0,3,0,2});
        map.put ("AULD LANG SYNE", new int[] {
                1,2,0, 0,2,2, 0,4,0, 3,0,0, 2,3,0, 4,0,2,
                0,0,2, 4,0,6, 0,7,0, 0,0,0, 0,7,0, 6,0,0,
                4,4,0, 2,0,3, 0,0,2, 3,0,4});
        map.put ("BEETHOVEN'S 9TH", new int[] {
                4,4,5, 6,6,5, 4,3,2, 2,3,4, 4,3,3, 0,4,4,
                5,6,6, 5,4,3, 2,2,3, 4,3,2, 2,0,3, 3,4,2,
                3,5,4, 2,3,5, 4,3,2, 3,1});
    }

    public static int[] getSong (String s) {
        return map.get (s);
    }
}
