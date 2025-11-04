package ninjawar.template;

import ninjawar.mylib.HTCustom;

public class MobTemplates {
    public static HTCustom mobTemplates = new HTCustom("MobTemplate");

    public static void add(QuaiTemplate item) {
        mobTemplates.put(item.idIcon, item);
    }

    public static QuaiTemplate get(int idTemplate) {
        try {
            return (QuaiTemplate) mobTemplates.get((short) idTemplate);
        } catch (Exception e) {
            return null;
        }
    }
}
