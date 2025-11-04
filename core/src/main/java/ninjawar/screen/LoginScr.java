package ninjawar.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.teamobi.ninjawar.NinjaWar;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Vector;
import ninjawar.input.KEY;
import ninjawar.input.MyButton;
import ninjawar.input.MyCheckBox;
import ninjawar.input.TField;
import ninjawar.message.SendMessage;
import ninjawar.model.ActionInterface;
import ninjawar.model.MapTemplate;
import ninjawar.model.MyCommand;
import ninjawar.mylib.AudioManager;
import ninjawar.mylib.BaseScreen;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Rms;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.MyTile;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.screen.dialog.MessageDialog;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

public class LoginScr extends BaseScreen implements ActionInterface {
    public static int aa = 0;
    public static int indexSvSelected = -1;
    public static boolean isChooseLogin = false;
    public static boolean isClearPin = false;
    public static boolean isLogOut = true;
    public static boolean isLogin = false;
    public static boolean isSelectedLanguage = false;
    public static boolean isSendRequestContinue = false;
    public static boolean isSendRequestLogin = false;
    public static boolean isSendRequestNewGame = false;
    public static boolean isSendRequestRegister = false;
    public static boolean isUseScroll;
    public static int local = 0;
    public static LoginScr me;
    public static String tokenTest = "";
    public int[] arrH;
    public int[] arrW;
    public int[] arrX;
    public int[] arrY;
    public MyButton btnClearData;
    public MyCheckBox checkBox;
    MyCommand cmdBack;
    MyCommand cmdLoginOauth2;
    int cmdStart;
    public int cmtoY;
    public int cmy;
    int colorBtn;
    public int defYL;
    mFont f;
    int focus;
    mFont fontPaintOauth2;
    mFont fontPaintOauth2Focus;
    mFont fontPaintRemember;
    mFont fontPaintTitle;
    mFont fontPaintUsername;
    mFont fontPlaceHolder;
    public int frameWidth;
    int h = 31;
    int hLogo;
    int hOauth2;
    public int hPanel;
    FrameImage[] iconOauth2;
    int indexDelay;
    public byte indexScreenLogin;
    boolean isCheckBox = true;
    public boolean isConfirmDeleteAccount;
    boolean[] isFocusOauth2;
    boolean isG = false;
    public boolean isLinked = false;
    public boolean isPaintLogo;
    boolean isPauseScroll = false;
    public boolean isReg = false;
    boolean isRun;
    private boolean isSaveLogin = true;
    public boolean isShowBack;
    public boolean isShowNext;
    boolean isTransing = false;
    public int localSaved;
    MapTemplate mapTemplate;
    public int margin;
    public int marginButton;
    int maxColor;
    int numOAuth2;
    public String passwordSaved;
    public int pinSaved = -1;
    public int serverSaved;
    private String sessionId;
    String[] textOauth2;
    String textRemember;
    String textTitle;
    public TField tfPass;
    public TField tfUser;
    int tickDelay;
    public long timeLogin;
    public long timeOut = 0;
    public long timeScroll = 0;
    boolean trans = false;
    public String usernameSaved;
    Vector<MyButton> vecBtnTest;
    int w = 93;
    int wOauth2;
    public int wPanel;
    public int widthButton;
    public int xDeskInLine;
    public int xDeskOutLine;
    int[] xIcon;
    int xLine;
    int[] xOauth2;
    int[] xTextOauth2;
    public int yDeskInLine;
    public int yDeskOutLine;
    int[] yIcon;
    public int yL;
    int yLine;
    public int yMore = 3;
    int[] yOauth2;
    int[] yTextOauth2;

    public static LoginScr gI() {
        if (me == null) {
            me = new LoginScr();
        }
        return me;
    }

