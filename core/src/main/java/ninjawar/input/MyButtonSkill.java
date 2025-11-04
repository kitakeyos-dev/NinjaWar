// Class Version: 8
package ninjawar.input;

import com.badlogic.gdx.graphics.g2d.Sprite;
import ninjawar.model.Skill;
import ninjawar.mylib.*;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.Player;
import ninjawar.supporter.KeyMove;
import ninjawar.supporter.LoadDataManager;
import ninjawar.template.SkillTemplate;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Vector;

public class MyButtonSkill {
    public static int COLOR_BLUE;
    public static int COLOR_CHARKA_CAM;
    public static int COLOR_CHARKA_TIM;
    public static int COLOR_GREEN;
    public static int COLOR_JUMP;
    public static int COLOR_PINK;
    public static int COLOR_SKILL_BIG;
    public static int COLOR_YELLOW;
    public static byte TYPE_CHARKA;
    public static byte TYPE_JUMP;
    public static byte TYPE_SKILL;
    public static Atlas atlasSkill;
    public static boolean isClickTrung;
    public FrameImage frameImg;
    public int h;
    int hCoolDown;
    int hCoolDownBig;
    public mSprite iconSkill;
    public int indexBtnSkill;
    public boolean isFocus;
    public boolean isFocusKey;
    public boolean isJump;
    boolean isOpacity;
    boolean isScale;
    public int keySkill;
    int percentOpacity;
    public int pointerIndex;
    public Skill skill;
    public int tickClick;
    public int tickClickKey;
    int timeCoolDown;
    public byte type;
    public int w;
    public int x;
    public int y;

    static {
        MyButtonSkill.COLOR_YELLOW = 0;
        MyButtonSkill.COLOR_GREEN = 1;
        MyButtonSkill.COLOR_BLUE = 2;
        MyButtonSkill.COLOR_PINK = 3;
        MyButtonSkill.COLOR_SKILL_BIG = 4;
        MyButtonSkill.COLOR_JUMP = 5;
        MyButtonSkill.COLOR_CHARKA_TIM = 6;
        MyButtonSkill.COLOR_CHARKA_CAM = 7;
        MyButtonSkill.TYPE_SKILL = 0;
        MyButtonSkill.TYPE_JUMP = 1;
        MyButtonSkill.TYPE_CHARKA = 2;
        MyButtonSkill.isClickTrung = false;
    }

    public MyButtonSkill(int n, int n2, final int n3, final int indexBtnSkill, final byte b) {
        this.keySkill = -1999;
        this.isJump = false;
        this.isFocus = false;
        this.isFocusKey = false;
        this.pointerIndex = -1;
        this.isScale = false;
        this.isOpacity = true;
        this.percentOpacity = 65;
        final FrameImage frameImg = LoadDataManager.mySkillButton[n3];
        this.frameImg = frameImg;
        final float frameWidth = frameImg.frameWidth;
        this.x = n - (int) frameWidth;
        final float frameHeight = frameImg.frameHeight;
        this.y = n2 - (int) frameHeight;
        n2 = 31;
        if (frameImg != null) {
            n = (int) frameWidth;
        } else {
            n = 31;
        }
        this.w = n;
        n = n2;
        if (frameImg != null) {
            n = (int) frameHeight;
        }
        this.h = n;
        this.indexBtnSkill = indexBtnSkill;
        this.type = b;
        if (b == MyButtonSkill.TYPE_JUMP) {
            this.isJump = true;
        }
    }

    public MyButtonSkill(int n, int n2, final int n3, final int indexBtnSkill, final Skill skill) {
        this.keySkill = -1999;
        this.isJump = false;
        this.isFocus = false;
        this.isFocusKey = false;
        this.pointerIndex = -1;
        this.isScale = false;
        this.isOpacity = true;
        this.percentOpacity = 65;
        final FrameImage frameImg = LoadDataManager.mySkillButton[n3];
        this.frameImg = frameImg;
        final float frameWidth = frameImg.frameWidth;
        this.x = n - (int) frameWidth;
        final float frameHeight = frameImg.frameHeight;
        this.y = n2 - (int) frameHeight;
        n2 = 31;
        if (frameImg != null) {
            n = (int) frameWidth;
        } else {
            n = 31;
        }
        this.w = n;
        n = n2;
        if (frameImg != null) {
            n = (int) frameHeight;
        }
        this.h = n;
        this.skill = skill;
        this.indexBtnSkill = indexBtnSkill;
        this.type = MyButtonSkill.TYPE_SKILL;
        final Atlas atlasSkill = MyButtonSkill.atlasSkill;
        final mSprite mSprite = null;
        if (atlasSkill == null) {
            Atlas atlas = null;
            Label_0205:
            {
                if (skill != null) {
                    final SkillTemplate template = skill.template;
                    if (template != null && template.iconId != -1) {
                        atlas = Atlas.createAtlas((short) 11, (short) 23);
                        break Label_0205;
                    }
                }
                atlas = null;
            }
            MyButtonSkill.atlasSkill = atlas;
        }
        mSprite iconSkill = null;
        Label_0313:
        {
            if (skill != null) {
                final SkillTemplate template2 = skill.template;
                if (template2 != null && template2.iconId != -1) {
                    final Atlas atlasSkill2 = MyButtonSkill.atlasSkill;
                    if (atlasSkill2 != null) {
                        final HashMap<String, Sprite> sprites = atlasSkill2.sprites;
                        final StringBuilder sb = new StringBuilder();
                        sb.append(skill.template.iconId);
                        sb.append("");
                        iconSkill = new mSprite(sprites.get(sb.toString()));
                        break Label_0313;
                    }
                }
            }
            iconSkill = mSprite;
        }
        this.iconSkill = iconSkill;
    }

