package ninjawar.screen.tab;

import java.util.Iterator;
import java.util.Vector;
import ninjawar.input.KEY;
import ninjawar.input.MyNextPrevious;
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
import ninjawar.screen.subtab.SubTabBuyShop;
import ninjawar.scroll.ScrollY;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;
import ninjawar.template.ItemTemplate;

public class TabShop extends TabScr {
    public static mFont fontPaintMoney = mFont.tahoma_7_brown_small;
    public static mFont fontPaintNameChar = mFont.tahoma_name_char_inventory;
    public static mFont fontPaintTagName = mFont.tahoma_brown_dv;
    public static mFont fontPaintTagNameFocus = mFont.tahoma_brown_focus_dv;
    public static TabShop me;
    public BoxInventory box;
    public Player character;
    public short idNpc;
    int indexSelected = -1;
    int indexSelectedUsed = -1;
    public int indexTabIconSelected = 0;
    int indexTagNameSelected = 0;
    boolean[] isClickTagIcon = new boolean[3];
    boolean[] isClickTagName = new boolean[4];
    boolean isHavePopup = false;
    public boolean isPaintTagIcon = false;
    public boolean isPaintTagName = false;
    int lastIndexSelected = -1;
    int lastIndexSelectedUsed = -1;
    public int marginBottomMoney = 5;
    public int marginMoney = 10;
    int maxDocBox = 20;
    int maxODoc = 4;
    public String[] moneyStr;
    MyNextPrevious myNextPrevious;
    public String nameTabShop = "";
    public String[] nameTags;
    int numNgangBox;
    int numODoc = 4;
    int numTag = 3;
    public PopupItemInfo popupItemInfo;
    public PopupItemInfo popupItemUsedInfo;
    int startUsed = 0;
    int sumWBoxNgang;
    int sumWMoney = 0;
    public Vector<Item> vecFixingItems = new Vector<>();
    public Vector<Item> vecItemMes = new Vector<>();
    public Vector<Item> vecItemTemps = new Vector<>();
    public Vector<Item> vecItemUsedInvens = new Vector<>();
    public Vector<Item> vecItems = new Vector<>();
    public Vector<Item> vecItemsCanSell = new Vector<>();
    public Vector<Item> vecPaint = new Vector<>();
    int wChar;
    int wLeft;
    int wRight;
    int xMoreFromNextPrevious;
    int xMoreFromNextPreviousDefault;
    public int[] xStartMoney;
    int[] yAnimTagIcon = new int[3];
    int[] yAnimTagName = new int[4];
    int yMoreFromNextPrevious;
    int yMoreFromNextPreviousDefault;
    public int[] yStartMoney;

