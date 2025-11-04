package ninjawar.screen.tab;

import ninjawar.message.SendMessage;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;
import ninjawar.supporter.SupportTranslate;

public class TabShopThoRen extends TabShop {
    public static TabShopThoRen me;
    public int idItemSelected = -1;

    public static TabShopThoRen gI() {
        if (me == null) {
            me = new TabShopThoRen();
        }
        return me;
    }

    public void startTabShop() {
        super.startTabShop(SupportTranslate.getTextLangue("SHOP_THO_REN"), true, false, true);
    }

    public void paint(mGraphics g) {
        super.paint(g);
    }

    public void update() {
        super.update();
    }

    public void commandTab(int index, int subIndex) {
        CanvasNinja.clearAllPointer();
        super.commandTab(index, subIndex);
        switch (index) {
            case 60:
                Res.outz("Sua do index: " + this.box.item.getRealName());
                this.idItemSelected = this.box.item.id;
                SendMessage.gI().repairItem(this.idItemSelected);
                return;
            case 61:
                Res.outz("Khôi phục đồ index" + this.box.item.getRealName());
                this.idItemSelected = this.box.item.id;
                SendMessage.gI().restoreItem(this.idItemSelected);
                return;
            default:
                return;
        }
    }
}
