package ninjawar.model;

import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;

public class Rectangle {
    public int h;
    public int w;
    public int x;
    public int x1;
    public int x2;
    public int y;
    public int y1;
    public int y2;

    public Rectangle() {
    }

    public Rectangle(int x3, int y3, int w2, int h2) {
        this.x = x3;
        this.y = y3;
        this.w = w2;
        this.h = h2;
        this.x1 = x3;
        this.y1 = y3 + h2;
        this.x2 = x3 + w2;
        this.y2 = y3;
    }

    public void updatePos(int x3, int y3, int w2, int h2) {
        this.x = x3;
        this.y = y3;
        this.w = w2;
        this.h = h2;
        this.x1 = x3;
        this.y1 = y3 + h2;
        this.x2 = x3 + w2;
        this.y2 = y3;
    }

    public boolean isInRectangle(Rectangle rectangle) {
        if (this.x1 > rectangle.x2 || rectangle.x1 > this.x2 || this.y1 < rectangle.y2 || rectangle.y1 < this.y2) {
            return false;
        }
        return true;
    }

    public void paint(mGraphics g) {
        CanvasNinja.paintBox(g, this.x, this.y, this.w, this.h, 2);
        mFont mfont = mFont.tahoma_7b_blue;
        mfont.drawString(g, this.x1 + "," + this.y1, this.x1, this.y1 + 5, 2);
    }
}
