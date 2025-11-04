package ninjawar.screen;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportTranslate;

public class LoadingMapScr {
    public FrameImage frameWaiting = LoadDataManager.frameWaiting;
    public int indexFrame;

    public void paint(mGraphics g) {
        g.setColor(0);
        g.fillRect(0, 0, CanvasNinja.w, CanvasNinja.h);
        FrameImage frameImage = this.frameWaiting;
        frameImage.drawFrame(this.indexFrame, (((float) CanvasNinja.w) - frameImage.frameWidth) / 2.0f, (((float) CanvasNinja.h) - frameImage.frameHeight) / 2.0f, 0, g);
        mFont.tahoma_7b_blue.drawString(g, SupportTranslate.getTextLangue("changingMap"), CanvasNinja.w / 2, ((((float) CanvasNinja.h) - this.frameWaiting.frameHeight) / 2.0f) - 20.0f, 2);
    }

    public void update() {
        CanvasNinja.clearAllPointer();
        this.indexFrame++;
        if (this.indexFrame > this.frameWaiting.nFrame - 1) {
            this.indexFrame = 0;
        }
    }
}
