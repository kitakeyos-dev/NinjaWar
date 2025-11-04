// Class Version: 8
package ninjawar.myscr;

import ninjawar.message.Config;
import ninjawar.input.MyButtonSkill;
import ninjawar.skill.EffectFrameBuff;
import ninjawar.skill.EffectBuff;
import ninjawar.model.Popup;
import java.util.Iterator;
import ninjawar.screen.tab.TabFriend;
import ninjawar.model.PointChangeMap;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.template.SkillTemplate;
import ninjawar.mylib.AudioManager;
import ninjawar.skill.EffectFrame;
import ninjawar.message.SendMessage;
import ninjawar.model.EffectBuffStepByStep;
import ninjawar.skill.EffectFrameBuffToEnd;
import java.util.List;
import ninjawar.supporter.SupportTranslate;
import ninjawar.mylib.mGraphics;
import ninjawar.skill.EffectTarget;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mylib.mSystem;
import ninjawar.supporter.LoadDataManager;
import ninjawar.model.TextFlyInfo;
import ninjawar.skill.EffectShield;
import ninjawar.model.RangeDamageSkill;
import ninjawar.skill.MainEffect;
import ninjawar.model.Drain;
import java.util.Vector;
import ninjawar.model.Skill;
import ninjawar.model.Rectangle;
import ninjawar.model.mPoint;
import ninjawar.object.mNPC;
import ninjawar.model.mClass;
import ninjawar.model.MovePointNew;
import ninjawar.object.Mascot;
import ninjawar.model.ItemInMap;
import ninjawar.mylib.Image;
import ninjawar.model.FrameEffectMore;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.VectorCustom;
import ninjawar.model.MyCommand;
import ninjawar.model.mMovePointCustom;
import ninjawar.model.PlayerInfo;
import ninjawar.mylib.ObjMap;
import ninjawar.supporter.APartInfo;
import ninjawar.object.Base;

public class Player extends Base
{
    public static int[][] FRAME;
    public static int TIME_DELAY_CLICK_MOVE;
    static int cloneCount;
    static int cloneTransitionTicks;
    public static boolean isLoadingMap;
    public static boolean isLockKey;
    public static boolean isManualFocus;
    public static boolean ischangingMap;
    private static Player myChar;
    public static long timeDelayChangingMap;
    static boolean wasRunning;
    APartInfo aPartInfo;
    int actionLast;
    byte actionTemp;
    public boolean attack;
    int attackCycleCount;
    public boolean autoFall;
    public ObjMap bgItemFocus;
    public int cBonusSpeed;
    public float cExpPercent;
    public int cHP;
    public int cHPFull;
    public int cMP;
    public int cMPFull;
    public byte cPk;
    public byte cPkPoint;
    public int cSP;
    public int cSPFull;
    public byte cTypePk;
    public int cactFirst;
    public int cactFirstNew;
    public int cdirSend;
    public int cdirSendNew;
    public int cgender;
    public int chJumpMax;
    public Player charFocus;
    public int charID;
    public PlayerInfo charInfo;
    public int classId;
    public int classWeaponId;
    public int clevel;
    public boolean cmtoChar;
    int color;
    public int config;
    public int coolDownNemPhiTieu;
    long coolDownTele;
    public int countJump;
    public int cp1;
    public int cp2;
    public int cp3;
    public byte criticalFrom1000Tiemnang;
    public int cspeed;
    public int currentMap;
    public mMovePointCustom currentMovePoint;
    int currentState;
    public int cvx;
    public int cvy;
    public int cvyJump;
    public int cx;
    int cxJumpSv;
    public int cxSend;
    public int cxSendNew;
    public int cxShadownTo;
    int cxTarget;
    public int cy;
    public int cyDefault;
    public int cySend;
    public int cySendNew;
    public int cyStartFall;
    int cyTarget;
    public int cyTempDoubleMax;
    public int cyTempMax;
    public int cyspeed;
    public int cyspeedFall;
    public int damMP;
    public byte defFrom1000TiemNang;
    public int delayFall;
    public int disAtk;
    int disY;
    int dx;
    int dy;
    public MyCommand endMovePointCommand;
    public VectorCustom focus;
    public mFont fontPaintMyName;
    public mFont fontPaintName;
    public mFont fontPaintNameFocus;
    public byte frame;
    public FrameImage frameDie;
    FrameEffectMore frameEffectWhenJump;
    int frameLast;
    int frameMax;
    public FrameImage frameRevive;
    public int frameTest;
    int gameTick;
    public int gem;
    public int gemLock;
    public int gold;
    public byte h_hp_bar;
    int h_real;
    int h_real_spec;
    public boolean holder;
    public int hpTemp;
    int idAtk;
    int idKhoi;
    public short[] idPart;
    public short[] idPartCache;
    int idStand;
    int iiii;
    public Image imgHPtem;
    public int indexAttackWeapon;
    int indexFlagLeader;
    int indexFrame;
    int indexFrameDie;
    int indexFramePauseDie;
    int indexFramePauseRevive;
    int indexFrameRevive;
    int indexFrameTemp;
    int indexJump;
    int indexMovePoint;
    int[][] indexPauseAttackWeaponCb;
    int[][] indexPauseJumpSkill;
    int[][] indexPauseStand;
    public int indexStartTiepDat;
    int indexUseSkill;
    public boolean isAttack;
    public boolean isAutoAtk;
    public boolean isBehideObjMap;
    public boolean isCanDoubleJump;
    public boolean isCanJump;
    boolean isCanJumpTo;
    boolean isCheckJumpSv;
    public boolean isClone;
    public boolean isCloneMe;
    public boolean isContinueSkill;
    public boolean isCrit;
    public boolean isDelayCharFallFromJump;
    public boolean isDie;
    public boolean isDontPaint;
    public boolean isDoubleJump;
    boolean isFirstPaintHp;
    public boolean isGetSharigan;
    public boolean isHaveChakra;
    public boolean isHideInTile;
    public boolean isInvisibleByTile;
    public boolean isInvisibleNew;
    public boolean isJump;
    boolean isJumpSkill;
    boolean isJumpSv;
    public boolean isLastFrameFall;
    public boolean isLeader;
    public boolean isLockMove;
    public boolean isMob;
    public boolean isMoveAtk;
    public boolean isMoveTo;
    boolean isNhayNamePhiTieu;
    public boolean isOffline;
    public boolean isPaintName;
    public boolean isRemove;
    public boolean isRun;
    boolean isSetPos;
    public boolean isShadownSharingan;
    public boolean isShowHp;
    boolean isSpecFall;
    boolean isStart;
    public boolean isStartDie;
    public boolean isStartEffectDie;
    public boolean isStartEffectRevive;
    public boolean isTeleport;
    public boolean isUpdateHp;
    public boolean isUsePlane;
    public boolean isWaitMonkey;
    public boolean isWaitingDoubleJump;
    public boolean isWaitingDoubleJump2;
    public ItemInMap itemFocus;
    public int killCharId;
    public int lastMap;
    private long lastMobFocusLostTime;
    long lastTimeAction;
    public int len;
    public Mascot mascotTemp;
    public boolean me;
    public Quai mobFocus;
    MovePointNew movePointCur;
    public MovePointNew movePointNewTemp;
    public mClass nClass;
    String nameTest;
    public mNPC npcFocus;
    public int numTeam;
    int offset;
    public mPoint pLast;
    mPoint pRight;
    public mPoint pWallLast;
    public int per;
    public int per_tem;
    public int percentHp;
    public int percentMp;
    public int percentSp;
    public Rectangle rectangle;
    public int sJumpMax;
    public Skill skillCalling;
    long standStartTime;
    public byte statusMe;
    public byte statusMeBefore;
    public int subActionSend;
    public byte subCheckDir;
    public byte subStatusMe;
    public float tJump;
    public int testCharId;
    public int tick;
    int tickSpeedFrame;
    int tickSpeedFrameAtk;
    long timeBlue;
    long timeClickMoveTo;
    long timeDelayAutoAttack;
    public long timeDelayCharFallFromJump;
    public long timeDelayContinueSkill;
    int timeDelayHideHP;
    public long timeDelayNemPhiTieu;
    int timeFocusToMob;
    long timeLockMove;
    public long timeStartClone;
    long timeStartLostHp;
    long timeStartShowHP;
    public long timeStartUnShadown;
    int tpos;
    public byte type;
    int typeLeftRightTarget;
    public Vector<Drain> vDrains;
    public VectorCustom vEff;
    Vector<MainEffect> vEffectAfters;
    Vector<MainEffect> vEffectBefores;
    public Vector<MainEffect> vEffectSkillWaiting;
    public Vector<Mascot> vMascots;
    VectorCustom vMobGetDmg;
    public Vector<mMovePointCustom> vMovePoints;
    public Vector<mMovePointCustom> vMovePoints2;
    VectorCustom vPlayerGetDmg;
    public Vector<RangeDamageSkill> vRangeBuffSkills;
    public Vector<RangeDamageSkill> vRangeDmgSkills;
    public Vector<Player> vShadows;
    public Vector<EffectShield> vShields;
    public VectorCustom vSkill;
    public VectorCustom vSkillFight;
    public Vector<mMovePointCustom> vcurrentMovePoints;
    public Vector<MovePointNew> vecMovePointNews;
    public Vector<TextFlyInfo> vecTextFlyInfos;
    int w_countDown;
    int w_countDown_spec;
    public int w_hp_bar;
    int w_real;
    int w_real_spec;
    int w_result;
    int w_result_spec;
    public int xSd;
    int xTo;
    int yMoreHardCode;
    public int ySd;
    int yTo;

    static {
        Player.FRAME = new int[][] { { 0, 1, 2, 1 }, { 3, 4, 5, 6, 7, 8 }, { 3, 4, 5, 6, 7, 8 }, { 15, 16, 17, 18, 19, 20, 21, 22, 23, 15, 16, 17, 18, 19, 15, 27, 28, 29 }, { 15, 24, 25, 26, 27, 28, 29 }, { 15, 20, 21, 22, 23 }, { 15, 24, 25, 30, 27, 28, 31 }, { 32 }, { 33, 34, 35 }, { 36, 44 }, { 37, 37, 37 }, { 39 }, { 40, 41, 42, 43 }, { 9, 10, 11, 12, 13, 14 }, { 20, 21, 22, 23 }, { 32, 36 }, { 20, 21, 22, 23 }, { 27, 28, 29 }, { 32 }, { 0, 1, 2, 1 }, { 21, 22, 23 }, { 32, 40, 41, 42, 43 }, { 37, 38 }, { 45 }, { 46 }, { 15, 16, 17, 18, 19, 16, 17, 18, 19, 16, 17, 18, 19, 16, 17, 18, 19, 16, 17, 18, 19, 16, 17, 18, 19 } };
        Player.cloneCount = 0;
        Player.wasRunning = false;
        Player.cloneTransitionTicks = 0;
        Player.isManualFocus = false;
        Player.TIME_DELAY_CLICK_MOVE = 0;
    }

    public Player() {
        this.isLastFrameFall = false;
        this.indexStartTiepDat = 1;
        this.isUpdateHp = true;
        this.vecTextFlyInfos = new Vector<TextFlyInfo>();
        this.vMascots = new Vector<Mascot>();
        this.vShadows = new Vector<Player>();
        this.isShadownSharingan = false;
        this.isGetSharigan = false;
        this.vShields = new Vector<EffectShield>();
        this.vRangeDmgSkills = new Vector<RangeDamageSkill>();
        this.vRangeBuffSkills = new Vector<RangeDamageSkill>();
        this.isClone = false;
        this.isRemove = false;
        this.timeStartClone = -1L;
        this.pWallLast = new mPoint(-1999, -1999);
        this.classId = 0;
        this.classWeaponId = -1;
        this.isContinueSkill = true;
        this.isCanDoubleJump = true;
        this.countJump = 0;
        this.charInfo = new PlayerInfo();
        this.isDelayCharFallFromJump = false;
        this.vEffectSkillWaiting = new Vector<MainEffect>();
        this.isInvisibleNew = false;
        this.isInvisibleByTile = false;
        this.isHaveChakra = false;
        this.cx = 24;
        this.cy = 24;
        this.cspeed = 4;
        this.cyspeed = 4;
        this.cyspeedFall = 4;
        this.isOffline = false;
        this.attack = true;
        this.isWaitingDoubleJump = false;
        this.vSkill = new VectorCustom("vSkill");
        this.vSkillFight = new VectorCustom("vSkillFight");
        this.vEff = new VectorCustom("vEff char");
        this.isPaintName = true;
        this.focus = new VectorCustom("focus");
        this.testCharId = -9999;
        this.killCharId = -9999;
        this.vDrains = new Vector<Drain>();
        this.indexUseSkill = -1;
        this.cactFirstNew = 0;
        this.cdirSendNew = 1;
        this.cdirSend = 1;
        this.cactFirst = 0;
        this.movePointNewTemp = new MovePointNew();
        this.vMovePoints = new Vector<mMovePointCustom>();
        this.vMovePoints2 = new Vector<mMovePointCustom>();
        this.isBehideObjMap = false;
        this.vcurrentMovePoints = new Vector<mMovePointCustom>();
        this.isCanJump = false;
        this.isMoveTo = false;
        this.subCheckDir = 0;
        this.tickSpeedFrame = 2;
        this.tickSpeedFrameAtk = 2;
        this.sJumpMax = MyTile.size * 2;
        this.isNhayNamePhiTieu = false;
        this.coolDownNemPhiTieu = 700;
        this.isStart = false;
        this.isSpecFall = false;
        this.indexPauseAttackWeaponCb = new int[][] { { 0, 8 }, { 9, 17 } };
        this.frameLast = -1;
        this.actionTemp = -1;
        this.isJumpSv = false;
        this.isCheckJumpSv = false;
        this.indexPauseJumpSkill = new int[][] { { 0, 8 }, { 9, 17 } };
        this.isJumpSkill = false;
        this.indexPauseStand = new int[][] { { 0, 3 }, { 4, 7 } };
        this.isCanJumpTo = true;
        this.yMoreHardCode = 12;
        this.lastMap = 0;
        this.currentMap = 0;
        this.idKhoi = -1;
        this.statusMe = 0;
        this.statusMeBefore = 0;
        this.subStatusMe = -1;
        this.vEffectBefores = new Vector<MainEffect>();
        this.vEffectAfters = new Vector<MainEffect>();
        this.fontPaintName = mFont.tahoma_7b_white_border_black;
        this.fontPaintNameFocus = mFont.tahoma_7b_red_border_black;
        this.fontPaintMyName = mFont.tahoma_7b_white_border_black;
        this.frameTest = 0;
        this.isRun = false;
        this.idAtk = 0;
        this.idStand = 0;
        this.currentState = 0;
        this.standStartTime = 0L;
        this.attackCycleCount = 0;
        this.nameTest = "";
        this.indexFlagLeader = 0;
        this.timeBlue = 0L;
        this.disY = 12;
        this.isShowHp = false;
        this.timeDelayHideHP = 3000;
        this.color = 8421504;
        this.len = 24;
        this.w_hp_bar = 24;
        this.per = 100;
        this.per_tem = 100;
        this.h_hp_bar = 4;
        this.offset = 0;
        this.isFirstPaintHp = true;
        this.iiii = 0;
        this.timeFocusToMob = 0;
        this.defFrom1000TiemNang = 1;
        this.criticalFrom1000Tiemnang = 1;
        this.isWaitMonkey = false;
        this.isSetPos = false;
        this.tpos = 0;
        this.isMoveAtk = false;
        this.disAtk = super.cw;
        this.isAutoAtk = false;
        this.coolDownTele = 0L;
        this.lastMobFocusLostTime = -1L;
        this.rectangle = new Rectangle();
        this.vecMovePointNews = new Vector<MovePointNew>();
        this.indexMovePoint = 0;
        this.type = 0;
        this.frameDie = LoadDataManager.frameCharDead;
        this.indexFramePauseDie = 13;
        this.frameRevive = LoadDataManager.frameCharRevive;
        this.indexFramePauseRevive = 3;
    }

    public static int abs(int n) {
        if (n <= 0) {
            n = -n;
        }
        return n;
    }

    private void checkDoFall() {
        if (!this.isInTop(this.cx, this.cy)) {
            if (this.isMe()) {
                this.config = 7;
                if (!MyTile.tileTypeAt(this.cx, this.cy, 2) && this.getAction() == 0 && this.isUsingChakra()) {
                    this.doAction((byte)9);
                }
                this.delayFall = 0;
                this.cp2 = 0;
            }
            else {
                this.config = 7;
                this.doAction((byte)9);
                this.delayFall = 0;
                this.cp2 = 0;
            }
        }
    }

    private void checkPerformEndMovePointAction() {
        if (this.endMovePointCommand != null) {
            final MyCommand endMovePointCommand = this.endMovePointCommand;
            this.endMovePointCommand = null;
            endMovePointCommand.perform();
        }
    }

    private void doAttackWeapon() {
        this.isStart = true;
        this.indexFrame = 0;
        this.isAttack = true;
        final int random = Res.random(0, this.indexPauseAttackWeaponCb.length - 1);
        this.indexAttackWeapon = random;
        this.indexFrame = this.indexPauseAttackWeaponCb[random][0];
    }

    private void doDoubleJump() {
        this.isDoubleJump = false;
        this.cyTempMax = this.cyTempDoubleMax;
        final StringBuilder sb = new StringBuilder();
        sb.append("cyTempMax:");
        sb.append(this.cyTempMax);
        Res.outz(4, sb.toString());
        this.isJump = true;
        this.setAction((byte)7);
        this.subStatusMe = 12;
    }

    private void doNemPhiTieu() {
        this.isNhayNamePhiTieu = true;
        this.isStart = true;
        this.indexFrame = 0;
        this.timeDelayNemPhiTieu = mSystem.currentTimeMillis() + this.coolDownNemPhiTieu;
    }

    private void doUpdateAtk() {
        if (CanvasNinja.gameTick % this.tickSpeedFrameAtk == 0) {
            if (!this.isMe()) {
                final StringBuilder sb = new StringBuilder();
                sb.append("indexFrame:");
                sb.append(this.indexFrame);
                Res.outz(sb.toString());
            }
            if (this.vecTextFlyInfos.size() > 1) {
                final TextFlyInfo textFlyInfo = this.vecTextFlyInfos.remove(0);
                MapScr.startFlyText(textFlyInfo.flyString, textFlyInfo.x, textFlyInfo.y, textFlyInfo.dx, textFlyInfo.dy, textFlyInfo.color);
            }
            ++this.indexFrame;
            final int frameLast = this.frameLast;
            final byte frame = this.frame;
            if (frameLast != frame && frame == 27) {
                this.frameLast = frame;
                if (this.vecTextFlyInfos.size() == 1) {
                    final TextFlyInfo textFlyInfo2 = this.vecTextFlyInfos.remove(0);
                    MapScr.startFlyText(textFlyInfo2.flyString, textFlyInfo2.x, textFlyInfo2.y, textFlyInfo2.dx, textFlyInfo2.dy, textFlyInfo2.color);
                }
                final Quai mobFocus = this.mobFocus;
                if (mobFocus != null) {
                    mobFocus.startEffectHurt(this);
                    final Quai mobFocus2 = this.mobFocus;
                    final byte direct = super.direct;
                    mobFocus2.directTem = direct;
                    final EffectTarget effectTarget = new EffectTarget(mobFocus2, (byte)0, 0, direct);
                    effectTarget.loadEffectFromTool(new short[] { 2 });
                    effectTarget.isRemoveWhenDone = true;
                    final Quai mobFocus3 = this.mobFocus;
                    mobFocus3.dirTemp = super.direct;
                    mobFocus3.vEffectSkillWaiting.add(effectTarget);
                }
                final Player charFocus = this.charFocus;
                if (charFocus != null) {
                    charFocus.doAction((byte)10);
                }
            }
            final int indexFrame = this.indexFrame;
            if (indexFrame == 1 || indexFrame == Player.FRAME[this.statusMe].length / 2) {
                int config;
                if (Res.random(0, 1) == 0) {
                    config = 100;
                }
                else {
                    config = 101;
                }
                this.config = config;
            }
            if (this.indexFrame > (this.frameMax = Player.FRAME[this.statusMe].length - 1)) {
                this.indexFrame = 0;
                this.isStart = false;
                this.resetAction();
            }
            this.frame = (byte)Player.FRAME[this.statusMe][this.indexFrame];
        }
    }

    private void doUpdateTickFrameSpeed() {
        final int cspeed = this.cspeed;
        if (cspeed < 6) {
            this.tickSpeedFrame = 5;
        }
        else if (cspeed < 9) {
            this.tickSpeedFrame = 4;
        }
        else if (cspeed <= 11) {
            this.tickSpeedFrame = 3;
        }
        else if (cspeed > 11) {
            this.tickSpeedFrame = 2;
        }
        if (cspeed < 6) {
            this.tickSpeedFrameAtk = 4;
        }
        else if (cspeed < 9) {
            this.tickSpeedFrameAtk = 3;
        }
        else if (cspeed <= 11) {
            this.tickSpeedFrameAtk = 2;
        }
        else if (cspeed > 11) {
            this.tickSpeedFrameAtk = 1;
        }
    }

    private mPoint findPointNextTileTopCanJump(int n, final mPoint mPoint) {
        int i = MyTile.leftPointAtPixel(n, this.cy).y;
        if (super.cdir == -1) {
            while (i >= this.cy - this.chJumpMax * 2) {
                for (int j = n; j >= mPoint.x; j -= MyTile.size) {
                    if (MyTile.tileTypeAt(j, i, 2)) {
                        final mPoint leftPointAtPixel = MyTile.leftPointAtPixel(j, i);
                        n = Res.distance(leftPointAtPixel.x, this.cy, mPoint.x, mPoint.y);
                        final StringBuilder sb = new StringBuilder();
                        sb.append("dissss từ điểm nhảy đến điểm rơi:");
                        sb.append(n);
                        Res.outz(2, sb.toString());
                        if (n <= this.sJumpMax) {
                            leftPointAtPixel.isDoJump = true;
                        }
                        else {
                            leftPointAtPixel.isDoDoubleJump = true;
                        }
                        return leftPointAtPixel;
                    }
                }
                i -= MyTile.size;
            }
        }
        else {
            while (i >= this.cy - this.chJumpMax * 2) {
                for (int k = n; k <= mPoint.x; k += MyTile.size) {
                    if (MyTile.tileTypeAt(k, i, 2)) {
                        final mPoint rightPointAtPixel = MyTile.rightPointAtPixel(k, i);
                        n = Res.distance(rightPointAtPixel.x, this.cy, mPoint.x, mPoint.y);
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("dissss từ điểm nhảy đến điểm rơi:");
                        sb2.append(n);
                        Res.outz(2, sb2.toString());
                        if (n <= this.sJumpMax) {
                            rightPointAtPixel.isDoJump = true;
                        }
                        else {
                            rightPointAtPixel.isDoDoubleJump = true;
                        }
                        return rightPointAtPixel;
                    }
                }
                i -= MyTile.size;
            }
        }
        return null;
    }

