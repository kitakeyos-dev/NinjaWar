package ninjawar.model;

import java.util.Iterator;
import java.util.Vector;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;

public class MissionPaint {
    public static mFont fontPaintMission = mFont.tahoma_7_small;
    public static mFont fontPaintTile = mFont.tahoma_7b_white_orange;
    public final int NUM_MAX_MISSION = 2;
    public boolean isPaint = true;
    int margin = 5;
    int marginEachMission = 2;
    int marginH = 5;
    int marginTitleAndMission = 3;
    int marginW = (5 * 2);
    int speedClose = 8;
    public Vector<MissionPaintDetail> vecMission = new Vector<>();
    public Vector<MissionPaintDetail> vecMissionPaints = new Vector<>();
    public int x;
    public int y;
    public int yMore;

    public void init(Vector<MissionPaintDetail> vecMissionPaint, int yMission) {
        this.x = NinjaMidlet.PADDING_TAI_THO;
        this.y = yMission;
        this.vecMissionPaints = vecMissionPaint;
    }

    public void update() {
    }

    public void updatePointer() {
    }

    public void paint(mGraphics g) {
        int i = 0;
        Iterator<MissionPaintDetail> it = this.vecMissionPaints.iterator();
        while (it.hasNext()) {
            MissionPaintDetail md = it.next();
            md.paintNormal(g, this.x, this.y + ((md.hPaint + this.margin) * i) + this.yMore + this.marginH, fontPaintMission);
            i++;
        }
        CanvasNinja.resetTrans(g);
    }
}
