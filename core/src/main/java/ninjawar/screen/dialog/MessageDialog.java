package ninjawar.screen.dialog;

import ninjawar.input.MyButton;
import ninjawar.message.SendMessage;
import ninjawar.model.DetailText;
import ninjawar.model.MyCommand;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.VectorCustom;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.Res;
import ninjawar.myscr.SplashScr;
import ninjawar.object.Item;
import ninjawar.screen.quest.NPCTalkQuestScreen;
import ninjawar.screen.subtab.SubTabApplyParty;
import ninjawar.screen.subtab.SubTabInviteParty;
import ninjawar.screen.subtab.SubTabScr;
import ninjawar.screen.tab.TabCreateParty;
import ninjawar.screen.tab.TabParty;
import ninjawar.screen.tab.TabScr;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportTranslate;

public class MessageDialog extends Dialog {
    public static int TIME_WAITING_LOGIN_OATH2 = 60000;
    public static mFont fontPaint = mFont.tahoma_7;
    public static MessageDialog instance;
    public int TIME_WAITING_DEFAULT = 15000;
    public MyButton[] btns;
    public String content;
    public String[] contentConvert;
    public String[] contentNoClors;
    public DetailText[] detailTextPaint;
    DetailText detailTextTemp;
    public FrameImage frameImg;
    public int h;
    public int idItemPaint = -1;
    byte idSpecialDiaLog = -1;
    public int idTask = -1;
    public int indexFrame;
    boolean isLeftJustify = false;
    public boolean isPaintItem = false;
    public boolean isShowTime = false;
    public boolean isUseBtn = true;
    boolean isUseColor = false;
    public boolean isWaiting;
    Item item = null;
    int margin = 5;
    public String result;
    public long timeStartWaiting = -1;
    public VectorCustom vecContent;
    public int w;
    int w2 = 0;
    int wTextTemp = 0;
    public int x;
    int xItem;
    public int xMidleText;
    public int xReponsive;
    public int y;
    int yItem;

    public static MessageDialog gI() {
        if (instance == null) {
            instance = new MessageDialog();
        }
        return instance;
    }

