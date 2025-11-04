package ninjawar.myscr;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
import ninjawar.message.SendMessage;
import ninjawar.model.DetailText;
import ninjawar.model.InteractObjModel;
import ninjawar.model.PartySearch;
import ninjawar.model.mCmd;
import ninjawar.model.mText;
import ninjawar.mylib.FileData;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.LibSysCustom;
import ninjawar.mylib.ObjMap;
import ninjawar.mylib.Rms;
import ninjawar.mylib.VectorCustom;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.NinjaMidlet;
import org.json.JSONObject;

public class Res {
    public static String[] BG_COLOR = {"\u001b[40m", "\u001b[41m", "\u001b[42m", "\u001b[43m", "\u001b[44m", "\u001b[45m", "\u001b[46m", "\u001b[47m"};
    public static String[] LOG_CAT = {"<color=#ff0000ff>[  LOG_CAT  ]</color>", "<color=#ff0000ff>[LOG_SESSION]</color>", "<color=#ffff00ff>[LOG_SESSION]</color>", "<color=#ff0000ff>[LOG_MOBILE ]</color>", ""};
    public static String[] TEXT_COLOR = {"\u001b[30m", "\u001b[31m", "\u001b[32m", "\u001b[33m", "\u001b[34m", "\u001b[35m", "\u001b[36m", "\u001b[37m", "\u001b[0m"};
    public static String colorKeyFirst = "~";
    public static String colorKeyLast = "~";
    private static short[] cos;
    public static VectorCustom debug = new VectorCustom("");
    public static Random r = new Random();
    private static short[] sin = {0, 18, 36, 54, 71, 89, 107, 125, 143, 160, 178, 195, 213, 230, 248, 265, 282, 299, 316, 333, 350, 367, 384, 400, 416, 433, 449, 465, 481, 496, 512, 527, 543, 558, 573, 587, 602, 616, 630, 644, 658, 672, 685, 698, 711, 724, 737, 749, 761, 773, 784, 796, 807, 818, 828, 839, 849, 859, 868, 878, 887, 896, 904, 912, 920, 928, 935, 943, 949, 956, 962, 968, 974, 979, 984, 989, 994, 998, 1002, 1005, 1008, 1011, 1014, 1016, 1018, 1020, 1022, 1023, 1023, 1024, 1024};
    private static int[] tan;

    public static void init() {
        cos = new short[91];
        tan = new int[91];
        for (int i = 0; i <= 90; i++) {
            short[] sArr = cos;
            short[] sArr2 = sin;
            short s = sArr2[90 - i];
            sArr[i] = s;
            if (s == 0) {
                tan[i] = Integer.MAX_VALUE;
            } else {
                tan[i] = (sArr2[i] << 10) / s;
            }
        }
    }

    public static void updateOnScreenDebug() {
        long curTime = mSystem.currentTimeMillis();
        if (SendMessage.curCheckController - curTime > 5000) {
            SendMessage.gI().sendCheckController();
        }
        if (SendMessage.curCheckMap - curTime > 5000) {
            SendMessage.gI().sendCheckMap();
        }
    }

    public static String replace(String _text, String _searchStr, String _replacementStr) {
        StringBuffer sb = new StringBuffer();
        int searchStringPos = _text.indexOf(_searchStr);
        int startPos = 0;
        int searchStringLength = _searchStr.length();
        while (searchStringPos != -1) {
            sb.append(_text.substring(startPos, searchStringPos));
            sb.append(_replacementStr);
            startPos = searchStringPos + searchStringLength;
            searchStringPos = _text.indexOf(_searchStr, startPos);
        }
        sb.append(_text.substring(startPos, _text.length()));
        return sb.toString();
    }

    public static int random(int a) {
        return r.nextInt(a);
    }

    public static int random(int a, int b) {
        return ((int) (Math.random() * ((double) ((b - a) + 1)))) + a;
    }

    public static int distance(int x, int y) {
        return abs(x - y);
    }

