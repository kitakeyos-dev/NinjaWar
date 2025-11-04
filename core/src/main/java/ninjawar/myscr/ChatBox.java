package ninjawar.myscr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import ninjawar.input.KEY;
import ninjawar.input.TField;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.screen.tab.TabScr;
import ninjawar.scroll.ScrollY;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;

public class ChatBox extends TabScr {
    public static final String[] TypeStrings = {"Gần", "Thế giới", "Clan", "Party", "Hệ thống", "Chat riêng"};
    public static HashMap<String, Vector<Message>> allMessages = new HashMap<>();
    public static boolean isPaintMessage = false;
    public static boolean isPing = false;
    public static HashMap<String, Boolean> isUnreadMsg = new HashMap<>();
    public static ChatBox me;
    public static Vector<String> nameTabCR = new Vector<>();
    public int[] arrXCR;
    public MyCommand cmdEmoji;
    public MyCommand cmdSendChat;
    int dir;
    public mFont fontPaintMyName = mFont.fontPaintMyName;
    public mFont fontPaintTab = mFont.fontPaintTab;
    public mFont fontPaintTabFocus = mFont.tahoma_brown_focus_dv;
    public mFont fontPaintUsername = mFont.fontChatBoxUserName;
    public mFont fontTextMsg = mFont.tahoma_7_brown_small;
    public mFont fontTitleNews = mFont.tahoma_brown_dv;
    public int h;
    int hChatBox = 0;
    int hMax;
    int hMaxPaint;
    int indexTabSelected = -1;
    int indexTabSelectedCR = -1;
    boolean[] isClickTab;
    public boolean isClose = false;
    public boolean isTrans = false;
    int margin = 5;
    int marginOneTab = 10;
    Vector<String> nameTab = new Vector<>();
    public ScrollY scroll;
    int speed = 15;
    int startCmd = 10;
    TField tfChat;
    Vector<MyCommand> vecCmds = new Vector<>();
    public int w;
    int w_extend = 0;
    int w_old = this.w;
    public int x;
    public int xEnd;
    public int xStart;
    public int y;
    int[] yAnimTab;

    public static ChatBox gI() {
        if (me == null) {
            me = new ChatBox((-CanvasNinja.w) / 2, 0);
        }
        return me;
    }

    public ChatBox(int xStart2, int xEnd2) {
        init();
        this.xStart = xStart2;
        this.x = xStart2;
        this.xEnd = xEnd2;
    }

    public void resetSelected() {
        this.indexTabSelected = 0;
    }

    public void resetSelectedCR() {
        this.indexTabSelectedCR = 0;
    }

