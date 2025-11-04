// Class Version: 8
package ninjawar.screen.tab;

import ninjawar.screen.dialog.InputDialog;
import ninjawar.input.KEY;
import ninjawar.input.MyButtonSkill;
import ninjawar.mylib.Image;
import ninjawar.model.MyCommand;
import ninjawar.myscr.Res;
import ninjawar.supporter.SupportTranslate;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.model.mClass;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ninjawar.mylib.Atlas;
import ninjawar.myscr.MapScr;
import ninjawar.message.SendMessage;
import ninjawar.mylib.AudioManager;
import ninjawar.mylib.FrameImage;
import ninjawar.supporter.SupportPaint;
import ninjawar.mymain.CanvasNinja;
import ninjawar.supporter.LoadDataManager;
import ninjawar.mylib.mGraphics;
import ninjawar.myscr.Player;
import ninjawar.mylib.VectorCustom;
import ninjawar.template.SkillTemplate;
import java.util.Vector;
import java.util.HashMap;
import ninjawar.mylib.mSprite;
import ninjawar.input.MyButton;
import ninjawar.mylib.mFont;

public class TabLearnSkill extends TabScr
{
    public static mFont fontPaintLockedLv;
    public static mFont fontPaintMoney;
    public static mFont fontPaintNameChar;
    public static mFont fontPaintSkillName;
    public static mFont fontPaintTagName;
    public static mFont fontPaintTagNameFocus;
    public static boolean isPaintButtonSkill;
    public static TabLearnSkill me;
    MyButton btnHoc;
    int count;
    mFont fontTextAn;
    mFont fontTextCam;
    mFont fontTextDo;
    mFont fontTextNau;
    mFont fontTextTrang;
    mFont fontTextXanh;
    mSprite imgTempHoldMove;
    int indexSelectedInven;
    int indexSelectedSkill;
    int indexSelectedUsed;
    int indexTabIconSelected;
    int indexTagNameSelected;
    int indexTemp;
    boolean[] isClickSkill;
    boolean isClickSpace;
    boolean[] isClickTagIcon;
    boolean isPaintBtnHoc;
    String keyTemp;
    public HashMap<Byte, Vector<Short>> mSkill;
    int marginBottom;
    int marginLeft;
    int marginLineGiua;
    int marginRight;
    int marginTop;
    int maxDocBox;
    public String[] nameTags;
    String skillDes;
    public SkillTemplate[] skillPaints;
    int startUsed;
    int sumWBtnSkill;
    String tmpSkillName;
    public VectorCustom vSkill;
    int wRight;
    int xMoreFromNextPrevious;
    int xMoreFromNextPreviousDefault;
    int[] yAnimTagIcon;

    static {
        TabLearnSkill.fontPaintMoney = mFont.tahoma_7_white_small;
        TabLearnSkill.fontPaintLockedLv = mFont.tahoma_7_red_small;
        TabLearnSkill.fontPaintNameChar = mFont.tahoma_name_char_inventory;
        TabLearnSkill.fontPaintTagName = mFont.tahoma_brown_dv;
        TabLearnSkill.fontPaintSkillName = mFont.fontPaintSkillName;
        TabLearnSkill.fontPaintTagNameFocus = mFont.tahoma_brown_focus_dv;
    }

    public TabLearnSkill() {
        this.startUsed = 0;
        this.marginLeft = 20;
        this.marginRight = 20;
        this.marginTop = 25;
        this.marginBottom = 50;
        this.marginLineGiua = 15;
        this.maxDocBox = 20;
        this.indexSelectedUsed = -1;
        this.indexSelectedInven = -1;
        this.indexSelectedSkill = 0;
        this.yAnimTagIcon = new int[4];
        this.isClickTagIcon = new boolean[4];
        this.isClickSkill = new boolean[9];
        this.vSkill = Player.myCharz().vSkill;
        this.mSkill = new HashMap<Byte, Vector<Short>>();
        this.isPaintBtnHoc = true;
        this.indexTemp = 0;
        this.keyTemp = "";
        this.fontTextTrang = mFont.fontTextTrang;
        this.fontTextCam = mFont.fontTextCam;
        this.fontTextNau = mFont.fontTextNau;
        this.fontTextDo = mFont.fontTextDo;
        this.fontTextXanh = mFont.fontTextXanh;
        this.fontTextAn = mFont.fontTextAn;
        this.skillDes = "";
        this.count = 0;
        this.isClickSpace = false;
    }

    public static TabLearnSkill gI() {
        if (TabLearnSkill.me == null) {
            TabLearnSkill.me = new TabLearnSkill();
        }
        return TabLearnSkill.me;
    }

