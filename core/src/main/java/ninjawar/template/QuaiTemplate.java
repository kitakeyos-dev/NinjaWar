package ninjawar.template;

import ninjawar.model.OptionTemplate;
import ninjawar.mylib.Atlas;
import ninjawar.mylib.FrameAtlas;
import ninjawar.mylib.mGraphics;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.supporter.ABossInfo;

public class QuaiTemplate {
    public static int[][] FRAME_BOSS_DEFAULT = {new int[]{0, 1, 2, 3, 4, 5}, new int[]{6, 7, 8, 9, 10, 11}, new int[]{24, 25}, new int[]{24, 25, 26, 27, 28, 29, 29, 29, 29, 29, 29}, new int[]{0, 14, 15, 16, 17, 18, 0, 1, 2, 3, 4, 5}, new int[]{0, 19, 20, 21, 22, 23, 19, 0, 1, 2, 3, 4, 5}, new int[]{11, 7}, new int[]{12, 13}};
    public static int[][] FRAME_MOB_DEFAULT = {new int[]{0, 1, 0, 1}, new int[]{2, 3, 4, 5}, new int[]{7, 7, 7, 7}, new int[]{7, 7, 7, 7}, new int[]{6, 6, 6, 6}};
    public int[][] FRAME_MOB;
    public short[] ID_LAYERS;
    public ABossInfo aBossInfo;
    public FrameAtlas frameAtlas;
    public int hReal = -1;
    public int hpMax;
    public short idIcon;
    public int index;
    boolean isSendRequestImg = false;
    public String name = "";
    public int numFrame = 1;
    public OptionTemplate optionMobTemplate;
    public String optionMobTemplateStr;
    int percentOpacityDie = 50;
    public byte rangeMove;
    float sizeNhun = 0.6666667f;
    public byte speed;
    public String[] talkRandoms;
    public String talkSomeThing = "";
    public byte type;
    public int wReal = -1;

    public boolean isUsePart() {
        return this.aBossInfo != null;
    }

    public void paint(mGraphics g, int action, int x, int y, int dir, boolean isDie, int pivot) {
        int i = dir;
        int i2 = pivot;
        try {
            if (this.index > this.FRAME_MOB[action].length - 1) {
                this.index = 0;
            }
            byte b = 2;
            if (isUsePart()) {
                ABossInfo aBossInfo2 = this.aBossInfo;
                int i3 = this.FRAME_MOB[action][this.index];
                if (i != -1) {
                    b = 3;
                }
                aBossInfo2.paint(g, x, y, i3, b, (byte) -1, this.percentOpacityDie, isDie, false, Player.myCharz().isGetSharigan);
                int i4 = y;
                return;
            }
            FrameAtlas frameAtlas2 = this.frameAtlas;
            if (frameAtlas2 != null) {
                float f = frameAtlas2.frameWidth;
                int i5 = i2 - (x - (((int) f) / 2));
                try {
                    frameAtlas2.drawFrame(g, this.FRAME_MOB[action][this.index], ((float) i2) - (f / 2.0f), ((float) y) - frameAtlas2.frameHeight, 0, i == -1 ? 0 : 2, false, isDie, this.percentOpacityDie, Player.myCharz().isGetSharigan);
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                }
            } else {
                int i6 = y;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int getWidth() {
        int w = 50;
        FrameAtlas frameAtlas2 = this.frameAtlas;
        if (frameAtlas2 != null) {
            w = (int) frameAtlas2.frameWidth;
        }
        if (isUsePart()) {
            return this.aBossInfo.getW();
        }
        return w;
    }

    public int getHeight() {
        int h = 110;
        FrameAtlas frameAtlas2 = this.frameAtlas;
        if (frameAtlas2 != null) {
            h = (int) frameAtlas2.frameHeight;
        }
        if (isUsePart()) {
            return this.aBossInfo.getH();
        }
        return h;
    }

    public static QuaiTemplate get(int idTemplate) {
        if (MobTemplates.get(idTemplate) != null) {
            return MobTemplates.get(idTemplate).clones();
        }
        return null;
    }

    public QuaiTemplate clones() {
        QuaiTemplate result = new QuaiTemplate();
        result.idIcon = this.idIcon;
        result.type = this.type;
        result.name = this.name;
        result.hpMax = this.hpMax;
        result.rangeMove = this.rangeMove;
        result.speed = this.speed;
        result.numFrame = this.numFrame;
        int sizeAction = this.FRAME_MOB.length;
        result.FRAME_MOB = new int[sizeAction][];
        for (int j = 0; j < sizeAction; j++) {
            int sizeFrame = this.FRAME_MOB[j].length;
            result.FRAME_MOB[j] = new int[sizeFrame];
            for (int k = 0; k < sizeFrame; k++) {
                result.FRAME_MOB[j][k] = this.FRAME_MOB[j][k];
            }
        }
        result.ID_LAYERS = this.ID_LAYERS;
        result.createTemplate();
        result.optionMobTemplateStr = this.optionMobTemplateStr;
        result.optionMobTemplate = this.optionMobTemplate;
        result.talkRandoms = this.talkRandoms;
        return result;
    }

    public void createTemplate() {
        short[] sArr = this.ID_LAYERS;
        if (sArr == null || sArr.length <= 0) {
            Res.outz(1, "zzz debug createTemplate IdIcon zzzzzzzzz:" + this.idIcon);
            Atlas atlas = Atlas.createAtlas((short) 0, (short) 23, (short) -1);
            if (atlas != null) {
                this.frameAtlas = new FrameAtlas(atlas, this.idIcon + "", this.numFrame, true);
                return;
            }
            return;
        }
        this.aBossInfo = new ABossInfo(this.ID_LAYERS);
    }

    public void setOption() {
        String[] str = Res.splitByKey(this.optionMobTemplateStr, ";");
        if (str != null && !str[0].equals("")) {
            this.optionMobTemplate = new OptionTemplate();
            OptionTemplate[] temps = new OptionTemplate[str.length];
            for (int i = 0; i < temps.length; i++) {
                temps[i] = new OptionTemplate();
                String[] keyValue = Res.splitByKey(str[i], ",");
                temps[i].id = Res.convertStringToNumber(keyValue[0]);
                temps[i].value = Res.convertStringToNumber(keyValue[1]);
            }
            this.optionMobTemplate.offSetX = OptionTemplate.findValueById(0, temps);
            this.optionMobTemplate.offSetY = OptionTemplate.findValueById(1, temps);
            this.optionMobTemplate.typeShadow = OptionTemplate.findValueById(2, temps);
            this.optionMobTemplate.offSetXQuai = OptionTemplate.findValueById(3, temps);
            this.optionMobTemplate.offSetYQuai = OptionTemplate.findValueById(4, temps);
            this.optionMobTemplate.typeInteract = (byte) OptionTemplate.findValueById(5, temps);
        }
    }
}
