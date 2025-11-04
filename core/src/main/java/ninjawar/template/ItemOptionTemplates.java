package ninjawar.template;

import ninjawar.model.ItemOptionTemplate;
import ninjawar.mylib.HTCustom;

public class ItemOptionTemplates {
    public static HTCustom itemOptionTemplates = new HTCustom("itemOptionTemplate");

    public static void add(ItemOptionTemplate it) {
        HTCustom hTCustom = itemOptionTemplates;
        hTCustom.put(it.id + "", it);
    }

    public static ItemOptionTemplate get(int id) {
        HTCustom hTCustom = itemOptionTemplates;
        return (ItemOptionTemplate) hTCustom.get(id + "");
    }
}
