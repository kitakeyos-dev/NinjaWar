package ninjawar.model;

public class MovePointNew {
    public byte action;
    public byte directTemp;
    public int index;
    public byte subAction;
    public int xEnd;
    public int yEnd;

    public MovePointNew(int xEnd2, int yEnd2, int directTemp2, int action2, int subAction2) {
        this.xEnd = xEnd2;
        this.yEnd = yEnd2;
        this.action = (byte) action2;
        this.directTemp = (byte) directTemp2;
        this.subAction = (byte) subAction2;
    }

    public MovePointNew() {
    }
}
