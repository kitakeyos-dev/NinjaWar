package ninjawar.supporter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ninjawar.input.TField;
import ninjawar.model.FrameRegion;
import ninjawar.model.MyCommand;
import ninjawar.mylib.BaseScreen;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.Player;

public class SupportPaint {
    public static int TYPE_NONE = 0;
    public static int TYPE_OPACITY = 1;
    public static Image[] goc = new Image[6];
    public static int hTab = 24;
    public static Image[] imgMsg = new Image[2];
    public static Image[] imgTick = new Image[2];
    public static int lenCaption = 0;
    public int[] color = {15970400, -844109861, 2250052, 16374659, 15906669, 12931125, 3108954, 168431359};
    FrameImage frameImageLast;
    float hTempSB;
    boolean isSmallSingleBorderFrame = false;
    int nHeightOutLine;
    double nHeightTemp;
    int nWidthOutLine;
    double nWidthTemp;
    boolean opacitySB = false;
    boolean opacitySB2 = false;
    float wTempSB;
    int xCircle;
    int yCircle;

    public void paintCmdBar(mGraphics g, MyCommand left, MyCommand center, MyCommand right) {
        boolean z = CanvasNinja.isTouch;
        mFont f = mFont.tahoma_7b_dark;
        if (left != null) {
            int width = f.getWidth(left.caption);
            lenCaption = width;
            if (width > 0) {
                if (left.x < 0 || left.y <= 0) {
                    g.drawImage(BaseScreen.keyTouch == 0 ? MapScr.imgLbtnFocus : MapScr.imgLbtn, 1.0f, (float) ((CanvasNinja.h - BaseScreen.cmdH) - 1), 0);
                    f.drawString(g, left.caption, 35, (CanvasNinja.h - BaseScreen.cmdH) + 3 + 3, 2);
                } else {
                    left.paint(g);
                }
            }
        }
        if (center != null) {
            int width2 = f.getWidth(center.caption);
            lenCaption = width2;
            if (width2 > 0) {
                if (center.x <= 0 || center.y <= 0) {
                    g.drawImage(BaseScreen.keyTouch == 1 ? MapScr.imgLbtnFocus : MapScr.imgLbtn, (float) (CanvasNinja.hw - 35), (float) ((CanvasNinja.h - BaseScreen.cmdH) - 1), 0);
                    f.drawString(g, center.caption, CanvasNinja.hw, (CanvasNinja.h - BaseScreen.cmdH) + 3 + 3, 2);
                } else {
                    center.paint(g);
                }
            }
        }
        if (right != null) {
            int width3 = f.getWidth(right.caption);
            lenCaption = width3;
            if (width3 <= 0) {
                return;
            }
            if (right.x <= 0 || right.y <= 0) {
                g.drawImage(BaseScreen.keyTouch == 2 ? MapScr.imgLbtnFocus : MapScr.imgLbtn, (float) (CanvasNinja.w - 71), (float) ((CanvasNinja.h - BaseScreen.cmdH) - 1), 0);
                f.drawString(g, right.caption, CanvasNinja.w - 35, (CanvasNinja.h - BaseScreen.cmdH) + 3 + 3, 2);
                return;
            }
            right.paint(g);
        }
    }

    public void paintTabSoft(mGraphics g) {
    }

    public void paintPopUp(int x, int y, int w, int h, mGraphics g) {
        paintSingleBorderFrame(g, LoadDataManager.frameBgOrange0, (float) x, (float) y, (float) w, (float) h, 0, false);
    }

    public void paintTagFrame(mGraphics g, FrameImage frameImage, int x, int y, int w, boolean isFocus, int type, boolean useClip) {
        paintTagFrame(g, frameImage, x, y, w, isFocus, type, false, 0, useClip);
    }

    public void paintTagFrame(mGraphics g, FrameImage frameImage, int x, int y, int w, boolean isFocus, boolean useClip) {
        paintTagFrame(g, frameImage, x, y, w, isFocus, TYPE_NONE, false, 0, useClip);
    }

    public void paintTagFrame(mGraphics g, FrameImage frameImage, int x, int y, int w, boolean isFocus) {
        paintTagFrame(g, frameImage, x, y, w, isFocus, TYPE_NONE, false, 0, false);
    }

