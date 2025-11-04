package ninjawar.input;

import ninjawar.model.MyCommand;
import ninjawar.model.mGiftData;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;

public class MyButton {
    public static mFont FONT_DEFAULT = mFont.tahoma_7_white;
    public static int wDefault;
    public MyCommand cmd;
    public mFont fontPaint = FONT_DEFAULT;
    public int h;
    public Integer id;
    public FrameImage imgButton;
    public FrameImage imgButtonHover;
    public Image imgDone;
    public Image imgFull;
    public Image imgMoney;
    public boolean isClick = false;
    public boolean isDelayDone = false;
    public boolean isDisable = false;
    public boolean isDone;
    public boolean isFull;
    public boolean isHold = false;
    public boolean isHover = false;
    public boolean isMoney;
    public boolean isSmall = false;
    private long lastTimeClicked = 0;
    int marginBtn = 3;
    public String name;
    int numStandard = 5;
    public String sku;
    public int sumWMoney;
    public int tickClick = 0;
    public int w;
    public int xBtn;
    public int yBtn;
    public int yViewPort;

    public void setWidthStandard() {
        int i;
        int i2 = this.w;
        int mod = i2 % ((int) this.imgButton.frameWidth);
        if (mod != 0 && mod <= (i = this.numStandard)) {
            this.w = i2 + i;
        }
    }

    public void createButton(Image imgFull2, Image imgDone2, FrameImage imgBtn, FrameImage imgBtnHover, int minW, int id2, String name2, int xBtn2, int yBtn2, MyCommand cmd2, boolean isMoney2) {
        Image image;
        this.isMoney = isMoney2;
        this.imgDone = imgDone2;
        this.imgFull = imgFull2;
        this.isDone = false;
        this.id = Integer.valueOf(id2);
        this.name = name2;
        if (cmd2 != null && isMoney2) {
            setImageMoney(cmd2.subAction);
            if (!(this.name == null || (image = this.imgMoney) == null)) {
                this.sumWMoney = image.getRWidth();
            }
        }
        this.imgButton = imgBtn;
        this.imgButtonHover = imgBtnHover;
        this.xBtn = xBtn2;
        this.yBtn = yBtn2;
        this.cmd = cmd2;
        this.w = this.fontPaint.getWidth(name2) + 15;
        if (this.imgMoney != null) {
            this.w = this.sumWMoney + 15;
        }
        if (this.w < minW) {
            this.w = minW;
        }
        setWidthStandard();
        this.h = (int) this.imgButton.frameHeight;
        this.yViewPort = this.yBtn;
        if (this.w == 0) {
            this.w = wDefault;
        }
    }

    public MyButton(FrameImage imgBtn, FrameImage imgBtnHover, int minW, int id2, String name2, int xBtn2, int yBtn2, MyCommand cmd2) {
        createButton((Image) null, (Image) null, imgBtn, imgBtnHover, minW, id2, name2, xBtn2, yBtn2, cmd2, false);
    }

    public MyButton(FrameImage imgBtn, FrameImage imgBtnHover, int minW, int id2, String name2, int xBtn2, int yBtn2, MyCommand cmd2, boolean isMoney2) {
        createButton((Image) null, (Image) null, imgBtn, imgBtnHover, minW, id2, name2, xBtn2, yBtn2, cmd2, isMoney2);
    }

    public MyButton(Image imgDone2, FrameImage imgBtn, FrameImage imgBtnHover, int minW, int id2, String name2, int xBtn2, int yBtn2, MyCommand cmd2) {
        createButton((Image) null, imgDone2, imgBtn, imgBtnHover, minW, id2, name2, xBtn2, yBtn2, cmd2, false);
    }

    public MyButton(Image imgFull2, Image imgDone2, FrameImage imgBtn, FrameImage imgBtnHover, int minW, int id2, String name2, int xBtn2, int yBtn2, MyCommand cmd2) {
        createButton(imgFull2, imgDone2, imgBtn, imgBtnHover, minW, id2, name2, xBtn2, yBtn2, cmd2, false);
    }

    public MyButton(mFont font, FrameImage imgBtn, FrameImage imgBtnHover, int minW, int id2, String name2, int xBtn2, int yBtn2, MyCommand cmd2) {
        this.fontPaint = font;
        createButton((Image) null, (Image) null, imgBtn, imgBtnHover, minW, id2, name2, xBtn2, yBtn2, cmd2, false);
    }

    public MyButton() {
    }

    public void update() {
        if (!this.isDone && !this.isDisable && this.isClick) {
            int i = this.tickClick + 1;
            this.tickClick = i;
            if (i % 3 == 0) {
                this.isClick = false;
                this.tickClick = 0;
            }
        }
    }

