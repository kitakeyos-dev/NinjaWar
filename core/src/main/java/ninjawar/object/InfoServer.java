package ninjawar.object;

import ninjawar.model.Popup;
import ninjawar.mylib.Atlas;
import ninjawar.mylib.FrameAtlas;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;

public class InfoServer {
    public int[][] FRAME;
    public int[][] FRAMEKHOI;
    public int action;
    int dirLR;
    int fDir;
    public FrameImage frameHide;
    public FrameAtlas frameImg;
    public int h;
    int hHide;
    public int index;
    public int indexEnd;
    public boolean isDone;
    boolean isDoneX;
    public boolean isShowEffectEnd;
    boolean isShowSmokeStart;
    public int loopCount;
    public int maxLoop;
    public Popup popup;
    private int prevPlayerX;
    private int prevPlayerY;
    int speedX = 20;
    public String text = "";
    public long timeEndInfo;
    public int timeShow;
    public int w;
    int wHide;
    public int x;
    int xMore;
    public int xTarget;
    public int y;
    public int yTarget;

    public InfoServer(String text2, int timeShow2) {
        Atlas imgTemp = Atlas.createAtlas((short) 0, (short) 23);
        Image imgTemp2 = Image.createImage((short) 8, (short) 18);
        this.index = 0;
        this.action = 0;
        this.FRAME = new int[][]{new int[]{0, 1, 2, 3}, new int[]{4, 5, 6, 7}, new int[]{8, 9, 10, 11}, new int[]{12, 13, 14, 15}};
        this.FRAMEKHOI = new int[][]{new int[]{0, 1, 2, 3}, new int[]{4, 5, 6, 7}};
        this.xTarget = Player.myCharz().cx - MapScr.cmx;
        this.yTarget = Player.myCharz().cy - MapScr.cmy;
        if (imgTemp != null) {
            FrameAtlas frameAtlas = new FrameAtlas(imgTemp, "1999", 16);
            this.frameImg = frameAtlas;
            int i = (int) frameAtlas.frameWidth;
            this.w = i;
            this.h = (int) frameAtlas.frameHeight;
            this.x = (CanvasNinja.w - i) / 2;
            this.y = this.yTarget;
        }
        if (imgTemp2 != null) {
            FrameImage frameImage = new FrameImage(imgTemp2, 8);
            this.frameHide = frameImage;
            this.wHide = (int) frameImage.frameWidth;
            this.hHide = ((int) frameImage.frameHeight) - 10;
        }
        this.timeShow = timeShow2;
        this.text = text2;
        this.dirLR = this.xTarget > this.x ? 1 : -1;
        this.speedX = 50;
        this.maxLoop = Res.random(1, 4);
        this.loopCount = 0;
        this.isShowSmokeStart = true;
        this.prevPlayerX = Player.myCharz().cx;
        this.prevPlayerY = Player.myCharz().cy;
    }

    public void startPopup(String chat, int time) {
        Popup popup2 = new Popup();
        this.popup = popup2;
        popup2.startPopup(chat, time);
    }

    public void paintPopup(mGraphics g, int x2, int y2) {
        Popup popup2 = this.popup;
        if (popup2 != null) {
            popup2.paint(g, x2, y2);
            Popup popup3 = this.popup;
            popup3.timeShow = 0;
            popup3.isPaint = false;
            this.popup = null;
        }
    }

    private void updatePopup() {
        Popup popup2 = this.popup;
        if (popup2 != null && popup2.timeShow != 0) {
            long currentTimeMillis = mSystem.currentTimeMillis();
            Popup popup3 = this.popup;
            if (currentTimeMillis > popup3.timeShow) {
                popup3.timeShow = 0;
                popup3.isPaint = false;
                this.popup = null;
            }
        }
    }