    public void paintTagFrame(mGraphics g, FrameImage frameImage, int x, int y, int w, boolean isFocus, boolean isOpacity, int percent, boolean useClip) {
        paintTagFrame(g, frameImage, x, y, w, isFocus, TYPE_OPACITY, isOpacity, percent, useClip);
    }

    public void paintTagFrame(mGraphics g, FrameImage frameImage, int x, int y, int w, boolean isFocus, int type, boolean isOpacity, int percent, boolean useClip) {
        int type2;
        boolean isSmall;
        int nW;
        int nW2;
        FrameImage frameImage2 = frameImage;
        int i = x;
        int i2 = y;
        int i3 = w;
        if (isOpacity) {
            type2 = TYPE_OPACITY;
        } else {
            type2 = type;
        }
        if (((float) i3) <= frameImage2.frameWidth) {
            isSmall = true;
        } else {
            isSmall = false;
        }
        if (isSmall) {
            frameImage2 = new FrameImage(frameImage2.imgFrame, (float) i3, frameImage2.frameHeight);
        }
        if (isFocus) {
            frameImage2.drawFrame(1, (float) i, (float) i2, 0, useClip, g, isOpacity, percent);
            if (!isSmall) {
                float f = frameImage2.frameWidth;
                int nW3 = (int) (((float) i3) / f);
                frameImage2.nFrame = 2;
                if (i3 % ((int) f) != 0) {
                    nW2 = nW3 + 1;
                } else {
                    nW2 = nW3;
                }
                int i4 = 1;
                while (i4 < nW2 - 1) {
                    frameImage2.drawFrameXY(1, 1, (((float) i4) * frameImage2.frameWidth) + ((float) i), (float) i2, 0, useClip, g, isOpacity, percent);
                    i4++;
                }
                int i5 = i4;
                float f2 = frameImage2.frameWidth;
                float wLast = ((float) i3) - (((float) (nW2 - 1)) * f2);
                if (type2 == TYPE_OPACITY) {
                    FrameImage frameImage3 = new FrameImage(frameImage2.imgFrame, wLast, frameImage2.frameHeight);
                    this.frameImageLast = frameImage3;
                    float f3 = wLast;
                    frameImage3.drawFrame(1, ((float) i) + (((float) (nW2 - 1)) * frameImage2.frameWidth), (float) i2, 2, 0, useClip, g, isOpacity, percent);
                    return;
                }
                frameImage2.drawFrame(1, ((float) (i + i3)) - f2, (float) i2, 2, 0, useClip, g, isOpacity, percent);
                return;
            }
            return;
        }
        if (frameImage2.nFrame < 2) {
            frameImage2.nFrame = 2;
        }
        frameImage2.drawFrame(0, (float) i, (float) i2, 0, useClip, g, isOpacity, percent);
        if (!isSmall) {
            float f4 = frameImage2.frameWidth;
            int nW4 = (int) (((float) i3) / f4);
            frameImage2.nFrame = 2;
            if (i3 % ((int) f4) != 0) {
                nW = nW4 + 1;
            } else {
                nW = nW4;
            }
            int i6 = 1;
            while (i6 < nW - 1) {
                frameImage2.drawFrameXY(1, 0, (((float) i6) * frameImage2.frameWidth) + ((float) i), (float) i2, 0, useClip, g, isOpacity, percent);
                i6++;
            }
            int i7 = i6;
            float f5 = frameImage2.frameWidth;
            float wLast2 = ((float) i3) - (((float) (nW - 1)) * f5);
            if (type2 == TYPE_OPACITY) {
                FrameImage frameImage4 = new FrameImage(frameImage2.imgFrame, wLast2, frameImage2.frameHeight);
                this.frameImageLast = frameImage4;
                float f6 = wLast2;
                frameImage4.drawFrame(0, ((float) i) + (((float) (nW - 1)) * frameImage2.frameWidth), (float) i2, 2, 0, useClip, g, isOpacity, percent);
                return;
            }
            frameImage2.drawFrame(0, ((float) (i + i3)) - f5, (float) i2, 2, 0, useClip, g, isOpacity, percent);
        }
    }

