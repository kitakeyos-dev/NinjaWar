package ninjawar.template;

import ninjawar.mylib.HTCustom;

public class NPCHashTables {
    public static HTCustom NPCs = new HTCustom("NPCs");

    public static void set(TemplateNPC item) {
        NPCs.put((short) item.npcTemplateId, item);
    }

    public static TemplateNPC get(int npcTemplateId) {
        try {
            return (TemplateNPC) NPCs.get((short) npcTemplateId);
        } catch (Exception e) {
            return null;
        }
    }
}
