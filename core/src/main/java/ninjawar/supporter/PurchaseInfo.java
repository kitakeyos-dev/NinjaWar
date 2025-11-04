package ninjawar.supporter;

import ninjawar.model.ProductPurchase;

public class PurchaseInfo {
    public int idTab;
    public String nameTab = "";
    public ProductPurchase[] products;

    public PurchaseInfo(int idTab2, String nameTab2, ProductPurchase[] products2) {
        this.idTab = idTab2;
        this.nameTab = nameTab2;
        this.products = products2;
    }
}
