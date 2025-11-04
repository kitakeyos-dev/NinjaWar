package ninjawar.screen.tab;

import java.util.Vector;
import ninjawar.input.MyButton;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.model.PartyInfo;
import ninjawar.model.PartySearch;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.screen.subtab.SubTabApplyParty;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportTranslate;

public class TabParty extends TabScr {
    public static TabParty me;
    public static PartyInfo partyInfo;
    public static Vector<PartySearch> vecPartySearchs = new Vector<>();
    public static Vector<PartySearch> vecPartySearchsTemp = new Vector<>();
    int hTitle;
    public int indexSelected = -1;
    int marginLeftRight;
    int marginOneTab;
    String title = "";
    int transOneTab;
    int wTitle;
    int xTitle;
    int yTitle;

    public static TabParty gI() {
        if (me == null) {
            me = new TabParty();
        }
        return me;
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(mgraphics, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        CanvasNinja.paintz.paintTagFrame(mgraphics, LoadDataManager.frameTitle2, this.xTitle, this.yTitle, this.wTitle, false, 0, false);
        TabScr.fontPaintTile.drawString(g, this.title, (this.wTitle / 2) + this.xTitle, (this.hTitle / 2) + this.yTitle, 3);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgCam, (float) this.arrX[0], (float) this.arrY[0], (float) this.arrW[0], (float) this.arrH[0], 8, false);
        for (int i = 0; i < 5; i++) {
            g.drawImage(LoadDataManager.imgNenCharNone, (float) (this.arrX[1] + (this.transOneTab * i)), (float) this.arrY[1]);
        }
        paintButton(g);
        CanvasNinja.resetTrans(g);
        this.cmdClose.paintIconOnly(g);
    }

    public void init() {
        this.margin = 5;
        this.marginLeftRight = 5 * 2;
        this.title = SupportTranslate.getTextLangue("group").toUpperCase();
        this.marginOneTab = this.margin;
        this.transOneTab = LoadDataManager.imgNenCharNone.getRWidth() + this.marginOneTab;
        int wNenCam = (LoadDataManager.imgNenCharNone.getRWidth() * 5) + (this.marginOneTab * 6);
        int hNenCam = LoadDataManager.imgNenCharNone.getRHeight() + (this.margin * 4);
        MyButton[] myButtonArr = new MyButton[2];
        this.btns = myButtonArr;
        FrameImage[] frameImageArr = LoadDataManager.myButtons;
        myButtonArr[0] = new MyButton(frameImageArr[1], frameImageArr[3], 55, 0, SupportTranslate.getTextLangue("apply"), 0, 0, new MyCommand("", 1, this));
        MyButton[] myButtonArr2 = this.btns;
        FrameImage[] frameImageArr2 = LoadDataManager.myButtons;
        myButtonArr2[1] = new MyButton(frameImageArr2[0], frameImageArr2[3], 55, 0, SupportTranslate.getTextLangue("apply"), 0, 0, new MyCommand("", 2, this));
        int i = (this.marginLeftRight * 2) + wNenCam + 14;
        this.w = i;
        int i2 = CanvasNinja.w;
        if (i > i2) {
            this.w = i2 - (this.margin * 5);
        }
        int i3 = hNenCam + 14 + (((int) LoadDataManager.frameTitle2.frameHeight) / 2);
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
        int width = TabScr.fontPaintTile.getWidth(this.title) + (this.margin * 8);
        this.wTitle = width;
        int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(8, width, LoadDataManager.frameTitle);
        this.wTitle = fixSizeTagFrameUp;
        this.xTitle = this.x + ((this.w - fixSizeTagFrameUp) / 2);
        this.yTitle = this.y - (this.hTitle / 2);
        this.arrH = new int[]{hNenCam, LoadDataManager.imgNenCharNone.getRHeight()};
        this.arrW = new int[]{wNenCam, LoadDataManager.imgNenCharNone.getRWidth()};
        int xStartLeft = this.x + 7 + this.marginLeftRight;
        int[] iArr = {xStartLeft, this.marginOneTab + xStartLeft};
        this.arrX = iArr;
        int i8 = this.y + 7 + (((int) LoadDataManager.frameTitle2.frameHeight) / 2);
        int i9 = this.margin;
        int yStart = i8 + (i9 * 2);
        this.arrY = new int[]{yStart, (i9 * 2) + yStart};
        this.btns[0].setXY(iArr[0], this.arrH[0] + yStart + (i9 * 2));
        MyButton myButton = this.btns[1];
        myButton.setXY((this.arrX[0] + this.arrW[0]) - myButton.w, this.arrY[0] + this.arrH[0] + (this.margin * 2));
    }

    public void update() {
        updateButton();
    }

    public void updateKey() {
        this.cmdClose.updateIconOnly();
        updatePointerButton();
        CanvasNinja.isPointerRelease();
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
        Res.outz("Vào commandTab TabParty:" + index);
        super.commandTab(index, subIndex);
        switch (index) {
            case 1:
                if (partyInfo == null) {
                    SendMessage.gI().createParty();
                }
                TabCreateParty.gI().show();
                return;
            case 2:
                SubTabApplyParty.gI().show();
                SendMessage.gI().updateListInviteParty();
                return;
            default:
                return;
        }
    }

    public static boolean isLeader() {
        PartyInfo partyInfo2 = partyInfo;
        return partyInfo2 != null && partyInfo2.vChars.size() > 0 && partyInfo.vChars.get(0).isMe();
    }

    public static int charInParty(int id) {
        if (partyInfo == null) {
            return -1;
        }
        for (int i = 0; i < partyInfo.vChars.size(); i++) {
            if (partyInfo.vChars.elementAt(i).charID == id) {
                return i;
            }
        }
        return -1;
    }
}
