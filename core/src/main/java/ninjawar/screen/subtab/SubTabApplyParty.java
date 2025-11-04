package ninjawar.screen.subtab;

import ninjawar.input.MyButton;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.screen.tab.TabCreateParty;
import ninjawar.screen.tab.TabParty;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportTranslate;

public class SubTabApplyParty extends SubTabScr {
    public static mFont fontPaintName = mFont.fontPaintNameParty;
    public static mFont fontPaintNum = mFont.fontPaintNumParty;
    public static mFont fontPaintNumFull = mFont.fontPaintNumPartyFull;
    public static mFont fontPaintNumPage = mFont.tahoma_7b_white;
    public static SubTabApplyParty me;
    public MyButton[] btnApplys;
    int hOneTag;
    int hSys;
    int hTitle;
    int indexMinus;
    int indexPlus;
    public int indexSelected = -1;
    int marginLeftRight;
    int marginOneTab;
    int maxPage = 1;
    public int numPage = 0;
    String title = "";
    int transOneTab;
    int wOneTag;
    int wSys;
    int wTitle;
    int xOneTag;
    int xTitle;
    int yAnimMinus;
    int yAnimPlus;
    int yOneTag;
    int yTitle;

    public static SubTabApplyParty gI() {
        if (me == null) {
            me = new SubTabApplyParty();
        }
        return me;
    }