    public void paintLineVertical(mGraphics g, FrameImage frameImage, int x, int y, int h, int hSum, boolean useClip) {
        FrameImage frameImage2 = frameImage;
        float f = frameImage2.frameHeight;
        int nH = (int) (((float) h) / f);
        frameImage2.nFrame = 2;
        int yy = y + ((hSum - ((int) (((float) nH) * f))) / 2);
        for (int i = 0; i < nH; i++) {
            frameImage.drawFrame(0, (float) x, (((float) i) * frameImage2.frameHeight) + ((float) yy), 0, useClip, g);
        }
        int i2 = x;
    }

    public void paintSingleBorderFrame(mGraphics g, FrameImage frameImageOutLine, float x, float y, float w, float h, int indexColor, boolean useClip) {
        paintSingleBorderFrame(g, frameImageOutLine, x, y, w, h, indexColor, 0, useClip);
    }

    public void paintSingleBorderFrame(mGraphics g, FrameImage frameImageOutLine, float x, float y, float w, float h, int indexColor, int percentOpacity, boolean useClip) {
        FrameImage frameImage = frameImageOutLine;
        this.opacitySB = false;
        if (percentOpacity > 0) {
            this.opacitySB = true;
        }
        this.isSmallSingleBorderFrame = false;
        if (h <= frameImage.frameHeight) {
            this.isSmallSingleBorderFrame = true;
        }
        float f = y;
        mGraphics mgraphics = g;
        int i = percentOpacity;
        frameImageOutLine.drawFrame(0, x, f, 0, mgraphics, this.opacitySB, i);
        frameImageOutLine.drawFrame(2, (x + w) - frameImage.frameWidth, f, 0, mgraphics, this.opacitySB, i);
        if (!this.isSmallSingleBorderFrame) {
            mGraphics mgraphics2 = g;
            int i2 = percentOpacity;
            frameImageOutLine.drawFrame(5, x, (y + h) - frameImage.frameHeight, 0, mgraphics2, this.opacitySB, i2);
            frameImageOutLine.drawFrame(7, (x + w) - frameImage.frameWidth, (y + h) - frameImage.frameHeight, 0, mgraphics2, this.opacitySB, i2);
        }
        float f2 = frameImage.frameHeight;
        int i3 = (int) ((h - (f2 * 2.0f)) / f2);
        this.nHeightOutLine = i3;
        float f3 = frameImage.frameWidth;
        int i4 = (int) ((w - (f3 * 2.0f)) / f3);
        this.nWidthOutLine = i4;
        double d = (double) ((h - (f2 * 2.0f)) / f2);
        this.nHeightTemp = d;
        double d2 = (double) ((w - (f3 * 2.0f)) / f3);
        this.nWidthTemp = d2;
        if (d - ((double) i3) > 0.0d) {
            i3++;
        }
        this.nHeightOutLine = i3;
        if (d2 - ((double) i4) > 0.0d) {
            i4++;
        }
        this.nWidthOutLine = i4;
        int i5 = 1;
        while (true) {
            int i6 = this.nWidthOutLine;
            if (i5 > i6) {
                break;
            }
            if (i5 < i6) {
                frameImageOutLine.drawFrame(1, x + (((float) i5) * frameImage.frameWidth), y, 0, g, this.opacitySB, percentOpacity);
                if (!this.isSmallSingleBorderFrame) {
                    frameImageOutLine.drawFrame(6, x + (((float) i5) * frameImage.frameWidth), (y + h) - frameImage.frameHeight, 0, g, this.opacitySB, percentOpacity);
                }
            } else {
                float f4 = frameImage.frameWidth;
                float f5 = (w - (f4 * 2.0f)) - (((float) (i6 - 1)) * f4);
                this.wTempSB = f5;
                if (percentOpacity > 0) {
                    frameImageOutLine.drawFrameSpec(1, ((x + w) - f5) - f4, y, f5, frameImage.frameHeight, 0, g, this.opacitySB, percentOpacity);
                    if (!this.isSmallSingleBorderFrame) {
                        float f6 = this.wTempSB;
                        float f7 = frameImage.frameHeight;
                        frameImageOutLine.drawFrameSpec(6, ((x + w) - f6) - frameImage.frameWidth, (y + h) - f7, f6, f7, 0, g, this.opacitySB, percentOpacity);
                    }
                } else {
                    frameImageOutLine.drawFrame(1, (x + w) - (f4 * 2.0f), y, 0, g, this.opacitySB, percentOpacity);
                    if (!this.isSmallSingleBorderFrame) {
                        frameImageOutLine.drawFrame(6, (x + w) - (frameImage.frameWidth * 2.0f), (y + h) - frameImage.frameHeight, 0, g, this.opacitySB, percentOpacity);
                    }
                }
            }
            i5++;
        }
        if (!this.isSmallSingleBorderFrame) {
            int i7 = 1;
            while (true) {
                int i8 = this.nHeightOutLine;
                if (i7 <= i8) {
                    if (i7 < i8) {
                        mGraphics mgraphics3 = g;
                        int i9 = percentOpacity;
                        frameImageOutLine.drawFrame(3, x, y + (((float) i7) * frameImage.frameHeight), 0, mgraphics3, this.opacitySB, i9);
                        frameImageOutLine.drawFrame(4, (x + w) - frameImage.frameWidth, y + (((float) i7) * frameImage.frameHeight), 0, mgraphics3, this.opacitySB, i9);
                    } else if (percentOpacity > 0) {
                        float f8 = frameImage.frameHeight;
                        float f9 = (h - (f8 * 2.0f)) - (((float) (i8 - 1)) * f8);
                        this.hTempSB = f9;
                        int i10 = percentOpacity;
                        frameImageOutLine.drawFrameSpec(3, x, ((y + h) - f9) - f8, frameImage.frameWidth, f9, 0, g, this.opacitySB, i10);
                        float f10 = frameImage.frameWidth;
                        float f11 = (y + h) - this.hTempSB;
                        float f12 = frameImage.frameHeight;
                        FrameImage frameImage2 = frameImageOutLine;
                        frameImage2.drawFrameSpec(4, (x + w) - f10, f11 - f12, f10, (h - (f12 * 2.0f)) - (((float) (this.nHeightOutLine - 1)) * f12), 0, g, this.opacitySB, i10);
                    } else {
                        mGraphics mgraphics4 = g;
                        int i11 = percentOpacity;
                        frameImageOutLine.drawFrame(3, x, (y + h) - (frameImage.frameHeight * 2.0f), 0, mgraphics4, this.opacitySB, i11);
                        frameImageOutLine.drawFrame(4, (x + w) - frameImage.frameWidth, (y + h) - (frameImage.frameHeight * 2.0f), 0, mgraphics4, this.opacitySB, i11);
                    }
                    i7++;
                } else {
                    g.setColor(LoadDataManager.color_frame[indexColor]);
                    float f13 = frameImage.frameWidth;
                    float f14 = frameImage.frameHeight;
                    g.fillRect(x + f13, y + f14, w - (f13 * 2.0f), h - (f14 * 2.0f), useClip, this.opacitySB, percentOpacity);
                    return;
                }
            }
        } else {
            mGraphics mgraphics5 = g;
        }
    }

