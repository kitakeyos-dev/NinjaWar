package ninjawar.skill;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Player;

public class EffectSkillFrameNoTarget extends MainEffect {
    int dirTemp;
    int dx;
    int dy;
    int frame;
    FrameImage frameImg;
    boolean isRemove;
    boolean isRemoveWhenDone;
    int numFrame;
    long timeBuff;
    int x;
    int y;

    public void init(Player charUse, int id, int numFrame2, boolean isRemoveWhenDone2, int dx2, int dy2, int timeExist) {
        Player player = charUse;
        int i = numFrame2;
        Image imgTemp = Image.createImage((short) id, (short) 20);
        if (imgTemp != null) {
            this.frameImg = new FrameImage(imgTemp, i);
        }
        FrameImage frameImage = this.frameImg;
        int i2 = 0;
        int wFrame = frameImage != null ? (int) frameImage.frameWidth : 0;
        int xUse = player.cx + (charUse.getW() / 2);
        int yUse = player.cy;
        if (player.cdir == -1) {
            xUse = (player.cx - (charUse.getW() / 2)) - wFrame;
        }
        this.dx = dx2;
        this.dy = dy2;
        this.x = xUse;
        this.y = yUse;
        this.isRemoveWhenDone = isRemoveWhenDone2;
        this.numFrame = i;
        this.timeBuff = ((long) timeExist) + mSystem.currentTimeMillis();
        if (Player.myCharz().cdir != 1) {
            i2 = 2;
        }
        this.dirTemp = i2;
    }

    public EffectSkillFrameNoTarget(Player charUse, int id, int numFrame2, boolean isRemoveWhenDone2, int dx2, int dy2, int timeExist) {
        init(charUse, id, numFrame2, isRemoveWhenDone2, dx2, dy2, timeExist);
    }

    public void paint(mGraphics g) {
        FrameImage frameImage = this.frameImg;
        if (frameImage != null) {
            frameImage.drawFrame(this.frame, (float) (this.x + this.dx), (((float) this.y) - frameImage.frameHeight) + ((float) this.dy), this.dirTemp, g);
        }
    }

    public void update() {
        if (this.frameImg != null && CanvasNinja.gameTick % 2 == 0) {
            int i = this.frame + 1;
            this.frame = i;
            if (i > this.numFrame - 1) {
                this.frame = 0;
                if (this.isRemoveWhenDone) {
                    this.isRemove = true;
                    this.isRemoveWhenDone = false;
                    this.frameImg = null;
                }
            }
        }
        long j = this.timeBuff;
        if (j != -1 && j <= mSystem.currentTimeMillis()) {
            this.isRemove = true;
            this.frameImg = null;
        }
    }
}
