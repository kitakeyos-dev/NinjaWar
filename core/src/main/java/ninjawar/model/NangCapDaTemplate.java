package ninjawar.model;

public class NangCapDaTemplate {
    public int idItem;
    public int index;
    public Material[] materials;
    public int money;
    public byte moneyType;
    public Material product;
    public byte rate;

    public NangCapDaTemplate(int index2, short idItem2, byte rate2, byte moneyType2, int money2, Material[] materials2, Material product2) {
        this.index = index2;
        this.idItem = idItem2;
        this.rate = rate2;
        this.moneyType = moneyType2;
        this.money = money2;
        this.materials = materials2;
        this.product = product2;
    }
}
