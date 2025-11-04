package ninjawar.input;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.supporter.LoadDataManager;

public class MyCheckBox {
    public FrameImage frameImg;
    public int h;
    public boolean isChecked = false;
    private long lastTimeClicked = 0;
    public int w;
    public int x;
    public int y;

    public MyCheckBox(int x2, int y2, int w2, int h2, boolean isCheck) {
        this.x = x2;
        this.y = y2;
        this.w = w2;
        this.h = h2;
        this.isChecked = isCheck;
        this.frameImg = LoadDataManager.frameCheckBox;
    }

    public void paint(mGraphics g) {
        this.frameImg.drawFrame(this.isChecked ? 1 : 0, (float) this.x, (float) this.y, 0, g);
    }

    public void update() {
        updatePointer();
    }

    public void updatePointer() {
        if (CanvasNinja.isPointerRelease() && CanvasNinja.isPoint(this.x, this.y, this.w, this.h)) {
            CanvasNinja.clearAllPointer();
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.lastTimeClicked >= 300) {
                this.lastTimeClicked = currentTime;
                this.isChecked = !this.isChecked;
            }
        }
    }
}
