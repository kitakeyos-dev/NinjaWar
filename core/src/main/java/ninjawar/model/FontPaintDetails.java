package ninjawar.model;

import ninjawar.mylib.mFont;
import ninjawar.mymain.CanvasNinja;
import ninjawar.screen.subtab.SubTabBuyShop;

public class FontPaintDetails {
    public static mFont fontPaintdefault = mFont.tahoma_7_white;
    public mFont fontPaint = mFont.tahoma_7_white;
    public String text;
    public byte type;

    public FontPaintDetails(byte type2, String text2) {
        this.type = type2;
        this.text = initLongText(text2);
        switch (type2) {
            case 0:
                this.fontPaint = mFont.tahoma_7_white;
                return;
            case 1:
                this.fontPaint = mFont.tahoma_7_info_xam;
                return;
            case 2:
                this.fontPaint = mFont.tahoma_7_info_do;
                return;
            case 3:
                this.fontPaint = mFont.tahoma_7_info_xanh_la;
                return;
            case 4:
                this.fontPaint = mFont.tahoma_7_info_cam;
                return;
            case 5:
                this.fontPaint = mFont.tahoma_7_info_blue;
                return;
            case 6:
                this.fontPaint = mFont.tahoma_7_info_xian;
                return;
            case 7:
                this.fontPaint = mFont.tahoma_7_info_cam_dam;
                return;
            case 8:
                this.fontPaint = mFont.tahoma_7_info_vang;
                return;
            default:
                this.fontPaint = mFont.tahoma_7_white;
                return;
        }
    }

    private String initLongText(String text2) {
        if (text2 == null || text2.isEmpty()) {
            return "";
        }
        String[] words = text2.split("\\s+");
        StringBuilder sb = new StringBuilder();
        int wordCount = 0;
        for (String word : words) {
            sb.append(word);
            sb.append(" ");
            wordCount++;
            if (wordCount >= 5) {
                sb.append("\n");
                wordCount = 0;
            }
        }
        return sb.toString().trim();
    }

    public FontPaintDetails(mFont fontPaint2, String text2) {
        this.fontPaint = fontPaint2;
        if (CanvasNinja.subTab instanceof SubTabBuyShop) {
            this.text = text2;
        } else {
            this.text = text2;
        }
    }
}
