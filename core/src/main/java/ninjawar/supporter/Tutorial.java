// Class Version: 8
package ninjawar.supporter;

import ninjawar.screen.quest.QuestScreen;
import ninjawar.screen.quest.NPCTalkQuestScreen;
import ninjawar.screen.tab.TabWareHouse;
import java.util.Iterator;
import ninjawar.model.PopupItemInfo;
import ninjawar.object.Item;
import ninjawar.screen.tab.TabInventory;
import ninjawar.model.Skill;
import ninjawar.screen.tab.TabScr;
import ninjawar.screen.tab.TabLearnSkill;
import ninjawar.mylib.mSystem;
import ninjawar.myscr.Res;
import ninjawar.mylib.mGraphics;
import ninjawar.mymain.CanvasNinja;
import ninjawar.model.MyCommand;
import ninjawar.myscr.MapScr;
import ninjawar.input.MyButtonSkill;
import ninjawar.myscr.Player;
import ninjawar.mylib.Rms;
import ninjawar.template.SkillTemplate;
import ninjawar.object.mNPC;
import ninjawar.myscr.Quai;
import ninjawar.mylib.FrameImage;
import ninjawar.input.MyButton;

public class Tutorial
{
    public MyButton[] btns;
    public FrameImage frame;
    public int hFocus;
    public int id;
    public int indexFrame;
    public boolean isComplete;
    public boolean isDone;
    public boolean isUseCamera;
    public Quai mobFocus;
    public mNPC npcFocus;
    public SkillTemplate skillTemplateTemp;
    public int step;
    long timeStartStep2GanSkill;
    long timeStartStep6GanSkill;
    public int wFocus;
    public int x;
    public int y;

    public Tutorial(final int n) {
        boolean isComplete = false;
        this.step = 0;
        this.step = 0;
        final boolean b = true;
        final boolean b2 = true;
        switch (n) {
            case 8: {
                final StringBuilder sb = new StringBuilder();
                sb.append(Rms.RMS_THU_CUOI);
                sb.append(Player.myCharz().charID);
                if (Rms.loadRMSInt(sb.toString()) != -1) {
                    isComplete = true;
                }
                this.isComplete = isComplete;
                break;
            }
            case 7: {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(Rms.RMS_TAP_TRUNG_CHARKA);
                sb2.append(Player.myCharz().charID);
                this.isComplete = (Rms.loadRMSInt(sb2.toString()) != -1 && b2);
                this.x = MapScr.myButtonSkills.get(6).x + MapScr.myButtonSkills.get(6).w / 2;
                this.y = MapScr.myButtonSkills.get(6).y + MapScr.myButtonSkills.get(6).h / 2;
                this.isUseCamera = false;
                this.step = 0;
                break;
            }
            case 6: {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(Rms.RMS_GAN_SKILL);
                sb3.append(Player.myCharz().charID);
                this.isComplete = (Rms.loadRMSInt(sb3.toString()) != -1);
                if (MapScr.gI().isShowMenuHidden()) {
                    this.step = 1;
                }
                else {
                    this.step = 0;
                }
                this.isUseCamera = false;
                break;
            }
            case 5: {
                final StringBuilder sb4 = new StringBuilder();
                sb4.append(Rms.RMS_THAO_TAC_KHO);
                sb4.append(Player.myCharz().charID);
                this.isComplete = (Rms.loadRMSInt(sb4.toString()) != -1);
                this.npcFocus = MapScr.findNPCInMap((short)3);
                this.isUseCamera = true;
                this.step = 0;
                break;
            }
            case 4: {
                final StringBuilder sb5 = new StringBuilder();
                sb5.append(Rms.RMS_THAO_TAC_ITEM);
                sb5.append(Player.myCharz().charID);
                this.isComplete = (Rms.loadRMSInt(sb5.toString()) != -1);
                if (MapScr.gI().isShowMenuHidden()) {
                    this.step = 1;
                }
                else {
                    this.step = 0;
                }
                this.isUseCamera = false;
                break;
            }
            case 3: {
                final StringBuilder sb6 = new StringBuilder();
                sb6.append(Rms.RMS_DOUBLE_JUMP);
                sb6.append(Player.myCharz().charID);
                this.isComplete = (Rms.loadRMSInt(sb6.toString()) != -1 && b);
                this.x = MapScr.myButtonSkills.get(5).x + MapScr.myButtonSkills.get(5).w / 2;
                this.y = MapScr.myButtonSkills.get(5).y + MapScr.myButtonSkills.get(5).h / 2;
                this.isUseCamera = false;
                break;
            }
            case 2: {
                final StringBuilder sb7 = new StringBuilder();
                sb7.append(Rms.RMS_ATK_ENEMY);
                sb7.append(Player.myCharz().charID);
                this.isComplete = (Rms.loadRMSInt(sb7.toString()) != -1);
                this.mobFocus = MapScr.findMobInMap(0);
                this.isUseCamera = true;
                break;
            }
            case 1: {
                final StringBuilder sb8 = new StringBuilder();
                sb8.append(Rms.RMS_TUONG_TAC_NPC);
                sb8.append(Player.myCharz().charID);
                this.isComplete = (Rms.loadRMSInt(sb8.toString()) != -1);
                this.npcFocus = MapScr.findNPCInMap((short)0);
                this.isUseCamera = true;
                break;
            }
            case 0: {
                this.skipAll();
                break;
            }
        }
        final mNPC npcFocus = this.npcFocus;
        if (npcFocus != null) {
            this.x = npcFocus.cx;
            this.y = npcFocus.cy - npcFocus.ch / 2;
        }
        final Quai mobFocus = this.mobFocus;
        if (mobFocus != null) {
            this.x = mobFocus.x;
            this.y = mobFocus.y - mobFocus.h / 2;
        }
        this.init(n, this.x, this.y);
    }

