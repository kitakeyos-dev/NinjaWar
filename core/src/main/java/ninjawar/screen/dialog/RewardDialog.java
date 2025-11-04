package ninjawar.screen.dialog;

import java.util.Iterator;
import java.util.Vector;
import ninjawar.input.MyButton;
import ninjawar.model.MissionPaintDetail;
import ninjawar.model.MyCommand;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.object.Item;
import ninjawar.scroll.ScrollX;
import ninjawar.supporter.LoadDataManager;

public class RewardDialog extends Dialog {
    public static mFont fontPaint = mFont.tahoma_brown_dv;
    public static mFont fontPaintDetail = mFont.tahoma_7_brown;
    public static mFont fontPaintFocus = mFont.tahoma_brown_focus_dv;
    public static mFont fontPaintPhanThuong = mFont.tahoma_7b_brown;
    public static mFont fontPaintText = mFont.tahoma_7_brown;
    public static mFont fontPaintTextFocus = mFont.tahoma_7_white;
    public static mFont fontPaintTile = mFont.tahoma_7b_white;
    public static mFont fontPaintTitle = mFont.tahoma_brown_focus_dv;
    public static mFont fontPlaintItemNum = mFont.tahoma_7_small;
    public static RewardDialog me;
    public static Vector<Item> vecItems = new Vector<>();
    public static Vector<MissionPaintDetail> vecMissions = new Vector<>();
    public int[] arrH;
    public int[] arrW;
    public int[] arrX;
    public int[] arrY;
    MyButton btn;
    public int h;
    int hTitle;
    public int indexSelected = -1;
    public int margin = 5;
    public int margin2 = 2;
    public int margin3 = 3;
    public int margin7 = 7;
    public int margin8 = 8;
    int marginTagNhiemVu;
    int marginTitleAndTagNhiemVu;
    MissionPaintDetail missionPaintDetail;
    ScrollX scrollX;
    String title = "";
    public int w;
    int wBeforeItem;
    int wTitle;
    public int x;
    int xTitle;
    public int y;
    int yTitle;

    public RewardDialog() {
        FrameImage frameImage = LoadDataManager.myButtons[1];
        this.btn = new MyButton(frameImage, frameImage, 75, 0, "Nhận", 0, 0, new MyCommand("", -1, this));
        this.marginTitleAndTagNhiemVu = 10;
        this.marginTagNhiemVu = 2;
    }

    public static RewardDialog gI() {
        if (me == null) {
            me = new RewardDialog();
        }
        return me;
    }

