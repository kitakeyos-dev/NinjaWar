package ninjawar.screen.objscr;

import java.io.PrintStream;
import ninjawar.input.KEY;
import ninjawar.input.MyButton;
import ninjawar.model.DetailText;
import ninjawar.model.MyCommand;
import ninjawar.model.PointChangeMap;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.MyTile;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.object.mNPC;
import ninjawar.supporter.LoadDataManager;

public class MiniMap extends ObjScr {
    public static mFont fontPaintNameMap = mFont.tahoma_7b_white13;
    public static mFont fontPaintNameNpc = mFont.tahoma_7b_white;
    public static mFont fontPaintNameNpcFocus = mFont.tahoma_7b_green;
    public static mFont fontPaintNameWP = mFont.tahoma_7b_white;
    public static int indexNPC = -1;
    public static int indexWP = -1;
    public static MiniMap me;
    int[] XYMe;
    public int[] arrH;
    public int[] arrW;
    public int[] arrX;
    public int[] arrY;
    public MyButton[] btns;
    int hNPC;
    public int hScale;
    boolean isCloseNPC = true;
    boolean isHoverClose = false;
    boolean isHoverOpen = false;
    String[] nameNPCs;
    String[] nameWPs;
    float scale;
    DetailText[] textNPC;
    int wNPC;
    public int wScale;
    public int xMe;
    int[] xNPC;
    int[] xNPCReal;
    public int xScale;
    int xStart;
    int[] xWP;
    public int yMe;
    int yMoreTextHCenter = -2;
    int[] yNPC;
    int[] yNPCReal;
    public int yScale;
    int yStart;
    int[] yWP;

    public void initCmd() {
        this.imgClose = LoadDataManager.imgCloseMiniMap;
        initClose();
    }

    private void init() {
        this.margin = 5;
        initCmd();
        this.arrH = new int[]{LoadDataManager.imgBGMinisize.getRHeight(), LoadDataManager.imgOpenOptionBg.getRHeight(), 0, LoadDataManager.imgCloseOptionBg.getRHeight()};
        this.arrW = new int[]{LoadDataManager.imgBGMinisize.getRWidth(), LoadDataManager.imgOpenOptionBg.getRWidth(), 0, LoadDataManager.imgCloseOptionBg.getRWidth()};
        initNPC();
        initWP();
        this.textNPC = Res.getTextDocInFrame(fontPaintNameNpc, this.arrX[0], this.arrY[0], this.arrW[0], this.arrH[0], this.margin, "NPC");
    }

    private void initNPC() {
        String[] strArr;
        int i;
        this.nameNPCs = new String[MapScr.vNpc.size()];
        this.xNPC = new int[MapScr.vNpc.size()];
        this.yNPC = new int[MapScr.vNpc.size()];
        this.xNPCReal = new int[MapScr.vNpc.size()];
        this.yNPCReal = new int[MapScr.vNpc.size()];
        int i2 = 0;
        while (true) {
            strArr = this.nameNPCs;
            if (i2 >= strArr.length) {
                break;
            }
            mNPC npc = (mNPC) MapScr.vNpc.get(i2);
            this.nameNPCs[i2] = npc.nameNPC;
            int[] iArr = this.xNPCReal;
            int i3 = npc.cx;
            iArr[i2] = i3;
            int[] iArr2 = this.yNPCReal;
            int i4 = npc.cy;
            iArr2[i2] = i4;
            int[] xyNPC = getPosInMiniMap(i3, i4);
            this.xNPC[i2] = this.xStart + xyNPC[0];
            this.yNPC[i2] = this.yStart + xyNPC[1];
            i2++;
        }
        if (strArr.length > 0) {
            int i5 = this.margin;
            i = (strArr.length * fontPaintNameNpc.getHeight()) + ((this.nameNPCs.length - 1) * i5) + (i5 * 2);
        } else {
            i = 0;
        }
        this.hNPC = i;
        String[] strArr2 = this.nameNPCs;
        this.wNPC = strArr2.length > 0 ? fontPaintNameNpc.getWidth(Res.findStringMax(strArr2)) + (this.margin * 2) : 0;
        this.arrX = new int[]{this.x - LoadDataManager.imgBGMinisize.getRWidth(), (this.x - LoadDataManager.imgBGMinisize.getRWidth()) - LoadDataManager.imgOpenOptionBg.getRWidth(), ((this.x - LoadDataManager.imgBGMinisize.getRWidth()) - LoadDataManager.imgOpenOption.getRWidth()) - 1, this.x - LoadDataManager.imgCloseOptionBg.getRWidth(), (this.x - LoadDataManager.imgCloseOption.getRWidth()) - 3, ((this.x - LoadDataManager.imgCloseOption.getRWidth()) - 4) - this.wNPC};
        this.arrY = new int[]{this.y + ((this.h - LoadDataManager.imgBGMinisize.getRHeight()) / 2), this.y + ((this.h - LoadDataManager.imgBGMinisize.getRHeight()) / 2) + ((LoadDataManager.imgBGMinisize.getRHeight() - LoadDataManager.imgOpenOptionBg.getRHeight()) / 2), this.y + ((this.h - LoadDataManager.imgBGMinisize.getRHeight()) / 2) + 4 + ((LoadDataManager.imgBGMinisize.getRHeight() - LoadDataManager.imgOpenOptionBg.getRHeight()) / 2), this.y + ((this.h - LoadDataManager.imgCloseOptionBg.getRHeight()) / 2), this.y + ((this.h - LoadDataManager.imgCloseOption.getRHeight()) / 2), (this.y + ((this.h - LoadDataManager.imgCloseOption.getRHeight()) / 2)) - (this.hNPC / 4)};
    }

