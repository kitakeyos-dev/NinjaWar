package ninjawar.screen.objscr;

import ninjawar.input.KEY;
import ninjawar.message.SendMessage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportTranslate;

public class PopupPK extends ObjScrMore {
    public static mFont fontPaintPK = mFont.tahoma_7_white;
    public static mFont fontPaintPKFocus = mFont.tahoma_7b_white;
    public static PopupPK me;
    public int[] arrH;
    public int[] arrW;
    public int[] arrX;
    public int[] arrY;
    public boolean isClosePK = true;
    public boolean isHover = false;
    String[] namePK;

    public void initCmd() {
    }

    private void init() {
        this.margin = 5;
        initCmd();
        String[] arrayLangue = SupportTranslate.getArrayLangue("PK_OPTION");
        this.namePK = arrayLangue;
        int width = (this.margin * 4) + fontPaintPK.getWidth(Res.findStringMax(arrayLangue));
        this.w = width;
        if (width < LoadDataManager.imgLineBgParty.getRWidth()) {
            this.w = (this.margin * 2) + LoadDataManager.imgLineBgParty.getRWidth();
        }
        int height = fontPaintPK.getHeight();
        int i = this.margin;
        this.h = ((height + i) * this.namePK.length) + i + (LoadDataManager.imgLineBgParty.getRHeight() * (this.namePK.length - 1));
        this.arrH = new int[]{LoadDataManager.imgBgPK.getRHeight(), (int) LoadDataManager.frameIconPk.frameHeight, this.h, fontPaintPK.getHeight() + this.margin + LoadDataManager.imgLineBgParty.getRHeight(), (int) LoadDataManager.frameFocusPk.frameHeight};
        int i2 = this.w;
        this.arrW = new int[]{LoadDataManager.imgBgPK.getRWidth(), (int) LoadDataManager.frameIconPk.frameWidth, i2, i2, i2 - (this.margin * 2)};
        int i3 = this.x;
        int i4 = this.x;
        int rWidth = LoadDataManager.imgBgPK.getRWidth();
        int i5 = this.w;
        int i6 = this.x;
        int i7 = this.arrW[0];
        this.arrX = new int[]{i3, i3 + ((LoadDataManager.imgBgPK.getRWidth() - ((int) LoadDataManager.frameIconPk.frameWidth)) / 2), i4 + ((rWidth - i5) / 2), (i7 / 2) + i6, ((i6 + (i7 / 2)) - (i5 / 2)) + this.margin};
        int i8 = this.y;
        int i9 = this.y;
        int i10 = this.h;
        int i11 = this.margin;
        this.arrY = new int[]{i8, i8 + ((LoadDataManager.imgBgPK.getRHeight() - ((int) LoadDataManager.frameIconPk.frameHeight)) / 2), (i9 - i10) - i11, ((i9 - i10) - i11) + i11, (((((i9 - i10) - i11) + i11) + (fontPaintPK.getHeight() / 2)) - (((int) LoadDataManager.frameFocusPk.frameHeight) / 2)) - 1};
    }

    public PopupPK(int x, int y) {
        this.x = x;
        this.y = y;
        this.margin = 5;
    }

    public void show() {
        init();
        me = this;
        CanvasNinja.objScrMore = this;
    }

    public void hide() {
        CanvasNinja.objScrMore = null;
    }

    public void updatePointer() {
        String[] strArr;
        if (CanvasNinja.isPointHover(this.arrX[0], this.arrY[0], this.arrW[0], this.arrH[0])) {
            this.isHover = true;
        } else {
            this.isHover = false;
        }
        if (CanvasNinja.isPointerClick && (strArr = this.namePK) != null && strArr.length > 0) {
            if (!this.isClosePK) {
                int i = 0;
                while (true) {
                    if (i >= this.namePK.length) {
                        break;
                    }
                    int i2 = this.arrX[4] - this.margin;
                    int i3 = this.arrY[4];
                    int[] iArr = this.arrH;
                    if (CanvasNinja.isPoint(i2, i3 + (iArr[3] * i), this.w, iArr[4])) {
                        CanvasNinja.clearAllPointer();
                        SendMessage.gI().sendPK(i);
                        this.isClosePK = true;
                        break;
                    }
                    i++;
                }
                if (CanvasNinja.isPoint(this.arrX[0], this.arrY[0], this.arrW[0], this.arrH[0])) {
                    CanvasNinja.clearAllPointer();
                    this.isClosePK = true;
                }
            } else if (CanvasNinja.isPoint(this.arrX[0], this.arrY[0], this.arrW[0], this.arrH[0])) {
                CanvasNinja.clearAllPointer();
                this.isClosePK = false;
            }
        }
    }

    public void update() {
    }

    public void updateKey() {
        super.updateKey();
        updatePointer();
    }

    public void paint(mGraphics g) {
        String[] strArr;
        mGraphics mgraphics = g;
        g.drawImage(LoadDataManager.imgBgPK, (float) this.arrX[0], (float) this.arrY[0], true, this.isHover ? 60 : 30);
        LoadDataManager.frameIconPk.drawFrame(Player.myCharz().cPk, (float) this.arrX[1], (float) this.arrY[1], mgraphics);
        mFont.tahoma_7b_white_border_black_medium.drawString(mgraphics, Player.myCharz().cPkPoint + "", this.arrX[0], this.arrY[0]);
        if (!this.isClosePK && (strArr = this.namePK) != null && strArr.length > 0) {
            CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgParty, (float) this.arrX[2], (float) this.arrY[2], (float) this.arrW[2], (float) this.arrH[2], 9, 50, false);
            CanvasNinja.resetColor(g);
            for (int i = 0; i < this.namePK.length; i++) {
                Image image = LoadDataManager.imgLineBgParty;
                mgraphics.drawImage(image, (float) (this.arrX[3] - (image.getRWidth() / 2)), (float) (this.arrY[3] + (this.margin / 2) + fontPaintPK.getHeight() + (this.arrH[3] * i)));
                if (Player.myCharz().cPk == i) {
                    CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameFocusPk, this.arrX[4], (this.arrH[3] * i) + this.arrY[4], this.arrW[4], false, 0, false, 0, false);
                    fontPaintPK.drawString(g, this.namePK[i], this.arrX[3], (this.arrH[3] * i) + this.arrY[3], 2);
                } else {
                    fontPaintPK.drawString(g, this.namePK[i], this.arrX[3], (this.arrH[3] * i) + this.arrY[3], 2);
                }
            }
        }
    }

    public boolean commandTabBoolean(int index, int subIndex) {
        Res.outz("Vô command tab popup PK:" + index);
        switch (index) {
            case -1:
                closeMenu();
                return true;
            default:
                return false;
        }
    }

    public void keyPress(int keyCode) {
        if (keyCode == KEY.KEY_P) {
            closeMenu();
        }
    }
}
