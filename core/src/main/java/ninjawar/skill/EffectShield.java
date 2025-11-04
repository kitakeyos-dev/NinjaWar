package ninjawar.skill;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Player;
import ninjawar.object.Mascot;

public class EffectShield {
    public Player charClone;
    public Player charHidden;
    public Player charMascot;
    public int dx;
    public int dy;
    public FrameImage frameImage;
    public int h;
    public short idTemplate;
    public int index;
    public boolean isContinueAction = false;
    public boolean isRemove = false;
    boolean isRemoveJustOne = false;
    public boolean isSpecFrame = false;
    public Mascot mascotTemp;
    public int numFrameSpec;
    public long timeBuff;
    public byte typePaint = 0;
    public int w;
    public int x;
    public int y;

    public EffectShield() {
    }

    public EffectShield(int idTemplate2, int timeBuff2) {
        init(idTemplate2, timeBuff2, 0, 0, false, (Player) null, (Player) null, (Player) null, 0, 0);
    }

    public EffectShield(int idTemplate2, int dx2, int dy2) {
        init(idTemplate2, -1, 0, 0, true, (Player) null, (Player) null, (Player) null, dx2, dy2);
    }

    public EffectShield(int idTemplate2, int x2, int y2, boolean isRemoveJustOne2, Player cClone) {
        init(idTemplate2, -1, x2, y2, isRemoveJustOne2, cClone, (Player) null, (Player) null, 0, 0);
    }

    public EffectShield(int idTemplate2, Player charMascot2, int x2, int y2, boolean isRemoveJustOne2) {
        init(idTemplate2, -1, x2, y2, isRemoveJustOne2, (Player) null, (Player) null, charMascot2, 0, 0);
    }

    public void init(int idTemplate2, int timeBuff2, int x2, int y2, boolean isRemoveJustOne2, Player cClone, Player cHidden, Player charMascot2, int dx2, int dy2) {
        Mascot mascot;
        this.dx = dx2;
        this.dy = dy2;
        this.charClone = cClone;
        this.charHidden = cHidden;
        this.charMascot = charMascot2;
        if (!(charMascot2 == null || (mascot = charMascot2.mascotTemp) == null)) {
            this.mascotTemp = mascot.clones();
            charMascot2.mascotTemp = null;
        }
        this.index = 0;
        this.x = x2;
        this.y = y2;
        this.isRemoveJustOne = isRemoveJustOne2;
        this.timeBuff = timeBuff2 == -1 ? -1 : mSystem.currentTimeMillis() + ((long) timeBuff2);
        this.idTemplate = (short) idTemplate2;
        Image img = Image.createImage((short) idTemplate2, (short) 18);
        if (img != null) {
            switch (idTemplate2) {
                case 0:
                    this.frameImage = new FrameImage(img, 4);
                    break;
                case 1:
                    this.frameImage = new FrameImage(img, 12);
                    break;
                case 2:
                    this.isSpecFrame = true;
                    this.numFrameSpec = 1;
                    this.frameImage = new FrameImage(img, 6);
                    break;
                case 3:
                    this.isSpecFrame = true;
                    this.numFrameSpec = 2;
                    this.frameImage = new FrameImage(img, 10);
                    break;
                case 4:
                    this.frameImage = new FrameImage(img, 4);
                    this.typePaint = 1;
                    break;
                case 5:
                    this.frameImage = new FrameImage(img, 15);
                    break;
                case 6:
                    this.isSpecFrame = true;
                    this.numFrameSpec = 1;
                    this.frameImage = new FrameImage(img, 6);
                    break;
                case 9:
                    this.isSpecFrame = true;
                    this.numFrameSpec = 1;
                    this.frameImage = new FrameImage(img, 18);
                    break;
            }
        }
        FrameImage frameImage2 = this.frameImage;
        if (frameImage2 != null) {
            this.w = (int) frameImage2.frameWidth;
            this.h = (int) frameImage2.frameHeight;
        }
    }

    public void update() {
        FrameImage frameImage2 = this.frameImage;
        if (frameImage2 != null) {
            if (CanvasNinja.gameTick % 2 == 0) {
                int i = this.index + 1;
                this.index = i;
                if (i > frameImage2.nFrame - 1) {
                    this.index = 0;
                    if (this.isRemoveJustOne) {
                        this.isRemove = true;
                    }
                }
                if (this.isSpecFrame && this.index > this.numFrameSpec) {
                    this.isContinueAction = true;
                }
            }
            long j = this.timeBuff;
            if (j != -1 && j <= mSystem.currentTimeMillis()) {
                this.isRemove = true;
            }
        }
    }

    public void paint(mGraphics g, int x2, int y2) {
        FrameImage frameImage2;
        if (this.typePaint == 0 && (frameImage2 = this.frameImage) != null) {
            frameImage2.drawFrame(this.index, (float) ((x2 - (this.w / 2)) + this.dx), (float) ((y2 - this.h) + this.dy), g);
        }
    }

    public void paintTopCenter(mGraphics g, int x2, int y2) {
        FrameImage frameImage2;
        if (this.typePaint == 1 && (frameImage2 = this.frameImage) != null) {
            frameImage2.drawFrame(this.index, (float) (x2 - (this.w / 2)), (float) y2, g);
        }
    }

    public void paint(mGraphics g) {
        FrameImage frameImage2 = this.frameImage;
        if (frameImage2 != null) {
            frameImage2.drawFrame(this.index, (float) (this.x - (this.w / 2)), (float) (this.y - this.h), g);
        }
    }
}
