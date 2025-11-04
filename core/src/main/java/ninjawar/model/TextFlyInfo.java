package ninjawar.model;

public class TextFlyInfo {
    public int color;
    public int dx;
    public int dy;
    public String flyString = "";
    public int x;
    public int y;

    public TextFlyInfo(String text, int x2, int y2, int dx2, int dy2, int color2) {
        this.flyString = text;
        this.x = x2;
        this.y = y2;
        this.dx = dy2;
        this.dy = dy2;
        this.color = color2;
    }
}