    public static MyButtonSkill findBtnSkillByKeyPress(final int n) {
        for (final MyButtonSkill myButtonSkill : MapScr.myButtonSkills) {
            final int keySkill = myButtonSkill.keySkill;
            if (keySkill != -1999 && keySkill == n) {
                return myButtonSkill;
            }
        }
        return null;
    }

    public static boolean isClickSkill(final Vector<MyButtonSkill> vector) {
        if (vector != null && vector.size() != 0) {
            for (int i = CanvasNinja.pointerNew.length - 1; i >= 0; --i) {
                final Pointer pointer = CanvasNinja.pointerNew[i];
                final boolean isPointerClick = pointer.isPointerClick;
                if (isPointerClick || pointer.isPointerDragged) {
                    if (isPointerClick && i == 0 && !CanvasNinja.isPointerClick) {
                        CanvasNinja.pointerNew[i].isPointerClick = false;
                    } else {
                        MyButtonSkill.isClickTrung = false;
                        for (final MyButtonSkill myButtonSkill : vector) {
                            if (CanvasNinja.isPointNew(myButtonSkill.x, myButtonSkill.y, myButtonSkill.w, myButtonSkill.h, i)) {
                                final KeyMove keyMove = MapScr.keyMove;
                                if (keyMove.dirIndex[i] != -1) {
                                    keyMove.reset(i);
                                }
                                myButtonSkill.isFocus = true;
                                myButtonSkill.pointerIndex = i;
                                MyButtonSkill.isClickTrung = true;
                                if (!myButtonSkill.isJump) {
                                    final Pointer pointer2 = CanvasNinja.pointerNew[i];
                                    if (!pointer2.isPointerDragged) {
                                        pointer2.isPointerClick = false;
                                        if (myButtonSkill.type == MyButtonSkill.TYPE_SKILL && myButtonSkill.skill != null) {
                                            Player.myCharz().doFire(myButtonSkill.skill);
                                        }
                                        if (myButtonSkill.type == MyButtonSkill.TYPE_CHARKA) {
                                            Player.myCharz().isHaveChakra ^= true;
                                            Player.myCharz().useCharka(Player.myCharz().isHaveChakra);
                                        }
                                    }
                                }
                                if (myButtonSkill.isJump) {
                                    final PrintStream out = System.out;
                                    final StringBuilder sb = new StringBuilder();
                                    sb.append("zzzzz log vị trí pointer trong nhảy skill:");
                                    sb.append(i);
                                    sb.append("_dragged:");
                                    sb.append(CanvasNinja.pointerNew[i].isPointerDragged);
                                    sb.append("_Click:");
                                    sb.append(CanvasNinja.pointerNew[i].isPointerClick);
                                    out.println(sb.toString());
                                    CanvasNinja.keyHoldSpec[2] = true;
                                    break;
                                }
                                break;
                            } else {
                                if (!myButtonSkill.isFocus) {
                                    continue;
                                }
                                myButtonSkill.isFocus = false;
                                myButtonSkill.pointerIndex = -1;
                                if (!myButtonSkill.isJump) {
                                    continue;
                                }
                                CanvasNinja.keyHoldSpec[2] = false;
                            }
                        }
                        if (MyButtonSkill.isClickTrung) {
                            break;
                        }
                    }
                }
            }
            for (int j = CanvasNinja.pointerNew.length - 1; j >= 0; --j) {
                if (CanvasNinja.pointerNew[j].isPointerRelease) {
                    for (final MyButtonSkill myButtonSkill2 : vector) {
                        if (myButtonSkill2.pointerIndex == j) {
                            final Pointer pointer3 = CanvasNinja.pointerNew[j];
                            pointer3.isPointerClick = false;
                            pointer3.isPointerDragged = false;
                            pointer3.isPointerRelease = false;
                            myButtonSkill2.isFocus = false;
                            myButtonSkill2.pointerIndex = -1;
                            if (!myButtonSkill2.isJump) {
                                continue;
                            }
                            CanvasNinja.keyHoldSpec[2] = false;
                        }
                    }
                }
            }
            return MyButtonSkill.isClickTrung;
        }
        return false;
    }

