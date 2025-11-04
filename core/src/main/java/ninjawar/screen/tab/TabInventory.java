package ninjawar.screen.tab;

import java.util.Vector;
import ninjawar.input.KEY;
import ninjawar.input.MyButton;
import ninjawar.input.MyNextPrevious;
import ninjawar.message.Config;
import ninjawar.message.SendMessage;
import ninjawar.model.BoxInventory;
import ninjawar.model.ItemOption;
import ninjawar.model.MyCommand;
import ninjawar.model.PlayerInfo;
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
import ninjawar.screen.dialog.InputDialog;
import ninjawar.scroll.ScrollY;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

public class TabInventory extends TabScr {
    public static mFont fontPaintCharInfo = mFont.fontPaintCharInfo;
    public static mFont fontPaintCharInfo2 = mFont.fontPaintCharInfo2;
    public static mFont fontPaintCharInfoFocus = mFont.fontPaintCharInfoFocus;
    public static mFont fontPaintMoney = mFont.fontPaintMoneyInven;
    public static mFont fontPaintNameChar = mFont.tahoma_name_char_inventory;
    public static mFont fontPaintTagName = mFont.tahoma_brown_dv;
    public static mFont fontPaintTagNameFocus = mFont.tahoma_brown_focus_dv;
    public static TabInventory me;
    int[] arrXPosCong;
    int[] arrXPosTru;
    int[] arrYPosCong;
    int[] arrYPosTru;
    MyButton btnConfirm;
    public int capacity = 100;
    public Player character;
    public int[] diemTiemNang;
    boolean flagConfirm;
    mFont fontPaintTitle;
    public int indexSelectedInven = -1;
    public int indexSelectedUsed = -1;
    public int indexTabIconSelected;
    public int indexTagNameSelected;
    boolean[] isClickTagIcon;
    boolean[] isClickTagName = new boolean[4];
    boolean isHavePopup;
    public int lastIndexSelectedInven = -1;
    public int lastIndexSelectedUsed = -1;
    int lastYScroll;
    int lineSpacing;
    public int marginBottomMoney;
    public int marginMoney;
    int maxDocBox = 20;
    int maxODoc;
    public short maxSlot = 30;
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
    String[] strPaints;
    int sumChange;
    int sumWBoxNgang;
    int sumWMoney;
    int tabInfo;
    int tiemNangIndex;
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

    public TabInventory() {
        int length = LoadDataManager.frameIconTabHanhTrangs.length;
        this.numTag = length;
        this.yAnimTagIcon = new int[length];
        this.isClickTagIcon = new boolean[length];
        this.arrXPosTru = new int[5];
        this.arrYPosTru = new int[5];
        this.arrXPosCong = new int[5];
        this.arrYPosCong = new int[5];
        this.diemTiemNang = new int[5];
        this.lastYScroll = 0;
        this.fontPaintTitle = fontPaintTagName.cloneFontColor(-1302582785);
        this.lineSpacing = fontPaintCharInfo.getHeight() + this.margin;
        this.marginMoney = 10;
        this.marginBottomMoney = 5;
        this.sumWMoney = 0;
        this.isHavePopup = false;
        this.sumChange = 0;
        this.statChange = new int[]{0, 0, 0, 0, 0};
        this.flagConfirm = false;
    }