    public void init() {
        resetSelected();
        resetSelectedCR();
        int i = CanvasNinja.w / 2;
        this.w_old = i;
        this.w_extend = 0;
        this.w = i + 0;
        this.cmdClose = new MyCommand("", -1, this);
        this.dir = -1;
        int i2 = CanvasNinja.h;
        int i3 = i2 - 40;
        this.h = i3;
        this.hMaxPaint = i3;
        int i4 = i2 - ((int) LoadDataManager.frameHide.frameHeight);
        int i5 = this.margin;
        int i6 = i4 - (i5 * 4);
        this.hMax = i6;
        this.xEnd -= this.w / 2;
        if (i3 > i6) {
            this.h = i6;
        }
        int i7 = CanvasNinja.h;
        int i8 = this.h;
        int i9 = (i7 - i8) / 2;
        this.y = i9;
        int wTagName = ((int) LoadDataManager.frameTabChatBox.frameWidth) * 5;
        int i10 = this.w_old;
        int wKhungTrang = ((((i10 - 15) - i5) - 15) - wTagName) - i5;
        int xKhungTrang = (i10 - 15) - wKhungTrang;
        int yKhungTrang = (i9 - 1) + 13;
        float f = LoadDataManager.frameTitle2.frameHeight;
        float f2 = LoadDataManager.frameIconSend.frameHeight;
        int[] iArr = {(int) f, ((i8 - (((int) f) - 1)) - (13 * 2)) - i5, LoadDataManager.imgDongSE.getRHeight(), this.fontTitleNews.getHeight(), 0, (int) LoadDataManager.frameTabChatBox.frameHeight, LoadDataManager.imgImojiIcon.getRHeight(), (int) LoadDataManager.frameSendChat.frameHeight, (int) f2, (int) f2};
        this.arrH = iArr;
        int yFirstTabName = yKhungTrang + ((iArr[1] - ((iArr[5] * 6) + (this.marginOneTab * (6 - 1)))) / 2);
        int wDuongNgang = (LoadDataManager.imgDongSE.getRWidth() * 2) + LoadDataManager.imgDong.getRWidth();
        int i11 = this.wTitle;
        int[] iArr2 = {i11, wKhungTrang, wDuongNgang, 0, i11 - (this.margin * 4), wTagName, LoadDataManager.imgImojiIcon.getRWidth(), 40, (int) LoadDataManager.frameIconSend.frameWidth, ((((xKhungTrang + wKhungTrang) - 40) - (10 * 2)) - LoadDataManager.imgImojiIcon.getRWidth()) - xKhungTrang};
        this.arrW = iArr2;
        int i12 = this.margin;
        int i13 = iArr2[7];
        this.arrX = new int[]{((wKhungTrang / 2) + xKhungTrang) - (this.wTitle / 2), xKhungTrang, (xKhungTrang + (wKhungTrang / 2)) - (iArr2[2] / 2), xKhungTrang + (wKhungTrang / 2), xKhungTrang + (i12 * 2), this.x + 15, xKhungTrang, (xKhungTrang + wKhungTrang) - iArr2[7], (((xKhungTrang + wKhungTrang) - i13) + (i13 / 2)) - (iArr2[8] / 2), xKhungTrang + iArr2[6] + 10, xKhungTrang};
        int i14 = this.w_old;
        this.arrXCR = new int[]{i14, (iArr2[5] + i14) - i12, i14 + i12};
        int i15 = 0;
        while (true) {
            int[] iArr3 = this.arrX;
            if (i15 >= iArr3.length) {
                break;
            }
            iArr3[i15] = iArr3[i15] - this.w;
            i15++;
        }
        int i16 = 0;
        while (true) {
            int[] iArr4 = this.arrXCR;
            if (i16 >= iArr4.length) {
                break;
            }
            iArr4[i16] = iArr4[i16] - this.w;
            i16++;
        }
        int i17 = this.margin;
        int[] iArr5 = this.arrH;
        int rHeight = yKhungTrang + (i17 * 3) + iArr5[3] + LoadDataManager.imgDongSE.getRHeight();
        int i18 = this.margin;
        int[] iArr6 = this.arrH;
        int i19 = iArr6[1];
        int i20 = this.h;
        this.arrY = new int[]{this.y - 1, yKhungTrang, yKhungTrang + (i17 * 3) + iArr5[3], yKhungTrang + (i17 * 2), rHeight + (i18 * 2), yFirstTabName, ((((yKhungTrang + i19) + i20) - 13) / 2) + i18, ((((yKhungTrang + i19) + i20) - 13) / 2) + i18, ((((((yKhungTrang + i19) + i20) - 13) / 2) + (iArr6[7] / 2)) - (iArr6[8] / 2)) + i18, ((((yKhungTrang + i19) + i20) - 13) / 2) + i18, (yKhungTrang + i19) - 10};
        this.yAnimTab = new int[6];
        this.isClickTab = new boolean[6];
        this.nameTab.add("Gần đây");
        this.nameTab.add("Thế giới");
        this.nameTab.add("Bang hội");
        this.nameTab.add("Party");
        this.nameTab.add("Hệ thống");
        this.nameTab.add("Chat riêng");
        MyCommand myCommand = new MyCommand("", -2, this);
        this.cmdEmoji = myCommand;
        myCommand.isClose = true;
        MyCommand myCommand2 = new MyCommand("", -3, this);
        this.cmdSendChat = myCommand2;
        myCommand2.isClose = true;
        this.tfChat = new TField();
        TField.resetTField();
        TField tField = this.tfChat;
        tField.isSendChat = false;
        tField.width = this.arrW[9];
        tField.height = this.arrH[9];
        if (!NinjaMidlet.isUseIOSSpec) {
            tField.isKeyTyped = true;
        }
        tField.setMaxTextLenght(60);
        this.tfChat.setFocus(false);
        this.tfChat.setIputType(0);
        initScroll();
        int i21 = this.w + this.arrX[1];
        int i22 = this.arrY[1];
        int i23 = this.arrW[1];
        int[] iArr7 = this.arrH;
        int numTabName = iArr7[1];
        int i24 = iArr7[7];
        int i25 = this.margin;
        initScroll2(i21, i22, i23, numTabName, (i24 * 2) - (i25 * 2), i24 - (i25 * 2));
        this.cmdClose.frameImg = LoadDataManager.imgOpenChatBox;
    }

