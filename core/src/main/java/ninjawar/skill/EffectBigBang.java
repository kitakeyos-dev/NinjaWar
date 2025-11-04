package ninjawar.skill;

import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;
import ninjawar.supporter.mColorGdxCustom;

public class EffectBigBang {
    public int disBigBang;
    public int disMax;
    public boolean isBigBang = false;
    public int speedBigBang = 20;
    public int x;
    public int y;

    public void createBigBang(int x2, int y2, int speed) {
        this.isBigBang = true;
        this.x = x2;
        this.y = y2;
        this.speedBigBang = speed;
        int i = CanvasNinja.w;
        this.disMax = (i + (i / 4)) - x2;
    }

    public void paint(mGraphics g) {
        if (this.isBigBang) {
            g.paintCircle(this.x, this.y, this.disBigBang, mColorGdxCustom.WHITE_COLOR);
        }
    }

    public void update() {
        if (this.isBigBang) {
            int i = this.disBigBang + this.speedBigBang;
            this.disBigBang = i;
            if (i >= this.disMax) {
                resetBigBang();
                MapScr.shock_scr = 15;
            }
        }
    }

    public void resetBigBang() {
        this.isBigBang = false;
        this.disBigBang = 0;
        this.speedBigBang = 10;
    }
}
