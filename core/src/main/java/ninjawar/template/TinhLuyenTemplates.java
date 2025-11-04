package ninjawar.template;

import ninjawar.model.TinhLuyenTemplate;
import ninjawar.mylib.HTCustom;

public class TinhLuyenTemplates {
    public static HTCustom tinhLuyenTemplates = new HTCustom("TinhLuyenTemplate");

    public static void add(TinhLuyenTemplate item) {
        tinhLuyenTemplates.put((short) item.lv, item);
    }

    public static TinhLuyenTemplate get(int lv) {
        try {
            return (TinhLuyenTemplate) tinhLuyenTemplates.get((short) lv);
        } catch (Exception e) {
            return null;
        }
    }
}
