package ninjawar.template;

import ninjawar.model.AtlasTemplate;
import ninjawar.mylib.HTCustom;

import java.util.HashMap;
import java.util.Map;

public class AtlasTemplates {
    private static Map<Integer, AtlasTemplate> atlasTemplates = new HashMap<>();

    public static void add(AtlasTemplate item) {
        atlasTemplates.put(item.idAtlas, item);
    }

    public static AtlasTemplate get(int idTemplate) {
        try {
            return atlasTemplates.get(idTemplate);
        } catch (Exception e) {
            return null;
        }
    }
}
