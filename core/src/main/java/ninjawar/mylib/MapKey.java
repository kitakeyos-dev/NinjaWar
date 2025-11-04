// Class Version: 8
package ninjawar.mylib;

import com.badlogic.gdx.Input;
import ninjawar.input.KEY;

public class MapKey
{
    private static HashTableCustom h;
    public static HashTableCustom hKEY;

    static {
        MapKey.h = new HashTableCustom();
        MapKey.hKEY = new HashTableCustom();
    }

    public static void load() {
        MapKey.h.put("A", 97);
        MapKey.h.put("B", 98);
        MapKey.h.put("C", 99);
        MapKey.h.put("D", 100);
        MapKey.h.put("E", 101);
        MapKey.h.put("F", 102);
        MapKey.h.put("G", 103);
        MapKey.h.put("H", 104);
        MapKey.h.put("I", 105);
        MapKey.h.put("J", 106);
        MapKey.h.put("K", 107);
        MapKey.h.put("L", 108);
        MapKey.h.put("M", 109);
        MapKey.h.put("N", 110);
        MapKey.h.put("O", 111);
        MapKey.h.put("P", 112);
        MapKey.h.put("Q", 113);
        MapKey.h.put("R", 114);
        MapKey.h.put("S", 115);
        MapKey.h.put("T", 116);
        MapKey.h.put("U", 117);
        MapKey.h.put("V", 118);
        MapKey.h.put("W", 119);
        MapKey.h.put("X", 120);
        MapKey.h.put("Y", 121);
        MapKey.h.put("Z", 122);
        MapKey.h.put("0", 48);
        MapKey.h.put("1", 49);
        MapKey.h.put("2", 50);
        MapKey.h.put("3", 51);
        MapKey.h.put("4", 52);
        MapKey.h.put("5", 53);
        MapKey.h.put("6", 54);
        MapKey.h.put("7", 55);
        MapKey.h.put("8", 56);
        MapKey.h.put("9", 57);
        MapKey.h.put("SPACE", 32);
        MapKey.h.put("EQUALS", -25);
        MapKey.h.put("TAB", -26);
        MapKey.h.put("MINUS", 45);
        MapKey.h.put("UP", -1);
        MapKey.h.put("DOWN", -2);
        MapKey.h.put("LEFT", -3);
        final HashTableCustom h = MapKey.h;
        final Integer value = -4;
        h.put("RIGHT", value);
        MapKey.h.put("DPAD_RIGHT", value);
        final HashTableCustom h2 = MapKey.h;
        final Integer value2 = -8;
        h2.put("BACKSPACE", value2);
        MapKey.h.put("PERIOD", 46);
        MapKey.h.put("AT", 64);
        MapKey.h.put("DELETE", value2);
        MapKey.h.put("ENTER", 5);
        MapKey.h.put("FORWARD DELETE", -9);
        MapKey.h.put("L-SHIFT", -30);
        MapKey.h.put("R-SHIFT", -31);
        MapKey.h.put("HOME", -32);
        MapKey.h.put("END", -33);
        MapKey.h.put("F1", -101);
        MapKey.h.put("F2", -102);
        MapKey.h.put("F3", -103);
        MapKey.h.put("F4", -104);
        MapKey.h.put("F5", -105);
        MapKey.h.put("F6", -106);
        MapKey.h.put("F7", -107);
        MapKey.h.put("F8", -108);
        MapKey.h.put("F9", -109);
        MapKey.h.put("F10", -110);
        MapKey.h.put("F11", -111);
        MapKey.h.put("F12", -112);
        MapKey.h.put("PRINT", -113);
        MapKey.h.put("SCROLL LOCK", -114);
        MapKey.h.put("PAUSE", -115);
        MapKey.h.put("`", -116);
        MapKey.h.put("-", -117);
        MapKey.h.put("=", -118);
        MapKey.h.put("[", -119);
        MapKey.h.put("]", -120);
        MapKey.h.put("\\", -121);
        MapKey.h.put(";", -122);
        MapKey.h.put("'", -123);
        MapKey.h.put(",", -124);
        MapKey.h.put(".", -125);
        MapKey.h.put("/", -126);
        MapKey.h.put("L-ALT", -127);
        MapKey.h.put("R-ALT", -128);
        MapKey.h.put("L-CTRL", -129);
        MapKey.h.put("R-CTRL", -130);
        MapKey.h.put("SYM", -131);
        MapKey.h.put("CAPS LOCK", -133);
        MapKey.h.put("INSERT", -134);
        MapKey.h.put("PAGE UP", -136);
        MapKey.h.put("PAGE DOWN", -137);
        MapKey.h.put("ESCAPE", -138);
    }

    public static KEY map(int intValue) {
        try {
            final String upperCase = Input.Keys.toString(intValue).toUpperCase();
            intValue = (int)MapKey.h.get(upperCase);
            final KEY key = new KEY();
            key.keyCode = intValue;
            key.name = upperCase;
            return key;
        }
        catch (Exception ex) {
            return null;
        }
    }

    public static KEY mapController(int intValue) {
        final int n = -1;
        switch (intValue) {
            default: {
                intValue = n;
                break;
            }
            case 15: {
                intValue = 19;
                break;
            }
            case 14: {
                intValue = 22;
                break;
            }
            case 13: {
                intValue = 21;
                break;
            }
            case 12: {
                intValue = 20;
                break;
            }
            case 11: {
                intValue = 42;
                break;
            }
            case 10: {
                intValue = 7;
                break;
            }
            case 9: {
                intValue = 67;
                break;
            }
            case 8: {
                intValue = 30;
                break;
            }
            case 3: {
                intValue = 11;
                break;
            }
            case 2: {
                intValue = 10;
                break;
            }
            case 1: {
                intValue = 9;
                break;
            }
            case 0: {
                intValue = 8;
                break;
            }
        }
        try {
            final String upperCase = Input.Keys.toString(intValue).toUpperCase();
            intValue = (int)MapKey.h.get(upperCase);
            final KEY key = new KEY();
            key.keyCode = intValue;
            key.name = upperCase;
            return key;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
