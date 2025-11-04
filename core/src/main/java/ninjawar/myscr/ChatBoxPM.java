package ninjawar.myscr;

import java.util.Vector;
import ninjawar.input.KEY;
import ninjawar.input.MyButton;
import ninjawar.input.TField;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.ChatBox;
import ninjawar.screen.tab.TabScr;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

public class ChatBoxPM extends TabScr {
    public static boolean isPaintMessage = false;
    public static boolean isPing = false;
    public static ChatBoxPM me;
    private MyButton btnDelAll;
    public MyCommand cmdEmoji;
    public MyCommand cmdSendChat;
    public mFont fontPaintMyName = mFont.fontPaintMyName;
    public mFont fontPaintTab = mFont.tahoma_brown_dv;
    public mFont fontPaintTabFocus = mFont.tahoma_brown_focus_dv;
    public mFont fontPaintUsername = mFont.fontChatBoxUserName;
    public mFont fontTextMsg = mFont.tahoma_7_brown_small;
    public mFont fontTitleNews = mFont.tahoma_brown_dv;
    private int hChatBox = 0;
    private int hTitle;
    private int indexTabSelected = -1;
    private boolean[] isClickTab;
    private int marginOneTab;
    private String[] nameTab;
    private TField tfChat;
    private String title = "";
    private int wTitle;
    private int w_extend = 0;
    private int w_old;
    private int xTitle;
    private int[] yAnimTab;
    private int yTitle;

    public static ChatBoxPM gI() {
        if (me == null) {
            me = new ChatBoxPM();
        }
        return me;
    }

