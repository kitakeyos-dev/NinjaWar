// Class Version: 8
package ninjawar.model;

import ninjawar.mylib.AudioManager;
import ninjawar.mylib.mSystem;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.Player;
import ninjawar.myscr.Quai;
import ninjawar.myscr.Res;
import ninjawar.object.NewBoss;
import ninjawar.skill.EffectFrame;
import ninjawar.skill.EffectFrameBuffToEnd;
import ninjawar.template.SkillTemplate;

import java.util.ArrayList;

public class Skill {
    public NewBoss boss;
    public Player c;
    public int coolDown;
    public int dx;
    public int dxTemplate;
    public int dy;
    public int dyTemplate;
    public short idBuffFrame;
    public short idEffect;
    public short idEffectFrame;
    public short idEffectMore;
    public short idSound;
    public short idSound2;
    public short idSoundTarget;
    public short idStatusBuff;
    public short idTarget;
    public boolean isRemoveWhenDone;
    public boolean isSendMoveServer;
    public long lastTimeUseSkill;
    public int level;
    public int levelUnlock;
    public int manaUse;
    public int maxFight;
    public int numFrameIdBuffFrame;
    public int numFrameIdEffectFrame;
    public SkillOption[] options;
    public boolean paintCanNotUseSkill;
    public int point;
    public short skillId;
    public int speedSkill;
    public SkillTemplate template;
    public int timeBuff;
    public int timeShowSkill;

    public Skill() {
        this.isSendMoveServer = false;
        this.paintCanNotUseSkill = false;
        this.timeShowSkill = 1000;
        this.maxFight = 1;
        this.timeBuff = -1;
        this.idEffect = -1;
        this.idEffectFrame = -1;
        this.idEffectMore = -1;
        this.idStatusBuff = -1;
        this.idBuffFrame = -1;
        this.speedSkill = 0;
        this.idTarget = -1;
        this.idSound = -1;
        this.idSound2 = -1;
        this.idSoundTarget = -1;
    }

    public Skill(final Player c, final int n, final int coolDown, final int n2, final int dx, final int dy) {
        this.isSendMoveServer = false;
        this.paintCanNotUseSkill = false;
        this.timeShowSkill = 1000;
        this.maxFight = 1;
        this.timeBuff = -1;
        this.idEffect = -1;
        this.idEffectFrame = -1;
        this.idEffectMore = -1;
        this.idStatusBuff = -1;
        this.idBuffFrame = -1;
        this.speedSkill = 0;
        this.idTarget = -1;
        this.idSound = -1;
        this.idSound2 = -1;
        this.idSoundTarget = -1;
        this.c = c;
        this.coolDown = coolDown;
        this.template = new SkillTemplate();
        this.dx = dx;
        this.dy = dy;
    }

    public Skill clones() {
        final Skill skill = new Skill();
        skill.skillId = this.skillId;
        skill.template = this.template.clones();
        skill.point = this.point;
        skill.level = this.level;
        skill.levelUnlock = this.levelUnlock;
        skill.manaUse = this.manaUse;
        skill.coolDown = this.coolDown;
        skill.timeShowSkill = this.timeShowSkill;
        skill.dx = this.dx;
        skill.dy = this.dy;
        skill.maxFight = this.maxFight;
        skill.timeBuff = this.timeBuff;
        skill.idBuffFrame = this.idBuffFrame;
        skill.idStatusBuff = this.idStatusBuff;
        skill.idEffectMore = this.idEffectMore;
        skill.idEffectFrame = this.idEffectFrame;
        skill.idEffect = this.idEffect;
        skill.idTarget = this.idTarget;
        skill.speedSkill = this.speedSkill;
        skill.options = this.options;
        skill.lastTimeUseSkill = this.lastTimeUseSkill;
        skill.dxTemplate = this.dxTemplate;
        skill.dyTemplate = this.dyTemplate;
        skill.idSound = this.idSound;
        skill.idSound2 = this.idSound2;
        skill.numFrameIdBuffFrame = this.numFrameIdBuffFrame;
        skill.numFrameIdEffectFrame = this.numFrameIdEffectFrame;
        skill.isRemoveWhenDone = this.isRemoveWhenDone;
        return skill;
    }

    public boolean isCanUse() {
        return this.lastTimeUseSkill < mSystem.currentTimeMillis();
    }

    public boolean isSkillCoTheGan() {
        return this.level != 0;
    }

    public void logSkill() {
    }