    private void initScroll() {
        int i = this.xEnd;
        int i2 = this.y;
        int i3 = this.w;
        int i4 = this.h;
        this.scroll = new ScrollY(i, i2, i3, i4, this.hMaxPaint, i4);
    }

    public void update() {
        this.cmdSendChat.updatePointer();
        if (!isUnreadMsg.values().contains(true)) {
            MapScr.isHaveUnReadMsg = false;
        }
        ScrollY scrollY = this.scroll;
        if (scrollY != null && CanvasNinja.currentTab == null) {
            scrollY.update();
        }
        updateScroll();
        int i = 0;
        while (true) {
            int[] iArr = this.yAnimTab;
            if (i >= iArr.length) {
                break;
            }
            boolean[] zArr = this.isClickTab;
            if (zArr[i]) {
                int i2 = iArr[i] + 1;
                iArr[i] = i2;
                if (i2 >= 2) {
                    iArr[i] = 0;
                    zArr[i] = false;
                }
            }
            i++;
        }
        if (NinjaMidlet.isAndroid && !NinjaMidlet.isUseIOSSpec) {
            if (this.tfChat.isFocused()) {
                Gdx.input.setOnscreenKeyboardVisible(true, Input.OnscreenKeyboardType.Default);
            }
            if (!this.tfChat.isFocused()) {
                Gdx.input.setOnscreenKeyboardVisible(false);
            }
        }
        if (this.isTrans) {
            this.x += this.dir * this.speed;
            int i3 = 0;
            while (true) {
                int[] iArr2 = this.arrX;
                if (i3 >= iArr2.length) {
                    break;
                }
                iArr2[i3] = iArr2[i3] + (this.dir * this.speed);
                i3++;
            }
            int i4 = 0;
            while (true) {
                int[] iArr3 = this.arrXCR;
                if (i4 >= iArr3.length) {
                    break;
                }
                iArr3[i4] = iArr3[i4] + (this.dir * this.speed);
                i4++;
            }
            if (this.dir == -1 && this.x < this.xStart) {
                int i5 = 0;
                while (true) {
                    int[] iArr4 = this.arrX;
                    if (i5 >= iArr4.length) {
                        break;
                    }
                    iArr4[i5] = iArr4[i5] + Math.abs(this.x - this.xStart);
                    i5++;
                }
                int i6 = 0;
                while (true) {
                    int[] iArr5 = this.arrXCR;
                    if (i6 >= iArr5.length) {
                        break;
                    }
                    iArr5[i6] = iArr5[i6] + Math.abs(this.x - this.xStart);
                    i6++;
                }
                this.x = this.xStart;
                this.isTrans = false;
                this.isClose = true;
            }
            if (this.dir == 1 && this.x > this.xEnd) {
                int i7 = 0;
                while (true) {
                    int[] iArr6 = this.arrX;
                    if (i7 >= iArr6.length) {
                        break;
                    }
                    iArr6[i7] = iArr6[i7] - Math.abs(this.x - this.xEnd);
                    i7++;
                }
                int i8 = 0;
                while (true) {
                    int[] iArr7 = this.arrXCR;
                    if (i8 >= iArr7.length) {
                        break;
                    }
                    iArr7[i8] = iArr7[i8] - Math.abs(this.x - this.xEnd);
                    i8++;
                }
                this.x = this.xEnd;
                this.isTrans = false;
            }
            Iterator<MyCommand> it = this.vecCmds.iterator();
            while (it.hasNext()) {
                it.next().x = this.x + this.margin;
            }
        }
        int i9 = this.x;
        if (i9 == this.xStart) {
            CanvasNinja.currentTab = null;
        }
        if (i9 == this.xEnd) {
            this.cmdClose.frameImg = LoadDataManager.imgCloseChatBox;
        }
    }

