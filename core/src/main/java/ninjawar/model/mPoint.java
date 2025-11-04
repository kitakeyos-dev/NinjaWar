package ninjawar.model;

public class mPoint {
    public int h;
    public boolean isDoDoubleJump;
    public boolean isDoJump;
    public int w;
    public int x;
    public int y;

    public mPoint(int x2, int y2) {
        this.x = x2;
        this.y = y2;
    }

    public mPoint(int x2, int y2, int w2, int h2) {
        this.x = x2;
        this.y = y2;
        this.w = w2;
        this.h = h2;
    }
}
