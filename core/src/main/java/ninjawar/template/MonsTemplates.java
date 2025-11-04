package ninjawar.template;

import ninjawar.model.MonsTemplate;
import ninjawar.mylib.HTCustom;

public class MonsTemplates {
    public static HTCustom monsTemplates = new HTCustom("MonsTemplates");

    public static void add(MonsTemplate item) {
        monsTemplates.put(item.idTemplate, item);
    }
}