    public void init() {
        MyButton[] myButtonArr;
        String text;
        boolean z;
        this.h = 150;
        if (this.idSpecialDiaLog == 0) {
            this.x = ((CanvasNinja.w - this.w) - this.w2) / 2;
        } else {
            this.x = (CanvasNinja.w - this.w) / 2;
        }
        this.y = (CanvasNinja.h - 150) / 2;
        String text2 = "";
        if (this.isUseColor) {
            for (int i = 0; i < this.vecContent.size(); i++) {
                text2 = text2 + ((DetailText) this.vecContent.elementAt(i)).text;
            }
            String[] splitStringByFontGetSpace = Res.splitStringByFontGetSpace(text2, this.w - (this.margin * 8), fontPaint);
            this.contentConvert = splitStringByFontGetSpace;
            if (splitStringByFontGetSpace.length == 1) {
                this.xMidleText = ((this.w - fontPaint.getWidth(splitStringByFontGetSpace[0])) / 2) - (this.margin * 4);
            } else {
                this.xMidleText = 0;
            }
            this.detailTextPaint = new DetailText[this.contentConvert.length];
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
            float count = 0.0f;
            for (int i2 = 0; i2 < this.vecContent.size(); i2++) {
                DetailText item2 = (DetailText) this.vecContent.elementAt(i2);
                if (i2 < this.vecContent.size() - 1) {
                    DetailText itemNext = (DetailText) this.vecContent.elementAt(i2 + 1);
                    if (itemNext.line != item2.line) {
                        count = 0.0f;
                    } else {
                        count += (float) item2.fontText.getWidth(item2.getText());
                        itemNext.wTextBefore = count;
                    }
                }
            }
        }
        if (!this.isUseColor) {
            this.contentConvert = this.contentNoClors;
        }
        int height = fontPaint.getHeight();
        String[] strArr2 = this.contentConvert;
        int length = (this.margin * 4) + 2 + (height * strArr2.length);
        int i3 = this.margin;
        int hTemp = length + ((strArr2.length - 1) * i3);
        int wBtn = 0;
        if (this.isUseBtn) {
            MyButton[] myButtonArr2 = this.btns;
            int length2 = myButtonArr2.length % 3;
            int soDongNut = myButtonArr2.length / 3;
            if (length2 != 0) {
                soDongNut++;
            }
            int height2 = fontPaint.getHeight();
            String[] strArr3 = this.contentConvert;
            int length3 = (i3 * 4) + 2 + (height2 * strArr3.length);
            int i4 = this.margin;
            hTemp = length3 + ((strArr3.length - 1) * i4) + (this.btns[0].h * soDongNut) + ((soDongNut - 1) * i4);
            if (strArr3.length == 1) {
                hTemp = (i4 * 6) + (fontPaint.getHeight() * this.contentConvert.length) + (this.btns[0].h * soDongNut) + (this.margin * (soDongNut - 1));
            }
        }
        int hTemp2 = hTemp + 20;
        if (this.h != hTemp2) {
            this.h = hTemp2;
        }
        this.y = (CanvasNinja.h - this.h) / 2;
        if (this.isUseBtn) {
            MyButton[] myButtonArr3 = this.btns;
            int maxBtn = myButtonArr3.length >= 3 ? 3 : myButtonArr3.length;
            for (int i5 = 0; i5 < maxBtn; i5++) {
                wBtn += this.btns[i5].w;
                if (i5 != 0) {
                    wBtn += this.margin;
                }
            }
            int i6 = 0;
            while (true) {
                myButtonArr = this.btns;
                if (i6 >= myButtonArr.length) {
                    break;
                }
                myButtonArr[i6].isSmall = true;
                i6++;
            }
            int i7 = this.margin;
            int yFirstBtn = (((this.y + hTemp2) - (i7 * 2)) - myButtonArr[0].h) - i7;
            int indexY = 0;
            int indexX = 0;
            int length4 = myButtonArr.length % 3;
            int soDongNut2 = myButtonArr.length / 3;
            if (length4 != 0) {
                soDongNut2++;
            }
            int[] wBtnTheoDong = new int[soDongNut2];
            int maxWBtn = wBtnTheoDong[0];
            for (int i8 = 0; i8 < wBtnTheoDong.length; i8++) {
                if (wBtnTheoDong[i8] > maxWBtn) {
                    maxWBtn = wBtnTheoDong[i8];
                }
            }
            int maxWBtn2 = maxWBtn + (this.margin * 15);
            int i9 = this.w;
            if (i9 < maxWBtn2) {
                this.xReponsive = (maxWBtn2 - i9) / 2;
                this.w = maxWBtn2;
            }
            int i10 = 0;
            while (true) {
                MyButton[] myButtonArr4 = this.btns;
                if (i10 >= myButtonArr4.length) {
                    break;
                }
                int xFirstBtn = this.x + ((this.w - wBtn) / 2);
                if (indexX == 0) {
                    myButtonArr4[i10].xBtn = xFirstBtn;
                    text = text2;
                } else {
                    text = text2;
                    myButtonArr4[i10].xBtn = myButtonArr4[i10 - 1].xBtn + this.margin + myButtonArr4[i10 - 1].w;
                }
                if (indexY == 0) {
                    MyButton myButton = myButtonArr4[i10];
                    myButton.yViewPort = yFirstBtn;
                    myButton.yBtn = yFirstBtn;
                    z = true;
                } else {
                    MyButton myButton2 = myButtonArr4[i10];
                    z = true;
                    int i11 = myButtonArr4[(indexY * 3) - 1].yBtn + this.margin + myButtonArr4[(indexY * 3) - 1].h;
                    myButton2.yViewPort = i11;
                    myButton2.yBtn = i11;
                }
                wBtnTheoDong[indexY] = wBtnTheoDong[indexY] + this.margin + myButtonArr4[i10].w;
                indexX++;
                if ((i10 + 1) % 3 == 0) {
                    indexY++;
                    indexX = 0;
                }
                i10++;
                text2 = text;
                boolean z2 = z;
            }
        }
        if (this.isPaintItem) {
            this.item = new Item(this.idItemPaint);
            int i12 = 0;
            while (true) {
                String[] strArr4 = this.contentNoClors;
                if (i12 < strArr4.length) {
                    if (strArr4[i12].contains("       ")) {
                        this.yItem = this.y + (this.margin * 3) + ((fontPaint.getHeight() + 5) * i12) + this.margin + (this.item.getImage().getRHeight() / 2) + 3;
                        int width = (this.x + (this.w / 2)) - (fontPaint.getWidth(this.contentNoClors[i12]) / 2);
                        mFont mfont = fontPaint;
                        String str = this.contentNoClors[i12];
                        this.xItem = ((width + mfont.getWidth(str.substring(0, str.indexOf("       ")))) + this.item.getImage().getRWidth()) - (this.margin * 2);
                    }
                    i12++;
                } else {
                    return;
                }
            }
        } else {
            this.item = null;
        }
    }

