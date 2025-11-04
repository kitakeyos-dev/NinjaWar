package ninjawar.template;

import ninjawar.mylib.HTCustom;

public class TypeItemtemplates {
    public static HTCustom typeItemTemplates = new HTCustom("type item template");

    public static void add(TypeItemtemplate it) {
        HTCustom hTCustom = typeItemTemplates;
        hTCustom.put(it.type + "", it);
    }

    public static TypeItemtemplate get(int id) {
        HTCustom hTCustom = typeItemTemplates;
        if (hTCustom.get(id + "") == null) {
            return new TypeItemtemplate();
        }
        HTCustom hTCustom2 = typeItemTemplates;
        return (TypeItemtemplate) hTCustom2.get(id + "");
    }
}
