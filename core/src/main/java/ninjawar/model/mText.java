package ninjawar.model;

import ninjawar.mymain.NinjaMidlet;

public class mText {
    static {
        loadLanguague((byte) 0);
    }

    public static void loadLanguague(byte newLanguage) {
        NinjaMidlet.language = newLanguage;
    }
}
