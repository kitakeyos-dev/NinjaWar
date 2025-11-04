// Class Version: 8
package ninjawar.screen.subtab;

import ninjawar.mylib.AudioManager;
import ninjawar.myscr.Player;
import ninjawar.screen.tab.TabFriend;
import ninjawar.supporter.SupportPaint;
import ninjawar.input.KEY;
import java.util.Collection;
import ninjawar.myscr.Res;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mylib.mGraphics;
import ninjawar.model.PartySearch;
import ninjawar.message.SendMessage;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.supporter.SupportTranslate;
import ninjawar.supporter.LoadDataManager;
import ninjawar.screen.tab.TabParty;
import ninjawar.input.TField;
import ninjawar.model.MyCommand;
import ninjawar.input.MyButton;
import ninjawar.mylib.mFont;

public class SubTabInviteParty extends SubTabScr
{
    public static mFont fontPaintName;
    public static mFont fontPaintNum;
    public static mFont fontPaintNumPage;
    public static SubTabInviteParty me;
    public MyButton[] btnApplys;
    MyCommand cmdSearch;
    int hOneTag;
    int hSys;
    int hTitle;
    int indexMinus;
    int indexPlus;
    int indexTabIconSelected;
    boolean[] isClickTagIcon;
    int marginLeftRight;
    int marginOneTab;
    int maxPage;
    public int numPage;
    int numTag;
    int numTagIcon;
    TField tfSearch;
    String title;
    int transOneTab;
    int wOneTag;
    int wSys;
    int wTitle;
    int xOneTag;
    int xTitle;
    int yAnimMinus;
    int yAnimPlus;
    int yAnimSearch;
    int[] yAnimTagIcon;
    int yOneTag;
    int yTitle;

    static {
        SubTabInviteParty.fontPaintName = mFont.fontPaintNameParty;
        SubTabInviteParty.fontPaintNum = mFont.fontPaintNumParty;
        SubTabInviteParty.fontPaintNumPage = mFont.tahoma_7b_white;
    }

    public SubTabInviteParty() {
        this.numPage = 0;
        this.title = "";
        this.maxPage = 1;
        this.numTagIcon = 3;
        this.isClickTagIcon = new boolean[3];
        this.yAnimTagIcon = new int[3];
        this.numTag = 4;
    }

    public static SubTabInviteParty gI() {
        if (SubTabInviteParty.me == null) {
            SubTabInviteParty.me = new SubTabInviteParty();
        }
        return SubTabInviteParty.me;
    }

    private void initApplys() {
        this.btnApplys = new MyButton[TabParty.vecPartySearchs.size()];
        this.btnApplys = new MyButton[TabParty.vecPartySearchs.size()];
        int n = 0;
        for (int i = 0; i < this.btnApplys.length; ++i) {
            final MyCommand myCommand = new MyCommand("", 99, this);
            myCommand.subAction = i;
            final MyButton[] btnApplys = this.btnApplys;
            final Image imgInvited = LoadDataManager.imgInvited;
            final FrameImage[] myButtons = LoadDataManager.myButtons;
            btnApplys[i] = new MyButton(imgInvited, myButtons[1], myButtons[3], 55, 0, SupportTranslate.getTextLangue("invite"), super.arrX[4], 0, myCommand);
            final MyButton myButton = this.btnApplys[i];
            myButton.setXY(super.arrX[4] - myButton.w / 2, super.arrY[4] + (this.hOneTag + super.margin) * n - myButton.h / 2);
            if (++n > this.numTag - 1) {
                n = 0;
            }
        }
    }

    private void invite(final int n) {
        this.btnApplys[n].isDone = true;
        SendMessage.gI().invitePlayerToParty(TabParty.vecPartySearchs.get(n).playerId);
    }

