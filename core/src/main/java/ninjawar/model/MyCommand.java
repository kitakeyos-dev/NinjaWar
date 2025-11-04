package ninjawar.model;

import ninjawar.input.ActionInterfaceNone;
import ninjawar.mylib.AudioManager;
import ninjawar.mylib.BaseScreen;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.screen.dialog.Dialog;
import ninjawar.screen.menu.MenuMain;
import ninjawar.screen.objscr.ObjScr;
import ninjawar.screen.objscr.ObjScrMore;
import ninjawar.screen.quest.QuestScreen;
import ninjawar.screen.subtab.SubTabScr;
import ninjawar.screen.tab.TabScr;

public class MyCommand {
    public ActionInterfaceNone action = null;
    public ActionInterface actionListener;
    public String caption = "";
    public String caption2 = "";
    public FrameImage frameImg;
    public int h = BaseScreen.cmdH;
    public int hw;
    public int idAction;
    public Image img;
    public Image imgFocus;
    public boolean isClose = false;
    public boolean isFocus = false;
    public boolean isPaint = false;
    public boolean isPlaySoundButton = true;
    int lenCaption = 0;
    public Object p;
    public int subAction = -1;
    public int type;
    public int w = BaseScreen.cmdW;
    public int x = 0;
    public int y = 0;

    public MyCommand(String caption3, int action2, Object p2) {
        this.caption = caption3;
        this.idAction = action2;
        this.p = p2;
    }

    public void perform() {
        ActionInterface actionInterface;
        String str;
        CanvasNinja.clearAllPointerEvent();
        if (this.isPlaySoundButton && (str = this.caption) != null && str.equals("")) {
            if (this.isClose) {
                AudioManager.buttonClose();
            } else {
                AudioManager.buttonClick();
            }
        }
        if (CanvasNinja.currentScreen != null) {
            Dialog dialog = CanvasNinja.currentDialog;
            if (dialog != null) {
                dialog.commandTab(this.idAction, this.subAction);
            } else {
                MenuMain menuMain = CanvasNinja.menuMain;
                if (menuMain != null) {
                    menuMain.commandTab(this.idAction, this.subAction);
                } else {
                    SubTabScr subTabScr = CanvasNinja.subTab;
                    if (subTabScr != null) {
                        subTabScr.commandTab(this.idAction, this.subAction);
                    } else {
                        TabScr tabScr = CanvasNinja.currentTab;
                        if (tabScr != null) {
                            tabScr.commandTab(this.idAction, this.subAction);
                        } else {
                            QuestScreen questScreen = CanvasNinja.questScreen;
                            if (questScreen != null) {
                                questScreen.commandTab(this.idAction, this.subAction);
                            } else {
                                ObjScr objScr = CanvasNinja.objScr;
                                if (objScr == null || !objScr.commandTabBoolean(this.idAction, this.subAction)) {
                                    ObjScrMore objScrMore = CanvasNinja.objScrMore;
                                    if (objScrMore == null || !objScrMore.commandTabBoolean(this.idAction, this.subAction)) {
                                        CanvasNinja.currentScreen.commandTab(this.idAction, this.subAction);
                                    } else {
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        int i = this.idAction;
        if (i > 0 && (actionInterface = this.actionListener) != null) {
            actionInterface.perform(i, this.p);
        }
    }

    public void paintIconOnly(mGraphics g) {
        paintIconOnly(g, false);
    }

    public void paintIconOnly(mGraphics g, boolean useClip) {
        if (this.isPaint) {
            Image image = this.img;
            if (image != null) {
                g.drawImage(image, (float) this.x, (float) this.y, 0, useClip);
            }
            FrameImage frameImage = this.frameImg;
            if (frameImage != null) {
                frameImage.drawFrame(this.isFocus ? 1 : 0, this.x, this.y, useClip, g);
            }
        }
    }

    public void updateIconOnly() {
        if (CanvasNinja.isPointerRelease() && this.isPaint) {
            Image image = this.img;
            if (image != null && CanvasNinja.isPoint(this.x, this.y, image.width, image.height)) {
                CanvasNinja.clearAllPointer();
                perform();
            }
            FrameImage frameImage = this.frameImg;
            if (frameImage != null && CanvasNinja.isPoint(this.x, this.y, (int) frameImage.frameWidth, (int) frameImage.frameHeight)) {
                this.isFocus = true;
                CanvasNinja.clearAllPointer();
                perform();
                this.isFocus = true;
            }
            if (this.isFocus && CanvasNinja.gameTick % 3 == 0) {
                this.isFocus = false;
            }
        }
    }

    public void paint(mGraphics g) {
        int xx;
        Image image = this.img;
        if (image != null) {
            g.drawImage(image, (float) this.x, (float) this.y, 0);
            if (this.isFocus) {
                g.drawImage(this.imgFocus, (float) this.x, (float) this.y, 0);
            }
            String str = this.caption;
            if (str != "" && str != null) {
                if (!this.isFocus) {
                    mFont.tahoma_7b_dark.drawString(g, str, this.x + (this.img.getRWidth() / 2), (this.y + (this.img.getRHeight() / 2)) - 5, 2);
                    return;
                }
                mFont.tahoma_7b_green2.drawString(g, str, this.x + (this.img.getRWidth() / 2), (this.y + (this.img.getRHeight() / 2)) - 5, 2);
                return;
            }
            return;
        }
        if (this.type == 1) {
            xx = this.x + this.hw;
        } else {
            xx = this.x + 38;
        }
        if (!this.isFocus) {
            mFont.tahoma_7b_dark.drawString(g, this.caption, xx, this.y + 7, 2);
            return;
        }
        mFont.tahoma_7b_green2.drawString(g, this.caption, xx, this.y + 7, 2);
    }

    public boolean isPointerPressInside() {
        this.isFocus = false;
        if (CanvasNinja.isPointerHoldIn(this.x, this.y, this.w, this.h)) {
            if (CanvasNinja.isPointerDown) {
                this.isFocus = true;
            }
            if (!CanvasNinja.isPointerRelease || !CanvasNinja.isPointerClick) {
                return false;
            }
            return true;
        }
        return false;
    }

    public MyCommand(String caption3, ActionInterfaceNone action2) {
        this.caption = caption3;
        this.action = action2;
        this.w = mFont.tahoma_7.getWidth(caption3);
        this.h = mFont.tahoma_7.getHeight();
    }

    public void setPosPaint(int x2, int y2, Image img2) {
        this.isPaint = true;
        this.img = img2;
        this.x = x2;
        this.y = y2;
        this.w = img2.getRWidth();
        this.h = img2.getRHeight();
    }

    public void setPosPaint(int x2, int y2, FrameImage frameImg2) {
        this.isPaint = true;
        this.frameImg = frameImg2;
        this.x = x2;
        this.y = y2;
        this.w = (int) frameImg2.frameWidth;
        this.h = (int) frameImg2.frameHeight;
    }

    public void updatePointer() {
        updatePointer(0);
    }

    public boolean updatePointer(int cmy) {
        if (CanvasNinja.gameTick % 3 == 0) {
            this.isFocus = false;
        }
        if (!CanvasNinja.isPointerRelease() || !CanvasNinja.isPoint(this.x, this.y - cmy, this.w, this.h)) {
            return false;
        }
        CanvasNinja.clearAllPointer();
        perform();
        this.isFocus = true;
        return true;
    }
}
