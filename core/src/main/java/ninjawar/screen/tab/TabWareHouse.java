// Class Version: 8
package ninjawar.screen.tab;

import ninjawar.model.BoxInventory;
import java.util.Collection;
import ninjawar.scroll.ScrollY;
import ninjawar.supporter.SupportPaint;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.FrameImage;
import ninjawar.input.MyButton;
import ninjawar.supporter.LoadDataManager;
import ninjawar.screen.subtab.SubTabKeyWareHouse;
import ninjawar.myscr.MapScr;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.supporter.SupportTranslate;
import ninjawar.screen.dialog.InputDialog;
import ninjawar.myscr.Res;
import ninjawar.mymain.CanvasNinja;
import ninjawar.object.Item;
import java.util.Vector;
import ninjawar.model.PopupItemInfo;
import ninjawar.input.MyNextPrevious;
import ninjawar.mylib.mFont;

public class TabWareHouse extends TabScr
{
    public static TabWareHouse me;
    public int capacity;
    public mFont fontPaintPage;
    int indexSelectedInven;
    int indexSelectedWareHouse;
    int lastIndexSelectedInven;
    int lastIndexSelectedWareHouse;
    int maxDocBox;
    int maxDocBoxWareHouse;
    int maxNumPage1;
    int maxNumPage2;
    public short maxSlotWH;
    MyNextPrevious[] myNextPrevious;
    int numBoxInven;
    int numBoxWareHouse;
    int numNgangBox;
    int numNgangBoxWareHouse;
    int numPage1;
    int numPage2;
    public PopupItemInfo popupItemInfo;
    public PopupItemInfo popupItemWareHouse;
    public Vector<Item> vecItemInvens;
    Vector<String> vecTitles;
    public Vector<Item> vecWareHouses;
    public int wOne;
    public int x1;
    public int x2;
    public int xTrans;

    public TabWareHouse() {
        this.fontPaintPage = mFont.tahoma_7b_white;
        this.capacity = 100;
        this.numBoxInven = 100;
        this.indexSelectedInven = -1;
        this.maxDocBox = 20;
        this.numBoxWareHouse = 50;
        this.indexSelectedWareHouse = -1;
        this.maxDocBoxWareHouse = 20;
        this.lastIndexSelectedInven = -1;
        this.lastIndexSelectedWareHouse = -1;
        this.numPage1 = 1;
        this.numPage2 = 1;
        this.maxNumPage1 = 3;
        this.maxNumPage2 = 3;
        this.vecTitles = new Vector<String>();
        this.vecItemInvens = new Vector<Item>();
        this.vecWareHouses = new Vector<Item>();
        this.maxSlotWH = 30;
    }

    public static TabWareHouse gI() {
        if (TabWareHouse.me == null) {
            TabWareHouse.me = new TabWareHouse();
        }
        return TabWareHouse.me;
    }

    private String getPage(int n) {
        StringBuilder sb;
        if (n == 0) {
            sb = new StringBuilder();
            sb.append(this.numPage1);
            sb.append("/");
            n = this.maxNumPage1;
        }
        else {
            sb = new StringBuilder();
            sb.append(this.numPage2);
            sb.append("/");
            n = this.maxNumPage2;
        }
        sb.append(n);
        return sb.toString();
    }

    private boolean isInRangeClickTab() {
        return CanvasNinja.isPoint(super.arrX[0], super.arrY[0], super.arrW[0], super.arrH[0]);
    }

    private boolean isInRangeClickTab2() {
        return CanvasNinja.isPoint(super.arrX[0] + this.xTrans, super.arrY[0], super.arrW[0], super.arrH[0]);
    }

    private boolean isInRangeVec(final Vector<Item> vector, final int n) {
        return n < vector.size() && n >= 0 && vector.get(n) != null;
    }

    private boolean isInRangeVecItem(final int n) {
        return n < this.vecItemInvens.size() && n >= 0 && this.vecItemInvens.get(n) != null;
    }

