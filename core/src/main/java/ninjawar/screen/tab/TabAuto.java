package ninjawar.screen.tab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.HashMap;
import ninjawar.input.MyButton;
import ninjawar.input.MyButtonSkill;
import ninjawar.input.MyCheckBox;
import ninjawar.input.TField;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.model.Skill;
import ninjawar.mylib.Atlas;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSprite;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import ninjawar.template.SkillTemplate;

public class TabAuto extends TabScr {
    public static TabAuto me;
    public MyCheckBox[] checkBoxs;
    mFont fontTextNau = mFont.fontTextNau;
    mFont fontTextTrang = mFont.fontTextTrang;
    mFont fontTextXam = mFont.fontTextXam;
    int hTitle;
    public int indexSelected = -1;
    int marginLeftRight;
    int marginOneTab;
    TField[] tf;
    String title = "";
    Skill tmpSkill;
    int transOneTab;
    int wTitle;
    int xTitle;
    int yTitle;

    public static TabAuto gI() {
        if (me == null) {
            me = new TabAuto();
        }
        return me;
    }

    public void paint(mGraphics g) {
        mSprite msprite;
        Atlas atlas;
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(mgraphics, LoadDataManager.frameXam3, (float) (this.x + 20), (float) (this.y + 20), (float) (this.w - 40), (float) (this.h - 80), 11, false);
        CanvasNinja.paintz.paintTagFrame(mgraphics, LoadDataManager.frameTitle2, this.xTitle, this.yTitle, this.wTitle, false, 0, false);
        TabScr.fontPaintTile.drawString(g, this.title, (this.wTitle / 2) + this.xTitle, (this.hTitle / 2) + this.yTitle, 3);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.lvInfo, this.x + 20 + 5, this.y + 20 + 5, 100, false, false);
        mFont mfont = this.fontTextTrang;
        int i = this.margin;
        mfont.drawString(g, "Tự dùng vật phẩm", (i * 2) + this.x + 20 + 5, this.y + 20 + 5 + i, 0);
        mFont mfont2 = this.fontTextNau;
        int i2 = this.margin;
        mfont2.drawString(g, "Hồi phục sinh lực: ", (i2 * 2) + this.x + 20 + 5, this.y + 40 + 5 + i2, 0);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameInputTf, (this.margin * 2) + this.x + 250 + 5, this.y + 40 + 5, 100, false, 0, false, 0, true);
        mFont mfont3 = this.fontTextNau;
        int i3 = this.margin;
        mfont3.drawString(g, "Hồi phục chakra: ", (i3 * 2) + this.x + 20 + 5, this.y + 60 + 5 + i3, 0);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameInputTf, (this.margin * 2) + this.x + 250 + 5, this.y + 60 + 5, 100, false, 0, false, 0, true);
        mFont mfont4 = this.fontTextXam;
        int i4 = this.margin;
        mfont4.drawString(g, "Về thành khi HP dưới: ", (i4 * 2) + this.x + 20 + 5, this.y + 80 + 5 + i4, 0);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameInputTf, (this.margin * 2) + this.x + 250 + 5, this.y + 80 + 5, 100, true, 0, false, 0, true);
        mFont mfont5 = this.fontTextXam;
        int i5 = this.margin;
        mfont5.drawString(g, "Vật phẩm tăng kinh nghiệm: ", (i5 * 2) + this.x + 20 + 5, this.y + 100 + 5 + i5, 0);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameInputTf, (this.margin * 2) + this.x + 250 + 5, this.y + 100 + 5, 100, true, 0, false, 0, true);
        for (int i6 = 0; i6 < 4; i6++) {
            TField tField = this.tf[i6];
            tField.x = this.x + 250 + 5 + (this.margin * 2);
            tField.y = this.y + 40 + 5 + (i6 * 20);
            tField.painTextInputNoneBg(g, 2);
            if (!this.tf[i6].text.equals("")) {
                String str = this.tf[i6].text;
                if (str.charAt(str.length() - 1) != '%') {
                    TField tField2 = this.tf[i6];
                    tField2.text = this.tf[i6].text + "%";
                }
            }
        }
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.lvInfo, this.x + 20 + 5, this.y + 120 + 5, 55, false, false);
        mFont mfont6 = this.fontTextTrang;
        int i7 = this.margin;
        mfont6.drawString(g, "Kỹ năng", (i7 * 2) + this.x + 20 + 5, this.y + 120 + 5 + i7, 0);
        for (int i8 = 0; i8 < 4; i8++) {
            Image image = LoadDataManager.imgBgIconSkill;
            g.drawImage(image, ((this.x + 80) + (i8 * 80)) - (image.getRWidth() / 2), ((this.y + 159) + 5) - (LoadDataManager.imgBgIconSkill.getRHeight() / 2), true);
            Skill skill = (Skill) Player.myCharz().vSkillFight.get(i8 + 1);
            this.tmpSkill = skill;
            if (skill != null) {
                SkillTemplate skillTemplate = skill.template;
                if (skillTemplate.iconId == -1 || (atlas = MyButtonSkill.atlasSkill) == null) {
                    msprite = null;
                } else {
                    HashMap<String, Sprite> hashMap = atlas.sprites;
                    msprite = new mSprite(hashMap.get(this.tmpSkill.template.iconId + ""));
                }
                skillTemplate.imgSkillTemplate = msprite;
                g.drawImage(LoadDataManager.imgBgIconSkillBd, (float) ((((this.x + 80) + (i8 * 80)) - (LoadDataManager.imgBgIconSkill.getRWidth() / 2)) - 3), (float) ((((this.y + 159) + 5) - (LoadDataManager.imgBgIconSkill.getRHeight() / 2)) - 3));
                g.drawSprite(this.tmpSkill.template.imgSkillTemplate, (float) (((this.x + 80) + (i8 * 80)) - (LoadDataManager.imgBgIconSkill.getRWidth() / 2)), (float) (((this.y + 159) + 5) - (LoadDataManager.imgBgIconSkill.getRHeight() / 2)), true);
            } else {
                g.drawImage(LoadDataManager.imgBgIconSkillBd, (float) ((((this.x + 80) + (i8 * 80)) - (LoadDataManager.imgBgIconSkill.getRWidth() / 2)) - 3), (float) ((((this.y + 159) + 5) - (LoadDataManager.imgBgIconSkill.getRHeight() / 2)) - 3));
                g.drawImage(LoadDataManager.imgSKillDefault, (float) (((this.x + 80) + (i8 * 80)) - (LoadDataManager.imgBgIconSkill.getRWidth() / 2)), (float) (((this.y + 159) + 5) - (LoadDataManager.imgBgIconSkill.getRHeight() / 2)));
            }
        }
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.lvInfo, this.x + 20 + 5, this.y + 180 + 5, 85, false, false);
        mFont mfont7 = this.fontTextTrang;
        int i9 = this.margin;
        mfont7.drawString(g, "Nhặt vật phẩm", (i9 * 2) + this.x + 20 + 5, this.y + 180 + 5 + i9, 0);
        int i10 = 0;
        while (true) {
            MyCheckBox[] myCheckBoxArr = this.checkBoxs;
            if (i10 < myCheckBoxArr.length) {
                MyCheckBox myCheckBox = myCheckBoxArr[i10];
                if (myCheckBox != null) {
                    myCheckBox.paint(g);
                }
                i10++;
            } else {
                mFont mfont8 = this.fontTextNau;
                int i11 = this.margin;
                mfont8.drawString(g, "Không nhặt gì", (i11 * 6) + this.x + 20 + 5, this.y + 200 + 5 + i11, 0);
                mFont mfont9 = this.fontTextNau;
                int i12 = this.margin;
                mfont9.drawString(g, "Nhặt vàng", (i12 * 6) + this.x + 150 + 5, this.y + 200 + 5 + i12, 0);
                mFont mfont10 = this.fontTextNau;
                int i13 = this.margin;
                mfont10.drawString(g, "Nhặt tất cả", (i13 * 6) + this.x + 250 + 5, this.y + 200 + 5 + i13, 0);
                mFont mfont11 = this.fontTextXam;
                int i14 = this.margin;
                mfont11.drawString(g, "Tự dùng v.p tăng kinh nghiệm ", (i14 * 6) + this.x + 20 + 5, this.y + 220 + 5 + i14, 0);
                mFont mfont12 = this.fontTextXam;
                int i15 = this.margin;
                mfont12.drawString(g, "Tự sửa đồ ", (i15 * 6) + this.x + 250 + 5, this.y + 220 + 5 + i15, 0);
                paintButton(g);
                CanvasNinja.resetTrans(g);
                this.cmdClose.paintIconOnly(g);
                return;
            }
        }
    }

    public void init() {
        this.margin = 5;
        this.marginLeftRight = 5 * 2;
        this.title = "thiết lập auto".toUpperCase();
        this.marginOneTab = this.margin;
        this.transOneTab = LoadDataManager.imgNenCharNone.getRWidth() + this.marginOneTab;
        int wNenCam = (LoadDataManager.imgNenCharNone.getRWidth() * 5) + (this.marginOneTab * 6);
        int hNenCam = LoadDataManager.imgNenCharNone.getRHeight() + (this.margin * 4);
        MyButton[] myButtonArr = new MyButton[2];
        this.btns = myButtonArr;
        FrameImage[] frameImageArr = LoadDataManager.myButtons;
        myButtonArr[0] = new MyButton(frameImageArr[1], frameImageArr[3], 55, 0, "Nâp cấp AUTO", 0, 0, new MyCommand("", 1, this));
        MyButton[] myButtonArr2 = this.btns;
        FrameImage[] frameImageArr2 = LoadDataManager.myButtons;
        myButtonArr2[1] = new MyButton(frameImageArr2[0], frameImageArr2[3], 55, 0, "Lưu cài đặt", 0, 0, new MyCommand("", 2, this));
        this.w = 400;
        int i = CanvasNinja.w;
        if (400 > i) {
            this.w = i - (this.margin * 5);
        }
        this.h = 310;
        int i2 = this.h;
        int i3 = CanvasNinja.h;
        if (i2 > i3) {
            this.h = i3 - (this.margin * 2);
        }
        initStart();
        MyCheckBox[] myCheckBoxArr = new MyCheckBox[5];
        this.checkBoxs = myCheckBoxArr;
        int i4 = this.margin * 2;
        float f = LoadDataManager.frameCheckBox.frameHeight;
        myCheckBoxArr[0] = new MyCheckBox(i4 + this.x + 20 + 5, this.y + 200 + 5, (int) f, (int) f, false);
        MyCheckBox[] myCheckBoxArr2 = this.checkBoxs;
        int i5 = (this.margin * 2) + this.x + 150 + 5;
        int i6 = this.y + 200 + 5;
        float f2 = LoadDataManager.frameCheckBox.frameHeight;
        myCheckBoxArr2[1] = new MyCheckBox(i5, i6, (int) f2, (int) f2, false);
        MyCheckBox[] myCheckBoxArr3 = this.checkBoxs;
        int i7 = (this.margin * 2) + this.x + 250 + 5;
        int i8 = this.y + 200 + 5;
        float f3 = LoadDataManager.frameCheckBox.frameHeight;
        myCheckBoxArr3[2] = new MyCheckBox(i7, i8, (int) f3, (int) f3, false);
        MyCheckBox[] myCheckBoxArr4 = this.checkBoxs;
        int i9 = (this.margin * 2) + this.x + 20 + 5;
        int i10 = this.y + 220 + 5;
        float f4 = LoadDataManager.frameCheckBox.frameHeight;
        myCheckBoxArr4[3] = new MyCheckBox(i9, i10, (int) f4, (int) f4, false);
        MyCheckBox[] myCheckBoxArr5 = this.checkBoxs;
        int i11 = (this.margin * 2) + this.x + 250 + 5;
        int i12 = this.y + 220 + 5;
        float f5 = LoadDataManager.frameCheckBox.frameHeight;
        myCheckBoxArr5[4] = new MyCheckBox(i11, i12, (int) f5, (int) f5, false);
        this.hTitle = (int) LoadDataManager.frameTitle.frameHeight;
        int width = TabScr.fontPaintTile.getWidth(this.title) + (this.margin * 8);
        this.wTitle = width;
        int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(8, width, LoadDataManager.frameTitle);
        this.wTitle = fixSizeTagFrameUp;
        this.xTitle = this.x + ((this.w - fixSizeTagFrameUp) / 2);
        this.yTitle = this.y - (this.hTitle / 2);
        this.arrH = new int[]{hNenCam, LoadDataManager.imgNenCharNone.getRHeight()};
        this.arrW = new int[]{wNenCam, LoadDataManager.imgNenCharNone.getRWidth()};
        int i13 = this.x;
        int xStartLeft = i13 + 7 + this.marginLeftRight;
        this.arrX = new int[]{xStartLeft, this.marginOneTab + xStartLeft};
        int i14 = this.y;
        int i15 = i14 + 7 + (((int) LoadDataManager.frameTitle2.frameHeight) / 2);
        int i16 = this.margin;
        int yStart = i15 + (i16 * 2);
        this.arrY = new int[]{yStart, (i16 * 2) + yStart};
        MyButton myButton = this.btns[0];
        myButton.setXY(i13 + 80, ((i14 + this.h) - 20) - myButton.h);
        MyButton[] myButtonArr3 = this.btns;
        MyButton myButton2 = myButtonArr3[1];
        MyButton myButton3 = myButtonArr3[0];
        myButton2.setXY(this.x + 100 + myButton3.w + (this.margin * 2), ((this.y + this.h) - 20) - myButton3.h);
        this.tf = new TField[4];
        for (int i17 = 0; i17 < 4; i17++) {
            this.tf[i17] = new TField();
            TField.resetTField();
            TField tField = this.tf[i17];
            tField.isSendChat = false;
            if (!NinjaMidlet.isUseIOSSpec) {
                tField.isKeyTyped = true;
            }
            tField.width = 90;
            tField.height = (int) LoadDataManager.frameInputTf.frameHeight;
            tField.setMaxTextLenght(3);
            this.tf[i17].setFocus(false);
            this.tf[i17].setIputType(1);
        }
    }

    public void unFocusAllTf() {
        for (int i = 0; i < 4; i++) {
            this.tf[i].setFocus(false);
        }
    }

    public void update() {
        if (NinjaMidlet.isAndroid && !NinjaMidlet.isUseIOSSpec) {
            for (int i = 0; i < 4; i++) {
                if (this.tf[i].isFocused()) {
                    unFocusAllTf();
                    this.tf[i].setFocus(true);
                    Res.outz("CHAY VO DAY");
                    Gdx.input.setOnscreenKeyboardVisible(true, Input.OnscreenKeyboardType.Default);
                }
                if (!this.tf[i].isFocused()) {
                    Gdx.input.setOnscreenKeyboardVisible(false);
                }
            }
        }
    }

    public void updateKey() {
        this.cmdClose.updateIconOnly();
        updatePointerButton();
        if (CanvasNinja.isPointerRelease()) {
            int i = 0;
            while (true) {
                if (i >= 3) {
                    break;
                }
                MyCheckBox myCheckBox = this.checkBoxs[i];
                if (CanvasNinja.isPointerHoldOrHover(myCheckBox.x, myCheckBox.y, myCheckBox.w, myCheckBox.h)) {
                    int j = 0;
                    while (j < 3) {
                        this.checkBoxs[j].isChecked = i == j;
                        j++;
                    }
                } else {
                    i++;
                }
            }
        }
        updateKeyInputChat();
    }

    public void updateKeyInputChat() {
        for (int i = 0; i < 4; i++) {
            TField tField = this.tf[i];
            if (!CanvasNinja.isPointerHoldOrHover(tField.x, tField.y, tField.width, tField.height) && !this.tf[i].isFocused()) {
                this.tf[i].isFocused();
            }
            this.tf[i].update();
            this.tf[i].updateFocus();
        }
    }

    public void show() {
        init();
        showTab();
    }

    public void keyPress(int keyCode) {
        for (int i = 0; i < 4; i++) {
            if (this.tf[i].isFocused()) {
                TField tField = this.tf[i];
                tField.isChat = false;
                tField.keyPressed(keyCode);
                return;
            }
        }
    }

    public void keyTyped() {
        for (int i = 0; i < 4; i++) {
            if (this.tf[i].isFocused()) {
                this.tf[i].keyTyped();
                return;
            }
        }
    }

    public void commandTab(int index, int subIndex) {
        super.commandTab(index, subIndex);
        switch (index) {
            case 1:
                SendMessage.gI().requestPremiumAuto();
                return;
            case 2:
                Res.outz("Thoát");
                return;
            default:
                return;
        }
    }
}
