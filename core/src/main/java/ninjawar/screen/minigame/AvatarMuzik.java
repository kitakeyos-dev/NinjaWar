package ninjawar.screen.minigame;

import ninjawar.input.KEY;
import ninjawar.input.MyButton;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.mylib.BaseScreen;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.MyTile;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;

public class AvatarMuzik extends BaseScreen {
    public static AvatarMuzik me;
    private final int BURST_DURATION = 3;
    private final int BUTTON_HEIGHT = LoadDataManager.frameNutBam.getRHeight();
    private final int BUTTON_MARGIN = 10;
    private final int BUTTON_WIDTH = LoadDataManager.frameNutBam.getRWidth();
    private final int EXPLOSION_DURATION = 5;
    private final int GIRL_EXTRA_DURATION = 15;
    private final int HEART_FRAME_DURATION = 2;
    private int ballFrameIndex = 0;
    private float ballSpeed;
    private final int ballWidth = LoadDataManager.frameVienBi.getRWidth();
    private int ballX;
    private int ballY;
    MyButton btnBack;
    private int burstFrame = 0;
    private int burstTimer = 0;
    private int burstX;
    private int burstY;
    private int buttonFrameIndex = 0;
    private int buttonX;
    private int buttonY;
    MyCommand cmdBack;
    private int explosionFrame = 0;
    private int explosionTimer = 0;
    private int explosionX;
    private int explosionY;
    private final int frameWidth = 400;
    private int frameX;
    private int frameY;
    private int girlTimer = 0;
    private int girlX;
    private int girlY;
    private int goalFrameIndex = 0;
    private int goalWidth = LoadDataManager.frameGoal.getRWidth();
    private int goalX;
    private int goalY;
    private int heartFrame = 0;
    private float[] heartSpeedX = new float[3];
    private float[] heartSpeedY = new float[3];
    private int heartTimer = 0;
    private int[] heartX = new int[3];
    private int[] heartY = new int[3];
    private boolean isPaused;
    int margin = 5;
    private boolean movingRight;
    public short nStep = 3;
    private boolean showBurst = false;
    private boolean showExplosion = false;
    private boolean showGirl = false;
    private boolean showHearts = false;
    private int stepCount = 0;

    public static AvatarMuzik gI() {
        if (me == null) {
            me = new AvatarMuzik();
        }
        return me;
    }

    public void initCmd() {
        this.cmdBack = new MyCommand("", -11, this);
        FrameImage frameImage = LoadDataManager.myButtons[1];
        int i = this.margin;
        MyButton myButton = new MyButton(frameImage, frameImage, 75, 0, "Quay lại", i * 4, CanvasNinja.h - (i * 8), this.cmdBack);
        this.btnBack = myButton;
        myButton.isSmall = true;
    }

    public void commandTab(int index, int subIndex) {
        Res.outz(1, "vo ông nội commandTab AvatarMuzik " + index);
        switch (index) {
            case -11:
                MapScr.gI().switchToMe();
                return;
            default:
                return;
        }
    }

    public void init() {
        this.frameX = (CanvasNinja.w - 400) / 2;
        this.frameY = 300;
        this.goalWidth = LoadDataManager.frameGoal.getRWidth();
        repositionGoal();
        this.goalFrameIndex = 0;
        this.ballX = this.frameX + this.margin;
        this.ballY = (this.frameY + (LoadDataManager.frameThanh2.getRHeight() / 2)) - (this.ballWidth / 2);
        this.ballSpeed = 10.0f;
        this.movingRight = true;
        this.isPaused = false;
        this.ballFrameIndex = 0;
        this.showExplosion = false;
        this.explosionFrame = 0;
        this.showBurst = false;
        this.burstFrame = 0;
        this.showGirl = false;
        this.girlTimer = 0;
        this.showHearts = false;
        this.heartFrame = 0;
        this.heartTimer = 0;
        for (int i = 0; i < 3; i++) {
            this.heartX[i] = 0;
            this.heartY[i] = 0;
            this.heartSpeedX[i] = 0.0f;
            this.heartSpeedY[i] = 0.0f;
        }
        this.buttonX = (CanvasNinja.w - this.BUTTON_WIDTH) - 10;
        this.buttonY = (CanvasNinja.h - this.BUTTON_HEIGHT) - 10;
        this.buttonFrameIndex = 0;
        this.stepCount = 0;
        initCmd();
    }