    public void updateKey() {
        this.cmdClose.updateIconOnly();
        this.cmdEmoji.updateIconOnly();
        this.cmdSendChat.updatePointer();
        if (CanvasNinja.isPointerRelease()) {
            for (int i = 0; i < this.isClickTab.length; i++) {
                int i2 = this.arrX[5];
                int i3 = this.arrY[5];
                int i4 = this.marginOneTab;
                int i5 = this.arrH[5];
                if (CanvasNinja.isPoint(i2, i3 + ((i4 + i5) * i), this.arrW[5], i5)) {
                    CanvasNinja.clearAllPointer();
                    boolean[] zArr = this.isClickTab;
                    zArr[i] = true;
                    this.indexTabSelected = i;
                    if (zArr[5]) {
                        this.w_extend = 105;
                        this.w = this.w_old + 105;
                    } else {
                        this.w_extend = 0;
                        this.w = this.w_old;
                    }
                }
            }
        }
        if (CanvasNinja.isPointerRelease()) {
            for (int i6 = 0; i6 < nameTabCR.size(); i6++) {
                int i7 = this.arrX[5] + this.w_old;
                int i8 = this.arrY[5];
                int i9 = this.marginOneTab;
                int i10 = this.arrH[5];
                if (CanvasNinja.isPoint(i7, i8 + ((i9 + i10) * i6) + this.yAnimTab[i6], this.arrW[5], i10)) {
                    CanvasNinja.clearAllPointer();
                    this.indexTabSelectedCR = i6;
                    isUnreadMsg.put(nameTabCR.get(i6), false);
                }
            }
        }
        updateKeyInputChat();
        if (CanvasNinja.isPointerRelease() && CanvasNinja.isPoint(this.arrX[7], this.arrY[7], this.arrW[7], this.arrH[7])) {
            CanvasNinja.clearAllPointer();
            this.cmdSendChat.perform();
        }
    }

    public void updateKeyInputChat() {
        TField tField = this.tfChat;
        if (!CanvasNinja.isPointerHoldOrHover(tField.x, tField.y, tField.width, tField.height) && !CanvasNinja.isPointerHoldOrHover(this.arrX[9], this.arrY[9], this.arrW[9], this.arrH[9]) && !this.tfChat.isFocused()) {
            this.tfChat.isFocused();
        }
        this.cmdSendChat.updatePointer();
        this.tfChat.update();
        this.tfChat.updateFocus();
        if (!CanvasNinja.isCheckClickAll()) {
            return;
        }
        if (CanvasNinja.isPoint(this.arrX[9], this.arrY[9], this.arrW[9], this.arrH[9])) {
            CanvasNinja.clearAllPointer();
            CanvasNinja.isPointerDraggedX = false;
            CanvasNinja.isPointerDraggedY = false;
            this.tfChat.setFocus(true);
            this.tfChat.isChat = false;
            return;
        }
        TField tField2 = this.tfChat;
        tField2.isChat = false;
        tField2.setFocus(false);
    }

    public void show() {
        init();
        showTab();
    }

