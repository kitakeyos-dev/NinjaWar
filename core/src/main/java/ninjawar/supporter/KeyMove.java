// Class Version: 8
package ninjawar.supporter;

import ninjawar.mylib.Pointer;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.mylib.mSystem;
import ninjawar.mylib.mGraphics;
import java.util.HashMap;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.mylib.Atlas;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ninjawar.model.mPoint;
import ninjawar.mylib.Image;

public class KeyMove
{
    public static int MARGIN_LEFT;
    public int ID_CHARKA;
    public int[] dirIndex;
    int dirTemp;
    public int h;
    int hKeyLR;
    int hKeyUD;
    private Image imgKeyMoveAll;
    int[] indexKeyMove;
    public boolean isFocus;
    public boolean isKeyMove;
    public boolean isUnFocus;
    int margin;
    public mPoint[] point;
    Sprite[] spriteKeyMoves;
    public int timeOpacity;
    public long timeStartClick;
    public int w;
    int wKeyLR;
    int wKeyUD;
    public int x;
    public int y;

    static {
        KeyMove.MARGIN_LEFT = 50;
    }

    public KeyMove() {
        this.isKeyMove = false;
        this.isUnFocus = true;
        this.imgKeyMoveAll = Image.createImage("/normal/pointer/0.png");
        this.dirTemp = 0;
        this.timeOpacity = 1500;
        this.point = new mPoint[4];
        this.ID_CHARKA = 99;
        this.dirIndex = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        this.indexKeyMove = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        this.isFocus = false;
        final Atlas atlas = Atlas.createAtlas("/atlas/", "myatlas");
        this.spriteKeyMoves = new Sprite[5];
        int n = 0;
        while (true) {
            final Sprite[] spriteKeyMoves = this.spriteKeyMoves;
            if (n >= spriteKeyMoves.length) {
                break;
            }
            final HashMap<String, Sprite> sprites = atlas.sprites;
            final StringBuilder sb = new StringBuilder();
            sb.append(n);
            sb.append("");
            spriteKeyMoves[n] = sprites.get(sb.toString());
            ++n;
        }
        this.margin = 20;
        final int padding_TAI_THO = NinjaMidlet.PADDING_TAI_THO;
        final int margin_LEFT = KeyMove.MARGIN_LEFT;
        this.isKeyMove = true;
        this.x = padding_TAI_THO + margin_LEFT;
        this.y = CanvasNinja.h - this.imgKeyMoveAll.getRHeight() - this.margin;
        this.w = this.imgKeyMoveAll.getRWidth();
        this.h = this.imgKeyMoveAll.getRHeight();
        this.wKeyLR = this.imgKeyMoveAll.getRWidth() / 2;
        this.hKeyLR = this.imgKeyMoveAll.getRHeight() / 2;
        this.wKeyUD = 50;
        this.hKeyUD = 41;
        this.point[0] = new mPoint(this.x + this.w / 2 - 50 / 2, this.y + this.h - 41, 50, 41);
        final mPoint[] point = this.point;
        final int x = this.x;
        final int n2 = this.w / 2;
        final int wKeyUD = this.wKeyUD;
        point[1] = new mPoint(x + n2 - wKeyUD / 2, this.y, wKeyUD, this.hKeyUD);
        final mPoint[] point2 = this.point;
        final int x2 = this.x;
        final int y = this.y;
        final int n3 = this.h / 2;
        final int hKeyLR = this.hKeyLR;
        point2[2] = new mPoint(x2, y + n3 - hKeyLR / 2, this.wKeyLR, hKeyLR);
        final mPoint[] point3 = this.point;
        final int x3 = this.x;
        final int w = this.w;
        final int wKeyLR = this.wKeyLR;
        final int y2 = this.y;
        final int n4 = this.h / 2;
        final int hKeyLR2 = this.hKeyLR;
        point3[3] = new mPoint(x3 + w - wKeyLR, y2 + n4 - hKeyLR2 / 2, wKeyLR, hKeyLR2);
    }

    public boolean isClickKeyMove() {
        return CanvasNinja.isClickKeyMove() || CanvasNinja.isPoint(this.x, this.y, this.w, this.h);
    }

    public void paint(final mGraphics mGraphics) {
        CanvasNinja.resetTrans(mGraphics);
        if (this.isKeyMove) {
            mGraphics.drawSprite(this.spriteKeyMoves[this.dirTemp], (float)this.x, (float)this.y, true, this.isUnFocus, 50);
        }
    }

