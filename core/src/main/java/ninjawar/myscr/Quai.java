// Class Version: 8
package ninjawar.myscr;

import ninjawar.message.SendMessage;
import ninjawar.mylib.AudioManager;
import ninjawar.supporter.SupportTranslate;
import ninjawar.mylib.FrameImage;
import ninjawar.skill.EffectTarget;
import java.util.Iterator;
import ninjawar.model.Popup;
import ninjawar.supporter.LoadDataManager;
import ninjawar.template.MobTemplates;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mylib.mSystem;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.model.OptionTemplate;
import ninjawar.skill.EffectShield;
import ninjawar.skill.MainEffect;
import java.util.Vector;
import ninjawar.model.TaskMob;
import ninjawar.model.Skill;
import ninjawar.model.Rectangle;
import ninjawar.template.QuaiTemplate;
import ninjawar.mylib.Image;
import ninjawar.model.FrameEffectMore;
import ninjawar.mylib.VectorCustom;
import ninjawar.object.Base;

public class Quai extends Base
{
    public static VectorCustom lastMob;
    public static VectorCustom newMob;
    public int HPTemp_server;
    public int action;
    public int[] attack1;
    public int[] attack2;
    public boolean blindEff;
    int cDirUpDown;
    public Player cFocus;
    int color;
    byte[] cou;
    public int dame;
    public int dameMp;
    public int dameSp;
    public int dir;
    public int dirAtkLong;
    public byte dirTemp;
    public int dirV;
    int disY;
    public int dmgHitTemp_server;
    public int forceWait;
    int frame;
    int[][] frameArr;
    FrameEffectMore frameEffectMore;
    int fy;
    public int h;
    public int hHe;
    public byte h_hp_bar;
    int h_real;
    int h_real_spec;
    public int holdEffID;
    public int hp;
    public int hpTemp;
    public int[] hurt;
    public Image imgHPtem;
    public Player injureBy;
    public boolean injureThenDie;
    private boolean isBeingPulled;
    public boolean isBoss;
    public boolean isBusyAttackSomeOne;
    public boolean isCheckDieTutorial;
    public boolean isDie;
    public boolean isDieEffect;
    public boolean isDisable;
    public boolean isDontMove;
    public boolean isFire;
    boolean isFirstPaintHp;
    public boolean isFreez;
    boolean isGetFr;
    public boolean isHide;
    public boolean isHideByDie;
    public boolean isIce;
    public boolean isLastHit;
    public boolean isMafuba;
    public boolean isMobMe;
    public boolean isShadown;
    public boolean isShowHp;
    public boolean isUpdateHp_server;
    public boolean isWaitingCheckTutorial;
    public boolean isWind;
    public long last;
    public int len;
    public byte level;
    public byte levelBoss;
    public int maxHp;
    public int maxTickDie;
    public QuaiTemplate mobTemplate;
    public Quai mobToAttack;
    public int[] move;
    public int[] moveFast;
    public Player myBoss;
    int offset;
    public int p1;
    public int p2;
    public int p3;
    public int per;
    public int per_tem;
    public short pointx;
    public short pointy;
    private int pullTargetX;
    private int pullTargetY;
    public Rectangle rectangle;
    public int seconds;
    public Skill skill;
    public boolean sleepEff;
    public int[] stand;
    public int status;
    public int sys;
    TaskMob taskMobTemp;
    public int templateId;
    int tick;
    public int tickDie;
    int timeDelayHideHP;
    long timeLockMove;
    long timeStartShowHP;
    public int timeStatus;
    int ty;
    Vector<MainEffect> vEffectAfters;
    Vector<MainEffect> vEffectBefores;
    public Vector<MainEffect> vEffectSkillWaiting;
    public VectorCustom vMobMove;
    Vector<EffectShield> vShields;
    public Vector<TaskMob> vTaskMobs;
    public int w;
    int wCount;
    public int wHe;
    int w_countDown;
    int w_countDown_spec;
    public int w_hp_bar;
    int w_real;
    int w_real_spec;
    int w_result;
    int w_result_spec;
    int wt;
    boolean wy;
    public int x;
    public int xFirst;
    public int xSd;
    public int xSdReal;
    public int y;
    int yAnimStand;
    public int yFirst;
    public int ySd;
    public int ySdReal;
    public int yTop;

    static {
        Quai.lastMob = new VectorCustom("lastMob");
        Quai.newMob = new VectorCustom("newMob");
    }

    public Quai() {
        this.vTaskMobs = new Vector<TaskMob>();
        this.vEffectSkillWaiting = new Vector<MainEffect>();
        this.vEffectBefores = new Vector<MainEffect>();
        this.vEffectAfters = new Vector<MainEffect>();
        this.vShields = new Vector<EffectShield>();
        this.dirTemp = 2;
        this.isLastHit = false;
        this.action = 0;
        this.isFreez = false;
        this.dir = 1;
        this.dirV = 1;
        this.vMobMove = new VectorCustom("vMobMove");
        this.isMafuba = false;
        this.yTop = -1;
        this.isShadown = true;
        this.cDirUpDown = 1;
        this.isBeingPulled = false;
        this.isBusyAttackSomeOne = true;
        this.stand = new int[] { 0, 1, 0, 1 };
        this.move = new int[] { 2, 2, 3, 3, 4, 4, 5, 5 };
        this.moveFast = new int[] { 1, 1, 2, 2, 3, 3, 2 };
        this.attack1 = new int[] { 6, 6, 6, 6 };
        this.attack2 = new int[] { 6, 6, 6, 6 };
        this.hurt = new int[] { 7, 7 };
        this.color = 8421504;
        this.len = 24;
        this.w_hp_bar = 24;
        this.per = 100;
        this.per_tem = 100;
        this.h_hp_bar = 4;
        this.offset = 0;
        this.isHide = false;
        this.isDieEffect = false;
        this.maxTickDie = 6;
        this.disY = 12;
        this.isShowHp = false;
        this.timeDelayHideHP = 3000;
        this.isFirstPaintHp = true;
        this.cou = new byte[] { -1, 1 };
        this.injureThenDie = false;
        this.frameArr = new int[][] { { 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1 } };
        this.isGetFr = true;
        this.rectangle = new Rectangle();
    }

