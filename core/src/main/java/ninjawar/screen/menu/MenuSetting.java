package ninjawar.screen.menu;

import java.io.PrintStream;
import ninjawar.input.KEY;
import ninjawar.input.MyButton;
import ninjawar.input.MyRadio;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.mylib.AudioManager;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.Rms;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.Res;
import ninjawar.myscr.SplashScr;
import ninjawar.screen.DownLoadingScr;
import ninjawar.screen.LoginScr;
import ninjawar.screen.dialog.MessageDialog;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

public class MenuSetting extends MenuMain {
    public static boolean isMuteAll = false;
    public static boolean isSetSoundRelease = true;
    public static boolean isTest = false;
    public static MenuSetting me;
    public int[] arrH;
    public int[] arrW;
    public int[] arrX;
    public int[] arrY;
    public MyButton[] btns;
    MyCommand cmdChangeAccount;
    MyCommand cmdLogOut;
    public mFont fontPaintText = mFont.tahoma_7b_brown;
    public mFont fontPaintTextCam = mFont.tahoma_7_orange;
    int hProcess = ((int) LoadDataManager.frameAmThanh.frameHeight);
    boolean[] isSetVolume;
    public MyRadio[] radios;
    public String[] textSetting = {SupportTranslate.getTextLangue("music"), SupportTranslate.getTextLangue("sound"), SupportTranslate.getTextLangue("option")};
    int[] typePaint;
    int[] typePaintPercent;
    int[] wDefaultProcess;
    int[] wProcess;
    int[] wStartProcess;
    int[] xProcess;
    int[] xStartProcess;
    int yMoreTextHCenter = -2;
    int[] yProcess;

    public static MenuSetting gI() {
        MenuSetting menuSetting = me;
        if (menuSetting != null) {
            return menuSetting;
        }
        MenuSetting menuSetting2 = new MenuSetting();
        me = menuSetting2;
        return menuSetting2;
    }

    public void initCmd() {
        initClose();
        this.cmdChangeAccount = new MyCommand("", 0, this);
        this.cmdLogOut = new MyCommand("", 1, this);
    }

    public void initVolumn() {
        int percentVolumnBG = Res.convertStringToNumber(Rms.loadRMSString(Rms.RMS_VOLUMN_BG));
        int percentVolumnEff = Res.convertStringToNumber(Rms.loadRMSString(Rms.RMS_VOLUMN_EFF));
        int percentVolumnBG2 = percentVolumnBG == -1 ? 100 : percentVolumnBG;
        int percentVolumnEff2 = percentVolumnEff == -1 ? 100 : percentVolumnEff;
        Res.outz("percentVolumnEff từ RMS:" + percentVolumnEff2);
        int[] iArr = new int[2];
        int[] iArr2 = this.arrW;
        int i = iArr2[1];
        int i2 = (i * percentVolumnBG2) % 100;
        int i3 = (i * percentVolumnBG2) / 100;
        if (i2 != 0) {
            i3++;
        }
        iArr[0] = i3;
        int i4 = iArr2[8];
        iArr[1] = (i4 * percentVolumnEff2) % 100 == 0 ? (i4 * percentVolumnEff2) / 100 : ((i4 * percentVolumnEff2) / 100) + 1;
        this.wProcess = iArr;
        int[] iArr3 = this.arrY;
        this.yProcess = new int[]{iArr3[1], iArr3[8]};
        this.wStartProcess = new int[]{iArr2[1], i4};
        this.wDefaultProcess = new int[]{iArr2[1], i4};
        this.typePaint = new int[]{0, 1, 1, 1, 2};
        this.typePaintPercent = new int[]{0, 1, 1, 1, 1};
        this.isSetVolume = new boolean[]{false, false};
        int[] iArr4 = this.arrX;
        int i5 = iArr4[8];
        this.xProcess = new int[]{iArr4[1], i5};
        this.xStartProcess = new int[]{iArr4[1], i5};
    }