    public void resetClickBtn() {
        this.isClick = false;
        this.tickClick = 0;
        this.isHold = false;
    }

    public boolean updatePointer() {
        if (this.isDone || this.isDisable) {
            return false;
        }
        if (CanvasNinja.isPointHover(this.xBtn, this.yViewPort, this.w, this.h)) {
            this.isHover = true;
        } else {
            this.isHover = false;
        }
        if (CanvasNinja.isPointerPress && !CanvasNinja.isPointerRelease && CanvasNinja.isPoint(this.xBtn, this.yViewPort, this.w, this.h)) {
            this.isHold = true;
        }
        if (CanvasNinja.isPointerRelease()) {
            this.isHold = false;
            if (CanvasNinja.isPoint(this.xBtn, this.yViewPort, this.w, this.h)) {
                CanvasNinja.clearAllPointer();
                if (System.currentTimeMillis() - this.lastTimeClicked >= 300) {
                    boolean z = !this.isClick;
                    this.isClick = z;
                    if (!z) {
                        resetClickBtn();
                    }
                    MyCommand myCommand = this.cmd;
                    if (myCommand != null) {
                        myCommand.perform();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void paint(mGraphics g) {
        if (this.isFull) {
            Image image = this.imgFull;
            if (image != null) {
                g.drawImage(image, (float) this.xBtn, (float) this.yBtn);
            }
        } else if (this.isDone) {
            Image image2 = this.imgDone;
            if (image2 != null) {
                g.drawImage(image2, (float) this.xBtn, (float) this.yBtn);
            }
        } else {
            paintButton(g, false);
        }
    }

    public void paintButton(mGraphics g, boolean useOutLine) {
        FrameImage frameImage = this.imgButton;
        int hBtn = (int) frameImage.frameHeight;
        int tickText = -1;
        boolean z = this.isClick;
        if (z || this.isHold) {
            tickText = 1;
        }
        SupportPaint supportPaint = CanvasNinja.paintz;
        if (this.isHover) {
            frameImage = this.imgButtonHover;
        }
        supportPaint.paintTagFrame(g, frameImage, this.xBtn, this.yBtn, this.w, z || this.isHold, SupportPaint.TYPE_NONE, false);
        if (!this.isMoney) {
            mFont mfont = this.fontPaint;
            mfont.drawString(g, this.name, this.xBtn + (this.w / 2), ((this.yBtn + (hBtn / 2)) - (mfont.getHeight() / 2)) + tickText, 2, false);
            return;
        }
        paintIconMoney(g, this.xBtn + ((this.w - this.sumWMoney) / 2), ((this.yBtn + (hBtn / 2)) - (this.imgMoney.getRHeight() / 2)) + tickText + 1, false);
    }

    public void paintIconMoney(mGraphics g, int x, int y, boolean useClip) {
        Image image = this.imgMoney;
        if (image != null) {
            g.drawImage(image, x, y, useClip);
        }
    }

    public void setXY(int xBtn2, int yBtn2) {
        this.xBtn = xBtn2;
        this.yViewPort = yBtn2;
        this.yBtn = yBtn2;
    }

    public void setImageMoney(int type) {
        this.imgMoney = LoadDataManager.imgMoney[mGiftData.partTypeServerToTypeClient(type)];
    }

    public static int getSumWidthListBtn(MyButton[] listBtn, int margin) {
        if (listBtn == null) {
            return 0;
        }
        if (listBtn.length == 1) {
            return listBtn[0].w;
        }
        int sumW = listBtn[0].w;
        for (int i = 1; i < listBtn.length; i++) {
            sumW += listBtn[i].w + margin;
        }
        return sumW;
    }

    public static int getWMaxBtns(MyButton[] listBtn) {
        if (listBtn == null) {
            return 0;
        }
        if (listBtn.length == 1) {
            return listBtn[0].w;
        }
        int wMax = listBtn[0].w;
        for (int i = 0; i < listBtn.length; i++) {
            if (wMax < listBtn[i].w) {
                wMax = listBtn[i].w;
            }
        }
        return wMax;
    }

    public static void setPosList(MyButton[] listBtn, int margin, int sumW, int xFirst, int yFirst) {
        for (int i = 0; i < listBtn.length; i++) {
            int wBefore = 0;
            if (i > 0) {
                for (int k = 0; k < i; k++) {
                    wBefore += listBtn[k].w + margin;
                }
            }
            listBtn[i].setXY(xFirst + wBefore, yFirst);
        }
    }

    public static void updateW(MyButton[] listBtn, int w2) {
        for (MyButton myButton : listBtn) {
            myButton.w = w2;
        }
    }
}