    public Quai(int i, final boolean isDisable, final boolean isDontMove, final boolean isFire, final boolean isIce, final boolean isWind, int yTop, int xFirst, int yFirst, final byte b, final int maxHp, final short n, final short n2, final byte status, final byte b2) {
        this.vTaskMobs = new Vector<TaskMob>();
        this.vEffectSkillWaiting = new Vector<MainEffect>();
        this.vEffectBefores = new Vector<MainEffect>();
        this.vEffectAfters = new Vector<MainEffect>();
        this.vShields = new Vector<EffectShield>();
        this.dirTemp = 2;
        this.isLastHit = false;
        this.action = 0;
        this.isFreez = false;
        this.dir = 1;
        this.dirV = 1;
        this.vMobMove = new VectorCustom("vMobMove");
        this.isMafuba = false;
        this.yTop = -1;
        this.isShadown = true;
        this.cDirUpDown = 1;
        this.isBeingPulled = false;
        this.isBusyAttackSomeOne = true;
        this.stand = new int[] { 0, 1, 0, 1 };
        this.move = new int[] { 2, 2, 3, 3, 4, 4, 5, 5 };
        this.moveFast = new int[] { 1, 1, 2, 2, 3, 3, 2 };
        this.attack1 = new int[] { 6, 6, 6, 6 };
        this.attack2 = new int[] { 6, 6, 6, 6 };
        this.hurt = new int[] { 7, 7 };
        this.color = 8421504;
        this.len = 24;
        this.w_hp_bar = 24;
        this.per = 100;
        this.per_tem = 100;
        this.h_hp_bar = 4;
        this.offset = 0;
        this.isHide = false;
        this.isDieEffect = false;
        this.maxTickDie = 6;
        this.disY = 12;
        this.isShowHp = false;
        this.timeDelayHideHP = 3000;
        this.isFirstPaintHp = true;
        this.cou = new byte[] { -1, 1 };
        this.injureThenDie = false;
        this.frameArr = new int[][] { { 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 1, 1 } };
        this.isGetFr = true;
        this.rectangle = new Rectangle();
        this.isDisable = isDisable;
        this.isDontMove = isDontMove;
        this.isFire = isFire;
        this.isIce = isIce;
        this.isWind = isWind;
        this.sys = xFirst;
        super.mobId = i;
        this.templateId = yTop;
        this.hp = yFirst;
        if (yFirst == 0) {
            this.isHideByDie = true;
        }
        this.level = b;
        final QuaiTemplate clones = QuaiTemplate.get(yTop).clones();
        this.mobTemplate = clones;
        final OptionTemplate optionMobTemplate = clones.optionMobTemplate;
        xFirst = (short)(n + optionMobTemplate.offSetXQuai);
        yFirst = (short)(n2 + optionMobTemplate.offSetYQuai);
        this.pointx = (short)xFirst;
        this.x = xFirst;
        this.xFirst = xFirst;
        this.pointy = (short)yFirst;
        this.y = yFirst;
        this.yFirst = yFirst;
        this.status = status;
        this.updateHe();
        final StringBuilder sb = new StringBuilder();
        sb.append(yTop);
        sb.append("");
        if (!isExistNewMob(sb.toString())) {
            final VectorCustom newMob = Quai.newMob;
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(yTop);
            sb2.append("");
            newMob.addElement(sb2.toString());
        }
        this.maxHp = maxHp;
        this.levelBoss = b2;
        this.updateHp_bar();
        this.isDie = false;
        this.xSd = this.x;
        this.ySd = this.y;
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("MOB ID= ");
        sb3.append(i);
        Res.outz(sb3.toString());
        if (!this.isMonsFly() && !MyTile.tileTypeAt(this.x, this.y, 2)) {
            for (i = this.y; i <= MyTile.size * MyTile.tmh; i += MyTile.size) {
                if (MyTile.tileTypeAt(this.x, i, 2)) {
                    this.yTop = MyTile.tileYofPixel(i);
                    break;
                }
            }
        }
        yTop = this.yTop;
        i = -1;
        if (yTop != -1) {
            if (yTop >= this.y) {
                i = 1;
            }
            this.cDirUpDown = i;
        }
    }

    private boolean isBlock() {
        return NinjaMidlet.timeSystems < this.timeLockMove;
    }

    public static boolean isExistNewMob(final String s) {
        for (int i = 0; i < Quai.newMob.size(); ++i) {
            if (((String)Quai.newMob.elementAt(i)).equals(s)) {
                return true;
            }
        }
        return false;
    }

    private void paintName(final mGraphics mGraphics) {
    }

    private void updateHideHP() {
        if (this.isShowHp && mSystem.currentTimeMillis() > this.timeStartShowHP && this.hpTemp == this.hp) {
            this.isShowHp = false;
        }
    }

    private void updateInjure() {
        final QuaiTemplate mobTemplate = this.mobTemplate;
        if (mobTemplate != null) {
            if (CanvasNinja.gameTick % 3 == 0) {
                ++mobTemplate.index;
            }
            if (this.hp <= 0) {
                this.startDie();
                this.injureBy = null;
            }
            final QuaiTemplate mobTemplate2 = this.mobTemplate;
            if (mobTemplate2.index > mobTemplate2.FRAME_MOB[this.action].length - 1) {
                mobTemplate2.index = 0;
                this.status = 5;
                final Player injureBy = this.injureBy;
                if (injureBy != null) {
                    this.dir = -injureBy.cdir;
                    if (Res.abs(this.x - injureBy.cx) < 24) {
                        this.status = 2;
                    }
                }
                this.p3 = 0;
                this.p2 = 0;
                this.p1 = 0;
                this.timeStatus = 0;
            }
        }
    }

    private void updateMobStandWait() {
        switch (MobTemplates.get(this.templateId).type) {
            case 4:
            case 5: {
                final int p1 = this.p1 + 1;
                this.p1 = p1;
                if (p1 <= super.mobId % 3) {
                    break;
                }
                final Player cFocus = this.cFocus;
                if (cFocus != null && Res.abs(cFocus.cx - this.x) <= 80) {
                    break;
                }
                final Quai mobToAttack = this.mobToAttack;
                if (mobToAttack == null || Res.abs(mobToAttack.x - this.x) > 80) {
                    this.status = 5;
                    this.action = 1;
                    break;
                }
                break;
            }
            case 0:
            case 1:
            case 2:
            case 3: {
                final int p2 = this.p1 + 1;
                this.p1 = p2;
                if (p2 <= super.mobId % 10 + 10) {
                    break;
                }
                final Player cFocus2 = this.cFocus;
                if (cFocus2 != null && (Res.abs(cFocus2.cx - this.x) <= 80 || this.cFocus.isDie)) {
                    break;
                }
                final Quai mobToAttack2 = this.mobToAttack;
                if (mobToAttack2 == null || Res.abs(mobToAttack2.x - this.x) > 80) {
                    this.action = 1;
                    this.status = 5;
                    break;
                }
                break;
            }
        }
        final Player cFocus3 = this.cFocus;
        if (cFocus3 != null && CanvasNinja.gameTick % (this.p1 % 20 + 10) == 0) {
            if (cFocus3.cx > this.x) {
                this.dir = 1;
            }
            else {
                this.dir = -1;
            }
        }
        else {
            final Quai mobToAttack3 = this.mobToAttack;
            if (mobToAttack3 != null && CanvasNinja.gameTick % (this.p1 % 20 + 10) == 0) {
                if (mobToAttack3.x > this.x) {
                    this.dir = 1;
                }
                else {
                    this.dir = -1;
                }
            }
        }
        final int forceWait = this.forceWait;
        if (forceWait > 0) {
            this.forceWait = forceWait - 1;
            this.status = 2;
            this.action = 0;
        }
    }

    private void updatePull() {
        final QuaiTemplate mobTemplate = this.mobTemplate;
        int speed;
        if (mobTemplate != null) {
            speed = mobTemplate.speed;
        }
        else {
            speed = 5;
        }
        if (Math.abs(this.x - this.pullTargetX) < speed && Math.abs(this.y - this.pullTargetY) < speed) {
            this.x = this.pullTargetX;
            this.y = this.pullTargetY;
            this.isBeingPulled = false;
            this.status = 2;
            this.action = 0;
            this.updateShadow();
            return;
        }
        final int n = this.pullTargetX - this.x;
        final int n2 = this.pullTargetY - this.y;
        final double sqrt = Math.sqrt(n * n + n2 * n2);
        if (sqrt > 0.0) {
            final double n3 = speed / sqrt;
            this.x += (int)(n * n3);
            this.y += (int)(n2 * n3);
        }
        if (Math.abs(this.x - this.pullTargetX) < speed) {
            this.x = this.pullTargetX;
        }
        if (Math.abs(this.y - this.pullTargetY) < speed) {
            this.y = this.pullTargetY;
        }
        this.updateShadow();
        final Rectangle rectangle = this.rectangle;
        final int x = this.x;
        final int w = this.w;
        final int n4 = w / 2;
        final int y = this.y;
        final int h = this.h;
        rectangle.updatePos(x - n4, y - h, w, h);
    }

