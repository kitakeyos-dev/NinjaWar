// Class Version: 8
package ninjawar.screen;

import ninjawar.input.KEY;
import ninjawar.input.MyButton;
import ninjawar.message.SendMessage;
import ninjawar.model.ActionInterface;
import ninjawar.model.EffSky;
import ninjawar.model.MyCommand;
import ninjawar.mylib.BaseScreen;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.*;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

import java.util.Iterator;
import java.util.Vector;

public class ChooseCharScreen extends BaseScreen implements ActionInterface {
    public static Player charTemp;
    public static mFont fontPaintTitle;
    public static ChooseCharScreen me;
    public int[] arrH;
    public int[] arrW;
    public int[] arrX;
    public int[] arrY;
    MyButton btnBack;
    MyButton btnJoin;
    public Player[] character;
    MyCommand cmdBack;
    MyCommand cmdJoin;
    public mFont fontPaintLevel;
    public mFont fontPaintLevelFocus;
    public mFont fontPaintText;
    public mFont fontPaintTextFocus;
    int h;
    int hChar;
    int hDat;
    int hLogo;
    public int hName;
    public int indexEff;
    int indexSelected;
    public boolean isFocusTenNhanVat;
    public int margin;
    public int margin7;
    int marginXChar;
    public Vector<EffSky> vecSkys;
    int w;
    int wCucDat;
    int wDat;
    int wLevel;
    int wLogo;
    public int wName;
    int x;
    int xDat;
    int xLogo;
    public int xName;
    int y;
    public int[] yAnim;
    int yDat;
    int yLogo;
    int yMoreUp;
    public int yName;

    static {
        ChooseCharScreen.fontPaintTitle = mFont.tahoma_7b_white;
    }

    public ChooseCharScreen() {
        this.vecSkys = new Vector<EffSky>();
        this.margin = 5;
        this.margin7 = 7;
        final mFont tahoma_7b_brown = mFont.tahoma_7b_brown;
        this.fontPaintLevel = tahoma_7b_brown;
        this.fontPaintLevelFocus = tahoma_7b_brown;
        this.fontPaintText = mFont.tahoma_brown_dv;
        this.fontPaintTextFocus = mFont.tahoma_brown_focus_dv;
        this.hChar = 90;
        this.wLevel = 70;
    }

    public static ChooseCharScreen gI() {
        if (ChooseCharScreen.me == null) {
            ChooseCharScreen.me = new ChooseCharScreen();
        }
        return ChooseCharScreen.me;
    }

    public static void paintBg(final mGraphics mGraphics) {
        MapScr.cmy = (MapScr.cmtoY = MyTile.size * MyTile.tmh - CanvasNinja.h);
        mGraphics.translate(-(MapScr.cmx = (MapScr.cmtoX = MyTile.size)), -MapScr.cmy + MyTile.size * 2);
        CanvasNinja.paintBGGameScr(mGraphics);
        mGraphics.translate(-MapScr.cmx, -MapScr.cmy);
        MyTile.paintTileMapSpec(mGraphics);
    }

    @Override
    public void commandTab(final int n, final int n2) {
        final StringBuilder sb = new StringBuilder();
        sb.append("vo ông nội commandTab ChooseCharScreen ");
        sb.append(n);
        Res.outz(1, sb.toString());
        switch (n) {
            case 11: {
                SendMessage.gI().selectChar(this.indexSelected);
                break;
            }
            case -11: {
                SendMessage.gI().logOut();
                LoginScr.isLogOut = true;
                CanvasNinja.resetLogin();
                SplashScr.gI().switchToMe();
                break;
            }
        }
    }

    public void init() {
        this.w = 450;
        this.h = 260;
        this.x = (CanvasNinja.w - 450) / 2;
        this.y = (CanvasNinja.h - 260) / 2;
        this.arrH = new int[]{(int) LoadDataManager.frameTitle.frameHeight};
        final int width = ChooseCharScreen.fontPaintTitle.getWidth(SupportTranslate.getTextLangue("chooseChar").toUpperCase());
        final int margin = this.margin;
        this.arrW = new int[]{width + margin * 10};
        final int x = this.x;
        final int w = this.w;
        this.arrX = new int[]{0, (w - margin * 2) / 3 + x + margin, x + (w - margin * 2) * 2 / 3 + margin};
        this.arrY = new int[]{this.y - this.arrH[0] / 2};
        this.wCucDat = LoadDataManager.imgCucDat.getRWidth();
        this.initChar();
        this.initCmd();
    }

