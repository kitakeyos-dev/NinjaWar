package ninjawar.model;

import ninjawar.mylib.Image;
import ninjawar.mylib.mGraphics;

public class FrameRegion {
    public float frameHeight;
    public float frameWidth;
    public Image imgFrame;
    public int xStart;
    public int yStart;

    public FrameRegion(Image imgFrame2, int xStart2, int yStart2, int w, int h) {
        this.imgFrame = imgFrame2;
        this.frameWidth = (float) w;
        this.frameHeight = (float) h;
        this.xStart = xStart2;
        this.yStart = yStart2;
    }

    public FrameRegion(Image imgFrame2, int xStart2, int yStart2, float w, float h) {
        if (w > 0.0f && h > 0.0f) {
            this.imgFrame = imgFrame2;
            this.frameWidth = w;
            this.frameHeight = h;
            this.xStart = xStart2;
            this.yStart = yStart2;
        }
    }

    public void drawFrame(mGraphics g, int x, int y, int trans, boolean isUseClip, boolean isOpacity, int percentOpacity) {
        g.drawRegion(this.imgFrame, (float) this.xStart, (float) this.yStart, this.frameWidth, this.frameHeight, trans, (float) x, (float) y, 0, isOpacity, isUseClip, percentOpacity);
    }

    public void drawFrame(mGraphics g, float x, float y, int trans, boolean isUseClip, boolean isOpacity, int percentOpacity) {
        drawFrame(g, (int) x, (int) y, 0, isUseClip, isOpacity, percentOpacity);
    }
}
