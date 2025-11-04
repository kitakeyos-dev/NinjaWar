// Class Version: 8
package ninjawar.skill;

import ninjawar.mymain.CanvasNinja;
import ninjawar.mylib.mGraphics;
import ninjawar.myscr.Res;
import ninjawar.mylib.mSystem;
import ninjawar.myscr.Player;
import ninjawar.supporter.BigImage;

public class EffectSkillMoveToByTool extends MainEffect
{
    public static int TIME_DELAY_REMOVE;
    public BigImage[] bigImgs;
    Player[] charAttack;
    int[] dmgHP;
    public boolean isDoneX;
    public boolean isDoneY;
    long timeDelayRemove;
    long timeStartEffect;

    static {
        EffectSkillMoveToByTool.TIME_DELAY_REMOVE = 300;
    }

    public EffectSkillMoveToByTool(int abs, int abs2, final int xTarget, final int yTarget, final byte b, int y, final int direct, final int subDirect, final int speedX, final Player[] charAttack, final int[] dmgHP) {
        super.timeBuff = 0L;
        super.isRemove = false;
        this.charAttack = charAttack;
        this.dmgHP = dmgHP;
        super.x = abs;
        super.y = abs2;
        super.xTarget = xTarget;
        super.yTarget = yTarget;
        super.timeDelayAddSkill = y + mSystem.currentTimeMillis();
        super.typeBeforeOrAfter = b;
        super.direct = direct;
        super.subDirect = subDirect;
        final int x = super.x;
        abs2 = -1;
        if (xTarget > x) {
            abs = 1;
        }
        else {
            abs = -1;
        }
        super.dirLR = abs;
        if (abs == -1) {
            abs = 2;
        }
        else {
            abs = 3;
        }
        super.dirTemp = abs;
        y = super.y;
        if (yTarget < y) {
            abs = abs2;
        }
        else {
            abs = 1;
        }
        super.dirUD = abs;
        if (yTarget == y) {
            super.dirUD = 0;
            this.isDoneY = true;
        }
        super.speedX = speedX;
        abs = Res.abs(xTarget - x);
        abs2 = Res.abs(super.y - yTarget);
        super.speedY = (int)(abs2 / (abs * 1.0f / super.speedX));
        this.timeStartEffect = mSystem.currentTimeMillis() + 3000L;
    }

    private void remove() {
        super.isRemove = true;
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
                        }
                    }
                }
                ++n;
            }
        }
        this.updateMoveFrame();
    }

    public void updateMoveFrame() {
        if (this.charAttack != null) {
            int n = 0;
            while (true) {
                final Player[] charAttack = this.charAttack;
                if (n >= charAttack.length) {
                    break;
                }
                final Player player = charAttack[n];
                if (player != null) {
                    if (super.dirLR == -1 && player.cx - super.x > 20) {
                        player.doInjure(this.dmgHP[n], 0, 0, false, false);
                        this.charAttack[n] = null;
                    }
                    if (super.dirLR == 1) {
                        final int x = super.x;
                        final Player player2 = this.charAttack[n];
                        if (x - player2.cx > 20) {
                            player2.doInjure(this.dmgHP[n], 0, 0, false, false);
                            this.charAttack[n] = null;
                        }
                    }
                }
                ++n;
            }
        }
        if (super.dirLR != 0) {
            final int speedX = super.speedX;
            final int abs = Res.abs(super.x - super.xTarget);
            int n2;
            if (abs < (n2 = speedX)) {
                n2 = abs;
            }
            final int x2 = super.x;
            final int dirLR = super.dirLR;
            final int x3 = x2 + dirLR * n2;
            super.x = x3;
            if (dirLR == -1) {
                final int xTarget = super.xTarget;
                if (x3 < xTarget) {
                    super.x = xTarget;
                    this.isDoneX = true;
                }
            }
            if (dirLR == 1) {
                final int x4 = super.x;
                final int xTarget2 = super.xTarget;
                if (x4 > xTarget2) {
                    super.x = xTarget2;
                    this.isDoneX = true;
                }
            }
        }
        if (super.dirUD != 0) {
            final int speedY = super.speedY;
            final int abs2 = Res.abs(super.y - super.yTarget);
            int n3;
            if (abs2 < (n3 = speedY)) {
                n3 = abs2;
            }
            final int y = super.y;
            final int dirUD = super.dirUD;
            final int y2 = y + dirUD * n3;
            super.y = y2;
            if (dirUD == -1) {
                final int yTarget = super.yTarget;
                if (y2 < yTarget) {
                    super.y = yTarget;
                    this.isDoneY = true;
                }
            }
            if (dirUD == 1) {
                final int y3 = super.y;
                final int yTarget2 = super.yTarget;
                if (y3 > yTarget2) {
                    super.y = yTarget2;
                    this.isDoneY = true;
                }
            }
        }
        else {
            this.isDoneY = true;
        }
        if (this.isDoneX && this.isDoneY) {
            this.timeDelayRemove = mSystem.currentTimeMillis() + EffectSkillMoveToByTool.TIME_DELAY_REMOVE;
            this.isDoneX = false;
            this.isDoneY = false;
            super.dirLR = 0;
            super.dirUD = 0;
        }
        if (this.timeStartEffect != 0L && mSystem.currentTimeMillis() > this.timeStartEffect) {
            this.timeStartEffect = 0L;
            this.remove();
        }
        final long currentTimeMillis = mSystem.currentTimeMillis();
        final long timeDelayRemove = this.timeDelayRemove;
        if (currentTimeMillis > timeDelayRemove && timeDelayRemove != 0L) {
            this.timeDelayRemove = 0L;
            this.remove();
        }
    }
}