    public void init() {
        initCmd();
        this.fontPaintTile = mFont.tahoma_7b_white;
        this.textTitle = SupportTranslate.getTextLangue("setting").toUpperCase();
        this.margin = 5;
        this.margin7 = 7;
        this.w = 340;
        int i = CanvasNinja.w;
        if (340 > i) {
            this.w = i - 20;
        }
        MyButton[] myButtonArr = new MyButton[2];
        this.btns = myButtonArr;
        FrameImage[] frameImageArr = LoadDataManager.myButtons;
        myButtonArr[0] = new MyButton(frameImageArr[2], frameImageArr[3], 50, 2, SupportTranslate.getTextLangue("deleteAccount"), 0, 0, this.cmdChangeAccount);
        MyButton[] myButtonArr2 = this.btns;
        FrameImage[] frameImageArr2 = LoadDataManager.myButtons;
        myButtonArr2[1] = new MyButton(frameImageArr2[1], frameImageArr2[3], 50, 2, SupportTranslate.getTextLangue("logout"), 0, 0, this.cmdLogOut);
        this.arrH = new int[]{LoadDataManager.imgNhac.getRHeight(), (int) LoadDataManager.frameAmThanh.frameHeight, this.fontPaintTextCam.getHeight(), LoadDataManager.imgDongSE.getRHeight(), LoadDataManager.imgDong.getRHeight(), LoadDataManager.imgDongSE.getRHeight(), LoadDataManager.imgAmLuong.getRHeight(), this.fontPaintText.getHeight(), (int) LoadDataManager.frameAmThanh.frameHeight, this.fontPaintTextCam.getHeight(), LoadDataManager.imgDongSE.getRHeight(), LoadDataManager.imgDong.getRHeight(), LoadDataManager.imgDongSE.getRHeight(), LoadDataManager.imgCauHinh.getRHeight(), this.fontPaintText.getHeight(), (int) LoadDataManager.frameRadio.frameHeight, this.fontPaintTextCam.getHeight(), (int) LoadDataManager.frameRadio.frameHeight, this.fontPaintTextCam.getHeight(), LoadDataManager.imgDongSE.getRHeight(), LoadDataManager.imgDong.getRHeight(), LoadDataManager.imgDongSE.getRHeight(), this.fontPaintText.getHeight()};
        this.h = 200;
        int i2 = CanvasNinja.w;
        int i3 = this.w;
        this.x = (i2 - i3) / 2;
        this.y = (CanvasNinja.h - 200) / 2;
        int margin2Ben = this.margin * 3;
        int rWidth = LoadDataManager.imgNhac.getRWidth() + this.margin + this.fontPaintText.getWidth(this.textSetting[1]);
        int i4 = this.margin;
        int wThanhNhac = i3 - ((((rWidth + (i4 * 3)) + (i4 * 3)) + this.fontPaintTextCam.getWidth("100")) + (margin2Ben * 2));
        int[] iArr = {LoadDataManager.imgNhac.getRWidth(), wThanhNhac, this.fontPaintTextCam.getWidth("100"), LoadDataManager.imgDongSE.getRWidth(), LoadDataManager.imgDong.getRWidth(), LoadDataManager.imgDongSE.getRWidth(), LoadDataManager.imgAmLuong.getRWidth(), this.fontPaintText.getWidth(this.textSetting[1]), wThanhNhac, this.fontPaintTextCam.getWidth("100"), LoadDataManager.imgDongSE.getRWidth(), LoadDataManager.imgDong.getRWidth(), LoadDataManager.imgDongSE.getRWidth(), LoadDataManager.imgCauHinh.getRWidth(), this.fontPaintText.getWidth(this.textSetting[2]), (int) LoadDataManager.frameRadio.frameWidth, this.fontPaintTextCam.getWidth(SupportTranslate.getTextLangue("low")), (int) LoadDataManager.frameRadio.frameWidth, this.fontPaintTextCam.getWidth(SupportTranslate.getTextLangue("high")), LoadDataManager.imgDongSE.getRWidth(), LoadDataManager.imgDong.getRWidth(), LoadDataManager.imgDongSE.getRWidth(), this.fontPaintText.getWidth(this.textSetting[0])};
        this.arrW = iArr;
        int i5 = iArr[3];
        int wShurikenNgang = (i5 * 2) + iArr[4];
        int i6 = this.x;
        int xStartShuriken = ((this.w - wShurikenNgang) / 2) + i6;
        int xEndShuriken = (xStartShuriken + wShurikenNgang) - i5;
        int i7 = this.margin;
        int i8 = iArr[7];
        int i9 = iArr[3];
        int i10 = iArr[7];
        int i11 = iArr[15];
        int i12 = iArr[16];
        this.arrX = new int[]{i6 + i7 + margin2Ben, i6 + (i7 * 2) + margin2Ben + i8 + (i7 * 6), i6 + (i7 * 4) + margin2Ben + i8 + (i7 * 6) + iArr[1], xStartShuriken, xStartShuriken + i9, xEndShuriken, i6 + i7 + margin2Ben, i6 + (i7 * 2) + margin2Ben + iArr[6], i6 + (i7 * 2) + margin2Ben + i10 + (i7 * 6), i6 + (i7 * 4) + margin2Ben + i10 + iArr[8] + (i7 * 6), xStartShuriken, xStartShuriken + i9, xEndShuriken, i6 + i7 + margin2Ben, i6 + (i7 * 2) + margin2Ben + iArr[13], i6 + (i7 * 2) + margin2Ben + i10 + (i7 * 4), i6 + (i7 * 2) + margin2Ben + i10 + (i7 * 4) + i11 + i7, i6 + (i7 * 2) + margin2Ben + i10 + (i7 * 4) + i11 + i7 + i12 + i7, i6 + (i7 * 2) + margin2Ben + i10 + (i7 * 4) + i11 + i7 + i12 + i7 + iArr[17] + i7, xStartShuriken, xStartShuriken + i9, xEndShuriken, i6 + (i7 * 2) + margin2Ben + iArr[0]};
        int hBtn = (int) LoadDataManager.myButtons[0].frameHeight;
        int yStartAfterClose = this.y + LoadDataManager.imgClose.getRWidth();
        int[] iArr2 = this.arrH;
        int marginTrenDuoi = ((this.h - ((((iArr2[0] * 3) + (this.margin * 6)) + (iArr2[3] * 3)) + hBtn)) - hBtn) / 2;
        int i13 = iArr2[0];
        int[] iArr3 = this.arrH;
        int i14 = iArr3[0];
        int i15 = this.margin;
        int i16 = yStartAfterClose + marginTrenDuoi + i14 + (i15 * 2);
        int i17 = iArr3[3];
        int[] iArr4 = this.arrH;
        int i18 = iArr4[0];
        int i19 = this.margin;
        int i20 = yStartAfterClose + marginTrenDuoi + i18 + (i19 * 2);
        int i21 = iArr4[3];
        int i22 = iArr4[6];
        int[] iArr5 = this.arrH;
        int i23 = iArr5[0];
        int i24 = this.margin;
        int i25 = yStartAfterClose + marginTrenDuoi + i23 + (i24 * 2);
        int i26 = iArr5[3];
        int i27 = iArr5[6];
        int i28 = iArr5[10];
        int[] iArr6 = this.arrH;
        int i29 = iArr6[0];
        int i30 = this.margin;
        int i31 = yStartAfterClose + marginTrenDuoi + i29 + (i30 * 4);
        int i32 = iArr6[3];
        int i33 = iArr6[6];
        int i34 = iArr6[10];
        int i35 = iArr6[13];
        int[] iArr7 = this.arrH;
        int i36 = iArr7[0];
        int i37 = this.margin;
        int i38 = yStartAfterClose + marginTrenDuoi + i36 + (i37 * 4);
        int i39 = iArr7[3];
        int i40 = iArr7[6];
        int i41 = iArr7[10];
        int i42 = iArr7[13];
        int[] iArr8 = this.arrH;
        int i43 = iArr8[0];
        int i44 = this.margin;
        int i45 = iArr8[3];
        int i46 = iArr8[6];
        int i47 = iArr8[10];
        int i48 = iArr8[13];
        this.arrY = new int[]{yStartAfterClose + marginTrenDuoi, ((yStartAfterClose + marginTrenDuoi) + (i13 / 2)) - (iArr2[1] / 2), (((yStartAfterClose + marginTrenDuoi) + (i13 / 2)) - (this.fontPaintTextCam.getHeight() / 2)) + this.yMoreTextHCenter, yStartAfterClose + marginTrenDuoi + i14 + i15, yStartAfterClose + marginTrenDuoi + i14 + i15, yStartAfterClose + marginTrenDuoi + i14 + i15, i16 + i17, ((((((yStartAfterClose + marginTrenDuoi) + i14) + (i15 * 2)) + i17) + (iArr3[6] / 2)) - (this.fontPaintTextCam.getHeight() / 2)) + this.yMoreTextHCenter, ((i20 + i21) + (i22 / 2)) - (iArr4[8] / 2), ((((((yStartAfterClose + marginTrenDuoi) + i18) + (i19 * 2)) + i21) + (i22 / 2)) - (this.fontPaintTextCam.getHeight() / 2)) + this.yMoreTextHCenter, i25 + i26 + i27 + i24, yStartAfterClose + marginTrenDuoi + i23 + (i24 * 2) + i26 + i27 + i24, yStartAfterClose + marginTrenDuoi + i23 + (i24 * 2) + i26 + i27 + i24, yStartAfterClose + marginTrenDuoi + i23 + (i24 * 4) + i26 + i27 + i28, ((((((((yStartAfterClose + marginTrenDuoi) + i23) + (i24 * 4)) + i26) + i27) + i28) + (iArr5[13] / 2)) - (this.fontPaintText.getHeight() / 2)) + this.yMoreTextHCenter, ((((i31 + i32) + i33) + i34) + (i35 / 2)) - (iArr6[15] / 2), ((((((((yStartAfterClose + marginTrenDuoi) + i29) + (i30 * 4)) + i32) + i33) + i34) + (i35 / 2)) - (this.fontPaintTextCam.getHeight() / 2)) + this.yMoreTextHCenter, ((((i38 + i39) + i40) + i41) + (i42 / 2)) - (iArr7[15] / 2), ((((((((yStartAfterClose + marginTrenDuoi) + i36) + (i37 * 4)) + i39) + i40) + i41) + (i42 / 2)) - (this.fontPaintTextCam.getHeight() / 2)) + this.yMoreTextHCenter, yStartAfterClose + marginTrenDuoi + i43 + (i44 * 4) + i45 + i46 + i47 + i48 + i44, yStartAfterClose + marginTrenDuoi + i43 + (i44 * 4) + i45 + i46 + i47 + i48 + i44, yStartAfterClose + marginTrenDuoi + i43 + (i44 * 4) + i45 + i46 + i47 + i48 + i44, (((yStartAfterClose + marginTrenDuoi) + (i43 / 2)) - (this.fontPaintText.getHeight() / 2)) + this.yMoreTextHCenter};
        this.cmdClose.x = (this.x + this.w) - LoadDataManager.imgClose.getRWidth();
        this.cmdClose.y = this.y;
        int marginBtn = this.margin * 3;
        int sumBtn = MyButton.getSumWidthListBtn(this.btns, marginBtn);
        MyButton.setPosList(this.btns, marginBtn, sumBtn, this.x + ((this.w - sumBtn) / 2), this.arrY[19] + this.arrH[19] + this.margin);
        int width = this.fontPaintTile.getWidth(SupportTranslate.getTextLangue("setting").toUpperCase()) + (this.margin * 4);
        this.wTitle = width;
        this.wTitle = Res.fixSizeTagFrameUp(8, width, LoadDataManager.frameTitle);
        this.hTitle = (int) LoadDataManager.frameTitle.frameHeight;
        int dataRmsCauHinh = Rms.loadRMSInt(Rms.RMS_CAU_HINH);
        int dataRmsCauHinh2 = dataRmsCauHinh != -1 ? dataRmsCauHinh : Rms.RMS_CAU_HINH_HIGH;
        MyRadio[] myRadioArr = new MyRadio[2];
        this.radios = myRadioArr;
        int i49 = marginBtn;
        int i50 = sumBtn;
        int i51 = hBtn;
        myRadioArr[0] = new MyRadio(this.arrX[15], this.arrY[15], this.arrW[15] + this.margin + this.fontPaintTextCam.getWidth(SupportTranslate.getTextLangue("low")), this.arrH[15], dataRmsCauHinh2 == Rms.RMS_CAU_HINH_LOW);
        MyRadio[] myRadioArr2 = this.radios;
        MyRadio myRadio = myRadioArr2[0];
        myRadio.dataSaveRms = Rms.RMS_CAU_HINH_LOW;
        myRadio.nameRms = Rms.RMS_CAU_HINH;
        int i52 = wThanhNhac;
        myRadioArr2[1] = new MyRadio(this.arrX[17], this.arrY[17], this.arrW[17] + this.margin + this.fontPaintTextCam.getWidth(SupportTranslate.getTextLangue("high")), this.arrH[17], dataRmsCauHinh2 == Rms.RMS_CAU_HINH_HIGH);
        MyRadio myRadio2 = this.radios[1];
        myRadio2.nameRms = Rms.RMS_CAU_HINH;
        myRadio2.dataSaveRms = Rms.RMS_CAU_HINH_HIGH;
        initVolumn();
    }

