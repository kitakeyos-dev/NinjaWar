package ninjawar.screen.tab;

import java.util.Vector;
import ninjawar.input.KEY;
import ninjawar.input.MyNextPrevious;
import ninjawar.message.Config;
import ninjawar.model.BoxInventory;
import ninjawar.model.ItemOption;
import ninjawar.model.MyCommand;
import ninjawar.model.PopupItemInfo;
import ninjawar.model.mItemOption;
import ninjawar.mylib.AudioManager;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.object.Item;
import ninjawar.scroll.ScrollY;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

public class TabCharInfo extends TabScr {
    public static mFont fontPaintCharInfo = mFont.fontPaintCharInfo;
    public static mFont fontPaintCharInfo2 = mFont.fontPaintCharInfo2;
    public static mFont fontPaintCharInfoFocus = mFont.fontPaintCharInfoFocus;
    public static mFont fontPaintMoney = mFont.fontPaintMoneyInven;
    public static mFont fontPaintNameChar = mFont.tahoma_name_char_inventory;
    public static mFont fontPaintTagName = mFont.tahoma_brown_dv;
    public static mFont fontPaintTagNameFocus = mFont.tahoma_brown_focus_dv;
    public static TabCharInfo me;
    public Player character;
    boolean flagConfirm;
    public int indexSelectedInven = -1;
    public int indexSelectedUsed = -1;
    public int indexTabIconSelected;
    public int indexTagNameSelected;
    boolean[] isClickTagIcon;
    boolean[] isClickTagName = new boolean[4];
    boolean isHavePopup;
    int lastYScroll;
    public int marginBottomMoney;
    public int marginMoney;
    int maxDocBox = 20;
    int maxODoc;
    public String[] moneyStr;
    MyNextPrevious myNextPrevious;
    public String[] nameTags;
    int numNgangBox;
    int numODoc;
    int numTag;
    public mItemOption[] optionsInfos;
    public PopupItemInfo popupItemInfo;
    public PopupItemInfo popupItemUsedInfo;
    int startUsed = 0;
    int[] statChange;
    int sumChange;
    int sumWBoxNgang;
    int sumWMoney;
    int tabInfo;
    public Vector<Item> vecItemInvenTemps = new Vector<>();
    public Vector<Item> vecItemInvens = new Vector<>();
    public Vector<Item> vecItemUsedInvens = new Vector<>();
    int wLeft;
    int wRight;
    int xMoreFromNextPrevious;
    int xMoreFromNextPreviousDefault;
    public int[] xStartMoney;
    int[] yAnimTagIcon;
    int[] yAnimTagName = new int[4];
    int yMoreFromNextPrevious;
    int yMoreFromNextPreviousDefault;
    public int[] yStartMoney;

    public TabCharInfo() {
        int length = LoadDataManager.frameIconTabHanhTrangs.length;
        this.numTag = length;
        this.yAnimTagIcon = new int[length];
        this.isClickTagIcon = new boolean[length];
        this.lastYScroll = 0;
        this.marginMoney = 10;
        this.marginBottomMoney = 5;
        this.sumWMoney = 0;
        this.isHavePopup = false;
        this.sumChange = 0;
        this.statChange = new int[]{0, 0, 0, 0, 0};
        this.flagConfirm = false;
    }

    public static TabCharInfo gI() {
        if (me == null) {
            me = new TabCharInfo();
        }
        return me;
    }