    public void reset(final int n) {
        final int[] indexKeyMove = this.indexKeyMove;
        final int n2 = indexKeyMove[n];
        if (n2 != -1) {
            CanvasNinja.keyHold[n2] = false;
        }
        this.isFocus = false;
        indexKeyMove[n] = (this.dirIndex[n] = -1);
        this.timeStartClick = mSystem.currentTimeMillis() + this.timeOpacity;
    }

    public void update() {
        if (this.isKeyMove) {
            final boolean[] keyHold = CanvasNinja.keyHold;
            final boolean b = keyHold[2];
            if (b || keyHold[this.ID_CHARKA]) {
                this.dirTemp = 2;
            }
            final boolean b2 = keyHold[4];
            if (b2) {
                this.dirTemp = 3;
            }
            final boolean b3 = keyHold[6];
            if (b3) {
                this.dirTemp = 4;
            }
            final boolean b4 = keyHold[8];
            if (b4) {
                this.dirTemp = 1;
            }
            if (b || b2 || b3 || b4 || keyHold[this.ID_CHARKA]) {
                this.isUnFocus = false;
                this.timeStartClick = mSystem.currentTimeMillis() + this.timeOpacity;
            }
            if (this.timeStartClick > 0L && mSystem.currentTimeMillis() > this.timeStartClick) {
                this.timeStartClick = 0L;
                this.isUnFocus = true;
            }
        }
    }

    public void updatePointer() {
        int n = 0;
        int n2 = 0;
        for (int i = CanvasNinja.pointerNew.length - 1; i >= 0; --i, n = n2) {
            final Pointer pointer = CanvasNinja.pointerNew[i];
            Label_0224: {
                if (!pointer.isPointerClick) {
                    n2 = n;
                    if (!pointer.isPointerDragged) {
                        break Label_0224;
                    }
                }
                int n3 = 0;
                while (true) {
                    final mPoint[] point = this.point;
                    if (n3 >= point.length) {
                        break;
                    }
                    final mPoint mPoint = point[n3];
                    if (CanvasNinja.isPointNew(mPoint.x, mPoint.y, mPoint.w, mPoint.h, i)) {
                        this.isFocus = true;
                        n2 = 1;
                        this.dirIndex[i] = i;
                        switch (n3) {
                            case 3: {
                                CanvasNinja.resetAllKeyMove();
                                CanvasNinja.keyHold[6] = true;
                                this.indexKeyMove[i] = 6;
                                break;
                            }
                            case 2: {
                                CanvasNinja.resetAllKeyMove();
                                CanvasNinja.keyHold[4] = true;
                                this.indexKeyMove[i] = 4;
                                break;
                            }
                            case 1: {
                                CanvasNinja.resetAllKeyMove();
                                final boolean[] keyHold = CanvasNinja.keyHold;
                                final int id_CHARKA = this.ID_CHARKA;
                                keyHold[id_CHARKA] = true;
                                this.indexKeyMove[i] = id_CHARKA;
                                break;
                            }
                            case 0: {
                                CanvasNinja.resetAllKeyMove();
                                CanvasNinja.keyHold[8] = true;
                                this.indexKeyMove[i] = 8;
                                break;
                            }
                        }
                        break;
                    }
                    ++n3;
                }
            }
            if (n2 != 0) {
                break;
            }
        }
        for (int j = CanvasNinja.pointerNew.length - 1; j >= 0; --j) {
            final Pointer pointer2 = CanvasNinja.pointerNew[j];
            if (pointer2.isPointerRelease && this.dirIndex[j] == j && this.indexKeyMove[j] != -1) {
                pointer2.isPointerRelease = false;
                pointer2.isPointerClick = false;
                pointer2.isPointerDragged = false;
                final StringBuilder sb = new StringBuilder();
                sb.append("reset keyhold:");
                sb.append(this.indexKeyMove[j]);
                Res.outz(1, sb.toString());
                final boolean[] keyHold2 = CanvasNinja.keyHold;
                final int n4 = this.indexKeyMove[j];
                keyHold2[n4] = false;
                if (n4 == this.ID_CHARKA) {
                    Player.myCharz().isHaveChakra ^= true;
                    Player.myCharz().useCharka(Player.myCharz().isHaveChakra);
                }
                this.dirIndex[j] = -1;
                this.indexKeyMove[j] = -1;
            }
        }
        final boolean[] keyHold3 = CanvasNinja.keyHold;
        if (!keyHold3[2] && !keyHold3[4] && !keyHold3[6] && !keyHold3[8] && !keyHold3[this.ID_CHARKA]) {
            this.dirTemp = 0;
        }
    }
}
