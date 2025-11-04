package ninjawar.supporter;

import com.badlogic.gdx.Gdx;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.PrintStream;
import ninjawar.message.SendMessage;
import ninjawar.model.AtlasTemplate;
import ninjawar.mylib.BaseScreen;
import ninjawar.mylib.FileData;
import ninjawar.mylib.Rms;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.Res;
import ninjawar.screen.ChooseCharScreen;
import ninjawar.screen.CreateCharScreen;
import ninjawar.screen.DownLoadingScr;
import ninjawar.template.AtlasTemplates;

public class LoadVersionManager {
    public static int MAX_COUNT = 5;
    public static int MAX_DELAY = 5000;
    public static int[] countUpdate = new int[20];
    public static boolean isCheckOnlyVersion = false;
    public static boolean isHaveTemplateAtlas = false;
    public static boolean isLoadAllData;
    public static boolean isLoadAtlas = false;
    public static boolean isUpdateData;
    public static boolean isUpdateItem;
    public static boolean isUpdateItemFrame;
    public static boolean isUpdateMap;
    public static boolean isUpdateMob;
    public static boolean isUpdateNPCFrame;
    public static boolean isUpdateObjectMap;
    public static boolean isUpdateSkill;
    public static boolean isUpdateTileFrame;
    public static long[] lastTimeUpdate = new long[20];
    public static final byte[] subCheck = {0, 1, 4, 12, 11, 2, 13};
    public static int vcData;
    public static int vcImageChar;
    public static int vcImageEffect;
    public static int vcImageEffectFrame;
    public static int vcImageIconBuff;
    public static int vcImageIconSkill;
    public static int vcImageItem;
    public static int vcImageMob;
    public static int vcImageNpc;
    public static int vcImageObjectMap;
    public static int vcImageStatusBuff;
    public static int vcImageTile;
    public static int vcImageWeapon;
    public static int vcImageWeaponSplash;
    public static int vcImageWorldMap;
    public static int vcItem;
    public static int vcMap;
    public static int vcMusic;
    public static int vcSkill;
    public static int vcSound;
    public static int vsData;
    public static int vsImageMob;
    public static int vsItem;
    public static int vsMap;
    public static int vsSkill;

    public static void loadClientVersion() {
        vcData = Rms.loadRMSInt(Rms.RMS_DATA_VERSION);
        int loadRMSInt = Rms.loadRMSInt(Rms.RMS_MAP_VERSION);
        vcMap = loadRMSInt;
        vcImageNpc = loadRMSInt;
        vcSkill = Rms.loadRMSInt(Rms.RMS_SKILL_VERSION);
        vcItem = Rms.loadRMSInt(Rms.RMS_ITEM_VERSION);
        vcImageMob = Rms.loadRMSInt(Rms.RMS_MOB_IMAGE_VERSION);
        vcImageItem = Rms.loadRMSInt(Rms.RMS_ITEM_FRAME_VERSION);
        vcImageTile = Rms.loadRMSInt(Rms.RMS_TILE_FRAME_VERSION);
        vcImageObjectMap = Rms.loadRMSInt(Rms.RMS_OBJECT_MAP_FRAME_VERSION);
        vcImageChar = Rms.loadRMSInt(Rms.RMS_PART_VERSION);
        vcImageEffect = Rms.loadRMSInt(Rms.RMS_EFFECT_VERSION);
        vcImageEffectFrame = Rms.loadRMSInt(Rms.RMS_EFFECT_FRAME_VERSION);
        vcImageWeapon = Rms.loadRMSInt(Rms.RMS_WEAPON_VERSION);
        vcImageWeaponSplash = Rms.loadRMSInt(Rms.RMS_WEAPON_SPLASH_VERSION);
        vcImageStatusBuff = Rms.loadRMSInt(Rms.RMS_STT_BUFF_VERSION);
        vcImageIconSkill = Rms.loadRMSInt(Rms.RMS_ICON_SKILL_VERSION);
        vcImageIconBuff = Rms.loadRMSInt(Rms.RMS_ICON_BUFF_VERSION);
        vcSound = Rms.loadRMSInt(Rms.RMS_SOUND_VERSION);
        vcMusic = Rms.loadRMSInt(Rms.RMS_MUSIC_VERSION);
    }

    public static boolean isLoadAllData() {
        int i = vsMap;
        boolean check = true;
        boolean checkMap = i == vcMap;
        int i2 = vsSkill;
        boolean checkSkill = i2 == vcSkill;
        boolean checkMapImageObject = i == vcImageObjectMap;
        boolean checkMapImageTile = i == vcImageTile;
        int i3 = vcImageChar;
        int i4 = vsData;
        boolean checkImageChar = i3 == i4;
        boolean checkImageEffect = vcImageEffect == i2;
        boolean checkImageEffectFrame = vcImageEffectFrame == i2;
        boolean checkImageWeapon = vcImageWeapon == i4;
        boolean checkImageWeaponSplash = vcImageWeaponSplash == i4;
        boolean checkImageIconBuff = vcImageIconBuff == vsItem;
        boolean checkSound = vcSound == i;
        boolean checkMusic = vcMusic == i;
        boolean checkTemplateAtlas = isHaveTemplateAtlas && isLoadAtlas();
        if (!checkMap || !checkSkill || !checkMapImageObject || !checkMapImageTile || !checkImageChar || !checkImageEffect || !checkImageEffectFrame || !checkImageWeapon || !checkImageWeaponSplash || !checkImageIconBuff || !checkSound || !checkMusic || !checkTemplateAtlas) {
            check = false;
        }
        PrintStream printStream = System.out;
        String sb = "Final check result: " +
            check;
        printStream.println(sb);
        return check;
    }