    private void paintSkillInfo(final mGraphics mGraphics) {
        final int n = super.arrX[3] + super.arrW[1] / 6;
        final int n2 = (int)(super.arrY[1] + super.margin + LoadDataManager.khungSkill.frameHeight / 2.0f);
        final SupportPaint paintz = CanvasNinja.paintz;
        final FrameImage khungTenSkill = LoadDataManager.khungTenSkill;
        paintz.paintTagFrame(mGraphics, khungTenSkill, (int)(n - khungTenSkill.frameWidth / 2.0f), n2 - (int)khungTenSkill.frameHeight / 2, 150, false, true);
        final SkillTemplate[] skillPaints = this.skillPaints;
        if (skillPaints != null) {
            final int indexSelectedSkill = this.indexSelectedSkill;
            if (indexSelectedSkill < skillPaints.length) {
                final SkillTemplate skillTemplate = skillPaints[indexSelectedSkill];
                if (skillTemplate != null) {
                    final mSprite imgSkillTemplate = skillTemplate.imgSkillTemplate;
                    if (imgSkillTemplate != null) {
                        mGraphics.drawImage(LoadDataManager.imgBgIconSkill, n - imgSkillTemplate.getWidth() / 2 - 2, n2 - this.skillPaints[this.indexSelectedSkill].imgSkillTemplate.getHeight() / 2, true);
                        final mSprite imgSkillTemplate2 = this.skillPaints[this.indexSelectedSkill].imgSkillTemplate;
                        mGraphics.drawSprite(imgSkillTemplate2, (float)(n - imgSkillTemplate2.getWidth() / 2), (float)(n2 - this.skillPaints[this.indexSelectedSkill].imgSkillTemplate.getHeight() / 2), true);
                    }
                }
            }
        }
        int level;
        if (Player.myCharz().getSkill(this.skillPaints[this.indexSelectedSkill]) == null) {
            level = 0;
        }
        else {
            level = Player.myCharz().getSkill(this.skillPaints[this.indexSelectedSkill]).level;
        }
        if (level == 0) {
            mGraphics.drawImage(LoadDataManager.imgCensored, (float)(n - this.skillPaints[this.indexSelectedSkill].imgSkillTemplate.getWidth() / 2), (float)(n2 - this.skillPaints[this.indexSelectedSkill].imgSkillTemplate.getHeight() / 2), 0, true, 80);
        }
        final SupportPaint paintz2 = CanvasNinja.paintz;
        final FrameImage khungSkill = LoadDataManager.khungSkill;
        final float n3 = (float)n;
        final float frameWidth = khungSkill.frameWidth;
        paintz2.paintTagFrame(mGraphics, khungSkill, (int)(n3 - frameWidth / 2.0f), n2 - (int)khungSkill.frameHeight / 2, (int)frameWidth, false, true);
        if (this.fontTextTrang.getWidth(this.skillPaints[this.indexSelectedSkill].name.toUpperCase()) <= 100) {
            this.fontTextTrang.drawString(mGraphics, this.skillPaints[this.indexSelectedSkill].name.toUpperCase(), super.margin + (n + 75), n2, 3);
        }
        else {
            this.fontTextTrang.drawString(mGraphics, this.initMaxWidthText(90, this.tmpSkillName), super.margin + (n + 75), n2, 3);
        }
        CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.lvInfo, (int)(n - LoadDataManager.khungSkill.frameWidth / 2.0f), super.margin + (super.arrY[3] + super.arrH[3] / 3), super.margin * 5 + this.fontTextTrang.getWidth("Mô tả"), false, false);
        this.fontTextTrang.drawString(mGraphics, "Mô tả", (int)(n - LoadDataManager.khungSkill.frameWidth / 2.0f + LoadDataManager.lvInfo.frameHeight / 2.0f) + 4, super.arrY[3] + super.arrH[3] / 3 + super.margin3 + super.margin + 1, 0);
        CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.lvInfo, (int)(n - LoadDataManager.khungSkill.frameWidth / 2.0f), super.margin + (super.arrY[3] + super.arrH[3] / 3 * 2), super.margin * 4 + this.fontTextTrang.getWidth("Cấp kế tiếp"), false, true);
        final int n4 = (int)(n - LoadDataManager.khungSkill.frameWidth / 2.0f + LoadDataManager.lvInfo.frameHeight / 2.0f);
        final int n5 = super.arrY[3] + super.arrH[3] / 3 * 2 + super.margin3;
        int param;
        if (Player.myCharz().getSkill(this.skillPaints[this.indexSelectedSkill]) == null) {
            param = 0;
        }
        else {
            param = Player.myCharz().getSkill(this.skillPaints[this.indexSelectedSkill]).options[0].param;
        }
        final mFont fontTextNau = this.fontTextNau;
        final StringBuilder sb = new StringBuilder();
        sb.append("Sát thương: ");
        sb.append(param);
        fontTextNau.drawString(mGraphics, sb.toString(), (int)(n - LoadDataManager.khungSkill.frameWidth / 2.0f), n5 - super.margin * 3, 0);
        this.fontTextTrang.drawString(mGraphics, "Cấp kế tiếp", n4 + 1, super.margin + n5 + 1, 0);
        final int indexTabIconSelected = this.indexTabIconSelected;
        String s;
        if (indexTabIconSelected == 0) {
            s = "[Kỹ năng thể thuật]";
        }
        else if (indexTabIconSelected == 1) {
            s = "[Kỹ năng nhẫn thuật]";
        }
        else if (indexTabIconSelected == 2) {
            s = "[Kỹ năng hỗ trợ]";
        }
        else if (indexTabIconSelected == 3) {
            s = "[Kỹ năng sống]";
        }
        else {
            s = "";
        }
        this.fontTextCam.drawString(mGraphics, s, (int)(n - LoadDataManager.khungSkill.frameWidth / 2.0f), n2 + super.margin * 6, 0);
        final mFont fontTextCam = this.fontTextCam;
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("Level: ");
        sb2.append(level);
        fontTextCam.drawString(mGraphics, sb2.toString(), (int)(n - LoadDataManager.khungSkill.frameWidth / 2.0f), n2 + super.margin * 9, 0);
        int manaUse;
        if (Player.myCharz().getSkill(this.skillPaints[this.indexSelectedSkill]) == null) {
            manaUse = 0;
        }
        else {
            manaUse = Player.myCharz().getSkill(this.skillPaints[this.indexSelectedSkill]).manaUse;
        }
        final mFont fontTextCam2 = this.fontTextCam;
        final StringBuilder sb3 = new StringBuilder();
        sb3.append("Chakra: ");
        sb3.append(manaUse);
        final String string = sb3.toString();
        final int n6 = super.arrX[3];
        final int[] arrW = super.arrW;
        fontTextCam2.drawString(mGraphics, string, n6 + arrW[3] - arrW[1] / 6, n2 + super.margin * 9, 1);
        for (final String s2 : this.skillPaints[this.indexSelectedSkill].description) {
            final StringBuilder sb4 = new StringBuilder();
            sb4.append(this.skillDes);
            sb4.append(s2);
            sb4.append(" ");
            this.skillDes = sb4.toString();
        }
        this.fontTextNau.drawString(mGraphics, this.wrapText(this.skillDes, 170), (int)(n - LoadDataManager.khungSkill.frameWidth / 2.0f), super.margin * 6 + (super.arrY[3] + super.arrH[3] / 3 + super.margin3), 0);
        this.skillDes = "";
        final int param2 = this.skillPaints[this.indexSelectedSkill].skills[level].options[0].param;
        final mFont fontTextDo = this.fontTextDo;
        final StringBuilder sb5 = new StringBuilder();
        sb5.append("Sát thương: ");
        sb5.append(param2);
        fontTextDo.drawString(mGraphics, sb5.toString(), (int)(n - LoadDataManager.khungSkill.frameWidth / 2.0f), super.margin * 6 + (super.arrY[3] + super.arrH[3] / 3 * 2 + super.margin3), 0);
        final mFont fontTextXanh = this.fontTextXanh;
        final StringBuilder sb6 = new StringBuilder();
        sb6.append("(+");
        sb6.append(param2 - param);
        sb6.append(")");
        final String string2 = sb6.toString();
        final int n7 = super.arrX[3];
        final int[] arrW2 = super.arrW;
        fontTextXanh.drawString(mGraphics, string2, n7 + arrW2[3] - arrW2[1] / 6, super.margin * 6 + (super.arrY[3] + super.arrH[3] / 3 * 2 + super.margin3), 1);
        final int manaUse2 = this.skillPaints[this.indexSelectedSkill].skills[level].manaUse;
        final mFont fontTextDo2 = this.fontTextDo;
        final StringBuilder sb7 = new StringBuilder();
        sb7.append("Chakra: ");
        sb7.append(manaUse2);
        fontTextDo2.drawString(mGraphics, sb7.toString(), (int)(n - LoadDataManager.khungSkill.frameWidth / 2.0f), super.margin * 9 + (super.arrY[3] + super.arrH[3] / 3 * 2 + super.margin3), 0);
        final mFont fontTextXanh2 = this.fontTextXanh;
        final StringBuilder sb8 = new StringBuilder();
        sb8.append("(+");
        sb8.append(manaUse2 - manaUse);
        sb8.append(")");
        final String string3 = sb8.toString();
        final int n8 = super.arrX[3];
        final int[] arrW3 = super.arrW;
        fontTextXanh2.drawString(mGraphics, string3, n8 + arrW3[3] - arrW3[1] / 6, super.margin * 9 + (super.arrY[3] + super.arrH[3] / 3 * 2 + super.margin3), 1);
    }

    @Override
    public void commandTab(final int n, final int n2) {
        super.commandTab(n, n2);
        switch (n) {
            case 696969: {
                if (!this.isClickSpace) {
                    AudioManager.buttonClick();
                    SendMessage.gI().requestUpgradeSkill(this.skillPaints[this.indexSelectedSkill].id);
                    break;
                }
                break;
            }
        }
    }

    public SkillTemplate[] getSkillPaint(int i) {
        final int classId = Player.myCharz().classId;
        final mClass[] nClasss = MapScr.nClasss;
        final SkillTemplate[] skillTemplates = nClasss[classId].skillTemplates;
        final SkillTemplate[] skillTemplates2 = nClasss[0].skillTemplates;
        final Vector<SkillTemplate> vector = new Vector<SkillTemplate>();
        final Vector<Short> vector2 = this.mSkill.get((byte)i);
        if (vector2 != null) {
            for (final Short n : vector2) {
                int length;
                SkillTemplate skillTemplate;
                for (length = skillTemplates.length, i = 0; i < length; ++i) {
                    skillTemplate = skillTemplates[i];
                    if (skillTemplate != null && skillTemplate.id == n && !vector.contains(skillTemplate)) {
                        vector.add(skillTemplate);
                        break;
                    }
                }
                int length2;
                SkillTemplate skillTemplate2;
                for (length2 = skillTemplates2.length, i = 0; i < length2; ++i) {
                    skillTemplate2 = skillTemplates2[i];
                    if (skillTemplate2.id == n && !vector.contains(skillTemplate2)) {
                        vector.add(skillTemplate2);
                        break;
                    }
                }
            }
        }
        SkillTemplate[] array;
        int iconId;
        mSprite mSprite;
        Atlas atlas;
        SkillTemplate skillTemplate3;
        HashMap<String, Sprite> sprites;
        StringBuilder sb;
        mSprite imgSkillTemplate;
        for (array = new SkillTemplate[vector.size()], i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
            if (array[i] != null) {
                iconId = array[i].iconId;
                mSprite = null;
                if (iconId != -1) {
                    atlas = Atlas.createAtlas((short)11, (short)23);
                }
                else {
                    atlas = null;
                }
                skillTemplate3 = array[i];
                if (array[i].iconId != -1 && atlas != null) {
                    sprites = atlas.sprites;
                    sb = new StringBuilder();
                    sb.append(array[i].iconId);
                    sb.append("");
                    imgSkillTemplate = new mSprite(sprites.get(sb.toString()));
                }
                else {
                    imgSkillTemplate = mSprite;
                }
                skillTemplate3.imgSkillTemplate = imgSkillTemplate;
            }
        }
        return array;
    }

    public void init() {
        this.isClickTagIcon = new boolean[4];
        this.yAnimTagIcon = new int[4];
        if (NinjaMidlet.isPC) {
            this.isClickTagIcon = new boolean[5];
            this.yAnimTagIcon = new int[5];
        }
        this.resetSelected();
        this.nameTags = SupportTranslate.getArrayLangue("TAG_NAME_INVENTORY");
        super.w = 480;
        super.h = 330;
        final int w = CanvasNinja.w;
        if (480 > w) {
            super.w = w - super.margin * 5 - (int)LoadDataManager.frameTabIconHanhTrang.frameWidth;
        }
        final int x = super.x;
        final int n = super.w / 2;
        final StringBuilder sb = new StringBuilder();
        sb.append("x: ");
        sb.append(super.x);
        sb.append("w: ");
        sb.append(super.w);
        sb.append("xGiua: ");
        sb.append(x + n);
        Res.outz(sb.toString());
        final int h = super.h;
        final int h2 = CanvasNinja.h;
        if (h > h2) {
            super.h = h2 - super.margin * 2;
        }
        this.initStart();
        final int n2 = (int)LoadDataManager.frameTagTitleHanhTrang.frameHeight;
        final int n3 = (int)LoadDataManager.frameTabIconHanhTrang.frameHeight;
        final int h3 = super.h;
        final int marginTop = this.marginTop;
        final int marginBottom = this.marginBottom;
        super.arrH = new int[] { n3, h3 - marginTop - marginBottom, h3 - marginTop - marginBottom, h3 - marginTop - marginBottom, (int)LoadDataManager.frameTitle.frameHeight, (int)LoadDataManager.mySkillButton[1].frameHeight };
        final int length = this.nameTags.length;
        Res.fixSizeTagFrameDown(7, (this.wRight - LoadDataManager.imgClose.getRWidth() - super.margin7 - super.margin * length) / length, LoadDataManager.frameTagTitleHanhTrang);
        final int[] arrX = new int[6];
        final int x2 = super.x;
        final int w2 = super.w;
        final int margin = super.margin;
        arrX[0] = x2 + w2 - margin * 4 + 1;
        final int marginLeft = this.marginLeft;
        final int n4 = x2 + marginLeft;
        arrX[1] = n4;
        final int n5 = w2 / 2;
        final int marginLineGiua = this.marginLineGiua;
        final int n6 = x2 + n5 + marginLineGiua;
        arrX[2] = n6;
        arrX[3] = x2 + w2 / 2 + marginLineGiua + margin * 2;
        arrX[4] = (int)(w2 / 2 + x2 - LoadDataManager.frameTitle.frameWidth);
        arrX[5] = marginLeft + x2;
        super.arrX = arrX;
        final int[] arrW = { (int)LoadDataManager.frameTabIconHanhTrang.frameWidth, n6 - n4 - margin * 2, 0, x2 + w2 - this.marginRight - arrX[2] - margin * 2, 0, 0 };
        final int width = TabScr.fontPaintTile.getWidth(SupportTranslate.getTextLangue("info").toUpperCase());
        final int margin2 = super.margin;
        arrW[4] = width + margin2 * 4;
        arrW[5] = (int)LoadDataManager.mySkillButton[1].frameWidth;
        super.arrW = arrW;
        final int n7 = (int)LoadDataManager.framePrevious.frameWidth + margin2;
        this.xMoreFromNextPreviousDefault = n7;
        this.xMoreFromNextPrevious = n7;
        final MyCommand cmdClose = super.cmdClose;
        final int y = cmdClose.y;
        final int h4 = cmdClose.h;
        final int margin3 = super.margin7;
        final int y2 = super.y;
        final int marginTop2 = this.marginTop;
        super.arrY = new int[] { y + h4 + margin3 * 2, y2 + marginTop2, y2 + marginTop2, marginTop2 + y2, y2 - margin3 - margin2, 0 };
        final int sumWBtnSkill = (int)(LoadDataManager.mySkillButton[1].frameWidth * 2.0f + margin2 * 4);
        this.sumWBtnSkill = sumWBtnSkill;
        final int[] arrX2 = super.arrX;
        final int n8 = arrX2[1];
        Res.abs((arrX2[5] = (arrW[1] - sumWBtnSkill) / 2 + n8) - n8);
        final int[] arrY = super.arrY;
        final int n9 = arrY[1];
        arrY[5] = super.margin * 3 + n9;
        final int n10 = super.arrX[1];
        final int n11 = super.arrW[1];
        final int n12 = super.arrH[1];
        this.initScroll(n10, n9, n11, n12, n12 / 3 * 5, n12);
        final SkillTemplate[] skillPaint = this.getSkillPaint(0);
        this.skillPaints = skillPaint;
        if (skillPaint != null && skillPaint.length != 0) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(" ");
            sb2.append(this.skillPaints[0].name.toUpperCase());
            sb2.append("     ");
            this.tmpSkillName = sb2.toString();
        }
        else {
            this.tmpSkillName = "      ";
        }
    }

    String initMaxWidthText(final int n, final String s) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            final StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(s.charAt(i));
            string = sb.toString();
            if (this.fontTextTrang.getWidth(string) > n) {
                return string;
            }
        }
        return string;
    }

    public String initSkillName(final String s) {
        if (s.length() > 12) {
            final StringBuilder sb = new StringBuilder();
            sb.append(s.substring(0, 10));
            sb.append("...");
            return sb.toString();
        }
        return s;
    }

    @Override
    public void keyPress(final int n) {
    }

    @Override
    public void keyTyped() {
    }

    @Override
    public void paint(final mGraphics mGraphics) {
        final String s = "MAX LV";
        if (!TabLearnSkill.isPaintButtonSkill && CanvasNinja.imgHoldToMoveTutorial == null) {
            CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameBgOrange2_0, (float)super.x, (float)super.y, (float)super.w, (float)super.h, 0, false);
            CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameTitle, super.arrX[4], super.arrY[4], super.arrW[4], false, 0, false);
            TabScr.fontPaintTile.drawString(mGraphics, "KỸ NĂNG", super.arrX[4] + super.arrW[4] / 2, super.arrY[4] + super.arrH[4] / 2, 3);
            CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameXam2, (float)super.arrX[1], (float)super.arrY[1], (float)super.arrW[1], (float)super.arrH[1], 2, false);
            String s2 = null;
            Label_1164: {
                if (Player.myCharz().clevel >= 10 || this.indexTabIconSelected == 3) {
                    if (this.skillPaints.length > 0) {
                        final int n = super.arrH[1];
                        final int margin = super.margin;
                        final int n2 = n - margin * 2;
                        final int n3 = super.arrY[1];
                        for (int i = 0; i < 3; ++i) {
                            for (int j = 0; j < 3; ++j) {
                                final int n4 = j * 3 + i;
                                final SkillTemplate[] skillPaints = this.skillPaints;
                                if (n4 >= skillPaints.length) {
                                    break;
                                }
                                final int n5 = super.arrX[1];
                                final int n6 = super.arrW[1];
                                final int n7 = i * n6 / 3;
                                final int n8 = n3 + margin + j * n2 / 3;
                                final int n9 = n5 + n7 + n6 / 6;
                                final int n10 = (int)(n8 + super.margin + LoadDataManager.khungSkill.frameHeight / 2.0f);
                                if (skillPaints != null && n4 < skillPaints.length) {
                                    final SkillTemplate skillTemplate = skillPaints[n4];
                                    if (skillTemplate != null) {
                                        final mSprite imgSkillTemplate = skillTemplate.imgSkillTemplate;
                                        if (imgSkillTemplate != null) {
                                            if (imgSkillTemplate != null) {
                                                skillTemplate.xTutorial = n9 - imgSkillTemplate.getWidth() / 2 + this.skillPaints[n4].imgSkillTemplate.getWidth() / 2;
                                                final SkillTemplate skillTemplate2 = this.skillPaints[n4];
                                                skillTemplate2.yTutorial = n10 - (int)LoadDataManager.skillIcon.frameHeight / 2 - 2 + skillTemplate2.imgSkillTemplate.getHeight() / 2;
                                            }
                                            final Image imgBgIconSkill = LoadDataManager.imgBgIconSkill;
                                            final SkillTemplate skillTemplate3 = this.skillPaints[n4];
                                            final int xTutorial = skillTemplate3.xTutorial;
                                            final int n11 = skillTemplate3.imgSkillTemplate.getWidth() / 2;
                                            final SkillTemplate skillTemplate4 = this.skillPaints[n4];
                                            mGraphics.drawImage(imgBgIconSkill, xTutorial - n11, skillTemplate4.yTutorial - skillTemplate4.imgSkillTemplate.getHeight() / 2, true);
                                            final SkillTemplate skillTemplate5 = this.skillPaints[n4];
                                            final mSprite imgSkillTemplate2 = skillTemplate5.imgSkillTemplate;
                                            final float n12 = (float)(skillTemplate5.xTutorial - imgSkillTemplate2.getWidth() / 2);
                                            final SkillTemplate skillTemplate6 = this.skillPaints[n4];
                                            mGraphics.drawSprite(imgSkillTemplate2, n12, (float)(skillTemplate6.yTutorial - skillTemplate6.imgSkillTemplate.getHeight() / 2), true);
                                        }
                                    }
                                }
                                TabLearnSkill.fontPaintSkillName.drawString(mGraphics, this.initSkillName(this.skillPaints[n4].name), n9, (int)(n8 + n2 / 3 - LoadDataManager.lvSkillUnlock.frameHeight - super.margin * 3), 3, true);
                                int level;
                                if (Player.myCharz().getSkill(this.skillPaints[n4]) == null) {
                                    level = 0;
                                }
                                else {
                                    level = Player.myCharz().getSkill(this.skillPaints[n4]).level;
                                }
                                final int maxPoint = this.skillPaints[n4].maxPoint;
                                CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.lvSkillUnlock, (int)(n9 - LoadDataManager.khungSkill.frameWidth / 2.0f), (int)(n8 + n2 / 3 - LoadDataManager.lvSkillUnlock.frameHeight - super.margin + super.margin3), (int)LoadDataManager.khungSkill.frameWidth, level == 0, true);
                                if (level != 0) {
                                    final mFont fontPaintMoney = TabLearnSkill.fontPaintMoney;
                                    final StringBuilder sb = new StringBuilder();
                                    sb.append("Lv ");
                                    sb.append(level);
                                    sb.append("/");
                                    sb.append(maxPoint);
                                    fontPaintMoney.drawString(mGraphics, sb.toString(), n9, (int)(n8 + n2 / 3 - LoadDataManager.lvSkillUnlock.frameHeight + super.margin3), 3, true);
                                }
                                else {
                                    final mFont fontPaintLockedLv = TabLearnSkill.fontPaintLockedLv;
                                    final StringBuilder sb2 = new StringBuilder();
                                    sb2.append("LV ");
                                    sb2.append(this.skillPaints[n4].skills[0].levelUnlock);
                                    fontPaintLockedLv.drawString(mGraphics, sb2.toString(), n9, (int)(n8 + n2 / 3 - LoadDataManager.lvSkillUnlock.frameHeight + super.margin3), 3, true);
                                    mGraphics.drawImage(LoadDataManager.imgCensored, (float)(n9 - this.skillPaints[n4].imgSkillTemplate.getWidth() / 2), (float)(n10 - (int)LoadDataManager.skillIcon.frameHeight / 2 - 2), 0, true, 80);
                                }
                                final SupportPaint paintz = CanvasNinja.paintz;
                                final FrameImage khungSkill = LoadDataManager.khungSkill;
                                final float n13 = (float)n9;
                                final float frameWidth = khungSkill.frameWidth;
                                paintz.paintTagFrame(mGraphics, khungSkill, (int)(n13 - frameWidth / 2.0f + 1.0f), n10 - 1 - (int)khungSkill.frameHeight / 2, (int)frameWidth, n4 == this.indexSelectedSkill, true);
                            }
                        }
                        CanvasNinja.resetTrans(mGraphics);
                        s2 = s;
                        break Label_1164;
                    }
                }
                s2 = "MAX LV";
                this.fontTextAn.drawString(mGraphics, "Bạn chưa gia nhập vào làng", super.arrW[1] / 2 + super.arrX[1], super.arrY[1] + super.arrH[1] / 2, 3);
            }
            for (int k = 0; k < this.isClickTagIcon.length; ++k) {
                CanvasNinja.paintz.paintTagFrame(mGraphics, LoadDataManager.frameTabIconHanhTrang, super.arrX[0], this.yAnimTagIcon[k] + (super.arrY[0] + (super.arrH[0] + super.margin * 2) * k), super.arrW[0], this.indexTabIconSelected != k, 0, false);
                final FrameImage frameImage = LoadDataManager.frameIconTabLearnSkill[k];
                if (frameImage != null) {
                    int n14;
                    if (this.indexTabIconSelected != k) {
                        n14 = 1;
                    }
                    else {
                        n14 = 0;
                    }
                    final float n15 = (float)super.arrX[0];
                    final float n16 = (super.arrW[0] - frameImage.frameWidth) / 2.0f;
                    final int margin2 = super.margin;
                    final float n17 = (float)margin2;
                    final int n18 = super.arrY[0];
                    final int n19 = this.yAnimTagIcon[k];
                    final int n20 = super.arrH[0];
                    frameImage.drawFrame(n14, n15 + n16 + n17, n18 + n19 + (margin2 * 2 + n20) * k + (n20 - frameImage.frameHeight) / 2.0f, mGraphics);
                }
            }
            final SupportPaint paintz2 = CanvasNinja.paintz;
            final FrameImage frameVachDoc = LoadDataManager.frameVachDoc;
            final int n21 = super.arrX[2];
            final int n22 = super.arrY[2];
            final int n23 = super.arrH[2];
            paintz2.paintLineVertical(mGraphics, frameVachDoc, n21, n22, n23 - super.margin * 2, n23, false);
            CanvasNinja.paintz.paintSingleBorderFrame(mGraphics, LoadDataManager.frameXam2, (float)super.arrX[3], (float)super.arrY[3], (float)super.arrW[3], (float)super.arrH[3], 2, false);
            super.cmdClose.paintIconOnly(mGraphics);
            String name;
            final String s3 = name = "NÂNG CẤP";
            try {
                int level2;
                if (Player.myCharz().getSkill(this.skillPaints[this.indexSelectedSkill]) == null) {
                    level2 = 0;
                }
                else {
                    name = s3;
                    level2 = Player.myCharz().getSkill(this.skillPaints[this.indexSelectedSkill]).level;
                }
                name = s3;
                final int maxPoint2 = this.skillPaints[this.indexSelectedSkill].maxPoint;
                if (level2 == 0) {
                    name = "HỌC";
                }
                else {
                    name = s3;
                    if (level2 >= maxPoint2) {
                        name = s2;
                        this.btnHoc.isDisable = true;
                        name = name;
                    }
                }
            }
            catch (Exception ex) {}
            if (this.btnHoc == null) {
                final mFont tahoma_7b_white = mFont.tahoma_7b_white;
                final FrameImage frameImage2 = LoadDataManager.myButtons[1];
                this.btnHoc = new MyButton(tahoma_7b_white, frameImage2, frameImage2, 70, 2, "NÂNG CẤP", 0, 0, new MyCommand("", 696969, this));
            }
            final MyButton btnHoc = this.btnHoc;
            if ((btnHoc.name = name) != s2) {
                btnHoc.isDisable = false;
            }
            final int n24 = super.arrX[3];
            final int n25 = super.arrW[3] / 2;
            final int n26 = btnHoc.w / 2;
            final int n27 = super.arrY[3] + super.arrH[3] + super.margin;
            btnHoc.setXY(n24 + n25 - n26, n27);
            if (this.isPaintBtnHoc) {
                this.btnHoc.paintButton(mGraphics, false);
            }
            final mFont fontPaintTagName = TabLearnSkill.fontPaintTagName;
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("Điểm kỹ năng: ");
            sb3.append(Player.myCharz().charInfo.skillPt);
            fontPaintTagName.drawString(mGraphics, sb3.toString(), super.arrX[1], n27 + this.btnHoc.h / 2, 4);
            final SkillTemplate[] skillPaints2 = this.skillPaints;
            if (skillPaints2.length > 0 && this.indexSelectedSkill < skillPaints2.length && (Player.myCharz().clevel >= 10 || this.indexTabIconSelected == 3)) {
                this.paintSkillInfo(mGraphics);
                this.isPaintBtnHoc = true;
            }
            else {
                this.fontTextAn.drawString(mGraphics, SupportTranslate.getTextLangue("notInfo"), super.arrW[3] / 2 + super.arrX[3], super.arrY[3] + super.arrH[3] / 2, 3);
                this.isPaintBtnHoc = false;
            }
            if (this.indexTabIconSelected == 4 && NinjaMidlet.isPC) {
                this.paintTabGanSkill(mGraphics);
            }
        }
    }

    public void paintTabGanSkill(final mGraphics mGraphics) {
        this.indexTemp = 0;
        this.keyTemp = "";
        for (int i = 1; i <= 2; ++i) {
            MapScr.myButtonSkills.get(i).frameImg.drawFrame(0, (float)(super.arrX[5] + this.indexTemp * (super.arrW[5] + super.margin * 4)), (float)super.arrY[5], mGraphics);
            final String nameKey = KEY.getNameKey(MapScr.myButtonSkills.get(i).keySkill);
            this.keyTemp = nameKey;
            if (nameKey.length() > 2) {
                final mFont tahoma_7b_red_small = mFont.tahoma_7b_red_small;
                final String keyTemp = this.keyTemp;
                final int n = super.arrX[5];
                final int indexTemp = this.indexTemp;
                final int n2 = super.arrW[5];
                tahoma_7b_red_small.drawString(mGraphics, keyTemp, n + indexTemp * (super.margin * 4 + n2) + n2 / 2, super.arrH[5] / 2 + super.arrY[5], 3);
            }
            else {
                final mFont tahoma_7b_red = mFont.tahoma_7b_red;
                final String keyTemp2 = this.keyTemp;
                final int n3 = super.arrX[5];
                final int indexTemp2 = this.indexTemp;
                final int n4 = super.arrW[5];
                tahoma_7b_red.drawString(mGraphics, keyTemp2, n3 + indexTemp2 * (super.margin * 4 + n4) + n4 / 2, super.arrH[5] / 2 + super.arrY[5], 3);
            }
            ++this.indexTemp;
        }
        this.indexTemp = 0;
        for (int j = 3; j <= 4; ++j) {
            final FrameImage frameImg = MapScr.myButtonSkills.get(j).frameImg;
            final int n5 = super.arrX[5];
            final int indexTemp3 = this.indexTemp;
            final int n6 = super.arrW[5];
            final int margin = super.margin;
            frameImg.drawFrame(0, (float)(n5 + indexTemp3 * (n6 + margin * 4)), (float)(super.arrY[5] + super.arrH[5] + margin * 2), mGraphics);
            final String nameKey2 = KEY.getNameKey(MapScr.myButtonSkills.get(j).keySkill);
            this.keyTemp = nameKey2;
            if (nameKey2.length() > 2) {
                final mFont tahoma_7b_red_small2 = mFont.tahoma_7b_red_small;
                final String keyTemp3 = this.keyTemp;
                final int n7 = super.arrX[5];
                final int indexTemp4 = this.indexTemp;
                final int n8 = super.arrW[5];
                final int margin2 = super.margin;
                final int n9 = n8 / 2;
                final int n10 = super.arrY[5];
                final int n11 = super.arrH[5];
                tahoma_7b_red_small2.drawString(mGraphics, keyTemp3, n7 + indexTemp4 * (margin2 * 4 + n8) + n9, n11 / 2 + (n10 + n11 + margin2 * 2), 3);
            }
            else {
                final mFont tahoma_7b_red2 = mFont.tahoma_7b_red;
                final String keyTemp4 = this.keyTemp;
                final int n12 = super.arrX[5];
                final int indexTemp5 = this.indexTemp;
                final int n13 = super.arrW[5];
                final int margin3 = super.margin;
                final int n14 = n13 / 2;
                final int n15 = super.arrY[5];
                final int n16 = super.arrH[5];
                tahoma_7b_red2.drawString(mGraphics, keyTemp4, n12 + indexTemp5 * (margin3 * 4 + n13) + n14, n16 / 2 + (n15 + n16 + margin3 * 2), 3);
            }
            ++this.indexTemp;
        }
        MapScr.myButtonSkills.get(5).frameImg.drawFrame(0, super.arrX[5] + (this.sumWBtnSkill - MapScr.myButtonSkills.get(5).frameImg.frameWidth) / 2.0f, (float)(super.arrY[5] + super.arrH[5] * 2 + super.margin * 4), mGraphics);
        final String nameKey3 = KEY.getNameKey(MapScr.myButtonSkills.get(5).keySkill);
        this.keyTemp = nameKey3;
        if (nameKey3.length() > 2) {
            final mFont tahoma_7b_red_small3 = mFont.tahoma_7b_red_small;
            final String keyTemp5 = this.keyTemp;
            final float n17 = (float)super.arrX[5];
            final float n18 = (this.sumWBtnSkill - MapScr.myButtonSkills.get(5).frameImg.frameWidth) / 2.0f;
            final float n19 = (float)(super.arrW[5] / 2);
            final int n20 = super.arrY[5];
            final int n21 = super.arrH[5];
            tahoma_7b_red_small3.drawString(mGraphics, keyTemp5, n19 + (n17 + n18), n21 / 2 + (n20 + n21 * 2 + super.margin * 4), 3);
        }
        else {
            final mFont tahoma_7b_red3 = mFont.tahoma_7b_red;
            final String keyTemp6 = this.keyTemp;
            final float n22 = (float)super.arrX[5];
            final float n23 = (this.sumWBtnSkill - MapScr.myButtonSkills.get(5).frameImg.frameWidth) / 2.0f;
            final float n24 = (float)(super.arrW[5] / 2);
            final int n25 = super.arrY[5];
            final int n26 = super.arrH[5];
            tahoma_7b_red3.drawString(mGraphics, keyTemp6, n24 + (n22 + n23), n26 / 2 + (n25 + n26 * 2 + super.margin * 4), 3);
        }
        MapScr.myButtonSkills.get(0).frameImg.drawFrame(0, super.arrX[5] + (this.sumWBtnSkill - MapScr.myButtonSkills.get(0).frameImg.frameWidth) / 2.0f, (float)(super.arrY[5] + super.arrH[5] * 3 + super.margin * 6), mGraphics);
        final String nameKey4 = KEY.getNameKey(MapScr.myButtonSkills.get(0).keySkill);
        this.keyTemp = nameKey4;
        if (nameKey4.length() > 2) {
            mFont.tahoma_7b_red_small.drawString(mGraphics, this.keyTemp, MapScr.myButtonSkills.get(0).frameImg.frameWidth / 2.0f + (super.arrX[5] + (this.sumWBtnSkill - MapScr.myButtonSkills.get(0).frameImg.frameWidth) / 2.0f), MapScr.myButtonSkills.get(0).frameImg.frameHeight / 2.0f + (super.arrY[5] + super.arrH[5] * 3 + super.margin * 6), 3);
        }
        else {
            mFont.tahoma_7b_red.drawString(mGraphics, this.keyTemp, MapScr.myButtonSkills.get(0).frameImg.frameWidth / 2.0f + (super.arrX[5] + (this.sumWBtnSkill - MapScr.myButtonSkills.get(0).frameImg.frameWidth) / 2.0f), MapScr.myButtonSkills.get(0).frameImg.frameHeight / 2.0f + (super.arrY[5] + super.arrH[5] * 3 + super.margin * 6), 3);
        }
    }

    public void resetSelected() {
        this.indexSelectedInven = -1;
        this.indexSelectedUsed = -1;
        this.indexTabIconSelected = 0;
        this.indexTagNameSelected = 0;
    }

    public String scrollText(final String s) {
        if (s.isEmpty()) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s.substring(1));
        sb.append(s.charAt(0));
        return sb.toString();
    }

    @Override
    public void show() {
        this.init();
        this.showTab();
        TabLearnSkill.me = this;
    }

    @Override
    public void update() {
        final MyButton btnHoc = this.btnHoc;
        if (btnHoc != null) {
            btnHoc.update();
        }
        final String tmpSkillName = this.tmpSkillName;
        if (tmpSkillName.substring(tmpSkillName.length() - 5).equals("     ") && this.tmpSkillName.charAt(0) == ' ') {
            ++this.count;
        }
        if (this.count > 10) {
            this.count = 0;
        }
        if (this.count == 0 && CanvasNinja.gameTick % 10 == 0) {
            this.tmpSkillName = this.scrollText(this.tmpSkillName);
        }
    }

    @Override
    public void updateKey() {
        if (this.indexTabIconSelected == 4) {
            this.updatePointerTabGanSkill();
        }
        this.updatePointer();
        if (CanvasNinja.isPointerRelease()) {
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    final int n = super.arrX[1];
                    final int n2 = super.arrW[1];
                    final int n3 = i * n2 / 3;
                    final int n4 = super.arrY[1];
                    final int n5 = super.arrH[1];
                    if (CanvasNinja.isPoint(n + n3, n4 + j * n5 / 3, n2 / 3, n5 / 3)) {
                        this.indexSelectedSkill = j * 3 + i;
                        try {
                            this.isClickSpace = false;
                            final StringBuilder sb = new StringBuilder();
                            sb.append(" ");
                            sb.append(this.skillPaints[this.indexSelectedSkill].name.toUpperCase());
                            sb.append("     ");
                            this.tmpSkillName = sb.toString();
                        }
                        catch (Exception ex) {
                            this.isClickSpace = true;
                            Res.outz("click vao khoang trong");
                        }
                    }
                }
            }
        }
        if (CanvasNinja.isPointerFirstClick) {
            for (int k = 0; k < 3; ++k) {
                for (int l = 0; l < 3; ++l) {
                    final int indexSelectedSkill = l * 3 + k;
                    final int n6 = super.arrX[1];
                    final int n7 = super.arrW[1];
                    final int n8 = k * n7 / 3;
                    final int n9 = super.arrY[1];
                    final int n10 = super.arrH[1];
                    if (CanvasNinja.isPoint(n6 + n8, n9 + l * n10 / 3, n7 / 3, n10 / 3)) {
                        AudioManager.buttonClick();
                        this.isClickSkill[indexSelectedSkill] = true;
                        this.indexSelectedSkill = indexSelectedSkill;
                        CanvasNinja.clearAllPointer();
                        try {
                            if (this.imgTempHoldMove == null && Player.myCharz().getSkill(this.skillPaints[this.indexSelectedSkill]) != null) {
                                final SkillTemplate[] skillPaints = this.skillPaints;
                                if (indexSelectedSkill < skillPaints.length) {
                                    this.imgTempHoldMove = skillPaints[indexSelectedSkill].imgSkillTemplate;
                                }
                                CanvasNinja.isPointerFirstClick = false;
                            }
                        }
                        catch (Exception ex2) {
                            this.isPaintBtnHoc = false;
                        }
                    }
                }
            }
        }
        if (CanvasNinja.isPointerDraggedX || CanvasNinja.isPointerDraggedY) {
            final mSprite imgTempHoldMove = this.imgTempHoldMove;
            if (imgTempHoldMove != null) {
                TabLearnSkill.isPaintButtonSkill = true;
                CanvasNinja.imgHoldToMove = imgTempHoldMove;
            }
        }
        if (CanvasNinja.isPointerRelease && this.imgTempHoldMove != null) {
            this.imgTempHoldMove = null;
            CanvasNinja.imgHoldToMove = null;
            CanvasNinja.isPointerDraggedX = false;
            CanvasNinja.isPointerDraggedY = false;
            CanvasNinja.isPointerRelease = false;
            TabLearnSkill.isPaintButtonSkill = false;
            for (final MyButtonSkill myButtonSkill : MapScr.myButtonSkills) {
                if (CanvasNinja.isPoint(myButtonSkill.x, myButtonSkill.y, myButtonSkill.w, myButtonSkill.h)) {
                    if (!myButtonSkill.isSkillUse() || this.indexSelectedSkill >= this.skillPaints.length) {
                        break;
                    }
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append("Gán skill vào ô index:");
                    sb2.append(myButtonSkill.indexBtnSkill);
                    sb2.append("_ID skill là:");
                    sb2.append(Player.myCharz().getSkill(this.skillPaints[this.indexSelectedSkill]).skillId);
                    Res.outz(2, sb2.toString());
                    SendMessage.gI().requestAddSkill(myButtonSkill.indexBtnSkill, Player.myCharz().getSkill(this.skillPaints[this.indexSelectedSkill]).skillId);
                    if (MapScr.gI().tutorial != null && MapScr.gI().tutorial.isStepGanSkill(6)) {
                        MapScr.gI().tutorial.step = 7;
                        break;
                    }
                    break;
                }
            }
        }
        super.cmdClose.updateIconOnly();
        if (CanvasNinja.isPointerRelease() && CanvasNinja.imgHoldToMove == null && CanvasNinja.isPointerRelease() && CanvasNinja.imgHoldToMove == null) {
            for (int indexTabIconSelected = 0; indexTabIconSelected < this.isClickTagIcon.length; ++indexTabIconSelected) {
                final int n11 = super.arrX[0];
                final int n12 = super.arrY[0];
                final int n13 = super.arrH[0];
                if (CanvasNinja.isPoint(n11, n12 + (super.margin * 2 + n13) * indexTabIconSelected, super.arrW[0], n13)) {
                    CanvasNinja.clearAllPointer2();
                    AudioManager.buttonClick();
                    this.isClickTagIcon[indexTabIconSelected] = true;
                    this.indexTabIconSelected = indexTabIconSelected;
                    final SkillTemplate[] skillPaint = this.getSkillPaint(indexTabIconSelected);
                    this.skillPaints = skillPaint;
                    if (skillPaint.length != 0) {
                        this.indexSelectedSkill = 0;
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append(" ");
                        sb3.append(this.skillPaints[this.indexSelectedSkill].name.toUpperCase());
                        sb3.append("     ");
                        this.tmpSkillName = sb3.toString();
                    }
                }
            }
        }
    }

    public void updatePointer() {
        final MyButton btnHoc = this.btnHoc;
        if (btnHoc != null) {
            btnHoc.updatePointer();
        }
    }

    public void updatePointerTabGanSkill() {
        if (CanvasNinja.isPointerRelease) {
            int n = 0;
            for (int i = 1; i <= 2; ++i) {
                final int n2 = super.arrX[5];
                final int n3 = super.arrW[5];
                if (CanvasNinja.isPoint(n2 + (super.margin * 4 + n3) * n, super.arrY[5], n3, super.arrH[5])) {
                    CanvasNinja.clearAllPointer();
                    final MyCommand myCommand = new MyCommand(SupportTranslate.getTextLangue("OK"), 10, this);
                    myCommand.subAction = i;
                    InputDialog.gI().startInputDlg(SupportTranslate.getTextLangue("inputYourKey"), 6, myCommand, true, 1, true, MapScr.myButtonSkills.get(i).keySkill);
                }
                ++n;
            }
        }
        if (CanvasNinja.isPointerRelease) {
            int n4 = 0;
            for (int j = 3; j <= 4; ++j) {
                final int n5 = super.arrX[5];
                final int n6 = super.arrW[5];
                final int margin = super.margin;
                final int n7 = super.arrY[5];
                final int n8 = super.arrH[5];
                if (CanvasNinja.isPoint(n5 + (margin * 4 + n6) * n4, n7 + n8 + margin * 2, n6, n8)) {
                    CanvasNinja.clearAllPointer();
                    final MyCommand myCommand2 = new MyCommand(SupportTranslate.getTextLangue("OK"), 10, this);
                    myCommand2.subAction = j;
                    InputDialog.gI().startInputDlg(SupportTranslate.getTextLangue("inputYourKey"), 6, myCommand2, true, 1, true, MapScr.myButtonSkills.get(j).keySkill);
                }
                ++n4;
            }
        }
        if (CanvasNinja.isPointerRelease) {
            final int n9 = super.arrX[5];
            final int n10 = (this.sumWBtnSkill - (int)MapScr.myButtonSkills.get(5).frameImg.frameWidth) / 2;
            final int n11 = super.arrY[5];
            final int n12 = super.arrH[5];
            if (CanvasNinja.isPoint(n9 + n10, n11 + n12 * 2 + super.margin * 4, super.arrW[5], n12)) {
                CanvasNinja.clearAllPointer();
                final MyCommand myCommand3 = new MyCommand(SupportTranslate.getTextLangue("OK"), 10, this);
                myCommand3.subAction = 5;
                InputDialog.gI().startInputDlg(SupportTranslate.getTextLangue("inputYourKey"), 6, myCommand3, true, 1, true, MapScr.myButtonSkills.get(5).keySkill);
            }
        }
        if (CanvasNinja.isPointerRelease && CanvasNinja.isPoint(super.arrX[5] + (this.sumWBtnSkill - (int)MapScr.myButtonSkills.get(0).frameImg.frameWidth) / 2, super.arrY[5] + super.arrH[5] * 3 + super.margin * 6, (int)MapScr.myButtonSkills.get(0).frameImg.frameWidth, (int)MapScr.myButtonSkills.get(0).frameImg.frameHeight)) {
            CanvasNinja.clearAllPointer();
            final MyCommand myCommand4 = new MyCommand(SupportTranslate.getTextLangue("OK"), 10, this);
            myCommand4.subAction = 0;
            InputDialog.gI().startInputDlg(SupportTranslate.getTextLangue("inputYourKey"), 6, myCommand4, true, 1, true, MapScr.myButtonSkills.get(0).keySkill);
        }
    }

    public String wrapText(String s, final int n) {
        final StringBuilder sb = new StringBuilder();
        final String[] split = s.split("\\s+");
        int n2 = 0;
        for (int length = split.length, i = 0; i < length; ++i) {
            s = split[i];
            final int width = this.fontTextNau.getWidth(s);
            int n3 = n2;
            if (n2 + width > n) {
                sb.append("\n");
                n3 = 0;
            }
            sb.append(s);
            sb.append(" ");
            n2 = n3 + (this.fontTextNau.getWidth(" ") + width);
        }
        return sb.toString();
    }
}
