// Class Version: 8
package ninjawar.screen;

import java.util.Iterator;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.mylib.Image;
import ninjawar.supporter.SupportPaint;
import ninjawar.myscr.MapScr;
import ninjawar.mylib.mGraphics;
import ninjawar.input.KEY;
import ninjawar.mylib.FrameImage;
import ninjawar.myscr.MyTile;
import ninjawar.supporter.LoadDataManager;
import ninjawar.myscr.SplashScr;
import ninjawar.mymain.CanvasNinja;
import ninjawar.message.SendMessage;
import ninjawar.myscr.Res;
import ninjawar.mylib.AudioManager;
import ninjawar.supporter.SupportTranslate;
import ninjawar.model.EffSky;
import java.util.Vector;
import ninjawar.input.TField;
import ninjawar.input.MyNextPrevious;
import ninjawar.model.MyCommand;
import ninjawar.myscr.Player;
import ninjawar.input.MyButton;
import ninjawar.mylib.mFont;
import ninjawar.model.ActionInterface;
import ninjawar.mylib.BaseScreen;

public class CreateCharScreen extends BaseScreen implements ActionInterface
{
    public static mFont fontPaintTitle;
    public static CreateCharScreen me;
    String[] TOC_HE_DETAIL;
    short[][] arrAo;
    String[][] arrAoS;
    public int[] arrH;
    short[][] arrQuan;
    String[][] arrQuanS;
    short[][] arrToc;
    String[][] arrTocS;
    public int[] arrW;
    public int[] arrX;
    public int[] arrY;
    MyButton btnBack;
    MyButton btnJoin;
    public Player character;
    MyCommand cmdBack;
    MyCommand cmdJoin;
    String[] content;
    String[] contentTemp;
    public boolean fixAnGioiTinh;
    public mFont fontPaintText;
    public mFont fontPaintTextFocus;
    public mFont fontPaintTextTileTocHe;
    public mFont fontPaintTextTocHe;
    public mFont fontPaintTocHe;
    int h;
    int hCuonTruc;
    int hInput;
    int hLogo;
    int hMaxCuonTruc;
    int hMuiTen1;
    int hMuiTen2;
    int hNamNu;
    int hSumTocHe;
    int hToc;
    int hTocHe;
    public short[] idPartCurrent;
    int[] indexSelected;
    int indexToc;
    public boolean isFocusNamNu;
    boolean[] isFocusTocHe;
    public boolean isMale;
    int lineHeight;
    public int margin;
    public int margin10;
    public int margin3;
    public int margin7;
    int marginTitleTocHe;
    int marginTocHe;
    int marginYTocQuanAo;
    public int maxIndex;
    public MyNextPrevious[] nextPrevious;
    int numTocHe;
    int selectedTocHe;
    int speedCuonTruc;
    public TField tfNameChar;
    String titleTocHe;
    String tocHe;
    public Vector<EffSky> vecSkys;
    int w;
    int wCuonTruc;
    int wInput;
    int wLogo;
    int wMuiTen1;
    int wMuiTen2;
    int wNamNu;
    int wToc;
    int wTocHe;
    int x;
    int[] xAnimTocHe;
    int xCuonTruc;
    int xDat;
    int xInput;
    int xLogo;
    int xMuiTen1;
    int xMuiTen2;
    int xNamNu;
    int xToc;
    int xTocHe;
    int y;
    public int[] yAnim;
    int yCuonTruc;
    int yDat;
    int yInput;
    int yLogo;
    int yMoreUp;
    int yMuiTen1;
    int yMuiTen2;
    int yNamNu;
    int yToc;
    int yTocHe;

    static {
        CreateCharScreen.fontPaintTitle = mFont.tahoma_7b_white;
    }

