package ninjawar.screen.quest;

import ninjawar.input.MyButton;
import ninjawar.message.SendMessage;
import ninjawar.model.DetailText;
import ninjawar.model.MyCommand;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.VectorCustom;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.Res;
import ninjawar.object.mNPC;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;

public class NPCTalkQuestScreen extends QuestScreen {
    public static mFont fontPaint = mFont.tahoma_7;
    public static mFont fontPaintName = mFont.tahoma_brown_dv;
    public static NPCTalkQuestScreen instance;
    public MyButton[] btns;
    public MyCommand cmdClose;
    public String[] contentArr;
    public String[] contentConvert;
    public DetailText[] detailTextPaint;
    DetailText detailTextTemp;
    public int hNPC;
    int hOneLine = fontPaint.getHeightMore();
    public int hPopupL;
    public int hPopupR;
    byte idScreen = -1;
    int indexBtnSelected = -1;
    boolean isContinue = false;
    boolean isNextContent = false;
    boolean isPaintBtnClose = false;
    boolean isPaintNextPointer = true;
    boolean isXaPhu = false;
    private long lastTimeClicked = 0;
    int marginTagNameNPC = 10;
    int maxContent = 0;
    int maxH = 0;
    int maxNumLine = 5;
    int maxStep;
    int minW = 300;
    int minH =  120;
    String nameNpc = "";
    public mNPC npc;
    int sizeTagNameNPC;
    byte[] status;
    int step = 0;
    int stepContent = 0;
    public VectorCustom vecContent;
    public int wContentR;
    public int wPopupL;
    public int wPopupR;
    int xAnimation;
    public int xContentR;
    public int xNPC;
    public int xNameNPC;
    public int[] xNext;
    public int xPopupL;
    public int xPopupR;
    int xTemp = 0;
    public int yNPC;
    public int yNameNPC;
    public int yPopupL;
    public int yPopupR;

    public static NPCTalkQuestScreen gI() {
        NPCTalkQuestScreen nPCTalkQuestScreen = instance;
        return nPCTalkQuestScreen != null ? nPCTalkQuestScreen : new NPCTalkQuestScreen();
    }

