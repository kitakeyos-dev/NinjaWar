// Class Version: 8
package ninjawar.object;

import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mylib.VectorCustom;
import ninjawar.mylib.AudioManager;
import ninjawar.mylib.mSystem;
import ninjawar.myscr.MyTile;
import ninjawar.myscr.Res;
import ninjawar.myscr.Player;
import ninjawar.supporter.AObjInfo;

public class ObjEffect
{
    public static int[][] FRAME;
    AObjInfo aObjInfo;
    byte dirPaint;
    int direct;
    int frame;
    int frameMax;
    short[] idPart;
    int indexFrame;
    boolean isHide;
    boolean isMoveOut;
    boolean isStart;
    long lastTimeAction;
    long lastTimeDir;
    int statusMe;
    long timeSoundEat;
    long timeTimeHide;
    int vx;
    int vy;
    int x;
    int xMoveTo;
    int xSd;
    int xStart;
    int xTarget;
    int y;
    int yMoveTo;
    int ySd;
    int yStart;
    int yTarget;

    static {
        ObjEffect.FRAME = new int[][] { { 0, 1, 2, 3 }, { 4, 5, 6, 7 }, { 8, 9, 10, 11 }, { 12, 13, 14, 15 } };
    }

    public ObjEffect() {
        this.direct = 1;
        this.statusMe = 0;
        this.isMoveOut = false;
        this.isHide = true;
    }

    public ObjEffect(final int part, final int n, final int n2) {
        this.direct = 1;
        this.statusMe = 0;
        this.isMoveOut = false;
        this.isHide = true;
        this.setPart(part);
        this.setPos(n, n2);
        this.start();
    }

    private void checkPlayerCome() {
        final int statusMe = this.statusMe;
        int dir = 1;
        if ((statusMe == 1 || statusMe == 0) && Res.distance(Player.myCharz().cx, Player.myCharz().cy, this.x, this.y) < MyTile.size * 4) {
            if (Player.myCharz().cx >= this.x) {
                dir = -1;
            }
            this.setDir(dir);
            this.moveOut();
        }
    }

    private boolean isInSound() {
        return Res.distance(Player.myCharz().cx, this.x) < MyTile.size * 5;
    }

    private void setAction(final int statusMe) {
        this.statusMe = statusMe;
    }

    private void setDir(final int direct) {
        this.direct = direct;
        if (direct == 1) {
            this.dirPaint = 2;
        }
        if (direct == -1) {
            this.dirPaint = 0;
        }
    }

    private void updateActionFly() {
        final int x = this.x + this.vx;
        this.x = x;
        final int y = this.y + this.vy;
        this.y = y;
        if (!this.isMoveOut) {
            if (y > this.yTarget - 32 && Res.distance(x, this.xTarget) < 5) {
                this.y = this.yTarget - 32;
                this.doAction((byte)3);
                this.vx = 0;
                this.vy = 2;
            }
        }
        else {
            final int yMoveTo = this.yMoveTo;
            if (y < yMoveTo) {
                if (this.direct == -1) {
                    final int xMoveTo = this.xMoveTo;
                    if (x < xMoveTo) {
                        this.y = xMoveTo;
                        this.y = yMoveTo;
                        this.vx = 0;
                        this.vy = 0;
                        this.doHide();
                    }
                }
                else {
                    final int xMoveTo2 = this.xMoveTo;
                    if (x > xMoveTo2) {
                        this.y = xMoveTo2;
                        this.y = yMoveTo;
                        this.vx = 0;
                        this.vy = 0;
                        this.doHide();
                    }
                }
            }
        }
    }

    private void updateActionMoveOut() {
        this.x += this.vx;
        final int y = this.y + this.vy;
        this.y = y;
        if (!this.isMoveOut) {
            final int yTarget = this.yTarget;
            if (y > yTarget) {
                this.y = yTarget;
                this.doAction((byte)0);
            }
        }
        else if (y < this.yTarget - 32) {
            this.doAction((byte)2);
            this.vx = this.direct * 8;
            this.vy = -5;
        }
    }

    private void updateFrame() {
        switch (this.statusMe) {
            default: {
                this.doUpdateFrameNormal();
                break;
            }
            case 1: {
                this.updateSoundEat();
                this.doUpdateFrameJustOne();
                break;
            }
        }
    }

    private void updateShadown() {
        final int x = this.x;
        this.xSd = x;
        final int y = this.y;
        this.ySd = y;
        if (y <= 0) {
            return;
        }
        if (MyTile.tileTypeAt(x, y, 2)) {
            return;
        }
        this.ySd = MyTile.findYTileTop(this.xSd, this.ySd);
    }

    private void updateSoundEat() {
        if (mSystem.currentTimeMillis() > this.timeSoundEat && this.isInSound()) {
            if (this.idPart[0] == 10001) {
                AudioManager.playSound(AudioManager.QUA_KEU);
            }
            else {
                AudioManager.playSound(AudioManager.CHIM_HOT_1);
            }
            this.timeSoundEat = mSystem.currentTimeMillis() + 5000L;
        }
    }

