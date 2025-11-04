package ninjawar.mylib;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Vector;
import ninjawar.message.RMSFile;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadVersionManager;

public class Rms {
    public static String RMS_ALL_TILE = "alltm";
    public static String RMS_ATK_ENEMY = "atkene";
    public static String RMS_ATLAS_DATA_ALL = "alda";
    public static String RMS_CAU_HINH = "op";
    public static int RMS_CAU_HINH_HIGH = 0;
    public static int RMS_CAU_HINH_LOW = 1;
    public static String RMS_CONTROL = "ctrl";
    public static String RMS_DATA = "dt";
    public static String RMS_DATA_VERSION = "dv";
    public static String RMS_DOUBLE_JUMP = "dbjump";
    public static String RMS_EFFECT_FRAME_SIZE = "efffs";
    public static String RMS_EFFECT_FRAME_VERSION = "efffv";
    public static String RMS_EFFECT_SIZE = "effs";
    public static String RMS_EFFECT_VERSION = "effv";
    public static String RMS_GAN_SKILL = "gsk";
    public static String RMS_ICON_BUFF_SIZE = "icbsize";
    public static String RMS_ICON_BUFF_VERSION = "icbv";
    public static String RMS_ICON_SKILL_SIZE = "icsksize";
    public static String RMS_ICON_SKILL_VERSION = "icskv";
    public static String RMS_ITEM = "ite";
    public static String RMS_ITEM_FRAME_SIZE = "itemframesize";
    public static String RMS_ITEM_FRAME_VERSION = "itemframev";
    public static String RMS_ITEM_VERSION = "itv";
    public static String RMS_KEY_SKILL = "kskkk";
    public static String RMS_MAP = "ma";
    public static String RMS_MAP_VERSION = "mv";
    public static String RMS_MOB_IMAGE_SIZE = "mobs";
    public static String RMS_MOB_IMAGE_VERSION = "mbimv";
    public static String RMS_MUSIC_TEMPLATE = "mmmte";
    public static String RMS_MUSIC_VERSION = "mmmv";
    public static String RMS_NPC_FRAME_SIZE = "nfrpcsae";
    public static String RMS_NPC_FRAME_VERSION = "npcframev";
    public static String RMS_OBJECT_MAP_FRAME_VERSION = "objmv";
    public static String RMS_OBJ_MAP_FRAME_SIZE = "objms";
    public static String RMS_PART_SIZE = "parts";
    public static String RMS_PART_VERSION = "partv";
    public static String RMS_REMMERBER_ME = "rmblg";
    public static String RMS_SAVE_CHAR_NAME = "cn";
    public static String RMS_SAVE_LOCATION = "lc";
    public static String RMS_SAVE_PASSWORD = "ps";
    public static String RMS_SAVE_PIN = "pin";
    public static String RMS_SAVE_SERVER = "sv";
    public static String RMS_SAVE_USER = "us";
    public static String RMS_SKILL = "ski";
    public static String RMS_SKILL_VERSION = "skv";
    public static String RMS_SOUND_TEMPLATE = "ssste";
    public static String RMS_SOUND_VERSION = "sssv";
    public static String RMS_STT_BUFF_SIZE = "stts";
    public static String RMS_STT_BUFF_VERSION = "sttv";
    public static String RMS_SV_IP = "sviii";
    public static String RMS_TAP_TRUNG_CHARKA = "ttckr";
    public static String RMS_THAO_TAC_ITEM = "ttitem";
    public static String RMS_THAO_TAC_KHO = "ttkho";
    public static String RMS_THU_CUOI = "tcuoi";
    public static String RMS_TILE_FRAME_SIZE = "tiles";
    public static String RMS_TILE_FRAME_VERSION = "tilv";
    public static String RMS_TILE_MAP = "tm";
    public static String RMS_TILE_MAP_VERSION = "tmv";
    public static String RMS_TUONG_TAC_NPC = "ttnpc";
    public static String RMS_VOLUMN_BG = "vlbg";
    public static String RMS_VOLUMN_EFF = "vleff";
    public static String RMS_WEAPON_SIZE = "wps";
    public static String RMS_WEAPON_SPLASH_SIZE = "wpspls";
    public static String RMS_WEAPON_SPLASH_VERSION = "wpsplv";
    public static String RMS_WEAPON_VERSION = "wpv";
    public static String RMS_WORLD_MAP_SIZE = "wldmaps";
    public static String RMS_WORLD_MAP_VERSION = "wldmapv";
    public static String pathPCSave = (System.getenv("APPDATA") + "/bfAvq6EU96");
    public static final String[] path_save = {"DGad9jQbMb", "0l4S6sOZw2", "qqTkoZPhMX", "Fr04hA69zU", "Q2DW2MaPeI", "hjMeUxpNVi", "xAe3W2MuGY", "AFFAOKqtak", "BrCHZ7XsdL", "7YpkAIyBOs", "z9tDPqURbR", "qtyEAhuTB0", "1zT4AUXe6S", "7X6KvvvlMu", "GmjzKBZS9x", "AY43XuORv6"};
    public static Vector<RMSFile> vecFileHandleRMS = new Vector<>();