    public void initChar() {
        this.yMoreUp = -15;
        this.hLogo = 90;
        this.wLogo = 120;
        this.xLogo = (CanvasNinja.w - 120) / 2;
        this.yLogo = 5;
        final int rWidth = LoadDataManager.imgMay2.getRWidth();
        final int rHeight = LoadDataManager.imgMay2.getRHeight();
        final int rWidth2 = LoadDataManager.imgMay1.getRWidth();
        final int rHeight2 = LoadDataManager.imgMay1.getRHeight();
        (this.vecSkys = new Vector<EffSky>()).add(new EffSky(CanvasNinja.w / 2, -rHeight2 / 2, 1, 0, LoadDataManager.imgMay1));
        this.vecSkys.add(new EffSky(20, rHeight2 / 2, 1, 200, LoadDataManager.imgMay1));
        this.vecSkys.add(new EffSky(50, rHeight / 2 + rHeight2 + 10, 1, 200, LoadDataManager.imgMay2));
        this.vecSkys.add(new EffSky(-20, rHeight / 2 + rHeight2 + 30, 1, 200, LoadDataManager.imgMay2));
        this.vecSkys.add(new EffSky(CanvasNinja.w / 2 - rWidth / 2, rHeight / 2 + rHeight2 * 2 + 10, 1, 200, LoadDataManager.imgMay2));
        this.vecSkys.add(new EffSky(CanvasNinja.w - rWidth - 30, rHeight / 2 + rHeight2 + 10, 1, 500, LoadDataManager.imgMay2));
        this.vecSkys.add(new EffSky(CanvasNinja.w - rWidth2 + 10, rHeight / 2 + rHeight2 + 30, 1, 500, LoadDataManager.imgMay1));
        this.yAnim = new int[this.character.length];
        this.indexSelected = 0;
        final FrameImage frameEffectChooseChar = LoadDataManager.frameEffectChooseChar;
        final int wDat = (int) frameEffectChooseChar.frameWidth;
        this.wDat = wDat;
        this.hDat = (int) frameEffectChooseChar.frameHeight;
        this.xDat = (CanvasNinja.w - wDat) / 2;
        this.yDat = CanvasNinja.h / 2;
        final int width = this.fontPaintText.getWidth("mmmmmmmmmm");
        this.wName = width;
        this.hName = (int) LoadDataManager.frameNameNhanVat.frameHeight;
        this.xName = (CanvasNinja.w - width) / 2;
        final int y = this.y;
        final int margin = this.margin;
        this.yName = y + margin * 5;
        this.marginXChar = (this.w - margin * 2) / 3 - margin;
    }

    public void initCmd() {
        this.cmdBack = new MyCommand("", -11, this);
        final FrameImage frameImage = LoadDataManager.myButtons[1];
        final int margin = this.margin;
        final MyButton btnBack = new MyButton(frameImage, frameImage, 75, 0, "Quay lại", margin * 4, CanvasNinja.h - margin * 8, this.cmdBack);
        this.btnBack = btnBack;
        btnBack.isSmall = true;
        this.cmdJoin = new MyCommand("", 11, this);
        final FrameImage frameImage2 = LoadDataManager.myButtons[1];
        final int w = CanvasNinja.w;
        final int margin2 = this.margin;
        final MyButton btnJoin = new MyButton(frameImage2, frameImage2, 75, 0, "Vào", w - margin2 * 4 - this.btnBack.w, CanvasNinja.h - margin2 * 8, this.cmdJoin);
        this.btnJoin = btnJoin;
        btnJoin.isSmall = true;
    }

    @Override
    public void keyPress(final int n) {
        if (n == KEY.KEY_ENTER) {
            this.cmdJoin.perform();
        }
    }

    @Override
    public void keyTyped() {
    }

    @Override
    public void paint(final mGraphics mGraphics) {
        paintBg(mGraphics);
        CanvasNinja.resetTrans(mGraphics);
        CanvasNinja.paintz.paintSingleBorderFrame(LoadDataManager.frameVuong, 0.0f, 0.0f, (float) CanvasNinja.w, (float) CanvasNinja.h, 100, false, mGraphics);
        this.paintChooseChar(mGraphics);
        CanvasNinja.resetTrans(mGraphics);
        this.paintObject(mGraphics);
        final SupportPaint paintz = CanvasNinja.paintz;
        final FrameImage frameVachDoc = LoadDataManager.frameVachDoc;
        final int n = this.arrX[1];
        final int y = this.y;
        final int margin = this.margin;
        final int h = this.h;
        paintz.paintLineVertical(mGraphics, frameVachDoc, n, y + margin * 2, h - margin * 4, h, false);
        final SupportPaint paintz2 = CanvasNinja.paintz;
        final FrameImage frameVachDoc2 = LoadDataManager.frameVachDoc;
        final int n2 = this.arrX[2];
        final int y2 = this.y;
        final int margin2 = this.margin;
        final int h2 = this.h;
        paintz2.paintLineVertical(mGraphics, frameVachDoc2, n2, margin2 * 2 + y2, h2 - margin2 * 4, h2, false);
    }

