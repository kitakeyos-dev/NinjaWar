package ninjawar.screen.subtab;

import java.util.Iterator;
import java.util.Vector;
import ninjawar.input.KEY;
import ninjawar.message.Config;
import ninjawar.model.BoxInventory;
import ninjawar.model.MyCommand;
import ninjawar.model.PopupItemInfo;
import ninjawar.mylib.AudioManager;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.object.Item;
import ninjawar.screen.tab.TabUpgrade;
import ninjawar.scroll.ScrollY;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

public class SubTabInven extends SubTabScr {
    public static mFont fontPaintMoney = mFont.tahoma_7_brown_small;
    public static mFont fontPaintNameChar = mFont.tahoma_name_char_inventory;
    public static mFont fontPaintTagName = mFont.tahoma_brown_dv;
    public static mFont fontPaintTagNameFocus = mFont.tahoma_brown_focus_dv;
    public static SubTabInven me;
    public BoxInventory box;
    public Player character;
    int indexSelected = -1;
    int indexSelectedUsed = -1;
    int indexTabIconSelected = -1;
    int indexTagNameSelected;
    boolean[] isClickTagIcon = new boolean[2];
    boolean[] isClickTagName = new boolean[4];
    boolean isHavePopup = false;
    public boolean isPaintNum;
    public boolean isPaintTagIcon = false;
    public boolean isPaintTagName = false;
    public int marginBottomMoney = 5;
    public int marginMoney = 10;
    int maxDocBox = 20;
    int maxODoc;
    public String[] moneyStr;
    public String nameTabShop = "";
    public String[] nameTags;
    int numNgangBox;
    int numODoc;
    int numTag = 2;
    public PopupItemInfo popupItemInfo;
    int startUsed = 0;
    int sumWBoxNgang;
    int sumWMoney = 0;
    public Vector<Item> vecFixingItems = new Vector<>();
    public Vector<Item> vecItemMes = new Vector<>();
    public Vector<Item> vecItemTemps = new Vector<>();
    public Vector<Item> vecItems = new Vector<>();
    int wChar;
    int wLeft;
    int wRight;
    public int[] xStartMoney;
    int[] yAnimTagIcon = new int[2];
    int[] yAnimTagName = new int[4];
    public int[] yStartMoney;

