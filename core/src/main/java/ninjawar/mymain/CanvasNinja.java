// Class Version: 8
package ninjawar.mymain;

import ninjawar.model.ActionInterface;
import ninjawar.model.EffSky;
import ninjawar.mylib.*;
import ninjawar.myscr.*;
import ninjawar.screen.DownLoadingScr;
import ninjawar.screen.LoadingMapScr;
import ninjawar.screen.LoginScr;
import ninjawar.screen.dialog.Dialog;
import ninjawar.screen.dialog.MessageDialog;
import ninjawar.screen.mInfoDialog;
import ninjawar.screen.menu.MenuMain;
import ninjawar.screen.objscr.MiniMap;
import ninjawar.screen.objscr.ObjScr;
import ninjawar.screen.objscr.ObjScrMore;
import ninjawar.screen.quest.QuestScreen;
import ninjawar.screen.subtab.SubTabScr;
import ninjawar.screen.tab.TabParty;
import ninjawar.screen.tab.TabPurchase;
import ninjawar.screen.tab.TabScr;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.LoadVersionManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

import java.util.Iterator;
import java.util.Vector;

public class CanvasNinja extends AbstractCanvas implements ActionInterface {
    public static String IP_d;
    public static final Object LOCK;
    public static String Port_d;
    public static int[] bgRain;
    public static int[] bgRainFont;
    public static char[][] charList;
    public static int cmy;
    public static int cmyLim;
    public static int cmyMax;
    public static int cmyMin;
    public static int color;
    public static boolean[] comboKeyPC;
    public static int[] countRequestLoadingMap;
    public static int curPos;
    public static Dialog currentDialog;
    public static BaseScreen currentScreen;
    public static TabScr currentTab;
    public static HTCustom danhHieu;
    public static int deltaY1;
    public static int deltaY2;
    public static int deltaY3;
    public static int deltaY4;
    public static int deltaY5;
    public static int deltaY6;
    public static int disTemp;
    public static int f;
    public static VectorCustom flyTexts;
    public static int fps;
    public static int gameTick;
    public static int h;
    public static int hBG1;
    public static int hBG2;
    public static int hBG3;
    public static int hBG4;
    public static int hBG5;
    public static int hBG6;
    public static float hF;
    public static int hMap;
    public static int hMoreStartBg;
    public static int hOpenCamera;
    public static int hSubBG2;
    public static int hSubBG3;
    public static int hSubBG4;
    public static int hSubBG5;
    public static int hSubBG6;
    public static int hh;
    public static int hw;
    public static int hx;
    public static int hy;
    public static Image img12;
    public static Image[] imgBlue;
    public static mSprite imgHoldToMove;
    public static mSprite imgHoldToMoveTutorial;
    public static Image imgShuriken;
    public static Image[] imgViolet;
    public static Image[] imgWS;
    public static int indexKey;
    public static CanvasNinja instance;
    public static boolean isBB;
    public static boolean isCheckDau;
    public static boolean isCloseConnect;
    public static boolean isDark;
    public static boolean isDelete;
    public static boolean isDoubleClick;
    public static boolean isEff1;
    public static boolean isEff2;
    public static boolean[] isFirstPress;
    public static boolean isInitChar;
    public static boolean isLoading;
    public static boolean isLogOut;
    public static boolean isLogin;
    public static boolean isLoginDuplicate;
    public static boolean isMoveNumberPad;
    public static boolean isOpenCamera;
    public static boolean isPlaySound;
    public static boolean isPointerClick;
    public static boolean isPointerClickRight;
    public static boolean isPointerDoubleClick;
    public static boolean isPointerDown;
    public static boolean isPointerDraggedX;
    public static boolean isPointerDraggedXDone;
    public static boolean isPointerDraggedY;
    public static boolean isPointerFirstClick;
    public static boolean isPointerHold;
    public static boolean isPointerHoldNew;
    public static boolean isPointerHover;
    public static boolean isPointerJustClick;
    public static boolean isPointerJustDown;
    public static boolean isPointerLongPress;
    public static boolean isPointerPress;
    public static boolean isPointerRelease;
    public static boolean isReconnect;
    public static boolean isScrollWhell;
    public static boolean isSetDau;
    public static boolean isShowConnectFail;
    public static boolean isTouch;
    public static boolean isTouchControl;
    public static boolean isTouchControlLargeScreen;
    public static boolean isTouchControlSmallScreen;
    public static boolean isUseEffectClick;
    public static String keyChange;
    public static boolean[] keyHold;
    public static int[] keyHoldControl;
    public static boolean[] keyHoldPC;
    public static boolean[] keyHoldSpec;
    public static String keyPCPressed;
    public static String keyPCTyped;
    public static boolean[] keyPressed;
    public static boolean[] keyReleased;
    public static boolean[] keyReleasedPC;
    public static int lastBg;
    static long lastTimePress;
    public static int load;
    public static LoadingMapScr loadingMapScr;
    public static boolean lowGraphic;
    public static int marginBg2AndBg3;
    public static int max;
    public static boolean mediumGraphic;
    public static MenuMain menuMain;
    public static VectorCustom messageServer;
    public static int moreY;
    public static int nBg;
    public static int numBg4;
    public static int numBg5;
    public static int numTile;
    public static ObjScr objScr;
    public static ObjScrMore objScrMore;
    public static SupportPaint paintz;
    public static int pingUDP;
    public static int planet;
    public static boolean[] pointerClick;
    public static int pointerClickCur;
    public static boolean[] pointerDown;
    public static boolean[] pointerDraggedY;
    public static boolean[] pointerHold;
    public static boolean[] pointerHoldNew;
    public static Pointer[] pointerNew;
    public static boolean[] pointerPress;
    public static boolean[] pointerRelease;
    public static int pointerReleaseCur;
    public static boolean[] pointerReleaseNew;
    public static int px;
    public static int[] pxArr;
    public static int pxFirst;
    public static int[] pxFirstArr;
    public static int pxLast;
    public static int[] pxLastArr;
    public static int py;
    public static int[] pyArr;
    public static int pyFirst;
    public static int[] pyFirstArr;
    public static int pyLast;
    public static int[] pyLastArr;
    public static QuestScreen questScreen;
    public static boolean serverchat;
    public static int[] speedBg;
    public static float speedNen345;
    public static int speedOpen;
    public static SubTabScr subTab;
    public static int taskTick;
    static String thongBaoTest;
    public static long timeBreakLoading;
    public static long[] timeClick;
    public static long timeDelayConnect;
    public static long timeDelayDragged;
    public static long timeDelayLoadingMap;
    public static long timeDoubleClick;
    public static long timeDraggedX;
    public static long timeDraggedY;
    public static long[] timeHoldNew;
    public static long timeNow;
    public static long[] timePressPC;
    public static int timeReconnect;
    public static long timeStartClick;
    public static long timeStartPress;
    public static long timeTickEff1;
    public static long timeTickEff2;
    public static int typeBg;
    public static int up;
    public static int upmax;
    public static Vector<String> vecPurchaseDebugs;
    public static Vector<EffSky> vecSkys;
    public static VectorCustom vecc;
    public static int w;
    public static int wBG1;
    public static int wBG2;
    public static int wBG3;
    public static int wBG4;
    public static int wBG5;
    public static int wBG6;
    public static int wBGSub2;
    public static int wBGSub3;
    public static int wBGSub4;
    public static int wBGSub5;
    public static int wBGSub6;
    public static float wF;
    public static int wMap;
    public static int waitTick;
    public static int[] wsF;
    public static int[] wsState;
    public static int[] wsX;
    public static int[] wsY;
    public static int xHoldMoveTutorial;
    public static int[] xRandomBG3;
    public static int[] xRandomBG4;
    public static int xThongBaoTranslate;
    public static int yBG1;
    public static int yBG3Max;
    public static int yBg2;
    public static int yBg3;
    public static int yBg4;
    public static int yBg5;
    public static int yBg6;
    public static int yHoldMoveTutorial;
    public static int ySubBg2;
    public static int ySubBg3;
    public static int ySubBgMax3;
    public static int[] yb;
    private String charA;
    private String charA2;
    private String charA3;
    private String charD;
    private String charE;
    private String charE2;
    private String charI;
    private String charNor;
    private String charO;
    private String charO2;
    private String charO3;
    private String charString;
    private String charU;
    private String charU2;
    private String charY;
    int click;
    int count;
    int delay;
    public mGraphics g;
    int hText;
    long timefps;
    long timeup;

    static {
        CanvasNinja.timeReconnect = 1000;
        CanvasNinja.isLoginDuplicate = false;
        CanvasNinja.isLogOut = false;
        CanvasNinja.isReconnect = false;
        CanvasNinja.isShowConnectFail = true;
        CanvasNinja.vecPurchaseDebugs = new Vector<String>();
        CanvasNinja.keyHoldPC = new boolean[20];
        CanvasNinja.keyReleasedPC = new boolean[20];
        CanvasNinja.isFirstPress = new boolean[20];
        CanvasNinja.timePressPC = new long[20];
        CanvasNinja.isInitChar = false;
        CanvasNinja.load = -1;
        CanvasNinja.lowGraphic = false;
        CanvasNinja.serverchat = false;
        CanvasNinja.mediumGraphic = false;
        CanvasNinja.timeNow = 0L;
        CanvasNinja.isMoveNumberPad = true;
        CanvasNinja.isTouch = false;
        CanvasNinja.timeClick = new long[32];
        CanvasNinja.keyPressed = new boolean[100];
        CanvasNinja.keyReleased = new boolean[100];
        CanvasNinja.keyHold = new boolean[100];
        CanvasNinja.keyHoldSpec = new boolean[10];
        CanvasNinja.isDark = false;
        LOCK = new Object();
        CanvasNinja.curPos = 0;
        CanvasNinja.planet = 0;
        CanvasNinja.imgBlue = new Image[7];
        CanvasNinja.imgViolet = new Image[7];
        CanvasNinja.danhHieu = new HTCustom("");
        CanvasNinja.messageServer = new VectorCustom("");
        CanvasNinja.isLogin = false;
        CanvasNinja.isCloseConnect = false;
        CanvasNinja.marginBg2AndBg3 = 3;
        CanvasNinja.speedNen345 = 1.0f;
        CanvasNinja.deltaY6 = 0;
        CanvasNinja.hMoreStartBg = 0;
        CanvasNinja.vecSkys = new Vector<EffSky>();
        CanvasNinja.typeBg = -1;
        CanvasNinja.yb = new int[5];
        CanvasNinja.nBg = 0;
        CanvasNinja.lastBg = -1;
        CanvasNinja.bgRain = new int[]{1, 4, 11};
        CanvasNinja.bgRainFont = new int[]{-1};
        CanvasNinja.lastTimePress = 0L;
        CanvasNinja.flyTexts = new VectorCustom("vFlyText");
        CanvasNinja.fps = 0;
        CanvasNinja.speedOpen = 10;
        CanvasNinja.xThongBaoTranslate = CanvasNinja.w - 60;
        CanvasNinja.vecc = new VectorCustom("");
        CanvasNinja.isPointerJustDown = false;
        CanvasNinja.isPlaySound = true;
        CanvasNinja.f = 0;
        CanvasNinja.countRequestLoadingMap = new int[4];
        CanvasNinja.indexKey = -1;
        CanvasNinja.keyHoldControl = new int[]{-1, -1, -1, -1};
        CanvasNinja.comboKeyPC = new boolean[5];
        CanvasNinja.keyPCPressed = "";
        CanvasNinja.isSetDau = false;
        CanvasNinja.isDelete = false;
        CanvasNinja.isCheckDau = false;
        CanvasNinja.keyPCTyped = "";
        CanvasNinja.keyChange = "";
        CanvasNinja.timeDelayDragged = 20L;
        CanvasNinja.isPointerDraggedX = false;
        CanvasNinja.isUseEffectClick = false;
        CanvasNinja.isPointerDraggedXDone = false;
        CanvasNinja.pointerHoldNew = new boolean[20];
        CanvasNinja.timeHoldNew = new long[20];
        CanvasNinja.pointerReleaseNew = new boolean[20];
        CanvasNinja.pointerClick = new boolean[20];
        CanvasNinja.pointerPress = new boolean[20];
        CanvasNinja.pointerDown = new boolean[20];
        CanvasNinja.pointerHold = new boolean[20];
        CanvasNinja.pointerDraggedY = new boolean[20];
        CanvasNinja.pointerRelease = new boolean[20];
        CanvasNinja.pxFirstArr = new int[20];
        CanvasNinja.pyFirstArr = new int[20];
        CanvasNinja.pxArr = new int[20];
        CanvasNinja.pyArr = new int[20];
        CanvasNinja.pxLastArr = new int[20];
        CanvasNinja.pyLastArr = new int[20];
        CanvasNinja.pointerReleaseCur = -1;
        CanvasNinja.pointerClickCur = -1;
        CanvasNinja.isPointerDraggedY = false;
        CanvasNinja.isScrollWhell = false;
        CanvasNinja.isDoubleClick = false;
        CanvasNinja.isPointerHover = false;
        CanvasNinja.pointerNew = new Pointer[20];
    }