    public void switchToMe() {
        init();
        show();
    }

    public void updatePointer() {
        super.updatePointer();
        for (MyButton btn : this.btns) {
            btn.updatePointer();
        }
        int i = 0;
        while (true) {
            MyRadio[] myRadioArr = this.radios;
            if (i >= myRadioArr.length) {
                break;
            } else if (myRadioArr[i].isChecked()) {
                MyRadio.selectOneRadio(this.radios, i);
                CanvasNinja.clearAllPointer();
                break;
            } else {
                i++;
            }
        }
        if (CanvasNinja.isPointerDraggedX|| CanvasNinja.isPointerRelease) {
            int i2 = 0;
            while (true) {
                int[] iArr = this.yProcess;
                if (i2 < iArr.length) {
                    if (CanvasNinja.isPoint(this.xStartProcess[i2] - 20, iArr[i2] - 5, this.wStartProcess[i2] + 40, this.hProcess + 10)) {
                        boolean[] zArr = this.isSetVolume;
                        zArr[i2] = false;
                        int wTemp = CanvasNinja.px - this.xProcess[i2];
                        if (wTemp <= 0) {
                            wTemp = 0;
                        }
                        int[] iArr2 = this.wDefaultProcess;
                        if (wTemp >= iArr2[i2]) {
                            wTemp = iArr2[i2];
                        }
                        int[] iArr3 = this.wProcess;
                        iArr3[i2] = wTemp;
                        if (i2 == 0) {
                            zArr[i2] = true;
                            isSetSoundRelease = false;
                            CanvasNinja.isPointerDraggedX = false;
                            CanvasNinja.isPointerDraggedXDone = true;
                            int percentVolume = (int) ((((float) iArr3[0]) / ((float) iArr2[0])) * 100.0f);
                            AudioManager.setVolumeBackground(percentVolume);
                            Rms.saveRMSString(Rms.RMS_VOLUMN_BG, percentVolume + "");
                            CanvasNinja.clearAllPointer();
                            return;
                        } else if (i2 == 1) {
                            zArr[i2] = true;
                            isSetSoundRelease = false;
                            CanvasNinja.isPointerDraggedX = false;
                            CanvasNinja.isPointerDraggedXDone = true;
                            int percentVolume2 = (int) ((((float) iArr3[1]) / ((float) iArr2[1])) * 100.0f);
                            AudioManager.setVolumeEff(percentVolume2);
                            Rms.saveRMSString(Rms.RMS_VOLUMN_EFF, percentVolume2 + "");
                            CanvasNinja.clearAllPointer();
                            return;
                        }
                    }
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    public void update() {
        for (MyButton btn : this.btns) {
            btn.update();
        }
        for (MyRadio radio : this.radios) {
            radio.update();
        }
    }

    public void updateKey() {
        updatePointer();
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        super.paint(g);
        mgraphics.drawImage(LoadDataManager.imgNhac, (float) this.arrX[0], (float) this.arrY[0]);
        this.fontPaintText.drawString(mgraphics, this.textSetting[0], this.arrX[22], this.arrY[22]);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameAmThanh, this.arrX[1], this.arrY[1], this.arrW[1], true, SupportPaint.TYPE_NONE, false);
        mgraphics.drawImage(LoadDataManager.imgDongSE, (float) this.arrX[3], (float) this.arrY[3]);
        mgraphics.drawImage(LoadDataManager.imgDong, (float) this.arrX[4], (float) this.arrY[4]);
        mgraphics.drawImage(LoadDataManager.imgDongSE, (float) this.arrX[5], (float) this.arrY[5]);
        mgraphics.drawImage(LoadDataManager.imgAmLuong, (float) this.arrX[6], (float) this.arrY[6]);
        this.fontPaintText.drawString(mgraphics, this.textSetting[1], this.arrX[7], this.arrY[7]);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameAmThanh, this.arrX[8], this.arrY[8], this.arrW[8], true, SupportPaint.TYPE_NONE, false);
        int i = 0;
        while (true) {
            int[] iArr = this.yProcess;
            if (i >= iArr.length) {
                break;
            }
            CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameAmThanh, this.xStartProcess[i], iArr[i], this.wProcess[i], false, SupportPaint.TYPE_NONE, false);
            Image image = LoadDataManager.imgTruotAmThanh;
            mgraphics.drawImage(image, (float) ((this.xStartProcess[i] + this.wProcess[i]) - (image.getRWidth() / 2)), (float) ((this.yProcess[i] + (this.hProcess / 2)) - (LoadDataManager.imgTruotAmThanh.getRHeight() / 2)));
            i++;
        }
        this.fontPaintTextCam.drawString(mgraphics, ((int) ((((float) this.wProcess[0]) / ((float) this.wDefaultProcess[0])) * 100.0f)) + "", this.arrX[2], this.arrY[2]);
        this.fontPaintTextCam.drawString(mgraphics, ((int) ((((float) this.wProcess[1]) / ((float) this.wDefaultProcess[1])) * 100.0f)) + "", this.arrX[9], this.arrY[9]);
        MyRadio[] myRadioArr = this.radios;
        int length = myRadioArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            myRadioArr[i2].paint(mgraphics);
        }
        this.fontPaintTextCam.drawString(mgraphics, SupportTranslate.getTextLangue("low"), this.arrX[16], this.arrY[16]);
        this.fontPaintTextCam.drawString(mgraphics, SupportTranslate.getTextLangue("high"), this.arrX[18], this.arrY[18]);
        mgraphics.drawImage(LoadDataManager.imgDongSE, (float) this.arrX[10], (float) this.arrY[10]);
        mgraphics.drawImage(LoadDataManager.imgDong, (float) this.arrX[11], (float) this.arrY[11]);
        mgraphics.drawImage(LoadDataManager.imgDongSE, (float) this.arrX[12], (float) this.arrY[12]);
        mgraphics.drawImage(LoadDataManager.imgCauHinh, (float) this.arrX[13], (float) this.arrY[13]);
        this.fontPaintText.drawString(mgraphics, this.textSetting[2], this.arrX[14], this.arrY[14]);
        mgraphics.drawImage(LoadDataManager.imgDongSE, (float) this.arrX[19], (float) this.arrY[19]);
        mgraphics.drawImage(LoadDataManager.imgDong, (float) this.arrX[20], (float) this.arrY[20]);
        mgraphics.drawImage(LoadDataManager.imgDongSE, (float) this.arrX[21], (float) this.arrY[21]);
        for (MyButton btn : this.btns) {
            btn.paintButton(mgraphics, false);
        }
    }

