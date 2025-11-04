package ninjawar.mylib;

import com.badlogic.gdx.graphics.Texture;

public class FrameImage {
    public int Id = -1;
    public float frameHeight;
    public float frameWidth;
    public Image imgFrame;
    public int nFrame;
    public int xMoreFrameMore = 8;
    public int yMoreFrameMore = 8;

    public FrameImage(Image img, float width, float height) {
        if (img != null) {
            this.imgFrame = img;
            this.frameWidth = width;
            this.frameHeight = height;
            int rHeight = (int) (((float) img.getRHeight()) / height);
            this.nFrame = rHeight;
            if (rHeight < 1) {
                this.nFrame = 1;
            }
        }
    }

    public FrameImage(Image img, int numFrameDoc) {
        if (img != null) {
            this.imgFrame = img;
            this.frameWidth = (float) img.getRWidth();
            this.frameHeight = (float) (img.getRHeight() / numFrameDoc);
            this.nFrame = numFrameDoc;
            if (numFrameDoc < 1) {
                this.nFrame = 1;
            }
        }
    }

    public void drawFrame(int idx, float x, float y, int trans, mGraphics g, boolean isOpacity, int percentOpacity) {
        drawFrame(idx, x, y, trans, g, false, isOpacity, percentOpacity, false);
    }

    public void drawFrame(int idx, float x, float y, int trans, mGraphics g, boolean isUseClip, boolean isOpacity, int percentOpacity, boolean isBlack) {
        int i = idx;
        if (i >= 0 && i < this.nFrame) {
            Image image = this.imgFrame;
            float f = this.frameHeight;
            int i2 = (int) f;
            g.drawRegion(image, 0.0f, ((float) i) * f, (int) this.frameWidth, i2, trans, x, y, 0, isUseClip, isOpacity, percentOpacity, isBlack);
        }
    }

    public void drawFrameSpec(int idx, float x, float y, float w, float h, int trans, mGraphics g, boolean isOpacity, int percentOpacity) {
        int i = idx;
        if (i >= 0 && i < this.nFrame) {
            g.drawRegion(this.imgFrame, 0.0f, ((float) i) * this.frameHeight, w, h, trans, x, y, 0, isOpacity, false, percentOpacity);
        }
    }

    public void drawFrame(int idx, float x, float y, int trans, boolean useclip, mGraphics g, boolean isOpacity, int percentOpacity) {
        int i = idx;
        if (i >= 0 && i < this.nFrame) {
            Image image = this.imgFrame;
            float f = this.frameHeight;
            g.drawRegion(image, 0.0f, ((float) i) * f, this.frameWidth, f, trans, x, y, 0, isOpacity, useclip, percentOpacity);
        }
    }

    public void drawFrame(int idx, float x, float y, int trans, int orthor, boolean useClip, mGraphics g) {
        int i = idx;
        if (i >= 0 && i < this.nFrame) {
            Image image = this.imgFrame;
            float f = this.frameHeight;
            g.drawRegion(image, 0.0f, ((float) i) * f, this.frameWidth, f, trans, x, y, orthor, useClip);
        }
    }

    public void drawFrame(int idx, float x, float y, int trans, int orthor, boolean useClip, mGraphics g, boolean isOpacity, int percent) {
        int i = idx;
        if (i >= 0 && i < this.nFrame) {
            Image image = this.imgFrame;
            float f = this.frameHeight;
            g.drawRegion(image, 0.0f, ((float) i) * f, this.frameWidth, f, trans, x, y, orthor, isOpacity, useClip, percent);
        }
    }

    public void drawFrame(int idx, float x, float y, int trans, mGraphics g) {
        int i = idx;
        if (i >= 0 && i < this.nFrame) {
            Image image = this.imgFrame;
            float f = this.frameHeight;
            g.drawRegion(image, 0.0f, ((float) i) * f, this.frameWidth, f, trans, x, y, 0, false);
        }
    }

    public void drawFrame(int idx, int x, int y, boolean useClip, mGraphics g) {
        int i = idx;
        if (i < 0 || i >= this.nFrame) {
            int i2 = x;
            int i3 = y;
            return;
        }
        Image image = this.imgFrame;
        float f = this.frameHeight;
        g.drawRegion(image, 0.0f, ((float) i) * f, this.frameWidth, f, 0, (float) x, (float) y, 0, useClip);
    }

    public void drawFrame(int idx, float x, float y, mGraphics g) {
        drawFrame(idx, x, y, 0, g);
    }

    public void drawFrame(mGraphics g, int idx, float x, float y, int flip, boolean isOpacity, int percentOpacity) {
        drawFrame(g, idx, x, y, flip, false, isOpacity, percentOpacity);
    }

    public void drawFrame(mGraphics g, int idx, float x, float y, int flip, boolean isUseClip, boolean isOpacity, int percentOpacity) {
        int i = idx;
        if (i >= 0 && i < this.nFrame) {
            Image image = this.imgFrame;
            float f = this.frameHeight;
            g.drawRegion(image, 0.0f, ((float) i) * f, this.frameWidth, f, flip, x, y, 0, isOpacity, isUseClip, percentOpacity);
        }
    }

    public void drawFrame(int idx, float x, float y, int trans, boolean useClip, mGraphics g) {
        drawFrame(g, idx, x, y, 0, useClip, false, 0);
    }

    public void drawFrameSpecScale(int idx, float x, float y, int trans, boolean useClip, mGraphics g, float scale) {
        int i = idx;
        if (i < 0) {
        } else if (i < this.nFrame) {
            drawFrameSpecScale(idx, x, y, trans, useClip, g, scale, true);
        }
    }

    public void drawFrameSpecScale(int idx, float x, float y, int trans, boolean useClip, mGraphics g, float scale, boolean isMiddle) {
        int i = idx;
        if (i >= 0 && i < this.nFrame) {
            if (!isMiddle) {
                Texture texture = this.imgFrame.texture;
                float f = this.frameHeight;
                float f2 = this.frameWidth;
                float f3 = mGraphics.zoomLevel;
                mGraphics mgraphics = g;
                mgraphics._drawRegion(texture, 0.0f, ((float) i) * f, f2, f, 0, x * f3, y * f3, 0, useClip, false, false, 0, false, f3 * scale);
                return;
            }
            Texture texture2 = this.imgFrame.texture;
            float f4 = this.frameHeight;
            float f5 = this.frameWidth;
            float f6 = mGraphics.zoomLevel;
            mGraphics mgraphics2 = g;
            boolean z = useClip;
            mgraphics2._drawRegion(texture2, 0.0f, ((float) i) * f4, f5, f4, 0, (((f5 - (f5 * scale)) * f6) / 2.0f) + (x * f6), (y * f6) + (((f4 - (f4 * scale)) * f6) / 2.0f), 0, z, false, false, 0, false, f6 * scale);
        }
    }

    public void drawFrameXY(int idx, int idy, float x, float y, int orthor, boolean useClip, mGraphics g, boolean isOpacity, int percentOpacity) {
        int i = idx;
        if (i < 0 || i >= this.nFrame) {
            int i2 = idy;
            return;
        }
        Image image = this.imgFrame;
        float f = this.frameWidth;
        float f2 = this.frameHeight;
        g.drawRegion(image, ((float) i) * f, ((float) idy) * f2, f, f2, 0, x, y, orthor, isOpacity, useClip, percentOpacity);
    }

    public int getRWidth() {
        return (int) this.frameWidth;
    }

    public int getRHeight() {
        return (int) this.frameHeight;
    }
}
