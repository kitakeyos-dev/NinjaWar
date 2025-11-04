// Class Version: 8
package ninjawar.screen.tab;

import ninjawar.message.SendMessage;
import com.badlogic.gdx.Gdx;
import java.util.Arrays;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import ninjawar.myscr.MyTile;
import ninjawar.mymain.CanvasNinja;
import java.util.Vector;
import ninjawar.input.TField;
import ninjawar.screen.selectbox.SelectBox;
import ninjawar.mylib.Image;

public class TabWorldMap extends TabScr
{
    public static TabWorldMap me;
    private Image currentPosMarker;
    private int dragStartX;
    private int dragStartY;
    int hTitle;
    boolean hienMarker;
    public short idBanDo;
    public int indexSelected;
    private boolean isDragging;
    private int lastXMapCenter;
    private int lastYMapCenter;
    private Image mapImg;
    int marginLeftRight;
    int marginOneTab;
    private Image[] markerType;
    private SelectBox selectBox;
    private Image selectedPosMarker;
    TField[] tf;
    String title;
    public Vector<MapMarker> vtMapMarker;
    int wTitle;
    int xMapCenter;
    int xTitle;
    int yMapCenter;
    int yTitle;

    public TabWorldMap() {
        this.indexSelected = -1;
        this.title = "";
        this.hienMarker = false;
        this.idBanDo = 20000;
        this.isDragging = false;
    }

    public static TabWorldMap gI() {
        if (TabWorldMap.me == null) {
            TabWorldMap.me = new TabWorldMap();
        }
        return TabWorldMap.me;
    }

    @Override
    public void commandTab(final int n, final int n2) {
        super.commandTab(n, n2);
    }

    public void init() {
        this.selectBox = null;
        super.w = 526;
        final int w = CanvasNinja.w;
        if (526 > w) {
            super.w = w - super.margin * 5;
        }
        super.h = 310;
        final int h = super.h;
        final int h2 = CanvasNinja.h;
        if (h > h2) {
            super.h = h2 - super.margin * 2;
        }
        this.initStart();
        super.margin = 5;
        this.marginLeftRight = 5 * 2;
        this.mapImg = Image.createImage(this.idBanDo, (short)27);
        this.markerType = new Image[9];
        for (int i = 0; i < 9; ++i) {
            this.markerType[i] = Image.createImage((short)i, (short)27);
        }
        this.currentPosMarker = Image.createImage((short)10001, (short)27);
        this.selectedPosMarker = Image.createImage((short)10000, (short)27);
        for (int j = 0; j < this.vtMapMarker.size(); ++j) {
            if (this.vtMapMarker.get(j).mapId == MyTile.mapID) {
                this.xMapCenter = this.vtMapMarker.get(j).x_pos;
                this.yMapCenter = this.vtMapMarker.get(j).y_pos;
            }
        }
        final String upperCase = "bản đồ".toUpperCase();
        this.title = upperCase;
        this.hTitle = (int)LoadDataManager.frameTitle.frameHeight;
        final int wTitle = TabScr.fontPaintTile.getWidth(upperCase) + super.margin * 8;
        this.wTitle = wTitle;
        final int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(8, wTitle, LoadDataManager.frameTitle);
        this.wTitle = fixSizeTagFrameUp;
        final int x = super.x;
        this.xTitle = (super.w - fixSizeTagFrameUp) / 2 + x;
        final int y = super.y;
        this.yTitle = y - this.hTitle / 2;
        final int n = x + 7 + this.marginLeftRight;
        super.arrX = new int[] { n, this.marginOneTab + n };
        final int n2 = (int)LoadDataManager.frameTitle2.frameHeight / 2;
        final int margin = super.margin;
        final int n3 = y + 7 + n2 + margin * 2;
        super.arrY = new int[] { n3, margin * 2 + n3 };
        this.tf = new TField[4];
        for (int k = 0; k < 4; ++k) {
            this.tf[k] = new TField();
            TField.resetTField();
            final TField tField = this.tf[k];
            tField.isSendChat = false;
            if (!NinjaMidlet.isUseIOSSpec) {
                tField.isKeyTyped = true;
            }
            tField.width = 90;
            tField.height = (int)LoadDataManager.frameInputTf.frameHeight;
            tField.setMaxTextLenght(3);
            this.tf[k].setFocus(false);
            this.tf[k].setIputType(1);
        }
    }

    @Override
    public void keyPress(final int n) {
        for (int i = 0; i < 4; ++i) {
            if (this.tf[i].isFocused()) {
                final TField tField = this.tf[i];
                tField.isChat = false;
                tField.keyPressed(n);
                return;
            }
        }
    }

    @Override
    public void keyTyped() {
        for (int i = 0; i < 4; ++i) {
            if (this.tf[i].isFocused()) {
                this.tf[i].keyTyped();
                return;
            }
        }
    }

