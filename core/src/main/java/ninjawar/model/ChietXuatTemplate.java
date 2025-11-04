package ninjawar.model;

public class ChietXuatTemplate {
    public int id;
    public byte lv;
    public Material[] materials;
    public int money;
    public byte moneyType;
    public Material product;
    public byte rate;

    public ChietXuatTemplate(int id2, byte lv2, byte rate2, byte moneyType2, int money2, Material[] materials2, Material product2) {
        this.id = id2;
        this.lv = lv2;
        this.rate = rate2;
        this.moneyType = moneyType2;
        this.money = money2;
        this.materials = materials2;
        this.product = product2;
    }
}