    public void paint(mGraphics g) {
        char c;
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        mgraphics.translate(this.xMoreFromNextPrevious, 0);
        mgraphics.drawImage(LoadDataManager.imgCucDat, (float) this.arrX[2], (float) this.arrY[2]);
        Player player = this.character;
        if (player != null) {
            player.paintChar(mgraphics, this.arrX[3], this.arrY[3], false);
            fontPaintNameChar.drawString(g, this.character.cName, this.arrX[4] - 5, this.arrY[4] - (this.margin * 2), 2);
            mgraphics.drawImage(LoadDataManager.imgHeIconPlayer[Player.myCharz().classId], (float) ((this.arrX[4] - 5) + (fontPaintNameChar.getWidth(this.character.cName) / 2) + this.margin), (float) ((this.arrY[4] + (fontPaintNameChar.getHeight() / 4)) - (this.margin * 2)));
        }
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameTitle, this.arrX[5], this.arrY[5], this.arrW[5], false, 0, false);
        TabScr.fontPaintTile.drawString(g, SupportTranslate.getTextLangue("info").toUpperCase(), (this.arrW[5] / 2) + this.arrX[5], (this.arrH[5] / 2) + this.arrY[5], 3);
        CanvasNinja.resetTrans(g);
        mgraphics.translate(this.xMoreFromNextPrevious, this.yMoreFromNextPrevious);
        int index = 0;
        int i = this.startUsed;
        while (i < this.maxODoc) {
            SupportPaint supportPaint = CanvasNinja.paintz;
            FrameImage frameImage = LoadDataManager.frameBoxUsed;
            int i2 = this.arrX[0];
            int i3 = ((this.arrH[0] + this.margin2) * index) + this.arrY[0];
            int i4 = this.arrW[0];
            int i5 = this.indexSelectedUsed;
            supportPaint.paintTagFrame(g, frameImage, i2, i3, i4, i5 != -1 && i5 == i, 0, false);
            SupportPaint supportPaint2 = CanvasNinja.paintz;
            FrameImage frameImage2 = LoadDataManager.frameBoxUsed;
            int i6 = this.arrX[1];
            int i7 = ((this.arrH[0] + this.margin2) * index) + this.arrY[1];
            int i8 = this.arrW[1];
            int i9 = this.indexSelectedUsed;
            supportPaint2.paintTagFrame(g, frameImage2, i6, i7, i8, i9 != -1 && i9 == this.numODoc + i, 0, false);
            if (isInRangeVecItemUsed(i)) {
                int i10 = this.arrX[0];
                int i11 = this.arrY[0];
                int i12 = this.arrH[0];
                this.vecItemUsedInvens.get(i).paintCenter(g, i10, ((this.margin2 + i12) * index) + i11, this.arrW[0], i12, false, false);
            } else {
                Image image = LoadDataManager.imgIconHide[i];
                int i13 = this.arrY[0];
                int i14 = this.arrH[0];
                mgraphics.drawImage(image, (float) ((this.arrX[0] + (this.arrW[0] / 2)) - (image.getRWidth() / 2)), (float) (((i13 + ((this.margin2 + i14) * index)) + (i14 / 2)) - (LoadDataManager.imgIconHide[i].getRHeight() / 2)));
            }
            if (isInRangeVecItemUsed(this.numODoc + i)) {
                int i15 = this.arrX[1];
                int i16 = this.arrY[1];
                int[] iArr = this.arrH;
                this.vecItemUsedInvens.get(this.numODoc + i).paintCenter(g, i15, ((iArr[0] + this.margin2) * index) + i16, this.arrW[1], iArr[1], false, false);
                c = 2;
            } else {
                Image[] imageArr = LoadDataManager.imgIconHide;
                int i17 = this.numODoc;
                if (imageArr[i + i17] != null) {
                    Image image2 = imageArr[i + i17];
                    c = 2;
                    float rWidth = (float) ((this.arrX[1] + (this.arrW[1] / 2)) - (imageArr[i17 + i].getRWidth() / 2));
                    int i18 = this.arrY[1];
                    int i19 = this.arrH[0];
                    mgraphics.drawImage(image2, rWidth, (float) (((i18 + ((this.margin2 + i19) * index)) + (i19 / 2)) - (LoadDataManager.imgIconHide[this.numODoc + i].getRHeight() / 2)));
                } else {
                    c = 2;
                }
            }
            index++;
            i++;
            char c2 = c;
        }
        CanvasNinja.resetTrans(g);
        MyNextPrevious myNextPrevious2 = this.myNextPrevious;
        if (myNextPrevious2 != null) {
            myNextPrevious2.paint(mgraphics);
        }
        PopupItemInfo popupItemInfo2 = this.popupItemUsedInfo;
        if (popupItemInfo2 != null && popupItemInfo2.isPaintPopup) {
            PopupItemInfo.isOtherCharInfo = true;
            popupItemInfo2.paint(mgraphics, false, false);
        }
        this.cmdClose.paintIconOnly(mgraphics);
    }

    private boolean isInRangeVecItem(int index) {
        return index < this.vecItemInvens.size() && index >= 0 && this.vecItemInvens.get(index) != null && this.vecItemInvens.get(index).idTemplate > -1;
    }

    private boolean isInRangeVecItemUsed(int index) {
        return index < this.vecItemUsedInvens.size() && index >= 0 && this.vecItemUsedInvens.get(index) != null && this.vecItemUsedInvens.get(index).idTemplate > -1;
    }

    public void initChar() {
        Player player = new Player();
        this.character = player;
        player.cloneMe();
        this.character.setDirByCDir(1);
    }

    public void resetSelected() {
        this.indexSelectedInven = -1;
        this.indexSelectedUsed = -1;
        this.indexTabIconSelected = 0;
        this.indexTagNameSelected = 0;
    }

    private int getSumHElement(int numODoc2) {
        return (this.arrH[0] * numODoc2) + ((numODoc2 - 1) * this.margin2);
    }

    public void init() {
        int curY;
        this.vecItemInvenTemps.addAll(this.vecItemInvens);
        resetSelected();
        initChar();
        String[] arrayLangue = SupportTranslate.getArrayLangue("TAG_NAME_INVENTORY");
        this.nameTags = arrayLangue;
        this.yAnimTagName = new int[arrayLangue.length];
        this.isClickTagName = new boolean[arrayLangue.length];
        int wBoxUsed = (int) LoadDataManager.frameBoxUsed.frameWidth;
        int wCucDat = LoadDataManager.imgCucDat.getRWidth();
        int i = this.margin;
        int i2 = (i * 4) + (wBoxUsed * 2) + (10 * 2) + wCucDat + i + ((((int) LoadDataManager.framePrevious.frameWidth) + i) * 2) + (i * 4);
        this.w = i2;
        this.h = 300;
        int i3 = CanvasNinja.w;
        if (i2 > i3) {
            this.w = (i3 - (i * 5)) - ((int) LoadDataManager.frameTabIconHanhTrang.frameWidth);
        }
        int i4 = CanvasNinja.h;
        if (300 > i4) {
            this.h = i4 - (i * 2);
        }
        initStart();
        int hTagTitleHanhTrang = (int) LoadDataManager.frameTagTitleHanhTrang.frameHeight;
        float f = LoadDataManager.frameBoxUsed.frameHeight;
        this.arrH = new int[]{(int) f, (int) f, LoadDataManager.imgCucDat.getRHeight(), 90, 0, (int) LoadDataManager.frameTitle.frameHeight, hTagTitleHanhTrang, (((this.h - (this.margin7 * 2)) - (this.margin * 2)) - hTagTitleHanhTrang) - 20, (int) LoadDataManager.frameBoxHanhTrang[0].frameHeight, (int) LoadDataManager.frameTabIconHanhTrang.frameHeight};
        int i5 = this.margin;
        int i6 = (i5 * 4) + (wBoxUsed * 2) + (10 * 2) + wCucDat + i5 + ((((int) LoadDataManager.framePrevious.frameWidth) + i5) * 2);
        this.wLeft = i6;
        int i7 = (this.w - i6) - i5;
        this.wRight = i7;
        int wKhungXam = (i7 - (LoadDataManager.imgClose.getRWidth() / 2)) - this.margin7;
        int numTab = this.nameTags.length;
        int wTagTitleHanhTrang = Res.fixSizeTagFrameDown(7, (((this.wRight - LoadDataManager.imgClose.getRWidth()) - this.margin7) - (this.margin * numTab)) / numTab, LoadDataManager.frameTagTitleHanhTrang);
        int width = TabScr.fontPaintTile.getWidth(SupportTranslate.getTextLangue("info").toUpperCase());
        int i8 = this.margin;
        int i9 = width + (i8 * 5);
        int i10 = (int) LoadDataManager.frameBoxHanhTrang[0].frameWidth;
        int[] iArr = {wBoxUsed, wBoxUsed, wCucDat, 22, 0, i9, wTagTitleHanhTrang, wKhungXam, i10, (int) LoadDataManager.frameTabIconHanhTrang.frameWidth};
        this.arrW = iArr;
        int i11 = this.x;
        int xODauTien = this.margin7 + i11 + (i8 * 2);
        int xRight = this.margin3 + this.wLeft + i11 + i8;
        int i12 = this.margin2;
        int i13 = (wKhungXam - i12) / (i12 + i10);
        this.numNgangBox = i13;
        int i14 = (i10 * i13) + ((i13 - 1) * i12);
        this.sumWBoxNgang = i14;
        int i15 = ((int) LoadDataManager.framePrevious.frameWidth) + i8;
        this.xMoreFromNextPreviousDefault = i15;
        this.xMoreFromNextPrevious = i15;
        int wName = wCucDat + (10 * 2);
        int i16 = iArr[0];
        this.arrX = new int[]{xODauTien, xODauTien + i16 + wCucDat + (10 * 2), xODauTien + i16 + 10, xODauTien + i16 + 10 + (wCucDat / 2), xODauTien + i16 + 10 + (wCucDat / 2), ((xODauTien + i16) + (wName / 2)) - (i9 / 2), xRight, xRight, xRight + ((wKhungXam - i14) / 2), ((i11 + this.w) - (i8 * 4)) + 1};
        this.numODoc = 4;
        this.maxODoc = 4;
        int sumHElement = getSumHElement(4);
        int i17 = this.y;
        int i18 = this.h;
        int yODauTien = i17 + ((i18 - sumHElement) / 2);
        int[] iArr2 = this.arrH;
        int hCucDatVaChar = iArr2[2] + 60;
        int i19 = this.margin;
        int yDauTienPhai = (i19 * 2) + i17 + this.margin3;
        int i20 = iArr2[6];
        MyCommand myCommand = this.cmdClose;
        this.arrY = new int[]{yODauTien, yODauTien, ((i18 - hCucDatVaChar) / 2) + i17 + 60, (((yODauTien + ((sumHElement - hCucDatVaChar) / 2)) + 60) + (iArr2[2] / 2)) - 4, yODauTien - (i19 * 2), i17 - (iArr2[5] / 2), yDauTienPhai, yDauTienPhai + i20 + i19, yDauTienPhai + i20 + i19 + i19, myCommand.y + myCommand.h + (this.margin7 * 2)};
        this.yMoreFromNextPreviousDefault = (i17 + ((i18 - getSumHElement(3)) / 2)) - this.arrY[1];
        int i21 = this.arrX[1] + this.arrW[1] + (this.margin * 2);
        FrameImage frameImage = LoadDataManager.framePrevious;
        float f2 = frameImage.frameWidth;
        float f3 = frameImage.frameHeight;
        this.myNextPrevious = new MyNextPrevious(i21 + ((int) f2), (this.y + (this.h / 2)) - (((int) f3) / 2), (int) f2, (int) f3, 0, 0, 0, true, true);
        int fontSize = (int) (((float) (this.arrH[6] - 14)) * mGraphics.zoomLevel);
        Res.outz("font sizeeeee:" + fontSize);
        fontPaintTagName = mFont.fontPaintTagName;
        fontPaintTagNameFocus = mFont.fontPaintTagNameFocus;
        int i22 = this.maxDocBox;
        int[] iArr3 = this.arrH;
        int hMaxPaint = (iArr3[8] * i22) + ((i22 - 1) * this.margin2);
        int i23 = this.arrX[7];
        int i24 = this.arrY[7];
        int i25 = this.arrW[7];
        int numTab2 = iArr3[7];
        int i26 = i25;
        int i27 = numTab;
        int i28 = wBoxUsed;
        int i29 = fontSize;
        initScroll(i23, i24, i26, numTab2, hMaxPaint, numTab2 - (this.margin * 2));
        int curY2 = this.arrY[7] + (this.margin * 9);
        if (this.optionsInfos != null) {
            for (int i30 = 1; i30 < this.optionsInfos.length; i30++) {
                int j = 0;
                while (true) {
                    ItemOption[] itemOptionArr = this.optionsInfos[i30].itemOption;
                    if (j >= itemOptionArr.length) {
                        break;
                    }
                    if (itemOptionArr[j].param[0] != 0) {
                        curY2 += this.margin * 3;
                    }
                    j++;
                }
            }
            curY = curY2;
        } else {
            curY = curY2;
        }
        int curY3 = this.margin;
        int i31 = this.arrX[7];
        int i32 = this.arrY[7];
        int i33 = this.arrW[7];
        int i34 = this.arrH[7];
        initScroll2(i31, i32, i33, i34 - 30, (curY + curY3) - 60, (i34 - (curY3 * 2)) - 30);
        initMoney();
        this.tabInfo = 1;
    }

    public void initMoney() {
        int[] money = Player.myCharz().getMoneyInven();
        this.moneyStr = new String[3];
        this.xStartMoney = new int[3];
        this.yStartMoney = new int[3];
        this.sumWMoney = 0;
        int yStartTemp = this.arrY[0] + getSumHElement(4) + 30;
        int xDefault = (this.arrX[0] + this.xMoreFromNextPrevious) - 10;
        int i = 0;
        while (true) {
            Image[] imageArr = LoadDataManager.imgBgMoney;
            if (i < imageArr.length) {
                this.sumWMoney += imageArr[i].getRWidth();
                this.moneyStr[i] = Res.formatMoney(money[i]);
                this.yStartMoney[i] = (yStartTemp - LoadDataManager.imgBgMoney[i].getRHeight()) - this.marginBottomMoney;
                int[] iArr = this.arrX;
                this.xStartMoney[i] = ((((((iArr[1] - iArr[0]) + (this.arrW[0] * 2)) - (this.margin * 2)) - 2) / 3) * i) + xDefault;
                i++;
            } else {
                this.sumWMoney += (imageArr.length - 1) * this.marginMoney;
                return;
            }
        }
    }

    public void update() {
        Player player = this.character;
        if (player != null) {
            player.update();
        }
        PopupItemInfo popupItemInfo2 = this.popupItemInfo;
        if (popupItemInfo2 != null) {
            popupItemInfo2.update();
        }
        PopupItemInfo popupItemInfo3 = this.popupItemUsedInfo;
        if (popupItemInfo3 != null) {
            popupItemInfo3.update();
        }
        MyNextPrevious myNextPrevious2 = this.myNextPrevious;
        if (myNextPrevious2 != null) {
            myNextPrevious2.update();
        }
        updateScroll();
        ScrollY scrollY = this.scroll;
        if (scrollY.trans) {
            scrollY.isHavePopup = false;
        }
        ScrollY scrollY2 = this.scroll2;
        if (scrollY2.trans) {
            scrollY2.isHavePopup = false;
        }
    }

    public void updateKey() {
        BoxInventory box;
        BoxInventory box2;
        BoxInventory box3;
        this.cmdClose.updateIconOnly();
        PopupItemInfo popupItemInfo2 = this.popupItemInfo;
        if (popupItemInfo2 != null) {
            popupItemInfo2.updatePointer();
        }
        PopupItemInfo popupItemInfo3 = this.popupItemUsedInfo;
        if (popupItemInfo3 != null) {
            popupItemInfo3.updatePointer();
        }
        if (this.myNextPrevious != null && CanvasNinja.isPointerRelease()) {
            MyNextPrevious myNextPrevious2 = this.myNextPrevious;
            if (CanvasNinja.isPoint(myNextPrevious2.x, myNextPrevious2.y, myNextPrevious2.w, myNextPrevious2.h)) {
                AudioManager.buttonClick();
                CanvasNinja.clearAllPointer();
                if (this.myNextPrevious.isNext) {
                    int i = this.arrX[0] - this.margin;
                    int i2 = this.y + (this.h / 2);
                    FrameImage frameImage = LoadDataManager.framePrevious;
                    float f = frameImage.frameHeight;
                    this.myNextPrevious = new MyNextPrevious(i, i2 - (((int) f) / 2), (int) frameImage.frameWidth, (int) f, 0, 0, 0, true, false);
                    this.xMoreFromNextPrevious = this.xMoreFromNextPreviousDefault;
                    this.yMoreFromNextPrevious = this.yMoreFromNextPreviousDefault;
                    this.startUsed = 8;
                    this.numODoc = 3;
                    this.maxODoc = 8 + 3;
                } else {
                    int i3 = this.arrX[1] + this.arrW[1] + (this.margin * 2);
                    FrameImage frameImage2 = LoadDataManager.framePrevious;
                    float f2 = frameImage2.frameWidth;
                    float f3 = frameImage2.frameHeight;
                    this.myNextPrevious = new MyNextPrevious(((int) f2) + i3, (this.y + (this.h / 2)) - (((int) f3) / 2), (int) f2, (int) f3, 0, 0, 0, true, true);
                    this.xMoreFromNextPrevious = this.xMoreFromNextPreviousDefault;
                    this.yMoreFromNextPrevious = 0;
                    this.startUsed = 0;
                    this.numODoc = 4;
                    this.maxODoc = 4 + 0;
                }
                this.popupItemInfo = null;
                this.popupItemUsedInfo = null;
            }
        }
        if (CanvasNinja.isPointerRelease()) {
            if (this.indexTabIconSelected == 0 && CanvasNinja.isPointerRelease()) {
                for (int i4 = 0; i4 < this.isClickTagName.length; i4++) {
                    int i5 = this.arrX[6];
                    int i6 = this.margin2;
                    int i7 = this.arrW[6];
                    if (CanvasNinja.isPoint(i5 + ((i6 + i7) * i4), this.arrY[6], i7, this.arrH[6])) {
                        CanvasNinja.clearAllPointer();
                        this.isClickTagName[i4] = true;
                        this.indexTagNameSelected = i4;
                        classifyItem((byte) i4);
                        AudioManager.buttonClick();
                    }
                }
            }
            if (CanvasNinja.isPointerRelease()) {
                for (int i8 = 0; i8 < this.isClickTagIcon.length; i8++) {
                    int i9 = this.arrX[9];
                    int i10 = this.arrY[9];
                    int i11 = this.arrH[9];
                    if (CanvasNinja.isPoint(i9, i10 + (((this.margin * 2) + i11) * i8), this.arrW[9], i11)) {
                        AudioManager.buttonClick();
                        this.isClickTagIcon[i8] = true;
                        this.indexTabIconSelected = i8;
                        CanvasNinja.clearAllPointer();
                    }
                }
            }
            if (this.indexTabIconSelected == 0) {
                boolean isCheckPopup = false;
                PopupItemInfo popupItemInfo4 = this.popupItemInfo;
                if (popupItemInfo4 != null) {
                    popupItemInfo4.updatePointer();
                }
                PopupItemInfo popupItemInfo5 = this.popupItemUsedInfo;
                if (popupItemInfo5 != null) {
                    popupItemInfo5.updatePointer();
                }
                byte b = -1;
                if (CanvasNinja.isPointerRelease()) {
                    int j = 0;
                    while (j < this.numNgangBox) {
                        int k = 0;
                        while (k < this.maxDocBox) {
                            int indexItem = (this.numNgangBox * k) + j;
                            int i12 = this.arrX[8];
                            int i13 = this.margin2;
                            int i14 = this.arrW[8];
                            int i15 = i12 + ((i13 + i14) * j);
                            int i16 = this.arrY[8];
                            int i17 = this.arrH[8];
                            if (CanvasNinja.isPoint(i15, (i16 + ((i13 + i17) * k)) - this.scroll.cmy, i14, i17)) {
                                CanvasNinja.clearAllPointer();
                                this.indexSelectedInven = indexItem;
                                if (isInRangeVecItem(indexItem)) {
                                    this.popupItemUsedInfo = null;
                                    this.popupItemInfo = new PopupItemInfo();
                                    byte type = 10;
                                    if (isClickLeft()) {
                                        type = 9;
                                    }
                                    new BoxInventory();
                                    if (this.vecItemInvens.get(indexItem).rarity != b) {
                                        box3 = new BoxInventory(LoadDataManager.frameBoxHanhTrang[this.vecItemInvens.get(indexItem).rarity], this.vecItemInvens.get(indexItem));
                                    } else {
                                        box3 = new BoxInventory(LoadDataManager.frameBoxHanhTrangDefault, this.vecItemInvens.get(indexItem));
                                    }
                                    if (this.vecItemInvens.get(indexItem).template != null && !this.vecItemInvens.get(indexItem).isDisableClick()) {
                                        PopupItemInfo popupItemInfo6 = this.popupItemInfo;
                                        int i18 = this.arrX[8];
                                        int i19 = this.margin2;
                                        int i20 = this.arrW[8];
                                        int i21 = i18 + ((i19 + i20) * j) + i20;
                                        int i22 = this.arrY[8];
                                        int i23 = this.arrH[8];
                                        popupItemInfo6.startPopupItemInfo(i21, ((i22 + ((i19 + i23) * k)) + i23) - this.scroll.cmy, type, box3, (byte) 0);
                                        if (MapScr.gI().tutorial != null && MapScr.gI().tutorial.isStepThaoTacItem(2) && this.vecItemInvens.get(indexItem).isFocusTutorial) {
                                            MapScr.gI().tutorial.step = 3;
                                        }
                                    }
                                    isCheckPopup = true;
                                }
                            }
                            k++;
                            b = -1;
                        }
                        j++;
                        b = -1;
                    }
                }
                boolean isHavePopupUsed = false;
                if (CanvasNinja.isPointerRelease()) {
                    int index = 0;
                    for (int i24 = this.startUsed; i24 < this.maxODoc; i24++) {
                        int i25 = this.arrX[0] + this.xMoreFromNextPrevious;
                        int i26 = this.arrY[0];
                        int i27 = this.arrH[0];
                        if (CanvasNinja.isPoint(i25, i26 + ((this.margin2 + i27) * index) + this.yMoreFromNextPrevious, this.arrW[0], i27)) {
                            this.indexSelectedUsed = i24;
                            byte alignY = 4;
                            if (i24 == 0) {
                                alignY = 5;
                            } else if (i24 == this.maxODoc - 1) {
                                alignY = 6;
                            }
                            CanvasNinja.clearAllPointer();
                            if (isInRangeVecItemUsed(this.indexSelectedUsed)) {
                                this.popupItemInfo = null;
                                this.popupItemUsedInfo = new PopupItemInfo();
                                new BoxInventory();
                                if (this.vecItemUsedInvens.get(this.indexSelectedUsed).rarity != -1) {
                                    box2 = new BoxInventory(LoadDataManager.frameBoxHanhTrang[this.vecItemUsedInvens.get(this.indexSelectedUsed).rarity], this.vecItemUsedInvens.get(this.indexSelectedUsed));
                                } else {
                                    box2 = new BoxInventory(LoadDataManager.frameBoxHanhTrangDefault, this.vecItemUsedInvens.get(this.indexSelectedUsed));
                                }
                                if (this.vecItemUsedInvens.get(this.indexSelectedUsed).template != null && !this.vecItemUsedInvens.get(this.indexSelectedUsed).isDisableClick()) {
                                    PopupItemInfo popupItemInfo7 = this.popupItemUsedInfo;
                                    int i28 = this.arrX[0] + this.arrW[0] + this.xMoreFromNextPrevious;
                                    int i29 = this.arrY[0];
                                    int i30 = this.arrH[0];
                                    popupItemInfo7.startPopupItemInfo(i28, i29 + ((this.margin2 + i30) * index) + (i30 / 2) + this.yMoreFromNextPrevious, alignY, box2, (byte) 1);
                                }
                                isHavePopupUsed = true;
                                isCheckPopup = true;
                            }
                        }
                        int i31 = this.arrX[1] + this.xMoreFromNextPrevious;
                        int i32 = this.arrY[1];
                        int i33 = this.arrH[1];
                        if (CanvasNinja.isPoint(i31, i32 + ((this.margin2 + i33) * index) + this.yMoreFromNextPrevious, this.arrW[1], i33)) {
                            this.indexSelectedUsed = this.numODoc + i24;
                            byte alignY2 = 4;
                            if (i24 == 0) {
                                alignY2 = 5;
                            } else if (i24 == this.maxODoc - 1) {
                                alignY2 = 6;
                            }
                            CanvasNinja.clearAllPointer();
                            if (isInRangeVecItemUsed(this.indexSelectedUsed)) {
                                this.popupItemInfo = null;
                                this.popupItemUsedInfo = new PopupItemInfo();
                                new BoxInventory();
                                if (this.vecItemUsedInvens.get(this.indexSelectedUsed).rarity != -1) {
                                    box = new BoxInventory(LoadDataManager.frameBoxHanhTrang[this.vecItemUsedInvens.get(this.indexSelectedUsed).rarity], this.vecItemUsedInvens.get(this.indexSelectedUsed));
                                } else {
                                    box = new BoxInventory(LoadDataManager.frameBoxHanhTrangDefault, this.vecItemUsedInvens.get(this.indexSelectedUsed));
                                }
                                if (this.vecItemUsedInvens.get(this.indexSelectedUsed).template != null) {
                                    if (!this.vecItemUsedInvens.get(this.indexSelectedUsed).isDisableClick()) {
                                        PopupItemInfo popupItemInfo8 = this.popupItemUsedInfo;
                                        int i34 = this.arrX[1] + this.arrW[1] + this.xMoreFromNextPrevious;
                                        int i35 = this.arrY[1];
                                        int[] iArr = this.arrH;
                                        popupItemInfo8.startPopupItemInfo(i34, i35 + ((iArr[1] + this.margin2) * index) + (iArr[0] / 2) + this.yMoreFromNextPrevious, alignY2, box, (byte) 1);
                                    }
                                }
                                isHavePopupUsed = true;
                                isCheckPopup = true;
                            }
                        }
                        index++;
                    }
                }
                if (!isHavePopupUsed) {
                    this.popupItemUsedInfo = null;
                }
                this.scroll.isHavePopup = isCheckPopup;
            }
        }
        if (this.indexTabIconSelected == 0 && !this.scroll.isHavePopup) {
            this.popupItemInfo = null;
        }
    }

    private void initItemByType(short[] type) {
        for (int i = 0; i < this.vecItemInvenTemps.size(); i++) {
            if (this.vecItemInvenTemps.get(i).template != null && Res.checkNumInArr((short) this.vecItemInvenTemps.get(i).template.type, type)) {
                this.vecItemInvens.add(this.vecItemInvenTemps.get(i));
            }
        }
    }

    public void classifyItem(byte type) {
        this.vecItemInvens.removeAllElements();
        switch (type) {
            case 0:
                this.vecItemInvens.addAll(this.vecItemInvenTemps);
                return;
            case 1:
                initItemByType(new short[]{0, 2, 6});
                return;
            case 2:
                initItemByType(new short[]{1});
                return;
            case 3:
                initItemByType(new short[]{3, 4, 5});
                return;
            case 4:
                initItemByType(new short[]{19, 20});
                return;
            default:
                return;
        }
    }

    public void show() {
        init();
        showTab();
        me = this;
    }

    public void show(Player ch) {
        init();
        this.character = ch;
        showTab();
        me = this;
    }

    public void keyPress(int keyCode) {
        if (keyCode == KEY.KEY_B) {
            close();
        }
        if (keyCode == KEY.KEY_T && Config.isOfflineMode) {
            hardCodeUpdateInven();
        }
    }

    private void hardCodeUpdateInven() {
        TabInventory.gI().updateNum(0, 10);
    }

    public void keyTyped() {
    }

    public void commandTab(int index, int subIndex) {
        super.commandTab(index, subIndex);
        Res.outz(1, "Vào command Tab Tab Inventory:" + index);
    }

    private boolean isClickLeft() {
        return CanvasNinja.isPoint(this.arrX[7], this.arrY[7], this.arrW[7] / 2, this.arrH[7]);
    }
}