    public void open() {
        TField tField = this.tfChat;
        mFont mfont = this.fontTextMsg;
        tField.fontTField = mfont;
        tField.fontPaint = mfont;
        TField.CARET_COLOR = 8799779;
        this.isTrans = true;
        this.dir = 1;
        this.cmdClose.frameImg = LoadDataManager.imgOpenChatBox;
        showTab();
    }

    public void close() {
        this.isTrans = true;
        this.dir = -1;
        TField.resetTField();
        MapScr.isPaintButtonSkill = true;
    }

    public static class Message {
        String content;
        boolean isMyChat;
        long time = System.currentTimeMillis();
        String username;

        public Message(String username2, String content2, boolean isMyChat2) {
            this.username = username2;
            this.content = content2;
            this.isMyChat = isMyChat2;
        }
    }

    public String initLongText(String text) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        for (char c : text.toCharArray()) {
            result.append(c);
            count++;
            if (count % 30 == 0) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    public void paintChatMsg(mGraphics g, Vector<Message> vtMessages) {
        int i;
        int x_Msg;
        Image muiten;
        Vector<Message> vector = vtMessages;
        if (vector != null) {
            int[] arrYC = new int[vtMessages.size()];
            int i2 = 1;
            arrYC[0] = (this.arrY[10] - this.margin) - this.fontTextMsg.getHeightH(initLongText(vector.get(vtMessages.size() - 1).content));
            int i3 = 0;
            while (i3 < vtMessages.size()) {
                int indexDaoNguoc = (vtMessages.size() - i3) - i2;
                String textDisplay = initLongText(vector.get(indexDaoNguoc).content);
                int textWidth = this.fontTextMsg.getWidth(textDisplay);
                int textHeight = this.fontTextMsg.getHeightH(textDisplay);
                Image image = LoadDataManager.imgMuitentrai;
                int muitenHeight = image.height;
                int muitenWidth = image.width;
                Image muiten2 = LoadDataManager.imgMuitentrai;
                int xMuiten = 0;
                if (i3 > 0) {
                    arrYC[i3] = (arrYC[i3 - 1] - (this.margin * 5)) - textHeight;
                }
                if (vector.get(indexDaoNguoc).isMyChat) {
                    int i4 = this.arrW[i2];
                    int i5 = this.margin;
                    Image muiten3 = LoadDataManager.imgMuitenphai;
                    int xMuiten2 = 0 + (i5 * 2) + textWidth;
                    mFont mfont = this.fontPaintMyName;
                    String str = vector.get(indexDaoNguoc).username;
                    Image muiten4 = muiten3;
                    i = 1;
                    int i6 = this.arrW[1];
                    int xMuiten3 = xMuiten2;
                    int xMuiten4 = this.margin;
                    mfont.drawString(g, str, i6 - (xMuiten4 * 2), arrYC[i3] - (xMuiten4 * 2), 1, true);
                    x_Msg = (i4 - (i5 * 4)) - textWidth;
                    muiten = muiten4;
                    xMuiten = xMuiten3;
                } else {
                    i = i2;
                    int x_Msg2 = this.margin * 2;
                    this.fontPaintUsername.drawString(g, vector.get(indexDaoNguoc).username, x_Msg2, arrYC[i3] - (this.margin * 2), 0, true);
                    muiten = muiten2;
                    x_Msg = x_Msg2;
                }
                int xMuiten5 = xMuiten + (x_Msg - (muitenWidth / 2));
                int i7 = (arrYC[i3] + (textHeight / 2)) - (muitenHeight / 2);
                int i8 = this.margin;
                int yMuiten = 0 + i7 + i8;
                int i9 = indexDaoNguoc;
                CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameChatMsg, (float) x_Msg, (float) arrYC[i3], (float) (textWidth + (i8 * 2)), (float) ((i8 * 2) + textHeight), 7, true);
                g.drawImage(muiten, (float) xMuiten5, (float) yMuiten, 0, true);
                mFont mfont2 = this.fontTextMsg;
                int i10 = arrYC[i3] + this.margin7;
                int i11 = yMuiten;
                int i12 = xMuiten5;
                int xMuiten6 = i10;
                int i13 = muitenWidth;
                int i14 = muitenHeight;
                mfont2.drawString(g, textDisplay, this.margin + x_Msg, xMuiten6, 0, true);
                this.hChatBox = arrYC[i3] + this.margin;
                i3++;
                vector = vtMessages;
                i2 = i;
            }
        }
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, this.scroll != null);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameChatBox, (float) this.arrX[1], (float) this.arrY[1], (float) this.arrW[1], (float) this.arrH[1], 0, this.scroll != null);
        int i = 0;
        while (i < this.isClickTab.length) {
            CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameTabChatBox, this.arrX[5], this.yAnimTab[i] + this.arrY[5] + ((this.marginOneTab + this.arrH[5]) * i), this.arrW[5], i == this.indexTabSelected, 0, false);
            if (i != this.indexTabSelected) {
                int i2 = (this.arrW[5] / 2) + this.arrX[5];
                int i3 = this.arrY[5];
                int i4 = this.marginOneTab;
                int i5 = this.arrH[5];
                this.fontPaintTab.drawString(g, this.nameTab.get(i), i2, i3 + ((i4 + i5) * i) + this.yAnimTab[i] + (i5 / 2), 3);
            } else {
                int i6 = (this.arrW[5] / 2) + this.arrX[5];
                int i7 = this.arrY[5];
                int i8 = this.marginOneTab;
                int i9 = this.arrH[5];
                this.fontPaintTabFocus.drawString(g, this.nameTab.get(i), i6, i7 + ((i8 + i9) * i) + this.yAnimTab[i] + (i9 / 2), 3);
            }
            i++;
        }
        this.cmdEmoji.setPosPaint(this.arrX[6], this.arrY[6], LoadDataManager.imgImojiIcon);
        this.cmdEmoji.paintIconOnly(mgraphics);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameSendChat, this.arrX[7], this.arrY[7], this.arrW[7], false);
        this.cmdSendChat.setPosPaint(this.arrX[8], this.arrY[8] + 2, LoadDataManager.frameIconSend);
        this.cmdSendChat.paintIconOnly(mgraphics);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameChatBoxInput, this.arrX[9], this.arrY[9], this.arrW[9], true, SupportPaint.TYPE_NONE, true);
        int i10 = this.arrX[1];
        int i11 = this.arrY[1];
        int i12 = this.margin;
        mgraphics.setClip(i10, i11 + i12, this.arrW[1], this.arrH[1] - (i12 * 2));
        mgraphics.translate(this.arrX[1], this.scroll2.cmy);
        int i13 = this.indexTabSelected;
        if (i13 != 5) {
            paintChatMsg(mgraphics, allMessages.get(Integer.toString(i13)));
        } else {
            try {
                if (allMessages.get(nameTabCR.get(this.indexTabSelectedCR)) != null) {
                    paintChatMsg(mgraphics, allMessages.get(nameTabCR.get(this.indexTabSelectedCR)));
                }
            } catch (Exception e) {
            }
        }
        CanvasNinja.resetTrans(g);
        int i14 = this.w_extend;
        if (i14 > 0) {
            SupportPaint supportPaint = CanvasNinja.paintz;
            FrameImage frameImage = LoadDataManager.frameXam2;
            int i15 = this.arrX[1] + this.arrW[1];
            int i16 = this.margin;
            supportPaint.paintSingleBorderFrame(g, frameImage, (float) (i15 + i16), (float) this.arrY[1], (float) (i14 - i16), (float) this.arrH[1], 2, true);
            if (nameTabCR.size() > 0) {
                int i17 = 0;
                while (i17 < nameTabCR.size()) {
                    int i18 = i17;
                    CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameChatRieng, this.arrXCR[0], this.arrY[5] + ((this.marginOneTab + this.arrH[5]) * i17), this.arrW[5], i17 == this.indexTabSelectedCR, 0, false);
                    try {
                        if (isUnreadMsg.get(nameTabCR.get(i18)).booleanValue()) {
                            Image image = LoadDataManager.imgNewMsg;
                            g.drawImage(image, (float) ((this.arrXCR[0] + this.arrW[5]) - image.getRWidth()), (float) (this.arrY[5] + (i18 * (this.marginOneTab + this.arrH[5]))), this.arrW[5], 0);
                        }
                    } catch (Exception e2) {
                    }
                    try {
                        long diff = System.currentTimeMillis() - ((Message) allMessages.get(nameTabCR.get(i18)).get(allMessages.get(nameTabCR.get(i18)).size() - 1)).time;
                        int i19 = this.arrXCR[1];
                        int i20 = this.arrY[5];
                        int i21 = this.marginOneTab;
                        int i22 = this.arrH[5];
                        this.fontTextMsg.drawString(g, (diff / 60000) + " phút trước", i19, (i22 / 2) + i20 + ((i21 + i22) * i18) + this.margin, 1, true);
                    } catch (Exception e3) {
                    }
                    this.fontPaintUsername.drawString(g, nameTabCR.get(i18), this.arrXCR[2], this.arrY[5] + (i18 * (this.marginOneTab + this.arrH[5])) + this.margin, 0, true);
                    i17 = i18 + 1;
                }
                int i23 = i17;
            }
        }
        TField tField = this.tfChat;
        tField.x = this.arrX[9] + this.margin;
        tField.y = this.arrY[9] + (this.arrH[9] / 4);
        tField.painTextInputNoneBackGround(mgraphics);
        if (this.scroll != null) {
            int i24 = this.x;
            int i25 = this.y;
            int i26 = this.margin;
            mgraphics.setClip(i24, i25 + i26, this.w, this.h - (i26 * 2));
            mgraphics.translate(0, -this.scroll.cmy);
        }
        MyCommand myCommand = this.cmdClose;
        myCommand.x = (this.x + this.w) - 3;
        myCommand.y = ((int) (((float) CanvasNinja.h) - LoadDataManager.imgCloseChatBox.frameHeight)) / 2;
        myCommand.isPaint = true;
        myCommand.paintIconOnly(mgraphics);
    }

    public void keyPress(int keyCode) {
        if (keyCode == KEY.KEY_ENTER && this.tfChat.isFocused()) {
            commandTab(-3, 0);
        }
        if (this.tfChat.isFocused()) {
            TField tField = this.tfChat;
            tField.isChat = false;
            tField.keyPressed(keyCode);
        }
    }

    public void keyTyped() {
        if (this.tfChat.isFocused()) {
            this.tfChat.keyTyped();
        }
    }

    public void commandTab(int index, int subIndex) {
        super.commandTab(index, subIndex);
        switch (index) {
            case -3:
                if (!this.tfChat.getText().equals("")) {
                    String msg = this.tfChat.getText();
                    Res.outz("Gửi chat " + msg);
                    if (this.indexTabSelected == 5) {
                        try {
                            String username = nameTabCR.get(this.indexTabSelectedCR);
                            if (username != null) {
                                SendMessage.gI().privateMessage(username, msg);
                                Message newMesg = new Message(Player.myCharz().cName, msg, true);
                                Vector<Message> currentMessage = allMessages.get(username);
                                if (currentMessage == null) {
                                    currentMessage = new Vector<>();
                                }
                                currentMessage.add(newMesg);
                                allMessages.put(nameTabCR.get(this.indexTabSelectedCR), currentMessage);
                            }
                            this.tfChat.setTextFirst("");
                            return;
                        } catch (Exception e) {
                            return;
                        }
                    } else {
                        SendMessage.gI().chatToDefault((byte) 0, (byte) this.indexTabSelected, Player.myCharz().charID, msg, (byte) 5, -1);
                        this.tfChat.setTextFirst("");
                        return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }
}