    private void initLoginForm() {
        char c;
        this.margin = 5;
        this.textOauth2 = new String[]{SupportTranslate.getTextLangue("loginWithGooogle"), SupportTranslate.getTextLangue("loginWithApple")};
        FrameImage[] frameImageArr = new FrameImage[2];
        this.iconOauth2 = frameImageArr;
        frameImageArr[0] = LoadDataManager.frameIconGoogle;
        frameImageArr[1] = LoadDataManager.frameIconApple;
        MyCommand myCommand = new MyCommand("", 3, this);
        this.cmdLoginOauth2 = myCommand;
        if (NinjaMidlet.isAndroid) {
            this.numOAuth2 = 1;
            myCommand.subAction = 0;
        }
        if (NinjaMidlet.isIOS) {
            this.numOAuth2 = 2;
            this.textOauth2 = new String[]{SupportTranslate.getTextLangue("loginWithGooogle"), SupportTranslate.getTextLangue("loginWithApple")};
            FrameImage[] frameImageArr2 = new FrameImage[2];
            this.iconOauth2 = frameImageArr2;
            frameImageArr2[0] = LoadDataManager.frameIconGoogle;
            frameImageArr2[1] = LoadDataManager.frameIconApple;
            this.cmdLoginOauth2.subAction = 1;
        }
        int i = this.numOAuth2;
        this.isFocusOauth2 = new boolean[i];
        this.xOauth2 = new int[i];
        this.yOauth2 = new int[i];
        this.xIcon = new int[i];
        this.yIcon = new int[i];
        this.xTextOauth2 = new int[i];
        this.yTextOauth2 = new int[i];
        this.wOauth2 = ((int) this.iconOauth2[0].frameWidth) + (this.margin * 5) + this.fontPaintOauth2.getWidth(this.textOauth2[0]);
        this.hOauth2 = (int) LoadDataManager.frameBgOAuth2.frameHeight;
        int rHeight = LoadDataManager.imgLineLogin.getRHeight();
        int i2 = this.margin;
        this.hLogo = 31;
        this.wPanel = 285;
        int i3 = (((rHeight + (i2 * 2)) + ((this.hOauth2 + i2) * this.numOAuth2)) + 230) - (i2 * 4);
        this.hPanel = i3;
        if (CanvasNinja.h - (i3 + ((int) ((LoadDataManager.myButtons[1].frameHeight + ((float) i2)) + 3.0f))) < 31) {
            PrintStream printStream = System.out;
            printStream.println("debug:" + CanvasNinja.h);
            this.isPaintLogo = false;
        }
        int i4 = (CanvasNinja.w / 2) - (this.wPanel / 2);
        this.xDeskOutLine = i4;
        int i5 = (CanvasNinja.h - this.hPanel) / 2;
        this.yDeskOutLine = i5;
        int i6 = this.margin;
        this.xDeskInLine = i4 + i6;
        this.yDeskInLine = i5 + i6;
        this.widthButton = this.frameWidth * 2;
        this.marginButton = 20;
        this.listBtn = new MyButton[2];
        int wMinBtn = MyButton.FONT_DEFAULT.getWidth(SupportTranslate.getTextLangue("register").length() > SupportTranslate.getTextLangue("login").length() ? SupportTranslate.getTextLangue("register") : SupportTranslate.getTextLangue("login")) + 15;
        MyCommand cmdRegister = new MyCommand("", 2, this);
        MyButton[] myButtonArr = this.listBtn;
        FrameImage[] frameImageArr3 = LoadDataManager.myButtons;
        myButtonArr[0] = new MyButton(frameImageArr3[0], frameImageArr3[3], wMinBtn, 0, SupportTranslate.getTextLangue("register"), 0, 0, cmdRegister);
        MyCommand cmdLogin = new MyCommand("", 1, this);
        MyButton[] myButtonArr2 = this.listBtn;
        FrameImage[] frameImageArr4 = LoadDataManager.myButtons;
        myButtonArr2[1] = new MyButton(frameImageArr4[1], frameImageArr4[3], wMinBtn, 0, SupportTranslate.getTextLangue("login"), 0, 0, cmdLogin);
        FrameImage[] frameImageArr5 = LoadDataManager.myButtons;
        MyButton myButton = new MyButton(frameImageArr5[2], frameImageArr5[3], wMinBtn, 0, SupportTranslate.getTextLangue("xoadulieu"), 0, 0, new MyCommand("", 99, this));
        this.btnClearData = myButton;
        myButton.setXY(NinjaMidlet.PADDING_TAI_THO, (CanvasNinja.h - myButton.h) - (this.margin * 3));
        int sumWBtn = MyButton.getSumWidthListBtn(this.listBtn, this.margin * 2);
        int xBtnFirst = this.xDeskOutLine + ((this.wPanel - sumWBtn) / 2);
        MyButton.setPosList(this.listBtn, this.margin, sumWBtn, xBtnFirst, CanvasNinja.h / 2);
        int i7 = this.wPanel;
        int i8 = this.margin;
        int width = this.fontPaintTitle.getWidth(this.textTitle) + (this.margin * 4);
        int[] iArr = {i7 - (i8 * 4), 0, i7 - (i8 * 6), 0, i7 - (i8 * 6), (int) LoadDataManager.frameCheckBox.frameWidth, width};
        this.arrW = iArr;
        iArr[6] = Res.fixSizeTagFrameUp(9, width, LoadDataManager.frameTagTitleHanhTrang);
        float height = (LoadDataManager.frameInput.frameHeight * 2.0f) + ((float) (this.fontPaintUsername.getHeight() * 2));
        int i9 = this.margin;
        int hKhungTrong = ((int) (height + ((float) i9) + ((float) (3 * 3)) + LoadDataManager.frameCheckBox.frameHeight + ((float) (i9 * 4)))) + this.yMore;
        int[] iArr2 = {hKhungTrong, this.fontPaintUsername.getHeight(), (int) LoadDataManager.frameInput.frameHeight, this.fontPaintUsername.getHeight(), (int) LoadDataManager.frameInput.frameHeight, (int) LoadDataManager.frameCheckBox.frameHeight, (int) LoadDataManager.frameTitle.frameHeight};
        this.arrH = iArr2;
        int i10 = this.xDeskOutLine;
        int i11 = this.margin;
        this.arrX = new int[]{i10 + (i11 * 2), i10 + (i11 * 3), i10 + (i11 * 3), i10 + (i11 * 3), i10 + (i11 * 3), i10 + (i11 * 3), i10 + ((this.wPanel - this.arrW[6]) / 2)};
        int marginKhungTrong = iArr2[6];
        int i12 = this.yDeskOutLine;
        int i13 = i12 + marginKhungTrong;
        int i14 = iArr2[1];
        int i15 = iArr2[2];
        int i16 = hKhungTrong;
        int hKhungTrong2 = this.yMore;
        int i17 = iArr2[3];
        this.arrY = new int[]{i13, i12 + marginKhungTrong + (i11 * 2), i12 + marginKhungTrong + (i11 * 2) + i14 + 3, i12 + marginKhungTrong + (i11 * 2) + i14 + i15 + i11 + 3 + hKhungTrong2, i12 + marginKhungTrong + (i11 * 2) + i14 + i15 + i17 + i11 + (3 * 2) + hKhungTrong2, i12 + marginKhungTrong + (i11 * 2) + i14 + i15 + i17 + iArr2[4] + (i11 * 2) + (3 * 3) + hKhungTrong2, ((i12 + i11) - (iArr2[6] / 2)) - i11};
        MyButton.setPosList(this.listBtn, i11 * 2, sumWBtn, xBtnFirst, i13 + iArr2[0] + (i11 * 2));
        initTField();
        this.checkBox = new MyCheckBox(this.arrX[5], this.arrY[5], this.arrW[5] + this.margin + this.fontPaintRemember.getWidth(this.textRemember), this.arrH[5], Rms.loadRMSInt(Rms.RMS_REMMERBER_ME) == 1);
        this.xLine = (int) (((float) this.xDeskOutLine) + ((((float) this.wPanel) - LoadDataManager.imgLineLogin.getWidth()) / 2.0f));
        MyButton myButton2 = this.listBtn[0];
        int i18 = myButton2.yBtn + myButton2.h + this.margin;
        this.yLine = i18;
        if (this.numOAuth2 > 0) {
            c = 0;
            this.xOauth2[0] = this.xDeskOutLine + ((this.wPanel - this.wOauth2) / 2);
            int[] iArr3 = this.yOauth2;
            float height2 = ((float) i18) + LoadDataManager.imgLineLogin.getHeight();
            int i19 = this.margin;
            iArr3[0] = (int) (height2 + ((float) i19));
            this.xIcon[0] = this.xOauth2[0] + (i19 * 2);
            this.yIcon[0] = (int) (((float) this.yOauth2[0]) + ((LoadDataManager.frameBgOAuth2.frameHeight - ((float) this.iconOauth2[0].getRHeight())) / 2.0f));
            this.xTextOauth2[0] = this.xIcon[0] + this.iconOauth2[0].getRWidth() + this.margin;
            this.yTextOauth2[0] = (int) (((float) this.yOauth2[0]) + ((LoadDataManager.frameBgOAuth2.frameHeight - ((float) this.fontPaintOauth2.getHeight())) / 2.0f));
        } else {
            c = 0;
        }
        if (this.numOAuth2 > 1) {
            int[] iArr4 = this.xOauth2;
            iArr4[1] = iArr4[c];
            int[] iArr5 = this.yOauth2;
            int i20 = iArr5[c];
            int i21 = this.hOauth2;
            int i22 = this.margin;
            iArr5[1] = i20 + i21 + i22;
            int[] iArr6 = this.xIcon;
            iArr6[1] = iArr6[c];
            int[] iArr7 = this.yIcon;
            iArr7[1] = iArr7[c] + i21 + i22;
            int[] iArr8 = this.xTextOauth2;
            int rWidth = iArr6[1] + this.iconOauth2[1].getRWidth();
            int i23 = this.margin;
            iArr8[1] = rWidth + i23;
            int[] iArr9 = this.yTextOauth2;
            iArr9[1] = iArr9[0] + this.hOauth2 + i23;
        }
    }

