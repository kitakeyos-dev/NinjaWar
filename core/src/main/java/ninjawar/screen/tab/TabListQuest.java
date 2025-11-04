package ninjawar.screen.tab;

import java.util.Iterator;
import java.util.Vector;
import ninjawar.model.MissionPaintDetail;
import ninjawar.model.MissionTypes;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.object.Item;
import ninjawar.scroll.ScrollX;
import ninjawar.scroll.ScrollY;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.SupportPaint;
import ninjawar.supporter.SupportTranslate;

public class TabListQuest extends TabScr {
    public static mFont fontPaint = mFont.tahoma_brown_dv;
    public static mFont fontPaintDetail = mFont.tahoma_7_brown;
    public static mFont fontPaintFocus;
    public static mFont fontPaintPhanThuong = mFont.tahoma_7b_brown;
    public static mFont fontPaintText = mFont.tahoma_7_brown;
    public static mFont fontPaintTextFocus = mFont.tahoma_7_white;
    public static mFont fontPaintTitle;
    public static mFont fontPlaintItemNum = mFont.tahoma_7_small;
    public static TabListQuest me;
    public static Vector<MissionPaintDetail> vecMissions = new Vector<>();
    int count = 0;
    int hTitle;
    public int indexSelected = -1;
    int marginTagNhiemVu = 2;
    int marginTitleAndTagNhiemVu = 4;
    MissionPaintDetail missionPaintDetail;
    ScrollX scrollX;
    ScrollY scrollY;
    String title = "";
    String tmpSkillName = "";
    int transOneTab;
    Vector<MissionTypes> vecMissionTypes;
    int wLeft;
    int wTitle;
    int xTitle;
    int yTitle;

    public static TabListQuest gI() {
        if (me == null) {
            me = new TabListQuest();
        }
        return me;
    }

    static {
        mFont mfont = mFont.tahoma_brown_focus_dv;
        fontPaintFocus = mfont;
        fontPaintTitle = mfont;
    }

    public void initDetailCur() {
        MissionPaintDetail missionPaintDetail2 = this.missionPaintDetail;
        if (missionPaintDetail2 != null) {
            missionPaintDetail2.setContentNormal(fontPaintDetail, this.arrW[3]);
            this.missionPaintDetail.setDescNormal(fontPaintDetail, this.arrW[3]);
        }
    }

    private void paintListItem(mGraphics g, int xStart, int yStart, boolean useClip) {
        int totalWidth = 0;
        int itemCount = this.missionPaintDetail.vecItems.size();
        for (int i = 0; i < itemCount; i++) {
            totalWidth += this.missionPaintDetail.vecItems.elementAt(i).getW();
            if (i < itemCount - 1) {
                totalWidth += this.margin * 3;
            }
        }
        int currentX = (xStart + ((this.arrW[5] - totalWidth) / 2)) - (this.margin * 2);
        for (int i2 = 0; i2 < itemCount; i2++) {
            Item item = this.missionPaintDetail.vecItems.elementAt(i2);
            item.paintCenter(g, currentX - (item.getW() / 2), (yStart - 2) - (this.missionPaintDetail.vecItems.elementAt(0).getH() / 2), useClip);
            mFont mfont = fontPlaintItemNum;
            mfont.drawString(g, "x" + item.num, currentX + (item.getW() / 2), yStart + 2, 2, useClip);
            currentX += item.getW() + (this.margin * 3);
        }
    }

