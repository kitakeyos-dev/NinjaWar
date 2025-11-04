package ninjawar.screen.dialog;

import java.util.Vector;
import ninjawar.input.KEY;
import ninjawar.input.MyButton;
import ninjawar.input.TField;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Rms;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

public class InputDialog extends Dialog {
    public static InputDialog instance;
    MyButton[] btns;
    MyCommand[] cmdBtn;
    int h;
    int hBtn;
    boolean[] isClick;
    boolean[] isHold;
    boolean isMiddle;
    int margin;
    int minH;
    int minW;
    String[] text;
    public TField tfInput;
    int[] tickClick;
    int tickSelectedBtn = 0;
    int w;
    int wBtn;
    int x;
    int xBtn;
    int y;
    int yBtn;

    public static InputDialog gI() {
        if (instance == null) {
            instance = new InputDialog();
        }
        return instance;
    }

    public void init() {
        this.tfInput = new TField();
        TField.resetTField();
        this.isClick = new boolean[]{false, false};
        this.isHold = new boolean[]{false, false};
        this.tickClick = new int[]{0, 0};
    }

    public void startInputDlg(String text2, int typeInput, MyCommand cmdAgree) {
        startInputDlg(text2, typeInput, cmdAgree, true, 300, false, -1999);
    }

    public void startInputDlg(String text2, int typeInput, MyCommand cmdAgree, boolean isCancel, int maxText, boolean isMiddle2, int keyCode) {
        String str = text2;
        int i = typeInput;
        MyCommand myCommand = cmdAgree;
        this.isMiddle = isMiddle2;
        init();
        Vector<String> vecStrs = new Vector<>();
        if (isCancel) {
            MyCommand[] myCommandArr = new MyCommand[2];
            this.cmdBtn = myCommandArr;
            myCommandArr[0] = myCommand;
            myCommandArr[1] = new MyCommand(SupportTranslate.getTextLangue("BACK"), 0, this);
            vecStrs.add(myCommand.caption);
            vecStrs.add(SupportTranslate.getTextLangue("BACK"));
        } else {
            MyCommand[] myCommandArr2 = new MyCommand[1];
            this.cmdBtn = myCommandArr2;
            myCommandArr2[0] = myCommand;
            vecStrs.add(myCommand.caption);
        }
        this.wBtn = MyButton.FONT_DEFAULT.getWidth(Res.findStringMax(vecStrs)) + 21;
        this.hBtn = (int) LoadDataManager.myButtons[0].frameHeight;
        this.margin = 5;
        this.minH = 134;
        if (this.h < 134) {
            this.h = 134;
        }
        this.minW = 255;
        if (this.w < 255) {
            this.w = 255;
        }
        this.x = (CanvasNinja.w - this.w) / 2;
        this.y = (CanvasNinja.h - this.h) / 2;
        int width = mFont.tahoma_7_white.getWidth(str);
        int i2 = this.w;
        if (width < i2) {
            String[] strArr = new String[1];
            this.text = strArr;
            strArr[0] = str;
        } else {
            this.text = mFont.tahoma_7_white.splitFontArray(str, (i2 - 15) - 10);
        }
        int yMoreText = 0;
        if (str != null) {
            yMoreText = mFont.tahoma_7_white.getHeight() * (this.text.length - 1);
        }
        int i3 = this.h + yMoreText;
        this.h = i3;
        this.y = (CanvasNinja.h - i3) / 2;
        TField tField = this.tfInput;
        tField.isKeyTyped = true;
        mFont mfont = mFont.tahoma_7;
        tField.fontPaint = mfont;
        tField.fontTField = mfont;
        tField.width = this.w / 2;
        if (i == 6) {
            tField.width = mfont.getWidth("DELETE") + (this.margin * 5);
        }
        TField tField2 = this.tfInput;
        tField2.keyCodeInputKey = keyCode;
        tField2.setTextFirst(KEY.getNameKey(keyCode));
        TField tField3 = this.tfInput;
        tField3.x = (this.x + (this.w / 2)) - (tField3.width / 2);
        tField3.y = this.y + (this.margin * 2) + mFont.tahoma_7_white.getHeight() + (this.margin * 4) + yMoreText;
        this.tfInput.setMaxTextLenght(maxText);
        this.tfInput.setIputType(i);
        if (isCancel) {
            int i4 = this.margin;
            this.xBtn = (this.x + (this.w / 2)) - (((this.wBtn * 2) + (i4 * 2)) / 2);
            this.yBtn = this.tfInput.y + ((int) LoadDataManager.frameInput.frameHeight) + (i4 * 2);
            MyButton[] myButtonArr = new MyButton[2];
            this.btns = myButtonArr;
            FrameImage frameImage = LoadDataManager.myButtons[0];
            int i5 = this.wBtn;
            String textLangue = SupportTranslate.getTextLangue("ACCEPT");
            int i6 = this.xBtn;
            myButtonArr[0] = new MyButton(frameImage, frameImage, i5, 0, textLangue, i6, this.yBtn, this.cmdBtn[0]);
            MyButton[] myButtonArr2 = this.btns;
            MyButton myButton = myButtonArr2[0];
            myButton.xBtn = this.xBtn;
            int i7 = this.yBtn;
            myButton.yViewPort = i7;
            myButton.yBtn = i7;
            FrameImage frameImage2 = LoadDataManager.myButtons[1];
            int i8 = this.wBtn;
            String textLangue2 = SupportTranslate.getTextLangue("BACK");
            int i9 = this.xBtn;
            myButtonArr2[1] = new MyButton(frameImage2, frameImage2, i8, 1, textLangue2, i9, this.yBtn, this.cmdBtn[1]);
            MyButton myButton2 = this.btns[1];
            myButton2.xBtn = this.xBtn + this.wBtn + (this.margin * 2);
            int i10 = this.yBtn;
            myButton2.yViewPort = i10;
            myButton2.yBtn = i10;
        } else {
            this.xBtn = (this.x + (this.w / 2)) - ((this.wBtn * 1) / 2);
            this.yBtn = this.tfInput.y + ((int) LoadDataManager.frameInput.frameHeight) + (this.margin * 2);
            MyButton[] myButtonArr3 = new MyButton[1];
            this.btns = myButtonArr3;
            FrameImage frameImage3 = LoadDataManager.myButtons[0];
            int i11 = this.wBtn;
            String textLangue3 = SupportTranslate.getTextLangue("ACCEPT");
            int i12 = this.xBtn;
            myButtonArr3[0] = new MyButton(frameImage3, frameImage3, i11, 0, textLangue3, i12, this.yBtn, this.cmdBtn[0]);
            MyButton myButton3 = this.btns[0];
            myButton3.xBtn = this.xBtn;
            int i13 = this.yBtn;
            myButton3.yViewPort = i13;
            myButton3.yBtn = i13;
        }
        show();
    }

