package ninjawar.model;

import ninjawar.message.SendMessage;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.MyTile;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;

public class PointChangeMap implements ActionInterface {
    public static mFont fontPaint = mFont.tahoma_7b_white_border_black;
    public FrameImage frameTelePort;
    public int h;
    public byte idPort;
    int indexFrame = 0;
    public boolean isEnter;
    boolean isOpacity = true;
    public short maxX;
    public short maxY;
    public short minX;
    public short minY;
    public String name = "Change map";
    int percentOpacity = 60;
    public int w;
    public int x;
    public int y;
    int yPaint;

    public PointChangeMap(short minX2, short minY2, short maxX2, short maxY2, String name2, byte idPort2) {
        short s = minX2;
        short s2 = minY2;
        short s3 = maxX2;
        short s4 = maxY2;
        String str = name2;
        byte b = idPort2;
        this.minX = s;
        this.minY = s2;
        this.maxX = s3;
        this.maxY = s4;
        this.name = str;
        this.idPort = b;
        this.frameTelePort = LoadDataManager.frameTelePort[b];
        Res.outz("=========>  name " + str);
        int wTemp = Res.abs(s - s3);
        int hTemp = Res.abs(s2 - s4);
        int wText = fontPaint.getWidth(str) + 20;
        int hText = fontPaint.getHeight() + 10;
        this.w = wText < wTemp ? wTemp : wText;
        this.h = (int) this.frameTelePort.frameHeight;
        this.x = s - (Res.abs(wTemp - wText) / 2);
        int abs = (s2 - (Res.abs(hTemp - hText) / 2)) - (MyTile.size * 3);
        this.y = abs;
        int margin = NinjaMidlet.PADDING_TAI_THO;
        int i = this.x;
        byte b2 = MyTile.size;
        if (i < b2) {
            this.x = b2 + margin;
        }
        int i2 = this.x;
        int i3 = this.w;
        int i4 = i2 + i3;
        int i5 = b2 * MyTile.tmw;
        byte b3 = MyTile.size;
        if (i4 > i5 - b3) {
            this.x = (((b3 * MyTile.tmw) - i3) - MyTile.size) - margin;
        }
        if (abs < 0) {
            this.y = 0;
        }
        int i6 = this.y;
        int i7 = this.h;
        if (i6 + i7 > MyTile.size * MyTile.tmh) {
            this.y = (MyTile.size * MyTile.tmh) - i7;
        }
        MyTile.vGo.addElement(this);
        this.yPaint = (MyTile.findYTileTop(this.x, this.y) - this.h) + 5;
    }

    public void perform(int idAction, Object p) {
        int i = 1;
        switch (idAction) {
            case 1:
                int x2 = (this.minX + this.maxX) / 2;
                int y2 = this.maxY;
                short s = this.maxY;
                short s2 = this.minY;
                if (s > s2 + 24) {
                    y2 = (s2 + s) / 2;
                }
                Player.myCharz().currentMovePoint = new mMovePointCustom(x2, y2);
                Player myCharz = Player.myCharz();
                if (Player.myCharz().cx - Player.myCharz().currentMovePoint.xEnd > 0) {
                    i = -1;
                }
                myCharz.cdir = i;
                SendMessage.gI().sendMove();
                return;
            case 2:
                SendMessage.gI().sendMove();
                SendMessage.gI().requestChangeMap();
                Player.isLockKey = true;
                Player.ischangingMap = true;
                CanvasNinja.clearKeyHold();
                CanvasNinja.clearKeyPressed();
                return;
            default:
                return;
        }
    }

    public void paint(mGraphics g) {
        g.setColor(0);
        boolean rightPort = true;
        int i = this.x;
        if (i < CanvasNinja.w / 2) {
            rightPort = false;
        }
        boolean rightPort2 = rightPort;
        if (CanvasNinja.gameTick % 3 == 0) {
            int i2 = this.indexFrame + 1;
            this.indexFrame = i2;
            if (i2 > this.frameTelePort.nFrame - 1) {
                this.indexFrame = 0;
            }
        }
        if (!rightPort2) {
            mGraphics mgraphics = g;
            this.frameTelePort.drawFrame(mgraphics, this.indexFrame, (float) (i - 20), (float) this.yPaint, 0, false, 100);
            mFont.tahoma_7b_white_border_black.drawString(mgraphics, this.name, this.x - 20, this.yPaint - 35, 0);
        } else {
            this.frameTelePort.drawFrame(g, this.indexFrame, (float) (i + 40), (float) this.yPaint, 2, false, 100);
            mFont.tahoma_7b_white_border_black.drawString(g, this.name, ((float) (this.x + 40)) + this.frameTelePort.frameWidth, this.yPaint - 35, 1);
        }
        g.resetColor();
    }

    public void updatePointer() {
        if (CanvasNinja.isPointerHoldOrHover(this.x - MapScr.cmx, this.y - MapScr.cmy, this.w, this.h)) {
            this.isOpacity = false;
        } else {
            this.isOpacity = true;
        }
        if (CanvasNinja.isPointerRelease() && CanvasNinja.isPoint(this.x - MapScr.cmx, this.y - MapScr.cmy, this.w, this.h)) {
            this.isOpacity = false;
            SendMessage.gI().requestChangeMap();
            CanvasNinja.clearAllPointer();
            CanvasNinja.isPointerDraggedX = false;
            CanvasNinja.isPointerDraggedY = false;
        }
    }
}
