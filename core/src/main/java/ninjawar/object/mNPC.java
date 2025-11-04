// Class Version: 8
package ninjawar.object;

import ninjawar.mymain.NinjaMidlet;
import java.io.PrintStream;
import ninjawar.myscr.Res;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mylib.FrameImage;
import ninjawar.supporter.LoadDataManager;
import ninjawar.model.Popup;
import ninjawar.mylib.mSystem;
import ninjawar.mylib.FrameAtlas;
import ninjawar.mylib.mGraphics;
import ninjawar.myscr.Player;
import ninjawar.myscr.MyTile;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ninjawar.template.NPCHashTables;
import java.util.Vector;
import ninjawar.template.TemplateNPC;
import ninjawar.model.mPoint;
import ninjawar.mylib.mFont;

public class mNPC extends Base
{
    public static mFont fontPaintName;
    public static mFont fontPaintNameFocus;
    public static long timeStartChatRandom;
    public byte action;
    public int avatar;
    public int cHP;
    public int cxFirst;
    public int cyFirst;
    public int dir;
    public int frameNPC;
    int indexTemp;
    public int indexTypeStatus;
    public boolean isShadown;
    public String nameNPC;
    public int npcId;
    mPoint pTo;
    public int sp;
    int speed;
    int speedY;
    public byte statusMe;
    public TemplateNPC template;
    public int templateId;
    public byte typeStatus;
    public Vector<mPoint> vecMoveTo;
    public int xSd;
    public int xTo;
    public int ySd;
    public int yTo;

    static {
        mNPC.fontPaintName = mFont.font_NPC_name;
        mNPC.fontPaintNameFocus = mFont.tahoma_7b_red_border_black;
    }

    public mNPC(final int n, final int n2, final int n3) {
        this.vecMoveTo = new Vector<mPoint>();
        this.action = 0;
        this.frameNPC = 1;
        this.typeStatus = -1;
        this.nameNPC = "";
        this.init(0, 0, n2, n3, n, 0);
    }

    public mNPC(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.vecMoveTo = new Vector<mPoint>();
        this.action = 0;
        this.frameNPC = 1;
        this.typeStatus = -1;
        this.nameNPC = "";
        this.init(n, n2, n3, n4, n5, n6);
    }

    private void init(int ch, int n, final int n2, final int n3, final int templateId, final int avatar) {
        this.isShadown = true;
        this.npcId = ch;
        this.avatar = avatar;
        this.cxFirst = n2;
        super.cx = n2;
        this.cyFirst = n3;
        super.cy = n3;
        this.xTo = n2;
        this.yTo = n3;
        this.pTo = new mPoint(n2, n3);
        this.xSd = n2;
        this.ySd = n3;
        this.statusMe = (byte)n;
        this.templateId = templateId;
        if (ch != -1) {
            this.template = NPCHashTables.get(templateId);
        }
        final TemplateNPC template = this.template;
        if (template != null) {
            final TemplateNPC clones = template.clones();
            this.template = clones;
            final Sprite sprite = clones.frameImg.sps[1];
            n = 0;
            if (sprite != null) {
                ch = sprite.getRegionWidth();
            }
            else {
                ch = 0;
            }
            super.cw = ch;
            final Sprite sprite2 = this.template.frameImg.sps[1];
            ch = n;
            if (sprite2 != null) {
                ch = sprite2.getRegionHeight();
            }
            super.ch = ch;
            final TemplateNPC template2 = this.template;
            if (template2.name == null) {
                template2.name = "";
            }
        }
    }

    private void updateShadow() {
        final int cx = super.cx;
        this.xSd = cx;
        final int cy = super.cy;
        this.ySd = cy;
        if (cy <= 0) {
            return;
        }
        if (MyTile.tileTypeAt(cx, cy, 2)) {
            return;
        }
        int ySd;
        if (this.action == 0) {
            ySd = super.cy;
        }
        else {
            ySd = MyTile.findYTileTop(this.xSd, this.ySd);
        }
        this.ySd = ySd;
    }

    public boolean isFocus() {
        return this.equals(Player.myCharz().npcFocus);
    }

    public boolean isInTop(final int n, final int n2) {
        return MyTile.tileTypeAt(n, n2, 2) || (n == this.cxFirst && n2 == this.cyFirst);
    }

