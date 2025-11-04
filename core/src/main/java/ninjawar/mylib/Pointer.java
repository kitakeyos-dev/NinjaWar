package ninjawar.mylib;

public class Pointer {
    public boolean isPointerClick = false;
    public boolean isPointerDragged = false;
    public boolean isPointerRelease = false;
    public int px;
    public int py;

    public Pointer(int px2, int py2, boolean isClick, boolean isRelease) {
        this.px = px2;
        this.py = py2;
        this.isPointerClick = isClick;
        this.isPointerRelease = isRelease;
    }

    public void setPointer(int px2, int py2, boolean isClick, boolean isRelease) {
        this.px = px2;
        this.py = py2;
        this.isPointerClick = isClick;
        this.isPointerRelease = isRelease;
    }
}
