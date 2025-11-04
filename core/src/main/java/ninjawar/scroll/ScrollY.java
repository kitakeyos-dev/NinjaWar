package ninjawar.scroll;

import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;

public class ScrollY {
    public int cmdy;
    public int cmtoY;
    public int cmvy;
    public int cmy;
    public int cmyLim;
    public long count;
    public int dis;
    public int dyTran;
    public int h;
    public boolean isG = false;
    public boolean isHavePopup = false;
    public boolean isTransing = false;
    public boolean isUse = false;
    public int limit;
    public int pa = 0;
    public int pyLast;
    long timeLastScroll;
    public long timePoint;
    public long timeScroll = 0;
    public long timeTrans;
    public boolean trans = false;
    public int vY;
    public int w;
    public int x;
    public int y;

    public void initScroll(int x2, int y2, int w2, int h2, int maxPaint, int hRealPaint) {
        this.x = x2;
        this.y = y2;
        this.w = w2;
        this.h = h2;
        this.isUse = true;
        int hPaint = maxPaint - hRealPaint;
        this.limit = hPaint;
        this.cmyLim = hPaint;
        if (hPaint < 0) {
            this.cmyLim = 0;
        }
        CanvasNinja.resetScrollWhell();
        CanvasNinja.cmyLim = this.cmyLim;
    }

    public ScrollY() {
    }

    public ScrollY(int x2, int y2, int w2, int h2, int maxPaint, int hRealPaint) {
        this.x = x2;
        this.y = y2;
        this.w = w2;
        this.h = h2;
        this.isUse = true;
        int hPaint = maxPaint - hRealPaint;
        this.limit = hPaint;
        this.cmyLim = hPaint;
        if (hPaint < 0) {
            this.cmyLim = 0;
        }
        CanvasNinja.resetScrollWhell();
        CanvasNinja.cmyLim = this.cmyLim;
    }

    private void moveCamY() {
        int i;
        this.count++;
        if (CanvasNinja.isPointerDraggedY && !CanvasNinja.isPointerRelease && !this.isTransing && checkClipScrollY()) {
            CanvasNinja.isPointerDraggedY = false;
            CanvasNinja.isPointerDraggedX = false;
            this.pa = this.cmy;
            this.trans = true;
            this.vY = 0;
            CanvasNinja.isScrollWhell = false;
        }
        if (this.trans) {
            this.timeTrans = mSystem.currentTimeMillis();
            this.isHavePopup = false;
            this.isTransing = true;
            if (CanvasNinja.isPointerDraggedY && CanvasNinja.isHoldPress()) {
                if (CanvasNinja.gameTick % 3 == 0) {
                    this.dyTran = CanvasNinja.py;
                    this.timePoint = this.count;
                }
                this.vY = 0;
                int dy = this.pa + CanvasNinja.dy();
                this.cmtoY = dy;
                if (dy < 0 || dy > this.cmyLim) {
                    this.cmtoY = this.pa + (CanvasNinja.dy() / 2);
                }
                this.cmy = this.cmtoY;
            }
            if (this.trans && (CanvasNinja.isPointerRelease || !checkClipScrollY())) {
                CanvasNinja.isPointerDraggedY = false;
                this.isTransing = false;
                this.trans = false;
                this.timePoint = -1;
                this.timeLastScroll = mSystem.currentTimeMillis() + 200;
                int ti = (int) (this.count - this.timePoint);
                int dy0 = this.dyTran - CanvasNinja.py;
                if (Res.abs(dy0) > 40 && ti < 10 && (i = this.cmtoY) > 0 && i < this.cmyLim) {
                    this.vY = (dy0 / ti) * 10;
                }
            }
        }
        int ti2 = this.vY;
        if (ti2 != 0) {
            int i2 = this.cmy;
            if (i2 < 0 || i2 > this.cmyLim) {
                int i3 = ti2 - (ti2 / 4);
                this.vY = i3;
                if (CanvasNinja.py < this.pyLast) {
                    this.cmy = i2 - (i3 / 20);
                } else {
                    this.cmy = i2 + (i3 / 20);
                }
                if (i3 / 10 <= 1) {
                    this.vY = 0;
                }
            } else {
                if (CanvasNinja.py < this.pyLast) {
                    this.cmy = i2 - (ti2 / 10);
                } else {
                    this.cmy = i2 + (ti2 / 10);
                }
                this.cmtoY = this.cmy;
                int i4 = ti2 - (ti2 / 10);
                this.vY = i4;
                if (i4 / 10 == 0) {
                    this.vY = 0;
                }
            }
            int i5 = this.cmy;
            if (i5 < 0) {
                int i6 = this.dis;
                if (i5 < (-i6) / 2) {
                    this.cmy = (-i6) / 2;
                    this.cmtoY = 0;
                    this.vY = 0;
                }
            } else {
                int i7 = this.cmyLim;
                if (i5 > i7) {
                    int i8 = this.dis;
                    if (i5 < (i8 / 2) + i7) {
                        this.cmy = (i8 / 2) + i7;
                        this.cmtoY = i7;
                        this.vY = 0;
                    }
                }
            }
        } else {
            int i9 = this.cmy;
            if (i9 < 0) {
                this.cmtoY = 0;
            } else {
                int i10 = this.cmyLim;
                if (i9 > i10) {
                    this.cmtoY = i10;
                }
            }
        }
        int i11 = this.cmy;
        int i12 = this.cmtoY;
        if (i11 == i12) {
            return;
        }
        if (i12 >= this.cmyLim || i12 <= 0) {
            int i13 = (i12 - i11) << 2;
            this.cmvy = i13;
            int i14 = this.cmdy + i13;
            this.cmdy = i14;
            this.cmy = i11 + (i14 >> 4);
            this.cmdy = i14 & 15;
        }
    }

    public void update() {
        if (this.isUse) {
            moveCamY();
            if (CanvasNinja.isScrollWhell) {
                int i = CanvasNinja.cmy;
                this.cmy = i;
                int i2 = this.cmyLim;
                if (i > i2) {
                    this.cmy = i2;
                }
            }
        }
    }

    public boolean checkClipScrollY() {
        if (CanvasNinja.isPoint(this.x, this.y, this.w, this.h)) {
            return true;
        }
        return false;
    }
}
