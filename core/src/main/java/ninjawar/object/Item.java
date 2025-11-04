package ninjawar.object;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import ninjawar.model.mItemOption;
import ninjawar.mylib.FrameAtlas;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.myscr.Res;
import ninjawar.supporter.GrayscaleShader;
import ninjawar.template.ItemTemplate;
import ninjawar.template.ItemTemplates;

public class Item {
    public byte durability = -1;
    int h;
    public String hsd = "";
    public int id;
    public int idTemplate;
    public boolean isBlock;
    public boolean isFocusTutorial;
    public int levelRefined;
    public int lvChietXuat;
    public short lvRequired;
    public byte lvUpgrade = 1;
    public mItemOption[] mItemOption = new mItemOption[0];
    public byte maxDurability = -1;
    public int num;
    public int[] price;
    public byte rarity = -1;
    public int sys;
    public ItemTemplate template;
    public byte[] typeMoneyServer;
    int w;
    int x;
    public int xTutorial;
    int y;
    public int yTutorial;

    public Item() {
    }

    public Item(int id2, int idTemplate2) {
        this.id = id2;
        this.idTemplate = idTemplate2;
        if (idTemplate2 != -32768) {
            this.template = ItemTemplates.get(idTemplate2) != null ? ItemTemplates.get(idTemplate2).clones() : null;
        }
    }

    public Item(int id2, int idTemplate2, byte rarity2, byte lvUpgrade2, short lvRequired2, boolean isBlock2, int num2, String hsd2, byte sys2) {
        init(id2, idTemplate2, rarity2, lvUpgrade2, lvRequired2, isBlock2, num2, hsd2, sys2, this.typeMoneyServer, this.price);
    }

    public Item(int id2, int idTemplate2, byte rarity2, byte lvUpgrade2, short lvRequired2, boolean isBlock2, int num2, String hsd2, byte sys2, byte[] typeMoney, int[] price2) {
        init(id2, idTemplate2, rarity2, lvUpgrade2, lvRequired2, isBlock2, num2, hsd2, sys2, typeMoney, price2);
    }

    public void init(int id2, int idTemplate2, byte rarity2, byte lvUpgrade2, short lvRequired2, boolean isBlock2, int num2, String hsd2, byte sys2, byte[] typeMoney, int[] price2) {
        this.id = id2;
        this.idTemplate = idTemplate2;
        this.lvRequired = lvRequired2;
        this.rarity = rarity2;
        this.lvUpgrade = lvUpgrade2;
        this.template = ItemTemplates.get(idTemplate2) != null ? ItemTemplates.get(idTemplate2).clones() : null;
        this.isBlock = isBlock2;
        this.num = num2;
        this.hsd = hsd2;
        this.sys = sys2;
        this.typeMoneyServer = typeMoney;
        Res.outz(3, this.template.name + "_typeMoneyServer:" + this.typeMoneyServer);
        this.price = price2;
    }

    public Item clones() {
        Item item = new Item();
        item.id = this.id;
        item.idTemplate = this.idTemplate;
        item.lvRequired = this.lvRequired;
        item.rarity = this.rarity;
        item.lvUpgrade = this.lvUpgrade;
        item.template = ItemTemplates.get(this.idTemplate) != null ? ItemTemplates.get(this.idTemplate).clones() : null;
        item.isBlock = this.isBlock;
        item.num = this.num;
        item.hsd = this.hsd;
        item.sys = this.sys;
        item.typeMoneyServer = this.typeMoneyServer;
        item.price = this.price;
        return item;
    }

    public mFont getFontByRarity() {
        mFont font = mFont.tahoma_7_white;
        switch (this.rarity) {
            case 0:
                return mFont.tahoma_7_info_xanh_la;
            case 1:
                return mFont.tahoma_7_info_xian;
            case 2:
                return mFont.tahoma_7_info_tim;
            case 3:
                return mFont.tahoma_7_info_do;
            case 4:
                return mFont.tahoma_7_info_cam;
            default:
                return font;
        }
    }

    public mFont getFontByHe() {
        mFont font = mFont.tahoma_7_white;
        switch (this.sys) {
            case 1:
                return mFont.tahoma_7_info_do;
            case 2:
                return mFont.tahoma_7_info_blue;
            case 3:
                return mFont.tahoma_7_info_vang;
            case 4:
                return mFont.tahoma_7_info_cam;
            case 5:
                return mFont.tahoma_7_info_xian;
            default:
                return font;
        }
    }

    public boolean isDisableClick() {
        return this.idTemplate < 0;
    }

    public Item(int idTemplate2) {
        this.idTemplate = idTemplate2;
        this.template = ItemTemplates.get(idTemplate2).clones();
    }

    public void paintCenter(mGraphics g, int x2, int y2, boolean useClip) {
        if (this.template != null) {
            this.x = x2;
            this.y = y2;
            this.w = getW();
            this.h = getH();
            this.template.paintCenter(g, x2, y2, useClip);
        }
    }

    public void paintCenter(mGraphics g, int x2, int y2, int wBox, int hBox, boolean useClip, boolean isPaintNum) {
        mGraphics mgraphics = g;
        if (this.id >= 0 && this.template != null) {
            this.xTutorial = ((wBox - getW()) / 2) + x2 + (getW() / 2);
            this.yTutorial = ((hBox - getH()) / 2) + y2 + (getH() / 2);
            if (this.durability <= 0 && this.maxDurability != -1) {
                g.setShader(GrayscaleShader.grayscaleShader);
            }
            this.template.paint(g, x2, y2, wBox, hBox, this.num, useClip, isPaintNum);
            g.setShader((ShaderProgram) null);
        }
    }

    public int getH() {
        ItemTemplate itemTemplate = this.template;
        if (itemTemplate != null) {
            return itemTemplate.h;
        }
        return 40;
    }

    public int getW() {
        ItemTemplate itemTemplate = this.template;
        if (itemTemplate != null) {
            return itemTemplate.w;
        }
        return 40;
    }

    public boolean isKhoaiTayChien() {
        return this.idTemplate == 264;
    }

    public boolean isCanCatKho() {
        return true;
    }

    public String getRealName() {
        ItemTemplate itemTemplate = this.template;
        String nameItem = itemTemplate != null ? itemTemplate.name : "null";
        if (this.lvUpgrade <= 0) {
            return nameItem;
        }
        return nameItem + " (+" + this.lvUpgrade + ")";
    }

    public FrameAtlas getImage() {
        ItemTemplate itemTemplate = this.template;
        if (itemTemplate != null) {
            return itemTemplate.frameAtlas;
        }
        return null;
    }
}