    public CanvasNinja() {
        this.g = new mGraphics();
        this.delay = 0;
        this.timefps = System.currentTimeMillis() + 1000L;
        this.timeup = System.currentTimeMillis() + 1000L;
        this.hText = 12;
        this.count = 1;
        this.charString = " \b0123456789.,:!?()+*$#/-%@";
        this.charNor = "abcdefghijklmnopqrstuvwxyz";
        this.charA = "aáàảãạăâ";
        this.charA2 = "ăắằẳẵặ";
        this.charA3 = "âấầẩẫậ";
        this.charD = "dđ";
        this.charE = "eéèẻẽẹê";
        this.charE2 = "êếềểễệ";
        this.charI = "iíìỉĩị";
        this.charO = "oóòỏõọôơ";
        this.charO2 = "ôốồổỗộ";
        this.charO3 = "ơớờởỡợ";
        this.charU = "uúùủũụư";
        this.charU2 = "ưứừửữự";
        this.charY = "yýỳỷỹỵ";
    }

    public static void clearAllPointer() {
        CanvasNinja.isPointerFirstClick = false;
        CanvasNinja.isPointerClick = false;
        CanvasNinja.isPointerRelease = false;
        CanvasNinja.isPointerDoubleClick = false;
        CanvasNinja.isPointerPress = false;
        CanvasNinja.isPointerRelease = false;
        CanvasNinja.isPointerHold = false;
        CanvasNinja.isPointerDown = false;
        CanvasNinja.isPointerJustDown = false;
        CanvasNinja.isPointerJustClick = false;
        CanvasNinja.isDoubleClick = false;
        CanvasNinja.isPointerDraggedX = false;
        CanvasNinja.isPointerDraggedY = false;
        CanvasNinja.isPointerDown = false;
        CanvasNinja.isPointerJustDown = false;
        MapScr.gI().lastSingleClick = 0L;
    }

    public static void clearAllPointer2() {
        CanvasNinja.isPointerClick = false;
        CanvasNinja.isPointerRelease = false;
        CanvasNinja.isPointerDoubleClick = false;
        CanvasNinja.isPointerPress = false;
        CanvasNinja.isPointerRelease = false;
        CanvasNinja.isPointerHold = false;
        CanvasNinja.isPointerDown = false;
        CanvasNinja.isPointerJustDown = false;
        CanvasNinja.isPointerJustClick = false;
        CanvasNinja.isDoubleClick = false;
        CanvasNinja.isPointerDraggedX = false;
        CanvasNinja.isPointerDraggedY = false;
        CanvasNinja.isPointerDown = false;
        CanvasNinja.isPointerJustDown = false;
        MapScr.gI().lastSingleClick = 0L;
    }

    public static void clearAllPointerEvent() {
        CanvasNinja.isPointerFirstClick = false;
        CanvasNinja.isPointerClick = false;
        CanvasNinja.isPointerRelease = false;
        CanvasNinja.isPointerDoubleClick = false;
        CanvasNinja.isPointerPress = false;
        CanvasNinja.isPointerRelease = false;
        CanvasNinja.isPointerHold = false;
        CanvasNinja.isDoubleClick = false;
        CanvasNinja.isPointerDown = false;
        CanvasNinja.isPointerDown = false;
        CanvasNinja.isPointerJustDown = false;
        MapScr.gI().lastSingleClick = 0L;
    }

    public static void clearKeyHold() {
        int n = 0;
        while (true) {
            final boolean[] keyHold = CanvasNinja.keyHold;
            if (n >= keyHold.length) {
                break;
            }
            keyHold[n] = false;
            ++n;
        }
    }

    public static void clearKeyPressed() {
        int n = 0;
        while (true) {
            final boolean[] keyPressed = CanvasNinja.keyPressed;
            if (n >= keyPressed.length) {
                break;
            }
            keyPressed[n] = false;
            ++n;
        }
    }

    public static void clearKeyReleased() {
        for (int i = 0; i < 100; ++i) {
            CanvasNinja.keyReleased[i] = false;
        }
    }

    public static void clearMultiPointer() {
        if (CanvasNinja.pointerReleaseCur != -1) {
            resetPxPy();
            CanvasNinja.pointerRelease[CanvasNinja.pointerReleaseCur] = false;
        }
    }

    public static void connect() {
        MySession.gI().close();
        CanvasNinja.isLogin = true;
        CanvasNinja.isCloseConnect = false;
        CanvasNinja.timeDelayConnect = 0L;
    }

    public static void debug(final String s, final int n) {
    }

    public static boolean draggedXY() {
        return CanvasNinja.isPointerDraggedX || CanvasNinja.isPointerDraggedY;
    }

    public static int dx() {
        return CanvasNinja.pxLast - CanvasNinja.px;
    }

    public static int dy() {
        return CanvasNinja.pyLast - CanvasNinja.py;
    }

    public static void endDlg() {
        Res.outz(1, "debug end dlg");
        CanvasNinja.currentDialog = null;
        mInfoDialog.hide();
    }

    public static void endQuestScreen() {
        CanvasNinja.questScreen = null;
    }

    public static void endSubTab() {
        CanvasNinja.subTab = null;
    }

    public static void endTab() {
        CanvasNinja.currentTab = null;
    }

    private static int getDeltaY(final int n) {
        switch (n) {
            default: {
                return 0;
            }
            case 3: {
                return CanvasNinja.deltaY4;
            }
            case 2: {
                return CanvasNinja.deltaY3;
            }
            case 1: {
                return CanvasNinja.deltaY2;
            }
            case 0: {
                return CanvasNinja.deltaY1;
            }
        }
    }

    private static int getMoreY(final int n, final int n2) {
        if (n2 == 0) {
            switch (n) {
                case 5: {
                    return MyTile.size + 6;
                }
                case 4: {
                    return MyTile.size * 2 / 2 - 5;
                }
                case 3: {
                    return MyTile.size * 10 / 2;
                }
                case 2: {
                    return MyTile.size * 5;
                }
                case 1: {
                    return MyTile.size * 10 / 2;
                }
                case 0: {
                    return MyTile.size * 7;
                }
            }
        } else if (n2 == 8) {
            switch (n) {
                case 5: {
                    return MyTile.size;
                }
                case 4: {
                    return MyTile.size * 2 / 2;
                }
                case 3: {
                    return MyTile.size * 10 / 2;
                }
                case 2: {
                    return MyTile.size * 5;
                }
                case 1: {
                    return MyTile.size * 10 / 2;
                }
                case 0: {
                    return MyTile.size * 7;
                }
            }
        } else if (n2 == 5) {
            switch (n) {
                case 5: {
                    return MyTile.size * 10;
                }
                case 4: {
                    return MyTile.size * 5 / 2;
                }
                case 3: {
                    return MyTile.size * 7 / 2;
                }
                case 2: {
                    return MyTile.size * 9 / 2;
                }
                case 1: {
                    return MyTile.size * 9 / 2;
                }
                case 0: {
                    return MyTile.size * 4;
                }
            }
        } else if (n2 == 4) {
            switch (n) {
                case 5: {
                    return MyTile.size * 10;
                }
                case 4: {
                    return MyTile.size * 3;
                }
                case 3: {
                    return MyTile.size * 10 / 2;
                }
                case 2: {
                    return MyTile.size * 6;
                }
                case 1: {
                    return MyTile.size * 13 / 2;
                }
                case 0: {
                    return MyTile.size * 6;
                }
            }
        }
        return 0;
    }

    public static int getSecond() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    public static TabPurchase getTabPurchase() {
        final TabScr currentTab = CanvasNinja.currentTab;
        TabPurchase tabPurchase;
        if (currentTab != null && currentTab instanceof TabPurchase) {
            tabPurchase = (TabPurchase) currentTab;
        } else {
            tabPurchase = null;
        }
        return tabPurchase;
    }

