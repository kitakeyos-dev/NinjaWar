package ninjawar.skill;

import ninjawar.model.Skill;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.IMapObject;
import ninjawar.myscr.Player;
import ninjawar.myscr.Quai;
import ninjawar.myscr.Res;
import ninjawar.supporter.BigImage;

public class EffectSkill extends MainEffect {
    public BigImage[] bigImgs;
    boolean isDoneX;
    Skill skill;
    long timeStartEffect;
    int xStart;
    int yStart;

    public EffectSkill(Player charUse, Skill skill2, IMapObject focus, int speedX, byte typeBeforeOrAfter, int timeBuff) {
        this.typeBeforeOrAfter = typeBeforeOrAfter;
        this.timeBuff = timeBuff == -1 ? -1 : ((long) timeBuff) + mSystem.currentTimeMillis();
        this.charUse = charUse;
        short s = skill2.idTarget;
        int i = 2;
        this.idTarget = s == -1 ? 2 : s;
        this.skill = skill2;
        this.timeStartEffect = mSystem.currentTimeMillis() + 2000;
        if (speedX > 0) {
            if (focus != null) {
                if (focus instanceof Player) {
                    Player player = (Player) focus;
                    this.charTarget = player;
                    this.xTarget = player.cx;
                    this.yTarget = player.cy;
                } else if (focus instanceof Quai) {
                    Quai quai = (Quai) focus;
                    this.mobTarget = quai;
                    this.xTarget = quai.x;
                    this.yTarget = quai.y - (quai.getH() / 4);
                }
            }
            int i2 = charUse.cx;
            this.x = i2;
            int i3 = charUse.cy;
            this.y = i3;
            this.xStart = i2;
            this.yStart = i3;
            int i4 = this.xTarget > i2 ? 1 : -1;
            this.dirLR = i4;
            this.dirTemp = i4 != -1 ? 3 : i;
            this.dirUD = 0;
            this.speedX = speedX;
            this.speedY = 0;
            return;
        }
        if (focus != null) {
            if (focus instanceof Player) {
                Player player2 = (Player) focus;
                this.charTarget = player2;
                int i5 = player2.cx;
                this.xTarget = i5;
                this.x = i5;
                int i6 = player2.cy;
                this.yTarget = i6;
                this.y = i6;
            } else if (focus instanceof Quai) {
                Quai quai2 = (Quai) focus;
                this.mobTarget = quai2;
                int i7 = quai2.x;
                this.xTarget = i7;
                this.x = i7;
                int h = quai2.y - (quai2.getH() / 4);
                this.yTarget = h;
                this.y = h;
            }
        }
        this.speedX = 0;
        this.speedY = 0;
        this.dirLR = 0;
        this.dirUD = 0;
        this.xStart = this.x;
        this.yStart = this.y;
    }

