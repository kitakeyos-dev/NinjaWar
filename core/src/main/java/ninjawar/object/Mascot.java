package ninjawar.object;

import ninjawar.model.Skill;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.myscr.MyTile;
import ninjawar.myscr.Player;
import ninjawar.myscr.Quai;
import ninjawar.template.QuaiTemplate;

public class Mascot extends Quai {
    int dis;
    int disMc = 16;
    public int index;
    public boolean isRemove;
    long lastTimeAtk;
    int speed;
    public long timeShow;
    public int timeShowInt;

    public Mascot() {
    }

    public Mascot(Player myBoss, int sys, int templateId, int hp, int level, int pointx, int pointy, int maxHP, int timeShow2, int index2, Skill skill) {
        init(myBoss, sys, templateId, hp, level, pointx, pointy, maxHP, timeShow2, index2, skill);
    }

    public Mascot clones() {
        return new Mascot(this.myBoss, this.sys, this.templateId, this.hp, this.level, this.x, this.y, this.maxHp, this.timeShowInt, this.index, this.skill);
    }

    private void init(Player myBoss, int sys, int templateId, int hp, int level, int pointx, int pointy, int maxHP, int timeShow2, int index2, Skill skill) {
        this.index = index2;
        this.timeShowInt = timeShow2;
        this.timeShow = timeShow2 != -1 ? mSystem.currentTimeMillis() + ((long) timeShow2) : 0;
        this.sys = sys;
        this.mobId = myBoss.charID;
        this.templateId = templateId;
        this.hp = hp;
        this.level = (byte) level;
        short s = (short) pointx;
        this.pointx = s;
        this.x = s;
        this.xFirst = s;
        short s2 = (short) pointy;
        this.pointy = s2;
        this.y = s2;
        this.yFirst = s2;
        updateHe();
        this.mobTemplate = QuaiTemplate.get(templateId);
        this.maxHp = maxHP;
        updateHp_bar();
        this.isDie = false;
        this.xSd = this.x;
        this.ySd = this.y;
        this.myBoss = myBoss;
        this.isMobMe = true;
        int i = this.y;
        while (true) {
            if (i > MyTile.size * MyTile.tmh) {
                break;
            } else if (MyTile.tileTypeAt(this.x, i, 2)) {
                this.yTop = MyTile.tileYofPixel(i);
                break;
            } else {
                i += MyTile.size;
            }
        }
        this.dis = myBoss.cw;
        this.speed = (myBoss.cspeed * 3) / 4;
    }

    public void paint(mGraphics g) {
        super.paint(g);
    }

    public void update() {
        super.update();
        followMyBoss();
        if (this.timeShow != 0 && mSystem.currentTimeMillis() >= this.timeShow) {
            this.isRemove = true;
        }
    }

    private void followMyBoss() {
        Player player = this.myBoss;
        if (player.skillCalling != null) {
            this.disMc = 2;
            Player player2 = player.charFocus;
            if (player2 != null) {
                int i = player2.cw / 2;
                this.dis = i;
                int i2 = player2.cx;
                int i3 = this.dir;
                this.xFirst = (i2 - (i * i3)) - ((i3 * this.index) * 2);
                this.yFirst = player2.cy;
                updateXY();
                if (this.x == this.xFirst && this.y == this.yFirst && mSystem.currentTimeMillis() >= this.lastTimeAtk) {
                    this.lastTimeAtk = mSystem.currentTimeMillis() + 1000;
                    setAttack(this.myBoss.charFocus);
                }
                this.dir = this.myBoss.cdir;
                return;
            }
            Quai quai = player.mobFocus;
            if (quai != null) {
                int i4 = quai.w / 2;
                this.dis = i4;
                int i5 = quai.x;
                int i6 = this.dir;
                this.xFirst = (i5 - (i4 * i6)) - ((i6 * this.index) * 2);
                this.yFirst = quai.y;
                updateXY();
                if (this.x == this.xFirst && this.y == this.yFirst && this.action != 4 && mSystem.currentTimeMillis() >= this.lastTimeAtk) {
                    this.lastTimeAtk = mSystem.currentTimeMillis() + 1000;
                    setAttack(this.myBoss.mobFocus);
                }
                this.dir = this.myBoss.cdir;
                return;
            }
        }
        this.disMc = 16;
        int i7 = player.cw;
        this.dis = i7;
        this.xFirst = player.cdir == 1 ? (player.cx - i7) - (this.index * 16) : player.cx + i7 + (this.index * 16);
        this.yFirst = player.cy;
        updateXY();
        if (this.x == this.xFirst && this.y == this.yFirst) {
            this.action = 0;
            this.dir = this.myBoss.cdir;
        }
    }

    private void updateXY() {
        int i = this.x;
        int i2 = this.xFirst;
        if (i < i2) {
            this.dir = 1;
            int i3 = i + this.speed;
            this.x = i3;
            this.action = 1;
            if (i3 > i2) {
                this.x = i2;
                this.status = 0;
            }
        }
        int i4 = this.x;
        if (i4 > i2) {
            this.dir = -1;
            int i5 = i4 - this.speed;
            this.x = i5;
            this.action = 1;
            if (i5 < i2) {
                this.x = i2;
                this.action = 0;
            }
        }
        int i6 = this.y;
        int i7 = this.yFirst;
        if (i6 < i7) {
            int i8 = i6 + this.speed;
            this.y = i8;
            this.action = 1;
            if (i8 > i7) {
                this.y = i7;
                this.action = 0;
            }
        }
        int i9 = this.y;
        if (i9 > i7) {
            int i10 = i9 - this.speed;
            this.y = i10;
            this.action = 1;
            if (i10 < i7) {
                this.y = i7;
                this.action = 0;
            }
        }
    }
}
