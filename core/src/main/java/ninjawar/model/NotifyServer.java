package ninjawar.model;

import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;

public class NotifyServer {
    byte dir;
    public mFont fontPaint = mFont.tahoma_7;
    public int h;
    public int hReal;
    boolean isClose;
    public boolean isDone;
    boolean isShowText;
    boolean isStart;
    int margin = 5;
    int marginLeft = 10;
    float speed;
    public String str = "";
    public int w;
    public int x;
    public int xBG;
    public int xEnd;
    public int xStart;
    public int y;

    private void init(String text, byte dirFrom) {
        this.speed = 0.5f;
        this.str = text;
        this.h = 0;
        this.hReal = this.fontPaint.getHeight() + (this.margin * 3);
        int i = CanvasNinja.w;
        this.w = i;
        this.x = i;
        this.xBG = i;
        this.xStart = i;
        this.y = 0;
        this.isStart = true;
        int width = this.fontPaint.getWidth(text);
        this.xEnd = 0;
        this.dir = -1;
    }

    public NotifyServer(String text) {
        init(text, (byte) 1);
    }

    public void update() {
        if (this.isStart) {
            int i = this.h + 2;
            this.h = i;
            int i2 = this.hReal;
            if (i >= i2) {
                this.h = i2;
                this.isStart = false;
                this.isShowText = true;
            }
        }
        if (this.isShowText) {
            int i3 = (int) (((float) this.x) - this.speed);
            this.x = i3;
            if (i3 <= this.xEnd) {
                this.isShowText = false;
                this.isClose = true;
            }
        }
        if (this.isClose) {
            int i4 = this.h - 2;
            this.h = i4;
            if (i4 <= 0) {
                this.h = 0;
                this.isClose = false;
                this.isDone = true;
            }
        }
    }

    public void paint(mGraphics g) {
        CanvasNinja.resetTrans(g);
        g.setColor(0);
        g.fillRect(0.0f, (float) this.y, (float) CanvasNinja.w, (float) this.h, true, true, 80);
        g.resetColor();
        if (this.isShowText) {
            mGraphics mgraphics = g;
            this.fontPaint.drawString(mgraphics, this.str, this.x, this.y + ((this.h - this.fontPaint.getHeight()) / 2), 0, true);
        }
    }
}
