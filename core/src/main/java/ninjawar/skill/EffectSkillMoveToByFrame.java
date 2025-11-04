package ninjawar.skill;

import java.util.Vector;
import ninjawar.message.SendMessage;
import ninjawar.model.RangeDamageSkill;
import ninjawar.model.Skill;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.VectorCustom;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.IMapObject;
import ninjawar.myscr.Player;
import ninjawar.myscr.Quai;
import ninjawar.myscr.Res;

public class EffectSkillMoveToByFrame {
    public static short ID_PHI_TIEU = 0;
    public static int TIME_DELAY_REMOVE = 200;
    Player charTarget;
    Player charUse;
    public int dirLR;
    public int dirTemp;
    public int dirUD;
    public FrameImage frame;
    public int idIcon;
    public Image img;
    public int indexFrame;
    boolean isContinueY = false;
    boolean isDoneX = false;
    boolean isDoneY = false;
    public boolean isRemove = false;
    Quai mobTarget;
    public int numMaxFrame;
    Skill skill;
    public int speedX;
    public int speedY;
    long timeDelayRemove;
    public int x;
    public int xTarget;
    public int y;
    public int yTarget;

    public EffectSkillMoveToByFrame(Player charUse2, Skill skill2, IMapObject focus, int dirCharUse, int speedX2, int idIcon2, int numFrame) {
        this.charUse = charUse2;
        this.skill = skill2;
        Image img2 = Image.createImage((short) idIcon2, (short) 20);
        if (img2 != null) {
            this.idIcon = idIcon2;
            this.numMaxFrame = numFrame;
            this.frame = new FrameImage(img2, numFrame);
            int i4 = 2;
            if (focus != null) {
                if (focus instanceof Player) {
                    Player player2 = (Player) focus;
                    this.charTarget = player2;
                    this.xTarget = player2.cx;
                    this.yTarget = player2.cy - (player2.getH() / 2);
                } else if (focus instanceof Quai) {
                    Quai quai = (Quai) focus;
                    this.mobTarget = quai;
                    this.xTarget = quai.x;
                    this.yTarget = quai.y - (quai.h / 2);
                }
            }
            this.x = charUse2.cx;
            int h = charUse2.cy - ((charUse2.getH() * 2) / 3);
            this.y = h;
            int i5 = this.xTarget;
            int i6 = this.x;
            int i7 = 1;
            int i8 = i5 > i6 ? 1 : -1;
            this.dirLR = i8;
            this.dirTemp = i8 != -1 ? 3 : i4;
            this.dirUD = this.yTarget < h ? -1 : i7;
            this.speedX = speedX2;
            this.speedY = (int) (((float) Res.abs(this.y - this.yTarget)) / ((((float) Res.abs(i5 - i6))) / ((float) speedX2)));
        }
    }

    public void updateMoveFrame() {
        int i;
        int i2;
        int i3;
        int i4;
        Player player = this.charTarget;
        if (player != null) {
            this.xTarget = player.cx;
            this.yTarget = player.cy - (player.ch / 2);
        } else {
            Quai quai = this.mobTarget;
            if (quai != null) {
                this.xTarget = quai.x;
                this.yTarget = quai.y - (quai.h / 2);
            } else {
                this.isRemove = true;
                return;
            }
        }
        if (this.dirLR != 0) {
            int speedX2 = this.speedX;
            int dis = Res.abs(this.x - this.xTarget);
            if (dis < speedX2) {
                speedX2 = dis;
            }
            int i5 = this.x;
            int i6 = this.dirLR;
            int i7 = i5 + (i6 * speedX2);
            this.x = i7;
            if (i6 == -1 && i7 <= (i4 = this.xTarget)) {
                this.x = i4;
                this.isDoneX = true;
            }
            if (i6 == 1 && this.x >= (i3 = this.xTarget)) {
                this.x = i3;
                this.isDoneX = true;
            }
        }
        if (this.dirUD != 0) {
            int speedY2 = this.speedY;
            int dis2 = Res.abs(this.y - this.yTarget);
            if (dis2 < speedY2) {
                speedY2 = dis2;
            }
            int i8 = this.y;
            int i9 = this.dirUD;
            int i10 = i8 + (i9 * speedY2);
            this.y = i10;
            if (i9 == -1 && i10 <= (i2 = this.yTarget)) {
                this.y = i2;
                this.isDoneY = true;
            }
            if (i9 == 1 && this.y >= (i = this.yTarget)) {
                this.y = i;
                this.isDoneY = true;
            }
        } else {
            this.isDoneY = true;
        }
        if (this.isDoneX && this.isDoneY) {
            Skill skill2 = this.skill;
            if (!(skill2 == null || skill2.template == null || !this.charUse.isMe())) {
                Player player2 = this.charUse;
                Vector<RangeDamageSkill> vector = player2.vRangeDmgSkills;
                int i11 = player2.charID;
                Skill skill3 = this.skill;
                vector.add(new RangeDamageSkill(i11, skill3, this.xTarget, this.yTarget, skill3.dx, skill3.dy, player2.getTypeSkill(), (byte) this.dirLR, this.skill.maxFight));
                VectorCustom vMob = new VectorCustom();
                VectorCustom vChar = new VectorCustom();
                Player player3 = this.charUse;
                Quai quai2 = player3.mobFocus;
                if (quai2 != null) {
                    if (quai2.hp <= 0) {
                        quai2.startDie();
                    } else {
                        quai2.startEffectHurt(player3);
                    }
                    vMob.addElement(this.charUse.mobFocus);
                } else {
                    Player player4 = player3.charFocus;
                    if (player4 != null) {
                        if (player4.cHP <= 0) {
                            Res.outz("debug charfocus nulllllll:");
                            this.charUse.charFocus = null;
                        } else {
                            player4.doAction((byte) 10);
                        }
                        vChar.addElement(this.charUse.charFocus);
                    }
                }
                if (this.charUse.isMe()) {
                    SendMessage.gI().sendPlayerAttack(vMob, vChar, this.skill.template.id);
                }
            }
            this.timeDelayRemove = mSystem.currentTimeMillis() + ((long) TIME_DELAY_REMOVE);
            this.isDoneX = false;
            this.isDoneY = false;
            this.dirLR = 0;
            this.dirUD = 0;
        }
        long currentTimeMillis = mSystem.currentTimeMillis();
        long j = this.timeDelayRemove;
        if (currentTimeMillis > j && j != 0) {
            this.timeDelayRemove = 0;
            remove();
        }
    }

    private void remove() {
        this.img = null;
        this.frame = null;
        this.isRemove = true;
    }

    private void updateFrame() {
        if (this.frame != null && CanvasNinja.gameTick % 1 == 0) {
            int i = this.indexFrame + 1;
            this.indexFrame = i;
            if (i > this.numMaxFrame) {
                this.indexFrame = 0;
            }
        }
    }

    public void update() {
        updateFrame();
        updateMoveFrame();
    }

    public void paint(mGraphics g) {
        FrameImage frameImage = this.frame;
        if (frameImage != null) {
            int i = this.dirTemp;
            frameImage.drawFrame(this.indexFrame, (float) (this.x + (i == 3 ? -((int) frameImage.frameWidth) : 0)), (float) this.y, i == 3 ? 0 : 2, g);
        }
    }
}