    public static float percentLoad() {
        int countVersion = 0;
        if (vsMap == vcMap) {
            countVersion++;
        }
        if (vsSkill == vcSkill) {
            countVersion++;
        }
        if (vsMap == vcImageObjectMap) {
            countVersion++;
        }
        if (vsMap == vcImageTile) {
            countVersion++;
        }
        if (vcImageChar == vsData) {
            countVersion++;
        }
        if (vcImageEffect == vsSkill) {
            countVersion++;
        }
        if (vcImageEffectFrame == vsSkill) {
            countVersion++;
        }
        if (vcImageWeapon == vsData) {
            countVersion++;
        }
        if (vcImageWeaponSplash == vsData) {
            countVersion++;
        }
        if (vcImageStatusBuff == vsSkill) {
            countVersion++;
        }
        if (vcImageIconBuff == vsItem) {
            countVersion++;
        }
        if (isHaveTemplateAtlas && isLoadAtlas()) {
            countVersion++;
        }
        if (vcMusic == vsMap) {
            countVersion++;
        }
        if (vcSound == vsMap) {
            countVersion++;
        }
        if (!isHaveTemplateAtlas || !isLoadAtlas()) {
            Res.outz(1, "isHaveTemplateAtlas:" + isHaveTemplateAtlas + "_isLoadAtlas:" + isLoadAtlas());
        }
        if (LoadVersionManager.vcMusic != LoadVersionManager.vsMap) {
            Res.outz(1, "vcMusic:" + LoadVersionManager.vcMusic);
        }
        if (vcSound != LoadVersionManager.vsMap) {
            Res.outz(1, "vcSound:" + vcSound);
        }
        return (((float) countVersion)) / ((float) 14);
    }

    public static void doCheckData() {
        doCheckData(true);
    }

    public static void doCheckData(boolean result) {
        BaseScreen baseScreen = CanvasNinja.currentScreen;
        if (!(baseScreen instanceof DownLoadingScr) && !(baseScreen instanceof ChooseCharScreen) && !(baseScreen instanceof CreateCharScreen)) {
            new DownLoadingScr().switchToMe();
        }
    }

    public static boolean doCheckSendRequest(int index) {
        return lastTimeUpdate[index] > mSystem.currentTimeMillis() || countUpdate[index] > MAX_COUNT;
    }

    public static void updateCount(int index) {
        lastTimeUpdate[index] = mSystem.currentTimeMillis() + ((long) MAX_DELAY);
        int[] iArr = countUpdate;
        iArr[index] = iArr[index] + 1;
    }

    public static boolean isLoadAtlas() {
        // Kiểm tra null safety
        if (subCheck == null) {
            isLoadAtlas = false;
            return false;
        }

        for (byte sub : subCheck) {
            if (!readAtlasBySub(sub)) {
                isLoadAtlas = false;
                return false;
            }
        }
        isLoadAtlas = true;
        return true;
    }

    public static boolean readAtlasBySub(byte sub) {
        try {
            byte[] data = Rms.loadRMS(Rms.RMS_ATLAS_DATA_ALL + sub);
            if (data == null) {
                SendMessage.gI().requestAtlasData(sub);
                return false;
            }
            DataInputStream d = new DataInputStream(new ByteArrayInputStream(data));
            short idAtlas = d.readShort();
            AtlasTemplate atlasTemplate = AtlasTemplates.get(idAtlas);
            int version = d.readInt();
            if (atlasTemplate != null && version != atlasTemplate.version) {
                Res.outz(1, "version server zzz:" + atlasTemplate.version);
                Res.outz(1, "version client zzz:" + version);
                SendMessage.gI().requestAtlasData(idAtlas);
                return false;
            } else if (atlasTemplate == null) {
                SendMessage.gI().requestAtlasData(idAtlas);
                return false;
            } else if (!checkFileIsExist(idAtlas, (short) 23, (short) -1)) {
                SendMessage.gI().requestAtlasData(idAtlas);
                return false;
            } else {
                for (int i = 0; i < atlasTemplate.numImgAtlas; i++) {
                    if (!checkFileIsExist(idAtlas, (short) 24, (short) i)) {
                        SendMessage.gI().requestAtlasData(idAtlas);
                        return false;
                    }
                }
                d.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            SendMessage.gI().requestAtlasData(sub);
            return false;
        }
    }

    public static boolean checkFileIsExist(short idIcon, short idFolder, short subType) {
        String path = FileData.getPathFromIdAndType(idIcon, idFolder, subType);
        if (Gdx.files.internal(path).exists()) {
            return true;
        }
        if (NinjaMidlet.isPC) {
            if (Gdx.files.absolute(Rms.pathPCSave + path).exists()) {
                return true;
            }
            return false;
        } else if (!NinjaMidlet.isAndroid) {
            return false;
        } else {
            if (Gdx.files.local(path).exists()) {
                return true;
            }
            if (Gdx.files.local("data/" + path).exists()) {
                return true;
            }
            return false;
        }
    }

    public static void resetVersion() {
        loadClientVersion();
        isLoadAtlas = false;
        SendMessage.gI().isSendAtlas = new boolean[20];
    }
}