    private void init(final int id, final int x, final int y) {
        this.isDone = false;
        this.id = id;
        this.x = x;
        this.y = y;
        this.frame = LoadDataManager.framePointer;
        this.wFocus = LoadDataManager.imgCircleTarget.getRWidth();
        this.hFocus = LoadDataManager.imgCircleTarget.getRHeight();
        this.indexFrame = 0;
        final MyButton[] btns = new MyButton[2];
        this.btns = btns;
        final FrameImage[] myButtons = LoadDataManager.myButtons;
        btns[0] = new MyButton(myButtons[1], myButtons[0], 55, 0, SupportTranslate.getTextLangue("skip"), 0, 0, new MyCommand("", 1999, MapScr.gI()));
        final MyButton myButton = this.btns[0];
        myButton.setXY(25, CanvasNinja.h / 2 - myButton.h);
        final MyButton[] btns2 = this.btns;
        btns2[0].isSmall = true;
        final FrameImage[] myButtons2 = LoadDataManager.myButtons;
        btns2[1] = new MyButton(myButtons2[1], myButtons2[0], 55, 0, "Tắt hướng dẫn", 0, 0, new MyCommand("", 2000, MapScr.gI()));
        this.btns[1].setXY(25, CanvasNinja.h / 2 + 10);
        this.btns[1].isSmall = true;
    }

    private void paintDoubleJump(final mGraphics mGraphics) {
        CanvasNinja.paintz.paintCircleTarget(mGraphics, this.x, this.y);
        final FrameImage frame = this.frame;
        if (frame != null) {
            frame.drawFrame(this.indexFrame, this.x - (int)frame.frameWidth / 2, this.y - (int)frame.frameHeight / 2, false, mGraphics);
        }
        CanvasNinja.paintz.paintDoubleJump(mGraphics, CanvasNinja.w / 2, CanvasNinja.h / 2);
    }

    private void paintFocusTarget(final mGraphics mGraphics) {
        if (this.isUseCamera) {
            CanvasNinja.paintz.paintCircleTarget(mGraphics, this.x - MapScr.cmx, this.y - MapScr.cmy);
            final FrameImage frame = this.frame;
            if (frame != null) {
                frame.drawFrame(this.indexFrame, this.x - (int)frame.frameWidth / 2 - MapScr.cmx, this.y - (int)frame.frameHeight / 2 - MapScr.cmy, false, mGraphics);
            }
        }
        else {
            CanvasNinja.paintz.paintCircleTarget(mGraphics, this.x, this.y);
            final FrameImage frame2 = this.frame;
            if (frame2 != null) {
                frame2.drawFrame(this.indexFrame, this.x - (int)frame2.frameWidth / 2, this.y - (int)frame2.frameHeight / 2, false, mGraphics);
            }
        }
    }