    private mPoint findPointNextTileTopCanMove(final int n, final int n2, final int n3, int i) {
        if (super.cdir == -1) {
            int j;
            mPoint rightPointAtPixel;
            for (i = n2; i >= n2 - this.chJumpMax * 2; i -= MyTile.size) {
                for (j = n; j >= n3; j -= MyTile.size) {
                    if (MyTile.tileTypeAt(j, i, 2)) {
                        rightPointAtPixel = MyTile.rightPointAtPixel(j, i);
                        if (Res.abs(rightPointAtPixel.x - n) <= this.sJumpMax) {
                            rightPointAtPixel.isDoJump = true;
                        }
                        else {
                            rightPointAtPixel.isDoDoubleJump = true;
                        }
                        return rightPointAtPixel;
                    }
                }
            }
        }
        else {
            int k;
            mPoint leftPointAtPixel;
            for (i = n2; i >= n2 - this.chJumpMax * 2; i -= MyTile.size) {
                for (k = n; k <= n3; k += MyTile.size) {
                    if (MyTile.tileTypeAt(k, i, 2)) {
                        leftPointAtPixel = MyTile.leftPointAtPixel(k, i);
                        if (Res.abs(leftPointAtPixel.x - n) <= this.sJumpMax) {
                            leftPointAtPixel.isDoJump = true;
                        }
                        else {
                            leftPointAtPixel.isDoDoubleJump = true;
                        }
                        return leftPointAtPixel;
                    }
                }
            }
        }
        return null;
    }

    private boolean isPaintCharWhenRevive() {
        return this.indexFrameRevive >= this.indexFramePauseRevive;
    }

    private boolean isScreenUpdateCharFall() {
        return CanvasNinja.currentScreen instanceof MapScr && CanvasNinja.currentTab == null;
    }

    public static Player myCharz() {
        if (Player.myChar == null) {
            final Player player = Player.myChar = new Player();
            player.me = true;
            player.cmtoChar = true;
        }
        return Player.myChar;
    }

    private void paintEffectDie(final mGraphics mGraphics) {
        if (this.isStartEffectDie) {
            final FrameImage frameDie = this.frameDie;
            frameDie.drawFrame(this.indexFrameDie, (float)(this.cx - (int)frameDie.frameWidth / 2), (float)(this.cy - (int)frameDie.frameHeight), mGraphics);
        }
    }

    private void paintEffectRevive(final mGraphics mGraphics) {
        if (this.isStartEffectRevive && this.getAction() != 9) {
            final FrameImage frameRevive = this.frameRevive;
            frameRevive.drawFrame(this.indexFrameRevive, (float)(this.cx - (int)frameRevive.frameWidth / 2), (float)(this.cy - (int)frameRevive.frameHeight), mGraphics);
        }
    }

