package ninjawar.screen.selectbox;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;

public class SelectBox {
    public static mFont fontPaint = mFont.fontPaintSelectOption;
    public static mFont fontPaintBold = mFont.fontTextNau;
    public int h;
    int hDir;
    int hOne;
    int hTrans;
    public int indexSelectBox = -1;
    boolean isFromBot;
    public boolean isSpecialBox = false;
    public int margin;
    public String[] options;
    public int w;
    public int x;
    int xStart;
    public int y;
    int yStart;

    public void init(int x2, int y2, String[] options2, int indexSelectBox2, boolean isFromBot2) {
        this.isFromBot = isFromBot2;
        if (isFromBot2) {
            this.hDir = -this.h;
        } else {
            this.hDir = 0;
        }
        this.indexSelectBox = indexSelectBox2;
        this.x = x2;
        this.y = y2;
        this.margin = 5;
        this.options = options2;
        String strMax = Res.findStringMax(options2);
        int wTextMax = 0;
        if (strMax != null) {
            wTextMax = fontPaint.getWidth(strMax);
        }
        int wDot = LoadDataManager.imgLineParty.getRWidth();
        int hDot = LoadDataManager.imgLineParty.getRHeight();
        int i = this.margin;
        int wTemp = (i * 6) + wTextMax;
        this.w = (((i * 2) * 2) + wDot) + 14 > wTemp ? (i * 2 * 2) + wDot + 14 : wTemp;
        this.hTrans = hDot + 16;
        int height = fontPaint.getHeight();
        this.hOne = height;
        this.h = height * options2.length * 3;
        this.yStart = y2 + 7 + (this.margin * 2);
        this.xStart = (this.w / 2) + x2;
    }

    public SelectBox(int x2, int y2, String[] options2, int indexSelectBox2) {
        init(x2, y2, options2, indexSelectBox2, true);
        this.isSpecialBox = false;
    }

    public void paint(mGraphics g) {
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) (this.y + this.hDir), (float) this.w, (float) this.h, 0, false);
        for (int i = 0; i < this.options.length; i++) {
            if (i == this.indexSelectBox) {
                SupportPaint supportPaint = CanvasNinja.paintz;
                FrameImage frameImage = LoadDataManager.frameSelected;
                int i2 = this.x;
                int i3 = this.margin;
                supportPaint.paintTagFrame(g, frameImage, (i3 * 2) + i2, (((this.yStart + (this.hTrans * i)) + this.hDir) - (((int) frameImage.frameHeight) / 2)) + 9, this.w - (i3 * 4), false, 0, false);
            }
            String[] strArr = this.options;
            if (i == strArr.length - 1 && this.isSpecialBox) {
                fontPaintBold.drawString(g, strArr[i], this.xStart, this.yStart + (this.hTrans * i) + this.hDir, 2);
            } else {
                fontPaint.drawString(g, strArr[i], this.xStart, this.yStart + (this.hTrans * i) + this.hDir, 2);
            }
            if (i != 0) {
                Image image = LoadDataManager.imgLineParty;
                g.drawImage(image, (float) (this.xStart - (image.getRWidth() / 2)), (float) ((this.yStart - 8) + (this.hTrans * i) + this.hDir));
            } else {
                mGraphics mgraphics = g;
            }
        }
        mGraphics mgraphics2 = g;
    }

    public int updatePointer() {
        if (!CanvasNinja.isPointerRelease()) {
            return -1;
        }
        if (CanvasNinja.isPoint(this.x, this.y + this.hDir, this.w, this.h)) {
            CanvasNinja.clearAllPointer();
            int i = 0;
            while (true) {
                if (i >= this.options.length) {
                    break;
                } else if (CanvasNinja.isPoint(this.x, this.yStart + (this.hTrans * i) + this.hDir, this.w, this.hOne)) {
                    this.indexSelectBox = i;
                    break;
                } else {
                    i++;
                }
            }
            return this.indexSelectBox;
        }
        CanvasNinja.clearAllPointer();
        return -1;
    }
}