    public void paint(mGraphics g) {
        mGraphics mgraphics = g;
        CanvasNinja.paintz.paintSingleBorderFrame(g, LoadDataManager.frameBgOrange2_0, (float) this.x, (float) this.y, (float) this.w, (float) this.h, 0, false);
        SupportPaint supportPaint = CanvasNinja.paintz;
        FrameImage frameImage = LoadDataManager.frameTitle2;
        int i = this.xTitle;
        int i2 = this.margin;
        supportPaint.paintTagFrame(g, frameImage, i - (i2 * 2), this.yTitle, this.wTitle + (i2 * 4), false, 0, false);
        char c = 2;
        mGraphics mgraphics2 = g;
        TabScr.fontPaintTile.drawString(mgraphics2, this.title, this.xTitle + (this.wTitle / 2), this.yTitle + (this.hTitle / 2), 3);
        CanvasNinja.paintz.paintSingleBorderFrame(mgraphics2, LoadDataManager.frameBgWhite1, (float) this.arrX[2], (float) this.arrY[2], (float) this.arrW[2], (float) this.arrH[2], 6, false);
        CanvasNinja.paintz.paintTagFrame(mgraphics2, LoadDataManager.frameBgGift, this.arrX[5], this.arrY[5], this.arrW[5], false, 0, false);
        fontPaintPhanThuong.drawString(mgraphics2, SupportTranslate.getTextLangue("gift"), this.arrX[4], this.arrY[4], 2);
        MissionPaintDetail missionPaintDetail2 = this.missionPaintDetail;
        char c2 = 1;
        if (missionPaintDetail2 == null || missionPaintDetail2.contentNormal == null) {
            try {
                MissionPaintDetail missionPaintDetail3 = this.vecMissionTypes.get(0).vecMissionPaintDetails.get(0);
                this.missionPaintDetail = missionPaintDetail3;
                missionPaintDetail3.setContentNormal(fontPaintDetail, this.arrW[3]);
                this.missionPaintDetail.setDescNormal(fontPaintDetail, this.arrW[3]);
            } catch (Exception e) {
                Res.outz("LOI MO TAB NHIEM VU");
            }
        } else {
            int i3 = 0;
            while (true) {
                String[] strArr = this.missionPaintDetail.descNormal;
                if (i3 >= strArr.length) {
                    break;
                }
                mFont.tahoma_7_orange_r.drawString(g, strArr[i3], this.arrX[3], (i3 * 3) + this.arrY[3] + (mFont.tahoma_7b_brown.getHeight() * i3), 0, true);
                i3++;
            }
            int i4 = 0;
            while (true) {
                MissionPaintDetail missionPaintDetail4 = this.missionPaintDetail;
                String[] strArr2 = missionPaintDetail4.contentNormal;
                if (i4 >= strArr2.length) {
                    break;
                }
                mFont mfont = fontPaintDetail;
                mfont.drawString(g, strArr2[i4], this.arrX[3], this.arrY[3] + (this.margin * 2) + ((missionPaintDetail4.descNormal.length + i4) * mfont.getHeight()) + (i4 * 3), 0, true);
                i4++;
            }
            if (this.scrollX != null) {
                mgraphics.setClip(this.arrX[5], this.arrY[5], this.arrW[5], this.arrH[5]);
                mgraphics.translate(-this.scrollX.cmx, 0);
            }
            paintListItem(mgraphics, this.arrX[5] + (this.margin * 3), this.arrY[5] + 35, true);
        }
        CanvasNinja.resetTrans(g);
        if (this.scrollY != null) {
            mgraphics.setClip(this.arrX[0], this.arrY[0], this.wLeft, this.arrH[2]);
            mgraphics.translate(0, -this.scrollY.cmy);
        }
        int yOffset = 0;
        int i5 = 0;
        while (i5 < this.vecMissionTypes.size()) {
            MissionTypes missionType = this.vecMissionTypes.get(i5);
            MissionTypes missionType2 = missionType;
            mFont.tahoma_7b_brown.drawString(g, SupportTranslate.getArrayLangue("MISSION_TYPE")[missionType.type], this.arrX[0], this.arrY[0] + yOffset, 0, true);
            yOffset += fontPaintTitle.getHeight() + this.marginTitleAndTagNhiemVu;
            int k = 0;
            while (k < missionType2.vecMissionPaintDetails.size()) {
                MissionPaintDetail mission = missionType2.vecMissionPaintDetails.get(k);
                SupportPaint supportPaint2 = CanvasNinja.paintz;
                FrameImage frameImage2 = LoadDataManager.frameBgMission;
                int i6 = this.arrX[c2];
                int i7 = this.arrY[c2] + yOffset;
                int i8 = this.arrW[c2];
                boolean z = this.indexSelected != mission.id;
                MissionPaintDetail mission2 = mission;
                int i9 = i8;
                int k2 = k;
                int i10 = i5;
                supportPaint2.paintTagFrame(g, frameImage2, i6, i7, i9, z, 0, true);
                String displayTitle = (this.indexSelected != mission2.id && !this.tmpSkillName.isEmpty()) ? this.tmpSkillName : mission2.title;
                if (displayTitle.length() > 20) {
                    displayTitle = displayTitle.substring(0, 15) + "...";
                }
                fontPaintText.drawString(g, displayTitle, this.arrX[1] + 5, ((this.arrY[1] + yOffset) + (this.arrH[1] / 2)) - 7, 0, true);
                yOffset += this.arrH[1] + (this.marginTitleAndTagNhiemVu * 3);
                k = k2 + 1;
                c = 2;
                c2 = 1;
                i5 = i10;
            }
            int i11 = k;
            char c3 = c2;
            char c4 = c;
            i5++;
            c2 = c3;
        }
        CanvasNinja.resetTrans(g);
        this.cmdClose.paintIconOnly(mgraphics);
    }

