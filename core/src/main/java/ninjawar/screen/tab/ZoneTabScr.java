package ninjawar.screen.tab;

import java.util.Vector;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.model.Zone;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

public class ZoneTabScr extends TabScr {
    public static ZoneTabScr instance;
    static long lastTimePress = 0;
    public MyCommand cmdZone;
    mFont fontPaintZone = mFont.tahoma_7_info_blue;
    public int numPage;
    public int numZoneDoc;
    public int numZoneNgang;
    public Vector<Zone> vecZones = new Vector<>();

    public void init() {
        int sumWZone;
        lastTimePress = System.currentTimeMillis();
        this.numPage = 0;
        this.margin = 5;
        this.numZoneNgang = 5;
        this.numZoneDoc = this.vecZones.size() / 5;
        int wOneZone = (int) LoadDataManager.frameZoneStatus[0].frameWidth;
        int i = this.numZoneNgang;
        int i2 = this.margin;
        int wNgang = (wOneZone * i) + ((i + 1) * i2) + (i2 * 4);
        int size = this.vecZones.size();
        int i3 = this.numZoneNgang;
        if (size < i3) {
            this.numZoneNgang = 5;
            this.numZoneDoc = 1;
            sumWZone = (this.vecZones.size() * wOneZone) + (this.margin * (this.vecZones.size() - 1));
        } else {
            sumWZone = (wOneZone * i3) + (this.margin * (i3 - 1));
        }
        int i4 = this.margin;
        int hOneZone = ((int) LoadDataManager.frameZoneStatus[0].frameHeight) + (i4 * 2);
        int i5 = this.numZoneDoc;
        int hDoc = (hOneZone * i5) + ((i5 + 1) * i4) + (i4 * 2) + 3 + LoadDataManager.imgClose.getRHeight();
        while (wNgang > CanvasNinja.w) {
            int i6 = this.numZoneNgang - 1;
            this.numZoneNgang = i6;
            int i7 = this.margin;
            wNgang = (wOneZone * i6) + ((i6 + 1) * i7) + (i7 * 4);
        }
        while (hDoc > CanvasNinja.h) {
            int i8 = this.numZoneDoc - 1;
            this.numZoneDoc = i8;
            int i9 = this.margin;
            hDoc = (hOneZone * i8) + ((i8 + 1) * i9) + (i9 * 2) + 3 + LoadDataManager.imgClose.getRHeight();
        }
        this.w = wNgang;
        this.h = hDoc;
        initStart();
        String upperCase = SupportTranslate.getTextLangue("zone").toUpperCase();
        this.textTitle = upperCase;
        int width = TabScr.fontPaintTile.getWidth(upperCase);
        int i10 = this.margin;
        this.wTitle = width + (i10 * 4);
        this.hTitle = (int) LoadDataManager.frameTitle.frameHeight;
        this.arrW = new int[]{wOneZone};
        this.arrH = new int[]{hOneZone};
        this.arrX = new int[]{this.x + ((this.w - sumWZone) / 2)};
        MyCommand myCommand = this.cmdClose;
        this.arrY = new int[]{myCommand.y + myCommand.h + i10};
    }

    public void show() {
        init();
        initMoreCmd();
        showTab();
        instance = this;
    }

    public void initMoreCmd() {
        this.cmdZone = new MyCommand("", 0, this);
    }

    public void paint(mGraphics g) {
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        SupportPaint supportPaint = CanvasNinja.paintz;
        FrameImage frameImage = LoadDataManager.frameTitle;
        int i = this.wTitle;
        supportPaint.paintTagFrame(g, frameImage, (this.x + (this.w / 2)) - (i / 2), this.y - (this.hTitle / 2), i, false, 0, false);
        mGraphics mgraphics = g;
        TabScr.fontPaintTile.drawString(mgraphics, this.textTitle.toUpperCase(), (this.w / 2) + this.x, this.y, 3);
        for (int i2 = 0; i2 < this.numZoneDoc; i2++) {
            int k = 0;
            while (true) {
                int i3 = this.numZoneNgang;
                if (k >= i3) {
                    break;
                }
                int index = (this.numPage * this.numZoneDoc * i3) + (i3 * i2) + k;
                if (index < this.vecZones.size()) {
                    SupportPaint supportPaint2 = CanvasNinja.paintz;
                    FrameImage frameImage2 = LoadDataManager.frameZoneStatus[this.vecZones.get(index).status];
                    int i4 = this.arrX[0];
                    int i5 = this.margin;
                    int i6 = this.arrW[0];
                    supportPaint2.paintTagFrame(g, frameImage2, ((i5 + i6) * k) + i4, this.arrY[0] + ((i5 + this.arrH[0]) * i2), i6, false);
                    mFont mfont = TabScr.fontPaintTile;
                    String str = this.vecZones.get(index).name;
                    int i7 = this.arrX[0];
                    int i8 = this.margin;
                    int i9 = this.arrW[0];
                    int i10 = i7 + ((i8 + i9) * k) + (i9 / 2);
                    int i11 = this.arrY[0];
                    int i12 = this.arrH[0];
                    mfont.drawString(g, str, i10, ((i11 + ((i8 + i12) * i2)) + (i12 / 2)) - (TabScr.fontPaintTile.getHeight() / 2), 3);
                }
                k++;
            }
        }
        this.cmdClose.paintIconOnly(g);
    }

    public void commandTab(int index, int subIndex) {
        super.commandTab(index, subIndex);
        Res.outz("Vào commandTab trong ZoneScr:" + index);
        switch (index) {
            case 0:
                if (System.currentTimeMillis() - lastTimePress >= 800) {
                    SendMessage.gI().requestZone(1, (short) subIndex);
                    close();
                    lastTimePress = System.currentTimeMillis();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void update() {
    }

    public void updatePointer() {
        if (CanvasNinja.isPointerRelease()) {
            MyCommand myCommand = this.cmdClose;
            if (CanvasNinja.isPoint(myCommand.x, myCommand.y, myCommand.w, myCommand.h)) {
                this.cmdClose.perform();
                CanvasNinja.clearAllPointer();
            }
            if (CanvasNinja.isPointerRelease()) {
                int i = 0;
                while (i < this.numZoneDoc) {
                    boolean isBreak = false;
                    int k = 0;
                    while (true) {
                        int i2 = this.numZoneNgang;
                        if (k >= i2) {
                            break;
                        }
                        int index = (this.numPage * this.numZoneDoc * i2) + (i2 * i) + k;
                        if (index < this.vecZones.size()) {
                            int i3 = this.arrX[0];
                            int i4 = this.margin;
                            int i5 = this.arrW[0];
                            int i6 = i3 + ((i4 + i5) * k);
                            int i7 = this.arrY[0];
                            int i8 = this.arrH[0];
                            if (CanvasNinja.isPoint(i6, i7 + ((i4 + i8) * i), i5, i8)) {
                                CanvasNinja.clearAllPointer();
                                this.cmdZone.subAction = this.vecZones.get(index).idZone;
                                this.cmdZone.perform();
                                isBreak = true;
                                break;
                            }
                        }
                        k++;
                    }
                    if (!isBreak) {
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void updateKey() {
        updatePointer();
    }

    public void keyPress(int keyCode) {
    }

    public void keyTyped() {
    }
}
