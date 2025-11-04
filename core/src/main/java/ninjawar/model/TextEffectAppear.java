// Class Version: 8
package ninjawar.model;

import ninjawar.mylib.mGraphics;
import ninjawar.myscr.Res;
import ninjawar.mymain.CanvasNinja;
import ninjawar.supporter.LoadDataManager;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.mylib.FrameAtlas;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;

public class TextEffectAppear
{
    boolean canMoveImg;
    int dirLeftRightTarget;
    int dirUpDown;
    int dirUpDownTarget;
    public mFont fontPaint;
    FrameImage frameBgText;
    FrameImage frameEffect;
    int h;
    public FrameAtlas imgItem;
    int indexFrameEff;
    int indexFrameItem;
    boolean isDoneX;
    boolean isDoneY;
    public boolean isMoveImg;
    boolean isPaintText;
    public boolean isRemove;
    int percentMax;
    int percentOpacity;
    int speed;
    int speedTargetX;
    int speedTargetY;
    public String text;
    int timeShow;
    long timeStartShow;
    byte type;
    int w;
    int x;
    int xImg;
    int xTargetImg;
    int xText;
    int y;
    int yImg;
    int yTarget;
    int yTargetImg;

    public TextEffectAppear(final FrameAtlas frameAtlas, final mFont mFont, final FrameImage frameImage, final String s, final int n, final byte b, final int n2, final int n3) {
        this.isPaintText = true;
        this.text = "";
        this.percentMax = 60;
        this.init(frameAtlas, mFont, frameImage, s, n, b, n2, n3);
    }

    public TextEffectAppear(final mFont mFont, final FrameImage frameImage, final String s, final int n) {
        this.isPaintText = true;
        this.text = "";
        this.percentMax = 60;
        this.init(null, mFont, frameImage, s, n, (byte)0, 0, 0);
    }

    private void updateImgeMove() {
        if (this.isMoveImg) {
            final int dirLeftRightTarget = this.dirLeftRightTarget;
            if (dirLeftRightTarget != 0) {
                if (!this.isDoneX) {
                    this.xImg += this.speedTargetX * dirLeftRightTarget;
                }
                if (dirLeftRightTarget == -1) {
                    final int xImg = this.xImg;
                    final int xTargetImg = this.xTargetImg;
                    if (xImg < xTargetImg) {
                        this.xImg = xTargetImg;
                        this.isDoneX = true;
                    }
                }
                if (dirLeftRightTarget == 1) {
                    final int xImg2 = this.xImg;
                    final int xTargetImg2 = this.xTargetImg;
                    if (xImg2 > xTargetImg2) {
                        this.xImg = xTargetImg2;
                        this.isDoneX = true;
                    }
                }
            }
            final int dirUpDownTarget = this.dirUpDownTarget;
            if (dirUpDownTarget != 0) {
                if (!this.isDoneY) {
                    this.yImg += this.speedTargetY * dirUpDownTarget;
                }
                if (dirUpDownTarget == -1) {
                    final int yImg = this.yImg;
                    final int yTargetImg = this.yTargetImg;
                    if (yImg < yTargetImg) {
                        this.yImg = yTargetImg;
                        this.isDoneY = true;
                    }
                }
                if (dirUpDownTarget == 1) {
                    final int yImg2 = this.yImg;
                    final int yTargetImg2 = this.yTargetImg;
                    if (yImg2 > yTargetImg2) {
                        this.yImg = yTargetImg2;
                        this.isDoneY = true;
                    }
                }
            }
            else {
                this.isDoneY = true;
            }
            if (this.isDoneX && this.isDoneY && NinjaMidlet.timeSystems >= this.timeStartShow) {
                this.isRemove = true;
                this.isMoveImg = false;
            }
        }
    }

    public void init(final FrameAtlas imgItem, final mFont fontPaint, final FrameImage frameBgText, final String text, int abs, final byte b, final int xTargetImg, final int n) {
        this.type = b;
        this.imgItem = imgItem;
        if (imgItem != null) {
            final FrameImage frameEffectUpgradeSuccess2 = LoadDataManager.frameEffectUpgradeSuccess2;
            this.frameEffect = frameEffectUpgradeSuccess2;
            this.xTargetImg = xTargetImg;
            this.yTargetImg = n + frameEffectUpgradeSuccess2.getRHeight() / 2 + 5;
        }
        this.timeShow = 1500;
        this.speed = 20;
        this.frameBgText = frameBgText;
        final int w = CanvasNinja.w;
        final int w2 = w * 60 / 100;
        this.w = w2;
        final int h = (int)frameBgText.frameHeight;
        this.h = h;
        this.text = text;
        this.fontPaint = fontPaint;
        this.x = (w - w2) / 2;
        this.yTarget = (CanvasNinja.h - h) / 2 - fontPaint.getHeight() / 2;
        if (this.frameEffect != null) {
            this.yTarget = (CanvasNinja.h - this.h) / 2 - fontPaint.getHeight() / 2 + this.frameEffect.getRHeight() / 2;
        }
        final int yTarget = this.yTarget;
        final int y = yTarget - abs * 70;
        this.y = y;
        this.dirUpDown = abs;
        this.percentOpacity = 20;
        final int n2 = this.x + this.w / 2;
        this.xText = n2;
        this.xImg = n2;
        this.yImg = yTarget;
        final int n3 = -1;
        if (xTargetImg > n2) {
            abs = 1;
        }
        else {
            abs = -1;
        }
        this.dirLeftRightTarget = abs;
        if (yTarget < y) {
            abs = n3;
        }
        else {
            abs = 1;
        }
        this.dirUpDownTarget = abs;
        this.speedTargetX = 10;
        final int abs2 = Res.abs(xTargetImg - n2);
        abs = Res.abs(n - this.yImg);
        final float n4 = abs2 * 1.0f / this.speedTargetX;
        final int speedTargetY = (int)(abs / n4);
        this.speedTargetY = speedTargetY;
        if (abs / n4 - speedTargetY > 0.5) {
            this.speedTargetY = speedTargetY + 1;
        }
        if (xTargetImg * n <= 0) {
            this.isMoveImg = false;
            this.canMoveImg = false;
        }
        else {
            this.isMoveImg = true;
            this.canMoveImg = true;
        }
    }

