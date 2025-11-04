// Class Version: 8
package ninjawar.mylib;

import ninjawar.model.AtlasTemplate;
import ninjawar.supporter.BigImage;
import ninjawar.template.AtlasTemplates;

public class FileData {
    // File type constants
    public static final short TYPE_SMALL_DATA = 0;
    public static final short TYPE_SMALL_IMG = 1;
    public static final short TYPE_EFFECT_IMG = 2;
    public static final short TYPE_EFFECT_DATA = 3;
    public static final short TYPE_ITEM = 4;
    public static final short TYPE_MOB = 5;
    public static final short TYPE_EFFECT_CHAR_IMG = 6;
    public static final short TYPE_EFFECT_CHAR_DATA = 7;
    public static final short TYPE_NPC = 8;
    public static final short TYPE_TILE = 9;
    public static final short TYPE_WEAPON_SPLASH_IMG = 11;
    public static final short TYPE_WEAPON_SPLASH_DATA = 12;
    public static final short TYPE_WEAPON_DATA = 13;
    public static final short TYPE_WEAPON_IMG = 14;
    public static final short TYPE_MAP_BACKGROUND_IMG = 15;
    public static final short TYPE_MAP_BACKGROUND_DATA = 16;
    public static final short TYPE_EFFECT_FRAME = 17;
    public static final short TYPE_EFFECT_FRAME_SHIELD = 18;
    public static final short TYPE_EFFECT_FRAME_GENERAL = 20;
    public static final short TYPE_SKILL_ICON = 21;
    public static final short TYPE_ICON_BUFF = 22;
    public static final short TYPE_ATLAS_DEFINITION = 23;
    public static final short TYPE_ATLAS_IMAGE = 24;
    public static final short TYPE_SOUND = 25;
    public static final short TYPE_MUSIC = 26;
    public static final short TYPE_KIENVAN69CM = 29;

    // Folder path constants
    public static final String PATH_SMALL_DATA = "/small/data/";
    public static final String PATH_SMALL_IMG = "/small/img/_big";
    public static final String PATH_EFFECT_IMG = "/effect/img/";
    public static final String PATH_EFFECT_DATA = "/effect/data/";
    public static final String PATH_ITEM = "/item/";
    public static final String PATH_MOB = "/mob/";
    public static final String PATH_EFFECT_CHAR_IMG = "/effectChar/img/";
    public static final String PATH_EFFECT_CHAR_DATA = "/effectChar/data/";
    public static final String PATH_NPC = "/npc/";
    public static final String PATH_TILE = "/tile/";
    public static final String PATH_WEAPON_SPLASH_IMG = "/effectSplash/eff_weapon/img/";
    public static final String PATH_WEAPON_SPLASH_DATA = "/effectSplash/eff_weapon/data/";
    public static final String PATH_WEAPON_DATA = "/effectSplash/weapon/data/";
    public static final String PATH_WEAPON_IMG = "/effectSplash/weapon/img/";
    public static final String PATH_MAP_BACKGROUND = "/mapBackGround/";
    public static final String PATH_MAP_BACKGROUND_DATA = "/mapBackGround/data/";
    public static final String PATH_EFFECT_FRAME = "/effect/frame/";
    public static final String PATH_EFFECT_FRAME_SHIELD = "/effect/frame/shield/";
    public static final String PATH_EFFECT_FRAME_GENERAL = "/effectFrame/";
    public static final String PATH_SKILL_ICON = "/skill/icon/";
    public static final String PATH_ICON_BUFF = "/iconBuff/";
    public static final String PATH_ATLAS = "/atlas/";
    public static final String PATH_SOUND = "/sound/";
    public static final String PATH_MUSIC = "/music/";
    public static final String PATH_KIENVAN69CM = "/kienvan69cm/";

    // File extensions
    public static final String EXT_ATLAS = ".atlas";
    public static final String EXT_PNG = ".png";
    public static final String EXT_WAV = ".wav";
    public static final String EXT_MP3 = ".mp3";

    public BigImage bigImage;
    public byte[] data;
    public short idIcon;
    public String path;
    public short subType;
    public short type;

    public FileData(final short idIcon, final short type, final short subType, final byte[] data) {
        this.path = "";
        this.idIcon = idIcon;
        this.type = type;
        this.data = data;
        this.subType = subType;
        this.path = getPathFromIdAndType(idIcon, type, subType);
    }

