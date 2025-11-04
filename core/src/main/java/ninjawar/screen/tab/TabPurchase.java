package ninjawar.screen.tab;

import com.teamobi.ninjawar.NinjaWar;
import ninjawar.input.MyButton;
import ninjawar.message.Config;
import ninjawar.model.MyCommand;
import ninjawar.model.ProductPurchase;
import ninjawar.mylib.Atlas;
import ninjawar.mylib.FrameAtlas;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.PurchaseInfo;
import ninjawar.supporter.SupportPaint;

public class TabPurchase extends TabScr {
    static long lastTimePress = 0;
    public static TabPurchase me;
    public FrameAtlas[] atlasPurchase;
    MyCommand[] cmds;
    public mFont fontContentNews;
    public mFont fontPaintTab = mFont.tahoma_brown_dv;
    public mFont fontPaintTabFocus = mFont.tahoma_brown_focus_dv;
    public mFont fontTitleNews;
    String[] gemValueText;
    int indexTabSelected;
    boolean[] isClickTab;
    int marginOneTab;
    public int numPage;
    public int numZoneDoc;
    public int numZoneNgang;
    String[] prices;
    String[] productIds;
    public PurchaseInfo purchaseInfo;
    public PurchaseInfo[] purchaseInfoAlls;
    int[] yAnim;
    int[] yAnimTab;

    public TabPurchase() {
        mFont mfont = mFont.tahoma_brown_dv;
        this.fontTitleNews = mfont;
        this.fontContentNews = mfont;
        this.indexTabSelected = -1;
    }

    public static TabPurchase gI() {
        if (me == null) {
            me = new TabPurchase();
        }
        return me;
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        SupportPaint supportPaint = CanvasNinja.paintz;
        FrameImage frameImage = LoadDataManager.frameTitle;
        int i = this.wTitle;
        supportPaint.paintTagFrame(g, frameImage, (this.x + (this.w / 2)) - (i / 2), this.y - (this.hTitle / 2), i, false, 0, false);
        TabScr.fontPaintTile.drawString(g, this.purchaseInfo.nameTab.toUpperCase(), (this.w / 2) + this.x, this.y, 3);
        for (int i2 = 0; i2 < this.numZoneDoc; i2++) {
            int k = 0;
            while (true) {
                int i3 = this.numZoneNgang;
                if (k >= i3) {
                    break;
                }
                int index = (this.numPage * this.numZoneDoc * i3) + (i3 * i2) + k;
                SupportPaint supportPaint2 = CanvasNinja.paintz;
                FrameImage frameImage2 = LoadDataManager.frameBgGem;
                int i4 = this.arrX[0];
                int i5 = this.margin;
                int i6 = this.arrW[0];
                supportPaint2.paintTagFrame(g, frameImage2, ((i5 + i6) * k) + i4, this.arrY[0] + ((i5 + this.arrH[0]) * i2), i6, false);
                FrameAtlas frameAtlas = this.atlasPurchase[index];
                int i7 = this.arrX[0];
                int i8 = this.margin;
                int i9 = this.arrW[0];
                int i10 = this.arrY[0];
                int i11 = this.margin;
                int i12 = this.arrH[0];
                frameAtlas.drawFrame(0, (float) (i7 + ((i8 + i9) * k) + ((i9 - LoadDataManager.imgGem[index].getRWidth()) / 2)), (float) (((i10 + ((i11 + i12) * i2)) + ((i12 - LoadDataManager.imgGem[index].getRHeight()) / 2)) - this.margin), mgraphics);
                mFont mfont = mFont.tahoma_7b_brown;
                String str = this.gemValueText[index];
                int i13 = this.arrX[0];
                int i14 = this.margin;
                int i15 = this.arrW[0];
                mfont.drawString(g, str, (i15 / 2) + i13 + ((i14 + i15) * k), this.arrY[0] + ((i14 + this.arrH[0]) * i2) + this.margin7, 2);
                SupportPaint supportPaint3 = CanvasNinja.paintz;
                FrameImage frameImage3 = LoadDataManager.frameGiaTien;
                int i16 = this.arrX[0];
                int i17 = this.margin;
                int i18 = i16 + ((this.arrW[0] + i17) * k) + 2;
                int i19 = this.arrY[0];
                int i20 = this.arrH[0];
                supportPaint3.paintTagFrame(g, frameImage3, i18, ((((i19 + ((i17 + i20) * i2)) + i20) - frameImage3.getRHeight()) - 2) + this.yAnim[index], this.arrW[0] - 4, false, 0, false);
                mFont mfont2 = mFont.tahoma_7b_brown;
                String str2 = this.prices[index];
                int i21 = this.arrX[0];
                int i22 = this.margin;
                int i23 = this.arrW[0];
                int i24 = (i23 / 2) + i21 + ((i22 + i23) * k);
                int i25 = this.arrY[0];
                int i26 = this.arrH[0];
                mfont2.drawString(g, str2, i24, ((((i25 + ((i22 + i26) * i2)) + i26) - LoadDataManager.frameGiaTien.getRHeight()) - 2) + (LoadDataManager.frameGiaTien.getRHeight() / 2) + this.yAnim[index], 3);
                k++;
            }
        }
        this.cmdClose.paintIconOnly(mgraphics);
    }

