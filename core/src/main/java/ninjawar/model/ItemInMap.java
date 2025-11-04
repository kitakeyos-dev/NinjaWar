package ninjawar.model;

import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.IMapObject;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.MyTile;
import ninjawar.myscr.Player;
import ninjawar.supporter.LoadDataManager;
import ninjawar.template.ItemTemplate;
import ninjawar.template.ItemTemplates;

public class ItemInMap implements IMapObject {
    public boolean isPicked;
    public boolean isRusted;
    public int itemMapID;
    public int playerId;
    public byte status = 1;
    public ItemTemplate template;
    public int vx;
    public int vy;
    public int x;
    public int xEnd;
    public int y;
    int yAnim;
    int yAnimFocus;
    public int yEnd;

    public ItemInMap(int playerId2, short ItemID, short itemTemplateID, int x2, int y2, short r) {
        this.itemMapID = ItemID;
        this.template = ItemTemplates.get(itemTemplateID).clones();
        this.xEnd = x2;
        this.x = x2;
        this.yEnd = y2;
        this.y = y2;
        this.playerId = playerId2;
    }

    public void setPoint(int xEnd2, int yEnd2) {
        this.xEnd = xEnd2;
        this.yEnd = yEnd2;
        this.vx = (xEnd2 - this.x) >> 2;
        this.vy = (yEnd2 - this.y) >> 2;
        this.status = 2;
    }

    public void update() {
        if (CanvasNinja.gameTick % 5 == 0) {
            int i = this.yAnim + 1;
            this.yAnim = i;
            if (i > 1) {
                this.yAnim = 0;
            }
        }
        byte b = this.status;
        if (b == 2 && this.x == this.xEnd && this.y == this.yEnd) {
            MapScr.vItemMap.removeElement(this);
            if (Player.myCharz().itemFocus != null && Player.myCharz().itemFocus.equals(this)) {
                Player.myCharz().itemFocus = null;
            }
        } else if (b > 0) {
            int i2 = this.vx;
            if (i2 == 0) {
                this.x = this.xEnd;
            }
            int i3 = this.vy;
            if (i3 == 0) {
                this.y = this.yEnd;
            }
            int i4 = this.x;
            int i5 = this.xEnd;
            if (i4 != i5) {
                int i6 = i4 + i2;
                this.x = i6;
                if ((i2 > 0 && i6 > i5) || (i2 < 0 && i6 < i5)) {
                    this.x = i5;
                }
            }
            int i7 = this.y;
            int i8 = this.yEnd;
            if (i7 != i8) {
                int i9 = i7 + i3;
                this.y = i9;
                if ((i3 > 0 && i9 > i8) || (i3 < 0 && i9 < i8)) {
                    this.y = i8;
                }
            }
        } else {
            byte b2 = (byte) (b - 4);
            this.status = b2;
            if (b2 < -12) {
                this.y -= 12;
                this.status = 1;
            }
        }
    }

    public void paint(mGraphics g) {
        g.drawImage(MyTile.shadow, (float) (this.x + 3), (float) this.y, 3);
        ItemTemplate itemTemplate = this.template;
        if (itemTemplate != null) {
            if (this.isRusted && itemTemplate.iconID != 350) {
                itemTemplate.iconID = 350;
                itemTemplate.createItemTemplate();
            }
            this.template.paint(g, this.x, this.y + this.yAnim);
            if (Player.myCharz().itemFocus != null && Player.myCharz().itemFocus.equals(this)) {
                if (CanvasNinja.gameTick % 4 == 0) {
                    int i = this.yAnimFocus + 1;
                    this.yAnimFocus = i;
                    if (i > 2) {
                        this.yAnimFocus = 0;
                    }
                }
                paintFocus(g, this.x, ((this.y - getH()) - 10) + this.yAnimFocus);
            }
        }
    }

    private void paintFocus(mGraphics g, int x2, int y2) {
        g.drawImage(LoadDataManager.imgFocus2, (float) (x2 - (LoadDataManager.imgFocus.getRWidth() / 2)), (float) y2);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getH() {
        ItemTemplate itemTemplate = this.template;
        if (itemTemplate != null) {
            return itemTemplate.h;
        }
        return 30;
    }

    public int getW() {
        ItemTemplate itemTemplate = this.template;
        if (itemTemplate != null) {
            return itemTemplate.w;
        }
        return 30;
    }

    public void stopMoving() {
    }

    public boolean isInvisible() {
        return this.isPicked;
    }
}