    public void GetFrame() {
    }

    public void addShield(final int n, final int n2) {
        if (n == 4) {
            this.doStun(n2);
        }
        else {
            this.vShields.addElement(new EffectShield((short)n, n2));
        }
    }

    public void checkFrameTick(final int[] array) {
        if (this.tick > array.length - 1) {
            this.tick = 0;
        }
        final int tick = this.tick;
        this.frame = array[tick];
        this.tick = tick + 1;
    }

    public void doAttack(final Player cFocus, final boolean isUpdateHp) {
        this.isBusyAttackSomeOne = true;
        this.mobToAttack = null;
        this.cFocus = cFocus;
        if (cFocus != null) {
            cFocus.isUpdateHp = isUpdateHp;
        }
        final QuaiTemplate mobTemplate = this.mobTemplate;
        if (mobTemplate != null) {
            mobTemplate.index = 0;
        }
        this.p1 = 0;
        this.p2 = 0;
        this.status = 3;
        this.action = 4;
        if (mobTemplate != null) {
            mobTemplate.index = 0;
        }
        this.tick = 0;
        final int cx = cFocus.cx;
        final int x = this.x;
        int dir;
        if (cx > x) {
            dir = 1;
        }
        else {
            dir = -1;
        }
        this.dir = dir;
        final int cx2 = cFocus.cx;
        final int cy = cFocus.cy;
        if (Res.abs(cx2 - x) < this.w * 2 && Res.abs(cy - this.y) < this.h * 2) {
            this.p3 = 0;
        }
        else {
            this.p3 = 1;
        }
    }

    public void doStun(final int n) {
        long timeLockMove;
        if (n == -1) {
            timeLockMove = -1L;
        }
        else {
            timeLockMove = mSystem.currentTimeMillis() + n;
        }
        this.timeLockMove = timeLockMove;
        this.vShields.addElement(new EffectShield(4, n));
    }

    @Override
    public int getH() {
        final QuaiTemplate mobTemplate = this.mobTemplate;
        int n;
        if (mobTemplate != null) {
            n = mobTemplate.getHeight();
        }
        else {
            n = this.h;
        }
        return n;
    }

    public QuaiTemplate getTemplate() {
        return MobTemplates.get(this.templateId);
    }