    public void paintSingleBorderFrame(FrameImage frameImageOutLine, float x, float y, float w, float h, int percentOpacity, boolean useClip, mGraphics g) {
        float f;
        int tileHeight;
        float wTile;
        float hTile;
        int nWidthOutLine2;
        float wTemp;
        FrameImage frameImage = frameImageOutLine;
        if (percentOpacity > 0) {
            this.opacitySB2 = true;
        }
        int i = 0;
        this.isSmallSingleBorderFrame = false;
        if (h <= frameImage.frameHeight) {
            this.isSmallSingleBorderFrame = true;
        }
        float f2 = y;
        mGraphics mgraphics = g;
        int i2 = percentOpacity;
        frameImageOutLine.drawFrame(0, x, f2, 0, mgraphics, this.opacitySB2, i2);
        frameImageOutLine.drawFrame(2, (x + w) - frameImage.frameWidth, f2, 0, mgraphics, this.opacitySB2, i2);
        if (!this.isSmallSingleBorderFrame) {
            mGraphics mgraphics2 = g;
            int i3 = percentOpacity;
            frameImageOutLine.drawFrame(5, x, (y + h) - frameImage.frameHeight, 0, mgraphics2, this.opacitySB2, i3);
            frameImageOutLine.drawFrame(7, (x + w) - frameImage.frameWidth, (y + h) - frameImage.frameHeight, 0, mgraphics2, this.opacitySB2, i3);
        }
        float f3 = frameImage.frameWidth;
        float remainingHeight = h - (frameImage.frameHeight * 2.0f);
        int wTemp2 = (int) Math.ceil((double) ((w - (f3 * 2.0f)) / f3));
        if (!this.isSmallSingleBorderFrame) {
            i = (int) Math.ceil((double) (remainingHeight / frameImage.frameHeight));
        }
        int i4 = i;
        float currentX = x + frameImage.frameWidth;
        while (true) {
            float f4 = frameImage.frameWidth;
            if (currentX >= (x + w) - f4) {
                break;
            }
            float wTemp3 = Math.min(f4, ((x + w) - f4) - currentX);
            if (wTemp3 <= 0.0f) {
                nWidthOutLine2 = wTemp2;
                wTemp = wTemp3;
            } else if (percentOpacity > 0) {
                float wTemp4 = wTemp3;
                nWidthOutLine2 = wTemp2;
                frameImageOutLine.drawFrameSpec(1, currentX, y, wTemp3, frameImage.frameHeight, 0, g, this.opacitySB2, percentOpacity);
                if (!this.isSmallSingleBorderFrame) {
                    float f5 = frameImage.frameHeight;
                    frameImageOutLine.drawFrameSpec(6, currentX, (y + h) - f5, wTemp4, f5, 0, g, this.opacitySB2, percentOpacity);
                    wTemp = wTemp4;
                } else {
                    wTemp = wTemp4;
                }
            } else {
                nWidthOutLine2 = wTemp2;
                Image image = frameImage.imgFrame;
                float f6 = frameImage.frameHeight;
                wTemp = wTemp3;
                Image image2 = image;
                new FrameRegion(image2, 0, ((int) f6) * 1, (float) ((int) wTemp), f6).drawFrame(g, currentX, y, 0, useClip, this.opacitySB2, percentOpacity);
                if (!this.isSmallSingleBorderFrame) {
                    Image image3 = frameImage.imgFrame;
                    float f7 = frameImage.frameHeight;
                    new FrameRegion(image3, 0, ((int) f7) * 6, (float) ((int) wTemp), f7).drawFrame(g, currentX, (y + h) - frameImage.frameHeight, 0, useClip, this.opacitySB2, percentOpacity);
                }
            }
            currentX += wTemp;
            wTemp2 = nWidthOutLine2;
        }
        int nWidthOutLine3 = wTemp2;
        if (!this.isSmallSingleBorderFrame) {
            float currentY = y + frameImage.frameHeight;
            while (true) {
                f = frameImage.frameHeight;
                if (currentY >= (y + h) - f) {
                    break;
                }
                float hTemp = Math.min(f, ((y + h) - f) - currentY);
                if (hTemp > 0.0f) {
                    if (percentOpacity > 0) {
                        float f8 = currentY;
                        float f9 = hTemp;
                        mGraphics mgraphics3 = g;
                        int i5 = percentOpacity;
                        frameImageOutLine.drawFrameSpec(3, x, f8, frameImage.frameWidth, f9, 0, mgraphics3, this.opacitySB2, i5);
                        float f10 = frameImage.frameWidth;
                        frameImageOutLine.drawFrameSpec(4, (x + w) - f10, f8, f10, f9, 0, mgraphics3, this.opacitySB2, i5);
                    } else {
                        float f11 = currentY;
                        mGraphics mgraphics4 = g;
                        int i6 = percentOpacity;
                        frameImageOutLine.drawFrame(3, x, f11, 0, mgraphics4, this.opacitySB2, i6);
                        frameImageOutLine.drawFrame(4, (x + w) - frameImage.frameWidth, f11, 0, mgraphics4, this.opacitySB2, i6);
                    }
                }
                currentY += hTemp;
            }
            float f12 = frameImage.frameWidth;
            float f13 = w - (f12 * 2.0f);
            float f14 = h - (2.0f * f);
            int tileWidth = (int) (f12 - ((float) frameImage.xMoreFrameMore));
            int tileHeight2 = (int) (f - ((float) frameImage.yMoreFrameMore));
            float fillX = x + f12;
            while (true) {
                float f15 = frameImage.frameWidth;
                if (fillX < (x + w) - f15) {
                    float wTile2 = Math.min((float) tileWidth, ((x + w) - f15) - fillX);
                    float fillY = y + frameImage.frameHeight;
                    while (true) {
                        float f16 = frameImage.frameHeight;
                        if (fillY >= (y + h) - f16) {
                            break;
                        }
                        float hTile2 = Math.min((float) tileHeight2, ((y + h) - f16) - fillY);
                        if (wTile2 <= 0.0f || hTile2 <= 0.0f) {
                            hTile = hTile2;
                            wTile = wTile2;
                            tileHeight = tileHeight2;
                        } else {
                            mGraphics mgraphics5 = g;
                            float f17 = fillX;
                            float f18 = fillY;
                            hTile = hTile2;
                            boolean z = useClip;
                            wTile = wTile2;
                            tileHeight = tileHeight2;
                            new FrameRegion(frameImage.imgFrame, frameImage.xMoreFrameMore, frameImage.yMoreFrameMore, (int) wTile2, (int) hTile2).drawFrame(mgraphics5, f17, f18, 0, z, this.opacitySB2, percentOpacity);
                        }
                        fillY += hTile;
                        wTile2 = wTile;
                        tileHeight2 = tileHeight;
                    }
                    int i7 = tileHeight2;
                    fillX += wTile2;
                } else {
                    return;
                }
            }
        }
    }