    private void initWP() {
        this.nameWPs = new String[MyTile.vGo.size()];
        for (int i = 0; i < this.nameWPs.length; i++) {
            String[] strArr = this.nameWPs;
            String str = ((PointChangeMap) MyTile.vGo.get(i)).name;
            if (str == null) {
                str = "Waypoint " + (i + 1);
            }
            strArr[i] = str;
        }
    }

    public MiniMap(int x, int y, int w, int h) {
        int i = x;
        int i2 = y;
        int i3 = w;
        int i4 = h;
        this.w = i3;
        this.h = i4;
        this.margin = 5;
        int i5 = i3 - (5 * 2);
        this.wScale = i5;
        int i6 = i4 - (5 * 4);
        this.hScale = i6;
        byte b = MyTile.size;
        int i7 = MyTile.tmw;
        float f = (((float) i5) * 1.0f) / ((float) (b * i7));
        this.scale = f;
        float f2 = ((float) i7) * f * ((float) MyTile.size);
        float f3 = f * ((float) MyTile.tmh);
        byte b2 = MyTile.size;
        if (f3 * ((float) b2) > ((float) i6)) {
            this.scale = (((float) i6) * 1.0f) / ((float) (b2 * MyTile.tmh));
        }
        this.x = i;
        this.y = i2;
        float f4 = this.scale;
        float wMapMini = ((float) MyTile.tmw) * f4 * ((float) MyTile.size);
        float hMapMini = ((float) MyTile.tmh) * f4 * ((float) MyTile.size);
        int i8 = (int) (((float) i) + ((((float) i3) - wMapMini) / 2.0f));
        this.xStart = i8;
        int i9 = (int) (((float) i2) + ((((float) i4) - hMapMini) / 2.0f));
        this.yStart = i9;
        this.xScale = (int) (((float) i8) / f4);
        this.yScale = (int) (((float) i9) / f4);
        Res.outz("scale:" + this.scale);
        int[] XYMe2 = getPosInMiniMap(Player.myCharz().cx, Player.myCharz().cy);
        this.xMe = this.xStart + XYMe2[0];
        this.yMe = this.yStart + XYMe2[1];
        int sizeWP = MyTile.vGo.size();
        this.xWP = new int[sizeWP];
        this.yWP = new int[sizeWP];
        for (int i10 = 0; i10 < sizeWP; i10++) {
            PointChangeMap wp = (PointChangeMap) MyTile.vGo.get(i10);
            int[] XYWP = getPosInMiniMap(wp.x, wp.y);
            this.xWP[i10] = this.xStart + XYWP[0];
            this.yWP[i10] = this.yStart + XYWP[1];
        }
    }

    public int[] getPosInMiniMap(int x, int y) {
        float f = this.scale;
        return new int[]{(int) (((float) x) * f), (int) (((float) y) * f)};
    }

