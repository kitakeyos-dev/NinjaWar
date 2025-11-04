package ninjawar.model;

public class UpgradeTemplate {
    public int id;
    public byte lv;
    public byte materialCount;
    public int money;
    public byte moneyType;
    public byte optionIncrease;
    public byte rate;

    public UpgradeTemplate(int id2, byte lv2, byte optionIncrease2, byte rate2, byte materialCount2, byte moneyType2, int money2) {
        this.id = id2;
        this.lv = lv2;
        this.optionIncrease = optionIncrease2;
        this.rate = rate2;
        this.materialCount = materialCount2;
        this.moneyType = moneyType2;
        this.money = money2;
    }
}