    public void startInputDlg(String text2, int typeInput, MyCommand cmdAgree, boolean isCancel) {
        startInputDlg(text2, typeInput, cmdAgree, isCancel, 300, false, -1999);
    }

    public void show() {
        CanvasNinja.currentDialog = this;
    }

    public void update() {
        int i = 0;
        while (true) {
            MyButton[] myButtonArr = this.btns;
            if (i < myButtonArr.length) {
                myButtonArr[i].update();
                i++;
            } else {
                this.tfInput.update();
                return;
            }
        }
    }

    public void updateKey() {
        this.tfInput.updateFocus();
        updatePointer();
    }

    public void keyTyped() {
        this.tfInput.keyTyped();
    }

    public void keyPress(int keyCode) {
        this.tfInput.keyPressed(keyCode);
        super.keyPress(keyCode);
    }

    public void paint(mGraphics g) {
        CanvasNinja.resetTrans(g);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        int i = 2;
        if (this.text != null) {
            int i2 = 0;
            while (true) {
                String[] strArr = this.text;
                if (i2 >= strArr.length) {
                    break;
                }
                mFont mfont = mFont.tahoma_brown_focus_dv;
                String str = strArr[i2];
                int i3 = this.x + (this.w / 2);
                int i4 = this.y;
                int i5 = this.margin;
                mfont.drawString(g, str, i3, i4 + (i5 * 2) + (i5 * 2) + (mfont.getHeight() * i2), 2);
                i2++;
            }
        }
        SupportPaint supportPaint = CanvasNinja.paintz;
        FrameImage frameImage = LoadDataManager.frameInput;
        TField tField = this.tfInput;
        int i6 = tField.x;
        int i7 = this.margin;
        supportPaint.paintTagFrame(g, frameImage, i6 - (i7 * 2), tField.y, tField.width + (i7 * 4), tField.isFocused(), 0, false, 0, true);
        TField tField2 = this.tfInput;
        if (!this.isMiddle) {
            i = 0;
        }
        tField2.painTextInputNoneBg(g, i);
        int i8 = 0;
        while (true) {
            MyButton[] myButtonArr = this.btns;
            if (i8 < myButtonArr.length) {
                myButtonArr[i8].paint(g);
                i8++;
            } else {
                super.paint(g);
                return;
            }
        }
    }

