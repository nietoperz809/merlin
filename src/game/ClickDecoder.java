package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static game.KEY.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class ClickDecoder {

    public ClickDecoder (MerlinGame mg)
    {
        mg.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                KEY idx = NOKEY;
                int x = e.getX(), y = e.getY();
                if (max(128, x) == min(165, x) && max(226, y) == min(260, y))
                    idx = KEY0;
                else if (max(73, x) == min(113, x) && max(279, y) == min(314, y))
                    idx = KEY1;
                else if (max(128, x) == min(165, x) && max(279, y) == min(314, y))
                    idx = KEY2;
                else if (max(182, x) == min(218, x) && max(279, y) == min(314, y))
                    idx = KEY3;
                else if (max(73, x) == min(113, x) && max(332, y) == min(368, y))
                    idx = KEY4;
                else if (max(128, x) == min(165, x) && max(332, y) == min(368, y))
                    idx = KEY5;
                else if (max(182, x) == min(218, x) && max(332, y) == min(368, y))
                    idx = KEY6;
                else if (max(73, x) == min(113, x) && max(386, y) == min(422, y))
                    idx = KEY7;
                else if (max(128, x) == min(165, x) && max(386, y) == min(422, y))
                    idx = KEY8;
                else if (max(182, x) == min(218, x) && max(386, y) == min(422, y))
                    idx = KEY9;
                else if (max(128, x) == min(165, x) && max(439, y) == min(476, y))
                    idx = KEY10;
                else if (max(99, x) == min(132, x) && max(545, y) == min(575, y))
                    idx = NEWGAME;
                else if (max(158, x) == min(191, x) && max(546, y) == min(573, y))
                    idx = SAMEGAME;
                else if (max(105, x) == min(133, x) && max(598, y) == min(626, y))
                    idx = HITME;
                else if (max(157, x) == min(184, x) && max(599, y) == min(626, y))
                    idx = COMPTURN;
                if (idx != NOKEY)
                    mg.buttonClicked(idx);
            }
        });
    }
}
