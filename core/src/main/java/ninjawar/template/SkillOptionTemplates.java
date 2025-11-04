package ninjawar.template;

import ninjawar.mylib.HTCustom;

public class SkillOptionTemplates {
    public static HTCustom skillOptionTemplates = new HTCustom("skillOptionTemplates");

    public static void add(SkillOptionTemplate it) {
        HTCustom hTCustom = skillOptionTemplates;
        hTCustom.put(it.id + "", it);
    }

    public static SkillOptionTemplate get(int id) {
        HTCustom hTCustom = skillOptionTemplates;
        return (SkillOptionTemplate) hTCustom.get(id + "");
    }
}