    public void initTField() {
        this.tfPass = new TField();
        TField tField = new TField();
        this.tfUser = tField;
        mFont mfont = mFont.tahoma_7b_white;
        tField.fontTField = mfont;
        this.tfPass.fontTField = mfont;
        tField.setIputType(0);
        this.tfUser.isKeyTyped = true;
        this.tfPass.setIputType(2);
        this.tfUser.setMaxTextLenght(30);
        this.tfPass.setMaxTextLenght(12);
        TField tField2 = this.tfUser;
        int[] iArr = this.arrX;
        tField2.x = iArr[2];
        int[] iArr2 = this.arrY;
        tField2.y = iArr2[2];
        int[] iArr3 = this.arrW;
        tField2.width = iArr3[2];
        int[] iArr4 = this.arrH;
        tField2.height = iArr4[2];
        TField tField3 = this.tfPass;
        tField3.x = iArr[4];
        tField3.y = iArr2[4];
        tField3.width = iArr3[4];
        tField3.height = iArr4[4];
        String str = this.usernameSaved;
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        tField2.setTextFirst(str);
        TField tField4 = this.tfPass;
        String str3 = this.passwordSaved;
        if (str3 != null) {
            str2 = str3;
        }
        tField4.setTextFirst(str2);
        this.tfUser.updateMarginTField(7);
        this.tfPass.updateMarginTField(7);
    }