    public void painTextInputNoneBackGround(mGraphics g, int x, int y, int width, int height, TField tf, boolean isFocus) {
        mGraphics mgraphics = g;
        TField tField = tf;
        if (!(!tf.isFocused() || tField.startSelected == -1 || tField.endSelected == -1)) {
            g.setColor(31743);
            g.fillRect(((((tField.offsetX + 4) + x) + tField.fontTField.getWidth(tField.paintedText.substring(0, tField.startSelected))) - 1) + 2, ((tField.fontTField.getHeight() + y) - (TField.CARET_HEIGHT / 2)) + 1, tField.fontTField.getWidth(tField.paintedText.substring(tField.startSelected, tField.endSelected)) + 1, tField.fontTField.getHeight() + 2);
        }
        if (tField.paintedText.equals("")) {
            mFont mfont = tField.fontTField;
            mfont.drawString(g, tField.sDefaust, tField.offsetX + 4 + x, y + ((height - mfont.getHeight()) / 2), 0, true);
        } else {
            mFont mfont2 = tField.fontTField;
            mfont2.drawString(g, tField.paintedText, tField.offsetX + 4 + x, ((((height - mfont2.getHeight()) / 2) + y) + 1) - 2, 0, true);
        }
        if (!tf.isFocused() || tField.startSelected != -1 || tField.endSelected != -1 || tField.keyInActiveState != 0 || tField.inputType == 6) {
            return;
        }
        if (tField.showCaretCounter > 0 || (tField.counter / 5) % 2 == 0) {
            g.setColor(TField.CARET_COLOR);
            int i = TField.CARET_HEIGHT;
            g.fillRect(((((tField.offsetX + 4) + x) + tField.fontTField.getWidth(tField.paintedText.substring(0, tField.caretPos))) - 1) + 1, ((tField.fontTField.getHeight() + y) - (i / 2)) + 1, 1, i);
        }
    }