    private void updateDoubleJump() {
        if (Player.myCharz().isDoubleJump() && !this.isDone) {
            final StringBuilder sb = new StringBuilder();
            sb.append(Rms.RMS_DOUBLE_JUMP);
            sb.append(Player.myCharz().charID);
            Rms.saveRMSInt(sb.toString(), 1);
            this.isDone = true;
        }
    }

    public boolean isInTutorial() {
        final boolean isUseCamera = this.isUseCamera;
        boolean b = false;
        int n;
        if (isUseCamera) {
            n = -MapScr.cmx;
        }
        else {
            n = 0;
        }
        int n2;
        if (isUseCamera) {
            n2 = -MapScr.cmy;
        }
        else {
            n2 = 0;
        }
        final MyButton myButton = this.btns[0];
        if (!CanvasNinja.isPoint(myButton.xBtn, myButton.yBtn, myButton.w, myButton.h)) {
            final MyButton myButton2 = this.btns[1];
            if (!CanvasNinja.isPoint(myButton2.xBtn, myButton2.yBtn, myButton2.w, myButton2.h)) {
                final int x = this.x;
                final int wFocus = this.wFocus;
                final int n3 = wFocus / 2;
                final int y = this.y;
                final int hFocus = this.hFocus;
                if (!CanvasNinja.isPoint(x - n3 + n, y - hFocus / 2 + n2, wFocus, hFocus)) {
                    return b;
                }
            }
        }
        b = true;
        return b;
    }

    public boolean isStepGanSkill(final int n) {
        return this.id == 6 && this.step == n;
    }

    public boolean isStepThaoTacItem(final int n) {
        return this.id == 4 && this.step == n;
    }

    public boolean isStepThaoTacKho(final int n) {
        return this.id == 5 && this.step == n;
    }

    public boolean isStepUseCharka(final int n) {
        return this.id == 7 && this.step == n;
    }

    public boolean isTutorialEnemy() {
        return this.id == 2;
    }

    public boolean isTutorialNpc() {
        final int id = this.id;
        boolean b = true;
        if (id != 1) {
            b = false;
        }
        return b;
    }

    public void paint(final mGraphics mGraphics) {
        if (CanvasNinja.questScreen != null) {
            return;
        }
        if (this.isComplete) {
            this.isDone = true;
            return;
        }
        if (this.step != -1 && (this.x != -1 || this.y != -1)) {
            switch (this.id) {
                default: {
                    this.paintFocusTarget(mGraphics);
                    break;
                }
                case 3: {
                    this.paintDoubleJump(mGraphics);
                    break;
                }
                case 0: {
                    this.isDone = true;
                    break;
                }
            }
            final MyButton[] btns = this.btns;
            if (btns != null && this.id != 0) {
                for (int length = btns.length, i = 0; i < length; ++i) {
                    btns[i].paint(mGraphics);
                }
            }
        }
    }

    public void skip() {
        switch (this.id) {
            case 8: {
                final StringBuilder sb = new StringBuilder();
                sb.append(Rms.RMS_THU_CUOI);
                sb.append(Player.myCharz().charID);
                Rms.saveRMSInt(sb.toString(), 1);
                break;
            }
            case 7: {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(Rms.RMS_TAP_TRUNG_CHARKA);
                sb2.append(Player.myCharz().charID);
                Rms.saveRMSInt(sb2.toString(), 1);
                break;
            }
            case 6: {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(Rms.RMS_GAN_SKILL);
                sb3.append(Player.myCharz().charID);
                Rms.saveRMSInt(sb3.toString(), 1);
                break;
            }
            case 5: {
                final StringBuilder sb4 = new StringBuilder();
                sb4.append(Rms.RMS_THAO_TAC_KHO);
                sb4.append(Player.myCharz().charID);
                Rms.saveRMSInt(sb4.toString(), 1);
                break;
            }
            case 4: {
                final StringBuilder sb5 = new StringBuilder();
                sb5.append(Rms.RMS_THAO_TAC_ITEM);
                sb5.append(Player.myCharz().charID);
                Rms.saveRMSInt(sb5.toString(), 1);
                break;
            }
            case 3: {
                final StringBuilder sb6 = new StringBuilder();
                sb6.append(Rms.RMS_DOUBLE_JUMP);
                sb6.append(Player.myCharz().charID);
                Rms.saveRMSInt(sb6.toString(), 1);
                break;
            }
            case 2: {
                final StringBuilder sb7 = new StringBuilder();
                sb7.append(Rms.RMS_ATK_ENEMY);
                sb7.append(Player.myCharz().charID);
                Rms.saveRMSInt(sb7.toString(), 1);
                break;
            }
            case 1: {
                final StringBuilder sb8 = new StringBuilder();
                sb8.append(Rms.RMS_TUONG_TAC_NPC);
                sb8.append(Player.myCharz().charID);
                Rms.saveRMSInt(sb8.toString(), 1);
                break;
            }
        }
        this.isDone = true;
    }