    private void paintTab0(final mGraphics mGraphics) {
        int n = 0;
        final int numPage = this.numPage;
        if (numPage > -1 && this.maxPage > -1) {
            int n2 = numPage * this.numTag;
            while (true) {
                final int numPage2 = this.numPage;
                final int numTag = this.numTag;
                if (n2 >= numPage2 * numTag + numTag) {
                    break;
                }
                if (n2 <= TabParty.vecPartySearchs.size() - 1) {
                    CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameBgTag, this.xOneTag, this.yOneTag + (this.hOneTag + super.margin) * n, this.wOneTag, false, 0, false);
                    mGraphics.drawImage(LoadDataManager.imgHeIcon[TabParty.vecPartySearchs.get(n2).sys], (float)super.arrX[1], (float)(super.arrY[1] - this.hSys / 2 + (this.hOneTag + super.margin) * n));
                    SubTabInviteParty.fontPaintName.drawString(mGraphics, TabParty.vecPartySearchs.get(n2).name, super.arrX[1] + this.wSys + 3, super.arrY[1] - SubTabInviteParty.fontPaintName.getHeight() + 5 + (this.hOneTag + super.margin) * n);
                    SubTabInviteParty.fontPaintName.drawString(mGraphics, TabParty.vecPartySearchs.get(n2).level, super.arrX[2], super.arrY[2] + (this.hOneTag + super.margin) * n, 3);
                    final int n3 = n + 1;
                    final MyButton[] btnApplys = this.btnApplys;
                    n = n3;
                    if (btnApplys != null) {
                        final MyButton myButton = btnApplys[n2];
                        n = n3;
                        if (myButton != null) {
                            myButton.paint(mGraphics);
                            n = n3;
                        }
                    }
                }
                ++n2;
            }
        }
    }

    @Override
    public void commandTab(int n, final int n2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Vào commandTab TabParrty:");
        sb.append(n);
        sb.append("_sub:");
        sb.append(n2);
        Res.outz(sb.toString());
        super.commandTab(n, n2);
        switch (n) {
            case 100: {
                final String text = this.tfSearch.getText();
                if (!text.equals("")) {
                    TabParty.vecPartySearchs = Res.findListCharByNameOrId(text, TabParty.vecPartySearchsTemp);
                }
                this.tfSearch.setTextFirst("");
                break;
            }
            case 99: {
                this.invite(n2);
                break;
            }
            case 2: {
                if (n2 >= 0 && n2 < this.btnApplys.length) {
                    n = this.numPage * this.numTag;
                    while (true) {
                        final int numPage = this.numPage;
                        final int numTag = this.numTag;
                        if (n >= numPage * numTag + numTag) {
                            break;
                        }
                        final MyButton[] btnApplys = this.btnApplys;
                        if (n < btnApplys.length && n2 <= btnApplys.length - 1) {
                            if (!btnApplys[n2].isDone) {
                                this.invite(n);
                            }
                        }
                        ++n;
                    }
                    break;
                }
            }
            case 1: {
                if (TabParty.vecPartySearchs.size() > 0) {
                    this.invite(Res.random(0, TabParty.vecPartySearchs.size() - 1));
                    break;
                }
                break;
            }
        }
    }

    public void init() {
        TabParty.vecPartySearchs.removeAllElements();
        TabParty.vecPartySearchs.addAll(TabParty.vecPartySearchsTemp);
        this.numPage = 0;
        if (TabParty.vecPartySearchs.size() > 0) {
            int maxPage;
            if (TabParty.vecPartySearchs.size() % this.numTag == 0) {
                maxPage = TabParty.vecPartySearchs.size() / this.numTag - 1;
            }
            else {
                maxPage = TabParty.vecPartySearchs.size() / this.numTag;
            }
            this.maxPage = maxPage;
        }
        else {
            this.numPage = -1;
            this.maxPage = -1;
        }
        final MyButton[] btns = new MyButton[2];
        super.btns = btns;
        final FrameImage[] myButtons = LoadDataManager.myButtons;
        btns[0] = new MyButton(myButtons[1], myButtons[3], 55, 0, SupportTranslate.getTextLangue("inviteWord"), 0, 0, new MyCommand("", 1, this));
        final MyButton[] btns2 = super.btns;
        final FrameImage[] myButtons2 = LoadDataManager.myButtons;
        btns2[1] = new MyButton(myButtons2[0], myButtons2[3], 55, 0, SupportTranslate.getTextLangue("inviteAll"), 0, 0, new MyCommand("", 2, this));
        this.hOneTag = (int)LoadDataManager.frameBgTag.frameHeight;
        super.w = 450;
        final int w = CanvasNinja.w;
        if (450 > w) {
            super.w = w - super.margin * 5;
        }
        this.wSys = LoadDataManager.imgHeIcon[0].getRWidth();
        this.hSys = LoadDataManager.imgHeIcon[0].getRHeight();
        super.margin = 5;
        this.marginLeftRight = 5 * 2;
        this.title = SupportTranslate.getTextLangue("group").toUpperCase();
        final int margin = super.margin;
        this.marginOneTab = margin;
        final int hOneTag = this.hOneTag;
        this.transOneTab = hOneTag + margin;
        int n = super.w - 14 - margin * 4;
        int n2 = hOneTag * this.numTag + margin * 6;
        if ((super.h = n2 + 14 + (int)LoadDataManager.frameTitle2.frameHeight / 2 + margin * 6 + super.btns[0].h + (int)LoadDataManager.framePartySearch.frameHeight + margin * 2) > CanvasNinja.h) {
            this.numTag = 4;
            this.numPage = 0;
            int maxPage2;
            if (TabParty.vecPartySearchs.size() % this.numTag == 0) {
                maxPage2 = TabParty.vecPartySearchs.size() / this.numTag - 1;
            }
            else {
                maxPage2 = TabParty.vecPartySearchs.size() / 5;
            }
            this.maxPage = maxPage2;
            final MyButton[] btns3 = new MyButton[2];
            super.btns = btns3;
            final FrameImage[] myButtons3 = LoadDataManager.myButtons;
            btns3[0] = new MyButton(myButtons3[1], myButtons3[3], 55, 0, SupportTranslate.getTextLangue("inviteWord"), 0, 0, new MyCommand("", 1, this));
            final MyButton[] btns4 = super.btns;
            final FrameImage[] myButtons4 = LoadDataManager.myButtons;
            btns4[1] = new MyButton(myButtons4[0], myButtons4[3], 55, 0, SupportTranslate.getTextLangue("inviteAll"), 0, 0, new MyCommand("", 2, this));
            this.hOneTag = (int)LoadDataManager.frameBgTag.frameHeight;
            super.w = 450;
            final int w2 = CanvasNinja.w;
            if (450 > w2) {
                super.w = w2 - super.margin * 5;
            }
            this.wSys = LoadDataManager.imgHeIcon[0].getRWidth();
            this.hSys = LoadDataManager.imgHeIcon[0].getRHeight();
            super.margin = 5;
            this.marginLeftRight = 5 * 2;
            this.title = SupportTranslate.getTextLangue("group").toUpperCase();
            final int margin2 = super.margin;
            this.marginOneTab = margin2;
            final int hOneTag2 = this.hOneTag;
            this.transOneTab = hOneTag2 + margin2;
            n = super.w - 14 - margin2 * 4;
            n2 = hOneTag2 * this.numTag + margin2 * 6;
            super.h = n2 + 14 + (int)LoadDataManager.frameTitle2.frameHeight / 2 + margin2 * 6 + super.btns[0].h + (int)LoadDataManager.framePartySearch.frameHeight + margin2 * 2;
        }
        this.initStart();
        this.hTitle = (int)LoadDataManager.frameTitle.frameHeight;
        final int wTitle = SubTabScr.fontPaintTile.getWidth(this.title) + super.margin * 8;
        this.wTitle = wTitle;
        final int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(8, wTitle, LoadDataManager.frameTitle);
        this.wTitle = fixSizeTagFrameUp;
        this.xTitle = super.x + (super.w - fixSizeTagFrameUp) / 2;
        this.yTitle = super.y - this.hTitle / 2;
        super.arrH = new int[] { n2, SubTabInviteParty.fontPaintName.getHeight(), SubTabInviteParty.fontPaintName.getHeight(), SubTabInviteParty.fontPaintName.getHeight(), 0, (int)LoadDataManager.frameMinus.frameHeight, (int)LoadDataManager.framePage.frameHeight, (int)LoadDataManager.framePlus.frameHeight, (int)LoadDataManager.framePartySearch.frameHeight, LoadDataManager.imgIconSearch.getRHeight(), (int)LoadDataManager.frameTabIconHanhTrang.frameHeight };
        final int wOneTag = n - super.margin * 2;
        this.wOneTag = wOneTag;
        final int n3 = wOneTag * 4 / 12;
        final int n4 = wOneTag * 2 / 12;
        final int n5 = wOneTag * 2 / 12;
        final int n6 = wOneTag * 4 / 12;
        final int[] arrW = { n, n3, n4, n5, n6, 0, 0, 0, 0, 0, 0 };
        final float frameWidth = LoadDataManager.frameMinus.frameWidth;
        arrW[5] = (int)frameWidth;
        arrW[6] = (int)(frameWidth * 1.5f);
        arrW[7] = (int)LoadDataManager.framePlus.frameWidth;
        arrW[8] = n / 3;
        arrW[9] = LoadDataManager.imgIconSearch.getRWidth();
        arrW[10] = (int)LoadDataManager.frameTabIconHanhTrang.frameWidth;
        super.arrW = arrW;
        final int n7 = arrW[5];
        final int margin3 = super.margin;
        final int n8 = n7 + margin3 * 2 + arrW[6] + arrW[7];
        final int x = super.x;
        final int n9 = x + 7 + this.marginLeftRight;
        final int xOneTag = n9 + margin3;
        this.xOneTag = xOneTag;
        final int[] arrX = { n9, xOneTag + margin3 * 5, xOneTag + n3 + n4 / 2, xOneTag + n3 + n4 + n5 / 2, xOneTag + n3 + n4 + n5 + n6 / 2, 0, 0, 0, 0, 0, 0 };
        final int w3 = super.w;
        arrX[5] = x + w3 / 2 - n8 / 2;
        final int n10 = w3 / 2;
        final int n11 = n8 / 2;
        final int n12 = arrW[5];
        arrX[6] = x + n10 - n11 + margin3 + n12;
        arrX[7] = x + w3 / 2 - n8 / 2 + margin3 * 2 + n12 + arrW[6];
        arrX[8] = xOneTag;
        arrX[9] = xOneTag + arrW[8] + margin3;
        arrX[10] = x + w3 - margin3 * 4 + 1;
        super.arrX = arrX;
        final int n13 = super.y + 7 + (int)LoadDataManager.frameTitle2.frameHeight / 2 + margin3 * 2;
        final int[] arrH = super.arrH;
        final int n14 = arrH[8] + n13 + margin3 * 2;
        final int yOneTag = n14 + margin3;
        this.yOneTag = yOneTag;
        final int hOneTag3 = this.hOneTag;
        final int n15 = hOneTag3 / 2;
        final int n16 = hOneTag3 / 2;
        final int n17 = hOneTag3 / 2;
        final int n18 = hOneTag3 / 2;
        final int n19 = arrH[0];
        final MyButton myButton = super.btns[0];
        final int h = myButton.h;
        final int n20 = (h - arrH[5]) / 2;
        final int n21 = (h - arrH[6]) / 2;
        final int n22 = (h - arrH[7]) / 2;
        final int n23 = (arrH[8] - arrH[9]) / 2;
        final MyCommand cmdClose = super.cmdClose;
        super.arrY = new int[] { n14, yOneTag + n15, yOneTag + n16, yOneTag + n17, yOneTag + n18, n14 + n19 + margin3 * 2 + n20, n14 + n19 + margin3 * 2 + n21, n14 + n19 + margin3 * 2 + n22, n13, n13 + n23, cmdClose.y + cmdClose.h + super.margin7 * 2 };
        myButton.setXY(arrX[0], n19 + n14 + margin3 * 2);
        final MyButton myButton2 = super.btns[1];
        myButton2.setXY(super.arrX[0] + super.arrW[0] - myButton2.w, super.arrY[0] + super.arrH[0] + super.margin * 2);
        this.initApplys();
        int n24 = 0;
        for (int i = 0; i < this.btnApplys.length; ++i) {
            final MyCommand myCommand = new MyCommand("", 99, this);
            myCommand.subAction = i;
            final MyButton[] btnApplys = this.btnApplys;
            final Image imgInvited = LoadDataManager.imgInvited;
            final FrameImage[] myButtons5 = LoadDataManager.myButtons;
            btnApplys[i] = new MyButton(imgInvited, myButtons5[1], myButtons5[3], 55, 0, SupportTranslate.getTextLangue("invite"), super.arrX[4], 0, myCommand);
            final MyButton myButton3 = this.btnApplys[i];
            myButton3.setXY(super.arrX[4] - myButton3.w / 2, super.arrY[4] + (this.hOneTag + super.margin) * n24 - myButton3.h / 2);
            if (++n24 > this.numTag - 1) {
                n24 = 0;
            }
        }
        final TField tfSearch = new TField();
        this.tfSearch = tfSearch;
        tfSearch.fontTField = mFont.tahoma_7b_brown;
        TField.CARET_COLOR = 8799779;
        tfSearch.setIputType(0);
        final TField tfSearch2 = this.tfSearch;
        tfSearch2.isKeyTyped = true;
        tfSearch2.setMaxTextLenght(30);
        final TField tfSearch3 = this.tfSearch;
        tfSearch3.x = super.arrX[8];
        tfSearch3.y = super.arrY[8];
        tfSearch3.width = super.arrW[8];
        tfSearch3.height = super.arrH[8];
        tfSearch3.updateMarginTField(7);
        this.cmdSearch = new MyCommand("", 100, this);
    }

    @Override
    public void keyPress(final int n) {
        if (this.tfSearch.isFocused()) {
            this.tfSearch.keyPressed(n);
            if (n == KEY.KEY_ENTER) {
                this.tfSearch.setFocus(false);
                this.cmdSearch.perform();
            }
        }
        else if (n == KEY.KEY_ENTER) {
            this.tfSearch.setFocus(true);
        }
    }

    @Override
    public void keyTyped() {
        if (this.tfSearch.isFocused()) {
            this.tfSearch.keyTyped();
        }
    }

    @Override
    public void paint(final mGraphics mGraphics) {
        CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameBgOrange2_0, (float)super.x, (float)super.y, (float)super.w, (float)super.h, 0, false);
        CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameTitle2, this.xTitle, this.yTitle, this.wTitle, false, 0, false);
        SubTabScr.fontPaintTile.drawString(mGraphics, this.title, this.wTitle / 2 + this.xTitle, this.hTitle / 2 + this.yTitle, 3);
        CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameBgCam, (float)super.arrX[0], (float)super.arrY[0], (float)super.arrW[0], (float)super.arrH[0], 10, false);
        SubTabScr.fontPaintTile.drawString(mGraphics, this.title, this.wTitle / 2 + this.xTitle, this.hTitle / 2 + this.yTitle, 3);
        if (this.indexTabIconSelected == 0) {
            this.paintTab0(mGraphics);
        }
        if (super.btns != null) {
            int n = 0;
            while (true) {
                final MyButton[] btns = super.btns;
                if (n >= btns.length) {
                    break;
                }
                if (this.indexTabIconSelected == 0 || n != 0) {
                    btns[n].paint(mGraphics);
                }
                ++n;
            }
        }
        CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.framePartySearch, super.arrX[8], super.arrY[8], super.arrW[8], this.tfSearch.isFocused(), SupportPaint.TYPE_NONE, true);
        this.tfSearch.painTextInputNoneBackGround(mGraphics);
        if (!this.tfSearch.isFocused() && this.tfSearch.getText().equals("")) {
            mFont.tahoma_place_holder.drawString(mGraphics, SupportTranslate.getTextLangue("searchParty"), super.arrX[8] + 7, super.arrH[8] / 2 + super.arrY[8], 4);
        }
        mGraphics.drawImage(LoadDataManager.imgIconSearch, (float)super.arrX[9], (float)(super.arrY[9] + this.yAnimSearch));
        int n2 = 0;
        while (true) {
            final int length = this.isClickTagIcon.length;
            int n3 = 1;
            if (n2 >= length) {
                break;
            }
            CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameTabIconHanhTrang, super.arrX[10], this.yAnimTagIcon[n2] + (super.arrY[10] + (super.arrH[10] + super.margin * 2) * n2), super.arrW[10], this.indexTabIconSelected != n2, 0, false);
            final FrameImage frameImage = LoadDataManager.frameIconTabPartys[n2];
            if (frameImage != null) {
                if (this.indexTabIconSelected != n2) {
                    n3 = 0;
                }
                final float n4 = (float)super.arrX[10];
                final float n5 = (super.arrW[10] - frameImage.frameWidth) / 2.0f;
                final int margin = super.margin;
                final float n6 = (float)margin;
                final int n7 = super.arrY[10];
                final int n8 = this.yAnimTagIcon[n2];
                final int n9 = super.arrH[10];
                frameImage.drawFrame(n3, n4 + n5 + n6, n7 + n8 + (margin * 2 + n9) * n2 + (n9 - frameImage.frameHeight) / 2.0f - 1.0f, mGraphics);
            }
            ++n2;
        }
        LoadDataManager.frameNextPreviousKhoDo[1].drawFrame(this.indexMinus, (float)super.arrX[5], (float)(super.arrY[5] + this.yAnimMinus), mGraphics);
        CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.framePage, super.arrX[6], super.arrY[6], super.arrW[6], false, 0, false);
        final mFont fontPaintNumPage = SubTabInviteParty.fontPaintNumPage;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.numPage + 1);
        sb.append("/");
        sb.append(this.maxPage + 1);
        fontPaintNumPage.drawString(mGraphics, sb.toString(), super.arrW[6] / 2 + super.arrX[6], super.arrY[6] + 5, 2);
        LoadDataManager.frameNextPreviousKhoDo[0].drawFrame(this.indexPlus, (float)super.arrX[7], (float)(super.arrY[7] + this.yAnimPlus), mGraphics);
        CanvasNinja.resetTrans(mGraphics);
        super.cmdClose.paintIconOnly(mGraphics);
    }

    public void selectTab(final int n) {
        switch (n) {
            default: {
                CanvasNinja.startOKDlg("Tính năng đang phát triển");
                break;
            }
            case 1: {
                TabParty.vecPartySearchs.removeAllElements();
                for (int i = 0; i < TabFriend.vecFriends.size(); ++i) {
                    TabParty.vecPartySearchs.add(new PartySearch(TabFriend.vecFriends.get(i)));
                }
                this.indexTabIconSelected = n;
                break;
            }
            case 0: {
                TabParty.vecPartySearchs.removeAllElements();
                TabParty.vecPartySearchs.addAll(TabParty.vecPartySearchsTemp);
                this.indexTabIconSelected = n;
                break;
            }
        }
    }

    public void show() {
        this.init();
        this.showTab();
    }

    @Override
    public void update() {
        if (super.btns != null) {
            int n = 0;
            while (true) {
                final MyButton[] btns = super.btns;
                if (n >= btns.length) {
                    break;
                }
                if (this.indexTabIconSelected == 0 || n != 0) {
                    btns[n].update();
                }
                ++n;
            }
        }
        int n2 = 0;
        while (true) {
            final boolean[] isClickTagIcon = this.isClickTagIcon;
            if (n2 >= isClickTagIcon.length) {
                break;
            }
            if (isClickTagIcon[n2]) {
                final int[] yAnimTagIcon = this.yAnimTagIcon;
                if (++yAnimTagIcon[n2] >= 2) {
                    yAnimTagIcon[n2] = 0;
                    isClickTagIcon[n2] = false;
                }
            }
            ++n2;
        }
        this.indexMinus = 0;
        this.yAnimMinus = 0;
        this.yAnimPlus = 0;
        this.indexPlus = 0;
        this.yAnimSearch = 0;
        final int numPage = this.numPage;
        if (numPage > -1 && this.maxPage > -1) {
            int n3 = numPage * this.numTag;
            while (true) {
                final int numPage2 = this.numPage;
                final int numTag = this.numTag;
                if (n3 >= numPage2 * numTag + numTag) {
                    break;
                }
                final MyButton[] btnApplys = this.btnApplys;
                if (n3 <= btnApplys.length - 1) {
                    if (btnApplys != null) {
                        final MyButton myButton = btnApplys[n3];
                        if (myButton != null) {
                            myButton.update();
                        }
                    }
                }
                ++n3;
            }
        }
        this.tfSearch.update();
    }

    @Override
    public void updateKey() {
        super.cmdClose.updateIconOnly();
        if (super.btns != null) {
            int n = 0;
            while (true) {
                final MyButton[] btns = super.btns;
                if (n >= btns.length) {
                    break;
                }
                if (this.indexTabIconSelected == 0 || n != 0) {
                    btns[n].updatePointer();
                }
                ++n;
            }
        }
        if (this.numPage > -1 && this.maxPage > -1) {
            if (CanvasNinja.isPointerRelease()) {
                if (CanvasNinja.isPoint(super.arrX[5], super.arrY[5], super.arrW[5], super.arrH[5])) {
                    this.indexMinus = 1;
                    this.yAnimMinus = 2;
                    CanvasNinja.clearAllPointer();
                    if (--this.numPage < 0) {
                        this.numPage = this.maxPage;
                    }
                }
                if (CanvasNinja.isPoint(super.arrX[7], super.arrY[7], super.arrW[7], super.arrH[7])) {
                    this.indexPlus = 1;
                    this.yAnimPlus = 2;
                    CanvasNinja.clearAllPointer();
                    if (++this.numPage > this.maxPage) {
                        this.numPage = 0;
                    }
                }
            }
            int n2 = this.numPage * this.numTag;
            while (true) {
                final int numPage = this.numPage;
                final int numTag = this.numTag;
                if (n2 >= numPage * numTag + numTag) {
                    break;
                }
                final MyButton[] btnApplys = this.btnApplys;
                if (n2 <= btnApplys.length - 1) {
                    if (btnApplys != null) {
                        final MyButton myButton = btnApplys[n2];
                        if (myButton != null) {
                            myButton.updatePointer();
                        }
                    }
                }
                ++n2;
            }
        }
        if (CanvasNinja.isPointerRelease() && CanvasNinja.isPoint(super.arrX[9], super.arrY[9], super.arrW[9], super.arrH[9])) {
            CanvasNinja.clearAllPointer();
            ++this.yAnimSearch;
            this.cmdSearch.perform();
        }
        if (CanvasNinja.isPointerRelease()) {
            for (int i = 0; i < this.isClickTagIcon.length; ++i) {
                final int n3 = super.arrX[10];
                final int n4 = super.arrY[10];
                final int n5 = super.arrH[10];
                if (CanvasNinja.isPoint(n3, n4 + (super.margin * 2 + n5) * i, super.arrW[10], n5)) {
                    AudioManager.buttonClick();
                    this.isClickTagIcon[i] = true;
                    this.selectTab(i);
                    CanvasNinja.clearAllPointer();
                }
            }
        }
        this.tfSearch.updateFocus2();
    }
}
