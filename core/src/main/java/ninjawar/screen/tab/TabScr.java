package ninjawar.screen.tab;

import ninjawar.input.MyButton;
import ninjawar.input.TField;
import ninjawar.model.MyCommand;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.scroll.ScrollY;
import ninjawar.supporter.LoadDataManager;

public abstract class TabScr {
    public static mFont fontPaintTile = mFont.tahoma_7b_white;
    public int[] arrH;
    public int[] arrW;
    public int[] arrX;
    public int[] arrY;
    public MyButton[] btns;
    public MyCommand cmdClose;
    public int h;
    public int hTitle;
    public int margin = 5;
    public int margin2 = 2;
    public int margin3 = 3;
    public int margin7 = 7;
    public int margin8 = 8;
    public ScrollY scroll;
    public ScrollY scroll2;
    public String textTitle = "";
    public int w;
    public int wTitle;
    public int x;
    public int xTitle;
    public int y;
    public int yTitle;

    public abstract void keyPress(int i);

    public abstract void keyTyped();

    public abstract void paint(mGraphics mgraphics);

    public abstract void show();

    public abstract void update();

    public abstract void updateKey();

    public void close() {
        CanvasNinja.currentTab = null;
    }

    public void initStart() {
        this.margin = 5;
        this.x = (CanvasNinja.w - this.w) / 2;
        this.y = (CanvasNinja.h - this.h) / 2;
        MyCommand myCommand = new MyCommand("", -1, this);
        this.cmdClose = myCommand;
        myCommand.setPosPaint((((this.x + 1) + this.w) - LoadDataManager.imgClose.getRWidth()) + 2, (this.y - 1) - 2, LoadDataManager.imgClose);
        this.cmdClose.isClose = true;
    }

    public void showTab() {
        CanvasNinja.currentTab = this;
    }

    public void initScroll(int x2, int y2, int w2, int h2, int hMaxPaint, int hRealPaint) {
        this.scroll = new ScrollY(x2, y2, w2, h2, hMaxPaint, hRealPaint);
    }

    public void initScroll2(int x2, int y2, int w2, int h2, int hMaxPaint, int hRealPaint) {
        this.scroll2 = new ScrollY(x2, y2, w2, h2, hMaxPaint, hRealPaint);
    }

    public void updateScroll() {
        ScrollY scrollY = this.scroll;
        if (scrollY != null) {
            scrollY.update();
        }
        ScrollY scrollY2 = this.scroll2;
        if (scrollY2 != null) {
            scrollY2.update();
        }
    }

    public void commandTab(int index, int subIndex) {
        switch (index) {
            case -1:
                close();
                TField.resetTField();
                return;
            default:
                return;
        }
    }

    public void paintButton(mGraphics g) {
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.paint(g);
            }
        }
    }

    public void updateButton() {
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.update();
            }
        }
    }

    public void updatePointerButton() {
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.updatePointer();
            }
        }
    }

    public void fixSizeTitle() {
        this.wTitle = Res.fixSizeTagFrameUp(8, this.wTitle, LoadDataManager.frameTitle);
    }
}