    public void moveTo(final int xTo, final int yTo) {
        this.xTo = xTo;
        this.yTo = yTo;
        this.pTo = new mPoint(xTo, yTo);
    }

    public void paint(final mGraphics mGraphics) {
        if (Player.myCharz().isGetSharigan) {
            return;
        }
        this.paintShadow(mGraphics);
        final TemplateNPC template = this.template;
        if (template != null) {
            final FrameAtlas frameImg = template.frameImg;
            if (frameImg != null) {
                final int frameNPC = this.frameNPC;
                final int cx = super.cx;
                final int cw = super.cw;
                int n = 2;
                final float n2 = (float)(cx - cw / 2);
                final float n3 = (float)(super.cy - super.ch);
                if (this.dir != -1) {
                    n = 0;
                }
                frameImg.drawFrame(frameNPC, n2, n3, n, 0, mGraphics);
                this.paintTypeStatus(mGraphics);
            }
        }
        this.paintName(mGraphics);
        this.paintFocus(mGraphics);
    }

    public void paintFocus(final mGraphics mGraphics) {
    }

    public void paintNPC(final mGraphics mGraphics, final int n, final int n2, final boolean b, final boolean b2) {
        if (b2) {
            this.paintShadow(mGraphics);
        }
        final TemplateNPC template = this.template;
        if (template != null) {
            final FrameAtlas frameImg = template.frameImg;
            if (frameImg != null) {
                frameImg.drawFrame(0, (float)n, n2 - frameImg.frameHeight, 0, 0, mGraphics);
            }
        }
        if (b2) {
            this.paintName(mGraphics);
        }
    }