    public FileData(final short idIcon, final short type, final byte[] data) {
        this.path = "";
        this.idIcon = idIcon;
        this.type = type;
        this.data = data;
        this.path = getPathFromIdAndType(idIcon, type);
    }

    public static byte[] decrypt(final byte[] array) {
        return array;
    }

    public static String getPathFromIdAndType(final short id, final short type) {
        return getPathFromIdAndType(id, type, (short) -1);
    }

    public static String getPathFromIdAndType(short id, short type, short subId) {
        // Special handling for atlas types
        if (type == TYPE_ATLAS_DEFINITION || type == TYPE_ATLAS_IMAGE) {
            return buildAtlasPath(id, type, subId);
        }
        // Build subfolder path if subId is specified
        String subPath = (subId != -1) ? subId + "/" : "";
        String basePath = getBasePath(type);
        if (basePath.isEmpty()) {
            return "";
        }

        // Special handling for audio files
        if (type == TYPE_SOUND) {
            return basePath + id + EXT_WAV;
        }
        if (type == TYPE_MUSIC) {
            return basePath + id + EXT_MP3;
        }

        // Build standard path: basePath + subPath + id
        return basePath + subPath + id;
    }

    private static String getBasePath(short type) {
        switch (type) {
            case TYPE_SMALL_DATA:
                return PATH_SMALL_DATA;
            case TYPE_SMALL_IMG:
                return PATH_SMALL_IMG;
            case TYPE_EFFECT_IMG:
                return PATH_EFFECT_IMG;
            case TYPE_EFFECT_DATA:
                return PATH_EFFECT_DATA;
            case TYPE_ITEM:
                return PATH_ITEM;
            case TYPE_MOB:
                return PATH_MOB;
            case TYPE_EFFECT_CHAR_IMG:
                return PATH_EFFECT_CHAR_IMG;
            case TYPE_EFFECT_CHAR_DATA:
                return PATH_EFFECT_CHAR_DATA;
            case TYPE_NPC:
                return PATH_NPC;
            case TYPE_TILE:
                return PATH_TILE;
            case TYPE_WEAPON_SPLASH_IMG:
                return PATH_WEAPON_SPLASH_IMG;
            case TYPE_WEAPON_SPLASH_DATA:
                return PATH_WEAPON_SPLASH_DATA;
            case TYPE_WEAPON_DATA:
                return PATH_WEAPON_DATA;
            case TYPE_WEAPON_IMG:
                return PATH_WEAPON_IMG;
            case TYPE_MAP_BACKGROUND_IMG:
                return PATH_MAP_BACKGROUND;
            case TYPE_MAP_BACKGROUND_DATA:
                return PATH_MAP_BACKGROUND_DATA;
            case TYPE_EFFECT_FRAME:
                return PATH_EFFECT_FRAME;
            case TYPE_EFFECT_FRAME_SHIELD:
                return PATH_EFFECT_FRAME_SHIELD;
            case TYPE_EFFECT_FRAME_GENERAL:
                return PATH_EFFECT_FRAME_GENERAL;
            case TYPE_SKILL_ICON:
                return PATH_SKILL_ICON;
            case TYPE_ICON_BUFF:
                return PATH_ICON_BUFF;
            case TYPE_SOUND:
                return PATH_SOUND;
            case TYPE_MUSIC:
                return PATH_MUSIC;
            case TYPE_KIENVAN69CM:
                return PATH_KIENVAN69CM;
            default:
                return "";
        }
    }

    private static String buildAtlasPath(short id, short type, short subId) {
        AtlasTemplate atlas = AtlasTemplates.get(id);
        if (atlas == null) {
            return "";
        }

        if (type == TYPE_ATLAS_DEFINITION) {
            // Atlas definition file
            return PATH_ATLAS + atlas.nameAtlas + EXT_ATLAS;
        } else if (type == TYPE_ATLAS_IMAGE) {
            // Atlas image file
            String suffix = (subId > 0) ? (subId + 1) + EXT_PNG : EXT_PNG;
            return PATH_ATLAS + atlas.nameAtlas + suffix;
        }

        return "";
    }
}