    public void updatePointer() {
        int i = 0;
        while (true) {
            MyButton[] myButtonArr = this.btns;
            if (i < myButtonArr.length) {
                myButtonArr[i].updatePointer();
                i++;
            } else {
                return;
            }
        }
    }

    public void commandTab(int index, int subIndex) {
        TField tField;
        TField tField2;
        TField tField3;
        Res.outz("Vào commandTab InputDialog:" + index);
        switch (index) {
            case 0:
                close();
                return;
            case 1:
                TField tField4 = this.tfInput;
                if (tField4 != null && !tField4.getText().equalsIgnoreCase("")) {
                    SendMessage.gI().sendOTP(subIndex, this.tfInput.getText());
                    return;
                }
                return;
            case 3:
                TField tField5 = this.tfInput;
                if (tField5 != null) {
                    tField5.getText().equalsIgnoreCase("YES");
                    return;
                }
                return;
            case 4:
                TField tField6 = this.tfInput;
                if (tField6 != null && !tField6.getText().equalsIgnoreCase("")) {
                    Res.outz("đổi tên");
                    return;
                }
                return;
            case 5:
                TField tField7 = this.tfInput;
                if (tField7 != null) {
                    tField7.getText().equalsIgnoreCase("");
                    return;
                }
                return;
            case 6:
                if (subIndex != -1 && (tField = this.tfInput) != null) {
                    String text2 = tField.getText();
                    int num = Res.convertStringToNumber(text2);
                    if (text2.equalsIgnoreCase("") || num <= 0) {
                        CanvasNinja.startOKDlg(SupportTranslate.getTextLangue("PLAESE_INPUT_NUMER"));
                        return;
                    }
                    Res.outz(2, "Vứt vật phẩm ở index:" + subIndex);
                    SendMessage.gI().removeItemInventory((byte) 3, subIndex, num);
                    close();
                    return;
                }
                return;
            case 7:
                if (subIndex != -1 && (tField2 = this.tfInput) != null) {
                    String text3 = tField2.getText();
                    int num2 = Res.convertStringToNumber(text3);
                    if (text3.equalsIgnoreCase("") || num2 <= 0) {
                        CanvasNinja.startOKDlg(SupportTranslate.getTextLangue("PLAESE_INPUT_NUMER"));
                        return;
                    }
                    Res.outz(2, "Cất vào kho vật phẩm ở index:" + subIndex);
                    SendMessage.gI().moveItemToWareHouse(subIndex, num2);
                    close();
                    return;
                }
                return;
            case 8:
                if (subIndex != -1 && (tField3 = this.tfInput) != null) {
                    String text4 = tField3.getText();
                    int num3 = Res.convertStringToNumber(text4);
                    if (text4.equalsIgnoreCase("") || num3 <= 0) {
                        CanvasNinja.startOKDlg(SupportTranslate.getTextLangue("PLAESE_INPUT_NUMER"));
                        return;
                    }
                    Res.outz(2, "Lấy từ kho ra vật phẩm ở index:" + subIndex);
                    SendMessage.gI().moveWareHouseToInventory(subIndex, num3);
                    close();
                    return;
                }
                return;
            case 10:
                for (int i = 0; i < MapScr.myButtonSkills.size(); i++) {
                    if (i != subIndex && MapScr.myButtonSkills.get(i).keySkill == this.tfInput.keyCodeInputKey) {
                        MapScr.myButtonSkills.get(i).keySkill = -1999;
                    }
                }
                int i2 = this.tfInput.keyCodeInputKey;
                MapScr.myButtonSkills.get(subIndex).keySkill = i2;
                if (i2 != -1999) {
                    Res.outz("gán sub:" + subIndex);
                    int[] keySkill = new int[7];
                    keySkill[0] = keySkill.length;
                    for (int i3 = 0; i3 < keySkill.length - 1; i3++) {
                        keySkill[i3 + 1] = -1999;
                        if (i3 < MapScr.myButtonSkills.size()) {
                            keySkill[i3 + 1] = MapScr.myButtonSkills.get(i3).keySkill;
                        }
                    }
                    byte[] data = Res.getByteArrayFromIntArray(keySkill);
                    Rms.saveRMS(Rms.RMS_KEY_SKILL + Player.myCharz().charID, data);
                    close();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void close() {
        CanvasNinja.endDlg();
    }
}
