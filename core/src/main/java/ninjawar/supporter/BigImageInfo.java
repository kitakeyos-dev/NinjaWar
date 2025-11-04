package ninjawar.supporter;

import ninjawar.mylib.Image;

public class BigImageInfo {
    public byte[] dataBig;
    public int idBig;
    public Image imgBig;
    public String key = "";
    public int typeBig;

    public BigImageInfo(Image imgBig2, byte[] dataBig2, int idBig2, int typeBig2) {
        this.imgBig = imgBig2;
        this.dataBig = dataBig2;
        this.idBig = idBig2;
        this.typeBig = typeBig2;
        this.key = this.typeBig + "" + this.idBig;
    }

    public String getKey() {
        return this.key;
    }
}
