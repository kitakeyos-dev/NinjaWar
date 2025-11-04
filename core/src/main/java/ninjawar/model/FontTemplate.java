package ninjawar.model;

public class FontTemplate {
    public int color;
    public int colorBorder;
    public int colorShadow;
    public String fontNameFile;
    public byte fontSize;
    public short id;
    public byte sizeBorder;

    public FontTemplate(short id2, int color2, byte fontSize2, String fontNameFile2) {
        this.id = id2;
        this.color = color2;
        this.fontNameFile = fontNameFile2;
        this.fontSize = fontSize2;
    }
}