    public static void initBG(int numBg4, final int numBg5) {
        CanvasNinja.wMap = MyTile.getWMap();
        int hMap = MyTile.getHMap();
        CanvasNinja.hMoreStartBg = 0;
        final int hMapFromTileTopLast = MyTile.getHMapFromTileTopLast();
        final BaseScreen currentScreen = CanvasNinja.currentScreen;
        if (currentScreen instanceof MapScr) {
            CanvasNinja.hMoreStartBg = hMap - hMapFromTileTopLast;
        }
        if (currentScreen instanceof SplashScr) {
            hMap = CanvasNinja.h;
        }
        CanvasNinja.hMap = hMap;
        initSuongMu();
        CanvasNinja.cmyMax = hMapFromTileTopLast - MapScr.gH23 - 1;
        final Image image = LoadDataManager.imgBgs[CanvasNinja.typeBg][0];
        int rWidth;
        if (image != null) {
            rWidth = image.getRWidth();
        } else {
            rWidth = 0;
        }
        CanvasNinja.wBG1 = rWidth;
        final Image image2 = LoadDataManager.imgBgs[CanvasNinja.typeBg][0];
        int rHeight;
        if (image2 != null) {
            rHeight = image2.getRHeight();
        } else {
            rHeight = 0;
        }
        CanvasNinja.hBG1 = rHeight;
        final Image image3 = LoadDataManager.imgBgs[CanvasNinja.typeBg][1];
        int rWidth2;
        if (image3 != null) {
            rWidth2 = image3.getRWidth();
        } else {
            rWidth2 = 0;
        }
        CanvasNinja.wBG2 = rWidth2;
        final Image image4 = LoadDataManager.imgBgs[CanvasNinja.typeBg][1];
        int rHeight2;
        if (image4 != null) {
            rHeight2 = image4.getRHeight();
        } else {
            rHeight2 = 0;
        }
        CanvasNinja.hBG2 = rHeight2;
        final Image image5 = LoadDataManager.imgBgs[CanvasNinja.typeBg][2];
        int rWidth3;
        if (image5 != null) {
            rWidth3 = image5.getRWidth();
        } else {
            rWidth3 = 0;
        }
        CanvasNinja.wBG3 = rWidth3;
        final Image image6 = LoadDataManager.imgBgs[CanvasNinja.typeBg][2];
        int rHeight3;
        if (image6 != null) {
            rHeight3 = image6.getRHeight();
        } else {
            rHeight3 = 0;
        }
        CanvasNinja.hBG3 = rHeight3;
        final Image image7 = LoadDataManager.imgBgs[CanvasNinja.typeBg][3];
        int rWidth4;
        if (image7 != null) {
            rWidth4 = image7.getRWidth();
        } else {
            rWidth4 = 0;
        }
        CanvasNinja.wBG4 = rWidth4;
        final Image image8 = LoadDataManager.imgBgs[CanvasNinja.typeBg][3];
        int rHeight4;
        if (image8 != null) {
            rHeight4 = image8.getRHeight();
        } else {
            rHeight4 = 0;
        }
        CanvasNinja.hBG4 = rHeight4;
        final Image image9 = LoadDataManager.imgBgs[CanvasNinja.typeBg][4];
        int rWidth5;
        if (image9 != null) {
            rWidth5 = image9.getRWidth();
        } else {
            rWidth5 = 0;
        }
        CanvasNinja.wBG5 = rWidth5;
        final Image image10 = LoadDataManager.imgBgs[CanvasNinja.typeBg][4];
        int rHeight5;
        if (image10 != null) {
            rHeight5 = image10.getRHeight();
        } else {
            rHeight5 = 0;
        }
        CanvasNinja.hBG5 = rHeight5;
        final Image image11 = LoadDataManager.imgBgs[CanvasNinja.typeBg][5];
        int rWidth6;
        if (image11 != null) {
            rWidth6 = image11.getRWidth();
        } else {
            rWidth6 = 0;
        }
        CanvasNinja.wBG6 = rWidth6;
        final Image image12 = LoadDataManager.imgBgs[CanvasNinja.typeBg][5];
        int rHeight6;
        if (image12 != null) {
            rHeight6 = image12.getRHeight();
        } else {
            rHeight6 = 0;
        }
        CanvasNinja.hBG6 = rHeight6;
        final Image image13 = LoadDataManager.imgSubBgs[CanvasNinja.typeBg][1];
        int rWidth7;
        if (image13 != null) {
            rWidth7 = image13.getRWidth();
        } else {
            rWidth7 = 0;
        }
        CanvasNinja.wBGSub2 = rWidth7;
        final Image image14 = LoadDataManager.imgSubBgs[CanvasNinja.typeBg][1];
        int rHeight7;
        if (image14 != null) {
            rHeight7 = image14.getRHeight();
        } else {
            rHeight7 = 0;
        }
        CanvasNinja.hSubBG2 = rHeight7;
        final Image image15 = LoadDataManager.imgSubBgs[CanvasNinja.typeBg][2];
        int rWidth8;
        if (image15 != null) {
            rWidth8 = image15.getRWidth();
        } else {
            rWidth8 = 0;
        }
        CanvasNinja.wBGSub3 = rWidth8;
        final Image image16 = LoadDataManager.imgSubBgs[CanvasNinja.typeBg][2];
        int rHeight8;
        if (image16 != null) {
            rHeight8 = image16.getRHeight();
        } else {
            rHeight8 = 0;
        }
        CanvasNinja.hSubBG3 = rHeight8;
        final Image image17 = LoadDataManager.imgSubBgs[CanvasNinja.typeBg][3];
        int rWidth9;
        if (image17 != null) {
            rWidth9 = image17.getRWidth();
        } else {
            rWidth9 = 0;
        }
        CanvasNinja.wBGSub4 = rWidth9;
        final Image image18 = LoadDataManager.imgSubBgs[CanvasNinja.typeBg][3];
        int rHeight9;
        if (image18 != null) {
            rHeight9 = image18.getRHeight();
        } else {
            rHeight9 = 0;
        }
        CanvasNinja.hSubBG4 = rHeight9;
        final Image image19 = LoadDataManager.imgSubBgs[CanvasNinja.typeBg][4];
        int rWidth10;
        if (image19 != null) {
            rWidth10 = image19.getRWidth();
        } else {
            rWidth10 = 0;
        }
        CanvasNinja.wBGSub5 = rWidth10;
        final Image image20 = LoadDataManager.imgSubBgs[CanvasNinja.typeBg][4];
        int rHeight10;
        if (image20 != null) {
            rHeight10 = image20.getRHeight();
        } else {
            rHeight10 = 0;
        }
        CanvasNinja.hSubBG5 = rHeight10;
        final Image image21 = LoadDataManager.imgSubBgs[CanvasNinja.typeBg][5];
        int rWidth11;
        if (image21 != null) {
            rWidth11 = image21.getRWidth();
        } else {
            rWidth11 = 0;
        }
        CanvasNinja.wBGSub6 = rWidth11;
        final Image image22 = LoadDataManager.imgSubBgs[CanvasNinja.typeBg][5];
        int rHeight11;
        if (image22 != null) {
            rHeight11 = image22.getRHeight();
        } else {
            rHeight11 = 0;
        }
        CanvasNinja.hSubBG6 = rHeight11;
        CanvasNinja.color = 7864319;
        if (CanvasNinja.typeBg == 8) {
            final int hMap2 = CanvasNinja.hMap;
            final int hbg1 = CanvasNinja.hBG1;
            final byte size = MyTile.size;
            CanvasNinja.yBG1 = hMap2 - hbg1 - size * 3;
            final int hMap3 = CanvasNinja.hMap;
            CanvasNinja.yBg2 = hMap3 - CanvasNinja.hBG2 - size * 3;
            CanvasNinja.yBg3 = hMap3 - CanvasNinja.hBG3 - size * 3;
            CanvasNinja.yBg4 = hMap3 - CanvasNinja.hBG4 - size * 3;
            CanvasNinja.yBg5 = hMap3 - CanvasNinja.hBG5 - size * 3;
            CanvasNinja.yBg6 = hMap3 - CanvasNinja.hBG6 - size * 3;
        }
        final int typeBg = CanvasNinja.typeBg;
        if (typeBg == 0) {
            final byte size2 = MyTile.size;
            final int n = CanvasNinja.yBg3 = CanvasNinja.hMap - CanvasNinja.hBG3 - CanvasNinja.hMoreStartBg + MyTile.size * 2;
            final int hbg2 = CanvasNinja.hBG1;
            final int hbg3 = CanvasNinja.hBG2;
            CanvasNinja.yBG1 = n - hbg2 - hbg3 / 3;
            CanvasNinja.yBg2 = n - hbg3 + 10;
            CanvasNinja.yBg6 = n - CanvasNinja.hBG6 + 2;
            CanvasNinja.cmyMin = CanvasNinja.cmyMax - size2;
            CanvasNinja.disTemp = size2 - 10;
        } else if (typeBg == 1) {
            final byte size3 = MyTile.size;
            final int n2 = size3 * 2;
            final int n3 = CanvasNinja.yBg4 = CanvasNinja.hMap - CanvasNinja.hBG4 - CanvasNinja.hMoreStartBg;
            final int hbg4 = CanvasNinja.hBG3;
            CanvasNinja.yBg3 = n3 - hbg4 + n2;
            final int hbg5 = CanvasNinja.hBG2;
            final int n4 = CanvasNinja.yBg2 = n3 - hbg5 + n2;
            CanvasNinja.ySubBg2 = n3 - CanvasNinja.hSubBG2 + n2;
            final int hSubBG3 = CanvasNinja.hSubBG3;
            CanvasNinja.ySubBg3 = n3 - hSubBG3 + n2;
            CanvasNinja.ySubBgMax3 = n3 - hSubBG3 + n2;
            CanvasNinja.yBG3Max = n3 - hbg4 + n2;
            CanvasNinja.cmyMin = CanvasNinja.cmyMax - n2;
            CanvasNinja.disTemp = n2 - size3 * 3 / 2;
            CanvasNinja.yBG1 = n4 - CanvasNinja.hBG1 + hbg5 / 8;
        } else if (typeBg == 2) {
            final byte size4 = MyTile.size;
            final int hMap4 = CanvasNinja.hMap;
            final int hbg6 = CanvasNinja.hBG4;
            final int hMoreStartBg = CanvasNinja.hMoreStartBg;
            final byte size5 = MyTile.size;
            final int n5 = CanvasNinja.yBg4 = hMap4 - hbg6 - hMoreStartBg + size5 * 3 / 2;
            CanvasNinja.yBg3 = n5 - CanvasNinja.hBG3 + size5 + 10;
            CanvasNinja.yBg2 = n5 - CanvasNinja.hBG2 + size5 + 10;
            CanvasNinja.yBG1 = n5 - CanvasNinja.hBG1 + 10;
            CanvasNinja.cmyMin = CanvasNinja.cmyMax - size4;
            CanvasNinja.disTemp = size4 - 10;
        } else if (typeBg == 3) {
            final byte size6 = MyTile.size;
            final int hMap5 = CanvasNinja.hMap;
            final int hbg7 = CanvasNinja.hBG3;
            final int hMoreStartBg2 = CanvasNinja.hMoreStartBg;
            CanvasNinja.yBg3 = hMap5 - hbg7 - hMoreStartBg2;
            CanvasNinja.yBG1 = hMap5 - CanvasNinja.hBG1 - hMoreStartBg2;
            CanvasNinja.yBg2 = hMap5 - CanvasNinja.hBG2 - hMoreStartBg2;
            CanvasNinja.yBg4 = hMap5 - CanvasNinja.hBG4 - hMoreStartBg2;
            CanvasNinja.disTemp = size6 - 10;
            CanvasNinja.color = 3750952;
        } else if (typeBg == 4) {
            final byte size7 = MyTile.size;
            final int n6 = size7 * 3;
            final int n7 = CanvasNinja.yBg4 = CanvasNinja.hMap - CanvasNinja.hBG4 - CanvasNinja.hMoreStartBg;
            final int hbg8 = CanvasNinja.hBG3;
            CanvasNinja.yBg3 = n7 - hbg8 + n6 * 2 - size7;
            final int hbg9 = CanvasNinja.hBG2;
            final int n8 = CanvasNinja.yBg2 = n7 - hbg9 + n6 * 2 - size7;
            CanvasNinja.ySubBg2 = n7 - CanvasNinja.hSubBG2 + n6;
            final int hSubBG4 = CanvasNinja.hSubBG3;
            CanvasNinja.ySubBg3 = n7 - hSubBG4 + n6;
            CanvasNinja.ySubBgMax3 = n7 - hSubBG4 + n6;
            CanvasNinja.yBG3Max = n7 - hbg8 + n6;
            CanvasNinja.cmyMin = CanvasNinja.cmyMax - n6;
            CanvasNinja.disTemp = n6 - size7 * 3 / 2;
            CanvasNinja.yBG1 = n8 - CanvasNinja.hBG1 + hbg9 / 8 + n6 + size7;
        } else if (typeBg == 5) {
            final byte size8 = MyTile.size;
            final int n9 = size8 * 2;
            final int n10 = CanvasNinja.yBg4 = CanvasNinja.hMap - CanvasNinja.hBG4 - CanvasNinja.hMoreStartBg;
            final int hbg10 = CanvasNinja.hBG3;
            CanvasNinja.yBg3 = n10 - hbg10 + n9 * 3;
            final int hbg11 = CanvasNinja.hBG2;
            final int n11 = CanvasNinja.yBg2 = n10 - hbg11 + n9 * 3;
            CanvasNinja.ySubBg2 = n9 * 3 + n11;
            final int hSubBG5 = CanvasNinja.hSubBG3;
            CanvasNinja.ySubBg3 = n10 - hSubBG5 + n9;
            CanvasNinja.ySubBgMax3 = n10 - hSubBG5 + n9;
            CanvasNinja.yBG3Max = n10 - hbg10 + n9;
            CanvasNinja.cmyMin = CanvasNinja.cmyMax - n9;
            CanvasNinja.disTemp = n9 - size8 * 3 / 2;
            CanvasNinja.yBG1 = n11 - CanvasNinja.hBG1 + hbg11 / 8 + n9 + size8;
        }
        CanvasNinja.numBg4 = numBg4;
        CanvasNinja.numBg5 = numBg5;
        CanvasNinja.speedBg = new int[]{6, 5, 4, 3, 2, 1};
        if (CanvasNinja.wBG3 > 0) {
            numBg4 = MapScr.gW / CanvasNinja.wBG3 + 5;
        } else {
            numBg4 = 0;
        }
        CanvasNinja.xRandomBG3 = new int[numBg4];
        CanvasNinja.xRandomBG4 = new int[numBg4];
        numBg4 = 0;
        while (true) {
            final int[] xRandomBG3 = CanvasNinja.xRandomBG3;
            if (numBg4 >= xRandomBG3.length) {
                break;
            }
            xRandomBG3[numBg4] = Res.random(0, 10);
            CanvasNinja.xRandomBG4[numBg4] = Res.random(0, 30);
            ++numBg4;
        }
    }

    private void initCharList() {
        final char[][] array = CanvasNinja.charList = new char[27][];
        final StringBuilder sb = new StringBuilder();
        sb.append(this.charString);
        sb.append(this.charNor);
        sb.append(this.charNor.toUpperCase());
        array[0] = sb.toString().toCharArray();
        CanvasNinja.charList[1] = this.charA.toCharArray();
        CanvasNinja.charList[2] = this.charA2.toCharArray();
        CanvasNinja.charList[3] = this.charA3.toCharArray();
        CanvasNinja.charList[4] = this.charD.toCharArray();
        CanvasNinja.charList[5] = this.charE.toCharArray();
        CanvasNinja.charList[6] = this.charE2.toCharArray();
        CanvasNinja.charList[7] = this.charI.toCharArray();
        CanvasNinja.charList[8] = this.charO.toCharArray();
        CanvasNinja.charList[9] = this.charO2.toCharArray();
        CanvasNinja.charList[10] = this.charO3.toCharArray();
        CanvasNinja.charList[11] = this.charU.toCharArray();
        CanvasNinja.charList[12] = this.charU2.toCharArray();
        CanvasNinja.charList[13] = this.charY.toCharArray();
        CanvasNinja.charList[14] = this.charA.toUpperCase().toCharArray();
        CanvasNinja.charList[15] = this.charA2.toUpperCase().toCharArray();
        CanvasNinja.charList[16] = this.charA3.toUpperCase().toCharArray();
        CanvasNinja.charList[17] = this.charD.toUpperCase().toCharArray();
        CanvasNinja.charList[18] = this.charE.toUpperCase().toCharArray();
        CanvasNinja.charList[19] = this.charE2.toUpperCase().toCharArray();
        CanvasNinja.charList[20] = this.charI.toUpperCase().toCharArray();
        CanvasNinja.charList[21] = this.charO.toUpperCase().toCharArray();
        CanvasNinja.charList[22] = this.charO2.toUpperCase().toCharArray();
        CanvasNinja.charList[23] = this.charO3.toUpperCase().toCharArray();
        CanvasNinja.charList[24] = this.charU.toUpperCase().toCharArray();
        CanvasNinja.charList[25] = this.charU2.toUpperCase().toCharArray();
        CanvasNinja.charList[26] = this.charY.toUpperCase().toCharArray();
    }

    public static void initSuongMu() {
        CanvasNinja.vecSkys = new Vector<EffSky>();
        final int rWidth = LoadDataManager.imgEffectMap[0].getRWidth();
        final byte[] array2;
        final byte[] array = array2 = new byte[2];
        array2[0] = 2;
        array2[1] = 3;
        CanvasNinja.vecSkys = new Vector<EffSky>();
        for (int i = 0; i < CanvasNinja.wMap; i += rWidth + 5) {
            CanvasNinja.vecSkys.add(new EffSky(i, CanvasNinja.hMap - CanvasNinja.hMoreStartBg + MyTile.size / 2 + Res.random(0, 32), Res.random(1, 2), 0, LoadDataManager.imgEffectMap[0], array));
            CanvasNinja.vecSkys.add(new EffSky(i, CanvasNinja.hMap - CanvasNinja.hMoreStartBg + MyTile.size * 3, Res.random(1, 2), 0, LoadDataManager.imgEffectMap[0], array));
        }
    }

    public static boolean isCanUpdateCurrentScr() {
        return CanvasNinja.currentDialog == null && CanvasNinja.menuMain == null && CanvasNinja.subTab == null && CanvasNinja.currentTab == null && CanvasNinja.questScreen == null;
    }

    public static boolean isCheckClickAll() {
        return isPointerRelease() || draggedXY() || CanvasNinja.isPointerFirstClick;
    }