    public void update() {
        FrameImage frameImage;
        long j = this.timeStartWaiting;
        if (!(j == -1 || j == 0 || j >= mSystem.currentTimeMillis())) {
            this.isWaiting = false;
            this.timeStartWaiting = -1;
            String str = this.result;
            if (str != null) {
                if (str.equals(SupportTranslate.getTextLangue("serverClose"))) {
                    CanvasNinja.resetLogin();
                }
                close();
            }
        }
        if (this.isWaiting && (frameImage = this.frameImg) != null) {
            int i = this.indexFrame + 1;
            this.indexFrame = i;
            if (i > frameImage.nFrame - 1) {
                this.indexFrame = 0;
            }
        }
        int i2 = 0;
        while (true) {
            MyButton[] myButtonArr = this.btns;
            if (i2 < myButtonArr.length) {
                myButtonArr[i2].update();
                i2++;
            } else {
                return;
            }
        }
    }

    public void updateKey() {
        updatePointer();
    }

    public void updatePointer() {
        if (this.isUseBtn && !this.isWaiting) {
            int i = 0;
            while (true) {
                MyButton[] myButtonArr = this.btns;
                if (i >= myButtonArr.length) {
                    return;
                }
                if (myButtonArr[i].updatePointer()) {
                    int i2 = this.idTask;
                    if (i2 != -1 && i2 == 0) {
                        SplashScr.gI().switchToMe();
                        return;
                    }
                    return;
                }
                i++;
            }
        }
    }

    public int getTime() {
        return (int) ((this.timeStartWaiting - mSystem.currentTimeMillis()) / 1000);
    }

