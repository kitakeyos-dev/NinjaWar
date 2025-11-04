package ninjawar.screen.tab;

import java.util.Vector;
import ninjawar.input.KEY;
import ninjawar.input.MyButton;
import ninjawar.input.TField;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.mylib.AudioManager;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.ChatBoxPM;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

public class TabFriend extends TabScr {
    public static mFont fontPaintName = mFont.fontPaintNameParty;
    public static mFont fontPaintNum = mFont.fontPaintNumParty;
    public static mFont fontPaintNumPage = mFont.tahoma_7b_white;
    public static TabFriend me;
    public static Vector<Player> vecFriends = new Vector<>();
    public static Vector<Player> vecPaints = new Vector<>();
    public static Vector<Player> vecRequests = new Vector<>();
    public MyCommand[] btnAccepts;
    public MyCommand btnAdd;
    public MyButton[] btnApplys;
    public MyCommand[] btnDeclines;
    public MyCommand[] btnMessages;
    public MyCommand btnMsg;
    public MyCommand[] btnRemoves;
    MyCommand cmdSearch;
    int hOneTag;
    int hSys;
    int hTitle;
    int indexMinus;
    int indexPlus;
    public int indexSelected = -1;
    int indexTabIconSelected;
    boolean[] isClickTagIcon = new boolean[3];
    int marginLeftRight;
    int marginOneTab;
    int maxPage = 1;
    public int numPage = 0;
    int numTag = 5;
    int numTagIcon = 3;
    TField tfSearch;
    String title = "";
    int transOneTab;
    int wOneTag;
    int wSys;
    int wTitle;
    int xOneTag;
    int xTitle;
    int yAnimMinus;
    int yAnimPlus;
    int yAnimSearch;
    int[] yAnimTagIcon = new int[3];
    int yOneTag;
    int yTitle;

    public static TabFriend gI() {
        if (me == null) {
            me = new TabFriend();
        }
        return me;
    }

