package ninjawar.template;

public class TypeItemtemplate {
    public String nameType = "";
    public byte type;

    public TypeItemtemplate() {
    }

    public TypeItemtemplate(int type2, String nameType2) {
        this.type = (byte) type2;
        this.nameType = nameType2;
    }
}