    public void skipAll() {
        final StringBuilder sb = new StringBuilder();
        sb.append(Rms.RMS_DOUBLE_JUMP);
        sb.append(Player.myCharz().charID);
        Rms.saveRMSInt(sb.toString(), 1);
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(Rms.RMS_ATK_ENEMY);
        sb2.append(Player.myCharz().charID);
        Rms.saveRMSInt(sb2.toString(), 1);
        final StringBuilder sb3 = new StringBuilder();
        sb3.append(Rms.RMS_THAO_TAC_ITEM);
        sb3.append(Player.myCharz().charID);
        Rms.saveRMSInt(sb3.toString(), 1);
        final StringBuilder sb4 = new StringBuilder();
        sb4.append(Rms.RMS_TUONG_TAC_NPC);
        sb4.append(Player.myCharz().charID);
        Rms.saveRMSInt(sb4.toString(), 1);
        final StringBuilder sb5 = new StringBuilder();
        sb5.append(Rms.RMS_THAO_TAC_KHO);
        sb5.append(Player.myCharz().charID);
        Rms.saveRMSInt(sb5.toString(), 1);
        final StringBuilder sb6 = new StringBuilder();
        sb6.append(Rms.RMS_GAN_SKILL);
        sb6.append(Player.myCharz().charID);
        Rms.saveRMSInt(sb6.toString(), 1);
        final StringBuilder sb7 = new StringBuilder();
        sb7.append(Rms.RMS_TAP_TRUNG_CHARKA);
        sb7.append(Player.myCharz().charID);
        Rms.saveRMSInt(sb7.toString(), 1);
        final StringBuilder sb8 = new StringBuilder();
        sb8.append(Rms.RMS_THU_CUOI);
        sb8.append(Player.myCharz().charID);
        Rms.saveRMSInt(sb8.toString(), 1);
        this.isDone = true;
    }

    public void update() {
        if (CanvasNinja.questScreen != null) {
            return;
        }
        final FrameImage frame = this.frame;
        int i = 0;
        if (frame != null && CanvasNinja.gameTick % 3 == 0 && ++this.indexFrame > frame.nFrame - 1) {
            this.indexFrame = 0;
        }
        switch (this.id) {
            case 7: {
                this.updateUseCharka(this.step);
                break;
            }
            case 6: {
                this.updateGanSkill(this.step);
                break;
            }
            case 5: {
                this.updateStepThaoTacKho(this.step);
                break;
            }
            case 4: {
                this.updateStepThaoTacItem(this.step);
                break;
            }
            case 3: {
                this.updateDoubleJump();
                break;
            }
            case 2: {
                this.updateAtkEnemy();
                break;
            }
        }
        final MyButton[] btns = this.btns;
        if (btns != null) {
            while (i < btns.length) {
                btns[i].update();
                ++i;
            }
        }
    }

    public void updateAtkEnemy() {
        final Quai mobFocus = this.mobFocus;
        if (mobFocus != null && mobFocus.isCanAttack(true)) {
            final Quai mobFocus2 = this.mobFocus;
            if (mobFocus2.isWaitingCheckTutorial && !mobFocus2.isCheckDieTutorial) {
                mobFocus2.isCheckDieTutorial = true;
                mobFocus2.isWaitingCheckTutorial = false;
            }
        }
        final Quai mobFocus3 = this.mobFocus;
        if (mobFocus3 != null && mobFocus3.isCheckDieTutorial && !mobFocus3.isCanAttack(true)) {
            final Quai mobFocus4 = this.mobFocus;
            mobFocus4.isCheckDieTutorial = false;
            mobFocus4.isWaitingCheckTutorial = false;
            this.isDone = true;
            Res.outz(1, "DONE TUT 2");
            final StringBuilder sb = new StringBuilder();
            sb.append(Rms.RMS_ATK_ENEMY);
            sb.append(Player.myCharz().charID);
            Rms.saveRMSInt(sb.toString(), 1);
        }
    }

