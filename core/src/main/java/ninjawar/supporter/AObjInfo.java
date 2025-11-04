package ninjawar.supporter;

import java.util.Vector;
import ninjawar.mylib.mGraphics;
import ninjawar.small.FrameEff;

public class AObjInfo {
    public BigImage[] bigImgs;
    public int height = -1;
    public int width = -1;

    public void loadDataFromTool(short[] idPart) {
        try {
            this.bigImgs = new BigImage[idPart.length];
            int i = 0;
            while (true) {
                BigImage[] bigImageArr = this.bigImgs;
                if (i < bigImageArr.length) {
                    bigImageArr[i] = new BigImage();
                    this.bigImgs[i].readDataFromTool(idPart[i], (byte) 5, i);
                    i++;
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(mGraphics g, int x, int y, int frame, byte direct, byte subDirect, int opacityPercent, boolean isOpacity, boolean useClip) {
        int k = 0;
        while (true) {
            BigImage[] bigImageArr = this.bigImgs;
            if (k < bigImageArr.length) {
                bigImageArr[k].paint(g, x, y, frame, direct, subDirect, opacityPercent, isOpacity, useClip);
                k++;
            } else {
                return;
            }
        }
    }

    public int getW() {
        BigImage bigImage;
        Vector<FrameEff> vector;
        int i = this.width;
        if (i != -1) {
            return i;
        }
        this.width = 35;
        BigImage[] bigImageArr = this.bigImgs;
        if (!(bigImageArr == null || (bigImage = bigImageArr[0]) == null || (vector = bigImage.listFrame) == null || 0 >= vector.size())) {
            this.width = this.bigImgs[0].smallImage[this.bigImgs[0].listFrame.elementAt(0).listPartTop.elementAt(0).idSmallImg].w;
        }
        return this.width;
    }

    public int getH() {
        BigImage bigImage;
        Vector<FrameEff> vector;
        int i = this.height;
        if (i != -1) {
            return i;
        }
        this.height = 35;
        BigImage[] bigImageArr = this.bigImgs;
        if (!(bigImageArr == null || (bigImage = bigImageArr[0]) == null || (vector = bigImage.listFrame) == null || 0 >= vector.size())) {
            this.height = this.bigImgs[0].smallImage[this.bigImgs[0].listFrame.elementAt(0).listPartTop.elementAt(0).idSmallImg].h;
        }
        return this.height;
    }
}