    public void resetSelected() {
        this.indexTabSelected = 0;
    }

    public void init() {
        resetSelected();
        this.w = 430;
        this.h = 250;
        int i = CanvasNinja.w;
        if (430 > i) {
            this.w = i - (this.margin * 5);
        }
        int i2 = CanvasNinja.h;
        if (250 > i2) {
            this.h = i2 - (this.margin * 2);
        }
        initStart();
        int size = this.purchaseInfo.products.length;
        if (size > 0) {
            this.btns = new MyButton[size];
            String[] txtBtns = new String[size];
            for (int i3 = 0; i3 < txtBtns.length; i3++) {
                txtBtns[i3] = this.purchaseInfo.products[i3].productId;
            }
            int maxW = MyButton.FONT_DEFAULT.getWidth(Res.findStringMax(txtBtns)) + (this.margin * 3);
            for (int i4 = 0; i4 < txtBtns.length; i4++) {
                txtBtns[i4] = this.purchaseInfo.products[i4].productId;
                MyButton[] myButtonArr = this.btns;
                FrameImage frameImage = LoadDataManager.myButtons[0];
                myButtonArr[i4] = new MyButton(frameImage, frameImage, maxW, 0, txtBtns[i4], 0, 0, new MyCommand("", i4, this));
                this.btns[i4].sku = this.purchaseInfo.products[i4].productId;
                Res.debugPurchase("btn sku:" + this.btns[i4].sku);
            }
            MyButton[] myButtonArr2 = this.btns;
            MyButton myButton = myButtonArr2[0];
            int sumW = (myButton.w * 3) + 10;
            MyButton.setPosList(myButtonArr2, 5, sumW, (this.x + (this.w / 2)) - (sumW / 2), (this.y + this.h) - (myButton.h * 2));
        }
        this.wTitle = this.fontTitleNews.getWidth("Nạp Nạp Nạp".toUpperCase()) + (this.margin7 * 2);
        this.marginOneTab = this.margin;
        this.yAnimTab = new int[3];
        this.isClickTab = new boolean[3];
        lastTimePress = System.currentTimeMillis();
        this.numPage = 0;
        this.margin = 5;
        FrameImage frameImage2 = LoadDataManager.frameBgGem;
        int wOneZone = (int) frameImage2.frameWidth;
        int i5 = this.numZoneNgang;
        int wNgang = (wOneZone * i5) + ((i5 + 1) * 5) + (5 * 20);
        int sumWZone = (wOneZone * i5) + ((i5 - 1) * 5);
        int hOneZone = ((int) frameImage2.frameHeight) + (5 * 2);
        int i6 = this.numZoneDoc;
        int hDoc = (hOneZone * i6) + (5 * (i6 + 1)) + (5 * 2) + 3 + LoadDataManager.imgClose.getRHeight();
        while (wNgang > CanvasNinja.w) {
            int i7 = this.numZoneNgang - 1;
            this.numZoneNgang = i7;
            int i8 = this.margin;
            wNgang = (wOneZone * i7) + ((i7 + 1) * i8) + (i8 * 4);
        }
        while (hDoc > CanvasNinja.h) {
            int i9 = this.numZoneDoc - 1;
            this.numZoneDoc = i9;
            int i10 = this.margin;
            hDoc = (hOneZone * i9) + ((i9 + 1) * i10) + (i10 * 2) + 3 + LoadDataManager.imgClose.getRHeight();
        }
        this.w = wNgang;
        this.h = hDoc;
        initStart();
        String upperCase = "NẠP NGỌC".toUpperCase();
        this.textTitle = upperCase;
        int width = TabScr.fontPaintTile.getWidth(upperCase);
        int i11 = this.margin;
        this.wTitle = width + (i11 * 4);
        this.hTitle = (int) LoadDataManager.frameTitle.frameHeight;
        this.arrW = new int[]{wOneZone};
        this.arrH = new int[]{hOneZone};
        this.arrX = new int[]{this.x + ((this.w - sumWZone) / 2)};
        MyCommand myCommand = this.cmdClose;
        this.arrY = new int[]{myCommand.y + myCommand.h + i11};
    }