    public void updateGanSkill(int i) {
        int n = i;
        if (this.timeStartStep2GanSkill != 0L) {
            n = i;
            if (mSystem.currentTimeMillis() >= this.timeStartStep2GanSkill) {
                this.timeStartStep2GanSkill = 0L;
                n = 3;
            }
        }
        i = n;
        if (this.isStepGanSkill(6)) {
            i = n;
            if (CanvasNinja.imgHoldToMove != null) {
                this.timeStartStep6GanSkill = 0L;
                i = 6;
            }
        }
        int step = i;
        if (this.isStepGanSkill(6)) {
            step = i;
            if (this.timeStartStep6GanSkill != 0L) {
                step = i;
                if (mSystem.currentTimeMillis() >= this.timeStartStep6GanSkill) {
                    this.timeStartStep6GanSkill = 0L;
                    step = 2;
                    CanvasNinja.imgHoldToMoveTutorial = null;
                }
            }
        }
        this.step = step;
        i = 0;
        switch (step) {
            case 7: {
                final StringBuilder sb = new StringBuilder();
                sb.append(Rms.RMS_GAN_SKILL);
                sb.append(Player.myCharz().charID);
                Rms.saveRMSInt(sb.toString(), 1);
                this.isDone = true;
                break;
            }
            case 6: {
                if (this.timeStartStep6GanSkill == 0L) {
                    this.timeStartStep6GanSkill = mSystem.currentTimeMillis() + 10000L;
                    break;
                }
                break;
            }
            case 5: {
                final SkillTemplate skillTemplateTemp = this.skillTemplateTemp;
                if (skillTemplateTemp != null) {
                    CanvasNinja.imgHoldToMoveTutorial = null;
                    this.x = skillTemplateTemp.xTutorial;
                    this.y = skillTemplateTemp.yTutorial;
                    this.step = 6;
                    break;
                }
                break;
            }
            case 4: {
                if (this.skillTemplateTemp != null) {
                    final int x = MapScr.myButtonSkills.get(1).x + MapScr.myButtonSkills.get(1).w / 2;
                    i = MapScr.myButtonSkills.get(1).y - 2 + MapScr.myButtonSkills.get(1).h / 2;
                    final SkillTemplate skillTemplateTemp2 = this.skillTemplateTemp;
                    final float n2 = (float)(int)((i - skillTemplateTemp2.yTutorial) / ((x - skillTemplateTemp2.xTutorial) * 1.0f / 5));
                    final int x2 = this.x + 5;
                    this.x = x2;
                    final int y = (int)(this.y + n2);
                    this.y = y;
                    if (x2 >= x) {
                        this.x = x;
                    }
                    if (y >= i) {
                        this.y = i;
                    }
                    CanvasNinja.setPosImgHoldToMoveTutorial(this.x, this.y);
                    if (this.x == x && this.y == i) {
                        this.step = 5;
                    }
                    break;
                }
                break;
            }
            case 3: {
                final SkillTemplate skillTemplateTemp3 = this.skillTemplateTemp;
                if (skillTemplateTemp3 != null) {
                    CanvasNinja.imgHoldToMoveTutorial = skillTemplateTemp3.imgSkillTemplate;
                    i = skillTemplateTemp3.xTutorial;
                    CanvasNinja.setPosImgHoldToMoveTutorial(this.x = i, this.y = skillTemplateTemp3.yTutorial);
                    this.step = 4;
                    break;
                }
                break;
            }
            case 2: {
                final TabScr currentTab = CanvasNinja.currentTab;
                if (currentTab != null && currentTab instanceof TabLearnSkill) {
                    final TabLearnSkill tabLearnSkill = (TabLearnSkill)currentTab;
                    this.skillTemplateTemp = null;
                    for (SkillTemplate[] skillPaints = tabLearnSkill.skillPaints; i < skillPaints.length; ++i) {
                        final SkillTemplate skillTemplateTemp4 = skillPaints[i];
                        if (skillTemplateTemp4.isLv10Required()) {
                            final Skill skill = Player.myCharz().getSkill(skillTemplateTemp4);
                            if (skill != null && skill.isSkillCoTheGan()) {
                                this.skillTemplateTemp = skillTemplateTemp4;
                                skillTemplateTemp4.isFocusTutorial = true;
                                break;
                            }
                        }
                    }
                    final SkillTemplate skillTemplateTemp5 = this.skillTemplateTemp;
                    if (skillTemplateTemp5 != null) {
                        this.x = skillTemplateTemp5.xTutorial;
                        this.y = skillTemplateTemp5.yTutorial;
                        if (this.timeStartStep2GanSkill == 0L) {
                            this.timeStartStep2GanSkill = mSystem.currentTimeMillis() + 300L;
                        }
                    }
                    else {
                        this.x = -1;
                        this.y = -1;
                    }
                    break;
                }
                break;
            }
            case 1: {
                if (MapScr.gI().menuHidden != null) {
                    this.x = MapScr.gI().menuHidden.vecCmds.get(2).x + MapScr.gI().menuHidden.vecCmds.get(2).w / 2;
                    this.y = MapScr.gI().menuHidden.vecCmds.get(2).y + MapScr.gI().menuHidden.vecCmds.get(2).h / 2;
                    break;
                }
                break;
            }
            case 0: {
                this.x = MapScr.gI().vecCmds.get(0).x + (int)MapScr.gI().vecCmds.get(0).frameImg.frameWidth / 2;
                this.y = MapScr.gI().vecCmds.get(0).y + (int)MapScr.gI().vecCmds.get(0).frameImg.frameHeight / 2;
                break;
            }
        }
    }