    public void getListMissionTypes() {
        this.vecMissionTypes = new Vector<>();
        int numTypes = SupportTranslate.getArrayLangue("MISSION_TYPE").length;
        Vector<MissionTypes> tempMissionTypes = new Vector<>();
        for (int i = 0; i < numTypes; i++) {
            MissionTypes missionType = new MissionTypes();
            missionType.type = i;
            tempMissionTypes.add(missionType);
        }
        Iterator<MissionPaintDetail> it = vecMissions.iterator();
        while (it.hasNext()) {
            MissionPaintDetail item = it.next();
            byte b = item.type;
            if (b >= 0 && b < numTypes) {
                tempMissionTypes.elementAt(b).vecMissionPaintDetails.add(item);
            }
        }
        Iterator<MissionTypes> it2 = tempMissionTypes.iterator();
        while (it2.hasNext()) {
            MissionTypes missionType2 = it2.next();
            if (!missionType2.vecMissionPaintDetails.isEmpty()) {
                this.vecMissionTypes.add(missionType2);
            }
        }
    }

    public void initScrollY() {
        int maxPaint = getSumHTypeAll(fontPaintTitle, this.arrH[1], this.marginTitleAndTagNhiemVu, this.marginTagNhiemVu);
        int i = this.arrX[0];
        int i2 = this.arrY[0];
        int i3 = this.wLeft;
        int i4 = this.arrH[2];
        this.scrollY = new ScrollY(i, i2, i3, i4, maxPaint, i4);
    }

    public void initScrollX() {
        MissionPaintDetail missionPaintDetail2 = this.missionPaintDetail;
        if (missionPaintDetail2 != null && missionPaintDetail2.vecItems.size() > 0) {
            int i = this.arrX[5];
            int i2 = this.arrY[5];
            int i3 = this.arrW[5];
            this.scrollX = new ScrollX(i, i2, i3, this.arrH[5], (this.missionPaintDetail.vecItems.size() * this.missionPaintDetail.vecItems.elementAt(0).getW()) + ((this.missionPaintDetail.vecItems.size() - 1) * this.margin), i3);
        }
    }

    public void init() {
        fontPaintText = mFont.tahoma_7_brown_12;
        this.title = SupportTranslate.getTextLangue("mission").toUpperCase();
        this.w = 450;
        int i = CanvasNinja.w;
        if (450 > i) {
            this.w = i - (this.margin * 5);
        }
        this.h = 300;
        int i2 = this.h;
        int i3 = CanvasNinja.h;
        if (i2 > i3) {
            this.h = i3 - (this.margin * 2);
        }
        initStart();
        this.hTitle = (int) LoadDataManager.frameTitle.frameHeight;
        int width = TabScr.fontPaintTile.getWidth(this.title) + (this.margin * 4);
        this.wTitle = width;
        int fixSizeTagFrameUp = Res.fixSizeTagFrameUp(8, width, LoadDataManager.frameTitle);
        this.wTitle = fixSizeTagFrameUp;
        this.xTitle = this.x + ((this.w - fixSizeTagFrameUp) / 2);
        this.yTitle = this.y - (this.hTitle / 2);
        getListMissionTypes();
        int i4 = this.margin;
        int marginTopBot = i4 * 4;
        int marginLeftRight = i4 * 2;
        this.wLeft = ((int) LoadDataManager.frameBgMission.frameWidth) + (i4 * 2);
        int wTextTitle = fontPaintTitle.getWidth(SupportTranslate.getArrayLangue("MISSION_TYPE")[0]) + this.margin;
        if (this.wLeft < wTextTitle) {
            this.wLeft = wTextTitle;
        }
        int wMaxLeft = getWMaxLeft(this.wLeft);
        this.wLeft = wMaxLeft;
        int wRight = (((this.w - wMaxLeft) - 14) - (marginLeftRight * 2)) - (this.margin * 4);
        int i5 = (this.h - 14) - (marginTopBot * 2);
        int i6 = this.margin;
        int[] iArr = {fontPaintTitle.getHeight(), (int) LoadDataManager.frameBgMission.frameHeight, i5 + i6, 0, 0, (int) LoadDataManager.frameBgGift.frameHeight};
        this.arrH = iArr;
        int i7 = wRight - (i6 * 4);
        this.arrW = new int[]{0, (int) LoadDataManager.frameBgMission.frameWidth, wRight, wRight - (i6 * 4), 0, i7};
        int xStartLeft = this.x + 14 + marginLeftRight;
        int xRight = this.wLeft + xStartLeft + i6;
        this.arrX = new int[]{xStartLeft, xStartLeft, xRight, (i6 * 2) + xRight, (wRight / 2) + xRight, ((wRight - i7) / 2) + xRight};
        int yStart = this.y + 14 + (i6 * 2);
        int[] iArr2 = this.arrH;
        this.arrY = new int[]{yStart, this.marginTitleAndTagNhiemVu + yStart, yStart, (i6 * 2) + yStart, ((((iArr[2] + yStart) - (i6 * 2)) - iArr[5]) - i6) - fontPaintPhanThuong.getHeight(), ((iArr2[2] + yStart) - (this.margin * 2)) - iArr2[5]};
        selectMission(0);
        initScrollY();
    }