    public void useSkill() {
        if (!this.isCanUse() && this.c.isMe()) {
            if (this.boss != null) {
                Res.outz(1, "BOSS KO THỂ DÙNG SKILL");
            }
            return;
        }
        if (this.c == null && this.boss == null) {
            return;
        }
        final NewBoss boss = this.boss;
        if (boss != null && this.template != null) {
            if (boss != null) {
                final StringBuilder sb = new StringBuilder();
                sb.append("BOSS ĐANG DÙNG SKILL 2:");
                sb.append(this.idEffect);
                Res.outz(1, sb.toString());
            }
            final short id = this.template.id;
            if (this.idEffect != -1) {
                final NewBoss boss2 = this.boss;
                final int abs = Res.abs(boss2.x - boss2.cTargetSkill.cx);
                final NewBoss boss3 = this.boss;
                final int x = boss3.x;
                final int n = boss3.y - boss3.getH() / 2;
                final NewBoss boss4 = this.boss;
                final int cx = boss4.cTargetSkill.cx;
                final int dx = this.dx;
                if (abs > dx) {
                    final short idEffect = this.idEffect;
                    int n2;
                    if (boss4.dir == -1) {
                        n2 = 2;
                    } else {
                        n2 = 0;
                    }
                    MapScr.addEffectTarget(idEffect, x, n, cx, n, n2, -1, this.speedSkill, boss4.charAttack, boss4.dameHP);
                } else {
                    final int x2 = boss4.x;
                    final int dir = boss4.dir;
                    final short idEffect2 = this.idEffect;
                    int n3;
                    if (dir == -1) {
                        n3 = 2;
                    } else {
                        n3 = 0;
                    }
                    MapScr.addEffectTarget(idEffect2, x, n, x2 + dx * dir, n, n3, -1, this.speedSkill, boss4.charAttack, boss4.dameHP);
                }
            }
        }
        final Player c = this.c;
        if (c != null) {
            if (c.cMP < this.manaUse || c.isDie()) {
                return;
            }
            switch (this.template.id) {
                case 42: {
                    this.c.doAction((byte) 16);
                    final Player c2 = this.c;
                    final int cw = c2.cw;
                    final int cx2 = c2.cx;
                    final int cdir = c2.cdir;
                    final int cy = c2.cy;
                    final int classId = c2.classId;
                    final int chp = c2.cHP;
                    MapScr.addMascot(c2, classId, 27, chp, c2.clevel, cx2 + cdir * cw, cy, chp, 3000, 0, this);
                    if (this.idEffect != -1) {
                        final EffectFrame effectFrame = new EffectFrame((byte) 0, 0, false);
                        effectFrame.loadEffectFromTool(new short[]{this.idEffect}, (byte) 1);
                        effectFrame.isRemoveWhenDone = true;
                        effectFrame.isRemoveSpec = true;
                        this.c.removeWeapon();
                        effectFrame.isAddSplashWeapon = true;
                        this.c.vEffectSkillWaiting.add(effectFrame);
                        break;
                    }
                    break;
                }
                case 10: {
                    this.isRemoveWhenDone = false;
                    break;
                }
                case 1: {
                    final Player c3 = this.c;
                    final Quai mobFocus = c3.mobFocus;
                    if (mobFocus != null) {
                        int n4;
                        if (c3.cx < mobFocus.x) {
                            n4 = 1;
                        } else {
                            n4 = -1;
                        }
                        c3.doNemPhiTieu(n4, mobFocus, this);
                        break;
                    }
                    final Player charFocus = c3.charFocus;
                    if (charFocus != null) {
                        int n5;
                        if (c3.cx < charFocus.cx) {
                            n5 = 1;
                        } else {
                            n5 = -1;
                        }
                        c3.doNemPhiTieu(n5, charFocus, this);
                        break;
                    }
                    break;
                }
            }
            this.lastTimeUseSkill = mSystem.currentTimeMillis() + this.coolDown;
            if (this.c != null) {
                final SkillTemplate template = this.template;
                if (template != null && template.id != 1) {
                    if (template.isAttackSkill()) {
                        final Player c4 = this.c;
                        if (!c4.isAttack) {
                            c4.doAttack();
                        }
                    }
                    if (this.template.isBuffToPlayer()) {
                        this.c.doAction((byte) 16);
                        final Player c5 = this.c;
                        c5.vRangeBuffSkills.add(new RangeDamageSkill(c5.charID, this, c5.cx, c5.cy, this.dx, this.dy, 1, (byte) 0, this.maxFight));
                    } else if (this.template.isChuongFrameNoTarget()) {
                        this.c.doAction((byte) 20);
                        final Player c6 = this.c;
                        c6.vRangeDmgSkills.add(new RangeDamageSkill(c6.charID, this, c6.cx, c6.cy, this.dx, this.dy, 2, (byte) c6.cdir, this.maxFight, this.timeBuff, 1000));
                    } else if (this.template.isChuong()) {
                        this.c.doAction((byte) 20);
                        final Player c7 = this.c;
                        c7.vRangeDmgSkills.add(new RangeDamageSkill(c7.charID, this, c7.cx, c7.cy, this.dx, this.dy, c7.getTypeSkill(), (byte) this.c.cdir, this.maxFight));
                    } else {
                        final Player c8 = this.c;
                        c8.vRangeDmgSkills.add(new RangeDamageSkill(c8.charID, this, c8.cx, c8.cy, this.dx, this.dy, c8.getTypeSkill(), (byte) this.c.cdir, this.maxFight));
                    }
                }
                this.c.delaySkillNext(this.timeShowSkill);
            }
        }
        if (this.idSound != -1) {
            if (this.idSound2 != -1 && Player.myCharz().indexAttackWeapon == 1) {
                AudioManager.playSound(this.idSound2, 1.0f);
            } else {
                AudioManager.playSound(this.idSound, 1.0f);
            }
        }
    }

    public boolean useSkillBuffSpec(final Player player) {
        boolean b = false;
        if (this.template == null) {
            return false;
        }
        final ArrayList<EffectFrameBuffToEnd> list = new ArrayList<EffectFrameBuffToEnd>();
        final short idEffectFrame = this.idEffectFrame;
        if (idEffectFrame != -1) {
            list.add(new EffectFrameBuffToEnd(idEffectFrame, this.numFrameIdEffectFrame, true, this.dxTemplate, this.dyTemplate));
            b = true;
        }
        final short idBuffFrame = this.idBuffFrame;
        if (idBuffFrame != -1) {
            list.add(new EffectFrameBuffToEnd(idBuffFrame, this.numFrameIdBuffFrame, false, this.dxTemplate, this.dyTemplate));
            b = true;
        }
        player.addBuffSpec(list, this.timeBuff);
        return b;
    }
}