    public void painTextInputNoneBg(mGraphics g, int x, int y, int width, int height, TField tf, boolean isFocus, int type) {
        int xCenter;
        mGraphics mgraphics = g;
        TField tField = tf;
        switch (type) {
            case 0:
                xCenter = 0;
                break;
            case 1:
                xCenter = width - tField.fontTField.getWidth(tField.paintedText);
                break;
            case 2:
                xCenter = (width / 2) - (tField.fontTField.getWidth(tField.paintedText) / 2);
                break;
            default:
                xCenter = 0;
                break;
        }
        if (!(!tf.isFocused() || tField.startSelected == -1 || tField.endSelected == -1)) {
            g.setColor(31743);
            g.fillRect(((((tField.offsetX + 4) + x) + tField.fontTField.getWidth(tField.paintedText.substring(0, tField.startSelected))) - 1) + 1, y + ((height - TField.CARET_HEIGHT) / 2) + 1, tField.fontTField.getWidth(tField.paintedText.substring(tField.startSelected, tField.endSelected)), tField.fontTField.getHeight() - 1);
        }
        if (tField.paintedText.equals("")) {
            mFont mfont = tField.fontTField;
            mfont.drawString(g, tField.sDefaust, tField.offsetX + 4 + x + xCenter, y + ((height - mfont.getHeight()) / 2), 0, false);
        } else {
            mFont mfont2 = tField.fontTField;
            mfont2.drawString(g, tField.paintedText, tField.offsetX + 4 + x + xCenter, y + ((height - mfont2.getHeight()) / 2) + 1, 0, true);
        }
        if (!tf.isFocused() || tField.startSelected != -1 || tField.endSelected != -1 || tField.keyInActiveState != 0 || tField.inputType == 6) {
            return;
        }
        if (tField.showCaretCounter > 0 || (tField.counter / 5) % 2 == 0) {
            g.setColor(TField.CARET_COLOR);
            int i = TField.CARET_HEIGHT;
            g.fillRect(((((tField.offsetX + 4) + x) + tField.fontTField.getWidth(tField.paintedText.substring(0, tField.caretPos))) - 1) + 1 + xCenter, y + ((height - i) / 2) + 1, 1, i);
        }
    }

