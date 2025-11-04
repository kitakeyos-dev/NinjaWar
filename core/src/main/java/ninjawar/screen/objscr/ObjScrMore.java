package ninjawar.screen.objscr;

import ninjawar.mylib.AudioManager;
import ninjawar.mylib.BaseScreen;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;
import ninjawar.supporter.LoadDataManager;

public class ObjScrMore extends BaseScreen {
    public mFont fontPaintTile = mFont.tahoma_7b_white;
    public int h;
    public Image imgClose = LoadDataManager.imgClose;
    public boolean isFocusMe;
    public int margin;
    public String textTitle = "";
    public int w;
    public int x;
    public int y;

    public boolean commandTabBoolean(int i, int i2) {
        throw null;
    }

    public void paint(mGraphics mgraphics) {
        throw null;
    }

    public void update() {
        throw null;
    }

    public void updateKey() {
        super.updateKey();
    }

    public void closeMenu() {
        CanvasNinja.objScrMore = null;
        AudioManager.buttonClose();
        MapScr.gI().popupPK = null;
    }
}