    public CreateCharScreen() {
        this.fixAnGioiTinh = true;
        this.marginTitleTocHe = 8;
        this.vecSkys = new Vector<EffSky>();
        this.margin = 5;
        this.margin7 = 7;
        this.margin3 = 3;
        this.margin10 = 10;
        this.speedCuonTruc = 6;
        this.fontPaintTextTileTocHe = mFont.tahoma_toc_he_title;
        this.fontPaintTextTocHe = mFont.tahoma_toc_he_detail_larger;
        this.fontPaintTocHe = mFont.tahoma_brown_dv;
        this.fontPaintText = mFont.fontTmp;
        this.fontPaintTextFocus = mFont.tahoma_brown_focus_dv;
        this.tocHe = "";
        this.titleTocHe = "";
        this.isMale = true;
        this.maxIndex = 2;
        this.arrToc = new short[][] { { 0, 4, 8 }, { 0, 4, 8 } };
        this.arrAo = new short[][] { { 1, 5, 9 }, { 1, 5, 9 } };
        this.arrQuan = new short[][] { { 2, 6, 10 }, { 2, 6, 10 } };
        this.arrTocS = new String[][] { { "Tóc vàng", "Tóc xanh lá", "Tóc xanh" }, { "Tóc vàng", "Tóc xanh lá", "Tóc xanh" } };
        this.arrAoS = new String[][] { { "Áo vàng", "Áo xanh lá", "Áo xanh" }, { "Áo vàng", "Áo xanh lá", "Áo xanh" } };
        this.arrQuanS = new String[][] { { "Quần vàng", "Quần xanh lá", "Quần xanh" }, { "Quần vàng", "Quần xanh lá", "Quần xanh" } };
        this.TOC_HE_DETAIL = SupportTranslate.getArrayLangue("TOC_HE_DETAIL");
        this.marginTocHe = 3;
        this.lineHeight = 5;
        this.indexToc = 0;
    }

    public static CreateCharScreen gI() {
        if (CreateCharScreen.me == null) {
            CreateCharScreen.me = new CreateCharScreen();
        }
        return CreateCharScreen.me;
    }

    private void selecteTocHe(final int selectedTocHe) {
        this.unFocusAllTocHe();
        this.isFocusTocHe[selectedTocHe] = true;
        this.selectedTocHe = selectedTocHe;
        this.updateDataParts();
        this.initCuonTruc();
        AudioManager.buttonClick();
    }