    private void paintListItem(mGraphics g, int xStart, int yStart, boolean useClip) {
        int i = 0;
        this.wBeforeItem = 0;
        Iterator<Item> it = vecItems.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            item.paintCenter(g, xStart + this.wBeforeItem + (i * 3 * this.margin), yStart - 5, useClip);
            mFont mfont = fontPlaintItemNum;
            mfont.drawString(g, "x" + item.num, xStart + this.wBeforeItem + (i * 3 * this.margin) + (item.getW() / 2), yStart + 17, 4, useClip);
            this.wBeforeItem = this.wBeforeItem + item.getW();
            i++;
        }
        mGraphics mgraphics = g;
        boolean z = useClip;
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(mgraphics, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        CanvasNinja.paintz.paintTagFrame(mgraphics, LoadDataManager.frameTitle2, this.xTitle, this.yTitle, this.wTitle, false, 0, false);
        fontPaintTile.drawString(g, this.title, (this.wTitle / 2) + this.xTitle, (this.hTitle / 2) + this.yTitle, 3);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameBgGift, this.arrX[5], this.arrY[5], this.arrW[5], false, 0, false);
        fontPaintPhanThuong.drawString(g, "Bạn đã nhận được", this.arrX[4], this.arrY[4], 2);
        if (vecItems != null) {
            if (this.scrollX != null) {
                g.setClip(this.arrX[5], this.arrY[5], this.arrW[5], this.arrH[5]);
                g.translate(-this.scrollX.cmx, 0);
            }
            paintListItem(g, this.arrX[5] + (((this.arrW[5] - (vecItems.size() * vecItems.get(0).getW())) - ((this.margin * 3) * vecItems.size())) / 2), this.arrY[5] + (this.arrH[5] / 2), true);
        }
        CanvasNinja.resetTrans(g);
        MyButton myButton = this.btn;
        if (myButton != null) {
            myButton.paintButton(g, false);
        }
    }

    public void initScrollX() {
        MissionPaintDetail missionPaintDetail2 = this.missionPaintDetail;
        if (missionPaintDetail2 != null && missionPaintDetail2.vecItems.size() > 0) {
            int i = this.arrX[5];
            int i2 = this.arrY[5];
            int i3 = this.arrW[5];
            this.scrollX = new ScrollX(i, i2, i3, this.arrH[5], (this.missionPaintDetail.vecItems.size() * this.missionPaintDetail.vecItems.elementAt(0).getW()) + ((this.missionPaintDetail.vecItems.size() - 1) * this.margin), i3);
        }
    }

    public void initStart() {
        this.margin = 5;
        this.x = (CanvasNinja.w - this.w) / 2;
        this.y = (CanvasNinja.h - this.h) / 2;
    }

    public void init() {
        this.title = "Chúc mừng".toUpperCase();
        this.w = 260;
        int i = CanvasNinja.w;
        if (260 > i) {
            this.w = i - (this.margin * 5);
        }
        this.h = 150;
        int i2 = this.h;
        int i3 = CanvasNinja.h;
        if (i2 > i3) {
            this.h = i3 - (this.margin * 2);
        }
        initStart();
        this.hTitle = (int) LoadDataManager.frameTitle.frameHeight;
        int width = fontPaintTile.getWidth(this.title) + (this.margin * 4);
        this.wTitle = width;
        int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(8, width, LoadDataManager.frameTitle);
        this.wTitle = fixSizeTagFrameUp;
        int i4 = this.x;
        int i5 = this.w;
        this.xTitle = i4 + ((i5 - fixSizeTagFrameUp) / 2);
        this.yTitle = this.y - (this.hTitle / 2);
        int i6 = this.margin;
        int marginTopBot = i6 * 4;
        int marginLeftRight = i6 * 2;
        int wRight = i5 - (marginLeftRight * 2);
        int i7 = (this.h - 14) - (marginTopBot * 2);
        int i8 = this.margin;
        int[] iArr = {fontPaintTitle.getHeight(), (int) LoadDataManager.frameBgMission.frameHeight, i7 + i8, 0, 0, (int) LoadDataManager.frameBgGift.frameHeight};
        this.arrH = iArr;
        int i9 = wRight - (i8 * 4);
        this.arrW = new int[]{0, (int) LoadDataManager.frameBgMission.frameWidth, wRight, wRight - (i8 * 4), 0, i9};
        int xStartLeft = this.x + marginLeftRight;
        int xRight = xStartLeft;
        this.arrX = new int[]{xStartLeft, xStartLeft, xRight, xRight + (i8 * 2), xRight + (wRight / 2), xRight + ((wRight - i9) / 2)};
        int yStart = this.y + 14 + (i8 * 2);
        this.arrY = new int[]{yStart, iArr[0] + yStart + this.marginTitleAndTagNhiemVu, yStart, (i8 * 2) + yStart, yStart, (i8 * 2) + yStart + fontPaintPhanThuong.getHeight()};
        initScrollX();
        MyButton myButton = this.btn;
        myButton.setXY((this.x + (this.w / 2)) - (myButton.w / 2), ((this.y + this.h) - ((int) LoadDataManager.myButtons[1].frameHeight)) - (this.margin * 3));
    }

    public void update() {
        ScrollX scrollX2 = this.scrollX;
        if (scrollX2 != null) {
            scrollX2.update();
        }
        this.btn.update();
    }

    public void updateKey() {
        this.btn.updatePointer();
    }

    public void show() {
        init();
        me = this;
        CanvasNinja.currentDialog = this;
    }

    public void keyPress(int keyCode) {
    }

    public void keyTyped() {
    }

    public void close() {
        CanvasNinja.currentDialog = null;
    }

    public void commandTab(int index, int subIndex) {
        switch (index) {
            case -1:
                close();
                return;
            default:
                return;
        }
    }
}
