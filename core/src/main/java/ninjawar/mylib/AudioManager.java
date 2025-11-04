package ninjawar.mylib;

import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.MyTile;
import ninjawar.myscr.Res;

public class AudioManager {
    public static int ATK_KIEM = 32;
    public static int BUTTON_CLICK = 8;
    public static int BUTTON_CLOSE = 7;
    public static int CHAR_FALL = 6;
    public static int CHIM_BAY = 10;
    public static int CHIM_HOT_1 = 13;
    public static int CHIM_HOT_2 = 14;
    public static int CHOOSE_CREATE_CHAR = 3;
    public static int DAN__QUA_KEU = 157;
    public static int DOUBLE_JUMP = 11;
    public static int GET_GIFT = 12;
    public static int GET_ITEM = 0;
    public static int ITEM_FALL = 40;
    public static boolean IsDelAcc = false;
    public static int JUMP = 5;
    public static int LEVEL_UP = 39;
    public static int LOW_KICK = 3;
    public static int LOW_PUNCH = 2;
    public static int MAP_SCR = 0;
    public static int MOB_PUNCH = 9;
    public static int MOVE = 1;
    public static int MOVE_ON_GRASS = 155;
    public static int MOVE_ON_WOOD = 33;
    public static int NHIP_TIM_MAU_THAP = 31;
    public static float PERCENT_BG = 100.0f;
    public static float PERCENT_EFF = 100.0f;
    public static int QUA_KEU = 157;
    public static int UPGRADE_FAILED = 37;
    public static int UPGRADE_SUCCESS = 36;
    public static float VOLUME_BG_MAX = 1.0f;
    public static float VOLUME_EFF_MAX = 1.0f;
    public static SoundGdx[] music;
    public static int poolCount = 0;
    public static SoundGdx[] sound;
    public static float volume = 0.5f;

    public static void init() {
        music = new SoundGdx[20];
        sound = new SoundGdx[150];
        int i = 0;
        while (true) {
            SoundGdx[] soundGdxArr = music;
            if (i >= soundGdxArr.length) {
                break;
            }
            soundGdxArr[i] = new SoundGdx("" + i, true);
            i++;
        }
        int i2 = 0;
        while (true) {
            SoundGdx[] soundGdxArr2 = sound;
            if (i2 < soundGdxArr2.length) {
                soundGdxArr2[i2] = new SoundGdx("" + i2, false);
                i2++;
            } else {
                return;
            }
        }
    }

