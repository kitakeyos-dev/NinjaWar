package ninjawar.template;

import java.util.HashMap;
import java.util.Map;

public class ItemTemplates {
    public static Map<Integer, ItemTemplate> itemTemplates = new HashMap<>();

    public static void add(ItemTemplate it) {
        itemTemplates.put(it.iconID, it);
        System.out.println("id: " + it.id + " name: " + it.name + " description: " + it.description);
    }

    public static ItemTemplate get(int id) {
        ItemTemplate itt = itemTemplates.get(id);
        if (itt == null) {
            itt = new ItemTemplate();
            itt.createDefault();
        }
        return itt;
    }
}