    private void paintHP(final mGraphics mGraphics, final int n, final int n2) {
        final Image imgHPtem = this.imgHPtem;
        if (imgHPtem != null) {
            this.w_real = mGraphics.getImageWidth(imgHPtem);
            this.h_real = mGraphics.getImageHeight(this.imgHPtem);
            final int w_real = this.w_real;
            final int per = this.per;
            final int n3 = w_real * per / 100;
            this.w_result = n3;
            this.w_countDown = n3;
            final int per_tem = this.per_tem;
            if (per_tem >= per) {
                if (this.isFirstPaintHp) {
                    this.isFirstPaintHp = false;
                    this.per_tem = per;
                    this.w_countDown = w_real * per / 100;
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
            mGraphics.drawImage(LoadDataManager.imgHP_tm_xam, (float)n, (float)n2, 20);
            mGraphics.drawRegion(LoadDataManager.imgHP_tm_trang, 0.0f, 0.0f, (float)this.w_countDown, (float)this.h_real, 0, (float)n, (float)n2, 20);
            mGraphics.drawRegion(this.imgHPtem, 0.0f, 0.0f, (float)this.w_result, (float)this.h_real, 0, (float)n, (float)n2, 20);
            mGraphics.drawImage(LoadDataManager.imgHeIcon[this.classId], (float)(this.w_real + n + 5), (float)(LoadDataManager.imgHP_tm_xam.getRHeight() / 2 + n2 - LoadDataManager.imgHeIcon[this.classId].getRHeight() / 2));
        }
    }

    private void paintMascot(final mGraphics mGraphics) {
        for (int i = this.vMascots.size() - 1; i >= 0; --i) {
            this.vMascots.elementAt(i).paint(mGraphics);
        }
    }

    private void paintTest(final mGraphics mGraphics) {
    }

    private mPoint pointFallWhenMoveDown(int distance, final int n, int distance2) {
        final mPoint mPoint = null;
        final mPoint mPoint2 = null;
        distance2 = distance;
        mPoint rightPointAtPixel;
        while (true) {
            rightPointAtPixel = mPoint;
            if (distance2 < distance - MyTile.size * (CanvasNinja.numTile / 2)) {
                break;
            }
            if (!MyTile.tileTypeAt(distance2, n, 2)) {
                rightPointAtPixel = MyTile.rightPointAtPixel(distance2, n);
                break;
            }
            distance2 -= MyTile.size;
        }
        distance2 = distance;
        mPoint rightPointAtPixel2;
        while (true) {
            rightPointAtPixel2 = mPoint2;
            if (distance2 > MyTile.size * (CanvasNinja.numTile / 2) + distance) {
                break;
            }
            if (!MyTile.tileTypeAt(distance2, n, 2)) {
                rightPointAtPixel2 = MyTile.rightPointAtPixel(distance2, n);
                break;
            }
            distance2 += MyTile.size;
        }
        if (rightPointAtPixel != null) {
            distance2 = Res.distance(rightPointAtPixel.x, distance);
        }
        else {
            distance2 = -1;
        }
        if (rightPointAtPixel2 != null) {
            distance = Res.distance(rightPointAtPixel2.x, distance);
        }
        else {
            distance = -1;
        }
        if (rightPointAtPixel != null && rightPointAtPixel2 != null) {
            for (int i = n; i <= MyTile.size * (CanvasNinja.numTile / 2) + n; i += MyTile.size) {
                if (MyTile.tileTypeAt(rightPointAtPixel.x, i, 2)) {
                    return new mPoint(rightPointAtPixel.x - MyTile.size, rightPointAtPixel.y);
                }
                if (MyTile.tileTypeAt(rightPointAtPixel2.x, i, 2)) {
                    return new mPoint(rightPointAtPixel2.x - MyTile.size, rightPointAtPixel2.y);
                }
            }
        }
        if ((distance2 < distance || (distance == -1 && distance2 != -1)) && rightPointAtPixel != null) {
            return new mPoint(rightPointAtPixel.x - MyTile.size, rightPointAtPixel.y);
        }
        if ((distance < distance2 || (distance != -1 && distance2 == -1)) && rightPointAtPixel2 != null) {
            return new mPoint(rightPointAtPixel2.x - MyTile.size, rightPointAtPixel2.y);
        }
        return null;
    }

    private mPoint pointFallWhenRun(int i, final int n, final int n2) {
        if (super.cdir == -1) {
            while (i >= n2) {
                if (!MyTile.tileTypeAt(i, n, 2)) {
                    return MyTile.rightPointAtPixel(i, n);
                }
                i -= MyTile.size;
            }
        }
        else {
            while (i <= n2) {
                if (!MyTile.tileTypeAt(i, n, 2)) {
                    return new mPoint(i, n);
                }
                i += MyTile.size;
            }
        }
        return null;
    }

    private boolean resetMinMaxDrainHP(final int n) {
        final int chp = this.cHP;
        final int chpFull = this.cHPFull;
        if (chp > chpFull) {
            this.cHP = chpFull;
            this.vDrains.removeElement(n);
            return true;
        }
        if (chp < 0) {
            this.cHP = 0;
            this.vDrains.removeElement(n);
            return true;
        }
        return false;
    }

    private boolean resetMinMaxDrainMP(final int n) {
        final int cmp = this.cMP;
        final int cmpFull = this.cMPFull;
        if (cmp > cmpFull) {
            this.cMP = cmpFull;
            this.vDrains.removeElement(n);
            return true;
        }
        if (cmp < 0) {
            this.cMP = 0;
            this.vDrains.removeElement(n);
            return true;
        }
        return false;
    }

    private boolean resetMinMaxDrainSP(final int n) {
        final int csp = this.cSP;
        final int cspFull = this.cSPFull;
        if (csp >= cspFull) {
            this.cSP = cspFull;
            this.vDrains.removeElement(n);
            return true;
        }
        if (csp <= 0) {
            this.cSP = 0;
            this.useCharka(this.isHaveChakra = false);
            Res.outz(1, "debug tắt charka");
            this.vDrains.removeElement(n);
            return true;
        }
        return false;
    }

    private void revive() {
        this.isStartDie = false;
        this.isStartEffectDie = false;
        this.isDie = false;
        this.indexFrameDie = 0;
        this.isLockMove = false;
        this.resetAction();
    }

    private void setCharFall() {
        this.isJump = false;
        this.cyStartFall = this.cy;
        this.cp1 = 0;
        this.cp2 = 0;
        this.doAction((byte)9);
        this.cvy = 0;
    }

    private void startEffectJump() {
        final FrameImage frameJump = LoadDataManager.frameJump;
        this.frameEffectWhenJump = new FrameEffectMore(frameJump, this.cx - (int)frameJump.frameWidth / 2, this.cy - (int)frameJump.frameHeight);
    }

    private void stop() {
        this.statusMe = 0;
        this.cp3 = 0;
        this.cvx = 0;
        this.cvy = 0;
        this.cp2 = 0;
        this.cp1 = 0;
        this.currentMovePoint = null;
    }

    private void updateCharMoveByServer() {
        if (this.vecMovePointNews.size() > 0 && this.movePointCur == null) {
            this.skillCalling = null;
            this.movePointCur = this.vecMovePointNews.remove(0);
        }
        final MovePointNew movePointCur = this.movePointCur;
        if (movePointCur != null) {
            final byte action = movePointCur.action;
            if (action == 4 || action == 37 || action == 6 || action == 20 || action == 10 || action == 14 || action == 17 || action == 35) {
                this.movePointCur = null;
                return;
            }
            if (Res.distance(this.cx, this.cy, movePointCur.xEnd, movePointCur.yEnd) > MyTile.size * 5) {
                final MovePointNew movePointCur2 = this.movePointCur;
                this.setPos(movePointCur2.xEnd, movePointCur2.yEnd);
                this.doAction((byte)0);
                this.movePointCur = null;
                return;
            }
            this.lastTimeAction = mSystem.currentTimeMillis() + 200L;
            final MovePointNew movePointCur3 = this.movePointCur;
            final byte action2 = movePointCur3.action;
            if (action2 != 1 && action2 != 2 && action2 != 0) {
                if (action2 != 7 && action2 != 9) {
                    this.isStart = true;
                    if (this.indexFrame > Player.FRAME[action2].length - 1) {
                        this.indexFrame = 0;
                    }
                    this.statusMe = action2;
                    this.actionLast = action2;
                    this.subStatusMe = movePointCur3.subAction;
                    this.cx = movePointCur3.xEnd;
                    this.cy = movePointCur3.yEnd;
                    this.movePointCur = null;
                }
                else {
                    final int abs = Res.abs(movePointCur3.xEnd - this.cx);
                    final int cspeed = this.cspeed;
                    int actionLast;
                    if (abs > cspeed) {
                        actionLast = 1;
                    }
                    else {
                        actionLast = 0;
                    }
                    if (actionLast == 1) {
                        this.isStart = true;
                        if (this.indexFrame > Player.FRAME[actionLast].length - 1) {
                            this.indexFrame = 0;
                        }
                        this.statusMe = (byte)actionLast;
                        this.actionLast = actionLast;
                        int n = 0;
                        final int xEnd = this.movePointCur.xEnd;
                        final int cx = this.cx;
                        if (xEnd > cx) {
                            n = 1;
                        }
                        else if (xEnd < cx) {
                            n = -1;
                        }
                        this.cx = cx + cspeed * n;
                        if (actionLast == 0 || actionLast == 11) {
                            this.cx = xEnd;
                        }
                        return;
                    }
                    this.isStart = true;
                    final int indexFrame = this.indexFrame;
                    final int[][] frame = Player.FRAME;
                    final MovePointNew movePointCur4 = this.movePointCur;
                    final byte action3 = movePointCur4.action;
                    if (indexFrame > frame[action3].length - 1) {
                        this.indexFrame = 0;
                    }
                    this.statusMe = action3;
                    this.actionLast = action3;
                    this.subStatusMe = movePointCur4.subAction;
                    this.setDirByCDir(movePointCur4.directTemp);
                    final MovePointNew movePointCur5 = this.movePointCur;
                    this.cx = movePointCur5.xEnd;
                    this.cy = movePointCur5.yEnd;
                    this.movePointCur = null;
                }
            }
            else {
                final int cspeed2 = this.cspeed;
                final int cyspeed = this.cyspeed;
                int dirByCDir = 0;
                int n2 = 0;
                final int n3 = 0;
                final int n4 = 0;
                final int xEnd2 = movePointCur3.xEnd;
                final int cx2 = this.cx;
                if (xEnd2 > cx2) {
                    dirByCDir = 1;
                }
                else if (xEnd2 < cx2) {
                    dirByCDir = -1;
                }
                final int yEnd = movePointCur3.yEnd;
                final int cy = this.cy;
                if (yEnd > cy) {
                    n2 = 1;
                }
                else if (yEnd < cy) {
                    n2 = -1;
                }
                final int n5 = 0;
                final boolean b = false;
                int actionLast2;
                int cspeed3;
                int dirByCDir2;
                if (this.isInWall()) {
                    if (this.isUsingChakra()) {
                        final MovePointNew movePointCur6 = this.movePointCur;
                        actionLast2 = n5;
                        cspeed3 = cyspeed;
                        dirByCDir2 = n3;
                        if (movePointCur6.xEnd == this.cx) {
                            actionLast2 = n5;
                            cspeed3 = cyspeed;
                            dirByCDir2 = n3;
                            if (Res.abs(movePointCur6.yEnd - this.cy) > cspeed2) {
                                actionLast2 = 2;
                                final byte typeInWall = this.typeInWall();
                                if (typeInWall == -1) {
                                    final int yEnd2 = this.movePointCur.yEnd;
                                    final int cy2 = this.cy;
                                    if (yEnd2 > cy2) {
                                        dirByCDir2 = -1;
                                    }
                                    else {
                                        dirByCDir2 = n4;
                                        if (yEnd2 < cy2) {
                                            dirByCDir2 = 1;
                                        }
                                    }
                                }
                                else {
                                    dirByCDir2 = n4;
                                    if (typeInWall == 1) {
                                        final int yEnd3 = this.movePointCur.yEnd;
                                        final int cy3 = this.cy;
                                        if (yEnd3 > cy3) {
                                            dirByCDir2 = 1;
                                        }
                                        else {
                                            dirByCDir2 = n4;
                                            if (yEnd3 < cy3) {
                                                dirByCDir2 = -1;
                                            }
                                        }
                                    }
                                }
                                cspeed3 = this.cspeed;
                            }
                        }
                    }
                    else {
                        actionLast2 = 11;
                        cspeed3 = cyspeed;
                        dirByCDir2 = n3;
                    }
                }
                else {
                    final MovePointNew movePointCur7 = this.movePointCur;
                    int n6 = b ? 1 : 0;
                    if (movePointCur7.yEnd == this.cy) {
                        n6 = (b ? 1 : 0);
                        if (Res.abs(movePointCur7.xEnd - this.cx) > cspeed2) {
                            n6 = 1;
                        }
                    }
                    final int yEnd4 = this.movePointCur.yEnd;
                    final int cy4 = this.cy;
                    if (yEnd4 > cy4) {
                        n6 = 9;
                    }
                    actionLast2 = n6;
                    cspeed3 = cyspeed;
                    dirByCDir2 = n3;
                    if (yEnd4 < cy4) {
                        actionLast2 = 7;
                        dirByCDir2 = n3;
                        cspeed3 = cyspeed;
                    }
                }
                this.isStart = true;
                if (this.indexFrame > Player.FRAME[actionLast2].length - 1) {
                    this.indexFrame = 0;
                }
                this.statusMe = (byte)actionLast2;
                this.actionLast = actionLast2;
                this.subStatusMe = this.movePointCur.subAction;
                if (actionLast2 == 2) {
                    if (dirByCDir2 != 0) {
                        this.setDirByCDir(dirByCDir2);
                    }
                }
                else if (dirByCDir != 0) {
                    this.setDirByCDir(dirByCDir);
                }
                this.cx += dirByCDir * cspeed2;
                this.cy += n2 * cspeed3;
                if (actionLast2 == 0 || actionLast2 == 11) {
                    final MovePointNew movePointCur8 = this.movePointCur;
                    this.cx = movePointCur8.xEnd;
                    this.cy = movePointCur8.yEnd;
                    this.movePointCur = null;
                }
            }
            if (this.vecMovePointNews.size() > 0) {
                this.movePointCur = null;
            }
        }
        final long lastTimeAction = this.lastTimeAction;
        if (lastTimeAction != 0L && lastTimeAction <= mSystem.currentTimeMillis()) {
            this.lastTimeAction = 0L;
        }
    }

    private void updateClickMovePointOther() {
        if (this.pLast != null && this.currentMovePoint == null && this.vcurrentMovePoints.size() == 0 && (Res.abs(this.cx - this.pLast.x) > MyTile.size || Res.abs(this.cy - this.pLast.y) > MyTile.size * 2)) {
            final mPoint pLast = this.pLast;
            this.moveToByServer(pLast.x, pLast.y, (byte)0, 0);
        }
    }

    private void updateEffectDie() {
        if (this.isStartEffectDie && CanvasNinja.gameTick % 3 == 0 && ++this.indexFrameDie > this.frameDie.nFrame - 1) {
            this.indexFrameDie = this.indexFramePauseDie;
        }
    }

    private void updateEffectRevive() {
        if (this.isStartEffectRevive && this.getAction() != 9 && CanvasNinja.gameTick % 3 == 0) {
            if (++this.indexFrameRevive > this.frameRevive.nFrame - 1) {
                this.indexFrameRevive = -1;
                this.isStartEffectRevive = false;
            }
            if (this.isPaintCharWhenRevive()) {
                this.revive();
            }
        }
    }

    private void updateFlyText() {
        for (int i = this.vDrains.size() - 1; i >= 0; --i) {
            final Drain drain = this.vDrains.elementAt(i);
            if (this.isMe() && drain.num != 0) {
                switch (drain.type) {
                    case 2: {
                        final int csp = this.cSP;
                        if (csp < 0 || csp > this.cSPFull) {
                            if (csp < 0) {
                                this.cSP = 0;
                            }
                            final int csp2 = this.cSP;
                            final int cspFull = this.cSPFull;
                            if (csp2 > cspFull) {
                                this.cSP = cspFull;
                            }
                            this.vDrains.removeElementAt(i);
                            break;
                        }
                        if (drain.lastTime == 0L || mSystem.currentTimeMillis() - drain.lastTime < drain.timeDelay) {
                            break;
                        }
                        drain.lastTime = mSystem.currentTimeMillis() + drain.timeDelay;
                        this.cSP += drain.num;
                        if (this.resetMinMaxDrainSP(i)) {
                            return;
                        }
                        StringBuilder sb;
                        if (drain.num > 0) {
                            sb = new StringBuilder();
                            sb.append("+");
                        }
                        else {
                            sb = new StringBuilder();
                            sb.append("");
                        }
                        sb.append(drain.num);
                        sb.append(" ");
                        sb.append(SupportTranslate.getTextLangue("SP"));
                        MapScr.startFlyText(sb.toString(), this.cx, this.cy - super.ch - 20, 0, -2, 1);
                        break;
                    }
                    case 1: {
                        final int cmp = this.cMP;
                        if (cmp < 0 || cmp > this.cMPFull) {
                            if (cmp < 0) {
                                this.cMP = 0;
                            }
                            final int cmp2 = this.cMP;
                            final int cmpFull = this.cMPFull;
                            if (cmp2 > cmpFull) {
                                this.cMP = cmpFull;
                            }
                            this.vDrains.removeElementAt(i);
                            break;
                        }
                        if (drain.lastTime == 0L || mSystem.currentTimeMillis() - drain.lastTime < drain.timeDelay) {
                            break;
                        }
                        drain.lastTime = mSystem.currentTimeMillis() + drain.timeDelay;
                        this.cMP += drain.num;
                        if (this.resetMinMaxDrainMP(i)) {
                            return;
                        }
                        StringBuilder sb2;
                        if (drain.num > 0) {
                            sb2 = new StringBuilder();
                            sb2.append("+");
                        }
                        else {
                            sb2 = new StringBuilder();
                            sb2.append("");
                        }
                        sb2.append(drain.num);
                        sb2.append(" ");
                        sb2.append(SupportTranslate.getTextLangue("KI"));
                        MapScr.startFlyText(sb2.toString(), this.cx, this.cy - super.ch - 20, 0, -2, 10);
                        break;
                    }
                    case 0: {
                        final int chp = this.cHP;
                        if (chp <= 0 || chp > this.cHPFull) {
                            if (chp < 0) {
                                this.cHP = 0;
                            }
                            final int chp2 = this.cHP;
                            final int chpFull = this.cHPFull;
                            if (chp2 > chpFull) {
                                this.cHP = chpFull;
                            }
                            this.vDrains.removeElementAt(i);
                            break;
                        }
                        if (drain.lastTime == 0L || mSystem.currentTimeMillis() - drain.lastTime < drain.timeDelay) {
                            break;
                        }
                        drain.lastTime = mSystem.currentTimeMillis() + drain.timeDelay;
                        this.cHP += drain.num;
                        if (this.resetMinMaxDrainHP(i)) {
                            return;
                        }
                        StringBuilder sb3;
                        if (drain.num > 0) {
                            sb3 = new StringBuilder();
                            sb3.append("+");
                        }
                        else {
                            sb3 = new StringBuilder();
                            sb3.append("");
                        }
                        sb3.append(drain.num);
                        sb3.append(" ");
                        sb3.append(SupportTranslate.getTextLangue("HP"));
                        MapScr.startFlyText(sb3.toString(), this.cx, this.cy - super.ch - 20, 0, -2, 9);
                        break;
                    }
                }
                MapScr.gI().updateHp_Lost(drain.type);
            }
        }
    }

    private void updateHideHP() {
        if (this.isShowHp && mSystem.currentTimeMillis() > this.timeStartShowHP && this.hpTemp == this.cHP) {
            this.isShowHp = false;
        }
    }

    private void updateLeaderFrame() {
        if (this.isLeader && CanvasNinja.gameTick % 3 == 0 && ++this.indexFlagLeader > LoadDataManager.frameFlagLeaderMap.nFrame - 1) {
            this.indexFlagLeader = 0;
        }
    }

    private void updateMascot() {
        for (int i = this.vMascots.size() - 1; i >= 0; --i) {
            this.vMascots.elementAt(i).update();
            if (this.vMascots.elementAt(i).isRemove) {
                MapScr.addEffectClearClone(this.vMascots.elementAt(i).x, this.vMascots.elementAt(i).y);
                this.vMascots.removeElementAt(i);
            }
        }
    }

    private void updateMaxHeight() {
        if (this.cy < 0) {
            this.cy = 0;
        }
    }

    private void updatePercentHpMpSp() {
        final int percentHp = (int)(this.cHP * 1.0f / this.cHPFull * 100.0f);
        this.percentHp = percentHp;
        final int percentMp = (int)(this.cMP * 1.0f / this.cMPFull * 100.0f);
        this.percentMp = percentMp;
        final int percentSp = (int)(this.cSP * 1.0f / this.cSPFull * 100.0f);
        this.percentSp = percentSp;
        if (percentHp < 0) {
            this.percentHp = 0;
        }
        if (percentMp < 0) {
            this.percentMp = 0;
        }
        if (percentSp < 0) {
            this.percentSp = 0;
        }
    }

    private void updateShadown() {
        final int cx = this.cx;
        this.xSd = cx;
        final int cy = this.cy;
        this.ySd = cy;
        if (cy <= 0) {
            return;
        }
        if (MyTile.tileTypeAt(cx, cy, 2)) {
            return;
        }
        this.ySd = MyTile.findYTileTop(this.xSd, this.ySd);
    }

    public void addBuffSpec(final List<EffectFrameBuffToEnd> list, final int n) {
        this.vShields.add(new EffectBuffStepByStep(list, n));
    }

    public void addMascot(final Mascot mascot) {
        mascot.index = this.vMascots.size();
        this.vMascots.addElement(mascot);
    }

    public void addMovePointNew(final MovePointNew movePointNew) {
        this.xTo = movePointNew.xEnd;
        this.yTo = movePointNew.yEnd;
        this.actionLast = this.statusMe;
        movePointNew.index = this.indexMovePoint;
        this.vecMovePointNews.add(movePointNew);
        ++this.indexMovePoint;
    }

    public void addShield(final int n, final int n2) {
        if (n == 4) {
            this.doStun(n2);
        }
        else {
            this.vShields.addElement(new EffectShield((short)n, n2));
        }
    }

    public void addSplashWeapon(final short n) {
        if (n == -32768) {
            return;
        }
        this.initEffectSplashWeapon(new short[] { n }, (byte)0);
    }

    public void addWeapon(final short n) {
        this.addWeapon(n, true);
    }

    public void addWeapon(final short n, final boolean b) {
        if (n == -32768) {
            return;
        }
        final short[] idPart = this.idPart;
        idPart[3] = n;
        this.setPart(idPart);
        if (b) {
            this.addSplashWeapon(n);
        }
    }

    public void autoAttack() {
        if (this.isDie) {
            this.skillCalling = null;
        }
        if (this.skillCalling == null || this.isInWall()) {
            return;
        }
        if (this.skillCalling.template.isUseAlways()) {
            this.vcurrentMovePoints.removeAllElements();
            this.currentMovePoint = null;
            if (MapScr.isAutoPlay && this.isMe()) {
                if (this.isContinueSkill && this.isMe()) {
                    final Skill skillCalling = this.skillCalling;
                    if (skillCalling != null) {
                        skillCalling.useSkill();
                    }
                }
                else if (!this.isMe()) {
                    this.skillCalling = null;
                }
            }
            else if (this.isClone) {
                final Skill skillCalling2 = this.skillCalling;
                if (skillCalling2 != null && this.isContinueSkill) {
                    skillCalling2.useSkill();
                }
            }
            else {
                this.skillCalling.useSkill();
            }
            return;
        }
        final Quai mobFocus = this.mobFocus;
        if (mobFocus == null || !mobFocus.isCanAttack(this.isMe())) {
            final Player charFocus = this.charFocus;
            if (charFocus == null || !charFocus.isCanAttack()) {
                this.skillCalling = null;
                return;
            }
        }
        final boolean canAttackPlayer = this.isCanAttackPlayer();
        final int n = -1;
        if (canAttackPlayer) {
            final Player charFocus2 = this.charFocus;
            if (charFocus2 != null && !charFocus2.equals(this) && this.skillCalling != null) {
                final int distance = Res.distance(this.cx, this.charFocus.cx);
                final int distance2 = Res.distance(this.cy, this.charFocus.cy);
                final Skill skillCalling3 = this.skillCalling;
                if ((distance <= skillCalling3.dx && distance2 <= skillCalling3.dy) || skillCalling3.template.isUseAlways()) {
                    if (this.isMe() && !SendMessage.gI().sendMove() && this.getAction() != 9 && this.getAction() != 7) {
                        return;
                    }
                    final Skill skillCalling4 = this.skillCalling;
                    if (distance2 > skillCalling4.dy && !skillCalling4.template.isUseAlways()) {
                        return;
                    }
                    this.vcurrentMovePoints.removeAllElements();
                    this.currentMovePoint = null;
                    int dirByCDir;
                    if (this.charFocus.cx < this.cx) {
                        dirByCDir = -1;
                    }
                    else {
                        dirByCDir = 1;
                    }
                    this.setDirByCDir(dirByCDir);
                    if (MapScr.isAutoPlay && this.isMe()) {
                        if (this.isContinueSkill && this.isMe()) {
                            final Skill skillCalling5 = this.skillCalling;
                            if (skillCalling5 != null && this.isContinueSkill) {
                                skillCalling5.useSkill();
                            }
                        }
                        else if (!this.isMe()) {
                            if (this.isClone) {
                                this.skillCalling.useSkill();
                            }
                            else {
                                this.skillCalling = null;
                            }
                        }
                    }
                    else {
                        this.skillCalling.useSkill();
                        this.skillCalling = null;
                    }
                }
                else {
                    if (mSystem.currentTimeMillis() < this.timeDelayAutoAttack || distance2 > this.skillCalling.dy) {
                        return;
                    }
                    this.timeDelayAutoAttack = mSystem.currentTimeMillis() + Player.TIME_DELAY_CLICK_MOVE;
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Gọi click di chuyển đến người x:");
                    sb.append(this.charFocus.cx);
                    sb.append("_y:");
                    sb.append(this.charFocus.cy);
                    sb.append("current movep:");
                    sb.append(this.currentMovePoint);
                    Res.outz(2, sb.toString());
                    int cdir;
                    if (this.charFocus.cx < this.cx) {
                        cdir = -1;
                    }
                    else {
                        cdir = 1;
                    }
                    this.setDirByCDir(super.cdir = cdir);
                    int dx;
                    if (super.cdir == -1) {
                        dx = this.skillCalling.dx;
                    }
                    else {
                        dx = -this.skillCalling.dx;
                    }
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("moreX:");
                    sb2.append(dx);
                    Res.outz(sb2.toString());
                    final Player charFocus3 = this.charFocus;
                    final int n2 = charFocus3.cx + dx;
                    final int cy = charFocus3.cy;
                    if (!this.isMe()) {
                        if (this.cy != cy) {
                            this.teleport(n2, cy);
                        }
                        else {
                            this.checkClickMoveTo(n2, cy, false);
                        }
                    }
                    else {
                        final Player charFocus4 = this.charFocus;
                        this.checkClickMoveTo(charFocus4.cx + dx, charFocus4.cy, false);
                    }
                    this.skillCalling.isSendMoveServer = false;
                }
            }
        }
        final Quai mobFocus2 = this.mobFocus;
        if (mobFocus2 != null && this.skillCalling != null) {
            final int n3 = Res.distance(this.cx, mobFocus2.x) + 10;
            final int distance3 = Res.distance(this.cy, this.mobFocus.y);
            final int cx = this.cx;
            final int cy2 = this.cy;
            final Quai mobFocus3 = this.mobFocus;
            final int distance4 = Res.distance(cx, cy2, mobFocus3.x, mobFocus3.y);
            final Skill skillCalling6 = this.skillCalling;
            final int dx2 = skillCalling6.dx;
            if ((n3 <= dx2 && distance3 <= skillCalling6.dy) || (this.cy != this.mobFocus.y && distance4 <= dx2) || skillCalling6.template.isUseAlways()) {
                if (this.isMe() && !SendMessage.gI().sendMove() && this.getAction() != 9 && this.getAction() != 7) {
                    return;
                }
                if (distance3 > Res.abs(this.skillCalling.dy + super.ch) && !this.skillCalling.template.isUseAlways()) {
                    Res.outz(2, "debuggggg ko đánh do thiếu Y");
                    return;
                }
                this.currentMovePoint = null;
                int dirByCDir2;
                if (this.mobFocus.x < this.cx) {
                    dirByCDir2 = n;
                }
                else {
                    dirByCDir2 = 1;
                }
                this.setDirByCDir(dirByCDir2);
                if (MapScr.isAutoPlay && this.isMe()) {
                    if (this.isContinueSkill && this.isMe()) {
                        final Skill skillCalling7 = this.skillCalling;
                        if (skillCalling7 != null && this.isContinueSkill) {
                            skillCalling7.useSkill();
                        }
                    }
                    else if (!this.isMe()) {
                        this.skillCalling = null;
                    }
                }
                else {
                    this.skillCalling.useSkill();
                    this.skillCalling = null;
                }
                this.mobFocus.updateHPServer();
            }
            else {
                if (mSystem.currentTimeMillis() < this.timeDelayAutoAttack || distance3 > this.skillCalling.dy) {
                    return;
                }
                this.timeDelayAutoAttack = mSystem.currentTimeMillis() + Player.TIME_DELAY_CLICK_MOVE;
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("Gọi click di chuyển đến quái x:");
                sb3.append(this.mobFocus.x);
                sb3.append("_y:");
                sb3.append(this.mobFocus.y);
                sb3.append("current movep:");
                sb3.append(this.currentMovePoint);
                sb3.append("_dx:");
                sb3.append(n3);
                sb3.append("_Skill dx:");
                sb3.append(this.skillCalling.dx);
                sb3.append("_dy:");
                sb3.append(distance3);
                Res.outz(2, sb3.toString());
                final Quai mobFocus4 = this.mobFocus;
                final int x = mobFocus4.x;
                int cdir2;
                if (x < this.cx) {
                    cdir2 = -1;
                }
                else {
                    cdir2 = 1;
                }
                super.cdir = cdir2;
                int n4;
                if (cdir2 == -1) {
                    n4 = this.skillCalling.dx - 10;
                }
                else {
                    n4 = -this.skillCalling.dx + 10;
                }
                if (mobFocus4.hp > 0 && mobFocus4.action != 3 && x != 0) {
                    final int y = mobFocus4.y;
                    if (y != 0) {
                        this.checkClickMoveTo(x + n4, y, false);
                    }
                }
            }
        }
    }

    public void cacheIdPart() {
        this.idPartCache = new short[this.idPart.length];
        int n = 0;
        while (true) {
            final short[] idPartCache = this.idPartCache;
            if (n >= idPartCache.length) {
                break;
            }
            idPartCache[n] = this.idPart[n];
            ++n;
        }
    }

    public void cancelAttack() {
    }

    public void cancelAutoAttack() {
        MapScr.isAutoPlay = false;
        this.skillCalling = null;
    }

    public void checkClickMoveTo(final int n, int dirByCDir) {
        if (mSystem.currentTimeMillis() < this.timeClickMoveTo && this.cy != dirByCDir) {
            return;
        }
        if (n == 0 && dirByCDir == 0) {
            return;
        }
        final byte statusMe = this.statusMe;
        if (statusMe == 7 || statusMe == 9) {
            return;
        }
        this.vecMovePointNews.removeAllElements();
        this.movePointCur = null;
        this.currentMovePoint = null;
        this.vcurrentMovePoints.removeAllElements();
        this.cancelAttack();
        final int pxw = MyTile.pxw;
        if (n < pxw && n > pxw - 32) {
            this.currentMovePoint = new mMovePointCustom(pxw, dirByCDir);
            return;
        }
        if (n < 32 && n > 0) {
            this.currentMovePoint = new mMovePointCustom(0, dirByCDir);
            return;
        }
        int n3;
        final int n2 = n3 = dirByCDir;
        if (n2 - this.cy > MyTile.size) {
            dirByCDir = this.cy;
            while (true) {
                n3 = n2;
                if (dirByCDir <= this.cy + MyTile.tmh * MyTile.size) {
                    break;
                }
                if (MyTile.tileTypeAt(this.cx, dirByCDir, 2)) {
                    n3 = dirByCDir;
                    break;
                }
                dirByCDir -= MyTile.size;
            }
        }
        this.delayFall = 0;
        final int cx = this.cx;
        final int cy = this.cy;
        dirByCDir = this.typeInWall();
        if (dirByCDir != 1 && dirByCDir != -1) {
            if (this.cx - n > 0) {
                dirByCDir = -1;
            }
            else {
                dirByCDir = 1;
            }
            this.setDirByCDir(dirByCDir);
            if (n3 < cy && Res.abs(n3 - cy) < this.chJumpMax) {
                final mPoint pointFallWhenRun = this.pointFallWhenRun(MyTile.leftPointAtPixel(cx, cy).x, cy, n);
                if (pointFallWhenRun != null) {
                    final mPoint pointNextTileTopCanMove = this.findPointNextTileTopCanMove(pointFallWhenRun.x, cy, n, n3);
                    if (pointNextTileTopCanMove != null) {
                        final mPoint pointNextTileTopCanJump = this.findPointNextTileTopCanJump(pointFallWhenRun.x + super.cdir * -1, pointNextTileTopCanMove);
                        if (pointNextTileTopCanJump != null) {
                            final mMovePointCustom mMovePointCustom = new mMovePointCustom(pointNextTileTopCanJump.x + super.cdir * -1, pointNextTileTopCanJump.y);
                            final mMovePointCustom mMovePointCustom2 = new mMovePointCustom(pointNextTileTopCanJump.x, pointNextTileTopCanJump.y);
                            mMovePointCustom2.isDoJump = pointNextTileTopCanJump.isDoJump;
                            mMovePointCustom2.isDoDoubleJump = pointNextTileTopCanJump.isDoDoubleJump;
                            this.vcurrentMovePoints.addElement(mMovePointCustom);
                            this.vcurrentMovePoints.addElement(mMovePointCustom2);
                            this.vcurrentMovePoints.addElement(new mMovePointCustom(n, n3));
                        }
                    }
                    else {
                        this.currentMovePoint = new mMovePointCustom(n, n3);
                    }
                }
                else {
                    this.currentMovePoint = new mMovePointCustom(n, n3);
                }
            }
            else if (n3 > MyTile.size + cy) {
                final mPoint pointFallWhenMoveDown = this.pointFallWhenMoveDown(MyTile.leftPointAtPixel(cx, cy).x, cy, n);
                if (pointFallWhenMoveDown != null) {
                    this.vcurrentMovePoints.addElement(new mMovePointCustom(pointFallWhenMoveDown.x, pointFallWhenMoveDown.y + MyTile.size));
                    final mMovePointCustom mMovePointCustom3 = new mMovePointCustom(n, n3);
                    mMovePointCustom3.isWaittingFall = true;
                    this.vcurrentMovePoints.addElement(mMovePointCustom3);
                }
                else {
                    this.currentMovePoint = new mMovePointCustom(n, n3);
                }
            }
            else {
                this.currentMovePoint = new mMovePointCustom(n, n3);
            }
        }
        else {
            this.currentMovePoint = new mMovePointCustom(n, n3);
        }
        if (this.cy != n3) {
            this.timeClickMoveTo = mSystem.currentTimeMillis() + 3000L;
        }
        this.endMovePointCommand = null;
    }

    public void checkClickMoveTo(final int n, final int n2, final boolean b) {
        if (b) {
            this.cancelAutoAttack();
        }
        this.checkClickMoveTo(n, n2);
    }

    public void clearMe() {
        Player.myChar = new Player();
    }

    public Player clone() {
        final Player player = new Player();
        player.charID = this.charID;
        player.isClone = true;
        player.cx = this.cx;
        player.cy = this.cy;
        player.cdir = super.cdir;
        player.cName = super.cName;
        player.setPart(this.idPart);
        player.doAction((byte)0);
        player.cHP = this.cHP;
        player.cMP = this.cMP;
        player.cSP = this.cSP;
        player.cHPFull = this.cHPFull;
        player.cMPFull = this.cMPFull;
        player.cSPFull = this.cSPFull;
        player.cspeed = this.cspeed;
        player.cyspeed = this.cyspeed;
        player.cyspeedFall = this.cyspeedFall;
        for (int i = 0; i < this.vSkillFight.size(); ++i) {
            final Skill skill = (Skill)this.vSkillFight.elementAt(i);
            if (skill != null) {
                player.vSkillFight.addElement(skill.clones());
            }
        }
        player.cspeed = this.cspeed;
        player.cyspeed = this.cyspeed;
        player.cyspeedFall = this.cyspeedFall;
        player.chJumpMax = this.chJumpMax;
        player.cvx = this.cvx;
        player.cvy = this.cvy;
        player.cvyJump = this.cvyJump;
        player.countJump = this.countJump;
        return player;
    }

    public void cloneMe() {
        this.charID = myCharz().charID * -1;
        super.cName = myCharz().cName;
        final short[] cloneArrShort = Res.cloneArrShort(myCharz().idPart);
        this.cx = myCharz().cx;
        this.cy = myCharz().cy;
        this.setPart(cloneArrShort);
        this.isCloneMe = true;
    }

    public void createButtonRevive() {
    }

    public void createEffectFrame(final short[] array, final byte b) {
        this.createEffectFrame(array, b, 0);
    }

    public void createEffectFrame(final short[] array, final byte b, final int n) {
        final EffectFrame effectFrame = new EffectFrame(b, n, false);
        effectFrame.loadEffectFromTool(array);
        this.vEffectSkillWaiting.add(effectFrame);
    }

    public void createEffectSplashWeapon(final short[] array, final byte b, final int n) {
        final EffectFrame effectFrame = new EffectFrame(b, n, false);
        effectFrame.loadEffectFromTool(array, (byte)3);
        this.vEffectSkillWaiting.add(effectFrame);
    }

    public void createMascotTemp(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final Skill skill) {
        this.mascotTemp = new Mascot(this, n, n2, n3, n4, n5, n6, n7, n8, n9, skill);
    }

    public void delaySkillNext(final int n) {
        this.isContinueSkill = false;
        this.timeDelayContinueSkill = mSystem.currentTimeMillis() + n;
    }

    public void doAction(final byte action) {
        if (this.getAction() == 21 && (action == 7 || action == 1 || this.isActionNotUseWhenLeoTuong(action))) {
            return;
        }
        if (action != 11 && action != 9 && action != 7) {
            this.resetBamTuong();
        }
        if (action == 10 && (this.getAction() == 7 || this.getAction() == 9)) {
            return;
        }
        switch (action) {
            default: {
                this.isStart = true;
                if (this.indexFrame > Player.FRAME[action].length - 1) {
                    this.indexFrame = 0;
                    break;
                }
                break;
            }
            case 21: {
                this.isStart = true;
                this.indexFrame = 0;
                this.cvx = Res.abs(this.cspeed) * super.cdir;
                this.cvy = -Res.abs(10);
                this.typeLeftRightTarget = super.cdir;
                final StringBuilder sb = new StringBuilder();
                sb.append("cvx:");
                sb.append(this.cvx);
                sb.append("_cvy:");
                sb.append(this.cvy);
                Res.outz(sb.toString());
                break;
            }
            case 19: {
                break;
            }
            case 12: {
                this.doDoubleJump();
                break;
            }
            case 11: {
                if (Res.abs(this.cx - this.pWallLast.x) > MyTile.size / 4) {
                    this.isStart = true;
                    this.indexFrame = 0;
                    this.pWallLast = new mPoint(this.cx, this.cy);
                    this.setAction((byte)11);
                    break;
                }
                this.isSpecFall = true;
                this.isStart = true;
                this.indexFrame = 0;
                this.setAction((byte)9);
                break;
            }
            case 10: {
                if (this.isTwoDamge()) {
                    this.indexFrameTemp = this.indexFrame;
                    this.actionTemp = this.getAction();
                }
                this.isStart = true;
                this.indexFrame = 0;
                break;
            }
            case 8: {
                this.doNemPhiTieu();
                break;
            }
            case 7: {
                this.doJump();
                break;
            }
            case 4:
            case 5:
            case 6:
            case 14:
            case 16:
            case 17:
            case 20: {
                this.isStart = true;
                this.indexFrame = 0;
                break;
            }
            case 3: {
                this.doAttackWeapon();
                break;
            }
        }
        if (action != 7 && action != 9 && action != 8) {
            this.resetJump();
        }
        if ((action != 7 && action != 19 && action != 11) || (action == 1 && this.isInTop(this.cx, this.cy))) {
            this.setAction(action);
        }
        this.statusMeBefore = this.statusMe;
    }

    public void doActionTemp() {
        final byte actionTemp = this.actionTemp;
        if (actionTemp != -1) {
            this.doAction(actionTemp);
            final int indexFrameTemp = this.indexFrameTemp;
            if (indexFrameTemp < Player.FRAME[this.actionTemp].length) {
                this.indexFrame = indexFrameTemp;
            }
            this.actionTemp = -1;
        }
    }

    public void doAttack() {
        if (this.getAction() != 8) {
            if (this.isUsingHoe()) {
                this.doAction((byte)25);
            }
            else if (this.isHaveWeapon()) {
                this.renmoveAllSplash();
                this.addWeapon(this.idPart[3]);
                this.doAction((byte)3);
            }
            else if (this.isHaveWeaponCache()) {
                this.renmoveAllSplash();
                this.addWeapon(this.idPartCache[3]);
                this.doAction((byte)3);
            }
            else {
                this.renmoveAllSplash();
                this.doAction((byte)4);
            }
        }
    }

    public void doFire(final Skill skill) {
        this.doFire(skill, false, this.cx, this.cy);
    }

    public void doFire(final Skill skillCalling, final boolean b, final int n, final int n2) {
        if (this.isMe() && this.itemFocus != null) {
            MapScr.cmdChatItem.perform();
            return;
        }
        if (skillCalling == null) {
            return;
        }
        if (this.isMe() && (myCharz().mobFocus == null || !myCharz().mobFocus.isCanAttack(true)) && (myCharz().charFocus == null || !myCharz().charFocus.isCanAttack())) {
            MapScr.searchFocusToAtk();
            MapScr.isAutoFocus = false;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("do fire skill:");
        sb.append(skillCalling.skillId);
        Res.outz(sb.toString());
        if (this.isMe()) {
            MapScr.isAutoPlay = false;
        }
        this.skillCalling = skillCalling;
        skillCalling.c = this;
    }

    public void doInjure(final int n, final int n2, final int n3, final boolean isCrit, final boolean isMob) {
        this.doAction((byte)10);
        this.isCrit = isCrit;
        this.isMob = isMob;
        if (this.isUpdateHp) {
            this.cHP -= n;
            this.cMP -= n2;
            this.cSP -= n3;
        }
        if (n > 0) {
            MapScr.startSplash(this.cx, this.cy - (super.ch >> 1), super.cdir);
        }
        MapScr.gI().isInjureHp = true;
        MapScr.gI().twHp = 0;
        MapScr.gI().isInjureMp = true;
        MapScr.gI().twMp = 0;
        if (this.cHP < 0) {
            this.cHP = 0;
        }
        if (this.cMP < 0) {
            this.cMP = 0;
        }
        if (isMob || (!isMob && this.damMP != -100)) {
            if (n <= 0) {
                if (this.me) {
                    MapScr.startFlyText(SupportTranslate.getTextLangue("miss"), this.cx, this.cy - super.ch, 0, -2, 7);
                }
                else {
                    MapScr.startFlyText(SupportTranslate.getTextLangue("miss"), this.cx, this.cy - super.ch, 0, -2, 4);
                }
            }
            else {
                final StringBuilder sb = new StringBuilder();
                sb.append("-");
                sb.append(n);
                final String string = sb.toString();
                final int cx = this.cx;
                final int cy = this.cy;
                final int ch = super.ch;
                int n4;
                if (!isCrit) {
                    n4 = 0;
                }
                else {
                    n4 = 3;
                }
                MapScr.startFlyText(string, cx, cy - ch, 0, -2, n4);
            }
        }
        if (this.isMe()) {
            if (n > 0) {
                MapScr.gI().updateHp_Lost(0);
            }
            if (n2 > 0) {
                MapScr.gI().updateHp_Lost(1);
            }
            if (n3 > 0) {
                MapScr.gI().updateHp_Lost(2);
            }
        }
        this.updateHp_bar();
    }

    public void doJump() {
        final StringBuilder sb = new StringBuilder();
        sb.append("countJump:");
        sb.append(this.countJump);
        Res.outz(1, sb.toString());
        final int countJump = this.countJump;
        if (countJump != 1) {
            if (this.getAction() != 7 && this.getAction() != 9) {
                this.cvy = -this.cyspeed;
                this.isJump = true;
                this.cyTempMax = this.cy - this.chJumpMax;
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("DO JUMPS!!!:");
                sb2.append(this.cyTempMax);
                Res.outz(4, sb2.toString());
                this.countJump = 1;
                SendMessage.gI().requestJump();
                this.setAction((byte)7);
                AudioManager.playSound(AudioManager.JUMP, 1.0f, this.getPan());
                this.startEffectJump();
            }
        }
        else if (this.isCanDoubleJump && countJump == 1) {
            this.cvy = -this.cyspeed;
            this.isJump = true;
            this.subStatusMe = 12;
            this.isDoubleJump = true;
            this.cyTempDoubleMax = this.cy - this.chJumpMax;
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("DO DOUBLE JUMP!!!:");
            sb3.append(this.cyTempDoubleMax);
            Res.outz(3, sb3.toString());
            this.countJump = 2;
            SendMessage.gI().requestJump();
            AudioManager.playSound(AudioManager.DOUBLE_JUMP, 1.0f, this.getPan());
        }
    }

    public void doNemPhiTieu(final int dirByCDir, final IMapObject mapObject, final Skill skill) {
        if (this.getAction() != 7 && this.getAction() != 9) {
            this.doAction((byte)14);
        }
        else {
            this.doAction((byte)8);
        }
        this.setDirByCDir(dirByCDir);
        MapScr.addEffectMoreSkill(this, skill, mapObject, 20, 0, 4);
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

    public void doUpdateAtkVuKhi() {
        if (this.isAttack && this.gameTick % 2 == 0) {
            final int indexFrame = this.indexFrame;
            final int[][] indexPauseAttackWeaponCb = this.indexPauseAttackWeaponCb;
            if (indexFrame == indexPauseAttackWeaponCb[0][0] || indexFrame == indexPauseAttackWeaponCb[1][0]) {
                this.config = 99;
            }
            if (this.vecTextFlyInfos.size() > 1) {
                final TextFlyInfo textFlyInfo = this.vecTextFlyInfos.remove(0);
                MapScr.startFlyText(textFlyInfo.flyString, textFlyInfo.x, textFlyInfo.y, textFlyInfo.dx, textFlyInfo.dy, textFlyInfo.color);
            }
            if (!this.isMe()) {
                final StringBuilder sb = new StringBuilder();
                sb.append("Index ATK:");
                sb.append(this.indexFrame);
                Res.outz(sb.toString());
            }
            this.frameMax = this.indexPauseAttackWeaponCb[this.indexAttackWeapon][1];
            ++this.indexFrame;
            final int frameLast = this.frameLast;
            final byte frame = this.frame;
            if (frameLast != frame && (frame == 21 || frame == 27)) {
                this.frameLast = frame;
                if (this.vecTextFlyInfos.size() == 1) {
                    final TextFlyInfo textFlyInfo2 = this.vecTextFlyInfos.remove(0);
                    MapScr.startFlyText(textFlyInfo2.flyString, textFlyInfo2.x, textFlyInfo2.y, textFlyInfo2.dx, textFlyInfo2.dy, textFlyInfo2.color);
                }
                final Quai mobFocus = this.mobFocus;
                if (mobFocus != null) {
                    mobFocus.startEffectHurt(this);
                    final Quai mobFocus2 = this.mobFocus;
                    final byte direct = super.direct;
                    mobFocus2.directTem = direct;
                    final EffectTarget effectTarget = new EffectTarget(mobFocus2, (byte)0, 0, direct);
                    effectTarget.loadEffectFromTool(new short[] { 2 });
                    effectTarget.isRemoveWhenDone = true;
                    final Quai mobFocus3 = this.mobFocus;
                    mobFocus3.dirTemp = super.direct;
                    mobFocus3.vEffectSkillWaiting.add(effectTarget);
                }
                final Player charFocus = this.charFocus;
                if (charFocus != null) {
                    charFocus.doAction((byte)10);
                }
            }
            if (this.indexFrame > this.frameMax) {
                if (!this.isMe()) {
                    Res.outz(1, "Trình diễn xong");
                }
                this.resetAction();
                this.isAttack = false;
                if (++this.indexAttackWeapon > this.indexPauseAttackWeaponCb.length - 1) {
                    this.indexAttackWeapon = 0;
                }
            }
            this.frame = (byte)Player.FRAME[this.statusMe][this.indexFrame];
        }
    }

    public void doUpdateCharJumpPhiTieu() {
        if (CanvasNinja.gameTick % 3 == 0 && this.isStart) {
            ++this.indexFrame;
            this.frameMax = Player.FRAME[this.statusMe].length - 1;
            boolean b = false;
            final StringBuilder sb = new StringBuilder();
            sb.append("debug indexFrame:");
            sb.append(this.indexFrame);
            Res.outz(1, sb.toString());
            final int indexFrame = this.indexFrame;
            final int frameMax = this.frameMax;
            if (indexFrame > frameMax) {
                this.indexFrame = frameMax;
                this.isStart = false;
                b = true;
                this.setCharFall();
            }
            if (!b) {
                this.frame = (byte)Player.FRAME[this.statusMe][this.indexFrame];
            }
        }
    }

    public void doUpdateFrameDie() {
        if (CanvasNinja.gameTick % this.tickSpeedFrame == 0 && this.isStart) {
            if (++this.indexFrame > (this.frameMax = Player.FRAME[this.statusMe].length - 1)) {
                this.indexFrame = 0;
                this.isStart = false;
                if (this.isMe()) {
                    this.isStartEffectDie = true;
                    CanvasNinja.startTaskDialog(SupportTranslate.getTextLangue("revive"), "Bạn đã bị trọng thương, về làng dưỡng sức.", 1);
                    this.isDie = true;
                    this.indexFrameDie = 0;
                }
                if (this.actionTemp != -1) {
                    this.doActionTemp();
                }
                this.resetAction();
            }
            this.frame = (byte)Player.FRAME[this.statusMe][this.indexFrame];
        }
    }

    public void doUpdateFrameHurt() {
        if (CanvasNinja.gameTick % this.tickSpeedFrame == 0 && this.isStart) {
            if (++this.indexFrame > (this.frameMax = Player.FRAME[this.statusMe].length - 1)) {
                this.indexFrame = 0;
                this.isStart = false;
                if (this.actionTemp != -1) {
                    this.doActionTemp();
                }
                this.resetAction();
            }
            this.frame = (byte)Player.FRAME[this.statusMe][this.indexFrame];
        }
    }

    public void doUpdateFrameJump() {
        if (CanvasNinja.gameTick % this.tickSpeedFrame == 0) {
            final byte subStatusMe = this.subStatusMe;
            if (subStatusMe == -1) {
                final int indexFrame = this.indexFrame + 1;
                this.indexFrame = indexFrame;
                final int[] array = Player.FRAME[this.statusMe];
                if (indexFrame > (this.frameMax = array.length - 1)) {
                    this.indexFrame = 0;
                }
                this.frame = (byte)array[this.indexFrame];
            }
            else {
                final int indexFrame2 = this.indexFrame + 1;
                this.indexFrame = indexFrame2;
                final int[] array2 = Player.FRAME[subStatusMe];
                if (indexFrame2 > (this.frameMax = array2.length - 1)) {
                    this.indexFrame = 0;
                }
                this.frame = (byte)array2[this.indexFrame];
            }
        }
    }

    public void doUpdateFrameJustOne() {
        if (CanvasNinja.gameTick % this.tickSpeedFrame == 0 && this.isStart) {
            if (++this.indexFrame > (this.frameMax = Player.FRAME[this.statusMe].length - 1)) {
                this.indexFrame = 0;
                this.isStart = false;
                this.resetAction();
            }
            this.frame = (byte)Player.FRAME[this.statusMe][this.indexFrame];
        }
    }

    public void doUpdateFrameNormal() {
        if (CanvasNinja.gameTick % this.tickSpeedFrame == 0) {
            ++this.indexFrame;
            if (!MyTile.tileTypeAt(this.cx, this.cy, 2) && this.getAction() == 0 && this.isUsingChakra()) {
                final int[] array = Player.FRAME[11];
                if (this.indexFrame > (this.frameMax = array.length - 1)) {
                    this.indexFrame = 0;
                }
                this.frame = (byte)array[this.indexFrame];
                if (MyTile.tileTypeAt(this.cx - 0, this.cy - 1, 8) && !this.isInTop(this.cx, this.cy)) {
                    this.setDirByCDir(-1);
                    final StringBuilder sb = new StringBuilder();
                    sb.append("TOA DO X CAN VE: ");
                    sb.append(this.cx);
                    Res.outz(sb.toString());
                }
                else if (MyTile.tileTypeAt(this.cx + 0, this.cy - 1, 4) && !this.isInTop(this.cx, this.cy)) {
                    this.setDirByCDir(1);
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("TOA DO X CAN VE: ");
                    sb2.append(this.cx);
                    Res.outz(sb2.toString());
                }
            }
            else {
                final int[] array2 = Player.FRAME[this.statusMe];
                if (this.indexFrame > (this.frameMax = array2.length - 1)) {
                    this.indexFrame = 0;
                }
                this.frame = (byte)array2[this.indexFrame];
            }
        }
    }

    public void fakeCmdTruMauServer(final int n) {
        final Quai mobInMapById = MapScr.findMobInMapById((short)n);
        mobInMapById.hp -= 100;
        mobInMapById.updateHp_bar();
        mobInMapById.setShowHP();
        final StringBuilder sb = new StringBuilder();
        sb.append("trừ máu monnnsss:");
        sb.append(n);
        Res.outz(1, sb.toString());
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("-");
        sb2.append(100);
        MapScr.startFlyText(sb2.toString(), mobInMapById.x, mobInMapById.y - mobInMapById.h, 0, -2, 5);
        if (mobInMapById.hp <= 0) {
            mobInMapById.startDie();
        }
    }

    public Skill findSkillCanUse() {
        if (this.isClone && this.isContinueSkill) {
            final Skill skill = null;
            int n = 0;
            Skill skill2;
            while (true) {
                skill2 = skill;
                if (n >= this.vSkillFight.size()) {
                    break;
                }
                skill2 = (Skill)this.vSkillFight.elementAt(n);
                if (skill2.isCanUse()) {
                    break;
                }
                ++n;
            }
            return skill2;
        }
        return null;
    }

    public void focusManualTo(final Object o) {
        this.focusManualTo(o, true);
    }

    public void focusManualTo(final Object o, final boolean isAutoFocus) {
        if (this.isMe()) {
            MapScr.isAutoFocus = isAutoFocus;
        }
        this.unFocusAll();
        if (o instanceof Quai) {
            this.mobFocus = (Quai)o;
        }
        else if (o instanceof mNPC) {
            this.npcFocus = (mNPC)o;
        }
        else if (o instanceof Player) {
            final Player charFocus = (Player)o;
            if (!charFocus.isMe()) {
                this.charFocus = charFocus;
            }
        }
        else if (o instanceof ItemInMap) {
            this.itemFocus = (ItemInMap)o;
        }
        else if (o instanceof ObjMap) {
            final ObjMap bgItemFocus = (ObjMap)o;
            if (bgItemFocus.idImage == 2000) {
                this.bgItemFocus = bgItemFocus;
            }
        }
        if (this.isMe()) {
            Player.isManualFocus = true;
        }
    }

    public byte getAction() {
        return this.statusMe;
    }

    @Override
    public int getH() {
        final int ch = super.ch;
        if (ch != 80) {
            return ch;
        }
        final int h = this.aPartInfo.getH();
        super.ch = h;
        super.chh = h / 2;
        return h;
    }

    public int[] getMoneyInven() {
        return new int[] { this.gold, this.gem, this.gemLock };
    }

    public float getPan() {
        float n = 0.0f;
        final Quai mobFocus = this.mobFocus;
        final byte b = 1;
        if (mobFocus != null) {
            final int x = mobFocus.x;
            final int cx = this.cx;
            byte b2;
            if (x > cx) {
                b2 = 1;
            }
            else {
                b2 = -1;
            }
            byte b3 = b2;
            if (x == cx) {
                b3 = 0;
            }
            n = Res.getValuePan(CanvasNinja.w / 2, Res.abs(cx - x), b3);
        }
        final Player charFocus = this.charFocus;
        if (charFocus != null) {
            final int cx2 = charFocus.cx;
            final int cx3 = this.cx;
            byte b4;
            if (cx2 > cx3) {
                b4 = b;
            }
            else {
                b4 = -1;
            }
            byte b5 = b4;
            if (cx2 == cx3) {
                b5 = 0;
            }
            n = Res.getValuePan(CanvasNinja.w / 2, Res.abs(cx3 - cx2), b5);
        }
        return n;
    }

    public Skill getSkill(final SkillTemplate skillTemplate) {
        for (int i = 0; i < this.vSkill.size(); ++i) {
            if (((Skill)this.vSkill.elementAt(i)).template.id == skillTemplate.id) {
                return (Skill)this.vSkill.elementAt(i);
            }
        }
        return null;
    }

    public byte getSubAction() {
        return this.subStatusMe;
    }

    public byte getTypeSkill() {
        if (this.mobFocus != null) {
            return 0;
        }
        if (this.charFocus != null) {
            return 1;
        }
        return -1;
    }

    @Override
    public int getW() {
        final int cw = super.cw;
        if (cw != 32) {
            return cw;
        }
        final int w = this.aPartInfo.getW();
        super.cw = w;
        super.chw = w / 2;
        return w;
    }

    @Override
    public int getX() {
        return this.cx;
    }

    @Override
    public int getY() {
        return this.cy;
    }

    public void initEffectByFrame(final short[] array, final byte b) {
        this.createEffectFrame(array, b);
    }

    public void initEffectSplashWeapon(final short[] array, final byte b) {
        this.createEffectSplashWeapon(array, b, 0);
    }

    public boolean isActionNotUseWhenLeoTuong(final byte b) {
        return b == 4 || b == 3 || b == 37 || b == 16 || b == 6 || b == 20 || b == 8 || b == 12;
    }

    public boolean isAlive() {
        return !this.isDie && this.cHP > 0;
    }

    public boolean isAttacPlayerStatus() {
        final byte cTypePk = this.cTypePk;
        return cTypePk == 4 || cTypePk == 3;
    }

    public boolean isBlock() {
        return NinjaMidlet.timeSystems < this.timeLockMove || super.isBlockMove;
    }

    public boolean isBossChar() {
        return this.type == 2;
    }

    public boolean isCanAttack() {
        return this.isAlive();
    }

    public boolean isCanAttackPlayer() {
        final int mapID = MyTile.mapID;
        if (mapID != 0) {
            final boolean b = true;
            if (mapID != 1) {
                return b;
            }
        }
        return false;
    }

    public boolean isCanJumpWhenInWall() {
        final int n = 0;
        int n2 = 0;
        if (MyTile.tileTypeAt(this.cx - 1, this.cy, 8)) {
            for (byte b = 0; b < this.chJumpMax; ++b) {
                if (MyTile.tileTypeAt(this.cx, this.cy + n2, 2) || MyTile.tileTypeAt(this.cx - MyTile.size, this.cy + n2, 2)) {
                    return true;
                }
                n2 -= MyTile.size * b;
            }
        }
        else if (MyTile.tileTypeAt(this.cx, this.cy, 4)) {
            byte b2 = 0;
            int n3 = n;
            while (b2 < this.chJumpMax) {
                if (MyTile.tileTypeAt(this.cx, this.cy + n3, 2) || MyTile.tileTypeAt(this.cx + MyTile.size, this.cy + n3, 2)) {
                    return true;
                }
                n3 -= MyTile.size * b2;
                ++b2;
            }
        }
        return false;
    }

    public boolean isCanRun() {
        return this.getAction() != 9 || this.isInTop(this.cx, this.cy);
    }

    public boolean isCharFocus() {
        return myCharz().charFocus != null && myCharz().charFocus.equals(this) && !this.isMe();
    }

    public boolean isDie() {
        return this.isDie || this.isStartDie || this.isStartEffectDie;
    }

    public boolean isDoubleJump() {
        return this.subStatusMe == 12;
    }

    public boolean isHaveWeapon() {
        final short[] idPart = this.idPart;
        return idPart.length > 4 && idPart[3] != -32768;
    }

    public boolean isHaveWeaponCache() {
        final short[] idPartCache = this.idPartCache;
        return idPartCache != null && idPartCache.length > 4 && idPartCache[3] != -32768;
    }

    public boolean isInRange(final Rectangle rectangle) {
        return this.rectangle.isInRectangle(rectangle);
    }

    public boolean isInTop(final int n, final int n2) {
        return MyTile.tileTypeAt(n, n2, 2) || (this.isUsingChakra() && MyTile.tileTypeAt(n, n2, 4096));
    }

    public boolean isInWall() {
        return !MyTile.tileTypeAt(this.cx, this.cy, 2) && (MyTile.tileTypeAt(this.cx, this.cy, 8) || MyTile.tileTypeAt(this.cx, this.cy, 4));
    }

    public boolean isInWaypoint() {
        if (!this.isTeleport && !this.isUsePlane) {
            for (int size = MyTile.vGo.size(), i = 0; i < size; i = (byte)(i + 1)) {
                final PointChangeMap pointChangeMap = (PointChangeMap)MyTile.vGo.elementAt(i);
                final int cx = this.cx;
                if (cx >= pointChangeMap.minX && cx <= pointChangeMap.maxX) {
                    final int cy = this.cy;
                    if (cy >= pointChangeMap.minY && cy <= pointChangeMap.maxY) {
                        if (!pointChangeMap.isEnter) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isInvisible() {
        return false;
    }

    public boolean isMe() {
        return myCharz().charID == this.charID && !this.isClone;
    }

    public boolean isMoveNew() {
        return this.isMe() || this.currentMovePoint != null;
    }

    public boolean isMyFriend(final String s) {
        final Iterator<Player> iterator = TabFriend.vecFriends.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().cName.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRun() {
        final byte statusMe = this.statusMe;
        boolean b = true;
        if (statusMe != 1) {
            b = (statusMe == 2 && b);
        }
        return b;
    }

    public boolean isTwoDamge() {
        return this.getAction() == 4 || this.getAction() == 3 || this.getAction() == 6 || this.getAction() == 16;
    }

    public boolean isUsingChakra() {
        return this.isHaveChakra;
    }

    boolean isUsingHoe() {
        return this.idPart[3] == 5;
    }

    public void levelUp(final int clevel) {
        if (this.clevel < clevel) {
            MapScr.startFlyText("LEVEL UP", this.cx, this.cy - super.ch - 7, 0, -10, 1);
            MapScr.gI().isEffLvUp = true;
            this.vShields.addElement(new EffectShield(5, 0, 20));
            if (this.isMe()) {
                AudioManager.levelUp(1.0f);
            }
        }
        this.clevel = clevel;
    }

    public void moveToByServer(final int xTo, final int yTo, final byte b, final int n) {
        if (Res.abs(this.cx - xTo) > 200 || Res.abs(this.cy - yTo) > 200) {
            this.setPos(xTo, yTo);
        }
        this.checkClickMoveTo(xTo, yTo, false);
        this.xTo = xTo;
        this.yTo = yTo;
    }

    public void paint(final mGraphics mGraphics) {
        if (this.isDontPaint) {
            return;
        }
        if (this.isHideInTile) {
            return;
        }
        if (myCharz().isGetSharigan && !this.isMe() && !this.isShadownSharingan) {
            return;
        }
        this.paintShadow(mGraphics);
        if (this.aPartInfo != null) {
            final int idKhoi = this.idKhoi;
            if (idKhoi != -1) {
                if (idKhoi >= 5) {
                    this.idKhoi = -1;
                }
                else if (!myCharz().isBehideObjMap) {
                    final FrameImage imgDapDat = LoadDataManager.imgDapDat;
                    imgDapDat.drawFrame(this.idKhoi, (float)(this.cx - imgDapDat.getRWidth() / 2), (float)(this.cy + this.dy - 4), mGraphics);
                }
                final int idKhoi2 = this.idKhoi;
                if (idKhoi2 >= 0 && CanvasNinja.gameTick % 2 == 0) {
                    this.idKhoi = idKhoi2 + 1;
                }
            }
            if (!this.isDie && (!this.isStartEffectDie || this.isPaintCharWhenRevive())) {
                final APartInfo aPartInfo = this.aPartInfo;
                final int cx = this.cx;
                final int dx = this.dx;
                final int cy = this.cy;
                final int dy = this.dy;
                int n;
                if (NinjaMidlet.isTestBoss) {
                    n = this.frameTest;
                }
                else {
                    n = this.frame;
                }
                final byte direct = super.direct;
                final byte subDirect = super.subDirect;
                int n2;
                if (this.isMe()) {
                    n2 = 50;
                }
                else {
                    n2 = 0;
                }
                aPartInfo.paint(mGraphics, cx + dx, cy + dy, n, direct, subDirect, n2, this.isInvisibleNew || this.isInvisibleByTile, false, myCharz().isGetSharigan);
            }
        }
        this.paintName(mGraphics);
        this.paintShield(mGraphics);
        this.paintDev(mGraphics);
        if (mSystem.isTest) {
            this.rectangle.paint(mGraphics);
            this.paintMovePointTest(mGraphics);
        }
        this.paintHp(mGraphics);
        this.paintMascot(mGraphics);
        this.paintEffectDie(mGraphics);
        this.paintEffectRevive(mGraphics);
        this.paintTest(mGraphics);
    }

    public void paintChar(final mGraphics mGraphics, final int n, final int n2, final boolean b) {
        this.paintChar(mGraphics, n, n2, b, false);
    }

    public void paintChar(final mGraphics mGraphics, final int n, final int n2, final boolean b, final boolean b2) {
        if (b) {
            mGraphics.drawImage(MyTile.shadow, (float)n, (float)(n2 - 2), 3);
        }
        final APartInfo aPartInfo = this.aPartInfo;
        if (aPartInfo != null) {
            final int dx = this.dx;
            final int dy = this.dy;
            final byte frame = this.frame;
            final byte direct = super.direct;
            final byte subDirect = super.subDirect;
            int n3;
            if (this.isMe()) {
                n3 = 50;
            }
            else {
                n3 = 0;
            }
            aPartInfo.paint(mGraphics, n + dx, n2 + dy, frame, direct, subDirect, n3, this.isInvisibleNew || this.isInvisibleByTile, false, false);
        }
        if (b2) {
            this.paintName(mGraphics);
        }
    }

    public void paintCharAtkLoop(final mGraphics mGraphics, final int n, final int n2, final boolean b) {
        final int random = Res.random(1000, 2000);
        if (this.currentState == 0) {
            if (this.standStartTime == 0L) {
                this.standStartTime = System.currentTimeMillis();
            }
            if (CanvasNinja.gameTick % 3 == 0 && ++this.idStand > Player.FRAME[0].length - 1) {
                this.idStand = 0;
            }
            if (System.currentTimeMillis() - this.standStartTime >= random) {
                this.currentState = 1;
                this.standStartTime = 0L;
                this.idStand = 0;
                this.attackCycleCount = 0;
            }
        }
        else if (CanvasNinja.gameTick % 3 == 0 && ++this.idAtk > Player.FRAME[3].length / 2 - 1) {
            this.idAtk = 0;
            if (++this.attackCycleCount >= 2) {
                this.currentState = 0;
                this.attackCycleCount = 0;
            }
        }
        byte b2;
        if (this.currentState == 0) {
            b2 = (byte)Player.FRAME[0][this.idStand];
        }
        else {
            b2 = (byte)Player.FRAME[3][this.idAtk];
        }
        for (int i = 0; i < this.vEffectBefores.size(); ++i) {
            final MainEffect mainEffect = this.vEffectBefores.get(i);
            final byte subDirect = super.subDirect;
            int n3;
            if (this.isMe()) {
                n3 = 50;
            }
            else {
                n3 = 0;
            }
            mainEffect.paint(mGraphics, n, n2, b2, (byte)1, subDirect, n3, this.isInvisibleNew || this.isInvisibleByTile, false);
        }
        final APartInfo aPartInfo = this.aPartInfo;
        if (aPartInfo != null) {
            final int dx = this.dx;
            final int dy = this.dy;
            final byte subDirect2 = super.subDirect;
            int n4;
            if (this.isMe()) {
                n4 = 50;
            }
            else {
                n4 = 0;
            }
            aPartInfo.paint(mGraphics, n + dx, n2 + dy, b2, (byte)1, subDirect2, n4, this.isInvisibleNew || this.isInvisibleByTile, false, false);
        }
        for (int j = 0; j < this.vEffectAfters.size(); ++j) {
            final MainEffect mainEffect2 = this.vEffectAfters.get(j);
            final byte subDirect3 = super.subDirect;
            int n5;
            if (this.isMe()) {
                n5 = 50;
            }
            else {
                n5 = 0;
            }
            mainEffect2.paint(mGraphics, n, n2, b2, (byte)1, subDirect3, n5, this.isInvisibleNew || this.isInvisibleByTile, false);
        }
    }

    public void paintDev(final mGraphics mGraphics) {
        if (mSystem.isTest) {
            mGraphics.setColor(16711680);
            mGraphics.fillRect(this.cx, this.cy, 1, 1);
        }
    }

    public void paintDoubleJump(final mGraphics mGraphics, final int n, final int n2) {
        if (CanvasNinja.gameTick % 5 == 0 && ++this.indexJump > Player.FRAME[12].length - 1) {
            this.indexJump = 0;
        }
        final byte b = (byte)Player.FRAME[12][this.indexJump];
        for (int i = 0; i < this.vEffectBefores.size(); ++i) {
            final MainEffect mainEffect = this.vEffectBefores.get(i);
            final byte subDirect = super.subDirect;
            int n3;
            if (this.isMe()) {
                n3 = 50;
            }
            else {
                n3 = 0;
            }
            mainEffect.paint(mGraphics, n, n2, b, (byte)1, subDirect, n3, this.isInvisibleNew || this.isInvisibleByTile, false);
        }
        final APartInfo aPartInfo = this.aPartInfo;
        if (aPartInfo != null) {
            final int dx = this.dx;
            final int dy = this.dy;
            final byte subDirect2 = super.subDirect;
            int n4;
            if (this.isMe()) {
                n4 = 50;
            }
            else {
                n4 = 0;
            }
            aPartInfo.paint(mGraphics, n + dx, n2 + dy, b, (byte)1, subDirect2, n4, this.isInvisibleNew || this.isInvisibleByTile, false, false);
        }
        for (int j = 0; j < this.vEffectAfters.size(); ++j) {
            final MainEffect mainEffect2 = this.vEffectAfters.get(j);
            final byte subDirect3 = super.subDirect;
            int n5;
            if (this.isMe()) {
                n5 = 50;
            }
            else {
                n5 = 0;
            }
            mainEffect2.paint(mGraphics, n, n2, b, (byte)1, subDirect3, n5, this.isInvisibleNew || this.isInvisibleByTile, false);
        }
    }

    public void paintEffectAfter(final mGraphics mGraphics) {
        for (int i = 0; i < this.vEffectAfters.size(); ++i) {
            final MainEffect mainEffect = this.vEffectAfters.get(i);
            final int cx = this.cx;
            final int cy = this.cy;
            final byte frame = this.frame;
            final byte direct = super.direct;
            final byte subDirect = super.subDirect;
            int n;
            if (this.isMe()) {
                n = 50;
            }
            else {
                n = 0;
            }
            mainEffect.paint(mGraphics, cx, cy, frame, direct, subDirect, n, this.isInvisibleNew || this.isInvisibleByTile, false);
        }
    }

    public void paintEffectBefore(final mGraphics mGraphics) {
        for (int i = 0; i < this.vEffectBefores.size(); ++i) {
            final MainEffect mainEffect = this.vEffectBefores.get(i);
            final int cx = this.cx;
            final int cy = this.cy;
            final byte frame = this.frame;
            final byte direct = super.direct;
            final byte subDirect = super.subDirect;
            int n;
            if (this.isMe()) {
                n = 50;
            }
            else {
                n = 0;
            }
            mainEffect.paint(mGraphics, cx, cy, frame, direct, subDirect, n, this.isInvisibleNew || this.isInvisibleByTile, false);
        }
    }

    public void paintHeadStatic(final mGraphics mGraphics, final int n, final int n2) {
        if (this.aPartInfo != null) {
            mGraphics.setColor(16777215);
            final int cw = super.cw;
            final int n3 = cw * 3 / 4;
            final int ch = super.ch;
            mGraphics.setClip(n - n3, n2 - ch, cw * 3 / 2, ch / 2 + 5);
            final APartInfo aPartInfo = this.aPartInfo;
            final byte subDirect = super.subDirect;
            int n4;
            if (this.isMe()) {
                n4 = 50;
            }
            else {
                n4 = 0;
            }
            aPartInfo.paint(mGraphics, n, n2, 0, (byte)0, subDirect, n4, this.isInvisibleNew || this.isInvisibleByTile, true, false);
            final int cw2 = super.cw;
            final int n5 = cw2 * 3 / 4;
            final int ch2 = super.ch;
            mGraphics.drawRect(n - n5 + 3, n2 - ch2 + 5, cw2 * 3 / 2 - 6, ch2 / 2 + 5 - 1 - 4);
        }
    }

    public void paintHp(final mGraphics mGraphics) {
        this.paintHp(mGraphics, this.cx - this.w_real / 2 - (LoadDataManager.imgHeIcon[this.classId].getRWidth() + 5) / 2, this.cy - super.ch - this.fontPaintName.getHeight() - 5 - LoadDataManager.imgHP_tm_xam.getRHeight());
    }

    public void paintHp(final mGraphics mGraphics, final int n, final int n2) {
        if ((this.isCharFocus() || this.isShowHp) && this.getAction() != 35 && this.cHP > 0) {
            this.paintHP(mGraphics, n, n2);
        }
    }

    public void paintHpSpec(final mGraphics mGraphics, final int n, final int n2) {
        if ((this.isCharFocus() || this.isShowHp) && this.getAction() != 35 && this.cHP > 0) {
            this.w_real_spec = mGraphics.getImageWidth(LoadDataManager.imgHP_tm_xanh_big_nen);
            this.h_real_spec = mGraphics.getImageHeight(LoadDataManager.imgHP_tm_xanh_big_nen);
            final int w_real_spec = this.w_real_spec;
            final int per = this.per;
            final int n3 = w_real_spec * per / 100;
            this.w_result_spec = n3;
            this.w_countDown_spec = n3;
            final int per_tem = this.per_tem;
            if (per_tem >= per) {
                if (this.isFirstPaintHp) {
                    this.isFirstPaintHp = false;
                    this.per_tem = per;
                    this.w_countDown = this.w_real * per / 100;
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
            }
            mGraphics.drawImage(LoadDataManager.imgHP_tm_xanh_big_nen, (float)(n - (w_real_spec >> 1)), (float)n2, 20);
            mGraphics.drawRegion(LoadDataManager.imgHP_tm_trang_big, 0.0f, 0.0f, (float)this.w_countDown_spec, (float)this.h_real_spec, 0, (float)(n - (this.w_real_spec >> 1)), (float)n2, 20);
            mGraphics.drawRegion(LoadDataManager.imgHP_tm_xanh_big, 0.0f, 0.0f, (float)this.w_result_spec, (float)this.h_real_spec, 0, (float)(n - (this.w_real_spec >> 1)), (float)n2, 20);
        }
    }

    public void paintMovePointTest(final mGraphics mGraphics) {
        for (int i = 0; i < this.vcurrentMovePoints.size(); ++i) {
            if (this.vcurrentMovePoints.get(i).isDoJump) {
                mGraphics.setColor(this.vcurrentMovePoints.get(i).colorJump);
            }
            else if (this.vcurrentMovePoints.get(i).isDoDoubleJump) {
                mGraphics.setColor(this.vcurrentMovePoints.get(i).colorDoubleJump);
            }
            else {
                mGraphics.setColor(this.vcurrentMovePoints.get(i).color);
            }
            mGraphics.fillRect(this.vcurrentMovePoints.get(i).xEnd, this.vcurrentMovePoints.get(i).yEnd, 10, 10);
        }
    }

    public void paintName(final mGraphics mGraphics) {
        if (this.isCharFocus()) {
            if (mSystem.isTest) {
                final StringBuilder sb = new StringBuilder();
                sb.append(super.cName);
                sb.append("_subDir:");
                sb.append(super.subDirect);
                sb.append("_action:");
                sb.append(this.getAction());
                final String string = sb.toString();
                this.nameTest = string;
                this.fontPaintNameFocus.drawString(mGraphics, string, this.cx, this.cy - super.ch - 10, 2);
            }
            else if (this.isLeader) {
                final int n = (int)LoadDataManager.frameFlagLeaderMap.frameWidth + 2 + this.fontPaintNameFocus.getWidth(super.cName);
                LoadDataManager.frameFlagLeaderMap.drawFrame(this.indexFlagLeader, (float)(this.cx - n / 2), this.cy - super.ch - 10 + (this.fontPaintNameFocus.getHeight() - LoadDataManager.frameFlagLeaderMap.frameHeight) / 2.0f, mGraphics);
                this.fontPaintNameFocus.drawString(mGraphics, super.cName, this.cx - n / 2 + LoadDataManager.frameFlagLeaderMap.frameWidth + 2.0f, this.cy - super.ch - 10, 0);
            }
            else {
                this.fontPaintNameFocus.drawString(mGraphics, super.cName, this.cx, this.cy - super.ch - 20, 2);
            }
            return;
        }
        if (this.isMe()) {
            if (mSystem.isTest) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(super.cName);
                sb2.append("_cx:");
                sb2.append(this.cx);
                sb2.append("_cy:");
                sb2.append(this.cy);
                sb2.append("_percentHp:");
                sb2.append(this.percentHp);
                sb2.append("_speed:");
                sb2.append(this.cspeed);
                final String string2 = sb2.toString();
                this.nameTest = string2;
                this.fontPaintMyName.drawString(mGraphics, string2, this.cx, this.cy - super.ch - 10, 2);
            }
            else if (this.isLeader) {
                final int n2 = (int)LoadDataManager.frameFlagLeaderMap.frameWidth + 2 + this.fontPaintMyName.getWidth(super.cName);
                LoadDataManager.frameFlagLeaderMap.drawFrame(this.indexFlagLeader, (float)(this.cx - n2 / 2), this.cy - super.ch - 10 + (this.fontPaintMyName.getHeight() - LoadDataManager.frameFlagLeaderMap.frameHeight) / 2.0f, mGraphics);
                this.fontPaintMyName.drawString(mGraphics, super.cName, this.cx - n2 / 2 + LoadDataManager.frameFlagLeaderMap.frameWidth + 2.0f, this.cy - super.ch - 10, 0);
            }
            else {
                this.fontPaintMyName.drawString(mGraphics, super.cName, this.cx, this.cy - super.ch - 20, 2);
            }
        }
        else if (this.isLeader) {
            final int n3 = (int)LoadDataManager.frameFlagLeaderMap.frameWidth + 2 + this.fontPaintName.getWidth(super.cName);
            LoadDataManager.frameFlagLeaderMap.drawFrame(this.indexFlagLeader, (float)(this.cx - n3 / 2), this.cy - super.ch - 10 + (this.fontPaintName.getHeight() - LoadDataManager.frameFlagLeaderMap.frameHeight) / 2.0f, mGraphics);
            this.fontPaintName.drawString(mGraphics, super.cName, this.cx - n3 / 2 + LoadDataManager.frameFlagLeaderMap.frameWidth + 2.0f, this.cy - super.ch - 10, 0);
        }
        else {
            this.fontPaintName.drawString(mGraphics, super.cName, this.cx, this.cy - super.ch - 20, 2);
        }
    }

    public void paintPopup(final mGraphics mGraphics) {
        final Popup popup = super.popup;
        if (popup != null) {
            popup.paint(mGraphics, this.cx, this.cy - super.ch - 10);
        }
    }

    public void paintShadow(final mGraphics mGraphics) {
        if (this.isInWall()) {
            return;
        }
        mGraphics.drawImage(MyTile.shadow, (float)this.xSd, (float)(this.ySd - 3), 3);
    }

    public void paintShadow(final mGraphics mGraphics, final int n, final int n2) {
        if (this.isInWall()) {
            return;
        }
        mGraphics.drawImage(MyTile.shadow, (float)n, (float)(n2 - 3), 3);
    }

    public void paintShadowSharigan(final mGraphics mGraphics) {
        final Iterator<Player> iterator = this.vShadows.iterator();
        while (iterator.hasNext()) {
            iterator.next().paint(mGraphics);
        }
    }

    public void paintShield(final mGraphics mGraphics) {
        final Iterator<EffectShield> iterator = this.vShields.iterator();
        while (iterator.hasNext()) {
            iterator.next().paint(mGraphics, this.cx, this.cy);
        }
        this.paintShieldTopCenter(mGraphics);
    }

    public void paintShieldTopCenter(final mGraphics mGraphics) {
        final Iterator<EffectShield> iterator = this.vShields.iterator();
        while (iterator.hasNext()) {
            iterator.next().paintTopCenter(mGraphics, this.cx, this.cy - super.ch);
        }
    }

    public void removeEffectChar(final int n, final int n2) {
        final int n3 = 0;
        int n4 = this.vEffectAfters.size() - 1;
        int n5;
        while (true) {
            n5 = n3;
            if (n4 < 0) {
                break;
            }
            final MainEffect mainEffect = this.vEffectAfters.elementAt(n4);
            if (mainEffect.id == n && mainEffect.type == n2) {
                this.vEffectAfters.remove(n4);
                n5 = 1;
                break;
            }
            --n4;
        }
        if (n5 == 0) {
            for (int i = this.vEffectBefores.size() - 1; i >= 0; --i) {
                final MainEffect mainEffect2 = this.vEffectBefores.elementAt(i);
                if (mainEffect2.id == n && mainEffect2.type == n2) {
                    this.vEffectBefores.remove(i);
                    break;
                }
            }
        }
    }

    public void removeEffectSkill(final int n, final byte b) {
        final int n2 = 0;
        int n3 = this.vEffectAfters.size() - 1;
        int n4;
        while (true) {
            n4 = n2;
            if (n3 < 0) {
                break;
            }
            final MainEffect mainEffect = this.vEffectAfters.elementAt(n3);
            if (mainEffect instanceof EffectFrame && ((EffectFrame)mainEffect).isMe(n, b)) {
                n4 = 1;
                this.vEffectAfters.remove(n3);
                break;
            }
            if (mainEffect instanceof EffectBuff && ((EffectBuff)mainEffect).isMe(n, b)) {
                n4 = 1;
                this.vEffectAfters.remove(n3);
                break;
            }
            --n3;
        }
        if (n4 == 0) {
            for (int i = this.vEffectBefores.size() - 1; i >= 0; --i) {
                final MainEffect mainEffect2 = this.vEffectBefores.elementAt(i);
                if (mainEffect2 instanceof EffectFrame && ((EffectFrame)mainEffect2).isMe(n, b)) {
                    this.vEffectAfters.remove(i);
                    break;
                }
                if (mainEffect2 instanceof EffectBuff && ((EffectBuff)mainEffect2).isMe(n, b)) {
                    this.vEffectAfters.remove(i);
                    break;
                }
            }
        }
    }

    public void removeSplashWeapon() {
        final short[] idPart = this.idPart;
        if (idPart.length - 1 < 3) {
            return;
        }
        this.removeEffectSkill(idPart[3], (byte)3);
    }

    public void removeWeapon() {
        if (!this.isHaveWeaponCache()) {
            this.cacheIdPart();
        }
        final short[] idPart = this.idPart;
        idPart[3] = -32768;
        this.setPart(idPart);
        this.removeSplashWeapon();
    }

    public void renmoveAllSplash() {
        for (int i = this.vEffectAfters.size() - 1; i >= 0; --i) {
            final MainEffect mainEffect = this.vEffectAfters.elementAt(i);
            if (mainEffect instanceof EffectFrame && ((EffectFrame)mainEffect).getType() == 3) {
                this.isRemove = true;
                this.vEffectAfters.remove(i);
            }
        }
        for (int j = this.vEffectBefores.size() - 1; j >= 0; --j) {
            final MainEffect mainEffect2 = this.vEffectBefores.elementAt(j);
            if (mainEffect2 instanceof EffectFrame && ((EffectFrame)mainEffect2).getType() == 3) {
                this.isRemove = true;
                this.vEffectAfters.remove(j);
            }
        }
    }

    public void resetAction() {
        this.indexFrame = 0;
        this.frameLast = -1;
        this.autoFall = false;
        this.doAction((byte)0);
    }

    public void resetBamTuong() {
        this.isSpecFall = false;
        this.pWallLast = new mPoint(-1999, -1999);
    }

    public void resetJump() {
        this.subStatusMe = -1;
        this.countJump = 0;
        this.isDoubleJump = false;
        this.isJump = false;
    }

    public void resetMyChar() {
        final Player player = Player.myChar = new Player();
        player.me = true;
        player.cmtoChar = true;
    }

    public void setAction(final byte b) {
        this.statusMe = b;
    }

    public void setActionRun() {
        this.doAction((byte)1);
    }

    public void setAttack(final VectorCustom vectorCustom, final VectorCustom vectorCustom2, final RangeDamageSkill rangeDamageSkill) {
        if (!rangeDamageSkill.isPaintSkill) {
            this.startEffecTarget(vectorCustom, vectorCustom2, rangeDamageSkill.skill);
            rangeDamageSkill.isPaintSkill = true;
        }
        if (this.isMe() && rangeDamageSkill.timeNextSendAtk <= mSystem.currentTimeMillis()) {
            Res.outz(1, "debuggggg 11111111111111");
            rangeDamageSkill.updateTimeNext();
            SendMessage.gI().sendPlayerAttack(vectorCustom, vectorCustom2, rangeDamageSkill.skill.template.id);
        }
    }

    public boolean setAutoFallCharka() {
        if (!this.isUsingChakra() && (!MyTile.tileTypeAt(this.cx, this.cy + MyTile.size - 1, 2) || !MyTile.tileTypeAt(this.cx, this.cy, 2))) {
            this.doAction((byte)9);
            return true;
        }
        return false;
    }

    public void setBuff(final VectorCustom vectorCustom, final Skill skill) {
        if (skill.template.isBuffToPlayer()) {
            for (int i = 0; i < vectorCustom.size(); ++i) {
                final Player player = (Player)vectorCustom.elementAt(i);
                if (!skill.useSkillBuffSpec(player)) {
                    if (skill.idEffect != -1) {
                        final EffectFrame effectFrame = new EffectFrame((byte)0, 0, false);
                        effectFrame.loadEffectFromTool(new short[] { skill.idEffect }, (byte)1);
                        effectFrame.isRemoveWhenDone = true;
                        effectFrame.isRemoveSpec = true;
                        this.removeWeapon();
                        effectFrame.isAddSplashWeapon = true;
                        this.vEffectSkillWaiting.add(effectFrame);
                    }
                    if (skill.idTarget != -1) {
                        final EffectBuff effectBuff = new EffectBuff(player, (byte)1, 0, skill.timeBuff, super.direct);
                        effectBuff.loadEffectFromTool(new short[] { skill.idTarget });
                        player.vEffectSkillWaiting.add(effectBuff);
                    }
                    if (skill.idStatusBuff != -1) {
                        final EffectBuff effectBuff2 = new EffectBuff(player, (byte)0, 0, skill.timeBuff, super.direct);
                        effectBuff2.effectFrameBuff = new EffectFrameBuff((byte)skill.idStatusBuff);
                        player.vEffectSkillWaiting.add(effectBuff2);
                    }
                    final short idBuffFrame = skill.idBuffFrame;
                    if (idBuffFrame != -1) {
                        player.addShield(idBuffFrame, skill.timeBuff);
                    }
                }
            }
        }
    }

    public void setCharFallFromJump() {
        if (this.isJump) {
            this.setCharFall();
        }
    }

    public void setCharJump(final int cvx) {
        this.isJump = true;
        this.cvy = -this.cyspeed;
        this.cvx = cvx;
        this.doAction((byte)7);
        this.cp1 = 0;
    }

    public void setDir(final byte b) {
        if (b == 2) {
            super.direct = 2;
        }
        if (b == 3) {
            super.direct = 0;
        }
        super.directTem = b;
    }

    public void setDirByCDir(int cdir) {
        super.cdir = cdir;
        final int n = 2;
        int n2;
        if (cdir == -1) {
            n2 = 2;
        }
        else {
            n2 = 0;
        }
        super.direct = (byte)n2;
        if (cdir == -1) {
            cdir = n;
        }
        else {
            cdir = 3;
        }
        super.directTem = (byte)cdir;
    }

    public void setDirrectByCDir(int n) {
        final int n2 = 2;
        int n3;
        if (n == -1) {
            n3 = 2;
        }
        else {
            n3 = 0;
        }
        super.direct = (byte)n3;
        if (n == -1) {
            n = n2;
        }
        else {
            n = 3;
        }
        super.directTem = (byte)n;
    }

    public void setPart(final short[] idPart) {
        if (NinjaMidlet.isTestBoss) {
            for (int i = 0; i < idPart.length; ++i) {
                if (i == 0) {
                    idPart[i] = 1000;
                }
                else {
                    idPart[i] = -32768;
                }
            }
        }
        if (idPart.length > 3 && idPart[3] != -32768) {
            this.addSplashWeapon(idPart[3]);
        }
        this.idPart = idPart;
        (this.aPartInfo = new APartInfo()).loadPartFromTool(idPart);
        this.initEffectByFrame(new short[] { 0 }, (byte)0);
        if (idPart.length < 4 || idPart[3] == -32768) {
            this.removeSplashWeapon();
        }
        this.getW();
        this.getH();
    }

    public void setPos(final int cx, final int cy) {
        this.cx = cx;
        this.cy = cy;
    }

    public void soundUpdate() {
        if (!this.isMe()) {
            if (this.isMe()) {
                return;
            }
            final int cx = this.cx;
            final int cmx = MapScr.cmx;
            if (cx < cmx || cx > cmx + CanvasNinja.w) {
                return;
            }
        }
        if (this.cHP > 0) {
            final int percentHp = this.percentHp;
            if (percentHp <= 20 && CanvasNinja.gameTick % 25 == 0) {
                AudioManager.HP_LOW(1.0f - percentHp * 1.0f / 20.0f + 1.0f);
            }
        }
        if (this.config == 99) {
            AudioManager.ATK_KIEM(this.getPan());
            this.config = -1;
        }
        if (this.config == 100) {
            AudioManager.PAUCH(this.getPan());
            this.config = -1;
        }
        if (this.config == 101) {
            AudioManager.KICK(this.getPan());
            this.config = -1;
        }
    }

    public void startDie(final int cp2, final int cp3) {
        this.skillCalling = null;
        this.currentMovePoint = null;
        this.createButtonRevive();
        this.vShields.removeAllElements();
        this.vEffectAfters.removeAllElements();
        this.vEffectBefores.removeAllElements();
        this.useCharka(false);
        this.isWaitMonkey = false;
        this.doAction((byte)10);
        this.isStartDie = true;
        if (this.isMe()) {
            Res.outz(1, ">>>>>>>>Chết và block moveeeeeeeeee<<<<<<<");
            this.isLockMove = true;
        }
        if (this.isDie) {
            return;
        }
        if (this.me) {
            this.isLockMove = true;
            for (int i = 0; i < MapScr.vCharInMap.size(); ++i) {
                ((Player)MapScr.vCharInMap.elementAt(i)).killCharId = -9999;
            }
        }
        this.cp2 = cp2;
        this.cp3 = cp3;
        this.cp1 = 0;
        this.cHP = 0;
        this.testCharId = -9999;
        this.killCharId = -9999;
        this.cTypePk = 0;
    }

    public void startEffecTarget(final VectorCustom vectorCustom, final VectorCustom vectorCustom2, final Skill skill) {
        if (skill.template.isChuongFrameNoTarget()) {
            final short idEffectFrame = skill.idEffectFrame;
            if (idEffectFrame != -1) {
                MapScr.addEffectFrame(idEffectFrame, this, skill, (byte)0, skill.timeBuff, skill.numFrameIdEffectFrame, skill.dxTemplate, skill.dyTemplate, skill.isRemoveWhenDone);
            }
        }
        if (skill.template.isAttackTarget()) {
            if (skill.idEffect != -1) {
                final EffectFrame effectFrame = new EffectFrame((byte)0, 0, false);
                effectFrame.loadEffectFromTool(new short[] { skill.idEffect }, (byte)1);
                effectFrame.isRemoveWhenDone = true;
                effectFrame.isRemoveSpec = true;
                this.removeWeapon();
                this.removeSplashWeapon();
                effectFrame.isAddSplashWeapon = true;
                this.vEffectSkillWaiting.add(effectFrame);
            }
            for (int i = 0; i < vectorCustom.size(); ++i) {
                final Quai quai = (Quai)vectorCustom.elementAt(i);
                if (skill.idTarget != -1) {
                    final EffectTarget effectTarget = new EffectTarget(quai, (byte)0, 0, super.direct);
                    effectTarget.loadEffectFromTool(new short[] { skill.idTarget });
                    effectTarget.isRemoveWhenDone = true;
                    quai.dirTemp = super.direct;
                    quai.vEffectSkillWaiting.add(effectTarget);
                }
                if (skill.idStatusBuff != -1) {
                    final EffectBuff effectBuff = new EffectBuff(quai, (byte)1, 0, skill.timeBuff, super.direct);
                    effectBuff.loadEffectFromTool(new short[] { skill.idStatusBuff });
                    quai.vEffectSkillWaiting.add(effectBuff);
                }
                final short idEffectMore = skill.idEffectMore;
                if (idEffectMore != -1) {
                    final EffectTarget effectTarget2 = new EffectTarget(quai, (byte)0, 0, super.direct);
                    effectTarget2.loadEffectFromTool(new short[] { idEffectMore });
                    effectTarget2.isRemoveWhenDone = true;
                    quai.vEffectSkillWaiting.add(effectTarget2);
                }
            }
            for (int j = 0; j < vectorCustom2.size(); ++j) {
                final Player player = (Player)vectorCustom2.elementAt(j);
                if (skill.idTarget != -1) {
                    final EffectTarget effectTarget3 = new EffectTarget(player, (byte)0, 0, super.direct);
                    effectTarget3.loadEffectFromTool(new short[] { skill.idTarget });
                    effectTarget3.isRemoveWhenDone = true;
                    player.vEffectSkillWaiting.add(effectTarget3);
                }
                if (skill.idStatusBuff != -1) {
                    final EffectBuff effectBuff2 = new EffectBuff(player, (byte)1, 0, skill.timeBuff, super.direct);
                    effectBuff2.loadEffectFromTool(new short[] { skill.idStatusBuff });
                    player.vEffectSkillWaiting.add(effectBuff2);
                }
                final short idEffectMore2 = skill.idEffectMore;
                if (idEffectMore2 != -1) {
                    if (skill.template.id == 48) {
                        MapScr.addEffect(new short[] { idEffectMore2 }, this, skill, player, skill.speedSkill, (byte)0, skill.timeBuff);
                    }
                    else {
                        final EffectTarget effectTarget4 = new EffectTarget(player, (byte)0, 0, super.direct);
                        effectTarget4.loadEffectFromTool(new short[] { idEffectMore2 });
                        effectTarget4.isRemoveWhenDone = true;
                        player.vEffectSkillWaiting.add(effectTarget4);
                    }
                }
            }
        }
        else if (skill.template.isChuong()) {
            if (skill.idTarget != -1 && skill.template.isChuongAOE()) {
                final short[] array = { skill.idTarget };
                final Quai mobFocus = myCharz().mobFocus;
                final Player charFocus = myCharz().charFocus;
                if (mobFocus != null) {
                    final EffectTarget effectTarget5 = new EffectTarget(mobFocus.x, mobFocus.y, (byte)0, 0, super.direct, super.subDirect);
                    effectTarget5.loadEffectFromTool(array);
                    effectTarget5.isRemoveWhenDone = true;
                    MapScr.vEffectSkillWaiting.add(effectTarget5);
                }
                if (charFocus != null) {
                    final EffectTarget effectTarget6 = new EffectTarget(charFocus.cx, charFocus.cy, (byte)0, 0, super.direct, super.subDirect);
                    effectTarget6.loadEffectFromTool(array);
                    effectTarget6.isRemoveWhenDone = true;
                    MapScr.vEffectSkillWaiting.add(effectTarget6);
                }
            }
            for (int k = 0; k < vectorCustom.size(); ++k) {
                final Quai quai2 = (Quai)vectorCustom.elementAt(k);
                final short idEffectMore3 = skill.idEffectMore;
                if (idEffectMore3 != -1) {
                    MapScr.addEffect(new short[] { idEffectMore3 }, this, skill, quai2, skill.speedSkill, (byte)0, skill.timeBuff);
                }
                if (skill.idStatusBuff != -1) {
                    final EffectBuff effectBuff3 = new EffectBuff(quai2, (byte)0, 0, skill.timeBuff, super.direct);
                    effectBuff3.effectFrameBuff = new EffectFrameBuff((byte)skill.idStatusBuff);
                    quai2.vEffectSkillWaiting.add(effectBuff3);
                }
                if (skill.idTarget != -1) {
                    if (!skill.template.isChuongAOE()) {
                        final short idTarget = skill.idTarget;
                        final EffectTarget effectTarget7 = new EffectTarget(quai2, (byte)0, 0, super.direct);
                        effectTarget7.loadEffectFromTool(new short[] { idTarget });
                        effectTarget7.isRemoveWhenDone = true;
                        quai2.vEffectSkillWaiting.add(effectTarget7);
                    }
                    final EffectTarget effectTarget8 = new EffectTarget(quai2, (byte)0, 0, super.direct);
                    effectTarget8.loadEffectFromTool(new short[] { 2 });
                    effectTarget8.isRemoveWhenDone = true;
                    quai2.vEffectSkillWaiting.add(effectTarget8);
                }
                final short idBuffFrame = skill.idBuffFrame;
                if (idBuffFrame != -1) {
                    quai2.addShield(idBuffFrame, skill.timeBuff);
                }
            }
            for (int l = 0; l < vectorCustom2.size(); ++l) {
                final Player player2 = (Player)vectorCustom2.elementAt(l);
                final short idEffectMore4 = skill.idEffectMore;
                if (idEffectMore4 != -1) {
                    MapScr.addEffect(new short[] { idEffectMore4 }, this, skill, player2, skill.speedSkill, (byte)0, skill.timeBuff);
                }
                if (skill.idStatusBuff != -1) {
                    final EffectBuff effectBuff4 = new EffectBuff(player2, (byte)0, 0, skill.timeBuff, super.direct);
                    effectBuff4.effectFrameBuff = new EffectFrameBuff((byte)skill.idStatusBuff);
                    player2.vEffectSkillWaiting.add(effectBuff4);
                }
                if (skill.idTarget != -1) {
                    if (!skill.template.isChuongAOE()) {
                        final short idTarget2 = skill.idTarget;
                        final EffectTarget effectTarget9 = new EffectTarget(player2, (byte)0, 0, super.direct);
                        effectTarget9.loadEffectFromTool(new short[] { idTarget2 });
                        effectTarget9.isRemoveWhenDone = true;
                        player2.vEffectSkillWaiting.add(effectTarget9);
                    }
                    final EffectTarget effectTarget10 = new EffectTarget(player2, (byte)0, 0, super.direct);
                    effectTarget10.loadEffectFromTool(new short[] { 2 });
                    effectTarget10.isRemoveWhenDone = true;
                    player2.vEffectSkillWaiting.add(effectTarget10);
                }
                final short idBuffFrame2 = skill.idBuffFrame;
                if (idBuffFrame2 != -1) {
                    player2.addShield(idBuffFrame2, skill.timeBuff);
                }
            }
        }
        if (skill.idEffect != -1) {
            final EffectFrame effectFrame2 = new EffectFrame((byte)0, 0, false);
            effectFrame2.loadEffectFromTool(new short[] { skill.idEffect }, (byte)1);
            effectFrame2.isRemoveWhenDone = true;
            if (skill.template.isAttackSkill()) {
                effectFrame2.isRemoveSpec = true;
            }
            if (skill.template.isChuong()) {
                this.removeWeapon();
                effectFrame2.isAddSplashWeapon = true;
            }
            this.vEffectSkillWaiting.add(effectFrame2);
        }
    }

    public void startFlyText(final byte b, final byte num, final short timeDelay) {
        final Drain drain = new Drain();
        drain.type = b;
        drain.num = num;
        drain.timeDelay = timeDelay;
        long currentTimeMillis;
        if (timeDelay != 0) {
            currentTimeMillis = mSystem.currentTimeMillis();
        }
        else {
            currentTimeMillis = 0L;
        }
        drain.lastTime = currentTimeMillis;
        this.vDrains.add(drain);
    }

    public void startPopup(final String s, final int n) {
        (super.popup = new Popup()).startPopup(s, n);
    }

    public void startRevive() {
        this.isDie = false;
        this.indexFrameDie = -1;
        this.isStartEffectDie = false;
        this.isStartEffectRevive = true;
        this.indexFrameRevive = 0;
        if (this.isMe()) {
            MapScr.gI().updateHPLostAll();
        }
    }

    public void stopFlyText(final byte b) {
        for (int i = this.vDrains.size() - 1; i >= 0; --i) {
            if (this.vDrains.elementAt(i).type == b) {
                this.vDrains.removeElementAt(i);
                break;
            }
        }
    }

    @Override
    public void stopMoving() {
    }

    public void teleport(final int cx, final int cy) {
        this.vecMovePointNews.removeAllElements();
        this.movePointCur = null;
        this.cx = cx;
        this.cy = cy;
    }

    public byte typeInWall() {
        if (this.getAction() == 9) {
            return 0;
        }
        if (MyTile.tileTypeAt(this.cx, this.cy, 2)) {
            final mPoint pointAtPixel = MyTile.pointAtPixel(this.cx, this.cy);
            if (this.cx <= pointAtPixel.x + MyTile.size - 1 && this.cy == pointAtPixel.y) {
                return 0;
            }
        }
        if (MyTile.tileTypeAt(this.cx, this.cy + super.chw, 8)) {
            if (this.isUsingChakra()) {
                return 1;
            }
        }
        else {
            if (!MyTile.tileTypeAt(this.cx, this.cy + super.chw, 4)) {
                return 0;
            }
            if (this.isUsingChakra()) {
                return -1;
            }
        }
        return 0;
    }

    public void unFocusAll() {
        this.bgItemFocus = null;
        this.mobFocus = null;
        this.npcFocus = null;
        this.charFocus = null;
        this.itemFocus = null;
    }

    public void update() {
        final int gameTick = this.gameTick + 1;
        this.gameTick = gameTick;
        if (gameTick > 10000) {
            this.gameTick = 0;
        }
        final Rectangle rectangle = this.rectangle;
        final int cx = this.cx;
        final int cw = super.cw;
        final int n = cw / 2;
        final int cy = this.cy;
        final int ch = super.ch;
        rectangle.updatePos(cx - n, cy - ch, cw, ch);
        this.updateMaxHeight();
        this.updateLeaderFrame();
        this.updateCharInLostHP();
        if ((this.isMe() && this.mobFocus == null && this.charFocus == null && this.npcFocus == null && this.bgItemFocus == null && this.itemFocus == null) || CanvasNinja.isClickKeyMove()) {
            MapScr.isAutoFocus = true;
        }
        if (this.isMe() && CanvasNinja.currentScreen instanceof MapScr) {
            SendMessage.gI().sendMove();
            SendMessage.gI().sendMoveNew();
        }
        this.updateEffectDie();
        this.updateEffectRevive();
        this.updateMascot();
        this.updatePercentHpMpSp();
        this.updateRangeSkill();
        this.updateBuffSkill();
        this.updateShield();
        this.updateHideHP();
        this.updateRemoveClone();
        this.doUpdateTickFrameSpeed();
        if (this.updateDelayFall()) {
            return;
        }
        this.updateFrameSpec();
        this.updateSubDir();
        this.updateAutoAtk();
        this.updateActionChar();
        this.updateOld();
        this.updateDoJumpDelay();
        this.updateEffectSkill();
        this.updatePopup();
        this.updateFlyText();
        this.updateCharInBridge();
        this.updateCharInThacNuoc();
        this.autoAttack();
        this.updateContinueSkill();
        final FrameEffectMore frameEffectWhenJump = this.frameEffectWhenJump;
        if (frameEffectWhenJump != null) {
            frameEffectWhenJump.update();
        }
        this.updateClone();
        this.updateCharMoveByServer();
    }

    public void updateActionChar() {
        if (NinjaMidlet.isTestBoss) {
            this.getAction();
            this.doUpdateFrameNormal();
            return;
        }
        final byte action = this.getAction();
        boolean b = false;
        if (action != 9) {
            this.isLastFrameFall = false;
        }
        switch (this.getAction()) {
            default: {
                this.doUpdateFrameJustOne();
                break;
            }
            case 35: {
                final int cp1 = this.cp1 + 1;
                this.cp1 = cp1;
                if (cp1 > 30) {
                    this.cp1 = 0;
                }
                if (this.cp1 % 15 < 5) {
                    this.config = 0;
                    break;
                }
                this.config = 1;
                break;
            }
            case 22: {
                this.doUpdateFrameDie();
                break;
            }
            case 21: {
                super.subDirect = -1;
                this.updateCharLeoTuong();
                this.doUpdateFrameNormal();
                break;
            }
            case 17: {
                this.doUpdateFrameNormal();
                break;
            }
            case 15: {
                this.updateCharJumpSkill();
                break;
            }
            case 13: {
                if (CanvasNinja.keyHold[8] || CanvasNinja.keyHoldSpec[2] || CanvasNinja.keyHold[2] || this.currentMovePoint != null) {
                    b = true;
                }
                this.updateCharLeoCauThang(b);
                break;
            }
            case 11: {
                this.updateCharBamTuong();
                this.doUpdateFrameNormal();
                break;
            }
            case 10: {
                if (this.isScreenUpdateCharFall()) {
                    this.setAutoFallCharka();
                }
                this.doUpdateFrameHurt();
                break;
            }
            case 9: {
                this.updateCharFall();
                this.updateFrameFall();
                break;
            }
            case 8: {
                this.doUpdateCharJumpPhiTieu();
                break;
            }
            case 7: {
                this.doUpdateFrameJump();
                if (this.isMoveNew()) {
                    this.updateCharJump();
                    break;
                }
                break;
            }
            case 4: {
                this.doUpdateAtk();
                break;
            }
            case 3: {
                this.doUpdateAtkVuKhi();
                break;
            }
            case 1:
            case 2: {
                this.doUpdateFrameNormal();
                if (this.isMoveNew()) {
                    this.updateCharRun();
                    break;
                }
                break;
            }
            case 0:
            case 19: {
                super.subDirect = -1;
                this.updateFrameStand();
                this.updateCharStand();
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
            }
        }
    }

    public void updateAutoAtk() {
        if (!this.isAutoAtk) {
            return;
        }
        final Quai mobFocus = this.mobFocus;
        if (mobFocus != null && !mobFocus.isDie) {
            this.lastMobFocusLostTime = -1L;
        }
        else {
            if (mobFocus != null && mobFocus.isDie && this.lastMobFocusLostTime == -1L) {
                this.lastMobFocusLostTime = System.currentTimeMillis();
            }
            if (this.mobFocus == null && this.lastMobFocusLostTime == -1L) {
                this.lastMobFocusLostTime = System.currentTimeMillis();
            }
            if (this.lastMobFocusLostTime != -1L && System.currentTimeMillis() - this.lastMobFocusLostTime >= 3000L) {
                this.unFocusAll();
                final Quai mobInMapMap = MapScr.findMobInMapMap(myCharz().cx, myCharz().cy, CanvasNinja.w * 4);
                this.mobFocus = mobInMapMap;
                if (mobInMapMap != null && !mobInMapMap.isDontMove) {
                    final SendMessage gi = SendMessage.gI();
                    final Quai mobFocus2 = this.mobFocus;
                    final int xFirst = mobFocus2.xFirst;
                    gi.requestTele((short)(xFirst - mobFocus2.w / 2), (short)MyTile.findYTileTop(xFirst, mobFocus2.yFirst));
                    this.lastMobFocusLostTime = -1L;
                }
                else {
                    this.unFocusAll();
                }
            }
        }
        final Quai mobFocus3 = this.mobFocus;
        if (mobFocus3 != null && !mobFocus3.isDie && !mobFocus3.isBoss) {
            for (final MyButtonSkill myButtonSkill : MapScr.myButtonSkills) {
                if (!myButtonSkill.isJump && myButtonSkill.isCanUse() && myButtonSkill.skill.manaUse < this.cMP) {
                    myButtonSkill.useSkill();
                }
            }
        }
    }

    public void updateBuffSkill() {
        for (int i = this.vRangeBuffSkills.size() - 1; i >= 0; --i) {
            final RangeDamageSkill rangeDamageSkill = this.vRangeBuffSkills.elementAt(i);
            if (rangeDamageSkill.type != -1) {
                final VectorCustom vectorCustom = new VectorCustom();
                final byte type = rangeDamageSkill.type;
                if (type == 1 || type == 2) {
                    vectorCustom.addElement(this);
                    Res.logChar("Char use BUFF SKILL ", this);
                    for (int j = 0; j < MapScr.vCharInMap.size(); ++j) {
                        final Player player = (Player)MapScr.vCharInMap.elementAt(j);
                        if (!player.isBossChar()) {
                            if (!player.equals(this)) {
                                if (player.isInRange(rangeDamageSkill.rectangle) && vectorCustom.size() < rangeDamageSkill.maxTarget) {
                                    vectorCustom.addElement(player);
                                    Res.logChar("CHAR BUFF SKILL ", player);
                                }
                            }
                        }
                    }
                }
                this.setBuff(vectorCustom, rangeDamageSkill.skill);
            }
            this.vRangeBuffSkills.remove(i);
        }
    }

    public void updateCamera() {
        if (this.cmtoChar) {
            MapScr.cmtoX = this.cx - MapScr.gW2;
            MapScr.cmtoY = this.cy - MapScr.gH23 - 1;
            if (!CanvasNinja.isTouchControl) {
                MapScr.cmtoX += MapScr.gW6 * super.cdir;
            }
        }
    }

    public void updateCharBamTuong() {
        if (this.getAction() == 11) {
            if (this.isUsingChakra()) {
                this.doAction((byte)0);
            }
            if (MyTile.tileTypeAt(this.cx, this.cy, 8)) {
                this.setDirByCDir(-1);
            }
            else if (MyTile.tileTypeAt(this.cx, this.cy, 4)) {
                this.setDirByCDir(1);
            }
            else {
                Res.outz("vào xử lý rơi");
                this.doAction((byte)0);
            }
        }
    }

    public void updateCharFall() {
        if (this.lastMap == 0) {
            this.lastMap = MyTile.mapID;
            this.statusMe = 0;
            return;
        }
        if (this.isMe()) {
            final boolean isWaitingDoubleJump = this.isWaitingDoubleJump;
            if ((isWaitingDoubleJump && CanvasNinja.keyHold[2]) || (isWaitingDoubleJump && CanvasNinja.keyHoldSpec[2]) || this.isWaitingDoubleJump2) {
                this.currentMovePoint = null;
                this.isWaitingDoubleJump = false;
                if (this.isWaitingDoubleJump2) {
                    this.isWaitingDoubleJump2 = false;
                    this.doAction((byte)7);
                }
                this.isMe();
                this.doDoubleJump();
                return;
            }
        }
        if (this.isDoubleJump) {
            this.isMe();
            this.doDoubleJump();
            return;
        }
        final int cy = this.cy;
        if (cy + 4 >= MyTile.pxh) {
            this.statusMe = 0;
            if (this.me) {
                AudioManager.charFall();
            }
            this.cvy = 0;
            this.cvx = 0;
            this.cp3 = 0;
            return;
        }
        final int cdir = super.cdir;
        if (cdir == 1) {
            final int cx = this.cx;
            final int chw = super.chw;
            final int tmw = MyTile.tmw;
            final byte size = MyTile.size;
            if (cx + chw > tmw * size - size) {
                this.cvx = 0;
            }
        }
        if (cdir == -1 && this.cx - super.chw < MyTile.size) {
            this.cvx = 0;
        }
        if (this.isSpecFall) {
            if (cdir == 1) {
                final int cx2 = this.cx;
                final int chw2 = super.chw;
                if (MyTile.tileTypeAt(cx2 + chw2, cy - chw2, 4)) {
                    this.cvx = 0;
                    this.cx = MyTile.tileXofPixel(this.cx + super.chw) - super.chw;
                }
            }
            else {
                final int cx3 = this.cx;
                final int chw3 = super.chw;
                if (MyTile.tileTypeAt(cx3 - chw3, cy - chw3, 8)) {
                    this.cvx = 0;
                    this.cx = MyTile.tileXofPixel(this.cx - super.chw) + MyTile.size + super.chw;
                }
            }
        }
        final int cx4 = this.cx + this.cvx;
        this.cx = cx4;
        if (!this.isLastFrameFall) {
            ++this.cvy;
        }
        final int cvy = this.cvy;
        final int cyspeedFall = this.cyspeedFall;
        if (cvy > cyspeedFall) {
            this.cvy = cyspeedFall;
        }
        final int cy2 = this.cy + this.cvy;
        this.cy = cy2;
        if (MyTile.tileTypeAt(cx4 + 0, cy2 - 1, 4) && !this.isInTop(this.cx, this.cy)) {
            final mPoint pointAtPixel = MyTile.pointAtPixel(this.cx, this.cy - 1);
            this.pRight = pointAtPixel;
            this.cx = pointAtPixel.x;
            this.cvx = 0;
            this.subCheckDir = -1;
            this.doAction((byte)0);
            this.cvy = -this.cspeed;
            Res.outz("bám tường khi nhảy trái");
        }
        else if (MyTile.tileTypeAt(this.cx - 0, this.cy - 1, 8)) {
            final mPoint pointAtPixel2 = MyTile.pointAtPixel(this.cx - 0, this.cy - 1);
            this.pRight = pointAtPixel2;
            this.cx = pointAtPixel2.x + MyTile.size - 1;
            this.cvx = 0;
            this.subCheckDir = 1;
            this.doAction((byte)0);
            this.cvy = -this.cspeed;
            Res.outz("bám tường khi nhảy phải");
        }
        if (this.cvy > 3 && this.isInTop(this.cx, this.cy)) {
            if (MyTile.tileTypeAt(this.cx, this.cy, 1024)) {
                AudioManager.charRunOnWood(0.5f);
            }
            this.cyStartFall = 0;
            this.cvy = 0;
            this.cvx = 0;
            this.resetJump();
            this.cp2 = 0;
            this.cp1 = 0;
            this.cy = MyTile.tileXofPixel(this.cy);
            this.isLastFrameFall = true;
            this.idKhoi = 0;
            this.cp3 = 0;
            if (this.isMe()) {
                AudioManager.charFall();
            }
            return;
        }
        this.isMe();
    }

    public void updateCharInBridge() {
        if (MyTile.tileTypeAt(this.cx, this.cy + 1, 1024)) {
            MyTile.setTileTypeAtPixel(this.cx, this.cy + 1, 512);
            MyTile.setTileTypeAtPixel(this.cx, this.cy - 2, 512);
        }
        if (this.getAction() == 0 && this.getAction() == 1) {
            if (MyTile.tileTypeAt(this.cx - MyTile.size, this.cy + 1, 512)) {
                MyTile.killTileTypeAt(this.cx - MyTile.size, this.cy + 1, 512);
                MyTile.killTileTypeAt(this.cx - MyTile.size, this.cy - 2, 512);
            }
            if (MyTile.tileTypeAt(this.cx + MyTile.size, this.cy + 1, 512)) {
                MyTile.killTileTypeAt(this.cx + MyTile.size, this.cy + 1, 512);
                MyTile.killTileTypeAt(this.cx + MyTile.size, this.cy - 2, 512);
            }
        }
        else {
            int n = 0;
            while (true) {
                final int chJumpMax = this.chJumpMax;
                final byte size = MyTile.size;
                if (n >= chJumpMax + size) {
                    break;
                }
                if (MyTile.tileTypeAt(this.cx - size, this.cy + n + 1, 512)) {
                    MyTile.killTileTypeAt(this.cx - MyTile.size, this.cy + n + 1, 512);
                    MyTile.killTileTypeAt(this.cx - MyTile.size, this.cy + n - 2, 512);
                }
                if (MyTile.tileTypeAt(this.cx + MyTile.size, this.cy + n + 1, 512)) {
                    MyTile.killTileTypeAt(this.cx + MyTile.size, this.cy + n + 1, 512);
                    MyTile.killTileTypeAt(this.cx + MyTile.size, this.cy + n - 2, 512);
                }
                n += MyTile.size;
            }
        }
    }

    public void updateCharInLostHP() {
        if (MyTile.tileTypeAt(this.cx, this.cy - 1, 8192) && mSystem.currentTimeMillis() >= this.timeStartLostHp) {
            this.timeStartLostHp = mSystem.currentTimeMillis() + 2000L;
            this.doAction((byte)10);
        }
    }

    public void updateCharInThacNuoc() {
        if (!MyTile.tileTypeAt(this.cx + super.cdir * super.cw / 2, this.cy - 1, 2048) && !MyTile.tileTypeAt(this.cx, this.cy - 1, 2048)) {
            if (this.isMe()) {
                this.isInvisibleByTile = false;
            }
            else {
                this.isHideInTile = false;
            }
        }
        else if (this.isMe()) {
            this.isInvisibleByTile = true;
        }
        else {
            this.isHideInTile = true;
        }
    }

    public void updateCharJump() {
        final int cdir = super.cdir;
        if (cdir == 1) {
            final int cx = this.cx;
            final int chw = super.chw;
            final int tmw = MyTile.tmw;
            final byte size = MyTile.size;
            if (cx + chw > tmw * size - size) {
                this.cvx = 0;
            }
        }
        if (cdir == -1 && this.cx - super.chw < MyTile.size) {
            this.cvx = 0;
        }
        if (this.cvy == 0) {
            this.setCharFallFromJump();
            return;
        }
        if (this.isDoubleJump) {
            this.doDoubleJump();
            this.isMe();
            return;
        }
        this.isCanJumpTo = true;
        if (this.cy < 0) {
            byte b = 0;
            while (true) {
                final int chJumpMax = this.chJumpMax;
                final byte size2 = MyTile.size;
                if (b >= chJumpMax * 2 / size2 + 3) {
                    break;
                }
                if (MyTile.tileTypeAt(this.cx - 0, this.cy + size2 * b, 8)) {
                    final mPoint pointAtPixel = MyTile.pointAtPixel(this.cx - 0, this.cy + MyTile.size * b);
                    this.pRight = pointAtPixel;
                    this.cvx = 0;
                    this.cx = pointAtPixel.x + MyTile.size - 1;
                    this.isCanJumpTo = false;
                    break;
                }
                if (MyTile.tileTypeAt(this.cx - 0, this.cy + MyTile.size * b, 4)) {
                    final mPoint pointAtPixel2 = MyTile.pointAtPixel(this.cx + 0, this.cy + MyTile.size * b);
                    this.pRight = pointAtPixel2;
                    this.cx = pointAtPixel2.x;
                    this.cvx = 0;
                    this.isCanJumpTo = false;
                    break;
                }
                ++b;
            }
            if (!this.isCanJumpTo) {
                this.doAction((byte)9);
                Res.outz(1, "Do fall can't jump");
                return;
            }
        }
        this.cx += this.cvx;
        final int cy = this.cy;
        int cvy = this.cvy;
        this.cy = cy + cvy;
        ++cvy;
        if ((this.cvy = cvy) > 0) {
            this.cvy = 0;
        }
        if (this.cvy == 0) {
            this.setCharFall();
        }
        if (MyTile.tileTypeAt(this.cx - 0, this.cy - 1, 8) && !this.isInTop(this.cx, this.cy)) {
            final mPoint pointAtPixel3 = MyTile.pointAtPixel(this.cx - 0, this.cy);
            this.pRight = pointAtPixel3;
            this.cx = pointAtPixel3.x + MyTile.size - 1;
            this.cvx = 0;
            this.subCheckDir = 1;
            this.doAction((byte)0);
            this.cvy = -this.cyspeed;
            Res.outz(1, "bám tường khi nhảy");
            this.resetJump();
        }
        else if (MyTile.tileTypeAt(this.cx - 0, this.cy - 1, 4) && !this.isInTop(this.cx, this.cy)) {
            final mPoint pointAtPixel4 = MyTile.pointAtPixel(this.cx + 0, this.cy - 1);
            this.pRight = pointAtPixel4;
            this.cx = pointAtPixel4.x;
            this.cvx = 0;
            this.subCheckDir = -1;
            this.doAction((byte)0);
            this.cvy = -this.cyspeed;
            Res.outz(1, "Bám tường khi nhảy");
        }
        if (this.isMe() && !Player.ischangingMap && this.isInWaypoint()) {
            if (MyTile.isTrainingMap()) {
                Player.ischangingMap = true;
            }
            else {
                SendMessage.gI().requestChangeMap();
            }
            Player.isLockKey = true;
            Player.ischangingMap = true;
            CanvasNinja.clearKeyHold();
            CanvasNinja.clearKeyPressed();
            return;
        }
        if (this.statusMe != 39 && MyTile.tileTypeAt(this.cx, this.cy - super.ch + MyTile.size, 16)) {
            this.cp1 = 0;
            this.cp2 = 0;
            this.cvy = 1;
            this.delayFall = 0;
            if (this.cy < 0) {
                this.cy = 0;
            }
            this.cy = MyTile.tileYofPixel(this.cy + 25);
            this.doAction((byte)9);
            CanvasNinja.clearKeyHold();
        }
        final int cp3 = this.cp3;
        if (cp3 < 0) {
            this.cp3 = cp3 + 1;
        }
        this.config = 7;
        if (!this.isMe()) {
            final mMovePointCustom currentMovePoint = this.currentMovePoint;
            if (currentMovePoint != null && this.cy < currentMovePoint.yEnd) {
                this.stop();
            }
        }
    }

    public void updateCharJumpSkill() {
        if (this.isJumpSkill) {
            final int cdir = super.cdir;
            if (cdir == 1) {
                final int cx = this.cx;
                final int chw = super.chw;
                final int tmw = MyTile.tmw;
                final byte size = MyTile.size;
                if (cx + chw > tmw * size - size) {
                    this.cvx = 0;
                }
            }
            if (cdir == -1 && this.cx - super.chw < MyTile.size) {
                this.cvx = 0;
            }
            final int cvy = this.cvy;
            if (cvy == 0) {
                this.setCharFallFromJump();
                return;
            }
            if (this.isDoubleJump) {
                this.doDoubleJump();
                this.isMe();
                return;
            }
            final int cvy2 = cvy + 1;
            this.cvy = cvy2;
            final int cx2 = this.cx + this.cvx;
            this.cx = cx2;
            final int cy = this.cy + cvy2;
            this.cy = cy;
            if (cvy2 > 0 || cy < this.cyTempMax) {
                this.cvy = 0;
            }
            if (MyTile.tileTypeAt(cx2 - 0, cy - 1, 8) && !this.isInTop(this.cx, this.cy)) {
                final mPoint pointAtPixel = MyTile.pointAtPixel(this.cx - 0, this.cy);
                this.pRight = pointAtPixel;
                this.cx = pointAtPixel.x + MyTile.size - 1;
                this.cvx = 0;
                this.subCheckDir = 1;
                this.doAction((byte)0);
                this.cvy = -this.cyspeed;
                Res.outz(1, "bám tường khi nhảy");
                this.resetJump();
            }
            else if (MyTile.tileTypeAt(this.cx + 0, this.cy - 1, 4) && !this.isInTop(this.cx, this.cy)) {
                final mPoint pointAtPixel2 = MyTile.pointAtPixel(this.cx + 0, this.cy - 1);
                this.pRight = pointAtPixel2;
                this.cx = pointAtPixel2.x;
                this.cvx = 0;
                this.subCheckDir = -1;
                this.doAction((byte)0);
                this.cvy = -this.cyspeed;
                Res.outz(1, "bám tường khi nhảy");
            }
            if (this.isMe() && !Player.ischangingMap && this.isInWaypoint()) {
                if (MyTile.isTrainingMap()) {
                    Player.ischangingMap = true;
                }
                else {
                    SendMessage.gI().requestChangeMap();
                }
                Player.isLockKey = true;
                Player.ischangingMap = true;
                CanvasNinja.clearKeyHold();
                CanvasNinja.clearKeyPressed();
                return;
            }
            if (this.statusMe != 39 && MyTile.tileTypeAt(this.cx, this.cy - super.ch + MyTile.size, 16)) {
                this.cp1 = 0;
                this.cp2 = 0;
                this.cvy = 1;
                this.delayFall = 0;
                if (this.cy < 0) {
                    this.cy = 0;
                }
                this.cy = MyTile.tileYofPixel(this.cy + 25);
                this.doAction((byte)9);
                CanvasNinja.clearKeyHold();
            }
            final int cp3 = this.cp3;
            if (cp3 < 0) {
                this.cp3 = cp3 + 1;
            }
            this.config = 7;
        }
    }

    public void updateCharLeoCauThang(final boolean b) {
        if ((b || !this.me) && CanvasNinja.gameTick % 3 == 0 && ++this.indexFrame > (this.frameMax = Player.FRAME[this.statusMe].length - 1)) {
            this.indexFrame = 0;
        }
        final int cy = this.cy;
        final int cvy = this.cvy;
        final int cy2 = cy + cvy;
        this.cy = cy2;
        final int cx = this.cx;
        final int cvx = this.cvx;
        final int cx2 = cx + cvx;
        this.cx = cx2;
        if (cvy > 0) {
            this.cvy = cvy - 1;
        }
        else if (cvy < 0) {
            this.cvy = cvy + 1;
        }
        if (cvx > 0) {
            this.cvx = cvx - 1;
        }
        else if (cvx < 0) {
            this.cvx = cvx + 1;
        }
        this.frame = (byte)Player.FRAME[this.statusMe][this.indexFrame];
        if (!MyTile.tileTypeAt(cx2, cy2, 32)) {
            this.doAction((byte)9);
        }
    }

    public void updateCharLeoTuong() {
        this.isLockMove = true;
        final int cx = this.cx + this.cvx;
        this.cx = cx;
        final int cy = this.cy;
        final int cvy = this.cvy;
        this.cy = cy + cvy;
        final int cvy2 = cvy + 1;
        this.cvy = cvy2;
        if (cvy2 > 0) {
            this.cvy = 0;
        }
        final int typeLeftRightTarget = this.typeLeftRightTarget;
        if (typeLeftRightTarget == -1) {
            final int cxTarget = this.cxTarget;
            if (cx < cxTarget) {
                this.cx = cxTarget;
            }
        }
        if (typeLeftRightTarget == 1) {
            final int cx2 = this.cx;
            final int cxTarget2 = this.cxTarget;
            if (cx2 > cxTarget2) {
                this.cx = cxTarget2;
            }
        }
        final int cx3 = this.cx;
        final int cxTarget3 = this.cxTarget;
        if (cx3 == cxTarget3) {
            this.cvx = 0;
        }
        if (cx3 == cxTarget3 && this.cvy == 0) {
            this.isLockMove = false;
            this.doAction((byte)11);
        }
    }

    public void updateCharRun() {
        if (this.isScreenUpdateCharFall() && this.setAutoFallCharka()) {
            return;
        }
        if (MapScr.gI().menuHidden != null) {
            MapScr.gI().closeMenuHidden();
        }
        if (super.cxTo != -100 && super.cyTo != -100) {
            return;
        }
        final int cx = this.cx;
        final int cmx = MapScr.cmx;
        if (cx >= cmx && cx <= cmx + CanvasNinja.w) {
            if (MyTile.tileTypeAt(cx, this.cy, 1024)) {
                AudioManager.charRunOnWood(0.5f);
            }
            else {
                AudioManager.charRun(0.5f);
            }
        }
        if (++this.cp1 >= 10) {
            this.cp1 = 0;
            this.cBonusSpeed = 0;
        }
        this.config = (this.cp1 >> 1) + 2;
        final int cdir = super.cdir;
        if (cdir == 1) {
            final int cx2 = this.cx;
            final int chw = super.chw;
            final int tmw = MyTile.tmw;
            final byte size = MyTile.size;
            if (cx2 + chw > tmw * size - size) {
                this.cvx = 0;
            }
        }
        if (cdir == -1 && this.cx - super.chw < MyTile.size) {
            this.cvx = 0;
        }
        final byte typeInWall = this.typeInWall();
        if (typeInWall == -1) {
            final int cdir2 = super.cdir;
            if (cdir2 == -1) {
                final int cy = this.cy;
                final int chw2 = super.chw;
                final int tmh = MyTile.tmh;
                final byte size2 = MyTile.size;
                if (cy + chw2 > tmh * size2 - size2) {
                    this.cvy = 0;
                }
            }
            if (cdir2 == 1) {
                final int cy2 = this.cy;
                final int chw3 = super.chw;
                if (cy2 - chw3 < 0 || MyTile.tileTypeAt(this.cx - super.chh, cy2 - chw3, 16)) {
                    this.cvy = 0;
                }
            }
        }
        else if (typeInWall == 1) {
            final int cdir3 = super.cdir;
            if (cdir3 == 1) {
                final int cy3 = this.cy;
                final int chw4 = super.chw;
                final int tmh2 = MyTile.tmh;
                final byte size3 = MyTile.size;
                if (cy3 + chw4 > tmh2 * size3 - size3) {
                    this.cvy = 0;
                }
            }
            if (cdir3 == -1) {
                final int cy4 = this.cy;
                final int chw5 = super.chw;
                if (cy4 - chw5 < 0 || MyTile.tileTypeAt(this.cx + super.chh, cy4 - chw5, 16)) {
                    this.cvy = 0;
                }
            }
        }
        final int cx3 = this.cx + this.cvx * 1;
        this.cx = cx3;
        final int cy5 = this.cy + this.cvy * 1;
        this.cy = cy5;
        if (super.cdir == 1) {
            final int chw6 = super.chw;
            if (MyTile.tileTypeAt(cx3 + chw6, cy5 - chw6, 4)) {
                if (this.isUsingChakra()) {
                    if (MyTile.tileTypeAt(this.cx, this.cy, 2) && !MyTile.tileTypeAt(this.cx, this.cy, 4)) {
                        Res.outz(2, "Đi gần tường trái và nhảy lên");
                        this.cxTarget = MyTile.tileXofPixel(this.cx + super.chw);
                        final int cx4 = this.cx;
                        final int chw7 = super.chw;
                        if (MyTile.tileTypeAt(cx4 - chw7 - 1, this.cy + chw7, 2)) {
                            final int cx5 = this.cx;
                            final int chw8 = super.chw;
                            final mPoint pointAtPixel = MyTile.pointAtPixel(cx5 - chw8 - 1, this.cy + chw8);
                            int cy6;
                            final int n = cy6 = this.cy;
                            if (super.chw + n >= pointAtPixel.y) {
                                cy6 = n - MyTile.size;
                            }
                            this.cyTarget = cy6;
                            this.isMe();
                        }
                        this.doAction((byte)21);
                    }
                    else {
                        this.cvx = 0;
                        final int tileXofPixel = MyTile.tileXofPixel(this.cx + super.chw);
                        this.cx = tileXofPixel;
                        final int chw9 = super.chw;
                        if (MyTile.tileTypeAt(tileXofPixel - chw9 - 1, this.cy + chw9, 2)) {
                            final int cx6 = this.cx;
                            final int chw10 = super.chw;
                            final mPoint pointAtPixel2 = MyTile.pointAtPixel(cx6 - chw10 - 1, this.cy + chw10);
                            int cy7;
                            final int n2 = cy7 = this.cy;
                            if (super.chw + n2 >= pointAtPixel2.y) {
                                cy7 = n2 - MyTile.size;
                            }
                            this.cy = cy7;
                            this.isMe();
                        }
                        this.doAction((byte)2);
                    }
                }
                else {
                    this.cvx = 0;
                    this.cx = MyTile.tileXofPixel(this.cx + super.chw) - super.chw;
                }
            }
        }
        else {
            final int chw11 = super.chw;
            if (MyTile.tileTypeAt(cx3 - chw11, cy5 - chw11, 8)) {
                if (this.isUsingChakra()) {
                    if (MyTile.tileTypeAt(this.cx, this.cy, 2) && !MyTile.tileTypeAt(this.cx, this.cy, 8)) {
                        Res.outz(2, "Đi gần tường phải và nhảy lên");
                        this.cxTarget = MyTile.tileXofPixel(this.cx - super.chw) + MyTile.size - 1;
                        final int cx7 = this.cx;
                        final int chw12 = super.chw;
                        if (MyTile.tileTypeAt(cx7 + chw12, this.cy + chw12, 2)) {
                            final int cx8 = this.cx;
                            final int chw13 = super.chw;
                            final mPoint pointAtPixel3 = MyTile.pointAtPixel(cx8 - chw13, this.cy + chw13);
                            int cy8;
                            final int n3 = cy8 = this.cy;
                            if (super.chw + n3 >= pointAtPixel3.y) {
                                cy8 = n3 - MyTile.size;
                            }
                            this.cyTarget = cy8;
                        }
                        this.doAction((byte)21);
                    }
                    else {
                        Res.outz("Có charka và đi trên tường bên phải");
                        this.cvx = 0;
                        final int cx9 = MyTile.tileXofPixel(this.cx - super.chw) + MyTile.size - 1;
                        this.cx = cx9;
                        final int chw14 = super.chw;
                        if (MyTile.tileTypeAt(cx9 + chw14, this.cy + chw14, 2)) {
                            final int cx10 = this.cx;
                            final int chw15 = super.chw;
                            final mPoint pointAtPixel4 = MyTile.pointAtPixel(cx10 - chw15, this.cy + chw15);
                            int cy9;
                            final int n4 = cy9 = this.cy;
                            if (super.chw + n4 >= pointAtPixel4.y) {
                                cy9 = n4 - MyTile.size;
                            }
                            this.cy = cy9;
                        }
                        this.doAction((byte)2);
                    }
                }
                else {
                    this.cvx = 0;
                    this.cx = MyTile.tileXofPixel(this.cx - super.chw) + MyTile.size + super.chw;
                }
            }
        }
        if (this.cvx == 0) {
            this.statusMe = 0;
            this.cBonusSpeed = 0;
        }
        if (this.cvy == 0) {
            this.statusMe = 0;
            this.cBonusSpeed = 0;
        }
        if (!this.isUsingChakra()) {
            this.checkDoFall();
        }
        else if (!MyTile.tileTypeAt(this.cx, this.cy, 8)) {
            if (!MyTile.tileTypeAt(this.cx, this.cy, 4)) {
                this.checkDoFall();
            }
        }
    }

    public void updateCharStand() {
        if (this.isCloneMe) {
            return;
        }
        if ((MyTile.tileTypeAt(this.cx - 1, this.cy, 8) || MyTile.tileTypeAt(this.cx, this.cy, 4)) && !this.isUsingChakra() && !MyTile.tileTypeAt(this.cx, this.cy, 2)) {
            super.subDirect = -1;
            this.doAction((byte)11);
            return;
        }
        if (this.isScreenUpdateCharFall()) {
            this.setAutoFallCharka();
        }
        this.isAttack = false;
        this.cvx = 0;
        this.cvy = 0;
        if (++this.cp1 > 30) {
            this.cp1 = 0;
        }
        if (this.cp1 % 15 < 5) {
            this.config = 0;
        }
        else {
            this.config = 1;
        }
        this.updateCharInBridge();
        if (!this.isMe() && ++this.cp3 > 50) {
            this.cp3 = 0;
            this.currentMovePoint = null;
        }
    }

    public void updateClickMovePointMe() {
        if (this.isBlock()) {
            return;
        }
        if (this.vcurrentMovePoints.size() > 0) {
            if (this.currentMovePoint == null && this.getAction() != 9 && this.getAction() != 7) {
                this.currentMovePoint = this.vcurrentMovePoints.elementAt(0);
                this.vcurrentMovePoints.remove(0);
            }
            else if (this.getAction() == 9 && this.vcurrentMovePoints.size() > 0 && this.vcurrentMovePoints.elementAt(0).isWaittingFall) {
                this.currentMovePoint = null;
            }
            else {
                this.cvx = this.cspeed * super.cdir;
                this.isMe();
            }
        }
        final mMovePointCustom currentMovePoint = this.currentMovePoint;
        if (currentMovePoint != null) {
            final int xEnd = currentMovePoint.xEnd;
            final int cx = this.cx;
            final int n = -1;
            int cdir;
            if (xEnd >= cx) {
                cdir = 1;
            }
            else {
                cdir = -1;
            }
            super.cdir = cdir;
            final int yEnd = currentMovePoint.yEnd;
            final int cy = this.cy;
            if (yEnd >= cy - this.chJumpMax && !currentMovePoint.isDoDoubleJump) {
                if (yEnd >= cy - super.ch && !currentMovePoint.isDoJump) {
                    if (yEnd < cy && this.skillCalling != null) {
                        int cspeed = this.cspeed;
                        final int abs = Res.abs(cx - xEnd);
                        if (abs < this.sJumpMax) {
                            cspeed = (int)(abs / this.tJump);
                        }
                        this.cvx = super.cdir * cspeed;
                        this.doAction((byte)7);
                        this.currentMovePoint = null;
                    }
                    else if (currentMovePoint != null && abs(cx - xEnd) <= this.cspeed && abs(this.cy - this.currentMovePoint.yEnd) <= MyTile.size / 2) {
                        if (MyTile.tileTypeAt(this.cx, this.currentMovePoint.yEnd, 32)) {
                            this.cx = MyTile.centerPointAtPixel(this.cx, this.currentMovePoint.yEnd).x;
                        }
                        else {
                            this.statusMe = 1;
                            this.cx = (this.currentMovePoint.xEnd + this.cx) / 2;
                        }
                        if (abs(this.cx - this.currentMovePoint.xEnd) <= 2) {
                            this.statusMe = 1;
                            this.cx = this.currentMovePoint.xEnd;
                        }
                        this.cvy = 0;
                        this.cvx = 0;
                        if (MyTile.tileTypeAt(this.cx, this.cy, 2)) {
                            this.statusMe = 1;
                            this.cy = MyTile.tileYofPixel(this.cy);
                        }
                        this.currentMovePoint = null;
                        MapScr.instance.clickMoving = false;
                    }
                    else {
                        final mMovePointCustom currentMovePoint2 = this.currentMovePoint;
                        if (currentMovePoint2 == null) {
                            return;
                        }
                        int cdir2 = n;
                        if (currentMovePoint2.xEnd > this.cx) {
                            cdir2 = 1;
                        }
                        this.setDirByCDir(super.cdir = cdir2);
                        if (MyTile.tileTypeAt(this.cx, this.cy, 2)) {
                            final int tileYofPixel = MyTile.tileYofPixel(this.cy);
                            if (this.currentMovePoint != null) {
                                if (this.cy != tileYofPixel) {
                                    this.cy = tileYofPixel;
                                }
                                this.setDirByCDir(super.cdir);
                                this.cvx = this.cspeed * super.cdir;
                                this.doAction((byte)1);
                            }
                            if (abs(this.cx - this.currentMovePoint.xEnd) <= 10) {
                                if (this.currentMovePoint.yEnd > this.cy) {
                                    final int n2 = 0;
                                    int n3 = 0;
                                    int n4;
                                    while (true) {
                                        n4 = n2;
                                        if (n3 >= 2) {
                                            break;
                                        }
                                        if (MyTile.tileTypeAt(this.currentMovePoint.xEnd + super.chw * super.cdir, this.cy + super.chh * n3, 2)) {
                                            n4 = 1;
                                            break;
                                        }
                                        ++n3;
                                    }
                                    if (n4 != 0) {
                                        this.currentMovePoint = null;
                                        MapScr.instance.clickMoving = false;
                                        this.doAction((byte)0);
                                        this.cvy = 0;
                                        this.cvx = 0;
                                        this.checkPerformEndMovePointAction();
                                    }
                                }
                                else {
                                    this.currentMovePoint = null;
                                }
                            }
                        }
                        else if (MyTile.tileTypeAt(this.cx - 1, this.cy + super.chw, 8)) {
                            if (this.isUsingChakra() && abs(this.cy - this.currentMovePoint.yEnd) <= MyTile.size / 4) {
                                this.cy = this.currentMovePoint.yEnd;
                                this.currentMovePoint = null;
                                MapScr.instance.clickMoving = false;
                                this.cvy = 0;
                                this.cvx = 0;
                            }
                        }
                        else if (MyTile.tileTypeAt(this.cx, this.cy + super.chw, 4)) {
                            if (this.isUsingChakra() && abs(this.cy - this.currentMovePoint.yEnd) <= MyTile.size / 4) {
                                this.cy = this.currentMovePoint.yEnd;
                                this.currentMovePoint = null;
                                MapScr.instance.clickMoving = false;
                                this.cvy = 0;
                                this.cvx = 0;
                            }
                        }
                        else if (MyTile.tileTypeAt(this.cx, this.cy + super.chw, 2)) {
                            final int cdir3 = super.cdir;
                            this.cvx = this.cspeed * cdir3;
                            this.setDirByCDir(cdir3);
                            this.doAction((byte)1);
                        }
                        else {
                            this.doAction((byte)9);
                            this.currentMovePoint = null;
                        }
                    }
                }
                else {
                    int cspeed2 = this.cspeed;
                    final int abs2 = Res.abs(cx - xEnd);
                    if (abs2 < this.sJumpMax) {
                        cspeed2 = (int)(abs2 / this.tJump);
                    }
                    this.cvx = super.cdir * cspeed2;
                    this.doAction((byte)7);
                    this.currentMovePoint = null;
                }
            }
            else {
                this.cvx = cdir * this.cspeed;
                this.doAction((byte)7);
                this.isWaitingDoubleJump2 = true;
                this.currentMovePoint = null;
            }
        }
    }

    public void updateClone() {
        if (this.isClone) {
            this.setDirByCDir(super.cdir);
            if (this.mobFocus == null && this.charFocus == null) {
                final IMapObject objectMap = MapScr.findObjectMap(this.cx, this.cy, MyTile.size * 5);
                if (objectMap != null) {
                    this.focusManualTo(objectMap);
                }
            }
            if (this.mobFocus != null) {
                final Skill skillCanUse = this.findSkillCanUse();
                if (skillCanUse != null) {
                    this.doFire(skillCanUse);
                }
            }
            if (this.charFocus != null && this.isCanAttackPlayer()) {
                final Skill skillCanUse2 = this.findSkillCanUse();
                if (skillCanUse2 != null) {
                    this.doFire(skillCanUse2);
                }
            }
        }
    }

    public void updateColorName(final int numTeam) {
        this.isLeader = false;
        switch (this.numTeam = numTeam) {
            default: {
                final mFont tahoma_7b_white_border_black = mFont.tahoma_7b_white_border_black;
                this.fontPaintName = tahoma_7b_white_border_black;
                this.fontPaintMyName = tahoma_7b_white_border_black;
                break;
            }
            case 9: {
                final mFont tahoma_7b_purple_border_black = mFont.tahoma_7b_purple_border_black;
                this.fontPaintName = tahoma_7b_purple_border_black;
                this.fontPaintMyName = tahoma_7b_purple_border_black;
                break;
            }
            case 8: {
                final mFont tahoma_7b_yellow_border_black = mFont.tahoma_7b_yellow_border_black;
                this.fontPaintName = tahoma_7b_yellow_border_black;
                this.fontPaintMyName = tahoma_7b_yellow_border_black;
                break;
            }
            case 6: {
                final mFont tahoma_7b_blue_border_black = mFont.tahoma_7b_blue_border_black;
                this.fontPaintName = tahoma_7b_blue_border_black;
                this.fontPaintMyName = tahoma_7b_blue_border_black;
                break;
            }
            case 5: {
                final mFont tahoma_7b_blue_border_black2 = mFont.tahoma_7b_blue_border_black;
                this.fontPaintName = tahoma_7b_blue_border_black2;
                this.fontPaintMyName = tahoma_7b_blue_border_black2;
                break;
            }
            case 4: {
                final mFont tahoma_7b_orange_border_black = mFont.tahoma_7b_orange_border_black;
                this.fontPaintName = tahoma_7b_orange_border_black;
                this.fontPaintMyName = tahoma_7b_orange_border_black;
                break;
            }
            case 3: {
                final mFont tahoma_7b_green_border_black = mFont.tahoma_7b_green_border_black;
                this.fontPaintName = tahoma_7b_green_border_black;
                this.fontPaintMyName = tahoma_7b_green_border_black;
                break;
            }
            case 2: {
                final mFont tahoma_7b_red_border_black = mFont.tahoma_7b_red_border_black;
                this.fontPaintName = tahoma_7b_red_border_black;
                this.fontPaintMyName = tahoma_7b_red_border_black;
                break;
            }
            case 0: {
                final mFont tahoma_7b_white_border_black2 = mFont.tahoma_7b_white_border_black;
                this.fontPaintName = tahoma_7b_white_border_black2;
                this.fontPaintMyName = tahoma_7b_white_border_black2;
                break;
            }
        }
    }

    public void updateColorNameByPK() {
        switch (this.cPk) {
            case 4: {
                this.updateColorName(2);
                break;
            }
            case 3: {
                this.updateColorName(6);
                break;
            }
            case 2: {
                this.updateColorName(9);
                break;
            }
            case 1: {
                this.updateColorName(8);
                break;
            }
            case 0: {
                this.updateColorName(0);
                break;
            }
        }
    }

    public void updateContinueSkill() {
        if ((this.isMe() || this.isClone) && !this.isContinueSkill && this.timeDelayContinueSkill != 0L && mSystem.currentTimeMillis() > this.timeDelayContinueSkill) {
            this.isContinueSkill = true;
        }
    }

    public boolean updateDelayFall() {
        if (this.timeDelayCharFallFromJump != 0L) {
            if (mSystem.currentTimeMillis() > this.timeDelayCharFallFromJump) {
                this.timeDelayCharFallFromJump = 0L;
                this.setCharFall();
            }
            return true;
        }
        return false;
    }

    public void updateDoJumpDelay() {
        if (this.isCheckJumpSv) {
            this.isJumpSv = false;
            if (Res.abs(this.cx - this.cxJumpSv) <= this.cspeed) {
                this.isJumpSv = true;
            }
            if (this.isJumpSv && Res.abs(this.cx - this.cxJumpSv) == 0) {
                this.doJump();
                this.isCheckJumpSv = false;
                return;
            }
            int n = 0;
            if (this.isJumpSv) {
                n = n;
                if (Res.abs(this.cx - this.cxJumpSv) < this.cspeed) {
                    n = 1;
                }
            }
            if (n != 0 && Res.abs(this.cx - this.cxJumpSv) <= this.cspeed * 2) {
                this.doJump();
                this.isCheckJumpSv = false;
            }
        }
    }

    public void updateDrainByType(final byte b, final int chp) {
        switch (b) {
            case 2: {
                this.cSP = chp;
                break;
            }
            case 1: {
                this.cMP = chp;
                break;
            }
            case 0: {
                this.cHP = chp;
                break;
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
                    MapScr.createBigBang(this.cx - MapScr.cmx, this.cy - MapScr.cmy, 20);
                }
                if (mainEffect.isAddSplashWeapon) {
                    if (this.isHaveWeaponCache()) {
                        this.addWeapon(this.idPartCache[3]);
                    }
                    mainEffect.isAddSplashWeapon = false;
                }
                this.vEffectAfters.remove(i);
            }
        }
        for (int j = 0; j < this.vEffectBefores.size(); ++j) {
            final MainEffect mainEffect2 = this.vEffectBefores.get(j);
            mainEffect2.update();
            if ((mainEffect2.timeBuff > 0L && mSystem.currentTimeMillis() > mainEffect2.timeBuff) || mainEffect2.isRemove) {
                if (mainEffect2.isBigBang) {
                    MapScr.createBigBang(this.cx - MapScr.cmx, this.cy - MapScr.cmy, 20);
                }
                if (mainEffect2.isAddSplashWeapon) {
                    if (this.isHaveWeapon()) {
                        this.addSplashWeapon(this.idPart[3]);
                    }
                    mainEffect2.isAddSplashWeapon = false;
                }
                this.vEffectBefores.remove(j);
            }
        }
    }

    public void updateFrameFall() {
        if (CanvasNinja.gameTick % 3 == 0) {
            ++this.indexFrame;
        }
        if (!this.isLastFrameFall) {
            this.indexFrame = 0;
        }
        else {
            final int indexFrame = this.indexFrame;
            final int indexStartTiepDat = this.indexStartTiepDat;
            if (indexFrame < indexStartTiepDat) {
                this.indexFrame = indexStartTiepDat;
            }
            if (this.indexFrame > (this.frameMax = Player.FRAME[this.statusMe].length - 1)) {
                this.indexFrame = indexStartTiepDat;
                this.statusMe = 0;
            }
        }
        this.frame = (byte)Player.FRAME[9][this.indexFrame];
    }

    public void updateFrameSpec() {
        if (this.isCloneMe) {
            this.dy = 0;
            this.dx = 0;
            return;
        }
        final byte action = this.getAction();
        int n = -1;
        if (action != 11 && (!this.isInWall() || this.getAction() != 0 || !this.isUsingChakra())) {
            if (this.statusMe == 12) {
                this.dy = -super.ch / 2;
            }
            else if (this.isInWall() && this.getAction() == 0) {
                if (super.direct != 2) {
                    n = 1;
                }
                this.dx = -n * 9;
            }
            else {
                this.dx = 0;
                this.dy = 0;
            }
        }
        else {
            if (super.direct != 2) {
                n = 1;
            }
            this.dx = -n * 9;
            this.dy = 0;
        }
    }

    public void updateFrameStand() {
        if (this.isInWall() && !this.isCloneMe) {
            if (MyTile.tileTypeAt(this.cx, this.cy + super.chw, 8)) {
                this.setDirrectByCDir(-1);
            }
            if (MyTile.tileTypeAt(this.cx, this.cy + super.chw, 4)) {
                this.setDirrectByCDir(1);
            }
            if (CanvasNinja.gameTick % this.tickSpeedFrame == 0) {
                final int indexFrame = this.indexFrame + 1;
                this.indexFrame = indexFrame;
                final int[] array = Player.FRAME[11];
                if (indexFrame > (this.frameMax = array.length - 1)) {
                    this.indexFrame = 0;
                }
                this.frame = (byte)array[this.indexFrame];
            }
        }
        else if (CanvasNinja.gameTick % this.tickSpeedFrame == 0) {
            final int indexFrame2 = this.indexFrame + 1;
            this.indexFrame = indexFrame2;
            final int[] array2 = Player.FRAME[this.statusMe];
            if (indexFrame2 > (this.frameMax = array2.length - 1)) {
                this.indexFrame = 0;
            }
            this.frame = (byte)array2[this.indexFrame];
        }
    }

    public void updateHp_bar() {
        if (this.isMe()) {
            final StringBuilder sb = new StringBuilder();
            sb.append("cHP meeeeeeeee:");
            sb.append(this.cHP);
            Res.outz(2, sb.toString());
        }
        final int chpFull = this.cHPFull;
        if (chpFull == 0) {
            return;
        }
        final int chp = this.cHP;
        this.len = (int)(chp * 100L / chpFull * this.w_hp_bar) / 100;
        final int n = (int)(chp * 100L / chpFull);
        if ((this.per = n) >= 100) {
            this.per_tem = n;
        }
        this.offset = 0;
        if (n < 30) {
            this.color = 15473700;
            this.imgHPtem = LoadDataManager.imgHP_tm_do;
        }
        else if (n < 60) {
            this.color = 16744448;
            this.imgHPtem = LoadDataManager.imgHP_tm_vang;
        }
        else {
            this.color = 11992374;
            this.imgHPtem = LoadDataManager.imgHP_tm_xanh;
        }
    }

    public void updateMoney(final int gold, final int gemLock, final int gem) {
        this.gold = gold;
        this.gem = gem;
        this.gemLock = gemLock;
    }

    public void updateOld() {
        final Player charFocus = this.charFocus;
        if (charFocus != null && charFocus.cy < 0) {
            this.charFocus = null;
        }
        this.soundUpdate();
        this.updateShadown();
        if (this.cmtoChar) {
            MapScr.cmtoX = this.cx - MapScr.gW2;
            MapScr.cmtoY = this.cy - MapScr.gH23 - 1;
            if (!CanvasNinja.isTouchControl) {
                MapScr.cmtoX += MapScr.gW6 * super.cdir;
            }
        }
        this.tick = (this.tick + 1) % 100;
        if (this.isMe()) {
            final Player charFocus2 = this.charFocus;
            if (charFocus2 != null && !MapScr.vCharInMap.contains(charFocus2)) {
                this.charFocus = null;
            }
            final int cx = this.cx;
            if (cx < 10) {
                this.cvx = 0;
                this.cx = 10;
            }
            else {
                final int pxw = MyTile.pxw;
                if (cx > pxw - 10) {
                    this.cx = pxw - 10;
                    this.cvx = 0;
                }
            }
            if (!Player.ischangingMap && this.isInWaypoint()) {
                if (MyTile.isTrainingMap()) {
                    Player.ischangingMap = true;
                }
                else {
                    SendMessage.gI().requestChangeMap();
                }
                Player.isLockKey = true;
                Player.ischangingMap = true;
                CanvasNinja.clearKeyHold();
                CanvasNinja.clearKeyPressed();
                return;
            }
            if (this.isLockMove) {
                this.currentMovePoint = null;
            }
            if (this.isMe()) {
                this.updateClickMovePointMe();
            }
        }
        else {
            this.updateClickMovePointMe();
            this.updateClickMovePointOther();
        }
    }

    public void updatePopup() {
        final Popup popup = super.popup;
        if (popup != null && popup.timeShow != 0L) {
            final long currentTimeMillis = mSystem.currentTimeMillis();
            final Popup popup2 = super.popup;
            if (currentTimeMillis > popup2.timeShow) {
                popup2.timeShow = 0L;
                popup2.isPaint = false;
                super.popup = null;
            }
        }
    }

    public void updateRangeSkill() {
        for (int i = this.vRangeDmgSkills.size() - 1; i >= 0; --i) {
            final RangeDamageSkill rangeDamageSkill = this.vRangeDmgSkills.elementAt(i);
            if (rangeDamageSkill.type != -1) {
                this.vMobGetDmg = new VectorCustom();
                this.vPlayerGetDmg = new VectorCustom();
                final byte type = rangeDamageSkill.type;
                if (type == 0 || type == 2) {
                    for (int j = 0; j < MapScr.vMob.size(); ++j) {
                        final Quai quai = (Quai)MapScr.vMob.elementAt(j);
                        if (quai.isInRange(rangeDamageSkill.rectangle) && this.vMobGetDmg.size() < rangeDamageSkill.maxTarget) {
                            this.vMobGetDmg.addElement(quai);
                            if (Config.isOfflineMode) {
                                final StringBuilder sb = new StringBuilder();
                                sb.append("Trừ máu mob khác:");
                                sb.append(quai.mobId);
                                Res.outz(sb.toString());
                                this.fakeCmdTruMauServer(quai.mobId);
                            }
                            if (rangeDamageSkill.timeNextSendAtk <= mSystem.currentTimeMillis()) {
                                if (quai.hp <= 0) {
                                    quai.startDie();
                                }
                                else {
                                    final Quai mobFocus = this.mobFocus;
                                    if (mobFocus != null) {
                                        byte b;
                                        if (mobFocus.x > this.cx) {
                                            b = -1;
                                        }
                                        else {
                                            b = 1;
                                        }
                                        mobFocus.setAction((byte)2, b);
                                    }
                                }
                            }
                        }
                    }
                }
                final byte type2 = rangeDamageSkill.type;
                if (type2 == 1 || type2 == 2) {
                    for (int k = 0; k < MapScr.vCharInMap.size(); ++k) {
                        final Player player = (Player)MapScr.vCharInMap.elementAt(k);
                        if (player.isInRange(rangeDamageSkill.rectangle) && !player.isMe() && this.vPlayerGetDmg.size() < rangeDamageSkill.maxTarget) {
                            final byte cPk = player.cPk;
                            if (cPk != 0) {
                                if (cPk != myCharz().cPk || player.cPk == 4) {
                                    this.vPlayerGetDmg.addElement(player);
                                    if (rangeDamageSkill.timeNextSendAtk <= mSystem.currentTimeMillis()) {
                                        if (player.cHP <= 0) {
                                            player.doAction((byte)35);
                                        }
                                        else {
                                            player.doAction((byte)10);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.setAttack(this.vMobGetDmg, this.vPlayerGetDmg, rangeDamageSkill);
            }
            if (rangeDamageSkill.timeSkill <= mSystem.currentTimeMillis()) {
                this.vRangeDmgSkills.remove(i);
                Res.outz(2, "debuggggg 222222222222");
            }
        }
    }

    public void updateRemoveClone() {
        if (this.isClone) {
            final long timeStartClone = this.timeStartClone;
            if (timeStartClone != -1L && timeStartClone <= mSystem.currentTimeMillis()) {
                this.isRemove = true;
            }
        }
    }

    public void updateShadowSharigan() {
        for (int i = this.vShadows.size() - 1; i >= 0; --i) {
            final Player player = this.vShadows.elementAt(i);
            if (player != null) {
                player.updateActionChar();
                final int cx = player.cx + player.cdir * 60;
                player.cx = cx;
                if (Res.distance(cx, myCharz().cx) < 60) {
                    this.doAction((byte)10);
                    MapScr.startSplash(myCharz().cx, myCharz().cy - myCharz().ch / 2, myCharz().cdir);
                }
            }
            if (Res.distance(player.cx, player.cxShadownTo) < 60) {
                if (i == 0) {
                    this.timeStartUnShadown = mSystem.currentTimeMillis() + 300L;
                }
                this.vShadows.removeElement(player);
            }
        }
        if (this.vShadows.size() == 0) {
            final long timeStartUnShadown = this.timeStartUnShadown;
            if (timeStartUnShadown != 0L && timeStartUnShadown <= mSystem.currentTimeMillis()) {
                myCharz().isGetSharigan = false;
            }
        }
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

    public void updateSubDir() {
        final byte action = this.getAction();
        int n = 6;
        int n2 = 5;
        if (action == 2 || this.getAction() == 2) {
            if (MyTile.tileTypeAt(this.cx, this.cy, 8)) {
                if (this.isUsingChakra()) {
                    if (super.cdir == 1) {
                        n2 = 7;
                    }
                    super.subDirect = (byte)n2;
                    return;
                }
            }
            else if (MyTile.tileTypeAt(this.cx, this.cy, 4) && this.isUsingChakra()) {
                if (super.cdir == 1) {
                    n = 4;
                }
                super.subDirect = (byte)n;
                return;
            }
        }
        if (this.getAction() == 21 || this.getAction() == 11 || this.getAction() == 0) {
            super.subDirect = -1;
            return;
        }
        if (this.getAction() == 9 && this.getAction() != 7) {
            super.subDirect = -1;
            return;
        }
        if (MyTile.tileTypeAt(this.cx, this.cy, 2)) {
            final mPoint pointAtPixel = MyTile.pointAtPixel(this.cx, this.cy);
            if (this.cx <= pointAtPixel.x + MyTile.size - 1 && this.cy == pointAtPixel.y) {
                super.subDirect = -1;
                return;
            }
        }
        if (MyTile.tileTypeAt(this.cx, this.cy, 8)) {
            if (this.isUsingChakra()) {
                if (super.cdir == 1) {
                    n2 = 7;
                }
                super.subDirect = (byte)n2;
            }
        }
        else if (MyTile.tileTypeAt(this.cx, this.cy, 4)) {
            if (this.isUsingChakra()) {
                if (super.cdir == 1) {
                    n = 4;
                }
                super.subDirect = (byte)n;
            }
        }
        else {
            super.subDirect = -1;
        }
    }

    public void useCharka(final boolean b) {
        this.useCharka(b, true);
    }

    public void useCharka(final boolean isHaveChakra, final boolean b) {
        this.isHaveChakra = isHaveChakra;
        if (isHaveChakra) {
            this.initEffectByFrame(new short[] { 1 }, (byte)1);
        }
        else {
            this.removeEffectChar(1, 2);
        }
        if (this.isMe() && isHaveChakra && MapScr.gI().tutorial != null && MapScr.gI().tutorial.isStepUseCharka(0)) {
            MapScr.gI().tutorial.step = 1;
        }
        if (b && this.isMe()) {
            SendMessage.gI().useDrain((byte)2, this.isHaveChakra);
        }
    }
}
