package ninjawar.template;

import ninjawar.model.UpgradeTemplate;
import ninjawar.mylib.HTCustom;

public class UpgradeTemplates {
    public static HTCustom upgradeTemplates = new HTCustom("UpgradeTemplate");

    public static void set(UpgradeTemplate item) {
        upgradeTemplates.put((short) item.lv, item);
    }

    public static UpgradeTemplate get(int lv) {
        try {
            return (UpgradeTemplate) upgradeTemplates.get((short) lv);
        } catch (Exception e) {
            return null;
        }
    }
}
