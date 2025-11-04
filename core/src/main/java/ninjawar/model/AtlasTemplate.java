package ninjawar.model;

public class AtlasTemplate {
    public int idAtlas;
    public String nameAtlas = "";
    public String[] nameImgs;
    public int numImgAtlas;
    public int version;

    public AtlasTemplate(int version2, int idAtlas2, String nameAtlas2, int numImgAtlas2) {
        this.version = version2;
        this.idAtlas = (short) idAtlas2;
        this.nameAtlas = nameAtlas2;
        this.numImgAtlas = numImgAtlas2;
        String[] strArr = new String[numImgAtlas2];
        this.nameImgs = strArr;
        if (numImgAtlas2 > 0) {
            strArr[0] = nameAtlas2;
            for (int i = 1; i < numImgAtlas2; i++) {
                String[] strArr2 = this.nameImgs;
                strArr2[i] = nameAtlas2 + (i + 1);
            }
        }
    }
}
