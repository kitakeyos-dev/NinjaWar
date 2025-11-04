package ninjawar.model;

import java.util.Vector;
import ninjawar.mylib.mFont;

public class MissionTypes {
    public int type;
    public Vector<MissionPaintDetail> vecMissionPaintDetails = new Vector<>();

    public int getHType(mFont fontPaintTitle, int hTag, int marginTitleTag, int marginTagTag) {
        return fontPaintTitle.getHeight() + marginTitleTag + (this.vecMissionPaintDetails.size() * hTag) + ((this.vecMissionPaintDetails.size() - 1) * marginTagTag);
    }
}