    public void addBtn(String text) {
        mFont mfont = mFont.tahoma_7b_white;
        FrameImage frameImage = LoadDataManager.myButtons[this.colorBtn];
        this.vecBtnTest.add(new MyButton(mfont, frameImage, frameImage, 70, 2, text, 0, 0, new MyCommand("", this.cmdStart, this)));
        this.cmdStart++;
        int i = this.colorBtn + 1;
        this.colorBtn = i;
        if (i > this.maxColor) {
            this.colorBtn = 0;
        }
    }

    public void initTestButton() {
        if (mSystem.isTest) {
            this.vecBtnTest.removeAllElements();
            addBtn("Chọn server Huy");
            addBtn("Chọn server Dịt");
            int sizeVec = this.vecBtnTest.size();
            int hBtn = (int) LoadDataManager.myButtons[0].frameHeight;
            int i = CanvasNinja.w;
            int i2 = this.margin;
            int xBtn = i - i2;
            int yBtn = (CanvasNinja.h - ((sizeVec * hBtn) + (i2 * (sizeVec - 1)))) / 2;
            int i3 = 0;
            Iterator<MyButton> it = this.vecBtnTest.iterator();
            while (it.hasNext()) {
                MyButton mBtn = it.next();
                mBtn.setXY(xBtn - mBtn.w, (((this.margin * 2) + hBtn) * i3) + yBtn);
                i3++;
            }
        }
    }

    public void switchToMe() {
        CanvasNinja.startOpenCamera();
        NinjaWar.me.signOutWithGooogle();
        resize();
        load();
        Player.myCharz().resetMyChar();
        NinjaMidlet.isSendClientOK = false;
        DownLoadingScr.isSendClientOk = false;
        load();
        this.isPaintLogo = true;
        resetCameraY();
        loadLoginSaved();
        initLoginForm();
        initStart();
        initCmd();
        MapTemplate mapTemplate2 = new MapTemplate();
        this.mapTemplate = mapTemplate2;
        mapTemplate2.loadData();
        initTestButton();
        AudioManager.MUSIC_NEN_CHON_CHAR();
        super.switchToMe();
        CanvasNinja.endDlg();
    }

    public void initStart() {
    }