    public void auto() {
        final int statusMe = this.statusMe;
        int dir = 1;
        if (statusMe == 0 && this.lastTimeAction <= mSystem.currentTimeMillis()) {
            this.doAction((byte)1);
            if (this.lastTimeDir == 0L) {
                this.lastTimeDir = 1L;
            }
            this.lastTimeAction = mSystem.currentTimeMillis() + Res.random(1000, 2000);
        }
        final int statusMe2 = this.statusMe;
        if (statusMe2 == 0 || statusMe2 == 1) {
            final long lastTimeDir = this.lastTimeDir;
            if (lastTimeDir != 0L && lastTimeDir <= mSystem.currentTimeMillis()) {
                this.lastTimeDir = mSystem.currentTimeMillis() + Res.random(2000, 3000);
                if (this.direct != -1) {
                    dir = -1;
                }
                this.setDir(dir);
            }
        }
        if (this.isHide && this.timeTimeHide <= mSystem.currentTimeMillis()) {
            this.start();
        }
    }

    public void checkPlayerCome(final VectorCustom vectorCustom) {
        final int statusMe = this.statusMe;
        final int n = 1;
        if (statusMe == 1 || statusMe == 0) {
            for (int i = 0; i < vectorCustom.size(); ++i) {
                final Player player = (Player)vectorCustom.elementAt(i);
                if (player.isMe()) {
                    if (Res.distance(player.cx, player.cy, this.x, this.y) < MyTile.size * 4) {
                        int dir;
                        if (player.cx < this.x) {
                            dir = n;
                        }
                        else {
                            dir = -1;
                        }
                        this.setDir(dir);
                        this.moveOut();
                        break;
                    }
                }
            }
        }
    }

    public void doAction(final byte action) {
        switch (action) {
            case 2: {
                if (this.isInSound()) {
                    AudioManager.playSound(AudioManager.CHIM_BAY, 1.0f);
                    break;
                }
                break;
            }
        }
        this.isStart = true;
        this.indexFrame = 0;
        this.setAction(action);
    }

    public void doHide() {
        this.isHide = true;
        this.timeTimeHide = mSystem.currentTimeMillis() + Res.random(4000, 5000);
    }

    public void doUpdateFrameJustOne() {
        if (CanvasNinja.gameTick % 3 == 0 && this.isStart) {
            if (++this.indexFrame > (this.frameMax = ObjEffect.FRAME[this.statusMe].length - 1)) {
                this.indexFrame = 0;
                this.isStart = false;
                this.resetAction();
            }
            this.frame = (byte)ObjEffect.FRAME[this.statusMe][this.indexFrame];
        }
    }

    public void doUpdateFrameNormal() {
        if (CanvasNinja.gameTick % 3 == 0) {
            final int indexFrame = this.indexFrame + 1;
            this.indexFrame = indexFrame;
            final int[] array = ObjEffect.FRAME[this.statusMe];
            if (indexFrame > (this.frameMax = array.length - 1)) {
                this.indexFrame = 0;
            }
            this.frame = (byte)array[this.indexFrame];
        }
    }

    public void moveOut() {
        this.isMoveOut = true;
        this.doAction((byte)3);
        this.vy = -8;
        this.xMoveTo = this.xTarget + this.direct * CanvasNinja.w;
        this.yMoveTo = CanvasNinja.h;
    }

    public void paint(final mGraphics mGraphics) {
        if (this.isHide) {
            return;
        }
        this.paintShadow(mGraphics);
        final AObjInfo aObjInfo = this.aObjInfo;
        if (aObjInfo != null) {
            aObjInfo.paint(mGraphics, this.x + this.direct * aObjInfo.getW() / 2, this.y - this.aObjInfo.getH() + 3, this.frame, this.dirPaint, (byte)(-1), 0, false, false);
        }
    }

    public void paintShadow(final mGraphics mGraphics) {
        final int statusMe = this.statusMe;
        if (statusMe != 0 && statusMe != 1 && statusMe != 3) {
            mGraphics.drawImage(MyTile.shadow, (float)this.xSd, (float)(this.ySd - 3), 3);
        }
        else {
            mGraphics.drawImage(MyTile.shadow, (float)this.x, (float)(this.y - 3), 3);
        }
    }

    public void resetAction() {
        this.indexFrame = 0;
        this.statusMe = 0;
    }

    public void setPart(final int n) {
        this.idPart = new short[] { (short)n };
        (this.aObjInfo = new AObjInfo()).loadDataFromTool(this.idPart);
    }

    public void setPos(final int xTarget, final int yTarget) {
        this.xTarget = xTarget;
        this.yTarget = yTarget;
    }

    public void start() {
        this.isHide = false;
        this.setDir(-1);
        final int n = this.xTarget - this.direct * 100;
        this.xStart = n;
        final int n2 = this.yTarget - 153;
        this.yStart = n2;
        this.x = n;
        this.y = n2;
        this.doAction((byte)2);
        this.isMoveOut = false;
        this.vx = this.direct * 6;
        this.vy = 8;
    }

    public void update() {
        this.updateFrame();
        this.updateAction();
        this.updateShadown();
        this.checkPlayerCome();
        this.auto();
    }

    public void updateAction() {
        switch (this.statusMe) {
            case 3: {
                this.updateActionMoveOut();
                break;
            }
            case 2: {
                this.updateActionFly();
                break;
            }
        }
    }
}
