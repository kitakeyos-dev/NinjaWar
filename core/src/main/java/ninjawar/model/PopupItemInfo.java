package ninjawar.model;

import java.util.Iterator;
import java.util.Vector;
import ninjawar.input.MyButton;
import ninjawar.mylib.AudioManager;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.object.Item;
import ninjawar.screen.subtab.SubTabInven;
import ninjawar.screen.tab.TabInventory;
import ninjawar.screen.tab.TabShop;
import ninjawar.screen.tab.TabWareHouse;
import ninjawar.scroll.ScrollY;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportTranslate;
import ninjawar.template.ItemTemplate;

public class PopupItemInfo {
    public static mFont fontPaintNameItem = mFont.bigNumber_While;
    public static boolean isOtherCharInfo = false;
    int[] arrH;
    int[] arrW;
    int[] arrX;
    int[] arrY;
    public BoxInventory box;
    public MyButton[] btns;
    int count = 0;
    int h;
    int hBgBtn;
    public boolean isPaintNext = false;
    public boolean isPaintNormal = false;
    public boolean isPaintPopup = false;
    boolean isUseScroll = false;
    int margin = 5;
    int marginLimit = 5;
    public int marginText = 5;
    int rBottom;
    int rLeft = 30;
    int rRight = 30;
    int rTop;
    ScrollY scorllY = new ScrollY();
    Star star;
    String tmpItemName;
    byte type = 0;
    Vector<FontPaintDetails> vecFontPaints = new Vector<>();
    Vector<String> vecStrings = new Vector<>();
    int w;
    int wBgBtn;
    int wPop = 8;
    int x;
    int xBgBtn;
    int xCenter;
    int y;
    public int yAnim = 0;
    int yBgBtn;
    int yCenter;

    public void startPopupItemInfo(int x2, int y2, byte type2, BoxInventory box2, byte typeUsed) {
        init(x2, y2, type2, box2, typeUsed);
    }

