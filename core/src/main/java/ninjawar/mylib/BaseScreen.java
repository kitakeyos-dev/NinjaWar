// Class Version: 8
package ninjawar.mylib;

import ninjawar.myscr.Res;
import ninjawar.screen.dialog.Dialog;
import ninjawar.mymain.CanvasNinja;
import ninjawar.input.MyButton;
import ninjawar.model.MyCommand;

public class BaseScreen
{
    public static int cmdH;
    public static int cmdW;
    public static int keyTouch;
    public MyCommand center;
    public MyCommand left;
    public MyButton[] listBtn;
    public MyCommand right;
    public long timeDelayClick;

    static {
        BaseScreen.cmdW = 68;
        BaseScreen.cmdH = 26;
        BaseScreen.keyTouch = -1;
    }

    public static boolean getCmdPointerLast(final MyCommand myCommand) {
        if (myCommand == null) {
            return false;
        }
        if (myCommand.x >= 0 && myCommand.y != 0) {
            return myCommand.isPointerPressInside();
        }
        final Dialog currentDialog = CanvasNinja.currentDialog;
        if (currentDialog != null) {
            if (currentDialog.center != null) {
                final int w = CanvasNinja.w;
                final int cmdW = BaseScreen.cmdW;
                final int h = CanvasNinja.h;
                final int cmdH = BaseScreen.cmdH;
                if (CanvasNinja.isPointerHoldIn(w - cmdW >> 1, h - cmdH - 5, BaseScreen.cmdW, cmdH + 10)) {
                    BaseScreen.keyTouch = 1;
                    if (myCommand == CanvasNinja.currentDialog.center && CanvasNinja.isPointerClick && CanvasNinja.isPointerRelease) {
                        return true;
                    }
                }
            }
            if (CanvasNinja.currentDialog.left != null) {
                final int h2 = CanvasNinja.h;
                final int cmdH2 = BaseScreen.cmdH;
                if (CanvasNinja.isPointerHoldIn(0, h2 - cmdH2 - 5, BaseScreen.cmdW, cmdH2 + 10)) {
                    BaseScreen.keyTouch = 0;
                    if (myCommand == CanvasNinja.currentDialog.left && CanvasNinja.isPointerClick && CanvasNinja.isPointerRelease) {
                        return true;
                    }
                }
            }
            if (CanvasNinja.currentDialog.right != null) {
                final int w2 = CanvasNinja.w;
                final int cmdW2 = BaseScreen.cmdW;
                final int h3 = CanvasNinja.h;
                final int cmdH3 = BaseScreen.cmdH;
                if (CanvasNinja.isPointerHoldIn(w2 - cmdW2, h3 - cmdH3 - 5, BaseScreen.cmdW, cmdH3 + 10)) {
                    BaseScreen.keyTouch = 2;
                    if (myCommand == CanvasNinja.currentDialog.right && CanvasNinja.isPointerClick && CanvasNinja.isPointerRelease) {
                        return true;
                    }
                }
            }
        }
        else {
            if (myCommand == CanvasNinja.currentScreen.left) {
                final int h4 = CanvasNinja.h;
                final int cmdH4 = BaseScreen.cmdH;
                if (CanvasNinja.isPointerHoldIn(0, h4 - cmdH4 - 5, BaseScreen.cmdW, cmdH4 + 10)) {
                    BaseScreen.keyTouch = 0;
                    if (CanvasNinja.isPointerClick && CanvasNinja.isPointerRelease) {
                        return true;
                    }
                }
            }
            if (myCommand == CanvasNinja.currentScreen.right) {
                final int w3 = CanvasNinja.w;
                final int cmdW3 = BaseScreen.cmdW;
                final int h5 = CanvasNinja.h;
                final int cmdH5 = BaseScreen.cmdH;
                if (CanvasNinja.isPointerHoldIn(w3 - cmdW3, h5 - cmdH5 - 5, BaseScreen.cmdW, cmdH5 + 10)) {
                    BaseScreen.keyTouch = 2;
                    if (CanvasNinja.isPointerClick && CanvasNinja.isPointerRelease) {
                        return true;
                    }
                }
            }
            if (myCommand == CanvasNinja.currentScreen.center) {
                final int w4 = CanvasNinja.w;
                final int cmdW4 = BaseScreen.cmdW;
                final int h6 = CanvasNinja.h;
                final int cmdH6 = BaseScreen.cmdH;
                if (CanvasNinja.isPointerHoldIn(w4 - cmdW4 >> 1, h6 - cmdH6 - 5, BaseScreen.cmdW, cmdH6 + 10)) {
                    BaseScreen.keyTouch = 1;
                    if (CanvasNinja.isPointerClick && CanvasNinja.isPointerRelease) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void commandTab(final int n, final int n2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("vo ông nội commandTab- ");
        sb.append(n);
        Res.outz(sb.toString());
    }

    public void keyPress(final int n) {
    }

    public void keyTyped() {
    }

    public void paint(final mGraphics mGraphics) {
        mGraphics.translate(-mGraphics.getTranslateX(), -mGraphics.getTranslateY());
        mGraphics.setClip(0, 0, CanvasNinja.w, CanvasNinja.h + 1);
    }

    public void paintShape(final mGraphics mGraphics) {
    }

    public void resize() {
    }

    public void switchToMe() {
        this.timeDelayClick = mSystem.currentTimeMillis() + 200L;
        CanvasNinja.clearAllPointer();
        CanvasNinja.clearKeyPressed();
        CanvasNinja.clearKeyHold();
        final BaseScreen currentScreen = CanvasNinja.currentScreen;
        if (currentScreen != null) {
            currentScreen.unLoad();
        }
        CanvasNinja.currentScreen = this;
        AbstractCanvas.setFullScreenMode(true);
    }

    public void unLoad() {
    }

    public void update() {
        if (this.listBtn != null) {
            int n = 0;
            while (true) {
                final MyButton[] listBtn = this.listBtn;
                if (n >= listBtn.length) {
                    break;
                }
                final MyButton myButton = listBtn[n];
                if (myButton != null) {
                    myButton.update();
                }
                ++n;
            }
        }
    }

    public void updateKey() {
        if (this.timeDelayClick != 0L && mSystem.currentTimeMillis() > this.timeDelayClick) {
            this.timeDelayClick = 0L;
        }
        if (this.timeDelayClick != 0L && mSystem.currentTimeMillis() < this.timeDelayClick) {
            CanvasNinja.clearAllPointer();
            CanvasNinja.clearKeyPressed();
            CanvasNinja.clearKeyHold();
            return;
        }
        if (this.listBtn != null) {
            int n = 0;
            while (true) {
                final MyButton[] listBtn = this.listBtn;
                if (n >= listBtn.length) {
                    break;
                }
                final MyButton myButton = listBtn[n];
                if (myButton != null) {
                    myButton.updatePointer();
                }
                ++n;
            }
        }
        if (CanvasNinja.keyPressed[5] || getCmdPointerLast(CanvasNinja.currentScreen.center)) {
            CanvasNinja.keyPressed[5] = false;
            BaseScreen.keyTouch = -1;
            CanvasNinja.isPointerRelease = false;
            final MyCommand center = this.center;
            if (center != null) {
                center.perform();
            }
        }
        if (CanvasNinja.keyPressed[12] || getCmdPointerLast(CanvasNinja.currentScreen.left)) {
            CanvasNinja.keyPressed[12] = false;
            BaseScreen.keyTouch = -1;
            CanvasNinja.isPointerRelease = false;
            final MyCommand left = this.left;
            if (left != null) {
                left.perform();
            }
        }
        if (CanvasNinja.keyPressed[13] || getCmdPointerLast(CanvasNinja.currentScreen.right)) {
            CanvasNinja.keyPressed[13] = false;
            BaseScreen.keyTouch = -1;
            CanvasNinja.isPointerRelease = false;
            final MyCommand right = this.right;
            if (right != null) {
                right.perform();
            }
        }
    }
}
