package ninjawar.screen;

import ninjawar.message.SendMessage;
import ninjawar.model.ActionInterface;
import ninjawar.mylib.BaseScreen;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.myscr.SplashScr;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.LoadVersionManager;
import ninjawar.supporter.SupportTranslate;

public class DownLoadingScr extends BaseScreen implements ActionInterface {
    public static boolean isSendClientOk = false;
    int[] arrH;
    int[] arrW;
    int[] arrX;
    int[] arrY;
    public FrameImage frameWaiting = LoadDataManager.frameWaiting;
    public int indexFrame;
    public long lastTimeScr;
    float percent;
    public long timeReturn;

    public void switchToMe() {
        Res.outz(1, "Downloading switch to me");
        CanvasNinja.endDlg();
        this.lastTimeScr = System.currentTimeMillis() + 5000;
        this.timeReturn = System.currentTimeMillis() + 30000;
        init();
        super.switchToMe();
    }

    private void init() {
        this.arrW = new int[]{0, 0, 1, LoadDataManager.img_ld_xanh_big_nen.getRWidth(), 0};
        this.arrH = new int[]{0, 0, 1, LoadDataManager.img_ld_xanh_big_nen.getRHeight(), 0};
        int i = CanvasNinja.w;
        this.arrX = new int[]{((int) (((float) i) - this.frameWaiting.frameWidth)) / 2, i / 2, 1, (i - LoadDataManager.img_ld_xanh_big_nen.getRWidth()) / 2, ((CanvasNinja.w - LoadDataManager.img_ld_xanh_big_nen.getRWidth()) / 2) + (this.arrW[3] / 2)};
        int i2 = CanvasNinja.h;
        this.arrY = new int[]{((i2 * 4) / 5) - 50, ((i2 * 4) / 5) - 20, 1, (i2 * 4) / 5, (((int) (((float) i2) - this.frameWaiting.frameHeight)) / 2) + 10 + LoadDataManager.img_ld_xanh_big_nen.getRHeight() + (this.arrH[3] / 2)};
    }

    public void paint(mGraphics g) {
        g.setColor(0);
        g.fillRect(0, 0, CanvasNinja.w, CanvasNinja.h);
        this.frameWaiting.drawFrame(this.indexFrame, (float) this.arrX[0], (float) this.arrY[0], 0, g);
        mFont mfont = mFont.tahoma_7_white;
        mfont.drawString(g, SupportTranslate.getTextLangue("downloadingData") + Res.round(this.percent * 100.0f, 2) + "%", this.arrX[1], this.arrY[1], 2);
        g.drawImage(LoadDataManager.img_ld_xanh_big_nen, (float) this.arrX[3], (float) this.arrY[3]);
        float percentLoad = LoadVersionManager.percentLoad();
        this.percent = percentLoad;
        g.drawRegion(LoadDataManager.img_ld_xanh_big, 0.0f, 0.0f, (float) ((int) (((float) this.arrW[3]) * percentLoad)), (float) this.arrH[3], 0, (float) this.arrX[3], (float) this.arrY[3], 0);
    }

    public void update() {
        if (this.timeReturn == 0 || mSystem.currentTimeMillis() < this.timeReturn) {
            if (CanvasNinja.gameTick % 1 == 0) {
                int i = this.indexFrame + 1;
                this.indexFrame = i;
                if (i > this.frameWaiting.nFrame - 1) {
                    this.indexFrame = 0;
                }
            }
            if (LoadDataManager.saveRMSDelay() && LoadVersionManager.isLoadAllData() && !isSendClientOk) {
                isSendClientOk = true;
                Res.outz("SEND CLIENT OKKKKKKKK");
                SendMessage.gI().clientOk();
                return;
            }
            return;
        }
        this.timeReturn = 0;
        SendMessage.gI().logOut();
        SplashScr.gI().switchToMe();
    }

    public void perform(int idAction, Object p) {
    }
}
