package ninjawar.screen.menu;

import ninjawar.model.MyCommand;
import ninjawar.mylib.BaseScreen;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;

public class MenuMain extends BaseScreen {
    public MyCommand cmdClose;
    public mFont fontPaintTile = mFont.tahoma_7b_white;
    public int h;
    public int hTitle;
    public Image imgClose = LoadDataManager.imgClose;
    public int margin;
    public int margin7;
    public String textTitle = "";
    public int w;
    public int wTitle;
    public int x;
    public int y;

    public void keyPress(int i) {
        throw null;
    }

    public void update() {
        throw null;
    }

    public void updateKey() {
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
        this.cmdClose.updatePointer();
    }

    public void paint(mGraphics g) {
        super.paint(g);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        SupportPaint supportPaint = CanvasNinja.paintz;
        FrameImage frameImage = LoadDataManager.frameTitle;
        int i = this.wTitle;
        supportPaint.paintTagFrame(g, frameImage, (this.x + (this.w / 2)) - (i / 2), this.y - (this.hTitle / 2), i, false, SupportPaint.TYPE_NONE, false);
        mGraphics mgraphics = g;
        this.fontPaintTile.drawString(mgraphics, this.textTitle, (this.w / 2) + this.x, this.y, 3);
        Image image = LoadDataManager.imgClose;
        MyCommand myCommand = this.cmdClose;
        g.drawImage(image, (float) myCommand.x, (float) myCommand.y);
    }

    public void show() {
        CanvasNinja.menuMain = this;
        CanvasNinja.clearAllPointer();
    }

    public void closeMenu() {
        CanvasNinja.menuMain = null;
    }

    public void commandTab(int index, int subIndex) {
        Res.outz("Vô command tab menu main:" + index);
        super.commandTab(index, subIndex);
        switch (index) {
            case -1:
                closeMenu();
                return;
            default:
                return;
        }
    }
}
