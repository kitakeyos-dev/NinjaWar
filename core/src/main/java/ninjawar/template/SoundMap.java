package ninjawar.template;

import ninjawar.mylib.SoundGdx;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.Player;

public class SoundMap {
    public int h;
    public int id;
    public boolean isPlay;
    public float pan = 0.0f;
    public SoundGdx soundSystem;
    public int w;
    public int x;
    public int xCenter;
    public int y;
    public int yCenter;

    public SoundMap(int id2, int x2, int y2, int w2, int h2) {
        this.xCenter = x2;
        this.yCenter = y2 - (h2 / 2);
        this.x = x2 - (w2 / 2);
        this.y = y2 - h2;
        this.w = w2;
        this.h = h2;
        this.id = id2;
    }

    public void paint(mGraphics g) {
        if (!mSystem.isTest) {
            return;
        }
        if (this.isPlay) {
            g.setColor(16746496);
            g.fillRect(this.x - MapScr.cmx, this.y - MapScr.cmy, this.w, this.h);
            return;
        }
        g.setColor(0, 0.25f);
        g.fillRect(this.x - MapScr.cmx, this.y - MapScr.cmy, this.w, this.h);
    }

    public void update() {
        if (Player.myCharz().cx <= this.x || Player.myCharz().cx >= this.x + this.w || Player.myCharz().cy <= this.y || Player.myCharz().cy >= this.y + this.h) {
            this.isPlay = false;
            stop();
            return;
        }
        updatePan();
        this.isPlay = true;
        SoundGdx soundGdx = this.soundSystem;
        if (soundGdx != null) {
            soundGdx.pan = this.pan;
            soundGdx.play();
        }
    }

    public void updatePan() {
        if (Player.myCharz().cx > this.xCenter && Player.myCharz().cx < this.x + this.w) {
            this.pan = ((float) (Player.myCharz().cx - this.xCenter)) / ((((float) this.w) * 1.0f) / 2.0f);
        } else if (Player.myCharz().cx >= this.xCenter || Player.myCharz().cx <= this.x) {
            this.pan = 0.0f;
        } else {
            this.pan = ((float) (Player.myCharz().cx - this.xCenter)) / ((((float) this.w) * 1.0f) / 2.0f);
        }
    }

    public void stop() {
        SoundGdx soundGdx = this.soundSystem;
        if (soundGdx != null) {
            soundGdx.stop();
        }
    }
}