    private void init(int x2, int y2, byte type2, BoxInventory box2, byte typeUsed) {
        mItemOption[] mitemoptionArr;
        Vector<String> vecStringTemp;
        int i = x2;
        int i2 = y2;
        byte b = type2;
        BoxInventory boxInventory = box2;
        byte b2 = typeUsed;
        AudioManager.buttonClick();
        this.type = b;
        this.x = i;
        this.y = i2;
        this.box = boxInventory;
        this.arrH = new int[]{(int) boxInventory.boxFrame.frameHeight, fontPaintNameItem.getHeight(), fontPaintNameItem.getHeight(), fontPaintNameItem.getHeight(), LoadDataManager.imgDotInfo.getRHeight(), 0};
        this.vecStrings.removeAllElements();
        this.vecStrings.add(boxInventory.item.getRealName());
        this.vecStrings.add("");
        this.vecStrings.add(SupportTranslate.getTextLangue("lv") + ": " + boxInventory.item.lvRequired);
        this.vecFontPaints.removeAllElements();
        this.vecFontPaints.add(new FontPaintDetails(boxInventory.item.getFontByRarity(), boxInventory.item.getRealName()));
        this.vecFontPaints.add(new FontPaintDetails((byte) 0, ""));
        this.vecFontPaints.add(new FontPaintDetails(boxInventory.item.lvRequired <= Player.myCharz().clevel ? (byte) 0 : 2, "" + boxInventory.item.lvRequired));
        Vector<String> vecStringTemp2 = new Vector<>();
        vecStringTemp2.addAll(this.vecStrings);
        String max = Res.findStringMax(vecStringTemp2);
        Item item = boxInventory.item;
        if (item != null) {
            this.star = new Star(item.levelRefined);
        }
        Vector<String> vecBtns = new Vector<>();
        if (b2 == 0) {
            vecBtns.add(SupportTranslate.getTextLangue("equip"));
            vecBtns.add(SupportTranslate.getTextLangue("deleteItem"));
        } else if (b2 == 1) {
            vecBtns.add(SupportTranslate.getTextLangue("remove"));
        } else if (b2 == 2) {
            vecBtns.add(SupportTranslate.getTextLangue("BUY"));
        } else if (b2 == 3) {
            vecBtns.add(SupportTranslate.getTextLangue("GETIN"));
            vecBtns.add(SupportTranslate.getTextLangue("GETIN_MORE"));
        } else if (b2 == 4) {
            vecBtns.add(SupportTranslate.getTextLangue("GETOUT"));
            vecBtns.add(SupportTranslate.getTextLangue("GETOUT_MORE"));
        } else if (b2 == 5) {
            vecBtns.add(SupportTranslate.getTextLangue("SELECT"));
            vecBtns.add(SupportTranslate.getTextLangue("BACK"));
        } else if (b2 == 6) {
            vecBtns.add(SupportTranslate.getTextLangue("FIX"));
            vecBtns.add(SupportTranslate.getTextLangue("restoration"));
        } else if (b2 == 7) {
            vecBtns.add("Bán");
        }
        int wBtn = MyButton.FONT_DEFAULT.getWidth(Res.findStringMax(vecBtns)) + 15;
        if (wBtn < 50) {
            wBtn = 50;
        }
        this.btns = new MyButton[vecBtns.size()];
        int startCmd = 2;
        if (b2 > 0) {
            startCmd = b2 * 10;
        }
        int i3 = 0;
        while (i3 < vecBtns.size()) {
            if (b2 == 5) {
                MyButton[] myButtonArr = this.btns;
                FrameImage[] frameImageArr = LoadDataManager.myButtonSmalls;
                myButtonArr[i3] = new MyButton(frameImageArr[i3 - (i3 / 4)], frameImageArr[i3 - (i3 / 4)], wBtn, 0, vecBtns.get(i3), 0, 0, new MyCommand("", startCmd, SubTabInven.gI()));
                vecStringTemp = vecStringTemp2;
            } else if (b2 == 2) {
                MyButton[] myButtonArr2 = this.btns;
                FrameImage[] frameImageArr2 = LoadDataManager.myButtonSmalls;
                vecStringTemp = vecStringTemp2;
                myButtonArr2[i3] = new MyButton(frameImageArr2[i3 - (i3 / 4)], frameImageArr2[i3 - (i3 / 4)], wBtn, 0, vecBtns.get(i3), 0, 0, new MyCommand("", startCmd, TabShop.gI()));
            } else {
                vecStringTemp = vecStringTemp2;
                if (b2 == 7) {
                    MyButton[] myButtonArr3 = this.btns;
                    FrameImage[] frameImageArr3 = LoadDataManager.myButtonSmalls;
                    myButtonArr3[i3] = new MyButton(frameImageArr3[i3 - (i3 / 4)], frameImageArr3[i3 - (i3 / 4)], wBtn, 0, vecBtns.get(i3), 0, 0, new MyCommand("", startCmd, TabShop.gI()));
                } else if (b2 == 3 || b2 == 4) {
                    MyButton[] myButtonArr4 = this.btns;
                    FrameImage[] frameImageArr4 = LoadDataManager.myButtonSmalls;
                    myButtonArr4[i3] = new MyButton(frameImageArr4[i3 - (i3 / 4)], frameImageArr4[i3 - (i3 / 4)], wBtn, 0, vecBtns.get(i3), 0, 0, new MyCommand("", startCmd, TabWareHouse.gI()));
                } else {
                    MyButton[] myButtonArr5 = this.btns;
                    FrameImage[] frameImageArr5 = LoadDataManager.myButtonSmalls;
                    myButtonArr5[i3] = new MyButton(frameImageArr5[i3 - (i3 / 4)], frameImageArr5[i3 - (i3 / 4)], wBtn, 0, vecBtns.get(i3), 0, 0, new MyCommand("", startCmd, TabInventory.gI()));
                }
            }
            startCmd++;
            i3++;
            vecStringTemp2 = vecStringTemp;
        }
        MyButton[] myButtonArr6 = this.btns;
        int length = myButtonArr6.length;
        MyButton myButton = myButtonArr6[0];
        int i4 = length * myButton.w;
        int length2 = myButtonArr6.length;
        int i5 = this.margin;
        this.wBgBtn = i4 + (length2 * i5) + i5;
        this.hBgBtn = myButton.h + (i5 * 2);
        Star star2 = this.star;
        int wStar = star2 != null ? star2.getWStar() : 0;
        int wTextMaxLeft = fontPaintNameItem.getWidth(max) > wStar ? fontPaintNameItem.getWidth(max) : wStar;
        this.arrW = new int[]{(int) boxInventory.boxFrame.frameWidth, wTextMaxLeft, wTextMaxLeft, wTextMaxLeft, LoadDataManager.imgDotInfo.getRWidth(), 0};
        int i6 = this.w;
        int i7 = this.wBgBtn;
        if (i6 < i7) {
            this.w = i7;
        }
        int wMin = (this.margin * 4) + LoadDataManager.imgDotInfo.getRWidth();
        if (this.w < wMin) {
            this.w = wMin;
        }
        if (boxInventory.item.mItemOption != null) {
            int i8 = 0;
            while (true) {
                mItemOption[] mitemoptionArr2 = boxInventory.item.mItemOption;
                if (i8 >= mitemoptionArr2.length) {
                    break;
                }
                if (mitemoptionArr2[i8].itemOption != null) {
                    int k = 0;
                    while (true) {
                        ItemOption[] itemOptionArr = boxInventory.item.mItemOption[i8].itemOption;
                        if (k >= itemOptionArr.length) {
                            break;
                        }
                        itemOptionArr[k].getDetailtext(this.w - (this.margin * 4));
                        k++;
                        byte b3 = typeUsed;
                        wStar = wStar;
                    }
                }
                i8++;
                byte b4 = typeUsed;
                wStar = wStar;
            }
        }
        int hLoai = (fontPaintNameItem.getHeight() * 3) + (this.margin * 3);
        Item item2 = boxInventory.item;
        if (item2.maxDurability == -1 && item2.durability == -1) {
            hLoai = (fontPaintNameItem.getHeight() * 2) + (this.margin * 2);
        }
        int sumHTemp = hLoai;
        int i9 = 0;
        while (true) {
            mitemoptionArr = boxInventory.item.mItemOption;
            if (i9 >= mitemoptionArr.length) {
                break;
            }
            sumHTemp += mitemoptionArr[i9].setH(fontPaintNameItem, this.margin);
            i9++;
            max = max;
        }
        int sumHBottom = sumHTemp;
        if (mitemoptionArr.length == 1) {
            sumHBottom = LoadDataManager.imgDotInfo.getRHeight() + sumHTemp + this.margin;
        } else if (mitemoptionArr.length > 1) {
            sumHBottom = sumHTemp + ((mitemoptionArr.length - 1) * (LoadDataManager.imgDotInfo.getRHeight() + this.margin));
        }
        int i10 = this.margin;
        int realH = (i10 * 2) + ((int) boxInventory.boxFrame.frameHeight) + sumHBottom;
        int[] iArr = this.arrH;
        iArr[5] = sumHBottom;
        this.h = realH;
        int i11 = sumHTemp;
        int i12 = sumHBottom;
        Vector<String> vector = vecBtns;
        int[] iArr2 = this.arrW;
        int i13 = iArr2[0];
        int i14 = this.w;
        this.arrX = new int[]{i + i10, (i10 * 2) + i + i13, (i10 * 2) + i + i13, (i10 * 2) + i + i13, i + ((i14 - iArr2[4]) / 2), i + i10};
        int i15 = iArr[1];
        int i16 = iArr[0];
        this.arrY = new int[]{i2 + i10, i2 + i10, i2 + (i10 * 2) + i15, i2 + (i10 * 3) + i15 + iArr[2], i2 + i16 + (i10 * 2) + hLoai, i2 + i16 + (i10 * 3) + iArr[4] + hLoai, i2 + i16 + (i10 * 2)};
        this.yBgBtn = i2;
        int i17 = realH / 4;
        this.rBottom = i17;
        this.rTop = i17;
        int wBox = iArr2[0];
        switch (b) {
            case 0:
                this.xCenter = -(i14 / 2);
                this.yCenter = 0;
                this.xBgBtn = i + i14 + i10;
                break;
            case 1:
                this.yCenter = 0;
                if (i < this.rLeft) {
                    this.rLeft = i - this.marginLimit;
                }
                this.xCenter = -this.rLeft;
                this.xBgBtn = i + i14 + i10;
                break;
            case 2:
                this.yCenter = 0;
                int i18 = CanvasNinja.w;
                if (i18 - i < this.rRight) {
                    this.rRight = (i18 - i) - this.marginLimit;
                }
                this.xCenter = -(i14 - this.rRight);
                this.xBgBtn = (i - i10) - this.wBgBtn;
                break;
            case 3:
                this.xCenter = 0;
                this.yCenter = 0;
                this.xBgBtn = i + i14 + i10;
                break;
            case 4:
                this.yCenter = -(realH / 2);
                this.xCenter = 0;
                this.xBgBtn = i + i14 + i10;
                break;
            case 5:
                this.xCenter = 0;
                this.yCenter = -i17;
                this.xBgBtn = i + i14 + i10;
                break;
            case 6:
                this.yCenter = i17 * -3;
                this.xBgBtn = i + i14 + i10;
                break;
            case 7:
                int i19 = CanvasNinja.w;
                int i20 = hLoai;
                if (i19 - i < this.rRight) {
                    this.rRight = (i19 - i) - this.marginLimit;
                }
                int i21 = this.wBgBtn;
                this.xBgBtn = (i - i10) - i21;
                this.xCenter = -((i14 - this.rRight) + i21);
                this.yCenter = -i17;
                break;
            case 8:
                this.yCenter = i17 * -3;
                int i22 = CanvasNinja.w;
                if (i22 - i < this.rRight) {
                    this.rRight = (i22 - i) - this.marginLimit;
                }
                int i23 = this.wBgBtn;
                this.xBgBtn = (i - i10) - i23;
                this.xCenter = -((i14 - this.rRight) + i23);
                int i24 = hLoai;
                break;
            case 9:
                this.yCenter = -(realH / 2);
                this.xCenter = 0;
                this.xBgBtn = i + i14 + i10;
                int i25 = hLoai;
                break;
            case 10:
                this.yCenter = -(realH / 2);
                this.xCenter = (-i14) - wBox;
                this.xBgBtn = i + i14 + i10;
                int i26 = hLoai;
                break;
            default:
                int i27 = hLoai;
                break;
        }
        int i28 = this.xCenter;
        int finalX = i + i28;
        int i29 = this.yCenter;
        int finalY = i2 + i29;
        if (finalX < 0) {
            this.xCenter = i28 + (-finalX);
            int i30 = wBox;
        } else {
            int i31 = finalX + i14;
            int i32 = wBox;
            int wBox2 = CanvasNinja.w;
            if (i31 > wBox2) {
                this.xCenter = i28 - ((i14 + finalX) - wBox2);
            }
        }
        if (finalY < 0) {
            this.yCenter = i29 + (-finalY);
        } else {
            int i33 = this.hBgBtn;
            int i34 = finalY + realH + i33;
            int i35 = CanvasNinja.h;
            if (i34 > i35) {
                this.yCenter = i29 - (((finalY + realH) + i33) - i35);
            }
        }
        int i36 = this.xCenter;
        int finalX2 = i + i36;
        int i37 = this.yCenter;
        int i38 = i2 + i37;
        this.xBgBtn = i36 + i;
        this.yBgBtn = i2 + i37 + realH;
        int i39 = 0;
        while (true) {
            MyButton[] myButtonArr7 = this.btns;
            if (i39 < myButtonArr7.length) {
                if (i39 == 0) {
                    MyButton myButton2 = myButtonArr7[i39];
                    int i40 = this.xBgBtn;
                    int i41 = this.margin;
                    myButton2.setXY(i40 + i41, this.yBgBtn + i41);
                } else {
                    MyButton myButton3 = myButtonArr7[i39];
                    int i42 = this.xBgBtn;
                    int i43 = this.margin;
                    myButton3.setXY(i42 + i43 + i43 + myButtonArr7[i39 - 1].w, this.yBgBtn + i43);
                }
                i39++;
            } else {
                this.isPaintPopup = true;
                this.tmpItemName = " " + boxInventory.item.getRealName().trim() + "     ";
                return;
            }
        }
    }