    public void paint(mGraphics g) {
        FrameImage frameImage;
        CanvasNinja.resetTrans(g);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        g.translate(this.xReponsive, 0);
        if (!this.isUseColor) {
            for (int i = 0; i < this.contentNoClors.length; i++) {
                if (!this.isShowTime || getTime() <= 0 || i != this.contentNoClors.length - 1) {
                    int i2 = 20;
                    if (this.isLeftJustify) {
                        mFont mfont = fontPaint;
                        String str = this.contentNoClors[i];
                        int i3 = this.x;
                        int i4 = this.margin;
                        int i5 = i3 + (i4 * 4);
                        int height = this.y + (i4 * 2) + ((mfont.getHeight() + 5) * i) + this.margin;
                        if (this.idSpecialDiaLog != 0) {
                            i2 = 0;
                        }
                        mfont.drawString(g, str, i5, height + i2, 0);
                    } else {
                        mFont mfont2 = fontPaint;
                        String str2 = this.contentNoClors[i];
                        int i6 = this.x + (this.w / 2);
                        int height2 = this.y + (this.margin * 2) + ((mfont2.getHeight() + 5) * i) + this.margin;
                        if (this.idSpecialDiaLog != 0) {
                            i2 = 0;
                        }
                        mfont2.drawString(g, str2, i6, height2 + i2, 2);
                    }
                } else {
                    fontPaint.drawString(g, this.contentNoClors[i] + " (" + getTime() + ")", (this.w / 2) + this.x, this.margin + this.y + (this.margin * 2) + ((fontPaint.getHeight() + 5) * i), 2);
                }
            }
        } else {
            for (int i7 = 0; i7 < this.vecContent.size(); i7++) {
                this.detailTextTemp = (DetailText) this.vecContent.elementAt(i7);
                if (!this.isShowTime || i7 != this.vecContent.size() - 1) {
                    DetailText detailText = this.detailTextTemp;
                    mFont mfont3 = detailText.fontText;
                    String str3 = detailText.text;
                    int i8 = this.xMidleText + this.x;
                    int i9 = this.margin;
                    mfont3.drawString(g, str3, ((float) (i8 + (i9 * 4))) + detailText.wTextBefore, (detailText.line * (detailText.h + 2)) + this.y + (i9 * 2), 0);
                } else {
                    int i10 = this.xMidleText + this.x;
                    int i11 = this.margin;
                    DetailText detailText2 = this.detailTextTemp;
                    this.detailTextTemp.fontText.drawString(g, this.detailTextTemp.text + " (" + getTime() + ")", detailText2.wTextBefore + ((float) (i10 + (i11 * 4))), this.y + (i11 * 2) + (detailText2.line * (detailText2.h + 2)), 0);
                }
            }
        }
        if (this.isWaiting && (frameImage = this.frameImg) != null) {
            int i12 = this.indexFrame;
            float f = ((float) this.x) + ((((float) this.w) - frameImage.frameWidth) / 2.0f);
            MyButton myButton = this.btns[0];
            frameImage.drawFrame(i12, f, ((((float) myButton.h) - frameImage.frameHeight) / 2.0f) + ((float) myButton.yBtn), 0, g);
        } else if (this.isUseBtn && this.idSpecialDiaLog != 0) {
            int i13 = 0;
            while (true) {
                MyButton[] myButtonArr = this.btns;
                if (i13 >= myButtonArr.length) {
                    break;
                }
                myButtonArr[i13].paint(g);
                i13++;
            }
        }
        super.paint(g);
    }

    public void close() {
        this.item = null;
        this.idSpecialDiaLog = -1;
        this.w2 = 0;
        CanvasNinja.endDlg();
    }