    public void loadEffectFromTool(short[] idPart) {
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
                    this.id = idPart[0];
                    this.type = 1;
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(mGraphics g) {
        int k = 0;
        while (true) {
            BigImage[] bigImageArr = this.bigImgs;
            if (k < bigImageArr.length) {
                BigImage bigImage = bigImageArr[k];
                byte[] bArr = bigImage.sequence;
                if (bArr == null || bArr.length <= 0) {
                    Player player = this.charUse;
                    if (player != null) {
                        bigImage.paint(g, this.x, (player.cy - (player.ch / 2)) + (bigImage.smallImage[0].h / 2), player.frame, (byte) this.dirTemp, (byte) -1, 0, false, false);
                    }
                } else {
                    int i = this.indexFrameEffect[k];
                    if (i != -1) {
                        int i2 = this.x;
                        Player player2 = this.charUse;
                        bigImage.paint(g, i2, (player2.cy - (player2.ch / 2)) + (bigImage.smallImage[0].h / 2), bArr[i], (byte) this.dirTemp, (byte) -1, 0, false, false);
                    }
                }
                k++;
            } else {
                return;
            }
        }
    }

    public void update() {
        int i;
        int i2;
        if (this.timeStartEffect != 0 && mSystem.currentTimeMillis() > this.timeStartEffect) {
            this.timeStartEffect = 0;
            this.isRemove = true;
        }
        if (CanvasNinja.gameTick % 2 == 0 && this.bigImgs != null) {
            int i3 = 0;
            while (true) {
                int[] iArr = this.indexFrameEffect;
                if (i3 >= iArr.length) {
                    break;
                }
                int i4 = iArr[i3] + 1;
                iArr[i3] = i4;
                byte[] bArr = this.bigImgs[i3].sequence;
                if (bArr != null && i4 > bArr.length - 1) {
                    iArr[i3] = 0;
                }
                i3++;
            }
        }
        Player player = this.charTarget;
        if (player != null) {
            this.xTarget = player.cx;
            this.yTarget = player.cy;
        } else {
            Quai quai = this.mobTarget;
            if (quai != null) {
                this.xTarget = quai.x;
                this.yTarget = quai.y - (quai.getH() / 4);
            }
        }
        Skill skill2 = this.skill;
        if (skill2 != null && skill2.template.isChuongAOE()) {
            int i5 = this.dirLR;
            if (i5 != 0) {
                this.x += i5 * this.speedX;
            }
            this.y = this.yStart;
            int distanceTraveled = Math.abs(this.x - this.xStart);
            if (isWithinRange(this.xTarget, this.yTarget)) {
                applyAoEEffect();
            }
            if (distanceTraveled >= this.skill.dx) {
                this.isRemove = true;
            }
        } else {
            if (this.dirLR != 0) {
                int speedX = this.speedX;
                int dis = Res.abs(this.x - this.xTarget);
                if (dis < speedX) {
                    speedX = dis;
                }
                int i6 = this.x;
                int i7 = this.dirLR;
                int i8 = i6 + (i7 * speedX);
                this.x = i8;
                if (i7 == -1 && i8 <= (i2 = this.xTarget)) {
                    this.x = i2;
                    this.isDoneX = true;
                }
                if (i7 == 1 && this.x >= (i = this.xTarget)) {
                    this.x = i;
                    this.isDoneX = true;
                }
            } else {
                this.isDoneX = true;
            }
            this.y = this.yStart;
            if (this.isDoneX) {
                this.isDoneX = false;
                if (this.idTarget != -1) {
                    Player player2 = this.charTarget;
                    if (player2 != null) {
                        EffectTarget effectTarget = new EffectTarget(player2, (byte) 0, 0, this.direct);
                        effectTarget.loadEffectFromTool(new short[]{this.idTarget});
                        effectTarget.isRemoveSpec = true;
                        this.charTarget.vEffectSkillWaiting.add(effectTarget);
                    } else {
                        Quai quai2 = this.mobTarget;
                        if (quai2 != null) {
                            EffectTarget effectTarget2 = new EffectTarget(quai2, (byte) 0, 0, this.direct);
                            effectTarget2.loadEffectFromTool(new short[]{this.idTarget});
                            effectTarget2.isRemoveSpec = true;
                            this.mobTarget.vEffectSkillWaiting.add(effectTarget2);
                        } else {
                            EffectTarget effectTarget3 = new EffectTarget(this.xTarget, this.yTarget, (byte) 0, 0, this.direct, this.subDirect);
                            effectTarget3.loadEffectFromTool(new short[]{this.idTarget});
                            effectTarget3.isRemoveSpec = true;
                            this.mobTarget.vEffectSkillWaiting.add(effectTarget3);
                        }
                    }
                }
                this.isRemove = true;
            }
        }
        Quai quai3 = this.mobTarget;
        if (quai3 != null && quai3.isInvisible()) {
            this.isRemove = true;
        }
    }

    private void applyAoEEffect() {
        if (this.idTarget != -1) {
            Player player = this.charTarget;
            if (player != null && isWithinRange(player.cx, player.cy)) {
                EffectTarget effectTarget = new EffectTarget(this.charTarget, (byte) 0, 0, this.direct);
                effectTarget.loadEffectFromTool(new short[]{this.idTarget});
                effectTarget.isRemoveSpec = true;
                this.charTarget.vEffectSkillWaiting.add(effectTarget);
            }
            Quai quai = this.mobTarget;
            if (quai != null && isWithinRange(quai.x, quai.y)) {
                EffectTarget effectTarget2 = new EffectTarget(this.mobTarget, (byte) 0, 0, this.direct);
                effectTarget2.loadEffectFromTool(new short[]{this.idTarget});
                effectTarget2.isRemoveSpec = true;
                this.mobTarget.vEffectSkillWaiting.add(effectTarget2);
            }
        }
    }

    private boolean isWithinRange(int targetX, int targetY) {
        return Math.abs(targetX - this.x) <= this.skill.dx;
    }
}
