package ninjawar.screen.subtab;

import ninjawar.input.KEY;
import ninjawar.input.MyButton;
import ninjawar.input.TField;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

public class SubTabKeyWareHouse extends SubTabScr {
    public static mFont fontPaintPlaceHolder = mFont.tahoma_place_holder;
    public static mFont fontPaintText;
    public static mFont fontPaintTitle;
    public static SubTabKeyWareHouse me;
    MyCommand cmdAccept;
    int marginEachOne = 15;
    int marginLeftRight;
    int marginTopBot;
    public TField tfPass;
    public TField tfRepass;

    static {
        mFont mfont = mFont.tahoma_7b_brown;
        fontPaintText = mfont;
        fontPaintTitle = mfont;
    }

    public void startSubTabBuyShop(int x, int y, int w, int h) {
        int i = (w * 35) / 100;
        this.w = i;
        int i2 = (h * 2) / 3;
        this.h = i2;
        this.x = (CanvasNinja.w - i) / 2;
        this.y = (CanvasNinja.h - i2) / 2;
        init();
        show();
    }

    public static SubTabKeyWareHouse gI() {
        if (me == null) {
            me = new SubTabKeyWareHouse();
        }
        return me;
    }

    public void init() {
        this.margin = 5;
        this.marginEachOne = 5 * 3;
        this.marginTopBot = 5 * 3;
        this.marginLeftRight = 5 * 2;
        this.cmdAccept = new MyCommand("", 1, this);
        MyButton[] myButtonArr = new MyButton[1];
        this.btns = myButtonArr;
        FrameImage frameImage = LoadDataManager.myButtons[1];
        myButtonArr[0] = new MyButton(frameImage, frameImage, 55, 0, SupportTranslate.getTextLangue("ACCEPT"), 0, 0, this.cmdAccept);
        int[] iArr = {fontPaintTitle.getHeight(), fontPaintText.getHeight(), (int) LoadDataManager.frameInput.frameHeight, fontPaintText.getHeight(), (int) LoadDataManager.frameInput.frameHeight};
        this.arrH = iArr;
        int i = this.w;
        int i2 = this.marginLeftRight;
        int wInput = (i - (i2 * 2)) - 14;
        this.arrW = new int[]{0, 0, wInput, 0, wInput};
        int i3 = this.x;
        int xStart = i2 + i3 + 7;
        this.arrX = new int[]{i3 + (i / 2), xStart + 3, xStart, xStart + 3, xStart};
        int i4 = (this.marginTopBot * 2) + iArr[0] + (this.marginEachOne * 2);
        int i5 = iArr[1];
        int i6 = this.margin;
        int i7 = i4 + ((i5 + i6 + iArr[2]) * 2) + 14 + this.btns[0].h + (i6 * 2);
        this.h = i7;
        this.y = (CanvasNinja.h - i7) / 2;
        initStart();
        int i8 = this.y;
        int i9 = this.marginTopBot;
        int yStart = i8 + 7 + i9;
        int[] iArr2 = this.arrH;
        int i10 = iArr2[0];
        int i11 = this.marginEachOne;
        int i12 = this.margin;
        int i13 = iArr2[1];
        int i14 = iArr2[2];
        this.arrY = new int[]{yStart, yStart + i10 + i11, yStart + i10 + i11 + i12 + i13, yStart + i10 + (i11 * 2) + i12 + i13 + i14, i10 + yStart + (i11 * 2) + (i12 * 2) + i13 + i14 + iArr2[3]};
        MyButton myButton = this.btns[0];
        myButton.setXY(this.x + ((this.w - myButton.w) / 2), (((i8 + this.h) - i9) - myButton.h) - 7);
        initTField();
    }

