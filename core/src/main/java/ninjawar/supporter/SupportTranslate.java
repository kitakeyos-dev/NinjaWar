package ninjawar.supporter;

import java.util.HashMap;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.Res;

public class SupportTranslate {
    public static String KEY = "|";
    public static byte TYPE_CLASS_STRING = 0;
    public static byte TYPE_CLASS_STRING_ARRAY = 1;
    public static HashMap<String, SupportTranslate> hashMapLanguage = new HashMap<>();
    public int id;
    public byte typeClass;
    public String value1 = null;
    public String value2 = null;
    public String value3 = null;
    public String[] values1 = null;
    public String[] values2 = null;
    public String[] values3 = null;
    public String variable = "";

    public SupportTranslate() {
    }

    public SupportTranslate(String variable2, byte typeClass2) {
        this.variable = variable2;
        this.typeClass = typeClass2;
    }

    public String getValue1() {
        String result = this.value1;
        if (this.values1 != null) {
            result = "";
            for (int i = 0; i < this.values1.length; i++) {
                result = result + this.values1[i];
                if (i != this.values1.length - 1) {
                    result = result + KEY;
                }
            }
        }
        return result;
    }

    public String getValue2() {
        String result = this.value2;
        if (this.values2 != null) {
            result = "";
            for (int i = 0; i < this.values2.length; i++) {
                result = result + this.values2[i];
                if (i != this.values2.length - 1) {
                    result = result + KEY;
                }
            }
        }
        return result;
    }

    public String getValue3() {
        String result = this.value3;
        if (this.values3 != null) {
            result = "";
            for (int i = 0; i < this.values3.length; i++) {
                result = result + this.values3[i];
                if (i != this.values3.length - 1) {
                    result = result + KEY;
                }
            }
        }
        return result;
    }

    public void setValue1(String value12) {
        if (value12.contains(KEY)) {
            this.values1 = Res.split(value12, KEY, 0);
            this.value1 = null;
            this.typeClass = TYPE_CLASS_STRING_ARRAY;
            return;
        }
        this.values1 = null;
        this.value1 = value12;
        this.typeClass = TYPE_CLASS_STRING;
    }

    public void setValue2(String value22) {
        if (value22.contains(KEY)) {
            this.values2 = Res.split(value22, KEY, 0);
            this.value2 = null;
            this.typeClass = TYPE_CLASS_STRING_ARRAY;
            return;
        }
        this.values2 = null;
        this.value2 = value22;
        this.typeClass = TYPE_CLASS_STRING;
    }

    public void setValue3(String value32) {
        if (value32.contains(KEY)) {
            this.values3 = Res.split(value32, KEY, 0);
            this.value3 = null;
            this.typeClass = TYPE_CLASS_STRING_ARRAY;
            return;
        }
        this.values3 = null;
        this.value3 = value32;
        this.typeClass = TYPE_CLASS_STRING;
    }

    public static void addLangue(SupportTranslate supportTranslate) {
        if (!hashMapLanguage.containsKey(supportTranslate.variable)) {
            hashMapLanguage.put(supportTranslate.variable, supportTranslate);
        }
    }

    public static void clear() {
        hashMapLanguage.clear();
    }

    public static String getTextLangue(String nameVariable) {
        try {
            HashMap<String, SupportTranslate> hashMap = hashMapLanguage;
            SupportTranslate supportTranslate = hashMap.get(nameVariable + "");
            switch (NinjaMidlet.getLanguage()) {
                case 0:
                    return supportTranslate.value1;
                case 1:
                    return supportTranslate.value2;
                case 2:
                    return supportTranslate.value3;
                default:
                    return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String[] getArrayLangue(String nameVariable) {
        try {
            HashMap<String, SupportTranslate> hashMap = hashMapLanguage;
            SupportTranslate supportTranslate = hashMap.get(nameVariable + "");
            switch (NinjaMidlet.getLanguage()) {
                case 0:
                    return supportTranslate.values1;
                case 1:
                    return supportTranslate.values2;
                case 2:
                    return supportTranslate.values3;
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