    private void repositionGoal() {
        this.goalX = (this.frameX + 400) - (this.goalWidth * 2);
        this.goalY = (this.frameY + (LoadDataManager.frameThanh2.getRHeight() / 2)) - (LoadDataManager.frameGoal.getRHeight() / 2);
    }

    public void switchToMe() {
        init();
        CanvasNinja.endDlg();
        super.switchToMe();
    }

    public void update() {
        super.update();
        if (!this.isPaused) {
            if (this.movingRight) {
                int i = (int) (((float) this.ballX) + this.ballSpeed);
                this.ballX = i;
                int i2 = this.ballWidth;
                int i3 = i + i2;
                int i4 = this.frameX;
                int i5 = this.margin;
                if (i3 >= (i4 + 400) - i5) {
                    this.ballX = ((i4 + 400) - i2) - i5;
                    this.movingRight = false;
                }
            } else {
                int i6 = (int) (((float) this.ballX) - this.ballSpeed);
                this.ballX = i6;
                int i7 = this.frameX;
                int i8 = this.margin;
                if (i6 <= i7 + i8) {
                    this.ballX = i7 + i8;
                    this.movingRight = true;
                }
            }
            this.goalFrameIndex = 0;
            this.ballFrameIndex = 0;
        }
        if (this.showExplosion) {
            int i9 = this.explosionTimer + 1;
            this.explosionTimer = i9;
            if (i9 >= 5) {
                this.explosionTimer = 0;
                int i10 = this.explosionFrame + 1;
                this.explosionFrame = i10;
                if (i10 > 1) {
                    this.showExplosion = false;
                    this.explosionFrame = 0;
                    this.ballFrameIndex = 0;
                    this.goalFrameIndex = 0;
                }
            }
        }
        if (this.showBurst) {
            int i11 = this.burstTimer + 1;
            this.burstTimer = i11;
            if (i11 >= 3) {
                this.burstTimer = 0;
                int i12 = this.burstFrame + 1;
                this.burstFrame = i12;
                if (i12 > 8) {
                    this.showBurst = false;
                    this.burstFrame = 0;
                }
            }
        }
        if (this.showGirl) {
            if (!this.showBurst) {
                int i13 = this.girlTimer + 1;
                this.girlTimer = i13;
                if (i13 >= 15) {
                    this.showGirl = false;
                    this.showHearts = false;
                    this.girlTimer = 0;
                    if (this.stepCount >= this.nStep) {
                        CanvasNinja.startOKDlg("Chúc mừng! Bạn đã thắng!", 69);
                        SendMessage.gI().requestDoneMiniGame();
                    }
                }
            }
            if (this.showHearts) {
                int i14 = this.heartTimer + 1;
                this.heartTimer = i14;
                if (i14 >= 2) {
                    this.heartTimer = 0;
                    this.heartFrame = (this.heartFrame + 1) % 2;
                }
                for (int i15 = 0; i15 < 3; i15++) {
                    int[] iArr = this.heartX;
                    iArr[i15] = (int) (((float) iArr[i15]) + this.heartSpeedX[i15]);
                    int[] iArr2 = this.heartY;
                    float[] fArr = this.heartSpeedY;
                    float f = fArr[i15];
                    iArr2[i15] = (int) (((float) iArr2[i15]) + f);
                    fArr[i15] = f - 0.05f;
                }
            }
        }
        this.btnBack.update();
    }

    public void updateKey() {
        if (CanvasNinja.isPointerRelease() && !this.showGirl && CanvasNinja.isPoint(this.buttonX, this.buttonY, this.BUTTON_WIDTH + this.margin, this.BUTTON_HEIGHT + 10)) {
            CanvasNinja.clearAllPointer();
            handleButtonAction();
        }
        this.btnBack.updatePointer();
    }

    public void keyPress(int keyCode) {
        if (keyCode == KEY.KEY_SPACE && !this.showGirl) {
            handleButtonAction();
        }
        super.keyPress(keyCode);
    }

