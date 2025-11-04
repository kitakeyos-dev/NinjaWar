package ninjawar.model;

import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;

public class TextFly {
    public int[] flyTextColor = new int[8];
    public int[] flyTextDx = new int[5];
    public int[] flyTextDy = new int[5];
    public int[] flyTextState = new int[5];
    public String[] flyTextString = new String[5];
    public int[] flyTextX = new int[5];
    public int[] flyTextY = new int[5];
    public int[] flyTextYTo = new int[5];
    public int[] flyTime = new int[5];
    int sizemax = 5;

    public TextFly() {
        for (int i = 0; i < this.sizemax; i++) {
            this.flyTextState[i] = -1;
        }
    }

    public void startFlyText(String flyString, int x, int y, int dx, int dy, int color) {
        int[] iArr = this.flyTextYTo;
        int i;
        int n = -1;
        int i2 = 0;
        while (true) {
            if (i2 >= this.sizemax) {
                break;
            } else if (this.flyTextState[i2] == -1) {
                n = i2;
                break;
            } else {
                i2++;
            }
        }
        if (n != -1) {
            this.flyTextColor[n] = color;
            this.flyTextString[n] = flyString;
            this.flyTextX[n] = x;
            this.flyTextY[n] = y;
            this.flyTextDx[n] = dx;
            this.flyTextDy[n] = dy < 0 ? -5 : 5;
            this.flyTextState[n] = 0;
            this.flyTime[n] = 0;
            this.flyTextYTo[n] = 10;
            for (int i3 = 0; i3 < 5; i3++) {
                if (!(this.flyTextState[i3] == -1 || n == i3 || this.flyTextDy[n] >= 0)) {
                    int[] iArr2 = this.flyTextX;
                    if (Res.abs(iArr2[n] - iArr2[i3]) <= 20 && iArr[n] == iArr[i3]) {
                        iArr[n] += 10;
                    }
                }
            }
        }
    }

    public void paint(mGraphics g) {
        for (int i = 0; i < this.sizemax; i++) {
            if (this.flyTextState[i] != -1 && CanvasNinja.isPaint(this.flyTextX[i], this.flyTextY[i])) {
                int i2 = this.flyTextColor[i];
                if (i2 == 0) {
                    mFont.bigNumber_red.drawStringBorder(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 2, mFont.bigNumber_black);
                } else if (i2 == 1) {
                    mFont.tahoma_7b_yellow_border_black_small.drawStringBorder(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 2, (mFont) null);
                } else if (i2 == 2) {
                    mFont.bigNumber_green.drawStringBorder(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 2, mFont.bigNumber_black);
                } else if (i2 == 3) {
                    mFont.bigNumber_yellow.drawStringBorder(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 2, mFont.bigNumber_black);
                } else if (i2 == 8) {
                    mFont.bigNumber_green.drawStringBorder(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 2, mFont.bigNumber_black);
                } else if (i2 == 4) {
                    mFont.bigNumber_While.drawStringBorder(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 2, mFont.bigNumber_black);
                } else if (i2 == 5) {
                    mFont.bigNumber_orange.drawStringBorder(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 2, mFont.bigNumber_black);
                } else if (i2 == 6) {
                    mFont.bigNumber_yellow.drawStringBorder(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 2, mFont.bigNumber_black);
                } else if (i2 == 7) {
                    mFont.bigNumber_While.drawStringBorder(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 2, mFont.bigNumber_black);
                } else if (i2 == 9) {
                    mFont.bigNumber_red.drawStringBorder(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 2, mFont.bigNumber_black);
                } else if (i2 == 10) {
                    mFont.bigNumber_blue.drawStringBorder(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 2, mFont.bigNumber_black);
                }
            }
        }
    }

    public void update() {
        for (int i = 0; i < this.sizemax; i++) {
            int[] iArr = this.flyTextState;
            int i2 = iArr[i];
            if (i2 != -1) {
                int[] iArr2 = this.flyTextYTo;
                if (i2 > iArr2[i]) {
                    int[] iArr3 = this.flyTime;
                    int i3 = iArr3[i] + 1;
                    iArr3[i] = i3;
                    if (i3 == 25) {
                        iArr3[i] = 0;
                        iArr[i] = -1;
                        iArr2[i] = 0;
                        this.flyTextDx[i] = 0;
                        this.flyTextX[i] = 0;
                    }
                } else {
                    iArr[i] = i2 + Res.abs(this.flyTextDy[i]);
                    int[] iArr4 = this.flyTextX;
                    iArr4[i] = iArr4[i] + this.flyTextDx[i];
                    int[] iArr5 = this.flyTextY;
                    iArr5[i] = iArr5[i] + this.flyTextDy[i];
                }
            }
        }
    }
}
