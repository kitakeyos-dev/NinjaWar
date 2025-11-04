package ninjawar.mylib;

import com.badlogic.gdx.Gdx;
import com.teamobi.ninjawar.NinjaWar;
import ninjawar.myscr.MapScr;

public class mSystem {
    public static byte LANGUAGE = 0;
    public static String android_pack = "";
    public static byte curINAPP = 0;
    public static boolean isDirect = false;
    public static boolean isPC = false;
    public static boolean isTest = false;
    public static boolean isTest2 = false;
    public static byte maxINAPP = 5;

    public static void isStopReadMessage() {
    }

    public static void openUrl(String url) {
        try {
            Gdx.net.openURI(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static void paintFlyText(mGraphics g) {
        MapScr.textFly.paint(g);
    }

    public static float get_realWidth() {
        return (float) NinjaWar.getW();
    }

    public static float get_realHeight() {
        return (float) NinjaWar.getH();
    }
}