    public void commandTab(int index, int subIndex) {
        Res.outz("Vào cmd tab trong npctalk screen:" + index);
        switch (index) {
            case -1:
                CanvasNinja.endQuestScreen();
                return;
            case 1:
                Res.outz("Nhấn nút gửi npc id npc:" + this.npc.template.npcTemplateId);
                if (MapScr.gI().tutorial != null && MapScr.gI().tutorial.isStepThaoTacKho(1) && this.npc.template.npcTemplateId == 3 && subIndex == 1) {
                    MapScr.gI().tutorial.step = 2;
                }
                if (!this.isXaPhu) {
                    SendMessage.gI().requestTalkToEndNpc((short) this.npc.template.npcTemplateId, (byte) this.stepContent, (byte) 3, (byte) subIndex);
                    CanvasNinja.endQuestScreen();
                    return;
                }
                Res.outz("Tab xa phu");
                if (this.indexBtnSelected == subIndex) {
                    SendMessage.gI().requestNextScreen(this.idScreen, (byte) subIndex);
                    this.indexBtnSelected = -1;
                    CanvasNinja.endQuestScreen();
                    return;
                }
                this.indexBtnSelected = subIndex;
                return;
            default:
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
                    SendMessage.gI().requestTalkToEndNpc((short) this.npc.template.npcTemplateId, (byte) this.stepContent, (byte) 3, (byte) -1);
                    return;
                }
                startNPCTalkQuest(this.contentArr, this.npc, i, i == i2 ? this.btns : null);
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
        int marginMore;
        int xTemp2;
        String currentText;
        mGraphics mgraphics = g;
        int i = 0;
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        byte[] bArr = this.status;
        if (bArr == null) {
            this.npc.paintNPC(g, this.xNPC, this.yNPC, false, false);
        } else if (bArr[0] == -2) {
            this.npc.paintNPC(g, this.xNPC, this.yNPC, false, false);
        } else {
            this.cmdClose.paintIconOnly(mgraphics);
            this.isPaintBtnClose = true;
        }
        fontPaintName.drawString(g, this.npc.template.name, this.xContentR + 2, this.yNameNPC, 0);
        int i2 = 0;
        String currentText2 = "";
        while (i2 < this.vecContent.size()) {
            DetailText detailText = (DetailText) this.vecContent.elementAt(i2);
            this.detailTextTemp = detailText;
            int i3 = detailText.line;
            int i4 = this.step;
            int i5 = this.maxNumLine;
            if (i3 >= i4 * i5 && i3 <= ((i4 + 1) * i5) - 1) {
                while (i2 < this.vecContent.size() && this.detailTextTemp.line == ((DetailText) this.vecContent.elementAt(i2)).line) {
                    currentText2 = currentText2 + ((DetailText) this.vecContent.elementAt(i2)).text;
                    i2++;
                }
                int i6 = i2 - 1;
                if (this.detailTextTemp.line > 0) {
                    xTemp2 = -fontPaint.getWidth("");
                } else {
                    xTemp2 = 0;
                }
                if (currentText2.charAt(i) == 32) {
                    currentText2 = currentText2.substring(1);
                }
                if (this.detailTextTemp.fontText.getWidth(currentText2.trim()) < this.w - 55) {
                    mFont mfont = this.detailTextTemp.fontText;
                    String trim = currentText2.trim();
                    int i7 = this.xContentR + 2 + xTemp2;
                    int i8 = this.yPopupR;
                    int i9 = this.hPopupR;
                    int i10 = this.hOneLine;
                    int i11 = this.maxNumLine;
                    int i12 = i8 + ((i9 - (i10 * i11)) / 2);
                    DetailText detailText2 = this.detailTextTemp;
                    mfont.drawString(g, trim, i7, (i12 + (detailText2.line * (detailText2.h + this.margin))) - ((this.step * i11) * i10), 0);
                    currentText = "";
                } else {
                    int lastSpaceIndex = currentText2.lastIndexOf(32);
                    mFont mfont2 = this.detailTextTemp.fontText;
                    String substring = currentText2.substring(i, lastSpaceIndex + 1);
                    DetailText detailText3 = this.detailTextTemp;
                    float f = ((float) xTemp2) + ((float) (this.xContentR + 2)) + detailText3.wTextBefore;
                    int i13 = this.yPopupR;
                    int i14 = this.hPopupR;
                    int i15 = this.hOneLine;
                    int i16 = this.maxNumLine;
                    mfont2.drawString(g, substring, f, ((i13 + ((i14 - (i15 * i16)) / 2)) + (detailText3.line * (detailText3.h + this.margin))) - ((this.step * i16) * i15), 0);
                    currentText = currentText2.substring(lastSpaceIndex);
                }
                DetailText detailText4 = this.detailTextTemp;
                mFont mfont3 = detailText4.fontText;
                String str = detailText4.text;
                float f2 = ((float) (this.xContentR + 2)) + detailText4.wTextBefore + ((float) xTemp2);
                int i17 = this.yPopupR;
                int i18 = this.hPopupR;
                int i19 = this.hOneLine;
                int i20 = this.maxNumLine;
                mfont3.drawString(g, str, f2, ((i17 + ((i18 - (i19 * i20)) / 2)) + (detailText4.line * (detailText4.h + this.margin))) - ((this.step * i20) * i19), 0);
                currentText2 = currentText;
                i2 = i6;
            }
            i2++;
            i = 0;
        }
        mFont mfont4 = this.detailTextTemp.fontText;
        String trim2 = currentText2.trim();
        DetailText detailText5 = this.detailTextTemp;
        float f3 = ((float) this.xTemp) + ((float) (this.xContentR + 2)) + detailText5.wTextBefore;
        int i21 = this.yPopupR;
        int i22 = this.hPopupR;
        int i23 = this.hOneLine;
        int i24 = this.maxNumLine;
        mfont4.drawString(g, trim2, f3, ((i21 + ((i22 - (i23 * i24)) / 2)) + ((detailText5.line + 1) * (detailText5.h + this.margin))) - ((this.step * i24) * i23), 0);
        byte[] bArr2 = this.status;
        if (bArr2 == null || bArr2[0] == -2) {
            marginMore = 0;
        } else {
            marginMore = 20;
        }
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr == null || myButtonArr.length == 0) {
            this.isPaintNextPointer = true;
        } else {
            this.isPaintNextPointer = false;
            if (!this.isXaPhu) {
                int i25 = 0;
                while (true) {
                    MyButton[] myButtonArr2 = this.btns;
                    if (i25 >= myButtonArr2.length) {
                        break;
                    }
                    myButtonArr2[i25].paint(mgraphics);
                    i25++;
                }
            } else {
                int i26 = 0;
                while (true) {
                    MyButton[] myButtonArr3 = this.btns;
                    if (i26 < myButtonArr3.length) {
                        if (this.indexBtnSelected == i26) {
                            Image image = LoadDataManager.imgSelect;
                            MyButton myButton = myButtonArr3[i26];
                            mgraphics.drawImage(image, (float) ((myButton.xBtn - (this.margin * 3)) + marginMore), (float) myButton.yBtn);
                            mFont mfont5 = mFont.tahoma_7_orange_r;
                            MyButton myButton2 = this.btns[i26];
                            mfont5.drawString(mgraphics, myButton2.name, myButton2.xBtn + marginMore, myButton2.yBtn);
                        } else {
                            mFont mfont6 = mFont.tahoma_7_brown;
                            MyButton myButton3 = myButtonArr3[i26];
                            mfont6.drawString(mgraphics, myButton3.name, myButton3.xBtn + marginMore, myButton3.yBtn);
                        }
                        byte[] bArr3 = this.status;
                        if (bArr3 != null) {
                            switch (bArr3[i26]) {
                                case -1:
                                    mgraphics.drawImage(LoadDataManager.imgMapBlock, (float) (((this.x + this.w) - (this.margin * 10)) - marginMore), (float) (this.btns[i26].yBtn + 2));
                                    MyButton[] myButtonArr4 = this.btns;
                                    if (i26 >= myButtonArr4.length - 1) {
                                        break;
                                    } else {
                                        SupportPaint supportPaint = CanvasNinja.paintz;
                                        FrameImage frameImage = LoadDataManager.frameLineNgang;
                                        int i27 = myButtonArr4[i26].xBtn;
                                        int i28 = this.margin;
                                        supportPaint.paintTagFrame(g, frameImage, i27 + i28 + marginMore, myButtonArr4[i26 + 1].yBtn - 4, (((this.x + this.w) - (i28 * 10)) - i27) - (marginMore * 2), false);
                                        break;
                                    }
                                case 0:
                                    mgraphics.drawImage(LoadDataManager.imgMapTaoSong, (float) (((this.x + this.w) - (this.margin * 10)) - marginMore), (float) (this.btns[i26].yBtn + 2));
                                    MyButton[] myButtonArr5 = this.btns;
                                    if (i26 >= myButtonArr5.length - 1) {
                                        break;
                                    } else {
                                        SupportPaint supportPaint2 = CanvasNinja.paintz;
                                        FrameImage frameImage2 = LoadDataManager.frameLineNgang;
                                        int i29 = myButtonArr5[i26].xBtn;
                                        int i30 = this.margin;
                                        supportPaint2.paintTagFrame(g, frameImage2, i29 + i30 + marginMore, myButtonArr5[i26 + 1].yBtn - 4, (((this.x + this.w) - (i30 * 10)) - i29) - (marginMore * 2), false);
                                        break;
                                    }
                                case 1:
                                    mgraphics.drawImage(LoadDataManager.imgMapCanJoin, (float) (((this.x + this.w) - (this.margin * 10)) - marginMore), (float) (this.btns[i26].yBtn + 2));
                                    MyButton[] myButtonArr6 = this.btns;
                                    if (i26 >= myButtonArr6.length - 1) {
                                        break;
                                    } else {
                                        SupportPaint supportPaint3 = CanvasNinja.paintz;
                                        FrameImage frameImage3 = LoadDataManager.frameLineNgang;
                                        int i31 = myButtonArr6[i26].xBtn;
                                        int i32 = this.margin;
                                        supportPaint3.paintTagFrame(g, frameImage3, i31 + i32 + marginMore, myButtonArr6[i26 + 1].yBtn - 4, (((this.x + this.w) - (i32 * 10)) - i31) - (marginMore * 2), false);
                                        break;
                                    }
                            }
                        }
                        i26++;
                    }
                }
            }
        }
        if (this.isNextContent && this.stepContent <= this.maxContent && this.isPaintNextPointer) {
            Image image2 = LoadDataManager.imgNextPreviousNPCTalk[0];
            mgraphics.drawImage(image2, (float) ((((this.xPopupR + this.wPopupR) - image2.getRWidth()) - 10) + this.xAnimation), (float) (((this.y + this.h) - (this.margin * 6)) - 5));
        }
    }

    public void resize() {
        startNPCTalkQuest(this.contentArr, this.npc, this.stepContent, this.btns);
    }

    public void init(String[] content, mNPC npc2, int stepContent2, MyButton[] btns2) {
        this.indexBtnSelected = 0;
        this.btns = btns2;
        this.contentArr = content;
        this.stepContent = stepContent2;
        this.maxContent = content.length - 1;
        this.isNextContent = true;
        this.npc = npc2;
        this.w = 350;
        if (this.isXaPhu) {
            this.w = 300;
        }
        int i = this.w;
        int i2 = CanvasNinja.w;
        if (i > i2) {
            this.w = (i2 * 90) / 100;
        }
        this.h = 70;
        int i3 = this.margin;
        this.y = (CanvasNinja.h - 70) - i3;
        this.x = (CanvasNinja.w - this.w) / 2;
        if (npc2 != null) {
            this.nameNpc = npc2.template.name;
            this.minH = this.hNPC + ((int) LoadDataManager.frameTagNameNPC.frameHeight) + 10 + (i3 * 12);
            int width = fontPaint.getWidth(npc2.cName) + (this.marginTagNameNPC * 2);
            this.sizeTagNameNPC = width;
            int i4 = this.margin;
            int sizeLeft = width + (i4 * 4);
            this.wPopupL = sizeLeft;
            int i5 = this.x;
            this.xNameNPC = i5 + i4;
            int i6 = i5 + i4;
            this.xPopupL = i6;
            this.xPopupR = i6 + sizeLeft + i4;
            int i7 = this.w;
            this.wPopupR = (i7 - sizeLeft) - (i4 * 3);
            this.wContentR = ((i7 - (LoadDataManager.imgNextPreviousNPCTalk[0].getRWidth() * 2)) - (this.margin * 4)) - (2 * 2);
            this.xContentR = this.xPopupL + LoadDataManager.imgNextPreviousNPCTalk[0].getRWidth() + 2 + (this.margin * 3);
            this.vecContent = Res.formatStringFromServer(this.contentArr[stepContent2]);
            init();
            int sizeVec = this.contentConvert.length;
            int i8 = this.margin;
            int heightMore = (i8 * 6) + ((sizeVec - 1) * i8) + (fontPaint.getHeightMore() * sizeVec);
            int i9 = this.margin;
            int sumHContent = heightMore + (i9 * 4) + ((int) LoadDataManager.myButtons[0].frameHeight);
            if (sizeVec > this.maxNumLine) {
                this.maxNumLine = sizeVec;
                int heightMore2 = (i9 * 6) + ((sizeVec - 1) * i9) + (fontPaint.getHeightMore() * sizeVec) + (this.margin * 4) + ((int) LoadDataManager.myButtons[0].frameHeight);
                this.maxH = heightMore2;
                this.h = heightMore2;
                this.isContinue = true;
                int i10 = this.minH;
                if (heightMore2 < i10) {
                    this.h = i10;
                }
            } else {
                this.maxNumLine = sizeVec;
                this.h = sumHContent;
                int i11 = this.minH;
                if (sumHContent < i11) {
                    this.h = i11;
                }
            }
            if (this.maxNumLine == 0) {
                this.maxNumLine = 1;
            }
            int i12 = this.maxNumLine;
            int i13 = sizeVec % i12;
            int i14 = sizeVec / i12;
            if (i13 == 0) {
                i14--;
            }
            this.maxStep = i14;
            this.step = 0;
            int i15 = CanvasNinja.h;
            int i16 = this.h;
            int i17 = this.margin;
            this.y = (i15 - i16) - i17;
            int height = ((i16 - (i17 * 2)) - 3) - fontPaintName.getHeight();
            this.hPopupR = height;
            this.hPopupL = height;
            if (btns2 != null && btns2.length > 0) {
                int i18 = this.h;
                int i19 = this.margin;
                this.hPopupR = (((i18 - (i19 * 2)) - (i19 * 2)) - btns2[0].h) - fontPaintName.getHeight();
            }
            initBtn();
            int height2 = this.y + this.margin + fontPaintName.getHeight();
            int i20 = this.margin;
            int i21 = height2 + i20;
            this.yPopupR = i21;
            this.yPopupL = i21;
            int i22 = this.y;
            this.yNameNPC = ((this.marginBT + i20) * 2) + i22;
            this.xNPC = this.x + i20;
            this.yNPC = i22;
            this.xNext = new int[]{this.xPopupR + 2};
        }
        if (this.stepContent != this.maxContent) {
            this.btns = null;
        }
        MyCommand myCommand = new MyCommand("", -1, this);
        this.cmdClose = myCommand;
        myCommand.setPosPaint(((this.x + 1) + this.w) - LoadDataManager.imgClose.getRWidth(), this.y - 1, LoadDataManager.imgClose);
        this.cmdClose.isClose = true;
    }

    public void startNPCTalkQuest(String[] content, mNPC npc2, int stepContent2, MyButton[] btns2) {
        this.isXaPhu = false;
        init(content, npc2, stepContent2, btns2);
        super.switchToMe();
    }

    public void startXaPhuScr(byte idScreen2, String[] content, mNPC npc2, int stepContent2, MyButton[] btns2, byte[] status2) {
        this.idScreen = idScreen2;
        this.status = status2;
        this.isXaPhu = true;
        init(content, npc2, stepContent2, btns2);
        super.switchToMe();
    }

    private void initBtn() {
        int i;
        int i2;
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null && myButtonArr.length > 0) {
            if (!this.isXaPhu) {
                int sumWBtn = 0;
                int i3 = 0;
                while (true) {
                    MyButton[] myButtonArr2 = this.btns;
                    if (i3 >= myButtonArr2.length) {
                        break;
                    }
                    if (i3 == 0) {
                        i2 = myButtonArr2[i3].w;
                    } else {
                        i2 = myButtonArr2[i3].w + this.margin;
                    }
                    sumWBtn += i2;
                    i3++;
                }
                int xFirstBtn = this.x + ((this.w - sumWBtn) / 2);
                int i4 = 0;
                while (true) {
                    MyButton[] myButtonArr3 = this.btns;
                    if (i4 < myButtonArr3.length) {
                        MyButton myButton = myButtonArr3[i4];
                        myButton.xBtn = i4 == 0 ? xFirstBtn : myButtonArr3[i4 - 1].xBtn + myButtonArr3[i4 - 1].w + this.margin;
                        int i5 = ((this.y + this.h) - (this.margin * 3)) - myButtonArr3[0].h;
                        myButton.yViewPort = i5;
                        myButton.yBtn = i5;
                        i4++;
                    } else {
                        return;
                    }
                }
            } else {
                int sumHBtn = 0;
                int yLastBtn = (this.y + this.h) - (this.margin * 2);
                for (int i6 = myButtonArr.length - 1; i6 >= 0; i6--) {
                    if (this.status[i6] == -2) {
                        i = fontPaint.getHeight() + 7;
                    } else {
                        i = fontPaint.getHeight() + 10;
                    }
                    sumHBtn += i;
                    this.btns[i6].h = fontPaint.getHeight();
                    MyButton myButton2 = this.btns[i6];
                    myButton2.xBtn = this.xContentR + 2;
                    int i7 = yLastBtn - sumHBtn;
                    myButton2.yViewPort = i7;
                    myButton2.yBtn = i7;
                }
                int i8 = this.h;
                int i9 = this.margin;
                this.h = i8 + (sumHBtn - (i9 * 6));
                this.y -= sumHBtn - (i9 * 6);
            }
        }
    }

    private void init() {
        String text = "";
        for (int i = 0; i < this.vecContent.size(); i++) {
            text = text + ((DetailText) this.vecContent.elementAt(i)).text;
        }
        String[] splitStringByFontGetSpace = Res.splitStringByFontGetSpace(text, this.wContentR, fontPaint);
        this.contentConvert = splitStringByFontGetSpace;
        this.detailTextPaint = new DetailText[splitStringByFontGetSpace.length];
        int indexTemp = 0;
        for (int k = 0; k < this.contentConvert.length; k++) {
            while (this.contentConvert[k].length() > 0) {
                DetailText detailText = (DetailText) this.vecContent.elementAt(indexTemp);
                if (this.contentConvert[k].startsWith(detailText.text)) {
                    detailText.line = k;
                    String[] strArr = this.contentConvert;
                    strArr[k] = strArr[k].substring(1);
                    indexTemp++;
                }
            }
        }
        int count = 0;
        for (int i2 = 0; i2 < this.vecContent.size(); i2++) {
            DetailText item = (DetailText) this.vecContent.elementAt(i2);
            if (i2 < this.vecContent.size() - 1) {
                DetailText itemNext = (DetailText) this.vecContent.elementAt(i2 + 1);
                if (itemNext.line != item.line) {
                    count = 0;
                } else {
                    count += item.fontText.getWidth(item.getText());
                    itemNext.wTextBefore = (float) count;
                }
            }
        }
    }

    public void updateKey() {
        updatePointer();
        if (this.isPaintBtnClose) {
            this.cmdClose.updateIconOnly();
        }
    }
}