    public LoginScr() {
        mFont mfont = mFont.tahoma_7b_brown;
        this.fontPaintUsername = mfont;
        this.fontPaintRemember = mfont;
        this.fontPlaceHolder = mFont.nameFontYellow;
        this.fontPaintTitle = mFont.tahoma_7b_white_orange;
        this.textTitle = SupportTranslate.getTextLangue("login").toUpperCase();
        this.textRemember = SupportTranslate.getTextLangue("remember");
        this.fontPaintOauth2 = mFont.tahoma_7;
        this.fontPaintOauth2Focus = mFont.tahoma_7_white;
        this.numOAuth2 = 2;
        this.vecBtnTest = new Vector<>();
        this.cmdStart = 1999;
        this.colorBtn = 0;
        this.maxColor = LoadDataManager.myButtons.length - 1;
        this.isShowNext = false;
        this.isShowBack = false;
        this.isConfirmDeleteAccount = false;
        this.indexScreenLogin = -1;
        this.f = mFont.tahoma_7b_focus;
        this.isRun = true;
        this.cmdBack = new MyCommand("", 5, this);
        this.tickDelay = 0;
        this.indexDelay = -1;
        init();
        this.focus = 0;
        me = this;
        this.sessionId = String.valueOf(System.currentTimeMillis());
    }

    public static void resetRequest() {
        isSendRequestNewGame = false;
        isSendRequestLogin = false;
        isSendRequestContinue = false;
    }

    public void load() {
        CanvasNinja.resetTab();
        CanvasNinja.resetStatic();
        Rms.clearVecRMS();
        resetRequest();
        this.timeOut = System.currentTimeMillis();
        initImg();
        resetLogo();
        MapScr.loadMapOfflineDefault();
        CanvasNinja.loadBG(Res.random(0, 3));
        MapScr.loadCamera(true, -1, -1);
        MapScr.cmx = 100;
        MapScr.cmy = 0;
        this.focus = 0;
    }

    public void initImg() {
    }

    public void init() {
    }

    public void initCmd() {
    }

    /* access modifiers changed from: protected */
    public void doLogin() {
        if (this.timeLogin <= mSystem.currentTimeMillis()) {
            String username = this.tfUser.getText().toLowerCase().trim();
            String password = this.tfPass.getText();
            String error = checkInputUsenameAndPassword();
            if (!error.equals("")) {
                Res.outz("Lỗi:" + error);
                CanvasNinja.startOKDlg(error);
                return;
            }
            if (this.checkBox.isChecked) {
                saveRmsLogin(username, password, 0, 0);
            } else {
                clearRmsLogin();
            }
            System.out.println("loginnnnnnnnnnn username:" + username + "_password:" + password);
            isSendRequestLogin = true;
            CanvasNinja.connect();
            doRequestLogin();
            CanvasNinja.startWaitDlg(SupportTranslate.getTextLangue("isLogining"), SupportTranslate.getTextLangue("serverClose"));
            this.timeLogin = mSystem.currentTimeMillis() + 200;
            saveRmsCheckBoxState(this.checkBox.isChecked ? 1 : 0);
        }
    }

    public void continueLoginOAuth2(String email, String tokenId) {
        if (this.timeLogin <= mSystem.currentTimeMillis()) {
            CanvasNinja.connect();
            doRequestLoginOAuth2(email, tokenId);
            CanvasNinja.startWaitDlg(SupportTranslate.getTextLangue("isWaitingAuth"), SupportTranslate.getTextLangue("timeOutAuth"), MessageDialog.TIME_WAITING_LOGIN_OATH2, true);
            this.timeLogin = mSystem.currentTimeMillis() + 200;
        }
    }

    public void doRequestLoginOAuth2(String email, String tokenId) {
        SendMessage.gI().sendClientInfo();
        SendMessage.gI().login(email, "", "1.0.0", "imei", tokenId, "numberclient", (byte) 0);
    }

    public void doRequestLogin() {
        String username = "";
        String password = "";
        if (!this.tfUser.getText().isEmpty()) {
            username = this.tfUser.getText().toLowerCase().trim();
        }
        if (!this.tfPass.getText().isEmpty()) {
            password = this.tfPass.getText();
        }
        SendMessage.gI().sendClientInfo();
        SendMessage.gI().login(username, password, "1.0.0", "imei", "", "numberclient", (byte) 0);
    }

    public void saveRmsCheckBoxState(int isChecked) {
        Rms.saveRMSInt(Rms.RMS_REMMERBER_ME, isChecked);
    }

    public void saveRmsLogin(String username, String password, int server, int local2) {
        String sessionPrefix = "SESSION_" + this.sessionId + "_";
        Rms.saveRMSString(sessionPrefix + Rms.RMS_SAVE_USER, username);
        Rms.saveRMSString(sessionPrefix + Rms.RMS_SAVE_PASSWORD, password);
        Rms.saveRMSInt(sessionPrefix + Rms.RMS_SAVE_SERVER, server);
        Rms.saveRMSInt(sessionPrefix + Rms.RMS_SAVE_LOCATION, local2);
        Rms.saveRMSString("GLOBAL_USER", username);
        Rms.saveRMSString("GLOBAL_PASSWORD", password);
        Rms.saveRMSInt("GLOBAL_SERVER", server);
        Rms.saveRMSInt("GLOBAL_LOCATION", local2);
        Rms.saveRMSString("LAST_SESSION_ID", this.sessionId);
    }