    public void paintCircleTarget(mGraphics g, int xStart, int yStart) {
        this.xCircle = xStart - (LoadDataManager.imgCircleTarget.getRWidth() / 2);
        this.yCircle = yStart - (LoadDataManager.imgCircleTarget.getRHeight() / 2);
        g.setColor(LoadDataManager.color_circle);
        g.drawImage(LoadDataManager.imgCircleTarget, (float) this.xCircle, (float) this.yCircle);
        g.setColor(LoadDataManager.color_circle);
        SpriteBatch spriteBatch = g.g;
        spriteBatch.setColor(spriteBatch.getColor().r, g.g.getColor().g, g.g.getColor().b, 0.42f);
        g.fillRect(0, 0, this.xCircle, CanvasNinja.h);
        int i = this.xCircle;
        g.fillRect(i, 0, CanvasNinja.w - i, this.yCircle);
        g.fillRect(this.xCircle, this.yCircle + LoadDataManager.imgCircleTarget.getRHeight(), CanvasNinja.w - this.xCircle, (CanvasNinja.h - this.yCircle) - LoadDataManager.imgCircleTarget.getRHeight());
        g.fillRect(this.xCircle + LoadDataManager.imgCircleTarget.getRWidth(), this.yCircle, (CanvasNinja.w - this.xCircle) - LoadDataManager.imgCircleTarget.getRWidth(), LoadDataManager.imgCircleTarget.getRHeight());
        g.g.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void paintDoubleJump(mGraphics g, int x, int y) {
        int sumW = (((int) LoadDataManager.mySkillButton[5].frameWidth) * 2) + 28 + (LoadDataManager.imgPlus.getRWidth() * 2) + Player.myCharz().cw;
        FrameImage frameImage = LoadDataManager.mySkillButton[5];
        frameImage.drawFrame(0, (float) (x - (sumW / 2)), ((float) y) - (frameImage.frameHeight / 2.0f), g);
        g.drawImage(LoadDataManager.imgPlus, (float) ((x - (sumW / 2)) + ((int) LoadDataManager.mySkillButton[5].frameWidth) + 7), (float) (y - (LoadDataManager.imgPlus.getRHeight() / 2)));
        FrameImage frameImage2 = LoadDataManager.mySkillButton[5];
        frameImage2.drawFrame(0, (float) ((x - (sumW / 2)) + ((int) frameImage2.frameWidth) + 14 + LoadDataManager.imgPlus.getRWidth()), ((float) y) - (LoadDataManager.mySkillButton[5].frameHeight / 2.0f), g);
        g.drawImage(LoadDataManager.imgPlus, (float) ((x - (sumW / 2)) + (((int) LoadDataManager.mySkillButton[5].frameWidth) * 2) + 21 + LoadDataManager.imgPlus.getRWidth()), (float) (y - (LoadDataManager.imgPlus.getRHeight() / 2)));
        Player.myCharz().paintDoubleJump(g, (x - (sumW / 2)) + (((int) LoadDataManager.mySkillButton[5].frameWidth) * 2) + 28 + (LoadDataManager.imgPlus.getRWidth() * 2) + 30, ((Player.myCharz().ch / 2) + y) - 20);
    }
}