    public void show() {
        init();
        me = this;
        CanvasNinja.objScr = this;
    }

    public void updatePointer() {
        String[] strArr;
        super.updatePointer();
        if (this.isCloseNPC) {
            int i = this.arrX[1];
            int i2 = this.arrY[0];
            int[] iArr = this.arrW;
            if (CanvasNinja.isPointHover(i, i2, iArr[0] + iArr[1], this.arrH[0])) {
                this.isHoverClose = true;
            } else {
                this.isHoverClose = false;
            }
        } else if (CanvasNinja.isPointHover(this.arrX[3], this.arrY[3], this.arrW[3], this.arrH[3])) {
            this.isHoverOpen = true;
        } else {
            this.isHoverOpen = false;
        }
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.updatePointer();
            }
        }
        if (CanvasNinja.isPointerClick && (strArr = this.nameNPCs) != null && strArr.length > 0) {
            if (this.isCloseNPC) {
                int i3 = this.arrX[1];
                int i4 = this.arrY[0];
                int[] iArr2 = this.arrW;
                if (CanvasNinja.isPoint(i3, i4, iArr2[0] + iArr2[1], this.arrH[0])) {
                    CanvasNinja.clearAllPointer();
                    this.isCloseNPC = false;
                }
            } else if (CanvasNinja.isPoint(this.arrX[3], this.arrY[3], this.arrW[3], this.arrH[3])) {
                CanvasNinja.clearAllPointer();
                this.isCloseNPC = true;
            }
        }
        if (CanvasNinja.isPointerClick) {
            if (!this.isCloseNPC) {
                int i5 = 0;
                while (true) {
                    if (i5 >= this.nameNPCs.length) {
                        break;
                    } else if (CanvasNinja.isPoint(this.arrX[5], this.arrY[5] + this.margin + ((fontPaintNameNpc.getHeight() + this.margin) * i5), this.wNPC, fontPaintNameNpc.getHeight())) {
                        CanvasNinja.clearAllPointer();
                        indexNPC = i5;
                        doActionNpc();
                        break;
                    } else {
                        i5++;
                    }
                }
            }
            for (int i6 = 0; i6 < this.xWP.length; i6++) {
                int wpSize = LoadDataManager.imgMiniMapRed.getRWidth();
                if (CanvasNinja.isPoint(this.xWP[i6] - (wpSize / 2), this.yWP[i6] - (wpSize / 2), wpSize, wpSize)) {
                    CanvasNinja.clearAllPointer();
                    indexWP = i6;
                    return;
                }
            }
        }
    }

    public void update() {
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.update();
            }
        }
        int[] posInMiniMap = getPosInMiniMap(Player.myCharz().cx, Player.myCharz().cy);
        this.XYMe = posInMiniMap;
        this.xMe = this.xStart + posInMiniMap[0];
        this.yMe = this.yStart + posInMiniMap[1];
        if (MapScr.vNpc.size() > 0) {
            initNPC();
        }
    }

    public void updateKey() {
        super.updateKey();
        updatePointer();
    }

    public void paint(mGraphics g) {
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgMiniMap, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 9, false);
        Image image = LoadDataManager.imgCloseMiniMap;
        MyCommand myCommand = this.cmdClose;
        g.drawImage(image, (float) myCommand.x, (float) myCommand.y);
        fontPaintNameMap.drawString(g, MyTile.mapName + " " + (MyTile.zoneID + 1), (this.w / 2) + this.x, (this.y - fontPaintNameMap.getHeight()) - 5, 2);
        for (int a = 0; a < MyTile.tmw; a++) {
            for (int b = 0; b < MyTile.tmh; b++) {
                if (!(a == 0 || a == MyTile.tmw - 1 || MyTile.maps[(MyTile.tmw * b) + a] - 1 == -1)) {
                    MyTile.paintMiniTileNew(g, this.xScale, this.yScale, 0, a, b, 0, this.scale);
                }
            }
        }
        String[] strArr = this.nameNPCs;
        if (strArr != null && strArr.length > 0) {
            int i2 = 70;
            if (!this.isCloseNPC) {
                Image image2 = LoadDataManager.imgCloseOptionBg;
                float f = (float) this.arrX[3];
                float f2 = (float) this.arrY[3];
                if (!this.isHoverOpen) {
                    i2 = 50;
                }
                g.drawImage(image2, f, f2, true, i2);
                g.drawImage(LoadDataManager.imgCloseOption, (float) this.arrX[4], (float) this.arrY[4]);
                CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgParty, (float) this.arrX[5], (float) this.arrY[5], (float) this.wNPC, (float) this.hNPC, 9, 50, false);
                CanvasNinja.resetColor(g);
                int i3 = 0;
                while (true) {
                    String[] strArr2 = this.nameNPCs;
                    if (i3 >= strArr2.length) {
                        break;
                    }
                    if (indexNPC == i3) {
                        fontPaintNameNpcFocus.drawString(g, strArr2[i3], (this.wNPC / 2) + this.arrX[5], ((fontPaintNameNpc.getHeight() + this.margin) * i3) + this.arrY[5] + this.margin, 2);
                    } else {
                        mFont mfont = fontPaintNameNpc;
                        mfont.drawString(g, strArr2[i3], (this.wNPC / 2) + this.arrX[5], ((mfont.getHeight() + this.margin) * i3) + this.arrY[5] + this.margin, 2);
                    }
                    i3++;
                }
            } else {
                g.drawImage(LoadDataManager.imgBGMinisize, (float) this.arrX[0], (float) this.arrY[0], true, this.isHoverClose ? 70 : 50);
                Image image3 = LoadDataManager.imgOpenOptionBg;
                float f3 = (float) this.arrX[1];
                float f4 = (float) this.arrY[1];
                if (!this.isHoverClose) {
                    i2 = 50;
                }
                g.drawImage(image3, f3, f4, 0, true, i2);
                g.drawImage(LoadDataManager.imgOpenOption, (float) this.arrX[2], (float) this.arrY[2]);
                for (DetailText dt : this.textNPC) {
                    fontPaintNameNpc.drawString(g, dt.text, dt.x, dt.y, 2);
                }
            }
        }
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.paint(g);
            }
        }
        Image image4 = LoadDataManager.imgMiniMapBlue;
        g.drawImage(image4, (float) (this.xMe - (image4.getRWidth() / 2)), (float) (this.yMe - LoadDataManager.imgMiniMapBlue.getRHeight()));
        int i4 = 0;
        while (true) {
            int[] iArr = this.xWP;
            if (i4 >= iArr.length) {
                break;
            }
            Image image5 = LoadDataManager.imgMiniMapRed;
            g.drawImage(image5, (float) (iArr[i4] - (image5.getRWidth() / 2)), (float) this.yWP[i4]);
            if (indexWP == i4) {
                fontPaintNameWP.drawString(g, this.nameWPs[i4], this.xWP[i4], (this.yWP[i4] - LoadDataManager.imgMiniMapRed.getRHeight()) - fontPaintNameWP.getHeight(), 2);
            }
            i4++;
        }
        int i5 = indexNPC;
        if (i5 != -1) {
            int[] iArr2 = this.xNPC;
            if (i5 < iArr2.length) {
                Image image6 = LoadDataManager.imgMiniMapGreen;
                g.drawImage(image6, (float) (iArr2[indexNPC] - (image6.getRWidth() / 2)), (float) (this.yNPC[indexNPC] - LoadDataManager.imgMiniMapGreen.getRHeight()));
            }
        }
    }

    public boolean commandTabBoolean(int index, int subIndex) {
        PrintStream printStream = System.out;
        printStream.println("commandTab trong MiniMap:" + index);
        switch (index) {
            case -1:
                closeMenu();
                MapScr.gI().menuMiniMap = null;
                return true;
            default:
                return false;
        }
    }

    public void keyPress(int keyCode) {
        if (keyCode == KEY.KEY_M) {
            closeMenu();
        }
    }

    private void doActionNpc() {
        int i = indexNPC;
        if (i != -1 && i < this.xNPCReal.length) {
            MapScr gI = MapScr.gI();
            int[] iArr = this.xNPCReal;
            int i2 = indexNPC;
            gI.startPointFind(iArr[i2], this.yNPCReal[i2], this.nameNPCs[i2]);
        }
    }
}
