package ninjawar.input;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Rms;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.supporter.LoadDataManager;

public class MyRadio {
    public int dataSaveRms = -2;
    public FrameImage frameImg;
    public int h;
    public boolean isChecked = false;
    public String nameRms;
    public int w;
    public int x;
    public int y;

    public MyRadio(int x2, int y2, int w2, int h2, boolean isCheck) {
        this.x = x2;
        this.y = y2;
        this.w = w2;
        this.h = h2;
        this.isChecked = isCheck;
        this.frameImg = LoadDataManager.frameRadio;
    }

    public void paint(mGraphics g) {
        this.frameImg.drawFrame(this.isChecked ^ true ? 1 : 0, (float) this.x, (float) this.y, 0, g);
    }

    public void update() {
    }

    public void updatePointer() {
        if (CanvasNinja.isPointerRelease() && CanvasNinja.isPoint(this.x, this.y, this.w, this.h)) {
            this.isChecked = !this.isChecked;
            saveRms();
            CanvasNinja.clearAllPointer();
        }
    }

    public void saveRms() {
        String str;
        int i;
        if (this.isChecked && (str = this.nameRms) != null && (i = this.dataSaveRms) != -2) {
            Rms.saveRMSInt(str, i);
        }
    }

    public boolean isChecked() {
        if (!CanvasNinja.isPointerRelease() || !CanvasNinja.isPoint(this.x, this.y, this.w, this.h)) {
            return false;
        }
        return true;
    }

    public static void selectOneRadio(MyRadio[] radios, int indexSelect) {
        for (MyRadio radio : radios) {
            radio.isChecked = false;
        }
        radios[indexSelect].isChecked = true;
        radios[indexSelect].saveRms();
    }
}
