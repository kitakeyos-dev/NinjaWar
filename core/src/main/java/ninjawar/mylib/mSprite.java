package ninjawar.mylib;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class mSprite {
    public int h;
    public Sprite sprite;
    public int w;

    public mSprite(Sprite sprite2) {
        this.sprite = sprite2;
        this.w = (int) (sprite2.getWidth() / mGraphics.zoomLevel);
        this.h = (int) (sprite2.getHeight() / mGraphics.zoomLevel);
    }

    public int getWidth() {
        return this.w;
    }

    public int getHeight() {
        return this.h;
    }
}
