package ninjawar.model;

public class OptionTemplate {
    public int id;
    public int offSetX;
    public int offSetXQuai;
    public int offSetY;
    public int offSetYQuai;
    public byte typeInteract;
    public int typeShadow;
    public int value;

    public static int findValueById(int id2, OptionTemplate[] temps) {
        if (temps == null) {
            return 0;
        }
        for (int i = 0; i < temps.length; i++) {
            if (temps[i].id == id2) {
                return temps[i].value;
            }
        }
        return 0;
    }
}