    public static int distance(int x1, int y1, int x2, int y2) {
        return sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

    public static int sqrt(int a) {
        int x1;
        if (a <= 0) {
            return 0;
        }
        int x = (a + 1) / 2;
        do {
            x1 = x;
            x = (x / 2) + (a / (x * 2));
        } while (Math.abs(x1 - x) > 1);
        return x;
    }

    public static int rnd(int a) {
        return r.nextInt(a);
    }

    public static int abs(int i) {
        return i > 0 ? i : -i;
    }

    public static void outz(int indexBgColor, String s) {
        if (NinjaMidlet.isDebugEclipse) {
            PrintStream printStream = System.out;
            printStream.println(BG_COLOR[indexBgColor] + s + TEXT_COLOR[8]);
        }
    }

    public static void debugPurchase(String s) {
    }

    public static void outz(String s) {
        if (NinjaMidlet.isDebugEclipse) {
            PrintStream printStream = System.out;
            printStream.println(TEXT_COLOR[2] + s + TEXT_COLOR[8]);
        }
    }

    public static void err(String s) {
        if (mSystem.isTest) {
            System.err.println(s);
        }
    }

    public static String[] split(String original, String separator, int count) {
        String[] result;
        int index = original.indexOf(separator);
        if (index >= 0) {
            result = split(original.substring(separator.length() + index), separator, count + 1);
        } else {
            result = new String[(count + 1)];
            index = original.length();
        }
        result[count] = original.substring(0, index);
        return result;
    }

    public static String[] splitByKey(String original, String key) {
        if (original == null) {
            return null;
        }
        try {
            return original.split(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] loadDataFromFile(String path, byte type) {
        switch (type) {
            case 0:
                Files files = Gdx.files;
                return files.internal(LibSysCustom.res + path).readBytes();
            case 1:
                try {
                    return Gdx.files.local(path).readBytes();
                } catch (Exception e) {
                    return null;
                }
            default:
                return null;
        }
    }

    public static String getPathMoreWhenRemoveFileName(String path) {
        try {
            if (path.lastIndexOf("/") > -1) {
                return path.substring(0, path.lastIndexOf("/"));
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean checkNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getTextFromClipBoard() {
        try {
            if (!NinjaMidlet.isPC) {
                return "";
            }
            Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
            if (!c.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                return "";
            }
            System.out.println();
            return (String) c.getData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void setTextToClipBoard(String text) {
        if (NinjaMidlet.isPC) {
            StringSelection selection = new StringSelection(text);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
        }
    }

    public static void logChar(String logFromCMD, Player chac) {
        outz(3, logFromCMD + " LOG CHARACTER Char IDDB:" + chac.charID + "_Name:" + chac.cName + "_dir:" + chac.cdir + "_speedX:" + chac.cspeed + "_speedY:" + chac.cyspeed + "_cx:" + chac.cx + "_cy:" + chac.cy + "_cHP:" + chac.cHP + "_cHPFull:" + chac.cHPFull + "_cMP:" + chac.cMP + "_cMPFull:" + chac.cMPFull + "_cSP:" + chac.cSP + "_cSPFull:" + chac.cSPFull + "_action:" + chac.getAction() + "_isDie:" + chac.isDie);
        if (chac.cx == 1750 && chac.cy == 1088) {
            for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
                outz(3, "Stack trace: " + element.toString());
            }
        }
        if (chac.idPart != null) {
            for (int i = 0; i < chac.idPart.length; i++) {
                outz(1, "idPart của chart:" + chac.idPart[i]);
            }
        }
    }

    public static void logBgItem(ObjMap bi) {
        outz(4, " LOG BgItem:" + bi.id + "_idImage:" + bi.idImage + "_layer:" + bi.layer + "_x:" + (bi.x / MyTile.size) + "_y:" + (bi.y / MyTile.size) + "_dx:" + bi.dx + "_dy:" + bi.dy);
    }

    public static short[] getPartOnlyUsed(short[] idParts) {
        Vector<Short> vecTemps = new Vector<>();
        for (int i = 0; i < idParts.length; i++) {
            if (i == 0 || i == 1 || i == 5) {
                vecTemps.add(Short.valueOf(idParts[i]));
            }
        }
        short[] result = new short[vecTemps.size()];
        for (int i2 = 0; i2 < vecTemps.size(); i2++) {
            result[i2] = vecTemps.get(i2).shortValue();
        }
        return result;
    }

    public static int convertStringToNumber(String numText) {
        try {
            return Integer.parseInt(numText);
        } catch (Exception e) {
            try {
                return (int) Long.decode(numText).longValue();
            } catch (Exception e2) {
                return -1;
            }
        }
    }

    public static int fixSizeTagFrameUp(int numStandard, int w, FrameImage frame) {
        int mod = w % ((int) frame.frameWidth);
        if (mod == 0 || mod > numStandard) {
            return w;
        }
        return w + numStandard;
    }

    public static int fixSizeTagFrameDown(int numStandard, int w, FrameImage frame) {
        int mod = w % ((int) frame.frameWidth);
        if (mod == 0 || mod > numStandard) {
            return w;
        }
        return w - numStandard;
    }

    public static boolean isHaveColorInStringServer(String string) {
        if (string.indexOf(colorKeyFirst) <= -1 || string.indexOf(colorKeyLast) <= -1) {
            return false;
        }
        return true;
    }

    public static String[] getArrStringByKey(String str, String keyFirst, String keyLast) {
        String[] arr = new String[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = "";
        }
        int indexFirst = str.indexOf(keyFirst);
        int indexLast = str.indexOf(keyLast);
        if (keyFirst.equals(keyLast)) {
            indexLast = str.indexOf(keyLast, str.indexOf(keyLast) + 1);
        }
        if (indexFirst <= -1 || indexLast <= -1) {
            arr[0] = str;
        } else {
            arr[0] = str.substring(0, indexFirst);
            String temp = str.substring(indexFirst + 1, indexLast);
            arr[1] = str.substring(indexFirst + 1, indexLast);
            arr[2] = str.substring(indexLast + 1, str.length());
            arr[3] = temp.substring(0, temp.indexOf(" "));
            arr[1] = arr[1].substring(arr[3].length() + 1, arr[1].length());
        }
        return arr;
    }

    public static VectorCustom formatStringFromServer(String str) {
        return formatStringFromServer(str, new DetailText().fontText);
    }

    public static VectorCustom formatStringFromServer(String str, mFont fontPaint) {
        VectorCustom vec = new VectorCustom();
        String[] arr = getArrStringByKey(str, colorKeyFirst, colorKeyLast);
        try {
            if (arr[0].length() > 0) {
                for (int i = 0; i < arr[0].length(); i++) {
                    DetailText detailText = new DetailText();
                    char ch = arr[0].charAt(i);
                    detailText.setColor(-1, fontPaint);
                    detailText.setText(String.valueOf(ch));
                    vec.addElement(detailText);
                }
            }
            for (int i2 = 0; i2 < arr[1].length(); i2++) {
                char ch2 = arr[1].charAt(i2);
                DetailText detailText2 = new DetailText();
                detailText2.setColor(convertStringToNumber(arr[3]), fontPaint);
                detailText2.setText(String.valueOf(ch2));
                vec.addElement(detailText2);
            }
            while (arr[2].indexOf(colorKeyFirst) > -1 && arr[2].indexOf(colorKeyLast) > -1) {
                String[] arrTemp = getArrStringByKey(arr[2], colorKeyFirst, colorKeyLast);
                if (arrTemp[0].length() > 0) {
                    for (int i3 = 0; i3 < arrTemp[0].length(); i3++) {
                        char ch3 = arrTemp[0].charAt(i3);
                        DetailText detail = new DetailText();
                        detail.setColor(-1, fontPaint);
                        detail.setText(String.valueOf(ch3));
                        vec.addElement(detail);
                    }
                }
                for (int i4 = 0; i4 < arrTemp[1].length(); i4++) {
                    char ch4 = arrTemp[1].charAt(i4);
                    DetailText detail2 = new DetailText();
                    detail2.setColor(convertStringToNumber(arrTemp[3]), fontPaint);
                    detail2.setText(String.valueOf(ch4));
                    vec.addElement(detail2);
                }
                arr[2] = arrTemp[2];
            }
            int indexLast = str.lastIndexOf(colorKeyLast);
            String tempLast = str.substring(indexLast + 1, str.length());
            if (indexLast > -1 && tempLast.length() > 0) {
                for (int i5 = 0; i5 < tempLast.length(); i5++) {
                    char ch5 = tempLast.charAt(i5);
                    DetailText last = new DetailText();
                    last.setColor(-1, fontPaint);
                    last.setText(String.valueOf(ch5));
                    vec.addElement(last);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vec;
    }

    public static String[] splitStringByFontGetSpace(String str, int maxW, mFont font) {
        int numRowContent = font.getWidth(str) % maxW == 0 ? font.getWidth(str) / maxW : (font.getWidth(str) / maxW) + 2;
        if (font.getWidth(str) < maxW) {
            numRowContent = 1;
        }
        String[] result = new String[numRowContent];
        int count = 0;
        while (str.length() > 0) {
            int indexTemp = 0;
            if (font.getWidth(str) >= maxW || count >= result.length) {
                for (int i = 0; i < str.length(); i++) {
                    if (font.getWidth(str.substring(0, i)) < maxW) {
                        indexTemp++;
                    }
                }
                String text = str.substring(0, indexTemp);
                if (text.lastIndexOf(" ") > -1 && count < result.length) {
                    result[count] = text.substring(0, text.lastIndexOf(" "));
                } else if (count < result.length) {
                    result[count] = text;
                }
                if (count < result.length) {
                    str = str.substring(str.indexOf(result[count]) + result[count].length(), str.length());
                }
            } else {
                result[count] = str;
                str = "";
            }
            if (count < result.length && result[count].substring(0, 1).equals(" ")) {
                result[count] = result[count].substring(0, result[count].length());
            }
            count++;
            if (count >= result.length) {
                break;
            }
        }
        if (result.length <= 0 || result[result.length - 1] != null) {
            return result;
        }
        return (String[]) Arrays.copyOf(result, result.length - 1);
    }

    public static String findStringMax(Vector<String> strs) {
        if (strs.size() <= 0) {
            return "";
        }
        String maxStr = strs.get(0);
        Iterator<String> it = strs.iterator();
        while (it.hasNext()) {
            String str = it.next();
            if (str.length() > maxStr.length()) {
                maxStr = str;
            }
        }
        return maxStr;
    }

    public static String findStringMax(InteractObjModel[] strs) {
        if (strs == null) {
            return null;
        }
        String maxStr = "";
        for (InteractObjModel str : strs) {
            if (str.name.length() > maxStr.length()) {
                maxStr = str.name;
            }
        }
        return maxStr;
    }

    public static String findStringMax(String[] strs) {
        if (strs == null) {
            return null;
        }
        String maxStr = strs[0];
        for (String str : strs) {
            if (str.length() > maxStr.length()) {
                maxStr = str;
            }
        }
        return maxStr;
    }

    public static boolean checkNumInArr(short num, short[] arr) {
        for (short value : arr) {
            if (value == num) {
                return true;
            }
        }
        return false;
    }

    public static int round(float num) {
        if (num - ((float) ((int) num)) > 0.1f) {
            return ((int) num) + 1;
        }
        outz("Vào hàm làm tròn");
        return (int) num;
    }

    public static String replace(String text, String key, int[] params) {
        for (int param : params) {
            text = String.valueOf(text.replaceFirst(key, "" + param));
        }
        return text;
    }

    public static float round(float value, int precision) {
        int scale = (int) Math.pow(10.0d, (double) precision);
        return ((float) Math.round(((float) scale) * value)) / ((float) scale);
    }

    public static String formatMoney(int money) {
        StringBuilder sb;
        StringBuilder sb2;
        if (money >= 1000000000) {
            String first = (money + "").substring(0, (money + "").length() - 9);
            String last = (money + "").substring(first.length(), first.length() + 3);
            if (last.equals("000")) {
                sb2 = new StringBuilder();
                sb2.append(first);
                sb2.append("B");
            } else {
                sb2 = new StringBuilder();
                sb2.append(first);
                sb2.append(".");
                sb2.append(last);
                sb2.append("M");
            }
            String result = sb2.toString();
            if (money % 1000000000 != 0) {
                return result;
            }
            return (money / 1000000000) + "B";
        } else if (money >= 1000000) {
            String first2 = (money + "").substring(0, (money + "").length() - 6);
            String last2 = (money + "").substring(first2.length(), first2.length() + 3);
            if (last2.equals("000")) {
                sb = new StringBuilder();
                sb.append(first2);
                sb.append("M");
            } else {
                sb = new StringBuilder();
                sb.append(first2);
                sb.append(".");
                sb.append(last2);
                sb.append("K");
            }
            String result2 = sb.toString();
            if (money % 1000000 != 0) {
                return result2;
            }
            return (money / 1000000) + "M";
        } else if (money >= 1000) {
            String first3 = (money + "").substring(0, (money + "").length() - 3);
            return first3 + "." + (money + "").substring(first3.length(), first3.length() + 3);
        } else {
            return money + "";
        }
    }

    public static String formatMoney2(int money) {
        if (money >= 1000000000) {
            return (money / 1000000000) + "B";
        } else if (money >= 1000000) {
            return (money / 1000000) + "M";
        } else if (money >= 1000) {
            return (money / 1000) + "K";
        } else {
            return money + "";
        }
    }

    public static short[] cloneArrShort(short[] arr) {
        short[] result = new short[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static Vector<PartySearch> findListCharByNameOrId(String nameOrId, Vector<PartySearch> listFind) {
        Vector<PartySearch> result = new Vector<>();
        if (nameOrId.equals("")) {
            return listFind;
        }
        if (listFind == null) {
            return result;
        }
        int id = convertStringToNumber(nameOrId);
        if (id != -1) {
            Iterator<PartySearch> it = listFind.iterator();
            while (it.hasNext()) {
                PartySearch c = it.next();
                if (c.playerId == id) {
                    result.addElement(c);
                }
            }
        }
        Iterator<PartySearch> it2 = listFind.iterator();
        while (it2.hasNext()) {
            PartySearch c2 = it2.next();
            if (c2.name.indexOf(nameOrId) > -1) {
                result.addElement(c2);
            }
        }
        return result;
    }

    public static DetailText[] getTextDocInFrame(mFont fontPaint, int xF, int yF, int wF, int hF, int margin, String text) {
        DetailText[] result = new DetailText[text.length()];
        int sumH = (result.length * fontPaint.getHeight()) + ((result.length - 1) * margin);
        for (int i = 0; i < result.length; i++) {
            result[i] = new DetailText();
            result[i].text = String.valueOf(text.charAt(i));
            result[i].x = (wF / 2) + xF;
            result[i].y = ((hF - sumH) / 2) + yF + ((fontPaint.getHeight() + margin) * i);
        }
        return result;
    }

    public static VectorCustom orderVectorIMapObject(VectorCustom obj) {
        if (obj == null) {
            return obj;
        }
        try {
            int a = obj.size();
            for (int i = 0; i < a - 1; i++) {
                IMapObject o = (IMapObject) obj.elementAt(i);
                for (int j = i + 1; j < a; j++) {
                    IMapObject o1 = (IMapObject) obj.elementAt(j);
                    if (o.getY() > o1.getY()) {
                        obj.setElementAt(o, j);
                        obj.setElementAt(o1, i);
                        o = o1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String formatNameProductPurchase(String nameProduct) {
        int start = nameProduct.indexOf("(");
        int end = nameProduct.indexOf(")");
        if (start <= 0 || end <= 0) {
            return nameProduct;
        }
        return nameProduct.substring(0, start);
    }

    public static JSONObject getJSONFromString(String jsonStr) {
        try {
            return new JSONObject(jsonStr);
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }

    public static float getValuePan(int disMax, int disCur, byte dir) {
        float percent = (((float) disCur) * 1.0f) / ((float) disMax);
        if (disCur > disMax) {
            percent = 0.0f;
        }
        if (dir == -1) {
            return 1.0f * percent;
        }
        if (dir == 1) {
            return -1.0f * percent;
        }
        return 0.0f;
    }

    public static void createFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static byte[] getByteArrayFromIntArray(int[] data) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeInt(data.length);
            for (int writeInt : data) {
                dos.writeInt(writeInt);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int[] getIntArrayFromByteArray(byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            DataInputStream d = new DataInputStream(new ByteArrayInputStream(data));
            int[] result = new int[d.readInt()];
            for (int i = 0; i < result.length; i++) {
                result[i] = d.readInt();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkValueExitsInIntegerArr(int[] arr, int num) {
        for (int i : arr) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }

    public static String getTextRandom(int numText) {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < numText) {
            salt.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".charAt((int) (rnd.nextFloat() * ((float) "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".length()))));
        }
        return salt.toString();
    }

    public static String getStringResources(Field fieldFind) {
        if (!fieldFind.getType().isAssignableFrom(String.class)) {
            return null;
        }
        Class<mText> types = mText.class;
        try {
            Field field = types.getDeclaredField(fieldFind.getName());
            field.setAccessible(true);
            return String.valueOf(field.get(types));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] getStringArrayResources(Field fieldFind) {
        if (!fieldFind.getType().isAssignableFrom(String[].class)) {
            return null;
        }
        String[] result = null;
        Class<mText> types = mText.class;
        try {
            Field field = types.getDeclaredField(fieldFind.getName());
            field.setAccessible(true);
            Object[] array = (String[]) field.get(types);
            if (array != null) {
                result = new String[array.length];
                for (int i = 0; i < array.length; i++) {
                    result[i] = String.valueOf(array[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkSoundExist(short idIcon, short type, short subType) {
        String path = FileData.getPathFromIdAndType(idIcon, type, subType);
        return isExists(path + ".wav", path);
    }

    public static boolean checkMusicExist(short idIcon, short type, short subType) {
        String path = FileData.getPathFromIdAndType(idIcon, type, subType);
        return isExists(path + ".mp3", path);
    }

    public static boolean isExists(String pathInterval, String pathRMS) {
        boolean result = Gdx.files.internal(pathInterval).exists();
        if (result) {
            return result;
        }
        if (NinjaMidlet.isPC) {
            return new File(Rms.pathPCSave + pathRMS).exists();
        }
        Files files = Gdx.files;
        return files.local("data/" + pathRMS).exists();
    }
}