    public void updatePointer() {
        if (CanvasNinja.questScreen != null) {
            return;
        }
        if (!this.isInTutorial()) {
            CanvasNinja.clearAllPointer();
        }
        final MyButton[] btns = this.btns;
        if (btns != null) {
            for (int length = btns.length, i = 0; i < length; ++i) {
                btns[i].updatePointer();
            }
        }
    }

    public void updateStepThaoTacItem(int step) {
        switch (this.step = step) {
            case 4: {
                this.isDone = true;
                final StringBuilder sb = new StringBuilder();
                sb.append(Rms.RMS_THAO_TAC_ITEM);
                sb.append(Player.myCharz().charID);
                Rms.saveRMSInt(sb.toString(), 1);
                break;
            }
            case 3: {
                final TabScr currentTab = CanvasNinja.currentTab;
                if (currentTab != null && currentTab instanceof TabInventory) {
                    final TabInventory tabInventory = (TabInventory)currentTab;
                    final PopupItemInfo popupItemInfo = tabInventory.popupItemInfo;
                    if (popupItemInfo != null && popupItemInfo.box.item.isKhoaiTayChien()) {
                        final MyButton myButton = tabInventory.popupItemInfo.btns[0];
                        this.x = myButton.xBtn + myButton.w / 2;
                        this.y = myButton.yBtn + myButton.h / 2;
                    }
                    else {
                        this.x = -1;
                        this.y = -1;
                    }
                    break;
                }
                break;
            }
            case 2: {
                final TabScr currentTab2 = CanvasNinja.currentTab;
                if (currentTab2 != null && currentTab2 instanceof TabInventory) {
                    final TabInventory tabInventory2 = (TabInventory)currentTab2;
                    final Item item = null;
                    step = 0;
                    final Iterator<Item> iterator = tabInventory2.vecItemInvens.iterator();
                    Item item2;
                    while (true) {
                        item2 = item;
                        if (!iterator.hasNext()) {
                            break;
                        }
                        final Item item3 = iterator.next();
                        if (item3.isKhoaiTayChien()) {
                            item2 = item3;
                            item3.isFocusTutorial = true;
                            break;
                        }
                        ++step;
                    }
                    if (item2 != null) {
                        this.x = item2.xTutorial;
                        this.y = item2.yTutorial;
                    }
                    else {
                        this.x = -1;
                        this.y = -1;
                        MapScr.gI().startInfoServer(SupportTranslate.getTextLangue("DONT_HAVE_ITEM_HEAL"), 3);
                        this.isDone = true;
                    }
                    break;
                }
                this.isDone = true;
                break;
            }
            case 1: {
                if (MapScr.gI().menuHidden != null) {
                    this.x = MapScr.gI().menuHidden.vecCmds.get(0).x + MapScr.gI().menuHidden.vecCmds.get(0).w / 2;
                    this.y = MapScr.gI().menuHidden.vecCmds.get(0).y + MapScr.gI().menuHidden.vecCmds.get(0).h / 2;
                    break;
                }
                break;
            }
            case 0: {
                this.x = MapScr.gI().vecCmds.get(0).x + (int)MapScr.gI().vecCmds.get(0).frameImg.frameWidth / 2;
                this.y = MapScr.gI().vecCmds.get(0).y + (int)MapScr.gI().vecCmds.get(0).frameImg.frameHeight / 2;
                break;
            }
        }
    }