    @Override
    public void commandTab(final int n, final int n2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Vào cmd tab ware house:");
        sb.append(n);
        Res.outz(sb.toString());
        super.commandTab(n, n2);
        switch (n) {
            default: {}
            case 1: {}
            case 41: {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("Lấy ra theo số lượng:");
                sb2.append(this.vecWareHouses.get(this.indexSelectedWareHouse).id);
                Res.outz(2, sb2.toString());
                final InputDialog inputDialog = new InputDialog();
                final MyCommand myCommand = new MyCommand(SupportTranslate.getTextLangue("ACCEPT"), 8, inputDialog);
                myCommand.subAction = this.vecWareHouses.get(this.indexSelectedWareHouse).id;
                inputDialog.startInputDlg(SupportTranslate.getTextLangue("INPUT_NUM"), 1, myCommand, true);
            }
            case 40: {
                SendMessage.gI().moveWareHouseToInventory(this.vecWareHouses.get(this.indexSelectedWareHouse).id, 1);
            }
            case 31: {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append("Cất vào theo số lượng:");
                sb3.append(this.vecItemInvens.get(this.indexSelectedInven).id);
                Res.outz(2, sb3.toString());
                final InputDialog inputDialog2 = new InputDialog();
                final MyCommand myCommand2 = new MyCommand(SupportTranslate.getTextLangue("ACCEPT"), 7, inputDialog2);
                myCommand2.subAction = this.vecItemInvens.get(this.indexSelectedInven).id;
                inputDialog2.startInputDlg(SupportTranslate.getTextLangue("INPUT_NUM"), 1, myCommand2, true);
            }
            case 30: {
                if (MapScr.gI().tutorial != null && MapScr.gI().tutorial.isStepThaoTacKho(3)) {
                    this.vecItemInvens.get(this.indexSelectedInven).isFocusTutorial = false;
                    MapScr.gI().tutorial.step = 4;
                }
                SendMessage.gI().moveItemToWareHouse(this.vecItemInvens.get(this.indexSelectedInven).id, 1);
            }
            case 3: {}
            case 2: {
                SubTabKeyWareHouse.gI().startSubTabBuyShop(super.x, super.y, super.w, super.h);
            }
        }
    }

