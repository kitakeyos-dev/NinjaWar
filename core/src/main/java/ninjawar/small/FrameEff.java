package ninjawar.small;

import java.util.Vector;

public class FrameEff {
    public int indexSmallBot = -1;
    public int indexSmallTop = -1;
    public Vector<mPartFrame> listPart = new Vector<>();
    public Vector<mPartFrame> listPartBottom = new Vector<>();
    public Vector<mPartFrame> listPartTop = new Vector<>();

    public FrameEff() {
    }

    public FrameEff(Vector<mPartFrame> vecTop, Vector<mPartFrame> vecBot) {
        this.listPartBottom = vecBot;
        this.listPartTop = vecTop;
    }
}