    @Override
    public void paint(final mGraphics mGraphics) {
        CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameBgOrange2_0, (float)super.x, (float)super.y, (float)super.w, (float)super.h, 0, false);
        mGraphics.setClip(super.x + 5, super.y + 5, super.w - 10, super.h - 10);
        final int rWidth = this.mapImg.getRWidth();
        final int rHeight = this.mapImg.getRHeight();
        final int n = super.w - 10;
        final int n2 = super.h - 10;
        final int x = super.x;
        final int n3 = x + 5 - (this.xMapCenter - n / 2);
        final int y = super.y;
        final int n4 = y + 5 - (this.yMapCenter - n2 / 2);
        int n5 = n3;
        if (n3 > x + 5) {
            n5 = x + 5;
        }
        int n6;
        if (n5 < x + 5 - (rWidth - n)) {
            n6 = x + 5 - (rWidth - n);
        }
        else {
            n6 = n5;
        }
        int n7 = n4;
        if (n4 > y + 5) {
            n7 = y + 5;
        }
        if (n7 < y + 5 - (rHeight - n2)) {
            n7 = y + 5 - (rHeight - n2);
        }
        final Image mapImg = this.mapImg;
        final int n8 = 1;
        mGraphics.drawImage(mapImg, n6, n7, true);
        int i = 0;
        final short n9 = (short)n7;
        int n10 = n8;
        while (i < this.vtMapMarker.size()) {
            final MapMarker mapMarker = this.vtMapMarker.get(i);
            final int n11 = mapMarker.x_pos + n6 - this.markerType[mapMarker.type].getRWidth() / 2;
            final int n12 = mapMarker.y_pos + n9 - this.markerType[mapMarker.type].getRHeight() / 2;
            final String processMapName = this.processMapName(this.vtMapMarker.get(i).mapName);
            final int width = mFont.fontPaintMapNameWhite.getWidth(processMapName);
            final int heightH = mFont.fontPaintMapNameWhite.getHeightH(processMapName);
            int n13 = 0;
            int n14 = 0;
            switch (i % 4) {
                default: {
                    n13 = n11;
                    n14 = n12;
                    break;
                }
                case 3: {
                    n13 = n11 - (width + 5);
                    n14 = n12;
                    break;
                }
                case 2: {
                    n13 = n11 - (width + 5);
                    n14 = n12;
                    break;
                }
                case 1: {
                    n13 = n11;
                    n14 = n12 - heightH;
                    break;
                }
                case 0: {
                    final Image[] markerType = this.markerType;
                    n13 = n11;
                    n14 = n12 + (markerType[mapMarker.type].getRHeight() + 3);
                    break;
                }
            }
            if (this.vtMapMarker.get(i).type != 0 && this.vtMapMarker.get(i).type != 1) {
                mFont.fontPaintMapNameWhite.drawString(mGraphics, processMapName, n13, n14, 0, true);
            }
            else {
                mFont.fontPaintMapNameYellow.drawString(mGraphics, processMapName, n13, n14, 0, true);
            }
            mGraphics.drawImage(this.markerType[mapMarker.type], n11, n12, true);
            if (this.vtMapMarker.get(i).mapId == MyTile.mapID) {
                mGraphics.drawImage(this.currentPosMarker, n11 + this.markerType[mapMarker.type].getRWidth() / 2 - this.currentPosMarker.getRWidth() / 2, this.markerType[mapMarker.type].getRHeight() / 2 + n12 - this.currentPosMarker.getRHeight() - 1 + CanvasNinja.gameTick % 15 / 5, true);
            }
            if (i == this.indexSelected) {
                mGraphics.drawImage(this.selectedPosMarker, n11 + this.markerType[mapMarker.type].getRWidth() / 2 - this.selectedPosMarker.getRWidth() / 2, this.markerType[mapMarker.type].getRHeight() / 2 + n12 - this.selectedPosMarker.getRHeight() - 1, true);
            }
            n10 = 1;
            ++i;
        }
        CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameTitle2, this.xTitle, this.yTitle, this.wTitle, false, 0, false);
        TabScr.fontPaintTile.drawString(mGraphics, this.title, this.wTitle / 2 + this.xTitle, this.hTitle / 2 + this.yTitle, 3);
        LoadDataManager.framePointerFindMap[0].drawFrame(CanvasNinja.gameTick % 11, (float)(super.x + super.w / 2 - LoadDataManager.framePointerFindMap[3].getRWidth() / 2), (float)(super.y + super.h - super.margin - LoadDataManager.framePointerFindMap[3].getRHeight()), mGraphics);
        LoadDataManager.framePointerFindMap[n10].drawFrame(CanvasNinja.gameTick % 11, (float)(super.x + super.w / 2 - LoadDataManager.framePointerFindMap[3].getRWidth() / 2), (float)(super.y + LoadDataManager.framePointerFindMap[3].getRHeight()), mGraphics);
        LoadDataManager.framePointerFindMap[2].drawFrame(CanvasNinja.gameTick % 11, (float)(super.x + super.margin), (float)(super.y + super.h / 2), mGraphics);
        LoadDataManager.framePointerFindMap[3].drawFrame(CanvasNinja.gameTick % 11, (float)(super.x + super.w - super.margin - LoadDataManager.framePointerFindMap[3].getRWidth()), (float)(super.y + super.h / 2), mGraphics);
        mGraphics.restoreCanvas();
        final SelectBox selectBox = this.selectBox;
        if (selectBox != null) {
            selectBox.paint(mGraphics);
        }
        super.cmdClose.paintIconOnly(mGraphics);
    }

    public String processMapName(final String s) {
        if (s != null && !s.trim().isEmpty()) {
            final String[] split = s.toUpperCase().split("\\s+");
            final StringBuilder sb = new StringBuilder();
            int n = 0;
            for (final String s2 : split) {
                if (s2.length() <= 8) {
                    int n2 = n;
                    if (s2.length() + n > 8) {
                        sb.append("\n");
                        n2 = 0;
                    }
                    if (sb.length() > 0 && n2 == 0 && sb.charAt(sb.length() - 1) != '\n') {
                        sb.append("\n");
                    }
                    sb.append(s2);
                    sb.append(" ");
                    n = n2 + (s2.length() + 1);
                    final String[] split2 = sb.toString().split("\n");
                    if (split2.length > 3) {
                        return String.join("\n", (CharSequence[])Arrays.copyOfRange(split2, 0, 3)).trim();
                    }
                }
            }
            return sb.toString().trim();
        }
        return "";
    }

    @Override
    public void show() {
        this.init();
        this.showTab();
    }

    @Override
    public void update() {
        if (Gdx.input.isButtonPressed(0)) {
            if (!this.isDragging) {
                this.isDragging = true;
                this.dragStartX = Gdx.input.getX();
                this.dragStartY = Gdx.input.getY();
                this.lastXMapCenter = this.xMapCenter;
                this.lastYMapCenter = this.yMapCenter;
                if (CanvasNinja.isPointerRelease()) {
                    final int x = Gdx.input.getX();
                    final int y = Gdx.input.getY();
                    this.mapImg.getRWidth();
                    this.mapImg.getRHeight();
                    final int w = super.w;
                    final int h = super.h;
                    final int x2 = super.x;
                    final int xMapCenter = this.xMapCenter;
                    final int n = (w - 10) / 2;
                    final int y2 = super.y;
                    final int yMapCenter = this.yMapCenter;
                    final int n2 = (h - 10) / 2;
                    this.indexSelected = -1;
                    for (int i = 0; i < this.vtMapMarker.size(); ++i) {
                        final MapMarker mapMarker = this.vtMapMarker.get(i);
                        final int n3 = mapMarker.x_pos + (x2 + 5 - (xMapCenter - n)) - this.markerType[mapMarker.type].getRWidth() / 2;
                        final int n4 = mapMarker.y_pos + (y2 + 5 - (yMapCenter - n2)) - this.markerType[mapMarker.type].getRHeight() / 2;
                        final int rWidth = this.markerType[mapMarker.type].getRWidth();
                        final int rHeight = this.markerType[mapMarker.type].getRHeight();
                        if (x >= n3 && x <= n3 + rWidth && y >= n4 && y <= n4 + rHeight) {
                            this.indexSelected = i;
                            break;
                        }
                    }
                }
            }
            else {
                final int x3 = Gdx.input.getX();
                final int dragStartX = this.dragStartX;
                final int y3 = Gdx.input.getY();
                final int dragStartY = this.dragStartY;
                this.xMapCenter = this.lastXMapCenter - (x3 - dragStartX);
                this.yMapCenter = this.lastYMapCenter - (y3 - dragStartY);
                final int rWidth2 = this.mapImg.getRWidth();
                final int rHeight2 = this.mapImg.getRHeight();
                final int n5 = super.w - 10;
                final int n6 = super.h - 10;
                this.xMapCenter = Math.max(n5 / 2, Math.min(this.xMapCenter, rWidth2 - n5 / 2));
                this.yMapCenter = Math.max(n6 / 2, Math.min(this.yMapCenter, rHeight2 - n6 / 2));
            }
        }
        else {
            this.isDragging = false;
        }
        if (CanvasNinja.isPointerRelease()) {
            final int n7 = super.w - 10;
            final int n8 = super.h - 10;
            final int x4 = super.x;
            final int xMapCenter2 = this.xMapCenter;
            final int n9 = n7 / 2;
            final int y4 = super.y;
            final int yMapCenter2 = this.yMapCenter;
            final int n10 = n8 / 2;
            for (int j = 0; j < this.vtMapMarker.size(); ++j) {
                final MapMarker mapMarker2 = this.vtMapMarker.get(j);
                final int n11 = mapMarker2.x_pos + (x4 + 5 - (xMapCenter2 - n9)) - this.markerType[mapMarker2.type].getRWidth() / 2;
                final int n12 = mapMarker2.y_pos + (y4 + 5 - (yMapCenter2 - n10)) - this.markerType[mapMarker2.type].getRHeight() / 2;
                if (CanvasNinja.isPoint(n11, n12, this.markerType[mapMarker2.type].getRWidth(), this.markerType[mapMarker2.type].getRHeight())) {
                    this.indexSelected = j;
                    final SelectBox selectBox = new SelectBox(Math.min(n11 + 10, CanvasNinja.w - mFont.fontPaintSelectOption.getWidth(this.vtMapMarker.get(j).descMap) * 2), n12 + 10, new String[] { this.vtMapMarker.get(j).descMap, "Chỉ đường" }, -1);
                    this.selectBox = selectBox;
                    selectBox.isSpecialBox = true;
                }
            }
            if (CanvasNinja.isPoint(super.x + super.w / 2 - LoadDataManager.framePointerFindMap[3].getRWidth() / 2, super.y + LoadDataManager.framePointerFindMap[3].getRHeight(), LoadDataManager.framePointerFindMap[3].getRWidth(), LoadDataManager.framePointerFindMap[3].getRHeight())) {
                this.yMapCenter -= 10;
            }
            if (CanvasNinja.isPoint(super.x + super.w / 2 - LoadDataManager.framePointerFindMap[3].getRWidth() / 2, super.y + super.h - super.margin - LoadDataManager.framePointerFindMap[3].getRHeight(), LoadDataManager.framePointerFindMap[3].getRWidth(), LoadDataManager.framePointerFindMap[3].getRHeight())) {
                this.yMapCenter += 10;
            }
            if (CanvasNinja.isPoint(super.x + super.margin, super.y + super.h / 2, LoadDataManager.framePointerFindMap[3].getRWidth(), LoadDataManager.framePointerFindMap[3].getRHeight())) {
                this.xMapCenter -= 10;
            }
            if (CanvasNinja.isPoint(super.x + super.w - super.margin - LoadDataManager.framePointerFindMap[3].getRWidth(), super.y + super.h / 2, LoadDataManager.framePointerFindMap[3].getRWidth(), LoadDataManager.framePointerFindMap[3].getRHeight())) {
                this.xMapCenter += 10;
            }
            final int rWidth3 = this.mapImg.getRWidth();
            final int rHeight3 = this.mapImg.getRHeight();
            this.xMapCenter = Math.max(n7 / 2, Math.min(this.xMapCenter, rWidth3 - n7 / 2));
            this.yMapCenter = Math.max(n8 / 2, Math.min(this.yMapCenter, rHeight3 - n8 / 2));
        }
    }

    @Override
    public void updateKey() {
        super.cmdClose.updateIconOnly();
        if (Gdx.input.isKeyPressed(21)) {
            this.xMapCenter -= 10;
        }
        if (Gdx.input.isKeyPressed(22)) {
            this.xMapCenter += 10;
        }
        if (Gdx.input.isKeyPressed(19)) {
            this.yMapCenter -= 10;
        }
        if (Gdx.input.isKeyPressed(20)) {
            this.yMapCenter += 10;
        }
        final int rWidth = this.mapImg.getRWidth();
        final int rHeight = this.mapImg.getRHeight();
        final int n = super.w - 10;
        final int n2 = super.h - 10;
        this.xMapCenter = Math.max(n / 2, Math.min(this.xMapCenter, rWidth - n / 2));
        this.yMapCenter = Math.max(n2 / 2, Math.min(this.yMapCenter, rHeight - n2 / 2));
        if (CanvasNinja.isPointerRelease()) {
            final SelectBox selectBox = this.selectBox;
            if (selectBox != null) {
                final int updatePointer = selectBox.updatePointer();
                if (updatePointer != -1) {
                    if (updatePointer == 1) {
                        SendMessage.gI().requestWorldMap(this.vtMapMarker.get(this.indexSelected).mapId);
                    }
                    this.selectBox = null;
                }
            }
        }
    }

    public static class MapMarker
    {
        String descMap;
        short mapId;
        String mapName;
        byte type;
        short x_pos;
        short y_pos;

        public MapMarker(final short n, final String mapName, final String descMap, final byte b, final short n2, final short n3) {
            this.mapId = n;
            this.mapName = mapName;
            this.descMap = descMap;
            this.type = b;
            this.x_pos = n2;
            this.y_pos = n3;
        }
    }
}
