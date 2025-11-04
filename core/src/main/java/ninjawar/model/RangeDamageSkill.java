package ninjawar.model;

import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;

public class RangeDamageSkill {
    public int delayTimeNext;
    public int h;
    public int idCharUseSkill;
    public boolean isPaintSkill;
    public int maxTarget = 1;
    public Rectangle rectangle;
    public Skill skill;
    public long timeNextSendAtk = -1;
    public long timeSkill = -1;
    public byte type = -1;
    public int w;
    public int x;
    public int y;

    public RangeDamageSkill() {
    }

    public void init(int idChar, Skill skill2, int x2, int y2, int w2, int h2, int type2, byte fromDirRight, int maxTarget2, int timeExist, int delayTimeNext2) {
        if (fromDirRight == 1) {
            this.x = x2;
            this.y = y2 - h2;
        } else if (fromDirRight == -1) {
            this.x = x2 - w2;
            this.y = y2 - h2;
        } else {
            this.x = x2 - (w2 / 2);
            this.y = y2 - h2;
        }
        this.w = w2;
        this.h = h2;
        this.rectangle = new Rectangle(this.x, this.y, w2, h2);
        this.skill = skill2;
        this.type = (byte) type2;
        this.idCharUseSkill = idChar;
        this.maxTarget = maxTarget2;
        this.timeSkill = timeExist != -1 ? mSystem.currentTimeMillis() + ((long) timeExist) : -1;
        this.delayTimeNext = delayTimeNext2;
        this.isPaintSkill = false;
    }

    public RangeDamageSkill(int idChar, Skill skill2, int x2, int y2, int w2, int h2, int type2, byte fromDirRight, int maxTarget2, int timeExist, int delayTimeNext2) {
        init(idChar, skill2, x2, y2, w2, h2, type2, fromDirRight, maxTarget2, timeExist, delayTimeNext2);
    }

    public RangeDamageSkill(int idChar, Skill skill2, int x2, int y2, int w2, int h2, int type2, byte fromDirRight, int maxTarget2) {
        init(idChar, skill2, x2, y2, w2, h2, type2, fromDirRight, maxTarget2, -1, -1);
    }

    public void paint(mGraphics g) {
    }

    public void updateTimeNext() {
        this.timeNextSendAtk = this.delayTimeNext != -1 ? mSystem.currentTimeMillis() + ((long) this.delayTimeNext) : -1;
    }
}