    public void commandTab(int index, int subIndex) {
        int i = index;
        PrintStream printStream = System.out;
        printStream.println("commandTab trong menuSetting:" + i);
        super.commandTab(index, subIndex);
        switch (i) {
            case 0:
                Res.outz(1, "Xóa acc");
                MyButton[] btnOutAlls = new MyButton[2];
                FrameImage[] frameImageArr = LoadDataManager.myButtons;
                btnOutAlls[0] = new MyButton(frameImageArr[2], frameImageArr[3], 55, 0, SupportTranslate.getTextLangue("YES"), 0, 0, new MyCommand("", -95, this));
                FrameImage[] frameImageArr2 = LoadDataManager.myButtons;
                btnOutAlls[1] = new MyButton(frameImageArr2[1], frameImageArr2[3], btnOutAlls[0].w, 0, SupportTranslate.getTextLangue("huy"), 0, 0, new MyCommand("", -1, this));
                MessageDialog.gI().startMsgDialog(SupportTranslate.getTextLangue("confirmDeleteAccount"), btnOutAlls);
                return;
            case 1:
                NinjaMidlet.isSendClientOK = false;
                DownLoadingScr.isSendClientOk = false;
                closeMenu();
                SendMessage.gI().logOut();
                LoginScr.isLogOut = true;
                CanvasNinja.resetLogin();
                SplashScr.gI().switchToMe();
                return;
            case 3:
                Res.outz(1, "Xóa acc");
                MyButton[] btnOutAlls2 = new MyButton[2];
                FrameImage[] frameImageArr3 = LoadDataManager.myButtons;
                btnOutAlls2[0] = new MyButton(frameImageArr3[2], frameImageArr3[3], 55, 0, SupportTranslate.getTextLangue("YES"), 0, 0, new MyCommand("", -95, this));
                FrameImage[] frameImageArr4 = LoadDataManager.myButtons;
                btnOutAlls2[1] = new MyButton(frameImageArr4[1], frameImageArr4[3], btnOutAlls2[0].w, 0, SupportTranslate.getTextLangue("huy"), 0, 0, new MyCommand("", -1, this));
                MessageDialog.gI().startMsgDialog(SupportTranslate.getTextLangue("confirmDeleteAccount"), btnOutAlls2);
                return;
            default:
                return;
        }
    }

    public void keyPress(int keyCode) {
        if (keyCode == KEY.KEY_ESC) {
            closeMenu();
        }
    }
}