    public static TabShop gI() {
        if (me == null) {
            me = new TabShop();
        }
        return me;
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        int i = 2;
        if (this.character != null) {
            SupportPaint supportPaint = CanvasNinja.paintz;
            FrameImage frameImage = LoadDataManager.frameTitle;
            int i2 = this.x + (this.w / 2);
            int i3 = this.arrW[5];
            supportPaint.paintTagFrame(g, frameImage, i2 - (i3 / 2), this.arrY[5], i3, false, 0, false);
            TabScr.fontPaintTile.drawString(g, this.nameTabShop.toUpperCase(), (this.w / 2) + this.x, (this.arrH[5] / 2) + this.arrY[5], 3);
        } else {
            SupportPaint supportPaint2 = CanvasNinja.paintz;
            FrameImage frameImage2 = LoadDataManager.frameTitle;
            int i4 = this.x + (this.w / 2);
            int i5 = this.arrW[5];
            supportPaint2.paintTagFrame(g, frameImage2, i4 - (i5 / 2), this.arrY[5], i5, false, 0, false);
            TabScr.fontPaintTile.drawString(g, this.nameTabShop.toUpperCase(), (this.w / 2) + this.x, (this.arrH[5] / 2) + this.arrY[5], 3);
        }
        mgraphics.translate(this.xMoreFromNextPrevious, 0);
        if (this.character != null) {
            mgraphics.drawImage(LoadDataManager.imgCucDat, (float) this.arrX[2], (float) (this.arrY[2] + 20));
            this.character.paintChar(mgraphics, this.arrX[3], this.arrY[3] + 20, false);
            fontPaintNameChar.drawString(g, this.character.cName, this.arrX[4], this.arrY[4] + 20, 2);
        }
        CanvasNinja.resetTrans(g);
        mgraphics.translate(this.xMoreFromNextPrevious, this.yMoreFromNextPrevious);
        int index = 0;
        int i6 = this.startUsed;
        for (int i7 = 4; i6 < this.startUsed + i7; i7 = 4) {
            SupportPaint supportPaint3 = CanvasNinja.paintz;
            FrameImage frameImage3 = LoadDataManager.frameBoxUsed;
            int i8 = this.arrX[0];
            int i9 = ((this.arrH[0] + this.margin2) * index) + this.arrY[0];
            int i10 = this.arrW[0];
            int i11 = this.indexSelectedUsed;
            boolean z = true;
            int i12 = i6;
            supportPaint3.paintTagFrame(g, frameImage3, i8, i9, i10, i11 != -1 && i11 == i6, 0, false);
            if (isInRangeVecItemUsed(i12)) {
                Item item = this.vecItemUsedInvens.get(i12);
                int i13 = this.arrX[0];
                int i14 = this.arrY[0];
                int i15 = this.arrH[0];
                item.paintCenter(g, i13, ((this.margin2 + i15) * index) + i14, this.arrW[0], i15, false, this.vecItemUsedInvens.get(i12).num > 1);
            } else {
                Image[] imageArr = LoadDataManager.imgIconHide;
                Image image = imageArr[i12 % imageArr.length];
                float rWidth = (float) ((this.arrX[0] + (this.arrW[0] / i)) - (imageArr[i12 % imageArr.length].getRWidth() / i));
                int i16 = this.arrY[0];
                int i17 = this.arrH[0];
                int i18 = i16 + ((this.margin2 + i17) * index) + (i17 / i);
                Image[] imageArr2 = LoadDataManager.imgIconHide;
                mgraphics.drawImage(image, rWidth, (float) (i18 - (imageArr2[i12 % imageArr2.length].getRHeight() / i)));
            }
            int rightIndex = i12 + 4;
            SupportPaint supportPaint4 = CanvasNinja.paintz;
            FrameImage frameImage4 = LoadDataManager.frameBoxUsed;
            int i19 = this.arrX[1];
            int i20 = ((this.arrH[0] + this.margin2) * index) + this.arrY[1];
            int i21 = this.arrW[1];
            int i22 = this.indexSelectedUsed;
            int rightIndex2 = rightIndex;
            supportPaint4.paintTagFrame(g, frameImage4, i19, i20, i21, i22 != -1 && i22 == rightIndex, 0, false);
            if (isInRangeVecItemUsed(rightIndex2)) {
                Item item2 = this.vecItemUsedInvens.get(rightIndex2);
                int i23 = this.arrX[1];
                int i24 = this.arrY[1];
                int[] iArr = this.arrH;
                int i25 = ((iArr[0] + this.margin2) * index) + i24;
                int i26 = this.arrW[1];
                int i27 = iArr[1];
                if (this.vecItemUsedInvens.get(rightIndex2).num <= 1) {
                    z = false;
                }
                item2.paintCenter(g, i23, i25, i26, i27, false, z);
            } else {
                Image[] imageArr3 = LoadDataManager.imgIconHide;
                if (imageArr3[rightIndex2 % imageArr3.length] != null) {
                    Image image2 = imageArr3[rightIndex2 % imageArr3.length];
                    float rWidth2 = (float) ((this.arrX[1] + (this.arrW[1] / 2)) - (imageArr3[rightIndex2 % imageArr3.length].getRWidth() / 2));
                    int i28 = this.arrY[1];
                    int i29 = this.arrH[0];
                    int i30 = i28 + ((this.margin2 + i29) * index) + (i29 / 2);
                    Image[] imageArr4 = LoadDataManager.imgIconHide;
                    mgraphics.drawImage(image2, rWidth2, (float) (i30 - (imageArr4[rightIndex2 % imageArr4.length].getRHeight() / 2)));
                }
            }
            index++;
            i6 = i12 + 1;
            i = 2;
        }
        int i31 = i6;
        CanvasNinja.resetTrans(g);
        MyNextPrevious myNextPrevious2 = this.myNextPrevious;
        if (myNextPrevious2 != null) {
            myNextPrevious2.paint(mgraphics);
        }
        if (this.isPaintTagName) {
            int i32 = 0;
            while (i32 < this.isClickTagName.length) {
                SupportPaint supportPaint5 = CanvasNinja.paintz;
                FrameImage frameImage5 = LoadDataManager.frameTagTitleHanhTrang;
                int i33 = this.arrX[6];
                int i34 = this.margin;
                int i35 = this.arrW[6];
                supportPaint5.paintTagFrame(g, frameImage5, ((i34 + i35) * i32) + i33, this.yAnimTagName[i32] + this.arrY[6], i35, this.indexTagNameSelected == i32, 0, false);
                FrameImage frameImage6 = LoadDataManager.frameIconTagNameInventory[i32];
                int i36 = this.indexTagNameSelected == i32 ? 1 : 0;
                int i37 = this.arrX[6];
                int i38 = this.margin;
                int i39 = this.arrW[6];
                frameImage6.drawFrame(i36, ((float) ((i37 + ((i38 + i39) * i32)) + (i39 / 2))) - (frameImage6.frameWidth / 2.0f), ((float) ((this.arrY[6] + this.yAnimTagName[i32]) + (this.arrH[6] / 2))) - (frameImage6.frameHeight / 2.0f), mgraphics);
                i32++;
            }
        }
        if (this.character != null) {
            SupportPaint supportPaint6 = CanvasNinja.paintz;
            FrameImage frameImage7 = LoadDataManager.frameVachDoc;
            int i40 = this.wLeft + this.x;
            int i41 = this.y;
            int i42 = this.margin;
            int i43 = this.h;
            supportPaint6.paintLineVertical(g, frameImage7, i40, i41 + i42, i43 - (i42 * 2), i43, false);
        }
        this.cmdClose.paintIconOnly(mgraphics);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameXam2, (float) this.arrX[7], (float) this.arrY[7], (float) this.arrW[7], (float) this.arrH[7], 2, false);
        int i44 = this.arrX[7];
        int i45 = this.arrY[7];
        int i46 = this.margin;
        mgraphics.setClip(i44, i45 + i46, this.arrW[7], this.arrH[7] - (i46 * 2));
        mgraphics.translate(0, -this.scroll.cmy);
        for (int j = 0; j < this.numNgangBox; j++) {
            int k = 0;
            while (k < this.maxDocBox) {
                int indexItem = (this.numNgangBox * k) + j;
                SupportPaint supportPaint7 = CanvasNinja.paintz;
                FrameImage frameImage8 = LoadDataManager.frameBoxHanhTrangDefault;
                int i47 = this.arrX[8];
                int i48 = this.margin2;
                int i49 = this.arrW[8];
                int i50 = ((i48 + i49) * j) + i47;
                int i51 = this.arrY[8] + ((i48 + this.arrH[8]) * k);
                int i52 = this.indexSelected;
                int indexItem2 = indexItem;
                int k2 = k;
                supportPaint7.paintTagFrame(g, frameImage8, i50, i51, i49, i52 != -1 && i52 == indexItem, 0, true);
                if (isInRangeVecItem(indexItem2)) {
                    if (this.vecPaint.get(indexItem2).rarity != -1) {
                        SupportPaint supportPaint8 = CanvasNinja.paintz;
                        FrameImage frameImage9 = LoadDataManager.frameBoxHanhTrang[this.vecPaint.get(indexItem2).rarity];
                        int i53 = this.arrX[8];
                        int i54 = this.margin2;
                        int i55 = this.arrW[8];
                        int i56 = ((i54 + i55) * j) + i53;
                        int i57 = this.arrY[8] + (k2 * (i54 + this.arrH[8]));
                        int i58 = this.indexSelected;
                        supportPaint8.paintTagFrame(g, frameImage9, i56, i57, i55, i58 != -1 && i58 == indexItem2, 0, true);
                    }
                    int i59 = this.arrX[8];
                    int i60 = this.margin2;
                    int i61 = this.arrW[8];
                    int i62 = ((i60 + i61) * j) + i59;
                    int i63 = this.arrY[8];
                    int i64 = this.arrH[8];
                    this.vecPaint.get(indexItem2).paintCenter(g, i62, i63 + (k2 * (i60 + i64)), i61, i64, true, false);
                    if (isItemMe(this.vecPaint.get(indexItem2))) {
                        Image image3 = LoadDataManager.imgInvenIsMe;
                        int i65 = this.arrX[8];
                        int i66 = this.margin2;
                        g.drawImage(image3, (float) (i65 + ((this.arrW[8] + i66) * j) + 5), (float) (this.arrY[8] + (k2 * (i66 + this.arrH[8])) + 5), 0, true);
                    }
                }
                k = k2 + 1;
            }
            int i67 = k;
        }
        CanvasNinja.resetTrans(g);
        paintMoney(mgraphics, 0);
        paintMoney(mgraphics, 1);
        paintMoney(mgraphics, 2);
        if (this.isPaintTagIcon) {
            int i68 = 0;
            while (i68 < this.isClickTagIcon.length) {
                CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameTabIconHanhTrang, this.arrX[9], this.yAnimTagIcon[i68] + this.arrY[9] + ((this.arrH[9] + (this.margin * 2)) * i68), this.arrW[9], this.indexTabIconSelected != i68, 0, false);
                FrameImage frameImage10 = LoadDataManager.frameIconTabThoRen[i68];
                if (frameImage10 != null) {
                    int i69 = this.indexTabIconSelected != i68 ? 0 : 1;
                    float f = ((float) this.arrX[9]) + ((((float) this.arrW[9]) - frameImage10.frameWidth) / 2.0f);
                    int i70 = this.margin;
                    int i71 = this.arrY[9] + this.yAnimTagIcon[i68];
                    int i72 = this.arrH[9];
                    frameImage10.drawFrame(i69, f + ((float) i70), (((float) (i71 + (((i70 * 2) + i72) * i68))) + ((((float) i72) - frameImage10.frameHeight) / 2.0f)) - 1.0f, mgraphics);
                }
                i68++;
            }
        }
        PopupItemInfo popupItemInfo2 = this.popupItemInfo;
        if (popupItemInfo2 != null && popupItemInfo2.isPaintPopup) {
            popupItemInfo2.paint(mgraphics, false, true);
        }
        PopupItemInfo popupItemInfo3 = this.popupItemUsedInfo;
        if (popupItemInfo3 != null && popupItemInfo3.isPaintPopup) {
            popupItemInfo3.paint(mgraphics, false, false);
        }
    }

    private boolean isInRangeVecItem(int index) {
        return index < this.vecPaint.size() && index >= 0 && this.vecPaint.get(index) != null;
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

    private int getSumHElement(int numODoc2) {
        return (this.arrH[0] * numODoc2) + ((numODoc2 - 1) * this.margin2);
    }

    public void updateNum(int index, int num) {
        if (num == -1) {
            this.vecPaint.remove(index);
            if (this.indexTabIconSelected == 2) {
                this.vecItemsCanSell.remove(index);
            }
        } else {
            this.vecPaint.get(index).num = num;
        }
        if (this.indexTagNameSelected != 0) {
            this.vecItemTemps.removeAllElements();
            this.vecItemTemps.addAll(this.vecPaint);
            classifyItem((byte) this.indexTagNameSelected);
        }
    }

    public void init(String nameTabShop2, boolean isPaintChar, boolean isPaintTagName2) {
        int i;
        boolean z = isPaintTagName2;
        int i2 = this.indexTabIconSelected;
        if (i2 == 0) {
            this.vecPaint = (Vector) this.vecItems.clone();
        } else if (i2 == 1) {
            this.vecPaint = (Vector) this.vecFixingItems.clone();
        } else if (i2 == 2) {
            this.vecPaint = (Vector) this.vecItemsCanSell.clone();
        }
        this.isPaintTagName = z;
        this.vecItemTemps.addAll(this.vecPaint);
        String[] arrayLangue = SupportTranslate.getArrayLangue("TAG_NAME_INVENTORY");
        this.nameTags = arrayLangue;
        this.yAnimTagName = new int[arrayLangue.length];
        this.isClickTagName = new boolean[arrayLangue.length];
        this.w = 360;
        if (isPaintChar) {
            this.w = 526;
        }
        this.h = 300;
        int i3 = this.w;
        int i4 = CanvasNinja.w;
        if (i3 > i4) {
            this.w = (i4 - (this.margin * 5)) - ((int) LoadDataManager.frameTabIconHanhTrang.frameWidth);
        }
        int i5 = CanvasNinja.h;
        if (300 > i5) {
            this.h = i5 - (this.margin * 2);
        }
        if (isPaintChar) {
            initChar();
            Res.outz("isMe zzzzzzzzzzzzzzzzzzzz:" + this.character.isMe());
            this.numNgangBox = this.numNgangBox + 1;
        }
        initStart();
        int hTagTitleHanhTrang = z ? (int) LoadDataManager.frameTagTitleHanhTrang.frameHeight : this.margin;
        float f = LoadDataManager.frameBoxUsed.frameHeight;
        this.arrH = new int[]{(int) f, (int) f, LoadDataManager.imgCucDat.getRHeight(), 90, 0, (int) LoadDataManager.frameTitle.frameHeight, hTagTitleHanhTrang, ((((this.h - (this.margin7 * 2)) - (this.margin * 2)) - hTagTitleHanhTrang) - 20) - 14, (int) LoadDataManager.frameBoxHanhTrang[0].frameHeight, (int) LoadDataManager.frameTabIconHanhTrang.frameHeight};
        Player player = this.character;
        if (player != null) {
            int i6 = this.margin * 12;
        }
        this.wChar = player != null ? player.cw : 0;
        int wCucDat = LoadDataManager.imgCucDat.getRWidth();
        int wBoxUsed = (int) LoadDataManager.frameBoxUsed.frameWidth;
        if (this.character != null) {
            int i7 = this.margin;
            i = (i7 * 4) + (wBoxUsed * 2) + (10 * 2) + wCucDat + i7 + ((((int) LoadDataManager.framePrevious.frameWidth) + i7) * 2);
        } else {
            i = this.margin7;
        }
        this.wLeft = i;
        int i8 = (this.w - i) - this.margin;
        this.wRight = i8;
        int wKhungXam = (i8 - (LoadDataManager.imgClose.getRWidth() / 2)) - this.margin7;
        int numTab = this.nameTags.length;
        int wTagTitleHanhTrang = Res.fixSizeTagFrameDown(7, (((this.wRight - LoadDataManager.imgClose.getRWidth()) - this.margin7) - (this.margin * numTab)) / numTab, LoadDataManager.frameTagTitleHanhTrang);
        this.nameTabShop = nameTabShop2;
        int width = TabScr.fontPaintTile.getWidth(nameTabShop2.toUpperCase());
        int i9 = this.margin;
        int i10 = width + (i9 * 10);
        int i11 = (int) LoadDataManager.frameBoxHanhTrang[0].frameWidth;
        int[] iArr = {wBoxUsed, wBoxUsed, wCucDat, 22, 0, i10, wTagTitleHanhTrang, wKhungXam, i11, (int) LoadDataManager.frameTabIconHanhTrang.frameWidth};
        this.arrW = iArr;
        int i12 = this.x;
        int xODauTien = this.margin7 + i12 + (i9 * 2);
        int wBoxUsed2 = wBoxUsed;
        int xRight = this.wLeft + i12 + i9 + this.margin3;
        int i13 = this.margin2;
        int i14 = (wKhungXam - i13) / (i13 + i11);
        this.numNgangBox = i14;
        int i15 = (i11 * i14) + ((i14 - 1) * i13);
        this.sumWBoxNgang = i15;
        int i16 = ((int) LoadDataManager.framePrevious.frameWidth) + i9;
        this.xMoreFromNextPreviousDefault = i16;
        this.xMoreFromNextPrevious = i16;
        int i17 = iArr[0];
        int numTab2 = numTab;
        this.arrX = new int[]{xODauTien, xODauTien + i17 + wCucDat + (10 * 2), xODauTien + i17 + 10, xODauTien + i17 + 10 + (wCucDat / 2), xODauTien + i17 + 10 + (wCucDat / 2), (xODauTien + i17) - (i10 / 2), xRight, xRight, xRight + ((wKhungXam - i15) / 2), ((i12 + this.w) - (i9 * 4)) + 1};
        this.numODoc = 4;
        this.maxODoc = 4;
        int sumHElement = getSumHElement(4);
        int i18 = this.y;
        int i19 = this.h;
        int yODauTien = i18 + ((i19 - sumHElement) / 2);
        int[] iArr2 = this.arrH;
        int yCucDat = yODauTien + ((sumHElement - (iArr2[2] + 60)) / 2) + 60;
        int i20 = this.margin;
        int yDauTienPhai = (i20 * 3) + i18 + this.margin3;
        int i21 = iArr2[6];
        MyCommand myCommand = this.cmdClose;
        this.arrY = new int[]{yODauTien, yODauTien, yCucDat, (yCucDat + (iArr2[2] / 2)) - 4, yODauTien - (i20 * 2), i18 - (iArr2[5] / 2), yDauTienPhai, yDauTienPhai + i21 + i20, yDauTienPhai + i21 + i20 + i20, myCommand.y + myCommand.h + (this.margin7 * 2)};
        this.yMoreFromNextPreviousDefault = (i18 + ((i19 - getSumHElement(4)) / 2)) - this.arrY[1];
        int i22 = this.arrX[1] + this.arrW[1] + (this.margin * 2);
        FrameImage frameImage = LoadDataManager.framePrevious;
        float f2 = frameImage.frameWidth;
        float f3 = frameImage.frameHeight;
        this.myNextPrevious = new MyNextPrevious(i22 + ((int) f2), (this.y + (this.h / 2)) - (((int) f3) / 2), (int) f2, (int) f3, 0, 0, 0, true, true);
        if (z) {
            int fontSize = (int) (((float) (this.arrH[6] - 14)) * mGraphics.zoomLevel);
            fontPaintTagName = mFont.fontPaintTagName;
            fontPaintTagNameFocus = mFont.fontPaintTagNameFocus;
        }
        int fontSize2 = this.maxDocBox;
        int[] iArr3 = this.arrH;
        int hMaxPaint = (iArr3[8] * fontSize2) + ((fontSize2 - 1) * this.margin2);
        int i23 = this.arrX[7];
        int i24 = this.arrY[7];
        int i25 = this.arrW[7];
        int i26 = iArr3[7];
        int i27 = i26 - (this.margin * 2);
        int i28 = wBoxUsed2;
        int i29 = i26;
        int i30 = numTab2;
        initScroll(i23, i24, i25, i29, hMaxPaint, i27);
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
        char c;
        BoxInventory box2;
        BoxInventory box3;
        byte b;
        this.cmdClose.updateIconOnly();
        PopupItemInfo popupItemInfo2 = this.popupItemInfo;
        if (popupItemInfo2 != null) {
            popupItemInfo2.updatePointer();
        }
        PopupItemInfo popupItemInfo3 = this.popupItemUsedInfo;
        if (popupItemInfo3 != null) {
            popupItemInfo3.updatePointer();
        }
        char c2 = 0;
        char c3 = 1;
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
                    this.yMoreFromNextPrevious = (this.y + ((this.h - getSumHElement(4)) / 2)) - this.arrY[1];
                    this.startUsed = 8;
                    this.numODoc = 4;
                    this.maxODoc = 8 + 4;
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
                    this.maxODoc = 0 + 4;
                }
                this.popupItemInfo = null;
                this.popupItemUsedInfo = null;
            }
        }
        if (CanvasNinja.isPointerRelease()) {
            if (this.isPaintTagName) {
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
            for (int i8 = 0; i8 < this.isClickTagIcon.length; i8++) {
                int i9 = this.arrX[9];
                int i10 = this.arrY[9];
                int i11 = this.arrH[9];
                if (CanvasNinja.isPoint(i9, i10 + (((this.margin * 2) + i11) * i8), this.arrW[9], i11)) {
                    AudioManager.buttonClick();
                    this.isClickTagIcon[i8] = true;
                    this.indexTabIconSelected = i8;
                    if (i8 == 0) {
                        this.vecPaint = (Vector) this.vecItems.clone();
                    } else if (i8 == 1) {
                        this.vecPaint = (Vector) this.vecFixingItems.clone();
                    } else if (i8 == 2) {
                        this.vecPaint = (Vector) this.vecItemsCanSell.clone();
                    }
                    this.vecItemTemps.removeAllElements();
                    this.vecItemTemps.addAll(this.vecPaint);
                    classifyItem((byte) this.indexTagNameSelected);
                    CanvasNinja.clearAllPointer();
                    this.popupItemInfo = null;
                    this.popupItemUsedInfo = null;
                }
            }
            boolean isCheckPopup = false;
            PopupItemInfo popupItemInfo4 = this.popupItemInfo;
            if (popupItemInfo4 != null) {
                popupItemInfo4.updatePointer();
            }
            PopupItemInfo popupItemInfo5 = this.popupItemUsedInfo;
            if (popupItemInfo5 != null) {
                popupItemInfo5.updatePointer();
            }
            if (CanvasNinja.isPointerRelease()) {
                for (int j = 0; j < this.numNgangBox; j++) {
                    for (int k = 0; k < this.maxDocBox; k++) {
                        int indexItem = (this.numNgangBox * k) + j;
                        int i12 = this.arrX[8];
                        int i13 = this.margin2;
                        int i14 = this.arrW[8];
                        int i15 = this.arrY[8];
                        int i16 = this.arrH[8];
                        if (CanvasNinja.isPoint(i12 + ((i13 + i14) * j), (i15 + ((i13 + i16) * k)) - this.scroll.cmy, i14, i16)) {
                            CanvasNinja.clearAllPointer();
                            this.indexSelected = indexItem;
                            if (!isInRangeVecItem(indexItem)) {
                                continue;
                            } else if (this.indexSelected == this.lastIndexSelected) {
                                this.popupItemInfo = null;
                                this.lastIndexSelected = -1;
                                return;
                            } else {
                                this.popupItemInfo = new PopupItemInfo();
                                this.lastIndexSelected = this.indexSelected;
                                byte type = 10;
                                if (isClickLeft()) {
                                    type = 9;
                                }
                                this.box = new BoxInventory();
                                if (this.vecPaint.get(indexItem).rarity != -1) {
                                    this.box = new BoxInventory(LoadDataManager.frameBoxHanhTrang[this.vecPaint.get(indexItem).rarity], this.vecPaint.get(indexItem));
                                } else {
                                    this.box = new BoxInventory(LoadDataManager.frameBoxHanhTrangDefault, this.vecPaint.get(indexItem));
                                }
                                if (this.vecPaint.get(indexItem).template != null) {
                                    ItemTemplate itt = this.vecPaint.get(indexItem).template;
                                    short s = itt.part;
                                    if (s != -1) {
                                        byte b2 = itt.type;
                                        if (b2 == 1) {
                                            this.character.idPart[3] = s;
                                        }
                                        if (b2 == 0) {
                                            this.character.idPart[0] = s;
                                        }
                                        if (b2 == 2) {
                                            this.character.idPart[1] = s;
                                        }
                                        b = 6;
                                        if (b2 == 6) {
                                            this.character.idPart[5] = s;
                                        }
                                        Player player = this.character;
                                        player.setPart(player.idPart);
                                    } else {
                                        b = 6;
                                    }
                                } else {
                                    b = 6;
                                }
                                int i17 = this.indexTabIconSelected;
                                byte popupType = i17 == 0 ? 2 : i17 == 1 ? b : 7;
                                PopupItemInfo popupItemInfo6 = this.popupItemInfo;
                                int i18 = this.arrX[8];
                                int i19 = this.margin2;
                                int i20 = this.arrW[8];
                                int i21 = i18 + ((i19 + i20) * j) + i20;
                                int i22 = this.arrY[8];
                                int i23 = this.arrH[8];
                                popupItemInfo6.startPopupItemInfo(i21, ((i22 + ((i19 + i23) * k)) + i23) - this.scroll.cmy, type, this.box, popupType);
                                isCheckPopup = true;
                            }
                        }
                    }
                }
            }
            boolean isHavePopupUsed = false;
            if (CanvasNinja.isPointerRelease()) {
                int index = 0;
                int i24 = this.startUsed;
                while (i24 < this.startUsed + 4) {
                    int i25 = this.arrX[c2] + this.xMoreFromNextPrevious;
                    int i26 = this.arrY[c2];
                    int i27 = this.arrH[c2];
                    if (CanvasNinja.isPoint(i25, i26 + ((this.margin2 + i27) * index) + this.yMoreFromNextPrevious, this.arrW[c2], i27)) {
                        this.indexSelectedUsed = i24;
                        byte alignY = 4;
                        int i28 = this.startUsed;
                        if (i24 == i28) {
                            alignY = 5;
                        } else if (i24 == i28 + 3) {
                            alignY = 6;
                        }
                        CanvasNinja.clearAllPointer();
                        if (isInRangeVecItemUsed(this.indexSelectedUsed)) {
                            if (this.indexSelectedUsed == this.lastIndexSelectedUsed) {
                                this.popupItemUsedInfo = null;
                                this.lastIndexSelectedUsed = -1;
                                return;
                            }
                            this.popupItemInfo = null;
                            this.popupItemUsedInfo = new PopupItemInfo();
                            this.lastIndexSelectedUsed = this.indexSelectedUsed;
                            new BoxInventory();
                            if (this.vecItemUsedInvens.get(this.indexSelectedUsed).rarity != -1) {
                                box3 = new BoxInventory(LoadDataManager.frameBoxHanhTrang[this.vecItemUsedInvens.get(this.indexSelectedUsed).rarity], this.vecItemUsedInvens.get(this.indexSelectedUsed));
                            } else {
                                box3 = new BoxInventory(LoadDataManager.frameBoxHanhTrangDefault, this.vecItemUsedInvens.get(this.indexSelectedUsed));
                            }
                            if (this.vecItemUsedInvens.get(this.indexSelectedUsed).template != null && !this.vecItemUsedInvens.get(this.indexSelectedUsed).isDisableClick()) {
                                PopupItemInfo popupItemInfo7 = this.popupItemUsedInfo;
                                int i29 = this.arrX[c2] + this.arrW[c2] + this.xMoreFromNextPrevious;
                                int i30 = this.arrY[c2];
                                int i31 = this.arrH[c2];
                                popupItemInfo7.startPopupItemInfo(i29, i30 + ((this.margin2 + i31) * index) + (i31 / 2) + this.yMoreFromNextPrevious, alignY, box3, (byte) 1);
                            }
                            isHavePopupUsed = true;
                            isCheckPopup = true;
                        }
                    }
                    int i32 = this.arrX[c3] + this.xMoreFromNextPrevious;
                    int i33 = this.arrY[c3];
                    int i34 = this.arrH[c3];
                    if (CanvasNinja.isPoint(i32, i33 + ((this.margin2 + i34) * index) + this.yMoreFromNextPrevious, this.arrW[c3], i34)) {
                        this.indexSelectedUsed = i24 + 4;
                        byte alignY2 = 4;
                        int i35 = this.startUsed;
                        if (i24 == i35) {
                            alignY2 = 5;
                        } else if (i24 == i35 + 3) {
                            alignY2 = 6;
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
                            if (this.vecItemUsedInvens.get(this.indexSelectedUsed).template == null) {
                                c = 0;
                            } else if (!this.vecItemUsedInvens.get(this.indexSelectedUsed).isDisableClick()) {
                                PopupItemInfo popupItemInfo8 = this.popupItemUsedInfo;
                                int i36 = this.arrX[c3] + this.arrW[c3] + this.xMoreFromNextPrevious;
                                int i37 = this.arrY[c3];
                                int[] iArr = this.arrH;
                                c = 0;
                                popupItemInfo8.startPopupItemInfo(i36, i37 + ((iArr[c3] + this.margin2) * index) + (iArr[0] / 2) + this.yMoreFromNextPrevious, alignY2, box2, (byte) 1);
                            } else {
                                c = 0;
                            }
                            isHavePopupUsed = true;
                            isCheckPopup = true;
                        } else {
                            c = 0;
                        }
                    } else {
                        c = 0;
                    }
                    index++;
                    i24++;
                    c2 = c;
                    c3 = 1;
                }
            }
            if (!isHavePopupUsed) {
                this.popupItemUsedInfo = null;
            }
            this.scroll.isHavePopup = isCheckPopup;
        }
    }

    private void initItemByType(short[] type) {
        for (int i = 0; i < this.vecItemTemps.size(); i++) {
            if (this.vecItemTemps.get(i).template != null && Res.checkNumInArr((short) this.vecItemTemps.get(i).template.type, type)) {
                this.vecPaint.add(this.vecItemTemps.get(i));
            }
        }
    }

    public void classifyItem(byte type) {
        this.vecPaint.removeAllElements();
        switch (type) {
            case 0:
                this.vecPaint.addAll(this.vecItemTemps);
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

    public void startTabShop(String nameTabShop2, boolean isPaintChar, boolean isPaintTagName2) {
        init(nameTabShop2, isPaintChar, isPaintTagName2);
        showTab();
        me = this;
    }

    public void startTabShop(String nameTabShop2, boolean isPaintChar, boolean isPaintTagName2, boolean isPaintTagIcon2) {
        init(nameTabShop2, isPaintChar, isPaintTagName2);
        this.isPaintTagIcon = true;
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
        gI().updateNum(0, 10);
    }

    public void keyTyped() {
    }

    public void commandTab(int index, int subIndex) {
        super.commandTab(index, subIndex);
        switch (index) {
            case 20:
                if (this.box != null) {
                    SubTabBuyShop gI = SubTabBuyShop.gI();
                    BoxInventory boxInventory = this.box;
                    gI.startSubTabBuyShop(boxInventory, boxInventory.item.typeMoneyServer, (CanvasNinja.w * 50) / 100, (this.h * 70) / 100);
                    SubTabBuyShop.gI().idNpc = this.idNpc;
                    return;
                }
                return;
            case 70:
                if (this.box != null && this.indexTabIconSelected == 2) {
                    SubTabBuyShop gI2 = SubTabBuyShop.gI();
                    BoxInventory boxInventory2 = this.box;
                    gI2.startSubTabBuyShop(boxInventory2, boxInventory2.item.typeMoneyServer, (CanvasNinja.w * 50) / 100, (this.h * 70) / 100);
                    SubTabBuyShop.gI().idNpc = this.idNpc;
                    return;
                }
                return;
            default:
                return;
        }
    }

    private boolean isClickLeft() {
        return CanvasNinja.isPoint(this.arrX[7], this.arrY[7], this.arrW[7] / 2, this.arrH[7]);
    }

    public void show() {
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
