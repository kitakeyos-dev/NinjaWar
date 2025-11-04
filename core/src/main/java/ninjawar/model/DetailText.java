package ninjawar.model;

import ninjawar.mylib.mFont;

public class DetailText {
    public int color;
    public mFont fontText;
    public int h;
    public int indexTemp;
    public boolean isHover = false;
    public boolean isPlus = true;
    public int line;
    public String text = "";
    public byte type;
    public float wTextBefore;
    public int x;
    public int y;

    public DetailText() {
        mFont mfont = mFont.tahoma_7;
        this.fontText = mfont;
        this.h = mfont.getHeight();
    }

    public DetailText(String text2, byte type2) {
        this.text = text2;
        this.type = type2;
        this.fontText = setFontByteType(type2);
    }

    public DetailText(String text2, byte type2, int line2) {
        this.line = line2;
        this.text = text2;
        this.type = type2;
        this.fontText = setFontByteType(type2);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text2) {
        this.text = text2;
    }

    public void setColor(int idColor, mFont font) {
        this.color = idColor;
        if (idColor == -1) {
            this.fontText = font;
        } else {
            this.fontText = mFont.getFontServerFromServer((short) idColor);
        }
    }

    public mFont setFontByteType(byte type2) {
        this.type = type2;
        mFont fontPaint = mFont.tahoma_7_white;
        switch (type2) {
            case 0:
                return mFont.tahoma_7_white;
            case 1:
                return mFont.tahoma_7_info_xam;
            case 2:
                return mFont.tahoma_7_info_do;
            case 3:
                return mFont.tahoma_7_info_xanh_la;
            case 4:
                return mFont.tahoma_7_info_cam;
            case 5:
                return mFont.tahoma_7_info_blue;
            case 6:
                return mFont.tahoma_7_info_xian;
            case 7:
                return mFont.tahoma_7_info_cam_dam;
            case 8:
                return mFont.tahoma_7_info_vang;
            default:
                return fontPaint;
        }
    }
}
