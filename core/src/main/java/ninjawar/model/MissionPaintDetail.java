package ninjawar.model;

import java.util.Vector;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.object.Item;

public class MissionPaintDetail {
    public static Vector<MissionPaintDetail> vecMissionPaintDetails = new Vector<>();
    public String[] contentNormal;
    public String[] descNormal;
    public String description = "";
    public String detailMission = "";
    public String detailMission2 = "";
    public int hPaint;
    public int id;
    public int idMap;
    public int idNpc;
    public boolean isOpen;
    public int numCur1;
    public int numCur2;
    public int numMaxMons1;
    public int numMaxMons2;
    public String title = "";
    public byte type;
    public Vector<Item> vecItems = new Vector<>();

    public void paintNormal(mGraphics g, int x, int y, mFont fontPaint) {
        fontPaint.drawString(g, this.title, x, y);
        fontPaint.drawString(g, this.detailMission, x, fontPaint.getHeight() + y + 3);
        fontPaint.drawString(g, this.detailMission2, x, ((fontPaint.getHeight() + 3) * 2) + y);
    }

    public void setDescNormal(mFont font, int w) {
        this.descNormal = font.splitFontArray(this.description, w);
    }

    public void setContentNormal(mFont font, int w) {
        this.contentNormal = font.splitFontArray(this.detailMission, w);
    }
}
