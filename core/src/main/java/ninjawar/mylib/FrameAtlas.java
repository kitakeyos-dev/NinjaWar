package ninjawar.mylib;

import com.badlogic.gdx.graphics.g2d.Sprite;
import ninjawar.myscr.Res;

public class FrameAtlas {
    public Atlas atlas;
    public float frameHeight;
    public float frameWidth;
    public int nFrame;
    public Sprite[] sps;

    public FrameAtlas(Atlas atlas2, String name, int numFrame, boolean useFrame) {
        init(atlas2, name, numFrame, useFrame);
    }

    public FrameAtlas(Atlas atlas2, String name, int numFrame) {
        init(atlas2, name, numFrame, true);
    }

    public FrameAtlas(Atlas atlas2, String name) {
        init(atlas2, name, 1, false);
    }

    public void init(Atlas atlas2, String name, int numFrame, boolean useFrame) {
        this.atlas = atlas2;
        if (atlas2 != null) {
            this.sps = new Sprite[numFrame];
            String nameReal = name;
            if (useFrame) {
                nameReal = name + "00";
            }
            Sprite spriteTemp = atlas2.sprites.get(nameReal);
            this.nFrame = numFrame;
            float f = 31.0f;
            this.frameWidth = spriteTemp != null ? spriteTemp.getWidth() / mGraphics.zoomLevel : 31.0f;
            if (spriteTemp != null) {
                f = spriteTemp.getHeight() / mGraphics.zoomLevel;
            }
            this.frameHeight = f;
            for (int i = 0; i < this.sps.length; i++) {
                if (useFrame) {
                    if (i < 10) {
                        nameReal = name + "0" + i;
                    } else {
                        nameReal = name + i;
                    }
                }
                Sprite spriteTemp2 = atlas2.texture.createSprite(nameReal);
                if (spriteTemp2 == null) {
                    Res.outz("nulll aaaaaa:" + nameReal);
                }
                if (spriteTemp2 != null) {
                    spriteTemp2.setSize(spriteTemp2.getWidth() * mGraphics.zoomLevel * mGraphics.zoomGdx, spriteTemp2.getHeight() * mGraphics.zoomLevel * mGraphics.zoomGdx);
                    spriteTemp2.setOrigin(0.0f, 0.0f);
                    spriteTemp2.flip(false, true);
                }
                this.sps[i] = spriteTemp2;
            }
        }
    }

    public void drawFrame(mGraphics g, int index, float x, float y, int anchor, int trans, boolean useClip, boolean isOpacity, int percentOpacity, boolean isBlack) {
        Sprite sprite;
        int i = index;
        Sprite[] spriteArr = this.sps;
        if (spriteArr != null && i < spriteArr.length && (sprite = spriteArr[i]) != null) {
            g.drawSprite(sprite, x, y, anchor, trans, useClip, isOpacity, percentOpacity, isBlack);
        }
    }

    public void drawFrame(int index, float x, float y, boolean useClip, mGraphics g) {
        drawFrame(g, index, x, y, 0, 0, useClip, false, 0, false);
    }

    public void drawFrame(int index, float x, float y, int trans, boolean useClip, mGraphics g) {
        drawFrame(g, index, x, y, 0, trans, useClip, false, 0, false);
    }

    public void drawFrame(int index, float x, float y, int trans, int anchor, mGraphics g) {
        drawFrame(g, index, x, y, anchor, trans, false, false, 0, false);
    }

    public void drawFrame(int index, float x, float y, int trans, mGraphics g) {
        drawFrame(g, index, x, y, 0, trans, false, false, 0, false);
    }

    public void drawFrame(int index, float x, float y, int trans, mGraphics g, boolean isOpacity, int percent) {
        drawFrame(g, index, x, y, 0, trans, false, isOpacity, percent, false);
    }

    public void drawFrame(int index, float x, float y, mGraphics g) {
        drawFrame(g, index, x, y, 0, 0, false, false, 0, false);
    }

    public int getRWidth() {
        return (int) this.frameWidth;
    }

    public int getRHeight() {
        return (int) this.frameHeight;
    }
}
