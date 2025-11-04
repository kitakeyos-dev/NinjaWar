package ninjawar.template;

import ninjawar.mylib.Atlas;
import ninjawar.mylib.FrameAtlas;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Player;
import ninjawar.screen.subtab.SubTabBuyShop;
import ninjawar.supporter.LoadDataManager;

public class ItemTemplate {
    public byte classId;
    public String description = "";
    public FrameAtlas frameAtlas;
    public byte gender;
    public int h;
    public int iconID = -1;
    public short id;
    int indexFrame = 0;
    public boolean isUpToUp;
    public short level;
    public String name = "";
    public int numFrame = 1;
    public short part;
    public byte type;
    public TypeItemtemplate typeItemtemplate = new TypeItemtemplate();
    public int w;

    public void createItemTemplate() {
        Atlas atlas = Atlas.createAtlas((short) 1, (short) 23, (short) -1);
        if (atlas != null) {
            FrameAtlas frameAtlas2 = new FrameAtlas(atlas, this.iconID + "");
            this.frameAtlas = frameAtlas2;
            this.w = (int) frameAtlas2.frameWidth;
            this.h = (int) frameAtlas2.frameHeight;
            return;
        }
        this.w = 32;
        this.h = 32;
    }

    public ItemTemplate() {
    }

    public void createDefault() {
        this.iconID = -1;
        createItemTemplate();
    }

    public void paint(mGraphics g, int x, int y) {
        paint(g, x, y, false);
    }

    public void paint(mGraphics g, int x, int y, boolean useClip) {
        FrameAtlas frameAtlas2 = this.frameAtlas;
        if (frameAtlas2 != null) {
            frameAtlas2.drawFrame(this.indexFrame, ((float) x) - (frameAtlas2.frameWidth / 2.0f), ((float) y) - frameAtlas2.frameHeight, g);
            return;
        }
        Image image = LoadDataManager.imgItemDefault;
        g.drawImage(image, (float) ((image.getRWidth() / 2) + x), (float) (y - LoadDataManager.imgItemDefault.getRHeight()));
    }

    public void paintCenter(mGraphics g, int x, int y, boolean useClip) {
        FrameAtlas frameAtlas2 = this.frameAtlas;
        if (frameAtlas2 != null) {
            frameAtlas2.drawFrame(this.indexFrame, ((float) x) + (frameAtlas2.frameWidth / 2.0f), ((float) y) - (frameAtlas2.frameHeight / 2.0f), useClip, g);
            return;
        }
        Image image = LoadDataManager.imgItemDefault;
        g.drawImage(image, (image.getRWidth() / 2) + x, y - (LoadDataManager.imgItemDefault.getRHeight() / 2), useClip);
    }

    public void paint(mGraphics g, int x, int y, int wBox, int hBox, int num, boolean useClip, boolean isPaintNum) {
        FrameAtlas frameAtlas2 = this.frameAtlas;
        if (frameAtlas2 != null) {
            frameAtlas2.drawFrame(g, this.indexFrame, (float) (((wBox - ((int) frameAtlas2.frameWidth)) / 2) + x), (float) (((hBox - ((int) frameAtlas2.frameHeight)) / 2) + y), 0, 0, useClip, false, 0, Player.myCharz().isGetSharigan);
        }
        if (isPaintNum) {
            mFont mfont = mFont.fontPaintNumberInventory;
            mfont.drawString(g, "" + num, x + 5, ((y + hBox) - mfont.getHeight()) - 5, 0, useClip);
            return;
        }
        int i = num;
    }

    public ItemTemplate(short templateID, byte type2, byte gender2, String name2, String description2, short level2, short iconID2, byte numFrame2, short part2, byte classId2) {
        this.id = templateID;
        this.type = type2;
        this.gender = gender2;
        if (CanvasNinja.subTab instanceof SubTabBuyShop) {
            this.name = name2;
        } else {
            this.name = name2;
        }
        this.description = description2;
        this.level = level2;
        this.iconID = iconID2;
        this.part = part2;
        this.classId = classId2;
        this.numFrame = numFrame2;
        createItemTemplate();
    }

    public ItemTemplate clones() {
        ItemTemplate result = new ItemTemplate();
        result.id = this.id;
        result.type = this.type;
        result.gender = this.gender;
        result.name = this.name;
        result.description = this.description;
        result.level = this.level;
        result.iconID = this.iconID;
        result.part = this.part;
        result.isUpToUp = this.isUpToUp;
        result.numFrame = this.numFrame;
        result.typeItemtemplate = this.typeItemtemplate;
        result.createItemTemplate();
        return result;
    }
}