    private void setProducts() {
        Atlas atlas = Atlas.createAtlas((short) 13, (short) 23);
        PurchaseInfo purchaseInfo2 = this.purchaseInfo;
        if (purchaseInfo2 != null) {
            int size = purchaseInfo2.products.length;
            this.gemValueText = new String[size];
            this.prices = new String[size];
            this.productIds = new String[size];
            this.cmds = new MyCommand[size];
            this.atlasPurchase = new FrameAtlas[size];
            this.yAnim = new int[size];
            for (int i = 0; i < size; i++) {
                if (atlas != null) {
                    FrameAtlas[] frameAtlasArr = this.atlasPurchase;
                    frameAtlasArr[i] = new FrameAtlas(atlas, this.purchaseInfo.products[i].idIcon + "");
                    FrameAtlas frameAtlas = this.atlasPurchase[i];
                    this.w = (int) frameAtlas.frameWidth;
                    this.h = (int) frameAtlas.frameHeight;
                } else {
                    this.w = 32;
                    this.h = 32;
                }
                String[] strArr = this.gemValueText;
                ProductPurchase[] productPurchaseArr = this.purchaseInfo.products;
                strArr[i] = productPurchaseArr[i].name;
                this.prices[i] = productPurchaseArr[i].price;
                this.productIds[i] = productPurchaseArr[i].productId;
                this.cmds[i] = new MyCommand("", i, this);
            }
            if (size > 2) {
                this.numZoneNgang = 3;
                int i2 = size % 3 == 0 ? size / 3 : 1 + (size / 3);
                this.numZoneDoc = i2;
                if (i2 > 2) {
                    this.numZoneDoc = 2;
                    return;
                }
                return;
            }
            this.numZoneNgang = size;
            this.numZoneDoc = 1;
        }
    }

