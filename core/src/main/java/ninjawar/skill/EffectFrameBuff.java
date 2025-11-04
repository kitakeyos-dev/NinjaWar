package ninjawar.skill;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;

public class EffectFrameBuff {
    public FrameImage frameMore;
    public int idTemplate;
    public int indexFrameMore;
    public boolean isFrameFirst = true;

    public EffectFrameBuff(int id) {
        this.idTemplate = id;
        Image imgTemp = Image.createImage((short) id, (short) 17);
        if (imgTemp != null) {
            this.frameMore = new FrameImage(imgTemp, 12);
        }
        resetFrame();
    }

    public void paint(mGraphics g, int x, int y, long timeBuff, int xMore, int yMore) {
        if (this.frameMore != null) {
            if (CanvasNinja.gameTick % 3 == 0) {
                this.indexFrameMore++;
            }
            if (this.isFrameFirst) {
                if (this.indexFrameMore > 4) {
                    this.indexFrameMore = 4;
                    this.isFrameFirst = false;
                }
            } else if (timeBuff != -1 && timeBuff - 700 <= mSystem.currentTimeMillis()) {
                int i = this.indexFrameMore;
                int i2 = this.frameMore.nFrame;
                if (i > i2 - 1) {
                    this.indexFrameMore = i2;
                }
            } else if (this.indexFrameMore > 7) {
                this.indexFrameMore = 4;
                this.isFrameFirst = false;
            }
            FrameImage frameImage = this.frameMore;
            frameImage.drawFrame(this.indexFrameMore, ((float) x) - (frameImage.frameWidth / 2.0f), (float) (y + yMore), g);
        }
    }

    public void resetFrame() {
        this.isFrameFirst = true;
        this.indexFrameMore = 0;
    }
}
