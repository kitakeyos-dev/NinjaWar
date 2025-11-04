package ninjawar.small;

import ninjawar.mylib.Image;

public class mPartFrame {
    public short dx;
    public short dy;
    public byte flip;
    public byte idSmallImg;
    public Image imgBig;
    public int index;
    public byte onTop = 0;
    public byte typePart;

    public mPartFrame(short dx2, short dy2, byte idSmall) {
        this.idSmallImg = idSmall;
        this.dx = dx2;
        this.dy = dy2;
    }

    public mPartFrame() {
    }
}