    public void paint(mGraphics g) {
        int i;
        MyCommand myCommand;
        MyCommand myCommand2;
        MyCommand myCommand3;
        MyCommand myCommand4;
        mGraphics mgraphics = g;
        mGraphics mgraphics2 = g;
        CanvasNinja.paintz.paintSingleBorderFrame(mgraphics2, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        CanvasNinja.paintz.paintTagFrame(mgraphics2, LoadDataManager.frameTitle2, this.xTitle, this.yTitle, this.wTitle, false, 0, false);
        TabScr.fontPaintTile.drawString(g, this.title, (this.wTitle / 2) + this.xTitle, (this.hTitle / 2) + this.yTitle, 3);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgCam, (float) this.arrX[0], (float) this.arrY[0], (float) this.arrW[0], (float) this.arrH[0], 10, false);
        TabScr.fontPaintTile.drawString(g, this.title, (this.wTitle / 2) + this.xTitle, (this.hTitle / 2) + this.yTitle, 3);
        if (vecPaints.size() != 0 && vecPaints != null) {
            int index = 0;
            int i2 = this.numPage * this.numTag;
            while (true) {
                int index2 = this.numPage;
                int i3 = this.numTag;
                if (i2 >= (index2 * i3) + i3) {
                    break;
                }
                if (i2 > vecPaints.size() - 1) {
                    i = i2;
                } else {
                    i = i2;
                    CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameBgTag, this.xOneTag, ((this.hOneTag + this.margin3) * index) + this.yOneTag, this.wOneTag, false, 0, false);
                    int xStart1 = this.arrX[1] + (this.margin * 2);
                    mgraphics.drawImage(LoadDataManager.imgHeIcon[vecPaints.get(i).classId], (float) xStart1, (float) ((this.arrY[1] - (this.hSys / 2)) + ((this.hOneTag + this.margin3) * index)));
                    if (!vecPaints.get(i).isOffline) {
                        fontPaintName.drawString(mgraphics, vecPaints.get(i).cName, this.wSys + xStart1 + (this.margin * 2), (this.arrY[1] - fontPaintName.getHeight()) + 6 + ((this.hOneTag + this.margin3) * index));
                    } else {
                        fontPaintName.drawString(mgraphics, vecPaints.get(i).cName + " (offline)", this.wSys + xStart1 + (this.margin * 2), (this.arrY[1] - fontPaintName.getHeight()) + 6 + ((this.hOneTag + this.margin3) * index));
                    }
                    fontPaintName.drawString(g, "Lv." + vecPaints.get(i).clevel, this.arrX[2], ((this.hOneTag + this.margin3) * index) + this.arrY[2], 3);
                    index++;
                    if (!vecFriends.contains(vecPaints.get(0)) && !vecRequests.contains(vecPaints.get(0))) {
                        MyCommand myCommand5 = this.btnMsg;
                        myCommand5.img = LoadDataManager.imgMessage;
                        myCommand5.idAction = 102;
                        myCommand5.subAction = 0;
                        MyCommand myCommand6 = this.btnAdd;
                        myCommand6.img = LoadDataManager.imgKB;
                        myCommand6.idAction = 104;
                        myCommand6.subAction = 0;
                        myCommand5.paintIconOnly(mgraphics);
                        this.btnAdd.paintIconOnly(mgraphics);
                    } else if (vecFriends.contains(vecPaints.get(0))) {
                        MyCommand[] myCommandArr = this.btnRemoves;
                        if (!(myCommandArr == null || (myCommand4 = myCommandArr[i]) == null)) {
                            myCommand4.img = LoadDataManager.imgRemove;
                            myCommand4.idAction = 101;
                            myCommand4.paintIconOnly(mgraphics);
                        }
                        MyCommand[] myCommandArr2 = this.btnMessages;
                        if (!(myCommandArr2 == null || (myCommand3 = myCommandArr2[i]) == null)) {
                            myCommand3.img = LoadDataManager.imgMessage;
                            myCommand3.idAction = 102;
                            myCommand3.paintIconOnly(mgraphics);
                        }
                    } else if (vecRequests.contains(vecPaints.get(0))) {
                        MyCommand[] myCommandArr3 = this.btnAccepts;
                        if (!(myCommandArr3 == null || (myCommand2 = myCommandArr3[i]) == null)) {
                            myCommand2.img = LoadDataManager.imgAccept;
                            myCommand2.idAction = 104;
                            myCommand2.paintIconOnly(mgraphics);
                        }
                        MyCommand[] myCommandArr4 = this.btnDeclines;
                        if (!(myCommandArr4 == null || (myCommand = myCommandArr4[i]) == null)) {
                            myCommand.img = LoadDataManager.imgDecline;
                            myCommand.idAction = 103;
                            myCommand.paintIconOnly(mgraphics);
                        }
                    }
                }
                i2 = i + 1;
            }
            int i4 = i2;
        } else if (this.indexTabIconSelected == 0) {
            mFont.tahoma_7_brown.drawString(g, SupportTranslate.getArrayLangue("tabFriendDefault")[0], (this.arrW[0] / 2) + this.arrX[0], (this.arrH[0] / 2) + this.arrY[0], 2);
        } else {
            mFont.tahoma_7_brown.drawString(g, SupportTranslate.getArrayLangue("tabFriendDefault")[1], (this.arrW[0] / 2) + this.arrX[0], (this.arrH[0] / 2) + this.arrY[0], 2);
        }
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.framePartySearch, this.arrX[8], this.arrY[8], this.arrW[8], this.tfSearch.isFocused(), SupportPaint.TYPE_NONE, true);
        this.tfSearch.painTextInputNoneBackGround(mgraphics);
        if (!this.tfSearch.isFocused() && this.tfSearch.getText().equals("")) {
            mFont.tahoma_place_holder.drawString(g, SupportTranslate.getTextLangue("searchParty"), this.arrX[8] + 7, (this.arrH[8] / 2) + this.arrY[8], 4);
        }
        mgraphics.drawImage(LoadDataManager.imgIconSearch, (float) this.arrX[9], (float) (this.arrY[9] + this.yAnimSearch));
        int i5 = 0;
        while (i5 < LoadDataManager.frameIconTabFriend.length) {
            CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameTabIconHanhTrang, this.arrX[10], this.yAnimTagIcon[i5] + this.arrY[10] + ((this.arrH[10] + (this.margin * 2)) * i5), this.arrW[10], this.indexTabIconSelected != i5, 0, false);
            FrameImage frameImage = LoadDataManager.frameIconTabFriend[i5];
            if (frameImage != null) {
                int i6 = this.indexTabIconSelected != i5 ? 0 : 1;
                float f = ((float) this.arrX[10]) + ((((float) this.arrW[10]) - frameImage.frameWidth) / 2.0f);
                int i7 = this.margin;
                int i8 = this.arrY[10] + this.yAnimTagIcon[i5];
                int i9 = this.arrH[10];
                frameImage.drawFrame(i6, f + ((float) i7), (((float) (i8 + (((i7 * 2) + i9) * i5))) + ((((float) i9) - frameImage.frameHeight) / 2.0f)) - 1.0f, mgraphics);
            }
            i5++;
        }
        LoadDataManager.frameNextPreviousKhoDo[1].drawFrame(this.indexMinus, (float) this.arrX[5], (float) (this.arrY[5] + this.yAnimMinus), mgraphics);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.framePage, this.arrX[6], this.arrY[6], this.arrW[6], false, 0, false);
        if (this.maxPage == -1) {
            fontPaintNumPage.drawString(g, "0/0", (this.arrW[6] / 2) + this.arrX[6], this.arrY[6] + 5, 2);
        } else {
            fontPaintNumPage.drawString(g, (this.numPage + 1) + "/" + (this.maxPage + 1), (this.arrW[6] / 2) + this.arrX[6], this.arrY[6] + 5, 2);
        }
        LoadDataManager.frameNextPreviousKhoDo[0].drawFrame(this.indexPlus, (float) this.arrX[7], (float) (this.arrY[7] + this.yAnimPlus), mgraphics);
        CanvasNinja.resetTrans(g);
        this.cmdClose.paintIconOnly(mgraphics);
    }

    public void init() {
        this.numPage = 0;
        this.maxPage = vecPaints.size() % this.numTag == 0 ? (vecPaints.size() / this.numTag) - 1 : vecPaints.size() / 5;
        MyButton[] myButtonArr = new MyButton[2];
        this.btns = myButtonArr;
        FrameImage[] frameImageArr = LoadDataManager.myButtons;
        String str = "";
        myButtonArr[0] = new MyButton(frameImageArr[1], frameImageArr[3], 55, 0, SupportTranslate.getTextLangue("inviteWord"), 0, 0, new MyCommand("", 1, this));
        MyButton[] myButtonArr2 = this.btns;
        FrameImage[] frameImageArr2 = LoadDataManager.myButtons;
        myButtonArr2[1] = new MyButton(frameImageArr2[0], frameImageArr2[3], 55, 0, SupportTranslate.getTextLangue("inviteAll"), 0, 0, new MyCommand(str, 2, this));
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
        this.title = SupportTranslate.getTextLangue("friend").toUpperCase();
        int i2 = this.margin;
        this.marginOneTab = i2;
        int i3 = this.hOneTag;
        this.transOneTab = i3 + i2;
        int wNenCam = (this.w - 14) - (i2 * 4);
        int i4 = i3 * this.numTag;
        int i5 = this.margin3;
        int hNenCam = i4 + (i5 * 7);
        int i6 = hNenCam + 14 + (((int) LoadDataManager.frameTitle2.frameHeight) / 2) + (i5 * 6) + this.btns[0].h + ((int) LoadDataManager.framePartySearch.frameHeight) + (i5 * 2);
        this.h = i6;
        if (i6 > CanvasNinja.h) {
            this.numTag = 5;
            this.numPage = 0;
            this.maxPage = vecPaints.size() % this.numTag == 0 ? (vecPaints.size() / this.numTag) - 1 : vecPaints.size() / 5;
            MyButton[] myButtonArr3 = new MyButton[2];
            this.btns = myButtonArr3;
            FrameImage[] frameImageArr3 = LoadDataManager.myButtons;
            myButtonArr3[0] = new MyButton(frameImageArr3[1], frameImageArr3[3], 55, 0, SupportTranslate.getTextLangue("inviteWord"), 0, 0, new MyCommand(str, 1, this));
            MyButton[] myButtonArr4 = this.btns;
            FrameImage[] frameImageArr4 = LoadDataManager.myButtons;
            myButtonArr4[1] = new MyButton(frameImageArr4[0], frameImageArr4[3], 55, 0, SupportTranslate.getTextLangue("inviteAll"), 0, 0, new MyCommand(str, 2, this));
            this.hOneTag = (int) LoadDataManager.frameBgTag.frameHeight;
            this.w = 450;
            int i7 = CanvasNinja.w;
            if (450 > i7) {
                this.w = i7 - (this.margin * 5);
            }
            this.wSys = LoadDataManager.imgHeIcon[0].getRWidth();
            this.hSys = LoadDataManager.imgHeIcon[0].getRHeight();
            this.margin = 5;
            this.marginLeftRight = 5 * 2;
            this.title = SupportTranslate.getTextLangue("friend").toUpperCase();
            int i8 = this.margin;
            this.marginOneTab = i8;
            int i9 = this.hOneTag;
            this.transOneTab = i9 + i8;
            wNenCam = (this.w - 14) - (i8 * 4);
            int i10 = i9 * this.numTag;
            int i11 = this.margin3;
            hNenCam = i10 + (i11 * 6);
            this.h = (i8 * 2) + hNenCam + (((int) LoadDataManager.frameTitle2.frameHeight) / 2) + (i11 * 6) + this.btns[0].h + ((int) LoadDataManager.framePartySearch.frameHeight) + (i11 * 2);
        }
        initStart();
        this.hTitle = (int) LoadDataManager.frameTitle.frameHeight;
        int width = TabScr.fontPaintTile.getWidth(this.title) + (this.margin * 8);
        this.wTitle = width;
        int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(8, width, LoadDataManager.frameTitle);
        this.wTitle = fixSizeTagFrameUp;
        this.xTitle = this.x + ((this.w - fixSizeTagFrameUp) / 2);
        this.yTitle = this.y - (this.hTitle / 2);
        this.arrH = new int[]{hNenCam, fontPaintName.getHeight(), fontPaintName.getHeight(), fontPaintName.getHeight(), 0, (int) LoadDataManager.frameMinus.frameHeight, (int) LoadDataManager.framePage.frameHeight, (int) LoadDataManager.framePlus.frameHeight, (int) LoadDataManager.framePartySearch.frameHeight, LoadDataManager.imgIconSearch.getRHeight(), (int) LoadDataManager.frameTabIconHanhTrang.frameHeight};
        int i12 = wNenCam - (this.margin * 2);
        this.wOneTag = i12;
        int w1 = (i12 * 4) / 12;
        int w2 = (i12 * 2) / 12;
        int w3 = (i12 * 2) / 12;
        float f = LoadDataManager.frameMinus.frameWidth;
        int[] iArr = {wNenCam, w1, w2, w3, (i12 * 4) / 12, (int) f, (int) (f * 1.5f), (int) LoadDataManager.framePlus.frameWidth, wNenCam / 3, LoadDataManager.imgIconSearch.getRWidth(), (int) LoadDataManager.frameTabIconHanhTrang.frameWidth};
        this.arrW = iArr;
        int i13 = iArr[5];
        int i14 = this.margin;
        int sumWPage = i13 + (i14 * 2) + iArr[6] + iArr[7];
        int i15 = this.x;
        int xStartLeft = i15 + 7 + this.marginLeftRight;
        int i16 = xStartLeft + i14;
        this.xOneTag = i16;
        int i17 = this.w;
        int i18 = iArr[5];
        int[] iArr2 = {xStartLeft, i16, (i15 + (i17 / 2)) - (i14 * 3), i16 + w1 + w2 + (w3 / 2), (xStartLeft + wNenCam) - i14, (i15 + (i17 / 2)) - (sumWPage / 2), ((i15 + (i17 / 2)) - (sumWPage / 2)) + i14 + i18, ((i15 + (i17 / 2)) - (sumWPage / 2)) + (i14 * 2) + i18 + iArr[6], i16, i16 + iArr[8] + i14, ((i15 + i17) - (i14 * 4)) + 1};
        this.arrX = iArr2;
        int yStartSearch = this.y + 7 + (((int) LoadDataManager.frameTitle2.frameHeight) / 2) + (i14 * 2);
        int[] iArr3 = this.arrH;
        int yStart = iArr3[8] + yStartSearch + i14;
        int i19 = yStart + i14;
        this.yOneTag = i19;
        int i20 = this.hOneTag;
        int i21 = iArr3[0];
        MyButton myButton = this.btns[0];
        int i22 = myButton.h;
        int i23 = hNenCam;
        int hNenCam2 = this.margin3;
        MyCommand myCommand = this.cmdClose;
        this.arrY = new int[]{yStart, i19 + (i20 / 2), i19 + (i20 / 2), i19 + (i20 / 2), i19 + (i20 / 2), ((((yStart + i21) + (i14 * 2)) + ((i22 - iArr3[5]) / 2)) - i14) - hNenCam2, ((((yStart + i21) + (i14 * 2)) + ((i22 - iArr3[6]) / 2)) - i14) - hNenCam2, ((((yStart + i21) + (i14 * 2)) + ((i22 - iArr3[5]) / 2)) - i14) - hNenCam2, yStartSearch - hNenCam2, (((iArr3[8] - iArr3[9]) / 2) + yStartSearch) - hNenCam2, myCommand.y + myCommand.h + (this.margin7 * 2)};
        myButton.setXY(iArr2[0], i21 + yStart + (i14 * 2));
        MyButton myButton2 = this.btns[1];
        myButton2.setXY((this.arrX[0] + this.arrW[0]) - myButton2.w, this.arrY[0] + this.arrH[0] + (this.margin * 2));
        this.btnMessages = new MyCommand[vecFriends.size()];
        this.btnRemoves = new MyCommand[vecFriends.size()];
        int index = 0;
        for (int i24 = 0; i24 < this.btnRemoves.length; i24++) {
            MyCommand cmdRemove = new MyCommand(str, 101, this);
            cmdRemove.subAction = i24;
            cmdRemove.x = this.arrX[4] - (cmdRemove.w / 2);
            cmdRemove.y = (this.arrY[4] + ((this.hOneTag + this.margin3) * index)) - (cmdRemove.h / 2);
            cmdRemove.isPaint = true;
            this.btnRemoves[i24] = cmdRemove;
            MyCommand cmdMessage = new MyCommand(str, 102, this);
            cmdMessage.subAction = i24;
            cmdMessage.img = LoadDataManager.imgMessage;
            cmdMessage.x = ((this.arrX[4] - (cmdMessage.w / 2)) - this.margin) - (cmdRemove.w / 2);
            cmdMessage.y = (this.arrY[4] + ((this.hOneTag + this.margin3) * index)) - (cmdMessage.h / 2);
            cmdMessage.isPaint = true;
            this.btnMessages[i24] = cmdMessage;
            index++;
            if (index > this.numTag - 1) {
                index = 0;
            }
        }
        this.btnMsg = new MyCommand(str, 102, this);
        int i25 = 104;
        MyCommand myCommand2 = new MyCommand(str, 104, this);
        this.btnAdd = myCommand2;
        MyCommand myCommand3 = this.btnMsg;
        int i26 = this.arrX[4];
        int i27 = myCommand3.w;
        myCommand3.x = ((i26 - (i27 / 2)) - this.margin) - (i27 / 2);
        int i28 = this.arrY[4];
        myCommand3.y = i28 - (myCommand3.h / 2);
        myCommand2.x = i26 - (myCommand2.w / 2);
        myCommand2.y = i28 - (myCommand2.h / 2);
        myCommand3.isPaint = true;
        myCommand2.isPaint = true;
        this.btnAccepts = new MyCommand[vecRequests.size()];
        this.btnDeclines = new MyCommand[vecRequests.size()];
        int index2 = 0;
        int i29 = 0;
        while (i29 < this.btnAccepts.length) {
            MyCommand cmdDecline = new MyCommand(str, 103, this);
            cmdDecline.subAction = i29;
            cmdDecline.img = LoadDataManager.imgDecline;
            cmdDecline.x = this.arrX[4] - (cmdDecline.w / 2);
            cmdDecline.y = (this.arrY[4] + ((this.hOneTag + this.margin3) * index2)) - (cmdDecline.h / 2);
            cmdDecline.isPaint = true;
            this.btnDeclines[i29] = cmdDecline;
            MyCommand cmdAccept = new MyCommand(str, i25, this);
            cmdAccept.subAction = i29;
            int i30 = this.arrX[4];
            int i31 = cmdAccept.w;
            cmdAccept.x = ((i30 - (i31 / 2)) - this.margin) - (i31 / 2);
            cmdAccept.y = (this.arrY[4] + ((this.hOneTag + this.margin3) * index2)) - (cmdAccept.h / 2);
            cmdAccept.isPaint = true;
            this.btnAccepts[i29] = cmdAccept;
            index2++;
            if (index2 > this.numTag - 1) {
                index2 = 0;
            }
            i29++;
            i25 = 104;
        }
        TField tField = new TField();
        this.tfSearch = tField;
        tField.fontTField = mFont.tahoma_7b_brown;
        TField.CARET_COLOR = 8799779;
        tField.setIputType(0);
        TField tField2 = this.tfSearch;
        tField2.isKeyTyped = true;
        tField2.setMaxTextLenght(30);
        TField tField3 = this.tfSearch;
        tField3.x = this.arrX[8];
        tField3.y = this.arrY[8];
        tField3.width = this.arrW[8];
        tField3.height = this.arrH[8];
        tField3.updateMarginTField(7);
        this.cmdSearch = new MyCommand(str, 100, this);
    }

    public void update() {
        MyCommand myCommand;
        MyCommand myCommand2;
        MyCommand myCommand3;
        MyCommand myCommand4;
        updateButton();
        int i = 0;
        while (true) {
            boolean[] zArr = this.isClickTagIcon;
            if (i >= zArr.length) {
                break;
            }
            if (zArr[i]) {
                int[] iArr = this.yAnimTagIcon;
                int i2 = iArr[i] + 1;
                iArr[i] = i2;
                if (i2 >= 2) {
                    iArr[i] = 0;
                    zArr[i] = false;
                }
            }
            i++;
        }
        this.indexMinus = 0;
        this.yAnimMinus = 0;
        this.yAnimPlus = 0;
        this.indexPlus = 0;
        this.yAnimSearch = 0;
        this.btnMsg.updateIconOnly();
        this.btnAdd.updateIconOnly();
        int i3 = this.indexTabIconSelected;
        if (i3 == 0) {
            int i4 = this.numPage * this.numTag;
            while (true) {
                int i5 = this.numPage;
                int i6 = this.numTag;
                if (i4 >= (i5 * i6) + i6) {
                    break;
                }
                MyCommand[] myCommandArr = this.btnRemoves;
                if (i4 <= myCommandArr.length - 1 && i4 >= 0) {
                    if (!(myCommandArr == null || (myCommand4 = myCommandArr[i4]) == null)) {
                        myCommand4.updateIconOnly();
                    }
                    MyCommand[] myCommandArr2 = this.btnMessages;
                    if (!(myCommandArr2 == null || (myCommand3 = myCommandArr2[i4]) == null)) {
                        myCommand3.updateIconOnly();
                    }
                }
                i4++;
            }
        } else if (i3 == 1) {
            int i7 = this.numPage * this.numTag;
            while (true) {
                int i8 = this.numPage;
                int i9 = this.numTag;
                if (i7 >= (i8 * i9) + i9) {
                    break;
                }
                MyCommand[] myCommandArr3 = this.btnAccepts;
                if (i7 <= myCommandArr3.length - 1 && i7 >= 0) {
                    if (!(myCommandArr3 == null || (myCommand2 = myCommandArr3[i7]) == null)) {
                        myCommand2.updateIconOnly();
                    }
                    MyCommand[] myCommandArr4 = this.btnDeclines;
                    if (!(myCommandArr4 == null || (myCommand = myCommandArr4[i7]) == null)) {
                        myCommand.updateIconOnly();
                    }
                }
                i7++;
            }
        }
        this.tfSearch.update();
    }

    public void updateKey() {
        this.cmdClose.updateIconOnly();
        updatePointerButton();
        if (CanvasNinja.isPointerRelease()) {
            if (CanvasNinja.isPoint(this.arrX[5], this.arrY[5], this.arrW[5], this.arrH[5])) {
                this.indexMinus = 1;
                this.yAnimMinus = 2;
                CanvasNinja.clearAllPointer();
                int i = this.numPage - 1;
                this.numPage = i;
                if (i < 0) {
                    this.numPage = Math.max(this.maxPage, -1);
                }
            }
            if (CanvasNinja.isPoint(this.arrX[7], this.arrY[7], this.arrW[7], this.arrH[7])) {
                this.indexPlus = 1;
                this.yAnimPlus = 2;
                CanvasNinja.clearAllPointer();
                int i2 = this.numPage + 1;
                this.numPage = i2;
                if (i2 > this.maxPage) {
                    this.numPage = 0;
                }
            }
        }
        if (CanvasNinja.isPointerRelease() && CanvasNinja.isPoint(this.arrX[9], this.arrY[9], this.arrW[9], this.arrH[9])) {
            CanvasNinja.clearAllPointer();
            this.yAnimSearch++;
            this.cmdSearch.perform();
        }
        if (CanvasNinja.isPointerRelease()) {
            for (int i3 = 0; i3 < this.isClickTagIcon.length; i3++) {
                int i4 = this.arrX[10];
                int i5 = this.arrY[10];
                int i6 = this.arrH[10];
                if (CanvasNinja.isPoint(i4, i5 + (((this.margin * 2) + i6) * i3), this.arrW[10], i6)) {
                    AudioManager.buttonClick();
                    this.isClickTagIcon[i3] = true;
                    this.indexTabIconSelected = i3;
                    if (i3 == 0) {
                        this.title = SupportTranslate.getTextLangue("friend").toUpperCase();
                        vecPaints = (Vector) vecFriends.clone();
                        showTab();
                    } else if (i3 == 1) {
                        this.title = SupportTranslate.getTextLangue("friend_request").toUpperCase();
                        vecPaints = (Vector) vecRequests.clone();
                        showTab();
                    }
                    int width = TabScr.fontPaintTile.getWidth(this.title) + (this.margin * 8);
                    this.wTitle = width;
                    int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(8, width, LoadDataManager.frameTitle);
                    this.wTitle = fixSizeTagFrameUp;
                    this.xTitle = this.x + ((this.w - fixSizeTagFrameUp) / 2);
                    this.yTitle = this.y - (this.hTitle / 2);
                    CanvasNinja.clearAllPointer();
                }
            }
        }
        this.tfSearch.updateFocus2();
    }

    public void show() {
        this.indexTabIconSelected = 0;
        vecPaints = (Vector) vecFriends.clone();
        init();
        showTab();
    }

    public void keyPress(int keyCode) {
        if (this.tfSearch.isFocused()) {
            this.tfSearch.keyPressed(keyCode);
            if (keyCode == KEY.KEY_ENTER) {
                this.tfSearch.setFocus(false);
                this.cmdSearch.perform();
            }
        } else if (keyCode == KEY.KEY_ENTER) {
            this.tfSearch.setFocus(true);
        }
    }

    public void keyTyped() {
        if (this.tfSearch.isFocused()) {
            this.tfSearch.keyTyped();
        }
    }

    public void commandTab(int index, int subIndex) {
        Res.outz("Vào commandTab TabFriend:" + index + "_sub:" + subIndex);
        super.commandTab(index, subIndex);
        switch (index) {
            case 2:
                int i = this.numPage * this.numTag;
                while (true) {
                    int i2 = this.numPage;
                    int i3 = this.numTag;
                    if (i < (i2 * i3) + i3) {
                        if (i < this.btnApplys.length) {
                            invite(i);
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            case 99:
                invite(subIndex);
                return;
            case 100:
                String text = this.tfSearch.getText();
                if (!text.equals("")) {
                    SendMessage.gI().getPlayersMatchingName(text);
                    showTab();
                    this.tfSearch.setTextFirst("");
                    return;
                }
                SendMessage.gI().openUIFriend();
                return;
            case 101:
                Player c = vecFriends.get(subIndex);
                SendMessage.gI().removeFriend(c.charID, c.cName, (byte) c.classId, (short) c.clevel);
                return;
            case 102:
                Player c2 = vecPaints.get(subIndex);
                if (c2 != Player.myCharz()) {
                    ChatBoxPM.gI().show(c2.cName);
                    return;
                }
                return;
            case 103:
                Player c3 = vecRequests.get(subIndex);
                SendMessage.gI().removeFriend(c3.charID, c3.cName, (byte) c3.classId, (short) c3.clevel);
                return;
            case 104:
                Player c4 = vecPaints.get(subIndex);
                SendMessage.gI().requestAddFriend(c4.charID, c4.cName, (byte) c4.classId, (short) c4.clevel);
                return;
            default:
                return;
        }
    }

    private void invite(int subIndex) {
        this.btnApplys[subIndex].isDone = true;
        SendMessage.gI().invitePlayerToParty(TabParty.vecPartySearchs.get(subIndex).playerId);
    }
}
