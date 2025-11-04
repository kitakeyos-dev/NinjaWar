package ninjawar.screen.quest;

import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;

public abstract class QuestScreen {
    public int h;
    public int margin = 5;
    public int marginBT = 2;
    int marginContent = 20;
    public int marginLR = 3;
    int marginTextNoti = 15;
    public int w;
    public int x;
    public int y;

    public abstract void commandTab(int i, int i2);

    public abstract void paint(mGraphics mgraphics);

    public abstract void update();

    public abstract void updateKey();

    public void resize() {
    }

    public void switchToMe() {
        CanvasNinja.resetScrollWhell();
        CanvasNinja.questScreen = this;
        CanvasNinja.clearKeyPressed();
    }
}