    public static SubTabInven gI() {
        if (me == null) {
            me = new SubTabInven();
        }
        return me;
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        if (this.character != null) {
            SupportPaint supportPaint = CanvasNinja.paintz;
            FrameImage frameImage = LoadDataManager.frameTitle;
            int i = this.x + (this.w / 2);
            int i2 = this.arrW[5];
            supportPaint.paintTagFrame(g, frameImage, i - (i2 / 2), this.arrY[5], i2, false, 0, false);
            SubTabScr.fontPaintTile.drawString(g, this.nameTabShop.toUpperCase(), (this.w / 2) + this.x, (this.arrH[5] / 2) + this.arrY[5], 3);
        } else {
            SupportPaint supportPaint2 = CanvasNinja.paintz;
            FrameImage frameImage2 = LoadDataManager.frameTitle;
            int i3 = this.x + (this.w / 2);
            int i4 = this.arrW[5];
            supportPaint2.paintTagFrame(g, frameImage2, i3 - (i4 / 2), this.arrY[5], i4, false, 0, false);
            SubTabScr.fontPaintTile.drawString(g, this.nameTabShop.toUpperCase(), (this.w / 2) + this.x, (this.arrH[5] / 2) + this.arrY[5], 3);
        }
        if (this.character != null) {
            mgraphics.drawImage(LoadDataManager.imgCucDat, (float) this.arrX[2], (float) (this.arrY[2] + 20));
            this.character.paintChar(mgraphics, this.arrX[3], this.arrY[3] + 20, false);
            fontPaintNameChar.drawString(g, this.character.cName, this.arrX[4], this.arrY[4] + 20, 2);
        }
        CanvasNinja.resetTrans(g);
        if (this.isPaintTagName) {
            int i5 = 0;
            while (i5 < this.isClickTagName.length) {
                SupportPaint supportPaint3 = CanvasNinja.paintz;
                FrameImage frameImage3 = LoadDataManager.frameTagTitleHanhTrang;
                int i6 = this.arrX[6];
                int i7 = this.margin;
                int i8 = this.arrW[6];
                int i9 = i5;
                supportPaint3.paintTagFrame(g, frameImage3, ((i7 + i8) * i5) + i6, this.yAnimTagName[i5] + this.arrY[6], i8, this.indexTagNameSelected == i5, 0, false);
                FrameImage frameImage4 = LoadDataManager.frameIconTagNameInventory[i9];
                int i10 = this.indexTagNameSelected == i9 ? 1 : 0;
                int i11 = this.arrX[6];
                int i12 = this.margin;
                int i13 = this.arrW[6];
                frameImage4.drawFrame(i10, ((float) ((i11 + (i9 * (i12 + i13))) + (i13 / 2))) - (frameImage4.frameWidth / 2.0f), ((float) ((this.arrY[6] + this.yAnimTagName[i9]) + (this.arrH[6] / 2))) - (frameImage4.frameHeight / 2.0f), mgraphics);
                i5 = i9 + 1;
            }
            int i14 = i5;
        }
        if (this.character != null) {
            SupportPaint supportPaint4 = CanvasNinja.paintz;
            FrameImage frameImage5 = LoadDataManager.frameVachDoc;
            int i15 = this.wLeft + this.x;
            int i16 = this.y;
            int i17 = this.margin;
            int i18 = this.h;
            supportPaint4.paintLineVertical(g, frameImage5, i15, i16 + i17, i18 - (i17 * 2), i18, false);
        }
        this.cmdClose.paintIconOnly(mgraphics);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameXam2, (float) this.arrX[7], (float) this.arrY[7], (float) this.arrW[7], (float) this.arrH[7], 2, false);
        int i19 = this.arrX[7];
        int i20 = this.arrY[7];
        int i21 = this.margin;
        mgraphics.setClip(i19, i20 + i21, this.arrW[7], this.arrH[7] - (i21 * 2));
        mgraphics.translate(0, -this.scroll.cmy);
        for (int j = 0; j < this.numNgangBox; j++) {
            int k = 0;
            while (k < this.maxDocBox) {
                int indexItem = (this.numNgangBox * k) + j;
                SupportPaint supportPaint5 = CanvasNinja.paintz;
                FrameImage frameImage6 = LoadDataManager.frameBoxHanhTrangDefault;
                int i22 = this.arrX[8];
                int i23 = this.margin2;
                int i24 = this.arrW[8];
                int i25 = ((i23 + i24) * j) + i22;
                int i26 = this.arrY[8] + ((i23 + this.arrH[8]) * k);
                int i27 = this.indexSelected;
                int indexItem2 = indexItem;
                int k2 = k;
                supportPaint5.paintTagFrame(g, frameImage6, i25, i26, i24, i27 != -1 && i27 == indexItem, 0, true);
                if (isInRangeVecItem(indexItem2)) {
                    if (this.vecItems.get(indexItem2).rarity != -1) {
                        SupportPaint supportPaint6 = CanvasNinja.paintz;
                        FrameImage frameImage7 = LoadDataManager.frameBoxHanhTrang[this.vecItems.get(indexItem2).rarity];
                        int i28 = this.arrX[8];
                        int i29 = this.margin2;
                        int i30 = this.arrW[8];
                        int i31 = ((i29 + i30) * j) + i28;
                        int i32 = this.arrY[8] + (k2 * (i29 + this.arrH[8]));
                        int i33 = this.indexSelected;
                        supportPaint6.paintTagFrame(g, frameImage7, i31, i32, i30, i33 != -1 && i33 == indexItem2, 0, true);
                    }
                    int i34 = this.arrX[8];
                    int i35 = this.margin2;
                    int i36 = this.arrW[8];
                    int i37 = ((i35 + i36) * j) + i34;
                    int i38 = this.arrY[8];
                    int i39 = this.arrH[8];
                    this.vecItems.get(indexItem2).paintCenter(g, i37, i38 + (k2 * (i35 + i39)), i36, i39, true, this.isPaintNum);
                    if (isItemMe(this.vecItems.get(indexItem2))) {
                        Image image = LoadDataManager.imgInvenIsMe;
                        int i40 = this.arrX[8];
                        int i41 = this.margin2;
                        g.drawImage(image, (float) (i40 + ((this.arrW[8] + i41) * j) + 5), (float) (this.arrY[8] + (k2 * (i41 + this.arrH[8])) + 5), 0, true);
                    }
                }
                k = k2 + 1;
            }
            int i42 = k;
        }
        CanvasNinja.resetTrans(g);
        paintMoney(mgraphics, 0);
        paintMoney(mgraphics, 1);
        paintMoney(mgraphics, 2);
        if (this.isPaintTagIcon) {
            int i43 = 0;
            while (i43 < this.isClickTagIcon.length) {
                CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameTabIconHanhTrang, this.arrX[9], this.yAnimTagIcon[i43] + this.arrY[9] + ((this.arrH[9] + (this.margin * 2)) * i43), this.arrW[9], this.indexTabIconSelected != i43, 0, false);
                FrameImage frameImage8 = LoadDataManager.frameIconTabHanhTrangs[i43];
                if (frameImage8 != null) {
                    int i44 = this.indexTabIconSelected != i43 ? 0 : 1;
                    float f = ((float) this.arrX[9]) + ((((float) this.arrW[9]) - frameImage8.frameWidth) / 2.0f);
                    int i45 = this.margin;
                    int i46 = this.arrY[9] + this.yAnimTagIcon[i43];
                    int i47 = this.arrH[9];
                    frameImage8.drawFrame(i44, f + ((float) i45), (((float) (i46 + (((i45 * 2) + i47) * i43))) + ((((float) i47) - frameImage8.frameHeight) / 2.0f)) - 1.0f, mgraphics);
                }
                i43++;
            }
        }
        PopupItemInfo popupItemInfo2 = this.popupItemInfo;
        if (popupItemInfo2 != null && popupItemInfo2.isPaintPopup) {
            popupItemInfo2.paint(mgraphics, false, true);
        }
    }

    private boolean isInRangeVecItem(int index) {
        return index < this.vecItems.size() && index >= 0 && this.vecItems.get(index) != null;
    }

    public void initChar() {
        Player player = new Player();
        this.character = player;
        player.cloneMe();
        this.character.setDirByCDir(1);
    }

    public void resetSelected() {
        this.indexSelected = -1;
        this.indexTagNameSelected = 0;
        this.indexTabIconSelected = 0;
    }

    private int getSumHElement(int numODoc2) {
        return (this.arrH[0] * numODoc2) + ((numODoc2 - 1) * this.margin2);
    }

    public void updateNum(int index, int num) {
        if (num == -1) {
            this.vecItems.remove(index);
        } else {
            this.vecItems.get(index).num = num;
        }
        if (this.indexTagNameSelected != 0) {
            this.vecItemTemps.removeAllElements();
            this.vecItemTemps.addAll(this.vecItems);
            classifyItem((byte) this.indexTagNameSelected);
        }
    }

    public void init(String nameTabShop2, boolean isPaintChar, boolean isPaintTagName2) {
        int i;
        boolean z = isPaintTagName2;
        this.isPaintTagName = z;
        this.vecItemTemps.addAll(this.vecItems);
        resetSelected();
        String[] arrayLangue = SupportTranslate.getArrayLangue("TAG_NAME_INVENTORY");
        this.nameTags = arrayLangue;
        this.yAnimTagName = new int[arrayLangue.length];
        this.isClickTagName = new boolean[arrayLangue.length];
        this.w = 360;
        this.h = 300;
        int i2 = CanvasNinja.w;
        if (360 > i2) {
            this.w = (i2 - (this.margin * 5)) - ((int) LoadDataManager.frameTabIconHanhTrang.frameWidth);
        }
        int i3 = CanvasNinja.h;
        if (300 > i3) {
            this.h = i3 - (this.margin * 2);
        }
        if (isPaintChar) {
            initChar();
            Res.outz("isMe zzzzzzzzzzzzzzzzzzzz:" + this.character.isMe());
            this.numNgangBox = this.numNgangBox + 1;
            int i4 = this.w;
            float f = LoadDataManager.frameBoxHanhTrang[0].frameWidth;
            int i5 = this.margin2;
            this.w = i4 + ((int) f) + i5;
            this.x -= (((int) f) + i5) / 2;
        }
        initStart();
        if (z) {
            i = (int) LoadDataManager.frameTagTitleHanhTrang.frameHeight;
        } else {
            i = this.margin;
        }
        int hTagTitleHanhTrang = i;
        float f2 = LoadDataManager.frameBoxUsed.frameHeight;
        this.arrH = new int[]{(int) f2, (int) f2, LoadDataManager.imgCucDat.getRHeight(), 90, 0, (int) LoadDataManager.frameTitle.frameHeight, hTagTitleHanhTrang, ((((this.h - (this.margin7 * 2)) - (this.margin * 2)) - hTagTitleHanhTrang) - 20) - 14, (int) LoadDataManager.frameBoxHanhTrang[0].frameHeight, (int) LoadDataManager.frameTabIconHanhTrang.frameHeight};
        Player player = this.character;
        int marginLeft = player != null ? this.margin * 12 : 0;
        int i6 = player != null ? player.cw : 0;
        this.wChar = i6;
        int i7 = player != null ? this.margin7 + (marginLeft * 2) + i6 : this.margin7;
        this.wLeft = i7;
        int i8 = (this.w - i7) - this.margin;
        this.wRight = i8;
        int wKhungXam = (i8 - (LoadDataManager.imgClose.getRWidth() / 2)) - this.margin7;
        int numTab = this.nameTags.length;
        int wTagTitleHanhTrang = Res.fixSizeTagFrameDown(7, (((this.wRight - LoadDataManager.imgClose.getRWidth()) - this.margin7) - (this.margin * numTab)) / numTab, LoadDataManager.frameTagTitleHanhTrang);
        this.nameTabShop = nameTabShop2;
        int width = SubTabScr.fontPaintTile.getWidth(nameTabShop2.toUpperCase());
        int i9 = this.margin;
        int i10 = (int) LoadDataManager.frameBoxHanhTrang[0].frameWidth;
        this.arrW = new int[]{0, 0, 0, 22, 0, width + (i9 * 10), wTagTitleHanhTrang, wKhungXam, i10, (int) LoadDataManager.frameTabIconHanhTrang.frameWidth};
        int i11 = this.x;
        int xODauTien = this.margin7 + i11 + marginLeft + (this.wChar / 2);
        int xRight = i11 + this.wLeft + i9 + this.margin3;
        int i12 = this.margin2;
        int i13 = (wKhungXam - i12) / (i12 + i10);
        this.numNgangBox = i13;
        int i14 = (i10 * i13) + ((i13 - 1) * i12);
        this.sumWBoxNgang = i14;
        int[] iArr = this.arrW;
        int i15 = iArr[0];
        this.arrX = new int[]{xODauTien, xODauTien, (int) (((float) xODauTien) - (LoadDataManager.imgCucDat.getWidth() / 2.0f)), xODauTien, xODauTien + i15, (xODauTien + i15) - (iArr[5] / 2), xRight, xRight, xRight + ((wKhungXam - i14) / 2), ((this.x + this.w) - (this.margin * 4)) + 1};
        this.numODoc = 4;
        this.maxODoc = 4;
        int sumHElement = getSumHElement(4);
        int i16 = this.y;
        int yODauTien = i16 + ((this.h - sumHElement) / 2);
        int[] iArr2 = this.arrH;
        int yCucDat = yODauTien + ((sumHElement - (iArr2[2] + 60)) / 2) + 60;
        int i17 = this.margin;
        int yDauTienPhai = (i17 * 3) + i16 + this.margin3;
        int i18 = iArr2[6];
        int i19 = yDauTienPhai + i18 + i17;
        int numTab2 = numTab;
        MyCommand myCommand = this.cmdClose;
        this.arrY = new int[]{yODauTien, yODauTien, yCucDat, (yCucDat + (iArr2[2] / 2)) - 4, yODauTien - (i17 * 2), i16 - (iArr2[5] / 2), yDauTienPhai, i19, yDauTienPhai + i18 + i17 + i17, myCommand.y + myCommand.h + (this.margin7 * 2)};
        if (z) {
            int i20 = (int) (((float) (i18 - 14)) * mGraphics.zoomLevel);
            fontPaintTagName = mFont.fontPaintTagName;
            fontPaintTagNameFocus = mFont.fontPaintTagNameFocus;
        }
        int fontSize = this.maxDocBox;
        int hMaxPaint = (iArr2[8] * fontSize) + ((fontSize - 1) * this.margin2);
        int i21 = this.arrX[7];
        int i22 = this.arrW[7];
        int i23 = iArr2[7];
        int i24 = i23 - (i17 * 2);
        int i25 = i23;
        int i26 = numTab2;
        initScroll(i21, i19, i22, i25, hMaxPaint, i24);
        initMoney();
    }

    public void initMoney() {
        int[] money = Player.myCharz().getMoneyInven();
        this.moneyStr = new String[3];
        this.xStartMoney = new int[3];
        this.yStartMoney = new int[3];
        this.sumWMoney = 0;
        int yStartTemp = this.arrY[7] + this.arrH[7] + this.margin + 23;
        int xDefault = this.arrX[7];
        int i = 0;
        while (true) {
            Image[] imageArr = LoadDataManager.imgBgMoney;
            if (i < imageArr.length) {
                this.sumWMoney += imageArr[i].getRWidth();
                this.moneyStr[i] = Res.formatMoney(money[i]);
                this.yStartMoney[i] = (yStartTemp - LoadDataManager.imgBgMoney[i].getRHeight()) - this.marginBottomMoney;
                String str = this.moneyStr[i];
                int i2 = this.arrX[7];
                this.xStartMoney[i] = xDefault + getAlignMoney(i, str, xDefault, (this.arrW[7] + i2) - i2);
                i++;
            } else {
                this.sumWMoney += (imageArr.length - 1) * this.marginMoney;
                return;
            }
        }
    }

    private int getAlignMoney(int type, String moneyStr2, int xStart, int wPaint) {
        int sumW = LoadDataManager.imgMoney[type].getRWidth() + this.margin + fontPaintMoney.getWidth(moneyStr2);
        if (type == 1) {
            return (wPaint - sumW) / 2;
        }
        if (type == 2) {
            return wPaint - sumW;
        }
        return 0;
    }

    public void paintMoney(mGraphics g, int type) {
        int yMore = 0;
        if (type == 2) {
            yMore = -2;
        }
        if (type == 1) {
            yMore = -4;
        }
        g.drawImage(LoadDataManager.imgMoney[type], (float) this.xStartMoney[type], (float) (this.yStartMoney[type] + yMore));
        if (type == 0) {
            fontPaintMoney.drawString(g, this.moneyStr[type], this.xStartMoney[type] + LoadDataManager.imgMoney[type].getRWidth() + this.margin, this.yStartMoney[0] + 7);
        }
        if (type == 1) {
            fontPaintMoney.drawString(g, this.moneyStr[type], this.xStartMoney[type] + LoadDataManager.imgMoney[type].getRWidth() + this.margin, (this.yStartMoney[type] + 7) - 2);
        }
        if (type == 2) {
            fontPaintMoney.drawString(g, this.moneyStr[type], this.xStartMoney[type] + LoadDataManager.imgMoney[type].getRWidth() + this.margin, (this.yStartMoney[type] + 7) - 2);
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
        updateScroll();
        ScrollY scrollY = this.scroll;
        if (scrollY.trans) {
            scrollY.isHavePopup = false;
        }
        int i = 0;
        while (true) {
            boolean[] zArr = this.isClickTagName;
            if (i >= zArr.length) {
                break;
            }
            if (zArr[i]) {
                int[] iArr = this.yAnimTagName;
                int i2 = iArr[i] + 1;
                iArr[i] = i2;
                if (i2 >= 2) {
                    iArr[i] = 0;
                    zArr[i] = false;
                }
            }
            i++;
        }
        int i3 = 0;
        while (true) {
            boolean[] zArr2 = this.isClickTagIcon;
            if (i3 < zArr2.length) {
                if (zArr2[i3]) {
                    int[] iArr2 = this.yAnimTagIcon;
                    int i4 = iArr2[i3] + 1;
                    iArr2[i3] = i4;
                    if (i4 >= 2) {
                        iArr2[i3] = 0;
                        zArr2[i3] = false;
                    }
                }
                i3++;
            } else {
                return;
            }
        }
    }

    public void updateKey() {
        this.cmdClose.updateIconOnly();
        PopupItemInfo popupItemInfo2 = this.popupItemInfo;
        if (popupItemInfo2 != null) {
            popupItemInfo2.updatePointer();
        }
        if (CanvasNinja.isPointerRelease()) {
            if (CanvasNinja.isPointerRelease() && this.isPaintTagName) {
                for (int i = 0; i < this.isClickTagName.length; i++) {
                    int i2 = this.arrX[6];
                    int i3 = this.margin2;
                    int i4 = this.arrW[6];
                    if (CanvasNinja.isPoint(i2 + ((i3 + i4) * i), this.arrY[6], i4, this.arrH[6])) {
                        CanvasNinja.clearAllPointer();
                        this.isClickTagName[i] = true;
                        this.indexTagNameSelected = i;
                        classifyItem((byte) i);
                        AudioManager.buttonClick();
                    }
                }
            }
            if (CanvasNinja.isPointerRelease()) {
                for (int i5 = 0; i5 < this.isClickTagIcon.length; i5++) {
                    int i6 = this.arrX[9];
                    int i7 = this.arrY[9];
                    int i8 = this.arrH[9];
                    if (CanvasNinja.isPoint(i6, i7 + (((this.margin * 2) + i8) * i5), this.arrW[9], i8)) {
                        AudioManager.buttonClick();
                        this.isClickTagIcon[i5] = true;
                        this.indexTabIconSelected = i5;
                        CanvasNinja.clearAllPointer();
                    }
                }
            }
            boolean isCheckPopup = false;
            PopupItemInfo popupItemInfo3 = this.popupItemInfo;
            if (popupItemInfo3 != null) {
                popupItemInfo3.updatePointer();
            }
            if (CanvasNinja.isPointerRelease()) {
                for (int j = 0; j < this.numNgangBox; j++) {
                    for (int k = 0; k < this.maxDocBox; k++) {
                        int indexItem = (this.numNgangBox * k) + j;
                        int i9 = this.arrX[8];
                        int i10 = this.margin2;
                        int i11 = this.arrW[8];
                        int i12 = i9 + ((i10 + i11) * j);
                        int i13 = this.arrY[8];
                        int i14 = this.arrH[8];
                        if (CanvasNinja.isPoint(i12, (i13 + ((i10 + i14) * k)) - this.scroll.cmy, i11, i14)) {
                            CanvasNinja.clearAllPointer();
                            this.indexSelected = indexItem;
                            if (isInRangeVecItem(indexItem)) {
                                this.popupItemInfo = new PopupItemInfo();
                                byte type = 10;
                                if (isClickLeft()) {
                                    type = 9;
                                }
                                this.box = new BoxInventory();
                                if (this.vecItems.get(indexItem).rarity != -1) {
                                    this.box = new BoxInventory(LoadDataManager.frameBoxHanhTrang[this.vecItems.get(indexItem).rarity], this.vecItems.get(indexItem));
                                } else {
                                    this.box = new BoxInventory(LoadDataManager.frameBoxHanhTrangDefault, this.vecItems.get(indexItem));
                                }
                                PopupItemInfo popupItemInfo4 = this.popupItemInfo;
                                int i15 = this.arrX[8];
                                int i16 = this.margin2;
                                int i17 = this.arrW[8];
                                int i18 = i15 + ((i16 + i17) * j) + i17;
                                int i19 = this.arrY[8];
                                int i20 = this.arrH[8];
                                popupItemInfo4.startPopupItemInfo(i18, ((i19 + ((i16 + i20) * k)) + i20) - this.scroll.cmy, type, this.box, (byte) 5);
                                isCheckPopup = true;
                            }
                        }
                    }
                }
            }
            this.scroll.isHavePopup = isCheckPopup;
        }
        if (!this.scroll.isHavePopup) {
            this.popupItemInfo = null;
        }
    }

    private void initItemByType(short[] type) {
        for (int i = 0; i < this.vecItemTemps.size(); i++) {
            if (this.vecItemTemps.get(i).template != null && Res.checkNumInArr((short) this.vecItemTemps.get(i).template.type, type)) {
                this.vecItems.add(this.vecItemTemps.get(i));
            }
        }
    }

    public void classifyItem(byte type) {
        this.vecItems.removeAllElements();
        switch (type) {
            case 0:
                this.vecItems.addAll(this.vecItemTemps);
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

    private void startTabShop(String nameTabShop2, boolean isPaintChar, boolean isPaintTagName2) {
        init(nameTabShop2, isPaintChar, isPaintTagName2);
        showTab();
        me = this;
    }

    public void startSubInven() {
        startTabShop(SupportTranslate.getTextLangue("trangbi"), false, false);
    }

    public void startSubItem() {
        startTabShop(SupportTranslate.getTextLangue("khoangThach"), false, false);
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
        gI().updateNum(0, 10);
    }

    public void keyTyped() {
    }

    public void commandTab(int index, int subIndex) {
        super.commandTab(index, subIndex);
        switch (index) {
            case 50:
                if (this.box != null) {
                    TabUpgrade.gI().selectItem(this.vecItems.get(this.indexSelected));
                    TabUpgrade.gI().indexItemTab01 = this.indexSelected;
                    this.popupItemInfo = null;
                    close();
                    return;
                }
                return;
            default:
                this.popupItemInfo = null;
                return;
        }
    }

    private boolean isClickLeft() {
        return CanvasNinja.isPoint(this.arrX[7], this.arrY[7], this.arrW[7] / 2, this.arrH[7]);
    }

    public boolean isItemMe(Item item) {
        Iterator<Item> it = this.vecItemMes.iterator();
        while (it.hasNext()) {
            if (it.next().idTemplate == item.idTemplate) {
                return true;
            }
        }
        return false;
    }
}
