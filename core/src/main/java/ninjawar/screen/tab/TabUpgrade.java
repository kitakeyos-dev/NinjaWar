// Class Version: 8
package ninjawar.screen.tab;

import ninjawar.screen.subtab.SubTabInven;
import ninjawar.mylib.FrameAtlas;
import java.util.Collection;
import ninjawar.mylib.AudioManager;
import ninjawar.model.NangCapDaTemplate;
import ninjawar.model.Material;
import ninjawar.model.ChietXuatTemplate;
import ninjawar.model.TinhLuyenTemplate;
import ninjawar.model.UpgradeTemplate;
import ninjawar.template.NangCapDaTemplates;
import ninjawar.template.ChietXuatTemplates;
import ninjawar.model.mGiftData;
import ninjawar.myscr.Player;
import ninjawar.input.KEY;
import java.util.Iterator;
import ninjawar.message.SendMessage;
import ninjawar.supporter.SupportPaint;
import ninjawar.scroll.ScrollY;
import ninjawar.mylib.Image;
import ninjawar.mymain.CanvasNinja;
import ninjawar.supporter.SupportTranslate;
import ninjawar.model.ItemOption;
import ninjawar.template.TinhLuyenTemplates;
import ninjawar.template.UpgradeTemplates;
import ninjawar.mylib.mGraphics;
import ninjawar.input.MyButton;
import ninjawar.mylib.FrameImage;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import java.util.Vector;
import ninjawar.model.TextEffectAppear;
import ninjawar.screen.selectbox.SelectBox;
import ninjawar.model.PopupItemInfo;
import ninjawar.object.Item;
import ninjawar.model.MyCommand;
import ninjawar.mylib.mFont;

public class TabUpgrade extends TabScr
{
    public static mFont fontPaintLvCurent;
    public static mFont fontPaintText;
    public static mFont fontPaintTextNguyenLieu;
    public static mFont fontPaintTextPercent;
    public static mFont fontPaintTextUp;
    public static TabUpgrade me;
    MyCommand[] cmds;
    int hOneSatThuong;
    int hardCodeEffUp;
    int[] idKhoang;
    public int indexItemTab01;
    public int indexItemTab2;
    int indexSelected;
    public int indexSelectedBox;
    int[] indexSubUpgradeSuccess;
    public int indexTabIconSelected;
    public int indexUpgradeSuccess;
    public boolean isAnimUpgrade;
    boolean[] isClickTagIcon;
    public boolean isDark;
    boolean isHavePopup;
    public boolean[] isLost;
    boolean isNullTemplate;
    public boolean isSendUp;
    public boolean isSuccess;
    public boolean isUpdateNum;
    public Item[] itemNeed;
    public Item itemUpgrade;
    int marginEach;
    int maxBoxNeed;
    int maxDocBox;
    public String notify;
    int numBoxNeed;
    public int numBuaChietXuat;
    public int numBuaNangCap;
    public String[] numCur;
    public String[] numNeed;
    int numNgangBox;
    int numPaintStMax;
    int numStar;
    int numTag;
    public int numThienThach;
    public int percentSuccess;
    public PopupItemInfo popupItemInfo;
    private SelectBox selectBox;
    int[] speedYEff;
    int[] statChange;
    int sumChange;
    int sumWBoxNgang;
    public TextEffectAppear textEffectAppear;
    public Vector<Item> vecItems;
    public Vector<Item> vecRefinedTemps;
    public Vector<Item> vecRefineds;
    int wBoxNeed;
    int wLeft;
    int wRight;
    int xRight;
    int xStart;
    int[] xSubEff;
    int yAnimChatDown;
    int[] yAnimTagIcon;
    int yRight;
    int yStart;
    int[] ySubEff;
    int[] ySubEffFirst;
    int[] yTargetEff;

    static {
        TabUpgrade.fontPaintLvCurent = mFont.tahoma_7_white;
        TabUpgrade.fontPaintText = mFont.fontPaintTextUpgrade;
        TabUpgrade.fontPaintTextUp = mFont.fontPaintTextUpgradeUp;
        TabUpgrade.fontPaintTextPercent = mFont.fontPaintTextUpgradeSuccess;
        TabUpgrade.fontPaintTextNguyenLieu = mFont.tahoma_7_white_border_black_small;
    }

    public TabUpgrade() {
        final int length = LoadDataManager.frameIconTabUpgrades.length;
        this.numTag = length;
        this.yAnimTagIcon = new int[length];
        this.isClickTagIcon = new boolean[length];
        this.isNullTemplate = true;
        this.indexItemTab01 = -1;
        this.indexItemTab2 = -1;
        this.hardCodeEffUp = 2;
        this.vecRefineds = new Vector<Item>();
        this.vecRefinedTemps = new Vector<Item>();
        this.vecItems = new Vector<Item>();
        this.maxBoxNeed = 3;
        this.maxDocBox = 20;
        this.indexSelected = -1;
        this.isDark = false;
        this.notify = "";
        this.isHavePopup = false;
        this.sumChange = 0;
        this.statChange = new int[] { 0, 0, 0, 0, 0 };
        this.idKhoang = new int[] { 345, 346, 347, 348 };
    }

    private void clearTab() {
        this.indexItemTab2 = -1;
        this.indexItemTab01 = -1;
        this.itemUpgrade = null;
    }

    private int[] findPositionTarget() {
        final int[] array = new int[2];
        final int indexTabIconSelected = this.indexTabIconSelected;
        if ((indexTabIconSelected == 2 || indexTabIconSelected == 3) && this.itemUpgrade != null) {
            for (int i = 0; i < this.vecRefineds.size(); ++i) {
                if (this.vecRefineds.get(i).idTemplate == this.itemUpgrade.idTemplate) {
                    final int numNgangBox = this.numNgangBox;
                    final int n = i / numNgangBox;
                    final int n2 = i % numNgangBox;
                    array[0] = super.arrX[10] + (super.margin2 + super.arrW[10]) * n2 + LoadDataManager.frameBoxHanhTrang[0].getRWidth() / 2;
                    array[1] = super.arrY[10] + (super.margin2 + super.arrH[10]) * n + LoadDataManager.frameBoxHanhTrang[0].getRHeight() / 2;
                    final StringBuilder sb = new StringBuilder();
                    sb.append("Hàng:");
                    sb.append(n);
                    sb.append("_Cột:");
                    sb.append(n2);
                    Res.outz(sb.toString());
                    return array;
                }
            }
        }
        final int size = this.vecRefineds.size();
        final int numNgangBox2 = this.numNgangBox;
        final int n3 = size / numNgangBox2;
        final int n4 = size % numNgangBox2;
        array[0] = super.arrX[10] + (super.margin2 + super.arrW[10]) * n4 + LoadDataManager.frameBoxHanhTrang[0].getRWidth() / 2;
        array[1] = super.arrY[10] + (super.margin2 + super.arrH[10]) * n3 + LoadDataManager.frameBoxHanhTrang[0].getRHeight() / 2;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("Hàng:");
        sb2.append(n3);
        sb2.append("_Cột:");
        sb2.append(n4);
        Res.outz(sb2.toString());
        return array;
    }

    public static TabUpgrade gI() {
        if (TabUpgrade.me == null) {
            TabUpgrade.me = new TabUpgrade();
        }
        return TabUpgrade.me;
    }