    public void paintChooseChar(final mGraphics mGraphics) {
        CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        final SupportPaint paintz = CanvasNinja.paintz;
        final FrameImage frameTitle = LoadDataManager.frameTitle;
        final int x = this.x;
        final int n = this.w / 2;
        final int n2 = this.arrW[0];
        paintz.paintTagFrame(mGraphics, frameTitle, x + n - n2 / 2, this.arrY[0], n2, false, 0, false);
        ChooseCharScreen.fontPaintTitle.drawString(mGraphics, SupportTranslate.getTextLangue("chooseChar").toUpperCase(), this.w / 2 + this.x, this.arrH[0] / 2 + this.arrY[0], 3);
        this.btnBack.paint(mGraphics);
        this.btnJoin.paint(mGraphics);
    }

    public void paintObject(final mGraphics mGraphics) {
        final Player[] character = this.character;
        if (character != null && character != null) {
            final int n = 0;
            int n2 = 0;
            int n3;
            while (true) {
                n3 = 3;
                if (n2 >= 3) {
                    break;
                }
                int n4;
                if (n2 == 2) {
                    n4 = 1;
                } else if (n2 == 1) {
                    n4 = -1;
                } else {
                    n4 = 0;
                }
                CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameChooseChar, this.xDat + LoadDataManager.imgDatCreateChar.getRWidth() / 2 + 60 - LoadDataManager.frameChooseChar.getRWidth() / 2 + this.marginXChar * n4 + 3, (this.yName + this.yDat) / 2, LoadDataManager.frameChooseChar.getRWidth(), this.indexSelected == n2);
                if (this.character[n2] == null) {
                    mGraphics.drawImage(LoadDataManager.imgPlus, (float) (this.xDat + LoadDataManager.imgDatCreateChar.getRWidth() / 2 + 60 - LoadDataManager.imgPlus.getRWidth() / 2 + this.marginXChar * n4), (float) ((this.yName + this.yDat + LoadDataManager.frameChooseChar.getRHeight() - LoadDataManager.imgPlus.getRHeight()) / 2));
                }
                ++n2;
            }
            final Player[] character2 = this.character;
            final int length = character2.length;
            final int n5 = 0;
            int n6 = n;
            int n11;
            for (int i = n5; i < length; ++i, n3 = n11) {
                final Player player = character2[i];
                int n7;
                if (n6 == 2) {
                    n7 = 1;
                } else if (n6 == 1) {
                    n7 = -1;
                } else {
                    n7 = 0;
                }
                if (player != null) {
                    mGraphics.drawImage(LoadDataManager.imgCucDat, (float) (this.xDat + this.wDat / 2 + 5 + this.marginXChar * n7 - this.wCucDat / 2), (float) (this.yDat + player.ch / 2 - this.margin * n3));
                    player.paintChar(mGraphics, this.xDat + this.wDat / 2 + 5 + this.marginXChar * n7, this.yDat + player.ch / 2, true);
                    final SupportPaint paintz = CanvasNinja.paintz;
                    final FrameImage frameNameNhanVat = LoadDataManager.frameNameNhanVat;
                    final int xName = this.xName;
                    final int marginXChar = this.marginXChar;
                    final int yName = this.yName;
                    final int wName = this.wName;
                    final boolean b = this.indexSelected == n7;
                    final int n8 = n7;
                    final int n9 = n6;
                    paintz.paintTagFrame(mGraphics, frameNameNhanVat, marginXChar * n7 + xName, yName, wName, b, 0, false);
                    if (this.indexSelected == n9) {
                        this.fontPaintTextFocus.drawString(mGraphics, player.cName, this.xName + this.wName / 2 + this.marginXChar * n8 - 5, this.hName / 2 + this.yName, 3);
                        mGraphics.drawImage(LoadDataManager.imgHeIconPlayer[player.classId], (float) (this.xName + this.wName / 2 + this.marginXChar * n8 - 5 + this.fontPaintTextFocus.getWidth(player.cName) / 2 + this.margin), (float) (this.yName + this.hName / 2 - 4));
                    } else {
                        this.fontPaintText.drawString(mGraphics, player.cName, this.xName + this.wName / 2 + this.marginXChar * n8 - 5, this.hName / 2 + this.yName, 3);
                        mGraphics.drawImage(LoadDataManager.imgHeIconPlayer[player.classId], (float) (this.xName + this.wName / 2 + this.marginXChar * n8 - 5 + this.fontPaintText.getWidth(player.cName) / 2 + this.margin), (float) (this.yName + this.hName / 2 - 4));
                    }
                    final SupportPaint paintz2 = CanvasNinja.paintz;
                    final FrameImage frameChooseTag = LoadDataManager.frameChooseTag;
                    final int n10 = CanvasNinja.w / 2;
                    final int marginXChar2 = this.marginXChar;
                    final int wLevel = this.wLevel;
                    paintz2.paintTagFrame(mGraphics, frameChooseTag, n10 + marginXChar2 * n8 - wLevel / 2, this.yDat + this.hDat - this.margin * 4, wLevel, this.indexSelected != n9, 0, false);
                    if (this.indexSelected == n9) {
                        final mFont fontPaintLevelFocus = this.fontPaintLevelFocus;
                        final StringBuilder sb = new StringBuilder();
                        sb.append("Lv.");
                        sb.append(player.clevel);
                        fontPaintLevelFocus.drawString(mGraphics, sb.toString(), this.marginXChar * n8 + CanvasNinja.w / 2, this.yDat + this.hDat - this.margin * 3 + 2, 2);
                        n11 = 3;
                    } else {
                        final mFont fontPaintLevel = this.fontPaintLevel;
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append("Lv.");
                        sb2.append(player.clevel);
                        final String string = sb2.toString();
                        final int n12 = CanvasNinja.w / 2;
                        final int marginXChar3 = this.marginXChar;
                        final int yDat = this.yDat;
                        final int hDat = this.hDat;
                        final int margin = this.margin;
                        n11 = 3;
                        fontPaintLevel.drawString(mGraphics, string, marginXChar3 * n8 + n12, yDat + hDat - margin * 3 + 2, 2);
                    }
                } else {
                    n11 = n3;
                }
                ++n6;
            }
        }
    }

    @Override
    public void perform(final int n, final Object o) {
    }

    public void startChooseCharScreen(final Player[] character) {
        this.character = character;
        Player.myCharz().resetMyChar();
        this.switchToMe();
    }

    @Override
    public void switchToMe() {
        this.init();
        CanvasNinja.endDlg();
        super.switchToMe();
    }

    @Override
    public void update() {
        super.update();
        final Player charTemp = ChooseCharScreen.charTemp;
        if (charTemp != null) {
            charTemp.updateCamera();
        }
        final Player[] character = this.character;
        if (character != null) {
            if (CanvasNinja.gameTick % 1 == 0) {
                final int indexEff = this.indexEff + 1;
                this.indexEff = indexEff;
                final int nFrame = LoadDataManager.frameEffectChooseChar.nFrame;
                if (indexEff > nFrame - 1) {
                    this.indexEff = nFrame - 1;
                }
            }
            for (final Player player : character) {
                if (player != null) {
                    player.update();
                }
            }
        }
        this.btnJoin.update();
        this.btnBack.update();
        final Iterator<EffSky> iterator = this.vecSkys.iterator();
        while (iterator.hasNext()) {
            iterator.next().update();
        }
    }

    @Override
    public void updateKey() {
        if (this.isFocusTenNhanVat) {
            final int[] yAnim = this.yAnim;
            final int indexSelected = this.indexSelected;
            if (++yAnim[indexSelected] > 2) {
                yAnim[indexSelected] = 0;
                this.isFocusTenNhanVat = false;
            }
        }
        final MyCommand cmdBack = this.cmdBack;
        if (cmdBack != null) {
            cmdBack.updateIconOnly();
        }
        final MyCommand cmdJoin = this.cmdJoin;
        if (cmdJoin != null) {
            cmdJoin.updateIconOnly();
        }
        if (CanvasNinja.isPointerRelease()) {
            final Player[] character = this.character;
            if (character != null && character != null) {
                int indexSelected2 = 0;
                for (final Player player : character) {
                    int n;
                    if (indexSelected2 == 2) {
                        n = 1;
                    } else if (indexSelected2 == 1) {
                        n = -1;
                    } else {
                        n = 0;
                    }
                    if (player != null) {
                        if (CanvasNinja.isPoint(this.xName + this.marginXChar * n, this.yName, this.wName, this.hName + this.hChar + this.hDat / 2)) {
                            CanvasNinja.clearAllPointer();
                            this.indexSelected = indexSelected2;
                            this.indexEff = 0;
                        }
                    } else if (CanvasNinja.isPoint(this.xDat + LoadDataManager.imgDatCreateChar.getRWidth() / 2 + 60 - LoadDataManager.imgPlus.getRWidth() / 2 + this.marginXChar * n, (this.yName + this.yDat + LoadDataManager.frameChooseChar.getRHeight() - LoadDataManager.imgPlus.getRHeight()) / 2, LoadDataManager.imgPlus.getRWidth(), LoadDataManager.imgPlus.getRHeight())) {
                        CanvasNinja.clearAllPointer();
                        SendMessage.gI().requestCreateChar();
                    }
                    ++indexSelected2;
                }
            }
        }
        this.btnBack.updatePointer();
        this.btnJoin.updatePointer();
    }
}
