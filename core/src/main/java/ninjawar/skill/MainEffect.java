package ninjawar.skill;

import ninjawar.mylib.mGraphics;
import ninjawar.myscr.Player;
import ninjawar.myscr.Quai;

public class MainEffect {
    public Player charTarget;
    public Player charUse;
    public int countUse = 0;
    public int dirLR;
    public int dirTemp;
    public int dirUD;
    public int direct;
    public EffectFrameBuff effectFrameBuff;
    public int id;
    public short idTarget = 2;
    public int[] indexFrameEffect = {-1};
    public boolean isAddSplashWeapon = false;
    public boolean isBigBang;
    public boolean isRemove = false;
    public boolean isRemoveSpec = false;
    public boolean isRemoveWhenDone = false;
    public Quai mobTarget;
    public int speedX;
    public int speedY;
    public int subDirect;
    public long timeBuff = -1;
    public long timeDelayAddSkill;
    public byte type;
    public byte typeBeforeOrAfter;
    public int x;
    public int xTarget;
    public int y;
    public int yTarget;

    public void paint(mGraphics g, int x2, int y2, int frame, byte direct2, byte subDirect2, int opacityPercent, boolean isOpacity, boolean useClip) {
    }

    public void paint(mGraphics g) {
    }

    public void update() {
    }
}
