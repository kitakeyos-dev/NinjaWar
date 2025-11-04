package ninjawar.screen.tab;

import ninjawar.input.MyButton;
import ninjawar.input.MyRadio;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.model.PartyInfo;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.screen.dialog.MessageDialog;
import ninjawar.screen.selectbox.SelectBox;
import ninjawar.screen.subtab.SubTabInviteParty;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportTranslate;

public class TabCreateParty extends TabScr {
    public static mFont fontPaintName = mFont.fontPaintNameParty;
    public static mFont fontPaintNameFocus = mFont.fontPaintNamePartyFocus;
    public static mFont fontPaintRadio = mFont.fontPaintAutoAccept;
    public static TabCreateParty me;
    MyButton[] btnOutAlls;
    MyCommand cmdInvite;
    MyCommand cmdKick;
    int hTitle;
    public int indexSelected = -1;
    public int indexSelectedBox = -1;
    int marginLeftRight;
    int marginOneTab;
    int minWBtn0;
    String nameRadio = "";
    MyRadio radio;
    public SelectBox selectBox;
    String title = "";
    int transOneTab;
    int wSys;
    int wTitle;
    int xTitle;
    int[] yAnimClose = new int[1];
    int yTitle;

    public static TabCreateParty gI() {
        if (me == null) {
            me = new TabCreateParty();
        }
        return me;
    }

