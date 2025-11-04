package ninjawar.model;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;

public class FrameEffectMore {
    public FrameImage frameEffectMore;
    int indexEffectMore = 0;
    int xEffectMore;
    int yEffectMore;

    public FrameEffectMore(FrameImage frameImg, int x, int y) {
        this.frameEffectMore = frameImg;
        this.xEffectMore = x;
        this.yEffectMore = y;
    }

    public void paint(mGraphics g) {
        FrameImage frameImage = this.frameEffectMore;
        if (frameImage != null) {
            frameImage.drawFrame(this.indexEffectMore, (float) this.xEffectMore, (float) this.yEffectMore, g);
        }
    }

    public void update() {
        FrameImage frameImage = this.frameEffectMore;
        if (frameImage != null && CanvasNinja.gameTick % 2 == 0) {
            int i = this.indexEffectMore + 1;
            this.indexEffectMore = i;
            if (i > frameImage.nFrame - 1) {
                this.frameEffectMore = null;
                this.indexEffectMore = 0;
            }
        }
    }
}
