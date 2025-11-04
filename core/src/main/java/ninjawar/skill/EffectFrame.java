package ninjawar.skill;

import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.supporter.BigImage;

public class EffectFrame extends MainEffect {
    public BigImage[] bigImgs;
    int frameTemp = -1;
    int indexFrameEffect = -1;

    public EffectFrame() {
    }

    public EffectFrame(byte typeBeforeOrAfter, int timeDelay, boolean isBigBang) {
        this.typeBeforeOrAfter = typeBeforeOrAfter;
        this.isBigBang = isBigBang;
        this.timeDelayAddSkill = ((long) timeDelay) + mSystem.currentTimeMillis();
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
                        this.bigImgs[i].readDataFromTool(idPart[i], (byte) 2, i);
                        i++;
                    } else {
                        this.id = idPart[0];
                        this.type = 2;
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadEffectFromTool(short[] idPart, byte folder) {
        try {
            this.bigImgs = new BigImage[idPart.length];
            int i = 0;
            while (true) {
                BigImage[] bigImageArr = this.bigImgs;
                if (i < bigImageArr.length) {
                    bigImageArr[i] = new BigImage();
                    this.bigImgs[i].readDataFromTool(idPart[i], folder, i);
                    i++;
                } else {
                    this.id = idPart[0];
                    this.type = folder;
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(mGraphics g, int x, int y, int frame, byte direct, byte subDirect, int opacityPercent, boolean isOpacity, boolean useClip) {
        this.frameTemp = frame;
        int k = 0;
        while (true) {
            BigImage[] bigImageArr = this.bigImgs;
            if (k < bigImageArr.length) {
                BigImage bigImage = bigImageArr[k];
                byte[] bArr = bigImage.sequence;
                if (bArr == null || bArr.length <= 0) {
                    bigImage.paint(g, x, y, frame, direct, subDirect, opacityPercent, isOpacity, useClip);
                } else {
                    int i = this.indexFrameEffect;
                    if (i != -1) {
                        bigImage.paint(g, x, y, bArr[i], direct, subDirect, opacityPercent, isOpacity, useClip);
                    }
                }
                k++;
            } else {
                return;
            }
        }
    }

    public void update() {
        int k = 0;
        while (true) {
            BigImage[] bigImageArr = this.bigImgs;
            if (k < bigImageArr.length) {
                BigImage bigImage = bigImageArr[k];
                byte[] bArr = bigImage.sequence;
                if (bArr != null && bArr.length > 0) {
                    int findIndexFrameEffect = findIndexFrameEffect(bigImage, this.frameTemp);
                    this.indexFrameEffect = findIndexFrameEffect;
                    if (this.isRemoveWhenDone) {
                        if (this.isRemoveSpec && findIndexFrameEffect == (this.bigImgs[0].sequence.length / 2) - 1) {
                            this.isRemove = true;
                        }
                        if (findIndexFrameEffect == this.bigImgs[0].sequence.length - 1) {
                            this.isRemove = true;
                        }
                    }
                }
                k++;
            } else {
                return;
            }
        }
    }

    public int findIndexFrameEffect(BigImage bigImg, int frame) {
        if (bigImg.frameChar[0] == null) {
            return -1;
        }
        int i = 0;
        while (true) {
            byte[] bArr = bigImg.frameChar[0];
            if (i >= bArr.length) {
                return -1;
            }
            if (frame == bArr[i]) {
                return i;
            }
            i++;
        }
    }

    public byte getType() {
        return this.type;
    }

    public boolean isMe(int id, byte type) {
        return this.id == id && type == getType();
    }
}