    public void paintName(final mGraphics mGraphics) {
        final TemplateNPC template = this.template;
        String name;
        if (template != null) {
            name = template.name;
        }
        else {
            name = "";
        }
        this.nameNPC = name;
        if (mSystem.isTest) {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.template.name);
            sb.append("_id:");
            sb.append(this.template.npcTemplateId);
            this.nameNPC = sb.toString();
        }
        if (this.isFocus()) {
            mNPC.fontPaintNameFocus.drawString(mGraphics, this.nameNPC, super.cx, super.cy - super.ch - 5 - mNPC.fontPaintName.getHeight(), 2);
        }
        else {
            final mFont fontPaintName = mNPC.fontPaintName;
            fontPaintName.drawString(mGraphics, this.nameNPC, super.cx, super.cy - super.ch - 5 - fontPaintName.getHeight(), 2);
        }
    }

    public void paintPopup(final mGraphics mGraphics) {
        final Popup popup = super.popup;
        if (popup != null) {
            popup.paint(mGraphics, super.cx, super.cy - super.ch - 5 - mNPC.fontPaintName.getHeight());
        }
    }

    public void paintShadow(final mGraphics mGraphics) {
        mGraphics.drawImage(MyTile.shadow, (float)this.xSd, (float)(this.ySd - 3), 3);
    }

    public void paintTypeStatus(final mGraphics mGraphics) {
        final byte typeStatus = this.typeStatus;
        if (typeStatus != -1) {
            final FrameImage frameImage = LoadDataManager.frameTypeNPCs[typeStatus];
            frameImage.drawFrame(this.indexTypeStatus, super.cx - frameImage.frameWidth / 2.0f, super.cy - this.template.frameImg.frameHeight - 5.0f - 5.0f - mNPC.fontPaintName.getHeight() - LoadDataManager.frameTypeNPCs[this.typeStatus].frameHeight, mGraphics);
        }
    }

    public void setTypeStatus(final int n) {
        this.typeStatus = (byte)n;
    }

    public void startPopup(final String s, final int n) {
        (super.popup = new Popup()).startPopup(s, n);
    }

    public void update() {
        this.updateAction();
        this.updateAutoMove();
        final byte typeStatus = this.typeStatus;
        if (typeStatus != -1 && CanvasNinja.gameTick % 3 == 0 && ++this.indexTypeStatus > LoadDataManager.frameTypeNPCs[typeStatus].nFrame - 1) {
            this.indexTypeStatus = 0;
        }
        this.updatePopup();
        this.updateNPCTalkRandom();
        this.updateShadow();
    }

    public void updateAction() {
        final TemplateNPC template = this.template;
        if (template != null) {
            final int[][] frame = template.FRAME;
            if (frame != null) {
                if (CanvasNinja.gameTick % 5 == 0) {
                    final int indexTemp = this.indexTemp + 1;
                    this.indexTemp = indexTemp;
                    final int[] array = frame[this.action];
                    if (indexTemp > array.length - 1) {
                        this.indexTemp = array[0];
                    }
                    this.frameNPC = array[this.indexTemp];
                }
            }
            else {
                final FrameAtlas frameImg = template.frameImg;
                if (frameImg != null && CanvasNinja.gameTick % 5 == 0 && ++this.frameNPC > frameImg.nFrame - 1) {
                    this.frameNPC = 1;
                }
            }
        }
    }

    public void updateAutoMove() {
        final TemplateNPC template = this.template;
        if (template != null) {
            final short rangeMove = template.rangeMove;
            if (rangeMove > 0) {
                if (template.speed > 0) {
                    final int cxFirst = this.cxFirst;
                    final int n = cxFirst - rangeMove;
                    final int n2 = cxFirst + rangeMove;
                    final int cx = super.cx;
                    if (cx == this.xTo && super.cy == this.yTo && this.action == 0) {
                        if (cx <= n) {
                            this.moveTo(n2, this.cyFirst);
                            this.action = 1;
                        }
                        else if (cx >= n2) {
                            this.moveTo(n, this.cyFirst);
                            this.action = 1;
                        }
                        else {
                            this.moveTo(n2, this.cyFirst);
                            this.action = 1;
                        }
                    }
                    final byte action = this.action;
                    if ((action == 0 || action == 1) && !this.isInTop(super.cx, super.cy)) {
                        this.action = 3;
                    }
                    if (this.action == 1) {
                        this.speed = this.template.speed;
                        int sp;
                        if (Res.abs(super.cx - this.xTo) < (sp = this.speed)) {
                            sp = Res.abs(super.cx - this.xTo);
                        }
                        this.sp = sp;
                        final int cx2 = super.cx;
                        final int xTo = this.xTo;
                        if (cx2 >= xTo) {
                            sp = -sp;
                        }
                        final int cx3 = cx2 + sp;
                        super.cx = cx3;
                        if (cx3 < xTo) {
                            this.dir = -1;
                        }
                        else if (cx3 > xTo) {
                            this.dir = 1;
                        }
                        if (Res.abs(cx3 - xTo) <= 1) {
                            super.cx = this.xTo;
                            this.action = 0;
                        }
                    }
                    if (this.action == 2) {
                        final int speedY = -this.template.speedY;
                        this.speedY = speedY;
                        if ((super.cy += speedY) < this.yTo) {
                            this.speedY = 0;
                            this.action = 3;
                        }
                    }
                    if (this.action == 3) {
                        final int cvy = super.cvy + 1;
                        super.cvy = cvy;
                        final int cy = super.cy + cvy;
                        super.cy = cy;
                        final int speedY2 = this.template.speedY;
                        if (cvy >= speedY2) {
                            super.cvy = speedY2;
                        }
                        if (this.isInTop(super.cx, cy)) {
                            super.cvy = 0;
                            super.cy = MyTile.tileXofPixel(super.cy);
                            this.action = 0;
                        }
                    }
                    final PrintStream out = System.out;
                    final StringBuilder sb = new StringBuilder();
                    sb.append("cx: ");
                    sb.append(super.cx);
                    sb.append(", xTo: ");
                    sb.append(this.xTo);
                    sb.append(", action: ");
                    sb.append(this.action);
                    out.println(sb.toString());
                    return;
                }
            }
        }
        this.action = 0;
    }

    public void updateNPCTalkRandom() {
        final TemplateNPC template = this.template;
        if (template != null && super.popup == null && template.getTalkRandom() != null && NinjaMidlet.timeSystems >= mNPC.timeStartChatRandom && Res.distance(Player.myCharz().cx, Player.myCharz().cy, super.cx, super.cy) < 50) {
            this.startPopup(this.template.getTalkRandom(), 3000);
            mNPC.timeStartChatRandom = mSystem.currentTimeMillis() + 10000L;
        }
    }

    public void updatePopup() {
        final Popup popup = super.popup;
        if (popup != null && popup.timeShow != 0L) {
            final long currentTimeMillis = mSystem.currentTimeMillis();
            final Popup popup2 = super.popup;
            if (currentTimeMillis > popup2.timeShow) {
                popup2.timeShow = 0L;
                popup2.isPaint = false;
                super.popup = null;
            }
        }
    }
}