    private void selectMission(int indexSelected2) {
        if (indexSelected2 >= 0) {
            this.indexSelected = indexSelected2;
            initDetailCur();
            initScrollX();
        }
    }

    public void update() {
        ScrollY scrollY2 = this.scrollY;
        if (scrollY2 != null) {
            scrollY2.update();
        }
        ScrollX scrollX2 = this.scrollX;
        if (scrollX2 != null) {
            scrollX2.update();
        }
    }

    public void updateKey() {
        this.cmdClose.updateIconOnly();
        if (CanvasNinja.isPointerRelease()) {
            int i = 0;
            while (true) {
                if (i >= vecMissions.size()) {
                    break;
                } else if (CanvasNinja.isPoint(this.x, this.arrY[0] + (this.transOneTab * i), this.w, this.arrH[0])) {
                    CanvasNinja.clearAllPointer();
                    break;
                } else {
                    i++;
                }
            }
        }
        int offsetY = 0;
        if (CanvasNinja.isPointerRelease()) {
            int i2 = 0;
            while (i2 < this.vecMissionTypes.size()) {
                boolean isBreak = false;
                int sumHType = getSumHType(fontPaintTitle, this.arrH[1], this.marginTitleAndTagNhiemVu, this.marginTagNhiemVu, this.vecMissionTypes.get(i2).type);
                offsetY += fontPaintTitle.getHeight() + this.marginTitleAndTagNhiemVu;
                int k = 0;
                while (true) {
                    if (k >= this.vecMissionTypes.get(i2).vecMissionPaintDetails.size()) {
                        break;
                    }
                    this.missionPaintDetail = this.vecMissionTypes.get(i2).vecMissionPaintDetails.get(k);
                    if (CanvasNinja.isPointerRelease()) {
                        int i3 = this.arrX[1];
                        int i4 = this.arrY[1];
                        int i5 = this.arrH[1];
                        if (CanvasNinja.isPoint(i3, ((i4 + (i5 / 2)) - 7) + offsetY, this.arrW[1], i5)) {
                            CanvasNinja.clearAllPointer();
                            isBreak = true;
                            int i6 = this.missionPaintDetail.id;
                            this.indexSelected = i6;
                            selectMission(i6);
                            break;
                        }
                    }
                    offsetY += this.arrH[1] + (this.marginTagNhiemVu * 4);
                    k++;
                }
                if (!isBreak) {
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    public void show() {
        init();
        showTab();
    }

    public void keyPress(int keyCode) {
    }

    public void keyTyped() {
    }

    public void commandTab(int index, int subIndex) {
        super.commandTab(index, subIndex);
    }

    public int getSumHType(mFont fontPaintTitle2, int hTag, int marginTitleTag, int marginTagTag, int type) {
        int result = 0;
        int i = 0;
        while (i < this.vecMissionTypes.size() && this.vecMissionTypes.get(i).type < type) {
            result += this.vecMissionTypes.get(i).getHType(fontPaintTitle2, hTag, marginTitleTag, marginTagTag);
            i++;
        }
        return result;
    }

    public int getSumHTypeAll(mFont fontPaintTitle2, int hTag, int marginTitleTag, int marginTagTag) {
        int result = 0;
        Iterator<MissionTypes> it = this.vecMissionTypes.iterator();
        while (it.hasNext()) {
            result += it.next().getHType(fontPaintTitle2, hTag, marginTitleTag, marginTagTag);
        }
        return result;
    }

    public int getWMaxLeft(int wCheck) {
        int result = wCheck;
        for (int i = 0; i < this.vecMissionTypes.size(); i++) {
            Iterator<MissionPaintDetail> it = this.vecMissionTypes.elementAt(i).vecMissionPaintDetails.iterator();
            while (it.hasNext()) {
                MissionPaintDetail item = it.next();
                if (result < fontPaintText.getWidth(item.title)) {
                    result = fontPaintText.getWidth(item.title);
                }
            }
        }
        return result;
    }
}
