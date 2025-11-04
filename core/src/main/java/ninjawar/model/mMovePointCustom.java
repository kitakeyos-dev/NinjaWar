package ninjawar.model;

public class mMovePointCustom {
    public int color = 16776960;
    public int colorDoubleJump = 16073300;
    public int colorJump = 206076;
    public boolean isDoAction = false;
    public boolean isDoDoubleJump;
    public boolean isDoJump;
    public boolean isWaittingFall;
    public int xEnd;
    public int yEnd;

    public mMovePointCustom(int xEnd2, int yEnd2) {
        this.xEnd = xEnd2;
        this.yEnd = yEnd2;
    }
}
