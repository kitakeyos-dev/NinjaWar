package ninjawar.mymain;

import ninjawar.message.Config;
import ninjawar.message.GetMessage;
import ninjawar.model.Langue1;
import ninjawar.mylib.AudioManager;
import ninjawar.mylib.MySession;
import ninjawar.mylib.Rms;
import ninjawar.mylib.mSystem;
import ninjawar.myscr.SplashScr;
import ninjawar.screen.LoginScr;
import ninjawar.screen.dialog.Dialog;
import ninjawar.screen.quest.QuestScreen;
import ninjawar.screen.tab.TabScr;
import ninjawar.supporter.csv.mReadCSV;
import ninjawar.supporter.csv.mWriteCSV;

public class NinjaMidlet {
    public static String IP = "192.168.216.253";
    public static String IP_BEFORE = "";
    public static int PADDING_TAI_THO = (((double) ((((float) CanvasNinja.w) * 1.0f) / ((float) CanvasNinja.h))) < 0.75d ? 0 : 30);
    public static int PORT = 14446;
    public static float SCALE_SPEC = 0.8f;
    public static String deviceName = "Unknown";
    public static CanvasNinja gameCanvas;
    public static NinjaMidlet instance;
    public static boolean isAndroid = false;
    public static boolean isCheckAppStore = false;
    public static boolean isDebugEclipse = true;
    public static boolean isDebugMouse = false;
    public static boolean isIOS = false;
    public static boolean isLevelUp;
    public static boolean isMapTest = false;
    public static boolean isPC = true;
    public static boolean isReadExcel = true;
    public static boolean isSendClientOK = false;
    public static boolean isTestBoss = false;
    public static boolean isTestMoveNew = true;
    public static boolean isUseIOSSpec = false;
    public static boolean isWriteExcel = false;
    public static byte language;
    public static String linkGetHost = "";
    public static String serect_key;
    public static long timeSystems;

    public void loadServer() {
        Langue1.load();
        if (isReadExcel) {
            mReadCSV.readCSV();
        }
        if (isWriteExcel && isPC) {
            mWriteCSV.wirteCSV();
        }
        loadIP();
        Rms.loadFirst();
    }

    public static void loadIP() {
        IP = Config.IP_DEV;
        PORT = Config.PORT;
        IP_BEFORE = LoginScr.loadIPBefore();
    }

    public NinjaMidlet() {
        gameCanvas = new CanvasNinja();
        loadServer();
        loadDevice();
        AudioManager.loadSound();
        instance = this;
    }

    public void loadDevice() {
        if (isAndroid) {
            isUseIOSSpec = true;
            isPC = false;
            mSystem.isTest = false;
        }
        if (isPC) {
            isUseIOSSpec = false;
            isAndroid = false;
        }
    }

    public void initGame() {
        gameCanvas = new CanvasNinja();
        initGame2();
    }

    public void initGame2() {
        MySession.gI().setHandler(GetMessage.gI());
        MySession.gI2().setHandler(GetMessage.gI());
        MySession.gI2().isMainSession = false;
        SplashScr.loadImg();
        SplashScr.loadSplashScr();
        CanvasNinja.currentScreen = new SplashScr();
    }

    public void resize(int teleport) {
        if (teleport == -1) {
            SplashScr.gI().switchToMe();
            return;
        }
        CanvasNinja.currentScreen.switchToMe();
        TabScr tabScr = CanvasNinja.currentTab;
        if (tabScr != null) {
            tabScr.show();
        }
        Dialog dialog = CanvasNinja.currentDialog;
        if (dialog != null) {
            dialog.resize();
        }
        QuestScreen questScreen = CanvasNinja.questScreen;
        if (questScreen != null) {
            questScreen.resize();
        }
    }

    public static byte getTypeDive() {
        if (isAndroid) {
            return 2;
        }
        if (isIOS) {
            return 3;
        }
        if (isPC) {
            return 1;
        }
        return 0;
    }

    public static String getDeviceName() {
        return deviceName;
    }

    public static int getProvider() {
        return -1;
    }

    public static String getAgentCode() {
        return "";
    }

    public static byte getLanguage() {
        return language;
    }
}
