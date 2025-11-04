package ninjawar.skill;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;

public class EffectFrameBuffToEnd {
    public int dx;
    public int dy;
    public int frame;
    FrameImage frameImg;
    public int idTemplate;
    public boolean isDone;
    public boolean isRemoveWhenDone;
    public int numFrame;

    private void resetFrame() {
        this.frame = 0;
        this.isDone = false;
    }

    public EffectFrameBuffToEnd(int id, int numFrame2, boolean isRemoveWhenDone2, int dx2, int dy2) {
        this.dx = dx2;
        this.dy = dy2;
        this.idTemplate = id;
        this.numFrame = numFrame2;
        this.isRemoveWhenDone = isRemoveWhenDone2;
        Image imgTemp = Image.createImage((short) id, (short) 20);
        if (imgTemp != null) {
            this.frameImg = new FrameImage(imgTemp, numFrame2);
        }
        resetFrame();
    }

    public void update() {
        if (this.frameImg != null && CanvasNinja.gameTick % 3 == 0) {
            int i = this.frame + 1;
            this.frame = i;
            if (i > this.numFrame - 1) {
                this.frame = 0;
                if (this.isRemoveWhenDone) {
                    this.isDone = true;
                    this.isRemoveWhenDone = false;
                    this.frameImg = null;
                }
            }
        }
    }

    public void paint(mGraphics g, int x, int y) {
        FrameImage frameImage = this.frameImg;
        if (frameImage != null) {
            frameImage.drawFrame(this.frame, (((float) x) - (frameImage.frameWidth / 2.0f)) + ((float) this.dx), (((float) y) - frameImage.frameHeight) + ((float) this.dy), g);
        }
    }
}
