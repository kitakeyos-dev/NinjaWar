package ninjawar.mylib;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.Res;

import java.io.PrintStream;

public class SoundGdx {
    public int delaySound;
    public int idSound;
    public int index;
    public boolean isLoop;
    public boolean isSoundMap;
    public String name = "";
    public Music objMusic;
    public Sound objSound;
    public float pan = 0.0f;
    public long timeStartSound;

    public SoundGdx(String name2, boolean isLoop2) {
        this.index = Res.convertStringToNumber(name2);
        this.name = name2;
        this.isSoundMap = false;
        this.isLoop = isLoop2;
        if (!name2.contains(".ogg")) {
            try {
                createAudio();
            } catch (Exception e) {
                PrintStream printStream = System.out;
                printStream.println("sound null = " + name2);
            }
        } else {
            throw new IllegalArgumentException("ONLY SUPPORT FILE.WAV AMD ");
        }
    }

    private void createAudio() {
        try {
            if (this.isLoop) {
                if (this.objMusic == null) {
                    this.name = "/music/" + this.name + ".mp3";
                    Files files = Gdx.files;
                    FileHandle file = files.internal(LibSysCustom.res + this.name);
                    if (file.exists()) {
                        Music newMusic = Gdx.audio.newMusic(file);
                        this.objMusic = newMusic;
                        newMusic.setLooping(true);
                    } else {
                        FileHandle fileRMS = loadRMSFromServer(FileData.getPathFromIdAndType((short) this.index, FileData.TYPE_MUSIC));
                        if (fileRMS.exists()) {
                            Music newMusic2 = Gdx.audio.newMusic(fileRMS);
                            this.objMusic = newMusic2;
                            newMusic2.setLooping(true);
                        }
                    }
                }
            } else if (this.objSound == null) {
                this.name = "/sound/" + this.name + ".wav";
                Files files2 = Gdx.files;
                FileHandle file2 = files2.internal(LibSysCustom.res + this.name);
                if (file2.exists()) {
                    this.objSound = Gdx.audio.newSound(file2);
                    this.idSound = Res.convertStringToNumber(this.name);
                    return;
                }
                FileHandle fileRMS2 = loadRMSFromServer(FileData.getPathFromIdAndType((short) this.index, FileData.TYPE_SOUND));
                if (fileRMS2.exists()) {
                    this.objSound = Gdx.audio.newSound(fileRMS2);
                    this.idSound = Res.convertStringToNumber(this.name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("sound null = " + this.name);
        }
    }

    public void play() {
        createAudio();
        if (this.isLoop) {
            Music music = this.objMusic;
            if (music != null) {
                music.setVolume((AudioManager.VOLUME_BG_MAX * AudioManager.PERCENT_BG) / 100.0f);
                this.objMusic.play();
            }
        } else if (this.objSound != null && mSystem.currentTimeMillis() >= this.timeStartSound) {
            this.objSound.play((AudioManager.VOLUME_EFF_MAX * AudioManager.PERCENT_EFF) / 100.0f, ((float) (Math.random() * 1.5d)) + 0.5f, this.pan);
            this.timeStartSound = mSystem.currentTimeMillis() + ((long) this.delaySound);
        }
    }

    public void stop() {
        if (!this.isLoop) {
            Sound sound = this.objSound;
            if (sound != null) {
                sound.stop();
                this.timeStartSound = 0;
            }
        } else if (this.objMusic != null) {
            Res.outz(1, "STOP MUSIC");
            this.objMusic.stop();
        }
    }

    public void dispose() {
        if (!this.isLoop) {
            Sound sound = this.objSound;
            if (sound != null) {
                sound.dispose();
                this.timeStartSound = 0;
            }
        } else if (this.objMusic != null) {
            Res.outz(1, "STOP MUSIC");
            this.objMusic.dispose();
        }
    }

    public void setLooping(boolean isLoop2) {
        Music music = this.objMusic;
        if (music != null) {
            music.setLooping(isLoop2);
        }
    }

    public void setVolume(float volume) {
        Music music = this.objMusic;
        if (music != null) {
            music.setVolume(volume);
        }
    }

    public static FileHandle loadRMSFromServer(String path) {
        try {
            if (NinjaMidlet.isPC) {
                FileHandle fileHandle = Rms.loadFileHandleRMSNew(Rms.pathPCSave + path);
                if (!fileHandle.exists()) {
                    Files files = Gdx.files;
                    fileHandle = files.internal(LibSysCustom.res + path);
                }
                return fileHandle;
            }
            Files files2 = Gdx.files;
            return Rms.loadFileHandleRMSNew(files2.local("data/" + path).file().getAbsolutePath());
        } catch (Exception e) {
            PrintStream printStream = System.out;
            printStream.println("văng catch khi đọc rms:" + "");
            return null;
        }
    }
}