    public void initTField() {
        this.tfPass = new TField();
        TField tField = new TField();
        this.tfRepass = tField;
        TField tField2 = this.tfPass;
        mFont mfont = mFont.tahoma_7b_white;
        tField2.fontTField = mfont;
        tField.fontTField = mfont;
        tField2.setIputType(2);
        this.tfPass.setMaxTextLenght(12);
        TField tField3 = this.tfPass;
        tField3.x = this.arrX[2];
        tField3.y = this.arrY[2];
        tField3.width = this.arrW[2];
        tField3.height = this.arrH[2];
        this.tfRepass.setIputType(2);
        this.tfRepass.setMaxTextLenght(12);
        TField tField4 = this.tfRepass;
        tField4.x = this.arrX[4];
        tField4.y = this.arrY[4];
        tField4.width = this.arrW[4];
        tField4.height = this.arrH[4];
        this.tfPass.updateMarginTField(7);
        this.tfRepass.updateMarginTField(7);
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        CanvasNinja.resetTrans(g);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        SupportPaint supportPaint = CanvasNinja.paintz;
        FrameImage frameImage = LoadDataManager.frameBgWhite1;
        int i = this.margin;
        supportPaint.paintSingleBorderFrame(g, frameImage, (float) (this.x + 7 + i), (float) (this.y + 7 + i), (float) ((this.w - 14) - (i * 2)), (float) ((this.h - 14) - (i * 2)), 1, false);
        fontPaintTitle.drawString(g, SupportTranslate.getTextLangue("inventory_Pass"), this.arrX[0], this.arrY[0], 2);
        fontPaintText.drawString(g, SupportTranslate.getTextLangue("password"), this.arrX[1], this.arrY[1], 0);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameInput, this.arrX[2], this.arrY[2], this.arrW[2], this.tfPass.isFocused(), SupportPaint.TYPE_NONE, true);
        fontPaintText.drawString(g, SupportTranslate.getTextLangue("repassword"), this.arrX[3], this.arrY[3], 0);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameInput, this.arrX[4], this.arrY[4], this.arrW[4], this.tfRepass.isFocused(), SupportPaint.TYPE_NONE, true);
        this.tfPass.painTextInputNoneBackGround(mgraphics);
        this.tfRepass.painTextInputNoneBackGround(mgraphics);
        if (!this.tfPass.isFocused() && this.tfPass.getText().equals("")) {
            fontPaintPlaceHolder.drawString(g, SupportTranslate.getTextLangue("password"), this.arrX[2] + 7, (this.arrH[2] / 2) + this.arrY[2], 4);
        }
        if (!this.tfRepass.isFocused() && this.tfRepass.getText().equals("")) {
            fontPaintPlaceHolder.drawString(g, SupportTranslate.getTextLangue("repassword"), this.arrX[4] + 7, (this.arrH[4] / 2) + this.arrY[4], 4);
        }
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton item : myButtonArr) {
                item.paint(mgraphics);
            }
        }
        MyCommand myCommand = this.cmdClose;
        if (myCommand != null) {
            myCommand.paintIconOnly(mgraphics);
        }
    }

    private String checkInputPasswordAndRePassword() {
        String pass = this.tfPass.getText();
        String repass = this.tfRepass.getText();
        if (pass.equals("")) {
            return SupportTranslate.getTextLangue("password") + SupportTranslate.getTextLangue("notNull");
        } else if (repass.equals("")) {
            return SupportTranslate.getTextLangue("repassword") + SupportTranslate.getTextLangue("notNull");
        } else if (pass.equals(repass)) {
            return "";
        } else {
            return SupportTranslate.getTextLangue("repassword") + " " + SupportTranslate.getTextLangue("notCorrect");
        }
    }

    public void update() {
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.update();
            }
        }
        this.tfPass.update();
        this.tfRepass.update();
        TField.updateFocus(this.tfPass, this.tfRepass);
    }

    public void updateKey() {
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.updatePointer();
            }
        }
        MyCommand myCommand = this.cmdClose;
        if (myCommand != null) {
            myCommand.updatePointer();
        }
    }

    public void show() {
        CanvasNinja.subTab = this;
        me = this;
    }

    public void keyPress(int keyCode) {
        if (keyCode == KEY.KEY_TAB) {
            CanvasNinja.clearAllPointer();
            CanvasNinja.keyPCTyped = "";
            if (this.tfPass.isFocused()) {
                this.tfPass.setFocus(false);
                this.tfRepass.setFocus(true);
            } else if (this.tfRepass.isFocused()) {
                this.tfPass.setFocus(true);
                this.tfRepass.setFocus(false);
            } else {
                this.tfPass.setFocus(true);
                this.tfRepass.setFocus(false);
            }
        } else {
            if (keyCode == KEY.KEY_ENTER) {
                CanvasNinja.keyPCTyped = "";
                this.cmdAccept.perform();
            }
            if (this.tfPass.isFocused()) {
                this.tfPass.keyPressed(keyCode);
            } else if (this.tfRepass.isFocused()) {
                this.tfRepass.keyPressed(keyCode);
            }
        }
    }

    public void keyTyped() {
        if (this.tfPass.isFocused()) {
            this.tfPass.keyTyped();
        } else if (this.tfRepass.isFocused()) {
            this.tfRepass.keyTyped();
        }
    }

    public void commandTab(int index, int subIndex) {
        Res.outz("Vào cmd tab của sub tab key warehouse:" + index);
        super.commandTab(index, subIndex);
        switch (index) {
            case 1:
                String error = checkInputPasswordAndRePassword();
                if (!error.equals("")) {
                    Res.outz("Lỗi:" + error);
                    CanvasNinja.startOKDlg(error);
                    return;
                }
                SendMessage.gI().setPassWareHouse(this.tfPass.getText());
                return;
            default:
                return;
        }
    }
}
