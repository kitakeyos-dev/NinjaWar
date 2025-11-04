package ninjawar.model;

public class TinhLuyenTemplate {
    public int id;
    public byte lv;
    public Material material;
    public int money;
    public byte moneyType;
    public byte optionNew;
    public byte rate;

    public TinhLuyenTemplate(int id2, byte lv2, byte optionNew2, byte rate2, byte moneyType2, int money2, short idItem, int num, String hsd) {
        this.id = id2;
        this.lv = lv2;
        this.optionNew = optionNew2;
        this.rate = rate2;
        this.moneyType = moneyType2;
        this.money = money2;
        this.material = new Material(idItem, num, hsd);
    }
}
