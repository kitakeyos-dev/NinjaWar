package ninjawar.object;

import ninjawar.model.Rectangle;
import ninjawar.model.Skill;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Player;
import ninjawar.myscr.Quai;
import ninjawar.myscr.Res;
import ninjawar.template.MobTemplates;
import ninjawar.template.QuaiTemplate;
import ninjawar.template.Skills;

public class NewBoss extends Quai {
    int[][] FRAME_BOSS_DEFAULT = {new int[]{0, 1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10, 11}, new int[]{24, 25}, new int[]{24, 25, 26, 27, 28, 29, 29, 29, 29, 29, 29}, new int[]{0, 14, 15, 16, 17, 18, 0, 1, 2, 3, 4, 5}, new int[]{0, 19, 20, 21, 22, 23, 19, 0, 1, 2, 3, 4, 5}, new int[]{11, 7}, new int[]{12, 13}};
    public Player cTargetSkill;
    public Player[] charAttack;
    byte[] cou = {-1, 1};
    public int[] dameHP;
    int ff = 0;
    public int forceWait;
    int frame;
    int[][] frameArr = {new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 1, 1, 1, 1}};
    public boolean injureThenDie = false;
    public boolean isAttkLong = false;
    Skill skillCalling;
    int tick;
    int xTempLeft = -1;
    int xTempRight = -1;
    public int xTo;
    int yTemp = -1;
    public int yTo;

    public NewBoss(int id, short px, short py, int templateID, int hp, int maxHp, int s) {
        short s2 = py;
        this.mobId = id;
        int i = px + 20;
        this.xFirst = i;
        this.x = i;
        this.yFirst = s2;
        this.y = s2;
        this.xTo = i;
        this.yTo = s2;
        this.maxHp = maxHp;
        this.hp = hp;
        this.templateId = templateID;
        this.mobTemplate = MobTemplates.get(templateID).clones();
        this.h_hp_bar = 6;
        this.w_hp_bar = 100;
        this.len = 100;
        updateHp_bar();
        this.status = 2;
        if (this.hp == 0) {
            this.isHideByDie = true;
        }
        this.frameArr = null;
    }

    public void checkFrameTick(int[] array) {
        int i = this.tick + 1;
        this.tick = i;
        if (i > array.length - 1) {
            this.tick = 0;
        }
        this.frame = array[this.tick];
    }

    public void resetAction() {
        if (this.action != 3) {
            QuaiTemplate quaiTemplate = this.mobTemplate;
            if (quaiTemplate != null) {
                quaiTemplate.index = 0;
            }
            int i = this.x;
            int i2 = this.xTo;
            if (i == i2) {
                this.action = 0;
                this.status = 2;
                return;
            }
            move(i2, this.yTo);
        }
    }

    public void updateNew() {
        QuaiTemplate quaiTemplate = this.mobTemplate;
        if (quaiTemplate != null) {
            this.w = quaiTemplate.getWidth();
            this.h = this.mobTemplate.getHeight();
            int i = this.action;
            if (i != 5) {
                if (i == 2 || i == 4 || i == 3) {
                    QuaiTemplate quaiTemplate2 = this.mobTemplate;
                    if (quaiTemplate2 != null) {
                        int i2 = quaiTemplate2.index;
                        int[] iArr = quaiTemplate2.FRAME_MOB[i];
                        if (i2 != iArr.length - 1) {
                            if (CanvasNinja.gameTick % 3 == 0) {
                                quaiTemplate2.index = i2 + 1;
                            }
                            if (quaiTemplate2.index >= iArr.length - 1) {
                                quaiTemplate2.index = iArr.length - 1;
                                resetAction();
                            }
                        }
                    }
                } else {
                    QuaiTemplate quaiTemplate3 = this.mobTemplate;
                    if (quaiTemplate3 != null) {
                        if (CanvasNinja.gameTick % 3 == 0) {
                            quaiTemplate3.index++;
                        }
                        if (quaiTemplate3.index > quaiTemplate3.FRAME_MOB[i].length - 1) {
                            quaiTemplate3.index = 0;
                        }
                    }
                }
            }
        }
        Rectangle rectangle = this.rectangle;
        int i3 = this.x;
        int i4 = this.w;
        int i5 = this.y;
        int i6 = this.h;
        rectangle.updatePos(i3 - (i4 / 2), i5 - i6, i4, i6);
    }

    public void update() {
        updateShadow();
        if (!this.isHideByDie) {
            updateNew();
            int i = 5;
            switch (this.status) {
                case 0:
                case 1:
                    this.action = 3;
                    updateDead();
                    return;
                case 2:
                    this.action = 0;
                    updateMobStandWait();
                    return;
                case 3:
                    Skill skill = this.skillCalling;
                    if (skill == null || skill.idEffect == -1) {
                        i = 4;
                    }
                    this.action = i;
                    updateMobAttack();
                    return;
                case 4:
                    this.action = 0;
                    updateMobFly();
                    return;
                case 5:
                    this.action = 1;
                    this.timeStatus = 0;
                    updateMobWalk();
                    return;
                case 6:
                    this.action = 7;
                    this.timeStatus = 0;
                    int i2 = this.p1 + 1;
                    this.p1 = i2;
                    int i3 = this.y + i2;
                    this.y = i3;
                    int i4 = this.yFirst;
                    if (i3 >= i4) {
                        this.y = i4;
                        this.p1 = 0;
                        this.status = 5;
                        return;
                    }
                    return;
                case 7:
                    this.action = 2;
                    updateInjure();
                    return;
                default:
                    return;
            }
        }
    }

    private void updateDead() {
        updateDie();
    }

    private void updateMobFly() {
    }

    private void updateInjure() {
    }

    private void updateMobStandWait() {
    }

    public void setAttack(Player[] cAttack, Player cTargetLast, int[] dame, short idSkill, byte dir) {
        this.charAttack = cAttack;
        this.dameHP = dame;
        this.dir = dir;
        this.dirAtkLong = dir;
        this.status = 3;
        QuaiTemplate quaiTemplate = this.mobTemplate;
        boolean z = false;
        if (quaiTemplate != null) {
            quaiTemplate.index = 0;
        }
        Skill skill = Skills.get(idSkill);
        this.skillCalling = skill;
        if (skill != null) {
            this.cTargetSkill = cTargetLast;
            skill.boss = this;
            if (skill.idEffect != -1) {
                z = true;
            }
            this.isAttkLong = z;
        }
    }

    public void updateMobAttack() {
        Skill skill;
        QuaiTemplate quaiTemplate = this.mobTemplate;
        if (quaiTemplate != null) {
            int i = this.action;
            if (i == 5) {
                if (CanvasNinja.gameTick % 3 == 0) {
                    quaiTemplate.index++;
                }
                if (quaiTemplate.index > quaiTemplate.FRAME_MOB[i].length - 1) {
                    quaiTemplate.index = 0;
                    resetAction();
                }
            }
            if (this.mobTemplate.index == 3 && this.action == 5 && (skill = this.skillCalling) != null) {
                skill.useSkill();
                this.cTargetSkill = null;
                this.skillCalling = null;
            }
        }
    }

    public void updateMobWalk() {
        QuaiTemplate quaiTemplate = this.mobTemplate;
        byte speed = quaiTemplate != null ? quaiTemplate.speed : 4;
        int sp = speed;
        if (Res.abs(this.x - this.xTo) < speed) {
            sp = Res.abs(this.x - this.xTo);
        }
        int i = this.x;
        int i2 = this.xTo;
        int i3 = i + (i < i2 ? sp : -sp);
        this.x = i3;
        this.y = this.yTo;
        if (i3 < i2) {
            this.dir = 1;
        } else if (i3 > i2) {
            this.dir = -1;
        }
        if (Res.abs(i3 - i2) <= 1) {
            this.x = this.xTo;
            this.status = 2;
        }
    }

    public void paint(mGraphics g) {
        boolean z;
        if ((!Player.myCharz().isGetSharigan || this.isMobMe) && !(z = this.isHideByDie)) {
            QuaiTemplate quaiTemplate = this.mobTemplate;
            if (quaiTemplate != null) {
                quaiTemplate.paint(g, this.action, this.x, this.y, this.dir, z, this.xSd);
            }
            paintShadow(g);
            paintHp(g);
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getH() {
        QuaiTemplate quaiTemplate = this.mobTemplate;
        return quaiTemplate != null ? quaiTemplate.getHeight() : this.h;
    }

    public int getW() {
        QuaiTemplate quaiTemplate = this.mobTemplate;
        return quaiTemplate != null ? quaiTemplate.getWidth() : this.w;
    }

    public void stopMoving() {
        if (this.status == 5) {
            this.status = 2;
            this.p3 = 0;
            this.p2 = 0;
            this.p1 = 0;
            this.forceWait = 50;
        }
    }

    public boolean isInvisible() {
        int i = this.status;
        return i == 0 || i == 1;
    }

    public void move(int xMoveTo, int yMoveTo) {
        this.xTo = xMoveTo;
        this.status = 5;
    }

    public void GetFrame() {
    }
}