    private String initLongText(String text) {
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

    public void paintChatMsg(mGraphics g, Vector<ChatBox.Message> vtMessages) {
        Image muiten;
        int x_Msg;
        Vector<ChatBox.Message> vector = vtMessages;
        if (vector != null && !vtMessages.isEmpty()) {
            int[] arrYC = new int[vtMessages.size()];
            arrYC[0] = (this.arrY[10] - this.margin) - this.fontTextMsg.getHeightH(initLongText(vector.get(vtMessages.size() - 1).content));
            for (int i = 0; i < vtMessages.size(); i++) {
                ChatBox.Message message = vector.get((vtMessages.size() - i) - 1);
                String textDisplay = initLongText(message.content);
                int textWidth = this.fontTextMsg.getWidth(textDisplay);
                int textHeight = this.fontTextMsg.getHeightH(textDisplay);
                Image muiten2 = LoadDataManager.imgMuitentrai;
                if (i > 0) {
                    arrYC[i] = (arrYC[i - 1] - (this.margin * 5)) - textHeight;
                }
                if (message.isMyChat) {
                    int i2 = this.arrW[1];
                    int i3 = this.margin;
                    Image muiten3 = LoadDataManager.imgMuitenphai;
                    mFont mfont = this.fontPaintMyName;
                    mfont.drawString(g, message.username, i2 - (i3 * 2), arrYC[i] - (i3 * 2), 1, true);
                    muiten = muiten3;
                    x_Msg = (i2 - (i3 * 4)) - textWidth;
                } else {
                    int i4 = this.margin;
                    int x_Msg2 = i4 * 2;
                    mFont mfont2 = this.fontPaintUsername;
                    mfont2.drawString(g, message.username, x_Msg2, arrYC[i] - (i4 * 2), 0, true);
                    muiten = muiten2;
                    x_Msg = x_Msg2;
                }
                Image muiten4 = LoadDataManager.imgMuitentrai;
                int i5 = muiten4.width;
                int xMuiten = x_Msg - (i5 / 2);
                int i6 = (arrYC[i] + (textHeight / 2)) - (muiten4.height / 2);
                int i7 = this.margin;
                int yMuiten = i6 + i7;
                if (message.isMyChat) {
                    xMuiten = ((x_Msg + textWidth) + (i7 * 2)) - (i5 / 2);
                }
                int xMuiten2 = xMuiten;
                CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameChatMsg, (float) x_Msg, (float) arrYC[i], (float) ((i7 * 2) + textWidth), (float) (textHeight + (i7 * 2)), 7, true);
                g.drawImage(muiten, (float) xMuiten2, (float) yMuiten, 0, true);
                mFont mfont3 = this.fontTextMsg;
                int i8 = x_Msg + this.margin;
                int i9 = xMuiten2;
                int xMuiten3 = arrYC[i] + this.margin7;
                int i10 = yMuiten;
                int i11 = x_Msg;
                mfont3.drawString(g, textDisplay, i8, xMuiten3, 0, true);
                this.hChatBox = arrYC[i] + this.margin;
            }
        }
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        mGraphics mgraphics2 = g;
        CanvasNinja.paintz.paintSingleBorderFrame(mgraphics2, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        CanvasNinja.paintz.paintTagFrame(mgraphics2, LoadDataManager.frameTitle2, this.xTitle, this.yTitle, this.wTitle, false, 0, false);
        TabScr.fontPaintTile.drawString(g, this.title, (this.wTitle / 2) + this.xTitle, (this.hTitle / 2) + this.yTitle, 3);
        paintTopTab(g);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameChatBox, (float) this.arrX[1], (float) this.arrY[1], (float) this.arrW[1], (float) this.arrH[1], 0, false);
        paintListTab(g);
        if (this.btnDelAll == null) {
            mFont mfont = mFont.tahoma_7b_white;
            FrameImage frameImage = LoadDataManager.myButtons[1];
            this.btnDelAll = new MyButton(mfont, frameImage, frameImage, 70, 2, "Xóa hết", 0, 0, new MyCommand("", 696969, this));
        }
        MyButton myButton = this.btnDelAll;
        int i = this.arrX[5];
        int i2 = this.margin;
        int i3 = this.arrY[5];
        int i4 = this.marginOneTab;
        int i5 = this.arrH[5];
        myButton.setXY(i + i2, ((i3 + ((i4 + i5) * 5)) + (i5 / 2)) - i2);
        this.btnDelAll.paintButton(mgraphics, false);
        this.cmdClose.paintIconOnly(mgraphics);
        this.cmdEmoji.paintIconOnly(mgraphics);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameSendChat, this.arrX[7], this.arrY[7], this.arrW[7], false);
        this.cmdSendChat.paintIconOnly(mgraphics);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameChatBoxInput, this.arrX[9], this.arrY[9], this.arrW[9], true, SupportPaint.TYPE_NONE, true);
        paintChatContent(g);
        this.tfChat.painTextInputNoneBackGround(mgraphics);
    }

    private void paintChatContent(mGraphics g) {
        int i = this.arrX[1];
        int i2 = this.arrY[1];
        int i3 = this.margin;
        g.setClip(i, i2 + i3, this.arrW[1], this.arrH[1] - (i3 * 2));
        g.translate(this.arrX[1], this.scroll.cmy);
        String[] strArr = this.nameTab;
        if (!(strArr == null || strArr.length == 0 || ChatBox.allMessages.get(strArr[this.indexTabSelected]) == null)) {
            paintChatMsg(g, ChatBox.allMessages.get(this.nameTab[this.indexTabSelected]));
        }
        CanvasNinja.resetTrans(g);
    }

    private void paintTopTab(mGraphics g) {
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameUserNameTitle, this.arrX[1] + 1, this.arrY[1] - 25, this.arrW[1] - 2, false);
        try {
            mFont mfont = this.fontPaintUsername;
            mfont.drawString(g, this.nameTab[this.indexTabSelected], this.arrX[1] + 1 + 5, (((float) (this.arrY[1] - 22)) + (LoadDataManager.frameInfoTitle.frameHeight / 2.0f)) - ((float) (mfont.getHeight() / 2)), 0);
        } catch (Exception e) {
            mFont mfont2 = this.fontPaintUsername;
            mfont2.drawString(g, "Không có ai", this.arrX[1] + 1 + 5, (((float) (this.arrY[1] - 22)) + (LoadDataManager.frameInfoTitle.frameHeight / 2.0f)) - ((float) (mfont2.getHeight() / 2)), 0);
        }
        Image image = LoadDataManager.imgXoa;
        g.drawImage(image, (float) (((((this.arrX[1] + 1) + this.arrW[1]) - 2) - 7) - image.getRWidth()), (float) (this.arrY[1] - 23));
        String[] strArr = this.nameTab;
        if (strArr != null && strArr.length != 0 && !Player.myCharz().isMyFriend(this.nameTab[this.indexTabSelected])) {
            Image image2 = LoadDataManager.imgAdd;
            g.drawImage(image2, (float) (((((this.arrX[1] + 1) + this.arrW[1]) - 2) - 14) - (image2.getRWidth() * 2)), (float) (this.arrY[1] - 23));
        }
    }

    private void paintListTab(mGraphics g) {
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgCam, (float) (this.arrX[5] - 3), (float) (this.arrY[5] - 3), (float) (this.arrW[5] + 6), (float) (((this.marginOneTab + this.arrH[5]) * 5) + 3), 10, false);
        String[] strArr = this.nameTab;
        if (strArr != null && strArr.length != 0) {
            int i = 0;
            while (i < this.isClickTab.length) {
                CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameChatRieng, this.arrX[5], this.arrY[5] + ((this.marginOneTab + this.arrH[5]) * i) + this.yAnimTab[i], this.arrW[5], i == this.indexTabSelected, 0, false);
                mFont.tahoma_7_brown_small.drawString(g, this.nameTab[i], this.arrX[5] + 5, this.arrY[5] + ((this.marginOneTab + this.arrH[5]) * i) + this.margin, 0, true);
                try {
                    ChatBox.gI();
                    if (ChatBox.isUnreadMsg.get(this.nameTab[i]).booleanValue()) {
                        Image image = LoadDataManager.imgNewMsg;
                        g.drawImage(image, (float) ((this.arrX[5] + this.arrW[5]) - image.getRWidth()), (float) (this.arrY[5] + ((this.marginOneTab + this.arrH[5]) * i)), this.arrW[5], 0);
                    }
                } catch (Exception e) {
                }
                try {
                    long now = System.currentTimeMillis();
                    ChatBox.gI();
                    ChatBox.gI();
                    int i2 = (this.arrX[5] + this.arrW[5]) - 3;
                    int i3 = this.arrY[5];
                    int i4 = this.marginOneTab;
                    int i5 = this.arrH[5];
                    this.fontTextMsg.drawString(g, ((now - ((ChatBox.Message) ChatBox.allMessages.get(this.nameTab[i]).get(ChatBox.allMessages.get(this.nameTab[i]).size() - 1)).time) / 60000) + " phút trước", i2, i3 + ((i4 + i5) * i) + this.margin + (i5 / 2), 1, true);
                } catch (Exception e2) {
                }
                i++;
            }
        }
    }

    public void resetSelected() {
        this.indexTabSelected = 0;
    }

    public void init() {
        resetSelected();
        int i = this.margin;
        int i2 = ((CanvasNinja.w / 3) * 2) + (i * 2);
        this.w_old = i2;
        this.w_extend = 0;
        int i3 = i2 + 0;
        this.w = i3;
        int i4 = i3 / 2;
        int i5 = (CanvasNinja.h / 4) * 3;
        this.h = i5;
        int i6 = CanvasNinja.w;
        if (i3 > i6) {
            this.w = i6 - (i * 5);
        }
        int i7 = CanvasNinja.h;
        if (i5 > i7) {
            this.h = i7 - (i * 2);
        }
        initStart();
        float f = LoadDataManager.frameTitle2.frameHeight;
        float f2 = LoadDataManager.frameIconSend.frameHeight;
        this.arrH = new int[]{(int) f, (((this.h - (((int) f) - 1)) - (13 * 2)) - this.margin) - 40, LoadDataManager.imgDongSE.getRHeight(), this.fontTitleNews.getHeight(), 0, (int) LoadDataManager.frameTabChatBox.frameHeight, LoadDataManager.imgImojiIcon.getRHeight(), (int) LoadDataManager.frameSendChat.frameHeight, (int) f2, (int) f2};
        int wTagName = ((int) LoadDataManager.frameTabChatBox.frameWidth) * 5;
        int i8 = this.margin;
        int wKhungTrang = (((((this.w_old - 15) - i8) - 15) - wTagName) - i8) - 15;
        this.wTitle = this.fontTitleNews.getWidth(SupportTranslate.getTextLangue("news").toUpperCase()) + (this.margin7 * 2);
        int wDuongNgang = (LoadDataManager.imgDongSE.getRWidth() * 2) + LoadDataManager.imgDong.getRWidth();
        int xKhungTrang = ((this.w_old - 15) - wKhungTrang) - 15;
        int i9 = this.wTitle;
        this.xTitle = (xKhungTrang + (wKhungTrang / 2)) - (i9 / 2);
        int[] iArr = {i9, wKhungTrang, wDuongNgang, 0, i9 - (this.margin * 4), wTagName, LoadDataManager.imgImojiIcon.getRWidth(), 40, (int) LoadDataManager.frameIconSend.frameWidth, ((((xKhungTrang + wKhungTrang) - 40) - (10 * 2)) - LoadDataManager.imgImojiIcon.getRWidth()) - xKhungTrang};
        this.arrW = iArr;
        int i10 = this.x;
        int i11 = iArr[7];
        this.arrX = new int[]{this.xTitle + i10, i10 + xKhungTrang, ((i10 + xKhungTrang) + (wKhungTrang / 2)) - (iArr[2] / 2), i10 + xKhungTrang + (wKhungTrang / 2), i10 + xKhungTrang + (this.margin * 2), i10 + 15, i10 + xKhungTrang, ((i10 + xKhungTrang) + wKhungTrang) - iArr[7], ((((i10 + xKhungTrang) + wKhungTrang) - i11) + (i11 / 2)) - (iArr[8] / 2), i10 + xKhungTrang + iArr[6] + 10, i10 + xKhungTrang};
        int yKhungTrang = (this.y - 1) + 50;
        this.marginOneTab = 10;
        Vector<String> vector = ChatBox.nameTabCR;
        String[] strArr = (String[]) vector.toArray(new String[vector.size()]);
        this.nameTab = strArr;
        int numTabName = strArr.length;
        int[] iArr2 = this.arrH;
        int yFirstTabName = (yKhungTrang + ((iArr2[1] - ((iArr2[5] * 5) + (this.marginOneTab * 4))) / 2)) - 15;
        this.yAnimTab = new int[numTabName];
        this.isClickTab = new boolean[numTabName];
        int i12 = this.margin;
        int i13 = this.y;
        int i14 = this.h;
        int[] iArr3 = this.arrH;
        this.arrY = new int[]{this.y - 1, yKhungTrang, yKhungTrang + (i12 * 3) + iArr2[3], yKhungTrang + (i12 * 2), yKhungTrang + (i12 * 3) + iArr2[3] + LoadDataManager.imgDongSE.getRHeight() + (this.margin * 2), yFirstTabName, (i13 + i14) - 35, (i13 + i14) - 35, (((i13 + i14) - 35) + (iArr3[7] / 2)) - (iArr3[8] / 2), (i13 + i14) - 35, (yKhungTrang + iArr3[1]) - 10};
        MyCommand myCommand = new MyCommand("", -2, this);
        this.cmdEmoji = myCommand;
        myCommand.setPosPaint(this.arrX[6], this.arrY[6], LoadDataManager.imgImojiIcon);
        this.cmdEmoji.isClose = true;
        MyCommand myCommand2 = new MyCommand("", -3, this);
        this.cmdSendChat = myCommand2;
        myCommand2.setPosPaint(this.arrX[8], this.arrY[8], LoadDataManager.frameIconSend);
        this.cmdSendChat.isClose = true;
        int i15 = this.arrX[1];
        int i16 = this.arrY[1];
        int i17 = this.arrW[1];
        int[] iArr4 = this.arrH;
        int numTabName2 = iArr4[1];
        int i18 = numTabName;
        initScroll(i15, i16, i17, numTabName2, numTabName2, iArr4[7] - (this.margin * 2));
        TField tField = new TField();
        this.tfChat = tField;
        tField.x = this.arrX[9] + this.margin;
        int i19 = this.arrY[9];
        int i20 = this.arrH[9];
        tField.y = i19 + (i20 / 4);
        tField.width = this.arrW[9];
        tField.height = i20;
        if (!NinjaMidlet.isUseIOSSpec) {
            tField.isKeyTyped = true;
        }
        tField.setMaxTextLenght(60);
        this.tfChat.setFocus(false);
        this.tfChat.setIputType(0);
        TField tField2 = this.tfChat;
        mFont mfont = this.fontTextMsg;
        tField2.fontTField = mfont;
        tField2.fontPaint = mfont;
        TField.CARET_COLOR = 8799779;
        String upperCase = SupportTranslate.getTextLangue("msg").toUpperCase();
        this.title = upperCase;
        this.hTitle = (int) LoadDataManager.frameTitle.frameHeight;
        int width = TabScr.fontPaintTile.getWidth(upperCase) + (this.margin * 8);
        this.wTitle = width;
        int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(8, width, LoadDataManager.frameTitle);
        this.wTitle = fixSizeTagFrameUp;
        this.xTitle = this.x + ((this.w - fixSizeTagFrameUp) / 2);
        this.yTitle = this.y - (this.hTitle / 2);
    }

    public void update() {
        this.tfChat.update();
        ChatBox.gI();
        if (!ChatBox.isUnreadMsg.values().contains(true)) {
            MapScr.isHaveUnReadMsg = false;
        }
        this.btnDelAll.updatePointer();
        this.btnDelAll.update();
        int i = 0;
        while (true) {
            int[] iArr = this.yAnimTab;
            if (i < iArr.length) {
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
            } else {
                return;
            }
        }
    }

    public void updateKey() {
        updateKeyInputChat();
        this.cmdClose.updateIconOnly();
        this.cmdEmoji.updateIconOnly();
        this.cmdSendChat.updateIconOnly();
        if (CanvasNinja.isPointerRelease()) {
            for (int i = 0; i < this.isClickTab.length; i++) {
                int i2 = this.arrX[5];
                int i3 = this.arrY[5];
                int i4 = this.marginOneTab;
                int i5 = this.arrH[5];
                if (CanvasNinja.isPoint(i2, i3 + ((i4 + i5) * i), this.arrW[5], i5)) {
                    CanvasNinja.clearAllPointer();
                    this.isClickTab[i] = true;
                    this.indexTabSelected = i;
                    ChatBox.gI();
                    ChatBox.isUnreadMsg.put(this.nameTab[i], false);
                }
            }
            handleTopTabClick();
            if (CanvasNinja.isPoint(this.arrX[7], this.arrY[7], this.arrW[7], this.arrH[7])) {
                CanvasNinja.clearAllPointer();
                this.cmdSendChat.perform();
            }
        }
    }

    private void handleTopTabClick() {
        if (CanvasNinja.isPoint(((((this.arrX[1] + 1) + this.arrW[1]) - 2) - 7) - LoadDataManager.imgXoa.getRWidth(), this.arrY[1] - 23, LoadDataManager.imgXoa.getRWidth(), LoadDataManager.imgXoa.getRHeight())) {
            CanvasNinja.clearAllPointer();
            Res.outz("Xoa thang nay");
            ChatBox.gI();
            ChatBox.nameTabCR.remove(this.nameTab[this.indexTabSelected]);
            ChatBox.gI();
            Vector<String> vector = ChatBox.nameTabCR;
            ChatBox.gI();
            this.nameTab = (String[]) vector.toArray(new String[ChatBox.nameTabCR.size()]);
            return;
        }
        String[] strArr = this.nameTab;
        if (strArr != null && strArr.length != 0 && CanvasNinja.isPoint(((((this.arrX[1] + 1) + this.arrW[1]) - 2) - 14) - (LoadDataManager.imgAdd.getRWidth() * 2), this.arrY[1] - 23, LoadDataManager.imgAdd.getRWidth(), LoadDataManager.imgAdd.getRHeight())) {
            CanvasNinja.clearAllPointer();
            if (!Player.myCharz().isMyFriend(this.nameTab[this.indexTabSelected])) {
                Player ch = MapScr.findCharInMap(this.nameTab[this.indexTabSelected]);
                SendMessage.gI().requestAddFriend(ch.charID, ch.cName, (byte) ch.classId, (short) ch.clevel);
            }
        }
    }

    public void updateKeyInputChat() {
        TField tField = this.tfChat;
        if (CanvasNinja.isPointerHoldOrHover(tField.x, tField.y, tField.width, tField.height) || CanvasNinja.isPointerHoldOrHover(this.arrX[9], this.arrY[9], this.arrW[9], this.arrH[9]) || this.tfChat.isFocused()) {
            Res.outz("DA NHAN VAO THANH TIM KIEM");
        }
        this.cmdSendChat.updateIconOnly();
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
            return;
        }
        this.tfChat.setFocus(false);
    }

    public void show() {
        init();
        TField tField = this.tfChat;
        mFont mfont = this.fontTextMsg;
        tField.fontTField = mfont;
        tField.fontPaint = mfont;
        TField.CARET_COLOR = 8799779;
        String[] strArr = this.nameTab;
        if (!(strArr == null || strArr.length == 0)) {
            ChatBox.gI();
            ChatBox.isUnreadMsg.put(this.nameTab[0], false);
        }
        showTab();
    }

    public void show(String username) {
        init();
        TField tField = this.tfChat;
        mFont mfont = this.fontTextMsg;
        tField.fontTField = mfont;
        tField.fontPaint = mfont;
        TField.CARET_COLOR = 8799779;
        ChatBox.gI();
        if (ChatBox.nameTabCR.contains(username)) {
            ChatBox.gI();
            this.indexTabSelected = ChatBox.nameTabCR.indexOf(username);
        } else {
            ChatBox.gI();
            ChatBox.nameTabCR.add(username);
            init();
            ChatBox.gI();
            this.indexTabSelected = ChatBox.nameTabCR.size() - 1;
        }
        showTab();
    }

    public void keyPress(int keyCode) {
        if (keyCode == KEY.KEY_ENTER && this.tfChat.isFocused()) {
            commandTab(-3, 0);
        }
        if (this.tfChat.isFocused()) {
            this.tfChat.keyPressed(keyCode);
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
                sendChatMessage();
                return;
            case 696969:
                clearChatHistory();
                return;
            default:
                return;
        }
    }

    private void sendChatMessage() {
        String msg = this.tfChat.getText();
        if (!msg.trim().equals("")) {
            try {
                String username = this.nameTab[this.indexTabSelected];
                Res.outz("Gui tin " + msg + "cho " + username);
                if (username != null) {
                    SendMessage.gI().privateMessage(username, msg);
                    ChatBox.Message newMesg = new ChatBox.Message(Player.myCharz().cName, msg, true);
                    Vector<ChatBox.Message> currentMessage = ChatBox.allMessages.get(username);
                    if (currentMessage == null) {
                        currentMessage = new Vector<>();
                    }
                    currentMessage.add(newMesg);
                    ChatBox.allMessages.put(username, currentMessage);
                }
                this.tfChat.setTextFirst("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void clearChatHistory() {
        Res.outz("Chay vo day");
        ChatBox.nameTabCR.removeAllElements();
        this.nameTab = null;
    }
}
