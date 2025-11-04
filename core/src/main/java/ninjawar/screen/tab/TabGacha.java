package ninjawar.screen.tab;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.Vector;
import ninjawar.input.KEY;
import ninjawar.input.MyButton;
import ninjawar.message.SendMessage;
import ninjawar.model.BoxInventory;
import ninjawar.model.MyCommand;
import ninjawar.model.TextEffectAppear;
import ninjawar.mylib.AudioManager;
import ninjawar.mylib.FrameAtlas;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.object.Item;
import ninjawar.screen.subtab.SubTabBuyShop;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;
import ninjawar.template.ItemTemplate;

public class TabGacha extends TabScr {
    public static mFont fontPaintMoney = mFont.tahoma_7_brown_small;
    public static mFont fontPaintNameChar = mFont.tahoma_name_char_inventory;
    public static mFont fontPaintTagName = mFont.tahoma_brown_dv;
    public static mFont fontPaintTagNameFocus = mFont.tahoma_brown_focus_dv;
    public static TabGacha me;
    private int blinkInterval = 300;
    private boolean blinkState = false;
    public BoxInventory box;
    MyButton btnStart;
    private int cencoredFrameIndex = 0;
    private float centerX;
    private float centerY;
    private int convergePhaseDuration;
    private float convergeRadius;
    public int costGem = 10;
    public int costRyo = 1000;
    private int[] currentSlotOrder = {0, 1, 2, 3, 4};
    private int currentSpinCycle = 0;
    private int currentSpinIndex = 0;
    private long delayStartTime = 0;
    private int finalSpinIndex = -1;
    private int flipDuration = 400;
    private float[] flipProgress = new float[5];
    private long[] flipStartTime = new long[5];
    int hardCodeEffUp = 2;
    private int highestRarityCell = -1;
    private int highestRarityIndex = -1;
    public short idNpc;
    private int indexRewardGem;
    private int indexRewardRyo;
    int indexSelected = -1;
    int indexSelectedUsed = -1;
    public int indexTabIconSelected = 0;
    int indexTagNameSelected = 0;
    public int indexUpgradeSuccess = 0;
    public boolean isAnimUpgrade = false;
    private boolean isBlinking = false;
    boolean[] isClickTagIcon = new boolean[2];
    boolean[] isClickTagName = new boolean[4];
    public boolean isDark = false;
    private boolean isDelayedAtHighest = false;
    private boolean[] isFlipping = new boolean[5];
    boolean isHavePopup = false;
    private boolean isRevealing = false;
    private boolean[] isShaking = new boolean[5];
    private boolean isShuffling = false;
    private boolean[] isSlotRevealed = new boolean[5];
    private boolean isSpinning = false;
    boolean isStartShuffle;
    public boolean isSuccess = true;
    private long lastBlinkTime = 0;
    int lastIndexSelected = -1;
    int lastIndexSelectedUsed = -1;
    private long lastRevealTime = 0;
    private long lastSpinTime = 0;
    private int longDelayDuration = 2000;
    public int marginBottomMoney = 5;
    public int marginMoney = 10;
    int maxDocBox = 5;
    int maxODoc;
    private int maxShufflePhases = 7;
    private int maxSpinCycles = 3;
    public String[] moneyStr;
    public String nameTabGacha = "";
    public String[] nameTags;
    public String notify = "";
    int numNgangBox = 5;
    int numODoc;
    private int numSlots = 5;
    private int numSlotsBottom = 2;
    private int numSlotsTop = 3;
    int numTag = 2;
    private int[] phaseDurations = new int[7];
    private long[] phaseStartTimes = new long[7];
    private float[][] phaseTargetX;
    private float[][] phaseTargetY;
    private int revealDelay = 500;
    private int revealIndex = 0;
    private int[] revealOrder = new int[5];
    private int selectedSlotIndex = -1;
    private float shakeAmplitude = 10.0f;
    private int shakeDuration = 500;
    private float[] shakeOffsetX = new float[5];
    private float[] shakeOffsetY = new float[5];
    private long[] shakeStartTime = new long[5];
    private int shuffleDuration = 500;
    private int[][] shuffleOrders;
    private int shufflePhase = 0;
    private long shuffleStartTime = 0;
    private float[] slotAnimX = new float[5];
    private float[] slotAnimY = new float[5];
    private int spinDelay = 20;
    private int spinDelayIncrement = 3;
    private int[] spinOrder = {0, 1, 2, 3, 4, 9, 14, 19, 24, 23, 22, 21, 20, 15, 10, 5};
    int startUsed = 0;
    int sumWBoxNgang;
    int sumWMoney = 0;
    private int[] targetSlotOrder = new int[5];
    private float[] targetSlotX = new float[5];
    private float[] targetSlotY = new float[5];
    public TextEffectAppear textEffectAppear;
    public Vector<Item> vecGemFirst = new Vector<>();
    public Vector<Item> vecGemItems = new Vector<>();
    public Vector<Item> vecPaint = new Vector<>();
    public Vector<Item> vecRyoItems = new Vector<>();
    int wLeft;
    int wRight;
    int xMoreFromNextPrevious;
    int xMoreFromNextPreviousDefault;
    public int[] xStartMoney;
    private int[] yAnimGemSlots = new int[5];
    int[] yAnimTagIcon = new int[2];
    int[] yAnimTagName = new int[4];
    int yMoreFromNextPrevious;
    public int[] yStartMoney;

    public TabGacha() {
        int[] iArr = new int[2];
        iArr[1] = 5;
        iArr[0] = 7;
        this.shuffleOrders = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        int i = this.maxShufflePhases;
        int[] iArr2 = new int[2];
        iArr2[1] = 5;
        iArr2[0] = i;
        Class cls = Float.TYPE;
        this.phaseTargetX = (float[][]) Array.newInstance(cls, iArr2);
        int i2 = this.maxShufflePhases;
        int[] iArr3 = new int[2];
        iArr3[1] = 5;
        iArr3[0] = i2;
        this.phaseTargetY = (float[][]) Array.newInstance(cls, iArr3);
        this.convergePhaseDuration = 300;
        this.convergeRadius = 5.0f;
        this.isStartShuffle = false;
        this.indexRewardRyo = -1;
        this.indexRewardGem = -1;
    }

    public static TabGacha gI() {
        if (me == null) {
            me = new TabGacha();
        }
        return me;
    }