    public void paint(final mGraphics mGraphics) {
        this.paint(mGraphics, false);
    }

    public void paint(final mGraphics mGraphics, final boolean b) {
        if (this.isRemove) {
            return;
        }
        final FrameImage frameBgText = this.frameBgText;
        if (frameBgText != null) {
            final byte type = this.type;
            if (type == 0) {
                CanvasNinja.paintz.paintTagFrame(mGraphics, frameBgText, this.x, this.y, this.w, false, true, this.percentOpacity, b);
            }
            else if (type == 1) {
                final FrameImage frameEffect = this.frameEffect;
                if (frameEffect != null && this.imgItem != null) {
                    frameEffect.drawFrame(this.indexFrameEff, this.xImg - frameEffect.getRWidth() / 2, this.yImg - this.frameEffect.getRHeight() - 5, b, mGraphics);
                    final FrameAtlas imgItem = this.imgItem;
                    imgItem.drawFrame(this.indexFrameItem, (float)(this.xImg - imgItem.getRWidth() / 2), (float)(this.yImg - this.frameEffect.getRHeight() / 2 - this.imgItem.getRWidth() / 2 - 5), b, mGraphics);
                }
            }
            if (this.isPaintText) {
                final mFont fontPaint = this.fontPaint;
                final String text = this.text;
                final int xText = this.xText;
                final int y = this.y;
                final int n = ((int)this.frameBgText.frameHeight - fontPaint.getHeight()) / 2;
                int percentOpacity;
                if ((percentOpacity = this.percentOpacity) == this.percentMax) {
                    percentOpacity = 100;
                }
                fontPaint.drawString(mGraphics, text, xText, y + n, 2, b, 0, percentOpacity);
            }
        }
    }

    public void update() {
        if (this.isRemove) {
            return;
        }
        final FrameImage frameEffect = this.frameEffect;
        if (frameEffect != null && CanvasNinja.gameTick % 3 == 0 && ++this.indexFrameEff > frameEffect.nFrame - 1) {
            this.indexFrameEff = 0;
        }
        final FrameAtlas imgItem = this.imgItem;
        if (imgItem != null) {
            if (CanvasNinja.gameTick % 3 == 0 && ++this.indexFrameItem > imgItem.nFrame - 1) {
                this.indexFrameItem = 0;
            }
            this.updateImgeMove();
        }
        final int y = this.y;
        final int speed = this.speed;
        final int dirUpDown = this.dirUpDown;
        final int y2 = y + speed * dirUpDown;
        this.y = y2;
        final int percentOpacity = this.percentOpacity + 5;
        this.percentOpacity = percentOpacity;
        final int percentMax = this.percentMax;
        if (percentOpacity > percentMax) {
            this.percentOpacity = percentMax;
        }
        if (dirUpDown == 1) {
            final int yTarget = this.yTarget;
            if (y2 > yTarget) {
                this.y = yTarget;
                if (this.timeStartShow == 0L) {
                    this.timeStartShow = NinjaMidlet.timeSystems + this.timeShow;
                }
            }
        }
        if (dirUpDown == -1) {
            final int y3 = this.y;
            final int yTarget2 = this.yTarget;
            if (y3 < yTarget2) {
                this.y = yTarget2;
                if (this.timeStartShow == 0L) {
                    this.timeStartShow = NinjaMidlet.timeSystems + this.timeShow;
                }
            }
        }
        final long timeStartShow = this.timeStartShow;
        if (timeStartShow != 0L && NinjaMidlet.timeSystems >= timeStartShow) {
            this.timeStartShow = 0L;
            if (this.imgItem == null) {
                this.isRemove = true;
            }
            else if (this.canMoveImg) {
                this.isMoveImg = true;
                this.isPaintText = false;
            }
            else {
                this.isRemove = true;
            }
        }
    }
}