    public static void setVolumeBackground(int percent) {
        PERCENT_BG = (float) percent;
        int i = 0;
        while (true) {
            SoundGdx[] soundGdxArr = music;
            if (i < soundGdxArr.length) {
                SoundGdx soundGdx = soundGdxArr[i];
                if (soundGdx != null) {
                    soundGdx.setVolume((((float) percent) / 100.0f) * VOLUME_BG_MAX);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public static void setVolumeEff(int percent) {
        PERCENT_EFF = (float) percent;
    }

    public static void loadSound() {
        int percentVolumnBG = Res.convertStringToNumber(Rms.loadRMSString(Rms.RMS_VOLUMN_BG));
        float f = 75.0f;
        PERCENT_BG = percentVolumnBG == -1 ? 75.0f : (float) percentVolumnBG;
        Res.outz("AM THANH MAC DINH: " + PERCENT_BG);
        int percentVolumnEff = Res.convertStringToNumber(Rms.loadRMSString(Rms.RMS_VOLUMN_EFF));
        if (percentVolumnEff != -1) {
            f = (float) percentVolumnEff;
        }
        PERCENT_EFF = f;
        init();
    }

    public static void HP_LOW(float volume2) {
        playSound(NHIP_TIM_MAU_THAP, 1.0f);
        poolCount++;
    }

    public static void MUSIC_NEN_CHON_CHAR() {
        if (music != null) {
            playMus(CHOOSE_CREATE_CHAR, true);
        }
    }

    public static void ATK_KIEM(float pan) {
        playSound(ATK_KIEM, 0.5f, pan);
        poolCount++;
    }

    public static void PAUCH(float pan) {
        playSound(LOW_PUNCH, 0.5f, pan);
        poolCount++;
    }

    public static void KICK(float pan) {
        playSound(LOW_KICK, 0.5f);
        poolCount++;
    }

    public static void mobPunch(boolean isKick, float volumn) {
        if (sound != null) {
            playSound(MOB_PUNCH, 0.1f);
            poolCount++;
        }
    }

    public static void getItem() {
        playSound(GET_ITEM, 0.3f);
        poolCount++;
    }

    public static void update() {
    }

    public static void charRun(float volumn) {
        if (CanvasNinja.gameTick % 8 == 0) {
            int i = MyTile.tileID;
            if (i == 1 || i == 3) {
                playSound(MOVE_ON_GRASS, volumn);
            } else {
                playSound(MOVE, volumn);
            }
            poolCount++;
        }
    }

    public static void charRunOnWood(float volumn) {
        if (CanvasNinja.gameTick % 8 == 0) {
            playSound(MOVE_ON_WOOD, volumn);
            poolCount++;
        }
    }

    public static void levelUp(float volumn) {
        playSound(LEVEL_UP, volumn);
        poolCount++;
    }

    public static void itemFall(float volumn) {
        playSound(ITEM_FALL, volumn);
        poolCount++;
    }

    public static void charFall() {
        playSound(CHAR_FALL, 0.5f);
        poolCount++;
    }

    public static void buttonClose() {
        playSound(BUTTON_CLOSE, 0.5f);
        poolCount++;
    }

    public static void buttonClick() {
        playSound(BUTTON_CLICK, 0.5f);
        poolCount++;
    }

    public static void upgradeSuccess() {
        playSound(UPGRADE_SUCCESS, 1.0f);
        poolCount++;
    }

    public static void upgradeFailed() {
        playSound(UPGRADE_FAILED, 1.0f);
        poolCount++;
    }

    public static void disposeAll() {
        if (music != null) {
            Res.outz("CO CHAY VO DAY");
            int i = 0;
            while (true) {
                SoundGdx[] soundGdxArr = music;
                if (i < soundGdxArr.length) {
                    SoundGdx soundGdx = soundGdxArr[i];
                    if (soundGdx != null) {
                        soundGdx.dispose();
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static void stopAll() {
        if (music != null) {
            int i = 0;
            while (true) {
                SoundGdx[] soundGdxArr = music;
                if (i < soundGdxArr.length) {
                    SoundGdx soundGdx = soundGdxArr[i];
                    if (soundGdx != null) {
                        soundGdx.stop();
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static void playSound(int id, float volume2) {
        SoundGdx[] soundGdxArr;
        try {
            if (CanvasNinja.isPlaySound && id != -1 && music != null && (soundGdxArr = sound) != null) {
                if (id <= soundGdxArr.length - 1) {
                    SoundGdx soundGdx = soundGdxArr[id];
                    soundGdx.pan = 0.0f;
                    soundGdx.play();
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public static void playSound(int id) {
        SoundGdx[] soundGdxArr;
        try {
            if (CanvasNinja.isPlaySound && id != -1 && music != null && (soundGdxArr = sound) != null) {
                if (id <= soundGdxArr.length - 1) {
                    SoundGdx soundGdx = soundGdxArr[id];
                    soundGdx.pan = 0.0f;
                    soundGdx.play();
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public static void playSound(int id, float volume2, float pan) {
        try {
            if (CanvasNinja.isPlaySound && id != -1 && music != null) {
                SoundGdx[] soundGdxArr = sound;
                if (soundGdxArr != null) {
                    SoundGdx soundGdx = soundGdxArr[id];
                    soundGdx.pan = pan;
                    soundGdx.play();
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public static void playMus(int id, boolean isLoop) {
        try {
            stopAll();
            if (!CanvasNinja.isPlaySound) {
                return;
            }
            if (id != -1) {
                music[id].setVolume((VOLUME_BG_MAX * PERCENT_BG) / 100.0f);
                music[id].setLooping(isLoop);
                music[id].play();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
