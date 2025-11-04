package ninjawar.screen.quest;

import java.util.HashMap;
import java.util.Map;
import ninjawar.input.MyButton;
import ninjawar.message.SendMessage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;

public class PlayerTalkScreen extends QuestScreen {
    public static mFont fontPaint = mFont.tahoma_7;
    public static mFont fontPaintName = mFont.tahoma_7b_green;
    public static PlayerTalkScreen instance;
    MyButton[] btns;
    public Player charFocus;
    HashMap<String, String> charInfos = new HashMap<>();
    public int hChar;
    int hOneLine = fontPaint.getHeightMore();
    public int hPopupL;
    public int hPopupR;
    boolean isContinue = false;
    boolean isNextContent = false;
    boolean isPaintNextPointer = true;
    private long lastTimeClicked = 0;
    int marginTagNameNPC = 10;
    int maxContent = 0;
    int maxH = 0;
    int maxNumLine = 5;
    int minH = 120;
    int minW = 300;
    String nameNpc = "";
    int sizeTagNameNPC;
    int step = 0;
    int stepContent = 0;
    public int wContentR;
    public int wPopupL;
    public int wPopupR;
    int xAnimation;
    int xBtnFirst;
    public int xChar;
    public int xContentR;
    int xFirstInfo;
    public int xNameNPC;
    public int[] xNext;
    public int xPopupL;
    public int xPopupR;
    int xTemp = 0;
    int yBtnFirst;
    public int yChar;
    int yFirstInfo;
    public int yNameNPC;
    public int yPopupL;
    public int yPopupR;

    public static PlayerTalkScreen gI() {
        PlayerTalkScreen playerTalkScreen = instance;
        return playerTalkScreen != null ? playerTalkScreen : new PlayerTalkScreen();
    }

    public void commandTab(int index, int subIndex) {
        Res.outz("Vào cmd tab trong npctalk screen:" + index);
        switch (index) {
            case -1:
                CanvasNinja.endQuestScreen();
                return;
            default:
                SendMessage.gI().requestMenuPvP(this.charFocus.charID, index);
                CanvasNinja.endQuestScreen();
                return;
        }
    }

