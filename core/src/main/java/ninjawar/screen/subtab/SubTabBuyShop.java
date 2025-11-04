package ninjawar.screen.subtab;

import ninjawar.input.MyButton;
import ninjawar.input.MyRadio;
import ninjawar.message.SendMessage;
import ninjawar.model.BoxInventory;
import ninjawar.model.MyCommand;
import ninjawar.model.mGiftData;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.object.Item;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportTranslate;

public class SubTabBuyShop extends SubTabScr {
    public static SubTabBuyShop me;
    public BoxInventory box;
    mFont fontPaint = mFont.tahoma_brown_dv;
    mFont fontPaintNum = mFont.tahoma_7b_white;
    public short idNpc;
    int indexMinus;
    int indexPlus;
    int num = 1;
    MyRadio[] priceRadios;
    String priceStr = "10.000";
    int selectedPriceIndex = 0;
    int typeMoney;
    byte[] typeMoneyServer;
    int yAnimMinus;
    int yAnimPlus;

    public void startSubTabBuyShop(BoxInventory box2, byte[] typeMoney2, int w, int h) {
        this.num = 1;
        this.box = box2;
        this.typeMoneyServer = typeMoney2;
        this.w = w;
        this.h = h;
        initStart();
        init();
        show();
    }

    public static SubTabBuyShop gI() {
        if (me == null) {
            me = new SubTabBuyShop();
        }
        return me;
    }