    public void clearRmsLogin() {
        String sessionPrefix = "SESSION_" + this.sessionId + "_";
        Rms.clearOneRMS(sessionPrefix + Rms.RMS_SAVE_USER);
        Rms.clearOneRMS(sessionPrefix + Rms.RMS_SAVE_PASSWORD);
    }

    public static String loadIPBefore() {
        return Rms.loadRMSString(Rms.RMS_SV_IP);
    }

    public void loadLoginSaved() {
        String sessionPrefix = "SESSION_" + this.sessionId + "_";
        this.usernameSaved = Rms.loadRMSString(sessionPrefix + Rms.RMS_SAVE_USER);
        this.passwordSaved = Rms.loadRMSString(sessionPrefix + Rms.RMS_SAVE_PASSWORD);
        this.serverSaved = Rms.loadRMSInt(sessionPrefix + Rms.RMS_SAVE_SERVER);
        this.localSaved = Rms.loadRMSInt(sessionPrefix + Rms.RMS_SAVE_LOCATION);
        if (this.usernameSaved == null || this.passwordSaved == null) {
            this.usernameSaved = Rms.loadRMSString("GLOBAL_USER");
            this.passwordSaved = Rms.loadRMSString("GLOBAL_PASSWORD");
            this.serverSaved = Rms.loadRMSInt("GLOBAL_SERVER");
            this.localSaved = Rms.loadRMSInt("GLOBAL_LOCATION");
            if (this.usernameSaved == null) {
                this.usernameSaved = "";
            }
            if (this.passwordSaved == null) {
                this.passwordSaved = "";
            }
        }
    }

    public void update() {
        super.update();
        MyButton myButton = this.btnClearData;
        if (myButton != null) {
            myButton.update();
        }
        MyCheckBox myCheckBox = this.checkBox;
        if (myCheckBox != null) {
            myCheckBox.update();
        }
        this.tfUser.update();
        if (!this.tfUser.isFocused()) {
            this.tfUser.fontTField = mFont.tahoma_7b_white;
        } else {
            this.tfUser.fontTField = mFont.tahoma_7b_custom;
        }
        this.tfPass.update();
        TField.updateFocus(this.tfUser, this.tfPass);
        if (NinjaMidlet.isAndroid && !NinjaMidlet.isUseIOSSpec) {
            if (this.tfUser.isFocused()) {
                Gdx.input.setOnscreenKeyboardVisible(true, Input.OnscreenKeyboardType.Default);
            }
            if (this.tfPass.isFocused()) {
                Gdx.input.setOnscreenKeyboardVisible(true);
            }
            if (!this.tfUser.isFocused() && !this.tfPass.isFocused()) {
                Gdx.input.setOnscreenKeyboardVisible(false);
            }
        }
        if (this.indexDelay != -1) {
            int i = this.tickDelay + 1;
            this.tickDelay = i;
            if (i > 5) {
                this.tickDelay = 0;
                this.indexDelay = -1;
            }
        }
        long currentTimeMillis = (System.currentTimeMillis() - this.timeOut) / 1000;
        updateLogo();
        int i2 = MapScr.cmx + 3;
        MapScr.cmx = i2;
        if (i2 > MyTile.size * MyTile.tmw) {
            MapScr.cmx = 100;
        }
    }

    public void updateLogo() {
        int i = this.defYL;
        int i2 = this.yL;
        if (i != i2) {
            this.yL = i2 + ((i - i2) >> 1);
        }
    }

    public void resetLogo() {
        this.yL = -50;
    }

    private void paintVersion(mGraphics g) {
        if (NinjaMidlet.isPC) {
            mFont.tahoma_7b_yellow.drawString(g, "Version: 1.0.0", CanvasNinja.w - 5, 5, 1);
            mFont mfont = mFont.tahoma_7b_yellow;
            mfont.drawString(g, "Zoom: " + mGraphics.zoomLevel, CanvasNinja.w - 5, mFont.tahoma_7b_yellow.getHeight() + 10, 1);
            return;
        }
        mFont.tahoma_7b_yellow.drawString(g, "Version: 1.0.0", CanvasNinja.w - 10, 5, 1);
        mFont mfont2 = mFont.tahoma_7b_yellow;
        mfont2.drawString(g, "Zoom: " + mGraphics.zoomLevel, CanvasNinja.w - 10, mFont.tahoma_7b_yellow.getHeight() + 10, 1);
    }