    public void update() {
        if (CanvasNinja.gameTick % 5 == 0) {
            int i = this.xAnimation + 1;
            this.xAnimation = i;
            if (i > 1) {
                this.xAnimation = 0;
            }
        }
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null && myButtonArr.length > 0) {
            int i2 = 0;
            while (true) {
                MyButton[] myButtonArr2 = this.btns;
                if (i2 < myButtonArr2.length) {
                    myButtonArr2[i2].update();
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    public void updatePointer() {
        if (CanvasNinja.isPointerRelease() && this.isNextContent && this.isPaintNextPointer && this.stepContent <= this.maxContent && CanvasNinja.isPoint(((((this.xPopupR + this.wPopupR) - LoadDataManager.imgNextPreviousNPCTalk[0].getRWidth()) - 10) + this.xAnimation) - 5, (((this.y + this.h) - (this.margin * 6)) - 5) - 5, LoadDataManager.imgNextPreviousNPCTalk[0].getRWidth() + 5, LoadDataManager.imgNextPreviousNPCTalk[0].getRHeight() + 5)) {
            CanvasNinja.clearAllPointer();
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.lastTimeClicked >= 500) {
                int i = this.stepContent + 1;
                this.stepContent = i;
                this.lastTimeClicked = currentTime;
                int i2 = this.maxContent;
                if (i > i2) {
                    this.stepContent = i2;
                    CanvasNinja.endQuestScreen();
                    return;
                }
            }
        }
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null && myButtonArr.length > 0) {
            int i3 = 0;
            while (true) {
                MyButton[] myButtonArr2 = this.btns;
                if (i3 < myButtonArr2.length) {
                    myButtonArr2[i3].updatePointer();
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    public void paint(mGraphics g) {
        CanvasNinja.paintz.paintSingleBorderFrame(LoadDataManager.frameBgInfo, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 100, true, g);
        this.charFocus.paintHeadStatic(g, this.xChar, this.yChar);
        CanvasNinja.resetTrans(g);
        Image image = LoadDataManager.imgHeIconPlayer[this.charFocus.classId];
        int i = this.xContentR;
        int i2 = this.margin;
        g.drawImage(image, (float) (i + (i2 * 5)), (float) (this.yNameNPC + i2));
        mFont mfont = fontPaintName;
        String str = this.charFocus.cName;
        int i3 = this.xContentR;
        int i4 = this.margin;
        mfont.drawString(g, str, (i4 * 8) + i3, this.yNameNPC + i4, 0);
        int i5 = this.xContentR;
        int i6 = this.margin;
        mFont.tahoma_7_white.drawString(g, "Lv: " + this.charFocus.clevel, (i6 * 5) + i5, this.yNameNPC + (i6 * 4), 0);
        Image image2 = LoadDataManager.imgDotInfo;
        g.drawImage(image2, (float) (this.x + ((this.w - image2.getRWidth()) / 2)), (float) (this.yFirstInfo - (this.margin * 2)));
        int index = 0;
        for (Map.Entry<String, String> entry : this.charInfos.entrySet()) {
            mFont.tahoma_7_yellow.drawString(g, entry.getKey() + ": ", this.xFirstInfo, this.yFirstInfo + ((mFont.tahoma_7_yellow.getHeight() + this.margin) * index));
            mFont.tahoma_7_white.drawString(g, entry.getValue(), this.xFirstInfo + mFont.tahoma_7_yellow.getWidth(entry.getKey() + ": "), this.yFirstInfo + ((mFont.tahoma_7_yellow.getHeight() + this.margin) * index));
            index++;
        }
        Image image3 = LoadDataManager.imgDotInfo;
        g.drawImage(image3, (float) (this.x + ((this.w - image3.getRWidth()) / 2)), (float) (this.yBtnFirst - (this.margin * 3)));
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null && myButtonArr.length != 0) {
            this.isPaintNextPointer = false;
            int i7 = 0;
            while (true) {
                MyButton[] myButtonArr2 = this.btns;
                if (i7 < myButtonArr2.length) {
                    myButtonArr2[i7].paint(g);
                    i7++;
                } else {
                    return;
                }
            }
        }
    }

    public void init(HashMap<String, String> charInfos2, Player charFocus2, MyButton[] btns2) {
        this.btns = btns2;
        this.charInfos = charInfos2;
        this.isNextContent = true;
        this.charFocus = charFocus2;
        this.w = 175;
        int i = CanvasNinja.w;
        if (175 > i) {
            this.w = (i * 90) / 100;
        }
        this.h = 160;
        int i2 = this.margin;
        this.y = (CanvasNinja.h - 160) - i2;
        this.x = (CanvasNinja.w - this.w) / 2;
        if (charFocus2 != null) {
            String str = charFocus2.cName;
            this.nameNpc = str;
            this.minH = this.hChar + ((int) LoadDataManager.frameTagNameNPC.frameHeight) + 10 + (i2 * 12);
            int width = fontPaint.getWidth(str) + (this.marginTagNameNPC * 2);
            this.sizeTagNameNPC = width;
            int i3 = this.margin;
            int sizeLeft = width + (i3 * 4);
            this.wPopupL = sizeLeft;
            int i4 = this.x;
            this.xNameNPC = i4 + i3;
            int i5 = i4 + i3;
            this.xPopupL = i5;
            this.xPopupR = i5 + sizeLeft + i3;
            int i6 = this.w;
            this.wPopupR = (i6 - sizeLeft) - (i3 * 3);
            this.wContentR = ((i6 - (LoadDataManager.imgNextPreviousNPCTalk[0].getRWidth() * 2)) - (this.margin * 4)) - (2 * 2);
            this.xContentR = this.xPopupL + LoadDataManager.imgNextPreviousNPCTalk[0].getRWidth() + 2 + (this.margin * 3);
            init();
            if (this.maxNumLine == 0) {
                this.maxNumLine = 1;
            }
            this.step = 0;
            int i7 = CanvasNinja.h / 4;
            this.y = i7;
            int height = i7 + this.margin + fontPaintName.getHeight();
            int i8 = this.margin;
            int i9 = height + i8;
            this.yPopupR = i9;
            this.yPopupL = i9;
            this.yNameNPC = this.y + ((this.marginBT + i8) * 2);
            int height2 = ((this.h - (i8 * 2)) - 3) - fontPaintName.getHeight();
            this.hPopupR = height2;
            this.hPopupL = height2;
            if (btns2 != null && btns2.length > 0) {
                int i10 = this.h;
                int i11 = this.margin;
                this.hPopupR = (((i10 - (i11 * 2)) - (i11 * 2)) - btns2[0].h) - fontPaintName.getHeight();
            }
            this.xChar = this.x + charFocus2.getW();
            this.yChar = this.y + charFocus2.getH() + this.margin;
            this.xNext = new int[]{this.xPopupR + 2};
        }
        initBtn();
        this.xFirstInfo = this.xBtnFirst + 3;
        this.yFirstInfo = (this.yBtnFirst - (this.margin * 3)) - (mFont.tahoma_7_yellow.getHeightMore() * charInfos2.size());
        if (this.stepContent != this.maxContent) {
            this.btns = null;
        }
    }

    public void startNPCTalkQuest(HashMap<String, String> charInfos2, Player charFocus2, MyButton[] btns2) {
        init(charInfos2, charFocus2, btns2);
        super.switchToMe();
    }

    private void initBtn() {
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null && myButtonArr.length > 0) {
            int round = Res.round((((float) myButtonArr.length) * 1.0f) / 2.0f);
            int i = this.btns[0].h;
            int i2 = this.margin;
            this.xBtnFirst = this.x + i2;
            this.yBtnFirst = (this.y + this.h) - ((round * (i + i2)) - i2);
            int i3 = 0;
            while (true) {
                MyButton[] myButtonArr2 = this.btns;
                if (i3 < myButtonArr2.length) {
                    MyButton myButton = myButtonArr2[i3];
                    myButton.fontPaint = mFont.tahoma_7_brown;
                    if (i3 % 2 == 0) {
                        myButton.xBtn = this.xBtnFirst;
                        int i4 = this.yBtnFirst;
                        int i5 = myButtonArr2[0].h;
                        int i6 = this.margin;
                        int i7 = (i4 + ((i3 / 2) * (i5 + i6))) - i6;
                        myButton.yViewPort = i7;
                        myButton.yBtn = i7;
                    } else {
                        int i8 = this.xBtnFirst;
                        int i9 = this.margin;
                        MyButton myButton2 = myButtonArr2[0];
                        myButton.xBtn = i8 + i9 + myButton2.w;
                        int i10 = (this.yBtnFirst + ((i3 / 2) * (myButton2.h + i9))) - i9;
                        myButton.yViewPort = i10;
                        myButton.yBtn = i10;
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    private void init() {
    }

    public void updateKey() {
        updatePointer();
    }
}
