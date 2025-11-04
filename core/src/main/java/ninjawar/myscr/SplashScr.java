package ninjawar.myscr;

import ninjawar.mylib.BaseScreen;
import ninjawar.mylib.Image;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.screen.LoginScr;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.LoadVersionManager;

public class SplashScr extends BaseScreen {
    public static Image imgLogo = Image.createImage("/gamelogo.png");
    public static SplashScr instance;
    public static int splashScrStat;
    public boolean isPaintLogoGame;
    public int percentLogo = 0;

    public SplashScr() {
        LoadVersionManager.loadClientVersion();
        instance = this;
    }

    public void switchToMe() {
        CanvasNinja.resetTab();
        splashScrStat = 30;
        super.switchToMe();
    }

    public static SplashScr gI() {
        SplashScr splashScr = instance;
        return splashScr != null ? splashScr : new SplashScr();
    }

    public static void loadSplashScr() {
        splashScrStat = 0;
    }

    public void update() {
        if (splashScrStat == 30 && mFont.isLoadFontDone) {
            splashScrStat = 31;
            this.isPaintLogoGame = true;
        }
        int i = splashScrStat;
        if (i > 30 && this.isPaintLogoGame) {
            this.percentLogo = (int) ((((float) i) / 80.0f) * 100.0f);
        }
        if (i == 80 && this.isPaintLogoGame) {
            LoginScr.gI().switchToMe();
        }
        splashScrStat++;
    }

    public void paint(mGraphics g) {
        if (splashScrStat < 30) {
            g.drawImage(imgLogo, (float) (CanvasNinja.w / 2), (float) (CanvasNinja.h / 2), 3);
            return;
        }
        g.drawImage(LoadDataManager.imgLogoGame, (float) (CanvasNinja.w / 2), (float) (CanvasNinja.h / 2), 3, true, this.percentLogo);
    }

    public static void loadImg() {
        imgLogo = Image.createImage("/gamelogo.png");
    }
}