    public void paint(mGraphics g) {
        int i;
        MyButton myButton;
        mGraphics mgraphics = g;
        mGraphics mgraphics2 = g;
        CanvasNinja.paintz.paintSingleBorderFrame(mgraphics2, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        CanvasNinja.paintz.paintTagFrame(mgraphics2, LoadDataManager.frameTitle2, this.xTitle, this.yTitle, this.wTitle, false, 0, false);
        SubTabScr.fontPaintTile.drawString(g, this.title, (this.wTitle / 2) + this.xTitle, (this.hTitle / 2) + this.yTitle, 3);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgCam, (float) this.arrX[0], (float) this.arrY[0], (float) this.arrW[0], (float) this.arrH[0], 10, false);
        SubTabScr.fontPaintTile.drawString(g, this.title, (this.wTitle / 2) + this.xTitle, (this.hTitle / 2) + this.yTitle, 3);
        int i2 = this.numPage;
        if (i2 > -1 && this.maxPage > -1) {
            int index = 0;
            int i3 = i2 * 5;
            while (i3 < (this.numPage * 5) + 5) {
                if (i3 > TabParty.vecPartySearchs.size() - 1) {
                    i = i3;
                } else {
                    i = i3;
                    CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameBgTag, this.xOneTag, ((this.hOneTag + this.margin) * index) + this.yOneTag, this.wOneTag, false, 0, false);
                    mgraphics.drawImage(LoadDataManager.imgHeIcon[TabParty.vecPartySearchs.get(i).sys], (float) this.arrX[1], (float) ((this.arrY[1] - (this.hSys / 2)) + ((this.hOneTag + this.margin) * index)));
                    fontPaintName.drawString(mgraphics, TabParty.vecPartySearchs.get(i).name, this.arrX[1] + this.wSys + 3, (this.arrY[1] - fontPaintName.getHeight()) + 5 + ((this.hOneTag + this.margin) * index));
                    fontPaintName.drawString(g, TabParty.vecPartySearchs.get(i).level, this.arrX[2], ((this.hOneTag + this.margin) * index) + this.arrY[2], 3);
                    if (TabParty.vecPartySearchs.get(i).numMem < 5) {
                        fontPaintNum.drawString(g, TabParty.vecPartySearchs.get(i).numMem(), this.arrX[3], ((this.hOneTag + this.margin) * index) + this.arrY[3], 3);
                    } else {
                        MyButton[] myButtonArr = this.btnApplys;
                        if (i < myButtonArr.length) {
                            myButtonArr[i].isFull = true;
                        }
                        fontPaintNumFull.drawString(g, TabParty.vecPartySearchs.get(i).numMem(), this.arrX[3], ((this.hOneTag + this.margin) * index) + this.arrY[3], 3);
                    }
                    index++;
                    MyButton[] myButtonArr2 = this.btnApplys;
                    if (!(myButtonArr2 == null || (myButton = myButtonArr2[i]) == null)) {
                        myButton.paint(mgraphics);
                    }
                }
                i3 = i + 1;
            }
            int i4 = i3;
        }
        paintButton(g);
        LoadDataManager.frameNextPreviousKhoDo[1].drawFrame(this.indexMinus, (float) this.arrX[5], (float) (this.arrY[5] + this.yAnimMinus), mgraphics);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.framePage, this.arrX[6], this.arrY[6], this.arrW[6], false, 0, false);
        fontPaintNumPage.drawString(g, (this.numPage + 1) + "/" + (this.maxPage + 1), (this.arrW[6] / 2) + this.arrX[6], this.arrY[6] + 5, 2);
        LoadDataManager.frameNextPreviousKhoDo[0].drawFrame(this.indexPlus, (float) this.arrX[7], (float) (this.arrY[7] + this.yAnimPlus), mgraphics);
        CanvasNinja.resetTrans(g);
        this.cmdClose.paintIconOnly(mgraphics);
    }

    public void init() {
        TabParty.vecPartySearchs.removeAllElements();
        TabParty.vecPartySearchs.addAll(TabParty.vecPartySearchsTemp);
        this.numPage = 0;
        if (TabParty.vecPartySearchs.size() > 0) {
            this.maxPage = TabParty.vecPartySearchs.size() % 5 == 0 ? (TabParty.vecPartySearchs.size() / 5) - 1 : TabParty.vecPartySearchs.size() / 5;
        } else {
            this.numPage = -1;
            this.maxPage = -1;
        }
        MyButton[] myButtonArr = new MyButton[2];
        this.btns = myButtonArr;
        FrameImage[] frameImageArr = LoadDataManager.myButtons;
        String str = "";
        myButtonArr[0] = new MyButton(frameImageArr[1], frameImageArr[3], 55, 0, SupportTranslate.getTextLangue("createGroup"), 0, 0, new MyCommand("", 1, this));
        MyButton[] myButtonArr2 = this.btns;
        FrameImage[] frameImageArr2 = LoadDataManager.myButtons;
        myButtonArr2[1] = new MyButton(frameImageArr2[0], frameImageArr2[3], 55, 0, SupportTranslate.getTextLangue("applyAll"), 0, 0, new MyCommand(str, 2, this));
        this.hOneTag = (int) LoadDataManager.frameBgTag.frameHeight;
        this.w = 450;
        int i = CanvasNinja.w;
        if (450 > i) {
            this.w = i - (this.margin * 5);
        }
        this.wSys = LoadDataManager.imgHeIcon[0].getRWidth();
        this.hSys = LoadDataManager.imgHeIcon[0].getRHeight();
        this.margin = 5;
        this.marginLeftRight = 5 * 2;
        this.title = SupportTranslate.getTextLangue("group").toUpperCase();
        int i2 = this.margin;
        this.marginOneTab = i2;
        int i3 = this.hOneTag;
        this.transOneTab = i3 + i2;
        int wNenCam = (this.w - 14) - (i2 * 4);
        int hNenCam = (i3 * 5) + (i2 * 6);
        int i4 = hNenCam + 14 + (((int) LoadDataManager.frameTitle2.frameHeight) / 2) + (i2 * 6) + this.btns[0].h;
        this.h = i4;
        int i5 = CanvasNinja.h;
        if (i4 > i5) {
            this.h = i5 - (i2 * 2);
        }
        initStart();
        this.hTitle = (int) LoadDataManager.frameTitle.frameHeight;
        int width = SubTabScr.fontPaintTile.getWidth(this.title) + (this.margin * 8);
        this.wTitle = width;
        int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(8, width, LoadDataManager.frameTitle);
        this.wTitle = fixSizeTagFrameUp;
        this.xTitle = this.x + ((this.w - fixSizeTagFrameUp) / 2);
        this.yTitle = this.y - (this.hTitle / 2);
        int[] iArr = {hNenCam, fontPaintName.getHeight(), fontPaintName.getHeight(), fontPaintName.getHeight(), 0, (int) LoadDataManager.frameMinus.frameHeight, (int) LoadDataManager.framePage.frameHeight, (int) LoadDataManager.framePlus.frameHeight};
        this.arrH = iArr;
        int i6 = this.margin;
        int i7 = wNenCam - (i6 * 2);
        this.wOneTag = i7;
        int w1 = (i7 * 4) / 12;
        int w2 = (i7 * 2) / 12;
        int w3 = (i7 * 2) / 12;
        int w4 = (i7 * 4) / 12;
        float f = LoadDataManager.frameMinus.frameWidth;
        int i8 = (int) f;
        int i9 = (int) (f * 1.5f);
        int i10 = (int) LoadDataManager.framePlus.frameWidth;
        int[] iArr2 = {wNenCam, w1, w2, w3, w4, i8, i9, i10};
        this.arrW = iArr2;
        int sumWPage = i8 + (i6 * 2) + i9 + i10;
        int i11 = this.x;
        int xStartLeft = i11 + 7 + this.marginLeftRight;
        int i12 = xStartLeft + i6;
        this.xOneTag = i12;
        int i13 = hNenCam;
        int i14 = this.w;
        int i15 = iArr2[5];
        int[] iArr3 = {xStartLeft, i12 + (i6 * 5), i12 + w1 + (w2 / 2), i12 + w1 + w2 + (w3 / 2), i12 + w1 + w2 + w3 + (w4 / 2), (i11 + (i14 / 2)) - (sumWPage / 2), ((i11 + (i14 / 2)) - (sumWPage / 2)) + i6 + i15, ((i11 + (i14 / 2)) - (sumWPage / 2)) + (i6 * 2) + i15 + iArr2[6]};
        this.arrX = iArr3;
        int yStart = this.y + 7 + (((int) LoadDataManager.frameTitle2.frameHeight) / 2) + (i6 * 2);
        int i16 = yStart + i6;
        this.yOneTag = i16;
        int i17 = this.hOneTag;
        int i18 = iArr[0];
        int i19 = sumWPage;
        MyButton myButton = this.btns[0];
        int i20 = myButton.h;
        this.arrY = new int[]{yStart, i16 + (i17 / 2), i16 + (i17 / 2), i16 + (i17 / 2), i16 + (i17 / 2), yStart + i18 + (i6 * 2) + ((i20 - iArr[5]) / 2), yStart + i18 + (i6 * 2) + ((i20 - iArr[6]) / 2), yStart + i18 + (i6 * 2) + ((i20 - iArr[7]) / 2)};
        myButton.setXY(iArr3[0], i18 + yStart + (i6 * 2));
        MyButton myButton2 = this.btns[1];
        myButton2.setXY((this.arrX[0] + this.arrW[0]) - myButton2.w, this.arrY[0] + this.arrH[0] + (this.margin * 2));
        this.btnApplys = new MyButton[TabParty.vecPartySearchs.size()];
        int index = 0;
        for (int i21 = 0; i21 < this.btnApplys.length; i21++) {
            MyCommand cmdApply = new MyCommand(str, 99, this);
            cmdApply.subAction = i21;
            MyButton[] myButtonArr3 = this.btnApplys;
            Image image = LoadDataManager.imgGrFull;
            Image image2 = LoadDataManager.imgApplied;
            FrameImage[] frameImageArr3 = LoadDataManager.myButtons;
            myButtonArr3[i21] = new MyButton(image, image2, frameImageArr3[1], frameImageArr3[3], 55, 0, SupportTranslate.getTextLangue("apply"), this.arrX[4], 0, cmdApply);
            MyButton myButton3 = this.btnApplys[i21];
            myButton3.setXY(this.arrX[4] - (myButton3.w / 2), (this.arrY[4] + ((this.hOneTag + this.margin) * index)) - (myButton3.h / 2));
            index++;
            if (index > 4) {
                index = 0;
            }
        }
    }

    public void update() {
        MyButton myButton;
        updateButton();
        this.indexMinus = 0;
        this.yAnimMinus = 0;
        this.yAnimPlus = 0;
        this.indexPlus = 0;
        int i = this.numPage;
        if (i > -1 && this.maxPage > -1) {
            for (int i2 = i * 5; i2 < (this.numPage * 5) + 5; i2++) {
                MyButton[] myButtonArr = this.btnApplys;
                if (!(i2 > myButtonArr.length - 1 || myButtonArr == null || (myButton = myButtonArr[i2]) == null)) {
                    myButton.update();
                }
            }
        }
    }

    public void updateKey() {
        MyButton myButton;
        int i;
        int i2;
        this.cmdClose.updateIconOnly();
        updatePointerButton();
        if (CanvasNinja.isPointerRelease()) {
            if (CanvasNinja.isPoint(this.arrX[5], this.arrY[5], this.arrW[5], this.arrH[5])) {
                this.indexMinus = 1;
                this.yAnimMinus = 2;
                CanvasNinja.clearAllPointer();
                int i3 = this.numPage;
                if (i3 > -1 && (i2 = this.maxPage) > -1) {
                    int i4 = i3 - 1;
                    this.numPage = i4;
                    if (i4 < 0) {
                        this.numPage = i2;
                    }
                }
            }
            if (CanvasNinja.isPoint(this.arrX[7], this.arrY[7], this.arrW[7], this.arrH[7])) {
                this.indexPlus = 1;
                this.yAnimPlus = 2;
                CanvasNinja.clearAllPointer();
                int i5 = this.numPage;
                if (i5 > -1 && (i = this.maxPage) > -1) {
                    int i6 = i5 + 1;
                    this.numPage = i6;
                    if (i6 > i) {
                        this.numPage = 0;
                    }
                }
            }
        }
        int i7 = this.numPage;
        if (i7 > -1 && this.maxPage > -1) {
            for (int i8 = i7 * 5; i8 < (this.numPage * 5) + 5; i8++) {
                MyButton[] myButtonArr = this.btnApplys;
                if (!(i8 > myButtonArr.length - 1 || myButtonArr == null || (myButton = myButtonArr[i8]) == null)) {
                    myButton.updatePointer();
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
        Res.outz("Vào commandTab TabParrty:" + index + "_sub:" + subIndex);
        super.commandTab(index, subIndex);
        switch (index) {
            case 1:
                SendMessage.gI().createParty();
                close();
                TabCreateParty.gI().show();
                return;
            case 2:
                for (int i = this.numPage * 5; i < (this.numPage * 5) + 5; i++) {
                    if (i < this.btnApplys.length) {
                        invite(i);
                    }
                }
                return;
            case 99:
                invite(subIndex);
                return;
            default:
                return;
        }
    }

    private void invite(int subIndex) {
        if (subIndex >= 0 && subIndex < TabParty.vecPartySearchs.size()) {
            this.btnApplys[subIndex].isDone = true;
            SendMessage.gI().invitePlayerToParty(TabParty.vecPartySearchs.get(subIndex).playerId);
        }
    }
}
