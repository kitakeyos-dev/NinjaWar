package ninjawar.mylib;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.io.File;
import ninjawar.mymain.NinjaMidlet;

public class Image {
    public static int transColor = 16777215;
    public int height;
    public float scale = 1.0f;
    public TextureRegion[] tRegions;
    public Texture texture;
    public int width;

    public static Image createImage(String url) {
        return createImage(url, 1.0f);
    }

    public static Image createImage(String url, float scale2) {
        Image img = new Image();
        try {
            Files files = Gdx.files;
            Texture texture2 = new Texture(files.internal(LibSysCustom.res + url), Pixmap.Format.RGBA8888, true);
            img.texture = texture2;
            img.scale = scale2;
            int currentWidth = texture2.getWidth();
            int currentHeight = img.texture.getHeight();
            Texture texture3 = img.texture;
            if (texture3 != null) {
                img.width = currentWidth;
                img.height = currentHeight;
                Texture.TextureFilter textureFilter = Texture.TextureFilter.Nearest;
                texture3.setFilter(textureFilter, textureFilter);
                if (img.texture == null) {
                    return null;
                }
                return img;
            }
            throw new IllegalArgumentException("!!! createImage scr is NULL-----------." + LibSysCustom.res + url);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isExists(String pathInterval, String pathRMS) {
        boolean result = Gdx.files.internal(pathInterval).exists();
        if (result) {
            return result;
        }
        if (NinjaMidlet.isPC) {
            return new File(Rms.pathPCSave + pathRMS).exists();
        }
        Files files = Gdx.files;
        return files.local("data/" + pathRMS).exists();
    }

    public static Image createImage(short idIcon, short type, short subType) {
        String path = FileData.getPathFromIdAndType(idIcon, type, subType);
        String pathPng = path + ".png";
        Image result = createImageFromRms(path);
        if (result == null) {
            result = createImage(pathPng);
        }
        if (result != null) {
            return result;
        }
        return null;
    }

    public static Image createImage(short idIcon, short type) {
        return createImage(idIcon, type, (short) -1);
    }

    public static boolean checkImageExist(short idIcon, short type) {
        return checkImageExist(idIcon, type, (short) -1);
    }

    public static boolean checkImageExist(short idIcon, short type, short subType) {
        String path = FileData.getPathFromIdAndType(idIcon, type, subType);
        return isExists(path + ".png", path);
    }

    public static Image createImageFromRms(String url) {
        new Image();
        try {
            byte[] data = Rms.loadRMSDataFromServer(url);
            Image img = createImage(data, 0, data.length);
            if (img == null || img.texture == null) {
                return null;
            }
            return img;
        } catch (Exception e) {
            return null;
        }
    }

    public float getWidth() {
        return (float) this.width;
    }

    public float getHeight() {
        return (float) this.height;
    }

    public int getRWidth() {
        return this.width;
    }

    public int getRHeight() {
        return this.height;
    }

    public static Image createImage(byte[] encodedData, int offset, int len) {
        return createImage(encodedData, offset, len, 1.0f);
    }

    public static Image createImage(byte[] encodedData, int offset, int len, float scale2) {
        Image img = new Image();
        try {
            Pixmap p = new Pixmap(encodedData, offset, len);
            Texture texture2 = new Texture(p);
            img.texture = texture2;
            img.scale = scale2;
            img.width = texture2.getWidth();
            img.height = img.texture.getHeight();
            p.dispose();
            return img;
        } catch (Exception e) {
            return null;
        }
    }
}