    public static TabInventory gI() {
        if (me == null) {
            me = new TabInventory();
        }
        return me;
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        mgraphics.translate(this.xMoreFromNextPrevious, 0);
        int i = 2;
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
        int i2 = this.startUsed;
        for (int i3 = 4; i2 < this.startUsed + i3; i3 = 4) {
            SupportPaint supportPaint = CanvasNinja.paintz;
            FrameImage frameImage = LoadDataManager.frameBoxUsed;
            int i4 = this.arrX[0];
            int i5 = this.yMoreFromNextPrevious + this.arrY[0] + ((this.arrH[0] + this.margin2) * index);
            int i6 = this.arrW[0];
            int i7 = this.indexSelectedUsed;
            boolean z = true;
            supportPaint.paintTagFrame(g, frameImage, i4, i5, i6, i7 != -1 && i7 == i2, 0, false);
            if (isInRangeVecItemUsed(i2)) {
                Item item = this.vecItemUsedInvens.get(i2);
                int i8 = this.arrX[0];
                int i9 = this.arrY[0];
                int i10 = this.arrH[0];
                item.paintCenter(g, i8, this.yMoreFromNextPrevious + i9 + ((this.margin2 + i10) * index), this.arrW[0], i10, false, this.vecItemUsedInvens.get(i2).num > 1);
            } else {
                Image[] imageArr = LoadDataManager.imgIconHide;
                Image image = imageArr[i2 % imageArr.length];
                float rWidth = (float) ((this.arrX[0] + (this.arrW[0] / i)) - (imageArr[i2 % imageArr.length].getRWidth() / i));
                int i11 = this.arrY[0];
                int i12 = this.arrH[0];
                int i13 = i11 + ((this.margin2 + i12) * index) + (i12 / i) + this.yMoreFromNextPrevious;
                Image[] imageArr2 = LoadDataManager.imgIconHide;
                mgraphics.drawImage(image, rWidth, (float) (i13 - (imageArr2[i2 % imageArr2.length].getRHeight() / i)));
            }
            int rightIndex = i2 + 4;
            SupportPaint supportPaint2 = CanvasNinja.paintz;
            FrameImage frameImage2 = LoadDataManager.frameBoxUsed;
            int i14 = this.arrX[1];
            int i15 = this.yMoreFromNextPrevious + this.arrY[1] + ((this.arrH[0] + this.margin2) * index);
            int i16 = this.arrW[1];
            int i17 = this.indexSelectedUsed;
            int rightIndex2 = rightIndex;
            supportPaint2.paintTagFrame(g, frameImage2, i14, i15, i16, i17 != -1 && i17 == rightIndex, 0, false);
            if (isInRangeVecItemUsed(rightIndex2)) {
                Item item2 = this.vecItemUsedInvens.get(rightIndex2);
                int i18 = this.arrX[1];
                int i19 = this.arrY[1];
                int[] iArr = this.arrH;
                int i20 = this.yMoreFromNextPrevious + i19 + ((iArr[0] + this.margin2) * index);
                int i21 = this.arrW[1];
                int i22 = iArr[1];
                if (this.vecItemUsedInvens.get(rightIndex2).num <= 1) {
                    z = false;
                }
                item2.paintCenter(g, i18, i20, i21, i22, false, z);
            } else {
                Image[] imageArr3 = LoadDataManager.imgIconHide;
                if (imageArr3[rightIndex2 % imageArr3.length] != null) {
                    Image image2 = imageArr3[rightIndex2 % imageArr3.length];
                    float rWidth2 = (float) ((this.arrX[1] + (this.arrW[1] / 2)) - (imageArr3[rightIndex2 % imageArr3.length].getRWidth() / 2));
                    int i23 = this.arrY[1];
                    int i24 = this.arrH[0];
                    int i25 = i23 + ((this.margin2 + i24) * index) + (i24 / 2) + this.yMoreFromNextPrevious;
                    Image[] imageArr4 = LoadDataManager.imgIconHide;
                    mgraphics.drawImage(image2, rWidth2, (float) (i25 - (imageArr4[rightIndex2 % imageArr4.length].getRHeight() / 2)));
                }
            }
            index++;
            i2++;
            i = 2;
        }
        CanvasNinja.resetTrans(g);
        MyNextPrevious myNextPrevious2 = this.myNextPrevious;
        if (myNextPrevious2 != null) {
            myNextPrevious2.paint(mgraphics);
        }
        int i26 = 0;
        while (i26 < this.isClickTagIcon.length) {
            CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameTabIconHanhTrang, this.arrX[9], this.yAnimTagIcon[i26] + this.arrY[9] + ((this.arrH[9] + (this.margin * 2)) * i26), this.arrW[9], this.indexTabIconSelected != i26, 0, false);
            FrameImage frameImage3 = LoadDataManager.frameIconTabHanhTrangs[i26];
            if (frameImage3 != null) {
                int i27 = this.indexTabIconSelected != i26 ? 0 : 1;
                float f = ((float) this.arrX[9]) + ((((float) this.arrW[9]) - frameImage3.frameWidth) / 2.0f);
                int i28 = this.margin;
                int i29 = this.arrY[9] + this.yAnimTagIcon[i26];
                int i30 = this.arrH[9];
                frameImage3.drawFrame(i27, f + ((float) i28), (((float) (i29 + (((i28 * 2) + i30) * i26))) + ((((float) i30) - frameImage3.frameHeight) / 2.0f)) - 1.0f, mgraphics);
            }
            i26++;
        }
        SupportPaint supportPaint3 = CanvasNinja.paintz;
        FrameImage frameImage4 = LoadDataManager.frameVachDoc;
        int i31 = this.wLeft + this.x;
        int i32 = this.y;
        int i33 = this.margin;
        int i34 = this.h;
        supportPaint3.paintLineVertical(g, frameImage4, i31, i32 + i33, i34 - (i33 * 2), i34, false);
        this.cmdClose.paintIconOnly(mgraphics);
        paintMoney(g);
        int i35 = this.indexTabIconSelected;
        if (i35 == 0) {
            paintRightInventory(g);
        } else if (i35 == 1) {
            int i36 = this.tabInfo;
            if (i36 == 1) {
                paintCharInfo1(g);
            } else if (i36 == 2) {
                paintCharInfo2(g);
            }
        } else if (i35 == 2) {
            paintTabTiemNang(g);
        }
    }

    private void paintTabTiemNang(mGraphics g) {
        if (this.sumChange > 0) {
            MyButton myButton = this.btnConfirm;
            FrameImage frameImage = LoadDataManager.myButtons[1];
            myButton.imgButton = frameImage;
            myButton.imgButtonHover = frameImage;
            myButton.fontPaint = mFont.tahoma_7b_white;
        } else {
            MyButton myButton2 = this.btnConfirm;
            FrameImage frameImage2 = LoadDataManager.myButtons[4];
            myButton2.imgButton = frameImage2;
            myButton2.imgButtonHover = frameImage2;
            myButton2.fontPaint = mFont.fontPaintDisableButotn;
        }
        mGraphics mgraphics = g;
        fontPaintTagNameFocus.drawString(mgraphics, "Điểm tiềm năng: " + Player.myCharz().charInfo.potentialPt, this.arrX[7], (((this.arrY[7] + this.margin) + this.arrH[7]) - 30) + 15, 0);
        this.btnConfirm.paint(g);
        CanvasNinja.paintz.paintSingleBorderFrame(mgraphics, LoadDataManager.frameXam2, (float) this.arrX[7], (float) this.arrY[7], (float) this.arrW[7], (float) (this.arrH[7] + -30), 2, false);
        mFont mfont = fontPaintNameChar;
        int i = this.arrX[7];
        int i2 = this.margin;
        mfont.drawString(g, "Tiềm năng nhân vật", (i2 * 3) + i, this.arrY[7] + (i2 * 3), 0);
        int curY = this.arrY[7] + (this.margin * 9);
        if (this.optionsInfos != null) {
            for (int i3 = 0; i3 < this.optionsInfos.length; i3++) {
                int j = 0;
                while (true) {
                    ItemOption[] itemOptionArr = this.optionsInfos[i3].itemOption;
                    if (j >= itemOptionArr.length) {
                        break;
                    }
                    if (i3 == 0 && j <= this.arrXPosTru.length - 1) {
                        mFont mfont2 = fontPaintTagName;
                        String str = itemOptionArr[j].optionTemplate.name;
                        mfont2.drawString(g, str.substring(0, str.length() - 2), (this.margin * 3) + this.arrX[7], curY, 0, true);
                        SupportPaint supportPaint = CanvasNinja.paintz;
                        FrameImage frameImage3 = LoadDataManager.frameTruTN;
                        supportPaint.paintTagFrame(g, frameImage3, (this.margin * 21) + this.arrX[7], curY - 3, (int) frameImage3.frameWidth, this.statChange[j] <= 0, 0, true);
                        int[] iArr = this.arrXPosTru;
                        int[] iArr2 = this.arrX;
                        int i4 = iArr2[7];
                        int i5 = this.margin;
                        iArr[j] = i4 + (i5 * 21);
                        this.arrYPosTru[j] = curY - 3;
                        SupportPaint supportPaint2 = CanvasNinja.paintz;
                        FrameImage frameImage4 = LoadDataManager.frameInfo;
                        supportPaint2.paintTagFrame(g, frameImage4, (i5 * 25) + iArr2[7], curY - 3, (int) frameImage4.frameWidth, false, 0, true);
                        if (this.statChange[j] == 0) {
                            fontPaintCharInfo2.drawString(g, "" + this.diemTiemNang[j], (((int) LoadDataManager.frameInfo.frameWidth) / 2) + this.arrX[7] + (this.margin * 25), curY, 2, true);
                        } else {
                            fontPaintCharInfoFocus.drawString(g, "" + this.diemTiemNang[j], (((int) LoadDataManager.frameInfo.frameWidth) / 2) + this.arrX[7] + (this.margin * 25), curY, 2, true);
                        }
                        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameCongTN, ((int) LoadDataManager.frameInfo.frameWidth) + this.arrX[7] + (this.margin * 26), curY - 3, (int) LoadDataManager.frameCongTN.frameWidth, Player.myCharz().charInfo.potentialPt <= 0, 0, true);
                        int[] iArr3 = this.arrXPosCong;
                        int i6 = this.arrX[7];
                        int i7 = this.margin;
                        iArr3[j] = i6 + (i7 * 26) + ((int) LoadDataManager.frameInfo.frameWidth);
                        this.arrYPosCong[j] = curY - 3;
                        curY += i7 * 5;
                    }
                    j++;
                }
            }
        }
    }

    private void paintInfoTitle(mGraphics g, int xx, int yy, int ww, String title) {
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameInfoTitle, xx, yy, ww, false);
        g.drawImage(LoadDataManager.ngoiSaoL, (float) (xx + 3), ((float) yy) + (LoadDataManager.frameInfoTitle.frameHeight / 4.0f));
        mFont mfont = this.fontPaintTitle;
        mfont.drawString(g, title, xx + (ww / 2), (((float) yy) + (LoadDataManager.frameInfoTitle.frameHeight / 2.0f)) - ((float) (mfont.getHeight() / 2)), 2);
        Image image = LoadDataManager.ngoiSaoR;
        g.drawImage(image, (float) (((xx + ww) - 3) - image.getRWidth()), ((float) yy) + (LoadDataManager.frameInfoTitle.frameHeight / 4.0f));
    }

    private void paintCharInfo1(mGraphics g) {
        mItemOption[] mitemoptionArr;
        String optionString;
        int wVachNgang;
        String optionString2;
        char c = 7;
        int i = 1;
        paintInfoTitle(g, this.arrX[7], this.arrY[7], this.arrW[7], this.optionsInfos[1].title);
        int wVachNgang2 = (int) LoadDataManager.frameLineNgang.frameWidth;
        int curY = this.arrY[7] + (this.margin * 5);
        int colWidth = this.arrW[7] / 3;
        int j = 0;
        int section = 0;
        while (true) {
            mitemoptionArr = this.optionsInfos;
            ItemOption[] itemOptionArr = mitemoptionArr[i].itemOption;
            if (j >= itemOptionArr.length) {
                break;
            }
            String optionString3 = itemOptionArr[j].optionStringTemp;
            if (optionString3.contains("----")) {
                CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameLineNgang, this.arrX[c] + ((this.arrW[c] - wVachNgang2) / 2), curY + this.margin, wVachNgang2, false);
                curY += this.lineSpacing;
                section++;
                wVachNgang = wVachNgang2;
            } else {
                int[] iArr = this.arrX;
                int xOffset = iArr[c];
                if (section == i && j % 2 == 0) {
                    curY -= this.lineSpacing;
                    xOffset = (iArr[c] + (colWidth * 2)) - (this.margin * 2);
                } else if (section > 1) {
                    xOffset = iArr[c];
                }
                this.strPaints = optionString3.split(":");
                StringBuilder sb = new StringBuilder();
                String[] strArr = this.strPaints;
                wVachNgang = wVachNgang2;
                sb.append(strArr[0]);
                sb.append(":");
                strArr[0] = sb.toString();
                String optionString4 = mFont.addColorByKey(optionString3 + "  ", optionString3.substring(0, optionString3.indexOf(58) + 1), "#b25c29");
                if (!optionString4.contains("/") || optionString4.contains("/#")) {
                    optionString2 = mFont.addColorByKey(optionString4, optionString4.substring(optionString4.indexOf(":") + 3), "#fa5e00");
                } else {
                    String optionString5 = mFont.addColorByKey(optionString4, optionString4.substring(optionString4.indexOf(":") + 3, optionString4.indexOf("/") + 1), "#fa5e00");
                    optionString2 = mFont.addColorByKey(optionString5, optionString5.substring(optionString5.indexOf("/") + 3), "#37bd2c");
                }
                fontPaintCharInfo.drawString(g, optionString2, xOffset, curY, 0, true);
                curY += this.lineSpacing;
            }
            j++;
            wVachNgang2 = wVachNgang;
            c = 7;
            i = 1;
        }
        int curY2 = (int) (((double) curY) + (((double) this.margin) * 1.5d));
        int i2 = wVachNgang2;
        paintInfoTitle(g, this.arrX[7], curY2, this.arrW[7], mitemoptionArr[2].title);
        int curY3 = curY2 + (this.margin * 5);
        int j2 = 0;
        while (true) {
            ItemOption[] itemOptionArr2 = this.optionsInfos[2].itemOption;
            if (j2 < itemOptionArr2.length) {
                String optionString6 = itemOptionArr2[j2].optionStringTemp;
                String optionString7 = mFont.addColorByKey(optionString6, optionString6.substring(0, optionString6.indexOf(58) + 1), "#b25c29");
                if (!optionString7.contains("/") || optionString7.contains("/#")) {
                    optionString = mFont.addColorByKey(optionString7, optionString7.substring(optionString7.indexOf(":") + 3), "#fa5e00");
                } else {
                    String optionString8 = mFont.addColorByKey(optionString7, optionString7.substring(optionString7.indexOf(":") + 3, optionString7.indexOf("/") + 1), "#fa5e00");
                    optionString = mFont.addColorByKey(optionString8, optionString8.substring(optionString8.indexOf("/") + 3), "#37bd2c");
                }
                fontPaintCharInfo.drawString(g, optionString, this.arrX[7], curY3, 0, true);
                curY3 += this.lineSpacing;
                j2++;
            } else {
                Image image = LoadDataManager.imgChatDown;
                g.drawImage(image, (float) (((this.arrX[7] + this.arrW[7]) - 3) - image.getRWidth()), (float) (this.arrY[7] + this.arrH[7]));
                return;
            }
        }
    }

    private void paintCharInfo2(mGraphics g) {
        mItemOption[] mitemoptionArr;
        paintInfoTitle(g, this.arrX[7], this.arrY[7], this.arrW[7], this.optionsInfos[3].title);
        int curY = this.arrY[7] + (this.margin * 5);
        int j = 0;
        while (true) {
            mitemoptionArr = this.optionsInfos;
            ItemOption[] itemOptionArr = mitemoptionArr[3].itemOption;
            if (j >= itemOptionArr.length) {
                break;
            }
            String optionString = itemOptionArr[j].optionStringTemp;
            String optionString2 = mFont.addColorByKey(optionString, optionString.substring(0, optionString.indexOf(58) + 1), "#b25c29");
            fontPaintCharInfo.drawString(g, mFont.addColorByKey(optionString2, optionString2.substring(optionString2.indexOf(":") + 3), "#fa5e00"), this.arrX[7], curY, 0, true);
            curY += this.lineSpacing;
            j++;
        }
        int curY2 = (int) (((double) curY) + (((double) this.margin) * 1.5d));
        paintInfoTitle(g, this.arrX[7], curY2, this.arrW[7], mitemoptionArr[4].title);
        int curY3 = curY2 + (this.margin * 5);
        int j2 = 0;
        while (true) {
            ItemOption[] itemOptionArr2 = this.optionsInfos[4].itemOption;
            if (j2 < itemOptionArr2.length) {
                String optionString3 = itemOptionArr2[j2].optionStringTemp;
                String optionString4 = mFont.addColorByKey(optionString3, optionString3.substring(0, optionString3.indexOf(58) + 1), "#b25c29");
                fontPaintCharInfo.drawString(g, mFont.addColorByKey(optionString4, optionString4.substring(optionString4.indexOf(":") + 3), "#fa5e00"), this.arrX[7], curY3, 0, true);
                curY3 += this.lineSpacing;
                j2++;
            } else {
                Image image = LoadDataManager.imgChatUp2;
                g.drawImage(image, (float) (((this.arrX[7] + this.arrW[7]) - 3) - image.getRWidth()), (float) (this.arrY[7] + this.arrH[7]));
                return;
            }
        }
    }

    private void paintRightInventory(mGraphics g) {
        mGraphics mgraphics = g;
        int i = 0;
        while (i < this.isClickTagName.length) {
            SupportPaint supportPaint = CanvasNinja.paintz;
            FrameImage frameImage = LoadDataManager.frameTagTitleHanhTrang;
            int i2 = this.arrX[6];
            int i3 = this.margin;
            int i4 = this.arrW[6];
            supportPaint.paintTagFrame(g, frameImage, ((i3 + i4) * i) + i2, this.yAnimTagName[i] + this.arrY[6], i4, this.indexTagNameSelected == i, 0, false);
            FrameImage frameImage2 = LoadDataManager.frameIconTagNameInventory[i];
            int i5 = this.indexTagNameSelected == i ? 1 : 0;
            int i6 = this.arrX[6];
            int i7 = this.margin;
            int i8 = this.arrW[6];
            frameImage2.drawFrame(i5, ((float) ((i6 + ((i7 + i8) * i)) + (i8 / 2))) - (frameImage2.frameWidth / 2.0f), ((float) ((this.arrY[6] + this.yAnimTagName[i]) + (this.arrH[6] / 2))) - (frameImage2.frameHeight / 2.0f), mgraphics);
            i++;
        }
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameXam2, (float) this.arrX[7], (float) this.arrY[7], (float) this.arrW[7], (float) this.arrH[7], 2, false);
        int i9 = this.arrX[7];
        int i10 = this.arrY[7];
        int i11 = this.margin;
        mgraphics.setClip(i9, i10 + i11, this.arrW[7], this.arrH[7] - (i11 * 2));
        mgraphics.translate(0, -this.scroll.cmy);
        for (int j = 0; j < this.numNgangBox; j++) {
            for (int k = 0; k < this.maxDocBox; k++) {
                int indexItem = (this.numNgangBox * k) + j;
                SupportPaint supportPaint2 = CanvasNinja.paintz;
                FrameImage frameImage3 = LoadDataManager.frameBoxHanhTrangLocked;
                int i12 = this.arrX[8];
                int i13 = this.margin2;
                int i14 = this.arrW[8];
                int i15 = ((i13 + i14) * j) + i12;
                int i16 = this.arrY[8] + ((i13 + this.arrH[8]) * k);
                int i17 = this.indexSelectedInven;
                supportPaint2.paintTagFrame(g, frameImage3, i15, i16, i14, i17 != -1 && i17 == indexItem, 0, true);
                if (indexItem < this.maxSlot) {
                    SupportPaint supportPaint3 = CanvasNinja.paintz;
                    FrameImage frameImage4 = LoadDataManager.frameBoxHanhTrangDefault;
                    int i18 = this.arrX[8];
                    int i19 = this.margin2;
                    int i20 = this.arrW[8];
                    int i21 = ((i19 + i20) * j) + i18;
                    int i22 = this.arrY[8] + ((i19 + this.arrH[8]) * k);
                    int i23 = this.indexSelectedInven;
                    supportPaint3.paintTagFrame(g, frameImage4, i21, i22, i20, i23 != -1 && i23 == indexItem, 0, true);
                }
                if (isInRangeVecItem(indexItem)) {
                    if (this.vecItemInvens.get(indexItem).rarity == -1 || this.vecItemInvens.get(indexItem).rarity >= LoadDataManager.frameBoxHanhTrang.length) {
                        SupportPaint supportPaint4 = CanvasNinja.paintz;
                        FrameImage frameImage5 = LoadDataManager.frameBoxHanhTrangDefault;
                        int i24 = this.arrX[8];
                        int i25 = this.margin2;
                        int i26 = this.arrW[8];
                        int i27 = ((i25 + i26) * j) + i24;
                        int i28 = this.arrY[8] + ((i25 + this.arrH[8]) * k);
                        int i29 = this.indexSelectedInven;
                        supportPaint4.paintTagFrame(g, frameImage5, i27, i28, i26, i29 != -1 && i29 == indexItem, 0, true);
                    } else {
                        SupportPaint supportPaint5 = CanvasNinja.paintz;
                        FrameImage frameImage6 = LoadDataManager.frameBoxHanhTrang[this.vecItemInvens.get(indexItem).rarity];
                        int i30 = this.arrX[8];
                        int i31 = this.margin2;
                        int i32 = this.arrW[8];
                        int i33 = ((i31 + i32) * j) + i30;
                        int i34 = this.arrY[8] + ((i31 + this.arrH[8]) * k);
                        int i35 = this.indexSelectedInven;
                        supportPaint5.paintTagFrame(g, frameImage6, i33, i34, i32, i35 != -1 && i35 == indexItem, 0, true);
                    }
                    int i36 = this.arrX[8];
                    int i37 = this.margin2;
                    int i38 = this.arrW[8];
                    int i39 = ((i37 + i38) * j) + i36;
                    int i40 = this.arrY[8];
                    int i41 = this.arrH[8];
                    this.vecItemInvens.get(indexItem).paintCenter(g, i39, i40 + ((i37 + i41) * k), i38, i41, true, true);
                } else if (indexItem == this.maxSlot) {
                    SupportPaint supportPaint6 = CanvasNinja.paintz;
                    FrameImage frameImage7 = LoadDataManager.frameBoxHanhTrangAdd;
                    int i42 = this.arrX[8];
                    int i43 = this.margin2;
                    int i44 = this.arrW[8];
                    int i45 = ((i43 + i44) * j) + i42;
                    int i46 = this.arrY[8] + ((i43 + this.arrH[8]) * k);
                    int i47 = this.indexSelectedInven;
                    supportPaint6.paintTagFrame(g, frameImage7, i45, i46, i44, i47 != -1 && i47 == indexItem, 0, true);
                }
            }
        }
        CanvasNinja.resetTrans(g);
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

    public void addItem(Item item) {
        this.vecItemInvens.add(item);
        if (this.indexTagNameSelected != 0) {
            this.vecItemInvenTemps.removeAllElements();
            this.vecItemInvenTemps.addAll(this.vecItemInvens);
            classifyItem((byte) this.indexTagNameSelected);
        }
    }

    public void updateNum(int index, int num) {
        if (num == -1) {
            this.vecItemInvens.remove(index);
        } else if (num == 0) {
            this.vecItemInvens.removeElement(Integer.valueOf(index));
        } else {
            this.vecItemInvens.get(index).num = num;
        }
        if (this.indexTagNameSelected != 0) {
            this.vecItemInvenTemps.removeAllElements();
            this.vecItemInvenTemps.addAll(this.vecItemInvens);
            classifyItem((byte) this.indexTagNameSelected);
        }
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
        this.w = 526;
        this.h = 300;
        int i = CanvasNinja.w;
        if (526 > i) {
            this.w = (i - (this.margin * 5)) - ((int) LoadDataManager.frameTabIconHanhTrang.frameWidth);
        }
        int i2 = CanvasNinja.h;
        if (300 > i2) {
            this.h = i2 - (this.margin * 2);
        }
        initStart();
        int hTagTitleHanhTrang = (int) LoadDataManager.frameTagTitleHanhTrang.frameHeight;
        float f = LoadDataManager.frameBoxUsed.frameHeight;
        this.arrH = new int[]{(int) f, (int) f, LoadDataManager.imgCucDat.getRHeight(), 90, 0, (int) LoadDataManager.frameTitle.frameHeight, hTagTitleHanhTrang, (((this.h - (this.margin7 * 2)) - (this.margin * 2)) - hTagTitleHanhTrang) - 20, (int) LoadDataManager.frameBoxHanhTrang[0].frameHeight, (int) LoadDataManager.frameTabIconHanhTrang.frameHeight};
        int wCucDat = LoadDataManager.imgCucDat.getRWidth();
        int wBoxUsed = (int) LoadDataManager.frameBoxUsed.frameWidth;
        int i3 = this.margin;
        int i4 = (i3 * 4) + (wBoxUsed * 2) + (10 * 2) + wCucDat + i3 + ((((int) LoadDataManager.framePrevious.frameWidth) + i3) * 2);
        this.wLeft = i4;
        int i5 = (this.w - i4) - i3;
        this.wRight = i5;
        int wKhungXam = (i5 - (LoadDataManager.imgClose.getRWidth() / 2)) - this.margin7;
        int numTab = this.nameTags.length;
        int wTagTitleHanhTrang = Res.fixSizeTagFrameDown(7, (((this.wRight - LoadDataManager.imgClose.getRWidth()) - this.margin7) - (this.margin * numTab)) / numTab, LoadDataManager.frameTagTitleHanhTrang);
        int width = TabScr.fontPaintTile.getWidth(SupportTranslate.getTextLangue("info").toUpperCase());
        int i6 = this.margin;
        int i7 = width + (i6 * 5);
        int i8 = (int) LoadDataManager.frameBoxHanhTrang[0].frameWidth;
        int[] iArr = {wBoxUsed, wBoxUsed, wCucDat, 22, 0, i7, wTagTitleHanhTrang, wKhungXam, i8, (int) LoadDataManager.frameTabIconHanhTrang.frameWidth};
        this.arrW = iArr;
        int i9 = this.x;
        int xODauTien = this.margin7 + i9 + (i6 * 2);
        int xRight = this.wLeft + i9 + i6 + this.margin3;
        int i10 = this.margin2;
        int i11 = (wKhungXam - i10) / (i10 + i8);
        this.numNgangBox = i11;
        this.maxDocBox = this.capacity / i11;
        int i12 = (i8 * i11) + ((i11 - 1) * i10);
        this.sumWBoxNgang = i12;
        int i13 = ((int) LoadDataManager.framePrevious.frameWidth) + i6;
        this.xMoreFromNextPreviousDefault = i13;
        this.xMoreFromNextPrevious = i13;
        int wName = wCucDat + (10 * 2);
        int i14 = iArr[0];
        this.arrX = new int[]{xODauTien, xODauTien + i14 + wCucDat + (10 * 2), xODauTien + i14 + 10, xODauTien + i14 + 10 + (wCucDat / 2), xODauTien + i14 + 10 + (wCucDat / 2), ((i14 + xODauTien) + (wName / 2)) - (i7 / 2), xRight, xRight, xRight + ((wKhungXam - i12) / 2), ((i9 + this.w) - (i6 * 4)) + 1};
        this.yMoreFromNextPrevious = 0;
        this.numODoc = 4;
        this.maxODoc = 4;
        int sumHElement = getSumHElement(4);
        int i15 = this.y;
        int i16 = this.h;
        int yODauTien = i15 + ((i16 - sumHElement) / 2);
        int[] iArr2 = this.arrH;
        int hCucDatVaChar = iArr2[2] + 60;
        int i17 = this.margin;
        int wBoxUsed2 = wBoxUsed;
        int yDauTienPhai = (i17 * 2) + i15 + this.margin3;
        int i18 = iArr2[6];
        MyCommand myCommand = this.cmdClose;
        this.arrY = new int[]{yODauTien, yODauTien, ((i16 - hCucDatVaChar) / 2) + i15 + 60, (((yODauTien + ((sumHElement - hCucDatVaChar) / 2)) + 60) + (iArr2[2] / 2)) - 4, yODauTien - (i17 * 2), i15 - (iArr2[5] / 2), yDauTienPhai, yDauTienPhai + i18 + i17, yDauTienPhai + i18 + i17 + i17, myCommand.y + myCommand.h + (this.margin7 * 2)};
        this.yMoreFromNextPreviousDefault = (i15 + ((i16 - getSumHElement(4)) / 2)) - this.arrY[1];
        int i19 = this.arrX[1] + this.arrW[1] + (this.margin * 2);
        FrameImage frameImage = LoadDataManager.framePrevious;
        float f2 = frameImage.frameWidth;
        float f3 = frameImage.frameHeight;
        this.myNextPrevious = new MyNextPrevious(i19 + ((int) f2), (this.y + (this.h / 2)) - (((int) f3) / 2), (int) f2, (int) f3, 0, 0, 0, true, true);
        fontPaintTagName = mFont.fontPaintTagName;
        fontPaintTagNameFocus = mFont.fontPaintTagNameFocus;
        int i20 = this.maxDocBox;
        int[] iArr3 = this.arrH;
        int hMaxPaint = (iArr3[8] * i20) + ((i20 - 1) * this.margin2);
        int i21 = this.arrX[7];
        int i22 = this.arrY[7];
        int i23 = this.arrW[7];
        int numTab2 = iArr3[7];
        int i24 = wBoxUsed2;
        int i25 = numTab;
        initScroll(i21, i22, i23, numTab2, hMaxPaint, numTab2 - (this.margin * 2));
        int curY2 = this.arrY[7] + (this.margin * 9);
        if (this.optionsInfos != null) {
            for (int i26 = 1; i26 < this.optionsInfos.length; i26++) {
                int j = 0;
                while (true) {
                    ItemOption[] itemOptionArr = this.optionsInfos[i26].itemOption;
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
        int i27 = this.arrX[7];
        int i28 = this.arrY[7];
        int i29 = this.arrW[7];
        int i30 = this.arrH[7];
        initScroll2(i27, i28, i29, i30 - 30, (curY + curY3) - 60, (i30 - (curY3 * 2)) - 30);
        initMoney();
        if (this.optionsInfos != null) {
            for (int i31 = 0; i31 < 5; i31++) {
                this.diemTiemNang[i31] = this.optionsInfos[0].itemOption[i31].param[0];
            }
        }
        if (this.btnConfirm == null) {
            mFont mfont = mFont.tahoma_7b_white;
            FrameImage frameImage2 = LoadDataManager.myButtons[4];
            this.btnConfirm = new MyButton(mfont, frameImage2, frameImage2, 80, 69, "XÁC NHẬN", (this.arrX[7] + this.arrW[7]) - 80, ((this.arrY[7] + this.margin) + this.arrH[7]) - 27, new MyCommand("", 696969, this));
        }
        this.tabInfo = 1;
        this.fontPaintTitle.setHeight(mFont.getFontSizeName());
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

    public void paintMoney(mGraphics g, int type) {
        int yMore = 0;
        if (type == 1) {
            yMore = -2;
        }
        g.drawImage(LoadDataManager.imgMoney[type], (float) this.xStartMoney[type], (float) (this.yStartMoney[type] + yMore));
        fontPaintMoney.drawString(g, this.moneyStr[type], this.xStartMoney[type] + LoadDataManager.imgMoney[type].getRWidth() + this.margin, this.yStartMoney[0] + 7 + 2);
    }

    public void paintMoney(mGraphics g) {
        paintMoney(g, 0);
        paintMoney(g, 1);
        paintMoney(g, 2);
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
        this.btnConfirm.update();
        ScrollY scrollY = this.scroll;
        if (scrollY.trans) {
            scrollY.isHavePopup = false;
        }
        ScrollY scrollY2 = this.scroll2;
        if (scrollY2.trans) {
            scrollY2.isHavePopup = false;
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
                    this.yMoreFromNextPrevious = (this.y + ((this.h - getSumHElement(4)) / 2)) - this.arrY[1];
                    this.startUsed = 8;
                    this.numODoc = 4;
                    this.maxODoc = 4 + 8;
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
                                    if (this.indexSelectedInven == this.lastIndexSelectedInven) {
                                        this.popupItemInfo = null;
                                        this.lastIndexSelectedInven = b;
                                        return;
                                    }
                                    this.popupItemUsedInfo = null;
                                    this.popupItemInfo = new PopupItemInfo();
                                    this.lastIndexSelectedInven = this.indexSelectedInven;
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
                                        Res.outz(3, "id Template:" + this.vecItemInvens.get(indexItem).idTemplate);
                                        if (MapScr.gI().tutorial != null && MapScr.gI().tutorial.isStepThaoTacItem(2) && this.vecItemInvens.get(indexItem).isFocusTutorial) {
                                            MapScr.gI().tutorial.step = 3;
                                        }
                                    }
                                    isCheckPopup = true;
                                } else if (indexItem == this.maxSlot) {
                                    SendMessage.gI().useItemInventory((byte) 3, this.maxSlot + 1);
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
                                    box2 = new BoxInventory(LoadDataManager.frameBoxHanhTrang[this.vecItemUsedInvens.get(this.indexSelectedUsed).rarity], this.vecItemUsedInvens.get(this.indexSelectedUsed));
                                } else {
                                    box2 = new BoxInventory(LoadDataManager.frameBoxHanhTrangDefault, this.vecItemUsedInvens.get(this.indexSelectedUsed));
                                }
                                if (this.vecItemUsedInvens.get(this.indexSelectedUsed).template != null && !this.vecItemUsedInvens.get(this.indexSelectedUsed).isDisableClick()) {
                                    PopupItemInfo popupItemInfo7 = this.popupItemUsedInfo;
                                    int i28 = this.arrX[0] + this.arrW[0] + this.xMoreFromNextPrevious;
                                    int i29 = this.arrY[0];
                                    int i30 = this.arrH[0];
                                    popupItemInfo7.startPopupItemInfo(i28, this.yMoreFromNextPrevious + i29 + ((this.margin2 + i30) * index) + (i30 / 2), alignY, box2, (byte) 1);
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
            if (this.indexTabIconSelected == 1 && this.tabInfo == 1 && CanvasNinja.isPointer(((this.arrX[7] + this.arrW[7]) - 3) - LoadDataManager.imgChatDown.getRWidth(), this.arrY[7] + this.arrH[7], LoadDataManager.imgChatDown.getRWidth(), LoadDataManager.imgChatDown.getRHeight())) {
                CanvasNinja.clearAllPointer();
                this.tabInfo = 2;
                Res.outz("Chuyen tab");
            }
            if (this.indexTabIconSelected == 1 && this.tabInfo == 2 && CanvasNinja.isPointer(((this.arrX[7] + this.arrW[7]) - 3) - LoadDataManager.imgChatDown.getRWidth(), this.arrY[7] + this.arrH[7], LoadDataManager.imgChatDown.getRWidth(), LoadDataManager.imgChatDown.getRHeight())) {
                CanvasNinja.clearAllPointer();
                this.tabInfo = 1;
            }
            if (this.indexTabIconSelected == 2) {
                this.btnConfirm.updatePointer();
                for (int i36 = 0; i36 < 5; i36++) {
                    int i37 = this.arrXPosTru[i36];
                    int i38 = this.arrYPosTru[i36];
                    float f4 = LoadDataManager.frameTruTN.frameWidth;
                    if (CanvasNinja.isPoint(i37, i38, (int) f4, (int) f4)) {
                        CanvasNinja.clearAllPointer();
                        if (this.sumChange > 0) {
                            switch (i36) {
                                case 0:
                                    if (this.diemTiemNang[i36] > Player.myCharz().charInfo.pSTR) {
                                        int[] iArr2 = this.diemTiemNang;
                                        iArr2[i36] = iArr2[i36] - 1;
                                        PlayerInfo playerInfo = Player.myCharz().charInfo;
                                        playerInfo.potentialPt = (short) (playerInfo.potentialPt + 1);
                                        this.sumChange--;
                                        int[] iArr3 = this.statChange;
                                        iArr3[i36] = iArr3[i36] - 1;
                                        break;
                                    }
                                    break;
                                case 1:
                                    if (this.diemTiemNang[i36] > Player.myCharz().charInfo.pAGI) {
                                        int[] iArr4 = this.diemTiemNang;
                                        iArr4[i36] = iArr4[i36] - 1;
                                        PlayerInfo playerInfo2 = Player.myCharz().charInfo;
                                        playerInfo2.potentialPt = (short) (playerInfo2.potentialPt + 1);
                                        this.sumChange--;
                                        int[] iArr5 = this.statChange;
                                        iArr5[i36] = iArr5[i36] - 1;
                                        break;
                                    }
                                    break;
                                case 2:
                                    if (this.diemTiemNang[i36] > Player.myCharz().charInfo.pDEF) {
                                        int[] iArr6 = this.diemTiemNang;
                                        iArr6[i36] = iArr6[i36] - 1;
                                        PlayerInfo playerInfo3 = Player.myCharz().charInfo;
                                        playerInfo3.potentialPt = (short) (playerInfo3.potentialPt + 1);
                                        this.sumChange--;
                                        int[] iArr7 = this.statChange;
                                        iArr7[i36] = iArr7[i36] - 1;
                                        break;
                                    }
                                    break;
                                case 3:
                                    if (this.diemTiemNang[i36] > Player.myCharz().charInfo.pHP) {
                                        int[] iArr8 = this.diemTiemNang;
                                        iArr8[i36] = iArr8[i36] - 1;
                                        PlayerInfo playerInfo4 = Player.myCharz().charInfo;
                                        playerInfo4.potentialPt = (short) (playerInfo4.potentialPt + 1);
                                        this.sumChange--;
                                        int[] iArr9 = this.statChange;
                                        iArr9[i36] = iArr9[i36] - 1;
                                        break;
                                    }
                                    break;
                                case 4:
                                    if (this.diemTiemNang[i36] > Player.myCharz().charInfo.pMP) {
                                        int[] iArr10 = this.diemTiemNang;
                                        iArr10[i36] = iArr10[i36] - 1;
                                        PlayerInfo playerInfo5 = Player.myCharz().charInfo;
                                        playerInfo5.potentialPt = (short) (playerInfo5.potentialPt + 1);
                                        this.sumChange--;
                                        int[] iArr11 = this.statChange;
                                        iArr11[i36] = iArr11[i36] - 1;
                                        break;
                                    }
                                    break;
                            }
                        }
                    }
                    int i39 = this.arrXPosCong[i36];
                    int i40 = this.arrYPosCong[i36];
                    float f5 = LoadDataManager.frameCongTN.frameWidth;
                    if (CanvasNinja.isPoint(i39, i40, (int) f5, (int) f5)) {
                        CanvasNinja.clearAllPointer();
                        if (Player.myCharz().charInfo.potentialPt > 0) {
                            int[] iArr12 = this.diemTiemNang;
                            iArr12[i36] = iArr12[i36] + 1;
                            PlayerInfo playerInfo6 = Player.myCharz().charInfo;
                            playerInfo6.potentialPt = (short) (playerInfo6.potentialPt - 1);
                            this.sumChange++;
                            int[] iArr13 = this.statChange;
                            iArr13[i36] = iArr13[i36] + 1;
                        }
                    }
                }
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
                this.vecItemInvens.removeAllElements();
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
        if (this.myNextPrevious == null || this.character != Player.myCharz()) {
            init();
        }
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
        if (keyCode == KEY.KEY_C) {
            int[] iArr = this.diemTiemNang;
            int i = this.tiemNangIndex;
            iArr[i] = iArr[i] + 6000;
            SendMessage.gI().requestUpgradeTN(gI().diemTiemNang);
            int i2 = this.tiemNangIndex + 1;
            this.tiemNangIndex = i2;
            if (i2 >= this.diemTiemNang.length) {
                this.tiemNangIndex = 0;
            }
        }
    }

    private void hardCodeUpdateInven() {
        gI().updateNum(0, 10);
    }

    public void keyTyped() {
    }

    public void commandTab(int index, int subIndex) {
        super.commandTab(index, subIndex);
        Res.outz(1, "Vào command Tab Tab Inventory:" + index);
        switch (index) {
            case 2:
                Res.outz(3, "Sử dụng vật phẩm ở index:" + this.vecItemInvens.get(this.indexSelectedInven).id);
                if (MapScr.gI().tutorial != null && MapScr.gI().tutorial.isStepThaoTacItem(3)) {
                    this.vecItemInvens.get(this.indexSelectedInven).isFocusTutorial = false;
                    MapScr.gI().tutorial.step = 4;
                }
                SendMessage.gI().useItemInventory((byte) 3, this.vecItemInvens.get(this.indexSelectedInven).id);
                this.popupItemInfo = null;
                return;
            case 3:
                Res.outz(2, "Vứt vật phẩm ở index:" + this.vecItemInvens.get(this.indexSelectedInven).id);
                InputDialog inputDialog = new InputDialog();
                MyCommand cmdAccept = new MyCommand(SupportTranslate.getTextLangue("ACCEPT"), 6, inputDialog);
                cmdAccept.subAction = this.vecItemInvens.get(this.indexSelectedInven).id;
                inputDialog.startInputDlg(SupportTranslate.getTextLangue("INPUT_NUM"), 1, cmdAccept, true);
                this.popupItemInfo = null;
                return;
            case 10:
                Res.outz(2, "Tháo vật phẩm ở index:" + this.vecItemUsedInvens.get(this.indexSelectedUsed).id);
                SendMessage.gI().useItemInventory((byte) 5, this.vecItemUsedInvens.get(this.indexSelectedUsed).id);
                this.popupItemInfo = null;
                return;
            case 696969:
                if (this.sumChange > 0) {
                    Res.outz(2, "nâng điểm tiềm năng");
                    SendMessage.gI().requestUpgradeTN(this.diemTiemNang);
                    this.sumChange = 0;
                    for (int i = 0; i < 5; i++) {
                        this.statChange[i] = 0;
                    }
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

    public Item removeItemUsed(int index) {
        return this.vecItemUsedInvens.remove(index);
    }

    public void updateItem(Vector<Item> vecItemInvens2, Vector<Item> vecItemUsed) {
        this.vecItemInvenTemps.removeAllElements();
        this.vecItemInvenTemps.addAll(vecItemInvens2);
        classifyItem((byte) this.indexTagNameSelected);
        this.vecItemUsedInvens.removeAllElements();
        this.vecItemUsedInvens.addAll(vecItemUsed);
        this.popupItemInfo = null;
        this.popupItemUsedInfo = null;
    }
}
