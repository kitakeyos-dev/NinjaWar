package ninjawar.template;

import ninjawar.model.ChietXuatTemplate;
import ninjawar.mylib.HTCustom;

public class ChietXuatTemplates {
    public static HTCustom chietXuatTemplates = new HTCustom("chietXuatTemplate");

    public static void add(ChietXuatTemplate item) {
        chietXuatTemplates.put((short) item.lv, item);
    }

    public static ChietXuatTemplate get(int lv) {
        try {
            return (ChietXuatTemplate) chietXuatTemplates.get((short) lv);
        } catch (Exception e) {
            return null;
        }
    }
}