    public void init() {
        super.w = 520;
        super.h = 275;
        final int w = CanvasNinja.w;
        if (520 > w) {
            super.w = w - super.margin * 5;
        }
        final int h = CanvasNinja.h;
        if (275 > h) {
            super.h = h - super.margin * 2;
        }
        this.initStart();
        final int w2 = super.w;
        final int margin = super.margin;
        final int wOne = (w2 - margin * 2) / 2;
        this.wOne = wOne;
        final int x = super.x;
        this.x1 = x;
        this.x2 = x + wOne + margin * 2;
        final float frameHeight = LoadDataManager.frameNextPreviousKhoDo[0].frameHeight;
        final int n = (int)frameHeight;
        final int n2 = (int)frameHeight;
        final int[] arrH = { super.h - margin * 4 - (n + margin * 4 + n2) - margin * 2 - (int)LoadDataManager.myButtons[0].frameHeight, (int)LoadDataManager.frameBoxHanhTrang[0].frameHeight, (int)LoadDataManager.frameNextPreviousKhoDo[0].frameHeight, (int)LoadDataManager.framePage.frameHeight, (int)LoadDataManager.frameNextPreviousKhoDo[0].frameHeight };
        super.arrH = arrH;
        final int[] arrW = { wOne - margin * 4 - margin * 2, (int)LoadDataManager.frameBoxHanhTrang[0].frameWidth, 0, 0, 0 };
        final FrameImage[] frameNextPreviousKhoDo = LoadDataManager.frameNextPreviousKhoDo;
        final FrameImage frameImage = frameNextPreviousKhoDo[0];
        final float frameWidth = frameImage.frameWidth;
        final int n3 = (int)frameWidth;
        arrW[2] = n3;
        final int n4 = (int)frameWidth + margin * 2;
        arrW[3] = n4;
        arrW[4] = (int)frameImage.frameHeight;
        super.arrW = arrW;
        final int n5 = x + (wOne - (margin * 2 + (n3 * 2 + n4))) / 2;
        final int[] arrX = { margin * 2 + x + margin, x + margin * 2 + margin * 2, n5, 0, 0 };
        final int n6 = arrW[2];
        arrX[3] = n5 + n6 + margin;
        arrX[4] = n6 + n5 + arrW[3] + margin * 2;
        super.arrX = arrX;
        final int y = super.y;
        final int n7 = arrH[0];
        final int n8 = y + margin * 2 + n2 + n7 + margin * 2;
        super.arrY = new int[] { y + margin * 2 + n2, y + margin * 3 + n2, n8, y + margin * 2 + n2 + n7 + margin * 2, y + margin * 2 + n2 + n7 + margin * 2 };
        this.xTrans = wOne + margin * 2;
        (this.myNextPrevious = new MyNextPrevious[4])[0] = new MyNextPrevious(frameNextPreviousKhoDo[1], arrX[2], n8, arrW[2], arrH[2], this.numPage1, this.maxNumPage1 - 1, 0, true, false);
        this.myNextPrevious[1] = new MyNextPrevious(LoadDataManager.frameNextPreviousKhoDo[1], super.arrX[4], super.arrY[4], super.arrW[4], super.arrH[4], this.numPage1, this.maxNumPage1 - 1, 0, true, true);
        this.myNextPrevious[2] = new MyNextPrevious(LoadDataManager.frameNextPreviousKhoDo[1], super.arrX[2] + this.xTrans, super.arrY[2], super.arrW[2], super.arrH[2], this.numPage2, this.maxNumPage2 - 1, 0, true, false);
        this.myNextPrevious[3] = new MyNextPrevious(LoadDataManager.frameNextPreviousKhoDo[1], super.arrX[4] + this.xTrans, super.arrY[4], super.arrW[4], super.arrH[4], this.numPage2, this.maxNumPage2 - 1, 0, true, true);
        this.vecTitles.add(SupportTranslate.getTextLangue("inventoryz").toUpperCase());
        this.vecTitles.add(SupportTranslate.getTextLangue("warehouse").toUpperCase());
        super.wTitle = TabScr.fontPaintTile.getWidth(Res.findStringMax(this.vecTitles)) + super.margin * 4;
        this.fixSizeTitle();
        final int[] arrW2 = super.arrW;
        final int n9 = arrW2[0];
        final int margin2 = super.margin2;
        this.numNgangBox = (n9 - margin2) / (margin2 + arrW2[1]);
        final int maxDocBox = TabInventory.gI().capacity / this.numNgangBox;
        this.maxDocBox = maxDocBox;
        final int[] arrW3 = super.arrW;
        final int n10 = arrW3[0];
        final int margin3 = super.margin2;
        final int numNgangBoxWareHouse = (n10 - margin3) / (arrW3[1] + margin3);
        this.numNgangBoxWareHouse = numNgangBoxWareHouse;
        this.maxDocBoxWareHouse = this.capacity / numNgangBoxWareHouse;
        final int[] arrH2 = super.arrH;
        final int n11 = arrH2[1];
        final int n12 = super.arrX[0];
        final int n13 = super.arrY[0];
        final int n14 = arrH2[0];
        this.initScroll(n12, n13, n10, n14, n11 * maxDocBox + (maxDocBox - 1) * margin3, n14 - super.margin * 2);
        final int maxDocBoxWareHouse = this.maxDocBoxWareHouse;
        final int[] arrH3 = super.arrH;
        final int n15 = arrH3[1];
        final int margin4 = super.margin2;
        final int n16 = super.arrX[0];
        final int xTrans = this.xTrans;
        final int n17 = super.arrY[0];
        final int n18 = super.arrW[0];
        final int n19 = arrH3[0];
        this.initScroll2(xTrans + n16, n17, n18, n19, n15 * maxDocBoxWareHouse + (maxDocBoxWareHouse - 1) * margin4, n19 - super.margin * 2);
        final Vector<String> vector = new Vector<String>();
        vector.removeAllElements();
        vector.add(SupportTranslate.getTextLangue("SORT"));
        vector.add(SupportTranslate.getTextLangue("inventory_Pass"));
        final int n20 = MyButton.FONT_DEFAULT.getWidth(Res.findStringMax(vector)) + 14;
        final MyButton[] btns = new MyButton[3];
        super.btns = btns;
        final mFont tahoma_7b_white = mFont.tahoma_7b_white;
        final FrameImage frameImage2 = LoadDataManager.myButtons[0];
        btns[0] = new MyButton(tahoma_7b_white, frameImage2, frameImage2, n20, 2, vector.get(0), super.arrX[0], super.arrY[2] + super.arrH[2] + super.margin * 2, new MyCommand("", 1, this));
        final MyButton[] btns2 = super.btns;
        final mFont tahoma_7b_white2 = mFont.tahoma_7b_white;
        final FrameImage frameImage3 = LoadDataManager.myButtons[0];
        btns2[1] = new MyButton(tahoma_7b_white2, frameImage3, frameImage3, n20, 2, vector.get(1), super.arrX[0] + this.xTrans, super.arrY[2] + super.arrH[2] + super.margin * 2, new MyCommand("", 2, this));
        final MyButton[] btns3 = super.btns;
        final mFont tahoma_7b_white3 = mFont.tahoma_7b_white;
        final FrameImage frameImage4 = LoadDataManager.myButtons[0];
        btns3[2] = new MyButton(tahoma_7b_white3, frameImage4, frameImage4, n20, 2, vector.get(0), super.arrX[0] + super.arrW[0] - n20 + this.xTrans, super.arrY[2] + super.arrH[2] + super.margin * 2, new MyCommand("", 3, this));
    }

