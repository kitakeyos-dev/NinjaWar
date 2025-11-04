// Class Version: 8
package ninjawar.myscr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ninjawar.input.KEY;
import ninjawar.input.MyButton;
import ninjawar.input.MyButtonSkill;
import ninjawar.input.TField;
import ninjawar.message.Config;
import ninjawar.message.SendMessage;
import ninjawar.model.*;
import ninjawar.mylib.*;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.object.*;
import ninjawar.screen.menu.MenuMain;
import ninjawar.screen.menu.MenuSetting;
import ninjawar.screen.minigame.AvatarMuzik;
import ninjawar.screen.objscr.InteractObjPaint;
import ninjawar.screen.objscr.MiniMap;
import ninjawar.screen.objscr.ObjScr;
import ninjawar.screen.objscr.PopupPK;
import ninjawar.screen.tab.*;
import ninjawar.skill.*;
import ninjawar.supporter.KeyMove;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.Tutorial;
import ninjawar.template.ItemOptionTemplates;
import ninjawar.template.ItemTemplates;
import ninjawar.template.Skills;
import ninjawar.template.SoundMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class MapScr extends BaseScreen {
    public static Vector<BuffEffect> buffEffects;
    public static int cmdBarH;
    public static int cmdBarW;
    public static int cmdBarX;
    public static int cmdBarY;
    public static MyCommand cmdChatBgItem;
    public static MyCommand cmdChatItem;
    public static MyCommand cmdChatNpc;
    public static MyCommand cmdChatPlayer;
    public static MyCommand cmdShowHide;
    public static int cmdx;
    public static int cmdy;
    public static int cmtoX;
    public static int cmtoY;
    public static int cmvx;
    public static int cmvy;
    public static int cmx;
    public static int cmxLim;
    public static int cmy;
    public static int cmyLim;
    public static int countEff;
    public static int csPadMaxH;
    public static int d;
    public static int disXC;
    public static int expBarW;
    public static int firstY;
    public static int gH;
    public static int gH2;
    public static int gH23;
    public static int gH3;
    public static int gH34;
    public static int gH6;
    public static int gW;
    public static int gW2;
    public static int gW23;
    public static int gW3;
    public static int gW34;
    public static int gW6;
    public static int girlHPBarY;
    public static int gssh;
    public static int gssw;
    public static int gssx;
    public static int gssxe;
    public static int gssy;
    public static int gssye;
    public static long hpBarW;
    public static int hpBarX;
    public static int hpBarY;
    public static int idBgSoundMap;
    public static Image imgLbtn;
    public static Image imgLbtnFocus;
    public static Image[] imgSplash;
    public static int indexEff;
    public static int indexFirst;
    public static int indexMenu;
    public static int indexRow;
    public static int indexSelect;
    public static int indexSize;
    public static int indexTitle;
    public static MapScr instance;
    public static boolean isAutoFocus;
    public static boolean isAutoPlay;
    public static boolean isChangeZone;
    public static boolean isCheckUnFocus;
    public static boolean isDisableUnfocus;
    public static boolean isHaveSelectSkill;
    public static boolean isHaveUnReadMsg;
    public static boolean isLoadAllData;
    public static boolean isPaint;
    public static boolean isPaintAlert;
    public static boolean isPaintAoNam;
    public static boolean isPaintAoNu;
    public static boolean isPaintBox;
    public static boolean isPaintBtnRevive;
    public static boolean isPaintButtonSkill;
    public static boolean isPaintCharInMap;
    public static boolean isPaintClan;
    public static boolean isPaintConvert;
    public static boolean isPaintEnemies;
    public static boolean isPaintFindTeam;
    public static boolean isPaintFriend;
    public static boolean isPaintGangTayNam;
    public static boolean isPaintGangTayNu;
    public static boolean isPaintGiayNam;
    public static boolean isPaintGiayNu;
    public static boolean isPaintGrocery;
    public static boolean isPaintGroceryLock;
    public static boolean isPaintInfoMe;
    public static boolean isPaintItemInfo;
    public static boolean isPaintLien;
    public static boolean isPaintMessage;
    public static boolean isPaintNgocBoi;
    public static boolean isPaintNhan;
    public static boolean isPaintNonNam;
    public static boolean isPaintNonNu;
    public static boolean isPaintOther;
    public static boolean isPaintPhu;
    public static boolean isPaintQuanNam;
    public static boolean isPaintQuanNu;
    public static boolean isPaintSkill;
    public static boolean isPaintSplit;
    public static boolean isPaintStack;
    public static boolean isPaintStackLock;
    public static boolean isPaintStore;
    public static boolean isPaintTask;
    public static boolean isPaintTeam;
    public static boolean isPaintTrade;
    public static boolean isPaintUpGrade;
    public static boolean isPaintUpGradeGold;
    public static boolean isPaintUpPearl;
    public static boolean isPaintWeapon;
    public static boolean isPaintZone;
    public static boolean isPing;
    public static boolean isRequestMember;
    public static boolean isViewClanInvite;
    public static boolean isViewClanMemOnline;
    public static KeyMove keyMove;
    static ArrayList<Object> leafFalls;
    static ArrayList<LightningBolt> lightningBolts;
    public static int lockTick;
    public static int mpBarW;
    public static Vector<MyButtonSkill> myButtonSkills;
    public static mClass[] nClasss;
    public static boolean notPaint;
    static ArrayList<RainEffect> rainEffects;
    static ArrayList<RainParticle> rainParticles;
    public static int shock_scr;
    private static int[] shock_x;
    private static int[] shock_y;
    public static int spBarW;
    public static int[] splashDir;
    public static int[] splashF;
    public static int[] splashState;
    public static int[] splashX;
    public static int[] splashY;
    public static TextFly textFly;
    public static VectorCustom textTime;
    public static int tickMove;
    public static String titleInputText;
    public static byte typeActive;
    public static byte typeControl;
    public static byte typeViewInfo;
    public static VectorCustom vCharCloneInMap;
    public static VectorCustom vCharInMap;
    public static VectorCustom vClan;
    public static Vector<EffectBigBang> vEffectBigBang;
    public static Vector<EffectSkillMoveToByFrame> vEffectMoreSkill;
    public static Vector<MainEffect> vEffectSkillAfters;
    public static Vector<MainEffect> vEffectSkillBefores;
    public static Vector<MainEffect> vEffectSkillWaiting;
    public static VectorCustom vEnemies;
    public static VectorCustom vFlag;
    public static VectorCustom vFriend;
    public static VectorCustom vItemMap;
    public static VectorCustom vItemUpGrade;
    public static VectorCustom vMob;
    public static VectorCustom vMobAttack;
    public static Vector<NotifyServer> vNotifyServers;
    public static VectorCustom vNpc;
    public static Vector<ObjEffect> vObjEffectMap;
    public static VectorCustom vPtMap;
    public static VectorCustom vSet;
    public static Vector<EffectShield> vecEffectLast;
    public static VectorCustom vecFocus;
    public static Vector<SoundMap> vecSoundMaps;
    static int xC;
    static int xF;
    static int xTG;
    static int yC;
    static int yF;
    public static int yHP;
    static int yTG;
    static int yTouchBar;
    int Hitem;
    int[] arrHFrameChat;
    public int[] arrHPMPSP;
    int[] arrWFrameChat;
    int[] arrXFrameChat;
    int[] arrYFrameChat;
    public int cLastFocusID;
    public int cPreFocusID;
    ChatBox chatBox;
    boolean clickMoving;
    public MyCommand cmdSendChat;
    public MyCommand cmdShowDetailChat;
    public MyCommand cmdShowUnreadMsg;
    int cmdStart;
    int cmxTransSkill;
    public int coinTrade;
    public int coinTradeOrder;
    int colorBtn;
    public int dHP;
    public int dMP;
    int dirTransSkill;
    int[][] disSkills;
    int fInfoMe;
    mFont fontPaint;
    mFont fontPaintHpFocus;
    mFont fontPaintLevelInfoMe;
    mFont fontPaintNameFocus;
    int hCoolDown;
    int hEffUp;
    int hInfoFocus;
    int[] h_real;
    String hpFocus;
    int[][] idEffectFrames;
    int[][] idEffects;
    int[][] idMoreEffects;
    int[][] idTargets;
    int idTypeTask;
    short idWeapon;
    int imgScrW;
    int indexEffUp;
    public int indexItemUse;
    public InfoServer infoServer;
    public InteractObjPaint interactObjPaint;
    public boolean isBigBang;
    boolean isChangeSkill;
    boolean isClickTest;
    public boolean isEffLvUp;
    public boolean isEffectFrameUp;
    boolean isExtendChat;
    boolean isFocusMsg;
    public boolean isInjureHp;
    public boolean isInjureMp;
    public boolean isLockKey;
    public boolean isOpacity;
    boolean isTran;
    int isTranKyNang;
    boolean isTransSkill;
    boolean isWhiteFlash;
    Vector<ChatBox.Message> last3Msg;
    public long lastSingleClick;
    public long lastTimeEff;
    long[] lastTimeHpMpSp;
    long[] lastTimeUpdateHpMpSp;
    int lightningFlashCounter;
    int margin;
    int maxColor;
    int[][] maxFights;
    int maxSizeRow;
    public MenuHidden menuHidden;
    public MiniMap menuMiniMap;
    public MissionPaint missionPaint;
    public NotifyServer notifyServer;
    int[] offset;
    int[] per;
    int[] per_tem;
    public PointFind pointFind;
    public PopupPK popupPK;
    public int speedBigBang;
    int speedTransSkill;
    int[][] speeds;
    public int tMenuDelay;
    int testEff;
    public TField tfChat;
    int[][] timeBuffs;
    public int timeCheck;
    public int timeTrade;
    long timeUp;
    public String tradeItemName;
    public String tradeName;
    public Tutorial tutorial;
    public int twHp;
    public int twMp;
    int[][] typeSkills;
    public int typeTrade;
    public int typeTradeOrder;
    public Vector<RangeDamageSkill> vRangeDmgSkills;
    Vector<MyButton> vecBtnTest;
    public Vector<MyCommand> vecCmds;
    int[] wCharChatPos;
    int wInfoFocus;
    int[] w_countDown;
    int[] w_real;
    int[] w_result;
    int xAnimFocusMiniMap;
    int[] xCharChatPos;
    int[] xHpMpSp;
    int xInfoFocus;
    int xInfoMe;
    int[] xOffsetHpMpSp;
    int[] xPosThanhTrang;
    int[] yCharChatPos;
    int[] yHpMpSp;
    int yInfoFocus;
    int yInfoMe;
    int yPaint;
    public int zoneCol;

    static {
        MapScr.isAutoPlay = false;
        MapScr.isChangeZone = false;
        MapScr.vecFocus = new VectorCustom();
        MapScr.vecEffectLast = new Vector<EffectShield>();
        MapScr.vecSoundMaps = new Vector<SoundMap>();
        MapScr.vObjEffectMap = new Vector<ObjEffect>();
        MapScr.vNotifyServers = new Vector<NotifyServer>();
        MapScr.vEffectMoreSkill = new Vector<EffectSkillMoveToByFrame>();
        MapScr.vEffectBigBang = new Vector<EffectBigBang>();
        MapScr.vEffectSkillWaiting = new Vector<MainEffect>();
        MapScr.vEffectSkillAfters = new Vector<MainEffect>();
        MapScr.vEffectSkillBefores = new Vector<MainEffect>();
        MapScr.isPaintOther = false;
        MapScr.isLoadAllData = false;
        MapScr.vClan = new VectorCustom("vClan");
        MapScr.vPtMap = new VectorCustom("vPtMap");
        MapScr.vFriend = new VectorCustom("vFriend");
        MapScr.vEnemies = new VectorCustom("vEnemies");
        MapScr.vCharInMap = new VectorCustom("vCharInMap");
        MapScr.vItemMap = new VectorCustom("vItemMap");
        MapScr.vMobAttack = new VectorCustom("vMobAttack");
        MapScr.vSet = new VectorCustom("vSet");
        MapScr.vMob = new VectorCustom("vMob");
        MapScr.vNpc = new VectorCustom("vNpc");
        MapScr.vFlag = new VectorCustom("vFlag");
        MapScr.vCharCloneInMap = new VectorCustom("vCharCloneInMap");
        MapScr.indexSize = 28;
        MapScr.indexTitle = 0;
        MapScr.indexSelect = 0;
        MapScr.indexRow = -1;
        MapScr.indexMenu = 0;
        MapScr.vItemUpGrade = new VectorCustom("vItemUpGrade");
        MapScr.isViewClanMemOnline = false;
        MapScr.isViewClanInvite = true;
        MapScr.titleInputText = "";
        MapScr.isPaintAlert = false;
        MapScr.isPaintTask = false;
        MapScr.isPaintTeam = false;
        MapScr.isPaintFindTeam = false;
        MapScr.isPaintFriend = false;
        MapScr.isPaintEnemies = false;
        MapScr.isPaintItemInfo = false;
        MapScr.isHaveSelectSkill = false;
        MapScr.isPaintSkill = false;
        MapScr.isPaintInfoMe = false;
        MapScr.isPaintStore = false;
        MapScr.isPaintNonNam = false;
        MapScr.isPaintNonNu = false;
        MapScr.isPaintAoNam = false;
        MapScr.isPaintAoNu = false;
        MapScr.isPaintGangTayNam = false;
        MapScr.isPaintGangTayNu = false;
        MapScr.isPaintQuanNam = false;
        MapScr.isPaintQuanNu = false;
        MapScr.isPaintGiayNam = false;
        MapScr.isPaintGiayNu = false;
        MapScr.isPaintLien = false;
        MapScr.isPaintNhan = false;
        MapScr.isPaintNgocBoi = false;
        MapScr.isPaintPhu = false;
        MapScr.isPaintWeapon = false;
        MapScr.isPaintStack = false;
        MapScr.isPaintStackLock = false;
        MapScr.isPaintGrocery = false;
        MapScr.isPaintGroceryLock = false;
        MapScr.isPaintUpGrade = false;
        MapScr.isPaintConvert = false;
        MapScr.isPaintUpGradeGold = false;
        MapScr.isPaintUpPearl = false;
        MapScr.isPaintBox = false;
        MapScr.isPaintSplit = false;
        MapScr.isPaintCharInMap = false;
        MapScr.isPaintTrade = false;
        MapScr.isPaintZone = false;
        MapScr.isPaintMessage = false;
        MapScr.isPaintClan = false;
        MapScr.isPaintButtonSkill = true;
        MapScr.isRequestMember = false;
        MapScr.typeViewInfo = 0;
        MapScr.typeActive = 0;
        MapScr.idBgSoundMap = 0;
        MapScr.isDisableUnfocus = false;
        MapScr.isAutoFocus = true;
        MapScr.isCheckUnFocus = false;
        MapScr.indexFirst = -1;
        MapScr.isPaint = true;
        MapScr.shock_x = new int[]{1, -1, 1, -1};
        MapScr.shock_y = new int[]{1, -1, -1, 1};
        MapScr.textTime = new VectorCustom("");
        MapScr.rainParticles = new ArrayList<RainParticle>();
        MapScr.rainEffects = new ArrayList<RainEffect>();
        MapScr.lightningBolts = new ArrayList<LightningBolt>();
        MapScr.leafFalls = new ArrayList<Object>();
        MapScr.textFly = new TextFly();
        MapScr.indexEff = 0;
        MapScr.notPaint = false;
        MapScr.isPing = false;
        MapScr.isHaveUnReadMsg = false;
        MapScr.isPaintBtnRevive = false;
    }

    public MapScr() {
        this.vRangeDmgSkills = new Vector<RangeDamageSkill>();
        this.isBigBang = false;
        this.speedBigBang = 20;
        this.vecCmds = new Vector<MyCommand>();
        this.idTypeTask = 0;
        this.isChangeSkill = false;
        this.tMenuDelay = 0;
        this.zoneCol = 6;
        this.typeTrade = 0;
        this.typeTradeOrder = 0;
        this.coinTrade = 0;
        this.coinTradeOrder = 0;
        this.timeTrade = 0;
        this.indexItemUse = -1;
        this.cLastFocusID = -1;
        this.cPreFocusID = -1;
        this.tradeName = "";
        this.tradeItemName = "";
        this.timeBuffs = new int[][]{{-1, 5000, -1, 5000, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}};
        this.speeds = new int[][]{{0, 0, 0, 0, 20, 0}, {0, 0, 0, 0, 20, 0}, {0, 0, 0, 0, 20, 0}, {0, 0, 0, 0, 20, 0}, {0, 0, 0, 0, 20, 0}};
        this.idEffects = new int[][]{{-1, 23, 1, 0, 5, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}};
        this.maxFights = new int[][]{{1, 1, 1, 5, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}};
        this.idMoreEffects = new int[][]{{-1, -1, 2, -1, 4, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}};
        this.idEffectFrames = new int[][]{{-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}};
        this.idTargets = new int[][]{{-1, 1, -1, -1, 6, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1}};
        this.typeSkills = new int[][]{{1, 1, 4, 2, 7, 6}, {1, 2, 4, 2, 7, 6}, {1, 2, 4, 2, 7, 6}, {1, 2, 4, 2, 7, 6}, {1, 2, 4, 2, 7, 6}};
        this.disSkills = new int[][]{{96, 320, 320, 320, 320, 320}, {96, 320, 128, 320, 320, 320}, {96, 320, 128, 320, 320, 320}, {96, 320, 128, 320, 320, 320}, {96, 320, 128, 320, 320, 320}};
        this.fontPaintLevelInfoMe = mFont.fontPaintLevelInfoMe;
        this.hpFocus = "";
        this.fontPaintNameFocus = mFont.tahoma_7_white_border_black;
        this.fontPaintHpFocus = mFont.tahoma_7_white_border_black_medium_clone;
        this.vecBtnTest = new Vector<MyButton>();
        this.cmdStart = 1999;
        this.colorBtn = 0;
        this.maxColor = LoadDataManager.myButtons.length - 1;
        this.idWeapon = 0;
        this.testEff = 0;
        this.isClickTest = false;
        this.lightningFlashCounter = 0;
        this.isWhiteFlash = false;
        this.yPaint = 0;
        this.Hitem = 30;
        this.maxSizeRow = 5;
        this.isTranKyNang = 0;
        this.isTran = false;
        this.margin = 5;
        this.isTransSkill = false;
        this.speedTransSkill = 15;
        this.isFocusMsg = false;
        this.timeUp = mSystem.currentTimeMillis() + 10000L;
        this.hCoolDown = LoadDataManager.coolDownBuffEffect.getRHeight();
        this.isExtendChat = false;
        this.arrHPMPSP = new int[]{0, 1, 2, 1};
        this.xOffsetHpMpSp = new int[3];
        this.xPosThanhTrang = new int[]{(int) ((1.0f - Player.myCharz().cHP * 1.0f / Player.myCharz().cHPFull) * LoadDataManager.imgHpMpSpInfoMe[0].getRWidth()), (int) ((1.0f - Player.myCharz().cMP * 1.0f / Player.myCharz().cMPFull) * LoadDataManager.imgHpMpSpInfoMe[1].getRWidth()), (int) ((1.0f - Player.myCharz().cSP * 1.0f / Player.myCharz().cSPFull) * LoadDataManager.imgHpMpSpInfoMe[2].getRWidth())};
        this.lastTimeUpdateHpMpSp = new long[3];
        this.fontPaint = mFont.tahoma_7;
        byte b;
        if (Rms.loadRMSInt(Rms.RMS_CONTROL) == -1) {
            b = 1;
        } else {
            b = (byte) Rms.loadRMSInt(Rms.RMS_CONTROL);
        }
        MapScr.typeControl = b;
        MapScr.typeControl = 1;
        if (CanvasNinja.w == 128 || CanvasNinja.h <= 208) {
            MapScr.indexSize = 20;
        }
        if (CanvasNinja.isTouch) {
            MapScr.isHaveSelectSkill = true;
        }
    }

    private void addBtn(final String s) {
        final mFont tahoma_7b_white = mFont.tahoma_7b_white;
        final FrameImage frameImage = LoadDataManager.myButtons[this.colorBtn];
        this.vecBtnTest.add(new MyButton(tahoma_7b_white, frameImage, frameImage, 70, 2, s, 0, 0, new MyCommand("", this.cmdStart, this)));
        ++this.cmdStart;
        final int colorBtn = this.colorBtn + 1;
        this.colorBtn = colorBtn;
        if (colorBtn > this.maxColor) {
            this.colorBtn = 0;
        }
    }

    public static void addEffect(final short[] array, final Player player, final Skill skill, final IMapObject mapObject, final int n, final byte b, final int n2) {
        final EffectSkill effectSkill = new EffectSkill(player, skill, mapObject, n, b, n2);
        effectSkill.loadEffectFromTool(array);
        MapScr.vEffectSkillWaiting.add(effectSkill);
    }

    public static void addEffectClearClone(final int n, final int n2) {
        MapScr.vecEffectLast.addElement(new EffectShield(2, n, n2, true, null));
    }

    public static void addEffectFrame(final short n, final Player player, final Skill skill, final byte b, final int n2, final int n3, final int n4, final int n5, final boolean b2) {
        MapScr.vEffectSkillWaiting.add(new EffectSkillFrameNoTarget(player, n, n3, b2, n4, n5, n2));
    }

    public static void addEffectMoreSkill(final Player player, final Skill skill, final IMapObject mapObject, final int n, final int n2, final int n3) {
        MapScr.vEffectMoreSkill.add(new EffectSkillMoveToByFrame(player, skill, mapObject, player.cdir, n, n2, n3));
    }

    public static void addEffectTarget(final short n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final Player[] array, final int[] array2) {
        final EffectSkillMoveToByTool effectSkillMoveToByTool = new EffectSkillMoveToByTool(n2, n3, n4, n5, (byte) 0, 0, n6, n7, n8, array, array2);
        effectSkillMoveToByTool.loadEffectFromTool(new short[]{n});
        MapScr.vEffectSkillWaiting.add(effectSkillMoveToByTool);
    }

    public static void addMascot(final Player player, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final Skill skill) {
        player.createMascotTemp(n, n2, n3, n4, n5, n6, n7, n8, n9, skill);
        MapScr.vecEffectLast.addElement(new EffectShield(6, player, n5, n6, true));
    }

    public static void addNotifyServer(final String s) {
        MapScr.vNotifyServers.add(new NotifyServer(s));
    }

    private void checkClick() {
        if (CanvasNinja.isPointerFirstClick || CanvasNinja.draggedXY()) {
            Player.myCharz().checkClickMoveTo(CanvasNinja.px + MapScr.cmx, CanvasNinja.py + MapScr.cmy, true);
            CanvasNinja.isPointerFirstClick = false;
            CanvasNinja.isPointerDraggedX = false;
            CanvasNinja.isPointerDraggedY = false;
        }
    }

    private boolean checkSingleClickEarly() {
        final int px = CanvasNinja.px;
        final int cmx = MapScr.cmx;
        final int py = CanvasNinja.py;
        final int cmy = MapScr.cmy;
        Player.myCharz().cancelAttack();
        final IMapObject objectMap = findObjectMap(px + cmx, py + cmy);
        if (objectMap == null) {
            return false;
        }
        if (objectMap == Player.myCharz().charFocus) {
            MapScr.cmdChatPlayer.perform();
            return true;
        }
        MapScr.isAutoFocus = false;
        if (Player.myCharz().itemFocus == objectMap) {
            MapScr.cmdChatItem.perform();
            return true;
        }
        if (Player.myCharz().isAttacPlayerStatus() && Player.myCharz().charFocus != null && !objectMap.equals(Player.myCharz().charFocus) && objectMap instanceof Player) {
            final Player player = (Player) objectMap;
        }
        if (Player.myCharz().npcFocus == objectMap) {
            MapScr.cmdChatNpc.perform();
            CanvasNinja.clearAllPointer();
            return true;
        }
        if (Player.myCharz().bgItemFocus == objectMap) {
            if (Player.myCharz().bgItemFocus.idImage == 2000) {
                MapScr.cmdChatBgItem.perform();
            }
            CanvasNinja.clearAllPointer();
            return true;
        }
        if (Player.myCharz().mobFocus == objectMap && Player.myCharz().mobFocus.isCanAttack(true)) {
            Player.myCharz().doFire(MapScr.myButtonSkills.elementAt(0).skill);
            return true;
        }
        Player.myCharz().focusManualTo(objectMap, false);
        objectMap.stopMoving();
        return true;
    }

    public static void createBigBang(final int n, final int n2, final int n3) {
        final EffectBigBang effectBigBang = new EffectBigBang();
        effectBigBang.createBigBang(n, n2, n3);
        MapScr.vEffectBigBang.add(effectBigBang);
    }

    private void doFire() {
    }

    private void doFire(final boolean b, final boolean b2) {
        this.doFire();
    }

    public static Player findCharInMap(final int n) {
        for (int i = 0; i < MapScr.vCharInMap.size(); ++i) {
            final Player player = (Player) MapScr.vCharInMap.elementAt(i);
            if (player.charID == n) {
                return player;
            }
        }
        return null;
    }

    public static Player findCharInMap(final String s) {
        for (int i = 0; i < MapScr.vCharInMap.size(); ++i) {
            final Player player = (Player) MapScr.vCharInMap.elementAt(i);
            if (player.cName.equals(s)) {
                return player;
            }
        }
        return null;
    }

    public static VectorCustom findListBoss() {
        final VectorCustom vectorCustom = new VectorCustom();
        for (int i = 0; i < MapScr.vMob.size(); ++i) {
            final IMapObject mapObject = (IMapObject) MapScr.vMob.elementAt(i);
            if (mapObject instanceof NewBoss) {
                vectorCustom.add(mapObject);
            }
        }
        return vectorCustom;
    }

    public static VectorCustom findListObjFocus(final int n, final int n2, final int n3, final int n4) {
        final VectorCustom vectorCustom = new VectorCustom();
        vectorCustom.addAll(MapScr.vItemMap);
        vectorCustom.addAll(MapScr.vNpc);
        vectorCustom.addAll(MapScr.vMob);
        vectorCustom.addAll(MapScr.vCharInMap);
        vectorCustom.addAll(MyTile.vCurrItem);
        final VectorCustom orderVectorIMapObject = Res.orderVectorIMapObject(vectorCustom);
        final VectorCustom vectorCustom2 = new VectorCustom();
        for (int i = 0; i < orderVectorIMapObject.size(); ++i) {
            final IMapObject mapObject = (IMapObject) orderVectorIMapObject.elementAt(i);
            if (mapObject != null) {
                if (!mapObject.isInvisible()) {
                    if (!(mapObject instanceof Player) || !((Player) mapObject).isMe()) {
                        if (!(mapObject instanceof ObjMap) || ((ObjMap) mapObject).isCanFocus()) {
                            final int x = mapObject.getX();
                            final int y = mapObject.getY();
                            final int w = mapObject.getW();
                            final int h = mapObject.getH();
                            if (inRectangle(n, n2, x - w / 2 - n3, y - h - n4, w + n3 * 2, h + n4 * 2)) {
                                vectorCustom2.add(mapObject);
                            }
                        }
                    }
                }
            }
        }
        return vectorCustom2;
    }

    public static VectorCustom findListObjFocus2(final int n, final int n2, final int n3, final int n4) {
        final VectorCustom vectorCustom = new VectorCustom();
        vectorCustom.addAll(MapScr.vItemMap);
        vectorCustom.addAll(MapScr.vNpc);
        vectorCustom.addAll(findListBoss());
        vectorCustom.addAll(MapScr.vCharInMap);
        vectorCustom.addAll(MyTile.vCurrItem);
        final VectorCustom orderVectorIMapObject = Res.orderVectorIMapObject(vectorCustom);
        final VectorCustom vectorCustom2 = new VectorCustom();
        for (int i = 0; i < orderVectorIMapObject.size(); ++i) {
            final IMapObject mapObject = (IMapObject) orderVectorIMapObject.elementAt(i);
            if (!mapObject.isInvisible()) {
                if (!(mapObject instanceof Player) || !((Player) mapObject).isMe()) {
                    if (!(mapObject instanceof ObjMap) || ((ObjMap) mapObject).isCanFocus()) {
                        final int x = mapObject.getX();
                        final int y = mapObject.getY();
                        final int w = mapObject.getW();
                        final int h = mapObject.getH();
                        if (inRectangle(n, n2, x - w / 2 - n3, y - h - n4, w + n3 * 2, h + n4 * 2)) {
                            vectorCustom2.add(mapObject);
                        }
                    }
                }
            }
        }
        return vectorCustom2;
    }

    public static Quai findMobInMap(final int n) {
        for (int i = 0; i < MapScr.vMob.size(); ++i) {
            final Quai quai = (Quai) MapScr.vMob.elementAt(i);
            if (quai.mobId == n) {
                return quai;
            }
        }
        return null;
    }

    public static Quai findMobInMapById(final short n) {
        for (int i = 0; i < MapScr.vMob.size(); ++i) {
            final IMapObject mapObject = (IMapObject) MapScr.vMob.elementAt(i);
            if (mapObject instanceof Quai) {
                final Quai quai = (Quai) mapObject;
                if (quai.mobId == n) {
                    return quai;
                }
            }
        }
        return null;
    }

    public static Quai findMobInMapMap(final int n, final int n2, final int n3) {
        final VectorCustom vectorCustom = new VectorCustom();
        vectorCustom.addAll(MapScr.vMob);
        final IMapObject objectMap = findObjectMap(n, n2, n3, vectorCustom);
        Quai quai;
        if (objectMap != null) {
            quai = (Quai) objectMap;
        } else {
            quai = null;
        }
        return quai;
    }

    public static mNPC findNPCInMap(final short n) {
        for (int i = 0; i < MapScr.vNpc.size(); ++i) {
            final mNPC mnpc = (mNPC) MapScr.vNpc.elementAt(i);
            if (mnpc.template.npcTemplateId == n) {
                return mnpc;
            }
        }
        return null;
    }

    public static NewBoss findNewBossInMap(final short n) {
        for (int i = 0; i < MapScr.vMob.size(); ++i) {
            final IMapObject mapObject = (IMapObject) MapScr.vMob.elementAt(i);
            if (mapObject instanceof NewBoss) {
                final NewBoss newBoss = (NewBoss) mapObject;
                if (newBoss.mobId == n) {
                    return newBoss;
                }
            }
        }
        return null;
    }

    public static IMapObject findObjectMap(final int n, final int n2) {
        return findObjectMap(n, n2, 30);
    }

    public static IMapObject findObjectMap(final int n, final int n2, final int n3) {
        final VectorCustom vectorCustom = new VectorCustom();
        vectorCustom.addAll(MapScr.vItemMap);
        vectorCustom.addAll(MapScr.vNpc);
        vectorCustom.addAll(MapScr.vMob);
        vectorCustom.addAll(MapScr.vCharInMap);
        vectorCustom.addAll(MyTile.vCurrItem);
        return findObjectMap(n, n2, n3, vectorCustom);
    }

    public static IMapObject findObjectMap(final int n, final int n2, final int n3, final VectorCustom vectorCustom) {
        return findObjectMap(n, n2, n3, vectorCustom, false);
    }

    public static IMapObject findObjectMap(final int n, final int n2, final int n3, final VectorCustom vectorCustom, final boolean b) {
        final int size = vectorCustom.size();
        IMapObject mapObject = null;
        if (size > 0) {
            final IMapObject mapObject2 = (IMapObject) vectorCustom.get(0);
            int distance = Res.distance(n, n2, mapObject2.getX(), mapObject2.getY() - mapObject2.getH() / 2);
            int n4 = 0;
            int n5;
            for (int i = 0; i < vectorCustom.size(); ++i, distance = n5) {
                final IMapObject mapObject3 = (IMapObject) vectorCustom.get(i);
                final int distance2 = Res.distance(n, n2, mapObject3.getX(), mapObject3.getY() - mapObject3.getH() / 2);
                if (distance2 < (n5 = distance)) {
                    n5 = distance2;
                    n4 = i;
                }
            }
            if (distance < n3) {
                mapObject = (IMapObject) vectorCustom.get(n4);
            }
            return mapObject;
        }
        return null;
    }

    public static IMapObject findObjectMapOld(final int n, final int n2, final int n3, final VectorCustom vectorCustom, final boolean b) {
        if (vectorCustom.size() > 0) {
            for (int i = 0; i < vectorCustom.size(); ++i) {
                final IMapObject mapObject = (IMapObject) vectorCustom.elementAt(i);
                if (mapObject != null) {
                    if (!mapObject.isInvisible()) {
                        if (!(mapObject instanceof Player) || !((Player) mapObject).isMe()) {
                            if (!(mapObject instanceof ObjMap) || ((ObjMap) mapObject).isCanFocus()) {
                                final int x = mapObject.getX();
                                final int y = mapObject.getY();
                                final int w = mapObject.getW();
                                final int h = mapObject.getH();
                                if (inRectangle(n, n2, x - w / 2 - n3, y - h - n3, w + n3 * 2, h + n3 * 2)) {
                                    return mapObject;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static IMapObject findObjectMapOld(final int n, final int n2, final int n3, final boolean b) {
        final VectorCustom vectorCustom = new VectorCustom();
        vectorCustom.addAll(MapScr.vItemMap);
        vectorCustom.addAll(MapScr.vNpc);
        vectorCustom.addAll(MapScr.vMob);
        vectorCustom.addAll(MapScr.vCharInMap);
        vectorCustom.addAll(MyTile.vCurrItem);
        return findObjectMapOld(n, n2, n3, vectorCustom, b);
    }

    public static IMapObject findPlayerInMap(final int n, final int n2, final int n3) {
        final VectorCustom vectorCustom = new VectorCustom();
        vectorCustom.addAll(MapScr.vCharInMap);
        final IMapObject objectMap = findObjectMap(n, n2, n3, vectorCustom);
        Player player;
        if (objectMap != null) {
            player = (Player) objectMap;
        } else {
            player = null;
        }
        return player;
    }

    public static MapScr gI() {
        if (MapScr.instance == null) {
            MapScr.instance = new MapScr();
        }
        return MapScr.instance;
    }

    public static boolean inRectangle(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return n >= n3 && n <= n3 + n5 && n2 >= n4 && n2 <= n4 + n6;
    }

    public static final void loadCamera(final boolean b, int n, int gssye) {
        MapScr.gW = CanvasNinja.w;
        MapScr.cmdBarH = 39;
        final int n2 = MapScr.gH = CanvasNinja.h;
        MapScr.cmdBarW = MapScr.gW;
        MapScr.cmdBarX = 0;
        MapScr.cmdBarY = n2 - SupportPaint.hTab - MapScr.cmdBarH;
        MapScr.girlHPBarY = 0;
        final int n3 = MapScr.csPadMaxH = CanvasNinja.h / 6;
        final byte size = MyTile.size;
        if (n3 < size) {
            MapScr.csPadMaxH = size;
        }
        final int gw = MapScr.gW;
        MapScr.gW2 = gw >> 1;
        final int gh = MapScr.gH;
        MapScr.gH2 = gh >> 1;
        MapScr.gW3 = gw / 3;
        MapScr.gH3 = gh / 3;
        MapScr.gW23 = gh - 120;
        MapScr.gH23 = gh * 2 / 3;
        MapScr.gW34 = gw * 3 / 4;
        MapScr.gH34 = gh * 3 / 4;
        MapScr.gW6 = gw / 6;
        MapScr.gH6 = gh / 6;
        final int n4 = MapScr.gssw = gw / size + 2;
        MapScr.gssh = gh / size + 2;
        if (gw % size != 0) {
            MapScr.gssw = n4 + 1;
        }
        MapScr.cmxLim = (MyTile.tmw - 1) * MyTile.size - MapScr.gW;
        MapScr.cmyLim = MyTile.tmh * MyTile.size - MapScr.gH;
        if (n == -1 && gssye == -1) {
            n = (MapScr.cmx = (MapScr.cmtoX = Player.myCharz().cx - MapScr.gW2 + MapScr.gW6 * Player.myCharz().cdir));
            n = (MapScr.cmy = (MapScr.cmtoY = Player.myCharz().cy - MapScr.gH23 - 1));
        } else {
            n = (MapScr.cmx = (MapScr.cmtoX = n - MapScr.gW23 + MapScr.gW6 * Player.myCharz().cdir));
            n = (MapScr.cmy = (MapScr.cmtoY = gssye - MapScr.gH23 - 1));
        }
        MapScr.firstY = MapScr.cmy;
        gssye = MapScr.cmx;
        n = MyTile.size;
        if (gssye < n) {
            MapScr.cmtoX = n;
            MapScr.cmx = n;
        }
        n = MapScr.cmx;
        gssye = MapScr.cmxLim;
        if (n > gssye) {
            MapScr.cmtoX = gssye;
            MapScr.cmx = gssye;
        }
        if (MapScr.cmy < 0) {
            MapScr.cmtoY = 0;
            MapScr.cmy = 0;
        }
        gssye = MapScr.cmy;
        n = MapScr.cmyLim;
        if (gssye > n) {
            MapScr.cmtoY = n;
            MapScr.cmy = n;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("cmx:");
        sb.append(MapScr.cmx);
        Res.outz(1, sb.toString());
        gssye = MapScr.cmx;
        n = MyTile.size;
        gssye = gssye / n - 1;
        if ((MapScr.gssx = gssye) <= 0) {
            MapScr.gssx = 0;
        }
        gssye = (MapScr.gssy = MapScr.cmy / n);
        MapScr.gssxe = MapScr.gssx + MapScr.gssw;
        n = (MapScr.gssye = MapScr.gssh + gssye);
        if (gssye < 0) {
            MapScr.gssy = 0;
        }
        gssye = MyTile.tmh;
        if (n > gssye) {
            MapScr.gssye = gssye;
        }
        gssye = (MyTile.countx = (MapScr.gssxe - MapScr.gssx) * 4);
        n = MyTile.tmw;
        if (gssye > n) {
            MyTile.countx = n;
        }
        gssye = (MyTile.county = (MapScr.gssye - MapScr.gssy) * 4);
        n = MyTile.tmh;
        if (gssye > n) {
            MyTile.county = n;
        }
        n = (Player.myCharz().cx - MapScr.gW * 2) / MyTile.size;
        if ((MyTile.gssx = n) < 0) {
            MyTile.gssx = 0;
        }
        gssye = (MyTile.gssxe = MyTile.gssx + MyTile.countx);
        n = MyTile.tmw;
        if (gssye > n) {
            MyTile.gssxe = n;
        }
        n = (Player.myCharz().cy - MapScr.gH * 2) / MyTile.size;
        if ((MyTile.gssy = n) < 0) {
            MyTile.gssy = 0;
        }
        n = (MyTile.gssye = MyTile.gssy + MyTile.county);
        gssye = MyTile.tmh;
        if (n > gssye) {
            MyTile.gssye = gssye;
        }
        final boolean isTouch = CanvasNinja.isTouch;
        n = 40;
        if (isTouch) {
            MapScr.yTouchBar = MapScr.gH - 88;
            MapScr.xC = MapScr.gW - 40;
            MapScr.yC = 2;
            gssye = CanvasNinja.w;
            if (gssye <= 240) {
                MapScr.xC = MapScr.gW - 35;
                MapScr.yC = 5;
            }
            int gw2 = MapScr.gW;
            final int n5 = MapScr.xF = gw2 - 55;
            int yTouchBar = MapScr.yTouchBar;
            final int n6 = MapScr.yF = yTouchBar + 35;
            gw2 -= 37;
            MapScr.xTG = gw2;
            MapScr.yTG = --yTouchBar;
            if (gssye >= 450) {
                MapScr.yTG = yTouchBar - 12;
                MapScr.yHP -= 7;
                MapScr.xF = n5 - 10;
                MapScr.yF = n6 - 5;
                MapScr.xTG = gw2 - 10;
            }
        }
        if (CanvasNinja.w <= 200) {
            n = 30;
        }
        MapScr.disXC = n;
    }

    private void loadInforBar() {
        this.imgScrW = 84;
        MapScr.hpBarW = 66L;
        MapScr.mpBarW = 59;
        MapScr.hpBarX = 52;
        MapScr.hpBarY = 10;
        MapScr.spBarW = 61;
        MapScr.expBarW = MapScr.gW - 61;
    }

    public static void loadMapOfflineDefault() {
        try {
            MyTile.loadMapFromResource(-1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static final void loadSplash() {
        if (MapScr.imgSplash == null) {
            MapScr.imgSplash = new Image[3];
            for (int i = 0; i < 3; ++i) {
                final Image[] imgSplash = MapScr.imgSplash;
                final StringBuilder sb = new StringBuilder();
                sb.append("/splash/sp");
                sb.append(i);
                sb.append(".png");
                imgSplash[i] = Image.createImage(sb.toString());
            }
        }
        MapScr.splashX = new int[2];
        MapScr.splashY = new int[2];
        final int[] array = MapScr.splashState = new int[2];
        MapScr.splashF = new int[2];
        MapScr.splashDir = new int[2];
        array[0] = (array[1] = -1);
    }

    private void openInventory() {
        final String[] array = {"Sức mạnh: #", "Thân pháp: #", "Gân cốt: #", "Tinh thần: #", "Tinh thần: #", "Linh hoạt: #", "Độ bền: #", "Độ aaaaaaa: #", "Độ bền: #", "Độ bền: #", "Độ bền: #", "Độ bền: #"};
        ItemTemplates.itemTemplates.clear();
        final StringBuilder sb = new StringBuilder();
        sb.append("sizeOption= ");
        sb.append(10);
        Res.outz(sb.toString());
        for (int i = 0; i < 10; ++i) {
            final ItemOptionTemplate itemOptionTemplate = new ItemOptionTemplate();
            itemOptionTemplate.id = i;
            itemOptionTemplate.name = array[i];
            itemOptionTemplate.type = i;
            ItemOptionTemplates.add(itemOptionTemplate);
        }
        final TabInventory tabInventory = new TabInventory();
        tabInventory.vecItemInvens = new Vector<Item>();
        for (short n = (short) Player.myCharz().idPart.length, n2 = 0; n2 < n; ++n2) {
            final short n3 = Player.myCharz().idPart[n2];
            if (n3 == -32768) {
                tabInventory.vecItemUsedInvens.add(new Item(n2, n3));
            } else {
                final short n4 = (short) (n2 + 100);
                byte b;
                if (n2 < 5) {
                    b = (byte) n2;
                } else {
                    b = 0;
                }
                final Item item = new Item(n2, n3, (byte) 3, (byte) 3, n4, false, 1, "Vĩnh viễn", b);
                item.mItemOption = new mItemOption[3];
                for (int j = 0; j < 3; ++j) {
                    item.mItemOption[j] = new mItemOption();
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("Size Option used:");
                    sb2.append(2);
                    Res.outz(1, sb2.toString());
                    item.mItemOption[j].itemOption = new ItemOption[2];
                    for (int k = 0; k < 2; ++k) {
                        final short n5 = (short) k;
                        final int[] array2 = {0};
                        final byte[] array3 = {0};
                        for (int l = 0; l < 1; ++l) {
                            array3[l] = 1;
                            array2[l] = 100;
                        }
                        item.mItemOption[j].itemOption[k] = new ItemOption(n5, (byte) 0, (byte) 0, array2, array3);
                    }
                }
                tabInventory.vecItemUsedInvens.add(item);
            }
        }
        for (int n6 = 30, n7 = 0; n7 < n6; ++n7) {
            final short n8 = (short) n7;
            final short n9 = (short) (n7 + 100);
            byte b2;
            if (n7 < 5) {
                b2 = (byte) n7;
            } else {
                b2 = 0;
            }
            final Item item2 = new Item(n7, n8, (byte) 3, (byte) 3, n9, false, 1, "Vĩnh viễn", b2);
            item2.mItemOption = new mItemOption[3];
            int n10 = 0;
            for (int n11 = 0; n11 < 3; ++n11) {
                item2.mItemOption[n11] = new mItemOption();
                item2.mItemOption[n11].itemOption = new ItemOption[2];
                for (int n12 = 0; n12 < 2; ++n12) {
                    final short n13 = (short) n10;
                    final int[] array4 = {0};
                    final byte[] array5 = {0};
                    for (int n14 = 0; n14 < 1; ++n14) {
                        array5[n14] = 2;
                        array4[n14] = 10000;
                    }
                    item2.mItemOption[n11].itemOption[n12] = new ItemOption(n13, (byte) 0, (byte) 0, array4, array5);
                    ++n10;
                }
            }
            tabInventory.vecItemInvens.add(item2);
        }
        tabInventory.show();
    }

    public static void paintBuffEffects(final mGraphics mGraphics) {
        try {
            final Vector<BuffEffect> buffEffects = MapScr.buffEffects;
            if (buffEffects != null) {
                for (final BuffEffect buffEffect : buffEffects) {
                    if (buffEffect != null) {
                        buffEffect.paint(mGraphics);
                    }
                }
            }
        } catch (Exception ex) {
            Res.outz("RONG");
        }
    }

    private void paintFrameChat(final mGraphics mGraphics) {
        if (CanvasNinja.gameTick % 10 == 0) {
            this.isFocusMsg ^= true;
        }
        final boolean isExtendChat = this.isExtendChat;
        int n = 0;
        if (isExtendChat) {
            final int n2 = this.arrXFrameChat[0];
            final int n3 = this.arrYFrameChat[0];
            final int n4 = this.arrWFrameChat[0];
            final int n5 = 60;
            mGraphics.setClip(n2, n3 - 61, n4, 60);
            CanvasNinja.paintz.paintSingleBorderFrame(LoadDataManager.frameBgFunction, (float) this.arrXFrameChat[0], (float) (this.arrYFrameChat[0] - 61), (float) this.arrWFrameChat[0], 60.0f, 100, true, mGraphics);
            final Vector<ChatBox.Message> last3Msg = ChatBox.allMessages.get("0");
            if ((this.last3Msg = last3Msg) != null) {
                int i;
                for (int size = last3Msg.size(), n6 = i = Math.max(0, size - 3); i < size; ++i) {
                    final ChatBox.Message message = this.last3Msg.get(i);
                    final int n7 = this.arrXFrameChat[n];
                    final int n8 = this.arrYFrameChat[n];
                    final StringBuilder sb = new StringBuilder();
                    sb.append(message.username);
                    sb.append(": ");
                    sb.append(message.content);
                    this.paintTypeChat(mGraphics, n7 + 5, n8 - n5 + (i - n6) * 20, (byte) 0, sb.toString());
                }
            } else {
                n = 0;
            }
            final FrameImage frameInputChat2 = LoadDataManager.frameInputChat2;
            final float n9 = (float) this.arrXFrameChat[3];
            final float n10 = (float) (this.arrYFrameChat[3] - 61);
            int n11;
            if (this.isOpacity) {
                n11 = 30;
            } else {
                n11 = 50;
            }
            frameInputChat2.drawFrame(0, n9, n10, 0, mGraphics, true, n11);
            this.cmdShowDetailChat.setPosPaint(this.arrXFrameChat[2], this.arrYFrameChat[2] - 59, LoadDataManager.imgIconHideDetailChat);
            this.cmdShowDetailChat.paint(mGraphics);
        } else {
            final int n12 = 0;
            if (MapScr.isHaveUnReadMsg) {
                final SupportPaint paintz = CanvasNinja.paintz;
                final FrameImage frameUnread = LoadDataManager.frameUnread;
                paintz.paintTagFrame(mGraphics, frameUnread, this.arrXFrameChat[n12], this.arrYFrameChat[n12] - this.margin - (int) frameUnread.frameHeight, (int) frameUnread.frameWidth, this.isFocusMsg, true);
            }
            final FrameImage frameInputChat3 = LoadDataManager.frameInputChat2;
            final float n13 = (float) this.arrXFrameChat[3];
            final float n14 = (float) this.arrYFrameChat[3];
            int n15;
            if (this.isOpacity) {
                n15 = 30;
            } else {
                n15 = 50;
            }
            frameInputChat3.drawFrame(0, n13, n14, 0, mGraphics, true, n15);
            this.cmdShowDetailChat.paint(mGraphics);
            n = n12;
        }
        CanvasNinja.resetClip(mGraphics);
        final SupportPaint paintz2 = CanvasNinja.paintz;
        final FrameImage frameInputChat4 = LoadDataManager.frameInputChat1;
        final int n16 = this.arrXFrameChat[n];
        final int n17 = this.arrYFrameChat[n];
        final int n18 = this.arrWFrameChat[n];
        int n19;
        if (this.isOpacity) {
            n19 = 30;
        } else {
            n19 = 50;
        }
        paintz2.paintTagFrame(mGraphics, frameInputChat4, n16, n17, n18, false, true, n19, true);
        this.tfChat.painTextInputNoneBackGround(mGraphics);
        this.cmdSendChat.paintIconOnly(mGraphics);
    }

    private void paintInfoServer(final mGraphics mGraphics) {
        final InfoServer infoServer = this.infoServer;
        if (infoServer != null) {
            infoServer.paint(mGraphics, Player.myCharz().cx, Player.myCharz().cy, Player.myCharz().cw, Player.myCharz().ch, Player.myCharz().cdir);
        }
    }

    private void paintItems(final mGraphics mGraphics) {
        for (int i = 0; i < MapScr.vItemMap.size(); ++i) {
            ((ItemInMap) MapScr.vItemMap.elementAt(i)).paint(mGraphics);
        }
    }

    private void paintMiniMap(final mGraphics mGraphics) {
        final ObjScr objScr = CanvasNinja.objScr;
        if (objScr != null && objScr instanceof MiniMap) {
            return;
        }
        if (CanvasNinja.gameTick % 3 == 0 && ++this.xAnimFocusMiniMap > 1) {
            this.xAnimFocusMiniMap = 0;
        }
        final mFont fontPaintNameFocus = this.fontPaintNameFocus;
        final StringBuilder sb = new StringBuilder();
        sb.append(MyTile.mapName);
        sb.append(" ");
        sb.append(MyTile.zoneID + 1);
        final String string = sb.toString();
        final int w = CanvasNinja.w;
        final int padding_TAI_THO = NinjaMidlet.PADDING_TAI_THO;
        final int margin = this.margin;
        fontPaintNameFocus.drawString(mGraphics, string, w - (padding_TAI_THO + margin), margin * 3, 1);
        final Image imgFocusMe = LoadDataManager.imgFocusMe;
        final int w2 = CanvasNinja.w;
        final int padding_TAI_THO2 = NinjaMidlet.PADDING_TAI_THO;
        final int margin2 = this.margin;
        final mFont fontPaintNameFocus2 = this.fontPaintNameFocus;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(MyTile.mapName);
        sb2.append(" ");
        sb2.append(MyTile.zoneID + 1);
        mGraphics.drawImage(imgFocusMe, (float) (w2 - (padding_TAI_THO2 + margin2) - fontPaintNameFocus2.getWidth(sb2.toString()) - 5 - LoadDataManager.imgFocusMe.getRWidth() + this.xAnimFocusMiniMap), (float) (this.margin * 3 + (this.fontPaintNameFocus.getHeight() - LoadDataManager.imgFocusMe.getRHeight()) / 2));
    }

    private void paintMob(final mGraphics mGraphics) {
        for (int i = 0; i < MapScr.vMob.size(); ++i) {
            final IMapObject mapObject = (IMapObject) MapScr.vMob.elementAt(i);
            if (mapObject instanceof Quai) {
                ((Quai) mapObject).paint(mGraphics);
            }
        }
    }

    public static void paintMyButtonSkill(final mGraphics mGraphics) {
        final Vector<MyButtonSkill> myButtonSkills = MapScr.myButtonSkills;
        if (myButtonSkills != null) {
            final Iterator<MyButtonSkill> iterator = myButtonSkills.iterator();
            while (iterator.hasNext()) {
                iterator.next().paint(mGraphics);
            }
        }
    }

    public static void paintMyButtonSkillOpacity(final mGraphics mGraphics) {
        final Vector<MyButtonSkill> myButtonSkills = MapScr.myButtonSkills;
        if (myButtonSkills != null) {
            final Iterator<MyButtonSkill> iterator = myButtonSkills.iterator();
            while (iterator.hasNext()) {
                iterator.next().paintOpacity(mGraphics);
            }
        }
    }

    private void paintNpc(final mGraphics mGraphics) {
        for (int i = 0; i < MapScr.vNpc.size(); ++i) {
            final mNPC mnpc = (mNPC) MapScr.vNpc.elementAt(i);
            if (mnpc.cHP > 0) {
                mnpc.paintShadow(mGraphics);
            }
        }
        for (int j = 0; j < MapScr.vNpc.size(); ++j) {
            ((mNPC) MapScr.vNpc.elementAt(j)).paint(mGraphics);
        }
    }

    private void paintObjInMap(final mGraphics mGraphics) {
        for (int i = 0; i < MapScr.vObjEffectMap.size(); ++i) {
            MapScr.vObjEffectMap.elementAt(i).paint(mGraphics);
        }
    }

    private void paintPlayer(final mGraphics mGraphics) {
        for (int i = 0; i < MapScr.vCharCloneInMap.size(); ++i) {
            ((Player) MapScr.vCharCloneInMap.elementAt(i)).paintEffectBefore(mGraphics);
        }
        for (int j = 0; j < MapScr.vCharInMap.size(); ++j) {
            final Player player = (Player) MapScr.vCharInMap.elementAt(j);
            if (!player.isMe()) {
                player.paintEffectBefore(mGraphics);
            }
        }
        for (int k = 0; k < MapScr.vCharCloneInMap.size(); ++k) {
            ((Player) MapScr.vCharCloneInMap.elementAt(k)).paint(mGraphics);
        }
        for (int l = 0; l < MapScr.vCharInMap.size(); ++l) {
            final Player player2 = (Player) MapScr.vCharInMap.elementAt(l);
            if (!player2.isMe()) {
                player2.paint(mGraphics);
            }
        }
        for (int n = 0; n < MapScr.vCharCloneInMap.size(); ++n) {
            ((Player) MapScr.vCharCloneInMap.elementAt(n)).paintEffectAfter(mGraphics);
        }
        for (int n2 = 0; n2 < MapScr.vCharInMap.size(); ++n2) {
            final Player player3 = (Player) MapScr.vCharInMap.elementAt(n2);
            if (!player3.isMe()) {
                player3.paintEffectAfter(mGraphics);
            }
        }
    }

    private void paintPlayerSharingan(final mGraphics mGraphics) {
        Player.myCharz().paintShadowSharigan(mGraphics);
    }

    private void paintPopupMob(final mGraphics mGraphics) {
        for (int i = 0; i < MapScr.vMob.size(); ++i) {
            ((Quai) MapScr.vMob.elementAt(i)).paintPopup(mGraphics);
        }
    }

    private void paintPopupNPC(final mGraphics mGraphics) {
        for (int i = 0; i < MapScr.vNpc.size(); ++i) {
            ((mNPC) MapScr.vNpc.elementAt(i)).paintPopup(mGraphics);
        }
    }

    private void paintPopupPlayer(final mGraphics mGraphics) {
        for (int i = 0; i < MapScr.vCharInMap.size(); ++i) {
            final Player player = (Player) MapScr.vCharInMap.elementAt(i);
            if (!player.isMe()) {
                player.paintPopup(mGraphics);
            }
        }
        Player.myCharz().paintEffectBefore(mGraphics);
        Player.myCharz().paint(mGraphics);
        Player.myCharz().paintEffectAfter(mGraphics);
        Player.myCharz().paintPopup(mGraphics);
    }

    private void paintSoundMap(final mGraphics mGraphics) {
        final Iterator<SoundMap> iterator = MapScr.vecSoundMaps.iterator();
        while (iterator.hasNext()) {
            iterator.next().paint(mGraphics);
        }
    }

    public static final void paintSplash(final mGraphics mGraphics) {
        if (MapScr.splashState == null) {
            return;
        }
        for (int i = 0; i < 2; ++i) {
            if (MapScr.splashState[i] != -1) {
                if (MapScr.splashDir[i] == 1) {
                    mGraphics.drawImage(MapScr.imgSplash[MapScr.splashF[i]], (float) MapScr.splashX[i], (float) MapScr.splashY[i], 3);
                } else {
                    final Image image = MapScr.imgSplash[MapScr.splashF[i]];
                    mGraphics.drawRegion(image, 0.0f, 0.0f, (float) mGraphics.getImageWidth(image), (float) mGraphics.getImageHeight(MapScr.imgSplash[MapScr.splashF[i]]), 2, (float) MapScr.splashX[i], (float) MapScr.splashY[i], 3);
                }
            }
        }
    }

    private void paintTypeChat(final mGraphics mGraphics, final int n, final int n2, final byte b, final String s) {
        final String s2 = ChatBox.TypeStrings[b];
        final int width = mFont.tahoma_7_white.getWidth(s2);
        CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameTypeChat[b], n, n2, width + 10, false);
        mFont.tahoma_7_white.drawString(mGraphics, s2, n + 5, n2 + 2, 0, true);
        mFont.tahoma_7_white.drawString(mGraphics, s, n + width + 15, n2 + 2, 0, true);
    }

    public static void playerJoinMap(final Player player) {
        for (int i = 0; i < MapScr.vCharInMap.size(); ++i) {
            if (((Player) MapScr.vCharInMap.elementAt(i)).charID == player.charID) {
                MapScr.vCharInMap.remove(i);
                break;
            }
        }
        MapScr.vCharInMap.addElement(player);
    }

    public static void removeCharInMap(final int n) {
        for (int i = 0; i < MapScr.vCharInMap.size(); ++i) {
            final Player player = (Player) MapScr.vCharInMap.elementAt(i);
            if (player.charID == n) {
                MapScr.vCharInMap.remove(player);
                break;
            }
        }
    }

    public static void removeNPCInMap(final short n) {
        for (int i = 0; i < MapScr.vNpc.size(); ++i) {
            final mNPC mnpc = (mNPC) MapScr.vNpc.elementAt(i);
            if (mnpc.template.npcTemplateId == n) {
                addEffectClearClone(mnpc.cx, mnpc.cy);
                MapScr.vNpc.removeElementAt(i);
                break;
            }
        }
    }

    public static void removeStatusAllNpc() {
        for (int i = 0; i < MapScr.vNpc.size(); ++i) {
            ((mNPC) MapScr.vNpc.elementAt(i)).setTypeStatus(-1);
        }
    }

    public static void resetAllvector() {
        MapScr.vItemMap.removeAllElements();
        MapScr.vMobAttack.removeAllElements();
        MapScr.vMob.removeAllElements();
        MapScr.vNpc.removeAllElements();
        Player.myCharz().vMovePoints.removeAllElements();
    }

    public static void resetColorNameChar() {
        for (int i = 0; i < MapScr.vCharInMap.size(); ++i) {
            ((Player) MapScr.vCharInMap.elementAt(i)).updateColorName(-1);
        }
    }

    public static void resetTranslate(final mGraphics mGraphics) {
        mGraphics.translate(-mGraphics.getTranslateX(), -mGraphics.getTranslateY());
        mGraphics.setClip(0, -200, CanvasNinja.w, CanvasNinja.h + 200);
    }

    public static void searchFocusToAtk() {
        if (Player.myCharz().mobFocus != null && !Player.myCharz().mobFocus.isCanAttack(true)) {
            Player.myCharz().mobFocus = null;
        }
        if (!Player.myCharz().isCanAttackPlayer()) {
            if (Player.myCharz().mobFocus == null) {
                final Quai mobInMapMap = findMobInMapMap(Player.myCharz().cx, Player.myCharz().cy, CanvasNinja.w / 2);
                if (mobInMapMap != null) {
                    Player.myCharz().focusManualTo(mobInMapMap);
                }
            }
        } else if (Player.myCharz().mobFocus == null && Player.myCharz().charFocus == null) {
            final int n = CanvasNinja.w / 2;
            final Quai mobInMapMap2 = findMobInMapMap(Player.myCharz().cx, Player.myCharz().cy, n);
            if (mobInMapMap2 != null) {
                Player.myCharz().focusManualTo(mobInMapMap2);
            }
            if (Player.myCharz().mobFocus == null) {
                final IMapObject playerInMap = findPlayerInMap(Player.myCharz().cx, Player.myCharz().cy, n);
                if (playerInMap != null) {
                    Player.myCharz().focusManualTo(playerInMap);
                }
            }
        }
        if (Player.myCharz().mobFocus != null && Player.myCharz().cy != Player.myCharz().mobFocus.y) {
            Player.myCharz().mobFocus = null;
        }
    }

    public static final void startFlyText(final String s, final int n, final int n2, final int n3, final int n4, final int n5) {
        MapScr.textFly.startFlyText(s, n, n2, n3, n4, n5);
    }

    public static final boolean startSplash(final int n, final int n2, final int n3) {
        final int[] splashState = MapScr.splashState;
        int n4;
        if (splashState[0] == -1) {
            n4 = 0;
        } else {
            n4 = 1;
        }
        if (splashState[n4] != -1) {
            return false;
        }
        splashState[n4] = 0;
        MapScr.splashDir[n4] = n3;
        MapScr.splashX[n4] = n;
        MapScr.splashY[n4] = n2;
        return true;
    }

    public static final void updateCamera() {
        final int cmx = MapScr.cmx;
        final int cmtoX = MapScr.cmtoX;
        if (cmx != cmtoX || MapScr.cmy != MapScr.cmtoY) {
            final int n = MapScr.cmvx = cmtoX - cmx << 2;
            final int cmtoY = MapScr.cmtoY;
            final int cmy = MapScr.cmy;
            final int n2 = MapScr.cmvy = cmtoY - cmy << 2;
            final int n3 = MapScr.cmdx += n;
            final int n4 = MapScr.cmx = cmx + (n3 >> 4);
            MapScr.cmdx = (n3 & 0xF);
            final int n5 = MapScr.cmdy += n2;
            MapScr.cmy = cmy + (n5 >> 4);
            MapScr.cmdy = (n5 & 0xF);
            final byte size = MyTile.size;
            if (n4 < size) {
                MapScr.cmx = size;
            }
            final int cmx2 = MapScr.cmx;
            final int cmxLim = MapScr.cmxLim;
            if (cmx2 > cmxLim) {
                MapScr.cmx = cmxLim;
            }
            if (MapScr.cmy < 0) {
                MapScr.cmy = 0;
            }
            final int cmy2 = MapScr.cmy;
            final int cmyLim = MapScr.cmyLim;
            if (cmy2 > cmyLim) {
                MapScr.cmy = cmyLim;
            }
        }
        final int cmx3 = MapScr.cmx;
        final byte size2 = MyTile.size;
        if ((MapScr.gssx = cmx3 / size2 - 1) < 0) {
            MapScr.gssx = 0;
        }
        final int n6 = MapScr.gssy = MapScr.cmy / size2;
        MapScr.gssxe = MapScr.gssx + MapScr.gssw;
        final int n7 = MapScr.gssye = MapScr.gssh + n6;
        if (n6 < 0) {
            MapScr.gssy = 0;
        }
        final int tmh = MyTile.tmh;
        if (n7 > tmh) {
            MapScr.gssye = tmh;
        }
        if ((MyTile.gssx = (Player.myCharz().cx - MapScr.gW * 2) / MyTile.size) < 0) {
            MyTile.gssx = 0;
        }
        final int n8 = MyTile.gssxe = MyTile.gssx + MyTile.countx;
        final int tmw = MyTile.tmw;
        if (n8 > tmw) {
            MyTile.gssxe = tmw;
            MyTile.gssx = tmw - MyTile.countx;
        }
        if ((MyTile.gssy = (Player.myCharz().cy - MapScr.gH * 2) / MyTile.size) < 0) {
            MyTile.gssy = 0;
        }
        final int n9 = MyTile.gssye = MyTile.gssy + MyTile.county;
        final int tmh2 = MyTile.tmh;
        if (n9 > tmh2) {
            MyTile.gssye = tmh2;
            MyTile.gssy = tmh2 - MyTile.county;
        }
    }

    public static final void updateFlyText() {
        MapScr.textFly.update();
    }

    private void updateInfoServer() {
        final InfoServer infoServer = this.infoServer;
        if (infoServer != null) {
            infoServer.update();
            if (this.infoServer.isDone) {
                this.infoServer = null;
            }
        }
    }

    private boolean updateMiniMapPointer() {
        if (CanvasNinja.menuMain != null && CanvasNinja.objScr instanceof MiniMap) {
            return false;
        }
        if (CanvasNinja.isPointerRelease() && CanvasNinja.isPoint(CanvasNinja.w - (NinjaMidlet.PADDING_TAI_THO + this.margin) - this.fontPaintNameFocus.getWidth(MyTile.mapName) - 5 - LoadDataManager.imgFocusMe.getRWidth() + this.xAnimFocusMiniMap, this.margin * 3, LoadDataManager.imgFocusMe.getRWidth() + this.margin + this.fontPaintNameFocus.getWidth(MyTile.mapName), this.fontPaintNameFocus.getHeight() + 10)) {
            CanvasNinja.clearAllPointer();
            this.startMiniMapDefault();
            return true;
        }
        return false;
    }

    private void updatePointFind() {
        final PointFind pointFind = this.pointFind;
        if (pointFind != null) {
            pointFind.update();
            final PointFind pointFind2 = this.pointFind;
            if (pointFind2.isDone) {
                if (pointFind2.getContentDone() != null) {
                    this.startInfoServer(this.pointFind.getContentDone(), 3);
                }
                this.pointFind.isDone = false;
                this.pointFind = null;
            }
        }
    }

    private void updatePopupAutoPointer() {
        if (CanvasNinja.isPointerRelease() && CanvasNinja.isPoint(this.arrXFrameChat[0] + this.arrWFrameChat[0] - LoadDataManager.imgBgPK.getRWidth() - 40, this.arrYFrameChat[0] - this.margin - LoadDataManager.imgBgPK.getRHeight(), LoadDataManager.imgBgPK.getRWidth(), LoadDataManager.imgBgPK.getRHeight())) {
            CanvasNinja.clearAllPointer();
            if (Player.myCharz().isAutoAtk) {
                SendMessage.gI().turnOffAuto();
            } else {
                SendMessage.gI().turnOnAuto();
            }
        }
    }

    private void updateSoundMap() {
        final Iterator<SoundMap> iterator = MapScr.vecSoundMaps.iterator();
        while (iterator.hasNext()) {
            iterator.next().update();
        }
    }

    public static final void updateSplash() {
        if (MapScr.splashState == null) {
            return;
        }
        for (int i = 0; i < 2; ++i) {
            final int[] splashState = MapScr.splashState;
            final int n = splashState[i];
            if (n != -1) {
                splashState[i] = n + 1;
                final int[] splashX = MapScr.splashX;
                splashX[i] += MapScr.splashDir[i] << 2;
                final int[] splashY = MapScr.splashY;
                --splashY[i];
                final int n2 = splashState[i];
                if (n2 >= 6) {
                    splashState[i] = -1;
                } else {
                    MapScr.splashF[i] = (n2 >> 1) % 3;
                }
            }
        }
    }

    private void updateTutorial() {
        final Tutorial tutorial = this.tutorial;
        if (tutorial != null) {
            tutorial.update();
            if (this.tutorial.isDone) {
                this.stopTutorial();
            }
        }
    }

    public void autoFocus() {
        final int n = MyTile.size + 16;
        if (MapScr.isAutoFocus) {
            MapScr.vecFocus.removeAllElements();
            MapScr.vecFocus = findListObjFocus(Player.myCharz().cx, Player.myCharz().cy, n, n);
            final IMapObject objectMapOld = findObjectMapOld(Player.myCharz().cx, Player.myCharz().cy, n, true);
            if ((Player.myCharz().mobFocus == null || objectMapOld instanceof mNPC || Res.distance(Player.myCharz().cx, Player.myCharz().mobFocus.x) > CanvasNinja.w / 2 || Res.distance(Player.myCharz().cy, Player.myCharz().mobFocus.y) > CanvasNinja.h / 2) && objectMapOld != null) {
                Player.myCharz().focusManualTo(objectMapOld);
            }
        }
        MapScr.vecFocus.removeAllElements();
        MapScr.vecFocus = findListObjFocus2(Player.myCharz().cx, Player.myCharz().cy, n, n);
    }

    public void autoUnFocus() {
        if (Player.myCharz().itemFocus != null && (Res.distance(Player.myCharz().cx, Player.myCharz().itemFocus.x) > MyTile.size * 3 || Res.distance(Player.myCharz().cy, Player.myCharz().itemFocus.y) > MyTile.size * 3)) {
            Player.myCharz().itemFocus = null;
            MapScr.isAutoFocus = true;
            MapScr.isCheckUnFocus = false;
        }
    }

    public boolean checkLeoCauThang() {
        return false;
    }

    public void closeButtonSkill() {
        this.isTransSkill = true;
        this.dirTransSkill = 1;
        MapScr.isPaintButtonSkill = false;
        final Tutorial tutorial = this.tutorial;
        if (tutorial != null && (tutorial.isStepThaoTacItem(0) || this.tutorial.isStepGanSkill(0))) {
            this.tutorial.step = 1;
        }
    }

    public void closeMenuHidden() {
        this.openButtonSkill();
        this.vecCmds.get(0).frameImg = LoadDataManager.frameHide;
        this.menuHidden.close();
    }

    public void closeParty() {
    }

    @Override
    public void commandTab(int w, int n) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Command tab trong mapscr:");
        sb.append(w);
        Res.outz(sb.toString());
        CanvasNinja.clearAllPointer();
        super.commandTab(w, n);
        n = -1;
        Label_0733:
        {
            switch (w) {
                case 2000: {
                    this.tutorial.skipAll();
                    break;
                }
                case 1999: {
                    final Tutorial tutorial = this.tutorial;
                    if (tutorial != null) {
                        tutorial.skip();
                        break;
                    }
                    break;
                }
                case 21: {
                    ChatBoxPM.gI().show();
                    break;
                }
                case 20: {
                    SendMessage.gI().requestWorldMap((short) (-1));
                    break;
                }
                case 19: {
                    SendMessage.gI().requestListPurchase();
                    break;
                }
                case 18: {
                    if (TabParty.partyInfo == null) {
                        TabParty.gI().show();
                    } else {
                        TabCreateParty.gI().show();
                    }
                    SendMessage.gI().updateListParty();
                    break;
                }
                case 17: {
                    SendMessage.gI().requestListItemGachaRyo();
                    break;
                }
                case 16: {
                    SendMessage.gI().loadInfoAuto();
                    TabAuto.gI().show();
                    break;
                }
                case 15: {
                    TabUpgrade.gI().show();
                    SendMessage.gI().requestTabUpgrade((byte) 0);
                    break;
                }
                case 14: {
                    MenuSetting.gI().switchToMe();
                    break;
                }
                case 13: {
                    SendMessage.gI().openUIFriend();
                    break;
                }
                case 12: {
                    SendMessage.gI().requestSkillPoint();
                    SendMessage.gI().requestTabLearnSKill();
                    final Tutorial tutorial2 = this.tutorial;
                    if (tutorial2 != null && tutorial2.isStepGanSkill(1)) {
                        this.tutorial.step = 2;
                        break;
                    }
                    break;
                }
                case 11: {
                    SendMessage.gI().requestOpenUIQuestAll();
                    TabListQuest.gI().show();
                    break;
                }
                case 10: {
                    SendMessage.gI().openInventory();
                    TabInventory.gI().show();
                    final Tutorial tutorial3 = this.tutorial;
                    if (tutorial3 != null && tutorial3.isStepThaoTacItem(1)) {
                        this.tutorial.step = 2;
                    }
                    if (Config.isOfflineMode) {
                        this.openInventory();
                        break;
                    }
                    break;
                }
                case 9: {
                    if (Player.myCharz().charFocus != null) {
                        SendMessage.gI().requestMenuPvP(Player.myCharz().charFocus.charID, -1);
                        break;
                    }
                    break;
                }
                case 8: {
                    if (Player.myCharz().itemFocus != null) {
                        SendMessage.gI().pickItem(Player.myCharz().itemFocus.itemMapID);
                        break;
                    }
                    break;
                }
                case 4: {
                    if (MapScr.keyMove.isClickKeyMove()) {
                        break Label_0733;
                    }
                    final mNPC npcFocus = Player.myCharz().npcFocus;
                    if (Player.myCharz().cx < npcFocus.cx) {
                        w = n;
                    } else {
                        w = 1;
                    }
                    npcFocus.dir = w;
                    SendMessage.gI().requestTalkToNPC(npcFocus.template.npcTemplateId, (byte) 0);
                    final Tutorial tutorial4 = this.tutorial;
                    if (tutorial4 != null && tutorial4.isTutorialNpc() && npcFocus.template.npcTemplateId == 0) {
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(Rms.RMS_TUONG_TAC_NPC);
                        sb2.append(Player.myCharz().charID);
                        Rms.saveRMSInt(sb2.toString(), 1);
                        this.tutorial.isDone = true;
                    }
                    final Tutorial tutorial5 = this.tutorial;
                    if (tutorial5 != null && tutorial5.isStepThaoTacKho(0) && npcFocus.template.npcTemplateId == 3) {
                        this.tutorial.step = 1;
                        break;
                    }
                    break;
                }
                case 77: {
                    SendMessage.gI().requestZone(0, 0);
                    break;
                }
                case 3: {
                    if (!(this.isExtendChat ^= true)) {
                        this.cmdShowDetailChat.setPosPaint(this.arrXFrameChat[2], this.arrYFrameChat[2], LoadDataManager.imgIconShowDetailChat);
                        break;
                    }
                    break;
                }
                case 2: {
                    if (!this.tfChat.getText().equals("")) {
                        if (Config.isOfflineMode) {
                            Player.myCharz().startPopup(this.tfChat.getText(), 10000);
                        }
                        SendMessage.gI().chatTo(Player.myCharz().charID, this.tfChat.getText());
                        this.tfChat.setTextFirst("");
                        this.tfChat.setFocus(false);
                        break;
                    }
                    break;
                }
                case 1: {
                    if (CanvasNinja.imgHoldToMove != null) {
                        break;
                    }
                    if (this.menuHidden != null) {
                        this.closeMenuHidden();
                        break;
                    }
                    this.closeButtonSkill();
                    this.vecCmds.get(0).frameImg = LoadDataManager.frameShow;
                    w = CanvasNinja.w;
                    final MyCommand cmdShowHide = MapScr.cmdShowHide;
                    (this.menuHidden = new MenuHidden(w, cmdShowHide.x + cmdShowHide.w / 2, cmdShowHide.y + cmdShowHide.h + this.margin)).open();
                    break;
                }
                case -3: {
                    if (!MapScr.isHaveUnReadMsg) {
                        return;
                    }
                    if (CanvasNinja.imgHoldToMove == null) {
                        this.closeButtonSkill();
                        ChatBoxPM.gI().show();
                        ChatBox.gI().show();
                        break;
                    }
                    break;
                }
            }
        }
    }

    public void init() {
        this.initCmd();
        this.initInfoMe();
        this.initInfoFocus();
        this.startPopupPKDefault();
        this.initTestButton();
    }

    public void initChat() {
        final int n = CanvasNinja.w * 40 / 100;
        final int x = MapScr.myButtonSkills.get(1).x;
        final KeyMove keyMove = MapScr.keyMove;
        final int n2 = x - (keyMove.x + keyMove.w) - 50;
        int n3 = n;
        if (n > n2) {
            n3 = n2;
        }
        int n4;
        if ((n4 = n3) < 70) {
            n4 = 70;
        }
        final int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(6, n4, LoadDataManager.frameInputChat1);
        this.arrWFrameChat = new int[]{fixSizeTagFrameUp, LoadDataManager.imgIconSend.getRWidth(), LoadDataManager.imgIconShowDetailChat.getRWidth(), (int) LoadDataManager.frameInputChat2.frameWidth};
        final int[] arrHFrameChat = {(int) LoadDataManager.frameInputChat1.frameHeight + 5, LoadDataManager.imgIconSend.getRHeight(), LoadDataManager.imgIconShowDetailChat.getRHeight(), (int) LoadDataManager.frameInputChat2.frameHeight};
        this.arrHFrameChat = arrHFrameChat;
        final int w = CanvasNinja.w;
        final int n5 = (w - fixSizeTagFrameUp) / 2;
        final int n6 = (w - fixSizeTagFrameUp) / 2;
        final int[] arrWFrameChat = this.arrWFrameChat;
        this.arrXFrameChat = new int[]{n5, n6 + arrWFrameChat[0] - 5 - arrWFrameChat[1], (w - fixSizeTagFrameUp) / 2 + fixSizeTagFrameUp / 2 - arrWFrameChat[2] / 2, (w - fixSizeTagFrameUp) / 2 + fixSizeTagFrameUp / 2 - arrWFrameChat[3] / 2};
        final int h = CanvasNinja.h;
        final int n7 = arrHFrameChat[0];
        final int n8 = arrHFrameChat[0];
        final int n9 = (n8 - arrHFrameChat[1]) / 2;
        final int n10 = arrHFrameChat[3];
        this.arrYFrameChat = new int[]{h - n7 - 25, h - n8 - 25 + n9 - 1, h - n8 - 25 - n10 + n10 / 2 - arrHFrameChat[2] / 2, h - n8 - 25 - n10};
        this.tfChat = new TField();
        TField.resetTField();
        final TField tfChat = this.tfChat;
        tfChat.isSendChat = true;
        tfChat.x = this.arrXFrameChat[0] + 5;
        tfChat.y = this.arrYFrameChat[0] - 2;
        final int[] arrWFrameChat2 = this.arrWFrameChat;
        tfChat.width = arrWFrameChat2[0] - arrWFrameChat2[1] - 10;
        tfChat.height = this.arrHFrameChat[0];
        if (!NinjaMidlet.isUseIOSSpec) {
            tfChat.isKeyTyped = true;
        }
        tfChat.setMaxTextLenght(60);
        this.tfChat.setFocus(false);
        this.tfChat.setIputType(0);
        (this.cmdSendChat = new MyCommand("", 2, this)).setPosPaint(this.arrXFrameChat[1], this.arrYFrameChat[1], LoadDataManager.imgIconSend);
        (this.cmdShowDetailChat = new MyCommand("", 3, this)).setPosPaint(this.arrXFrameChat[2], this.arrYFrameChat[2], LoadDataManager.imgIconShowDetailChat);
        final MyCommand cmdShowUnreadMsg = new MyCommand("", -3, this);
        this.cmdShowUnreadMsg = cmdShowUnreadMsg;
        final int n11 = this.arrXFrameChat[0];
        final int n12 = this.arrYFrameChat[0];
        final int margin = this.margin;
        final FrameImage frameUnread = LoadDataManager.frameUnread;
        cmdShowUnreadMsg.setPosPaint(n11, n12 - margin - (int) frameUnread.frameHeight, frameUnread);
        this.xCharChatPos = new int[3];
        this.yCharChatPos = new int[3];
        this.wCharChatPos = new int[3];
        for (int i = 0; i < 3; ++i) {
            this.xCharChatPos[i] = this.arrXFrameChat[0] + 30;
            this.yCharChatPos[i] = this.arrYFrameChat[0] - (i + 1) * 20;
            this.wCharChatPos[i] = 100;
        }
    }

    public void initCmd() {
        this.vecCmds = new Vector<MyCommand>();
        this.initSetting();
    }

    public void initInfoFocus() {
        this.wInfoFocus = LoadDataManager.imgHP_tm_xanh_big_nen.getRWidth();
        this.hInfoFocus = LoadDataManager.imgHP_tm_xanh_big_nen.getRHeight();
        this.xInfoFocus = CanvasNinja.w / 2 - this.wInfoFocus / 2;
        this.yInfoFocus = this.margin * 2;
    }

    public void initInfoMe() {
        this.xInfoMe = NinjaMidlet.PADDING_TAI_THO;
        this.yInfoMe = this.margin * 2 + 2;
        this.w_real = new int[3];
        this.h_real = new int[3];
        this.w_result = new int[3];
        final int[] per = new int[3];
        this.per = per;
        this.w_countDown = new int[3];
        this.per_tem = new int[3];
        this.offset = new int[3];
        this.xHpMpSp = new int[3];
        this.yHpMpSp = new int[3];
        this.lastTimeHpMpSp = new long[3];
        per[0] = (int) (Player.myCharz().cHP * 1.0f / Player.myCharz().cHPFull * 100.0f);
        this.per[1] = (int) (Player.myCharz().cMP * 1.0f / Player.myCharz().cMPFull * 100.0f);
        this.per[2] = (int) (Player.myCharz().cSP * 1.0f / Player.myCharz().cSPFull * 100.0f);
        int n = 0;
        while (true) {
            final Image[] imgHpMpSpInfoMe = LoadDataManager.imgHpMpSpInfoMe;
            if (n >= imgHpMpSpInfoMe.length) {
                break;
            }
            this.w_real[n] = imgHpMpSpInfoMe[n].getRWidth();
            this.h_real[n] = LoadDataManager.imgHpMpSpInfoMe[n].getRHeight() / LoadDataManager.numFrameHpMpSp;
            ++n;
        }
        final int[] xHpMpSp = this.xHpMpSp;
        final int xInfoMe = this.xInfoMe;
        xHpMpSp[0] = xInfoMe + 44;
        xHpMpSp[2] = (xHpMpSp[1] = xInfoMe + 52);
        final int[] yHpMpSp = this.yHpMpSp;
        final int yInfoMe = this.yInfoMe;
        yHpMpSp[0] = yInfoMe + 17;
        yHpMpSp[1] = yInfoMe + 27;
        yHpMpSp[2] = yInfoMe + 37;
        this.updateHPLostAll();
    }

    public void initInteractObj() {
        final int padding_TAI_THO = NinjaMidlet.PADDING_TAI_THO;
        final int rHeight = LoadDataManager.imgBackgroundInfoMe.getRHeight();
        final Vector<BuffEffect> buffEffects = MapScr.buffEffects;
        int n;
        if (buffEffects != null && !buffEffects.isEmpty()) {
            n = 0;
        } else {
            n = 30;
        }
        this.interactObjPaint = new InteractObjPaint(padding_TAI_THO, rHeight + 90 - n);
    }

    public void initMission(final Vector<MissionPaintDetail> vector) {
        MissionPaintDetail.vecMissionPaintDetails.removeAllElements();
        MissionPaintDetail.vecMissionPaintDetails.addAll(vector);
        if (!MissionPaintDetail.vecMissionPaintDetails.isEmpty()) {
            this.missionPaint = new MissionPaint();
            final int rHeight = LoadDataManager.imgBackgroundInfoMe.getRHeight();
            final Vector<BuffEffect> buffEffects = MapScr.buffEffects;
            int n;
            if (buffEffects != null && !buffEffects.isEmpty()) {
                n = 0;
            } else {
                n = 10;
            }
            this.missionPaint.init(vector, rHeight + 58 - n);
        } else {
            this.missionPaint = null;
        }
    }

    public void initPartyPaint() {
    }

    public void initSetting() {
        MapScr.cmdShowHide = new MyCommand("", 1, this);
        final int padding_TAI_THO = NinjaMidlet.PADDING_TAI_THO;
        final int margin = this.margin;
        MapScr.cmdShowHide.setPosPaint(CanvasNinja.w - (int) LoadDataManager.frameShow.frameWidth - (padding_TAI_THO + margin), margin * 7, LoadDataManager.frameHide);
        this.vecCmds.add(MapScr.cmdShowHide);
        MapScr.cmdChatNpc = new MyCommand("", 4, this);
        MapScr.cmdChatBgItem = new MyCommand("", 77, this);
        MapScr.cmdChatItem = new MyCommand("", 8, this);
        MapScr.cmdChatPlayer = new MyCommand("", 9, this);
        if (this.menuHidden != null && CanvasNinja.imgHoldToMove == null) {
            this.openButtonSkill();
            this.vecCmds.get(0).frameImg = LoadDataManager.frameHide;
            this.menuHidden.close();
        }
    }

    public void initSkillPaint() {
        MapScr.myButtonSkills = new Vector<MyButtonSkill>();
        final int n = CanvasNinja.w - (KeyMove.MARGIN_LEFT + NinjaMidlet.PADDING_TAI_THO);
        final int n2 = CanvasNinja.h - 18;
        MapScr.myButtonSkills.add(new MyButtonSkill(n, n2, MyButtonSkill.COLOR_SKILL_BIG, 0, null));
        MapScr.myButtonSkills.add(new MyButtonSkill((int) (n - LoadDataManager.mySkillButton[MyButtonSkill.COLOR_SKILL_BIG].frameWidth - 8 - 7.0f), CanvasNinja.h - 7, MyButtonSkill.COLOR_GREEN, 1, null));
        MapScr.myButtonSkills.add(new MyButtonSkill((int) (n - LoadDataManager.mySkillButtonOld[MyButtonSkill.COLOR_SKILL_BIG].frameWidth - 8 - 12.0f), (int) (CanvasNinja.h - LoadDataManager.mySkillButtonOld[MyButtonSkill.COLOR_GREEN].frameHeight - 7 * 2), MyButtonSkill.COLOR_YELLOW, 2, null));
        MapScr.myButtonSkills.add(new MyButtonSkill((int) (n - LoadDataManager.mySkillButtonOld[MyButtonSkill.COLOR_SKILL_BIG].frameWidth - 8 + 40.0f), (int) (n2 - LoadDataManager.mySkillButtonOld[MyButtonSkill.COLOR_SKILL_BIG].frameHeight - 7 * 2), MyButtonSkill.COLOR_PINK, 3, null));
        final int n3 = (int) (n + LoadDataManager.mySkillButtonOld[MyButtonSkill.COLOR_GREEN].frameWidth / 2.0f - 5.0f);
        final int n4 = (int) (n2 - LoadDataManager.mySkillButtonOld[MyButtonSkill.COLOR_SKILL_BIG].frameHeight - 7 * 2 - 5.0f);
        MapScr.myButtonSkills.add(new MyButtonSkill(n3, n4, MyButtonSkill.COLOR_BLUE, 4, null));
        MapScr.myButtonSkills.add(new MyButtonSkill(n3, (int) (n4 - LoadDataManager.mySkillButtonOld[MyButtonSkill.COLOR_JUMP].frameHeight - 5.0f), MyButtonSkill.COLOR_JUMP, 5, MyButtonSkill.TYPE_JUMP));
        if (Config.isOfflineMode) {
            Player.myCharz().vSkillFight = new VectorCustom();
            final Skill skill = new Skill(Player.myCharz(), 0, 300, Player.myCharz().classId, Player.myCharz().cw / 2, Player.myCharz().ch / 2);
            skill.skillId = 0;
            skill.template.type = 1;
            Skills.add(skill);
            this.initSkillTest(0);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(Rms.RMS_KEY_SKILL);
        sb.append(Player.myCharz().charID);
        int[] intArrayFromByteArray;
        if (MyButtonSkill.isKeySkillDefault(intArrayFromByteArray = Res.getIntArrayFromByteArray(Rms.loadRMS(sb.toString())))) {
            intArrayFromByteArray = new int[]{7, KEY.KEY_SPACE, KEY.KEY_1, KEY.KEY_2, KEY.KEY_3, KEY.KEY_4, KEY.KEY_J};
        }
        for (int i = 0; i < MapScr.myButtonSkills.size(); ++i) {
            if (Player.myCharz().vSkillFight.size() > 0 && i < Player.myCharz().vSkillFight.size()) {
                final Skill skill2 = (Skill) Player.myCharz().vSkillFight.elementAt(i);
                if (skill2 != null) {
                    skill2.c = Player.myCharz();
                }
                MapScr.myButtonSkills.elementAt(i).setSkill(skill2);
            }
            if (intArrayFromByteArray != null && i < intArrayFromByteArray.length && i + 1 < intArrayFromByteArray.length) {
                MapScr.myButtonSkills.elementAt(i).keySkill = intArrayFromByteArray[i + 1];
            }
        }
    }

    public void initSkillTest(int i) {
        for (int j = 0; j < this.idEffects[i].length; ++j) {
            final Player myCharz = Player.myCharz();
            final int n = this.idEffects[i][j];
            final int classId = Player.myCharz().classId;
            final int n2 = this.disSkills[i][j];
            final Skill skill = new Skill(myCharz, n, 1000, classId, n2, n2);
            skill.idEffect = 38;
            skill.template.type = 1;
            skill.dx = 384;
            skill.dy = 384;
            Player.myCharz().vSkillFight.addElement(skill);
        }
        Skill skill2;
        for (i = 0; i < MapScr.myButtonSkills.size(); ++i) {
            if (Player.myCharz().vSkillFight.size() > 0 && i < Player.myCharz().vSkillFight.size()) {
                skill2 = (Skill) Player.myCharz().vSkillFight.elementAt(i);
                skill2.c = Player.myCharz();
                MapScr.myButtonSkills.elementAt(i).setSkill(skill2);
            }
        }
    }

    public void initTestButton() {
        if (mSystem.isTest) {
            this.vecBtnTest = new Vector<MyButton>();
            this.cmdStart = 1999;
            this.addBtn("Đánh vũ khí");
            this.addBtn("Tim đập");
            this.addBtn("Dùng/Cất Vũ Khí");
            final int size = this.vecBtnTest.size();
            final int n = (int) LoadDataManager.myButtons[0].frameHeight;
            final int w = CanvasNinja.w;
            final int margin = this.margin;
            final int n2 = (CanvasNinja.h - (size * n + margin * (size - 1))) / 2;
            int n3 = 0;
            for (final MyButton myButton : this.vecBtnTest) {
                myButton.setXY(w - margin * 20 - myButton.w, (this.margin + n) * n3 + (n2 - 60));
                ++n3;
            }
        }
    }

    public boolean isShowMenuHidden() {
        return this.menuHidden != null;
    }

    @Override
    public void keyPress(final int n) {
        if (NinjaMidlet.isPC && !this.tfChat.isFocused()) {
            final MyButtonSkill btnSkillByKeyPress = MyButtonSkill.findBtnSkillByKeyPress(n);
            if (btnSkillByKeyPress != null) {
                btnSkillByKeyPress.useSkill();
                return;
            }
        }
        if (n == KEY.KEY_ENTER) {
            if (!this.tfChat.isFocused() && Player.myCharz().bgItemFocus != null) {
                MapScr.cmdChatBgItem.perform();
                return;
            }
            if (!this.tfChat.isFocused() && Player.myCharz().npcFocus != null) {
                MapScr.cmdChatNpc.perform();
                return;
            }
            if (!this.tfChat.isFocused() && Player.myCharz().npcFocus == null) {
                this.tfChat.setFocus(true);
                return;
            }
        }
        if (this.tfChat.isFocused()) {
            final TField tfChat = this.tfChat;
            tfChat.isChat = true;
            tfChat.keyPressed(n);
            return;
        }
        if (n == KEY.KEY_Q && Player.myCharz().mobFocus != null) {
            Player.myCharz().mobFocus.startDie();
        }
        if (n == KEY.KEY_A) {
            AvatarMuzik.gI().switchToMe();
        }
        if (n == KEY.KEY_U) {
            TabUpgrade.gI().show();
            SendMessage.gI().requestTabUpgrade((byte) 0);
        }
        if (n == KEY.KEY_P && gI().popupPK != null) {
            if (!gI().popupPK.isClosePK) {
                gI().popupPK.isClosePK = true;
            } else {
                gI().popupPK.isClosePK = false;
            }
        }
        if (n == KEY.KEY_M) {
            if (gI().menuMiniMap != null) {
                CanvasNinja.objScr = null;
                gI().menuMiniMap = null;
            } else {
                this.startMiniMapDefault();
            }
        }
        if (n == KEY.KEY_B) {
            SendMessage.gI().openInventory();
            TabInventory.gI().show();
            if (Config.isOfflineMode) {
                this.openInventory();
            }
        }
        if (n == KEY.KEY_N) {
            Player.myCharz().isHaveChakra ^= true;
            Player.myCharz().useCharka(Player.myCharz().isHaveChakra);
            if (Config.isOfflineMode) {
                Player.myCharz().useCharka(Player.myCharz().isHaveChakra);
                if (Player.myCharz().isHaveChakra) {
                    Player.myCharz().startFlyText((byte) 2, (byte) (-1), (short) 500);
                    Player.myCharz().startFlyText((byte) 0, (byte) 12, (short) 500);
                } else {
                    Player.myCharz().stopFlyText((byte) 2);
                    Player.myCharz().stopFlyText((byte) 0);
                }
            } else {
                final StringBuilder sb = new StringBuilder();
                sb.append("Char.myCharz().isHaveChakra:");
                sb.append(Player.myCharz().isHaveChakra);
                Res.outz(sb.toString());
            }
        }
        if (n == KEY.KEY_DELETE) {
            final MenuMain menuMain = CanvasNinja.menuMain;
            if (menuMain == null) {
                MenuSetting.gI().switchToMe();
            } else if (menuMain instanceof MenuSetting) {
                CanvasNinja.menuMain = null;
            }
            CanvasNinja.currentTab = null;
        }
        if (n == KEY.KEY_0) {
            MapScr.myButtonSkills.elementAt(0).useSkill();
        }
        if (n == KEY.KEY_1) {
            MapScr.myButtonSkills.elementAt(1).useSkill();
        }
        if (n == KEY.KEY_2) {
            MapScr.myButtonSkills.elementAt(2).useSkill();
        }
        if (n == KEY.KEY_3) {
            MapScr.myButtonSkills.elementAt(3).useSkill();
        }
        if (n == KEY.KEY_4) {
            MapScr.myButtonSkills.elementAt(4).useSkill();
        }
    }

    @Override
    public void keyTyped() {
        if (this.tfChat.isFocused()) {
            this.tfChat.keyTyped();
        }
    }

    public void loadGameScr() {
        loadSplash();
        this.loadInforBar();
    }

    public void openButtonSkill() {
        this.isTransSkill = true;
        this.dirTransSkill = -1;
        MapScr.isPaintButtonSkill = true;
        final Tutorial tutorial = this.tutorial;
        if (tutorial != null && (tutorial.isStepThaoTacItem(1) || this.tutorial.isStepGanSkill(1))) {
            this.tutorial.step = 0;
        }
    }

    @Override
    public void paint(final mGraphics mGraphics) {
        MapScr.countEff = 0;
        if (!MapScr.isPaint) {
            return;
        }
        CanvasNinja.debug("PA1", 1);
        CanvasNinja.debug("PA2", 1);
        CanvasNinja.paintBGGameScr(mGraphics);
        CanvasNinja.debug("PA3", 1);
        final int shock_scr = MapScr.shock_scr;
        if (shock_scr > 0) {
            final int n = -MapScr.cmx;
            final int[] shock_x = MapScr.shock_x;
            final int n2 = shock_x[shock_scr % shock_x.length];
            final int n3 = -MapScr.cmy;
            final int[] shock_y = MapScr.shock_y;
            mGraphics.translate(n + n2, n3 + shock_y[shock_scr % shock_y.length]);
            --MapScr.shock_scr;
        } else {
            mGraphics.translate(-MapScr.cmx, -MapScr.cmy);
        }
        gI().paintBgItem(mGraphics, new int[]{0, 1, 2});
        MyTile.paintTileMap(mGraphics);
        MyTile.paintWayPoint(mGraphics);
        this.paintBgItemKhu(mGraphics);
        this.paintEffectSkillBefore(mGraphics);
        if (Player.myCharz().isGetSharigan) {
            this.paintSharigan(mGraphics);
        }
        this.paintObjInMap(mGraphics);
        gI().paintBgItem(mGraphics, 3);
        this.paintItems(mGraphics);
        this.paintMob(mGraphics);
        this.paintNpc(mGraphics);
        this.paintPlayer(mGraphics);
        this.paintPopupMob(mGraphics);
        this.paintPopupNPC(mGraphics);
        this.paintPopupPlayer(mGraphics);
        this.paintPlayerSharingan(mGraphics);
        this.paintEffectLast(mGraphics);
        paintSplash(mGraphics);
        CanvasNinja.paintSky(mGraphics);
        this.paintBgItem(mGraphics, new int[]{4, 5, 6});
        this.paintEffectSkillAfter(mGraphics);
        mSystem.paintFlyText(mGraphics);
        paintSplash(mGraphics);
        this.paintEffectMoreSkill(mGraphics);
        resetTranslate(mGraphics);
        this.paintInfoServer(mGraphics);
        final PopupPK popupPK = this.popupPK;
        if (popupPK != null) {
            if (!this.isExtendChat) {
                popupPK.show();
                this.paintPopupAuto(mGraphics);
            } else {
                popupPK.hide();
            }
        }
        this.paintFrameChat(mGraphics);
        if (!MapScr.isPaintButtonSkill) {
            this.paintMenuHidden(mGraphics);
        }
        this.paintChatBox(mGraphics);
        CanvasNinja.resetTrans(mGraphics);
        MapScr.keyMove.paint(mGraphics);
        if (TabLearnSkill.isPaintButtonSkill || CanvasNinja.imgHoldToMoveTutorial != null) {
            paintMyButtonSkillOpacity(mGraphics);
        }
        mGraphics.translate(this.cmxTransSkill, 0);
        if (!TabLearnSkill.isPaintButtonSkill) {
            paintMyButtonSkill(mGraphics);
        }
        CanvasNinja.resetTrans(mGraphics);
        paintBuffEffects(mGraphics);
        final MissionPaint missionPaint = this.missionPaint;
        if (missionPaint != null) {
            final int rHeight = LoadDataManager.imgBackgroundInfoMe.getRHeight();
            final Vector<BuffEffect> buffEffects = MapScr.buffEffects;
            int n4;
            if (buffEffects != null && !buffEffects.isEmpty()) {
                n4 = 0;
            } else {
                n4 = 30;
            }
            missionPaint.y = rHeight + 58 - n4;
            this.missionPaint.paint(mGraphics);
        }
        CanvasNinja.resetTrans(mGraphics);
        this.paintSomeThing(mGraphics);
        this.paintInfoMe(mGraphics);
        this.paintInfoFocus(mGraphics);
        this.paintMiniMap(mGraphics);
        this.paintInteractObj(mGraphics);
        CanvasNinja.resetTrans(mGraphics);
        this.paintCmd(mGraphics);
        if (CanvasNinja.isDark && this.tutorial == null) {
            mGraphics.setColor(0, 0.25f);
            mGraphics.fillRect(0, 0, CanvasNinja.w, CanvasNinja.h, false);
        }
        this.paintDmgSkill(mGraphics);
        CanvasNinja.resetTrans(mGraphics);
        this.paintNotifyServer(mGraphics);
        this.paintSoundMap(mGraphics);
    }

    public void paintBgItem(final mGraphics mGraphics, final int n) {
        for (int i = 0; i < MyTile.vCurrItem.size(); ++i) {
            final ObjMap objMap = (ObjMap) MyTile.vCurrItem.elementAt(i);
            final short idImage = objMap.idImage;
            if (idImage != -1 && objMap.layer == n && idImage != 2000 && idImage < 10000) {
                objMap.paint(mGraphics);
            }
        }
    }

    public void paintBgItem(final mGraphics mGraphics, final int[] array) {
        int n = 0;
        int n2;
        for (int i = 0; i < MyTile.vCurrItem.size(); ++i, n = n2) {
            final ObjMap objMap = (ObjMap) MyTile.vCurrItem.elementAt(i);
            n2 = n;
            if (objMap.idImage != -1) {
                int n3 = 0;
                while (true) {
                    n2 = n;
                    if (n3 >= array.length) {
                        break;
                    }
                    int n4 = n;
                    if (objMap.layer == array[n3]) {
                        final short idImage = objMap.idImage;
                        n4 = n;
                        if (idImage != 2000) {
                            n4 = n;
                            if (idImage < 10000) {
                                n4 = n;
                                if (objMap.x + objMap.dx + objMap.transX < Player.myCharz().cx) {
                                    n4 = n;
                                    if (Player.myCharz().cx < objMap.x + objMap.dx + objMap.transX + objMap.w) {
                                        n4 = n;
                                        if (objMap.layer == 4) {
                                            n4 = n + 1;
                                        }
                                    }
                                }
                                objMap.paint(mGraphics);
                            }
                        }
                    }
                    ++n3;
                    n = n4;
                }
            }
        }
        if (array[0] == 4) {
            if (n >= 1) {
                Player.myCharz().isBehideObjMap = true;
            } else {
                Player.myCharz().isBehideObjMap = false;
            }
        }
    }

    public void paintBgItemKhu(final mGraphics mGraphics) {
        for (int i = 0; i < MyTile.vCurrItem.size(); ++i) {
            final ObjMap objMap = (ObjMap) MyTile.vCurrItem.elementAt(i);
            final short idImage = objMap.idImage;
            if (idImage != -1 && (idImage == 2000 || idImage >= 10000)) {
                objMap.paint(mGraphics);
            }
        }
    }

    public void paintBigBang(final mGraphics mGraphics) {
        final Iterator<EffectBigBang> iterator = MapScr.vEffectBigBang.iterator();
        while (iterator.hasNext()) {
            iterator.next().paint(mGraphics);
        }
    }

    public void paintChatBox(final mGraphics mGraphics) {
        final ChatBox chatBox = this.chatBox;
        if (chatBox != null && CanvasNinja.imgHoldToMove == null) {
            chatBox.paint(mGraphics);
        }
    }

    public void paintCmd(final mGraphics mGraphics) {
        final Iterator<MyCommand> iterator = this.vecCmds.iterator();
        while (iterator.hasNext()) {
            iterator.next().paintIconOnly(mGraphics);
        }
    }

    public void paintDmgSkill(final mGraphics mGraphics) {
        if (mSystem.isTest) {
            for (int i = this.vRangeDmgSkills.size() - 1; i >= 0; --i) {
                this.vRangeDmgSkills.elementAt(i).paint(mGraphics);
            }
        }
    }

    public void paintEffectLast(final mGraphics mGraphics) {
        for (int i = 0; i < MapScr.vecEffectLast.size(); ++i) {
            MapScr.vecEffectLast.elementAt(i).paint(mGraphics);
        }
    }

    public void paintEffectMoreSkill(final mGraphics mGraphics) {
        for (int i = 0; i < MapScr.vEffectMoreSkill.size(); ++i) {
            MapScr.vEffectMoreSkill.get(i).paint(mGraphics);
        }
    }

    public void paintEffectSkillAfter(final mGraphics mGraphics) {
        for (int i = 0; i < MapScr.vEffectSkillAfters.size(); ++i) {
            MapScr.vEffectSkillAfters.get(i).paint(mGraphics);
        }
    }

    public void paintEffectSkillBefore(final mGraphics mGraphics) {
        for (int i = 0; i < MapScr.vEffectSkillBefores.size(); ++i) {
            MapScr.vEffectSkillBefores.get(i).paint(mGraphics);
        }
    }

    public void paintInfoFocus(final mGraphics mGraphics) {
        if (Player.myCharz().charFocus != null && Player.myCharz().charFocus.isCanAttack()) {
            final StringBuilder sb = new StringBuilder();
            sb.append(Player.myCharz().charFocus.cHP);
            sb.append("/");
            sb.append(Player.myCharz().charFocus.cHPFull);
            this.hpFocus = sb.toString();
            final mFont fontPaintNameFocus = this.fontPaintNameFocus;
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(Player.myCharz().charFocus.cName);
            sb2.append(" (");
            sb2.append(Player.myCharz().charFocus.cHP);
            sb2.append(")");
            fontPaintNameFocus.drawString(mGraphics, sb2.toString(), this.xInfoFocus + this.wInfoFocus / 2, this.yInfoFocus, 2);
            Player.myCharz().charFocus.paintHpSpec(mGraphics, this.xInfoFocus + this.wInfoFocus / 2, this.yInfoFocus + this.fontPaintNameFocus.getHeight() + this.margin);
        }
        if (Player.myCharz().mobFocus != null && Player.myCharz().mobFocus.mobTemplate != null && Player.myCharz().mobFocus.isCanAttack(true)) {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(Player.myCharz().mobFocus.hp);
            sb3.append("/");
            sb3.append(Player.myCharz().mobFocus.maxHp);
            this.hpFocus = sb3.toString();
            final mFont fontPaintNameFocus2 = this.fontPaintNameFocus;
            final StringBuilder sb4 = new StringBuilder();
            sb4.append(Player.myCharz().mobFocus.mobTemplate.name);
            sb4.append(" (");
            sb4.append(Player.myCharz().mobFocus.hp);
            sb4.append(")");
            fontPaintNameFocus2.drawString(mGraphics, sb4.toString(), this.xInfoFocus + this.wInfoFocus / 2, this.yInfoFocus, 2);
            Player.myCharz().mobFocus.paintHpSpec(mGraphics, this.xInfoFocus + this.wInfoFocus / 2, this.yInfoFocus + this.fontPaintNameFocus.getHeight() + this.margin);
        }
    }

    public void paintInfoMe(final mGraphics mGraphics) {
        CanvasNinja.resetClip(mGraphics);
        mGraphics.drawImage(LoadDataManager.imgBackgroundInfoMe, this.xInfoMe, this.yInfoMe, true);
        if (this.xOffsetHpMpSp[0] != (int) ((1.0f - Player.myCharz().cHP * 1.0f / Player.myCharz().cHPFull) * LoadDataManager.imgHpMpSpInfoMe[0].getRWidth()) && mSystem.currentTimeMillis() - this.lastTimeUpdateHpMpSp[0] > 1000L) {
            this.xOffsetHpMpSp[0] = (int) ((1.0f - Player.myCharz().cHP * 1.0f / Player.myCharz().cHPFull) * LoadDataManager.imgHpMpSpInfoMe[0].getRWidth());
            this.lastTimeUpdateHpMpSp[0] = mSystem.currentTimeMillis();
        }
        if (this.xOffsetHpMpSp[1] != (int) ((1.0f - Player.myCharz().cMP * 1.0f / Player.myCharz().cMPFull) * LoadDataManager.imgHpMpSpInfoMe[1].getRWidth()) && mSystem.currentTimeMillis() - this.lastTimeUpdateHpMpSp[1] > 1000L) {
            this.xOffsetHpMpSp[1] = (int) ((1.0f - Player.myCharz().cMP * 1.0f / Player.myCharz().cMPFull) * LoadDataManager.imgHpMpSpInfoMe[1].getRWidth());
            this.lastTimeUpdateHpMpSp[1] = mSystem.currentTimeMillis();
        }
        if (this.xOffsetHpMpSp[2] != (int) ((1.0f - Player.myCharz().cSP * 1.0f / Player.myCharz().cSPFull) * LoadDataManager.imgHpMpSpInfoMe[2].getRWidth()) && mSystem.currentTimeMillis() - this.lastTimeUpdateHpMpSp[2] > 1000L) {
            this.xOffsetHpMpSp[2] = (int) ((1.0f - Player.myCharz().cSP * 1.0f / Player.myCharz().cSPFull) * LoadDataManager.imgHpMpSpInfoMe[2].getRWidth());
            this.lastTimeUpdateHpMpSp[2] = mSystem.currentTimeMillis();
        }
        mGraphics.setClip(this.xInfoMe + 17, this.yInfoMe + 5, LoadDataManager.imgHpMpSpInfoMe[0].getRWidth(), 36);
        for (int i = 0; i < LoadDataManager.imgLostInfoMe.length; ++i) {
            if (Player.myCharz().cHP > 0 || i != 0) {
                if (Player.myCharz().cMP > 0 || i != 1) {
                    if (Player.myCharz().cSP > 0 || i != 2) {
                        final int[] xPosThanhTrang = this.xPosThanhTrang;
                        final int n = xPosThanhTrang[i];
                        final int n2 = this.xOffsetHpMpSp[i];
                        if (n < n2) {
                            xPosThanhTrang[i] = n + 1;
                        } else {
                            xPosThanhTrang[i] = n2;
                        }
                        mGraphics.drawImage(LoadDataManager.imgLostInfoMe[i], this.xInfoMe + 17 - xPosThanhTrang[i], this.yInfoMe + 5 + i * 12, true);
                        mGraphics.drawImage(LoadDataManager.imgHpMpSpInfoMe[i], this.xInfoMe + 17 - this.xOffsetHpMpSp[i], this.yInfoMe + 5 + i * 12, true);
                    }
                }
            }
        }
        final mFont fontPaintLevelInfoMe = this.fontPaintLevelInfoMe;
        final StringBuilder sb = new StringBuilder();
        sb.append("Lv.");
        sb.append(Player.myCharz().clevel);
        String s;
        if (Player.myCharz().cExpPercent > 0.0f) {
            s = " (+";
        } else {
            s = " (";
        }
        sb.append(s);
        sb.append(Player.myCharz().cExpPercent);
        sb.append("%)");
        fontPaintLevelInfoMe.drawString(mGraphics, sb.toString(), this.xInfoMe + 15, this.yInfoMe + 50 - this.fontPaintLevelInfoMe.getHeight());
    }

    public void paintInteractObj(final mGraphics mGraphics) {
        final InteractObjPaint interactObjPaint = this.interactObjPaint;
        if (interactObjPaint != null) {
            final int rHeight = LoadDataManager.imgBackgroundInfoMe.getRHeight();
            final Vector<BuffEffect> buffEffects = MapScr.buffEffects;
            int n;
            if (buffEffects != null && !buffEffects.isEmpty()) {
                n = 0;
            } else {
                n = 30;
            }
            interactObjPaint.y = rHeight + 90 - n;
            this.interactObjPaint.paint(mGraphics);
        }
    }

    public void paintMenuHidden(final mGraphics mGraphics) {
        final MenuHidden menuHidden = this.menuHidden;
        if (menuHidden != null && CanvasNinja.imgHoldToMove == null) {
            menuHidden.paint(mGraphics);
        }
    }

    public void paintNotifyServer(final mGraphics mGraphics) {
        final NotifyServer notifyServer = this.notifyServer;
        if (notifyServer != null) {
            notifyServer.fontPaint = mFont.fontPaintMyName;
            notifyServer.paint(mGraphics);
        }
    }

    public void paintPointFind(final mGraphics mGraphics) {
        final PointFind pointFind = this.pointFind;
        if (pointFind != null) {
            pointFind.paint(mGraphics);
        }
    }

    public void paintPopupAuto(final mGraphics mGraphics) {
        final int n = this.arrXFrameChat[0] + this.arrWFrameChat[0] - LoadDataManager.imgBgPK.getRWidth() - 40;
        final int n2 = this.arrYFrameChat[0] - this.margin - LoadDataManager.imgBgPK.getRHeight();
        mGraphics.drawImage(LoadDataManager.imgBgPK, (float) n, (float) n2, true, 30);
        LoadDataManager.frameIconAuto.drawFrame(Player.myCharz().isAutoAtk ? 1 : 0, (float) ((LoadDataManager.imgBgPK.getRWidth() - (int) LoadDataManager.frameIconAuto.frameWidth) / 2 + n), (float) ((LoadDataManager.imgBgPK.getRHeight() - (int) LoadDataManager.frameIconAuto.frameHeight) / 2 + n2), mGraphics);
    }

    @Override
    public void paintShape(final mGraphics mGraphics) {
        this.paintBigBang(mGraphics);
    }

    public void paintSharigan(final mGraphics mGraphics) {
        mGraphics.setColor(16777215);
        mGraphics.fillRect(MapScr.cmx, MapScr.cmy, CanvasNinja.w + MyTile.size * 2, CanvasNinja.h + MyTile.size * 2);
    }

    public void paintSomeThing(final mGraphics mGraphics) {
        if (Config.isOfflineMode) {
            mFont.tahoma_7b_red.drawString(mGraphics, "Nhấn N để bật tắt Chakra", 0, 5);
        }
        final boolean isTest = mSystem.isTest;
    }

    public void paintTutorial(final mGraphics mGraphics) {
        final Tutorial tutorial = this.tutorial;
        if (tutorial != null) {
            tutorial.paint(mGraphics);
        }
    }

    @Override
    public void resize() {
        this.fontPaintLevelInfoMe = mFont.fontPaintLevelInfoMe;
        this.fontPaintNameFocus = mFont.tahoma_7b_white_border_black;
        this.fontPaintHpFocus = mFont.tahoma_7_white_border_black_medium_clone;
        this.fontPaint = mFont.tahoma_7;
    }

    public void setCharJump(final int charJump) {
        Player.myCharz().setCharJump(charJump);
    }

    public void startInfoServer(final String s, final int n) {
        this.infoServer = new InfoServer(s, n * 1000);
    }

    public void startMiniMap(final int n, final int n2, final int n3, final int n4) {
        (this.menuMiniMap = new MiniMap(n, n2, n3, n4)).show();
    }

    public void startMiniMapDefault() {
        final int w = CanvasNinja.w;
        final int padding_TAI_THO = NinjaMidlet.PADDING_TAI_THO;
        final int margin = this.margin;
        this.startMiniMap(w - (padding_TAI_THO + margin * 3 + (int) LoadDataManager.frameHide.frameWidth + 135), margin * 3 + this.fontPaintNameFocus.getHeight(), 135, 87);
    }

    public void startPointFind(final int n, final int n2, final String s) {
        this.pointFind = new PointFind(n, n2, s);
    }

    public void startPopupPK(final int n, final int n2) {
        this.popupPK = new PopupPK(n, n2);
    }

    public void startPopupPKDefault() {
        this.startPopupPK(this.arrXFrameChat[0] + this.arrWFrameChat[0] - LoadDataManager.imgBgPK.getRWidth(), this.arrYFrameChat[0] - this.margin - LoadDataManager.imgBgPK.getRHeight());
    }

    public void startTutorial(final int n) {
        this.tutorial = new Tutorial(n);
    }

    public void stopTutorial() {
        this.tutorial = null;
    }

    @Override
    public void switchToMe() {
        if (MapScr.keyMove == null) {
            MapScr.keyMove = new KeyMove();
        }
        if (gI().menuMiniMap != null) {
            CanvasNinja.objScr = null;
            gI().menuMiniMap = null;
        }
        this.resize();
        TField.resetTField();
        this.initSkillPaint();
        this.initInteractObj();
        this.initChat();
        this.init();
        CanvasNinja.clearAllPointer();
        AudioManager.playMus(MapScr.idBgSoundMap, true);
        if (Config.isSplitImg) {
            LoadDataManager.splitFrameImage();
        }
        super.switchToMe();
    }

    @Override
    public void update() {
        MyTile.updateEffTileNew();
        this.updateMua();
        this.updateInteractObj();
        this.updateTutorial();
        this.updatePointFind();
        if (CanvasNinja.gameTick % 100 == 0 && MyTile.mapID == 137) {
            MapScr.shock_scr = 30;
        }
        if (mSystem.isTest) {
            final Vector<MyButton> vecBtnTest = this.vecBtnTest;
            if (vecBtnTest != null && vecBtnTest.size() > 0) {
                final Iterator<MyButton> iterator = this.vecBtnTest.iterator();
                while (iterator.hasNext()) {
                    iterator.next().update();
                }
            }
        }
        this.updateSoundMap();
        this.updateMenuHidden();
        if (this.isExtendChat && CanvasNinja.currentTab == null) {
            int n = 0;
            int n2;
            for (int i = 0; i < 3; ++i, n = n2) {
                n2 = n;
                if (CanvasNinja.isPointer(this.xCharChatPos[i], this.yCharChatPos[i] - 2, this.wCharChatPos[i], mFont.tahoma_7b_white.getHeight() + 4)) {
                    n2 = n;
                    if (this.last3Msg != null) {
                        CanvasNinja.clearAllPointer();
                        CanvasNinja.clearMultiPointer();
                        final Vector<ChatBox.Message> last3Msg = this.last3Msg;
                        int n3;
                        if (last3Msg.size() - i - 1 > 0) {
                            n3 = this.last3Msg.size() - i - 1;
                        } else {
                            n3 = 0;
                        }
                        final int charID = findCharInMap(last3Msg.get(n3).username).charID;
                        if (charID != Player.myCharz().charID) {
                            SendMessage.gI().requestMenuPvP(charID, -2);
                        }
                        n2 = 1;
                    }
                }
            }
            if (n == 0 && CanvasNinja.isPointer(this.arrXFrameChat[0], this.arrYFrameChat[0] - 61, this.arrWFrameChat[0], 60)) {
                CanvasNinja.clearAllPointer();
                CanvasNinja.clearMultiPointer();
                ChatBox.gI().open();
                ChatBoxPM.gI().show();
            }
        }
        this.updateNotifyServer();
        this.updateTransButtonSkill();
        this.updateBigBang();
        this.updateInfoMe();
        final MissionPaint missionPaint = this.missionPaint;
        if (missionPaint != null) {
            missionPaint.update();
        }
        if (++this.timeCheck == 100) {
            this.timeCheck = 0;
        }
        if (!this.tfChat.isFocused()) {
            MapScr.keyMove.update();
        } else {
            final KeyMove keyMove = MapScr.keyMove;
            keyMove.isUnFocus = true;
            keyMove.timeStartClick = 0L;
        }
        final Vector<MyButtonSkill> myButtonSkills = MapScr.myButtonSkills;
        if (myButtonSkills != null) {
            final Iterator<MyButtonSkill> iterator2 = myButtonSkills.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().update();
            }
        }
        final Vector<BuffEffect> buffEffects = MapScr.buffEffects;
        if (buffEffects != null) {
            final Iterator<BuffEffect> iterator3 = buffEffects.iterator();
            while (iterator3.hasNext()) {
                iterator3.next().update();
            }
        }
        try {
            updateCamera();
            for (int j = 0; j < MapScr.vCharInMap.size(); ++j) {
                ((Player) MapScr.vCharInMap.elementAt(j)).update();
            }
            for (int k = MapScr.vCharCloneInMap.size() - 1; k >= 0; --k) {
                final Player player = (Player) MapScr.vCharCloneInMap.elementAt(k);
                player.update();
                if (player.isRemove) {
                    addEffectClearClone(player.cx, player.cy);
                    MapScr.vCharCloneInMap.removeElementAt(k);
                }
            }
            Player.myCharz().updateShadowSharigan();
            this.updateEffectLast();
            for (int l = 0; l < MapScr.vObjEffectMap.size(); ++l) {
                MapScr.vObjEffectMap.elementAt(l).update();
                MapScr.vObjEffectMap.elementAt(l).checkPlayerCome(MapScr.vCharInMap);
            }
            if (Player.myCharz().statusMe == 0 && CanvasNinja.gameTick % 100 == 0) {
                System.gc();
            }
            for (int n4 = 0; n4 < MapScr.vMob.size(); ++n4) {
                ((Quai) MapScr.vMob.elementAt(n4)).update();
            }
            CanvasNinja.debug("E6", 0);
            for (int n5 = 0; n5 < MapScr.vNpc.size(); ++n5) {
                ((mNPC) MapScr.vNpc.elementAt(n5)).update();
            }
            updateFlyText();
            updateSplash();
            for (int n6 = 0; n6 < MapScr.vItemMap.size(); ++n6) {
                ((ItemInMap) MapScr.vItemMap.elementAt(n6)).update();
            }
            if (this.isInjureHp) {
                if (++this.twHp == 20) {
                    this.twHp = 0;
                    this.isInjureHp = false;
                }
            } else if (this.dHP > Player.myCharz().cHP) {
                int n7;
                if ((n7 = this.dHP - Player.myCharz().cHP >> 1) < 1) {
                    n7 = 1;
                }
                this.dHP -= n7;
            } else {
                this.dHP = Player.myCharz().cHP;
            }
            if (this.isInjureMp) {
                if (++this.twMp == 20) {
                    this.twMp = 0;
                    this.isInjureMp = false;
                }
            } else if (this.dMP > Player.myCharz().cMP) {
                int n8;
                if ((n8 = this.dMP - Player.myCharz().cMP >> 1) < 1) {
                    n8 = 1;
                }
                this.dMP -= n8;
            } else {
                this.dMP = Player.myCharz().cMP;
            }
            this.updateInfoServer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.updateEffectSkill();
        this.updateEffectMoreSkill();
        this.autoFocus();
        if (MapScr.isPaintButtonSkill && this.dirTransSkill != -1) {
            this.openButtonSkill();
            if (this.menuHidden != null) {
                this.closeMenuHidden();
            }
        }
        if (!MapScr.isPaintButtonSkill && this.dirTransSkill == -1) {
            this.closeButtonSkill();
        }
        if (NinjaMidlet.isAndroid && !NinjaMidlet.isUseIOSSpec) {
            if (this.tfChat.isFocused()) {
                Gdx.input.setOnscreenKeyboardVisible(true, Input.OnscreenKeyboardType.Default);
            }
            if (!this.tfChat.isFocused()) {
                Gdx.input.setOnscreenKeyboardVisible(false);
            }
        }
        this.autoUnFocus();
    }

    public void updateAddEffectDelay() {
        for (int i = MapScr.vEffectSkillWaiting.size() - 1; i >= 0; --i) {
            final MainEffect mainEffect = MapScr.vEffectSkillWaiting.elementAt(i);
            if (mainEffect.timeDelayAddSkill < mSystem.currentTimeMillis()) {
                if (mainEffect.typeBeforeOrAfter == 0) {
                    MapScr.vEffectSkillAfters.add(mainEffect);
                }
                if (mainEffect.typeBeforeOrAfter == 1) {
                    MapScr.vEffectSkillBefores.add(mainEffect);
                }
                MapScr.vEffectSkillWaiting.remove(i);
            }
        }
    }

    public void updateBigBang() {
        final Iterator<EffectBigBang> iterator = MapScr.vEffectBigBang.iterator();
        while (iterator.hasNext()) {
            iterator.next().update();
        }
        for (final EffectBigBang effectBigBang : MapScr.vEffectBigBang) {
            if (!effectBigBang.isBigBang) {
                MapScr.vEffectBigBang.remove(effectBigBang);
                break;
            }
        }
    }

    public void updateClickInfoMe() {
        if (CanvasNinja.isPointerRelease && CanvasNinja.isPoint(this.xInfoMe + 24, this.yInfoMe + 13, 46, 46)) {
            CanvasNinja.clearAllPointer();
            AudioManager.buttonClick();
            TabInventory.gI().show();
            TabInventory.gI().indexTabIconSelected = 1;
            SendMessage.gI().openInventory();
        }
    }

    public void updateCmd() {
        final Iterator<MyCommand> iterator = this.vecCmds.iterator();
        while (iterator.hasNext()) {
            iterator.next().updatePointer();
        }
    }

    public void updateEffectLast() {
        for (int i = MapScr.vecEffectLast.size() - 1; i >= 0; --i) {
            MapScr.vecEffectLast.elementAt(i).update();
            if (MapScr.vecEffectLast.elementAt(i).isContinueAction) {
                if (MapScr.vecEffectLast.elementAt(i).charClone != null) {
                    MapScr.vCharCloneInMap.addElement(MapScr.vecEffectLast.elementAt(i).charClone);
                }
                if (MapScr.vecEffectLast.elementAt(i).charHidden != null) {
                    final Player charHidden = MapScr.vecEffectLast.elementAt(i).charHidden;
                    charHidden.setPos(charHidden.cx - charHidden.cdir * MyTile.size, charHidden.cy);
                    MapScr.vecEffectLast.elementAt(i).charHidden = null;
                }
                if (MapScr.vecEffectLast.elementAt(i).charMascot != null) {
                    MapScr.vecEffectLast.elementAt(i).charMascot.addMascot(MapScr.vecEffectLast.elementAt(i).mascotTemp.clones());
                    MapScr.vecEffectLast.elementAt(i).charMascot = null;
                    MapScr.vecEffectLast.elementAt(i).mascotTemp = null;
                }
            }
            if (MapScr.vecEffectLast.elementAt(i).isRemove) {
                MapScr.vecEffectLast.removeElementAt(i);
            }
        }
    }

    public void updateEffectMoreSkill() {
        for (int i = MapScr.vEffectMoreSkill.size() - 1; i >= 0; --i) {
            final EffectSkillMoveToByFrame effectSkillMoveToByFrame = MapScr.vEffectMoreSkill.elementAt(i);
            effectSkillMoveToByFrame.update();
            if (effectSkillMoveToByFrame.isRemove) {
                MapScr.vEffectMoreSkill.remove(i);
                break;
            }
        }
    }

    public void updateEffectSkill() {
        this.updateAddEffectDelay();
        for (int i = 0; i < MapScr.vEffectSkillAfters.size(); ++i) {
            final MainEffect mainEffect = MapScr.vEffectSkillAfters.get(i);
            mainEffect.update();
            if ((mainEffect.timeBuff > 0L && mSystem.currentTimeMillis() > mainEffect.timeBuff) || mainEffect.isRemove) {
                if (mainEffect.isBigBang) {
                    final Player charTarget = mainEffect.charTarget;
                    if (charTarget != null) {
                        createBigBang(charTarget.cx - MapScr.cmx, charTarget.cy - MapScr.cmy, 20);
                    }
                }
                MapScr.vEffectSkillAfters.remove(i);
            }
        }
        for (int j = 0; j < MapScr.vEffectSkillBefores.size(); ++j) {
            final MainEffect mainEffect2 = MapScr.vEffectSkillBefores.get(j);
            mainEffect2.update();
            if ((mainEffect2.timeBuff > 0L && mSystem.currentTimeMillis() > mainEffect2.timeBuff) || mainEffect2.isRemove) {
                if (mainEffect2.isBigBang) {
                    final Player charTarget2 = mainEffect2.charTarget;
                    if (charTarget2 != null) {
                        createBigBang(charTarget2.cx - MapScr.cmx, charTarget2.cy - MapScr.cmy, 20);
                    }
                }
                MapScr.vEffectSkillBefores.remove(j);
            }
        }
    }

    public void updateHPLostAll() {
        for (int i = 0; i < 3; ++i) {
            this.updateHp_Lost(i);
        }
    }

    public void updateHp_Lost(final int n) {
        final int[] per = this.per;
        if (per == null) {
            return;
        }
        if (n == 0) {
            per[n] = (int) (Player.myCharz().cHP * 1.0f / Player.myCharz().cHPFull * 100.0f);
        } else if (n == 1) {
            per[n] = (int) (Player.myCharz().cMP * 1.0f / Player.myCharz().cMPFull * 100.0f);
        } else {
            per[n] = (int) (Player.myCharz().cSP * 1.0f / Player.myCharz().cSPFull * 100.0f);
        }
        final int n2 = this.per[n];
        if (n2 >= 100 || this.per_tem[n] == 0) {
            this.per_tem[n] = n2;
        }
        this.offset[n] = 1;
        this.lastTimeHpMpSp[n] = mSystem.currentTimeMillis() + 1000L;
    }

    public void updateInfoMe() {
        if (CanvasNinja.gameTick % 3 == 0) {
            if (mSystem.currentTimeMillis() - this.lastTimeEff > 2000L && ++this.fInfoMe > this.arrHPMPSP.length - 1) {
                this.fInfoMe = 0;
                this.lastTimeEff = mSystem.currentTimeMillis();
            }
            if (this.isEffLvUp && (this.hEffUp += 5) > LoadDataManager.imgHe[0].getRHeight()) {
                this.hEffUp = 0;
                this.isEffLvUp = false;
                this.isEffectFrameUp = true;
                this.indexEffUp = -1;
            }
            if (this.isEffectFrameUp && ++this.indexEffUp > LoadDataManager.frameEffLvUp.nFrame - 1) {
                this.indexEffUp = 0;
                this.isEffectFrameUp = false;
            }
        }
        for (int i = 0; i < LoadDataManager.imgLostInfoMe.length; ++i) {
            final int[] w_result = this.w_result;
            final int[] w_real = this.w_real;
            final int n = w_real[i];
            final int[] per = this.per;
            final int n2 = n * per[i] / 100;
            w_result[i] = n2;
            final int[] w_countDown = this.w_countDown;
            w_countDown[i] = n2;
            final int[] per_tem = this.per_tem;
            final int n3 = per_tem[i];
            if (n3 >= per[i]) {
                final float n4 = (float) w_real[i];
                int n5;
                if (CanvasNinja.gameTick % 6 > 3) {
                    n5 = this.offset[i]++;
                } else {
                    n5 = this.offset[i];
                }
                final int n6 = n3 - n5;
                per_tem[i] = n6;
                w_countDown[i] = (int) (n4 * 1.0f * n6 / 100.0f);
                if (per_tem[i] <= 0) {
                    per_tem[i] = 0;
                }
                final int n7 = per_tem[i];
                final int n8 = per[i];
                if (n7 < n8) {
                    per_tem[i] = n8;
                }
                final int[] offset = this.offset;
                if (offset[i] >= 3) {
                    offset[i] = 3;
                }
            }
        }
    }

    public void updateInteractObj() {
        final InteractObjPaint interactObjPaint = this.interactObjPaint;
        if (interactObjPaint != null) {
            interactObjPaint.update();
        }
    }

    @Override
    public void updateKey() {
        final MissionPaint missionPaint = this.missionPaint;
        if (missionPaint != null) {
            missionPaint.updatePointer();
        }
        this.updateClickInfoMe();
        this.updatePointerInteractObj();
        this.updateMiniMapPointer();
        this.updatePopupAutoPointer();
        if (mSystem.isTest) {
            final Vector<MyButton> vecBtnTest = this.vecBtnTest;
            if (vecBtnTest != null && vecBtnTest.size() > 0) {
                final Iterator<MyButton> iterator = this.vecBtnTest.iterator();
                while (iterator.hasNext()) {
                    iterator.next().updatePointer();
                }
            }
        }
        final int n = 0;
        boolean b = false;
        int clickSkill = 0;
        Label_0158:
        {
            if (this.menuHidden == null) {
                final Tutorial tutorial = this.tutorial;
                if (tutorial == null || tutorial.isInTutorial()) {
                    clickSkill = (MyButtonSkill.isClickSkill(MapScr.myButtonSkills) ? 1 : 0);
                    break Label_0158;
                }
            }
            clickSkill = n;
            if (CanvasNinja.imgHoldToMove == null) {
                final MenuHidden menuHidden = this.menuHidden;
                b = (menuHidden != null && menuHidden.updatePointer());
                clickSkill = n;
            }
        }
        MyTile.updatePointerWayPoint();
        final KeyMove keyMove = MapScr.keyMove;
        if (keyMove.isFocus) {
            keyMove.isFocus = false;
        }
        this.updateCmd();
        this.updateKeyInputChat();
        if (Player.myCharz().currentMovePoint != null) {
            int n2 = 0;
            while (true) {
                final boolean[] keyPressed = CanvasNinja.keyPressed;
                if (n2 >= keyPressed.length) {
                    break;
                }
                if (keyPressed[n2]) {
                    Player.myCharz().currentMovePoint = null;
                    break;
                }
                ++n2;
            }
        }
        if (this.isLockKey) {
            CanvasNinja.clearKeyHold();
            CanvasNinja.clearKeyPressed();
            return;
        }
        if (!MapScr.keyMove.isClickKeyMove() && !this.interactObjPaint.isClickFocusTagName() && (CanvasNinja.isPointerFirstClick || CanvasNinja.draggedXY())) {
            int checkSingleClickEarly = 0;
            if (clickSkill == 0) {
                checkSingleClickEarly = checkSingleClickEarly;
                if (!b) {
                    checkSingleClickEarly = (this.checkSingleClickEarly() ? 1 : 0);
                }
            }
            if (this.isClickTest && checkSingleClickEarly == 0 && clickSkill == 0) {
                this.checkClick();
            }
            CanvasNinja.isPointerFirstClick = false;
        }
        CanvasNinja.clearKeyPressed();
        CanvasNinja.isPointerRelease = false;
    }

    public void updateKeyInputChat() {
        final TField tfChat = this.tfChat;
        if (!CanvasNinja.isPointerHoldOrHover(tfChat.x, tfChat.y, tfChat.width, tfChat.height) && !CanvasNinja.isPointerHoldOrHover(this.arrXFrameChat[3], this.arrYFrameChat[3], this.arrWFrameChat[3], this.arrHFrameChat[3]) && !this.tfChat.isFocused()) {
            if (!this.tfChat.isFocused()) {
                this.isOpacity = true;
            }
        } else {
            this.isOpacity = false;
        }
        this.cmdSendChat.updateIconOnly();
        this.cmdShowDetailChat.updateIconOnly();
        this.cmdShowUnreadMsg.updateIconOnly();
        this.tfChat.update();
        this.tfChat.updateFocus();
    }

    public void updateKeyMoveChar() {
        if (Player.myCharz().isBlock()) {
            return;
        }
        if (this.tfChat.isFocused()) {
            return;
        }
        if (MapScr.typeControl == 1) {
            MapScr.keyMove.updatePointer();
        }
        final boolean[] keyHold = CanvasNinja.keyHold;
        if (keyHold[4] || keyHold[6] || keyHold[8] || keyHold[2] || CanvasNinja.keyHoldSpec[2]) {
            Player.myCharz().cancelAutoAttack();
            Player.myCharz().vcurrentMovePoints.removeAllElements();
            Player.myCharz().currentMovePoint = null;
            Player.myCharz().isMoveAtk = false;
        }
        final int n = 0;
        boolean tileType = false;
        if (Player.myCharz().statusMe == 0) {
            CanvasNinja.debug("F10", 0);
            final boolean[] keyPressed = CanvasNinja.keyPressed;
            if (keyPressed[5]) {
                this.doFire(keyPressed[5] = false, false);
            } else if (!this.checkLeoCauThang()) {
                if (!CanvasNinja.keyHold[2] && !CanvasNinja.keyHoldSpec[2]) {
                    final boolean[] keyHold2 = CanvasNinja.keyHold;
                    if (keyHold2[4]) {
                        if (Player.myCharz().isCanRun()) {
                            Player.myCharz().setDirByCDir(-1);
                            Player.myCharz().isAttack = false;
                            if (!Player.myCharz().isLockMove) {
                                if (Player.myCharz().cx - Player.myCharz().cxSend != 0) {
                                    tileType = MyTile.tileTypeAt(Player.myCharz().cx - Player.myCharz().chw, Player.myCharz().cy - 1, 2);
                                }
                                if (!MyTile.tileTypeAt(Player.myCharz().cx - Player.myCharz().chw, Player.myCharz().cy - 1, 8) && !tileType) {
                                    if (MyTile.tileTypeAt(Player.myCharz().cx, Player.myCharz().cy, 4)) {
                                        int n3;
                                        final int n2 = n3 = 0;
                                        if (MyTile.tileTypeAt(Player.myCharz().cx, Player.myCharz().cy, 2)) {
                                            final mPoint pointAtPixel = MyTile.pointAtPixel(Player.myCharz().cx, Player.myCharz().cy);
                                            n3 = n2;
                                            if (Player.myCharz().cx <= pointAtPixel.x + MyTile.size - 1) {
                                                n3 = n2;
                                                if (Player.myCharz().cy == pointAtPixel.y) {
                                                    n3 = 1;
                                                }
                                            }
                                        }
                                        if (Player.myCharz().isUsingChakra() && !MyTile.tileTypeAt(Player.myCharz().cx - Player.myCharz().chh, Player.myCharz().cy + MyTile.size, 2) && n3 == 0) {
                                            Player.myCharz().doAction((byte) 2);
                                            Player.myCharz().cvy = Player.myCharz().cspeed;
                                        } else {
                                            Player.myCharz().cvy = 0;
                                            Player.myCharz().cvx = -Res.abs(Player.myCharz().cspeed);
                                            Player.myCharz().setActionRun();
                                        }
                                    } else {
                                        Player.myCharz().cvy = 0;
                                        Player.myCharz().cvx = -Res.abs(Player.myCharz().cspeed);
                                        Player.myCharz().setActionRun();
                                    }
                                } else if (Player.myCharz().isUsingChakra()) {
                                    Player.myCharz().subCheckDir = 1;
                                    Player.myCharz().doAction((byte) 2);
                                    Player.myCharz().cvy = -Player.myCharz().cspeed;
                                    Player.myCharz().cvx = 0;
                                } else {
                                    Player.myCharz().cvy = 0;
                                    Player.myCharz().setActionRun();
                                    Player.myCharz().cvx = Player.myCharz().cspeed;
                                }
                            }
                            Player.myCharz().holder = false;
                        }
                    } else if (keyHold2[6] && Player.myCharz().isCanRun()) {
                        Player.myCharz().setDirByCDir(1);
                        Player.myCharz().isAttack = false;
                        if (Player.myCharz().cdir == -1) {
                            Player.myCharz().cdir = 1;
                        } else if (!Player.myCharz().isLockMove) {
                            int tileType2 = n;
                            if (Player.myCharz().cx - Player.myCharz().cxSend != 0) {
                                tileType2 = (MyTile.tileTypeAt(Player.myCharz().cx, Player.myCharz().cy - 1, 2) ? 1 : 0);
                            }
                            if (MyTile.tileTypeAt(Player.myCharz().cx - Player.myCharz().chw, Player.myCharz().cy, 8)) {
                                int n5;
                                final int n4 = n5 = 0;
                                if (MyTile.tileTypeAt(Player.myCharz().cx, Player.myCharz().cy, 2)) {
                                    final mPoint pointAtPixel2 = MyTile.pointAtPixel(Player.myCharz().cx, Player.myCharz().cy);
                                    n5 = n4;
                                    if (Player.myCharz().cx <= pointAtPixel2.x + MyTile.size - 1) {
                                        n5 = n4;
                                        if (Player.myCharz().cy == pointAtPixel2.y) {
                                            n5 = 1;
                                        }
                                    }
                                }
                                if (Player.myCharz().isUsingChakra() && n5 == 0 && !MyTile.tileTypeAt(Player.myCharz().cx + Player.myCharz().chh, Player.myCharz().cy + MyTile.size, 2)) {
                                    Player.myCharz().doAction((byte) 2);
                                    Player.myCharz().cvy = Player.myCharz().cspeed;
                                    Player.myCharz().cvx = 0;
                                } else {
                                    Player.myCharz().cvy = 0;
                                    Player.myCharz().setActionRun();
                                    Player.myCharz().cvx = Player.myCharz().cspeed;
                                }
                            } else if (!MyTile.tileTypeAt(Player.myCharz().cx, Player.myCharz().cy - 1, 4) && tileType2 == 0) {
                                Player.myCharz().cvy = 0;
                                Player.myCharz().cvx = Player.myCharz().cspeed;
                                Player.myCharz().setActionRun();
                            } else if (Player.myCharz().isUsingChakra()) {
                                Player.myCharz().doAction((byte) 2);
                                Player.myCharz().cvy = -Player.myCharz().cspeed;
                                Player.myCharz().cvx = 0;
                            } else {
                                Player.myCharz().cvy = 0;
                                Player.myCharz().setActionRun();
                                Player.myCharz().cvx = Player.myCharz().cspeed;
                            }
                        }
                        Player.myCharz().holder = false;
                    }
                } else if (!Player.myCharz().isLockMove && !this.checkLeoCauThang()) {
                    if (MyTile.tileTypeAt(Player.myCharz().cx - Player.myCharz().chw, Player.myCharz().cy, 8) && Player.myCharz().cdir == 1) {
                        Res.outz("đang đứng ở phải phải mà nhảy");
                        Player.myCharz().cvy = 0;
                        Player.myCharz().cvx = Player.myCharz().cspeed;
                        Player.myCharz().doAction((byte) 7);
                    } else if (MyTile.tileTypeAt(Player.myCharz().cx, Player.myCharz().cy, 4) && !MyTile.tileTypeAt(Player.myCharz().cx - Player.myCharz().chw, Player.myCharz().cy, 8) && Player.myCharz().cdir == -1) {
                        Res.outz("đang đứng ở tường trái mà nhảy");
                        Player.myCharz().cvy = 0;
                        Player.myCharz().cvx = -Player.myCharz().cspeed;
                        Player.myCharz().doAction((byte) 7);
                    } else if (Player.myCharz().cdir == 1 && MyTile.tileTypeAt(Player.myCharz().cx, Player.myCharz().cy, 4)) {
                        if (Player.myCharz().isCanJumpWhenInWall()) {
                            Player.myCharz().doAction((byte) 7);
                        }
                    } else if (Player.myCharz().cdir == -1 && MyTile.tileTypeAt(Player.myCharz().cx - 1, Player.myCharz().cy, 8)) {
                        if (Player.myCharz().isCanJumpWhenInWall()) {
                            Player.myCharz().doAction((byte) 7);
                        }
                    } else {
                        this.setCharJump(0);
                    }
                }
            }
        } else if (Player.myCharz().isRun()) {
            CanvasNinja.debug("F11", 0);
            final boolean[] keyPressed2 = CanvasNinja.keyPressed;
            if (keyPressed2[5]) {
                this.doFire(keyPressed2[5] = false, true);
            } else if (!CanvasNinja.keyHold[2] && !CanvasNinja.keyHoldSpec[2]) {
                final boolean[] keyHold3 = CanvasNinja.keyHold;
                if (keyHold3[4]) {
                    if (Player.myCharz().isCanRun()) {
                        Player.myCharz().setDirByCDir(-1);
                        if (Player.myCharz().cdir == 1) {
                            Player.myCharz().cdir = -1;
                        } else {
                            Player.myCharz().cvx = -Player.myCharz().cspeed - Player.myCharz().cBonusSpeed;
                        }
                    }
                } else if (keyHold3[6]) {
                    if (Player.myCharz().isCanRun()) {
                        Player.myCharz().setDirByCDir(1);
                        if (Player.myCharz().cdir == -1) {
                            Player.myCharz().cdir = 1;
                        } else {
                            Player.myCharz().cvx = Player.myCharz().cspeed + Player.myCharz().cBonusSpeed;
                        }
                    }
                } else if (keyHold3[8]) {
                    this.checkLeoCauThang();
                }
            } else if (!this.checkLeoCauThang() && (Player.myCharz().cx - Player.myCharz().cxSend != 0 || Player.myCharz().cy - Player.myCharz().cySend != 0)) {
                if (MyTile.tileTypeAt(Player.myCharz().cx, Player.myCharz().cy, 4) && Player.myCharz().cdir == -1) {
                    Res.outz("đang chạy mà nhảy");
                    Player.myCharz().cvy = 0;
                    Player.myCharz().cvx = -Player.myCharz().cspeed;
                    Player.myCharz().doAction((byte) 7);
                } else if (MyTile.tileTypeAt(Player.myCharz().cx - Player.myCharz().chw, Player.myCharz().cy, 8) && Player.myCharz().cdir == 1) {
                    Res.outz("đang chạy mà nhảy");
                    Player.myCharz().cvy = 0;
                    Player.myCharz().cvx = Player.myCharz().cspeed;
                    Player.myCharz().doAction((byte) 7);
                } else if (Player.myCharz().cdir == 1 && MyTile.tileTypeAt(Player.myCharz().cx, Player.myCharz().cy, 4)) {
                    if (Player.myCharz().isCanJumpWhenInWall()) {
                        Player.myCharz().doAction((byte) 7);
                    }
                } else if (Player.myCharz().cdir == -1 && MyTile.tileTypeAt(Player.myCharz().cx - 1, Player.myCharz().cy, 8)) {
                    if (Player.myCharz().isCanJumpWhenInWall()) {
                        Player.myCharz().doAction((byte) 7);
                    }
                } else {
                    Player.myCharz().cvy = -Player.myCharz().cyspeed;
                    Player.myCharz().doAction((byte) 7);
                    Player.myCharz().cp1 = 0;
                }
            }
        } else if (Player.myCharz().statusMe == 13) {
            this.checkLeoCauThang();
            final boolean[] keyHold4 = CanvasNinja.keyHold;
            if (keyHold4[4]) {
                Player.myCharz().setDir((byte) 2);
                if (Player.myCharz().cdir == 1) {
                    Player.myCharz().cdir = -1;
                } else {
                    Player.myCharz().cvx = -Player.myCharz().cspeed + Player.myCharz().cBonusSpeed;
                }
            } else if (keyHold4[6]) {
                Player.myCharz().setDir((byte) 3);
                if (Player.myCharz().cdir == -1) {
                    Player.myCharz().cdir = 1;
                } else {
                    Player.myCharz().cvx = Player.myCharz().cspeed + Player.myCharz().cBonusSpeed;
                }
            }
        } else if (Player.myCharz().statusMe == 11) {
            if (Player.myCharz().cdir == -1) {
                if (!CanvasNinja.keyHold[2] && !CanvasNinja.keyHoldSpec[2]) {
                    final boolean b = CanvasNinja.keyHold[6];
                } else {
                    Res.outz("debug 666 phải");
                    Player.myCharz().setDirByCDir(1);
                    Player.myCharz().doAction((byte) 7);
                    final Player myCharz = Player.myCharz();
                    myCharz.cx += Player.myCharz().cw;
                }
            } else if (Player.myCharz().cdir == 1) {
                if (!CanvasNinja.keyHold[2] && !CanvasNinja.keyHoldSpec[2]) {
                    final boolean b2 = CanvasNinja.keyHold[4];
                } else {
                    Res.outz("debug 444 trái");
                    Player.myCharz().setDirByCDir(-1);
                    Player.myCharz().doAction((byte) 7);
                    final Player myCharz2 = Player.myCharz();
                    myCharz2.cx += Player.myCharz().cdir * Player.myCharz().cw;
                }
            }
        } else if (Player.myCharz().statusMe == 7) {
            CanvasNinja.debug("F12", 0);
            final boolean[] keyPressed3 = CanvasNinja.keyPressed;
            if (keyPressed3[5]) {
                this.doFire(keyPressed3[5] = false, true);
            }
            final boolean[] keyHold5 = CanvasNinja.keyHold;
            if (keyHold5[4]) {
                Player.myCharz().setDirByCDir(-1);
                Player.myCharz().cvx = Player.myCharz().cdir * Player.myCharz().cspeed;
            } else if (keyHold5[6]) {
                Player.myCharz().setDirByCDir(1);
                Player.myCharz().cvx = Player.myCharz().cdir * Player.myCharz().cspeed;
            }
            if ((CanvasNinja.keyHold[2] || CanvasNinja.keyHoldSpec[2]) && Player.myCharz().isCanDoubleJump && Player.myCharz().cMP > 0 && Player.myCharz().countJump == 1) {
                Player.myCharz().isWaitingDoubleJump = true;
            }
        } else if (Player.myCharz().statusMe == 9) {
            CanvasNinja.debug("F13", 0);
            final boolean[] keyPressed4 = CanvasNinja.keyPressed;
            if (keyPressed4[5]) {
                this.doFire(keyPressed4[5] = false, true);
            }
            if ((CanvasNinja.keyHold[2] || CanvasNinja.keyHoldSpec[2]) && Player.myCharz().isCanDoubleJump && Player.myCharz().cMP > 0 && Player.myCharz().countJump == 1) {
                Player.myCharz().cvy = -Player.myCharz().cyspeed;
                Player.myCharz().doAction((byte) 7);
            }
            final boolean[] keyHold6 = CanvasNinja.keyHold;
            if (keyHold6[4]) {
                Player.myCharz().cdir = -1;
                if (!Player.myCharz().isSpecFall) {
                    Player.myCharz().cvx = Player.myCharz().cdir * Player.myCharz().cspeed;
                }
            } else if (keyHold6[6]) {
                Player.myCharz().cdir = 1;
                if (!Player.myCharz().isSpecFall) {
                    Player.myCharz().cvx = Player.myCharz().cdir * Player.myCharz().cspeed;
                }
            }
        } else if (Player.myCharz().statusMe == 4 || Player.myCharz().statusMe == 3) {
            CanvasNinja.debug("F15", 0);
            final boolean[] keyPressed5 = CanvasNinja.keyPressed;
            if (keyPressed5[5]) {
                keyPressed5[5] = false;
            }
            final boolean[] keyHold7 = CanvasNinja.keyHold;
            if (keyHold7[4]) {
                if (Player.myCharz().cdir == 1) {
                    Player.myCharz().cdir = -1;
                } else {
                    Player.myCharz().cvx = -Player.myCharz().cspeed + 2;
                }
            } else if (keyHold7[6]) {
                if (Player.myCharz().cdir == -1) {
                    Player.myCharz().cdir = 1;
                } else {
                    Player.myCharz().cvx = Player.myCharz().cspeed - 2;
                }
            }
        }
        final boolean[] keyHold8 = CanvasNinja.keyHold;
        if (!keyHold8[6] && !keyHold8[4] && !keyHold8[2] && !keyHold8[8]) {
            final boolean b3 = CanvasNinja.keyHoldSpec[2];
        }
        final boolean[] keyHold9 = CanvasNinja.keyHold;
        if (keyHold9[6]) {
            final boolean b4 = keyHold9[4];
        }
    }

    public void updateMenuHidden() {
        final MenuHidden menuHidden = this.menuHidden;
        if (menuHidden != null && CanvasNinja.imgHoldToMove == null) {
            menuHidden.update();
            if (this.menuHidden.isClose) {
                this.menuHidden = null;
            }
        }
    }

    public void updateMua() {
        LoadDataManager.frameEffBg[0].getRWidth();
        final int gw = MapScr.gW;
        boolean isWhiteFlash;
        while (true) {
            final int size = MapScr.rainEffects.size();
            isWhiteFlash = true;
            if (size >= 150) {
                break;
            }
            for (int i = 0; i < 40; ++i) {
                final int n = Res.rnd(gw - 0) + 0;
                final int random = Res.random(CanvasNinja.h);
                final RainEffect rainEffect = new RainEffect(n, random);
                rainEffect.layer = 1;
                final RainEffect rainEffect2 = new RainEffect(n, random);
                rainEffect2.layer = 2;
                MapScr.rainEffects.add(rainEffect);
                MapScr.rainEffects.add(rainEffect2);
            }
            for (int j = 0; j < 10; ++j) {
                final RainEffect rainEffect3 = new RainEffect(Res.rnd(gw - 0) + 0, Res.random(CanvasNinja.h));
                rainEffect3.layer = 0;
                MapScr.rainEffects.add(rainEffect3);
            }
        }
        while (MapScr.rainParticles.size() < 7) {
            MapScr.rainParticles.add(new RainParticle(Res.rnd(gw - 0) + 0, Res.rnd(21) + 220));
        }
        if (Res.random(100) < 2) {
            this.lightningFlashCounter = 3;
            if (Res.random(2) != 0) {
                isWhiteFlash = false;
            }
            this.isWhiteFlash = isWhiteFlash;
            MapScr.lightningBolts.add(new LightningBolt(Res.rnd(gw - 0) + 0, 0));
        }
    }

    public void updateNotifyServer() {
        if (this.notifyServer == null && MapScr.vNotifyServers.size() > 0) {
            this.notifyServer = MapScr.vNotifyServers.remove(0);
        }
        final NotifyServer notifyServer = this.notifyServer;
        if (notifyServer != null) {
            notifyServer.update();
            if (this.notifyServer.isDone) {
                this.notifyServer = null;
            }
        }
    }

    public void updatePointerInteractObj() {
        final InteractObjPaint interactObjPaint = this.interactObjPaint;
        if (interactObjPaint != null) {
            interactObjPaint.updatePointer();
        }
    }

    public void updateTransButtonSkill() {
        if (this.isTransSkill) {
            final int cmxTransSkill = this.cmxTransSkill;
            final int dirTransSkill = this.dirTransSkill;
            final int cmxTransSkill2 = cmxTransSkill + this.speedTransSkill * dirTransSkill;
            this.cmxTransSkill = cmxTransSkill2;
            if (dirTransSkill == 1) {
                if (cmxTransSkill2 >= 250) {
                    this.cmxTransSkill = 250;
                    this.isTransSkill = false;
                }
            } else if (cmxTransSkill2 <= 0) {
                this.cmxTransSkill = 0;
                this.isTransSkill = false;
            }
        }
    }

    public class LightningBolt {
        private int frame;
        private boolean isActive;
        private int x;
        private int y;

        public LightningBolt(final int x, final int y) {
            this.x = x;
            this.y = y;
            this.frame = 0;
            this.isActive = true;
        }
    }

    class RainEffect {
        float alpha;
        int idFrame;
        boolean isActive;
        int layer;
        float x;
        float y;

        RainEffect(final int n, final int n2) {
            this.alpha = 0.2f;
            this.x = (float) n;
            this.y = (float) n2;
            this.idFrame = 0;
            this.isActive = true;
            this.layer = Res.random(3);
        }
    }

    class RainParticle {
        int idFrame;
        boolean isActive;
        int type;
        int x;
        int y;

        RainParticle(final int x, final int y) {
            this.type = 0;
            this.x = x;
            this.y = y;
            this.idFrame = 0;
            this.isActive = true;
            this.type = Res.random(2);
        }
    }
}
