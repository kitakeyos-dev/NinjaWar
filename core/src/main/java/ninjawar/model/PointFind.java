package ninjawar.model;

import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.mGraphics;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.MyTile;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportTranslate;

public class PointFind {
    public FrameImage[] frameFind;
    public Image[] imgFind;
    public boolean isDone;
    public String nameFind = "";
    public int x;
    int xAnim;
    public int y;
    int yAnim;

    public PointFind(int x2, int y2, String nameFind2) {
        this.x = x2;
        this.y = y2;
        this.nameFind = nameFind2;
        this.imgFind = LoadDataManager.imgPointerFindMap;
        this.frameFind = LoadDataManager.framePointerFindMap;
    }

    public void paint(mGraphics g) {
        if (isLeft()) {
            if (isUp()) {
                this.frameFind[4].drawFrame(this.xAnim, (float) ((((Player.myCharz().cx - Player.myCharz().cw) - 5) - this.frameFind[4].getRWidth()) - MapScr.cmx), (float) ((Player.myCharz().cy - ((Player.myCharz().ch * 3) / 4)) - MapScr.cmy), g);
            } else if (isDown()) {
                this.frameFind[5].drawFrame(this.xAnim, (float) ((((Player.myCharz().cx - Player.myCharz().cw) - 5) - this.frameFind[5].getRWidth()) - MapScr.cmx), (float) ((Player.myCharz().cy - (Player.myCharz().ch / 4)) - MapScr.cmy), g);
            } else {
                this.frameFind[2].drawFrame(this.xAnim, (float) ((((Player.myCharz().cx - Player.myCharz().cw) - 5) - this.frameFind[2].getRWidth()) - MapScr.cmx), (float) ((Player.myCharz().cy - (Player.myCharz().ch / 2)) - MapScr.cmy), g);
            }
        }
        if (isRight()) {
            if (isUp()) {
                this.frameFind[6].drawFrame(this.xAnim, (float) ((((Player.myCharz().cx + Player.myCharz().cw) + 5) + this.frameFind[2].getRWidth()) - MapScr.cmx), (float) ((Player.myCharz().cy - ((Player.myCharz().ch * 3) / 4)) - MapScr.cmy), g);
            } else if (isDown()) {
                this.frameFind[7].drawFrame(this.xAnim, (float) ((((Player.myCharz().cx + Player.myCharz().cw) + 5) + this.frameFind[2].getRWidth()) - MapScr.cmx), (float) ((Player.myCharz().cy - (Player.myCharz().ch / 4)) - MapScr.cmy), g);
            } else {
                this.frameFind[3].drawFrame(this.xAnim, (float) ((((Player.myCharz().cx + Player.myCharz().cw) + 5) + this.frameFind[2].getRWidth()) - MapScr.cmx), (float) ((Player.myCharz().cy - (Player.myCharz().ch / 2)) - MapScr.cmy), g);
            }
        }
        if (isUp() && !isLeft() && !isRight()) {
            this.frameFind[1].drawFrame(this.xAnim, (float) ((Player.myCharz().cx - (this.imgFind[2].getRWidth() / 2)) - MapScr.cmx), (float) ((((Player.myCharz().cy - 5) - Player.myCharz().ch) - this.frameFind[1].getRHeight()) - MapScr.cmy), g);
        }
        if (isDown() && !isLeft() && !isRight()) {
            this.frameFind[0].drawFrame(this.xAnim, (float) ((Player.myCharz().cx - (this.frameFind[2].getRWidth() / 2)) - MapScr.cmx), (float) ((Player.myCharz().cy + 5) - MapScr.cmy), g);
        }
    }

    public boolean isLeft() {
        return Player.myCharz().cx > this.x && Res.abs(Player.myCharz().cx - this.x) > MyTile.size * 3;
    }

    public boolean isRight() {
        return Player.myCharz().cx < this.x && Res.abs(Player.myCharz().cx - this.x) > MyTile.size * 3;
    }

    public boolean isUp() {
        return Player.myCharz().cy > this.y && Res.abs(Player.myCharz().cy - this.y) > MyTile.size * 3;
    }

    public boolean isDown() {
        return Player.myCharz().cy < this.y && Res.abs(Player.myCharz().cy - this.y) > MyTile.size * 3;
    }

    public void update() {
        int i = this.xAnim + 1;
        this.xAnim = i;
        if (i > 10) {
            this.xAnim = 0;
        }
        int i2 = this.yAnim + 1;
        this.yAnim = i2;
        if (i2 > 10) {
            this.yAnim = 0;
        }
        if (!this.isDone && !isLeft() && !isRight() && !isUp() && !isDown()) {
            this.isDone = true;
        }
    }

    public String getContentDone() {
        if (this.nameFind.equals("")) {
            return null;
        }
        return SupportTranslate.getTextLangue("findSuccess") + " " + this.nameFind;
    }
}
