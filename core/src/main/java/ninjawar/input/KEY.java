package ninjawar.input;

import ninjawar.mylib.HashTableCustom;
import ninjawar.mylib.MapKey;

public class KEY {
    public static int KEY_0;
    public static int KEY_1;
    public static int KEY_2;
    public static int KEY_3;
    public static int KEY_4;
    public static int KEY_5;
    public static int KEY_6;
    public static int KEY_7;
    public static int KEY_8;
    public static int KEY_9;
    public static int KEY_A;
    public static int KEY_B;
    public static int KEY_BACKSPACE;
    public static int KEY_C;
    public static int KEY_D;
    public static int KEY_DELETE;
    public static int KEY_DOWN;
    public static int KEY_E;
    public static int KEY_ENTER;
    public static int KEY_ESC;
    public static int KEY_F;
    public static int KEY_G;
    public static int KEY_H;
    public static int KEY_I;
    public static int KEY_J;
    public static int KEY_K;
    public static int KEY_L;
    public static int KEY_LEFT;
    public static int KEY_LSHIFT;
    public static int KEY_M;
    public static int KEY_N;
    public static int KEY_O;
    public static int KEY_P;
    public static int KEY_Q;
    public static int KEY_R;
    public static int KEY_RIGHT;
    public static int KEY_RSHIFT;
    public static int KEY_S;
    public static int KEY_SPACE;
    public static int KEY_T;
    public static int KEY_TAB;
    public static int KEY_U;
    public static int KEY_UP;
    public static int KEY_V;
    public static int KEY_W;
    public static int KEY_X;
    public static int KEY_Y;
    public static int KEY_Z;
    public int keyCode;
    public String name;

    static {
        KEY.KEY_A = 97;
        KEY.KEY_B = 98;
        KEY.KEY_C = 99;
        KEY.KEY_D = 100;
        KEY.KEY_E = 101;
        KEY.KEY_F = 102;
        KEY.KEY_G = 103;
        KEY.KEY_H = 104;
        KEY.KEY_I = 105;
        KEY.KEY_J = 106;
        KEY.KEY_K = 107;
        KEY.KEY_L = 108;
        KEY.KEY_M = 109;
        KEY.KEY_N = 110;
        KEY.KEY_O = 111;
        KEY.KEY_P = 112;
        KEY.KEY_Q = 113;
        KEY.KEY_R = 114;
        KEY.KEY_S = 115;
        KEY.KEY_T = 116;
        KEY.KEY_U = 117;
        KEY.KEY_V = 118;
        KEY.KEY_W = 119;
        KEY.KEY_X = 120;
        KEY.KEY_Y = 121;
        KEY.KEY_Z = 122;
        KEY.KEY_TAB = -26;
        KEY.KEY_ENTER = 5;
        KEY.KEY_SPACE = 32;
        KEY.KEY_LSHIFT = -30;
        KEY.KEY_RSHIFT = -31;
        KEY.KEY_0 = 48;
        KEY.KEY_1 = 49;
        KEY.KEY_2 = 50;
        KEY.KEY_3 = 51;
        KEY.KEY_4 = 52;
        KEY.KEY_5 = 53;
        KEY.KEY_6 = 54;
        KEY.KEY_7 = 55;
        KEY.KEY_8 = 56;
        KEY.KEY_9 = 57;
        KEY.KEY_UP = -1;
        KEY.KEY_DOWN = -2;
        KEY.KEY_LEFT = -3;
        KEY.KEY_RIGHT = -4;
        KEY.KEY_BACKSPACE = -8;
        KEY.KEY_DELETE = -9;
        KEY.KEY_ESC = -138;
    }

    public KEY() {
        this.name = "UNKNOW";
    }

    public static String getNameKey(final int n) {
        final HashTableCustom hkey = MapKey.hKEY;
        String s = "";
        final String s2 = (String) hkey.get(String.valueOf(n));
        if (s2 != null) {
            s = s2;
        }
        return s;
    }
}
