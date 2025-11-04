package ninjawar.model;

import ninjawar.mylib.Image;
import ninjawar.mylib.mGraphics;
import ninjawar.supporter.LoadDataManager;

public class Star {
    public Image img;
    public int numStar = 1;

    public Star(int numStar2) {
        this.numStar = numStar2;
        this.img = LoadDataManager.imgSao;
    }

    public void paint(mGraphics g, int x, int y, boolean useClip) {
        if (this.img != null) {
            for (int i = 0; i < 5; i++) {
                g.drawImage(LoadDataManager.imgSaoDen, ((this.img.getRWidth() + 2) * i) + x, y, useClip);
            }
            for (int i2 = 0; i2 < this.numStar; i2++) {
                Image image = this.img;
                g.drawImage(image, ((image.getRWidth() + 2) * i2) + x, y, useClip);
            }
        }
    }

    public int getWStar() {
        return (this.numStar * (this.img.getRWidth() + 2)) - 2;
    }

    public void paint(mGraphics g, int x, int y) {
        paint(g, x, y, false);
    }
}
