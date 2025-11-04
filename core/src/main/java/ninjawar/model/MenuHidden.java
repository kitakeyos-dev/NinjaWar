package ninjawar.model;

import java.util.Iterator;
import java.util.Vector;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;
import ninjawar.scroll.ScrollY;
import ninjawar.supporter.LoadDataManager;

public class MenuHidden {
    int col;
    int colWidth;
    int dir;
    public int h;
    public boolean isClose = false;
    public boolean isTrans = false;
    int itemsPerCol = 5;
    int margin = 5;
    public ScrollY scroll;
    int speed = 12;
    int startCmd = 10;
    public Vector<MyCommand> vecCmds = new Vector<>();
    public int w;
    public int x;
    public int xEnd;
    public int xStart;
    public int y;

    public MenuHidden(int xStart2, int xEnd2, int y2) {
        this.xStart = xStart2;
        this.x = xStart2;
        this.xEnd = xEnd2;
        this.y = y2;
        init();
    }

    private void addCmd(FrameImage frameImage) {
        MyCommand cmd = new MyCommand("", this.startCmd, MapScr.gI());
        int row = this.vecCmds.size() % this.itemsPerCol;
        int i = this.x + this.w;
        int i2 = this.margin;
        cmd.setPosPaint((i - i2) - ((0 + 1) * this.colWidth), this.y + i2 + ((((int) frameImage.frameHeight) + i2) * row), frameImage);
        this.vecCmds.add(cmd);
        this.startCmd++;
    }

    public void init() {
        this.dir = -1;
        this.vecCmds.removeAllElements();
        addCmd(LoadDataManager.frameInventory);
        addCmd(LoadDataManager.frameQuest);
        addCmd(LoadDataManager.frameLearnSkill);
        addCmd(LoadDataManager.frameFriendList);
        addCmd(LoadDataManager.frameSetting);
        addCmd(LoadDataManager.frameUpgrade);
        addCmd(LoadDataManager.frameAuto);
        addCmd(LoadDataManager.frameGacha);
        addCmd(LoadDataManager.frameParty);
        addCmd(LoadDataManager.framePurchase);
        addCmd(LoadDataManager.frameWorldMap);
        addCmd(LoadDataManager.frameMessage);
        this.col = (int) Math.ceil(((double) this.vecCmds.size()) / ((double) this.itemsPerCol));
        int i = this.vecCmds.get(0).w;
        int i2 = this.margin;
        int i3 = i + i2;
        this.colWidth = i3;
        int i4 = (this.col * i3) + i2;
        this.w = i4;
        this.xEnd -= i4 - (i2 * 2);
        int itemsInFirstCol = Math.min(this.itemsPerCol, this.vecCmds.size());
        int i5 = this.margin;
        int i6 = i5 + ((this.vecCmds.get(0).h + i5) * itemsInFirstCol);
        int i7 = this.margin;
        int i8 = i6 + i7;
        this.h = i8;
        int i9 = this.itemsPerCol * (i7 + this.vecCmds.get(0).h);
        int i10 = this.margin;
        if (i8 > i9 + i10) {
            this.h = (this.itemsPerCol * (i10 + this.vecCmds.get(0).h)) + this.margin;
        }
        initScroll();
    }

    private void initScroll() {
        int i = this.xEnd;
        int i2 = this.y;
        int i3 = this.w;
        int i4 = this.h;
        this.scroll = new ScrollY(i, i2, i3, i4, i4, i4);
    }

    public void update() {
        int i;
        int i2;
        if (this.isTrans) {
            int i3 = this.x;
            int i4 = this.dir;
            int i5 = i3 + (this.speed * i4);
            this.x = i5;
            if (i4 == -1 && i5 < (i2 = this.xEnd)) {
                this.x = i2;
                this.isTrans = false;
            }
            if (i4 == 1 && this.x > (i = this.xStart)) {
                this.x = i;
                this.isTrans = false;
                this.isClose = true;
            }
        }
    }

    public void open() {
        this.isTrans = true;
        this.dir = -1;
    }

    public void close() {
        this.isTrans = true;
        this.dir = 1;
    }

    public boolean updatePointer() {
        ScrollY scrollY = this.scroll;
        if (scrollY != null && CanvasNinja.currentTab == null) {
            scrollY.update();
        }
        if (CanvasNinja.isPoint(this.x, this.y, this.w, this.h)) {
            Iterator<MyCommand> it = this.vecCmds.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                it.next().updatePointer();
                if (0 != 0) {
                    CanvasNinja.clearAllPointer();
                    break;
                }
            }
        }
        return isFocusMenuHidden();
    }

    public boolean isFocusMenuHidden() {
        return CanvasNinja.isFocusAt(this.x, this.y, this.w, this.h);
    }

    public void paint(mGraphics g) {
        CanvasNinja.paintz.paintSingleBorderFrame(LoadDataManager.frameBgFunction, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 100, this.scroll != null, g);
        g.setClip(this.x, this.y, this.w, this.h);
        ScrollY scrollY = this.scroll;
        if (scrollY != null) {
            g.translate(0, -scrollY.cmy);
        }
        for (int i = 0; i < this.vecCmds.size(); i++) {
            this.vecCmds.get(i).x = (this.x + this.w) - (((i / this.itemsPerCol) + 1) * this.colWidth);
            this.vecCmds.get(i).paintIconOnly(g, true);
        }
    }
}
