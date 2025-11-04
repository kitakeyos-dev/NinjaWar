package ninjawar.supporter;

import ninjawar.mylib.HTCustom;

public class BigImageInfos {
    public static HTCustom bigImgInfos = new HTCustom("MobTemplate");

    public static void add(BigImageInfo item) {
        bigImgInfos.put(item.getKey(), item);
    }

    public static BigImageInfo get(String key) {
        try {
            return (BigImageInfo) bigImgInfos.get(key);
        } catch (Exception e) {
            return null;
        }
    }
}
