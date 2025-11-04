package ninjawar.screen;

import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;

public class mInfoDialog {
    public static long delay;
    public static boolean isLock;
    public static boolean isShow;
    private static String subtitke;
    private static String title;

    public static void show(String title2, String subtitle, int delay2) {
        if (title2 != null) {
            isShow = true;
            title = title2;
            subtitke = subtitle;
            delay = ((long) delay2) + mSystem.currentTimeMillis();
        }
    }

    public static void paint(mGraphics g) {
        if (isShow && !MapScr.isPaintAlert) {
            CanvasNinja.paintz.paintPopUp(CanvasNinja.hw - 75, 10, 150, 55, g);
            if (isLock) {
                CanvasNinja.paintShukiren((CanvasNinja.hw - (mFont.tahoma_8b.getWidth(title) / 2)) - 10, 10 + 28, g);
                mFont.tahoma_8b.drawString(g, title, CanvasNinja.hw + 5, 10 + 21, 2);
            } else if (subtitke != null) {
                mFont.tahoma_8b.drawString(g, title, CanvasNinja.hw, 10 + 13, 2);
                mFont.tahoma_7_green2.drawString(g, subtitke, CanvasNinja.hw, 10 + 30, 2);
            } else {
                mFont.tahoma_8b.drawString(g, title, CanvasNinja.hw, 10 + 21, 2);
            }
        }
    }

    public static void hide() {
        title = "";
        subtitke = null;
        isLock = false;
        delay = 0;
        isShow = false;
    }
}