    public void init() {
        Item item;
        int[] iArr;
        byte[] bArr;
        this.margin = 5;
        MyButton[] myButtonArr = new MyButton[1];
        this.btns = myButtonArr;
        FrameImage frameImage = LoadDataManager.myButtons[1];
        myButtonArr[0] = new MyButton(frameImage, frameImage, 55, 0, SupportTranslate.getTextLangue("ACCEPT"), 0, 0, new MyCommand("", 1, this));
        int i = this.btns[0].h;
        BoxInventory boxInventory = this.box;
        if (boxInventory == null || (item = boxInventory.item) == null || (iArr = item.price) == null || (bArr = item.typeMoneyServer) == null) {
            this.priceRadios = new MyRadio[0];
        } else {
            int numOptions = Math.min(iArr.length, bArr.length);
            this.priceRadios = new MyRadio[numOptions];
            int i2 = 0;
            while (i2 < numOptions) {
                MyRadio[] myRadioArr = this.priceRadios;
                FrameImage frameImage2 = LoadDataManager.frameRadio;
                myRadioArr[i2] = new MyRadio(0, 0, (int) frameImage2.frameWidth, (int) frameImage2.frameHeight, i2 == 0);
                MyRadio myRadio = this.priceRadios[i2];
                myRadio.nameRms = "price_option_" + this.box.item.id + "_" + i2;
                this.priceRadios[i2].dataSaveRms = i2;
                i2++;
            }
            if (numOptions > 0) {
                MyRadio.selectOneRadio(this.priceRadios, 0);
                this.selectedPriceIndex = 0;
                this.typeMoney = mGiftData.partTypeServerToTypeClient(this.box.item.typeMoneyServer[0]);
                setPrice();
            }
        }
        int[] radioGroupWidths = new int[this.priceRadios.length];
        int totalRadioWidth = 0;
        for (int i3 = 0; i3 < this.priceRadios.length; i3++) {
            int priceTextWidth = this.fontPaint.getWidth(this.box.item.price[i3] + "");
            int i4 = this.margin;
            radioGroupWidths[i3] = ((int) LoadDataManager.frameRadio.frameWidth) + i4 + priceTextWidth + i4 + LoadDataManager.imgMoney[0].getRWidth();
            totalRadioWidth += radioGroupWidths[i3];
            if (i3 < this.priceRadios.length - 1) {
                totalRadioWidth += this.margin;
            }
        }
        float f = LoadDataManager.framePage.frameHeight;
        this.arrH = new int[]{((int) LoadDataManager.framePage.frameHeight) + (this.margin * 21) + ((int) LoadDataManager.frameRadio.frameHeight), (int) LoadDataManager.frameBoxHanhTrang[0].frameHeight, this.fontPaint.getHeight(), (int) f, (int) f, (int) f, (int) f, (int) LoadDataManager.frameRadio.frameHeight};
        mFont mfont = this.fontPaint;
        int width = mfont.getWidth(SupportTranslate.getTextLangue("num") + ":");
        float f2 = LoadDataManager.frameMinus.frameWidth;
        int i5 = (int) f2;
        int i6 = ((int) f2) * 2;
        int i7 = (int) f2;
        int[] iArr2 = {(this.w - 14) - (this.margin * 2), (int) LoadDataManager.frameBoxHanhTrang[0].frameWidth, 0, width, i5, i6, i7, totalRadioWidth};
        this.arrW = iArr2;
        int i8 = width + i5;
        int i9 = this.margin;
        int sumWSoLuong = i8 + (i9 * 3) + i6 + i7;
        int i10 = this.x;
        int i11 = this.w;
        int i12 = iArr2[3];
        int i13 = iArr2[4];
        int[] iArr3 = {i10 + 7 + i9, i10 + ((i11 - iArr2[1]) / 2), i10 + (i11 / 2), i10 + ((i11 - sumWSoLuong) / 2), i10 + ((i11 - sumWSoLuong) / 2) + i12 + i9, i10 + ((i11 - sumWSoLuong) / 2) + i12 + i13 + (i9 * 2), i10 + ((i11 - sumWSoLuong) / 2) + i12 + i13 + iArr2[5] + (i9 * 3), i10 + ((i11 - totalRadioWidth) / 2)};
        this.arrX = iArr3;
        int i14 = this.y;
        int[] iArr4 = this.arrH;
        int i15 = iArr4[1];
        int i16 = iArr4[2];
        this.arrY = new int[]{i14 + 7 + i9, i14 + 7 + (i9 * 2), i14 + 7 + (i9 * 3) + i15, i14 + 7 + (i9 * 4) + i16 + i15 + i9, i14 + 7 + (i9 * 4) + i16 + i15 + i9, i14 + 7 + (i9 * 4) + i16 + i15 + i9, i14 + 7 + (i9 * 4) + i16 + i15 + i9, i14 + 7 + (i9 * 5) + i16 + i15 + iArr4[3] + (i9 * 2)};
        int currentX = iArr3[7];
        int i17 = 0;
        while (true) {
            MyRadio[] myRadioArr2 = this.priceRadios;
            if (i17 < myRadioArr2.length) {
                MyRadio myRadio2 = myRadioArr2[i17];
                myRadio2.x = currentX;
                myRadio2.y = this.arrY[7];
                FrameImage frameImage3 = LoadDataManager.frameRadio;
                myRadio2.w = (int) frameImage3.frameWidth;
                myRadio2.h = (int) frameImage3.frameHeight;
                currentX += radioGroupWidths[i17] + this.margin;
                i17++;
            } else {
                MyButton myButton = this.btns[0];
                myButton.setXY(this.x + ((this.w - myButton.w) / 2), this.arrY[0] + this.arrH[0] + (this.margin * 2));
                setPrice();
                return;
            }
        }
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        CanvasNinja.resetTrans(g);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgWhite1, (float) this.arrX[0], (float) this.arrY[0], (float) this.arrW[0], (float) this.arrH[0], 1, false);
        BoxInventory boxInventory = this.box;
        if (boxInventory != null) {
            boxInventory.paint(mgraphics, this.arrX[1], this.arrY[1]);
            this.fontPaint.drawString(g, this.box.item.getRealName().replace("\n", ""), this.arrX[2], this.arrY[2], 2);
        }
        this.fontPaint.drawString(mgraphics, SupportTranslate.getTextLangue("num") + ":", this.arrX[3], this.arrY[3] + 3);
        LoadDataManager.frameMinus.drawFrame(this.indexMinus, (float) this.arrX[4], (float) (this.arrY[4] + this.yAnimMinus), mgraphics);
        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.framePage, this.arrX[5], this.arrY[5], this.arrW[5], false, 0, false);
        this.fontPaintNum.drawString(g, this.num + "", (this.arrW[5] / 2) + this.arrX[5], this.arrY[5] + 5, 2);
        LoadDataManager.framePlus.drawFrame(this.indexPlus, (float) this.arrX[6], (float) (this.arrY[6] + this.yAnimPlus), mgraphics);
        int i = 0;
        while (true) {
            MyRadio[] myRadioArr = this.priceRadios;
            if (i >= myRadioArr.length) {
                break;
            }
            myRadioArr[i].paint(mgraphics);
            String optionPrice = this.box.item.price[i] + "";
            int typeMoneyOption = mGiftData.partTypeServerToTypeClient(this.box.item.typeMoneyServer[i]);
            mFont mfont = this.fontPaint;
            MyRadio myRadio = this.priceRadios[i];
            mfont.drawString(mgraphics, optionPrice, myRadio.x + myRadio.w + this.margin, myRadio.y + 3);
            Image image = LoadDataManager.imgMoney[typeMoneyOption];
            MyRadio myRadio2 = this.priceRadios[i];
            mgraphics.drawImage(image, (float) (myRadio2.x + myRadio2.w + this.fontPaint.getWidth(optionPrice) + (this.margin * 2)), (float) (this.priceRadios[i].y - 2));
            i++;
        }
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.paint(mgraphics);
            }
        }
        MyCommand myCommand = this.cmdClose;
        if (myCommand != null) {
            myCommand.paintIconOnly(mgraphics);
        }
        CanvasNinja.resetTrans(g);
    }

    public void update() {
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.update();
            }
        }
        this.indexMinus = 0;
        this.yAnimMinus = 0;
        this.yAnimPlus = 0;
        this.indexPlus = 0;
        for (MyRadio radio : this.priceRadios) {
            radio.update();
        }
    }

    public void updateKey() {
        MyButton[] myButtonArr = this.btns;
        if (myButtonArr != null) {
            for (MyButton btn : myButtonArr) {
                btn.updatePointer();
            }
        }
        MyCommand myCommand = this.cmdClose;
        if (myCommand != null) {
            myCommand.updatePointer();
        }
        if (CanvasNinja.isPointerRelease()) {
            if (CanvasNinja.isPoint(this.arrX[4], this.arrY[4], this.arrW[4], this.arrH[4])) {
                this.indexMinus = 1;
                this.yAnimMinus = 2;
                CanvasNinja.clearAllPointer();
                int i = this.num - 1;
                this.num = i;
                if (i < 1) {
                    this.num = 1;
                }
                setPrice();
            }
            if (CanvasNinja.isPoint(this.arrX[6], this.arrY[6], this.arrW[6], this.arrH[6])) {
                this.indexPlus = 1;
                this.yAnimPlus = 2;
                CanvasNinja.clearAllPointer();
                int i2 = this.num + 1;
                this.num = i2;
                if (i2 > 30000) {
                    this.num = 30000;
                }
                setPrice();
            }
            int i3 = 0;
            while (true) {
                MyRadio[] myRadioArr = this.priceRadios;
                if (i3 < myRadioArr.length) {
                    MyRadio myRadio = myRadioArr[i3];
                    if (!CanvasNinja.isPoint(myRadio.x, myRadio.y, myRadio.w, myRadio.h) || !CanvasNinja.isPointerRelease()) {
                        i3++;
                    } else {
                        MyRadio.selectOneRadio(this.priceRadios, i3);
                        this.selectedPriceIndex = i3;
                        this.typeMoney = mGiftData.partTypeServerToTypeClient(this.box.item.typeMoneyServer[i3]);
                        setPrice();
                        CanvasNinja.clearAllPointer();
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    private void setPrice() {
        Item item;
        BoxInventory boxInventory = this.box;
        if (!(boxInventory == null || (item = boxInventory.item) == null)) {
            int i = this.selectedPriceIndex;
            int[] iArr = item.price;
            if (i < iArr.length) {
                int price = iArr[i];
                this.priceStr = (this.num * price) + "";
                return;
            }
        }
        this.priceStr = "0";
    }

    public void show() {
        CanvasNinja.subTab = this;
        me = this;
    }

    public void keyPress(int keyCode) {
    }

    public void keyTyped() {
    }

    public void commandTab(int index, int subIndex) {
        super.commandTab(index, subIndex);
        switch (index) {
            case 1:
                BoxInventory boxInventory = this.box;
                if (boxInventory != null && this.selectedPriceIndex < boxInventory.item.typeMoneyServer.length) {
                    Res.outz(2, "Mua vật phẩm ở index:" + this.box.item.id + "_sl là:" + this.num + "_typeMoney:" + this.box.item.typeMoneyServer[this.selectedPriceIndex]);
                    SendMessage gI = SendMessage.gI();
                    short s = this.idNpc;
                    Item item = this.box.item;
                    gI.buyItemShop(s, 1, item.id, this.num, item.typeMoneyServer[this.selectedPriceIndex]);
                    CanvasNinja.subTab = null;
                    return;
                }
                return;
            default:
                return;
        }
    }
}
