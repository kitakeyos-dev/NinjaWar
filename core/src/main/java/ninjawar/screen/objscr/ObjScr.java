package ninjawar.screen.objscr;

import ninjawar.model.MyCommand;
import ninjawar.mylib.AudioManager;
import ninjawar.mylib.BaseScreen;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.supporter.LoadDataManager;

public class ObjScr extends BaseScreen {
    public MyCommand cmdClose;
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

    public void initClose() {
        MyCommand myCommand = new MyCommand("", -1, this);
        this.cmdClose = myCommand;
        myCommand.x = ((this.x + 1) + this.w) - this.imgClose.getRWidth();
        MyCommand myCommand2 = this.cmdClose;
        myCommand2.y = this.y - 1;
        myCommand2.w = this.imgClose.getRWidth();
        this.cmdClose.h = this.imgClose.getRHeight();
        this.cmdClose.isClose = true;
    }

    public void updatePointer() {
        if (CanvasNinja.isPointerRelease()) {
            MyCommand myCommand = this.cmdClose;
            if (CanvasNinja.isPoint(myCommand.x, myCommand.y, myCommand.w, myCommand.h)) {
                CanvasNinja.clearAllPointer();
                this.cmdClose.perform();
            }
        }
    }

    public void updateKey() {
        super.updateKey();
    }

    public void closeMenu() {
        CanvasNinja.objScr = null;
        AudioManager.buttonClose();
    }
}
