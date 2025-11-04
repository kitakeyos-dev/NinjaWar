package ninjawar.screen.objscr;

import ninjawar.message.SendMessage;
import ninjawar.model.InteractObjModel;
import ninjawar.model.ItemInMap;
import ninjawar.mylib.ObjMap;
import ninjawar.mylib.VectorCustom;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.IMapObject;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.MyTile;
import ninjawar.myscr.Player;
import ninjawar.myscr.Quai;
import ninjawar.myscr.Res;
import ninjawar.object.mNPC;
import ninjawar.scroll.ScrollY;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportTranslate;
import ninjawar.template.ItemTemplate;
import ninjawar.template.QuaiTemplate;
import ninjawar.template.TemplateNPC;

public class InteractObjPaint {
    public static mFont fontPaintTagName = mFont.tahoma_7_white_11;
    public int[] arrH;
    public int[] arrW;
    public int[] arrX;
    public int[] arrY;
    public int charIdFocus;
    public int h;
    int hClip;
    public InteractObjModel[] interactObjModels;
    public boolean isClose = true;
    boolean[] isHover;
    public boolean isMenuPlayer = false;
    public int margin;
    public String nameFocus = "";
    public int numMaxPaint = 3;
    ScrollY scrollY = new ScrollY();
    public int w;
    public int x;
    int xMore = 0;
    public int y;

    public void updateNameFocus(VectorCustom vectorObj) {
        int i;
        ItemTemplate itemTemplate;
        VectorCustom vectorCustom = vectorObj;
        this.isMenuPlayer = false;
        int size = vectorCustom != null ? vectorObj.size() : 0;
        if (size == 0) {
            this.interactObjModels = null;
            return;
        }
        this.interactObjModels = new InteractObjModel[size];
        this.isHover = new boolean[size];
        for (int i2 = 0; i2 < size; i2++) {
            IMapObject obj = (IMapObject) vectorCustom.get(i2);
            this.interactObjModels[i2] = new InteractObjModel();
            InteractObjModel interactObjModel = this.interactObjModels[i2];
            interactObjModel.obj = obj;
            if (obj instanceof mNPC) {
                TemplateNPC templateNPC = ((mNPC) obj).template;
                if (templateNPC != null) {
                    interactObjModel.name = templateNPC.name;
                }
                interactObjModel.type = 1;
            }
            if (obj instanceof Quai) {
                QuaiTemplate quaiTemplate = ((Quai) obj).mobTemplate;
                if (quaiTemplate != null) {
                    interactObjModel.name = quaiTemplate.name;
                }
                interactObjModel.type = 2;
            }
            if (obj instanceof Player) {
                interactObjModel.name = ((Player) obj).cName;
                interactObjModel.type = 3;
            }
            if ((obj instanceof ObjMap) && ((ObjMap) obj).idImage == 2000) {
                interactObjModel.name = SupportTranslate.getTextLangue("zone") + " " + (MyTile.zoneID + 1);
                this.interactObjModels[i2].type = 4;
            }
            if ((obj instanceof ItemInMap) && (itemTemplate = ((ItemInMap) obj).template) != null) {
                InteractObjModel interactObjModel2 = this.interactObjModels[i2];
                interactObjModel2.name = itemTemplate.name;
                interactObjModel2.type = 0;
            }
        }
        int width = (this.margin * 4) + fontPaintTagName.getWidth(Res.findStringMax(this.interactObjModels));
        this.w = width;
        if (width < LoadDataManager.tagNameInteract.getRWidth()) {
            this.w = (this.margin * 2) + LoadDataManager.tagNameInteract.getRWidth();
        }
        int hTagName = LoadDataManager.tagNameInteract.getRHeight();
        InteractObjModel[] interactObjModelArr = this.interactObjModels;
        if (interactObjModelArr.length > 1) {
            int i3 = this.margin;
            i = ((hTagName + i3) * interactObjModelArr.length) + i3;
        } else {
            i = interactObjModelArr.length * (this.margin + hTagName);
        }
        this.h = i;
        int h1 = interactObjModelArr.length > 1 ? this.margin + hTagName : hTagName;
        int i4 = (this.numMaxPaint * h1) + (h1 / 2);
        this.hClip = i4;
        this.arrH = new int[]{hTagName, h1, i4};
        int i5 = this.w;
        this.arrW = new int[]{i5, 0, CanvasNinja.w};
        int i6 = this.x;
        this.arrX = new int[]{i6, (i5 / 2) + i6, i6};
        int i7 = this.y;
        int i8 = this.y;
        this.arrY = new int[]{i7, i7 + ((hTagName - fontPaintTagName.getHeight()) / 2), i8};
        InteractObjModel[] interactObjModelArr2 = this.interactObjModels;
        int maxPaint = interactObjModelArr2.length * h1;
        if (interactObjModelArr2.length > this.numMaxPaint) {
            this.scrollY.initScroll(this.arrX[2], i8, this.arrW[2], this.arrH[2], maxPaint, this.hClip);
        } else {
            this.scrollY = new ScrollY();
        }
    }