    public static boolean isClickKeyMove() {
        final boolean[] keyHold = CanvasNinja.keyHold;
        return keyHold[2] || keyHold[4] || keyHold[6] || keyHold[8];
    }

    public static boolean isFocusAt(final int n, final int n2, final int n3, final int n4) {
        return isPoint(n, n2, n3, n4);
    }

    public static boolean isHoldPress() {
        return System.currentTimeMillis() - CanvasNinja.lastTimePress >= 800L;
    }

    public static boolean isPaint(int cmy, final int n) {
        final int cmx = MapScr.cmx;
        if (cmy < cmx) {
            return false;
        }
        if (cmy > cmx + MapScr.gW) {
            return false;
        }
        cmy = MapScr.cmy;
        return n >= cmy && n <= cmy + MapScr.gH + 30;
    }

    public static boolean isPoint(int py, final int n, final int n2, final int n3) {
        final int px = CanvasNinja.px;
        if (px >= py && px <= py + n2) {
            py = CanvasNinja.py;
            if (py >= n && py <= n + n3) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPointFirst(int pyFirst, final int n, final int n2, final int n3) {
        final int pxFirst = CanvasNinja.pxFirst;
        if (pxFirst >= pyFirst && pxFirst <= pyFirst + n2) {
            pyFirst = CanvasNinja.pyFirst;
            if (pyFirst >= n && pyFirst <= n + n3) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPointHover(int hy, final int n, final int n2, final int n3) {
        final int hx = CanvasNinja.hx;
        if (hx >= hy && hx <= hy + n2) {
            hy = CanvasNinja.hy;
            if (hy >= n && hy <= n + n3) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPointNew(int py, final int n, final int n2, final int n3, int px) {
        final Pointer pointer = CanvasNinja.pointerNew[px];
        px = pointer.px;
        if (px >= py && px <= py + n2) {
            py = pointer.py;
            if (py >= n && py <= n + n3) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPointer(int py, final int n, final int n2, final int n3) {
        if (!CanvasNinja.isPointerDown && !CanvasNinja.isPointerRelease) {
            return false;
        }
        final int px = CanvasNinja.px;
        if (px >= py && px <= py + n2) {
            py = CanvasNinja.py;
            if (py >= n && py <= n + n3) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPointerHold(int py, final int n, final int n2, final int n3) {
        if (CanvasNinja.isPointerHold) {
            final int px = CanvasNinja.px;
            if (px >= py && px <= py + n2) {
                py = CanvasNinja.py;
                if (py >= n && py <= n + n3) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isPointerHoldIn(int py, final int n, final int n2, final int n3) {
        if (!CanvasNinja.isPointerDown && !CanvasNinja.isPointerRelease) {
            return false;
        }
        final int px = CanvasNinja.px;
        if (px >= py && px <= py + n2) {
            py = CanvasNinja.py;
            if (py >= n && py <= n + n3) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPointerHoldOrHover(final int n, final int n2, final int n3, final int n4) {
        return isPointerHold(n, n2, n3, n4) || isPointHover(n, n2, n3, n4);
    }

    public static boolean isPointerRelease() {
        return CanvasNinja.isPointerRelease && notDraggedXY();
    }

    public static void loadBG(final int typeBg) {
        CanvasNinja.typeBg = typeBg;
        initBG(3, 3);
    }

    public static void loadCurrMap(final byte b, final int n) {
        final StringBuilder sb = new StringBuilder();
        sb.append("is loading map = ");
        sb.append(Player.isLoadingMap);
        Res.outz(sb.toString());
        MapScr.isChangeZone = false;
        MapScr.lockTick = 0;
        Res.outz("is nn = 1");
        Res.outz("is nn = 2");
        final int n2 = -1;
        int cx;
        if (b == 1) {
            cx = Player.myCharz().cx;
        } else {
            cx = -1;
        }
        int n3;
        if (b == 0) {
            n3 = n2;
        } else {
            n3 = 0;
        }
        MapScr.loadCamera(false, cx, n3);
        Res.outz("is nn = 3");
        MyTile.loadMainTile();
        Res.outz("is nn = 4");
        MyTile.loadMap(n);
        Res.outz("is nn = 5");
        Player.myCharz().cvx = 0;
        Player.myCharz().statusMe = 9;
        Player.myCharz().currentMovePoint = null;
        Player.myCharz().mobFocus = null;
        Player.myCharz().charFocus = null;
        Player.myCharz().npcFocus = null;
        Player.myCharz().bgItemFocus = null;
        Player.myCharz().itemFocus = null;
        clearAllPointerEvent();
        MapScr.gI().loadGameScr();
        Player.isLockKey = false;
        clearKeyHold();
        clearKeyPressed();
        MapScr.gI().dHP = Player.myCharz().cHP;
        MapScr.gI().dMP = Player.myCharz().cMP;
        Player.ischangingMap = false;
        MapScr.gI().switchToMe();
        mInfoDialog.hide();
        final String mapName = MyTile.mapName;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(SupportTranslate.getTextLangue("zone"));
        sb2.append(" ");
        sb2.append(MyTile.zoneID);
        mInfoDialog.show(mapName, sb2.toString(), 30);
        endDlg();
        CanvasNinja.isLoading = false;
        debug("SA75x9", 2);
        Res.outz("============ DEBUG_TIME_LOAD_DATA STOP = DONE");
    }

    public static void mapKeyPressPCNew(final int n) {
        switch (n) {
            case 123: {
                CanvasNinja.keyHoldPC[12] = true;
                CanvasNinja.isFirstPress[12] = true;
                CanvasNinja.timePressPC[12] = mSystem.currentTimeMillis();
            }
            case 60: {
                CanvasNinja.keyHoldPC[10] = true;
                CanvasNinja.isFirstPress[10] = true;
                CanvasNinja.timePressPC[10] = mSystem.currentTimeMillis();
            }
            case 59: {
                CanvasNinja.keyHoldPC[9] = true;
                CanvasNinja.isFirstPress[9] = true;
                CanvasNinja.timePressPC[9] = mSystem.currentTimeMillis();
            }
            case 5: {
                CanvasNinja.keyHoldPC[5] = true;
                CanvasNinja.isFirstPress[5] = true;
                CanvasNinja.timePressPC[5] = mSystem.currentTimeMillis();
                break;
            }
            case 3: {
                CanvasNinja.keyHoldPC[11] = true;
                CanvasNinja.isFirstPress[11] = true;
                CanvasNinja.timePressPC[11] = mSystem.currentTimeMillis();
            }
            case -1: {
                CanvasNinja.keyHoldPC[2] = true;
                CanvasNinja.isFirstPress[2] = true;
                CanvasNinja.timePressPC[2] = mSystem.currentTimeMillis();
                break;
            }
            case -2: {
                CanvasNinja.keyHoldPC[8] = true;
                CanvasNinja.isFirstPress[8] = true;
                CanvasNinja.timePressPC[8] = mSystem.currentTimeMillis();
                break;
            }
            case -3: {
                CanvasNinja.keyHoldPC[4] = true;
                CanvasNinja.isFirstPress[4] = true;
                CanvasNinja.timePressPC[4] = mSystem.currentTimeMillis();
                break;
            }
            case -4: {
                CanvasNinja.keyHoldPC[6] = true;
                CanvasNinja.isFirstPress[6] = true;
                CanvasNinja.timePressPC[6] = mSystem.currentTimeMillis();
                break;
            }
            case -9: {
                CanvasNinja.keyHoldPC[1] = true;
                CanvasNinja.isFirstPress[1] = true;
                CanvasNinja.timePressPC[1] = mSystem.currentTimeMillis();
                break;
            }
        }
    }

    public static boolean notDraggedXY() {
        return (!CanvasNinja.isPointerDraggedX && mSystem.currentTimeMillis() > CanvasNinja.timeDraggedX) || (!CanvasNinja.isPointerDraggedY && mSystem.currentTimeMillis() > CanvasNinja.timeDraggedY);
    }

    public static void paintAllLayers(final mGraphics mGraphics, final int n) {
        int n2 = 0;
        while (true) {
            int n3 = 5;
            if (n >= 4) {
                if (n != 8) {
                    n3 = 4;
                }
            } else if (n != 0) {
                n3 = 3;
            }
            if (n2 > n3) {
                break;
            }
            final int n4 = CanvasNinja.speedBg[n2];
            final int rWidth = LoadDataManager.imgBgs[n][n2].getRWidth();
            final int rHeight = LoadDataManager.imgBgs[n][n2].getRHeight();
            final int moreY = getMoreY(n2, n);
            final int hMap = CanvasNinja.hMap;
            int n5;
            if (!LoginScr.isLogOut) {
                n5 = MyTile.size * 2;
            } else {
                n5 = MyTile.size * -2;
            }
            final int hMap2 = CanvasNinja.hMap;
            final int deltaY = getDeltaY(n2);
            int n6 = 0;
            for (int i = -(MapScr.cmx + 0 >> CanvasNinja.speedBg[n2] % rWidth); i <= MapScr.gW + rWidth; i += rWidth) {
                final Image image = LoadDataManager.imgBgs[n][n2];
                final float n7 = (float) i;
                final int cmy = MapScr.cmy;
                final float n8 = (float) (hMap - n5 - rHeight - moreY - cmy);
                final float n9 = (hMap2 - 401 - cmy) * n4 * 1.0f / 10.0f;
                int n10;
                if (deltaY > 0) {
                    final int cmy2 = CanvasNinja.cmy;
                    n10 = (cmy2 >> deltaY) - cmy2;
                } else {
                    n10 = 0;
                }
                mGraphics.drawImage(image, n7, n8 - n9 - n10, 0);
                ++n6;
            }
            ++n2;
        }
    }

    public static void paintBGGameScr(final mGraphics mGraphics) {
        if (CanvasNinja.typeBg == 1) {
            CanvasNinja.moreY = 0;
            final int cmy = MapScr.cmy;
            final int cmyMax = CanvasNinja.cmyMax;
            if (cmy < cmyMax) {
                final int n = CanvasNinja.moreY = (cmyMax - cmy) / 5;
                final int disTemp = CanvasNinja.disTemp;
                if (n > disTemp) {
                    CanvasNinja.moreY = disTemp;
                }
            }
        } else {
            CanvasNinja.moreY = 0;
        }
        mGraphics.setColor(CanvasNinja.color);
        mGraphics.fillRect(0, 0, CanvasNinja.w, CanvasNinja.h);
        final int typeBg = CanvasNinja.typeBg;
        if ((typeBg == 5 || typeBg == 4 || typeBg == 8 || typeBg == 0) && MyTile.mapID != -1) {
            paintAllLayers(mGraphics, CanvasNinja.typeBg);
        } else {
            paintNen1(mGraphics);
            paintNen2(mGraphics);
            paintNen3(mGraphics);
            paintNen4(mGraphics);
        }
        mGraphics.translate(-MapScr.cmx, -MapScr.cmy);
        resetTrans(mGraphics);
        final int length = LoadDataManager.imgBgs[CanvasNinja.typeBg].length;
    }

    public static void paintBox(final mGraphics mGraphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        mGraphics.setColor(16776960);
        mGraphics.fillRect(n, n2, n3, n5);
        mGraphics.fillRect(n, n2, n5, n4);
        mGraphics.fillRect(n + n3, n2, n5, n4);
        mGraphics.fillRect(n, n2 + n4, n3, n5);
    }

    public static void paintImgMoveByHold(final mGraphics mGraphics) {
        final mSprite imgHoldToMove = CanvasNinja.imgHoldToMove;
        if (imgHoldToMove != null) {
            mGraphics.drawSprite(imgHoldToMove, (float) (CanvasNinja.px - imgHoldToMove.getWidth() / 2), (float) (CanvasNinja.py - CanvasNinja.imgHoldToMove.getHeight() / 2), 0, true, 70);
        }
    }

    public static void paintImgMoveTutorial(final mGraphics mGraphics) {
        final mSprite imgHoldToMoveTutorial = CanvasNinja.imgHoldToMoveTutorial;
        if (imgHoldToMoveTutorial != null) {
            mGraphics.drawSprite(imgHoldToMoveTutorial, (float) (CanvasNinja.xHoldMoveTutorial - imgHoldToMoveTutorial.getWidth() / 2), (float) (CanvasNinja.yHoldMoveTutorial - CanvasNinja.imgHoldToMoveTutorial.getHeight() / 2), 0, true, 70);
        }
    }

    public static void paintLuoi(final mGraphics mGraphics, final int n, final int n2, final int n3, final int n4, final int n5, int i, final int n6) {
        mGraphics.setColor(16776960);
        for (int j = 0; j <= n3; j += i) {
            mGraphics.fillRect(j + n, n2, n5, n4);
        }
        for (i = 0; i <= n4; i += n6) {
            mGraphics.fillRect(n, n2 + i, n3, n5);
        }
    }

    public static void paintNen1(final mGraphics mGraphics) {
        boolean b = false;
        final int hbg1 = CanvasNinja.hBG1;
        final int hMap = CanvasNinja.hMap;
        if (hbg1 < hMap) {
            b = true;
        }
        if (CanvasNinja.typeBg == 3) {
            for (int i = -((MapScr.cmx + 0 >> CanvasNinja.speedBg[0]) % CanvasNinja.wBG1); i <= MapScr.gW + CanvasNinja.wBG1; i += CanvasNinja.wBG1) {
                mGraphics.drawImage(LoadDataManager.imgBgs[CanvasNinja.typeBg][0], (float) i, (float) (CanvasNinja.yBG1 - MapScr.cmy + CanvasNinja.moreY), 0);
            }
        } else {
            if (b) {
                final int n = (hMap - CanvasNinja.hBG3 - hbg1 - CanvasNinja.hMoreStartBg) / (hbg1 / 2);
                for (int j = -(MapScr.cmx + 0 >> CanvasNinja.speedBg[0] % CanvasNinja.wBG1); j < MapScr.gW; j += CanvasNinja.wBG1) {
                    for (int k = 0; k < n + 1; ++k) {
                        final FrameImage frameImage = LoadDataManager.frameBGs[CanvasNinja.typeBg][0];
                        final float n2 = (float) j;
                        final int hMap2 = CanvasNinja.hMap;
                        final int hbg2 = CanvasNinja.hBG3;
                        final int hbg3 = CanvasNinja.hBG1;
                        final int n3 = hbg3 / 2;
                        final int n4 = hbg3 / 2;
                        final int hMoreStartBg = CanvasNinja.hMoreStartBg;
                        final int deltaY1 = CanvasNinja.deltaY1;
                        int n5;
                        if (deltaY1 > 0) {
                            n5 = CanvasNinja.cmy >> deltaY1;
                        } else {
                            n5 = 0;
                        }
                        frameImage.drawFrame(0, n2, (float) (hMap2 - hbg2 - hbg3 - n3 - n4 * k - hMoreStartBg - n5), mGraphics);
                    }
                }
            }
            for (int l = -((MapScr.cmx + 0 >> CanvasNinja.speedBg[0]) % CanvasNinja.wBG1); l < MapScr.gW; l += CanvasNinja.wBG1) {
                final Image image = LoadDataManager.imgBgs[CanvasNinja.typeBg][0];
                final float n6 = (float) l;
                final int ybg1 = CanvasNinja.yBG1;
                final int cmy = MapScr.cmy;
                final int deltaY2 = CanvasNinja.deltaY1;
                int n7;
                if (deltaY2 > 0) {
                    n7 = CanvasNinja.cmy >> deltaY2;
                } else {
                    n7 = 0;
                }
                mGraphics.drawImage(image, n6, (float) (ybg1 - cmy - n7), 0);
            }
        }
    }

    public static void paintNen2(final mGraphics mGraphics) {
        int n = 0;
        int n2;
        if ((n2 = MapScr.cmy) > CanvasNinja.h) {
            n2 = CanvasNinja.h;
        }
        int n6;
        for (int i = -(MapScr.cmx + 0 >> CanvasNinja.speedBg[1] % CanvasNinja.wBG2); i <= MapScr.gW + CanvasNinja.wBG2; i = n6 + CanvasNinja.wBG2) {
            final Image image = LoadDataManager.imgSubBgs[CanvasNinja.typeBg][1];
            if (image != null && n == 1) {
                final float n3 = (float) i;
                final int ySubBg2 = CanvasNinja.ySubBg2;
                final int cmy = MapScr.cmy;
                final int n4 = CanvasNinja.moreY / 2;
                final int deltaY2 = CanvasNinja.deltaY2;
                int n5;
                if (deltaY2 > 0) {
                    n5 = n2 >> deltaY2;
                } else {
                    n5 = 0;
                }
                mGraphics.drawImage(image, n3, (float) (ySubBg2 - cmy - n4 - n5), 0);
                n6 = i + (CanvasNinja.wBGSub2 - CanvasNinja.wBG2);
                ++n;
            } else {
                final Image image2 = LoadDataManager.imgBgs[CanvasNinja.typeBg][1];
                final float n7 = (float) i;
                final int yBg2 = CanvasNinja.yBg2;
                final int cmy2 = MapScr.cmy;
                final int n8 = CanvasNinja.moreY / 2;
                final int deltaY3 = CanvasNinja.deltaY2;
                int n9;
                if (deltaY3 > 0) {
                    n9 = n2 >> deltaY3;
                } else {
                    n9 = 0;
                }
                mGraphics.drawImage(image2, n7, (float) (yBg2 - cmy2 - n8 - n9), 0);
                final int n10 = ++n;
                n6 = i;
                if (CanvasNinja.typeBg == 0) {
                    final Image image3 = LoadDataManager.imgBgs[CanvasNinja.typeBg][5];
                    n = n10;
                    n6 = i;
                    if (image3 != null) {
                        final float n11 = (float) (i + 20);
                        final int yBg3 = CanvasNinja.yBg3;
                        final int hbg6 = CanvasNinja.hBG6;
                        final int cmy3 = MapScr.cmy;
                        final int moreY = CanvasNinja.moreY;
                        final int deltaY4 = CanvasNinja.deltaY2;
                        int n12;
                        if (deltaY4 > 0) {
                            n12 = n2 >> deltaY4;
                        } else {
                            n12 = 0;
                        }
                        mGraphics.drawImage(image3, n11, (float) (yBg3 + 2 - hbg6 - cmy3 + moreY - n12), 0);
                        n6 = i;
                        n = n10;
                    }
                }
            }
        }
    }

    public static void paintNen3(final mGraphics mGraphics) {
        final int cmy = MapScr.cmy;
        final int typeBg = CanvasNinja.typeBg;
        if (typeBg == 0) {
            for (int i = -(MapScr.cmx + 0 >> CanvasNinja.speedBg[2] % CanvasNinja.wBG3); i <= MapScr.gW + CanvasNinja.wBG3; i += CanvasNinja.wBG3) {
                final Image image = LoadDataManager.imgBgs[CanvasNinja.typeBg][2];
                final float n = (float) i;
                final int yBg3 = CanvasNinja.yBg3;
                final int cmy2 = MapScr.cmy;
                final int moreY = CanvasNinja.moreY;
                final int deltaY3 = CanvasNinja.deltaY3;
                int n2;
                if (deltaY3 > 0) {
                    n2 = cmy >> deltaY3;
                } else {
                    n2 = 0;
                }
                mGraphics.drawImage(image, n, (float) (yBg3 - cmy2 + moreY - n2), 0);
                final Image image2 = LoadDataManager.imgBgs[CanvasNinja.typeBg][3];
                final float n3 = (float) i;
                final int yBg4 = CanvasNinja.yBg3;
                final int hbg4 = CanvasNinja.hBG4;
                final int cmy3 = MapScr.cmy;
                final int moreY2 = CanvasNinja.moreY;
                final int deltaY4 = CanvasNinja.deltaY4;
                int n4;
                if (deltaY4 > 0) {
                    n4 = cmy >> deltaY4;
                } else {
                    n4 = 0;
                }
                mGraphics.drawImage(image2, n3, (float) (yBg4 + 10 - hbg4 - cmy3 + moreY2 - n4), 0);
                final Image image3 = LoadDataManager.imgBgs[CanvasNinja.typeBg][4];
                if (image3 != null) {
                    final float n5 = (float) (i + 20);
                    final int yBg5 = CanvasNinja.yBg3;
                    final int hbg5 = CanvasNinja.hBG5;
                    final int cmy4 = MapScr.cmy;
                    final int moreY3 = CanvasNinja.moreY;
                    final int deltaY5 = CanvasNinja.deltaY5;
                    int n6;
                    if (deltaY5 > 0) {
                        n6 = cmy >> deltaY5;
                    } else {
                        n6 = 0;
                    }
                    mGraphics.drawImage(image3, n5, (float) (yBg5 + 30 - hbg5 - cmy4 + moreY3 - n6), 0);
                }
            }
        } else if (typeBg == 1) {
            int n7 = 0;
            int n15;
            for (int j = -(MapScr.cmx + 0 >> CanvasNinja.speedBg[2] % CanvasNinja.wBG3); j <= MapScr.gW + CanvasNinja.wBG3; j = n15) {
                final Image[] array = LoadDataManager.imgSubBgs[CanvasNinja.typeBg];
                int n9;
                int n10;
                if (array[1] != null && n7 == 1) {
                    mGraphics.drawImage(array[2], (float) j, (float) (CanvasNinja.ySubBg3 - MapScr.cmy - CanvasNinja.moreY / 2), 0);
                    final int n8 = j + (CanvasNinja.wBGSub3 - CanvasNinja.wBG3);
                    n9 = n7 + 1;
                    n10 = n8;
                } else {
                    final Image image4 = LoadDataManager.imgBgs[CanvasNinja.typeBg][2];
                    final float n11 = (float) j;
                    final int yBg6 = CanvasNinja.yBg3;
                    final int cmy5 = MapScr.cmy;
                    final int n12 = CanvasNinja.moreY / 2;
                    final int deltaY6 = CanvasNinja.deltaY3;
                    int n13;
                    if (deltaY6 > 0) {
                        n13 = cmy >> deltaY6;
                    } else {
                        n13 = 0;
                    }
                    mGraphics.drawImage(image4, n11, (float) (yBg6 - cmy5 - n12 - n13), 0);
                    final int n14 = n7 + 1;
                    n10 = j;
                    n9 = n14;
                }
                n15 = n10 + CanvasNinja.wBG3;
                n7 = n9;
            }
        } else {
            for (int k = -((MapScr.cmx + 0 >> CanvasNinja.speedBg[2]) % CanvasNinja.wBG3); k <= MapScr.gW + CanvasNinja.wBG3; k += CanvasNinja.wBG3) {
                final Image image5 = LoadDataManager.imgBgs[CanvasNinja.typeBg][2];
                final float n16 = (float) k;
                final int yBg7 = CanvasNinja.yBg3;
                final int cmy6 = MapScr.cmy;
                final int moreY4 = CanvasNinja.moreY;
                final int deltaY7 = CanvasNinja.deltaY3;
                int n17;
                if (deltaY7 > 0) {
                    n17 = cmy >> deltaY7;
                } else {
                    n17 = 0;
                }
                mGraphics.drawImage(image5, n16, (float) (yBg7 - cmy6 + moreY4 - n17), 0);
            }
        }
    }

    public static void paintNen4(final mGraphics mGraphics) {
        final int cmy = MapScr.cmy;
        final int typeBg = CanvasNinja.typeBg;
        if (typeBg != 0) {
            if (typeBg == 1) {
                for (int i = -((MapScr.cmx + 0 >> CanvasNinja.speedBg[3]) % CanvasNinja.wBG4); i < MapScr.gW; i += CanvasNinja.wBG4) {
                    final Image image = LoadDataManager.imgBgs[CanvasNinja.typeBg][3];
                    final float n = (float) i;
                    final int yBg4 = CanvasNinja.yBg4;
                    final int cmy2 = MapScr.cmy;
                    final int moreY = CanvasNinja.moreY;
                    final int deltaY4 = CanvasNinja.deltaY4;
                    int n2;
                    if (deltaY4 > 0) {
                        n2 = cmy >> deltaY4;
                    } else {
                        n2 = 0;
                    }
                    mGraphics.drawImage(image, n, (float) (yBg4 - cmy2 + moreY - n2), 0);
                }
            } else if (typeBg == 3) {
                for (int j = -((MapScr.cmx + 0 >> CanvasNinja.speedBg[3]) % CanvasNinja.wBG4); j < MapScr.gW; j += CanvasNinja.wBG4) {
                    mGraphics.setColor(CanvasNinja.color);
                    final int yBg5 = CanvasNinja.yBg3;
                    final int cmy3 = MapScr.cmy;
                    final int moreY2 = CanvasNinja.moreY;
                    final int deltaY5 = CanvasNinja.deltaY3;
                    int n3;
                    if (deltaY5 > 0) {
                        n3 = cmy >> deltaY5;
                    } else {
                        n3 = 0;
                    }
                    mGraphics.fillRect(j, yBg5 - cmy3 + moreY2 - n3, CanvasNinja.w, 50);
                    final Image image2 = LoadDataManager.imgBgs[CanvasNinja.typeBg][3];
                    final float n4 = (float) j;
                    final int yBg6 = CanvasNinja.yBg4;
                    final int cmy4 = MapScr.cmy;
                    final int moreY3 = CanvasNinja.moreY;
                    final int deltaY6 = CanvasNinja.deltaY4;
                    int n5;
                    if (deltaY6 > 0) {
                        n5 = cmy >> deltaY6;
                    } else {
                        n5 = 0;
                    }
                    mGraphics.drawImage(image2, n4, (float) (yBg6 - cmy4 + moreY3 - n5), 0);
                }
            } else {
                for (int k = -((MapScr.cmx + 0 >> CanvasNinja.speedBg[3]) % CanvasNinja.wBG4); k < MapScr.gW; k += CanvasNinja.wBG4) {
                    final Image image3 = LoadDataManager.imgBgs[CanvasNinja.typeBg][3];
                    final float n6 = (float) k;
                    final int yBg7 = CanvasNinja.yBg4;
                    final int cmy5 = MapScr.cmy;
                    final int moreY4 = CanvasNinja.moreY;
                    final int deltaY7 = CanvasNinja.deltaY4;
                    int n7;
                    if (deltaY7 > 0) {
                        n7 = cmy >> deltaY7;
                    } else {
                        n7 = 0;
                    }
                    mGraphics.drawImage(image3, n6, (float) (yBg7 - cmy5 + moreY4 - n7), 0);
                }
            }
        }
    }

    public static void paintShukiren(final int n, final int n2, final mGraphics mGraphics) {
        if (++CanvasNinja.waitTick == 10000) {
            CanvasNinja.waitTick = 0;
        }
        if (CanvasNinja.waitTick % 1 == 0 && ++CanvasNinja.f > 4 - 1) {
            CanvasNinja.f = 0;
        }
        final Image imgShuriken = CanvasNinja.imgShuriken;
        mGraphics.drawRegion(imgShuriken, 0.0f, (float) (CanvasNinja.f * imgShuriken.getRHeight() / 4), (float) CanvasNinja.imgShuriken.getRWidth(), (float) (CanvasNinja.imgShuriken.getRHeight() / 4), 0, (float) n, (float) n2, 3);
    }

    public static void paintSky(final mGraphics mGraphics) {
        final Iterator<EffSky> iterator = CanvasNinja.vecSkys.iterator();
        while (iterator.hasNext()) {
            iterator.next().paint(mGraphics);
        }
    }

    public static void pointerDown(final int n, final int n2, final int n3) {
        CanvasNinja.pointerNew[n3].setPointer(n, n2, true, false);
    }

    public static void pointerUp(final int px, final int py, final int n) {
        final Pointer pointer = CanvasNinja.pointerNew[n];
        if (pointer != null) {
            pointer.px = px;
            pointer.py = py;
            pointer.isPointerClick = false;
            pointer.isPointerRelease = true;
            pointer.isPointerDragged = false;
        }
    }

    public static void resetAllKeyMove() {
        for (int i = 2; i <= 8; i += 2) {
            CanvasNinja.keyHold[i] = false;
        }
        CanvasNinja.keyHold[99] = false;
    }

    public static void resetClip(final mGraphics mGraphics) {
        mGraphics.setClip(0, 0, CanvasNinja.w, CanvasNinja.h);
    }

    public static void resetColor(final mGraphics mGraphics) {
        mGraphics.g.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void resetDau() {
        CanvasNinja.keyPCTyped = "";
        CanvasNinja.keyChange = "";
        CanvasNinja.isSetDau = false;
        CanvasNinja.isDelete = false;
    }

    private static void resetKeyHoldPCNew() {
        int n = 0;
        while (true) {
            final boolean[] keyHoldPC = CanvasNinja.keyHoldPC;
            if (n >= keyHoldPC.length) {
                break;
            }
            keyHoldPC[n] = false;
            CanvasNinja.isFirstPress[n] = false;
            CanvasNinja.timePressPC[n] = -1999L;
            ++n;
        }
    }

    public static void resetLoadMap() {
        MapScr.gI().tutorial = null;
        MapScr.gI().pointFind = null;
        if (MapScr.gI().menuMiniMap != null) {
            final BaseScreen currentScreen = CanvasNinja.currentScreen;
            if (currentScreen != null && currentScreen instanceof MapScr) {
                MapScr.gI().startMiniMapDefault();
            }
        }
        MiniMap.indexNPC = -1;
    }

    public static void resetLogin() {
        CanvasNinja.isLogin = false;
        CanvasNinja.isCloseConnect = false;
        CanvasNinja.timeDelayConnect = 0L;
        DownLoadingScr.isSendClientOk = false;
    }

    public static void resetPxPy() {
        CanvasNinja.py = -1999;
        CanvasNinja.px = -1999;
    }

    public static void resetScrollWhell() {
        CanvasNinja.cmy = 0;
        CanvasNinja.cmyLim = 0;
        CanvasNinja.isScrollWhell = false;
    }

    public static void resetStatic() {
        LoadVersionManager.isLoadAtlas = false;
        LoadVersionManager.isHaveTemplateAtlas = false;
        MapScr.gI().getClass();
        TabParty.partyInfo = null;
        TabParty.vecPartySearchs.removeAllElements();
        TabParty.vecPartySearchsTemp.removeAllElements();
        resetLoadMap();
    }

    public static void resetTab() {
        CanvasNinja.currentTab = null;
        CanvasNinja.questScreen = null;
        CanvasNinja.menuMain = null;
        CanvasNinja.objScr = null;
        CanvasNinja.currentDialog = null;
        CanvasNinja.subTab = null;
        CanvasNinja.objScrMore = null;
    }

    public static void resetTrans(final mGraphics mGraphics) {
        mGraphics.translate(-mGraphics.getTranslateX(), -mGraphics.getTranslateY());
        mGraphics.setClip(0, 0, CanvasNinja.w, CanvasNinja.h);
    }

    public static void setPosImgHoldToMoveTutorial(final int xHoldMoveTutorial, final int yHoldMoveTutorial) {
        CanvasNinja.xHoldMoveTutorial = xHoldMoveTutorial;
        CanvasNinja.yHoldMoveTutorial = yHoldMoveTutorial;
    }

    public static void startOKDlg(final String s) {
        new MessageDialog().startMsgDialog(s);
    }

    public static void startOKDlg(final String s, final int n) {
        new MessageDialog().startMsgDialog(s, n);
    }

    public static void startOpenCamera() {
        CanvasNinja.isOpenCamera = true;
        CanvasNinja.hOpenCamera = CanvasNinja.h / 2;
    }

    public static void startTaskDialog(final String s, final String s2, final int n) {
        new MessageDialog().startMsgDialog(s2, s, s2, n);
    }

    public static void startWaitDlg(final String s, final String s2) {
        new MessageDialog().startWaitingDialog(s, s2);
    }

    public static void startWaitDlg(final String s, final String s2, final int n, final boolean isShowTime) {
        final MessageDialog messageDialog = new MessageDialog();
        messageDialog.isShowTime = isShowTime;
        messageDialog.startWaitingDialog(s, s2, n);
    }

    public static void updateLogin() {
        if (CanvasNinja.isLogin) {
            if (!CanvasNinja.isCloseConnect) {
                Res.outz("gọi close");
                MySession.gI().close();
                CanvasNinja.isCloseConnect = true;
                CanvasNinja.timeDelayConnect = mSystem.currentTimeMillis() + 500L;
            }
            if (CanvasNinja.timeDelayConnect != 0L && mSystem.currentTimeMillis() > CanvasNinja.timeDelayConnect) {
                Res.outz("gọi đăng nhập");
                CanvasNinja.timeDelayConnect = 0L;
                MySession.gI().connect(NinjaMidlet.IP, NinjaMidlet.PORT);
                CanvasNinja.isLogin = false;
                CanvasNinja.isCloseConnect = false;
            }
        }
    }

    public boolean comboKeyPressed(final int n) {
        boolean b = false;
        switch (n) {
            case 3: {
                Res.outz("Ctrl V");
                CanvasNinja.comboKeyPC[3] = true;
                b = true;
                break;
            }
            case 2: {
                CanvasNinja.comboKeyPC[2] = true;
                b = true;
                break;
            }
            case 1: {
                CanvasNinja.comboKeyPC[1] = true;
                b = true;
                break;
            }
            case 0: {
                CanvasNinja.comboKeyPC[0] = true;
                Res.outz("Ctrl A");
                b = true;
                break;
            }
        }
        return b;
    }

    public void comboKeyReleased() {
        int n = 0;
        while (true) {
            final boolean[] comboKeyPC = CanvasNinja.comboKeyPC;
            if (n >= comboKeyPC.length) {
                break;
            }
            comboKeyPC[n] = false;
            ++n;
        }
    }

    public void initGame() {
        CanvasNinja.wF = this.getWidthz() * this.g.camera.zoom;
        final float n = CanvasNinja.hF = this.getHeightz() * this.g.camera.zoom;
        final int w = (int) CanvasNinja.wF;
        final int h = (int) n;
        CanvasNinja.w = w;
        CanvasNinja.h = h;
        final int n2 = w / MyTile.size;
        boolean lowGraphic = true;
        CanvasNinja.numTile = n2 + 1;
        CanvasNinja.hw = CanvasNinja.w / 2;
        CanvasNinja.hh = CanvasNinja.h / 2;
        if (this.hasPointerEvents()) {
            CanvasNinja.isTouch = true;
            final int w2 = CanvasNinja.w;
            if (w2 >= 240) {
                CanvasNinja.isTouchControl = true;
            }
            if (w2 < 320) {
                CanvasNinja.isTouchControlSmallScreen = true;
            }
            if (w2 >= 320) {
                CanvasNinja.isTouchControlLargeScreen = true;
            }
        }
        if (CanvasNinja.h <= 160) {
            SupportPaint.hTab = 15;
            BaseScreen.cmdH = 17;
        }
        int w3 = CanvasNinja.w;
        final int h2 = CanvasNinja.h;
        if (w3 <= h2) {
            w3 = h2;
        }
        MapScr.d = w3 + 20;
        CanvasNinja.instance = this;
        System.gc();
        this.initPaint();
        this.loadWaterSplash();
        CanvasNinja.imgShuriken = Image.createImage("/normal/frame/waiting.png");
        if (Rms.loadRMSInt("lowGraphic") != 1) {
            lowGraphic = false;
        }
        CanvasNinja.lowGraphic = lowGraphic;
        Res.init();
        CanvasNinja.img12 = Image.createImage("/12+.png");
        this.initCharList();
        LoadDataManager.init();
        int n3 = 0;
        while (true) {
            final Pointer[] pointerNew = CanvasNinja.pointerNew;
            if (n3 >= pointerNew.length) {
                break;
            }
            pointerNew[n3] = new Pointer(0, 0, false, false);
            ++n3;
        }
    }

    public void initPaint() {
        CanvasNinja.paintz = new SupportPaint();
    }

    public void keyPressed(final int n, final String s) {
        final HashTableCustom hkey = MapKey.hKEY;
        final StringBuilder sb = new StringBuilder();
        sb.append(n);
        sb.append("");
        if (!hkey.contains(sb.toString())) {
            final HashTableCustom hkey2 = MapKey.hKEY;
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(n);
            sb2.append("");
            hkey2.put(sb2.toString(), s);
        }
        clearKeyPressed();
        this.mapKeyPress(n);
    }

    public void keyReleased(final int n) {
        resetKeyHoldPCNew();
        this.resetDau();
        clearKeyReleased();
        this.mapKeyRelease(n);
    }

    public void loadWaterSplash() {
        if (CanvasNinja.lowGraphic) {
            return;
        }
        CanvasNinja.imgWS = new Image[3];
        CanvasNinja.wsX = new int[2];
        CanvasNinja.wsY = new int[2];
        final int[] array = CanvasNinja.wsState = new int[2];
        CanvasNinja.wsF = new int[2];
        array[0] = (array[1] = -1);
    }

    public void mapKeyPress(int n) {
        mapKeyPressPCNew(n);
        final Dialog currentDialog = CanvasNinja.currentDialog;
        if (currentDialog != null) {
            currentDialog.keyPress(n);
        } else {
            final MenuMain menuMain = CanvasNinja.menuMain;
            if (menuMain != null) {
                menuMain.keyPress(n);
            } else {
                final SubTabScr subTab = CanvasNinja.subTab;
                if (subTab != null) {
                    subTab.keyPress(n);
                } else {
                    final TabScr currentTab = CanvasNinja.currentTab;
                    if (currentTab != null) {
                        currentTab.keyPress(n);
                    } else {
                        CanvasNinja.currentScreen.keyPress(n);
                    }
                }
            }
        }
        switch (n) {
            default: {
            }
            case 57: {
                CanvasNinja.keyHold[9] = true;
                CanvasNinja.keyPressed[9] = true;
            }
            case 55: {
                CanvasNinja.keyHold[7] = true;
                CanvasNinja.keyPressed[7] = true;
            }
            case 51: {
                CanvasNinja.keyHold[3] = true;
                CanvasNinja.keyPressed[3] = true;
            }
            case 49: {
                CanvasNinja.keyHold[1] = true;
                CanvasNinja.keyPressed[1] = true;
            }
            case 48: {
                CanvasNinja.keyHold[0] = true;
                CanvasNinja.keyPressed[0] = true;
            }
            case 42: {
                CanvasNinja.keyHold[10] = true;
                CanvasNinja.keyPressed[10] = true;
            }
            case 35: {
                CanvasNinja.keyHold[11] = true;
                CanvasNinja.keyPressed[11] = true;
            }
            case -3: {
                if (CanvasNinja.currentScreen instanceof MapScr && Player.myCharz().isAttack) {
                    clearKeyHold();
                    clearKeyPressed();
                    return;
                }
                CanvasNinja.keyHold[4] = true;
                CanvasNinja.keyPressed[4] = true;
                n = ++CanvasNinja.indexKey;
                CanvasNinja.keyHoldControl[2] = n;
            }
            case -4: {
                if (CanvasNinja.currentScreen instanceof MapScr && Player.myCharz().isAttack) {
                    clearKeyHold();
                    clearKeyPressed();
                    return;
                }
                CanvasNinja.keyHold[6] = true;
                CanvasNinja.keyPressed[6] = true;
                n = ++CanvasNinja.indexKey;
                CanvasNinja.keyHoldControl[3] = n;
            }
            case -5:
            case 10: {
                if (CanvasNinja.currentScreen instanceof MapScr && Player.myCharz().isAttack) {
                    clearKeyHold();
                    clearKeyPressed();
                    return;
                }
                CanvasNinja.keyHold[5] = true;
                CanvasNinja.keyPressed[5] = true;
            }
            case -21:
            case -6: {
                CanvasNinja.keyHold[12] = true;
                CanvasNinja.keyPressed[12] = true;
            }
            case -22:
            case -7: {
                CanvasNinja.keyHold[13] = true;
                CanvasNinja.keyPressed[13] = true;
            }
            case -30: {
                CanvasNinja.keyHold[50] = true;
                CanvasNinja.keyPressed[50] = true;
            }
            case -31: {
                CanvasNinja.keyHold[51] = true;
                CanvasNinja.keyPressed[51] = true;
            }
            case -32: {
                CanvasNinja.keyHold[52] = true;
                CanvasNinja.keyPressed[52] = true;
            }
            case -33: {
                CanvasNinja.keyHold[53] = true;
                CanvasNinja.keyPressed[53] = true;
            }
            case -38:
            case -1: {
                if (CanvasNinja.currentScreen instanceof MapScr && Player.myCharz().isAttack) {
                    clearKeyHold();
                    clearKeyPressed();
                    return;
                }
                CanvasNinja.keyHold[2] = true;
                CanvasNinja.keyPressed[2] = true;
                n = ++CanvasNinja.indexKey;
                CanvasNinja.keyHoldControl[0] = n;
            }
            case -39:
            case -2: {
                if (CanvasNinja.currentScreen instanceof MapScr && Player.myCharz().isAttack) {
                    clearKeyHold();
                    clearKeyPressed();
                    return;
                }
                CanvasNinja.keyHold[8] = true;
                CanvasNinja.keyPressed[8] = true;
                n = ++CanvasNinja.indexKey;
                CanvasNinja.keyHoldControl[1] = n;
            }
        }
    }

    public void mapKeyRelease(final int n) {
        switch (n) {
            default: {
            }
            case 57: {
                CanvasNinja.keyHold[9] = false;
                CanvasNinja.keyReleased[9] = true;
            }
            case 55: {
                CanvasNinja.keyHold[7] = false;
                CanvasNinja.keyReleased[7] = true;
            }
            case 51: {
                if (CanvasNinja.currentScreen == MapScr.instance && CanvasNinja.isMoveNumberPad) {
                    CanvasNinja.keyHold[3] = false;
                    CanvasNinja.keyReleased[3] = true;
                }
            }
            case 49: {
                if (CanvasNinja.currentScreen == MapScr.instance && CanvasNinja.isMoveNumberPad) {
                    CanvasNinja.keyHold[1] = false;
                    CanvasNinja.keyReleased[1] = true;
                }
            }
            case 48: {
                CanvasNinja.keyHold[0] = false;
                CanvasNinja.keyReleased[0] = true;
            }
            case 42: {
                CanvasNinja.keyHold[10] = false;
                CanvasNinja.keyReleased[10] = true;
            }
            case 35: {
                CanvasNinja.keyHold[11] = false;
                CanvasNinja.keyReleased[11] = true;
            }
            case -3: {
                CanvasNinja.keyHold[4] = false;
                CanvasNinja.keyReleased[4] = true;
                CanvasNinja.keyHoldControl[2] = -1;
            }
            case -4: {
                CanvasNinja.keyHold[6] = false;
                CanvasNinja.keyReleased[6] = true;
                CanvasNinja.keyHoldControl[3] = -1;
            }
            case -5:
            case 10: {
                CanvasNinja.keyHold[5] = false;
                CanvasNinja.keyReleased[5] = true;
            }
            case -21:
            case -6: {
                CanvasNinja.keyHold[12] = false;
                CanvasNinja.keyReleased[12] = true;
            }
            case -22:
            case -7: {
                CanvasNinja.keyHold[13] = false;
                CanvasNinja.keyReleased[13] = true;
            }
            case -30: {
                CanvasNinja.keyHold[50] = false;
                CanvasNinja.keyReleased[50] = true;
            }
            case -31: {
                CanvasNinja.keyHold[51] = false;
                CanvasNinja.keyReleased[51] = true;
            }
            case -32: {
                CanvasNinja.keyHold[52] = false;
                CanvasNinja.keyReleased[52] = true;
            }
            case -33: {
                CanvasNinja.keyHold[53] = false;
                CanvasNinja.keyReleased[53] = true;
            }
            case -38:
            case -1: {
                CanvasNinja.keyHold[2] = false;
                CanvasNinja.keyReleased[2] = true;
                CanvasNinja.keyHoldControl[0] = -1;
            }
            case -39:
            case -2: {
                CanvasNinja.keyHold[8] = false;
                CanvasNinja.keyReleased[8] = true;
                CanvasNinja.keyHoldControl[1] = -1;
            }
        }
    }

    public void mapKeyTyped(final char c) {
        for (int i = 0; i < CanvasNinja.charList.length; ++i) {
            final int n = 0;
            int n2 = 0;
            int n3;
            while (true) {
                final char[] array = CanvasNinja.charList[i];
                n3 = n;
                if (n2 >= array.length) {
                    break;
                }
                if (c == array[n2]) {
                    final boolean b = true;
                    boolean isSetDau = true;
                    if (i == 0 && n2 == 1) {
                        CanvasNinja.keyPCTyped = "";
                        CanvasNinja.isDelete = true;
                    } else {
                        CanvasNinja.isDelete = false;
                        CanvasNinja.keyPCTyped = String.valueOf(c);
                    }
                    if (i == 0) {
                        isSetDau = false;
                    }
                    CanvasNinja.isSetDau = isSetDau;
                    n3 = (b ? 1 : 0);
                    if (isSetDau) {
                        CanvasNinja.keyChange = String.valueOf(CanvasNinja.charList[i][0]);
                        n3 = (b ? 1 : 0);
                        break;
                    }
                    break;
                } else {
                    ++n2;
                }
            }
            if (n3 != 0) {
                break;
            }
        }
        final Dialog currentDialog = CanvasNinja.currentDialog;
        if (currentDialog != null) {
            currentDialog.keyTyped();
        } else {
            final MenuMain menuMain = CanvasNinja.menuMain;
            if (menuMain != null) {
                menuMain.keyTyped();
                return;
            }
            final SubTabScr subTab = CanvasNinja.subTab;
            if (subTab != null) {
                subTab.keyTyped();
                return;
            }
            final TabScr currentTab = CanvasNinja.currentTab;
            if (currentTab != null) {
                currentTab.keyTyped();
                return;
            }
            final BaseScreen currentScreen = CanvasNinja.currentScreen;
            if (currentScreen != null) {
                currentScreen.keyTyped();
            }
        }
    }

    public void paint(mGraphics g) {
        this.g = g;
        try {
            debug("PA", 1);
            final BaseScreen currentScreen = CanvasNinja.currentScreen;
            if (currentScreen != null) {
                currentScreen.paint(this.g);
                if (CanvasNinja.currentScreen instanceof MapScr) {
                    final ObjScrMore objScrMore = CanvasNinja.objScrMore;
                    if (objScrMore != null) {
                        objScrMore.paint(this.g);
                    }
                    final ObjScr objScr = CanvasNinja.objScr;
                    if (objScr != null) {
                        objScr.paint(this.g);
                    }
                }
                final MenuMain menuMain = CanvasNinja.menuMain;
                if (menuMain != null) {
                    menuMain.paint(this.g);
                }
                final QuestScreen questScreen = CanvasNinja.questScreen;
                if (questScreen != null) {
                    questScreen.paint(this.g);
                }
                final TabScr currentTab = CanvasNinja.currentTab;
                if (currentTab != null) {
                    currentTab.paint(this.g);
                }
                final SubTabScr subTab = CanvasNinja.subTab;
                if (subTab != null) {
                    subTab.paint(this.g);
                }
                final Dialog currentDialog = CanvasNinja.currentDialog;
                if (currentDialog != null) {
                    currentDialog.paint(this.g);
                }
            }
            debug("PB", 1);
            g = this.g;
            g.translate(-g.getTranslateX(), -this.g.getTranslateY());
            this.g.setClip(0, 0, CanvasNinja.w, CanvasNinja.h);
            mInfoDialog.paint(this.g);
            debug("PE", 1);
            resetTrans(this.g);
            paintImgMoveByHold(this.g);
            paintImgMoveTutorial(this.g);
            if (CanvasNinja.currentScreen instanceof MapScr) {
                MapScr.gI().paintTutorial(this.g);
                if (CanvasNinja.menuMain == null && CanvasNinja.currentTab == null && CanvasNinja.questScreen == null && CanvasNinja.subTab == null && CanvasNinja.currentDialog == null) {
                    MapScr.gI().paintPointFind(this.g);
                }
            }
            this.g.drawImage(LoadDataManager.img18Plus, 5.0f, 5.0f);
            this.paintSomething(this.g);
            final LoadingMapScr loadingMapScr = CanvasNinja.loadingMapScr;
            if (loadingMapScr != null) {
                loadingMapScr.paint(this.g);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        resetTrans(this.g);
        final int n = CanvasNinja.h / 4;
        final BaseScreen currentScreen2 = CanvasNinja.currentScreen;
        if (currentScreen2 != null && currentScreen2 instanceof MapScr && CanvasNinja.thongBaoTest != null) {
            this.g.setClip(60, n, CanvasNinja.w - 120, mFont.tahoma_7_white.getHeight() + 2);
            mFont.tahoma_7_grey.drawString(this.g, CanvasNinja.thongBaoTest, CanvasNinja.xThongBaoTranslate, n + 1, 0);
            mFont.tahoma_7_yellow.drawString(this.g, CanvasNinja.thongBaoTest, CanvasNinja.xThongBaoTranslate, n, 0);
            resetTrans(this.g);
        }
    }

    public void paintOpenCamera(final mGraphics mGraphics) {
        mGraphics.setColor(0);
        mGraphics.fillRect(0, 0, CanvasNinja.w, CanvasNinja.hOpenCamera);
        final int h = CanvasNinja.h;
        mGraphics.fillRect(0, h - CanvasNinja.hOpenCamera, CanvasNinja.w, h / 2);
    }

    public void paintShape(final mGraphics mGraphics) {
        final BaseScreen currentScreen = CanvasNinja.currentScreen;
        if (currentScreen != null) {
            currentScreen.paintShape(mGraphics);
        }
    }

    public void paintSomething(final mGraphics mGraphics) {
        if (mSystem.isTest) {
            final int w = CanvasNinja.w;
            final int h = CanvasNinja.h;
            final byte size = MyTile.size;
            paintLuoi(mGraphics, 0, 0, w, h, 1, size, size);
        }
        this.paintOpenCamera(mGraphics);
    }

    @Override
    public void perform(final int n, final Object o) {
    }

    public void pointerDragged(final int n, final int n2, final int n3) {
        CanvasNinja.px = n;
        CanvasNinja.py = n2;
        CanvasNinja.isPointerHold = true;
        CanvasNinja.isPointerDraggedX = true;
        CanvasNinja.timeDraggedX = mSystem.currentTimeMillis() + CanvasNinja.timeDelayDragged;
        final Pointer pointer = CanvasNinja.pointerNew[n3];
        pointer.px = n;
        pointer.py = n2;
        pointer.isPointerClick = false;
        pointer.isPointerDragged = true;
    }

    public void pointerDraggedY(final int px, final int py) {
        CanvasNinja.px = px;
        CanvasNinja.py = py;
        CanvasNinja.isPointerHold = true;
        CanvasNinja.isPointerDraggedY = true;
        CanvasNinja.timeDraggedY = mSystem.currentTimeMillis() + CanvasNinja.timeDelayDragged;
    }

    public void pointerHover(final int hx, final int hy) {
        CanvasNinja.hx = hx;
        CanvasNinja.hy = hy;
        CanvasNinja.isPointerHover = true;
    }

    public void pointerMultiDraggedY(final int n, final int n2, final int n3) {
        CanvasNinja.pxArr[n3] = n;
        CanvasNinja.pyArr[n3] = n2;
        CanvasNinja.pointerHold[n3] = true;
        CanvasNinja.pointerDraggedY[n3] = true;
    }

    public void pointerMultiPressed(final int n, final int n2, final int pointerClickCur) {
        CanvasNinja.pointerClickCur = pointerClickCur;
        CanvasNinja.pointerPress[pointerClickCur] = true;
        CanvasNinja.pointerClick[pointerClickCur] = true;
        CanvasNinja.pointerDown[pointerClickCur] = true;
        CanvasNinja.pxLastArr[pointerClickCur] = n;
        CanvasNinja.pyLastArr[pointerClickCur] = n2;
        CanvasNinja.pxArr[pointerClickCur] = n;
        CanvasNinja.pyArr[pointerClickCur] = n2;
        CanvasNinja.pxFirstArr[pointerClickCur] = n;
        CanvasNinja.pyFirstArr[pointerClickCur] = n2;
    }

    public void pointerMultiReleased(final int n, final int n2, final int pointerReleaseCur) {
        CanvasNinja.pointerReleaseCur = pointerReleaseCur;
        CanvasNinja.isPointerLongPress = false;
        CanvasNinja.pointerPress[pointerReleaseCur] = false;
        CanvasNinja.pointerDown[pointerReleaseCur] = false;
        CanvasNinja.pointerRelease[pointerReleaseCur] = true;
        CanvasNinja.pointerHold[pointerReleaseCur] = false;
        CanvasNinja.pxArr[pointerReleaseCur] = n;
        CanvasNinja.pyArr[pointerReleaseCur] = n2;
    }

    public void pointerPressed(final int pxFirst, final int pyFirst, final int n) {
        CanvasNinja.timeStartPress = System.currentTimeMillis();
        CanvasNinja.isPointerDraggedX = false;
        CanvasNinja.isPointerDraggedXDone = false;
        CanvasNinja.isPointerClick = true;
        CanvasNinja.isPointerFirstClick = true;
        CanvasNinja.isPointerDown = true;
        CanvasNinja.isPointerHold = true;
        CanvasNinja.pxLast = pxFirst;
        CanvasNinja.pyLast = pyFirst;
        CanvasNinja.px = pxFirst;
        CanvasNinja.py = pyFirst;
        CanvasNinja.isPointerPress = true;
        if (this.click == 0) {
            CanvasNinja.timeDoubleClick = mSystem.currentTimeMillis();
            CanvasNinja.timeStartClick = mSystem.currentTimeMillis();
        }
        ++this.click;
        CanvasNinja.pxFirst = pxFirst;
        CanvasNinja.pyFirst = pyFirst;
    }

    public void pointerReleased(final int px, final int py, final int n) {
        CanvasNinja.timeStartPress = 0L;
        CanvasNinja.isPointerLongPress = false;
        CanvasNinja.isPointerHoldNew = false;
        CanvasNinja.pointerReleaseCur = 0;
        CanvasNinja.isPointerPress = false;
        CanvasNinja.isPointerDoubleClick = false;
        CanvasNinja.isPointerDown = false;
        CanvasNinja.isPointerRelease = true;
        CanvasNinja.px = px;
        CanvasNinja.py = py;
        if (CanvasNinja.isPointerDraggedX) {
            CanvasNinja.isPointerDraggedXDone = true;
        }
        CanvasNinja.isPointerHold = false;
    }

    public void scrollWhell(int cmyLim) {
        CanvasNinja.isScrollWhell = true;
        final int n = CanvasNinja.cmy += cmyLim;
        if (n < 0) {
            CanvasNinja.cmy = 0;
        } else {
            cmyLim = CanvasNinja.cmyLim;
            if (n > cmyLim) {
                CanvasNinja.cmy = cmyLim;
            }
        }
    }

    public void update() {
        final long currentTimeMillis = mSystem.currentTimeMillis();
        final long timefps = this.timefps;
        if (currentTimeMillis > timefps) {
            this.timefps = timefps + 1000L;
            CanvasNinja.max = CanvasNinja.fps;
            CanvasNinja.fps = 0;
        }
        ++CanvasNinja.fps;
        updateLogin();
        if (CanvasNinja.messageServer.size() > 0 && CanvasNinja.thongBaoTest == null) {
            CanvasNinja.messageServer.removeElementAt(0);
        }
        if (CanvasNinja.gameTick % 5 == 0) {
            CanvasNinja.timeNow = mSystem.currentTimeMillis();
        }
        final long currentTimeMillis2 = mSystem.currentTimeMillis();
        final long timeup = this.timeup;
        if (currentTimeMillis2 > timeup) {
            this.timeup = timeup + 1000L;
            if ((CanvasNinja.upmax = CanvasNinja.up) > 34) {
                ++this.delay;
            } else {
                final int delay = this.delay;
                if (delay > 1) {
                    this.delay = delay - 1;
                }
            }
            CanvasNinja.up = 0;
        }
        ++CanvasNinja.up;
        final long currentTimeMillis3 = System.currentTimeMillis();
        if (MapScr.isPing) {
            Res.updateOnScreenDebug();
        }
        try {
            final Iterator<EffSky> iterator = CanvasNinja.vecSkys.iterator();
            while (iterator.hasNext()) {
                iterator.next().update();
            }
            if (currentTimeMillis3 - CanvasNinja.timeTickEff1 >= 780L && !CanvasNinja.isEff1) {
                CanvasNinja.timeTickEff1 = currentTimeMillis3;
                CanvasNinja.isEff1 = true;
            } else {
                CanvasNinja.isEff1 = false;
            }
            if (currentTimeMillis3 - CanvasNinja.timeTickEff2 >= 7800L && !CanvasNinja.isEff2) {
                CanvasNinja.timeTickEff2 = currentTimeMillis3;
                CanvasNinja.isEff2 = true;
            } else {
                CanvasNinja.isEff2 = false;
            }
            final int taskTick = CanvasNinja.taskTick;
            if (taskTick > 0) {
                CanvasNinja.taskTick = taskTick - 1;
            }
            if (++CanvasNinja.gameTick > 10000) {
                CanvasNinja.gameTick = 0;
            }
            debug("A", 0);
            final long timeDelayLoadingMap = CanvasNinja.timeDelayLoadingMap;
            if (timeDelayLoadingMap != 0L && timeDelayLoadingMap < mSystem.currentTimeMillis()) {
                CanvasNinja.timeDelayLoadingMap = 0L;
                CanvasNinja.loadingMapScr = null;
            }
            this.updateOpenCamera();
            final LoadingMapScr loadingMapScr = CanvasNinja.loadingMapScr;
            if (loadingMapScr != null) {
                loadingMapScr.update();
            }
            final BaseScreen currentScreen = CanvasNinja.currentScreen;
            if (currentScreen != null) {
                if (currentScreen instanceof MapScr && MapScr.gI().tutorial != null) {
                    MapScr.gI().tutorial.updatePointer();
                }
                if (CanvasNinja.currentDialog != null) {
                    debug("B", 0);
                    CanvasNinja.currentDialog.update();
                    final Dialog currentDialog = CanvasNinja.currentDialog;
                    if (currentDialog != null) {
                        currentDialog.updateKey();
                        CanvasNinja.isPointerRelease = false;
                    }
                    final TabScr currentTab = CanvasNinja.currentTab;
                    if (currentTab != null) {
                        currentTab.update();
                        final MenuMain menuMain = CanvasNinja.menuMain;
                    }
                    final MenuMain menuMain2 = CanvasNinja.menuMain;
                    final ObjScr objScr = CanvasNinja.objScr;
                    if (objScr != null && objScr instanceof MiniMap) {
                        objScr.update();
                    }
                    final ObjScrMore objScrMore = CanvasNinja.objScrMore;
                    if (objScrMore != null) {
                        objScrMore.update();
                    }
                    CanvasNinja.isDark = true;
                } else {
                    final SubTabScr subTab = CanvasNinja.subTab;
                    if (subTab != null) {
                        subTab.update();
                        final SubTabScr subTab2 = CanvasNinja.subTab;
                        if (subTab2 != null) {
                            subTab2.updateKey();
                            CanvasNinja.isPointerRelease = false;
                        }
                        final TabScr currentTab2 = CanvasNinja.currentTab;
                        if (currentTab2 != null) {
                            currentTab2.update();
                        }
                        final MenuMain menuMain3 = CanvasNinja.menuMain;
                        final ObjScr objScr2 = CanvasNinja.objScr;
                        if (objScr2 != null && objScr2 instanceof MiniMap) {
                            objScr2.update();
                        }
                        final ObjScrMore objScrMore2 = CanvasNinja.objScrMore;
                        if (objScrMore2 != null) {
                            objScrMore2.update();
                        }
                        CanvasNinja.isDark = true;
                    } else {
                        final TabScr currentTab3 = CanvasNinja.currentTab;
                        if (currentTab3 != null) {
                            currentTab3.update();
                            final TabScr currentTab4 = CanvasNinja.currentTab;
                            if (currentTab4 != null) {
                                currentTab4.updateKey();
                                CanvasNinja.isPointerRelease = false;
                            }
                            final SubTabScr subTab3 = CanvasNinja.subTab;
                            if (subTab3 != null) {
                                subTab3.updateKey();
                                CanvasNinja.isPointerRelease = false;
                            }
                            final MenuMain menuMain4 = CanvasNinja.menuMain;
                            final ObjScr objScr3 = CanvasNinja.objScr;
                            if (objScr3 != null && objScr3 instanceof MiniMap) {
                                objScr3.update();
                            }
                            final ObjScrMore objScrMore3 = CanvasNinja.objScrMore;
                            if (objScrMore3 != null) {
                                objScrMore3.update();
                            }
                            CanvasNinja.isDark = true;
                        } else {
                            final QuestScreen questScreen = CanvasNinja.questScreen;
                            if (questScreen != null) {
                                CanvasNinja.isDark = true;
                                questScreen.update();
                                final QuestScreen questScreen2 = CanvasNinja.questScreen;
                                if (questScreen2 != null) {
                                    questScreen2.updateKey();
                                    CanvasNinja.isPointerRelease = false;
                                }
                                final TabScr currentTab5 = CanvasNinja.currentTab;
                                if (currentTab5 != null) {
                                    currentTab5.updateKey();
                                    CanvasNinja.isPointerRelease = false;
                                }
                                final SubTabScr subTab4 = CanvasNinja.subTab;
                                if (subTab4 != null) {
                                    subTab4.updateKey();
                                    CanvasNinja.isPointerRelease = false;
                                }
                                final MenuMain menuMain5 = CanvasNinja.menuMain;
                                final ObjScr objScr4 = CanvasNinja.objScr;
                                if (objScr4 != null && objScr4 instanceof MiniMap) {
                                    objScr4.update();
                                }
                                final ObjScrMore objScrMore4 = CanvasNinja.objScrMore;
                                if (objScrMore4 != null) {
                                    objScrMore4.update();
                                }
                            } else {
                                final MenuMain menuMain6 = CanvasNinja.menuMain;
                                if (menuMain6 != null) {
                                    menuMain6.update();
                                    final MenuMain menuMain7 = CanvasNinja.menuMain;
                                    if (menuMain7 != null) {
                                        menuMain7.updateKey();
                                        CanvasNinja.isPointerRelease = false;
                                    }
                                    final TabScr currentTab6 = CanvasNinja.currentTab;
                                    if (currentTab6 != null) {
                                        currentTab6.updateKey();
                                        CanvasNinja.isPointerRelease = false;
                                    }
                                    final SubTabScr subTab5 = CanvasNinja.subTab;
                                    if (subTab5 != null) {
                                        subTab5.updateKey();
                                        CanvasNinja.isPointerRelease = false;
                                    }
                                    final ObjScr objScr5 = CanvasNinja.objScr;
                                    if (objScr5 != null && objScr5 instanceof MiniMap) {
                                        objScr5.update();
                                    }
                                    final ObjScrMore objScrMore5 = CanvasNinja.objScrMore;
                                    if (objScrMore5 != null) {
                                        objScrMore5.update();
                                    }
                                    CanvasNinja.isDark = true;
                                } else {
                                    int n = 0;
                                    final int n2 = 0;
                                    if (CanvasNinja.currentScreen instanceof MapScr) {
                                        final ObjScr objScr6 = CanvasNinja.objScr;
                                        int n3 = n2;
                                        if (objScr6 != null) {
                                            objScr6.update();
                                            final ObjScr objScr7 = CanvasNinja.objScr;
                                            n3 = n2;
                                            if (objScr7 != null) {
                                                objScr7.updateKey();
                                                final ObjScr objScr8 = CanvasNinja.objScr;
                                                n3 = n2;
                                                if (objScr8 != null) {
                                                    n3 = n2;
                                                    if (objScr8.isFocusMe) {
                                                        n3 = 1;
                                                    }
                                                }
                                            }
                                        }
                                        final ObjScrMore objScrMore6 = CanvasNinja.objScrMore;
                                        n = n3;
                                        if (objScrMore6 != null) {
                                            objScrMore6.update();
                                            final ObjScrMore objScrMore7 = CanvasNinja.objScrMore;
                                            n = n3;
                                            if (objScrMore7 != null) {
                                                objScrMore7.updateKey();
                                                final ObjScrMore objScrMore8 = CanvasNinja.objScrMore;
                                                n = n3;
                                                if (objScrMore8 != null) {
                                                    n = n3;
                                                    if (objScrMore8.isFocusMe) {
                                                        n = 1;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    CanvasNinja.isDark = false;
                                    if (n == 0) {
                                        CanvasNinja.currentScreen.updateKey();
                                    }
                                }
                            }
                        }
                    }
                }
                debug("E", 0);
                CanvasNinja.currentScreen.update();
                debug("F", 0);
                AudioManager.update();
            }
            debug("Ix", 0);
            debug("Hx", 0);
            debug("G", 0);
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ex2) {
            }
            if (mSystem.isTest) {
                Res.outz("err ==>   Update in gameCanvas   ========================= ");
                ex.printStackTrace();
                ex.getMessage();
                ex.getCause();
            }
        }
    }

    public void updateOpenCamera() {
        if (CanvasNinja.isOpenCamera && (CanvasNinja.hOpenCamera -= CanvasNinja.speedOpen) <= 0) {
            CanvasNinja.hOpenCamera = 0;
            CanvasNinja.isOpenCamera = false;
        }
    }
}