    public static boolean isKeySkillDefault(final int[] array) {
        if (array == null) {
            return true;
        }
        final boolean b = true;
        int n = 1;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= array.length) {
                break;
            }
            if (array[n] != -1999) {
                b2 = false;
                break;
            }
            ++n;
        }
        return b2;
    }

    public boolean isCanUse() {
        final Skill skill = this.skill;
        return skill != null && skill.isCanUse();
    }

    public boolean isSkillUse() {
        final int indexBtnSkill = this.indexBtnSkill;
        boolean b2;
        final boolean b = b2 = true;
        if (indexBtnSkill != 1) {
            b2 = b;
            if (indexBtnSkill != 2) {
                b2 = b;
                if (indexBtnSkill != 3) {
                    b2 = (indexBtnSkill == 4 && b);
                }
            }
        }
        return b2;
    }

    public void paint(final mGraphics mGraphics) {
        CanvasNinja.resetClip(mGraphics);
        final FrameImage frameImg = this.frameImg;
        if (frameImg != null) {
            final boolean isScale = this.isScale;
            final int n = 0;
            if (isScale) {
                int n2;
                if (!this.isFocus && !this.isFocusKey) {
                    n2 = 0;
                } else {
                    n2 = 1;
                }
                frameImg.drawFrameSpecScale(n2, (float) this.x, (float) this.y, 0, true, mGraphics, NinjaMidlet.SCALE_SPEC);
            } else {
                int n3;
                if (!this.isFocus && !this.isFocusKey) {
                    n3 = 0;
                } else {
                    n3 = 1;
                }
                frameImg.drawFrame(mGraphics, n3, (float) this.x, (float) this.y, 0, true, this.isOpacity, this.percentOpacity);
            }
            int n4 = 0;
            Label_0150:
            {
                if (!this.isFocus) {
                    n4 = n;
                    if (!this.isFocusKey) {
                        break Label_0150;
                    }
                }
                n4 = 2;
            }
            final mSprite iconSkill = this.iconSkill;
            if (iconSkill != null && this.indexBtnSkill != 0) {
                if (this.isScale) {
                    mGraphics.drawSpriteSpecZoom(iconSkill, (float) (this.x + 4 - 1), (float) (this.y + 4 + n4 - 1), NinjaMidlet.SCALE_SPEC, true);
                } else {
                    mGraphics.drawSpriteSpecZoom(iconSkill, (float) (this.x + 4 - 1), (float) (this.y + 4 + n4 - 1), NinjaMidlet.SCALE_SPEC, true);
                }
            }
            final Skill skill = this.skill;
            if (skill != null) {
                final long lastTimeUseSkill = skill.lastTimeUseSkill;
                if (lastTimeUseSkill != 0L) {
                    final int timeCoolDown = (int) (lastTimeUseSkill - mSystem.currentTimeMillis());
                    this.timeCoolDown = timeCoolDown;
                    if (this.indexBtnSkill != 0) {
                        this.hCoolDown = (int) (timeCoolDown * 1.0f / this.skill.coolDown * LoadDataManager.coolDownSkill.getRHeight());
                        mGraphics.setClip(this.x + 4, this.y + 4 + n4 + (LoadDataManager.coolDownSkill.getRHeight() - this.hCoolDown), LoadDataManager.coolDownSkill.getRWidth(), this.hCoolDown);
                        if (this.isScale) {
                            mGraphics.drawImageSpecZoom(LoadDataManager.coolDownSkill, (float) (this.x + 4), (float) (this.y + 4 + n4), NinjaMidlet.SCALE_SPEC, true);
                        } else {
                            mGraphics.drawImage(LoadDataManager.coolDownSkill, (float) (this.x + 4), (float) (this.y + 4 + n4), true, this.isOpacity, this.percentOpacity);
                        }
                    } else {
                        this.hCoolDownBig = (int) (timeCoolDown * 1.0f / this.skill.coolDown * LoadDataManager.coolDownSkillBig.getRHeight());
                        mGraphics.setClip(this.x + 4, this.y + 4 + n4 + (LoadDataManager.coolDownSkillBig.getRHeight() - this.hCoolDownBig), LoadDataManager.coolDownSkillBig.getRWidth(), this.hCoolDownBig);
                        if (this.isScale) {
                            mGraphics.drawImageSpecZoom(LoadDataManager.coolDownSkillBig, (float) (this.x + 4), (float) (this.y + 4 + n4), NinjaMidlet.SCALE_SPEC, true);
                        } else {
                            mGraphics.drawImage(LoadDataManager.coolDownSkillBig, (float) (this.x + 4), (float) (this.y + 4 + n4), true, this.isOpacity, this.percentOpacity);
                        }
                    }
                    final int timeCoolDown2 = this.timeCoolDown;
                    if (timeCoolDown2 > 0) {
                        if (timeCoolDown2 >= 1000) {
                            final mFont tahoma_7 = mFont.tahoma_7;
                            final StringBuilder sb = new StringBuilder();
                            sb.append(this.timeCoolDown / 1000);
                            sb.append("");
                            final String string = sb.toString();
                            final int x = this.x;
                            final FrameImage frameImg2 = this.frameImg;
                            tahoma_7.drawString(mGraphics, string, (int) frameImg2.frameWidth / 2 + x, this.y + (int) frameImg2.frameHeight / 2, 3, false);
                        } else {
                            final mFont tahoma_8 = mFont.tahoma_7;
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append("0.");
                            sb2.append(this.timeCoolDown / 100);
                            final String string2 = sb2.toString();
                            final int x2 = this.x;
                            final FrameImage frameImg3 = this.frameImg;
                            tahoma_8.drawString(mGraphics, string2, (int) frameImg3.frameWidth / 2 + x2, this.y + (int) frameImg3.frameHeight / 2, 3, false);
                        }
                    }
                }
            }
        }
    }

    public void paintOpacity(final mGraphics mGraphics) {
        final FrameImage frameImg = this.frameImg;
        if (frameImg != null) {
            final boolean isFocus = this.isFocus;
            final int n = 0;
            int n2;
            if (!isFocus && !this.isFocusKey) {
                n2 = 0;
            } else {
                n2 = 1;
            }
            frameImg.drawFrame(n2, (float) this.x, (float) this.y, 0, mGraphics, true, 50);
            final mSprite iconSkill = this.iconSkill;
            if (iconSkill != null && this.indexBtnSkill != 0) {
                int n3 = 0;
                Label_0098:
                {
                    if (!this.isFocus) {
                        n3 = n;
                        if (!this.isFocusKey) {
                            break Label_0098;
                        }
                    }
                    n3 = 2;
                }
                mGraphics.drawSprite(iconSkill, (float) (this.x + 4), (float) (this.y + 4 + n3), 0, 0, true, this.isOpacity, 50, false);
            }
        }
    }

    public void resetClickBtn() {
        this.tickClick = 0;
        if (this.type == MyButtonSkill.TYPE_JUMP) {
            CanvasNinja.keyHoldSpec[2] = false;
        }
    }

    public void setSkill(final Skill skill) {
        this.skill = skill;
        final Atlas atlasSkill = MyButtonSkill.atlasSkill;
        final mSprite mSprite = null;
        if (atlasSkill == null) {
            Atlas atlas = null;
            Label_0049:
            {
                if (skill != null) {
                    final SkillTemplate template = skill.template;
                    if (template != null && template.iconId != -1) {
                        atlas = Atlas.createAtlas((short) 11, (short) 23);
                        break Label_0049;
                    }
                }
                atlas = null;
            }
            MyButtonSkill.atlasSkill = atlas;
        }
        mSprite iconSkill = null;
        Label_0138:
        {
            if (skill != null) {
                final SkillTemplate template2 = skill.template;
                if (template2 != null && template2.iconId != -1) {
                    final Atlas atlasSkill2 = MyButtonSkill.atlasSkill;
                    if (atlasSkill2 != null) {
                        final HashMap<String, Sprite> sprites = atlasSkill2.sprites;
                        final StringBuilder sb = new StringBuilder();
                        sb.append(skill.template.iconId);
                        sb.append("");
                        iconSkill = new mSprite(sprites.get(sb.toString()));
                        break Label_0138;
                    }
                }
            }
            iconSkill = mSprite;
        }
        this.iconSkill = iconSkill;
    }

    public void update() {
        if (this.isFocus) {
            ++this.tickClick;
        }
        if (this.isFocusKey) {
            if (this.type == MyButtonSkill.TYPE_JUMP) {
                CanvasNinja.keyHoldSpec[2] = false;
            }
            if (++this.tickClickKey > 2) {
                this.isFocusKey = false;
                this.resetClickBtn();
            }
        }
    }

    public void useSkill() {
        this.isFocusKey = true;
        if (!this.isJump) {
            if (this.type == MyButtonSkill.TYPE_SKILL && this.skill != null) {
                Player.myCharz().doFire(this.skill);
            }
            if (this.type == MyButtonSkill.TYPE_CHARKA) {
                Player.myCharz().isHaveChakra ^= true;
                Player.myCharz().useCharka(Player.myCharz().isHaveChakra);
            }
        }
        if (this.isJump) {
            CanvasNinja.keyHoldSpec[2] = true;
        }
    }
}