    public void updateStepThaoTacKho(final int step) {
        switch (this.step = step) {
            case 4: {
                final StringBuilder sb = new StringBuilder();
                sb.append(Rms.RMS_THAO_TAC_KHO);
                sb.append(Player.myCharz().charID);
                Rms.saveRMSInt(sb.toString(), 1);
                this.isDone = true;
                break;
            }
            case 3: {
                final TabScr currentTab = CanvasNinja.currentTab;
                if (currentTab != null && currentTab instanceof TabWareHouse) {
                    final TabWareHouse tabWareHouse = (TabWareHouse)currentTab;
                    if (tabWareHouse.popupItemInfo != null) {
                        tabWareHouse.vecItemInvens.get(0).isFocusTutorial = true;
                        final MyButton myButton = tabWareHouse.popupItemInfo.btns[0];
                        this.x = myButton.xBtn + myButton.w / 2;
                        this.y = myButton.yBtn + myButton.h / 2;
                        this.isUseCamera = false;
                    }
                    break;
                }
                break;
            }
            case 2: {
                final TabScr currentTab2 = CanvasNinja.currentTab;
                if (currentTab2 != null && currentTab2 instanceof TabWareHouse) {
                    final TabWareHouse tabWareHouse2 = (TabWareHouse)currentTab2;
                    if (tabWareHouse2.vecItemInvens.size() > 0 && tabWareHouse2.vecItemInvens.get(0).isCanCatKho()) {
                        tabWareHouse2.vecItemInvens.get(0).isFocusTutorial = true;
                        this.x = tabWareHouse2.vecItemInvens.get(0).xTutorial;
                        this.y = tabWareHouse2.vecItemInvens.get(0).yTutorial;
                        this.isUseCamera = false;
                    }
                    break;
                }
                break;
            }
            case 1: {
                final QuestScreen questScreen = CanvasNinja.questScreen;
                if (questScreen != null && questScreen instanceof NPCTalkQuestScreen) {
                    final MyButton[] btns = ((NPCTalkQuestScreen)questScreen).btns;
                    if (btns.length >= 2) {
                        final MyButton myButton2 = btns[1];
                        this.x = myButton2.xBtn + myButton2.w / 2;
                        this.y = myButton2.yBtn + myButton2.h / 2;
                        this.isUseCamera = false;
                    }
                    else {
                        this.isDone = true;
                    }
                    break;
                }
                break;
            }
            case 0: {
                final mNPC npcInMap = MapScr.findNPCInMap((short)3);
                this.npcFocus = npcInMap;
                if (npcInMap != null) {
                    this.x = npcInMap.cx;
                    this.y = npcInMap.cy - npcInMap.ch / 2;
                    break;
                }
                break;
            }
        }
    }

    public void updateUseCharka(final int step) {
        switch (this.step = step) {
            case 1: {
                final StringBuilder sb = new StringBuilder();
                sb.append(Rms.RMS_TAP_TRUNG_CHARKA);
                sb.append(Player.myCharz().charID);
                Rms.saveRMSInt(sb.toString(), 1);
                this.isDone = true;
                break;
            }
            case 0: {
                if (MapScr.gI().menuHidden != null) {
                    MapScr.gI().closeMenuHidden();
                    break;
                }
                break;
            }
        }
    }
}
