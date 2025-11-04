package ninjawar.model;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.VectorCustom;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;

public class Popup {
    public int MAX_W_POPUP;
    public String[] contentConvert;
    VectorCustom contents;
    DetailText detailTextTemp;
    public mFont fontPaint;
    public FrameImage frameBgPopup;
    public int h;
    public Image imgArrow;
    public boolean isHaveColor;
    public boolean isPaint;
    int marginH;
    int marginW;
    int marginYText = 2;
    String textPaint;
    public long timeShow;
    byte typePaintTextColor;
    public int w;
    int[] xMidleText;

    public Popup() {
        mFont mfont = mFont.tahoma_7;
        this.fontPaint = mfont;
        this.MAX_W_POPUP = mfont.getWidth("mmmmmmmmmm");
        this.typePaintTextColor = 2;
        this.textPaint = "";
    }

    public void startPopup(String content, int timeShow2) {
        this.isPaint = true;
        this.frameBgPopup = LoadDataManager.frameChat4;
        this.imgArrow = LoadDataManager.imgPopup;
        this.contents = new VectorCustom();
        this.marginW = 10;
        this.marginH = 5;
        this.h = this.fontPaint.getHeight() + (this.marginH * 2);
        if (Res.isHaveColorInStringServer(content)) {
            this.isHaveColor = true;
            setContentsColor(content);
        } else {
            int width = this.fontPaint.getWidth(content);
            int i = this.marginW;
            int i2 = width + (i * 2);
            this.w = i2;
            int i3 = this.MAX_W_POPUP;
            if (i2 > i3) {
                this.w = i3 + (i * 2);
            }
            this.isHaveColor = false;
            this.contents = this.fontPaint.splitFontVector(content, this.w - (i * 2));
        }
        if (timeShow2 != -1) {
            this.timeShow = mSystem.currentTimeMillis() + ((long) timeShow2);
        }
        if (this.contents == null) {
            return;
        }
        if (this.isHaveColor) {
            this.h = ((this.fontPaint.getHeight() + 1 + this.marginYText) * this.contentConvert.length) + (this.marginH * 2);
        } else {
            this.h = ((this.fontPaint.getHeight() + this.marginYText) * this.contents.size()) + (this.marginH * 2);
        }
    }

    public void paint(mGraphics g, int x, int y) {
        Image image;
        if (this.frameBgPopup != null && (image = this.imgArrow) != null && this.isPaint) {
            CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameChat4, (float) (x - (this.w / 2)), (float) ((y - this.h) - image.getRHeight()), (float) this.w, (float) this.h, 4, false);
            if (this.isHaveColor) {
                for (int i = 0; i < this.contents.size(); i++) {
                    DetailText detailText = (DetailText) this.contents.elementAt(i);
                    this.detailTextTemp = detailText;
                    mFont mfont = detailText.fontText;
                    String str = detailText.text;
                    float f = ((float) (this.xMidleText[detailText.line] + x)) + detailText.wTextBefore;
                    int rHeight = ((y - this.h) - this.imgArrow.getRHeight()) + this.marginH;
                    DetailText detailText2 = this.detailTextTemp;
                    mfont.drawString(g, str, f, (detailText2.line * (detailText2.h + 1 + this.marginYText)) + rHeight, 0);
                }
            } else {
                for (int i2 = 0; i2 < this.contents.size(); i2++) {
                    String obj = this.contents.elementAt(i2).toString();
                    this.textPaint = obj;
                    this.fontPaint.drawString(g, obj, x, ((y - this.h) - this.imgArrow.getRHeight()) + this.marginH + ((this.fontPaint.getHeight() + this.marginYText) * i2), 2);
                }
            }
            Image image2 = this.imgArrow;
            g.drawImage(image2, (float) x, (float) ((y - image2.getRHeight()) - 1));
        }
    }

    public void setContentsColor(String content) {
        String[] strArr;
        this.contents = Res.formatStringFromServer(content, this.fontPaint);
        String text = "";
        for (int i = 0; i < this.contents.size(); i++) {
            text = text + ((DetailText) this.contents.elementAt(i)).text;
        }
        int width = this.fontPaint.getWidth(text);
        int i2 = this.marginW;
        int i3 = width + (i2 * 2);
        this.w = i3;
        int i4 = this.MAX_W_POPUP;
        if (i3 > i4) {
            this.w = i4 + (i2 * 2);
        }
        String[] splitFontArray = this.fontPaint.splitFontArray(text, this.w - (i2 * 2));
        this.contentConvert = splitFontArray;
        this.xMidleText = new int[splitFontArray.length];
        int k = 0;
        while (true) {
            strArr = this.contentConvert;
            if (k >= strArr.length) {
                break;
            }
            if (this.typePaintTextColor == 2) {
                this.xMidleText[k] = (-this.fontPaint.getWidth(strArr[k])) / 2;
            }
            k++;
        }
        DetailText[] detailTextArr = new DetailText[strArr.length];
        int indexTemp = 0;
        for (int k2 = 0; k2 < this.contentConvert.length; k2++) {
            while (this.contentConvert[k2].length() > 0) {
                DetailText detailText = (DetailText) this.contents.elementAt(indexTemp);
                if (this.contentConvert[k2].startsWith(detailText.text)) {
                    detailText.line = k2;
                    String[] strArr2 = this.contentConvert;
                    strArr2[k2] = strArr2[k2].substring(1);
                }
                indexTemp++;
            }
        }
        float count = 0.0f;
        for (int i5 = 0; i5 < this.contents.size(); i5++) {
            DetailText item = (DetailText) this.contents.elementAt(i5);
            if (i5 < this.contents.size() - 1) {
                DetailText itemNext = (DetailText) this.contents.elementAt(i5 + 1);
                if (itemNext.line != item.line) {
                    count = 0.0f;
                } else {
                    count += item.fontText.getWidthF(item.getText());
                    itemNext.wTextBefore = count;
                }
            }
        }
    }
}
