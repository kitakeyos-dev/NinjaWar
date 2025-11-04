// Class Version: 8
package ninjawar.model;

import ninjawar.myscr.MyTile;
import ninjawar.myscr.Res;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mylib.mSystem;
import ninjawar.mylib.Image;

public class EffSky
{
    public int h;
    Image img;
    public boolean isStart;
    public boolean isStop;
    int maxW;
    public int speed;
    public long timeContinue;
    public long timeDelay;
    public long timeRandomStop;
    public byte[] typeBg;
    public int w;
    public int x;
    public int y;

    public EffSky(final int n, final int n2, final int n3, final int n4, final Image image) {
        this.isStart = false;
        this.isStop = false;
        this.init(n, n2, n3, n4, image, null);
    }

    public EffSky(final int n, final int n2, final int n3, final int n4, final Image image, final byte[] array) {
        this.isStart = false;
        this.isStop = false;
        this.init(n, n2, n3, n4, image, array);
    }

    public void init(final int x, final int y, final int speed, final int n, final Image img, final byte[] typeBg) {
        this.typeBg = typeBg;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.img = img;
        this.w = img.getRWidth();
        this.h = img.getRHeight();
        this.timeDelay = mSystem.currentTimeMillis() + n;
        this.setTimeStop();
    }

    public boolean isTypeBg() {
        if (this.typeBg != null) {
            int n = 0;
            while (true) {
                final byte[] typeBg = this.typeBg;
                if (n >= typeBg.length) {
                    break;
                }
                if (typeBg[n] == CanvasNinja.typeBg) {
                    return true;
                }
                ++n;
            }
        }
        return false;
    }

    public void paint(final mGraphics mGraphics) {
        if (this.typeBg == null || this.isTypeBg()) {
            mGraphics.drawImage(this.img, (float)this.x, (float)this.y);
        }
    }

    public void setTimeStop() {
        this.timeRandomStop = mSystem.currentTimeMillis() + Res.random(5, 10) * 1000;
    }

    public void update() {
        if (this.timeRandomStop != 0L && mSystem.currentTimeMillis() > this.timeRandomStop) {
            this.isStart = false;
            this.timeRandomStop = 0L;
            this.timeContinue = mSystem.currentTimeMillis() + 0L;
        }
        if (this.timeContinue != 0L && mSystem.currentTimeMillis() > this.timeContinue) {
            this.timeContinue = 0L;
            this.isStart = true;
        }
        if (this.timeDelay != 0L && mSystem.currentTimeMillis() > this.timeDelay) {
            this.isStart = true;
            this.timeDelay = 0L;
        }
        if (this.isStart) {
            if (this.timeRandomStop == 0L) {
                this.setTimeStop();
            }
            final int x = this.x + this.speed;
            this.x = x;
            int w;
            if (CanvasNinja.w > MyTile.tmw * MyTile.size) {
                w = CanvasNinja.w;
            }
            else {
                w = MyTile.tmw * MyTile.size;
            }
            this.maxW = w;
            if (x >= w) {
                this.x = -this.w;
            }
        }
        this.updatePointer();
    }

    public void updatePointer() {
    }
}