    public InteractObjPaint(int x2, int y2) {
        this.x = x2;
        this.y = y2;
        this.margin = 5;
    }

    public void updatePointer() {
        InteractObjModel[] interactObjModelArr = this.interactObjModels;
        if (interactObjModelArr != null && interactObjModelArr.length > 0) {
            for (int i = 0; i < this.interactObjModels.length; i++) {
                if (!this.isMenuPlayer || i == 0) {
                    this.xMore = 0;
                } else {
                    this.xMore = 20;
                }
                if (CanvasNinja.isPointerRelease()) {
                    int i2 = this.arrX[0] + this.xMore;
                    int i3 = this.arrY[0];
                    int[] iArr = this.arrH;
                    if (CanvasNinja.isPoint(i2, (i3 + (iArr[1] * i)) - this.scrollY.cmy, this.arrW[0], iArr[0])) {
                        CanvasNinja.clearAllPointer();
                        Res.outz(1, "Click focus:" + i);
                        if (!this.isMenuPlayer) {
                            this.nameFocus = this.interactObjModels[i].name;
                            Player.myCharz().focusManualTo(this.interactObjModels[i].obj, false);
                            updateNameFocus(MapScr.vecFocus);
                            InteractObjModel[] interactObjModelArr2 = this.interactObjModels;
                            if (interactObjModelArr2 != null && i < interactObjModelArr2.length) {
                                if (interactObjModelArr2[i].obj instanceof mNPC) {
                                    Res.outz("click type NPC");
                                    MapScr.cmdChatNpc.perform();
                                }
                                IMapObject iMapObject = this.interactObjModels[i].obj;
                                boolean z = iMapObject instanceof Quai;
                                if (iMapObject instanceof ObjMap) {
                                    MapScr.cmdChatBgItem.perform();
                                }
                                if (this.interactObjModels[i].obj instanceof ItemInMap) {
                                    MapScr.cmdChatItem.perform();
                                }
                                IMapObject iMapObject2 = this.interactObjModels[i].obj;
                                if (iMapObject2 instanceof Player) {
                                    this.nameFocus = ((Player) iMapObject2).cName;
                                    Res.outz("click type Player");
                                    MapScr.cmdChatPlayer.perform();
                                    this.charIdFocus = ((Player) this.interactObjModels[i].obj).charID;
                                    Player.myCharz().focusManualTo(this.interactObjModels[i].obj, false);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        if (!(this.interactObjModels[i].indexIcon == -1 || Player.myCharz().charFocus == null)) {
                            SendMessage.gI().requestMenuPvP(Player.myCharz().charFocus.charID, this.interactObjModels[i].indexIcon);
                        }
                        CanvasNinja.clearAllPointer();
                        this.isMenuPlayer = false;
                        updateNameFocus(MapScr.vecFocus);
                        return;
                    }
                }
            }
        }
    }

    public void selectMenuPlayer(InteractObjModel[] interactObjModels2) {
        int i;
        InteractObjModel[] interactObjModelArr = interactObjModels2;
        this.isMenuPlayer = true;
        int size = interactObjModelArr.length;
        this.interactObjModels = interactObjModelArr;
        this.isHover = new boolean[size];
        int width = (this.margin * 4) + fontPaintTagName.getWidth(Res.findStringMax(interactObjModels2));
        this.w = width;
        if (width < LoadDataManager.tagNameInteract.getRWidth()) {
            this.w = (this.margin * 2) + LoadDataManager.tagNameInteract.getRWidth();
        }
        int hTagName = LoadDataManager.tagNameInteract.getRHeight();
        if (interactObjModelArr.length > 1) {
            int i2 = this.margin;
            i = ((hTagName + i2) * interactObjModelArr.length) + i2;
        } else {
            i = interactObjModelArr.length * (this.margin + hTagName);
        }
        this.h = i;
        int h1 = interactObjModelArr.length > 1 ? this.margin + hTagName : hTagName;
        int i3 = (this.numMaxPaint * h1) + (h1 / 2);
        this.hClip = i3;
        this.arrH = new int[]{hTagName, h1, i3};
        int i4 = this.w;
        this.arrW = new int[]{i4, 0, CanvasNinja.w};
        int i5 = this.x;
        this.arrX = new int[]{i5, (i4 / 2) + i5 + 20, i5};
        int i6 = this.y;
        int i7 = this.y;
        this.arrY = new int[]{i6, i6 + ((hTagName - fontPaintTagName.getHeight()) / 2), i7};
        int maxPaint = interactObjModelArr.length * h1;
        if (interactObjModelArr.length > this.numMaxPaint) {
            this.scrollY.initScroll(this.arrX[2], i7, this.arrW[2], this.arrH[2], maxPaint, this.hClip);
        } else {
            this.scrollY = new ScrollY();
        }
    }

    public boolean isClickFocusTagName() {
        int i = this.x;
        int i2 = this.xMore;
        if (CanvasNinja.isPoint(i + i2, this.y, this.w + i2, this.hClip)) {
            return true;
        }
        int i3 = this.x;
        int i4 = this.xMore;
        if (CanvasNinja.isPointFirst(i3 + i4, this.y, this.w + i4, this.hClip)) {
            return true;
        }
        return false;
    }

    public void update() {
        ScrollY scrollY2 = this.scrollY;
        if (scrollY2 != null) {
            scrollY2.update();
        }
        if (Player.myCharz().getAction() != 0) {
            updateNameFocus(MapScr.vecFocus);
        }
        InteractObjModel[] interactObjModelArr = this.interactObjModels;
        if (interactObjModelArr != null && interactObjModelArr.length > 0) {
            for (int i = 0; i < this.interactObjModels.length; i++) {
                int i2 = this.arrX[0] + this.xMore;
                int i3 = this.arrY[0];
                int[] iArr = this.arrH;
                if (CanvasNinja.isPointHover(i2, (i3 + (iArr[1] * i)) - this.scrollY.cmy, this.w, iArr[0])) {
                    this.isHover[i] = true;
                } else {
                    this.isHover[i] = false;
                }
            }
        }
    }

    public void paint(mGraphics g) {
        InteractObjModel[] interactObjModelArr = this.interactObjModels;
        if (interactObjModelArr != null && interactObjModelArr.length > 0) {
            g.setClip(this.arrX[2], this.arrY[2], this.arrW[2], this.arrH[2]);
            for (int i = 0; i < this.interactObjModels.length; i++) {
                if (!this.isMenuPlayer || i == 0) {
                    this.xMore = 0;
                } else {
                    this.xMore = 20;
                }
                g.drawImage(LoadDataManager.tagNameInteract, (float) (this.arrX[0] + this.xMore), (float) ((this.arrY[0] + (this.arrH[1] * i)) - this.scrollY.cmy), true, this.isHover[i] ? 100 : 85);
                fontPaintTagName.drawString(g, this.interactObjModels[i].name, this.arrX[1], (this.arrY[1] + (this.arrH[1] * i)) - this.scrollY.cmy, 2, true);
            }
        }
    }
}