    private void initSub() {
        final float n = (float)super.arrW[2];
        final FrameImage frameSubEffectUpgradeSuccess = LoadDataManager.frameSubEffectUpgradeSuccess;
        final int n2 = (int)(n / frameSubEffectUpgradeSuccess.frameWidth) + 4;
        final int n3 = (int)(super.arrH[2] / frameSubEffectUpgradeSuccess.frameHeight) + 4;
        final int[] array = new int[n2 * n3];
        final int[] array2 = new int[n2 * n3];
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n3; ++j) {
                final int n4 = i * n3 + j;
                array[n4] = super.arrX[2] + super.arrW[2] * i / n2;
                array2[n4] = super.arrY[2] + super.arrH[2] * j / n3;
            }
        }
        final int[] array3 = new int[array.length * 2 / 3];
        for (int k = 0; k < array3.length; ++k) {
            int n5;
            for (n5 = Res.random(0, array.length - 1); Res.checkValueExitsInIntegerArr(array3, n5); n5 = Res.random(0, array.length - 1)) {}
            array3[k] = n5;
        }
        this.indexSubUpgradeSuccess = new int[array3.length];
        this.yTargetEff = new int[array3.length];
        this.xSubEff = new int[array3.length];
        this.ySubEff = new int[array3.length];
        this.ySubEffFirst = new int[array3.length];
        this.speedYEff = new int[array3.length];
        for (int l = 0; l < array3.length; ++l) {
            this.xSubEff[l] = array[array3[l]];
            this.ySubEff[l] = array2[array3[l]];
            this.yTargetEff[l] = super.arrY[2] + super.arrH[2] + Res.random(20, 30);
            this.ySubEffFirst[l] = this.ySubEff[l];
            this.indexSubUpgradeSuccess[l] = Res.random(0, LoadDataManager.frameSubEffectUpgradeSuccess.nFrame - 1);
            this.speedYEff[l] = Res.random(5, 13);
        }
    }

    private boolean isActive() {
        if (this.itemUpgrade != null && !this.isMaxLevelUpgrade() && !this.isNullTemplate) {
            final boolean b = true;
            int n = 0;
            boolean b2;
            while (true) {
                final boolean[] isLost = this.isLost;
                b2 = b;
                if (n >= isLost.length) {
                    break;
                }
                if (isLost[n]) {
                    b2 = false;
                    break;
                }
                ++n;
            }
            return b2;
        }
        return false;
    }

    private boolean isInRangeVecItem(final int n) {
        return n < this.vecRefineds.size() && n >= 0 && this.vecRefineds.get(n) != null;
    }

    private boolean isMaxLevelUpgrade() {
        final Item itemUpgrade = this.itemUpgrade;
        return itemUpgrade != null && itemUpgrade.lvUpgrade > 9 && this.indexTabIconSelected == 0;
    }

    private void onOffBtn(final boolean b) {
        if (b) {
            final MyButton myButton = super.btns[0];
            final FrameImage[] myButtons = LoadDataManager.myButtons;
            myButton.imgButton = myButtons[1];
            myButton.imgButtonHover = myButtons[3];
            myButton.isDisable = false;
        }
        else {
            final MyButton myButton2 = super.btns[0];
            final FrameImage frameImage = LoadDataManager.myButtons[4];
            myButton2.imgButton = frameImage;
            myButton2.imgButtonHover = frameImage;
            myButton2.isDisable = true;
        }
    }

    private void paintItemInfo(final mGraphics mGraphics, final Item item) {
        final int indexTabIconSelected = this.indexTabIconSelected;
        final int n = 15;
        if (indexTabIconSelected == 0 && UpgradeTemplates.get(item.lvUpgrade + 1) != null) {
            int n2 = 0;
            for (int i = 0; i < item.mItemOption.length; ++i) {
                int n3 = 0;
                while (true) {
                    final ItemOption[] itemOption = item.mItemOption[i].itemOption;
                    if (n3 >= itemOption.length) {
                        break;
                    }
                    if (itemOption[n3].getOptionName() != null && item.mItemOption[i].itemOption[n3].typeCanUpgrade == 0) {
                        if (n2 <= this.numPaintStMax - 1) {
                            final mFont fontPaintText = TabUpgrade.fontPaintText;
                            final StringBuilder sb = new StringBuilder();
                            sb.append(item.mItemOption[i].itemOption[n3].getOptionName());
                            sb.append(":");
                            fontPaintText.drawString(mGraphics, sb.toString(), super.arrX[14], super.arrY[14] + this.hOneSatThuong * n2);
                            mGraphics.drawImage(LoadDataManager.imgBgTagUpgrade, (float)super.arrX[15], (float)(super.arrY[15] + this.hOneSatThuong * n2));
                            final mFont fontPaintText2 = TabUpgrade.fontPaintText;
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append(item.mItemOption[i].itemOption[n3].param[0]);
                            sb2.append("");
                            fontPaintText2.drawString(mGraphics, sb2.toString(), super.arrX[19], super.arrY[19] + this.hOneSatThuong * n2, 2);
                            mGraphics.drawImage(LoadDataManager.imgChangeUpgrade, (float)super.arrX[20], (float)(super.arrY[20] + n2 * this.hOneSatThuong));
                            final mFont fontPaintText3 = TabUpgrade.fontPaintText;
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append(Res.round(item.mItemOption[i].itemOption[n3].param[0] * (UpgradeTemplates.get(item.lvUpgrade + 1).optionIncrease + 100) * 1.0f / 100.0f, 1));
                            sb3.append("");
                            fontPaintText3.drawString(mGraphics, sb3.toString(), super.arrX[21], super.arrY[21] + n2 * this.hOneSatThuong);
                            final mFont fontPaintTextUp = TabUpgrade.fontPaintTextUp;
                            final StringBuilder sb4 = new StringBuilder();
                            sb4.append("(+");
                            sb4.append(UpgradeTemplates.get(item.lvUpgrade + 1).optionIncrease);
                            sb4.append("%)");
                            fontPaintTextUp.drawString(mGraphics, sb4.toString(), super.arrX[22], super.arrY[22] + n2 * this.hOneSatThuong);
                            mGraphics.drawImage(LoadDataManager.imgUpUpgrade, (float)super.arrX[23], (float)(super.arrY[23] + n2 * this.hOneSatThuong));
                            ++n2;
                        }
                    }
                    ++n3;
                }
            }
        }
        if (this.indexTabIconSelected == 1 && TinhLuyenTemplates.get(item.levelRefined + 1) != null) {
            mGraphics.drawImage(LoadDataManager.imgBgTagUpgrade, (float)super.arrX[14], (float)super.arrY[14]);
            mGraphics.drawImage(LoadDataManager.imgUpUpgrade, (float)super.arrX[27], (float)super.arrY[27]);
            final mFont fontPaintText4 = TabUpgrade.fontPaintText;
            final StringBuilder sb5 = new StringBuilder();
            sb5.append("+");
            sb5.append(TinhLuyenTemplates.get(item.levelRefined + 1).optionNew);
            sb5.append(" loại chỉ số");
            fontPaintText4.drawString(mGraphics, sb5.toString(), super.arrX[28], super.arrY[28]);
            final int n4 = 0;
            int j = 0;
            int n5 = n;
            int n6 = n4;
            while (j < item.mItemOption.length) {
                int n7 = 0;
                while (true) {
                    final ItemOption[] itemOption2 = item.mItemOption[j].itemOption;
                    if (n7 >= itemOption2.length) {
                        break;
                    }
                    if (itemOption2[n7].getOptionName() != null) {
                        if (n6 <= this.numPaintStMax - 1) {
                            mGraphics.drawImage(LoadDataManager.imgBgTagUpgrade, (float)super.arrX[29], (float)(super.arrY[29] + (super.arrH[n5] + super.margin) * n6));
                            final mFont fontPaintText5 = TabUpgrade.fontPaintText;
                            final StringBuilder sb6 = new StringBuilder();
                            sb6.append(item.mItemOption[j].itemOption[n7].getOptionName());
                            sb6.append(":");
                            fontPaintText5.drawString(mGraphics, sb6.toString(), super.arrX[30], super.arrY[30] + (super.arrH[n5] + super.margin) * n6);
                            final mFont fontPaintText6 = TabUpgrade.fontPaintText;
                            final StringBuilder sb7 = new StringBuilder();
                            sb7.append(item.mItemOption[j].itemOption[n7].param[0]);
                            sb7.append("");
                            final String string = sb7.toString();
                            final int n8 = super.arrX[30];
                            final mFont fontPaintText7 = TabUpgrade.fontPaintText;
                            final StringBuilder sb8 = new StringBuilder();
                            sb8.append(item.mItemOption[j].itemOption[n7].getOptionName());
                            sb8.append(": ");
                            final int width = fontPaintText7.getWidth(sb8.toString());
                            final int n9 = super.arrY[30];
                            final int[] arrH = super.arrH;
                            n5 = 15;
                            fontPaintText6.drawString(mGraphics, string, n8 + width, n9 + (arrH[15] + super.margin) * n6);
                            ++n6;
                        }
                    }
                    ++n7;
                }
                ++j;
            }
        }
    }

    private void paintRightTab0(final mGraphics mGraphics) {
        if (this.itemUpgrade == null) {
            TabUpgrade.fontPaintText.drawString(mGraphics, SupportTranslate.getTextLangue("notInfo"), super.arrX[8], super.arrY[8], 2);
        }
        else {
            CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.lvInfo, super.arrX[11], super.arrY[11], super.arrW[11], false, false);
            TabUpgrade.fontPaintLvCurent.drawString(mGraphics, SupportTranslate.getTextLangue("lvCurrent"), super.arrW[11] / 2 + super.arrX[11], super.arrH[11] / 2 + super.arrY[11], 3);
            mGraphics.drawImage(LoadDataManager.imgBgTagUpgrade, (float)super.arrX[12], (float)super.arrY[12]);
            if (this.isMaxLevelUpgrade()) {
                final mFont fontPaintText = TabUpgrade.fontPaintText;
                final StringBuilder sb = new StringBuilder();
                sb.append(SupportTranslate.getTextLangue("level"));
                sb.append(" ");
                sb.append(this.itemUpgrade.lvUpgrade);
                sb.append(" (Cấp tối đa)");
                fontPaintText.drawString(mGraphics, sb.toString(), super.arrX[16] - super.margin * 2 + super.arrW[12] / 2, super.arrY[16], 3);
            }
            else {
                final mFont fontPaintText2 = TabUpgrade.fontPaintText;
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(SupportTranslate.getTextLangue("level"));
                sb2.append(" ");
                sb2.append(this.itemUpgrade.lvUpgrade);
                fontPaintText2.drawString(mGraphics, sb2.toString(), super.arrX[16], super.arrY[16], 4);
                mGraphics.drawImage(LoadDataManager.imgChangeBigUpgrade, (float)super.arrX[17], (float)super.arrY[17]);
                final mFont fontPaintText3 = TabUpgrade.fontPaintText;
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(SupportTranslate.getTextLangue("level"));
                sb3.append(" ");
                sb3.append(this.itemUpgrade.lvUpgrade + 1);
                fontPaintText3.drawString(mGraphics, sb3.toString(), super.arrX[18], super.arrY[18], 4);
                mGraphics.drawImage(LoadDataManager.imgLineUpgrade, (float)super.arrX[13], (float)super.arrY[13]);
                this.paintItemInfo(mGraphics, this.itemUpgrade);
            }
        }
    }

    private void paintRightTab1(final mGraphics mGraphics) {
        if (this.itemUpgrade == null) {
            TabUpgrade.fontPaintText.drawString(mGraphics, SupportTranslate.getTextLangue("notInfo"), super.arrX[8], super.arrY[8], 2);
        }
        else {
            CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.lvInfo, super.arrX[11], super.arrY[11], super.arrW[11], false, false);
            TabUpgrade.fontPaintLvCurent.drawString(mGraphics, SupportTranslate.getTextLangue("lvCurrent"), super.arrW[11] / 2 + super.arrX[11], super.arrH[11] / 2 + super.arrY[11], 3);
            mGraphics.drawImage(LoadDataManager.imgBgTagUpgrade, (float)super.arrX[12], (float)super.arrY[12]);
            this.numStar = this.itemUpgrade.levelRefined;
            int n = 0;
            int numStar;
            while (true) {
                numStar = this.numStar;
                if (n >= numStar) {
                    break;
                }
                final Image imgStarUpgrade = LoadDataManager.imgStarUpgrade;
                final int n2 = super.arrX[25];
                final int n3 = super.arrW[25];
                mGraphics.drawImage(imgStarUpgrade, (float)(n2 + (4 - numStar) * (n3 + 2) / 2 + (n3 + 2) * n), (float)super.arrY[25]);
                ++n;
            }
            if (numStar < 5) {
                mGraphics.drawImage(LoadDataManager.imgChangeBigUpgrade, (float)(super.arrX[16] + (super.arrW[25] + 2) * 4 + super.margin), (float)super.arrY[17]);
                int n4 = 0;
                while (true) {
                    final int numStar2 = this.numStar;
                    if (n4 >= numStar2 + 1) {
                        break;
                    }
                    final Image imgStarUpgrade2 = LoadDataManager.imgStarUpgrade;
                    final int n5 = super.arrX[26];
                    final int n6 = super.arrW[25];
                    mGraphics.drawImage(imgStarUpgrade2, (float)(n5 + (5 - numStar2) * (n6 + 2) / 2 + (n6 + 2) * n4), (float)super.arrY[26]);
                    ++n4;
                }
            }
            mGraphics.drawImage(LoadDataManager.imgLineUpgrade, (float)super.arrX[13], (float)super.arrY[13]);
            this.paintItemInfo(mGraphics, this.itemUpgrade);
        }
    }

    private void paintRightTab2(final mGraphics mGraphics) {
        CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameXam2, (float)super.arrX[9], (float)super.arrY[9], (float)super.arrW[9], (float)super.arrH[9], 2, false);
        final int n = super.arrX[9];
        final int n2 = super.arrY[9];
        final int margin = super.margin;
        mGraphics.setClip(n, n2 + margin, super.arrW[9], super.arrH[9] - margin * 2);
        final ScrollY scroll = super.scroll;
        if (scroll != null) {
            mGraphics.translate(0, -scroll.cmy);
        }
        for (int i = 0; i < this.numNgangBox; ++i) {
            for (int j = 0; j < this.maxDocBox; ++j) {
                final int n3 = this.numNgangBox * j + i;
                final SupportPaint paintz = CanvasNinja.paintz;
                final FrameImage frameBoxHanhTrangDefault = LoadDataManager.frameBoxHanhTrangDefault;
                final int n4 = super.arrX[10];
                final int margin2 = super.margin2;
                final int n5 = super.arrW[10];
                final int n6 = super.arrY[10];
                final int n7 = super.arrH[10];
                final int indexSelected = this.indexSelected;
                final boolean b = true;
                paintz.paintTagFrame(mGraphics, frameBoxHanhTrangDefault, (margin2 + n5) * i + n4, n6 + (margin2 + n7) * j, n5, indexSelected != -1 && indexSelected == n3, 0, true);
                if (this.isInRangeVecItem(n3)) {
                    if (this.vecRefineds.get(n3).rarity != -1) {
                        final SupportPaint paintz2 = CanvasNinja.paintz;
                        final FrameImage frameImage = LoadDataManager.frameBoxHanhTrang[this.vecRefineds.get(n3).rarity];
                        final int n8 = super.arrX[10];
                        final int margin3 = super.margin2;
                        final int n9 = super.arrW[10];
                        final int n10 = super.arrY[10];
                        final int n11 = super.arrH[10];
                        final int indexSelected2 = this.indexSelected;
                        paintz2.paintTagFrame(mGraphics, frameImage, (margin3 + n9) * i + n8, n10 + (margin3 + n11) * j, n9, indexSelected2 != -1 && indexSelected2 == n3 && b, 0, true);
                    }
                    final Item item = this.vecRefineds.get(n3);
                    final int n12 = super.arrX[10];
                    final int margin4 = super.margin2;
                    final int n13 = super.arrW[10];
                    final int n14 = super.arrY[10];
                    final int n15 = super.arrH[10];
                    item.paintCenter(mGraphics, (margin4 + n13) * i + n12, n14 + (margin4 + n15) * j, n13, n15, true, true);
                }
            }
        }
    }

    private void paintRightTab3(final mGraphics mGraphics) {
        CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameXam2, (float)super.arrX[9], (float)super.arrY[9], (float)super.arrW[9], (float)super.arrH[9], 2, false);
        final int n = super.arrX[9];
        final int n2 = super.arrY[9];
        final int margin = super.margin;
        mGraphics.setClip(n, n2 + margin, super.arrW[9], super.arrH[9] - margin * 2);
        final ScrollY scroll = super.scroll;
        if (scroll != null) {
            mGraphics.translate(0, -scroll.cmy);
        }
        for (int i = 0; i < this.numNgangBox; ++i) {
            for (int j = 0; j < this.maxDocBox; ++j) {
                final int n3 = this.numNgangBox * j + i;
                final SupportPaint paintz = CanvasNinja.paintz;
                final FrameImage frameBoxHanhTrangDefault = LoadDataManager.frameBoxHanhTrangDefault;
                final int n4 = super.arrX[10];
                final int margin2 = super.margin2;
                final int n5 = super.arrW[10];
                final int n6 = super.arrY[10];
                final int n7 = super.arrH[10];
                final int indexSelected = this.indexSelected;
                final boolean b = true;
                paintz.paintTagFrame(mGraphics, frameBoxHanhTrangDefault, (margin2 + n5) * i + n4, n6 + (margin2 + n7) * j, n5, indexSelected != -1 && indexSelected == n3, 0, true);
                if (this.isInRangeVecItem(n3)) {
                    if (this.vecRefineds.get(n3).rarity != -1) {
                        final SupportPaint paintz2 = CanvasNinja.paintz;
                        final FrameImage frameImage = LoadDataManager.frameBoxHanhTrang[this.vecRefineds.get(n3).rarity];
                        final int n8 = super.arrX[10];
                        final int margin3 = super.margin2;
                        final int n9 = super.arrW[10];
                        final int n10 = super.arrY[10];
                        final int n11 = super.arrH[10];
                        final int indexSelected2 = this.indexSelected;
                        paintz2.paintTagFrame(mGraphics, frameImage, (margin3 + n9) * i + n8, n10 + (margin3 + n11) * j, n9, indexSelected2 != -1 && indexSelected2 == n3 && b, 0, true);
                    }
                    final Item item = this.vecRefineds.get(n3);
                    final int n12 = super.arrX[10];
                    final int margin4 = super.margin2;
                    final int n13 = super.arrW[10];
                    final int n14 = super.arrY[10];
                    final int n15 = super.arrH[10];
                    item.paintCenter(mGraphics, (margin4 + n13) * i + n12, n14 + (margin4 + n15) * j, n13, n15, true, true);
                }
            }
        }
    }

    private void selectTab(int n) {
        this.clearTab();
        this.indexTabIconSelected = n;
        this.selectBox = null;
        super.textTitle = SupportTranslate.getArrayLangue("upgradeExtractRefined")[n].toUpperCase();
        final MyButton[] btns = { null };
        super.btns = btns;
        final FrameImage frameImage = LoadDataManager.myButtons[4];
        btns[0] = new MyButton(frameImage, frameImage, 55, 0, SupportTranslate.getArrayLangue("upgradeExtractRefined")[n], 0, 0, this.cmds[n]);
        final MyButton myButton = super.btns[0];
        final int xStart = this.xStart;
        final int n2 = (this.wLeft - myButton.w) / 2;
        final int yStart = this.yStart;
        final int margin = super.margin;
        final int[] arrH = super.arrH;
        myButton.setXY(xStart + n2, yStart + margin * 4 + arrH[1] + arrH[2] + arrH[3] + arrH[5] + arrH[6] + 3 + this.marginEach * 4);
        super.btns[0].isDisable = true;
        final int width = TabScr.fontPaintTile.getWidth(super.textTitle);
        final int margin2 = super.margin;
        final int wTitle = width + margin2 * 6;
        super.wTitle = wTitle;
        final int hTitle = (int)LoadDataManager.frameTitle.frameHeight;
        super.hTitle = hTitle;
        super.xTitle = super.x + (super.w - wTitle) / 2;
        super.yTitle = super.y - hTitle / 2;
        if (n == 2) {
            this.numBoxNeed = 3;
            final int maxDocBox = this.maxDocBox;
            final int[] arrH2 = super.arrH;
            final int n3 = arrH2[10];
            final int margin3 = super.margin2;
            n = super.arrX[9];
            final int n4 = super.arrY[9];
            final int n5 = super.arrW[9];
            final int n6 = arrH2[9];
            this.initScroll(n, n4, n5, n6, n3 * maxDocBox + (maxDocBox - 1) * margin3, n6 - margin2 * 2);
        }
        else {
            this.numBoxNeed = 2;
            super.scroll = null;
        }
        final int numBoxNeed = this.numBoxNeed;
        final int[] arrW = super.arrW;
        if (numBoxNeed > 1) {
            n = arrW[5] * numBoxNeed + (numBoxNeed - 1) * (super.margin * 4);
        }
        else {
            n = arrW[5] * numBoxNeed;
        }
        this.wBoxNeed = n;
        n = this.maxBoxNeed;
        if (numBoxNeed > n) {
            this.wBoxNeed = super.arrW[5] * n + (n - 1) * (super.margin * 4);
        }
        this.numCur = new String[numBoxNeed];
        this.numNeed = new String[numBoxNeed];
        this.itemNeed = new Item[numBoxNeed];
        this.isLost = new boolean[numBoxNeed];
        final int[] arrX = super.arrX;
        final int xStart2 = this.xStart;
        final int wLeft = this.wLeft;
        n = this.wBoxNeed;
        arrX[5] = (wLeft - n) / 2 + xStart2;
        arrX[6] = xStart2 + (wLeft - n) / 2 + super.arrW[5] / 2;
    }

    @Override
    public void commandTab(final int n, final int n2) {
        super.commandTab(n, n2);
        final StringBuilder sb = new StringBuilder();
        sb.append("Vào command Tab Tab TabUpgrade:");
        sb.append(n);
        Res.outz(1, sb.toString());
        if (!this.isActive()) {
            return;
        }
        switch (n) {
            case 3: {
                this.isSendUp = true;
                SendMessage.gI().upgradeItem((byte)5, this.itemUpgrade.id);
                break;
            }
            case 2: {
                this.isSendUp = true;
                SendMessage.gI().upgradeItem((byte)3, this.itemUpgrade.lvChietXuat + 1);
                break;
            }
            case 1: {
                this.isSendUp = true;
                SendMessage.gI().upgradeItem((byte)2, this.itemUpgrade.id);
                break;
            }
            case 0: {
                this.isSendUp = true;
                SendMessage.gI().upgradeItem((byte)1, this.itemUpgrade.id);
                break;
            }
        }
    }

    public Item getItemChietXuatByIdIcon(final int n) {
        for (final Item item : this.vecRefineds) {
            if (item.idTemplate == n) {
                return item;
            }
        }
        return null;
    }

    public void init() {
        this.clearTab();
        this.cmds = new MyCommand[4];
        int n = 0;
        while (true) {
            final MyCommand[] cmds = this.cmds;
            if (n >= cmds.length) {
                break;
            }
            cmds[n] = new MyCommand(SupportTranslate.getArrayLangue("upgradeExtractRefined")[n], n, this);
            ++n;
        }
        this.resetSelected();
        super.w = 526;
        final int margin = super.margin;
        this.marginEach = margin * 4;
        final int h = margin * 4 + 14 + TabUpgrade.fontPaintText.getHeight() * 2 + this.marginEach * 5 + 3 + (int)LoadDataManager.frameBoxUpgrade.frameHeight + LoadDataManager.imgBoxNeed.getRHeight() + TabUpgrade.fontPaintTextNguyenLieu.getHeight() + (int)LoadDataManager.myButtons[0].frameHeight;
        super.h = h;
        final int w = super.w;
        final int w2 = CanvasNinja.w;
        if (w > w2) {
            super.w = w2 - super.margin * 5 - (int)LoadDataManager.frameTabIconHanhTrang.frameWidth;
        }
        final int h2 = CanvasNinja.h;
        if (h > h2) {
            super.h = h2 - super.margin * 2;
        }
        this.initStart();
        final int n2 = super.w / 2;
        this.wRight = n2;
        this.wLeft = n2;
        super.arrH = new int[] { LoadDataManager.imgThaThu.getRHeight(), TabUpgrade.fontPaintText.getHeight(), (int)LoadDataManager.frameBoxUpgrade.frameHeight, TabUpgrade.fontPaintText.getHeight(), TabUpgrade.fontPaintText.getHeight(), LoadDataManager.imgBoxNeed.getRHeight(), TabUpgrade.fontPaintTextNguyenLieu.getHeight(), (int)LoadDataManager.frameTabIconHanhTrang.frameHeight, 0, super.h - super.margin * 10, (int)LoadDataManager.frameBoxHanhTrang[0].frameHeight, TabUpgrade.fontPaintLvCurent.getHeight(), LoadDataManager.imgBgTagUpgrade.getRHeight(), LoadDataManager.imgLineUpgrade.getRHeight(), TabUpgrade.fontPaintText.getHeight(), LoadDataManager.imgBgTagUpgrade.getRHeight(), TabUpgrade.fontPaintText.getHeight(), LoadDataManager.imgChangeBigUpgrade.getRHeight(), TabUpgrade.fontPaintText.getHeight(), TabUpgrade.fontPaintText.getHeight(), LoadDataManager.imgChangeUpgrade.getRHeight(), TabUpgrade.fontPaintText.getHeight(), TabUpgrade.fontPaintTextUp.getHeight(), LoadDataManager.imgUpUpgrade.getRHeight(), LoadDataManager.imgChatDown.getRHeight(), LoadDataManager.imgStarUpgrade.getRHeight() };
        final int n3 = this.wRight - 7 - super.margin * 6;
        final mFont fontPaintText = TabUpgrade.fontPaintText;
        final StringBuilder sb = new StringBuilder();
        sb.append(SupportTranslate.getTextLangue("percentSucess"));
        sb.append(": 100%");
        final int width = fontPaintText.getWidth(sb.toString());
        final int[] arrW = { LoadDataManager.imgThaThu.getRWidth(), TabUpgrade.fontPaintText.getWidth(SupportTranslate.getArrayLangue("chooseItemUpgradeExtractRefined")[this.indexTabIconSelected]), (int)LoadDataManager.frameBoxUpgrade.frameWidth, super.margin * 2 + width, 0, LoadDataManager.imgBoxNeed.getRWidth(), 0, (int)LoadDataManager.frameTabIconHanhTrang.frameWidth, 0, n3, (int)LoadDataManager.frameBoxHanhTrang[0].frameWidth, TabUpgrade.fontPaintLvCurent.getWidth(SupportTranslate.getTextLangue("lvCurrent")) + super.margin * 4, LoadDataManager.imgBgTagUpgrade.getRWidth(), LoadDataManager.imgLineUpgrade.getRWidth(), 0, this.wRight - super.margin * 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        final mFont fontPaintText2 = TabUpgrade.fontPaintText;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(SupportTranslate.getTextLangue("level"));
        sb2.append(" 100");
        arrW[16] = fontPaintText2.getWidth(sb2.toString());
        arrW[17] = LoadDataManager.imgChangeBigUpgrade.getRWidth();
        final mFont fontPaintText3 = TabUpgrade.fontPaintText;
        final StringBuilder sb3 = new StringBuilder();
        sb3.append(SupportTranslate.getTextLangue("level"));
        sb3.append(" 100");
        arrW[18] = fontPaintText3.getWidth(sb3.toString());
        arrW[19] = TabUpgrade.fontPaintText.getWidth("1000");
        arrW[20] = LoadDataManager.imgChangeUpgrade.getRWidth();
        arrW[21] = TabUpgrade.fontPaintText.getWidth("1000");
        arrW[22] = TabUpgrade.fontPaintText.getWidth("+10%");
        arrW[23] = LoadDataManager.imgUpUpgrade.getRWidth();
        arrW[24] = LoadDataManager.imgChatDown.getRWidth();
        arrW[25] = LoadDataManager.imgStarUpgrade.getRWidth();
        super.arrW = arrW;
        final int margin2 = super.margin2;
        final int n4 = arrW[10];
        final int numNgangBox = (n3 - margin2) / (margin2 + n4);
        this.numNgangBox = numNgangBox;
        this.sumWBoxNgang = n4 * numNgangBox + (numNgangBox - 1) * margin2;
        final int x = super.x;
        final int xStart = x + 7;
        this.xStart = xStart;
        this.numBoxNeed = 3;
        this.wBoxNeed = arrW[5] * 3 + (3 - 1) * (super.margin * 4);
        final int maxBoxNeed = this.maxBoxNeed;
        if (3 > maxBoxNeed) {
            this.wBoxNeed = arrW[5] * maxBoxNeed + (maxBoxNeed - 1) * (super.margin * 4);
        }
        this.numCur = new String[3];
        this.numNeed = new String[3];
        this.itemNeed = new Item[3];
        this.isLost = new boolean[3];
        final int wLeft = this.wLeft;
        final int margin3 = super.margin;
        final int xRight = xStart + wLeft + margin3;
        this.xRight = xRight;
        this.numPaintStMax = 4;
        final int[] arrH = super.arrH;
        this.hOneSatThuong = arrH[14] + arrH[15] + margin3 * 2;
        final int n5 = xRight + margin3 * 5;
        final int n6 = (wLeft - arrW[0]) / 2;
        final int n7 = wLeft / 2;
        final int n8 = (wLeft - arrW[2]) / 2;
        final int n9 = (wLeft - arrW[3]) / 2;
        final int n10 = (wLeft - width) / 2;
        final int wBoxNeed = this.wBoxNeed;
        final int n11 = (wLeft - wBoxNeed) / 2;
        final int n12 = (wLeft - wBoxNeed) / 2;
        final int n13 = arrW[5] / 2;
        final int w3 = super.w;
        final int wRight = this.wRight;
        final int n14 = wRight / 2;
        final int n15 = (wRight - margin3 * 6) / 2;
        final int n16 = arrW[13] / 2;
        final int n17 = arrW[16];
        final int n18 = arrW[17];
        final int n19 = arrW[19];
        final int n20 = arrW[20];
        final int n21 = arrW[21];
        final int n22 = arrW[22];
        final int n23 = arrW[12];
        final int n24 = arrW[24];
        final int n25 = arrW[14];
        super.arrX = new int[] { xStart + n6, xStart + n7, xStart + n8, xStart + n9, xStart + n10, xStart + n11, n12 + xStart + n13, x + w3 - margin3 * 4 + 1, xStart + wLeft + n14, xRight, xRight + margin3, n5, n5, n5 + n15 - n16, n5, n5, n5 + margin3 * 2, n5 + margin3 * 2 + n17 + margin3 * 2, n5 + margin3 * 2 + n17 + n18 + margin3 * 5, n5 + margin3 * 4, n5 + n19 + margin3 * 3, n5 + n19 + n20 + margin3 * 6, n5 + n19 + n20 + n21 + margin3 * 7, n5 + n19 + n20 + n21 + n22 + margin3 * 10, n5 + n23 - n24, n5 + margin3 * 2, n5 + margin3 * 2 + n17 + n18 + margin3 * 3, n5 + margin3 * 2 + n25, n5 + margin3 * 3 + n25 + n20, n5 + margin3 + n25, n5 + margin3 * 2 + n25 };
        final int yStart = super.y + 7;
        this.yStart = yStart;
        this.yRight = margin3 * 5 + yStart;
        final int n26 = yStart + margin3 * 4;
        final int h3 = super.h;
        final int n27 = arrH[12];
        final int n28 = arrH[13];
        LoadDataManager.imgChatDown.getRHeight();
        final int[] arrH2 = super.arrH;
        final int n29 = arrH2[11];
        final int n30 = arrH2[12];
        final int margin4 = super.margin;
        final int n31 = n29 + n26 + n30 + margin4 * 6 + arrH2[13];
        final int yStart2 = this.yStart;
        final int n32 = arrH2[1];
        final int marginEach = this.marginEach;
        final int n33 = arrH2[2];
        final int n34 = arrH2[3];
        final int n35 = (n34 - arrH2[4]) / 2;
        final int n36 = arrH2[5];
        final MyCommand cmdClose = super.cmdClose;
        final int y = cmdClose.y;
        final int h4 = cmdClose.h;
        final int margin5 = super.margin7;
        final int y2 = super.y;
        final int n37 = (super.h - TabUpgrade.fontPaintText.getHeight()) / 2;
        final int yRight = this.yRight;
        final int margin6 = super.margin;
        final int[] arrH3 = super.arrH;
        final int n38 = arrH3[11];
        final int n39 = arrH3[12];
        final int n40 = arrH3[14];
        final int n41 = n39 / 2;
        final int n42 = n39 / 2;
        final int n43 = arrH3[17] / 2;
        final int n44 = n39 / 2;
        final int n45 = arrH3[15];
        super.arrY = new int[] { yStart2 + 2, margin4 * 4 + yStart2, margin4 * 4 + yStart2 + n32 + marginEach, margin4 * 4 + yStart2 + n32 + n33 + marginEach * 2, margin4 * 4 + yStart2 + n32 + n33 + marginEach * 2 + n35 + 5, margin4 * 4 + yStart2 + n32 + n33 + n34 + marginEach * 3, yStart2 + margin4 * 4 + n32 + n33 + n34 + n36 + 3 + marginEach * 3, y + h4 + margin5 * 2, y2 + n37, yRight, yRight + margin6, n26, n26 + n38 + margin6 * 2, n26 + n38 + n39 + margin6 * 4, n31, n31 + n40 + margin6, n26 + n38 + margin6 * 2 + n41, n26 + n38 + margin6 * 2 + n42 - n43, n26 + n38 + margin6 * 2 + n44, n31 + margin6 + n40 + n45 / 2 - arrH3[19] / 2, n31 + margin6 + n40 + n45 / 2 - arrH3[20] / 2, n31 + margin6 + n40 + n45 / 2 - arrH3[21] / 2, n31 + margin6 + n40 + n45 / 2 - arrH3[22] / 2, n31 + margin6 + n40 + n45 / 2 - arrH3[23] / 2, super.y + super.h - 7 - margin6 * 2 - arrH3[24], n26 + n38 + margin6 * 2 + (n39 - arrH3[25]) / 2, n38 + n26 + margin6 * 2 + (n39 - arrH3[25]) / 2, (n45 - arrH3[20]) / 2 + n31, (n45 - n40) / 2 + n31, n31 + n45 + margin6, n31 + n45 + margin6 + (n45 - n40) / 2 };
        this.initSub();
        this.selectTab(this.indexTabIconSelected);
    }

    public boolean isUpgrading() {
        return this.isAnimUpgrade || this.textEffectAppear != null || this.isSendUp;
    }

    @Override
    public void keyPress(final int n) {
        if (n == KEY.KEY_V) {
            this.close();
        }
    }

    @Override
    public void keyTyped() {
    }

    @Override
    public void paint(final mGraphics mGraphics) {
        CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameBgOrange2_0, (float)super.x, (float)super.y, (float)super.w, (float)super.h, 0, false);
        mGraphics.drawImage(LoadDataManager.imgThaThu, (float)super.arrX[0], (float)super.arrY[0]);
        CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameTitle, super.xTitle, super.yTitle, super.wTitle, false, 0, false);
        TabScr.fontPaintTile.drawString(mGraphics, super.textTitle, super.wTitle / 2 + super.xTitle, super.hTitle / 2 + super.yTitle, 3);
        final Item itemUpgrade = this.itemUpgrade;
        if (itemUpgrade == null) {
            TabUpgrade.fontPaintText.drawString(mGraphics, SupportTranslate.getArrayLangue("chooseItemUpgradeExtractRefined")[this.indexTabIconSelected], super.arrX[1], super.arrY[1], 2);
        }
        else {
            TabUpgrade.fontPaintText.drawString(mGraphics, itemUpgrade.template.name, super.arrX[1], super.arrY[1], 2);
        }
        final FrameImage frameBoxUpgrade = LoadDataManager.frameBoxUpgrade;
        int n;
        if (this.itemUpgrade != null) {
            n = 0;
        }
        else {
            n = 1;
        }
        frameBoxUpgrade.drawFrame(n, (float)super.arrX[2], (float)super.arrY[2], mGraphics);
        CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameBGPercentSucess, super.arrX[3], super.arrY[3], super.arrW[3], false);
        final mFont fontPaintText = TabUpgrade.fontPaintText;
        final StringBuilder sb = new StringBuilder();
        sb.append(SupportTranslate.getTextLangue("percentSucess"));
        sb.append(": ");
        fontPaintText.drawString(mGraphics, sb.toString(), super.arrX[4], super.arrY[4]);
        final mFont fontPaintTextPercent = TabUpgrade.fontPaintTextPercent;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(this.percentSuccess);
        sb2.append("%");
        final String string = sb2.toString();
        final int n2 = super.arrX[4];
        final mFont fontPaintText2 = TabUpgrade.fontPaintText;
        final StringBuilder sb3 = new StringBuilder();
        sb3.append(SupportTranslate.getTextLangue("percentSucess"));
        sb3.append(": ");
        fontPaintTextPercent.drawString(mGraphics, string, n2 + fontPaintText2.getWidth(sb3.toString()), super.arrY[4]);
        for (int i = 0; i < this.numBoxNeed; ++i) {
            mGraphics.drawImage(LoadDataManager.imgBoxNeed, (float)(super.arrX[5] + (super.margin * 4 + super.arrW[5]) * i), (float)super.arrY[5]);
            if (this.itemNeed[i] != null) {
                final mFont fontPaintTextNguyenLieu = TabUpgrade.fontPaintTextNguyenLieu;
                final StringBuilder sb4 = new StringBuilder();
                sb4.append(this.numNeed[i]);
                sb4.append("/");
                sb4.append(this.numCur[i]);
                fontPaintTextNguyenLieu.drawString(mGraphics, sb4.toString(), (super.margin * 4 + super.arrW[5]) * i + super.arrX[6], super.arrY[6], 2);
                final Item item = this.itemNeed[i];
                final int n3 = super.arrX[5];
                final int margin = super.margin;
                final int n4 = super.arrW[5];
                item.paintCenter(mGraphics, (margin * 4 + n4) * i + n3, super.arrY[5], n4, super.arrH[5], false, false);
            }
            if (this.isLost[i]) {
                final Image imgBoxNeedLost = LoadDataManager.imgBoxNeedLost;
                final int n5 = super.arrX[5];
                final int margin2 = super.margin;
                final int n6 = super.arrW[5];
                mGraphics.drawImage(imgBoxNeedLost, (float)(n5 + (margin2 * 4 + n6) * i + (n6 - imgBoxNeedLost.getRWidth()) / 2), (float)(super.arrY[5] + (super.arrH[5] - LoadDataManager.imgBoxNeedLost.getRHeight()) / 2), true, 60);
            }
        }
        this.paintButton(mGraphics);
        CanvasNinja.resetTrans(mGraphics);
        for (int j = 0; j < this.isClickTagIcon.length; ++j) {
            CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameTabIconHanhTrang, super.arrX[7], this.yAnimTagIcon[j] + (super.arrY[7] + (super.arrH[7] + super.margin * 2) * j), super.arrW[7], this.indexTabIconSelected != j, 0, false);
            final FrameImage frameImage = LoadDataManager.frameIconTabUpgrades[j];
            if (frameImage != null) {
                int n7;
                if (this.indexTabIconSelected != j) {
                    n7 = 0;
                }
                else {
                    n7 = 1;
                }
                final float n8 = (float)super.arrX[7];
                final float n9 = (super.arrW[7] - frameImage.frameWidth) / 2.0f;
                final int margin3 = super.margin;
                final float n10 = (float)margin3;
                final int n11 = super.arrY[7];
                final int n12 = this.yAnimTagIcon[j];
                final int n13 = super.arrH[7];
                frameImage.drawFrame(n7, n8 + n9 + n10, n11 + n12 + (margin3 * 2 + n13) * j + (n13 - frameImage.frameHeight) / 2.0f - 1.0f, mGraphics);
            }
        }
        final SupportPaint paintz = CanvasNinja.paintz;
        final FrameImage frameVachDoc = LoadDataManager.frameVachDoc;
        final int x = super.x;
        final int wLeft = this.wLeft;
        final int y = super.y;
        final int margin4 = super.margin;
        final int h = super.h;
        paintz.paintLineVertical(mGraphics, frameVachDoc, wLeft + x, y + margin4, h - margin4 * 2, h, false);
        super.cmdClose.paintIconOnly(mGraphics);
        final int indexTabIconSelected = this.indexTabIconSelected;
        if (indexTabIconSelected == 0) {
            this.paintRightTab0(mGraphics);
        }
        else if (indexTabIconSelected == 1) {
            this.paintRightTab1(mGraphics);
        }
        else if (indexTabIconSelected == 2) {
            this.paintRightTab2(mGraphics);
        }
        else if (indexTabIconSelected == 3) {
            this.paintRightTab3(mGraphics);
        }
        CanvasNinja.resetTrans(mGraphics);
        if (this.isDark) {
            mGraphics.setColor(0, 0.25f);
            mGraphics.fillRect(0, 0, CanvasNinja.w, CanvasNinja.h);
        }
        final Item itemUpgrade2 = this.itemUpgrade;
        if (itemUpgrade2 != null) {
            itemUpgrade2.paintCenter(mGraphics, super.arrX[2], super.arrY[2], super.arrW[2], super.arrH[2], false, false);
        }
        if (this.isAnimUpgrade) {
            final FrameImage frameEffectUpgradeSuccess = LoadDataManager.frameEffectUpgradeSuccess;
            frameEffectUpgradeSuccess.drawFrame(this.indexUpgradeSuccess, super.arrX[2] + this.hardCodeEffUp + (super.arrW[2] - frameEffectUpgradeSuccess.frameWidth) / 2.0f, super.arrY[2] + (super.arrH[2] - frameEffectUpgradeSuccess.frameHeight) / 2.0f, mGraphics);
            int n14 = 0;
            while (true) {
                final int[] indexSubUpgradeSuccess = this.indexSubUpgradeSuccess;
                if (n14 >= indexSubUpgradeSuccess.length) {
                    break;
                }
                final FrameImage frameSubEffectUpgradeSuccess = LoadDataManager.frameSubEffectUpgradeSuccess;
                frameSubEffectUpgradeSuccess.drawFrame(indexSubUpgradeSuccess[n14], this.xSubEff[n14] - frameSubEffectUpgradeSuccess.frameWidth / 2.0f, this.ySubEff[n14] - frameSubEffectUpgradeSuccess.frameHeight / 2.0f, mGraphics);
                ++n14;
            }
        }
        final SelectBox selectBox = this.selectBox;
        if (selectBox != null) {
            selectBox.paint(mGraphics);
        }
        final PopupItemInfo popupItemInfo = this.popupItemInfo;
        if (popupItemInfo != null && popupItemInfo.isPaintPopup) {
            popupItemInfo.paint(mGraphics, false, true);
        }
        final TextEffectAppear textEffectAppear = this.textEffectAppear;
        if (textEffectAppear != null) {
            textEffectAppear.paint(mGraphics);
        }
    }

    public void reloadItemUpgrade() {
        if (this.itemUpgrade != null) {
            final int indexTabIconSelected = this.indexTabIconSelected;
            if (indexTabIconSelected != 0 && indexTabIconSelected != 1) {
                final int indexItemTab2 = this.indexItemTab2;
                if (indexItemTab2 != -1) {
                    final Item itemUpgrade = new Item(this.idKhoang[indexItemTab2]);
                    this.itemUpgrade = itemUpgrade;
                    itemUpgrade.lvChietXuat = this.indexItemTab2;
                    this.selectItem(itemUpgrade);
                }
            }
            else {
                final int indexItemTab3 = this.indexItemTab01;
                if (indexItemTab3 != -1) {
                    this.selectItem(this.vecItems.get(indexItemTab3));
                }
            }
        }
    }

    public void resetSelected() {
        this.indexTabIconSelected = 0;
    }

    public void selectItem(final Item itemUpgrade) {
        this.itemUpgrade = itemUpgrade;
        if (this.indexTabIconSelected == 0 && itemUpgrade != null) {
            final UpgradeTemplate value = UpgradeTemplates.get(itemUpgrade.lvUpgrade + 1);
            this.isNullTemplate = (value == null);
            if (value != null) {
                this.percentSuccess = value.rate;
                this.numBoxNeed = 2;
                final Item[] itemNeed = new Item[2];
                this.itemNeed = itemNeed;
                this.numCur = new String[2];
                this.numNeed = new String[2];
                this.isLost = new boolean[2];
                itemNeed[0] = new Item();
                if (value.moneyType == 1) {
                    this.itemNeed[0] = new Item(312);
                }
                if (value.moneyType == 3) {
                    this.itemNeed[0] = new Item(313);
                }
                if (value.moneyType == 2) {
                    this.itemNeed[0] = new Item(314);
                }
                final int[] moneyInven = Player.myCharz().getMoneyInven();
                this.numNeed[0] = Res.formatMoney2(value.money);
                final int n = moneyInven[mGiftData.partTypeServerToTypeClient(value.moneyType)];
                this.numCur[0] = Res.formatMoney2(n);
                this.isLost[0] = (n != 0 && n < value.money);
                this.itemNeed[1] = new Item(319);
                final Item item = this.itemNeed[1];
                final byte materialCount = value.materialCount;
                item.num = materialCount;
                this.numNeed[1] = Res.formatMoney2(materialCount);
                this.numCur[1] = Res.formatMoney2(this.numThienThach);
                this.isLost[1] = (this.numThienThach < value.materialCount);
            }
        }
        if (this.indexTabIconSelected == 1 && itemUpgrade != null) {
            final TinhLuyenTemplate value2 = TinhLuyenTemplates.get(itemUpgrade.levelRefined + 1);
            this.isNullTemplate = (value2 == null);
            if (value2 != null) {
                this.percentSuccess = value2.rate;
                this.numBoxNeed = 2;
                final Item[] itemNeed2 = new Item[2];
                this.itemNeed = itemNeed2;
                this.numCur = new String[2];
                this.numNeed = new String[2];
                this.isLost = new boolean[2];
                itemNeed2[0] = new Item();
                if (value2.moneyType == 1) {
                    this.itemNeed[0] = new Item(312);
                }
                if (value2.moneyType == 3) {
                    this.itemNeed[0] = new Item(313);
                }
                if (value2.moneyType == 2) {
                    this.itemNeed[0] = new Item(314);
                }
                final int[] moneyInven2 = Player.myCharz().getMoneyInven();
                this.numNeed[0] = Res.formatMoney2(value2.money);
                final int n2 = moneyInven2[mGiftData.partTypeServerToTypeClient(value2.moneyType)];
                this.numCur[0] = Res.formatMoney2(n2);
                this.isLost[0] = (n2 != 0 && n2 < value2.money);
                this.itemNeed[1] = new Item(value2.material.idIcon);
                this.numNeed[1] = Res.formatMoney2(value2.material.num);
                int num = 0;
                final Item itemChietXuatByIdIcon = this.getItemChietXuatByIdIcon(value2.material.idIcon);
                if (itemChietXuatByIdIcon != null) {
                    num = itemChietXuatByIdIcon.num;
                }
                this.numCur[1] = Res.formatMoney2(num);
                this.isLost[1] = (num < value2.material.num);
            }
        }
        if (this.indexTabIconSelected == 2) {
            final ChietXuatTemplate value3 = ChietXuatTemplates.get(itemUpgrade.lvChietXuat + 1);
            this.isNullTemplate = (value3 == null);
            if (value3 != null) {
                this.percentSuccess = value3.rate;
                final Material[] materials = value3.materials;
                int numBoxNeed;
                if (materials != null) {
                    numBoxNeed = materials.length + 1;
                }
                else {
                    numBoxNeed = 1;
                }
                this.numBoxNeed = numBoxNeed;
                int wBoxNeed;
                if (numBoxNeed > 1) {
                    wBoxNeed = super.arrW[5] * numBoxNeed + (numBoxNeed - 1) * (super.margin * 4);
                }
                else {
                    wBoxNeed = numBoxNeed * super.arrW[5];
                }
                this.wBoxNeed = wBoxNeed;
                final int maxBoxNeed = this.maxBoxNeed;
                if (numBoxNeed > maxBoxNeed) {
                    this.wBoxNeed = super.arrW[5] * maxBoxNeed + (maxBoxNeed - 1) * (super.margin * 4);
                }
                final int[] arrX = super.arrX;
                final int xStart = this.xStart;
                final int wLeft = this.wLeft;
                final int wBoxNeed2 = this.wBoxNeed;
                arrX[5] = xStart + (wLeft - wBoxNeed2) / 2;
                arrX[6] = xStart + (wLeft - wBoxNeed2) / 2 + super.arrW[5] / 2;
                final Item[] itemNeed3 = new Item[numBoxNeed];
                this.itemNeed = itemNeed3;
                this.numCur = new String[numBoxNeed];
                this.numNeed = new String[numBoxNeed];
                this.isLost = new boolean[numBoxNeed];
                itemNeed3[0] = new Item();
                if (value3.moneyType == 1) {
                    this.itemNeed[0] = new Item(312);
                }
                if (value3.moneyType == 3) {
                    this.itemNeed[0] = new Item(313);
                }
                if (value3.moneyType == 2) {
                    this.itemNeed[0] = new Item(314);
                }
                final int[] moneyInven3 = Player.myCharz().getMoneyInven();
                this.numNeed[0] = Res.formatMoney2(value3.money);
                final int n3 = moneyInven3[mGiftData.partTypeServerToTypeClient(value3.moneyType)];
                this.numCur[0] = Res.formatMoney2(n3);
                this.isLost[0] = (n3 < value3.money);
                if (value3.materials != null) {
                    int n4 = 0;
                    while (true) {
                        final Material[] materials2 = value3.materials;
                        if (n4 >= materials2.length) {
                            break;
                        }
                        this.itemNeed[n4 + 1] = new Item(materials2[n4].idIcon);
                        this.numNeed[n4 + 1] = Res.formatMoney2(value3.materials[n4].num);
                        int n5 = 0;
                        final short idIcon = value3.materials[n4].idIcon;
                        if (idIcon == 320) {
                            n5 = this.numBuaChietXuat;
                        }
                        final Item itemChietXuatByIdIcon2 = this.getItemChietXuatByIdIcon(idIcon);
                        if (itemChietXuatByIdIcon2 != null) {
                            n5 = itemChietXuatByIdIcon2.num;
                        }
                        this.numCur[n4 + 1] = Res.formatMoney2(n5);
                        this.isLost[n4 + 1] = (n5 < value3.materials[n4].num);
                        ++n4;
                    }
                }
            }
        }
        if (this.indexTabIconSelected == 3) {
            final NangCapDaTemplate value4 = NangCapDaTemplates.get(itemUpgrade.idTemplate);
            this.isNullTemplate = (value4 == null);
            if (value4 != null) {
                this.percentSuccess = value4.rate;
                final Material[] materials3 = value4.materials;
                int numBoxNeed2;
                if (materials3 != null) {
                    numBoxNeed2 = materials3.length + 1;
                }
                else {
                    numBoxNeed2 = 1;
                }
                this.numBoxNeed = numBoxNeed2;
                final int[] arrW = super.arrW;
                int wBoxNeed3;
                if (numBoxNeed2 > 1) {
                    wBoxNeed3 = arrW[5] * numBoxNeed2 + (numBoxNeed2 - 1) * (super.margin * 4);
                }
                else {
                    wBoxNeed3 = arrW[5] * numBoxNeed2;
                }
                this.wBoxNeed = wBoxNeed3;
                final int maxBoxNeed2 = this.maxBoxNeed;
                if (numBoxNeed2 > maxBoxNeed2) {
                    this.wBoxNeed = super.arrW[5] * maxBoxNeed2 + (maxBoxNeed2 - 1) * (super.margin * 4);
                }
                final int[] arrX2 = super.arrX;
                final int xStart2 = this.xStart;
                final int wLeft2 = this.wLeft;
                final int wBoxNeed4 = this.wBoxNeed;
                arrX2[5] = (wLeft2 - wBoxNeed4) / 2 + xStart2;
                arrX2[6] = xStart2 + (wLeft2 - wBoxNeed4) / 2 + super.arrW[5] / 2;
                final Item[] itemNeed4 = new Item[numBoxNeed2];
                this.itemNeed = itemNeed4;
                this.numCur = new String[numBoxNeed2];
                this.numNeed = new String[numBoxNeed2];
                this.isLost = new boolean[numBoxNeed2];
                itemNeed4[0] = new Item();
                if (value4.moneyType == 1) {
                    this.itemNeed[0] = new Item(312);
                }
                if (value4.moneyType == 3) {
                    this.itemNeed[0] = new Item(313);
                }
                if (value4.moneyType == 2) {
                    this.itemNeed[0] = new Item(314);
                }
                final int[] moneyInven4 = Player.myCharz().getMoneyInven();
                this.numNeed[0] = Res.formatMoney2(value4.money);
                final int n6 = moneyInven4[mGiftData.partTypeServerToTypeClient(value4.moneyType)];
                this.numCur[0] = Res.formatMoney2(n6);
                this.isLost[0] = (n6 < value4.money);
                if (value4.materials != null) {
                    int n7 = 0;
                    while (true) {
                        final Material[] materials4 = value4.materials;
                        if (n7 >= materials4.length) {
                            break;
                        }
                        this.itemNeed[n7 + 1] = new Item(materials4[n7].idIcon);
                        this.numNeed[n7 + 1] = Res.formatMoney2(value4.materials[n7].num);
                        int n8 = 0;
                        final short idIcon2 = value4.materials[n7].idIcon;
                        if (idIcon2 == 320) {
                            n8 = this.numBuaChietXuat;
                        }
                        if (idIcon2 == 349) {
                            n8 = this.numBuaNangCap;
                        }
                        final Item itemChietXuatByIdIcon3 = this.getItemChietXuatByIdIcon(idIcon2);
                        if (itemChietXuatByIdIcon3 != null) {
                            n8 = itemChietXuatByIdIcon3.num;
                        }
                        this.numCur[n7 + 1] = Res.formatMoney2(n8);
                        this.isLost[n7 + 1] = (n8 < value4.materials[n7].num);
                        ++n7;
                    }
                }
            }
        }
        this.onOffBtn(this.isActive());
    }

    @Override
    public void show() {
        this.init();
        this.showTab();
        TabUpgrade.me = this;
    }

    public void startEffUpgrade(final boolean isSuccess, final String notify) {
        this.initSub();
        if (isSuccess) {
            AudioManager.upgradeSuccess();
        }
        else {
            AudioManager.upgradeFailed();
        }
        this.isDark = true;
        this.isSuccess = isSuccess;
        this.notify = notify;
        this.isAnimUpgrade = true;
        this.indexUpgradeSuccess = 0;
    }

    @Override
    public void update() {
        this.yAnimChatDown = 0;
        if (this.isAnimUpgrade && CanvasNinja.gameTick % 2 == 0) {
            int n = 0;
            while (true) {
                final int[] indexSubUpgradeSuccess = this.indexSubUpgradeSuccess;
                if (n >= indexSubUpgradeSuccess.length) {
                    break;
                }
                final int[] ySubEff = this.ySubEff;
                if ((ySubEff[n] += this.speedYEff[n]) >= this.yTargetEff[n]) {
                    ySubEff[n] = this.ySubEffFirst[n];
                }
                if (++indexSubUpgradeSuccess[n] > LoadDataManager.frameSubEffectUpgradeSuccess.nFrame - 1) {
                    indexSubUpgradeSuccess[n] = 0;
                }
                ++n;
            }
            final int indexUpgradeSuccess = this.indexUpgradeSuccess + 1;
            this.indexUpgradeSuccess = indexUpgradeSuccess;
            final int nFrame = LoadDataManager.frameEffectUpgradeSuccess.nFrame;
            if (indexUpgradeSuccess > nFrame - 1) {
                this.indexUpgradeSuccess = 0;
                this.isAnimUpgrade = false;
            }
            if (this.indexUpgradeSuccess == nFrame - 3) {
                this.isDark = (this.isSuccess ^ true);
                final int indexTabIconSelected = this.indexTabIconSelected;
                if (indexTabIconSelected != 0 && indexTabIconSelected != 1) {
                    final int[] array = new int[2];
                    final int[] positionTarget = this.findPositionTarget();
                    FrameAtlas image;
                    if (this.isSuccess) {
                        image = this.itemUpgrade.getImage();
                    }
                    else {
                        image = null;
                    }
                    this.textEffectAppear = new TextEffectAppear(image, mFont.tahoma_7b_yellow_border_orange, LoadDataManager.frameBgTextAppear, this.notify, -1, (byte)1, positionTarget[0], positionTarget[1]);
                }
                else {
                    this.textEffectAppear = new TextEffectAppear(mFont.tahoma_7b_yellow_border_orange, LoadDataManager.frameBgTextAppear, this.notify, -1);
                }
            }
        }
        this.updateButton();
        final TextEffectAppear textEffectAppear = this.textEffectAppear;
        if (textEffectAppear != null) {
            textEffectAppear.update();
            if (this.textEffectAppear.isRemove) {
                this.isDark = false;
                Res.outz("RELOAD ITEM");
                if (this.isUpdateNum) {
                    this.isUpdateNum = false;
                    this.vecRefineds.removeAllElements();
                    this.vecRefineds.addAll(this.vecRefinedTemps);
                }
                this.reloadItemUpgrade();
                this.textEffectAppear = null;
            }
        }
        final PopupItemInfo popupItemInfo = this.popupItemInfo;
        if (popupItemInfo != null) {
            popupItemInfo.update();
        }
        this.updateScroll();
        int n2 = 0;
        while (true) {
            final boolean[] isClickTagIcon = this.isClickTagIcon;
            if (n2 >= isClickTagIcon.length) {
                break;
            }
            if (isClickTagIcon[n2]) {
                final int[] yAnimTagIcon = this.yAnimTagIcon;
                if (++yAnimTagIcon[n2] >= 2) {
                    yAnimTagIcon[n2] = 0;
                    isClickTagIcon[n2] = false;
                }
            }
            ++n2;
        }
    }

    @Override
    public void updateKey() {
        super.cmdClose.updateIconOnly();
        this.updatePointerButton();
        final PopupItemInfo popupItemInfo = this.popupItemInfo;
        if (popupItemInfo != null) {
            popupItemInfo.updatePointer();
        }
        if (CanvasNinja.isPointerRelease() && this.indexTabIconSelected == 2) {
            final SelectBox selectBox = this.selectBox;
            if (selectBox != null) {
                final int updatePointer = selectBox.updatePointer();
                if ((this.indexSelectedBox = updatePointer) != -1) {
                    this.indexItemTab2 = updatePointer;
                    final Item itemUpgrade = new Item(this.idKhoang[updatePointer]);
                    this.itemUpgrade = itemUpgrade;
                    itemUpgrade.lvChietXuat = this.indexItemTab2;
                    this.selectItem(itemUpgrade);
                    this.selectBox = null;
                }
            }
        }
        if (CanvasNinja.isPointerRelease()) {
            for (int i = 0; i < this.isClickTagIcon.length; ++i) {
                final int n = super.arrX[7];
                final int n2 = super.arrY[7];
                final int n3 = super.arrH[7];
                if (CanvasNinja.isPoint(n, n2 + (super.margin * 2 + n3) * i, super.arrW[7], n3)) {
                    AudioManager.buttonClick();
                    this.isClickTagIcon[i] = true;
                    if (i != this.indexTabIconSelected) {
                        this.selectTab(i);
                    }
                    CanvasNinja.clearAllPointer();
                }
            }
        }
        if (CanvasNinja.isPointerRelease() && CanvasNinja.isPoint(super.arrX[2], super.arrY[2], super.arrW[2], super.arrH[2])) {
            CanvasNinja.clearAllPointer();
            Res.outz("Thêm 1 item vũ khí có trong túi đồ");
            final int indexTabIconSelected = this.indexTabIconSelected;
            if (indexTabIconSelected == 3) {
                final SubTabInven gi = SubTabInven.gI();
                final Vector<Item> vecItems = new Vector<Item>();
                final int[] array2;
                final int[] array = array2 = new int[16];
                array2[0] = 341;
                array2[1] = 337;
                array2[2] = 333;
                array2[3] = 322;
                array2[4] = 342;
                array2[5] = 338;
                array2[6] = 334;
                array2[7] = 323;
                array2[8] = 343;
                array2[9] = 339;
                array2[10] = 335;
                array2[11] = 324;
                array2[12] = 344;
                array2[13] = 340;
                array2[14] = 336;
                array2[15] = 325;
                for (int j = 0; j < array.length; ++j) {
                    final Item item = new Item(array[j]);
                    item.id = array[j];
                    vecItems.add(item);
                }
                gi.vecItems = vecItems;
                gi.startSubItem();
            }
            else if (indexTabIconSelected != 2) {
                final SubTabInven gi2 = SubTabInven.gI();
                gi2.vecItems = this.vecItems;
                gi2.startSubInven();
                gi2.isPaintNum = false;
            }
            else {
                this.selectBox = new SelectBox(super.arrX[2] + super.arrW[2] / 2, super.arrY[2] + super.arrH[2] / 2, SupportTranslate.getArrayLangue("typeRefined"), this.indexSelectedBox);
            }
        }
    }
}