    public static void loadFirst() {
        if (NinjaMidlet.isPC) {
            Res.createFolder(pathPCSave);
            int i = 0;
            while (true) {
                String[] strArr = path_save;
                if (i < strArr.length) {
                    Res.createFolder(pathPCSave + "/" + strArr[i]);
                    i++;
                } else {
                    return;
                }
            }
        } else if (NinjaMidlet.isAndroid) {
            String pathR = Gdx.files.local("data/").file().getAbsolutePath();
            Res.createFolder(pathR);
            Res.createFolder(Gdx.files.local("data/ouCjCi98fQ/").file().getAbsolutePath());
            int i2 = 0;
            while (true) {
                String[] strArr2 = path_save;
                if (i2 < strArr2.length) {
                    Res.createFolder(pathR + "/" + strArr2[i2]);
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    public static void saveRMSInt(String file, int x) {
        try {
            saveRMS(file, new byte[]{(byte) x});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveRMS(String filename, byte[] data) {
        try {
            clearIfExistByFileNameFileHandleRMS(filename);
            vecFileHandleRMS.add(new RMSFile(data, filename));
            if (NinjaMidlet.isPC) {
                saveRMSPC("/data/" + filename, data);
                return;
            }
            Files files = Gdx.files;
            files.local("data/" + filename).writeBytes(data, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearIfExistByFileNameFileHandleRMS(String fileName) {
        Iterator<RMSFile> it = vecFileHandleRMS.iterator();
        while (it.hasNext()) {
            if (it.next().fileName.equals(fileName)) {
                vecFileHandleRMS.removeElement(fileName);
                return;
            }
        }
    }

    public static void saveRMSDataFromServer(String filename, byte[] data) {
        try {
            if (filename.contains("atlas")) {
                Res.outz("debug");
            }
            if (filename.contains("mp3")) {
                Res.outz("debug mp3");
            }
            if (filename.contains("wav")) {
                Res.outz("debug wav");
            }
            vecFileHandleRMS.add(new RMSFile(data, filename));
            if (NinjaMidlet.isPC) {
                saveRMSPC(filename, data);
                return;
            }
            Files files = Gdx.files;
            files.local("data/" + filename).writeBytes(data, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] loadRMS(String filename) {
        try {
            if (NinjaMidlet.isPC) {
                return loadRMSNew(new File(pathPCSave + "/" + "data/" + filename).getAbsolutePath());
            }
            Files files = Gdx.files;
            FileHandle file = files.local("data/" + filename);
            byte[] data = loadRMSNew(file.file().getAbsolutePath());
            if (data == null) {
                return file.readBytes();
            }
            return data;
        } catch (Exception e) {
            if (0 == 0) {
                return getDataByFileName(filename);
            }
            return null;
        }
    }

    public static byte[] loadRMSDataFromServer(String filename) {
        byte[] data;
        try {
            if (NinjaMidlet.isPC) {
                data = loadRMSPC(filename);
            } else {
                Files files = Gdx.files;
                FileHandle file = files.local("data/" + filename);
                data = loadRMSNew(file.file().getAbsolutePath());
                if (data == null) {
                    data = file.readBytes();
                }
            }
        } catch (Exception e) {
            PrintStream printStream = System.out;
            printStream.println("văng catch khi đọc rms:" + "");
            data = getDataByFileName(filename);
        }
        if (data == null) {
            return data;
        }
        try {
            return FileData.decrypt(data);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int loadRMSInt(String file) {
        byte[] b = loadRMS(file);
        if (b == null) {
            return -1;
        }
        return b[0];
    }

    public static void saveRMSString(String filename, String s) {
        try {
            saveRMS(filename, s.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String loadRMSString(String filename) {
        byte[] data = loadRMS(filename);
        if (data == null) {
            return null;
        }
        try {
            return new String(data, "UTF-8");
        } catch (Exception e) {
            return new String(data);
        }
    }

    public static void clearAll() {
        vecFileHandleRMS.removeAllElements();
        if (NinjaMidlet.isPC) {
            File folder = new File(Gdx.files.local("data/").file().getAbsolutePath());
            String[] pathDontClear = {RMS_REMMERBER_ME, RMS_SV_IP, RMS_SAVE_USER, RMS_SAVE_PASSWORD, RMS_VOLUMN_BG, RMS_VOLUMN_EFF, RMS_CAU_HINH};
            deleteFolder(folder, pathDontClear);
            for (int i = 0; i < pathDontClear.length; i++) {
                pathDontClear[i] = "/data/" + pathDontClear[i];
            }
            deleteFolder(new File(pathPCSave), pathDontClear);
        } else {
            deleteFolder(new File(Gdx.files.local("data/").file().getAbsolutePath()), new String[]{RMS_REMMERBER_ME, RMS_SV_IP, RMS_SAVE_USER, RMS_SAVE_PASSWORD, RMS_VOLUMN_BG, RMS_VOLUMN_EFF, RMS_CAU_HINH});
        }
        LoadVersionManager.resetVersion();
    }

    public static void clearOneRMS(String rms) {
        Files files = Gdx.files;
        new File(files.local("data/" + rms).file().getAbsolutePath()).delete();
    }

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (folder.isDirectory()) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
    }

    public static void deleteFolder(File folder, String[] fileNames) {
        File[] files = folder.listFiles();
        if (folder.isDirectory()) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else if (!isLikeName(f.getName(), fileNames)) {
                    f.delete();
                }
            }
        }
    }

    private static boolean isLikeName(String fileName, String[] fileNames) {
        if (fileNames == null) {
            return false;
        }
        for (String equals : fileNames) {
            if (fileName.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    public static byte[] getDataByFileName(String fileName) {
        Iterator<RMSFile> it = vecFileHandleRMS.iterator();
        while (it.hasNext()) {
            RMSFile file = it.next();
            if (file.fileName.equals(fileName)) {
                return file.data;
            }
        }
        return null;
    }

    public static void clearVecRMS() {
        vecFileHandleRMS.clear();
    }

    public static void saveRMSPC(String fileName, byte[] data) {
        BufferedOutputStream bs = null;
        try {
            File file = new File(pathPCSave + Res.getPathMoreWhenRemoveFileName(fileName));
            if (!file.exists()) {
                file.mkdirs();
            }
            java.nio.file.Files.setAttribute(Paths.get(pathPCSave), "dos:hidden", Boolean.TRUE, LinkOption.NOFOLLOW_LINKS);
            BufferedOutputStream bs2 = new BufferedOutputStream(java.nio.file.Files.newOutputStream(new File(pathPCSave + fileName).toPath()));
            bs2.write(data);
            bs2.close();
        } catch (Exception e2) {
            Res.outz(1, "Lưu RMS thất bại");
        }
    }

    public static byte[] loadRMSPC(String fileName) {
        return loadRMSNew(pathPCSave + fileName);
    }

    public static byte[] loadRMSNew(String fileName) {
        try {
            return Gdx.files.absolute(fileName).readBytes();
        } catch (Exception e) {
            Res.err("[RMS] Không tìm thấy ảnh trong RMS PC");
            return null;
        }
    }

    public static FileHandle loadFileHandleRMSNew(String fileName) {
        try {
            return Gdx.files.absolute(fileName);
        } catch (Exception e) {
            Res.err("[RMS] Không tìm thấy FileHandle trong RMS PC");
            return null;
        }
    }
}