    @Override
    public void commandTab(int i, final int n) {
        final StringBuilder sb = new StringBuilder();
        sb.append("vo ông nội commandTab CreateChar ");
        sb.append(i);
        Res.outz(1, sb.toString());
        switch (i) {
            case 11: {
                final short[] idPartCurrent = this.idPartCurrent;
                if (idPartCurrent != null) {
                    short[] partOnlyUsed;
                    StringBuilder sb2;
                    for (partOnlyUsed = Res.getPartOnlyUsed(idPartCurrent), i = 0; i < partOnlyUsed.length; ++i) {
                        sb2 = new StringBuilder();
                        sb2.append("Gửi lên server tạo char id là:");
                        sb2.append(partOnlyUsed[i]);
                        Res.outz(1, sb2.toString());
                    }
                    SendMessage.gI().createChar(this.tfNameChar.getText(), this.isMale ? 1 : 0, this.selectedTocHe + 1, partOnlyUsed);
                    break;
                }
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

    public Vector<String> getAllStringTocQuanAo() {
        final Vector<String> vector = new Vector<String>();
        for (int i = 0; i < this.arrTocS.length; ++i) {
            int n = 0;
            while (true) {
                final String[] array = this.arrTocS[i];
                if (n >= array.length) {
                    break;
                }
                vector.add(array[n]);
                vector.add(this.arrAoS[i][n]);
                vector.add(this.arrQuanS[i][n]);
                ++n;
            }
        }
        return vector;
    }

    public void init() {
        this.numTocHe = this.TOC_HE_DETAIL.length;
        this.w = 500;
        this.h = 260;
        this.x = (CanvasNinja.w - 500) / 2;
        this.y = (CanvasNinja.h - 260) / 2;
        this.arrH = new int[] { (int)LoadDataManager.frameTitle.frameHeight };
        this.arrW = new int[] { CreateCharScreen.fontPaintTitle.getWidth(SupportTranslate.getTextLangue("createChar").toUpperCase()) + this.margin * 10 };
        this.arrX = new int[] { 0 };
        this.arrY = new int[] { this.y - this.arrH[0] / 2 };
        CanvasNinja.loadBG(MyTile.tileID);
        CanvasNinja.initBG(4, 4);
        this.yMoreUp = -15;
        this.initCmd();
        this.initChar();
        this.initNP();
        this.initTField();
        this.initCuonTruc();
    }

    public void initChar() {
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
        final float frameHeight = LoadDataManager.frameNenHes[0].frameHeight;
        final int hTocHe = (int)frameHeight;
        this.hTocHe = hTocHe;
        this.wTocHe = (int)frameHeight;
        final int numTocHe = this.numTocHe;
        (this.isFocusTocHe = new boolean[numTocHe])[this.selectedTocHe = 0] = true;
        final int hSumTocHe = hTocHe * numTocHe + (numTocHe - 1) * this.marginTocHe;
        this.hSumTocHe = hSumTocHe;
        this.xTocHe = this.x + 15;
        this.yTocHe = (CanvasNinja.h - hSumTocHe) / 2 + this.margin + this.margin3;
        this.xAnimTocHe = new int[numTocHe];
        this.yAnim = new int[2];
        this.xDat = (CanvasNinja.w - LoadDataManager.imgDatCreateChar.getRWidth()) / 2;
        this.yDat = this.y + this.h / 2;
        final FrameImage framePrevious = LoadDataManager.framePrevious;
        final int n = (int)framePrevious.frameWidth;
        this.wMuiTen2 = n;
        this.wMuiTen1 = n;
        final int n2 = (int)framePrevious.frameHeight;
        this.hMuiTen2 = n2;
        this.hMuiTen1 = n2;
        final int wToc = this.fontPaintText.getWidth(Res.findStringMax(this.getAllStringTocQuanAo())) + this.margin * 4;
        this.wToc = wToc;
        final int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(3, wToc, LoadDataManager.frameTagText);
        this.wToc = fixSizeTagFrameUp;
        final int hToc = (int)LoadDataManager.frameTagText.frameHeight;
        this.hToc = hToc;
        final int wMuiTen1 = this.wMuiTen1;
        final int margin = this.margin;
        final int n3 = wMuiTen1 * 2 + margin * 2 + fixSizeTagFrameUp;
        final int wInput = n3 - margin * 4;
        this.wInput = wInput;
        this.hInput = (int)LoadDataManager.frameInput1.frameHeight;
        this.xMuiTen1 = this.x + this.w - n3 - 15;
        final int n4 = margin * 3;
        this.marginYTocQuanAo = 10;
        int n5 = this.hNamNu + hToc * 3 + 10 * 2 + n4;
        if (this.fixAnGioiTinh) {
            n5 = hToc * 3 + 10 * 2 + n4;
        }
        this.xInput = (CanvasNinja.w - wInput) / 2;
        final int yDat = this.yDat;
        final int n6 = LoadDataManager.imgBgChar.getRHeight() / 2;
        final int n7 = LoadDataManager.imgDatCreateChar.getRHeight() / 2;
        final int margin2 = this.margin;
        this.yInput = yDat + n6 - n7 - margin2;
        final int xToc = this.xMuiTen1 + this.wMuiTen1 + margin2;
        this.xToc = xToc;
        final int yToc = (CanvasNinja.h - n5) / 2 + margin2 * 6;
        this.yToc = yToc;
        final int n8 = yToc + (this.hToc - this.hMuiTen1) / 2;
        this.yMuiTen1 = n8;
        this.yMuiTen2 = n8;
        this.xMuiTen2 = xToc + this.wToc + margin2;
        this.hNamNu = (int)LoadDataManager.frameChooseTag.frameHeight;
        if (SupportTranslate.getTextLangue("male").length() > SupportTranslate.getTextLangue("female").length()) {
            SupportTranslate.getTextLangue("male");
        }
        else {
            SupportTranslate.getTextLangue("female");
        }
        final int rWidth3 = LoadDataManager.iconNam.getRWidth();
        this.wNamNu = rWidth3;
        final int margin3 = this.margin;
        this.xNamNu = this.xMuiTen1 + (n3 - (rWidth3 * 2 + margin3 * 2)) / 2;
        this.yNamNu = this.yToc - margin3 * 8;
        this.initDataParts();
        (this.character = new Player()).setDirByCDir(1);
        this.character.setPart(this.idPartCurrent);
        this.character.addWeapon((short)this.selectedTocHe);
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

    public void initCuonTruc() {
        this.content = null;
        final String[] arrayLangue = SupportTranslate.getArrayLangue("nameTocHe");
        final int selectedTocHe = this.selectedTocHe;
        this.titleTocHe = arrayLangue[selectedTocHe + 1];
        this.tocHe = this.TOC_HE_DETAIL[selectedTocHe];
        final int xTocHe = this.xTocHe;
        final int wTocHe = this.wTocHe;
        this.xCuonTruc = xTocHe + wTocHe + 10 + 2;
        final int wCuonTruc = this.xInput - (xTocHe + wTocHe) - 10 - 2 - this.margin * 2;
        this.wCuonTruc = wCuonTruc;
        final int wInput = this.wInput;
        if (wCuonTruc > wInput * 3 / 2) {
            this.wCuonTruc = wInput * 3 / 2;
        }
        final int height = this.fontPaintTextTileTocHe.getHeight();
        final int marginTitleTocHe = this.marginTitleTocHe;
        final int margin7 = this.margin7;
        this.hCuonTruc = height + marginTitleTocHe + margin7 * 2;
        final String[] splitFontArray = this.fontPaintTextTocHe.splitFontArray(this.tocHe, this.wCuonTruc - margin7 * 2);
        this.contentTemp = splitFontArray;
        this.hMaxCuonTruc = this.margin7 * 2 + splitFontArray.length * (this.lineHeight + this.fontPaintTextTocHe.getHeight()) + this.fontPaintTextTileTocHe.getHeight() + this.marginTitleTocHe + (int)LoadDataManager.frameThanhCuonTruc.frameHeight / 2;
        final StringBuilder sb = new StringBuilder();
        sb.append("hMax cuon trục:");
        sb.append(this.hMaxCuonTruc);
        Res.outz(1, sb.toString());
        this.yCuonTruc = CanvasNinja.h / 2 - this.hMaxCuonTruc / 2;
    }

    public void initDataParts() {
        final int n = (this.isMale ^ true) ? 1 : 0;
        this.indexSelected = new int[] { 0, 0, 0 };
        this.idPartCurrent = new short[13];
        int n2 = 0;
        while (true) {
            final short[] idPartCurrent = this.idPartCurrent;
            if (n2 >= idPartCurrent.length) {
                break;
            }
            if (n2 == 0) {
                idPartCurrent[n2] = this.arrToc[n][this.indexSelected[0]];
            }
            else if (n2 == 1) {
                idPartCurrent[n2] = this.arrAo[n][this.indexSelected[1]];
            }
            else if (n2 == 5) {
                idPartCurrent[n2] = this.arrQuan[n][this.indexSelected[2]];
            }
            else if (n2 == 3) {
                idPartCurrent[n2] = -32768;
            }
            else {
                idPartCurrent[n2] = -32768;
            }
            ++n2;
        }
    }

    public void initNP() {
        final int n = (this.isMale ^ true) ? 1 : 0;
        (this.nextPrevious = new MyNextPrevious[6])[0] = new MyNextPrevious(this.xMuiTen1, this.yMuiTen1, this.wMuiTen1, this.hMuiTen1, 0, this.arrToc[n].length - 1, 0, true, false);
        this.nextPrevious[1] = new MyNextPrevious(this.xMuiTen2, this.yMuiTen2, this.wMuiTen2, this.hMuiTen2, 0, this.arrToc[n].length - 1, 0, true, true);
        this.nextPrevious[2] = new MyNextPrevious(this.xMuiTen1, this.hToc + (this.yMuiTen1 + this.marginYTocQuanAo), this.wMuiTen1, this.hMuiTen1, 0, this.arrAo[n].length - 1, 1, false, false);
        this.nextPrevious[3] = new MyNextPrevious(this.xMuiTen2, this.hToc + (this.yMuiTen2 + this.marginYTocQuanAo), this.wMuiTen2, this.hMuiTen2, 0, this.arrAo[n].length - 1, 1, false, true);
        this.nextPrevious[4] = new MyNextPrevious(this.xMuiTen1, (this.marginYTocQuanAo + this.hToc) * 2 + this.yMuiTen1, this.wMuiTen1, this.hMuiTen1, 0, this.arrQuan[n].length - 1, 2, false, false);
        this.nextPrevious[5] = new MyNextPrevious(this.xMuiTen2, (this.marginYTocQuanAo + this.hToc) * 2 + this.yMuiTen2, this.wMuiTen2, this.hMuiTen2, 0, this.arrQuan[n].length - 1, 2, false, true);
    }

    public void initTField() {
        final TField tfNameChar = new TField();
        this.tfNameChar = tfNameChar;
        TField.CARET_COLOR = 8799779;
        tfNameChar.fontTField = mFont.tahoma_7_brown;
        tfNameChar.setIputType(0);
        final TField tfNameChar2 = this.tfNameChar;
        tfNameChar2.isKeyTyped = true;
        tfNameChar2.setMaxTextLenght(12);
        final TField tfNameChar3 = this.tfNameChar;
        tfNameChar3.x = this.xInput;
        tfNameChar3.y = this.yInput;
        tfNameChar3.width = this.wInput;
        tfNameChar3.height = this.hInput;
        tfNameChar3.updateMarginTField(7);
    }

    @Override
    public void keyPress(final int n) {
        if (this.tfNameChar.isFocused()) {
            this.tfNameChar.keyPressed(n);
        }
        if (n == KEY.KEY_TAB) {
            if (++this.selectedTocHe > this.isFocusTocHe.length - 1) {
                this.selectedTocHe = 0;
            }
            this.selecteTocHe(this.selectedTocHe);
        }
        if (n == KEY.KEY_ENTER) {
            this.cmdJoin.perform();
        }
    }

    @Override
    public void keyTyped() {
        if (this.tfNameChar.isFocused()) {
            this.tfNameChar.keyTyped();
        }
    }

    public void load(final short[][] arrToc, final short[][] arrAo, final short[][] arrQuan, final String[][] arrTocS, final String[][] arrAoS, final String[][] arrQuanS, final String[] toc_HE_DETAIL) {
        this.arrToc = arrToc;
        this.arrAo = arrAo;
        this.arrQuan = arrQuan;
        this.arrTocS = arrTocS;
        this.arrAoS = arrAoS;
        this.arrQuanS = arrQuanS;
        this.TOC_HE_DETAIL = toc_HE_DETAIL;
    }

    @Override
    public void paint(final mGraphics mGraphics) {
        this.paintBg(mGraphics);
        CanvasNinja.resetTrans(mGraphics);
        CanvasNinja.paintz.paintSingleBorderFrame(LoadDataManager.frameVuong, 0.0f, 0.0f, (float)CanvasNinja.w, (float)CanvasNinja.h, 100, false, mGraphics);
        this.paintCreateCharNew(mGraphics);
        this.paintInfoHe(mGraphics);
        CanvasNinja.resetTrans(mGraphics);
    }

    public void paintBg(final mGraphics mGraphics) {
        MapScr.cmy = (MapScr.cmtoY = MyTile.size * MyTile.tmh - CanvasNinja.h);
        mGraphics.translate(-(MapScr.cmx = (MapScr.cmtoX = MyTile.size)), 500);
        CanvasNinja.paintBGGameScr(mGraphics);
        mGraphics.translate(-MapScr.cmx, -MapScr.cmy);
        MyTile.paintTileMapSpec(mGraphics);
    }

    public void paintCreateCharNew(final mGraphics mGraphics) {
        CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameBgOrange2_0, (float)this.x, (float)this.y, (float)this.w, (float)this.h, 0, false);
        final SupportPaint paintz = CanvasNinja.paintz;
        final FrameImage frameTitle = LoadDataManager.frameTitle;
        final int x = this.x;
        final int n = this.w / 2;
        final int[] arrW = this.arrW;
        final int n2 = 0;
        final int n3 = arrW[0];
        paintz.paintTagFrame(mGraphics, frameTitle, x + n - n3 / 2, this.arrY[0], n3, false, 0, false);
        CreateCharScreen.fontPaintTitle.drawString(mGraphics, SupportTranslate.getTextLangue("createChar").toUpperCase(), this.w / 2 + this.x, this.arrH[0] / 2 + this.arrY[0], 3);
        for (int i = 0; i < this.numTocHe; ++i) {
            LoadDataManager.frameNenHes[i].drawFrame(this.isFocusTocHe[i] ? 1 : 0, (float)(this.xTocHe + this.xAnimTocHe[i]), (float)(this.yTocHe + (this.marginTocHe + this.hTocHe) * i), 0, mGraphics);
        }
        if (this.fixAnGioiTinh) {
            CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.iconNam, this.xNamNu, this.yAnim[0] + this.yNamNu, this.wNamNu, this.isMale);
            final SupportPaint paintz2 = CanvasNinja.paintz;
            final FrameImage iconNu = LoadDataManager.iconNu;
            final int xNamNu = this.xNamNu;
            final int wNamNu = this.wNamNu;
            paintz2.paintTagFrame(mGraphics, iconNu, this.margin * 2 + (xNamNu + wNamNu), this.yAnim[0] + this.yNamNu, wNamNu, this.isMale ^ true);
        }
        for (int j = 0; j < 3; ++j) {
            CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameTagText, this.xToc, (this.marginYTocQuanAo + this.hToc) * j + this.yToc, this.wToc, j == this.indexToc, 0, false);
        }
        final MyNextPrevious[] nextPrevious = this.nextPrevious;
        if (!nextPrevious[0].isEnable && !nextPrevious[1].isEnable) {
            this.fontPaintText.drawString(mGraphics, this.arrTocS[this.isMale ? 0 : 1][this.indexSelected[0]], this.wToc / 2 + this.xToc, this.hToc / 2 + this.yToc, 3);
        }
        else {
            this.fontPaintTextFocus.drawString(mGraphics, this.arrTocS[this.isMale ? 0 : 1][this.indexSelected[0]], this.wToc / 2 + this.xToc, this.hToc / 2 + this.yToc, 3);
        }
        final MyNextPrevious[] nextPrevious2 = this.nextPrevious;
        if (!nextPrevious2[2].isEnable && !nextPrevious2[3].isEnable) {
            final mFont fontPaintText = this.fontPaintText;
            final String s = this.arrAoS[this.isMale ? 0 : 1][this.indexSelected[1]];
            final int xToc = this.xToc;
            final int n4 = this.wToc / 2;
            final int yToc = this.yToc;
            final int marginYTocQuanAo = this.marginYTocQuanAo;
            final int hToc = this.hToc;
            fontPaintText.drawString(mGraphics, s, n4 + xToc, yToc + (marginYTocQuanAo + hToc) * 1 + hToc / 2, 3);
        }
        else {
            final mFont fontPaintTextFocus = this.fontPaintTextFocus;
            final String s2 = this.arrAoS[this.isMale ? 0 : 1][this.indexSelected[1]];
            final int xToc2 = this.xToc;
            final int n5 = this.wToc / 2;
            final int yToc2 = this.yToc;
            final int marginYTocQuanAo2 = this.marginYTocQuanAo;
            final int hToc2 = this.hToc;
            fontPaintTextFocus.drawString(mGraphics, s2, n5 + xToc2, yToc2 + (marginYTocQuanAo2 + hToc2) * 1 + hToc2 / 2, 3);
        }
        final MyNextPrevious[] nextPrevious3 = this.nextPrevious;
        if (!nextPrevious3[4].isEnable && !nextPrevious3[5].isEnable) {
            final mFont fontPaintText2 = this.fontPaintText;
            final String s3 = this.arrQuanS[this.isMale ? 0 : 1][this.indexSelected[2]];
            final int xToc3 = this.xToc;
            final int n6 = this.wToc / 2;
            final int yToc3 = this.yToc;
            final int marginYTocQuanAo3 = this.marginYTocQuanAo;
            final int hToc3 = this.hToc;
            fontPaintText2.drawString(mGraphics, s3, n6 + xToc3, yToc3 + (marginYTocQuanAo3 + hToc3) * 2 + hToc3 / 2, 3);
        }
        else {
            final mFont fontPaintTextFocus2 = this.fontPaintTextFocus;
            final String s4 = this.arrQuanS[this.isMale ? 0 : 1][this.indexSelected[2]];
            final int xToc4 = this.xToc;
            final int n7 = this.wToc / 2;
            final int yToc4 = this.yToc;
            final int marginYTocQuanAo4 = this.marginYTocQuanAo;
            final int hToc4 = this.hToc;
            fontPaintTextFocus2.drawString(mGraphics, s4, n7 + xToc4, yToc4 + (marginYTocQuanAo4 + hToc4) * 2 + hToc4 / 2, 3);
        }
        if (this.character != null) {
            final Image imgBgChar = LoadDataManager.imgBgChar;
            mGraphics.drawImage(imgBgChar, (float)(this.xDat - imgBgChar.getRWidth() / 2 + LoadDataManager.imgDatCreateChar.getRWidth() / 2), (float)(this.yDat - LoadDataManager.imgBgChar.getRHeight() / 2 - LoadDataManager.imgDatCreateChar.getRHeight() / 2));
            this.character.paintShadow(mGraphics, this.xDat + LoadDataManager.imgDatCreateChar.getRWidth() / 2, this.yDat + LoadDataManager.imgDatCreateChar.getRHeight() / 2);
            this.character.paintCharAtkLoop(mGraphics, this.xDat + LoadDataManager.imgDatCreateChar.getRWidth() / 2 + this.margin3, this.yDat + LoadDataManager.imgDatCreateChar.getRHeight() / 2, false);
        }
        CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameInput1, this.xInput, this.yInput, this.wInput, this.tfNameChar.isFocused(), 0, true);
        this.tfNameChar.painTextInputNoneBackGround(mGraphics);
        if (!this.tfNameChar.isFocused() && this.tfNameChar.getText().equals("")) {
            mFont.tahoma_place_holder.drawString(mGraphics, SupportTranslate.getTextLangue("inputYourName"), this.xInput + 7, this.hInput / 2 + this.yInput, 4);
        }
        final MyNextPrevious[] nextPrevious4 = this.nextPrevious;
        for (int length = nextPrevious4.length, k = n2; k < length; ++k) {
            nextPrevious4[k].paint(mGraphics);
        }
        this.btnBack.paint(mGraphics);
        this.btnJoin.paint(mGraphics);
    }

