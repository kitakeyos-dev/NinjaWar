package ninjawar.scroll;

import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;

public class ScrollX {
    public int cmdx;
    public int cmtox;
    public int cmvx;
    public int cmx;
    public int cmxLim;
    private long countX;
    boolean disAble = false;
    public int disX;
    int dxTran;
    public int h;
    public boolean isGX = false;
    boolean isTransingX = false;
    int limitX;
    int paX = 0;
    private long timePointX;
    public long timeScrollX = 0;
    public boolean transX = false;
    int vX;
    int w;
    int x;
    int y;

    public void update() {
        moveCamX();
    }

    public ScrollX(int x2, int y2, int w2, int h2, int maxPaint, int wRealPaint) {
        this.x = x2;
        this.y = y2;
        this.w = w2;
        this.h = h2;
        int wPaint = maxPaint - wRealPaint;
        this.limitX = wPaint;
        this.cmxLim = wPaint;
        if (wPaint < 0) {
            this.cmxLim = 0;
        }
    }

    private void moveCamX() {
        int i;
        this.countX++;
        if (CanvasNinja.isPointerDraggedX && !this.isTransingX && checkClipScrollX()) {
            CanvasNinja.isPointerDraggedY = false;
            CanvasNinja.isPointerDraggedX = false;
            this.paX = this.cmx;
            this.transX = true;
            this.vX = 0;
        }
        if (this.transX) {
            this.isTransingX = true;
            if (CanvasNinja.isPointerDown) {
                if (CanvasNinja.gameTick % 3 == 0) {
                    this.dxTran = CanvasNinja.px;
                    this.timePointX = this.countX;
                }
                this.vX = 0;
                int dx = this.paX + CanvasNinja.dx();
                this.cmtox = dx;
                if (dx < 0 || dx > this.cmxLim) {
                    this.cmtox = this.paX + (CanvasNinja.dx() / 2);
                }
                this.cmx = this.cmtox;
            }
            if (CanvasNinja.isPointerRelease || !checkClipScrollX()) {
                this.transX = false;
                int ti = (int) (this.countX - this.timePointX);
                int dx0 = this.dxTran - CanvasNinja.px;
                if (Res.abs(dx0) > 40 && ti < 10 && (i = this.cmtox) > 0 && i < this.cmxLim) {
                    this.vX = (dx0 / ti) * 10;
                }
                CanvasNinja.isPointerRelease = false;
                CanvasNinja.isPointerDraggedX = false;
                this.isTransingX = false;
                this.timePointX = -1;
                Res.outz("cmx:" + this.cmx + "_vx:" + this.vX);
            }
        }
        int ti2 = this.vX;
        if (ti2 != 0) {
            int i2 = this.cmx;
            if (i2 < 0 || i2 > this.cmxLim) {
                int i3 = ti2 - (ti2 / 4);
                this.vX = i3;
                this.cmx = i2 + (i3 / 20);
                if (i3 / 10 <= 1) {
                    this.vX = 0;
                }
            }
            int i4 = this.cmx;
            if (i4 < 0) {
                int i5 = this.disX;
                if (i4 < (-i5) / 2) {
                    this.cmx = (-i5) / 2;
                    this.cmtox = 0;
                    this.vX = 0;
                }
            } else {
                int i6 = this.cmxLim;
                if (i4 > i6) {
                    int i7 = this.disX;
                    if (i4 < (i7 / 2) + i6) {
                        this.cmx = (i7 / 2) + i6;
                        this.cmtox = i6;
                        this.vX = 0;
                    }
                } else {
                    this.cmx = i4 + (this.vX / 10);
                }
            }
            this.cmtox = this.cmx;
            int i8 = this.vX;
            int i9 = i8 - (i8 / 10);
            this.vX = i9;
            if (i9 / 10 == 0) {
                this.vX = 0;
            }
        } else {
            int i10 = this.cmx;
            if (i10 < 0) {
                this.cmtox = 0;
            } else {
                int i11 = this.cmxLim;
                if (i10 > i11) {
                    this.cmtox = i11;
                }
            }
        }
        int i12 = this.cmx;
        int i13 = this.cmtox;
        if (i12 != i13) {
            int i14 = (i13 - i12) << 2;
            this.cmvx = i14;
            int i15 = this.cmdx + i14;
            this.cmdx = i15;
            this.cmx = i12 + (i15 >> 4);
            this.cmdx = i15 & 15;
        }
    }

    public boolean checkClipScrollX() {
        if (CanvasNinja.isPoint(this.x, this.y, this.w, this.h)) {
            return true;
        }
        return false;
    }
}
