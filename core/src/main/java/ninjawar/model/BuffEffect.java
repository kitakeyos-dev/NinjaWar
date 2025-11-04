package ninjawar.model;

import ninjawar.mylib.Atlas;
import ninjawar.mylib.FrameAtlas;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;
import ninjawar.supporter.LoadDataManager;

public class BuffEffect {
    public static int COLOR_BLUE = 2;
    public static int COLOR_CHARKA_CAM = 7;
    public static int COLOR_CHARKA_TIM = 6;
    public static int COLOR_GREEN = 1;
    public static int COLOR_JUMP = 5;
    public static int COLOR_PINK = 3;
    public static int COLOR_SKILL_BIG = 4;
    public static int COLOR_YELLOW = 0;
    public static byte TYPE_CHARKA = 2;
    public static byte TYPE_JUMP = 1;
    public static byte TYPE_SKILL = 0;
    public static boolean isClickTrung = false;
    long duration;
    public FrameAtlas frameImg;
    public int h;
    int hCoolDown = LoadDataManager.coolDownBuffEffect.getRHeight();
    public boolean isFocus = false;
    public boolean isJump = false;
    public int pointerIndex = -1;
    public int tickClick;
    long timeUp;
    public int w;
    public int x;
    public int y;

    public BuffEffect(int x2, int y2, int idBuffIcon, long duration2) {
        Atlas atlas = Atlas.createAtlas((short) 12, (short) 23);
        if (atlas != null) {
            this.frameImg = new FrameAtlas(atlas, idBuffIcon + "");
        }
        FrameAtlas frameAtlas = this.frameImg;
        float f = frameAtlas.frameWidth;
        this.x = x2 - ((int) f);
        float f2 = frameAtlas.frameHeight;
        this.y = y2 - ((int) f2);
        int i = 31;
        this.w = (int) f;
        this.h = (int) f2;
        this.timeUp = mSystem.currentTimeMillis() + duration2;
        this.duration = duration2;
    }

    public void paint(mGraphics g) {
        CanvasNinja.resetClip(g);
        if (this.timeUp > mSystem.currentTimeMillis()) {
            FrameAtlas frameAtlas = this.frameImg;
            if (frameAtlas != null) {
                frameAtlas.drawFrame(this.isFocus ? 1 : 0, (float) this.x, (float) this.y, 0, true, g);
                int yMore = this.isFocus ? 2 : 0;
                this.hCoolDown = (int) ((((long) LoadDataManager.coolDownBuffEffect.getRHeight()) * (this.timeUp - mSystem.currentTimeMillis())) / this.duration);
                g.setClip(this.x, this.y + yMore + (LoadDataManager.coolDownBuffEffect.getRHeight() - this.hCoolDown), LoadDataManager.coolDownBuffEffect.getRWidth(), this.hCoolDown);
                g.drawImage(LoadDataManager.coolDownBuffEffect, this.x, this.y + yMore, true);
                return;
            }
            return;
        }
        MapScr.buffEffects.remove(this);
    }

    public void update() {
        if (this.isFocus) {
            this.tickClick++;
        }
    }
}