    public void paintBackNext(mGraphics g) {
    }

    public void paint(mGraphics g) {
        CanvasNinja.paintBGGameScr(g);
        CanvasNinja.resetTrans(g);
        paintLoginNew(g);
        CanvasNinja.resetTrans(g);
        paintVersion(g);
        if (!isChooseLogin) {
            paintBackNext(g);
        }
        MyButton myButton = this.btnClearData;
        if (myButton != null) {
            myButton.paint(g);
        }
        paintTest(g);
        if (CanvasNinja.isDark) {
            g.setColor(0, 0.25f);
            g.fillRect(0, 0, CanvasNinja.w, CanvasNinja.h, false);
        }
        super.paint(g);
    }

    public void paintTest(mGraphics g) {
        if (mSystem.isTest) {
            Iterator<MyButton> it = this.vecBtnTest.iterator();
            while (it.hasNext()) {
                it.next().paint(g);
            }
        }
    }

    private void paintLoginNew(mGraphics g) {
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange0, (float) this.xDeskOutLine, (float) this.yDeskOutLine, (float) this.wPanel, (float) this.hPanel, 0, false);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameTitle, this.arrX[6], this.arrY[6], this.arrW[6], false, SupportPaint.TYPE_NONE, false);
        this.fontPaintTitle.drawString(g, this.textTitle, (this.arrW[6] / 2) + this.arrX[6], (this.arrH[6] / 2) + this.arrY[6], 3);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgWhite1, (float) this.arrX[0], (float) this.arrY[0], (float) this.arrW[0], (float) this.arrH[0], 1, false);
        this.fontPaintUsername.drawString(g, SupportTranslate.getTextLangue("username"), this.arrX[1], this.arrY[1], 0);
        int i = 0;
        while (true) {
            MyButton[] myButtonArr = this.listBtn;
            if (i >= myButtonArr.length) {
                break;
            }
            MyButton myButton = myButtonArr[i];
            if (myButton != null) {
                myButton.paint(mgraphics);
            }
            i++;
        }
        this.fontPaintUsername.drawString(g, SupportTranslate.getTextLangue("username"), this.arrX[1], this.arrY[1], 0);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameInput, this.arrX[2], this.arrY[2], this.arrW[2], this.tfUser.isFocused(), SupportPaint.TYPE_NONE, true);
        this.fontPaintUsername.drawString(g, SupportTranslate.getTextLangue("password"), this.arrX[3], this.arrY[3], 0);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameInput, this.arrX[4], this.arrY[4], this.arrW[4], this.tfPass.isFocused(), SupportPaint.TYPE_NONE, true);
        this.tfUser.painTextInputNoneBackGround(mgraphics);
        this.tfPass.painTextInputNoneBackGround(mgraphics);
        if (!this.tfUser.isFocused() && this.tfUser.getText().equals("")) {
            mFont.tahoma_place_holder.drawString(g, SupportTranslate.getTextLangue("username"), this.arrX[2] + 7, (this.arrH[2] / 2) + this.arrY[2], 4);
        }
        if (!this.tfPass.isFocused() && this.tfPass.getText().equals("")) {
            mFont.tahoma_place_holder.drawString(g, SupportTranslate.getTextLangue("password"), this.arrX[4] + 7, (this.arrH[4] / 2) + this.arrY[4], 4);
        }
        MyCheckBox myCheckBox = this.checkBox;
        if (myCheckBox != null) {
            myCheckBox.paint(mgraphics);
        }
        this.fontPaintRemember.drawString(g, SupportTranslate.getTextLangue("remember"), this.margin + this.arrX[5] + this.arrW[5], this.arrY[5] + (this.arrH[5] / 2), 4);
        mgraphics.isCircle = true;
        mgraphics.drawImage(LoadDataManager.imgLineLogin, (float) this.xLine, (float) this.yLine);
        for (int i2 = 0; i2 < this.xIcon.length; i2++) {
            CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameBgOAuth2, this.xOauth2[i2], this.yOauth2[i2], this.wOauth2, this.isFocusOauth2[i2], SupportPaint.TYPE_NONE, false, 100, false);
            SupportPaint supportPaint = CanvasNinja.paintz;
            FrameImage frameImage = this.iconOauth2[i2];
            supportPaint.paintTagFrame(g, frameImage, this.xIcon[i2], this.yIcon[i2], (int) frameImage.frameWidth, this.isFocusOauth2[i2], SupportPaint.TYPE_NONE, false, 100, false);
            if (this.isFocusOauth2[i2]) {
                this.fontPaintOauth2Focus.drawString(mgraphics, this.textOauth2[i2], this.xTextOauth2[i2], this.yTextOauth2[i2]);
            } else {
                this.fontPaintOauth2.drawString(mgraphics, this.textOauth2[i2], this.xTextOauth2[i2], this.yTextOauth2[i2]);
            }
        }
    }

    public void paintShape(mGraphics g) {
    }

    public void resetCameraY() {
        this.cmy = 0;
        this.cmtoY = 0;
        isUseScroll = false;
    }

    public void updateKey() {
        if (mSystem.isTest) {
            Iterator<MyButton> it = this.vecBtnTest.iterator();
            while (it.hasNext()) {
                MyButton btn = it.next();
                btn.update();
                btn.updatePointer();
            }
        }
        MyButton myButton = this.btnClearData;
        if (myButton != null) {
            myButton.updatePointer();
        }
        int i = 0;
        while (true) {
            if (i >= this.xIcon.length) {
                break;
            }
            this.isFocusOauth2[i] = CanvasNinja.isPointerHoldOrHover(this.xOauth2[i], this.yOauth2[i], this.wOauth2, this.hOauth2);
            if (!CanvasNinja.isPointerRelease || !CanvasNinja.isPoint(this.xOauth2[i], this.yOauth2[i], this.wOauth2, this.hOauth2)) {
                i++;
            } else {
                CanvasNinja.clearAllPointer();
                this.isFocusOauth2[i] = true;
                if (NinjaMidlet.isPC || NinjaMidlet.isIOS) {
                    this.cmdLoginOauth2.subAction = i;
                }
                this.cmdLoginOauth2.perform();
            }
        }
        super.updateKey();
    }

    public void commandTab(int index, int subIndex) {
        Res.outz("Vào commandTab trong LoginScr:" + index);
        switch (index) {
            case 1:
                doLogin();
                return;
            case 2:
                mSystem.openUrl("https://ccngdemo.teamobi.com//dang-ky");
                return;
            case 3:
                if (subIndex == 0) {
                    NinjaWar.me.signInWithGooogle();
                    return;
                } else if (subIndex == 1) {
                    NinjaWar.me.signInWithApple();
                    return;
                } else {
                    return;
                }
            case 99:
                Rms.clearAll();
                CanvasNinja.resetLogin();
                CanvasNinja.startOKDlg(SupportTranslate.getTextLangue("clearDataSuccess"));
                Res.outz(1, SupportTranslate.getTextLangue("clearDataSuccess"));
                return;
            default:
                return;
        }
    }

    public void keyTyped() {
        if (this.tfUser.isFocused()) {
            this.tfUser.keyTyped();
        } else if (this.tfPass.isFocused()) {
            this.tfPass.keyTyped();
        }
    }

    public void keyPress(int keyCode) {
        Res.outz("Login scr keyCode:" + keyCode);
        if (keyCode == KEY.KEY_TAB) {
            CanvasNinja.clearAllPointer();
            CanvasNinja.keyPCTyped = "";
            if (this.tfUser.isFocused()) {
                this.tfUser.setFocus(false);
                this.tfPass.setFocus(true);
            } else if (this.tfPass.isFocused()) {
                this.tfUser.setFocus(true);
                this.tfPass.setFocus(false);
            } else {
                this.tfUser.setFocus(true);
                this.tfPass.setFocus(false);
            }
        } else {
            if (keyCode == KEY.KEY_ENTER) {
                CanvasNinja.keyPCTyped = "";
                doLogin();
            }
            if (this.tfUser.isFocused()) {
                this.tfUser.keyPressed(keyCode);
            } else if (this.tfPass.isFocused()) {
                this.tfPass.keyPressed(keyCode);
            }
        }
    }

    public String checkInputUsenameAndPassword() {
        if (this.tfUser.getText().equals("")) {
            return SupportTranslate.getTextLangue("username") + SupportTranslate.getTextLangue("notNull");
        } else if (!this.tfPass.getText().equals("")) {
            return "";
        } else {
            return SupportTranslate.getTextLangue("password") + SupportTranslate.getTextLangue("notNull");
        }
    }

    public void perform(int idAction, Object p) {
    }

    public void resize() {
        super.resize();
        mFont mfont = mFont.tahoma_7b_brown;
        this.fontPaintUsername = mfont;
        this.fontPaintRemember = mfont;
        this.fontPlaceHolder = mFont.nameFontYellow;
        this.fontPaintTitle = mFont.tahoma_7b_white_orange;
    }
}
