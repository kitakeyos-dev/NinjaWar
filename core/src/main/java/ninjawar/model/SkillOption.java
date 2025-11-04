package ninjawar.model;

import ninjawar.myscr.Res;
import ninjawar.template.SkillOptionTemplate;
import ninjawar.template.SkillOptionTemplates;

public class SkillOption {
    public int idOption;
    public String optionString = "";
    public SkillOptionTemplate optionTemplate;
    public int param;

    public SkillOption(int idOption2, int param2) {
        String str;
        this.idOption = idOption2;
        this.param = param2;
        SkillOptionTemplate skillOptionTemplate = SkillOptionTemplates.get(idOption2);
        this.optionTemplate = skillOptionTemplate;
        if (skillOptionTemplate != null) {
            str = Res.replace(skillOptionTemplate.name, "#", new int[]{param2});
        } else {
            str = "null";
        }
        this.optionString = str;
    }
}