    public void update() {
        int i;
        updateScroll();
        int i2 = 0;
        while (true) {
            int[] iArr = this.yAnimTab;
            if (i2 >= iArr.length) {
                break;
            }
            boolean[] zArr = this.isClickTab;
            if (zArr[i2]) {
                int i3 = iArr[i2] + 1;
                iArr[i2] = i3;
                if (i3 >= 2) {
                    iArr[i2] = 0;
                    zArr[i2] = false;
                }
            }
            i2++;
        }
        int i4 = 0;
        while (true) {
            int[] iArr2 = this.yAnim;
            if (i4 >= iArr2.length) {
                break;
            }
            iArr2[i4] = 0;
            i4++;
        }
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.update();
            }
        }
    }

    public void updateKey() {
        this.cmdClose.updateIconOnly();
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.updatePointer();
            }
        }
        if (CanvasNinja.isPointerRelease()) {
            for (int i = 0; i < this.numZoneDoc; i++) {
                int k = 0;
                while (true) {
                    int i2 = this.numZoneNgang;
                    if (k >= i2) {
                        break;
                    }
                    int index = (this.numPage * this.numZoneDoc * i2) + (i2 * i) + k;
                    int i3 = this.arrX[0];
                    int i4 = this.margin;
                    int i5 = this.arrY[0];
                    int i6 = this.arrH[0];
                    if (CanvasNinja.isPoint(i3 + ((this.arrW[0] + i4) * k) + 2, (((i5 + ((i4 + i6) * i)) + i6) - LoadDataManager.frameGiaTien.getRHeight()) - 2, this.arrW[0] - 4, (int) LoadDataManager.frameGiaTien.frameHeight)) {
                        this.cmds[index].perform();
                        this.yAnim[index] = 2;
                        break;
                    }
                    k++;
                }
            }
        }
    }

    public void startTabPurchase(PurchaseInfo[] purchaseInfos) {
        this.purchaseInfoAlls = purchaseInfos;
        selectTab(0);
        show();
    }

    private void selectTab(int indexTab) {
        this.indexTabSelected = indexTab;
        this.purchaseInfo = this.purchaseInfoAlls[indexTab];
        setProducts();
    }

    public void show() {
        CanvasNinja.vecPurchaseDebugs.removeAllElements();
        init();
        showTab();
    }

    public void keyPress(int keyCode) {
    }

    public void keyTyped() {
    }

    public void commandTab(int index, int subIndex) {
        Res.outz("Vào cmd tab tab purchase:" + index);
        super.commandTab(index, subIndex);
    }

    public void updateTextBtn(int index, String textNew, boolean isOn) {
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            int size = this.purchaseInfo.products.length;
            if (size > 0) {
                myButtonArr[index].name = textNew;
                String[] txtBtns = new String[size];
                for (int i2 = 0; i2 < txtBtns.length; i2++) {
                    txtBtns[i2] = this.btns[i2].name;
                }
                int maxW = MyButton.FONT_DEFAULT.getWidth(Res.findStringMax(txtBtns)) + (this.margin * 3);
                for (int i3 = 0; i3 < txtBtns.length; i3++) {
                    MyButton[] myButtonArr2 = this.btns;
                    if (!myButtonArr2[i3].isDisable) {
                        FrameImage frameImage = LoadDataManager.myButtons[0];
                        myButtonArr2[i3] = new MyButton(frameImage, frameImage, maxW, 0, txtBtns[i3], 0, 0, new MyCommand("", i3, this));
                        this.btns[i3].sku = this.purchaseInfo.products[i3].productId;
                    }
                }
                MyButton[] myButtonArr3 = this.btns;
                MyButton myButton = myButtonArr3[0];
                int sumW = (myButton.w * myButtonArr3.length) + ((myButtonArr3.length - 1) * 5);
                MyButton.setPosList(myButtonArr3, 5, sumW, (this.x + (this.w / 2)) - (sumW / 2), (this.y + this.h) - (myButton.h * 2));
            }
            this.btns[index].name = textNew;
            onOffBtn(index, isOn);
            return;
        }
        boolean z = isOn;
    }

    private void onOffBtn(int index, boolean isOn) {
        if (isOn) {
            MyButton myButton = this.btns[index];
            FrameImage[] frameImageArr = LoadDataManager.myButtons;
            myButton.imgButton = frameImageArr[0];
            myButton.imgButtonHover = frameImageArr[3];
            myButton.isDisable = false;
            return;
        }
        MyButton myButton2 = this.btns[index];
        FrameImage frameImage = LoadDataManager.myButtons[4];
        myButton2.imgButton = frameImage;
        myButton2.imgButtonHover = frameImage;
        myButton2.isDisable = true;
    }

    public void updatePricesAndName(int index, String price, String name) {
        String[] strArr = this.gemValueText;
        if (index < strArr.length - 1) {
            strArr[index] = name;
        }
        String[] strArr2 = this.prices;
        if (index < strArr2.length - 1) {
            strArr2[index] = price;
        }
    }
}