    public void cheatKQ(int targetIndex) {
        int i;
        if (targetIndex < 0 || targetIndex >= this.numSlots || this.vecGemItems.size() < this.numSlots || (i = this.indexRewardGem) < 0 || i >= this.vecGemItems.size()) {
            throw new IllegalArgumentException("Invalid target index or reward index, or insufficient items");
        } else if (targetIndex != this.indexRewardGem) {
            Vector<Item> vector = this.vecGemItems;
            vector.set(targetIndex, vector.get(this.indexRewardGem));
            this.vecGemItems.set(this.indexRewardGem, this.vecGemItems.get(targetIndex));
        }
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        SupportPaint supportPaint = CanvasNinja.paintz;
        FrameImage frameImage = LoadDataManager.frameTitle;
        int i = this.x + (this.w / 2);
        int i2 = this.arrW[5];
        supportPaint.paintTagFrame(g, frameImage, i - (i2 / 2), this.arrY[5], i2, false, 0, false);
        TabScr.fontPaintTile.drawString(g, this.nameTabGacha.toUpperCase(), (this.w / 2) + this.x, (this.arrH[5] / 2) + this.arrY[5], 3);
        this.cmdClose.paintIconOnly(mgraphics);
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgCam, (float) this.arrX[7], (float) this.arrY[7], (float) this.arrW[7], (float) this.arrH[7], 10, false);
        int i3 = this.arrX[7];
        int i4 = this.arrY[7];
        int i5 = this.margin;
        mgraphics.setClip(i3, i4 + i5, this.arrW[7], this.arrH[7] - (i5 * 2));
        if (this.indexTabIconSelected == 0) {
            paintRyoTab(g);
        } else {
            paintGemTab(g);
        }
        int i6 = 0;
        while (true) {
            int i7 = 1;
            if (i6 >= this.numTag) {
                break;
            }
            CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameTabIconHanhTrang, this.arrX[9], this.yAnimTagIcon[i6] + this.arrY[9] + ((this.arrH[9] + (this.margin * 2)) * i6), this.arrW[9], this.indexTabIconSelected != i6, 0, false);
            FrameImage frameImage2 = LoadDataManager.frameIconTabGacha[i6];
            if (frameImage2 != null) {
                if (this.indexTabIconSelected != i6) {
                    i7 = 0;
                }
                float f = ((float) this.arrX[9]) + ((((float) this.arrW[9]) - frameImage2.frameWidth) / 2.0f);
                int i8 = this.margin;
                int i9 = this.arrY[9] + this.yAnimTagIcon[i6];
                int i10 = this.arrH[9];
                frameImage2.drawFrame(i7, f + ((float) i8), (((float) (i9 + (((i8 * 2) + i10) * i6))) + ((((float) i10) - frameImage2.frameHeight) / 2.0f)) - 1.0f, mgraphics);
            }
            i6++;
        }
        CanvasNinja.resetTrans(g);
        paintMoney(mgraphics, 0);
        paintMoney(mgraphics, 1);
        paintMoney(mgraphics, 2);
        if (this.isDark) {
            mgraphics.setColor(0, 0.25f);
            mgraphics.fillRect(0, 0, CanvasNinja.w, CanvasNinja.h);
        }
        TextEffectAppear textEffectAppear2 = this.textEffectAppear;
        if (textEffectAppear2 != null) {
            textEffectAppear2.paint(mgraphics);
        }
    }

    private void paintRyoTab(mGraphics g) {
        int k;
        mGraphics mgraphics = g;
        for (int j = 0; j < this.numNgangBox; j++) {
            int k2 = 0;
            while (k2 < this.maxDocBox) {
                if (k2 < 1 || k2 > 3 || j < 1 || j > 3) {
                    int indexItem = (this.numNgangBox * k2) + j;
                    SupportPaint supportPaint = CanvasNinja.paintz;
                    FrameImage frameImage = LoadDataManager.frameBoxHanhTrangDefault;
                    int i = this.arrX[8];
                    int i2 = this.margin2;
                    int i3 = this.arrW[8];
                    int indexItem2 = indexItem;
                    k = k2;
                    supportPaint.paintTagFrame(g, frameImage, ((i2 + i3) * j) + i, this.arrY[8] + ((i2 + this.arrH[8]) * k2), i3, this.indexSelected == indexItem, 0, true);
                    if (isInRangeVecItem(indexItem2)) {
                        if (this.vecPaint.get(indexItem2).rarity != -1) {
                            SupportPaint supportPaint2 = CanvasNinja.paintz;
                            FrameImage frameImage2 = LoadDataManager.frameBoxHanhTrang[this.vecPaint.get(indexItem2).rarity];
                            int i4 = this.arrX[8];
                            int i5 = this.margin2;
                            int i6 = this.arrW[8];
                            supportPaint2.paintTagFrame(g, frameImage2, ((i5 + i6) * j) + i4, this.arrY[8] + (k * (i5 + this.arrH[8])), i6, this.indexSelected == indexItem2, 0, true);
                        }
                        Item item = this.vecPaint.get(indexItem2);
                        int i7 = this.arrX[8];
                        int i8 = this.margin2;
                        int i9 = this.arrW[8];
                        int i10 = ((i8 + i9) * j) + i7;
                        int i11 = this.arrY[8];
                        int i12 = this.arrH[8];
                        item.paintCenter(g, i10, i11 + (k * (i8 + i12)), i9, i12, true, this.vecPaint.get(indexItem2).num > 1);
                    }
                    if (this.isAnimUpgrade && indexItem2 == this.finalSpinIndex) {
                        int i13 = this.arrX[8];
                        int i14 = this.margin2;
                        int i15 = this.arrW[8];
                        int effectX = ((i13 + ((i14 + i15) * j)) + (i15 / 2)) - (LoadDataManager.frameEffectUpgradeSuccess.getRWidth() / 2);
                        int i16 = this.arrY[8];
                        int i17 = this.margin2;
                        int i18 = this.arrH[8];
                        LoadDataManager.frameEffectUpgradeSuccess.drawFrame(this.indexUpgradeSuccess, (float) (this.hardCodeEffUp + effectX), (float) (((i16 + (k * (i17 + i18))) + (i18 / 2)) - (LoadDataManager.frameEffectUpgradeSuccess.getRHeight() / 2)), mgraphics);
                    }
                } else {
                    k = k2;
                }
                k2 = k + 1;
            }
            int i19 = k2;
        }
        int i20 = this.arrX[8];
        int i21 = this.margin2;
        int i22 = this.arrW[8];
        int innerX = i20 + i21 + i22;
        int i23 = this.arrY[8];
        int i24 = this.arrH[8];
        int innerY = i23 + i21 + i24;
        int innerWidth = (i22 * 3) + (i21 * 2);
        int innerHeight = (i24 * 3) + (i21 * 2);
        mFont mfont = mFont.tahoma_7b_white;
        FrameImage frameImage3 = LoadDataManager.myButtons[3];
        this.btnStart = new MyButton(mfont, frameImage3, frameImage3, mFont.tahoma_7_brown.getWidth(this.costRyo + " ryo/lượt"), 2, "Bắt đầu", 0, 0, new MyCommand("", 1, (Object) null));
        int btnWidth = mFont.tahoma_7_brown.getWidth(this.costRyo + " ryo/lượt");
        int btnHeight = LoadDataManager.myButtons[1].getRHeight();
        int btnY = (((innerHeight - btnHeight) / 2) + innerY) - this.margin;
        this.btnStart.setXY(innerX + ((innerWidth - btnWidth) / 2), btnY);
        this.btnStart.paintButton(mgraphics, false);
        String belowText = this.costRyo + " ryo/lượt";
        String str = belowText;
        int i25 = btnY;
        mFont.tahoma_7_brown.drawString(g, belowText, innerX + ((innerWidth - mFont.tahoma_7_brown.getWidth(belowText)) / 2), btnY + btnHeight + this.margin, 0, true);
    }

    private void paintGemTab(mGraphics g) {
        int ySlot;
        int xSlot;
        int currentSlotWidth;
        int slotWidth;
        int slotHeight;
        int startYTop;
        int startYBottom;
        float[] originalX;
        float[] originalY;
        int i;
        int slotWidth2;
        mGraphics mgraphics;
        FrameAtlas frameAtlas;
        Item item = null;
        FrameAtlas frameAtlas2;
        int currentSlotWidth2;
        mGraphics mgraphics2 = g;
        int i2 = this.arrX[8];
        int i3 = this.margin2;
        int i4 = this.arrW[8];
        int innerX = i2 + i3 + i4;
        int i5 = this.arrY[8];
        int i6 = this.margin;
        int innerY = i5 - i6;
        int innerWidth = (i4 * 3) + (i3 * 2);
        int i7 = 2;
        int i8 = (this.arrH[8] * 3) + (i3 * 2);
        FrameImage frameImage = LoadDataManager.frameBgGem;
        int slotWidth3 = (int) frameImage.frameWidth;
        int slotHeight2 = (int) frameImage.frameHeight;
        int i9 = this.numSlotsTop;
        int startXTop = innerX + ((innerWidth - ((i9 * slotWidth3) + ((i9 - 1) * 5))) / 2);
        int startYTop2 = innerY + i6;
        int i10 = this.numSlotsBottom;
        int startXBottom = innerX + ((innerWidth - ((i10 * slotWidth3) + ((i10 - 1) * 5))) / 2);
        int startYBottom2 = startYTop2 + slotHeight2 + 5;
        int i11 = this.numSlots;
        float[] originalX2 = new float[i11];
        float[] originalY2 = new float[i11];
        for (int i12 = 0; i12 < this.numSlotsTop; i12++) {
            originalX2[i12] = (float) (startXTop + ((slotWidth3 + 5) * i12));
            originalY2[i12] = (float) startYTop2;
        }
        for (int i13 = 0; i13 < this.numSlotsBottom; i13++) {
            int i14 = this.numSlotsTop;
            originalX2[i13 + i14] = (float) (startXBottom + ((slotWidth3 + 5) * i13));
            originalY2[i14 + i13] = (float) startYBottom2;
        }
        int i15 = 0;
        while (true) {
            Item item2 = null;
            if (i15 < this.numSlots) {
                int slotIndex = this.currentSlotOrder[i15];
                boolean z = this.isShuffling;
                int xSlot2 = (int) (z ? this.slotAnimX[i15] : originalX2[slotIndex]);
                int ySlot2 = (int) (z ? this.slotAnimY[i15] : originalY2[slotIndex]);
                if (this.isShaking[slotIndex]) {
                    xSlot2 += (int) this.shakeOffsetX[slotIndex];
                    ySlot = ySlot2 + ((int) this.shakeOffsetY[slotIndex]);
                } else {
                    ySlot = ySlot2;
                }
                int ySlot3 = slotWidth3;
                if (this.isFlipping[slotIndex]) {
                    float progress = this.flipProgress[slotIndex];
                    if (progress < 0.5f) {
                        currentSlotWidth2 = (int) (((float) slotWidth3) * (1.0f - (progress / 0.5f)));
                    } else {
                        currentSlotWidth2 = (int) (((float) slotWidth3) * ((progress - 0.5f) / 0.5f));
                    }
                    currentSlotWidth = currentSlotWidth2;
                    xSlot = xSlot2 + ((slotWidth3 - currentSlotWidth2) / i7);
                } else {
                    currentSlotWidth = ySlot3;
                    xSlot = xSlot2;
                }
                if (slotIndex < this.vecGemItems.size()) {
                    item2 = this.vecGemItems.get(slotIndex);
                }
                Item item3 = item2;
                String gemText = item3 != null ? item3.template.name : "GEM";
                String priceText = item3 != null ? String.valueOf(item3.num) : "0";
                if (!this.isSlotRevealed[slotIndex]) {
                    if (!this.isFlipping[slotIndex]) {
                        i = i15;
                        originalY = originalY2;
                        originalX = originalX2;
                        startYBottom = startYBottom2;
                        startYTop = startYTop2;
                        item = item3;
                        slotHeight = slotHeight2;
                        slotWidth = slotWidth3;
                        int slotHeight3 = i7;
                        mGraphics mgraphics3 = mgraphics2;
                        slotWidth2 = slotIndex;
                    } else if (this.flipProgress[slotIndex] < 0.5f) {
                        i = i15;
                        originalY = originalY2;
                        originalX = originalX2;
                        startYBottom = startYBottom2;
                        startYTop = startYTop2;
                        item = item3;
                        slotHeight = slotHeight2;
                        slotWidth = slotWidth3;
                        int slotHeight4 = i7;
                        mGraphics mgraphics4 = mgraphics2;
                        slotWidth2 = slotIndex;
                    } else {
                        int slotIndex2 = slotIndex;
                        i = i15;
                        originalY = originalY2;
                        originalX = originalX2;
                        CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameBgGem, xSlot, ySlot, currentSlotWidth, false);
                        if (!(item3 == null || (frameAtlas2 = item3.template.frameAtlas) == null)) {
                            item3.template.frameAtlas.drawFrame(0, (float) (xSlot + ((currentSlotWidth - frameAtlas2.getRWidth()) / i7)), (float) ((ySlot + ((slotHeight2 - item3.template.frameAtlas.getRHeight()) / i7)) - 5), 0, g);
                        }
                        startYBottom = startYBottom2;
                        startYTop = startYTop2;
                        slotHeight = slotHeight2;
                        slotWidth = slotWidth3;
                        mFont.tahoma_7b_brown.drawString(g, gemText, xSlot + (currentSlotWidth / 2), ySlot + 5, 2);
                        SupportPaint supportPaint = CanvasNinja.paintz;
                        FrameImage frameImage2 = LoadDataManager.frameGiaTien;
                        slotWidth2 = slotIndex2;
                        supportPaint.paintTagFrame(g, frameImage2, xSlot + 2, (((ySlot + slotHeight) - frameImage2.getRHeight()) - i7) + this.yAnimGemSlots[slotWidth2], currentSlotWidth - 4, false, 0, false);
                        int i16 = i7;
                        mGraphics mgraphics5 = mgraphics2;
                        mFont.tahoma_7b_brown.drawString(g, priceText, xSlot + (currentSlotWidth / 2), (((ySlot + slotHeight) - LoadDataManager.frameGiaTien.getRHeight()) - i7) + (LoadDataManager.frameGiaTien.getRHeight() / i7) + this.yAnimGemSlots[slotWidth2], 3);
                        Item item4 = item3;
                    }
                    CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameCencored[this.cencoredFrameIndex], xSlot, ySlot, currentSlotWidth, false);
                    Item item5 = item;
                } else {
                    i = i15;
                    originalY = originalY2;
                    originalX = originalX2;
                    startYBottom = startYBottom2;
                    startYTop = startYTop2;
                    Item item6 = item3;
                    slotHeight = slotHeight2;
                    slotWidth = slotWidth3;
                    int slotHeight5 = i7;
                    mGraphics mgraphics6 = mgraphics2;
                    slotWidth2 = slotIndex;
                    CanvasNinja.paintz.paintTagFrame(g, LoadDataManager.frameBgGem, xSlot, ySlot, currentSlotWidth, false);
                    if (!(item3 == null || (frameAtlas = item3.template.frameAtlas) == null)) {
                        item3.template.frameAtlas.drawFrame(0, (float) (xSlot + ((currentSlotWidth - frameAtlas.getRWidth()) / slotHeight5)), (float) ((ySlot + ((slotHeight - item3.template.frameAtlas.getRHeight()) / slotHeight5)) - 5), 0, g);
                    }
                    mFont.tahoma_7b_brown.drawString(g, gemText, xSlot + (currentSlotWidth / 2), ySlot + 5, 2);
                    SupportPaint supportPaint2 = CanvasNinja.paintz;
                    FrameImage frameImage3 = LoadDataManager.frameGiaTien;
                    supportPaint2.paintTagFrame(g, frameImage3, xSlot + 2, this.yAnimGemSlots[slotWidth2] + (((ySlot + slotHeight) - frameImage3.getRHeight()) - slotHeight5), currentSlotWidth - 4, false, 0, false);
                    mFont.tahoma_7b_brown.drawString(g, priceText, xSlot + (currentSlotWidth / 2), (((ySlot + slotHeight) - LoadDataManager.frameGiaTien.getRHeight()) - slotHeight5) + (LoadDataManager.frameGiaTien.getRHeight() / slotHeight5) + this.yAnimGemSlots[slotWidth2], 3);
                }
                int i17 = this.selectedSlotIndex;
                if (slotWidth2 != i17 || i17 == -1) {
                    mgraphics = g;
                } else if (!this.isSlotRevealed[slotWidth2] || this.isFlipping[slotWidth2]) {
                    int focusX = xSlot - 2;
                    int focusY = ySlot - 2;
                    int focusWidth = currentSlotWidth + (2 * 2);
                    int focusHeight = slotHeight + (2 * 2);
                    mgraphics = g;
                    mgraphics.setColor(0, 255, 255);
                    for (int t = 0; t < 2; t++) {
                        mgraphics.drawRect(focusX - t, focusY - t, (t * 2) + focusWidth, focusHeight + (t * 2));
                    }
                    g.resetColor();
                } else {
                    mgraphics = g;
                }
                i15 = i + 1;
                mgraphics2 = mgraphics;
                originalY2 = originalY;
                originalX2 = originalX;
                startYBottom2 = startYBottom;
                startYTop2 = startYTop;
                slotHeight2 = slotHeight;
                slotWidth3 = slotWidth;
                i7 = 2;
            } else {
                int i18 = i15;
                float[] fArr = originalY2;
                float[] fArr2 = originalX2;
                mFont mfont = mFont.tahoma_7b_white;
                FrameImage frameImage4 = LoadDataManager.myButtons[3];
                MyButton myButton = new MyButton(mfont, frameImage4, frameImage4, mFont.tahoma_7_brown.getWidth("1000 ryo/lượt"), 2, "Bắt đầu", 0, 0, new MyCommand("", 2, (Object) null));
                myButton.isSmall = true;
                myButton.setXY(innerX + ((innerWidth - (mFont.tahoma_7_brown.getWidth("1000 ryo/lượt") + 4)) / 2), startYBottom2 + slotHeight2 + 5);
                myButton.paintButton(mgraphics2, false);
                return;
            }
        }
    }

    private boolean isInRangeVecItem(int index) {
        return index < this.vecPaint.size() && index >= 0 && this.vecPaint.get(index) != null;
    }

    private int getSumHElement(int numODoc2) {
        return (this.arrH[0] * numODoc2) + ((numODoc2 - 1) * this.margin2);
    }

    private void populateOuterCellsWithRandomItems() {
        this.vecPaint.removeAllElements();
        Vector<Item> sourceItems = this.indexTabIconSelected == 0 ? this.vecRyoItems : this.vecGemItems;
        if (sourceItems != null && !sourceItems.isEmpty()) {
            int[] outerCellIndices = {0, 1, 2, 3, 4, 9, 14, 19, 24, 23, 22, 21, 20, 15, 10, 5};
            for (int i = 0; i < 25; i++) {
                this.vecPaint.add(null);
            }
            for (int i2 = 0; i2 < outerCellIndices.length; i2++) {
                if (i2 < sourceItems.size()) {
                    this.vecPaint.set(outerCellIndices[i2], sourceItems.get(i2));
                }
            }
        }
    }

    private void startAnimUpgrade() {
        this.isAnimUpgrade = true;
        this.indexUpgradeSuccess = 0;
        AudioManager.upgradeSuccess();
    }

    private void determineFinalSpinIndex() {
        int i = this.indexRewardRyo;
        if (i >= 0) {
            int[] iArr = this.spinOrder;
            if (i < iArr.length) {
                this.finalSpinIndex = iArr[i];
                int maxRarity = -1;
                this.highestRarityCell = -1;
                int i2 = 0;
                while (true) {
                    int[] iArr2 = this.spinOrder;
                    if (i2 >= iArr2.length) {
                        break;
                    }
                    int cellIndex = iArr2[i2];
                    if (isInRangeVecItem(cellIndex) && this.vecPaint.get(cellIndex).rarity > maxRarity) {
                        maxRarity = this.vecPaint.get(cellIndex).rarity;
                        this.highestRarityCell = cellIndex;
                    }
                    i2++;
                }
                if (this.highestRarityCell != -1) {
                    int rewardIndexInSpinOrder = -1;
                    int highestIndexInSpinOrder = -1;
                    int i3 = 0;
                    while (true) {
                        int[] iArr3 = this.spinOrder;
                        if (i3 >= iArr3.length) {
                            break;
                        }
                        int i4 = iArr3[i3];
                        if (i4 == this.finalSpinIndex) {
                            rewardIndexInSpinOrder = i3;
                        }
                        if (i4 == this.highestRarityCell) {
                            highestIndexInSpinOrder = i3;
                        }
                        i3++;
                    }
                    if (Math.abs(rewardIndexInSpinOrder - highestIndexInSpinOrder) == 1) {
                        int[] iArr4 = this.spinOrder;
                        this.finalSpinIndex = iArr4[(highestIndexInSpinOrder + 1) % iArr4.length];
                        return;
                    }
                    return;
                }
                return;
            }
        }
        this.finalSpinIndex = this.spinOrder[(int) (Math.random() * ((double) this.spinOrder.length))];
    }

    public void init(String nameTabShop) {
        this.nameTabGacha = nameTabShop;
        Vector<Item> sourceItems = this.indexTabIconSelected == 0 ? this.vecRyoItems : this.vecGemItems;
        this.vecPaint = (Vector) sourceItems.clone();
        for (int i = 0; i < this.numSlots; i++) {
            this.isSlotRevealed[i] = true;
            this.isFlipping[i] = false;
            this.flipProgress[i] = 0.0f;
            this.flipStartTime[i] = 0;
        }
        this.isRevealing = false;
        this.revealIndex = 0;
        this.selectedSlotIndex = -1;
        populateOuterCellsWithRandomItems();
        String[] arrayLangue = SupportTranslate.getArrayLangue("TAG_NAME_INVENTORY");
        this.nameTags = arrayLangue;
        this.yAnimTagName = new int[arrayLangue.length];
        this.isClickTagName = new boolean[arrayLangue.length];
        this.w = 360;
        this.h = 340;
        int i2 = CanvasNinja.w;
        if (360 > i2) {
            this.w = (i2 - (this.margin * 5)) - ((int) LoadDataManager.frameTabIconHanhTrang.frameWidth);
        }
        int i3 = CanvasNinja.h;
        if (340 > i3) {
            this.h = i3 - (this.margin * 2);
        }
        initStart();
        this.y += 5;
        int hHanhTrang = (((this.h - (this.margin7 * 2)) - (this.margin * 2)) - 20) - 14;
        float f = LoadDataManager.frameBoxUsed.frameHeight;
        this.arrH = new int[]{(int) f, (int) f, LoadDataManager.imgCucDat.getRHeight(), 90, 0, (int) LoadDataManager.frameTitle.frameHeight, 0, hHanhTrang, (int) LoadDataManager.frameBoxHanhTrang[0].frameHeight, (int) LoadDataManager.frameTabIconHanhTrang.frameHeight};
        int wCucDat = LoadDataManager.imgCucDat.getRWidth();
        int wBoxUsed = (int) LoadDataManager.frameBoxUsed.frameWidth;
        int i4 = this.margin7;
        this.wLeft = i4;
        int i5 = (this.w - i4) - this.margin;
        this.wRight = i5;
        int wKhungXam = (i5 - (LoadDataManager.imgClose.getRWidth() / 2)) - this.margin7;
        int numTab = this.nameTags.length;
        int wTagTitleHanhTrang = Res.fixSizeTagFrameDown(7, (((this.wRight - LoadDataManager.imgClose.getRWidth()) - this.margin7) - (this.margin * numTab)) / numTab, LoadDataManager.frameTagTitleHanhTrang);
        int width = TabScr.fontPaintTile.getWidth(nameTabShop.toUpperCase());
        int i6 = this.margin;
        int i7 = width + (i6 * 10);
        int i8 = (int) LoadDataManager.frameBoxHanhTrang[0].frameWidth;
        int[] iArr = {wBoxUsed, wBoxUsed, wCucDat, 22, 0, i7, wTagTitleHanhTrang, wKhungXam, i8, (int) LoadDataManager.frameTabIconHanhTrang.frameWidth};
        this.arrW = iArr;
        int i9 = this.x;
        Vector<Item> vector = sourceItems;
        int xODauTien = this.margin7 + i9 + (i6 * 2);
        int i10 = hHanhTrang;
        int xRight = this.wLeft + i9 + i6 + this.margin3;
        this.numNgangBox = 5;
        this.maxDocBox = 5;
        int i11 = (i8 * 5) + ((5 - 1) * this.margin2);
        this.sumWBoxNgang = i11;
        int i12 = ((int) LoadDataManager.framePrevious.frameWidth) + i6;
        this.xMoreFromNextPreviousDefault = i12;
        this.xMoreFromNextPrevious = i12;
        int i13 = wBoxUsed;
        int i14 = iArr[0];
        this.arrX = new int[]{xODauTien, xODauTien + i14 + wCucDat + (10 * 2), xODauTien + i14 + 10, xODauTien + i14 + 10 + (wCucDat / 2), xODauTien + i14 + 10 + (wCucDat / 2), (i14 + xODauTien) - (i7 / 2), xRight, xRight, xRight + ((wKhungXam - i11) / 2), ((i9 + this.w) - (i6 * 4)) + 1};
        this.numODoc = 4;
        this.maxODoc = 4;
        int sumHElement = getSumHElement(4);
        int i15 = this.y;
        int yODauTien = ((this.h - sumHElement) / 2) + i15;
        int[] iArr2 = this.arrH;
        int yCucDat = yODauTien + ((sumHElement - (iArr2[2] + 60)) / 2) + 60;
        int i16 = this.margin;
        int i17 = sumHElement;
        int yDauTienPhai = i15 + (i16 * 3) + this.margin3;
        int i18 = iArr2[6];
        MyCommand myCommand = this.cmdClose;
        this.arrY = new int[]{yODauTien, yODauTien, yCucDat, (yCucDat + (iArr2[2] / 2)) - 4, yODauTien - (i16 * 2), i15 - (iArr2[5] / 2), yDauTienPhai, yDauTienPhai + i18 + i16, yDauTienPhai + i18 + i16 + i16, myCommand.y + myCommand.h + (this.margin7 * 2)};
        initMoney();
        determineFinalSpinIndex();
        for (int i19 = 0; i19 < this.numSlots; i19++) {
            this.yAnimGemSlots[i19] = 0;
            this.slotAnimX[i19] = 0.0f;
            this.slotAnimY[i19] = 0.0f;
            this.targetSlotX[i19] = 0.0f;
            this.targetSlotY[i19] = 0.0f;
        }
        for (int i20 = 0; i20 < this.numSlots; i20++) {
            this.isShaking[i20] = false;
            this.shakeOffsetX[i20] = 0.0f;
            this.shakeOffsetY[i20] = 0.0f;
            this.shakeStartTime[i20] = 0;
        }
    }

    public void initMoney() {
        int[] money = Player.myCharz().getMoneyInven();
        this.moneyStr = new String[3];
        this.xStartMoney = new int[3];
        this.yStartMoney = new int[3];
        this.sumWMoney = 0;
        int yStartTemp = this.arrY[7] + this.arrH[7] + this.margin + 23;
        int xDefault = this.arrX[7];
        int i = 0;
        while (true) {
            Image[] imageArr = LoadDataManager.imgBgMoney;
            if (i < imageArr.length) {
                this.sumWMoney += imageArr[i].getRWidth();
                this.moneyStr[i] = Res.formatMoney(money[i]);
                this.yStartMoney[i] = (yStartTemp - LoadDataManager.imgBgMoney[i].getRHeight()) - this.marginBottomMoney;
                String str = this.moneyStr[i];
                int i2 = this.arrX[7];
                this.xStartMoney[i] = xDefault + getAlignMoney(i, str, xDefault, (this.arrW[7] + i2) - i2);
                i++;
            } else {
                this.sumWMoney += (imageArr.length - 1) * this.marginMoney;
                return;
            }
        }
    }

    private int getAlignMoney(int type, String moneyStr2, int xStart, int wPaint) {
        int sumW = LoadDataManager.imgMoney[type].getRWidth() + this.margin + fontPaintMoney.getWidth(moneyStr2);
        if (type == 1) {
            return (wPaint - sumW) / 2;
        }
        if (type == 2) {
            return wPaint - sumW;
        }
        return 0;
    }

    public void paintMoney(mGraphics g, int type) {
        int yMore = 0;
        if (type == 2) {
            yMore = -2;
        }
        if (type == 1) {
            yMore = -4;
        }
        g.drawImage(LoadDataManager.imgMoney[type], (float) this.xStartMoney[type], (float) (this.yStartMoney[type] + yMore));
        if (type == 0) {
            fontPaintMoney.drawString(g, this.moneyStr[type], this.xStartMoney[type] + LoadDataManager.imgMoney[type].getRWidth() + this.margin, this.yStartMoney[0] + 7);
        }
        if (type == 1) {
            fontPaintMoney.drawString(g, this.moneyStr[type], this.xStartMoney[type] + LoadDataManager.imgMoney[type].getRWidth() + this.margin, (this.yStartMoney[type] + 7) - 2);
        }
        if (type == 2) {
            fontPaintMoney.drawString(g, this.moneyStr[type], this.xStartMoney[type] + LoadDataManager.imgMoney[type].getRWidth() + this.margin, (this.yStartMoney[type] + 7) - 2);
        }
    }

    public void update() {
        int i;
        int i2;
        int ySlot;
        int xSlot;
        ItemTemplate itemTemplate;
        ItemTemplate itemTemplate2;
        long currentTime = System.currentTimeMillis();
        if (this.isSpinning) {
            if (!this.isDelayedAtHighest) {
                int i3 = this.spinDelay;
                if (currentTime - this.lastSpinTime >= ((long) i3)) {
                    int[] iArr = this.spinOrder;
                    int length = (this.currentSpinIndex + 1) % iArr.length;
                    this.currentSpinIndex = length;
                    int i4 = iArr[length];
                    this.indexSelected = i4;
                    int i5 = this.currentSpinCycle + 1;
                    this.currentSpinCycle = i5;
                    this.spinDelay = i3 + this.spinDelayIncrement;
                    if (i5 >= this.maxSpinCycles * iArr.length && i4 == this.finalSpinIndex) {
                        this.isDelayedAtHighest = true;
                        this.delayStartTime = currentTime;
                    }
                    this.lastSpinTime = currentTime;
                }
            } else if (currentTime - this.delayStartTime >= ((long) this.longDelayDuration)) {
                this.isSpinning = false;
                this.isDelayedAtHighest = false;
                this.isBlinking = true;
                this.lastBlinkTime = currentTime;
                this.indexSelected = this.finalSpinIndex;
                startAnimUpgrade();
            }
        }
        if (this.isBlinking && currentTime - this.lastBlinkTime >= ((long) this.blinkInterval)) {
            boolean z = !this.blinkState;
            this.blinkState = z;
            this.indexSelected = z ? this.finalSpinIndex : -1;
            this.lastBlinkTime = currentTime;
        }
        if (this.isAnimUpgrade && CanvasNinja.gameTick % 2 == 0) {
            int i6 = this.indexUpgradeSuccess + 1;
            this.indexUpgradeSuccess = i6;
            if (i6 > LoadDataManager.frameEffectUpgradeSuccess.nFrame - 1) {
                this.indexUpgradeSuccess = 0;
                this.isAnimUpgrade = false;
                this.isBlinking = false;
                if (isInRangeVecItem(this.finalSpinIndex)) {
                    Item rewardItem = this.vecPaint.get(this.finalSpinIndex);
                    this.notify = "Chúc mừng bạn may mắn nhận được " + rewardItem.template.name + " x" + rewardItem.num;
                    this.indexRewardRyo = -1;
                    this.textEffectAppear = new TextEffectAppear(rewardItem.getImage(), mFont.tahoma_7b_yellow_border_orange, LoadDataManager.frameBgTextAppear, this.notify, -1, (byte) 1, 0, 0);
                    this.isDark = true;
                }
            }
        }
        TextEffectAppear textEffectAppear2 = this.textEffectAppear;
        if (textEffectAppear2 != null) {
            textEffectAppear2.update();
            if (this.textEffectAppear.isRemove) {
                this.isDark = false;
                this.textEffectAppear = null;
                initMoney();
            }
        }
        for (int i7 = 0; i7 < this.numSlots; i7++) {
            boolean[] zArr = this.isFlipping;
            if (zArr[i7]) {
                float progress = ((float) (currentTime - this.flipStartTime[i7])) / ((float) this.flipDuration);
                if (progress >= 1.0f) {
                    zArr[i7] = false;
                    this.flipProgress[i7] = 1.0f;
                    this.isSlotRevealed[i7] = true;
                } else {
                    this.flipProgress[i7] = progress;
                }
            }
        }
        for (int i8 = 0; i8 < this.numSlots; i8++) {
            boolean[] zArr2 = this.isShaking;
            if (zArr2[i8]) {
                float progress2 = ((float) (currentTime - this.shakeStartTime[i8])) / ((float) this.shakeDuration);
                if (progress2 >= 1.0f) {
                    zArr2[i8] = false;
                    this.shakeOffsetX[i8] = 0.0f;
                    this.shakeOffsetY[i8] = 0.0f;
                } else {
                    this.shakeOffsetX[i8] = this.shakeAmplitude * ((float) Math.sin(((double) progress2) * 3.141592653589793d * 8.0d)) * (1.0f - progress2);
                    this.shakeOffsetY[i8] = 0.0f;
                }
            }
        }
        if (this.isRevealing) {
            long currentTimeReveal = System.currentTimeMillis();
            String str = " x";
            if (currentTimeReveal - this.lastRevealTime >= ((long) this.revealDelay)) {
                int i9 = this.revealIndex;
                String priceText = "0";
                String str2 = "GEM";
                if (i9 < this.numSlots) {
                    int slotToReveal = this.revealOrder[i9];
                    this.isFlipping[slotToReveal] = true;
                    this.flipProgress[slotToReveal] = 0.0f;
                    this.flipStartTime[slotToReveal] = currentTimeReveal;
                    this.isShaking[slotToReveal] = true;
                    this.shakeStartTime[slotToReveal] = currentTimeReveal;
                    AudioManager.buttonClick();
                    Item item = slotToReveal < this.vecGemItems.size() ? this.vecGemItems.get(slotToReveal) : null;
                    if (!(item == null || (itemTemplate2 = item.template) == null)) {
                        str2 = itemTemplate2.name;
                    }
                    String gemText = str2;
                    if (item != null) {
                        priceText = String.valueOf(item.num);
                    }
                    System.out.println("Revealing slot " + slotToReveal + ": " + gemText + " for " + priceText);
                    this.revealIndex = this.revealIndex + 1;
                    this.lastRevealTime = currentTimeReveal;
                } else {
                    this.isRevealing = false;
                    int i10 = this.selectedSlotIndex;
                    if (i10 != -1) {
                        FrameImage frameImage = LoadDataManager.frameBgGem;
                        int slotWidth = (int) frameImage.frameWidth;
                        int slotHeight = (int) frameImage.frameHeight;
                        int i11 = this.arrX[8];
                        int i12 = this.margin2;
                        int i13 = this.arrW[8];
                        int innerX = i11 + i12 + i13;
                        int i14 = this.arrY[8];
                        int i15 = this.margin;
                        int innerY = i14 - i15;
                        int innerWidth = (i13 * 3) + (i12 * 2);
                        int xSlot2 = this.numSlotsTop;
                        int startXTop = innerX + ((innerWidth - ((xSlot2 * slotWidth) + ((xSlot2 - 1) * 5))) / 2);
                        int startYTop = i15 + innerY;
                        int i16 = innerY;
                        int innerY2 = this.numSlotsBottom;
                        int startXBottom = ((innerWidth - ((innerY2 * slotWidth) + ((innerY2 - 1) * 5))) / 2) + innerX;
                        int startYBottom = startYTop + slotHeight + 5;
                        if (i10 < xSlot2) {
                            xSlot = startXTop + ((slotWidth + 5) * i10) + (slotWidth / 2);
                            ySlot = startYTop + (slotHeight / 2);
                        } else {
                            xSlot = ((i10 - xSlot2) * (slotWidth + 5)) + startXBottom + (slotWidth / 2);
                            ySlot = startYBottom + (slotHeight / 2);
                        }
                        int i17 = innerX;
                        Item selectedItem = i10 < this.vecGemItems.size() ? this.vecGemItems.get(this.selectedSlotIndex) : null;
                        if (!(selectedItem == null || (itemTemplate = selectedItem.template) == null)) {
                            str2 = itemTemplate.name;
                        }
                        String itemName = str2;
                        if (selectedItem != null) {
                            priceText = String.valueOf(selectedItem.num);
                        }
                        StringBuilder sb = new StringBuilder();
                        int i18 = innerWidth;
                        sb.append("Chúc mừng bạn nhận được ");
                        sb.append(itemName);
                        sb.append(str);
                        sb.append(priceText);
                        this.notify = sb.toString();
                        this.textEffectAppear = new TextEffectAppear(selectedItem != null ? selectedItem.getImage() : null, mFont.tahoma_7b_yellow_border_orange, LoadDataManager.frameBgTextAppear, this.notify, -1, (byte) 0, xSlot, ySlot);
                        this.isDark = true;
                        AudioManager.upgradeSuccess();
                    }
                    this.selectedSlotIndex = -1;
                }
            }
        }
        int i19 = 0;
        while (true) {
            boolean[] zArr3 = this.isClickTagIcon;
            if (i19 >= zArr3.length) {
                break;
            }
            if (zArr3[i19]) {
                int[] iArr2 = this.yAnimTagIcon;
                int i20 = iArr2[i19] + 1;
                iArr2[i19] = i20;
                if (i20 >= 2) {
                    iArr2[i19] = 0;
                    zArr3[i19] = false;
                }
            }
            i19++;
        }
        for (int i21 = 0; i21 < this.numSlots; i21++) {
            int[] iArr3 = this.yAnimGemSlots;
            int i22 = iArr3[i21];
            if (i22 > 0) {
                iArr3[i21] = i22 - 1;
            }
        }
        if (this.isShuffling) {
            long[] jArr = this.phaseStartTimes;
            int i23 = this.shufflePhase;
            if (jArr[i23] == 0) {
                jArr[i23] = currentTime;
            }
            float progress3 = Math.min(1.0f, ((float) (currentTime - jArr[i23])) / ((float) this.phaseDurations[i23]));
            float easedProgress = progress3 * progress3 * (3.0f - (2.0f * progress3));
            for (int i24 = 0; i24 < this.numSlots; i24++) {
                float[] fArr = this.slotAnimX;
                float f = fArr[i24];
                float[][] fArr2 = this.phaseTargetX;
                int i25 = this.shufflePhase;
                fArr[i24] = f + ((fArr2[i25][i24] - f) * easedProgress);
                float[] fArr3 = this.slotAnimY;
                float f2 = fArr3[i24];
                fArr3[i24] = f2 + ((this.phaseTargetY[i25][i24] - f2) * easedProgress);
            }
            if (progress3 >= 1.0f) {
                int i26 = this.shufflePhase + 1;
                this.shufflePhase = i26;
                if (i26 < this.maxShufflePhases) {
                    int i27 = 0;
                    while (true) {
                        i2 = this.numSlots;
                        if (i27 >= i2) {
                            break;
                        }
                        float[] fArr4 = this.slotAnimX;
                        float[][] fArr5 = this.phaseTargetX;
                        int i28 = this.shufflePhase;
                        fArr4[i27] = fArr5[i28 - 1][i27];
                        float[] fArr6 = this.slotAnimY;
                        float[][] fArr7 = this.phaseTargetY;
                        fArr6[i27] = fArr7[i28 - 1][i27];
                        this.targetSlotX[i27] = fArr5[i28][i27];
                        this.targetSlotY[i27] = fArr7[i28][i27];
                        i27++;
                    }
                    long[] jArr2 = this.phaseStartTimes;
                    int i29 = this.shufflePhase;
                    jArr2[i29] = 0;
                    System.arraycopy(this.shuffleOrders[i29], 0, this.currentSlotOrder, 0, i2);
                } else {
                    this.isShuffling = false;
                    this.shufflePhase = 0;
                    int i30 = 0;
                    while (true) {
                        i = this.numSlots;
                        if (i30 >= i) {
                            break;
                        }
                        float[] fArr8 = this.slotAnimX;
                        float[][] fArr9 = this.phaseTargetX;
                        int i31 = this.maxShufflePhases;
                        fArr8[i30] = fArr9[i31 - 1][i30];
                        this.slotAnimY[i30] = this.phaseTargetY[i31 - 1][i30];
                        i30++;
                    }
                    System.arraycopy(this.shuffleOrders[this.maxShufflePhases - 1], 0, this.currentSlotOrder, 0, i);
                    for (int i32 = 0; i32 < this.numSlots; i32++) {
                        this.isSlotRevealed[i32] = false;
                    }
                }
            }
        }
        this.btnStart.update();
    }

    private void startSpin() {
        this.isSpinning = true;
        this.currentSpinIndex = 0;
        this.currentSpinCycle = 0;
        this.spinDelay = 20;
        this.lastSpinTime = System.currentTimeMillis();
        this.isBlinking = false;
        this.isDelayedAtHighest = false;
        this.indexSelected = this.spinOrder[this.currentSpinIndex];
    }

    private void startShuffle() {
        int i;
        int slotHeight;
        if (this.isStartShuffle) {
            AudioManager.buttonClick();
            this.cencoredFrameIndex = new Random().nextInt(6);
            this.isShuffling = true;
            this.shufflePhase = 0;
            this.shuffleStartTime = System.currentTimeMillis();
            int i2 = 0;
            while (true) {
                i = this.numSlots;
                if (i2 >= i) {
                    break;
                }
                this.isSlotRevealed[i2] = false;
                i2++;
            }
            FrameImage frameImage = LoadDataManager.frameBgGem;
            int slotWidth = (int) frameImage.frameWidth;
            int slotHeight2 = (int) frameImage.frameHeight;
            int i3 = this.arrX[8];
            int i4 = this.margin2;
            int i5 = this.arrW[8];
            int innerX = i3 + i4 + i5;
            int i6 = this.arrY[8];
            int i7 = this.margin;
            int innerWidth = (i5 * 3) + (i4 * 2);
            int innerHeight = (this.arrH[8] * 3) + (i4 * 2);
            int i8 = this.numSlotsTop;
            int startXTop = ((innerWidth - ((i8 * slotWidth) + ((i8 - 1) * 5))) / 2) + innerX;
            int startYTop = i7 + (i6 - i7);
            int i9 = this.numSlotsBottom;
            int startXBottom = ((innerWidth - ((i9 * slotWidth) + ((i9 - 1) * 5))) / 2) + innerX;
            int startYBottom = startYTop + slotHeight2 + 5;
            float[] originalX = new float[i];
            float[] originalY = new float[i];
            int i10 = 0;
            while (true) {
                int innerX2 = innerX;
                if (i10 >= this.numSlotsTop) {
                    break;
                }
                int i11 = this.currentSlotOrder[i10];
                originalX[i11] = (float) (startXTop + ((slotWidth + 5) * i10));
                originalY[i11] = (float) startYTop;
                i10++;
                innerX = innerX2;
                innerHeight = innerHeight;
            }
            int i12 = 0;
            while (i12 < this.numSlotsBottom) {
                int[] iArr = this.currentSlotOrder;
                int i13 = this.numSlotsTop;
                originalX[iArr[i12 + i13]] = (float) (startXBottom + ((slotWidth + 5) * i12));
                originalY[iArr[i13 + i12]] = (float) startYBottom;
                i12++;
                innerWidth = innerWidth;
            }
            for (int i14 = 0; i14 < this.numSlots; i14++) {
                this.slotAnimX[i14] = originalX[i14];
                this.slotAnimY[i14] = originalY[i14];
            }
            this.centerX = (((float) CanvasNinja.w) / 2.0f) - (((float) slotWidth) / 2.0f);
            this.centerY = (((float) CanvasNinja.h) / 2.0f) - (((float) slotHeight2) / 2.0f);
            this.maxShufflePhases = 10;
            this.phaseStartTimes = new long[10];
            this.phaseDurations = new int[10];
            int[] iArr2 = new int[2];
            iArr2[1] = 5;
            char c = 0;
            iArr2[0] = 10;
            this.shuffleOrders = (int[][]) Array.newInstance(Integer.TYPE, iArr2);
            int i15 = this.maxShufflePhases;
            int[] iArr3 = new int[2];
            iArr3[1] = 5;
            iArr3[0] = i15;
            Class cls = Float.TYPE;
            this.phaseTargetX = (float[][]) Array.newInstance(cls, iArr3);
            int i16 = this.maxShufflePhases;
            int[] iArr4 = new int[2];
            iArr4[1] = 5;
            iArr4[0] = i16;
            this.phaseTargetY = (float[][]) Array.newInstance(cls, iArr4);
            this.shuffleOrders[0] = (int[]) this.currentSlotOrder.clone();
            this.phaseDurations[0] = this.convergePhaseDuration;
            int i17 = 0;
            while (i17 < this.numSlots) {
                this.phaseTargetX[c][i17] = this.centerX;
                this.phaseTargetY[c][i17] = this.centerY;
                i17++;
                c = 0;
            }
            Random rand = new Random();
            this.shuffleOrders[1] = (int[]) this.currentSlotOrder.clone();
            int phase = 1;
            while (phase < this.maxShufflePhases) {
                int[][] iArr5 = this.shuffleOrders;
                iArr5[phase] = (int[]) iArr5[phase - 1].clone();
                int i18 = this.numSlots - 1;
                while (i18 > 0) {
                    int j = rand.nextInt(i18 + 1);
                    float[] originalX2 = originalX;
                    int[] iArr6 = this.shuffleOrders[phase];
                    int temp = iArr6[i18];
                    iArr6[i18] = iArr6[j];
                    iArr6[j] = temp;
                    i18--;
                    originalX = originalX2;
                }
                this.phaseDurations[phase] = (int) (rand.nextInt(200) + 2.8E-43f);
                this.phaseStartTimes[phase] = 0;
                phase++;
                originalX = originalX;
            }
            for (int phase2 = 1; phase2 < this.maxShufflePhases; phase2++) {
                int i19 = 0;
                while (i19 < this.numSlots) {
                    int slotIndex = this.shuffleOrders[phase2][i19];
                    int i20 = this.numSlotsTop;
                    if (i19 < i20) {
                        slotHeight = slotHeight2;
                        this.phaseTargetX[phase2][slotIndex] = (float) (startXTop + ((slotWidth + 5) * i19));
                        this.phaseTargetY[phase2][slotIndex] = (float) startYTop;
                    } else {
                        slotHeight = slotHeight2;
                        this.phaseTargetX[phase2][slotIndex] = (float) (((i19 - i20) * (slotWidth + 5)) + startXBottom);
                        this.phaseTargetY[phase2][slotIndex] = (float) startYBottom;
                    }
                    i19++;
                    slotHeight2 = slotHeight;
                }
            }
            for (int i21 = 0; i21 < this.numSlots; i21++) {
                this.targetSlotX[i21] = this.phaseTargetX[0][i21];
                this.targetSlotY[i21] = this.phaseTargetY[0][i21];
            }
        }
    }

    public void updateKey() {
        int xSlot;
        int totalWidthTop;
        int slotIndex;
        int ySlot;
        this.btnStart.updatePointer();
        this.cmdClose.updateIconOnly();
        if (CanvasNinja.isPointerRelease()) {
            int i = this.arrX[8];
            int i2 = this.margin2;
            int i3 = this.arrW[8];
            int inner3x3TopLeftX = i + ((i2 + i3) * 1);
            int inner3x3TopLeftY = this.arrY[8];
            int inner3x3Width = (i3 * 3) + (i2 * 2);
            int inner3x3Height = (this.arrH[8] * 3) + (i2 * 2);
            if (this.indexTabIconSelected == 1) {
                FrameImage frameImage = LoadDataManager.frameBgGem;
                int slotWidth = (int) frameImage.frameWidth;
                int slotHeight = (int) frameImage.frameHeight;
                int i4 = this.numSlotsTop;
                int totalWidthTop2 = (i4 * slotWidth) + ((i4 - 1) * 5);
                int startXTop = ((inner3x3Width - totalWidthTop2) / 2) + inner3x3TopLeftX;
                int startYTop = this.margin + inner3x3TopLeftY;
                int i5 = this.numSlotsBottom;
                int startXBottom = ((inner3x3Width - ((i5 * slotWidth) + ((i5 - 1) * 5))) / 2) + inner3x3TopLeftX;
                int startYBottom = startYTop + slotHeight + 5;
                int i6 = 0;
                while (true) {
                    if (i6 >= this.numSlots) {
                        int i7 = startXTop;
                        int i8 = totalWidthTop2;
                        int i9 = startYTop;
                        int i10 = startXBottom;
                        break;
                    }
                    if (this.isShuffling) {
                        int xSlot2 = (int) this.slotAnimX[i6];
                        int i11 = totalWidthTop2;
                        totalWidthTop = (int) this.slotAnimY[i6];
                        slotIndex = xSlot2;
                        xSlot = i11;
                    } else {
                        int slotIndex2 = this.currentSlotOrder[i6];
                        xSlot = totalWidthTop2;
                        int xSlot3 = this.numSlotsTop;
                        if (slotIndex2 < xSlot3) {
                            slotIndex = ((slotWidth + 5) * slotIndex2) + startXTop;
                            totalWidthTop = startYTop;
                        } else {
                            slotIndex = ((slotIndex2 - xSlot3) * (slotWidth + 5)) + startXBottom;
                            totalWidthTop = startYBottom;
                        }
                    }
                    if (CanvasNinja.isPoint(slotIndex, totalWidthTop, slotWidth, slotHeight)) {
                        int i12 = slotIndex;
                        if (this.isRevealing && !this.isShuffling && this.isStartShuffle) {
                            this.isStartShuffle = false;
                            AudioManager.buttonClick();
                            this.selectedSlotIndex = this.currentSlotOrder[i6];
                            SendMessage.gI().requestIndexRewardItemGachaGem();
                            CanvasNinja.clearAllPointer();
                            int j = 0;
                            while (true) {
                                int slotWidth2 = slotWidth;
                                if (j >= this.numSlots) {
                                    break;
                                }
                                this.isSlotRevealed[j] = false;
                                this.isFlipping[j] = false;
                                this.flipProgress[j] = 0.0f;
                                this.flipStartTime[j] = 0;
                                j++;
                                slotWidth = slotWidth2;
                            }
                            Vector<Integer> tempOrder = new Vector<>();
                            int j2 = 0;
                            while (true) {
                                int startXTop2 = startXTop;
                                if (j2 >= this.numSlots) {
                                    break;
                                }
                                if (j2 != this.selectedSlotIndex) {
                                    tempOrder.add(Integer.valueOf(j2));
                                }
                                j2++;
                                startXTop = startXTop2;
                            }
                            int j3 = 0;
                            while (j3 < tempOrder.size() - 1) {
                                int k = j3 + 1;
                                while (true) {
                                    ySlot = totalWidthTop;
                                    if (k >= tempOrder.size()) {
                                        break;
                                    }
                                    int idx1 = tempOrder.get(j3).intValue();
                                    int startYTop2 = startYTop;
                                    int idx2 = tempOrder.get(k).intValue();
                                    int startXBottom2 = startXBottom;
                                    int num1 = idx1 < this.vecGemItems.size() ? this.vecGemItems.get(idx1).num : 0;
                                    int i13 = idx1;
                                    int num2 = idx2 < this.vecGemItems.size() ? this.vecGemItems.get(idx2).num : 0;
                                    if (num1 > num2) {
                                        int temp = tempOrder.get(j3).intValue();
                                        int i14 = num2;
                                        tempOrder.set(j3, tempOrder.get(k));
                                        tempOrder.set(k, Integer.valueOf(temp));
                                    }
                                    k++;
                                    totalWidthTop = ySlot;
                                    startXBottom = startXBottom2;
                                    startYTop = startYTop2;
                                }
                                int i15 = startXBottom;
                                j3++;
                                totalWidthTop = ySlot;
                            }
                            int ySlot2 = totalWidthTop;
                            int i16 = startYTop;
                            int i17 = startXBottom;
                            tempOrder.add(Integer.valueOf(this.selectedSlotIndex));
                            for (int j4 = 0; j4 < this.numSlots; j4++) {
                                this.revealOrder[j4] = tempOrder.get(j4).intValue();
                            }
                            this.isRevealing = true;
                            this.revealIndex = 0;
                            this.lastRevealTime = System.currentTimeMillis();
                            CanvasNinja.clearAllPointer();
                        }
                    } else {
                        int xSlot4 = slotIndex;
                    }
                    int i18 = totalWidthTop;
                    i6++;
                    totalWidthTop2 = xSlot;
                    slotWidth = slotWidth;
                    startXTop = startXTop;
                    startXBottom = startXBottom;
                    startYTop = startYTop;
                }
                if (CanvasNinja.isPoint(((inner3x3Width - 70) / 2) + inner3x3TopLeftX, startYBottom + slotHeight + 5, 70, LoadDataManager.myButtons[1].getRHeight()) && !this.isShuffling && !this.isRevealing) {
                    CanvasNinja.clearAllPointer();
                    this.cencoredFrameIndex = new Random().nextInt(6);
                    this.isStartShuffle = true;
                    initMoney();
                    startShuffle();
                }
            } else {
                int buttonHeight = LoadDataManager.myButtons[1].getRHeight();
                if (CanvasNinja.isPoint(((inner3x3Width - 70) / 2) + inner3x3TopLeftX, (((inner3x3Height - buttonHeight) / 2) + inner3x3TopLeftY) - this.margin, 70, buttonHeight)) {
                    CanvasNinja.clearAllPointer();
                    AudioManager.buttonClick();
                    startSpin();
                }
            }
            for (int i19 = 0; i19 < this.isClickTagIcon.length; i19++) {
                int i20 = this.arrX[9];
                int i21 = this.arrY[9];
                int i22 = this.arrH[9];
                if (CanvasNinja.isPoint(i20, i21 + (((this.margin * 2) + i22) * i19), this.arrW[9], i22)) {
                    AudioManager.buttonClick();
                    this.isClickTagIcon[i19] = true;
                    this.indexTabIconSelected = i19;
                    if (i19 == 0) {
                        this.nameTabGacha = "Ô MAY MẮN - RYO";
                        this.vecPaint = (Vector) this.vecRyoItems.clone();
                    } else if (i19 == 1) {
                        this.nameTabGacha = "Ô MAY MẮN - GEM";
                        this.vecPaint = (Vector) this.vecGemItems.clone();
                    }
                    init(this.nameTabGacha);
                    populateOuterCellsWithRandomItems();
                    determineFinalSpinIndex();
                    CanvasNinja.clearAllPointer();
                }
            }
            if (CanvasNinja.isPointerRelease()) {
                int index = 0;
                for (int i23 = this.startUsed; i23 < this.maxODoc; i23++) {
                    int i24 = this.arrX[0] + this.xMoreFromNextPrevious;
                    int i25 = this.arrY[0];
                    int i26 = this.arrH[0];
                    if (CanvasNinja.isPoint(i24, i25 + ((this.margin2 + i26) * index) + this.yMoreFromNextPrevious, this.arrW[0], i26)) {
                        this.indexSelectedUsed = i23;
                        if (i23 != 0) {
                            if (i23 == this.maxODoc - 1) {
                            }
                        }
                        CanvasNinja.clearAllPointer();
                    }
                    int i27 = this.arrX[1] + this.xMoreFromNextPrevious;
                    int i28 = this.arrY[1];
                    int i29 = this.arrH[1];
                    if (CanvasNinja.isPoint(i27, i28 + ((this.margin2 + i29) * index) + this.yMoreFromNextPrevious, this.arrW[1], i29)) {
                        this.indexSelectedUsed = this.numODoc + i23;
                        if (i23 != 0) {
                            if (i23 == this.maxODoc - 1) {
                            }
                        }
                        CanvasNinja.clearAllPointer();
                    }
                    index++;
                }
            }
        }
    }

    public void keyPress(int keyCode) {
        if (keyCode == KEY.KEY_B) {
            close();
        }
    }

    public void keyTyped() {
    }

    public void commandTab(int index, int subIndex) {
        super.commandTab(index, subIndex);
        switch (index) {
            case 1:
                CanvasNinja.clearAllPointer();
                AudioManager.buttonClick();
                SendMessage.gI().requestIndexRewardItemGachaRyo();
                return;
            case 20:
                if (this.box != null) {
                    SubTabBuyShop gI = SubTabBuyShop.gI();
                    BoxInventory boxInventory = this.box;
                    gI.startSubTabBuyShop(boxInventory, boxInventory.item.typeMoneyServer, this.w - (this.margin * 8), (this.h * 65) / 100);
                    SubTabBuyShop.gI().idNpc = this.idNpc;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void show() {
        this.indexTabIconSelected = 0;
        this.nameTabGacha = "Ô MAY MẮN - RYO";
        Vector<Item> vector = this.vecRyoItems;
        if (vector == null || vector.size() == 0) {
            SendMessage.gI().requestListItemGachaRyo();
        }
        Vector<Item> vector2 = this.vecGemItems;
        if (vector2 == null || vector2.size() == 0) {
            SendMessage.gI().requestListItemGachaGem();
        }
        this.vecPaint = (Vector) this.vecRyoItems.clone();
        init(this.nameTabGacha);
        showTab();
        me = this;
    }

    public void setRewardIndexRyo(int index) {
        this.indexRewardRyo = index;
        determineFinalSpinIndex();
        initMoney();
        startSpin();
    }

    public void setRewardIndexGem(int index) {
        initMoney();
        this.indexRewardGem = index;
        if (this.indexTabIconSelected == 1 && this.selectedSlotIndex != -1 && index >= 0 && index < this.vecGemItems.size()) {
            cheatKQ(this.selectedSlotIndex);
            for (int j = 0; j < this.numSlots; j++) {
                this.isSlotRevealed[j] = false;
                this.isFlipping[j] = false;
                this.flipProgress[j] = 0.0f;
                this.flipStartTime[j] = 0;
            }
            Vector<Integer> tempOrder = new Vector<>();
            for (int j2 = 0; j2 < this.numSlots; j2++) {
                if (j2 != this.selectedSlotIndex) {
                    tempOrder.add(Integer.valueOf(j2));
                }
            }
            for (int j3 = 0; j3 < tempOrder.size() - 1; j3++) {
                for (int k = j3 + 1; k < tempOrder.size(); k++) {
                    int idx1 = tempOrder.get(j3).intValue();
                    int idx2 = tempOrder.get(k).intValue();
                    if ((idx1 < this.vecGemItems.size() ? this.vecGemItems.get(idx1).num : 0) > (idx2 < this.vecGemItems.size() ? this.vecGemItems.get(idx2).num : 0)) {
                        int temp = tempOrder.get(j3).intValue();
                        tempOrder.set(j3, tempOrder.get(k));
                        tempOrder.set(k, Integer.valueOf(temp));
                    }
                }
            }
            tempOrder.add(Integer.valueOf(this.selectedSlotIndex));
            for (int j4 = 0; j4 < this.numSlots; j4++) {
                this.revealOrder[j4] = tempOrder.get(j4).intValue();
            }
            this.isRevealing = true;
            this.revealIndex = 0;
            this.lastRevealTime = System.currentTimeMillis();
        }
    }
}
