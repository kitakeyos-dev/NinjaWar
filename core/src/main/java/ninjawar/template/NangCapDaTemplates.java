package ninjawar.template;

import ninjawar.model.NangCapDaTemplate;
import ninjawar.mylib.HTCustom;

public class NangCapDaTemplates {
    public static HTCustom nangCapDaTemplates = new HTCustom("nangCapDaTemplates");

    public static void add(NangCapDaTemplate item) {
        nangCapDaTemplates.put((short) item.idItem, item);
    }

    public static NangCapDaTemplate get(int idItem) {
        try {
            return (NangCapDaTemplate) nangCapDaTemplates.get((short) idItem);
        } catch (Exception e) {
            return null;
        }
    }
}
