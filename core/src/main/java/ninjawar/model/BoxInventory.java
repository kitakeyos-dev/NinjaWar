package ninjawar.model;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mGraphics;
import ninjawar.object.Item;

public class BoxInventory {
    public FrameImage boxFrame;
    public Item item;

    public BoxInventory() {
    }

    public BoxInventory(FrameImage boxFrame2, Item item2) {
        this.boxFrame = boxFrame2;
        this.item = item2;
    }

    public void paint(mGraphics g, int x, int y) {
        FrameImage frameImage = this.boxFrame;
        if (frameImage != null) {
            frameImage.drawFrame(0, x, y, false, g);
        }
        Item item2 = this.item;
        if (item2 != null) {
            FrameImage frameImage2 = this.boxFrame;
            item2.paintCenter(g, x, y, (int) frameImage2.frameWidth, (int) frameImage2.frameHeight, false, false);
        }
    }
}
