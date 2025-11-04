package ninjawar.input;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.supporter.LoadDataManager;

public class MyNextPrevious {
    int delayChange = 0;
    public FrameImage frameNextPrevious;
    public int h;
    public int indexSelected;
    public boolean isEnable = false;
    public boolean isFocus = false;
    public boolean isNext;
    private long lastTimeClicked = 0;
    public int maxIndex;
    public int pos;
    public int w;
    public int x;
    public int y;
    public int yAnim;

    public MyNextPrevious(int x2, int y2, int w2, int h2, int indexSelected2, int maxIndex2, int pos2, boolean isEnable2, boolean isNext2) {
        this.x = x2;
        this.y = y2;
        this.w = w2;
        this.h = h2;
        this.indexSelected = indexSelected2;
        this.isNext = isNext2;
        this.isEnable = isEnable2;
        this.maxIndex = maxIndex2;
        this.pos = pos2;
        this.frameNextPrevious = LoadDataManager.framePrevious;
    }

    public MyNextPrevious(FrameImage frame, int x2, int y2, int w2, int h2, int indexSelected2, int maxIndex2, int pos2, boolean isEnable2, boolean isNext2) {
        this.x = x2;
        this.y = y2;
        this.w = w2;
        this.h = h2;
        this.indexSelected = indexSelected2;
        this.isNext = isNext2;
        this.isEnable = isEnable2;
        this.maxIndex = maxIndex2;
        this.pos = pos2;
        this.frameNextPrevious = frame;
    }

    public void paint(mGraphics g) {
        if (!this.isNext) {
            this.frameNextPrevious.drawFrame(this.isEnable ^ true ? 1 : 0, (float) this.x, (float) (this.y + this.yAnim), 0, g);
            return;
        }
        this.frameNextPrevious.drawFrame(this.isEnable ^ true ? 1 : 0, (float) this.x, (float) (this.y + this.yAnim), 2, 0, false, g);
    }

    public void update() {
        if (this.isFocus) {
            int i = this.yAnim + 1;
            this.yAnim = i;
            if (i > 2) {
                this.yAnim = 0;
                this.isFocus = false;
            }
        }
    }

    public int updateSelected() {
        if (!CanvasNinja.isPointerRelease() || !CanvasNinja.isPoint(this.x, this.y, this.w, this.h)) {
            return -1;
        }
        CanvasNinja.clearAllPointer();
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastTimeClicked >= 300) {
            if (this.isNext) {
                int i = this.indexSelected + 1;
                this.indexSelected = i;
                if (i > this.maxIndex) {
                    this.indexSelected = 0;
                }
            } else {
                int i2 = this.indexSelected - 1;
                this.indexSelected = i2;
                if (i2 < 0) {
                    this.indexSelected = this.maxIndex;
                }
            }
            this.lastTimeClicked = currentTime;
            this.isFocus = true;
        }
        return this.indexSelected;
    }
}