    public void paint(mGraphics g, int x2, int y2, int cw, int ch, int dir) {
        FrameImage frameImage;
        FrameImage frameImage2;
        int i = dir;
        this.fDir = i;
        this.xTarget = x2 - MapScr.cmx;
        this.yTarget = y2 - MapScr.cmy;
        int i2 = 5;
        if (i == -1) {
            i2 = (-this.w) - 5;
        }
        this.xMore = i2;
        if (this.frameImg != null) {
            if (this.isShowSmokeStart && (frameImage2 = this.frameHide) != null) {
                int[] iArr = this.FRAMEKHOI[0];
                int i3 = iArr[this.index % iArr.length];
                int i4 = (this.x + i2) - ((i * cw) / 2);
                int i5 = this.w;
                frameImage2.drawFrame(i3, (float) ((i4 - (i * i5)) + ((i5 / 2) - (this.wHide / 2))), (float) (this.y - this.hHide), 0, g);
            }
            this.frameImg.drawFrame(this.index, (float) (((this.x + this.xMore) - ((i * cw) / 2)) - (this.w * i)), (float) ((this.y - ch) - 30), this.fDir == -1 ? 2 : 0, g);
            int i6 = this.action;
            if (i6 == 2 || i6 == 3) {
                int i7 = this.x - ((i * cw) / 2);
                int i8 = this.w;
                paintPopup(g, (i7 - (i * i8)) + ((i8 * i) / 2), ((this.y + ch) - LoadDataManager.conCho.getRHeight()) - 30);
            } else {
                mGraphics mgraphics = g;
            }
        } else {
            mGraphics mgraphics2 = g;
        }
        if (this.isShowEffectEnd && (frameImage = this.frameHide) != null) {
            int[] iArr2 = this.FRAMEKHOI[1];
            int i9 = iArr2[this.indexEnd % iArr2.length];
            int i10 = (this.x + this.xMore) - ((i * cw) / 2);
            int i11 = this.w;
            frameImage.drawFrame(i9, (float) ((i10 - (i * i11)) + ((i11 / 2) - (this.wHide / 2))), (float) (this.y - this.hHide), 0, g);
        }
    }

    private void updateMoveTo() {
        int i = this.xTarget;
        int i2 = this.x;
        int i3 = i > i2 ? 1 : -1;
        this.dirLR = i3;
        this.y = this.yTarget;
        if (i3 != 0) {
            int i4 = i2 + (this.speedX * i3);
            this.x = i4;
            if (i3 == -1 && i4 < i) {
                this.x = i;
                this.isDoneX = true;
            }
            if (i3 == 1 && this.x > i) {
                this.x = i;
                this.isDoneX = true;
            }
        }
        if (this.isDoneX) {
            doAction(2);
            startPopup(this.text, this.timeShow);
            if (this.timeEndInfo == 0) {
                this.timeEndInfo = mSystem.currentTimeMillis() + ((long) this.timeShow);
            }
            this.isDoneX = false;
        }
    }

    public void update() {
        if (this.frameImg != null) {
            updateMoveTo();
            int currentPlayerX = Player.myCharz().cx;
            int currentPlayerY = Player.myCharz().cy;
            boolean isPlayerMoving = (currentPlayerX == this.prevPlayerX && currentPlayerY == this.prevPlayerY) ? false : true;
            this.prevPlayerX = currentPlayerX;
            this.prevPlayerY = currentPlayerY;
            boolean isMoving = !this.isDoneX;
            if (isPlayerMoving && isMoving) {
                doAction(3);
                this.isShowSmokeStart = false;
            } else if (!isPlayerMoving && this.action == 3) {
                doAction(2);
            }
            if (CanvasNinja.gameTick % 5 == 0) {
                int i = this.index + 1;
                this.index = i;
                int[][] iArr = this.FRAME;
                int i2 = this.action;
                int[] iArr2 = iArr[i2];
                if (i > iArr2[iArr2.length - 1]) {
                    int i3 = iArr2[0];
                    this.index = i3;
                    if (i2 == 0) {
                        this.isShowSmokeStart = false;
                        doAction(1);
                    } else if (i2 == 1) {
                        int i4 = this.loopCount + 1;
                        this.loopCount = i4;
                        if (i4 >= this.maxLoop) {
                            doAction(2);
                        }
                    } else if (i2 == 2 || i2 == 3) {
                        this.index = i3;
                    }
                }
            }
            updatePopup();
        }
        long currentTimeMillis = mSystem.currentTimeMillis();
        long j = this.timeEndInfo;
        if (currentTimeMillis >= j && j != 0) {
            this.isShowEffectEnd = true;
            this.timeEndInfo = 0;
            this.popup = null;
            this.frameImg = null;
        }
        if (this.isShowEffectEnd && this.frameHide != null && CanvasNinja.gameTick % 3 == 0) {
            int i5 = this.indexEnd + 1;
            this.indexEnd = i5;
            if (i5 > this.FRAMEKHOI[1].length - 1) {
                this.indexEnd = 0;
                this.frameHide = null;
                this.isShowEffectEnd = false;
                this.isDone = true;
            }
        }
    }

    public void doAction(int action2) {
        this.action = action2;
    }
}
