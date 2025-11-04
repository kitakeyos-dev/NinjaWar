package ninjawar.model;

public class ItemOptionTemplate {
    public int id;
    public String name = "";
    public String nameType = "";
    public int type;

    public ItemOptionTemplate() {
    }

    public ItemOptionTemplate(int id2, String name2, int type2) {
        this.id = id2;
        this.name = name2;
        this.type = type2;
    }

    public ItemOptionTemplate clones() {
        return new ItemOptionTemplate(this.id, this.name, this.type);
    }
}
