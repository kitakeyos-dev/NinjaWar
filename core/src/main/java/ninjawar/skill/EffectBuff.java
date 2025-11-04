package ninjawar.skill;

import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Player;
import ninjawar.myscr.Quai;
import ninjawar.supporter.BigImage;

public class EffectBuff extends MainEffect {
    public BigImage[] bigImgs;

    public EffectBuff() {
    }

    public EffectBuff(Quai mob, byte typeBeforeOrAfter, int timeDelay, int timeBuff, int direct) {
        this.mobTarget = mob;
        this.timeDelayAddSkill = ((long) timeDelay) + mSystem.currentTimeMillis();
        this.typeBeforeOrAfter = typeBeforeOrAfter;
        this.timeBuff = timeBuff == -1 ? -1 : mSystem.currentTimeMillis() + ((long) timeBuff);
        this.direct = direct;
    }

    public EffectBuff(Player c, byte typeBeforeOrAfter, int timeDelay, int timeBuff, int direct) {
        this.charTarget = c;
        this.timeDelayAddSkill = ((long) timeDelay) + mSystem.currentTimeMillis();
        this.typeBeforeOrAfter = typeBeforeOrAfter;
        this.timeBuff = timeBuff == -1 ? -1 : mSystem.currentTimeMillis() + ((long) timeBuff);
        this.direct = direct;
    }

    public void loadEffectFromTool(short[] idPart) {
        if (idPart[0] != -1) {
            try {
                this.bigImgs = new BigImage[idPart.length];
                int i = 0;
                while (true) {
                    BigImage[] bigImageArr = this.bigImgs;
                    if (i < bigImageArr.length) {
                        bigImageArr[i] = new BigImage();
                        this.bigImgs[i].readDataFromTool(idPart[i], (byte) 1, i);
                        i++;
                    } else {
                        this.indexFrameEffect = new int[idPart.length];
                        this.id = idPart[0];
                        this.type = 1;
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(mGraphics g, int x, int y, int frame, byte direct, byte subDirect, int opacityPercent, boolean isOpacity, boolean useClip) {
        int yMore;
        int xMore;
        if (this.bigImgs != null) {
            int k = 0;
            while (true) {
                BigImage[] bigImageArr = this.bigImgs;
                if (k >= bigImageArr.length) {
                    break;
                }
                int i = this.indexFrameEffect[k];
                if (i != -1) {
                    BigImage bigImage = bigImageArr[k];
                    byte[] bArr = bigImage.sequence;
                    if (bArr.length > 0) {
                        bigImage.paint(g, x, y, bArr[i], direct, subDirect, opacityPercent, isOpacity, useClip);
                    }
                }
                k++;
            }
        }
        EffectFrameBuff effectFrameBuff = this.effectFrameBuff;
        if (effectFrameBuff != null) {
            Player player = this.charTarget;
            int yMore2 = 0;
            int xMore2 = player != null ? (-player.cw) / 2 : 0;
            int yMore3 = player != null ? -player.ch : 0;
            if (player == null) {
                Quai quai = this.mobTarget;
                int xMore3 = quai != null ? (-quai.w) / 2 : 0;
                if (quai != null) {
                    yMore2 = -quai.h;
                }
                xMore = xMore3;
                yMore = yMore2;
            } else {
                xMore = xMore2;
                yMore = yMore3;
            }
            effectFrameBuff.paint(g, x, y, this.timeBuff, xMore, yMore);
        }
    }

    public byte getType() {
        return this.type;
    }

    public boolean isMe(int id, byte type) {
        return this.id == id && type == getType();
    }

    public void update() {
        if (CanvasNinja.gameTick % 2 == 0 && this.bigImgs != null) {
            int i = 0;
            while (true) {
                int[] iArr = this.indexFrameEffect;
                if (i >= iArr.length) {
                    break;
                }
                int i2 = iArr[i] + 1;
                iArr[i] = i2;
                if (i2 > this.bigImgs[i].sequence.length - 1) {
                    iArr[i] = 0;
                }
                i++;
            }
        }
        Player player = this.charTarget;
        if (player != null) {
            this.x = player.cx;
            this.y = player.cy;
        }
        Quai quai = this.mobTarget;
        if (quai != null) {
            this.x = quai.x;
            this.y = quai.y;
            if (quai.isInvisible()) {
                this.isRemove = true;
            }
        }
    }
}
