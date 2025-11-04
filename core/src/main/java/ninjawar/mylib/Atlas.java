package ninjawar.mylib;

import java.io.PrintStream;
import ninjawar.mymain.NinjaMidlet;
import com.badlogic.gdx.files.FileHandle;
import ninjawar.model.AtlasTemplate;
import ninjawar.message.SendMessage;
import ninjawar.template.AtlasTemplates;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.Gdx;
import ninjawar.supporter.LoadDataManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.HashMap;

public class Atlas {
    public HashMap<String, Sprite> sprites;
    TextureAtlas texture;

    /**
     * Create atlas from path with caching
     */
    public static Atlas createAtlas(String basePath, String atlasName) {
        // Check cache first
        Atlas cachedAtlas = LoadDataManager.hashMapAtlasAll.get(atlasName);
        if (cachedAtlas != null) {
            return cachedAtlas;
        }

        try {
            String atlasPath = basePath + atlasName + ".atlas";
            Atlas newAtlas = new Atlas();

            // Load texture atlas
            FileHandle atlasFile = Gdx.files.internal(LibSysCustom.res + atlasPath);
            newAtlas.texture = new TextureAtlas(atlasFile);
            newAtlas.sprites = new HashMap<>();

            // Create sprites from atlas regions
            createSpritesFromAtlas(newAtlas);

            // Cache the atlas
            LoadDataManager.hashMapAtlasAll.put(atlasName, newAtlas);
            return newAtlas;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Create atlas from ID and type
     */
    public static Atlas createAtlas(short id, short type) {
        return createAtlas(id, type, (short)-1);
    }

    /**
     * Create atlas from ID, type and subID with server fallback
     */
    public static Atlas createAtlas(short id, short type, short subId) {
        AtlasTemplate template = AtlasTemplates.get(id);
        if (template == null) {
            return null;
        }

        // Check cache first
        Atlas cachedAtlas = LoadDataManager.hashMapAtlasAll.get(template.nameAtlas);
        if (cachedAtlas != null) {
            return cachedAtlas;
        }

        try {
            String atlasPath = FileData.getPathFromIdAndType(id, type, subId);
            System.out.println("atlas path: " + atlasPath + " id: " + id + " type: " + type + " subId: " + subId);
            FileHandle atlasFile = loadRMSFromServer(atlasPath);

            if (!atlasFile.exists()) {
                return null;
            }

            Atlas newAtlas = new Atlas();
            newAtlas.texture = new TextureAtlas(atlasFile);
            newAtlas.sprites = new HashMap<>();

            // Create sprites from atlas regions
            createSpritesFromAtlas(newAtlas);

            // Cache the atlas
            LoadDataManager.hashMapAtlasAll.put(template.nameAtlas, newAtlas);
            return newAtlas;

        } catch (Exception e) {
            e.printStackTrace();
            // Request atlas data from server on failure
            SendMessage.gI().requestAtlasData(id);
            return null;
        }
    }

    /**
     * Helper method to create sprites from atlas regions
     */
    private static void createSpritesFromAtlas(Atlas atlas) {
        for (AtlasRegion region : atlas.texture.getRegions()) {
            Sprite sprite = atlas.texture.createSprite(region.name);
            if (sprite != null) {
                // Apply zoom scaling
                float scaledWidth = sprite.getWidth() * mGraphics.zoomLevel * mGraphics.zoomGdx;
                float scaledHeight = sprite.getHeight() * mGraphics.zoomLevel * mGraphics.zoomGdx;
                sprite.setSize(scaledWidth, scaledHeight);
                sprite.setOrigin(0.0f, 0.0f);
                sprite.flip(false, true);

                atlas.sprites.put(region.name, sprite);
            }
        }
    }

    /**
     * Load file handle from RMS or internal resources
     */
    public static FileHandle loadRMSFromServer(String absolutePath) {
        try {
            if (NinjaMidlet.isPC) {
                return loadRMSForPC(absolutePath);
            } else {
                return loadRMSForMobile(absolutePath);
            }
        } catch (Exception e) {
            System.out.println("Error loading RMS: " + absolutePath);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Load RMS for PC platform
     */
    private static FileHandle loadRMSForPC(String absolutePath) {
        // Try to load from PC save path first
        String pcPath = Rms.pathPCSave + absolutePath;
        FileHandle rmsFile = Rms.loadFileHandleRMSNew(pcPath);

        if (rmsFile.exists()) {
            return rmsFile;
        }

        // Fallback to internal resources
        String internalPath = LibSysCustom.res + absolutePath;
        return Gdx.files.internal(internalPath);
    }

    /**
     * Load RMS for mobile platform
     */
    private static FileHandle loadRMSForMobile(String absolutePath) {
        String localPath = Gdx.files.local("data/" + absolutePath).file().getAbsolutePath();
        return Rms.loadFileHandleRMSNew(localPath);
    }
}
