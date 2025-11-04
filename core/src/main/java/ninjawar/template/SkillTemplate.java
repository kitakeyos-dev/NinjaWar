package ninjawar.template;

import ninjawar.model.Skill;
import ninjawar.mylib.mSprite;

public class SkillTemplate {
    public String[] description;
    public int iconId = -1;
    public short id;
    public mSprite imgSkillTemplate;
    public boolean isFocusTutorial;
    public int maxPoint;
    public String name = "";
    public Skill[] skills;
    public int type;
    public int xTutorial;
    public int yTutorial;

    public boolean isBuffToPlayer() {
        return this.type == 2;
    }

    public boolean isChuongTool() {
        return this.type == 7;
    }

    public boolean isChuongFrameNoTarget() {
        return this.type == 8;
    }

    public boolean isChuongAOE() {
        return this.type == 14;
    }

    public boolean isChuong() {
        return isChuongTool() || isChuongAOE();
    }

    public boolean isUseAlone() {
        return this.type == 3;
    }

    public boolean isAttackSkill() {
        int i = this.type;
        return i == 1 || i == 4;
    }

    public boolean isAttackTarget() {
        int i = this.type;
        return i == 4 || i == 1;
    }

    public boolean isUseAlways() {
        return isUseAlone() || isBuffToPlayer() || isChuongFrameNoTarget();
    }

    public SkillTemplate clones() {
        SkillTemplate result = new SkillTemplate();
        result.id = this.id;
        result.name = this.name;
        result.maxPoint = this.maxPoint;
        result.type = this.type;
        result.iconId = this.iconId;
        result.description = this.description;
        result.skills = this.skills;
        result.imgSkillTemplate = this.imgSkillTemplate;
        return result;
    }

    public boolean isLv10Required() {
        Skill[] skillArr = this.skills;
        return skillArr != null && skillArr[0].levelUnlock == 10;
    }
}