    public void paintInfoHe(final mGraphics mGraphics) {
        CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameBgCam, (float)this.xCuonTruc, (float)this.yTocHe, (float)this.wCuonTruc, (float)this.hSumTocHe, 10, false);
        if (this.content != null) {
            this.fontPaintTextTileTocHe.drawString(mGraphics, this.titleTocHe, this.xCuonTruc + this.wCuonTruc / 2, this.yTocHe - this.margin * 4, 2);
            final Image imgGachChan = LoadDataManager.imgGachChan;
            mGraphics.drawImage(imgGachChan, (float)(this.xCuonTruc + this.wCuonTruc / 2 - imgGachChan.getRWidth() / 2), (float)(this.yTocHe - this.margin));
            final Image imgMuiTen = LoadDataManager.imgMuiTen;
            final float n = (float)(this.xCuonTruc - imgMuiTen.getRWidth());
            final int yTocHe = this.yTocHe;
            final int selectedTocHe = this.selectedTocHe;
            final int marginTocHe = this.marginTocHe;
            final int hTocHe = this.hTocHe;
            mGraphics.drawImage(imgMuiTen, n, (float)(yTocHe + selectedTocHe * (marginTocHe + hTocHe) + hTocHe / 2 - LoadDataManager.imgMuiTen.getRHeight() / 2));
            int n2 = 0;
            while (true) {
                final String[] content = this.content;
                if (n2 >= content.length) {
                    break;
                }
                final String s = content[n2];
                if (s != null) {
                    final mFont fontPaintTextTocHe = this.fontPaintTextTocHe;
                    fontPaintTextTocHe.drawString(mGraphics, s, this.xCuonTruc + this.margin7, this.yTocHe + this.margin + fontPaintTextTocHe.getHeight() * n2 + this.lineHeight * n2);
                }
                ++n2;
            }
        }
    }

    @Override
    public void perform(final int n, final Object o) {
    }

    public void resetDataParts() {
        this.indexSelected = new int[] { 0, 0, 0 };
    }

    @Override
    public void switchToMe() {
        MyTile.tileID = 1;
        this.init();
        CanvasNinja.endDlg();
        super.switchToMe();
    }

    public void unFocusAllTocHe() {
        for (int i = 0; i < this.numTocHe; ++i) {
            this.isFocusTocHe[i] = false;
        }
    }

    @Override
    public void update() {
        super.update();
        MapScr.updateCamera();
        this.tfNameChar.update();
        this.tfNameChar.updateFocus();
        final Player character = this.character;
        if (character != null) {
            character.update();
        }
        this.btnJoin.update();
        this.btnBack.update();
        final boolean isAndroid = NinjaMidlet.isAndroid;
        final int n = 0;
        if (isAndroid && !NinjaMidlet.isUseIOSSpec) {
            if (this.tfNameChar.isFocused()) {
                Gdx.input.setOnscreenKeyboardVisible(true, Input.OnscreenKeyboardType.Default);
            }
            else {
                Gdx.input.setOnscreenKeyboardVisible(false);
            }
        }
        for (int i = 0; i < this.numTocHe; ++i) {
            if (this.isFocusTocHe[i]) {
                this.xAnimTocHe[i] = 0;
            }
            else {
                this.xAnimTocHe[i] = 0;
            }
        }
        final MyNextPrevious[] nextPrevious = this.nextPrevious;
        for (int length = nextPrevious.length, j = n; j < length; ++j) {
            nextPrevious[j].update();
        }
        final Iterator<EffSky> iterator = this.vecSkys.iterator();
        while (iterator.hasNext()) {
            iterator.next().update();
        }
        final int hCuonTruc = this.hCuonTruc;
        if (hCuonTruc < this.hMaxCuonTruc) {
            final int hCuonTruc2 = hCuonTruc + this.speedCuonTruc;
            this.hCuonTruc = hCuonTruc2;
            if (hCuonTruc2 >= 40 && hCuonTruc2 <= 43) {
                this.hCuonTruc = 44;
            }
            final int hCuonTruc3 = this.hCuonTruc;
            if (hCuonTruc3 > this.margin7) {
                final int n2 = (hCuonTruc3 - this.margin * 2 - this.fontPaintTextTileTocHe.getHeight() - this.marginTitleTocHe - (int)LoadDataManager.frameThanhCuonTruc.frameHeight / 2) / (this.fontPaintTocHe.getHeight() + this.lineHeight - 1);
                if (n2 > 0) {
                    if (n2 == this.contentTemp.length) {
                        this.content = new String[n2];
                        for (int k = 0; k < n2; ++k) {
                            this.content[k] = this.contentTemp[k];
                        }
                    }
                    else {
                        this.content = new String[n2 - 1];
                        for (int l = 0; l < n2 - 1; ++l) {
                            try {
                                this.content[l] = this.contentTemp[l];
                            }
                            catch (Exception ex) {}
                        }
                    }
                }
            }
        }
    }

    public void updateDataParts() {
        final int n = (this.isMale ^ true) ? 1 : 0;
        this.idPartCurrent = new short[13];
        int n2 = 0;
        short[] idPartCurrent;
        while (true) {
            idPartCurrent = this.idPartCurrent;
            if (n2 >= idPartCurrent.length) {
                break;
            }
            if (n2 == 0) {
                idPartCurrent[n2] = this.arrToc[n][this.indexSelected[0]];
            }
            else if (n2 == 1) {
                idPartCurrent[n2] = this.arrAo[n][this.indexSelected[1]];
            }
            else if (n2 == 5) {
                idPartCurrent[n2] = this.arrQuan[n][this.indexSelected[2]];
            }
            else if (n2 == 3) {
                idPartCurrent[n2] = -32768;
            }
            else {
                idPartCurrent[n2] = -32768;
            }
            ++n2;
        }
        this.character.setPart(idPartCurrent);
        this.character.addWeapon((short)this.selectedTocHe);
    }

    @Override
    public void updateKey() {
        final MyCommand cmdBack = this.cmdBack;
        if (cmdBack != null) {
            cmdBack.updateIconOnly();
        }
        final MyCommand cmdJoin = this.cmdJoin;
        if (cmdJoin != null) {
            cmdJoin.updateIconOnly();
        }
        if (this.isFocusNamNu) {
            final int[] yAnim = this.yAnim;
            final boolean isMale = this.isMale;
            final int n = (isMale ^ true) ? 1 : 0;
            ++yAnim[n];
            if (yAnim[isMale ? 0 : 1] > 2) {
                yAnim[isMale ? 0 : 1] = 0;
                this.isFocusNamNu = false;
            }
        }
        if (CanvasNinja.isPointerRelease()) {
            for (final MyNextPrevious myNextPrevious : this.nextPrevious) {
                final int updateSelected = myNextPrevious.updateSelected();
                if (updateSelected != -1) {
                    this.indexSelected[myNextPrevious.pos] = (short)updateSelected;
                    this.updateDataParts();
                    this.updateNextPrevious(myNextPrevious.pos, updateSelected, true);
                    AudioManager.buttonClick();
                    break;
                }
            }
        }
        if (CanvasNinja.isPointerRelease()) {
            for (int j = 0; j < 3; ++j) {
                final int xToc = this.xToc;
                final int yToc = this.yToc;
                final int marginYTocQuanAo = this.marginYTocQuanAo;
                final int hToc = this.hToc;
                if (CanvasNinja.isPoint(xToc, yToc + (marginYTocQuanAo + hToc) * j, this.wToc, hToc)) {
                    this.indexToc = j;
                    CanvasNinja.clearAllPointer();
                    this.updateNextPrevious(j, this.indexSelected[j], true);
                }
            }
        }
        if (CanvasNinja.isPointerRelease()) {
            if (CanvasNinja.isPoint(this.xNamNu, this.yNamNu, this.wNamNu, this.hNamNu)) {
                CanvasNinja.clearAllPointer();
                if (!this.isMale) {
                    this.resetDataParts();
                }
                this.isMale = true;
                this.isFocusNamNu = true;
                this.initNP();
                this.updateDataParts();
                AudioManager.buttonClick();
            }
            final int xNamNu = this.xNamNu;
            final int margin = this.margin;
            final int wNamNu = this.wNamNu;
            if (CanvasNinja.isPoint(xNamNu + margin * 2 + wNamNu, this.yNamNu, wNamNu, this.hNamNu)) {
                CanvasNinja.clearAllPointer();
                if (this.isMale) {
                    this.resetDataParts();
                }
                this.isMale = false;
                this.isFocusNamNu = true;
                this.initNP();
                this.updateDataParts();
                AudioManager.buttonClick();
            }
            for (int k = 0; k < this.numTocHe; ++k) {
                final int xTocHe = this.xTocHe;
                final int yTocHe = this.yTocHe;
                final int marginTocHe = this.marginTocHe;
                final int hTocHe = this.hTocHe;
                if (CanvasNinja.isPoint(xTocHe, yTocHe + (marginTocHe + hTocHe) * k, this.wTocHe + this.margin * 2, hTocHe)) {
                    CanvasNinja.clearAllPointer();
                    this.selecteTocHe(k);
                    break;
                }
            }
        }
        this.btnBack.updatePointer();
        this.btnJoin.updatePointer();
    }

    public void updateNextPrevious(final int indexToc, final int indexSelected, final boolean isEnable) {
        for (final MyNextPrevious myNextPrevious : this.nextPrevious) {
            if (myNextPrevious.pos == indexToc) {
                myNextPrevious.indexSelected = indexSelected;
                myNextPrevious.isEnable = isEnable;
            }
            else {
                myNextPrevious.isEnable = (isEnable ^ true);
            }
        }
        this.indexToc = indexToc;
    }
}
