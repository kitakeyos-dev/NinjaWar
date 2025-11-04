package ninjawar.supporter;

import java.util.Vector;
import ninjawar.mylib.mGraphics;
import ninjawar.myscr.Res;
import ninjawar.small.FrameEff;

public class ABossInfo {
    public static int[] ORDER_PAINT = {0};
    public BigImage[] bigImgs;
    public int height = -1;
    public int width = -1;

    public ABossInfo(short[] ID_LAYERS) {
        loadDataFromTool(ID_LAYERS);
    }

    public void loadDataFromTool(short[] idPart) {
        try {
            this.bigImgs = new BigImage[idPart.length];
            int i = 0;
            while (true) {
                BigImage[] bigImageArr = this.bigImgs;
                if (i < bigImageArr.length) {
                    bigImageArr[i] = new BigImage();
                    BigImage bigImage = this.bigImgs[i];
                    bigImage.isOverSize = true;
                    bigImage.readDataFromTool(idPart[i], (byte) 0, i);
                    i++;
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(mGraphics g, int x, int y, int frame, byte direct, byte subDirect, int opacityPercent, boolean isOpacity, boolean useClip, boolean isSharigan) {
        for (int i = 0; i < ORDER_PAINT.length; i++) {
            int k = 0;
            while (true) {
                BigImage[] bigImageArr = this.bigImgs;
                if (k >= bigImageArr.length) {
                    break;
                }
                BigImage bigImage = bigImageArr[k];
                if (bigImage != null && bigImage.indexBig == ORDER_PAINT[i]) {
                    bigImage.paint(g, x, y, frame, direct, subDirect, opacityPercent, isOpacity, useClip, isSharigan);
                    break;
                }
                k++;
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
        this.width = 50;
        BigImage[] bigImageArr = this.bigImgs;
        if (!(bigImageArr == null || (bigImage = bigImageArr[0]) == null || (vector = bigImage.listFrame) == null || 0 >= vector.size())) {
            FrameEff frameTop = this.bigImgs[0].listFrame.elementAt(0);
            int wMax = 0;
            for (int i2 = 0; i2 < frameTop.listPartTop.size(); i2++) {
                int w = this.bigImgs[0].smallImage[frameTop.listPartTop.elementAt(i2).idSmallImg].w;
                if (w > wMax) {
                    wMax = w;
                }
            }
            this.width = wMax;
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
        this.height = 100;
        BigImage[] bigImageArr = this.bigImgs;
        if (!(bigImageArr == null || (bigImage = bigImageArr[0]) == null || (vector = bigImage.listFrame) == null || 0 >= vector.size())) {
            FrameEff frameTop = this.bigImgs[0].listFrame.elementAt(0);
            int hMax = 0;
            for (int i2 = 0; i2 < frameTop.listPartTop.size(); i2++) {
                int dy = frameTop.listPartTop.elementAt(i2).dy;
                if (dy < hMax) {
                    hMax = dy;
                }
            }
            this.height = Res.abs(hMax);
        }
        return this.height;
    }
}