    @Override
    public int getW() {
        final QuaiTemplate mobTemplate = this.mobTemplate;
        int n;
        if (mobTemplate != null) {
            n = mobTemplate.getWidth();
        }
        else {
            n = this.w;
        }
        return n;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    public boolean isCanAttack(final boolean b) {
        final boolean b2 = false;
        final boolean b3 = false;
        if (b) {
            boolean b4 = b3;
            if (this.status != 1) {
                b4 = b3;
                if (this.action != 3) {
                    b4 = b3;
                    if (this.hp > 0) {
                        b4 = b3;
                        if (!this.isHideByDie) {
                            b4 = true;
                        }
                    }
                }
            }
            return b4;
        }
        boolean b5 = b2;
        if (this.status != 1) {
            b5 = b2;
            if (this.action != 3) {
                b5 = b2;
                if (this.hp > 0) {
                    b5 = b2;
                    if (!this.isHideByDie) {
                        b5 = true;
                    }
                }
            }
        }
        return b5;
    }

    public boolean isCanMoveFrame() {
        if (this.action == 0) {
            final QuaiTemplate mobTemplate = this.mobTemplate;
            if (mobTemplate != null && mobTemplate.rangeMove > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isInRange(final Rectangle rectangle) {
        return this.rectangle.isInRectangle(rectangle);
    }

    public boolean isInRangeMove(final int n) {
        return this.x < this.xFirst + MobTemplates.get(this.templateId).rangeMove && this.x > this.xFirst - MobTemplates.get(this.templateId).rangeMove;
    }

    @Override
    public boolean isInvisible() {
        return this.action == 3;
    }

    public boolean isMobFocus() {
        return Player.myCharz().mobFocus != null && Player.myCharz().mobFocus.mobId == super.mobId;
    }

    public boolean isMonsFly() {
        return false;
    }

    boolean isNewMod() {
        return false;
    }

    boolean isNewModStand() {
        return this.templateId == 76;
    }

    boolean isSpecial() {
        return false;
    }

    boolean isTypeNewMod() {
        return false;
    }

    public void paint(final mGraphics mGraphics) {
        if (Player.myCharz().isGetSharigan && !this.isMobMe) {
            return;
        }
        if (this.isHideByDie) {
            return;
        }
        if (this.templateId == 11) {
            this.isShadown = false;
        }
        if (this.isShadown) {
            this.paintShadow(mGraphics);
        }
        this.paintEffectBefore(mGraphics);
        final QuaiTemplate mobTemplate = this.mobTemplate;
        if (mobTemplate != null) {
            mobTemplate.paint(mGraphics, this.action, this.x, this.y, this.dir, this.isDieEffect, this.xSd);
        }
        this.paintEffectAfter(mGraphics);
        final FrameEffectMore frameEffectMore = this.frameEffectMore;
        if (frameEffectMore != null) {
            frameEffectMore.paint(mGraphics);
        }
        this.paintShield(mGraphics);
        this.paintHp(mGraphics);
        this.paintName(mGraphics);
    }

    public void paintEffectAfter(final mGraphics mGraphics) {
        for (int i = 0; i < this.vEffectAfters.size(); ++i) {
            this.vEffectAfters.get(i).paint(mGraphics, Player.myCharz().cx, Player.myCharz().cy, this.frame, (byte)0, super.subDirect, 0, false, false);
        }
    }

    public void paintEffectBefore(final mGraphics mGraphics) {
        for (int i = 0; i < this.vEffectBefores.size(); ++i) {
            this.vEffectBefores.get(i).paint(mGraphics, this.xSdReal, this.ySdReal, this.frame, this.dirTemp, super.subDirect, 0, false, false);
        }
    }

    public void paintHp(final mGraphics mGraphics) {
        if ((this.isMobFocus() || this.isShowHp) && this.status != 1 && this.action != 3 && this.hp > 0) {
            final Image imgHPtem = this.imgHPtem;
            if (imgHPtem != null) {
                this.w_real = mGraphics.getImageWidth(imgHPtem);
                this.h_real = mGraphics.getImageHeight(this.imgHPtem);
                final int w_real = this.w_real;
                final int per = this.per;
                final int n = w_real * per / 100;
                this.w_result = n;
                this.w_countDown = n;
                final int per_tem = this.per_tem;
                if (per_tem >= per) {
                    if (this.isFirstPaintHp) {
                        this.isFirstPaintHp = false;
                        this.per_tem = per;
                        this.w_countDown = per * w_real / 100;
                    }
                    else {
                        int offset;
                        if (CanvasNinja.gameTick % 6 > 3) {
                            offset = this.offset++;
                        }
                        else {
                            offset = this.offset;
                        }
                        final int per_tem2 = per_tem - offset;
                        this.per_tem = per_tem2;
                        this.w_countDown = w_real * per_tem2 / 100;
                        if (per_tem2 <= 0) {
                            this.per_tem = 0;
                        }
                        if (this.per_tem < per) {
                            this.per_tem = per;
                        }
                        if (this.offset >= 3) {
                            this.offset = 3;
                        }
                    }
                }
                mGraphics.drawImage(LoadDataManager.imgHP_tm_xam, (float)(this.x - (w_real >> 1) - (LoadDataManager.imgHeIcon[this.sys].getRWidth() + 5) / 2 + this.dir * this.mobTemplate.optionMobTemplate.offSetX), (float)(this.y - this.h - this.disY), 20);
                mGraphics.drawRegion(LoadDataManager.imgHP_tm_trang, 0.0f, 0.0f, (float)this.w_countDown, (float)this.h_real, 0, (float)(this.x - (this.w_real >> 1) - (LoadDataManager.imgHeIcon[this.sys].getRWidth() + 5) / 2 + this.dir * this.mobTemplate.optionMobTemplate.offSetX), (float)(this.y - this.h - this.disY), 20);
                mGraphics.drawRegion(this.imgHPtem, 0.0f, 0.0f, (float)this.w_result, (float)this.h_real, 0, (float)(this.x - (this.w_real >> 1) - (LoadDataManager.imgHeIcon[this.sys].getRWidth() + 5) / 2 + this.dir * this.mobTemplate.optionMobTemplate.offSetX), (float)(this.y - this.h - this.disY), 20);
                mGraphics.drawImage(LoadDataManager.imgHeIcon[this.sys], (float)(this.x + (this.w_real >> 1) + this.dir * this.mobTemplate.optionMobTemplate.offSetX), (float)(this.y - this.h - this.disY + LoadDataManager.imgHP_tm_xam.getRHeight() / 2 - this.hHe / 2));
            }
        }
    }

    public void paintHpSpec(final mGraphics mGraphics, final int n, final int n2) {
        if ((this.isMobFocus() || this.isShowHp) && this.status != 1 && this.action != 3 && this.hp > 0) {
            this.w_real_spec = mGraphics.getImageWidth(LoadDataManager.imgHP_tm_xanh_big_nen);
            this.h_real_spec = mGraphics.getImageHeight(LoadDataManager.imgHP_tm_xanh_big_nen);
            final int w_real_spec = this.w_real_spec;
            final int per = this.per;
            final int n3 = w_real_spec * per / 100;
            this.w_result_spec = n3;
            this.w_countDown_spec = n3;
            final int per_tem = this.per_tem;
            if (per_tem >= per) {
                int offset;
                if (CanvasNinja.gameTick % 6 > 3) {
                    offset = this.offset++;
                }
                else {
                    offset = this.offset;
                }
                final int per_tem2 = per_tem - offset;
                this.per_tem = per_tem2;
                this.w_countDown_spec = w_real_spec * per_tem2 / 100;
                if (per_tem2 <= 0) {
                    this.per_tem = 0;
                }
                if (this.per_tem < per) {
                    this.per_tem = per;
                }
                if (this.offset >= 3) {
                    this.offset = 3;
                }
            }
            mGraphics.drawImage(LoadDataManager.imgHP_tm_xanh_big_nen, (float)(n - (w_real_spec >> 1)), (float)n2, 20);
            mGraphics.drawRegion(LoadDataManager.imgHP_tm_trang_big, 0.0f, 0.0f, (float)this.w_countDown_spec, (float)this.h_real_spec, 0, (float)(n + 2 - (this.w_real_spec >> 1)), (float)n2, 20);
            mGraphics.drawRegion(LoadDataManager.imgHP_tm_xanh_big, 0.0f, 0.0f, (float)this.w_result_spec, (float)this.h_real_spec, 0, (float)(n - (this.w_real_spec >> 1)), (float)n2, 20);
            mGraphics.drawImage(LoadDataManager.imgHeIcon[this.sys], (float)(n + this.w_real_spec / 2 + 5), (float)(LoadDataManager.imgHP_tm_xanh_big_nen.getRHeight() / 2 + n2 - this.hHe / 2));
        }
    }

    public void paintPopup(final mGraphics mGraphics) {
        final Popup popup = super.popup;
        if (popup != null) {
            popup.paint(mGraphics, this.x, this.y - this.h - 5);
        }
    }

    public void paintShadow(final mGraphics mGraphics) {
        final QuaiTemplate mobTemplate = this.mobTemplate;
        if (mobTemplate != null) {
            final OptionTemplate optionMobTemplate = mobTemplate.optionMobTemplate;
            if (optionMobTemplate != null) {
                final int typeShadow = optionMobTemplate.typeShadow;
                if (typeShadow == 1) {
                    final int xSdReal = this.xSd + this.dir * optionMobTemplate.offSetX;
                    this.xSdReal = xSdReal;
                    final int ySdReal = this.ySd + optionMobTemplate.offSetY;
                    this.ySdReal = ySdReal;
                    mGraphics.drawImage(MyTile.shadowQuai, (float)xSdReal, (float)ySdReal, 3);
                }
                else if (typeShadow == 2) {
                    final int xSdReal2 = this.xSd + this.dir * optionMobTemplate.offSetX;
                    this.xSdReal = xSdReal2;
                    final int ySdReal2 = this.ySd + optionMobTemplate.offSetY;
                    this.ySdReal = ySdReal2;
                    mGraphics.drawImage(MyTile.shadowQuai2, (float)xSdReal2, (float)ySdReal2, 3);
                }
                else if (typeShadow == 3) {
                    final int xSdReal3 = this.xSd + this.dir * optionMobTemplate.offSetX;
                    this.xSdReal = xSdReal3;
                    final int ySdReal3 = this.ySd + optionMobTemplate.offSetY;
                    this.ySdReal = ySdReal3;
                    mGraphics.drawImage(MyTile.shadowQuai3, (float)xSdReal3, (float)ySdReal3, 3);
                }
            }
        }
    }

    public void paintShield(final mGraphics mGraphics) {
        final Iterator<EffectShield> iterator = this.vShields.iterator();
        while (iterator.hasNext()) {
            iterator.next().paint(mGraphics, this.x, this.y);
        }
        this.paintShieldTopCenter(mGraphics);
    }

    public void paintShieldTopCenter(final mGraphics mGraphics) {
        final Iterator<EffectShield> iterator = this.vShields.iterator();
        while (iterator.hasNext()) {
            iterator.next().paintTopCenter(mGraphics, this.x, this.y - this.h);
        }
    }

    public void pull(int n, final int pullTargetY) {
        if (!this.isBeingPulled && !this.isDie && !this.isHideByDie) {
            final int n2 = 1;
            this.isBeingPulled = true;
            this.pullTargetX = n;
            this.pullTargetY = pullTargetY;
            if (n > this.x) {
                n = n2;
            }
            else {
                n = -1;
            }
            this.dir = n;
            this.status = 2;
            this.action = 0;
            final QuaiTemplate mobTemplate = this.mobTemplate;
            if (mobTemplate != null) {
                mobTemplate.index = 0;
            }
        }
    }

    public void resetSetFromDie() {
        this.tickDie = 0;
        this.isDieEffect = false;
        this.isDie = false;
        this.isHideByDie = false;
        this.injureThenDie = false;
        this.status = 2;
        this.action = 0;
        final QuaiTemplate mobTemplate = this.mobTemplate;
        if (mobTemplate != null) {
            mobTemplate.index = 0;
        }
    }

    public void setAction(final byte action, final byte dir) {
        if (dir != -2) {
            this.dir = dir;
        }
        this.action = action;
        final QuaiTemplate mobTemplate = this.mobTemplate;
        if (mobTemplate != null) {
            mobTemplate.index = 0;
        }
    }

    public void setAttack(final Player cFocus) {
        this.isBusyAttackSomeOne = true;
        this.mobToAttack = null;
        this.cFocus = cFocus;
        this.p1 = 0;
        this.p2 = 0;
        this.status = 3;
        this.action = 4;
        final QuaiTemplate mobTemplate = this.mobTemplate;
        if (mobTemplate != null) {
            mobTemplate.index = 0;
        }
        this.tick = 0;
        final int cx = cFocus.cx;
        final int x = this.x;
        int dir;
        if (cx > x) {
            dir = 1;
        }
        else {
            dir = -1;
        }
        this.dir = dir;
        final int cx2 = cFocus.cx;
        final int cy = cFocus.cy;
        if (Res.abs(cx2 - x) < this.w * 2 && Res.abs(cy - this.y) < this.h * 2) {
            this.p3 = 0;
        }
        else {
            this.p3 = 1;
        }
    }

    public void setAttack(final Player cFocus, final boolean isUpdateHp) {
        final int n = cFocus.cx - this.x;
        if (Res.abs(n) > 32) {
            final TaskMob taskMob = new TaskMob();
            taskMob.dis = 32 - 5;
            taskMob.speed = Res.abs(n) / 6;
            taskMob.cFocus = cFocus;
            taskMob.isUpdateHp = isUpdateHp;
            taskMob.status = 3;
            this.vTaskMobs.add(taskMob);
        }
        else {
            this.doAttack(cFocus, isUpdateHp);
        }
    }

    public void setAttack(final Quai mobToAttack) {
        int dir = 1;
        this.isBusyAttackSomeOne = true;
        this.cFocus = null;
        this.mobToAttack = mobToAttack;
        this.p1 = 0;
        this.p2 = 0;
        this.status = 3;
        this.action = 4;
        final QuaiTemplate mobTemplate = this.mobTemplate;
        if (mobTemplate != null) {
            mobTemplate.index = 0;
        }
        this.tick = 0;
        if (mobToAttack.x <= this.x) {
            dir = -1;
        }
        this.dir = dir;
    }

    public void setInjure() {
        final int hp = this.hp;
        if (hp > 0) {
            final int status = this.status;
            if (status != 3 && status != 7) {
                if (hp <= 0) {
                    return;
                }
                this.timeStatus = 4;
                if (status != 3) {
                    if (status != 3) {
                        this.status = 7;
                        this.action = 2;
                        final QuaiTemplate mobTemplate = this.mobTemplate;
                        if (mobTemplate != null) {
                            mobTemplate.index = 0;
                        }
                    }
                    final QuaiTemplate mobTemplate2 = this.mobTemplate;
                    if (mobTemplate2 != null) {
                        mobTemplate2.index = 0;
                    }
                }
            }
        }
    }

    public void setShowHP() {
        this.isShowHp = true;
        this.timeStartShowHP = mSystem.currentTimeMillis() + this.timeDelayHideHP;
        this.hpTemp = this.hp;
    }

    public void startDie() {
        Res.outz(1, "mob start die");
        this.injureThenDie = true;
        this.hp = 0;
        this.isLastHit = true;
        this.status = 1;
        this.action = 3;
        final QuaiTemplate mobTemplate = this.mobTemplate;
        if (mobTemplate != null) {
            mobTemplate.index = 0;
        }
        this.p1 = -3;
        this.p2 = -this.dir;
        this.p3 = 0;
    }

    public void startEffectHurt(final Player injureBy) {
        if (this.hp <= 0) {
            return;
        }
        final byte direct = injureBy.direct;
        this.injureBy = injureBy;
        this.status = 7;
        this.action = 2;
        final QuaiTemplate mobTemplate = this.mobTemplate;
        if (mobTemplate != null) {
            mobTemplate.index = 0;
        }
        final FrameImage frameMobHurt = LoadDataManager.frameMobHurt;
        this.frameEffectMore = new FrameEffectMore(frameMobHurt, this.x + this.w / 2 - (int)frameMobHurt.frameWidth, this.y - (int)frameMobHurt.frameHeight);
        MapScr.startSplash(this.x, this.y - (this.h >> 1), super.cdir);
        final EffectTarget effectTarget = new EffectTarget(this, (byte)0, 0, direct);
        effectTarget.loadEffectFromTool(new short[] { 2 });
        effectTarget.isRemoveWhenDone = true;
        this.vEffectSkillWaiting.add(effectTarget);
    }

    public void startPopup(final String s, final int n) {
        (super.popup = new Popup()).startPopup(s, n);
    }

    @Override
    public void stopMoving() {
        if (this.status == 5) {
            this.status = 2;
            this.action = 0;
            this.p3 = 0;
            this.p2 = 0;
            this.p1 = 0;
            this.forceWait = 10;
        }
    }

    public void update() {
        if (this.isBeingPulled) {
            this.updatePull();
            return;
        }
        final Player cFocus = this.cFocus;
        if (cFocus != null && MapScr.findCharInMap(cFocus.charID) == null) {
            this.cFocus = null;
        }
        this.updateTask();
        if (this.isCanMoveFrame() && CanvasNinja.gameTick % 5 == 0 && ++this.yAnimStand > 1) {
            this.yAnimStand = 0;
        }
        this.updateShield();
        final FrameEffectMore frameEffectMore = this.frameEffectMore;
        if (frameEffectMore != null) {
            frameEffectMore.update();
        }
        this.updateEffectSkill();
        this.updateNew();
        if (this.isHideByDie) {
            return;
        }
        this.updateHideHP();
        this.GetFrame();
        if (this.isFreez) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.last >= 1000L) {
                final int seconds = this.seconds - 1;
                this.seconds = seconds;
                this.last = currentTimeMillis;
                if (seconds < 0) {
                    this.isFreez = false;
                    this.seconds = 0;
                }
            }
            if (this.isTypeNewMod()) {
                final int[] hurt = this.hurt;
                this.frame = hurt[CanvasNinja.gameTick % hurt.length];
            }
            else if (this.isNewModStand()) {
                final int[] attack1 = this.attack1;
                this.frame = attack1[CanvasNinja.gameTick % attack1.length];
            }
            else if (this.isNewMod()) {
                if (CanvasNinja.gameTick % 20 > 5) {
                    this.frame = 11;
                }
                else {
                    this.frame = 10;
                }
            }
            else if (this.isSpecial()) {
                if (CanvasNinja.gameTick % 20 > 5) {
                    this.frame = 1;
                }
                else {
                    this.frame = 15;
                }
            }
            else if (CanvasNinja.gameTick % 20 > 5) {
                this.frame = 11;
            }
            else {
                this.frame = 10;
            }
        }
        if (this.isShadown) {
            this.updateShadow();
        }
        if (this.vMobMove == null && MobTemplates.get(this.templateId).rangeMove != 0) {
            return;
        }
        if (this.status != 3 && this.isBusyAttackSomeOne) {
            final Player cFocus2 = this.cFocus;
            if (cFocus2 != null) {
                cFocus2.doInjure(this.dame, this.dameMp, this.dameSp, false, true);
            }
            else {
                final Quai mobToAttack = this.mobToAttack;
                if (mobToAttack != null) {
                    mobToAttack.setInjure();
                }
            }
            this.isBusyAttackSomeOne = false;
        }
        if (this.status == 5 && this.isBlock()) {
            this.action = 0;
            return;
        }
        switch (this.status) {
            case 7: {
                this.action = 2;
                this.updateInjure();
                break;
            }
            case 6: {
                this.action = 2;
                this.timeStatus = 0;
                final int p1 = this.p1 + 1;
                this.p1 = p1;
                final int y = this.y + p1;
                this.y = y;
                final int yFirst = this.yFirst;
                if (y >= yFirst) {
                    this.y = yFirst;
                    this.p1 = 0;
                    this.status = 5;
                    this.action = 1;
                    break;
                }
                break;
            }
            case 5: {
                this.action = 1;
                if (this.holdEffID != 0) {
                    return;
                }
                if (this.blindEff) {
                    return;
                }
                if (this.sleepEff) {
                    return;
                }
                if (this.isFreez) {
                    if (MobTemplates.get(this.templateId).type == 4) {
                        ++this.ty;
                        final int wt = this.wt + 1;
                        this.wt = wt;
                        final int fy = this.fy;
                        final boolean wy = this.wy;
                        int n;
                        if (!wy) {
                            n = 1;
                        }
                        else {
                            n = -1;
                        }
                        this.fy = fy + n;
                        if (wt == 10) {
                            this.wt = 0;
                            this.wy = (wy ^ true);
                        }
                    }
                    return;
                }
                this.timeStatus = 0;
                if (this.taskMobTemp != null) {
                    this.updateMobByTask();
                    break;
                }
                this.updateMobWalk();
                break;
            }
            case 4: {
                this.action = 0;
                if (this.holdEffID != 0) {
                    return;
                }
                if (this.blindEff) {
                    return;
                }
                if (this.sleepEff) {
                    return;
                }
                if (this.isFreez) {
                    return;
                }
                this.timeStatus = 0;
                if (++this.p1 > super.mobId % 5 + 40) {
                    this.y -= 2;
                    this.status = 5;
                    this.action = 1;
                    this.p1 = 0;
                    break;
                }
                break;
            }
            case 3: {
                this.action = 4;
                if (this.holdEffID != 0) {
                    return;
                }
                if (this.blindEff) {
                    return;
                }
                if (this.sleepEff) {
                    return;
                }
                if (this.isFreez) {
                    return;
                }
                this.updateMobAttack();
                break;
            }
            case 2: {
                this.action = 0;
                if (this.holdEffID != 0) {
                    return;
                }
                if (this.isFreez) {
                    return;
                }
                if (this.blindEff) {
                    return;
                }
                if (this.sleepEff) {
                    return;
                }
                this.timeStatus = 0;
                this.updateMobStandWait();
                break;
            }
            case 1: {
                this.updateDie();
                break;
            }
        }
    }

    public void updateAddEffectDelay() {
        for (int i = this.vEffectSkillWaiting.size() - 1; i >= 0; --i) {
            final MainEffect mainEffect = this.vEffectSkillWaiting.elementAt(i);
            if (mainEffect.timeDelayAddSkill < mSystem.currentTimeMillis()) {
                if (mainEffect.typeBeforeOrAfter == 0) {
                    this.vEffectAfters.add(mainEffect);
                }
                if (mainEffect.typeBeforeOrAfter == 1) {
                    this.vEffectBefores.add(mainEffect);
                }
                this.vEffectSkillWaiting.remove(i);
                break;
            }
        }
    }

    public void updateDie() {
        this.action = 3;
        this.isDie = true;
        if (CanvasNinja.gameTick % 3 == 0) {
            final StringBuilder sb = new StringBuilder();
            sb.append("TickDie:");
            sb.append(this.tickDie);
            Res.outz(1, sb.toString());
            ++this.tickDie;
        }
        if (this.tickDie >= this.maxTickDie) {
            Res.outz("debug 111111111");
            this.tickDie = 0;
            this.isDieEffect = false;
        }
        else {
            this.isDieEffect = true;
        }
        this.isDisable = false;
        this.isDontMove = false;
        this.isFire = false;
        this.isIce = false;
        this.isWind = false;
        final int y = this.y + this.p1;
        this.y = y;
        if (CanvasNinja.gameTick % 2 == 0) {
            final int p2 = this.p2;
            if (p2 > 1) {
                this.p2 = p2 - 1;
            }
            else if (p2 < -1) {
                this.p2 = p2 + 1;
            }
        }
        final int x = this.x + this.p2;
        this.x = x;
        if (this.isDie) {
            this.isHideByDie = true;
            this.p1 = 0;
            this.p2 = 0;
            this.y = 0;
            this.x = 0;
            this.hp = this.getTemplate().hpMax;
            this.status = 0;
            this.timeStatus = 0;
            return;
        }
        if ((MyTile.tileTypeAtPixel(x, y) & 0x2) == 0x2) {
            final int p3 = this.p1;
            int p4;
            if (p3 > 4) {
                p4 = -4;
            }
            else {
                p4 = -p3;
            }
            this.p1 = p4;
            if (this.p3 == 0) {
                this.p3 = 16;
            }
        }
        else {
            ++this.p1;
        }
        int p5 = this.p3;
        if (p5 > 0) {
            --p5;
            if ((this.p3 = p5) == 0) {
                this.isDie = true;
            }
        }
    }

    public void updateEffectSkill() {
        this.updateAddEffectDelay();
        for (int i = 0; i < this.vEffectAfters.size(); ++i) {
            final MainEffect mainEffect = this.vEffectAfters.get(i);
            mainEffect.update();
            if ((mainEffect.timeBuff > 0L && mSystem.currentTimeMillis() > mainEffect.timeBuff) || mainEffect.isRemove) {
                if (mainEffect.isBigBang) {
                    MapScr.createBigBang(super.cx - MapScr.cmx, super.cy - MapScr.cmy, 20);
                }
                this.vEffectAfters.remove(i);
            }
        }
        for (int j = 0; j < this.vEffectBefores.size(); ++j) {
            final MainEffect mainEffect2 = this.vEffectBefores.get(j);
            mainEffect2.update();
            if ((mainEffect2.timeBuff > 0L && mSystem.currentTimeMillis() > mainEffect2.timeBuff) || mainEffect2.isRemove) {
                if (mainEffect2.isBigBang) {
                    MapScr.createBigBang(super.cx - MapScr.cmx, super.cy - MapScr.cmy, 20);
                }
                this.vEffectBefores.remove(j);
            }
        }
    }

    public void updateHPServer() {
        if (this.isUpdateHp_server) {
            final int hpTemp_server = this.HPTemp_server;
            if (hpTemp_server == -1 || this.dmgHitTemp_server == -1) {
                return;
            }
            this.isUpdateHp_server = false;
            this.hp = hpTemp_server;
            this.updateHp_bar();
            if (this.dmgHitTemp_server == 0) {
                MapScr.startFlyText(SupportTranslate.getTextLangue("miss"), this.x, this.y - this.h, 0, -2, 4);
            }
            else {
                final StringBuilder sb = new StringBuilder();
                sb.append("-");
                sb.append(this.dmgHitTemp_server);
                MapScr.startFlyText(sb.toString(), this.x, this.y - this.h, 0, -2, 5);
            }
            if (this.hp <= 0) {
                this.startDie();
            }
            this.HPTemp_server = -1;
            this.dmgHitTemp_server = -1;
        }
    }

    public void updateHe() {
        final int sys = this.sys;
        Image image;
        if (sys < 5) {
            image = LoadDataManager.imgHeIcon[sys];
        }
        else {
            image = LoadDataManager.imgHeIcon[0];
        }
        final int rWidth = image.getRWidth();
        this.wHe = rWidth;
        this.wHe = rWidth + 5;
        final int sys2 = this.sys;
        Image image2;
        if (sys2 < 5) {
            image2 = LoadDataManager.imgHeIcon[sys2];
        }
        else {
            image2 = LoadDataManager.imgHeIcon[0];
        }
        this.hHe = image2.getRHeight();
    }

    public void updateHp_bar() {
        final int hp = this.hp;
        final long n = hp;
        final int maxHp = this.maxHp;
        this.len = (int)(n * 100L / maxHp * this.w_hp_bar) / 100;
        final int n2 = (int)(hp * 100L / maxHp);
        this.per = n2;
        if (n2 >= 100) {
            this.per_tem = n2;
        }
        this.offset = 0;
        if (n2 < 30) {
            this.color = 15473700;
            this.imgHPtem = LoadDataManager.imgHP_tm_do;
        }
        else if (n2 < 60) {
            this.color = 16744448;
            this.imgHPtem = LoadDataManager.imgHP_tm_vang;
        }
        else {
            this.color = 11992374;
            this.imgHPtem = LoadDataManager.imgHP_tm_xanh;
        }
    }

    public void updateMobAttack() {
        int[] array;
        if (this.p3 == 0) {
            array = this.attack1;
        }
        else {
            array = this.attack2;
        }
        final int tick = this.tick;
        final int length = array.length;
        final int n = 1;
        if (tick < length) {
            this.checkFrameTick(array);
            final int x = this.x;
            final int cmx = MapScr.cmx;
            if (x >= cmx && x <= cmx + CanvasNinja.w && this.p3 == 0 && CanvasNinja.gameTick % 2 == 0) {
                Res.outz(1, "Sound atk");
                AudioManager.mobPunch(false, 1.0f);
            }
        }
        final int p1 = this.p1;
        if (p1 == 0) {
            final Player cFocus = this.cFocus;
            if (cFocus == null && this.mobToAttack == null) {
                return;
            }
            int n2;
            if (cFocus != null) {
                n2 = cFocus.cx;
            }
            else {
                n2 = this.mobToAttack.x;
            }
            if (cFocus != null) {
                final int cy = cFocus.cy;
            }
            else {
                final int y = this.mobToAttack.y;
            }
            if (!this.isNewMod()) {
                if (this.x > this.xFirst + MobTemplates.get(this.templateId).rangeMove) {
                    this.p1 = 1;
                }
                if (this.x < this.xFirst - MobTemplates.get(this.templateId).rangeMove) {
                    this.p1 = 1;
                }
            }
            final int p2 = this.p2 + 1;
            this.p2 = p2;
            if (p2 > array.length - 1 || this.p1 == 1) {
                this.p1 = 1;
                if (this.p3 == 0) {
                    final Player cFocus2 = this.cFocus;
                    if (cFocus2 != null) {
                        cFocus2.doInjure(this.dame, this.dameMp, this.dameSp, false, true);
                    }
                    else {
                        final Quai mobToAttack = this.mobToAttack;
                        if (mobToAttack != null) {
                            mobToAttack.setInjure();
                            final Player myBoss = this.myBoss;
                            if (myBoss != null && myBoss.isMe()) {
                                final VectorCustom vectorCustom = new VectorCustom();
                                vectorCustom.addElement(this.mobToAttack);
                                final VectorCustom vectorCustom2 = new VectorCustom();
                                final Skill skill = this.skill;
                                if (skill != null && skill.template != null) {
                                    SendMessage.gI().sendPlayerAttack(vectorCustom, vectorCustom2, this.skill.template.id);
                                }
                            }
                        }
                    }
                    this.isBusyAttackSomeOne = false;
                }
                else {
                    if (this.cFocus == null) {
                        final Player player = new Player();
                        final Quai mobToAttack2 = this.mobToAttack;
                        player.cx = mobToAttack2.x;
                        player.cy = mobToAttack2.y;
                        player.charID = -100;
                    }
                    this.isBusyAttackSomeOne = false;
                }
            }
            int dir;
            if (this.x < n2) {
                dir = n;
            }
            else {
                dir = -1;
            }
            this.dir = dir;
        }
        else if (p1 == 1) {
            if (MobTemplates.get(this.templateId).type != 0) {
                final boolean isDontMove = this.isDontMove;
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("tick:");
            sb.append(this.tick);
            sb.append("len.length:");
            sb.append(array.length);
            Res.outz(1, sb.toString());
            if (this.tick == array.length) {
                this.status = 2;
                this.action = 0;
                this.p1 = 0;
                this.p2 = 0;
                this.tick = 0;
            }
        }
    }

    public void updateMobByTask() {
        final TaskMob taskMobTemp = this.taskMobTemp;
        int n;
        if (taskMobTemp != null) {
            n = taskMobTemp.speed;
        }
        else {
            n = MobTemplates.get(this.templateId).speed;
        }
        final TaskMob taskMobTemp2 = this.taskMobTemp;
        final int cx = taskMobTemp2.cFocus.cx;
        int dir;
        if (cx > this.x) {
            dir = 1;
        }
        else {
            dir = -1;
        }
        this.dir = dir;
        final int n2 = cx - dir * taskMobTemp2.dis;
        try {
            if (this.injureThenDie) {
                this.status = 1;
                this.action = 3;
                this.p2 = this.injureBy.cdir << 3;
                this.p1 = -5;
                this.p3 = 0;
            }
            if (this.isIce) {
                return;
            }
            if (this.isDontMove || this.isWind) {
                this.checkFrameTick(this.stand);
                return;
            }
            switch (MobTemplates.get(this.templateId).type) {
                case 5: {
                    final byte b = (byte)n;
                    final int x = this.x + this.dir * b;
                    this.x = x;
                    if (CanvasNinja.gameTick % 10 > 2) {
                        this.y += this.dirV * b;
                    }
                    if (Res.distance(n2, x) < b) {
                        this.taskMobTemp.speed = (byte)Res.distance(n2, this.x);
                    }
                    if (!this.isInRangeMove(n2)) {
                        Res.outz(1, "Out range move cancel");
                        this.doAttack(this.taskMobTemp.cFocus, this.isUpdateHp_server);
                        this.taskMobTemp = null;
                        return;
                    }
                    if (Res.distance(n2, this.x) < 1) {
                        final TaskMob taskMobTemp3 = this.taskMobTemp;
                        this.setAttack(taskMobTemp3.cFocus, taskMobTemp3.isUpdateHp);
                        this.taskMobTemp = null;
                    }
                    final int y = this.y;
                    final int yFirst = this.yFirst;
                    if (y > yFirst + 24) {
                        this.dirV = -1;
                    }
                    else if (y < yFirst - (CanvasNinja.gameTick % 10 + 20)) {
                        this.dirV = 1;
                    }
                    if (!MyTile.tileTypeAt(this.x, y, 2)) {
                        break;
                    }
                    if (CanvasNinja.gameTick % 10 > 5) {
                        this.y = MyTile.tileYofPixel(this.y);
                        this.status = 4;
                        this.p1 = 0;
                        this.dirV = -1;
                        break;
                    }
                    this.dirV = -1;
                    break;
                }
                case 4: {
                    final byte b2 = (byte)n;
                    final int x2 = this.x + this.dir * b2;
                    this.x = x2;
                    if (CanvasNinja.gameTick % 10 > 2) {
                        this.y += this.dirV * b2;
                    }
                    if (Res.distance(n2, x2) < b2) {
                        this.taskMobTemp.speed = (byte)Res.distance(n2, this.x);
                    }
                    if (!this.isInRangeMove(n2)) {
                        Res.outz(1, "Out range move cancel");
                        this.doAttack(this.taskMobTemp.cFocus, this.isUpdateHp_server);
                        this.taskMobTemp = null;
                        return;
                    }
                    if (Res.distance(n2, this.x) < 1) {
                        final TaskMob taskMobTemp4 = this.taskMobTemp;
                        this.setAttack(taskMobTemp4.cFocus, taskMobTemp4.isUpdateHp);
                        this.taskMobTemp = null;
                    }
                    final int y2 = this.y;
                    final int yFirst2 = this.yFirst;
                    if (y2 > yFirst2 + 24) {
                        this.dirV = -1;
                    }
                    else if (y2 < yFirst2 - (CanvasNinja.gameTick % 10 + 20)) {
                        this.dirV = 1;
                    }
                    this.checkFrameTick(this.move);
                    break;
                }
                case 1:
                case 2:
                case 3: {
                    final int x3 = this.x + this.dir * n;
                    this.x = x3;
                    if (Res.distance(n2, x3) < n) {
                        this.taskMobTemp.speed = Res.distance(n2, this.x);
                    }
                    if (!this.isInRangeMove(n2)) {
                        Res.outz(1, "Out range move cancel");
                        this.doAttack(this.taskMobTemp.cFocus, this.isUpdateHp_server);
                        this.taskMobTemp = null;
                        return;
                    }
                    if (Res.distance(n2, this.x) < 1) {
                        final TaskMob taskMobTemp5 = this.taskMobTemp;
                        this.setAttack(taskMobTemp5.cFocus, taskMobTemp5.isUpdateHp);
                        this.taskMobTemp = null;
                    }
                    int[] array;
                    if (this.w > 20) {
                        array = this.move;
                    }
                    else {
                        array = this.moveFast;
                    }
                    this.checkFrameTick(array);
                    break;
                }
                case 0: {
                    this.frame = 0;
                    this.action = 0;
                    break;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateMobWalk() {
        try {
            final boolean injureThenDie = this.injureThenDie;
            final int n = 1;
            if (injureThenDie) {
                this.status = 1;
                this.action = 3;
                this.p2 = this.injureBy.cdir << 3;
                this.p1 = -5;
                this.p3 = 0;
            }
            if (this.isIce) {
                return;
            }
            if (this.isDontMove || this.isWind) {
                this.checkFrameTick(this.stand);
                return;
            }
            switch (MobTemplates.get(this.templateId).type) {
                case 5: {
                    final byte speed = MobTemplates.get(this.templateId).speed;
                    final int mobId = super.mobId;
                    final byte b = (byte)(mobId % 2 + speed);
                    final int x = this.x + this.dir * b;
                    this.x = x;
                    final int gameTick = CanvasNinja.gameTick;
                    final byte b2 = (byte)((mobId + gameTick) % 2 + b);
                    if (gameTick % 10 > 2) {
                        this.y += this.dirV * b2;
                    }
                    if (x > this.xFirst + MobTemplates.get(this.templateId).rangeMove) {
                        this.dir = -1;
                        this.status = 2;
                        this.forceWait = 5;
                        this.p1 = 0;
                    }
                    else if (this.x < this.xFirst - MobTemplates.get(this.templateId).rangeMove) {
                        this.dir = 1;
                        this.status = 2;
                        this.forceWait = 5;
                        this.p1 = 0;
                    }
                    final int y = this.y;
                    final int yFirst = this.yFirst;
                    if (y > yFirst + 24) {
                        this.dirV = -1;
                    }
                    else if (y < yFirst - (CanvasNinja.gameTick % 10 + 20)) {
                        this.dirV = 1;
                    }
                    if (!MyTile.tileTypeAt(this.x, y, 2)) {
                        break;
                    }
                    if (CanvasNinja.gameTick % 10 > 5) {
                        this.y = MyTile.tileYofPixel(this.y);
                        this.status = 4;
                        this.p1 = 0;
                        this.dirV = -1;
                        break;
                    }
                    this.dirV = -1;
                    break;
                }
                case 4: {
                    final byte speed2 = MobTemplates.get(this.templateId).speed;
                    final int mobId2 = super.mobId;
                    final byte b3 = (byte)(mobId2 % 2 + speed2);
                    final int x2 = this.x + this.dir * b3;
                    this.x = x2;
                    final int gameTick2 = CanvasNinja.gameTick;
                    if (gameTick2 % 10 > 2) {
                        this.y += this.dirV * b3;
                    }
                    final byte b4 = (byte)((gameTick2 + mobId2) % 2 + b3);
                    if (x2 > this.xFirst + MobTemplates.get(this.templateId).rangeMove) {
                        this.dir = -1;
                        this.status = 2;
                        this.forceWait = 5;
                        this.p1 = 0;
                    }
                    else if (this.x < this.xFirst - MobTemplates.get(this.templateId).rangeMove) {
                        this.dir = 1;
                        this.status = 2;
                        this.forceWait = 5;
                        this.p1 = 0;
                    }
                    final int y2 = this.y;
                    final int yFirst2 = this.yFirst;
                    if (y2 > yFirst2 + 24) {
                        this.dirV = -1;
                    }
                    else if (y2 < yFirst2 - (CanvasNinja.gameTick % 10 + 20)) {
                        this.dirV = 1;
                    }
                    this.checkFrameTick(this.move);
                    break;
                }
                case 1:
                case 2:
                case 3: {
                    final TaskMob taskMobTemp = this.taskMobTemp;
                    int n2;
                    if (taskMobTemp != null) {
                        n2 = taskMobTemp.speed;
                    }
                    else {
                        n2 = MobTemplates.get(this.templateId).speed;
                    }
                    final int n3 = n2;
                    int n4;
                    if (n3 == 1) {
                        n4 = n3;
                        if (CanvasNinja.gameTick % 2 == 1) {
                            break;
                        }
                    }
                    else if (n3 > 2) {
                        n4 = n3 + super.mobId % 2;
                    }
                    else {
                        n4 = n3;
                        if (CanvasNinja.gameTick % 2 == 1) {
                            n4 = n3 - 1;
                        }
                    }
                    if ((this.x += this.dir * n4) > this.xFirst + MobTemplates.get(this.templateId).rangeMove) {
                        this.dir = -1;
                    }
                    else if (this.x < this.xFirst - MobTemplates.get(this.templateId).rangeMove) {
                        this.dir = 1;
                    }
                    if (this.taskMobTemp == null && Res.abs(this.x - Player.myCharz().cx) < 40 && Res.abs(this.x - this.xFirst) < MobTemplates.get(this.templateId).rangeMove && Res.distance(Player.myCharz().cx, Player.myCharz().cy, this.x, this.y) < 40) {
                        int dir = n;
                        if (this.x > Player.myCharz().cx) {
                            dir = -1;
                        }
                        this.dir = dir;
                        this.status = 2;
                        this.action = 0;
                        this.forceWait = 50;
                    }
                    int[] array;
                    if (this.w > 30) {
                        array = this.move;
                    }
                    else {
                        array = this.moveFast;
                    }
                    this.checkFrameTick(array);
                    break;
                }
                case 0: {
                    this.frame = 0;
                    this.action = 0;
                    break;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateNew() {
        final QuaiTemplate mobTemplate = this.mobTemplate;
        if (mobTemplate != null) {
            this.w = mobTemplate.getWidth();
            this.h = this.mobTemplate.getHeight();
            if (this.status != 7) {
                final QuaiTemplate mobTemplate2 = this.mobTemplate;
                if (mobTemplate2 != null) {
                    if (CanvasNinja.gameTick % 3 == 0) {
                        ++mobTemplate2.index;
                    }
                    if (mobTemplate2.index > mobTemplate2.FRAME_MOB[this.action].length - 1) {
                        mobTemplate2.index = 0;
                    }
                }
            }
        }
        final Rectangle rectangle = this.rectangle;
        final int x = this.x;
        final int w = this.w;
        final int n = w / 2;
        final int y = this.y;
        final int h = this.h;
        rectangle.updatePos(x - n, y - h, w, h);
    }

    public void updateShadow() {
        final int x = this.x;
        this.xSd = x;
        final int y = this.y;
        this.ySd = y;
        this.wCount = 0;
        if (y < 0) {
            return;
        }
        if (MyTile.tileTypeAt(x, y, 2)) {
            return;
        }
        this.ySd = MyTile.findYTileTop(this.xSd, this.ySd);
    }

    public void updateShield() {
        for (final EffectShield effectShield : this.vShields) {
            effectShield.update();
            if (effectShield.isRemove) {
                this.vShields.removeElement(effectShield);
                break;
            }
        }
    }

    public void updateTask() {
        if (!this.vTaskMobs.isEmpty() && this.taskMobTemp == null) {
            this.taskMobTemp = this.vTaskMobs.remove(0);
        }
    }
}
