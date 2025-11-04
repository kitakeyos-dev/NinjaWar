// Class Version: 8
package ninjawar.skill;

import ninjawar.mymain.CanvasNinja;
import ninjawar.mylib.mGraphics;
import ninjawar.myscr.Quai;
import ninjawar.myscr.Player;
import ninjawar.mylib.mSystem;
import ninjawar.supporter.BigImage;

public class EffectTarget extends MainEffect
{
    public BigImage[] bigImgs;

    public EffectTarget(final int xTarget, final int yTarget, final byte b, final int n, final int direct, final int subDirect) {
        super.xTarget = xTarget;
        super.yTarget = yTarget;
        super.timeDelayAddSkill = n + mSystem.currentTimeMillis();
        super.typeBeforeOrAfter = b;
        super.direct = direct;
        super.subDirect = subDirect;
    }

    public EffectTarget(final Player charTarget, final byte b, final int n, final int direct) {
        super.charTarget = charTarget;
        super.xTarget = charTarget.cx;
        super.yTarget = charTarget.cy;
        super.timeDelayAddSkill = n + mSystem.currentTimeMillis();
        super.typeBeforeOrAfter = b;
        super.direct = direct;
    }

    public EffectTarget(final Quai mobTarget, final byte b, final int n, final int direct) {
        super.mobTarget = mobTarget;
        super.xTarget = mobTarget.xSdReal;
        super.yTarget = mobTarget.ySdReal;
        super.timeDelayAddSkill = n + mSystem.currentTimeMillis();
        super.typeBeforeOrAfter = b;
        super.direct = direct;
    }

    public void loadEffectFromTool(final short[] array) {
        try {
            this.bigImgs = new BigImage[array.length];
            int n = 0;
            while (true) {
                final BigImage[] bigImgs = this.bigImgs;
                if (n >= bigImgs.length) {
                    break;
                }
                bigImgs[n] = new BigImage();
                this.bigImgs[n].readDataFromTool(array[n], (byte)1, n);
                ++n;
            }
            super.indexFrameEffect = new int[array.length];
            super.id = array[0];
            super.type = 1;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void paint(final mGraphics mGraphics) {
        if (this.bigImgs != null) {
            int n = 0;
            while (true) {
                final BigImage[] bigImgs = this.bigImgs;
                if (n >= bigImgs.length) {
                    break;
                }
                final int n2 = super.indexFrameEffect[n];
                if (n2 != -1) {
                    final BigImage bigImage = bigImgs[n];
                    final byte[] sequence = bigImage.sequence;
                    if (sequence != null && sequence.length > 0) {
                        bigImage.paint(mGraphics, super.x, super.y, n2, (byte)super.direct, (byte)super.subDirect, 0, false, false);
                    }
                }
                ++n;
            }
        }
    }

    @Override
    public void paint(final mGraphics mGraphics, int n, int n2, final int n3, final byte b, final byte b2, final int n4, final boolean b3, final boolean b4) {
        if (this.bigImgs != null) {
            n = 0;
            while (true) {
                final BigImage[] bigImgs = this.bigImgs;
                if (n >= bigImgs.length) {
                    break;
                }
                n2 = super.indexFrameEffect[n];
                if (n2 != -1) {
                    final BigImage bigImage = bigImgs[n];
                    final byte[] sequence = bigImage.sequence;
                    if (sequence != null && sequence.length > 0) {
                        bigImage.paint(mGraphics, super.x, super.y, n2, (byte)super.direct, b2, n4, b3, b4);
                    }
                }
                ++n;
            }
        }
    }

    @Override
    public void update() {
        if (CanvasNinja.gameTick % 2 == 0) {
            int n = 0;
            while (true) {
                final BigImage[] bigImgs = this.bigImgs;
                if (n >= bigImgs.length) {
                    break;
                }
                final int[] indexFrameEffect = super.indexFrameEffect;
                int n2 = indexFrameEffect[n];
                if (n2 != -1) {
                    final byte[] sequence = bigImgs[n].sequence;
                    if (sequence != null && sequence.length > 0) {
                        ++n2;
                        if ((indexFrameEffect[n] = n2) > sequence.length - 1) {
                            indexFrameEffect[n] = 0;
                            super.isRemove = true;
                        }
                    }
                }
                ++n;
            }
        }
        final Player charTarget = super.charTarget;
        if (charTarget != null) {
            super.x = charTarget.cx;
            super.y = charTarget.cy;
        }
        else {
            final Quai mobTarget = super.mobTarget;
            if (mobTarget != null) {
                super.x = mobTarget.xSdReal;
                super.y = mobTarget.ySdReal;
            }
            else {
                super.x = super.xTarget;
                super.y = super.yTarget;
            }
        }
        final Quai mobTarget2 = super.mobTarget;
        if (mobTarget2 != null && mobTarget2.isInvisible()) {
            super.isRemove = true;
        }
    }
}
