package ninjawar.mylib;

import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.IMapObject;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.MyTile;
import ninjawar.myscr.Player;
import ninjawar.object.ObjEffect;
import ninjawar.supporter.LoadDataManager;

public class ObjMap implements IMapObject {
    public static int[] idMiniBg = {79, 80, 81, 85, 86, 90, 91, 92, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108};
    public static HTCustom imgNew = new HTCustom("imgNew BgItem");
    public static VectorCustom vKeysLast = new VectorCustom("vKeyLast");
    public static VectorCustom vKeysNew = new VectorCustom("vKeyNews");
    public int dx;
    public int dy;
    public int h;
    public int id;
    public short idImage;
    public FrameAtlas image;
    boolean isBlur = false;
    public byte layer;
    public ObjEffect objEffect;
    public int[] tileX;
    public int[] tileY;
    public int trans;
    public int transX = 0;
    public int transY = 0;
    public int w;
    public int x;
    public int y;
    int yAnimFocus;

    public void paint(mGraphics g) {
        FrameAtlas frameAtlas;
        FrameAtlas frameAtlas2;
        mGraphics mgraphics = g;
        BaseScreen baseScreen = CanvasNinja.currentScreen;
        if (baseScreen != null && (baseScreen instanceof MapScr)) {
            int cmx = MapScr.cmx;
            int cmy = MapScr.cmy;
            byte b = this.layer;
            if (b >= 5) {
                this.transX = ((-cmx) / 2) + 100;
            }
            if (b < 3) {
                this.transX = (-(cmx >> 4)) + 50;
            }
            ObjEffect objEffect2 = this.objEffect;
            if (objEffect2 != null) {
                objEffect2.paint(mgraphics);
                this.objEffect.update();
                return;
            }
            int i = this.w;
            int i2 = (-i) / 2;
            this.dx = i2;
            int i3 = this.h;
            int i4 = -i3;
            this.dy = i4;
            int i5 = this.x;
            int X = i5 + i2 + this.transX;
            int i6 = this.y;
            int Y = i4 + i6 + this.transY;
            if (this.idImage == 2000 || (frameAtlas = this.image) == null) {
                FrameAtlas frameAtlas3 = this.image;
                if (frameAtlas3 != null) {
                    frameAtlas3.drawFrame(0, (float) (i5 - (i / 2)), (float) (i6 - i3), mgraphics);
                    if (this.idImage == 2000) {
                        mFont mfont = mFont.fontPaintKhu;
                        mfont.drawString(g, (MyTile.zoneID + 1) + "", this.x, (this.y - this.h) + 8, 2);
                        if (Player.myCharz().bgItemFocus != null && Player.myCharz().bgItemFocus.equals(this)) {
                            if (CanvasNinja.gameTick % 4 == 0) {
                                int i7 = this.yAnimFocus + 1;
                                this.yAnimFocus = i7;
                                if (i7 > 2) {
                                    this.yAnimFocus = 0;
                                }
                            }
                            paintFocus(mgraphics, this.x, ((this.y - this.h) - 20) + this.yAnimFocus);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            int rWidth = i5 + i2 + frameAtlas.getRWidth();
            int i8 = this.transX;
            if (rWidth + i8 >= cmx && this.x + this.dx + i8 <= CanvasNinja.w + cmx && this.y + this.dy + this.transY + this.image.getRHeight() >= cmy && this.y + this.dy + this.transY <= CanvasNinja.h + cmy && (frameAtlas2 = this.image) != null) {
                frameAtlas2.drawFrame(0, (float) X, (float) Y, 0, g, X < Player.myCharz().cx && Player.myCharz().cx < this.w + X && this.layer == 4, 75);
            }
        }
    }

    private void paintFocus(mGraphics g, int x2, int y2) {
        Image image2 = LoadDataManager.imgFocus;
        g.drawImage(image2, (float) (x2 - (image2.getRWidth() / 2)), (float) y2);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getW() {
        return this.w;
    }

    public int getH() {
        return this.h;
    }

    public void stopMoving() {
    }

    public boolean isInvisible() {
        return false;
    }

    public boolean isCanFocus() {
        return this.idImage == 2000;
    }
}