    private void handleButtonAction() {
        boolean z = !this.isPaused;
        this.isPaused = z;
        this.buttonFrameIndex = z ? 1 : 0;
        if (z) {
            int i = this.ballX;
            int i2 = this.margin;
            int i3 = this.ballWidth + i + i2;
            int i4 = this.goalX;
            if (i3 < i4 || i > (i4 + this.goalWidth) - i2) {
                this.goalFrameIndex = 2;
                this.ballFrameIndex = 2;
                this.stepCount = 0;
                CanvasNinja.startOKDlg("Bạn đã trượt! Chơi lại từ đầu.");
                return;
            }
            this.goalFrameIndex = 1;
            this.ballFrameIndex = 1;
            this.ballSpeed *= 1.1f;
            this.stepCount++;
            this.showExplosion = true;
            this.explosionFrame = 0;
            this.explosionTimer = 0;
            this.explosionX = i - ((LoadDataManager.frameEffNo.getRWidth() - this.ballWidth) / 2);
            this.explosionY = this.ballY - ((LoadDataManager.frameEffNo.getRHeight() - this.ballWidth) / 2);
            this.showBurst = true;
            this.burstFrame = 0;
            this.burstTimer = 0;
            this.burstX = ((CanvasNinja.w / 2) - (LoadDataManager.frameEffBungNo.getRWidth() / 2)) - this.margin;
            this.burstY = (CanvasNinja.h / 2) - (LoadDataManager.frameEffBungNo.getRHeight() / 2);
            this.showGirl = true;
            this.girlX = ((CanvasNinja.w / 2) - (LoadDataManager.imgCoGai.getRWidth() / 2)) - this.margin;
            this.girlY = (CanvasNinja.h / 2) - (LoadDataManager.imgCoGai.getRHeight() / 2);
            this.girlTimer = 0;
            this.showHearts = true;
            this.heartFrame = 0;
            this.heartTimer = 0;
            int i5 = this.margin;
            int startX = (CanvasNinja.w / 2) + (i5 * 2);
            int startY = (CanvasNinja.h / 2) - (i5 * 3);
            for (int i6 = 0; i6 < 3; i6++) {
                this.heartX[i6] = ((LoadDataManager.frameTraiTim.getRWidth() + 5) * i6) + startX;
                this.heartY[i6] = startY;
                this.heartSpeedX[i6] = (float) ((Math.random() * 2.0d) - 1.0d);
                this.heartSpeedY[i6] = -1.0f;
            }
            repositionGoal();
            return;
        }
        this.ballX = this.frameX;
        this.movingRight = true;
        this.goalFrameIndex = 0;
        this.ballFrameIndex = 0;
        this.showExplosion = false;
        this.showBurst = false;
        this.showGirl = false;
        this.showHearts = false;
        this.girlTimer = 0;
    }

    public void paint(mGraphics g) {
        paintBg(g);
        CanvasNinja.resetTrans(g);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameThanh2, this.frameX, this.frameY, 400, false);
        LoadDataManager.frameGoal.drawFrame(this.goalFrameIndex, (float) this.goalX, (float) this.goalY, g);
        LoadDataManager.frameVienBi.drawFrame(this.ballFrameIndex, (float) this.ballX, (float) this.ballY, g);
        if (this.showExplosion) {
            LoadDataManager.frameEffNo.drawFrame(this.explosionFrame, (float) this.explosionX, (float) this.explosionY, g);
        }
        if (this.showGirl && (this.burstFrame >= 5 || !this.showBurst)) {
            g.drawImage(LoadDataManager.imgCoGai, (float) this.girlX, (float) this.girlY);
            if (this.showHearts) {
                for (int i = 0; i < 3; i++) {
                    LoadDataManager.frameTraiTim.drawFrame((this.heartFrame + i) % 2, (float) this.heartX[i], (float) this.heartY[i], g);
                }
            }
        }
        if (this.showBurst) {
            LoadDataManager.frameEffBungNo.drawFrame(this.burstFrame, (float) this.burstX, (float) this.burstY, g);
        }
        this.btnBack.paint(g);
        LoadDataManager.frameNutBam.drawFrame(this.buttonFrameIndex, (float) this.buttonX, (float) this.buttonY, g);
        mFont mfont = mFont.fontTextTrang;
        mfont.drawString(g, "Pass: " + this.stepCount + "/" + this.nStep, this.frameX, this.frameY - 50);
    }

    public static void paintBg(mGraphics g) {
        int i = (MyTile.size * MyTile.tmh) - CanvasNinja.h;
        MapScr.cmtoY = i;
        MapScr.cmy = i;
        byte b = MyTile.size;
        MapScr.cmtoX = b;
        MapScr.cmx = b;
        g.translate(-b, (-MapScr.cmy) + (MyTile.size * 2));
        CanvasNinja.paintBGGameScr(g);
        g.translate(-MapScr.cmx, -MapScr.cmy);
        MyTile.paintTileMapSpec(g);
    }
}