    public void updateNext() {
        ScrollY scrollY = this.scorllY;
        if (scrollY.cmy == scrollY.cmyLim) {
            this.isPaintNext = false;
        } else {
            this.isPaintNext = true;
        }
        if (this.isPaintNext && CanvasNinja.gameTick % 3 == 0) {
            int i = this.yAnim - 1;
            this.yAnim = i;
            if (i < -2) {
                this.yAnim = 0;
            }
        }
    }

    public String scrollText(String str) {
        if (str.isEmpty()) {
            return "";
        }
        return str.substring(1) + str.charAt(0);
    }

    public void update() {
        String str = this.tmpItemName;
        if (str.substring(str.length() - 5).equals("     ") && this.tmpItemName.charAt(0) == ' ') {
            this.count++;
        }
        if (this.count > 5) {
            this.count = 0;
        }
        if (this.count == 0 && CanvasNinja.gameTick % 5 == 0) {
            this.tmpItemName = scrollText(this.tmpItemName);
        }
        if (this.isUseScroll) {
            this.scorllY.update();
            if (CanvasNinja.isScrollWhell) {
                this.scorllY.cmy = CanvasNinja.cmy;
            }
        }
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.update();
            }
        }
        updateNext();
    }

    public void updatePointer() {
        MyButton[] myButtonArr;
        if (CanvasNinja.isPointerRelease && (myButtonArr = this.btns) != null) {
            for (MyButton btn : myButtonArr) {
                btn.updatePointer();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String initMaxWidthText(int mWidth, String txt) {
        String paintedText = "";
        for (int i = 0; i < txt.length(); i++) {
            paintedText = paintedText + txt.charAt(i);
            if (mFont.tahoma_7_white.getWidth(paintedText) > mWidth) {
                return paintedText;
            }
        }
        return paintedText;
    }

    public void paint(mGraphics g, boolean useClip, boolean isPaintNum) {
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(LoadDataManager.frameBgInfo, (float) (this.x + this.xCenter), (float) (this.y + this.yCenter), (float) this.w, (float) this.h, 100, useClip, g);
        this.box.boxFrame.drawFrame(0, this.arrX[0] + this.xCenter, this.arrY[0] + this.yCenter, useClip, g);
        BoxInventory boxInventory = this.box;
        Item item = boxInventory.item;
        FrameImage frameImage = boxInventory.boxFrame;
        item.paintCenter(g, this.arrX[0] + this.xCenter, this.arrY[0] + this.yCenter, (int) frameImage.frameWidth, (int) frameImage.frameHeight, useClip, isPaintNum);
        boolean isHaveStar = this.star != null;
        Iterator<FontPaintDetails> it = this.vecFontPaints.iterator();
        int o = 1;
        while (it.hasNext()) {
            FontPaintDetails item2 = it.next();
            if (o == 2) {
                if (isHaveStar) {
                    this.star.paint(mgraphics, this.arrX[o] + this.xCenter, this.arrY[o] + this.yCenter);
                    o++;
                } else {
                    fontPaintNameItem.drawString(mgraphics, SupportTranslate.getTextLangue("lv") + ": ", this.arrX[o] + this.xCenter, this.arrY[o] + this.yCenter + fontPaintNameItem.getHeightH(item2.text));
                    mFont.tahoma_7_info_do.drawString(mgraphics, item2.text, this.arrX[o] + this.xCenter + item2.fontPaint.getWidth(SupportTranslate.getTextLangue("lv") + ": "), this.arrY[o] + this.yCenter + fontPaintNameItem.getHeightH(item2.text));
                }
            } else if (!isHaveStar || o != 3) {
                if (mFont.tahoma_7_white.getWidth(item2.text) <= 90) {
                    mFont.tahoma_7_info_xanh_la.drawString(g, item2.text, this.xCenter + this.arrX[o], this.yCenter + this.arrY[o], 0);
                } else {
                    mFont.tahoma_7_info_xanh_la.drawString(g, initMaxWidthText(80, this.tmpItemName), this.xCenter + this.arrX[o], this.yCenter + this.arrY[o], 0);
                }
                o++;
            } else {
                fontPaintNameItem.drawString(mgraphics, SupportTranslate.getTextLangue("lv") + ": ", this.arrX[o] + this.xCenter, this.arrY[o] + this.yCenter);
                mFont.tahoma_7_info_do.drawString(mgraphics, item2.text, this.arrX[o] + this.xCenter + item2.fontPaint.getWidth(SupportTranslate.getTextLangue("lv") + ": "), this.arrY[o] + this.yCenter);
            }
        }
        fontPaintNameItem.drawString(mgraphics, SupportTranslate.getTextLangue("type") + ": ", this.arrX[5] + this.xCenter + this.margin, this.arrY[6] + this.yCenter);
        mFont.tahoma_7_info_xanh_la.drawString(mgraphics, this.box.item.template.typeItemtemplate.nameType, this.arrX[5] + this.xCenter + this.margin + fontPaintNameItem.getWidth(SupportTranslate.getTextLangue("type") + ": "), this.arrY[6] + this.yCenter);
        ItemTemplate itemTemplate = this.box.item.template;
        fontPaintNameItem.drawString(mgraphics, SupportTranslate.getTextLangue("intendedFor"), this.arrX[5] + this.xCenter + this.margin, this.arrY[6] + fontPaintNameItem.getHeight() + 5 + this.yCenter);
        this.box.item.getFontByHe().drawString(mgraphics, SupportTranslate.getArrayLangue("nameTocHe")[this.box.item.sys], this.arrX[5] + this.margin + this.xCenter + fontPaintNameItem.getWidth(SupportTranslate.getTextLangue("intendedFor") + ": "), this.arrY[6] + this.yCenter + fontPaintNameItem.getHeight() + 5);
        Item item3 = this.box.item;
        if (item3.maxDurability != -1) {
            if (item3.template.type == 33) {
                fontPaintNameItem.drawString(mgraphics, "Độ trung thành: " + this.box.item.durability + "/" + this.box.item.maxDurability, this.arrX[5] + this.xCenter + this.margin, this.arrY[6] + (fontPaintNameItem.getHeight() * 2) + 10 + this.yCenter);
            } else {
                mFont mfont = fontPaintNameItem;
                mfont.drawString(mgraphics, "Độ bền: ", this.arrX[5] + this.xCenter + this.margin, this.arrY[6] + (mfont.getHeight() * 2) + 10 + this.yCenter);
                mFont.tahoma_7_info_xanh_la.drawString(mgraphics, this.box.item.durability + "/" + this.box.item.maxDurability, this.arrX[5] + this.xCenter + this.margin + fontPaintNameItem.getWidth("Độ bền: "), this.arrY[6] + (fontPaintNameItem.getHeight() * 2) + 10 + this.yCenter);
            }
        }
        int hBefore = 0;
        for (int i = 0; i < this.box.item.mItemOption.length; i++) {
            mgraphics.drawImage(LoadDataManager.imgDotInfo, (float) (this.arrX[4] + this.xCenter), (float) (((this.arrY[5] + hBefore) + this.yCenter) - 4));
            int k = 0;
            while (true) {
                ItemOption[] itemOptionArr = this.box.item.mItemOption[i].itemOption;
                if (k >= itemOptionArr.length) {
                    break;
                }
                itemOptionArr[k].paint(mgraphics, this.arrX[5] + this.margin + this.xCenter, this.arrY[5] + hBefore + this.yCenter);
                hBefore += this.box.item.mItemOption[i].itemOption[k].h;
                k++;
            }
        }
        if (!isOtherCharInfo) {
            CanvasNinja.paintz.paintSingleBorderFrame(LoadDataManager.frameBgInfo, (float) this.xBgBtn, (float) this.yBgBtn, (float) this.wBgBtn, (float) this.hBgBtn, 100, useClip, g);
            for (MyButton btn : this.btns) {
                btn.paint(mgraphics);
            }
        }
    }
}