    public void startMsgDialog(String content2, int[] colorBtn, String[] nameBtns) {
        String content3 = content2;
        String[] strArr = nameBtns;
        this.timeStartWaiting = -1;
        this.idTask = this.idTask;
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr == null || myButtonArr.length < 3) {
            this.w = 260;
        } else {
            this.w = 300;
        }
        this.result = this.result;
        this.isWaiting = false;
        this.content = content3;
        if (content3.contains("###l")) {
            this.isLeftJustify = true;
            content3 = content3.replaceAll("###l", "");
        } else {
            this.isLeftJustify = false;
        }
        if (!Res.isHaveColorInStringServer(content3)) {
            this.contentNoClors = fontPaint.splitFontArray(content3, this.w - ((this.margin * 4) * 2));
            this.isUseColor = false;
        } else {
            this.isUseColor = true;
            this.vecContent = Res.formatStringFromServer(content3, fontPaint);
        }
        this.isUseBtn = true;
        this.btns = new MyButton[strArr.length];
        for (int i = 0; i < this.btns.length; i++) {
            MyCommand cmd = new MyCommand(strArr[i], 1, NPCTalkQuestScreen.gI());
            cmd.subAction = i;
            MyButton[] myButtonArr2 = this.btns;
            FrameImage[] frameImageArr = LoadDataManager.myButtons;
            myButtonArr2[i] = new MyButton(frameImageArr[colorBtn[i]], frameImageArr[colorBtn[i]], 75, 0, strArr[i], 0, 0, cmd);
        }
        init();
        show();
    }

    public void startMsgDialog(String content2, MyButton[] btns2, byte idSpecialDiaLog2) {
        this.idSpecialDiaLog = idSpecialDiaLog2;
        this.timeStartWaiting = -1;
        this.idTask = this.idTask;
        if (btns2 == null || btns2.length < 3) {
            this.w = 260;
        } else {
            this.w = 300;
        }
        this.result = this.result;
        this.isWaiting = false;
        if (content2.contains("%")) {
            this.isPaintItem = true;
            int vi_tri1 = content2.indexOf(37) + 1;
            this.idItemPaint = Integer.parseInt(content2.substring(vi_tri1, content2.indexOf(37, vi_tri1 + 1)));
            content2 = content2.replaceAll("%[^%]+%", "       ");
        }
        if (content2.contains("###l")) {
            this.isLeftJustify = true;
            content2 = content2.replaceAll("###l", "");
        } else {
            this.isLeftJustify = false;
        }
        this.content = content2;
        if (!Res.isHaveColorInStringServer(content2)) {
            this.contentNoClors = fontPaint.splitFontArray(content2, this.w - ((this.margin * 4) * 2));
            this.isUseColor = false;
        } else {
            this.isUseColor = true;
            this.vecContent = Res.formatStringFromServer(content2, fontPaint);
        }
        this.isUseBtn = true;
        this.btns = btns2;
        if (idSpecialDiaLog2 == 0) {
            this.w2 = this.w + (this.margin * 10);
        }
        init();
        show();
    }

    public void startMsgDialog(String content2, MyButton[] btns2) {
        this.timeStartWaiting = -1;
        this.idTask = this.idTask;
        if (btns2 == null || btns2.length < 3) {
            this.w = 260;
        } else {
            this.w = 300;
        }
        this.result = this.result;
        this.isWaiting = false;
        if (content2.contains("%")) {
            this.isPaintItem = true;
            int vi_tri1 = content2.indexOf(37) + 1;
            this.idItemPaint = Integer.parseInt(content2.substring(vi_tri1, content2.indexOf(37, vi_tri1 + 1)));
            content2 = content2.replaceAll("%[^%]+%", "       ");
        }
        if (content2.contains("###l")) {
            this.isLeftJustify = true;
            content2 = content2.replaceAll("###l", "");
        } else {
            this.isLeftJustify = false;
        }
        this.content = content2;
        if (!Res.isHaveColorInStringServer(content2)) {
            this.contentNoClors = fontPaint.splitFontArray(content2, this.w - ((this.margin * 4) * 2));
            this.isUseColor = false;
        } else {
            this.isUseColor = true;
            this.vecContent = Res.formatStringFromServer(content2, fontPaint);
        }
        this.isUseBtn = true;
        this.btns = btns2;
        init();
        show();
    }

    public void startMsgDialog(String content2, String result2) {
        startMsgDialog(content2, result2, -1);
    }

    public void startMsgDialog(String content2, int idTask2) {
        startMsgDialog(content2, content2, idTask2);
    }

    public void startMsgDialog(String content2, String result2, int idTask2) {
        startMsgDialog(content2, (String) null, result2, idTask2);
    }

    public void startMsgDialog(String content2, String nameBtn, String result2, int idTask2) {
        String content3 = content2;
        this.timeStartWaiting = -1;
        this.idTask = idTask2;
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr == null || myButtonArr.length < 3) {
            this.w = 260;
        } else {
            this.w = 300;
        }
        this.result = result2;
        this.isWaiting = false;
        if (content3.contains("###l")) {
            this.isLeftJustify = true;
            content3 = content3.replaceAll("###l", "");
        } else {
            this.isLeftJustify = false;
        }
        this.content = content3;
        Res.outz("CONTENT: " + content3);
        if (!Res.isHaveColorInStringServer(content3)) {
            this.contentNoClors = fontPaint.splitFontArray(content3, this.w - ((this.margin * 4) * 2));
            this.isUseColor = false;
        } else {
            this.isUseColor = true;
            this.vecContent = Res.formatStringFromServer(content3, fontPaint);
        }
        this.isUseBtn = true;
        String nameButton = nameBtn != null ? nameBtn : SupportTranslate.getTextLangue("CLOSE");
        this.btns = new MyButton[1];
        int i = 0;
        while (true) {
            MyButton[] myButtonArr2 = this.btns;
            if (i < myButtonArr2.length) {
                FrameImage frameImage = LoadDataManager.myButtons[1];
                myButtonArr2[i] = new MyButton(frameImage, frameImage, 75, 0, nameButton, 0, 0, new MyCommand("", i, this));
                i++;
            } else {
                init();
                show();
                return;
            }
        }
    }

    public void startMsgDialog(String content2) {
        startMsgDialog(content2, content2);
    }

    public void startWaitingDialog(String text, String result2, int timeWaiting) {
        this.frameImg = LoadDataManager.frameWaiting;
        startMsgDialog(text, result2);
        this.timeStartWaiting = timeWaiting != -1 ? mSystem.currentTimeMillis() + ((long) timeWaiting) : -1;
        this.isWaiting = true;
    }

    public void startWaitingDialog(String text, String result2) {
        startWaitingDialog(text, result2, this.TIME_WAITING_DEFAULT);
    }

    public void show() {
        instance = this;
        CanvasNinja.currentDialog = this;
    }

    public void commandTab(int index, int subIndex) {
        Res.outz("Vào commandTab trong message dialog:" + index);
        CanvasNinja.clearAllPointer();
        switch (index) {
            case -106:
                break;
            case -105:
                SendMessage.gI().sendYesNoInput(this.idTask, true);
                close();
                break;
            case -104:
                SendMessage.gI().acceptTeleportParty(false);
                close();
                return;
            case -103:
                SendMessage.gI().acceptTeleportParty(true);
                close();
                return;
            case -102:
                SendMessage.gI().acceptJoinParty(subIndex, false);
                close();
                return;
            case -101:
                SendMessage.gI().acceptJoinParty(subIndex, true);
                close();
                return;
            case -99:
                if (TabParty.isLeader()) {
                    SendMessage.gI().outGroupParty(-2);
                    TabScr tabScr = CanvasNinja.currentTab;
                    if (tabScr != null && ((tabScr instanceof TabCreateParty) || (tabScr instanceof TabParty))) {
                        TabCreateParty.me = null;
                        TabParty.me = null;
                        CanvasNinja.endTab();
                    }
                    SubTabScr subTabScr = CanvasNinja.subTab;
                    if (subTabScr != null && ((subTabScr instanceof SubTabApplyParty) || (subTabScr instanceof SubTabInviteParty))) {
                        SubTabApplyParty.me = null;
                        SubTabInviteParty.me = null;
                        CanvasNinja.endSubTab();
                    }
                } else {
                    SendMessage.gI().outGroupParty(-1);
                    TabScr tabScr2 = CanvasNinja.currentTab;
                    if (tabScr2 != null && ((tabScr2 instanceof TabCreateParty) || (tabScr2 instanceof TabParty))) {
                        TabCreateParty.me = null;
                        TabParty.me = null;
                        CanvasNinja.endTab();
                    }
                    SubTabScr subTabScr2 = CanvasNinja.subTab;
                    if (subTabScr2 != null && ((subTabScr2 instanceof SubTabApplyParty) || (subTabScr2 instanceof SubTabInviteParty))) {
                        SubTabApplyParty.me = null;
                        SubTabInviteParty.me = null;
                        CanvasNinja.endSubTab();
                    }
                }
                close();
                return;
            case -98:
                SendMessage.gI().teleportParty(subIndex);
                close();
                return;
            case -97:
                SendMessage.gI().teleportParty(subIndex);
                close();
                return;
            case -96:
                SendMessage.gI().requestConfirmIndexButton(subIndex);
                close();
                return;
            case -95:
                SendMessage.gI().deleteAccount();
                close();
                return;
            case -1:
                break;
            case 0:
                Res.outz("idTask:" + this.idTask);
                int i = this.idTask;
                if (i != -1 && i == 0) {
                    SplashScr.gI().switchToMe();
                }
                if (this.idTask == 1) {
                    SendMessage.gI().requestRevive((byte) 0);
                }
                if (this.idTask == 69) {
                    MapScr.gI().switchToMe();
                }
                close();
                return;
            case 1:
                SendMessage.gI().requestRevive((byte) subIndex);
                CanvasNinja.endDlg();
                return;
            default:
                return;
        }
        SendMessage.gI().sendYesNoInput(this.idTask, false);
        close();
        close();
    }

    public void resize() {
        startMsgDialog(this.content, this.result);
        init();
        show();
    }
}
