package ninjawar.screen.dialog;

import ninjawar.model.MyCommand;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;

public abstract class Dialog {
    public MyCommand center;
    public MyCommand left;
    int lenCaption = 0;
    public MyCommand right;

    public abstract void commandTab(int i, int i2);

    public abstract void update();

    public abstract void updateKey();

    public void paint(mGraphics g) {
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.setClip(0, 0, CanvasNinja.w, CanvasNinja.h);
        CanvasNinja.paintz.paintTabSoft(g);
        CanvasNinja.paintz.paintCmdBar(g, this.left, this.center, this.right);
    }

    public void resize() {
    }

    public void keyPress(int keyCode) {
        switch (keyCode) {
            case -39:
            case -2:
                CanvasNinja.keyHold[8] = true;
                CanvasNinja.keyPressed[8] = true;
                return;
            case -38:
            case -1:
                CanvasNinja.keyHold[2] = true;
                CanvasNinja.keyPressed[2] = true;
                return;
            case -22:
            case -7:
                CanvasNinja.keyHold[13] = true;
                CanvasNinja.keyPressed[13] = true;
                return;
            case -21:
            case -6:
                CanvasNinja.keyHold[12] = true;
                CanvasNinja.keyPressed[12] = true;
                return;
            case -5:
            case 10:
                CanvasNinja.keyHold[5] = true;
                CanvasNinja.keyPressed[5] = true;
                return;
            default:
                return;
        }
    }

    public void keyTyped() {
    }
}
