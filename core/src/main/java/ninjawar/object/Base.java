package ninjawar.object;

import ninjawar.model.Popup;
import ninjawar.model.mPoint;
import ninjawar.myscr.IMapObject;

public class Base implements IMapObject {
    public static mPoint[] DIR = {new mPoint(0, 1), new mPoint(0, -1), new mPoint(-1, 0), new mPoint(1, 0), new mPoint(0, 0)};
    public int SPEED_LEO_CAU_THANG = 2;
    public String cName = "";
    public int cdir = 1;
    public int cdirUp = 0;
    public int ch = 80;
    public int chh = (80 / 2);
    public int chw = (32 / 2);
    public int cvx;
    public int cvy;
    public int cw = 32;
    public int cx;
    public int cxTo = -100;
    public int cy;
    public int cyTo = -100;
    public byte direct = 0;
    public byte directTem = 4;
    public short dyFly = 0;
    public boolean isBlockMove;
    public int mobId;
    public String monPhai = "";
    public Popup popup;
    public byte subDirect = -1;

    public int getX() {
        return this.cx;
    }

    public int getW() {
        return this.cw;
    }

    public int getH() {
        return this.ch;
    }

    public int getY() {
        return this.cy;
    }

    public void stopMoving() {
    }

    public boolean isInvisible() {
        return false;
    }
}