    public void paint(mGraphics g) {
        MyRadio myRadio;
        Player cTemp;
        mGraphics mgraphics = g;
        mGraphics mgraphics2 = g;
        CanvasNinja.paintz.paintSingleBorderFrame(mgraphics2, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        CanvasNinja.paintz.paintTagFrame(mgraphics2, LoadDataManager.frameTitle2, this.xTitle, this.yTitle, this.wTitle, false, 0, false);
        TabScr.fontPaintTile.drawString(g, this.title, (this.wTitle / 2) + this.xTitle, (this.hTitle / 2) + this.yTitle, 3);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgCam, (float) this.arrX[0], (float) this.arrY[0], (float) this.arrW[0], (float) this.arrH[0], 8, false);
        for (int i = 0; i < 5; i++) {
            PartyInfo partyInfo = TabParty.partyInfo;
            if (partyInfo == null || i >= partyInfo.vChars.size()) {
                cTemp = null;
            } else {
                cTemp = TabParty.partyInfo.vChars.get(i);
            }
            if (cTemp != null) {
                mgraphics.drawImage(LoadDataManager.imgNenChar, (float) (this.arrX[1] + (this.transOneTab * i)), (float) this.arrY[1]);
                cTemp.paintChar(mgraphics, this.arrX[3] + (this.transOneTab * i), this.arrY[3], true);
                if (i == 0) {
                    mgraphics.drawImage(LoadDataManager.imgFlagLeader, (float) this.arrX[2], (float) this.arrY[2]);
                }
                if (cTemp.isMe()) {
                    fontPaintNameFocus.drawString(g, cTemp.cName, this.arrX[4] + (this.transOneTab * i), this.arrY[4], 2);
                } else {
                    fontPaintName.drawString(g, cTemp.cName, this.arrX[4] + (this.transOneTab * i), this.arrY[4], 2);
                }
                int sumWLv = fontPaintName.getWidth("Lv." + cTemp.clevel) + this.wSys + 3;
                mgraphics.drawImage(LoadDataManager.imgHeIcon[cTemp.classId], (float) ((this.arrX[5] - (sumWLv / 2)) + (this.transOneTab * i)), (float) (this.arrY[5] + 2));
                if (cTemp.isMe()) {
                    fontPaintNameFocus.drawString(mgraphics, "LV." + cTemp.clevel, (this.arrX[5] - (sumWLv / 2)) + 3 + this.wSys + (this.transOneTab * i), this.arrY[5]);
                } else {
                    fontPaintName.drawString(mgraphics, "LV." + cTemp.clevel, (this.arrX[5] - (sumWLv / 2)) + 3 + this.wSys + (this.transOneTab * i), this.arrY[5]);
                }
                if (i != 0 && TabParty.isLeader()) {
                    Image image = LoadDataManager.imgCloseParty;
                    mgraphics.drawImage(image, (float) ((((this.arrX[1] + this.arrW[1]) - image.getRWidth()) - 3) + (this.transOneTab * i)), (float) (this.arrY[1] + 3 + this.yAnimClose[i]));
                }
            } else if (TabParty.isLeader()) {
                mgraphics.drawImage(LoadDataManager.imgNenCharPlus, (float) (this.arrX[1] + (this.transOneTab * i)), (float) this.arrY[1]);
            } else {
                mgraphics.drawImage(LoadDataManager.imgNenCharNone, (float) (this.arrX[1] + (this.transOneTab * i)), (float) this.arrY[1]);
            }
        }
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            int index = 0;
            for (MyButton btn : myButtonArr) {
                PartyInfo partyInfo2 = TabParty.partyInfo;
                if (partyInfo2 != null && (partyInfo2.vChars.size() > 1 || index != 2)) {
                    if (index != 0 || TabParty.isLeader()) {
                        if (index != 2 || TabParty.isLeader()) {
                            btn.paint(mgraphics);
                        }
                    } else if (this.indexSelectedBox != -1) {
                        fontPaintName.drawString(mgraphics, SupportTranslate.getArrayLangue("PARTY_OPTION_GET_ITEM")[this.indexSelectedBox] + " " + SupportTranslate.getTextLangue("getItem").toLowerCase(), btn.xBtn, btn.yBtn + (btn.h / 4));
                    }
                }
                index++;
            }
        }
        if (TabParty.isLeader() && (myRadio = this.radio) != null) {
            myRadio.paint(mgraphics);
            mFont mfont = fontPaintRadio;
            String str = this.nameRadio;
            MyRadio myRadio2 = this.radio;
            mfont.drawString(g, str, myRadio2.x + ((int) myRadio2.frameImg.frameWidth) + 5, myRadio2.y, 0);
        }
        CanvasNinja.resetTrans(g);
        this.cmdClose.paintIconOnly(mgraphics);
        SelectBox selectBox2 = this.selectBox;
        if (selectBox2 != null) {
            selectBox2.paint(mgraphics);
        }
    }

    public void init() {
        this.wSys = LoadDataManager.imgHeIcon[0].getRWidth();
        this.margin = 5;
        this.marginLeftRight = 5 * 2;
        this.cmdInvite = new MyCommand("", 99, this);
        this.cmdKick = new MyCommand("", 98, this);
        this.title = SupportTranslate.getTextLangue("group").toUpperCase();
        this.marginOneTab = this.margin;
        this.transOneTab = LoadDataManager.imgNenCharNone.getRWidth() + this.marginOneTab;
        int wNenCam = (LoadDataManager.imgNenCharNone.getRWidth() * 5) + (this.marginOneTab * 6);
        int hNenCam = LoadDataManager.imgNenCharNone.getRHeight() + (this.margin * 4);
        this.minWBtn0 = MyButton.FONT_DEFAULT.getWidth(Res.findStringMax(SupportTranslate.getArrayLangue("PARTY_OPTION_GET_ITEM"))) + 15;
        MyButton[] myButtonArr = new MyButton[3];
        this.btns = myButtonArr;
        FrameImage[] frameImageArr = LoadDataManager.myButtons;
        myButtonArr[0] = new MyButton(frameImageArr[1], frameImageArr[3], this.minWBtn0, 0, SupportTranslate.getTextLangue("getItem"), 0, 0, new MyCommand("", 1, this));
        MyButton[] myButtonArr2 = this.btns;
        FrameImage[] frameImageArr2 = LoadDataManager.myButtons;
        myButtonArr2[1] = new MyButton(frameImageArr2[0], frameImageArr2[3], 55, 0, TabParty.isLeader() ? SupportTranslate.getTextLangue("outAll") : SupportTranslate.getTextLangue("outParty"), 0, 0, new MyCommand("", 2, this));
        MyButton[] myButtonArr3 = this.btns;
        FrameImage[] frameImageArr3 = LoadDataManager.myButtons;
        myButtonArr3[2] = new MyButton(frameImageArr3[1], frameImageArr3[3], 55, 0, SupportTranslate.getTextLangue("callBack"), 0, 0, new MyCommand("", 3, this));
        int i = (this.marginLeftRight * 2) + wNenCam + 14;
        this.w = i;
        int i2 = CanvasNinja.w;
        if (i > i2) {
            this.w = i2 - (this.margin * 5);
        }
        int i3 = (this.margin7 * 2) + hNenCam + (((int) LoadDataManager.frameTitle2.frameHeight) / 2);
        int i4 = this.margin;
        int i5 = i3 + (i4 * 6) + this.btns[0].h;
        this.h = i5;
        if (i5 < 70) {
            this.h = 70;
        }
        int i6 = this.h;
        int i7 = CanvasNinja.h;
        if (i6 > i7) {
            this.h = i7 - (i4 * 2);
        }
        initStart();
        this.hTitle = (int) LoadDataManager.frameTitle.frameHeight;
        int width = TabScr.fontPaintTile.getWidth(this.title) + (this.margin * 6);
        this.wTitle = width;
        int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(8, width, LoadDataManager.frameTitle);
        this.wTitle = fixSizeTagFrameUp;
        this.xTitle = this.x + ((this.w - fixSizeTagFrameUp) / 2);
        this.yTitle = this.y - (this.hTitle / 2);
        this.arrH = new int[]{hNenCam, LoadDataManager.imgNenCharNone.getRHeight(), LoadDataManager.imgFlagLeader.getRHeight(), 80, 0, 0};
        this.arrW = new int[]{wNenCam, LoadDataManager.imgNenCharNone.getRWidth(), LoadDataManager.imgFlagLeader.getRWidth(), 32, 0, 0};
        int xStartLeft = this.x + 7 + this.marginLeftRight;
        int i8 = this.marginOneTab;
        int[] iArr = {xStartLeft, xStartLeft + i8, xStartLeft + i8 + 1, i8 + xStartLeft + (LoadDataManager.imgNenChar.getRWidth() / 2), this.marginOneTab + xStartLeft + (LoadDataManager.imgNenChar.getRWidth() / 2), this.marginOneTab + xStartLeft + (LoadDataManager.imgNenChar.getRWidth() / 2)};
        this.arrX = iArr;
        int i9 = this.y + 7 + (((int) LoadDataManager.frameTitle2.frameHeight) / 2);
        int i10 = this.margin;
        int yStart = i9 + (i10 * 2);
        this.arrY = new int[]{yStart, yStart + (i10 * 2), yStart + (i10 * 2) + 1, yStart + (i10 * 2) + i10 + 80, (i10 * 2) + yStart + 105, (i10 * 2) + yStart + 122};
        this.btns[0].setXY(iArr[0], this.arrH[0] + yStart + (i10 * 2));
        MyButton myButton = this.btns[1];
        myButton.setXY((this.arrX[0] + this.arrW[0]) - myButton.w, this.arrY[0] + this.arrH[0] + (this.margin * 2));
        MyButton[] myButtonArr4 = this.btns;
        MyButton myButton2 = myButtonArr4[2];
        int i11 = ((this.arrX[0] + this.arrW[0]) - myButtonArr4[1].w) - myButton2.w;
        int i12 = this.margin;
        myButton2.setXY(i11 - i12, this.arrY[0] + this.arrH[0] + (i12 * 2));
        setMode(this.indexSelectedBox);
        String textLangue = SupportTranslate.getTextLangue("autoAccept");
        this.nameRadio = textLangue;
        int wRadio = fontPaintRadio.getWidth(textLangue);
        int hRadio = fontPaintRadio.getHeight();
        MyButton myButton3 = this.btns[0];
        this.radio = new MyRadio(myButton3.xBtn + myButton3.w + this.margin, ((myButton3.yBtn + myButton3.h) - hRadio) - 7, wRadio, hRadio, true);
        MyButton[] myButtonArr5 = new MyButton[2];
        this.btnOutAlls = myButtonArr5;
        FrameImage[] frameImageArr4 = LoadDataManager.myButtons;
        myButtonArr5[0] = new MyButton(frameImageArr4[1], frameImageArr4[3], 55, 0, SupportTranslate.getTextLangue("outAll"), 0, 0, new MyCommand("", -99, this));
        MyButton[] myButtonArr6 = this.btnOutAlls;
        FrameImage[] frameImageArr5 = LoadDataManager.myButtons;
        myButtonArr6[1] = new MyButton(frameImageArr5[1], frameImageArr5[3], this.btnOutAlls[0].w, 0, SupportTranslate.getTextLangue("huy"), 0, 0, new MyCommand("", -1, this));
        PartyInfo partyInfo = TabParty.partyInfo;
        if (partyInfo != null) {
            this.yAnimClose = new int[partyInfo.vChars.size()];
        }
        this.selectBox = null;
    }

    public void setMode(int mode) {
        int i = mode;
        this.indexSelectedBox = i;
        if (i != -1) {
            MyButton[] myButtonArr = this.btns;
            FrameImage[] frameImageArr = LoadDataManager.myButtons;
            myButtonArr[0] = new MyButton(frameImageArr[1], frameImageArr[3], this.minWBtn0, 0, SupportTranslate.getArrayLangue("PARTY_OPTION_GET_ITEM")[this.indexSelectedBox], this.arrX[0], this.arrY[0] + this.arrH[0] + (this.margin * 2), new MyCommand("", 1, this));
            return;
        }
        MyButton[] myButtonArr2 = this.btns;
        FrameImage[] frameImageArr2 = LoadDataManager.myButtons;
        myButtonArr2[0] = new MyButton(frameImageArr2[1], frameImageArr2[3], this.minWBtn0, 0, SupportTranslate.getTextLangue("getItem"), this.arrX[0], this.arrY[0] + this.arrH[0] + (this.margin * 2), new MyCommand("", 1, this));
    }

    public void update() {
        int i;
        Player cTemp;
        int i2 = 0;
        while (true) {
            int[] iArr = this.yAnimClose;
            if (i2 >= iArr.length) {
                break;
            }
            iArr[i2] = 0;
            i2++;
        }
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            int index = 0;
            for (MyButton btn : myButtonArr) {
                PartyInfo partyInfo = TabParty.partyInfo;
                if (partyInfo != null && ((partyInfo.vChars.size() > 1 || index != 2) && ((index != 0 || TabParty.isLeader()) && (index != 2 || TabParty.isLeader())))) {
                    btn.update();
                }
                index++;
            }
        }
        MyRadio myRadio = this.radio;
        if (myRadio != null) {
            myRadio.update();
        }
        for (int i3 = 0; i3 < 5; i3++) {
            PartyInfo partyInfo2 = TabParty.partyInfo;
            if (!(partyInfo2 == null || i3 >= partyInfo2.vChars.size() || (cTemp = TabParty.partyInfo.vChars.get(i3)) == null)) {
                cTemp.update();
            }
        }
    }

    public void updateKey() {
        SelectBox selectBox2;
        this.cmdClose.updateIconOnly();
        if (CanvasNinja.isPointerRelease() && (selectBox2 = this.selectBox) != null) {
            int updatePointer = selectBox2.updatePointer();
            this.indexSelectedBox = updatePointer;
            if (updatePointer != -1) {
                SendMessage.gI().setModeParty(this.indexSelectedBox);
                MyButton[] myButtonArr = this.btns;
                FrameImage[] frameImageArr = LoadDataManager.myButtons;
                myButtonArr[0] = new MyButton(frameImageArr[1], frameImageArr[3], this.minWBtn0, 0, this.selectBox.options[this.indexSelectedBox], this.arrX[0], (this.margin * 2) + this.arrY[0] + this.arrH[0], new MyCommand("", 1, this));
                this.selectBox = null;
            }
        }
        MyButton[] myButtonArr2 = this.btns;
        if (myButtonArr2 != null) {
            int index = 0;
            for (MyButton btn : myButtonArr2) {
                PartyInfo partyInfo = TabParty.partyInfo;
                if (partyInfo != null && ((partyInfo.vChars.size() > 1 || index != 2) && ((index != 0 || TabParty.isLeader()) && (index != 2 || TabParty.isLeader())))) {
                    btn.updatePointer();
                }
                index++;
            }
        }
        MyRadio myRadio = this.radio;
        if (myRadio != null) {
            myRadio.updatePointer();
        }
        if (CanvasNinja.isPointerRelease()) {
            for (int i = 0; i < 5; i++) {
                PartyInfo partyInfo2 = TabParty.partyInfo;
                if (partyInfo2 == null || i >= partyInfo2.vChars.size()) {
                    if (CanvasNinja.isPoint(this.arrX[1] + (this.transOneTab * i), this.arrY[1], this.arrW[1], this.arrH[1])) {
                        CanvasNinja.clearAllPointer();
                        this.cmdInvite.perform();
                        return;
                    }
                } else if (i != 0) {
                    int k = 0;
                    while (true) {
                        if (k >= this.yAnimClose.length) {
                            break;
                        } else if (CanvasNinja.isPoint((((this.arrX[1] + this.arrW[1]) - LoadDataManager.imgCloseParty.getRWidth()) - 3) + (this.transOneTab * i), this.arrY[1] + 3 + this.yAnimClose[k], LoadDataManager.imgCloseParty.getRWidth(), LoadDataManager.imgCloseParty.getRHeight())) {
                            CanvasNinja.clearAllPointer();
                            MyCommand myCommand = this.cmdKick;
                            myCommand.subAction = i;
                            myCommand.perform();
                            this.yAnimClose[k] = 2;
                            break;
                        } else {
                            k++;
                        }
                    }
                }
            }
        }
    }

    public void show() {
        init();
        showTab();
    }

    public void keyPress(int keyCode) {
    }

    public void keyTyped() {
    }

    public void commandTab(int index, int subIndex) {
        int i = index;
        int i2 = subIndex;
        Res.outz("Vào commandTab TabCreateParty:" + i);
        super.commandTab(index, subIndex);
        switch (i) {
            case 1:
                MyButton myButton = this.btns[0];
                this.selectBox = new SelectBox(myButton.xBtn + (myButton.w / 2), myButton.yBtn, SupportTranslate.getArrayLangue("PARTY_OPTION_GET_ITEM"), this.indexSelectedBox);
                return;
            case 2:
                if (TabParty.isLeader()) {
                    MyButton[] myButtonArr = new MyButton[2];
                    this.btnOutAlls = myButtonArr;
                    FrameImage[] frameImageArr = LoadDataManager.myButtons;
                    myButtonArr[0] = new MyButton(frameImageArr[1], frameImageArr[3], 55, 0, SupportTranslate.getTextLangue("outAll"), 0, 0, new MyCommand("", -99, this));
                    MyButton[] myButtonArr2 = this.btnOutAlls;
                    FrameImage[] frameImageArr2 = LoadDataManager.myButtons;
                    myButtonArr2[1] = new MyButton(frameImageArr2[1], frameImageArr2[3], this.btnOutAlls[0].w, 0, SupportTranslate.getTextLangue("huy"), 0, 0, new MyCommand("", -1, this));
                    MessageDialog.gI().startMsgDialog(SupportTranslate.getTextLangue("confirmAllOutParty"), this.btnOutAlls);
                    return;
                }
                MyButton[] myButtonArr3 = new MyButton[2];
                this.btnOutAlls = myButtonArr3;
                FrameImage[] frameImageArr3 = LoadDataManager.myButtons;
                myButtonArr3[0] = new MyButton(frameImageArr3[1], frameImageArr3[3], 55, 0, SupportTranslate.getTextLangue("outParty"), 0, 0, new MyCommand("", -99, this));
                MyButton[] myButtonArr4 = this.btnOutAlls;
                FrameImage[] frameImageArr4 = LoadDataManager.myButtons;
                myButtonArr4[1] = new MyButton(frameImageArr4[1], frameImageArr4[3], this.btnOutAlls[0].w, 0, SupportTranslate.getTextLangue("huy"), 0, 0, new MyCommand("", -1, this));
                MessageDialog.gI().startMsgDialog(SupportTranslate.getTextLangue("confirmOutParty"), this.btnOutAlls);
                return;
            case 3:
                if (TabParty.isLeader()) {
                    MyCommand cmdMoneyGem = new MyCommand("", -98, MessageDialog.gI());
                    cmdMoneyGem.subAction = 3;
                    FrameImage[] frameImageArr5 = LoadDataManager.myButtons;
                    MyCommand cmdMoneyGemLock = new MyCommand("", -97, MessageDialog.gI());
                    cmdMoneyGemLock.subAction = 2;
                    FrameImage[] frameImageArr6 = LoadDataManager.myButtons;
                    FrameImage[] frameImageArr7 = LoadDataManager.myButtons;
                    FrameImage frameImage = frameImageArr7[2];
                    FrameImage frameImage2 = frameImageArr7[3];
                    int i3 = this.btnOutAlls[0].w;
                    int i4 = i3;
                    MessageDialog.gI().startMsgDialog(SupportTranslate.getTextLangue("pickYourMoneyCallBackParty"), new MyButton[]{new MyButton(frameImageArr5[1], frameImageArr5[3], 55, 0, SupportTranslate.getTextLangue("gem"), 0, 0, cmdMoneyGem, true), new MyButton(frameImageArr6[1], frameImageArr6[3], this.btnOutAlls[0].w, 0, SupportTranslate.getTextLangue("gemLock"), 0, 0, cmdMoneyGemLock, true), new MyButton(frameImage, frameImage2, i4, 0, SupportTranslate.getTextLangue("CLOSE"), 0, 0, new MyCommand("", -1, MessageDialog.gI()))});
                    return;
                }
                return;
            case 98:
                Res.outz("Kick mem ở index:" + i2);
                if (i2 < TabParty.partyInfo.vChars.size()) {
                    SendMessage.gI().outGroupParty(TabParty.partyInfo.vChars.get(i2).charID);
                    return;
                }
                return;
            case 99:
                if (TabParty.isLeader()) {
                    SendMessage.gI().updateListInviteParty();
                    SubTabInviteParty.gI().show();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
