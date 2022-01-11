package game;

public enum KEY {
    NOKEY(-1),
    KEY0(0), KEY1(1), KEY2(2), KEY3(3), KEY4(4), KEY5(5),
    KEY6(6), KEY7(7), KEY8(8), KEY9(9), KEY10(10),
    NEWGAME(11), SAMEGAME(12), HITME(13), COMPTURN(14);

    private int numVal;

    KEY(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
