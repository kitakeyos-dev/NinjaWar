package ninjawar.template;

import ninjawar.mylib.Atlas;
import ninjawar.mylib.FrameAtlas;
import ninjawar.myscr.Res;

public class TemplateNPC {
    public int[][] FRAME;
    public FrameAtlas frameImg;
    public String[] menu;
    public String name = "";
    public int npcTemplateId;
    public int numFrame;
    public short rangeMove = 0;
    public int speed = 5;
    public int speedY = 15;
    public String[] talkRandomNPCs;

    public TemplateNPC clones() {
        TemplateNPC result = new TemplateNPC();
        result.numFrame = this.numFrame;
        result.npcTemplateId = this.npcTemplateId;
        result.name = this.name;
        result.menu = this.menu;
        result.createNPCTemplate();
        result.talkRandomNPCs = this.talkRandomNPCs;
        result.rangeMove = this.rangeMove;
        return result;
    }

    public void createNPCTemplate() {
        Atlas atlas = Atlas.createAtlas((short) 2, (short) 23);
        if (atlas != null) {
            this.frameImg = new FrameAtlas(atlas, this.npcTemplateId + "", this.numFrame);
        }
    }

    public String getTalkRandom() {
        String[] strArr = this.talkRandomNPCs;
        if (strArr != null) {
            return strArr[Res.random(0, strArr.length - 1)];
        }
        return null;
    }
}