    @Override
    public void keyPress(final int n) {
    }

    @Override
    public void keyTyped() {
    }

    @Override
    public void paint(final mGraphics mGraphics) {
        CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameBgOrange2_0, (float)this.x1, (float)super.y, (float)this.wOne, (float)super.h, 0, false);
        CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameBgOrange2_0, (float)(this.x1 + this.xTrans), (float)super.y, (float)this.wOne, (float)super.h, 0, false);
        super.cmdClose.paintIconOnly(mGraphics);
        final MyNextPrevious[] myNextPrevious = this.myNextPrevious;
        for (int length = myNextPrevious.length, i = 0; i < length; ++i) {
            myNextPrevious[i].paint(mGraphics);
        }
        final MyButton[] btns = super.btns;
        for (int length2 = btns.length, j = 0; j < length2; ++j) {
            btns[j].paint(mGraphics);
        }
        for (int k = 0; k < 2; ++k) {
            final SupportPaint paintz = CanvasNinja.paintz;
            final FrameImage frameTitle = LoadDataManager.frameTitle;
            final int x1 = this.x1;
            final int wOne = this.wOne;
            final int wTitle = super.wTitle;
            paintz.paintTagFrame(mGraphics, frameTitle, this.xTrans * k + (x1 + (wOne - wTitle) / 2), super.margin + (super.y - (int)frameTitle.frameHeight / 2), wTitle, false, 0, false);
            CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.framePage, this.xTrans * k + super.arrX[3], super.arrY[3], super.arrW[3], false, 0, false);
            this.fontPaintPage.drawString(mGraphics, this.getPage(k), this.xTrans * k + (super.arrX[3] + super.arrW[3] / 2), super.arrH[3] / 2 + super.arrY[3], 3);
            final mFont fontPaintTile = TabScr.fontPaintTile;
            final String s = this.vecTitles.get(k);
            final int x2 = this.x1;
            final int wTitle2 = super.wTitle;
            fontPaintTile.drawString(mGraphics, s, this.xTrans * k + (x2 + wTitle2 / 2 + (this.wOne - wTitle2) / 2), super.margin + super.y, 3);
            CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameXam2, (float)(super.arrX[0] + this.xTrans * k), (float)super.arrY[0], (float)super.arrW[0], (float)super.arrH[0], 2, true);
        }
        final int n = super.arrX[0];
        final int n2 = super.arrY[0];
        final int margin = super.margin;
        mGraphics.setClip(n, n2 + margin, super.arrW[0], super.arrH[0] - margin * 2);
        mGraphics.translate(0, -super.scroll.cmy);
        for (int l = 0; l < this.numNgangBox; ++l) {
            for (int n3 = 0; n3 < this.maxDocBox; ++n3) {
                final int n4 = this.numNgangBox * n3 + l;
                final SupportPaint paintz2 = CanvasNinja.paintz;
                final FrameImage frameBoxHanhTrangLocked = LoadDataManager.frameBoxHanhTrangLocked;
                final int n5 = super.arrX[1];
                final int margin2 = super.margin2;
                final int n6 = super.arrW[1];
                final int n7 = super.arrY[1];
                final int n8 = super.arrH[1];
                final int indexSelectedInven = this.indexSelectedInven;
                paintz2.paintTagFrame(mGraphics, frameBoxHanhTrangLocked, (margin2 + n6) * l + n5, n7 + (margin2 + n8) * n3, n6, indexSelectedInven != -1 && indexSelectedInven == n4, 0, true);
                if (n4 < TabInventory.gI().maxSlot) {
                    final SupportPaint paintz3 = CanvasNinja.paintz;
                    final FrameImage frameBoxHanhTrangDefault = LoadDataManager.frameBoxHanhTrangDefault;
                    final int n9 = super.arrX[1];
                    final int margin3 = super.margin2;
                    final int n10 = super.arrW[1];
                    final int n11 = super.arrY[1];
                    final int n12 = super.arrH[1];
                    final int indexSelectedInven2 = this.indexSelectedInven;
                    paintz3.paintTagFrame(mGraphics, frameBoxHanhTrangDefault, (margin3 + n10) * l + n9, n11 + n3 * (margin3 + n12), n10, indexSelectedInven2 != -1 && indexSelectedInven2 == n4, 0, true);
                }
                if (this.isInRangeVecItem(n4)) {
                    if (this.vecItemInvens.get(n4).rarity != -1) {
                        final SupportPaint paintz4 = CanvasNinja.paintz;
                        final FrameImage frameImage = LoadDataManager.frameBoxHanhTrang[this.vecItemInvens.get(n4).rarity];
                        final int n13 = super.arrX[1];
                        final int margin4 = super.margin2;
                        final int n14 = super.arrW[1];
                        final int n15 = super.arrY[1];
                        final int n16 = super.arrH[1];
                        final int indexSelectedInven3 = this.indexSelectedInven;
                        paintz4.paintTagFrame(mGraphics, frameImage, (margin4 + n14) * l + n13, n15 + n3 * (margin4 + n16), n14, indexSelectedInven3 != -1 && indexSelectedInven3 == n4, 0, true);
                    }
                    final Item item = this.vecItemInvens.get(n4);
                    final int n17 = super.arrX[1];
                    final int margin5 = super.margin2;
                    final int n18 = super.arrW[1];
                    final int n19 = super.arrY[1];
                    final int n20 = super.arrH[1];
                    item.paintCenter(mGraphics, (margin5 + n18) * l + n17, n19 + n3 * (margin5 + n20), n18, n20, true, true);
                }
                else if (n4 == TabInventory.gI().maxSlot) {
                    final SupportPaint paintz5 = CanvasNinja.paintz;
                    final FrameImage frameBoxHanhTrangAdd = LoadDataManager.frameBoxHanhTrangAdd;
                    final int n21 = super.arrX[1];
                    final int margin6 = super.margin2;
                    final int n22 = super.arrW[1];
                    final int n23 = super.arrY[1];
                    final int n24 = super.arrH[1];
                    final int indexSelectedInven4 = this.indexSelectedInven;
                    paintz5.paintTagFrame(mGraphics, frameBoxHanhTrangAdd, (margin6 + n22) * l + n21, n23 + n3 * (margin6 + n24), n22, indexSelectedInven4 != -1 && indexSelectedInven4 == n4, 0, true);
                }
            }
        }
        CanvasNinja.resetTrans(mGraphics);
        final int n25 = super.arrX[0];
        final int xTrans = this.xTrans;
        final int n26 = super.arrY[0];
        final int margin7 = super.margin;
        mGraphics.setClip(n25 + xTrans, n26 + margin7, super.arrW[0], super.arrH[0] - margin7 * 2);
        mGraphics.translate(0, -super.scroll2.cmy);
        for (int n27 = 0; n27 < this.numNgangBoxWareHouse; ++n27) {
            for (int n28 = 0; n28 < this.maxDocBoxWareHouse; ++n28) {
                final int n29 = this.numNgangBoxWareHouse * n28 + n27;
                final SupportPaint paintz6 = CanvasNinja.paintz;
                final FrameImage frameBoxHanhTrangLocked2 = LoadDataManager.frameBoxHanhTrangLocked;
                final int n30 = super.arrX[1];
                final int margin8 = super.margin2;
                final int n31 = super.arrW[1];
                final int xTrans2 = this.xTrans;
                final int n32 = super.arrY[1];
                final int n33 = super.arrH[1];
                final int indexSelectedWareHouse = this.indexSelectedWareHouse;
                paintz6.paintTagFrame(mGraphics, frameBoxHanhTrangLocked2, xTrans2 + (n30 + (margin8 + n31) * n27), n32 + (margin8 + n33) * n28, n31, indexSelectedWareHouse != -1 && indexSelectedWareHouse == n29, 0, true);
                if (n29 < this.maxSlotWH) {
                    final SupportPaint paintz7 = CanvasNinja.paintz;
                    final FrameImage frameBoxHanhTrangDefault2 = LoadDataManager.frameBoxHanhTrangDefault;
                    final int n34 = super.arrX[1];
                    final int margin9 = super.margin2;
                    final int n35 = super.arrW[1];
                    final int xTrans3 = this.xTrans;
                    final int n36 = super.arrY[1];
                    final int n37 = super.arrH[1];
                    final int indexSelectedWareHouse2 = this.indexSelectedWareHouse;
                    paintz7.paintTagFrame(mGraphics, frameBoxHanhTrangDefault2, xTrans3 + (n34 + (margin9 + n35) * n27), n36 + (margin9 + n37) * n28, n35, indexSelectedWareHouse2 != -1 && indexSelectedWareHouse2 == n29, 0, true);
                }
                if (this.isInRangeVec(this.vecWareHouses, n29)) {
                    if (this.vecWareHouses.get(n29).rarity != -1) {
                        final SupportPaint paintz8 = CanvasNinja.paintz;
                        final FrameImage frameImage2 = LoadDataManager.frameBoxHanhTrang[this.vecWareHouses.get(n29).rarity];
                        final int n38 = super.arrX[1];
                        final int margin10 = super.margin2;
                        final int n39 = super.arrW[1];
                        final int xTrans4 = this.xTrans;
                        final int n40 = super.arrY[1];
                        final int n41 = super.arrH[1];
                        final int indexSelectedWareHouse3 = this.indexSelectedWareHouse;
                        paintz8.paintTagFrame(mGraphics, frameImage2, xTrans4 + (n38 + (margin10 + n39) * n27), n40 + (margin10 + n41) * n28, n39, indexSelectedWareHouse3 != -1 && indexSelectedWareHouse3 == n29, 0, true);
                    }
                    final Item item2 = this.vecWareHouses.get(n29);
                    final int n42 = super.arrX[1];
                    final int margin11 = super.margin2;
                    final int n43 = super.arrW[1];
                    final int xTrans5 = this.xTrans;
                    final int n44 = super.arrY[1];
                    final int n45 = super.arrH[1];
                    item2.paintCenter(mGraphics, xTrans5 + (n42 + (margin11 + n43) * n27), n44 + (margin11 + n45) * n28, n43, n45, true, true);
                }
                else if (n29 == this.maxSlotWH) {
                    final SupportPaint paintz9 = CanvasNinja.paintz;
                    final FrameImage frameBoxHanhTrangAdd2 = LoadDataManager.frameBoxHanhTrangAdd;
                    final int n46 = super.arrX[1];
                    final int margin12 = super.margin2;
                    final int n47 = super.arrW[1];
                    final int xTrans6 = this.xTrans;
                    final int n48 = super.arrY[1];
                    final int n49 = super.arrH[1];
                    final int indexSelectedWareHouse4 = this.indexSelectedWareHouse;
                    paintz9.paintTagFrame(mGraphics, frameBoxHanhTrangAdd2, xTrans6 + (n46 + (margin12 + n47) * n27), n48 + (margin12 + n49) * n28, n47, indexSelectedWareHouse4 != -1 && indexSelectedWareHouse4 == n29, 0, true);
                }
            }
        }
        final PopupItemInfo popupItemInfo = this.popupItemInfo;
        if (popupItemInfo != null && popupItemInfo.isPaintPopup) {
            popupItemInfo.paint(mGraphics, false, true);
        }
        final PopupItemInfo popupItemWareHouse = this.popupItemWareHouse;
        if (popupItemWareHouse != null && popupItemWareHouse.isPaintPopup) {
            popupItemWareHouse.paint(mGraphics, false, true);
        }
    }

    @Override
    public void show() {
        this.init();
        this.showTab();
        TabWareHouse.me = this;
    }

    @Override
    public void update() {
        final PopupItemInfo popupItemInfo = this.popupItemInfo;
        if (popupItemInfo != null) {
            popupItemInfo.update();
        }
        final PopupItemInfo popupItemWareHouse = this.popupItemWareHouse;
        if (popupItemWareHouse != null) {
            popupItemWareHouse.update();
        }
        final MyNextPrevious[] myNextPrevious = this.myNextPrevious;
        if (myNextPrevious != null) {
            for (int length = myNextPrevious.length, i = 0; i < length; ++i) {
                myNextPrevious[i].update();
            }
        }
        final MyButton[] btns = super.btns;
        if (btns != null) {
            for (int length2 = btns.length, j = 0; j < length2; ++j) {
                btns[j].update();
            }
        }
        this.updateScroll();
        final ScrollY scroll = super.scroll;
        if (scroll.trans) {
            scroll.isHavePopup = false;
        }
        final ScrollY scroll2 = super.scroll2;
        if (scroll2.trans) {
            scroll2.isHavePopup = false;
        }
    }

    public void updateItem(final Vector<Item> vector, final Vector<Item> vector2) {
        this.vecItemInvens.removeAllElements();
        this.vecItemInvens.addAll(vector);
        this.vecWareHouses.removeAllElements();
        this.vecWareHouses.addAll(vector2);
        this.popupItemInfo = null;
        this.popupItemWareHouse = null;
    }

    @Override
    public void updateKey() {
        super.cmdClose.updateIconOnly();
        final PopupItemInfo popupItemInfo = this.popupItemInfo;
        if (popupItemInfo != null && popupItemInfo.isPaintPopup) {
            popupItemInfo.updatePointer();
        }
        final PopupItemInfo popupItemWareHouse = this.popupItemWareHouse;
        if (popupItemWareHouse != null && popupItemWareHouse.isPaintPopup) {
            popupItemWareHouse.updatePointer();
        }
        final MyButton[] btns = super.btns;
        if (btns != null) {
            for (int length = btns.length, i = 0; i < length; ++i) {
                btns[i].updatePointer();
            }
        }
        if (CanvasNinja.isPointerRelease()) {
            final boolean pointerRelease = CanvasNinja.isPointerRelease();
            final int n = 3;
            if (pointerRelease) {
                final MyNextPrevious[] myNextPrevious = this.myNextPrevious;
                if (myNextPrevious != null) {
                    int n2 = 0;
                    final int length2 = myNextPrevious.length;
                    int j = 0;
                    while (j < length2) {
                        final int updateSelected = myNextPrevious[j].updateSelected();
                        if (updateSelected != -1) {
                            CanvasNinja.clearAllPointer();
                            if (n2 < 2) {
                                this.numPage1 = updateSelected + 1;
                                final MyNextPrevious[] myNextPrevious2 = this.myNextPrevious;
                                myNextPrevious2[0].indexSelected = updateSelected;
                                myNextPrevious2[1].indexSelected = updateSelected;
                                break;
                            }
                            this.numPage2 = updateSelected + 1;
                            final MyNextPrevious[] myNextPrevious3 = this.myNextPrevious;
                            myNextPrevious3[2].indexSelected = updateSelected;
                            myNextPrevious3[3].indexSelected = updateSelected;
                            break;
                        }
                        else {
                            ++n2;
                            ++j;
                        }
                    }
                }
            }
            final boolean b = false;
            boolean b2 = false;
            boolean isHavePopup = b;
            Label_0778: {
                if (CanvasNinja.isPointerRelease()) {
                    isHavePopup = b;
                    if (this.isInRangeClickTab()) {
                        int n3 = 0;
                        int step = n;
                        Label_0248:
                        while (true) {
                            isHavePopup = b2;
                            if (n3 < this.numNgangBox) {
                                final int n4 = 0;
                                while (true) {
                                    for (int k = 0; k < this.maxDocBox; ++k) {
                                        final int indexSelectedInven = this.numNgangBox * k + n3;
                                        final int n5 = super.arrX[1];
                                        final int margin2 = super.margin2;
                                        final int n6 = super.arrW[1];
                                        final int n7 = super.arrY[1];
                                        final int n8 = super.arrH[1];
                                        if (CanvasNinja.isPoint(n5 + (margin2 + n6) * n3, n7 + (margin2 + n8) * k - super.scroll.cmy, n6, n8)) {
                                            CanvasNinja.clearAllPointer();
                                            this.indexSelectedInven = indexSelectedInven;
                                            if (this.isInRangeVec(this.vecItemInvens, indexSelectedInven)) {
                                                if (this.indexSelectedInven == this.lastIndexSelectedInven) {
                                                    this.popupItemInfo = null;
                                                    this.lastIndexSelectedInven = -1;
                                                    return;
                                                }
                                                this.popupItemWareHouse = null;
                                                this.popupItemInfo = new PopupItemInfo();
                                                this.lastIndexSelectedInven = this.indexSelectedInven;
                                                byte b3 = 1;
                                                if (indexSelectedInven > this.numNgangBox * k + 1) {
                                                    b3 = 2;
                                                }
                                                new BoxInventory();
                                                BoxInventory boxInventory;
                                                if (this.vecItemInvens.get(indexSelectedInven).rarity != -1) {
                                                    boxInventory = new BoxInventory(LoadDataManager.frameBoxHanhTrang[this.vecItemInvens.get(indexSelectedInven).rarity], this.vecItemInvens.get(indexSelectedInven));
                                                }
                                                else {
                                                    boxInventory = new BoxInventory(LoadDataManager.frameBoxHanhTrangDefault, this.vecItemInvens.get(indexSelectedInven));
                                                }
                                                if (MapScr.gI().tutorial != null && MapScr.gI().tutorial.isStepThaoTacKho(2) && this.vecItemInvens.get(indexSelectedInven).isFocusTutorial) {
                                                    MapScr.gI().tutorial.step = step;
                                                }
                                                final PopupItemInfo popupItemInfo2 = this.popupItemInfo;
                                                final int n9 = super.arrX[1];
                                                final int margin3 = super.margin2;
                                                final int n10 = super.arrW[1];
                                                final int n11 = n10 / 2;
                                                final int n12 = super.arrY[1];
                                                final int n13 = super.arrH[1];
                                                popupItemInfo2.startPopupItemInfo(n11 + (n9 + (margin3 + n10) * n3), n12 + (margin3 + n13) * k + n13 - super.scroll.cmy, b3, boxInventory, (byte)3);
                                                isHavePopup = true;
                                                final int n14 = 1;
                                                step = 3;
                                                if (n14 != 0) {
                                                    break Label_0778;
                                                }
                                                ++n3;
                                                b2 = isHavePopup;
                                                continue Label_0248;
                                            }
                                            else if (indexSelectedInven == TabInventory.gI().maxSlot) {
                                                final SendMessage gi = SendMessage.gI();
                                                final short maxSlot = TabInventory.gI().maxSlot;
                                                step = 3;
                                                gi.useItemInventory((byte)3, maxSlot + 1);
                                            }
                                            else {
                                                step = 3;
                                            }
                                        }
                                    }
                                    final int n14 = n4;
                                    isHavePopup = b2;
                                    continue;
                                }
                            }
                            break;
                        }
                    }
                }
            }
            super.scroll.isHavePopup = isHavePopup;
            if (CanvasNinja.isPointerRelease() && this.isInRangeClickTab2()) {
                int n15 = 0;
                int n16 = 0;
                int n17 = 0;
                Label_1265:
                while (true) {
                    n17 = n15;
                    if (n16 < this.numNgangBoxWareHouse) {
                        final int n18 = 0;
                        while (true) {
                            for (int l = 0; l < this.maxDocBoxWareHouse; ++l) {
                                final int indexSelectedWareHouse = this.numNgangBoxWareHouse * l + n16;
                                final int n19 = super.arrX[1];
                                final int margin4 = super.margin2;
                                final int n20 = super.arrW[1];
                                final int xTrans = this.xTrans;
                                final int n21 = super.arrY[1];
                                final int n22 = super.arrH[1];
                                if (CanvasNinja.isPoint(n19 + (margin4 + n20) * n16 + xTrans, n21 + (margin4 + n22) * l - super.scroll2.cmy, n20, n22)) {
                                    CanvasNinja.clearAllPointer();
                                    this.indexSelectedWareHouse = indexSelectedWareHouse;
                                    if (this.isInRangeVec(this.vecWareHouses, indexSelectedWareHouse)) {
                                        if (this.indexSelectedWareHouse == this.lastIndexSelectedWareHouse) {
                                            this.popupItemWareHouse = null;
                                            this.lastIndexSelectedWareHouse = -1;
                                            return;
                                        }
                                        this.popupItemInfo = null;
                                        this.popupItemWareHouse = new PopupItemInfo();
                                        this.lastIndexSelectedWareHouse = this.indexSelectedWareHouse;
                                        byte b4 = 1;
                                        if (indexSelectedWareHouse > this.numNgangBox * l + 1) {
                                            b4 = 2;
                                        }
                                        BoxInventory boxInventory2;
                                        if (this.vecWareHouses.get(indexSelectedWareHouse).rarity != -1) {
                                            boxInventory2 = new BoxInventory(LoadDataManager.frameBoxHanhTrang[this.vecWareHouses.get(indexSelectedWareHouse).rarity], this.vecWareHouses.get(indexSelectedWareHouse));
                                        }
                                        else {
                                            boxInventory2 = new BoxInventory(LoadDataManager.frameBoxHanhTrangDefault, this.vecWareHouses.get(indexSelectedWareHouse));
                                        }
                                        final PopupItemInfo popupItemWareHouse2 = this.popupItemWareHouse;
                                        final int n23 = super.arrX[1];
                                        final int margin5 = super.margin2;
                                        final int n24 = super.arrW[1];
                                        final int n25 = n24 / 2;
                                        final int xTrans2 = this.xTrans;
                                        final int n26 = super.arrY[1];
                                        final int n27 = super.arrH[1];
                                        popupItemWareHouse2.startPopupItemInfo(n23 + (margin5 + n24) * n16 + n25 + xTrans2, n26 + (l - 3) * (margin5 + n27) + n27 - super.scroll2.cmy, b4, boxInventory2, (byte)4);
                                        n15 = 1;
                                        final int n28 = 1;
                                        if (n28 != 0) {
                                            n17 = n15;
                                            break Label_1265;
                                        }
                                        ++n16;
                                        continue Label_1265;
                                    }
                                    else if (indexSelectedWareHouse == this.maxSlotWH) {
                                        SendMessage.gI().useItemInventory((byte)4, this.maxSlotWH + 1);
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                if (n17 == 0) {
                    this.popupItemWareHouse = null;
                }
            }
        }
        if (!super.scroll.isHavePopup) {
            this.popupItemInfo = null;
        }
    }
}
