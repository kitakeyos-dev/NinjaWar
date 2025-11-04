package ninjawar.template;

import ninjawar.model.Skill;
import ninjawar.mylib.HTCustom;

public class Skills {
    public static HTCustom skills = new HTCustom("VSKILL");

    public static void add(Skill skill) {
        skills.put(skill.skillId, skill);
    }

    public static Skill get(short skillId) {
        try {
            return ((Skill) skills.get(skillId)).clones();
        } catch (Exception e) {
            return null;
        }
    }
}
