package ninjawar.message;

import com.badlogic.gdx.Gdx;
import ninjawar.input.MyButton;
import ninjawar.model.*;
import ninjawar.mylib.*;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.*;
import ninjawar.object.*;
import ninjawar.screen.*;
import ninjawar.screen.dialog.Dialog;
import ninjawar.screen.dialog.InputDialog;
import ninjawar.screen.dialog.MessageDialog;
import ninjawar.screen.dialog.RewardDialog;
import ninjawar.screen.minigame.AvatarMuzik;
import ninjawar.screen.quest.NPCTalkQuestScreen;
import ninjawar.screen.quest.PlayerTalkScreen;
import ninjawar.screen.subtab.SubTabApplyParty;
import ninjawar.screen.subtab.SubTabInviteParty;
import ninjawar.screen.subtab.SubTabScr;
import ninjawar.screen.tab.*;
import ninjawar.supporter.LoadDataManager;
import ninjawar.supporter.LoadVersionManager;
import ninjawar.supporter.PurchaseInfo;
import ninjawar.supporter.SupportTranslate;
import ninjawar.template.*;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;

public class GetMessage extends mCmd implements MessageInterface {
    public static boolean isLoadingData = false;
    public static boolean isStopReadMessage;
    protected static GetMessage me;
    public MessageSupport messWait;
    int move = 0;
    int total = 0;

    private void createAtlas(MessageSupport messageSupport) throws IOException {
        short readShort = 0;
        int readInt = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            try {
                try {
                    int readInt2 = messageSupport.reader().readInt();
                    readShort = messageSupport.reader().readShort();
                    dataOutputStream.writeShort(readShort);
                    dataOutputStream.writeInt(readInt2);
                    readInt = messageSupport.reader().readInt();
                } catch (Exception e) {
                    e.printStackTrace();
                    dataOutputStream.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (readInt == 0) {
                try {
                    dataOutputStream.close();
                    return;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return;
                }
            }
            byte[] bArr = new byte[readInt];
            for (int i = 0; i < readInt; i++) {
                bArr[i] = messageSupport.reader().readByte();
            }
            LoadDataManager.vSaveRMSDelay.add(new FileData(readShort, (short) 23, bArr));
            int readShort2 = messageSupport.reader().readShort();
            for (int i2 = 0; i2 < readShort2; i2++) {
                int readInt3 = messageSupport.reader().readInt();
                if (readInt3 != 0) {
                    byte[] bArr2 = new byte[readInt3];
                    for (int i3 = 0; i3 < readInt3; i3++) {
                        bArr2[i3] = messageSupport.reader().readByte();
                    }
                    LoadDataManager.vSaveRMSDelay.add(new FileData(readShort, (short) 24, (short) i2, bArr2));
                }
            }
            DownLoadingScr.isSendClientOk = false;
            Rms.saveRMS(Rms.RMS_ATLAS_DATA_ALL + ((int) readShort), byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
            LoadVersionManager.doCheckData();
        } catch (Throwable th) {
            try {
                dataOutputStream.close();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    private void createItem(DataInputStream dataInputStream) {
        try {
            LoadVersionManager.vcItem = dataInputStream.readByte();
            Res.outz("LoadVersionManager.vcItem= " + LoadVersionManager.vcItem);
            int readShort = dataInputStream.readShort();
            for (int i = 0; i < readShort; i++) {
                short readShort2 = dataInputStream.readShort();
                String readUTF = dataInputStream.readUTF();
                TypeItemtemplates.add(new TypeItemtemplate(readShort2, readUTF));
                Res.outz("type:" + ((int) readShort2) + "_nameType:" + readUTF);
            }
            ItemTemplates.itemTemplates.clear();
            int readShort3 = dataInputStream.readShort();
            Res.outz("sizeOption= " + readShort3);
            for (int i2 = 0; i2 < readShort3; i2++) {
                ItemOptionTemplate itemOptionTemplate = new ItemOptionTemplate();
                itemOptionTemplate.id = i2;
                itemOptionTemplate.name = dataInputStream.readUTF();
                itemOptionTemplate.type = dataInputStream.readByte();
                ItemOptionTemplates.add(itemOptionTemplate);
                Res.outz("name zzzz= " + itemOptionTemplate.name);
            }
            int readShort4 = dataInputStream.readShort();
            for (int i3 = 0; i3 < readShort4; i3++) {
                byte readByte = dataInputStream.readByte();
                byte readByte2 = dataInputStream.readByte();
                String readUTF2 = dataInputStream.readUTF();
                String readUTF3 = dataInputStream.readUTF();
                short readShort5 = dataInputStream.readShort();
                short readShort6 = dataInputStream.readShort();
                Res.outz("idIcon zzzzzzzzzzzz:" + ((int) readShort6));
                ItemTemplate itemTemplate = new ItemTemplate((short) i3, readByte, readByte2, readUTF2, readUTF3, readShort5, readShort6, dataInputStream.readByte(), dataInputStream.readShort(), dataInputStream.readByte());
                int readShort7 = dataInputStream.readShort();
                byte[] bArr = new byte[readShort7];
                for (int i4 = 0; i4 < readShort7; i4++) {
                    bArr[i4] = dataInputStream.readByte();
                }
                int s = itemTemplate.iconID;
                if (itemTemplate.type == 33 && readShort7 != 0) {
                    MonsTemplate.readDataTemplateFromBytes(s, bArr);
                }
                itemTemplate.typeItemtemplate = TypeItemtemplates.get(readByte);
                ItemTemplates.add(itemTemplate);
            }
            int readByte3 = dataInputStream.readByte();
            for (int i5 = 0; i5 < readByte3; i5++) {
                UpgradeTemplates.set(new UpgradeTemplate(i5, dataInputStream.readByte(), dataInputStream.readByte(), dataInputStream.readByte(), dataInputStream.readByte(), dataInputStream.readByte(), dataInputStream.readInt()));
            }
            int readByte4 = dataInputStream.readByte();
            for (int i6 = 0; i6 < readByte4; i6++) {
                TinhLuyenTemplates.add(new TinhLuyenTemplate(i6, dataInputStream.readByte(), dataInputStream.readByte(), dataInputStream.readByte(), dataInputStream.readByte(), dataInputStream.readInt(), dataInputStream.readShort(), dataInputStream.readInt(), dataInputStream.readUTF()));
            }
            int readByte5 = dataInputStream.readByte();
            for (int i7 = 0; i7 < readByte5; i7++) {
                byte readByte6 = dataInputStream.readByte();
                byte readByte7 = dataInputStream.readByte();
                byte readByte8 = dataInputStream.readByte();
                int readInt = dataInputStream.readInt();
                int readByte9 = dataInputStream.readByte();
                Material[] materialArr = new Material[readByte9];
                for (int i8 = 0; i8 < readByte9; i8++) {
                    short readShort8 = dataInputStream.readShort();
                    int readInt2 = dataInputStream.readInt();
                    String readUTF4 = dataInputStream.readUTF();
                    Res.outz("Nguyên liệu chjieet xuat " + ((int) readByte6) + ":" + ((int) readShort8));
                    materialArr[i8] = new Material(readShort8, readInt2, readUTF4);
                }
                short readShort9 = dataInputStream.readShort();
                Res.outz("Sản phẩm " + ((int) readByte6) + ":" + ((int) readShort9));
                ChietXuatTemplates.add(new ChietXuatTemplate(i7, readByte6, readByte7, readByte8, readInt, materialArr, new Material(readShort9, dataInputStream.readInt(), dataInputStream.readUTF())));
            }
            int readByte10 = dataInputStream.readByte();
            for (int i9 = 0; i9 < readByte10; i9++) {
                short readShort10 = dataInputStream.readShort();
                byte readByte11 = dataInputStream.readByte();
                byte readByte12 = dataInputStream.readByte();
                int readInt3 = dataInputStream.readInt();
                int readByte13 = dataInputStream.readByte();
                Material[] materialArr2 = new Material[readByte13];
                for (int i10 = 0; i10 < readByte13; i10++) {
                    materialArr2[i10] = new Material(dataInputStream.readShort(), dataInputStream.readInt(), dataInputStream.readUTF());
                }
                NangCapDaTemplates.add(new NangCapDaTemplate(i9, readShort10, readByte11, readByte12, readInt3, materialArr2, new Material(dataInputStream.readShort(), dataInputStream.readInt(), dataInputStream.readUTF())));
            }
            LoadVersionManager.isUpdateItem = false;
            dataInputStream.close();
        } catch (Exception e) {
        }
    }

    private void createMap(final DataInputStream dataInputStream) throws Exception {
        LoadVersionManager.vcMap = dataInputStream.readByte();
        MyTile.mapNames = new String[dataInputStream.readShort()];
        int n = 0;
        while (true) {
            final String[] mapNames = MyTile.mapNames;
            if (n >= mapNames.length) {
                break;
            }
            mapNames[n] = dataInputStream.readUTF();
            final StringBuilder sb = new StringBuilder();
            sb.append("map name= ");
            sb.append(MyTile.mapNames[n]);
            Res.outz(2, sb.toString());
            ++n;
        }
        for (short short1 = dataInputStream.readShort(), n2 = 0; n2 < short1; ++n2) {
            final int int1 = dataInputStream.readInt();
            final int int2 = dataInputStream.readInt();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("idTile nào:");
            sb2.append(int1);
            sb2.append("_version:");
            sb2.append(int2);
            Res.outz(sb2.toString());
        }
        for (short short2 = dataInputStream.readShort(), n3 = 0; n3 < short2; n3 = (byte) (n3 + 1)) {
            final TemplateNPC templateNPC = new TemplateNPC();
            templateNPC.npcTemplateId = dataInputStream.readShort();
            templateNPC.name = dataInputStream.readUTF();
            templateNPC.numFrame = dataInputStream.readByte();
            templateNPC.menu = new String[dataInputStream.readByte()];
            int n4 = 0;
            while (true) {
                final String[] menu = templateNPC.menu;
                if (n4 >= menu.length) {
                    break;
                }
                menu[n4] = dataInputStream.readUTF();
                ++n4;
            }
            templateNPC.FRAME = new int[dataInputStream.readByte()][];
            int n5 = 0;
            while (true) {
                final int[][] frame = templateNPC.FRAME;
                if (n5 >= frame.length) {
                    break;
                }
                frame[n5] = new int[dataInputStream.readByte()];
                int n6 = 0;
                while (true) {
                    final int[] array = templateNPC.FRAME[n5];
                    if (n6 >= array.length) {
                        break;
                    }
                    array[n6] = dataInputStream.readByte();
                    ++n6;
                }
                ++n5;
            }
            templateNPC.speed = dataInputStream.readByte();
            templateNPC.speedY = dataInputStream.readByte();
            templateNPC.rangeMove = dataInputStream.readShort();
            NPCHashTables.set(templateNPC);
        }
        for (short short3 = dataInputStream.readShort(), n7 = 0; n7 < short3; ++n7) {
            final QuaiTemplate quaiTemplate = new QuaiTemplate();
            quaiTemplate.idIcon = dataInputStream.readShort();
            quaiTemplate.numFrame = dataInputStream.readByte();
            quaiTemplate.type = dataInputStream.readByte();
            quaiTemplate.name = dataInputStream.readUTF();
            quaiTemplate.hpMax = dataInputStream.readInt();
            quaiTemplate.rangeMove = dataInputStream.readByte();
            quaiTemplate.speed = dataInputStream.readByte();
            quaiTemplate.optionMobTemplateStr = dataInputStream.readUTF();
            final byte byte1 = dataInputStream.readByte();
            quaiTemplate.FRAME_MOB = new int[byte1][];
            for (byte b = 0; b < byte1; ++b) {
                final byte byte2 = dataInputStream.readByte();
                quaiTemplate.FRAME_MOB[b] = new int[byte2];
                for (byte b2 = 0; b2 < byte2; ++b2) {
                    quaiTemplate.FRAME_MOB[b][b2] = dataInputStream.readByte();
                }
            }
            final byte byte3 = dataInputStream.readByte();
            quaiTemplate.ID_LAYERS = new short[byte3];
            for (byte b3 = 0; b3 < byte3; ++b3) {
                quaiTemplate.ID_LAYERS[b3] = dataInputStream.readShort();
            }
            quaiTemplate.setOption();
            MobTemplates.add(quaiTemplate);
        }
        LoadVersionManager.isUpdateMap = false;
        Res.err("========> READ DONE UPDATE MAP");
    }

    private void createSkill(final DataInputStream dataInputStream) throws Exception {
        LoadVersionManager.vcSkill = dataInputStream.readByte();
        for (byte byte1 = dataInputStream.readByte(), id = 0; id < byte1; ++id) {
            final SkillOptionTemplate skillOptionTemplate = new SkillOptionTemplate();
            skillOptionTemplate.id = id;
            skillOptionTemplate.name = dataInputStream.readUTF();
            SkillOptionTemplates.add(skillOptionTemplate);
        }
        MapScr.nClasss = new mClass[dataInputStream.readByte()];
        int classId = 0;
        while (true) {
            final mClass[] nClasss = MapScr.nClasss;
            if (classId >= nClasss.length) {
                break;
            }
            nClasss[classId] = new mClass();
            final mClass mClass = MapScr.nClasss[classId];
            mClass.classId = classId;
            mClass.name = dataInputStream.readUTF();
            MapScr.nClasss[classId].skillTemplates = new SkillTemplate[dataInputStream.readByte()];
            int n = 0;
            while (true) {
                final SkillTemplate[] skillTemplates = MapScr.nClasss[classId].skillTemplates;
                if (n >= skillTemplates.length) {
                    break;
                }
                skillTemplates[n] = new SkillTemplate();
                MapScr.nClasss[classId].skillTemplates[n].id = dataInputStream.readShort();
                MapScr.nClasss[classId].skillTemplates[n].name = dataInputStream.readUTF();
                MapScr.nClasss[classId].skillTemplates[n].maxPoint = dataInputStream.readByte();
                MapScr.nClasss[classId].skillTemplates[n].type = dataInputStream.readByte();
                MapScr.nClasss[classId].skillTemplates[n].iconId = dataInputStream.readShort();
                final String utf = dataInputStream.readUTF();
                int n2;
                if (CanvasNinja.w != 128 && CanvasNinja.h > 208) {
                    n2 = 130;
                } else {
                    n2 = 100;
                }
                MapScr.nClasss[classId].skillTemplates[n].description = mFont.tahoma_7.splitFontArray(utf, n2);
                MapScr.nClasss[classId].skillTemplates[n].skills = new Skill[dataInputStream.readByte()];
                int n3 = 0;
                while (true) {
                    final Skill[] skills = MapScr.nClasss[classId].skillTemplates[n].skills;
                    if (n3 >= skills.length) {
                        break;
                    }
                    skills[n3] = new Skill();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].skillId = dataInputStream.readShort();
                    final SkillTemplate template = MapScr.nClasss[classId].skillTemplates[n];
                    final Skill skill = template.skills[n3];
                    skill.template = template;
                    skill.point = dataInputStream.readByte();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].level = dataInputStream.readByte();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].levelUnlock = dataInputStream.readByte();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].manaUse = dataInputStream.readShort();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].coolDown = dataInputStream.readInt();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].timeShowSkill = dataInputStream.readInt();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].dx = dataInputStream.readShort();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].dy = dataInputStream.readShort();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].maxFight = dataInputStream.readByte();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].timeBuff = dataInputStream.readInt();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].idEffect = dataInputStream.readShort();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].idEffectFrame = dataInputStream.readShort();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].idEffectMore = dataInputStream.readShort();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].idStatusBuff = dataInputStream.readShort();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].idBuffFrame = dataInputStream.readShort();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].speedSkill = dataInputStream.readByte();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].idTarget = dataInputStream.readShort();
                    final String[] split = dataInputStream.readUTF().split(",");
                    if (split.length >= 1) {
                        MapScr.nClasss[classId].skillTemplates[n].skills[n3].idSound = Short.valueOf(split[0]);
                    }
                    if (split.length >= 2) {
                        MapScr.nClasss[classId].skillTemplates[n].skills[n3].idSound2 = Short.valueOf(split[1]);
                    }
                    if (split.length == 3) {
                        MapScr.nClasss[classId].skillTemplates[n].skills[n3].idSoundTarget = Short.valueOf(split[2]);
                    }
                    try {
                        final String[] split2 = dataInputStream.readUTF().split(";");
                        if (split2.length > 0 && !split2[0].isEmpty()) {
                            final String[] split3 = split2[0].split(",");
                            if (split3.length > 0 && !split3[0].isEmpty()) {
                                MapScr.nClasss[classId].skillTemplates[n].skills[n3].dxTemplate = Integer.parseInt(split3[0]);
                            }
                            if (split3.length > 1 && !split3[1].isEmpty()) {
                                MapScr.nClasss[classId].skillTemplates[n].skills[n3].dyTemplate = Integer.parseInt(split3[1]);
                            }
                        }
                        if (split2.length > 1 && !split2[1].isEmpty()) {
                            final String[] split4 = split2[1].split(",");
                            if (split4.length > 1 && !split4[1].isEmpty()) {
                                MapScr.nClasss[classId].skillTemplates[n].skills[n3].numFrameIdEffectFrame = Integer.parseInt(split4[1]);
                            }
                        }
                        if (split2.length > 2 && !split2[2].isEmpty()) {
                            final String[] split5 = split2[2].split(",");
                            if (split5.length > 1 && !split5[1].isEmpty()) {
                                MapScr.nClasss[classId].skillTemplates[n].skills[n3].numFrameIdBuffFrame = Integer.parseInt(split5[1]);
                            }
                        }
                    } catch (Exception ex) {
                        Res.outz("BREAK");
                    }
                    final byte byte2 = dataInputStream.readByte();
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].options = new SkillOption[byte2];
                    for (byte b = 0; b < byte2; ++b) {
                        MapScr.nClasss[classId].skillTemplates[n].skills[n3].options[b] = new SkillOption(dataInputStream.readByte(), dataInputStream.readShort());
                    }
                    MapScr.nClasss[classId].skillTemplates[n].skills[n3].logSkill();
                    Skills.add(MapScr.nClasss[classId].skillTemplates[n].skills[n3]);
                    ++n3;
                }
                ++n;
            }
            ++classId;
        }
        LoadVersionManager.isUpdateSkill = false;
        Res.outz(1, "=== LOAD SKILL FROM SERVER DONE ====");
    }

    public static GetMessage gI() {
        if (me == null) {
            me = new GetMessage();
        }
        return me;
    }

    private Player readCharInfo(MessageSupport messageSupport) {
        try {
            Player player = new Player();
            player.charID = messageSupport.reader().readInt();
            player.cgender = messageSupport.reader().readBoolean() ? 0 : 1;
            ((Base) player).cName = messageSupport.reader().readUTF();
            player.classId = messageSupport.reader().readByte();
            player.clevel = messageSupport.reader().readShort();
            int readByte = messageSupport.reader().readByte();
            short[] sArr = new short[readByte];
            for (int i = 0; i < readByte; i++) {
                sArr[i] = messageSupport.reader().readShort();
            }
            player.setPart(sArr);
            return player;
        } catch (Exception e) {
            return null;
        }
    }

    private Item readItemIventory(int i, MessageSupport messageSupport) throws IOException {
        short readShort = messageSupport.reader().readShort();
        if (readShort < 0) {
            return new Item(i, readShort);
        }
        Item item = new Item(i, readShort, messageSupport.reader().readByte(), messageSupport.reader().readByte(), messageSupport.reader().readShort(), messageSupport.reader().readBoolean(), messageSupport.reader().readShort(), messageSupport.reader().readUTF(), messageSupport.reader().readByte());
        item.mItemOption = readmItemOptions(messageSupport);
        messageSupport.reader().readUTF();
        item.levelRefined = messageSupport.reader().readByte();
        byte readByte = messageSupport.reader().readByte();
        byte readByte2 = messageSupport.reader().readByte();
        item.durability = readByte;
        item.maxDurability = readByte2;
        return item;
    }

    private Item readItemShop(int i, MessageSupport messageSupport) throws IOException {
        short readShort = messageSupport.reader().readShort();
        if (readShort < 0) {
            return new Item(i, readShort);
        }
        byte readByte = messageSupport.reader().readByte();
        byte readByte2 = messageSupport.reader().readByte();
        short readShort2 = messageSupport.reader().readShort();
        boolean readBoolean = messageSupport.reader().readBoolean();
        byte readByte3 = messageSupport.reader().readByte();
        short readShort3 = messageSupport.reader().readShort();
        int readByte4 = messageSupport.reader().readByte();
        byte[] bArr = new byte[readByte4];
        int[] iArr = new int[readByte4];
        for (int i2 = 0; i2 < readByte4; i2++) {
            bArr[i2] = messageSupport.reader().readByte();
            iArr[i2] = messageSupport.reader().readInt();
        }
        Item item = new Item(i, readShort, readByte, readByte2, readShort2, readBoolean, readShort3, messageSupport.reader().readUTF(), readByte3, bArr, iArr);
        item.mItemOption = readmItemOptions(messageSupport);
        return item;
    }

    private mItemOption[] readmItemOptions(MessageSupport messageSupport) throws IOException {
        int readByte = messageSupport.reader().readByte();
        mItemOption[] mitemoptionArr = new mItemOption[readByte];
        for (int i = 0; i < readByte; i++) {
            mitemoptionArr[i] = new mItemOption();
            int readShort = messageSupport.reader().readShort();
            mitemoptionArr[i].itemOption = new ItemOption[readShort];
            for (int i2 = 0; i2 < readShort; i2++) {
                short readShort2 = messageSupport.reader().readShort();
                byte readByte2 = messageSupport.reader().readByte();
                byte readByte3 = messageSupport.reader().readByte();
                int readByte4 = messageSupport.reader().readByte();
                int[] iArr = new int[readByte4];
                byte[] bArr = new byte[readByte4];
                for (int i3 = 0; i3 < readByte4; i3++) {
                    bArr[i3] = messageSupport.reader().readByte();
                    iArr[i3] = messageSupport.reader().readInt();
                }
                mitemoptionArr[i].itemOption[i2] = new ItemOption(readShort2, readByte2, readByte3, iArr, bArr);
            }
        }
        return mitemoptionArr;
    }

    public void getImage(MessageSupport messageSupport, short s, String str, String str2, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            try {
                try {
                    short readShort = messageSupport.reader().readShort();
                    dataOutputStream.writeShort(readShort);
                    for (int i2 = 0; i2 < readShort; i2++) {
                        int readInt = messageSupport.reader().readInt();
                        dataOutputStream.writeShort(readInt);
                        dataOutputStream.writeByte(1);
                        int readInt2 = messageSupport.reader().readInt();
                        if (readInt2 != 0) {
                            byte[] bArr = new byte[readInt2];
                            for (int i3 = 0; i3 < readInt2; i3++) {
                                bArr[i3] = messageSupport.reader().readByte();
                            }
                            LoadDataManager.vSaveRMSDelay.add(new FileData((short) readInt, s, bArr));
                        }
                    }
                    DownLoadingScr.isSendClientOk = false;
                    Rms.saveRMSInt(str, i);
                    Rms.saveRMS(str2, byteArrayOutputStream.toByteArray());
                    dataOutputStream.close();
                    dataOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    dataOutputStream.close();
                }
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                throw th;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void getImageAndData(MessageSupport messageSupport, short param2, short param3, String rmsKey, String rmsDataKey, int version) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

        try {
            // Đọc số lượng file từ server
            short fileCount = messageSupport.reader().readShort();
            dataOutputStream.writeShort(fileCount);

            // Xử lý từng file
            for (int i = 0; i < fileCount; i++) {
                try {
                    // Đọc ID của file
                    int fileId = messageSupport.reader().readInt();
                    dataOutputStream.writeShort(fileId);
                    dataOutputStream.writeByte(1);

                    // Đọc kích thước dữ liệu đầu tiên
                    int dataSize1 = messageSupport.reader().readInt();

                    if (dataSize1 > 0) {
                        // Đọc dữ liệu đầu tiên
                        byte[] data1 = new byte[dataSize1];
                        for (int j = 0; j < dataSize1; j++) {
                            data1[j] = messageSupport.reader().readByte();
                        }

                        // Tạo FileData object và thêm vào danh sách lưu trữ
                        FileData fileData1 = new FileData((short) fileId, param2, data1);
                        LoadDataManager.vSaveRMSDelay.add(fileData1);
                    }

                    // Đọc kích thước dữ liệu thứ hai
                    int dataSize2 = messageSupport.reader().readInt();

                    if (dataSize2 > 0) {
                        // Đọc dữ liệu thứ hai
                        byte[] data2 = new byte[dataSize2];
                        for (int j = 0; j < dataSize2; j++) {
                            data2[j] = messageSupport.reader().readByte();
                        }

                        // Tạo FileData object thứ hai và thêm vào danh sách lưu trữ
                        FileData fileData2 = new FileData((short) fileId, param3, data2);
                        LoadDataManager.vSaveRMSDelay.add(fileData2);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                // Đánh dấu là đã hoàn thành việc nhận dữ liệu từ client
                DownLoadingScr.isSendClientOk = false;

                // Lưu version vào RMS
                Rms.saveRMSInt(rmsKey, version);

                // Chuyển đổi dữ liệu thành byte array
                byte[] allData = byteArrayOutputStream.toByteArray();

                // Lưu tất cả dữ liệu vào RMS
                Rms.saveRMS(rmsDataKey, allData);

                // Đóng output stream
                try {
                    dataOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();

                // Đóng stream trong trường hợp có lỗi
                try {
                    dataOutputStream.close();
                } catch (Exception closeException) {
                    closeException.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            // Đảm bảo stream được đóng ngay cả khi có lỗi
            try {
                dataOutputStream.close();
            } catch (Exception closeException) {
                closeException.printStackTrace();
            }
        } finally {
            // Final cleanup - đóng stream nếu chưa đóng
            try {
                dataOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void keyValueAction(String str, String str2) {
        if (str.equals("eff")) {
            return;
        }
        str.equals("beff");
    }

    public void loadCurrMap(byte b) {
        Res.outz("is loading map = " + Player.isLoadingMap);
        MapScr.isChangeZone = false;
        MapScr.lockTick = 0;
        Res.outz("is nn = 1");
        Res.outz("is nn = 2");
        MapScr.loadCamera(false, b == 1 ? Player.myCharz().cx : -1, Player.myCharz().cy);
        Res.outz("is nn = 3");
        MyTile.loadMainTile();
        Res.outz("is nn = 4");
        MyTile.loadMap(MyTile.tileID);
        Res.outz("is nn = 5");
        Player.myCharz().cvx = 0;
        Player.myCharz().statusMe = (byte) 9;
        Player.myCharz().currentMovePoint = null;
        Player.myCharz().mobFocus = null;
        Player.myCharz().charFocus = null;
        Player.myCharz().bgItemFocus = null;
        Player.myCharz().npcFocus = null;
        Player.myCharz().itemFocus = null;
        CanvasNinja.clearAllPointerEvent();
        MapScr.gI().loadGameScr();
        Player.isLockKey = false;
        CanvasNinja.clearKeyHold();
        CanvasNinja.clearKeyPressed();
        MapScr.gI().dHP = Player.myCharz().cHP;
        MapScr.gI().dMP = Player.myCharz().cMP;
        Player.ischangingMap = false;
        if (MyTile.typeMap != 1) {
            MapScr.gI().switchToMe();
        } else {
            Res.outz(1, "đợi cmd vô 2 màn hình kia");
        }
        mInfoDialog.hide();
        String str = MyTile.mapName;
        mInfoDialog.show(str, SupportTranslate.getTextLangue("zone") + " " + MyTile.zoneID, 30);
        CanvasNinja.endDlg();
        CanvasNinja.isLoading = false;
        CanvasNinja.debug("SA75x9", 2);
        Res.outz("============ DEBUG_TIME_LOAD_DATA STOP = DONE");
    }

    public void loadInfoMap(MessageSupport messageSupport) {
        if (messageSupport == null) {
            return;
        }

        try {
            // Debug logging with null check
            String debugMessage = (Player.myCharz() != null) ? "!null" : "null";
            Res.outz("123 char= " + debugMessage);
            CanvasNinja.debug("SA75x4", 2);

            // Load change map points (vGo)
            byte vGoSize = messageSupport.reader().readByte();
            Res.outz("vGo size= " + vGoSize);

            for (int i = 0; i < vGoSize; i++) {
                new PointChangeMap(
                    messageSupport.reader().readShort(),  // x
                    messageSupport.reader().readShort(),  // y
                    messageSupport.reader().readShort(),  // w
                    messageSupport.reader().readShort(),  // h
                    messageSupport.reader().readUTF(),    // name
                    messageSupport.reader().readByte()    // mapId
                );
            }

            // Load background items
            MyTile.vCurrItem.removeAllElements();
            ObjMap.vKeysNew.removeAllElements();

            short itemCount = messageSupport.reader().readShort();

            for (int i = 0; i < itemCount; i++) {
                short idItem = messageSupport.reader().readShort();
                short x = messageSupport.reader().readShort();
                short y = messageSupport.reader().readShort();
                byte layer = messageSupport.reader().readByte();

                ObjMap template = MyTile.getBIById(idItem);
                Res.outz(2, "BGItem idItem:" + idItem + "_IDMAP:" + MyTile.mapID);

                if (template != null) {
                    ObjMap objMap = new ObjMap();
                    objMap.id = idItem;
                    objMap.idImage = template.idImage;
                    objMap.dx = template.dx;
                    objMap.dy = template.dy;
                    objMap.x = x;
                    objMap.y = y;
                    objMap.layer = layer;

                    // Set transparency for items that exist more than once
                    if (MyTile.isExistMoreOne(idItem)) {
                        objMap.trans = (i % 2 == 0) ? 0 : 2;
                    }

                    Res.logBgItem(objMap);
                    short imageId = objMap.idImage;

                    if (imageId < 10000) {
                        // Load atlas image
                        Atlas atlas = Atlas.createAtlas((short)4, (short)23, (short)-1);
                        FrameAtlas frameAtlas = null;

                        if (atlas != null) {
                            frameAtlas = new FrameAtlas(atlas, imageId + "");
                            objMap.image = frameAtlas;
                        }

                        // Set dimensions
                        objMap.w = (frameAtlas != null) ? frameAtlas.getRWidth() : 0;
                        objMap.h = (frameAtlas != null) ? frameAtlas.getRHeight() : 0;

                    } else if (imageId == 10001) {
                        // Create object effect
                        ObjEffect objEffect = new ObjEffect(objMap.idImage, objMap.x, objMap.y);
                        objMap.objEffect = objEffect;
                    }

                    MyTile.vCurrItem.addElement(objMap);
                } else {
                    Res.outz("item null");
                }
            }

            // Load key-value actions
            short actionCount = messageSupport.reader().readShort();
            for (int i = 0; i < actionCount; i++) {
                String key = messageSupport.reader().readUTF();
                String value = messageSupport.reader().readUTF();
                keyValueAction(key, value);
            }

            // Load mobs
            byte mobCount = messageSupport.reader().readByte();
            Quai.newMob.removeAllElements();

            for (int i = 0; i < mobCount; i++) {
                Quai mob = new Quai(
                    i,                                          // id
                    messageSupport.reader().readBoolean(),     // isDie
                    messageSupport.reader().readBoolean(),     // isFire
                    messageSupport.reader().readBoolean(),     // isIce
                    messageSupport.reader().readBoolean(),     // isWind
                    messageSupport.reader().readBoolean(),     // isBossIntro
                    messageSupport.reader().readShort(),       // templateId
                    messageSupport.reader().readByte(),        // sys
                    messageSupport.reader().readInt(),         // hp
                    messageSupport.reader().readByte(),        // level
                    messageSupport.reader().readInt(),         // maxHp
                    messageSupport.reader().readShort(),       // x
                    messageSupport.reader().readShort(),       // y
                    messageSupport.reader().readByte(),        // status
                    messageSupport.reader().readByte()         // levelBoss
                );

                mob.xSd = mob.x;
                mob.ySd = mob.y;

                boolean isBoss = messageSupport.reader().readBoolean();
                mob.isBoss = isBoss;
                mob.isMobMe = false;

                Object mobToAdd = null;
                if (isBoss) {
                    mobToAdd = new NewBoss(i, (short)mob.x, (short)mob.y,
                        mob.templateId, mob.hp, mob.maxHp, mob.sys);
                }

                if (mobToAdd != null) {
                    MapScr.vMob.addElement(mobToAdd);
                } else {
                    MapScr.vMob.addElement(mob);
                }
            }

            // Load sound maps
            byte soundMapCount = messageSupport.reader().readByte();
            MapScr.vecSoundMaps.removeAllElements();

            for (int i = 0; i < soundMapCount; i++) {
                short soundId = messageSupport.readShort();
                short x = messageSupport.readShort();
                short y = messageSupport.readShort();
                short w = messageSupport.readShort();
                short h = messageSupport.readShort();
                float delayFloat = messageSupport.readFloat();

                SoundMap soundMap = new SoundMap(soundId, x, y, w, h);
                SoundGdx soundGdx = new SoundGdx(soundId + "", false);

                // Convert delay from float to int
                int delay = (delayFloat == 0.0f) ? 100 : (int)(1000.0f * delayFloat);
                soundGdx.delaySound = delay;
                soundMap.soundSystem = soundGdx;

                MapScr.vecSoundMaps.add(soundMap);
            }

            // Load NPCs
            CanvasNinja.debug("SA75x6", 2);
            byte npcCount = messageSupport.reader().readByte();
            Res.outz("NPC size= " + npcCount);

            for (int i = 0; i < npcCount; i++) {
                byte status = messageSupport.reader().readByte();
                short x = messageSupport.reader().readShort();
                short y = messageSupport.reader().readShort();
                byte templateId = messageSupport.reader().readByte();

                Res.outz("tempalte = " + templateId);

                mNPC npc = new mNPC(i, status, x, y, templateId, 0);
                MapScr.vNpc.addElement(npc);
                Res.outz("6");
            }

            // Load items in map
            CanvasNinja.debug("SA75x7", 2);
            byte itemMapCount = messageSupport.reader().readByte();
            Res.outz("item size = " + itemMapCount);

            for (int i = 0; i < itemMapCount; i++) {
                short itemMapId = messageSupport.reader().readShort();
                short itemTemplateId = messageSupport.reader().readShort();
                short x = messageSupport.reader().readShort();
                short y = messageSupport.reader().readShort();

                ItemInMap itemInMap = new ItemInMap(-1, itemMapId, itemTemplateId, x, y, (short)0);

                // Check if item already exists
                boolean exists = false;
                for (int j = 0; j < MapScr.vItemMap.size(); j++) {
                    ItemInMap existingItem = (ItemInMap)MapScr.vItemMap.elementAt(j);
                    if (existingItem.itemMapID == itemInMap.itemMapID) {
                        exists = true;
                        break;
                    }
                }

                Res.outz("Nhận từ server ID item:" + itemMapId + "_x:" + x + "_y:" + y);

                if (!exists) {
                    MapScr.vItemMap.addElement(itemInMap);
                }
            }

            // Skip zone info
            //messageSupport.reader().readUTF(); // Skip zone name
            byte zoneCount = messageSupport.reader().readByte();
            for (int i = 0; i < zoneCount; i++) {
                messageSupport.reader().readByte(); // Skip zone data
            }

            // Finalize map loading
            MapScr.idBgSoundMap = messageSupport.reader().readByte();
            loadCurrMap((byte)1);

            Res.logChar("LoadInfoMap", Player.myCharz());
            System.out.println("Load map doneeeeeeee= " + 1);

            Player.isLoadingMap = false;
            CanvasNinja.resetLoadMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onConnectOK(boolean z) {
    }

    public void onConnectionFail(boolean z) {
    }

    public void onDisconnected(boolean z) {
        if (LoginScr.isLogOut) {
            return;
        }
        Player.myCharz().isBlockMove = true;
        CanvasNinja.startOKDlg(SupportTranslate.getTextLangue("disConnect"), 0);
    }

    public void onMessage(MessageSupport messageSupport) {
        short readShort = 0;
        mNPC findNPCInMap;
        Quai findMobInMap;
        int i;
        byte readByte = 0;
        if (messageSupport == null) {
            Res.outz(1, "Nhận MSG NULLLLLLLLLLLLLLL");
            return;
        }
        mSystem.isStopReadMessage();
        CanvasNinja.debug("SA1", 2);
        try {
            try {
                byte cmd = messageSupport.command;
                switch (cmd) {
                    case -128:
                        CanvasNinja.pingUDP = ((int) (mSystem.currentTimeMillis() - messageSupport.reader().readLong())) / 2;
                        break;
                    case -127:
                        byte readByte2 = messageSupport.reader().readByte();
                        if (readByte2 == 0) {
                            messageSupport.reader().readUTF();
                            messageSupport.reader().readShort();
                            break;
                        } else if (readByte2 == 1) {
                            System.out.println("Connected succeed!");
                            if (LoginScr.isSendRequestLogin) {
                                LoginScr.isSendRequestLogin = false;
                                LoginScr.gI().doRequestLogin();
                                break;
                            }
                        }
                        break;
                    case -30:
                        byte readByte3 = messageSupport.reader().readByte();
                        Res.outz(2, "CMD_SUB_LOGIN_SUCCESS SUB:" + ((int) readByte3));
                        if (readByte3 == -82) {
                            int i2 = NinjaMidlet.PADDING_TAI_THO;
                            Vector vector = MapScr.buffEffects;
                            if (vector == null || vector.size() == 0) {
                                MapScr.buffEffects = new Vector();
                            } else {
                                MapScr.buffEffects.removeAllElements();
                            }
                            byte readByte4 = messageSupport.reader().readByte();
                            for (int i3 = 0; i3 < readByte4; i3++) {
                                MapScr.buffEffects.add(new BuffEffect(i2 + 40 + (i3 * 30), LoadDataManager.imgBackgroundInfoMe.getRHeight() + 55, messageSupport.reader().readByte(), messageSupport.reader().readInt()));
                            }
                            break;
                        } else if (readByte3 == -81) {
                            byte readByte5 = messageSupport.reader().readByte();
                            Res.outz("TANG DIEM DO SAT " + ((int) readByte5));
                            Player.myCharz().cPkPoint = (byte) readByte5;
                            break;
                        } else if (readByte3 == -93) {
                            int readInt = messageSupport.reader().readInt();
                            byte readByte6 = messageSupport.reader().readByte();
                            Player findCharInMap = MapScr.findCharInMap(readInt);
                            if (findCharInMap != null) {
                                findCharInMap.cPk = readByte6;
                                findCharInMap.updateColorNameByPK();
                            }
                            break;
                        } else if (readByte3 == -110) {
                            Player findCharInMap2 = MapScr.findCharInMap(messageSupport.reader().readInt());
                            if (findCharInMap2 == null) {
                                Res.outz(1, "hồi sinh thằng ko có trên map >>>> Sai");
                                return;
                            }
                            findCharInMap2.cHP = messageSupport.reader().readInt();
                            findCharInMap2.cHPFull = messageSupport.reader().readInt();
                            findCharInMap2.cMP = messageSupport.reader().readInt();
                            findCharInMap2.cMPFull = messageSupport.reader().readInt();
                            findCharInMap2.cSP = messageSupport.reader().readInt();
                            findCharInMap2.cSPFull = messageSupport.reader().readInt();
                            findCharInMap2.cx = messageSupport.reader().readShort();
                            findCharInMap2.cy = messageSupport.reader().readShort();
                            findCharInMap2.startRevive();
                            findCharInMap2.cp3 = 0;
                            Res.outz(1, ">>>>> HỒI SINH " + ((Base) findCharInMap2).cName + " <<<<<<<<<<");
                            break;
                        } else if (readByte3 == -111) {
                            int readInt2 = messageSupport.reader().readInt();
                            int readInt3 = messageSupport.reader().readInt();
                            Player findCharInMap3 = MapScr.findCharInMap(readInt2);
                            if (findCharInMap3 != null) {
                                findCharInMap3.cHP = readInt3;
                                if (findCharInMap3.isMe()) {
                                    MapScr.gI().updateHp_Lost(0);
                                }
                            }
                            break;
                        } else if (readByte3 == -126) {
                            Player.myCharz().nClass = MapScr.nClasss[messageSupport.reader().readByte()];
                            if (Player.myCharz().statusMe != 35) {
                                Player.myCharz().cHP = Player.myCharz().cHPFull;
                                Player.myCharz().cMP = Player.myCharz().cMPFull;
                            }
                            Player.myCharz().vSkill.removeAllElements();
                            Player.myCharz().vSkillFight.removeAllElements();
                            short readShort2 = messageSupport.reader().readShort();
                            short s = 0;
                            while (true) {
                                short s2 = s;
                                if (s2 >= readShort2) {
                                    byte readByte7 = messageSupport.reader().readByte();
                                    byte b2 = 0;
                                    while (true) {
                                        byte b3 = b2;
                                        if (b3 >= readByte7) {
                                            MapScr.gI().initSkillPaint();
                                            break;
                                        } else {
                                            Skill skill = Skills.get(messageSupport.reader().readShort());
                                            if (skill != null) {
                                                Res.outz("Range skill:" + ((int) readShort) + "_dx:" + skill.dx);
                                            }
                                            Player.myCharz().vSkillFight.addElement(skill);
                                            b2 = (byte) (b3 + 1);
                                        }
                                    }
                                } else {
                                    Player.myCharz().vSkill.addElement(Skills.get(messageSupport.reader().readShort()));
                                    s = (short) (s2 + 1);
                                }
                            }
                        } else if (readByte3 == Byte.MIN_VALUE) {
                            Player.myCharz().charInfo.skillPt = messageSupport.reader().readShort();
                            break;
                        } else if (readByte3 == 115) {
                            Player.myCharz().charID = messageSupport.reader().readInt();
                            Player.myCharz().cgender = messageSupport.reader().readByte();
                            ((Base) Player.myCharz()).cName = messageSupport.reader().readUTF();
                            Player.myCharz().classId = messageSupport.reader().readByte();
                            Player.myCharz().clevel = messageSupport.reader().readShort();
                            int readByte8 = messageSupport.reader().readByte();
                            short[] sArr = new short[readByte8];
                            for (int i4 = 0; i4 < readByte8; i4++) {
                                sArr[i4] = messageSupport.reader().readShort();
                            }
                            Player.myCharz().setPart(sArr);
                            Player.myCharz().cHP = messageSupport.reader().readInt();
                            Player.myCharz().cMP = messageSupport.reader().readInt();
                            Player.myCharz().cSP = messageSupport.reader().readInt();
                            Player.myCharz().cHPFull = messageSupport.reader().readInt();
                            Player.myCharz().cMPFull = messageSupport.reader().readInt();
                            Player.myCharz().cSPFull = messageSupport.reader().readInt();
                            Player.myCharz().cExpPercent = (messageSupport.reader().readShort() * 1.0f) / 100.0f;
                            Player.myCharz().levelUp(Player.myCharz().clevel);
                            Res.logChar("UPDATE_INFO_ME ", Player.myCharz());
                            Gdx.graphics.setTitle("NinjaWar - " + ((Base) Player.myCharz()).cName + "-" + Player.myCharz().charID);
                            break;
                        } else if (readByte3 == -123) {
                            int readInt4 = messageSupport.reader().readInt();
                            int readInt5 = messageSupport.reader().readInt();
                            int readInt6 = messageSupport.reader().readInt();
                            int readInt7 = messageSupport.reader().readInt();
                            int readInt8 = messageSupport.reader().readInt();
                            int readInt9 = messageSupport.reader().readInt();
                            byte readByte9 = messageSupport.reader().readByte();
                            Res.outz(3, "canMove:" + ((int) readByte9));
                            Player.myCharz().cHP = readInt7;
                            Player.myCharz().cMP = readInt8;
                            Player.myCharz().cSP = readInt9;
                            Player.myCharz().updateMoney(readInt4, readInt5, readInt6);
                            StringBuilder sb = new StringBuilder();
                            sb.append("Server gửi isLockmove:");
                            sb.append(readByte9 == 0);
                            Res.outz(sb.toString());
                            Player.myCharz().isLockMove = readByte9 == 0;
                            Player.myCharz().updateHp_bar();
                            MapScr.gI().updateHPLostAll();
                            Res.outz(1, "Đọc data đồ nhưng chưa xử lý");
                            break;
                        } else if (readByte3 == -122) {
                            Player findCharInMap4 = MapScr.findCharInMap(messageSupport.reader().readInt());
                            if (findCharInMap4 != null) {
                                int readInt10 = messageSupport.reader().readInt();
                                int readInt11 = messageSupport.reader().readInt();
                                int readInt12 = messageSupport.reader().readInt();
                                int readInt13 = messageSupport.reader().readInt();
                                int readInt14 = messageSupport.reader().readInt();
                                int readInt15 = messageSupport.reader().readInt();
                                byte readByte10 = messageSupport.reader().readByte();
                                byte readByte11 = messageSupport.reader().readByte();
                                short readShort3 = messageSupport.reader().readShort();
                                findCharInMap4.cHPFull = readInt11;
                                findCharInMap4.cMPFull = readInt13;
                                findCharInMap4.cSPFull = readInt15;
                                findCharInMap4.cspeed = readByte10;
                                findCharInMap4.cyspeed = readByte11;
                                findCharInMap4.chJumpMax = readShort3;
                                int i5 = readInt10 - findCharInMap4.cHP;
                                int i6 = readInt12 - findCharInMap4.cMP;
                                int i7 = readInt14 - findCharInMap4.cSP;
                                Res.outz("dmgHp:" + i5);
                                if (i5 != 0) {
                                    MapScr.startFlyText((i5 > 0 ? "+" : "-") + Res.abs(i5), findCharInMap4.cx, findCharInMap4.cy - ((Base) findCharInMap4).ch, 0, -2, 9);
                                }
                                if (i6 != 0) {
                                    MapScr.startFlyText((i6 > 0 ? "+" : "-") + Res.abs(i6), findCharInMap4.cx, findCharInMap4.cy - ((Base) findCharInMap4).ch, 0, -2, 10);
                                }
                                if (i7 != 0) {
                                    MapScr.startFlyText((i7 > 0 ? "+" : "-") + Res.abs(i7), findCharInMap4.cx, findCharInMap4.cy - ((Base) findCharInMap4).ch, 0, -2, 1);
                                }
                                findCharInMap4.cHP = readInt10;
                                findCharInMap4.cMP = readInt12;
                                findCharInMap4.cSP = readInt14;
                                if (findCharInMap4.isMe()) {
                                    findCharInMap4.updateHp_bar();
                                    MapScr.gI().updateHPLostAll();
                                    break;
                                }
                            }
                        } else if (readByte3 == -109) {
                            switch (messageSupport.reader().readByte()) {
                                case 0:
                                    messageSupport.reader().readByte();
                                    int readInt16 = messageSupport.reader().readInt();
                                    int readInt17 = messageSupport.reader().readInt();
                                    int readInt18 = messageSupport.reader().readInt();
                                    Player.myCharz().cHPFull = readInt16;
                                    Player.myCharz().cMPFull = readInt17;
                                    Player.myCharz().cSPFull = readInt18;
                                    PlayerInfo playerInfo = new PlayerInfo();
                                    playerInfo.potentialPt = messageSupport.reader().readShort();
                                    Res.outz("Diem tiem nang: " + ((int) playerInfo.potentialPt));
                                    playerInfo.pSTR = messageSupport.reader().readShort();
                                    playerInfo.pAGI = messageSupport.reader().readShort();
                                    playerInfo.pDEF = messageSupport.reader().readShort();
                                    playerInfo.pHP = messageSupport.reader().readShort();
                                    playerInfo.pMP = messageSupport.reader().readShort();
                                    Player.myCharz().charInfo = playerInfo;
                                    Res.outz(1, "Đọc data chỉ số nhưng chưa xử lý 2");
                                    break;
                                case 1:
                                    PlayerInfo playerInfo2 = new PlayerInfo();
                                    playerInfo2.potentialPt = messageSupport.reader().readShort();
                                    playerInfo2.pSTR = messageSupport.reader().readShort();
                                    playerInfo2.pAGI = messageSupport.reader().readShort();
                                    playerInfo2.pDEF = messageSupport.reader().readShort();
                                    playerInfo2.pHP = messageSupport.reader().readShort();
                                    playerInfo2.pMP = messageSupport.reader().readShort();
                                    Res.outz("" + ((int) playerInfo2.potentialPt) + " " + ((int) playerInfo2.pDEF));
                                    Player.myCharz().charInfo = playerInfo2;
                                    break;
                            }
                            break;
                        }
                        break;
                    case -29:
                        if (messageSupport.readByte() == -125) {
                            NinjaMidlet.isCheckAppStore = messageSupport.readBoolean();
                            break;
                        }
                        break;
                    case -28:
                        messageNotMap(messageSupport);
                        break;
                    case -26:
                        String readUTF = messageSupport.reader().readUTF();
                        byte readByte12 = messageSupport.reader().readByte();
                        int readByte13 = messageSupport.readByte();
                        MyButton[] myButtonArr = new MyButton[readByte13];
                        String[] strArr = new String[readByte13];
                        byte[] bArr = new byte[readByte13];
                        for (int i8 = 0; i8 < readByte13; i8++) {
                            bArr[i8] = messageSupport.readByte();
                            strArr[i8] = messageSupport.readUTF();
                            messageSupport.readByte();
                        }
                        byte readByte14 = messageSupport.readByte();
                        if (readByte13 > 0) {
                            int width = MyButton.FONT_DEFAULT.getWidth(Res.findStringMax(strArr));
                            for (int i9 = 0; i9 < readByte13; i9++) {
                                MyCommand myCommand = new MyCommand("", -96, MessageDialog.gI());
                                myCommand.subAction = i9;
                                FrameImage[] frameImageArr = LoadDataManager.myButtons;
                                myButtonArr[i9] = new MyButton(frameImageArr[bArr[i9]], frameImageArr[3], width, 0, strArr[i9], 0, 0, myCommand);
                            }
                            if (readByte14 == -1) {
                                MessageDialog.gI().startMsgDialog(readUTF, myButtonArr);
                            } else {
                                MessageDialog.gI().startMsgDialog(readUTF, myButtonArr, (byte) -1);
                            }
                            break;
                        } else {
                            Res.outz("Log dialog:" + readUTF + "time delay:" + ((int) readByte12));
                            if (readByte12 != -1) {
                                MessageDialog.gI().timeStartWaiting = mSystem.currentTimeMillis() + (readByte12 * 1000);
                                CanvasNinja.startOKDlg(readUTF);
                                break;
                            } else {
                                MessageDialog.gI().timeStartWaiting = -1L;
                                CanvasNinja.startOKDlg(readUTF);
                                break;
                            }
                        }
                    case -25:
                        MapScr.addNotifyServer(messageSupport.reader().readUTF());
                        break;
                    case -24:
                        boolean readBoolean = messageSupport.reader().readBoolean();
                        Player readCharInfo = readCharInfo(messageSupport);
                        TabCharInfo.gI().vecItemUsedInvens.removeAllElements();
                        short readShort4 = messageSupport.reader().readShort();
                        for (int i10 = 0; i10 < readShort4; i10++) {
                            TabCharInfo.gI().vecItemUsedInvens.add(readItemIventory(i10, messageSupport));
                        }
                        if (readBoolean) {
                            TabCharInfo.gI().show(readCharInfo);
                            break;
                        }
                        break;
                    case -23:
                        byte readByte15 = messageSupport.reader().readByte();
                        if (readByte15 == 5) {
                            byte readByte16 = messageSupport.reader().readByte();
                            int readInt19 = messageSupport.reader().readInt();
                            Res.outz(2, "Server gửi chat Mob:" + readInt19);
                            String readUTF2 = messageSupport.reader().readUTF();
                            byte readByte17 = messageSupport.reader().readByte();
                            if (readByte16 == 0 && (findMobInMap = MapScr.findMobInMap((short) readInt19)) != null) {
                                findMobInMap.startPopup(readUTF2, readByte17 * 1000);
                            }
                            break;
                        } else if (readByte15 == -1) {
                            ChatBox.gI().open();
                            break;
                        } else if (readByte15 == -2) {
                            String readUTF3 = messageSupport.reader().readUTF();
                            if (readUTF3 != ((Base) Player.myCharz()).cName) {
                                ChatBoxPM.gI().show(readUTF3);
                            }
                            break;
                        } else if (readByte15 == 0) {
                            byte readByte18 = messageSupport.reader().readByte();
                            int readInt20 = messageSupport.reader().readInt();
                            String readUTF4 = messageSupport.reader().readUTF();
                            byte readByte19 = messageSupport.reader().readByte();
                            if (readByte18 == 0) {
                                Player findCharInMap5 = MapScr.findCharInMap(readInt20);
                                if (findCharInMap5 != null) {
                                    findCharInMap5.startPopup(readUTF4, readByte19 * 1000);
                                    ChatBox.Message message = new ChatBox.Message(((Base) findCharInMap5).cName, readUTF4, findCharInMap5.isMe());
                                    ChatBox.gI();
                                    Vector vector2 = (Vector) ChatBox.allMessages.get("0");
                                    Vector vector3 = vector2;
                                    if (vector2 == null) {
                                        vector3 = new Vector();
                                    }
                                    vector3.add(message);
                                    ChatBox.gI();
                                    ChatBox.allMessages.put("0", vector3);
                                    break;
                                }
                            } else if (readByte18 == 3) {
                                String readUTF5 = messageSupport.reader().readUTF();
                                ChatBox.Message message2 = new ChatBox.Message(readUTF5, readUTF4, readUTF5.equals(((Base) Player.myCharz()).cName));
                                ChatBox.gI();
                                Vector vector4 = (Vector) ChatBox.allMessages.get("3");
                                Vector vector5 = vector4;
                                if (vector4 == null) {
                                    vector5 = new Vector();
                                }
                                vector5.add(message2);
                                ChatBox.gI();
                                ChatBox.allMessages.put("3", vector5);
                                break;
                            }
                        } else if (readByte15 == 2) {
                            byte readByte20 = messageSupport.reader().readByte();
                            int readInt21 = messageSupport.reader().readInt();
                            Res.outz(2, "Server gửi chat NPC:" + readInt21);
                            String readUTF6 = messageSupport.reader().readUTF();
                            byte readByte21 = messageSupport.reader().readByte();
                            if (readByte20 == 0 && (findNPCInMap = MapScr.findNPCInMap((short) readInt21)) != null) {
                                findNPCInMap.startPopup(readUTF6, readByte21 * 1000);
                            }
                            break;
                        } else if (readByte15 == 1) {
                            int readInt22 = messageSupport.reader().readInt();
                            messageSupport.reader().readInt();
                            messageSupport.reader().readInt();
                            messageSupport.reader().readUTF();
                            String readUTF7 = messageSupport.reader().readUTF();
                            byte readByte22 = messageSupport.reader().readByte();
                            mPoint pointAtIndex = MyTile.getPointAtIndex(readInt22);
                            mNPC findObjectMap = (mNPC) MapScr.findObjectMap(pointAtIndex.x, pointAtIndex.y);
                            if (findObjectMap != null) {
                                Res.outz("add chat obj");
                                if (findObjectMap instanceof mNPC) {
                                    findObjectMap.startPopup(readUTF7, readByte22 * 1000);
                                }
                            } else {
                                Res.outz("add chat index");
                            }
                            break;
                        } else if (readByte15 == 3) {
                            int readShort5 = messageSupport.reader().readShort();
                            Res.outz("Size :" + readShort5);
                            for (int i11 = 0; i11 < readShort5; i11++) {
                                short readShort6 = messageSupport.reader().readShort();
                                int readShort7 = messageSupport.reader().readShort();
                                NPCHashTables.get(readShort6).talkRandomNPCs = new String[readShort7];
                                for (int i12 = 0; i12 < readShort7; i12++) {
                                    NPCHashTables.get(readShort6).talkRandomNPCs[i12] = messageSupport.reader().readUTF();
                                }
                            }
                            int readShort8 = messageSupport.reader().readShort();
                            for (int i13 = 0; i13 < readShort8; i13++) {
                                short readShort9 = messageSupport.reader().readShort();
                                int readShort10 = messageSupport.reader().readShort();
                                MobTemplates.get(readShort9).talkRandoms = new String[readShort10];
                                for (int i14 = 0; i14 < readShort10; i14++) {
                                    MobTemplates.get(readShort9).talkRandoms[i14] = messageSupport.reader().readUTF();
                                }
                            }
                            break;
                        } else if (readByte15 == 4) {
                            String readUTF8 = messageSupport.reader().readUTF();
                            String readUTF9 = messageSupport.reader().readUTF();
                            messageSupport.reader().readUTF();
                            ChatBox.gI();
                            if (ChatBox.nameTabCR.contains(readUTF8)) {
                                Res.outz("Nguoi nay dang chat");
                            } else {
                                Res.outz("Nguoi nay khong co trong danh sach");
                                ChatBox.gI();
                                ChatBox.nameTabCR.add(readUTF8);
                            }
                            ChatBox.gI();
                            ChatBox.isUnreadMsg.put(readUTF8, true);
                            ChatBox.Message message3 = new ChatBox.Message(readUTF8, readUTF9, false);
                            ChatBox.gI();
                            Vector vector6 = (Vector) ChatBox.allMessages.get(readUTF8);
                            Vector vector7 = vector6;
                            if (vector6 == null) {
                                vector7 = new Vector();
                            }
                            vector7.add(message3);
                            ChatBox.gI();
                            ChatBox.allMessages.put(readUTF8, vector7);
                            MapScr.isHaveUnReadMsg = true;
                            break;
                        }
                        break;
                    case -22:
                        String readUTF10 = messageSupport.reader().readUTF();
                        byte readByte23 = messageSupport.reader().readByte();
                        Res.outz("show alert:" + ((int) readByte23));
                        MapScr.gI().startInfoServer(readUTF10, readByte23);
                        break;
                    case -18:
                        BaseScreen baseScreen = CanvasNinja.currentScreen;
                        if (baseScreen != null && (baseScreen instanceof MapScr)) {
                            CanvasNinja.loadingMapScr = new LoadingMapScr();
                        }
                        short readShort11 = messageSupport.reader().readShort();
                        if (readShort11 == -1) {
                            boolean z = Player.ischangingMap;
                            if (z) {
                                z = false;
                            }
                            Player.ischangingMap = z;
                            return;
                        }
                        MapScr.vCharInMap.removeAllElements();
                        if (CanvasNinja.currentScreen instanceof MapScr) {
                            CanvasNinja.timeBreakLoading = mSystem.currentTimeMillis() + 3000;
                        } else {
                            CanvasNinja.timeBreakLoading = mSystem.currentTimeMillis() + 3000;
                        }
                        Player.isLoadingMap = true;
                        CanvasNinja.isLoading = true;
                        CanvasNinja.debug("SA75", 2);
                        MapScr.resetAllvector();
                        CanvasNinja.endDlg();
                        MyTile.vGo.removeAllElements();
                        System.gc();
                        MyTile.mapID = readShort11;
                        Res.outz("==============  " + MyTile.mapID);
                        MyTile.tileID = messageSupport.reader().readByte();
                        MyTile.bgID = messageSupport.reader().readByte();
                        MyTile.typeMap = messageSupport.reader().readByte();
                        MyTile.mapName = messageSupport.reader().readUTF();
                        Res.outz(1, "MAP ID=" + MyTile.mapID + "_tileID=" + MyTile.tileID + "_bgID=" + MyTile.bgID + "_typeMap:" + MyTile.typeMap + "_mapName:" + MyTile.mapName);
                        MyTile.zoneID = messageSupport.reader().readByte();
                        try {
                            MyTile.loadMapFromResource(MyTile.mapID);
                            loadInfoMap(messageSupport);
                            SendMessage.gI().requestChangeMapDone();
                            try {
                                MyTile.isMapDouble = messageSupport.reader().readByte() != 0;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            MapScr.cmx = MapScr.cmtoX;
                            MapScr.cmy = MapScr.cmtoY;
                            MapScr.idBgSoundMap = messageSupport.reader().readByte();
                        } catch (Exception e2) {
                            SendMessage.gI().requestMaptemplate(MyTile.mapID);
                            this.messWait = messageSupport;
                            return;
                        }
                        break;
                    case -16:
                        MapScr.gI().startPointFind(messageSupport.reader().readShort(), messageSupport.reader().readShort(), messageSupport.reader().readUTF());
                        break;
                    case -15:
                        TabWorldMap.gI().idBanDo = messageSupport.readShort();
                        int readShort12 = messageSupport.readShort();
                        if (TabWorldMap.gI().vtMapMarker != null) {
                            TabWorldMap.gI().vtMapMarker.removeAllElements();
                        } else {
                            TabWorldMap.gI().vtMapMarker = new Vector();
                        }
                        for (int i15 = 0; i15 < readShort12; i15++) {
                            TabWorldMap.gI().vtMapMarker.add(new TabWorldMap.MapMarker(messageSupport.readShort(), messageSupport.readUTF(), messageSupport.readUTF(), messageSupport.readByte(), messageSupport.readShort(), messageSupport.readShort()));
                        }
                        TabWorldMap.gI().show();
                        break;
                    case -9:
                        short readShort13 = messageSupport.reader().readShort();
                        short readShort14 = messageSupport.reader().readShort();
                        Res.outz(2, "exp100:" + ((int) readShort13) + "_level:" + ((int) readShort14));
                        Player.myCharz().cExpPercent = (((float) readShort13) * 1.0f) / 100.0f;
                        Player.myCharz().levelUp(readShort14);
                        break;
                    case -8:
                        Res.outz(1, "Nhận update money");
                        Player.myCharz().updateMoney(messageSupport.reader().readInt(), messageSupport.reader().readInt(), messageSupport.reader().readInt());
                        TabScr currentTab = CanvasNinja.currentTab;
                        if (currentTab != null) {
                            if (currentTab instanceof TabShop) {
                                ((TabShop) currentTab).initMoney();
                                break;
                            } else if (currentTab instanceof TabInventory) {
                                ((TabInventory) currentTab).initMoney();
                                break;
                            }
                        }
                        break;
                    case -7:
                        short readShort15 = messageSupport.reader().readShort();
                        if (readShort15 != -1) {
                            short readShort16 = messageSupport.reader().readShort();
                            int readShort17 = messageSupport.reader().readShort();
                            byte[] bArr2 = new byte[readShort17];
                            for (int i16 = 0; i16 < readShort17; i16++) {
                                bArr2[i16] = messageSupport.reader().readByte();
                            }
                            String pathFromIdAndType = FileData.getPathFromIdAndType(readShort16, readShort15);
                            if (pathFromIdAndType != null) {
                                Rms.saveRMSDataFromServer(pathFromIdAndType, bArr2);
                                FileData findBigImageWaiting = LoadDataManager.findBigImageWaiting(readShort16, readShort15);
                                if (findBigImageWaiting != null) {
                                    findBigImageWaiting.bigImage.createDataBig();
                                    LoadDataManager.vAssetsWaitings.remove(findBigImageWaiting);
                                }
                            }
                            break;
                        } else {
                            NinjaMidlet.serect_key = messageSupport.reader().readUTF();
                            LoginScr.isLogOut = false;
                            System.out.println("Key:" + NinjaMidlet.serect_key);
                            break;
                        }
                    case 2:
                        MapScr.removeCharInMap(messageSupport.reader().readInt());
                        break;
                    case 3:
                        Res.outz(1, "debuggggggggggggg PLAYER_JOIN_MAP");
                        messageSupport.reader().readShort();
                        messageSupport.reader().readShort();
                        int readByte24 = messageSupport.reader().readByte();
                        Res.outz(1, "size player join map:" + readByte24);
                        for (int i17 = 0; i17 < readByte24; i17++) {
                            Player player = new Player();
                            player.charID = messageSupport.reader().readInt();
                            Player player2 = player;
                            if (player.isMe()) {
                                player2 = Player.myCharz();
                            }
                            player2.cgender = messageSupport.reader().readByte();
                            ((Base) player2).cName = messageSupport.reader().readUTF();
                            player2.classId = messageSupport.reader().readByte();
                            player2.clevel = messageSupport.reader().readShort();
                            int readByte25 = messageSupport.reader().readByte();
                            short[] sArr2 = new short[readByte25];
                            for (int i18 = 0; i18 < readByte25; i18++) {
                                sArr2[i18] = messageSupport.reader().readShort();
                            }
                            player2.setPart(sArr2);
                            player2.cx = messageSupport.reader().readInt();
                            int readInt23 = messageSupport.reader().readInt();
                            player2.cyDefault = readInt23;
                            player2.cy = readInt23;
                            Res.outz("SV GUI: cx=" + player2.cx + "cy=" + player2.cy);
                            player2.cspeed = messageSupport.reader().readShort();
                            player2.cyspeed = messageSupport.reader().readShort();
                            player2.cyspeedFall = messageSupport.reader().readShort();
                            player2.chJumpMax = messageSupport.reader().readShort();
                            byte readByte26 = messageSupport.reader().readByte();
                            ((Base) player2).cdir = readByte26;
                            player2.setDirByCDir(readByte26);
                            player2.statusMe = messageSupport.reader().readByte();
                            float f = (player2.chJumpMax * 1.0f) / player2.cyspeed;
                            player2.tJump = f;
                            player2.sJumpMax = (int) (f * player2.cspeed);
                            player2.cHP = messageSupport.reader().readInt();
                            player2.cHPFull = messageSupport.reader().readInt();
                            player2.updateHp_bar();
                            player2.setPos(player2.cx, player2.cy);
                            Res.logChar("player_join_map", player2);
                            player2.updateColorName(-1);
                            int charInParty = TabParty.charInParty(player2.charID);
                            if (charInParty != -1) {
                                player2.updateColorName(3);
                                if (charInParty == 0) {
                                    player2.isLeader = true;
                                }
                            }
                            MapScr.playerJoinMap(player2);
                        }
                        break;
                    case 4:
                        int readInt24 = messageSupport.reader().readInt();
                        messageSupport.reader().readInt();
                        messageSupport.reader().readInt();
                        Player findCharInMap6 = MapScr.findCharInMap(readInt24);
                        if (findCharInMap6 != null && !findCharInMap6.isMe()) {
                            Res.outz("do Jump from server:" + ((Base) findCharInMap6).cName);
                        }
                        if (findCharInMap6 != null && findCharInMap6.isMe()) {
                            Res.outz(1, "server gửi bản thân jump:" + ((Base) findCharInMap6).cName);
                            break;
                        }
                        break;
                    case 5:
                        int readInt25 = messageSupport.reader().readInt();
                        int readInt26 = messageSupport.reader().readInt();
                        int readInt27 = messageSupport.reader().readInt();
                        byte readByte27 = messageSupport.reader().readByte();
                        byte readByte28 = messageSupport.reader().readByte();
                        byte readByte29 = messageSupport.reader().readByte();
                        Player findCharInMap7 = MapScr.findCharInMap(readInt25);
                        if (findCharInMap7 != null && !findCharInMap7.isMe()) {
                            findCharInMap7.addMovePointNew(new MovePointNew(readInt26, readInt27, readByte27, readByte28, readByte29));
                            break;
                        }
                        break;
                    case 6: // ITEM_MAP command
                        byte subCommand = messageSupport.reader().readByte();
                        Res.outz(3, "[REC] ITEM_MAP 6_ SUB:" + subCommand);

                        switch (subCommand) {
                            case 0: // Add item to map
                                short itemMapID = messageSupport.reader().readShort();
                                short itemTemplateID = messageSupport.reader().readShort();
                                int x = messageSupport.reader().readInt();
                                int y = messageSupport.reader().readInt();
                                boolean isRusted = messageSupport.reader().readBoolean();

                                Res.outz(2, "nhận item map itemTemplateID:" + itemTemplateID + "_itemMapID:" + itemMapID + "_x:" + x + "_y:" + y);

                                ItemInMap itemInMap = new ItemInMap(-1, itemMapID, itemTemplateID, x, y, (short) 0);
                                itemInMap.isRusted = isRusted;

                                // Play sound effect and add to map
                                AudioManager.itemFall(1.0f);
                                MapScr.vItemMap.addElement(itemInMap);
                                break;

                            case 1: // Remove item from map
                                short removeItemMapID = messageSupport.reader().readShort();

                                // Find and remove item by ID
                                for (int i99 = 0; i99 < MapScr.vItemMap.size(); i99++) {
                                    ItemInMap item = (ItemInMap) MapScr.vItemMap.elementAt(i99);
                                    if (item.itemMapID == removeItemMapID) {
                                        MapScr.vItemMap.removeElementAt(i99);
                                        break;
                                    }
                                }
                                break;

                            case 2: // Pick up item
                                short pickupItemMapID = messageSupport.reader().readShort();
                                int playerID = messageSupport.reader().readInt();

                                if (playerID == -1) {
                                    // Remove item without pickup animation (server cleanup)
                                    for (int i99 = MapScr.vItemMap.size() - 1; i99 >= 0; i99--) {
                                        ItemInMap item = (ItemInMap) MapScr.vItemMap.elementAt(i99);
                                        if (item.itemMapID == pickupItemMapID) {
                                            MapScr.vItemMap.removeElementAt(i99);
                                            break;
                                        }
                                    }
                                    return;
                                }

                                // Find player who picked up the item
                                Player picker = MapScr.findCharInMap(playerID);
                                if (picker != null) {
                                    // Find item and start pickup animation
                                    for (int i99 = 0; i99 < MapScr.vItemMap.size(); i99++) {
                                        ItemInMap item = (ItemInMap) MapScr.vItemMap.elementAt(i99);
                                        if (item.itemMapID == pickupItemMapID) {
                                            item.isPicked = true;
                                            item.setPoint(picker.cx, picker.cy - 10);

                                            // Clear player's item focus and update UI
                                            picker.itemFocus = null;
                                            MapScr.gI().autoFocus();
                                            MapScr.gI().interactObjPaint.updateNameFocus(MapScr.vecFocus);

                                            // Play pickup sound
                                            AudioManager.getItem();
                                            break;
                                        }
                                    }
                                }
                                break;
                        }
                        break;
                    case 7:
                        byte readByte31 = messageSupport.reader().readByte();
                        Res.outz(3, "[REC] AUTO_ATK 7_ SUB:" + ((int) readByte31));
                        if (readByte31 == 0) {
                            messageSupport.reader().readBoolean();
                            Res.outz("THOI GIAN CON LAI: " + messageSupport.reader().readUTF());
                            messageSupport.reader().readByte();
                            messageSupport.reader().readByte();
                            messageSupport.reader().readByte();
                            messageSupport.reader().readShort();
                            int readByte32 = messageSupport.reader().readByte();
                            for (int i22 = 0; i22 < readByte32; i22++) {
                                messageSupport.reader().readShort();
                            }
                            messageSupport.reader().readByte();
                            messageSupport.reader().readBoolean();
                            messageSupport.reader().readBoolean();
                            break;
                        } else if (readByte31 == 1) {
                            if (messageSupport.reader().readBoolean()) {
                                Player.myCharz().isAutoAtk = true;
                                break;
                            } else {
                                Player.myCharz().isAutoAtk = false;
                                break;
                            }
                        } else if (readByte31 == 2) {
                            int readInt31 = messageSupport.reader().readInt();
                            short readShort22 = messageSupport.reader().readShort();
                            short readShort23 = messageSupport.reader().readShort();
                            Player findCharInMap9 = MapScr.findCharInMap(readInt31);
                            findCharInMap9.cx = readShort22;
                            findCharInMap9.cy = readShort23;
                            break;
                        }
                        break;
                    case 8: // GACHA command
                        byte gachaType = messageSupport.reader().readByte();

                        switch (gachaType) {
                            case 0: // Ryo Gacha
                                byte ryoSubCommand = messageSupport.reader().readByte();

                                switch (ryoSubCommand) {
                                    case 0: // Load Ryo gacha items
                                        short ryoItemCount = messageSupport.reader().readShort();
                                        Vector<Item> ryoItems = new Vector<>();

                                        for (int i999 = 0; i999 < ryoItemCount; i999++) {
                                            ryoItems.add(readItemShop(i999, messageSupport));
                                        }

                                        int ryoCost = messageSupport.reader().readInt();

                                        TabGacha.gI().vecRyoItems = ryoItems;
                                        TabGacha.gI().costRyo = ryoCost;
                                        TabGacha.gI().show();
                                        break;

                                    case 1: // Set Ryo gacha reward result
                                        short ryoRewardIndex = messageSupport.reader().readShort();
                                        TabGacha.gI().setRewardIndexRyo(ryoRewardIndex);
                                        break;

                                    default:
                                        // Unknown ryo sub-command
                                        break;
                                }
                                break;

                            case 1: // Gem Gacha
                                byte gemSubCommand = messageSupport.reader().readByte();

                                switch (gemSubCommand) {
                                    case 0: // Load Gem gacha items
                                        short gemItemCount = messageSupport.reader().readShort();
                                        Vector<Item> gemItems = new Vector<>();

                                        for (int i000 = 0; i000 < gemItemCount; i000++) {
                                            gemItems.add(readItemShop(i000, messageSupport));
                                        }

                                        int gemCost = messageSupport.reader().readInt();

                                        TabGacha.gI().vecGemItems = gemItems;
                                        TabGacha.gI().vecGemFirst.addAll(gemItems);
                                        TabGacha.gI().costGem = gemCost;
                                        TabGacha.gI().show();
                                        break;

                                    case 1: // Set Gem gacha reward result
                                        short rewardIndex = messageSupport.reader().readShort();
                                        Item rewardItem = (Item) TabGacha.gI().vecGemFirst.get(rewardIndex);

                                        // Find matching item in vecGemItems
                                        int matchingIndex = -1;
                                        for (int i000 = 0; i000 < TabGacha.gI().vecGemItems.size(); i000++) {
                                            Item currentItem = (Item) TabGacha.gI().vecGemItems.get(i000);

                                            // Compare item properties to find match
                                            if (rewardItem.num == currentItem.num && rewardItem.getRealName().equals(currentItem.getRealName())) {
                                                matchingIndex = i000;
                                                break;
                                            }
                                        }

                                        TabGacha.gI().setRewardIndexGem(matchingIndex);
                                        break;
                                }
                                break;
                        }
                        break;
                    case 9:
                        if (messageSupport.reader().readByte() == 0) {
                            HashMap hashMap = new HashMap();
                            byte b4 = 0;
                            while (true) {
                                byte b5 = b4;
                                if (b5 > 3) {
                                    TabLearnSkill.gI().mSkill = hashMap;
                                    TabLearnSkill.gI().show();
                                    break;
                                } else {
                                    byte readByte36 = messageSupport.reader().readByte();
                                    Vector vector10 = new Vector();
                                    byte b6 = 0;
                                    while (true) {
                                        byte b7 = b6;
                                        if (b7 < readByte36) {
                                            vector10.add(Short.valueOf(messageSupport.readShort()));
                                            b6 = (byte) (b7 + 1);
                                        } else {
                                            hashMap.put(Byte.valueOf(b5), vector10);
                                            b4 = (byte) (b5 + 1);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 10:
                        messageSupport.reader().readInt();
                        messageSupport.reader().readShort();
                        messageSupport.reader().readShort();
                        messageSupport.reader().readInt();
                        break;
                    case 14:
                        byte readByte37 = messageSupport.reader().readByte();
                        Res.outz(2, "[REC] REN_UI sub:" + ((int) readByte37));
                        if (readByte37 == 4) {
                            boolean readBoolean3 = messageSupport.reader().readBoolean();
                            String readUTF11 = messageSupport.reader().readUTF();
                            TabScr tabScr = CanvasNinja.currentTab;
                            if (tabScr != null && (tabScr instanceof TabUpgrade)) {
                                TabUpgrade.gI().startEffUpgrade(readBoolean3, readUTF11);
                            }
                            break;
                        } else if (readByte37 == 0) {
                            messageSupport.reader().readInt();
                            messageSupport.reader().readInt();
                            messageSupport.reader().readInt();
                            int readInt34 = messageSupport.reader().readInt();
                            int readInt35 = messageSupport.reader().readInt();
                            int readInt36 = messageSupport.reader().readInt();
                            short readShort26 = messageSupport.reader().readShort();
                            Vector vector11 = new Vector();
                            for (int i26 = 0; i26 < readShort26; i26++) {
                                short readShort27 = messageSupport.reader().readShort();
                                Item readItemIventory = readItemIventory(i26, messageSupport);
                                readItemIventory.id = readShort27;
                                Res.outz("-----------");
                                vector11.add(readItemIventory);
                            }
                            Vector vector12 = new Vector();
                            Vector vector13 = new Vector();
                            short readShort28 = messageSupport.reader().readShort();
                            for (int i27 = 0; i27 < readShort28; i27++) {
                                short readShort29 = messageSupport.reader().readShort();
                                Item readItemIventory2 = readItemIventory(i27, messageSupport);
                                readItemIventory2.id = readShort29;
                                vector12.add(readItemIventory2);
                                vector13.add(readItemIventory2.clones());
                            }
                            TabScr tabScr2 = CanvasNinja.currentTab;
                            if (tabScr2 != null && (tabScr2 instanceof TabUpgrade)) {
                                TabUpgrade.gI().vecItems = vector11;
                                TabUpgrade.gI().numThienThach = readInt34;
                                TabUpgrade.gI().numBuaChietXuat = readInt35;
                                TabUpgrade.gI().numBuaNangCap = readInt36;
                                if (TabUpgrade.gI().isUpgrading()) {
                                    TabUpgrade.gI().vecRefinedTemps.removeAllElements();
                                    for (int i28 = 0; i28 < vector13.size(); i28++) {
                                        Item itemChietXuatByIdIcon = TabUpgrade.gI().getItemChietXuatByIdIcon(((Item) vector13.get(i28)).idTemplate);
                                        if (itemChietXuatByIdIcon != null) {
                                            Res.outz("num:" + itemChietXuatByIdIcon.num);
                                            ((Item) vector13.get(i28)).num = itemChietXuatByIdIcon.num;
                                        }
                                    }
                                    TabUpgrade.gI().vecRefineds = vector13;
                                    TabUpgrade.gI().isSendUp = false;
                                    TabUpgrade.gI().isUpdateNum = true;
                                    Res.outz("IS UPGRADE");
                                    TabUpgrade.gI().vecRefinedTemps = vector12;
                                    break;
                                } else {
                                    TabUpgrade.gI().vecRefineds = vector12;
                                    break;
                                }
                            }
                        }
                        break;
                    case 15: {
                        byte action = messageSupport.reader().readByte();

                        if (action == 0) {
                            // Đọc các thông tin không dùng đến mà không xử lý
                            messageSupport.reader().readByte();
                            int size = messageSupport.reader().readByte();
                            for (int i13 = 0; i13 < size; i13++) {
                                messageSupport.reader().readByte();
                                messageSupport.reader().readInt();
                                messageSupport.reader().readInt();
                            }
                            messageSupport.reader().readShort();
                            messageSupport.reader().readShort();

                            byte itemIdToRemove = messageSupport.reader().readByte();
                            messageSupport.reader().readByte();
                            messageSupport.reader().readShort();

                            // Loại bỏ món đồ đã dùng và thêm lại vào inventory
                            TabInventory.gI().addItem(TabInventory.gI().removeItemUsed(itemIdToRemove));
                            break;

                        } else if (action == 1) {
                            // Thêm item mới vào Inventory
                            short templateId = messageSupport.reader().readShort();
                            TabInventory.gI().addItem(readItemIventory(templateId, messageSupport));
                            break;

                        } else if (action == 2) {
                            // Cập nhật số lượng item trong inventory
                            short itemId = messageSupport.reader().readShort();
                            short newNum = messageSupport.reader().readShort();
                            TabInventory.gI().updateNum(itemId, newNum);
                            break;

                        } else if (action == 4) {
                            // Load lại toàn bộ inventory
                            TabInventory tabInv = TabInventory.gI();
                            tabInv.vecItemInvens.removeAllElements();
                            tabInv.vecItemUsedInvens.removeAllElements();

                            short usedItemCount = messageSupport.reader().readShort();
                            for (int i14 = 0; i14 < usedItemCount; i14++) {
                                tabInv.vecItemUsedInvens.add(readItemIventory(i14, messageSupport));
                            }

                            short itemCount = messageSupport.reader().readShort();
                            for (int i14 = 0; i14 < itemCount; i14++) {
                                tabInv.vecItemInvens.add(readItemIventory(i14, messageSupport));
                            }

                            tabInv.optionsInfos = readmItemOptions(messageSupport);

                            int optionsLength = messageSupport.reader().readByte();
                            for (int i14 = 0; i14 < optionsLength; i14++) {
                                tabInv.optionsInfos[i14].title = messageSupport.reader().readUTF();
                            }

                            tabInv.maxSlot = messageSupport.reader().readShort();
                            tabInv.capacity = messageSupport.reader().readShort();

                            tabInv.show();

                            break;

                        } else if (action == 6) {
                            // Xử lý tab kho hàng (Warehouse)
                            TabWareHouse tabWareHouse = new TabWareHouse();
                            tabWareHouse.vecItemInvens.removeAllElements();
                            tabWareHouse.vecWareHouses.removeAllElements();

                            short itemInvCount = messageSupport.reader().readShort();
                            for (int i12 = 0; i12 < itemInvCount; i12++) {
                                tabWareHouse.vecItemInvens.add(readItemIventory(i12, messageSupport));
                            }

                            short wareHouseCount = messageSupport.reader().readShort();
                            for (int i12 = 0; i12 < wareHouseCount; i12++) {
                                tabWareHouse.vecWareHouses.add(readItemIventory(i12, messageSupport));
                            }

                            tabWareHouse.popupItemInfo = null;
                            tabWareHouse.popupItemWareHouse = null;

                            TabWareHouse.gI().maxSlotWH = messageSupport.reader().readShort();
                            TabWareHouse.gI().capacity = messageSupport.reader().readShort();

                            currentTab = CanvasNinja.currentTab;
                            if (!(currentTab instanceof TabWareHouse)) {
                                tabWareHouse.show();
                                CanvasNinja.subTab = null;
                                Dialog currentDialog = CanvasNinja.currentDialog;
                                if (currentDialog instanceof InputDialog) {
                                    CanvasNinja.currentDialog = null;
                                }
                            } else {
                                TabWareHouse.gI().updateItem(tabWareHouse.vecItemInvens, tabWareHouse.vecWareHouses);
                                CanvasNinja.subTab = null;
                                Dialog currentDialog = CanvasNinja.currentDialog;
                                if (currentDialog instanceof InputDialog) {
                                    CanvasNinja.currentDialog = null;
                                }
                            }
                            break;

                        } else if (action == 7) {
                            // Cập nhật phần trang bị của nhân vật
                            int charId = messageSupport.reader().readInt();
                            short partCount = messageSupport.reader().readShort();
                            short[] parts = new short[partCount];
                            for (i = 0; i < partCount; i++) {
                                parts[i] = messageSupport.reader().readShort();
                                Res.outz(2, "Nhận part idpart sub 7 là là là: " + parts[i]);
                            }

                            Player player = MapScr.findCharInMap(charId);
                            if (player != null) {
                                player.setPart(parts);

                                if (player.isMe()) {
                                    short usedItemCount = messageSupport.reader().readShort();
                                    Res.outz("sizeUsed" + usedItemCount);

                                    TabInventory.gI().vecItemUsedInvens.removeAllElements();
                                    short[] templateIds = new short[usedItemCount];

                                    for (i = 0; i < usedItemCount; i++) {
                                        Item item = readItemIventory(i, messageSupport);
                                        TabInventory.gI().vecItemUsedInvens.add(item);

                                        templateIds[i] = (short) item.idTemplate;
                                        Res.outz(1, "Nhận template id là: " + templateIds[i]);

                                        if (ItemTemplates.get(templateIds[i]).typeItemtemplate.type == 1) {
                                            Player.myCharz().classWeaponId = ItemTemplates.get(templateIds[i]).classId;
                                        }
                                    }

                                    TabScr currentTab2 = CanvasNinja.currentTab;
                                    if (currentTab2 instanceof TabInventory) {
                                        TabInventory.gI().updateItem(TabInventory.gI().vecItemInvens, TabInventory.gI().vecItemUsedInvens);
                                        TabInventory.gI().initChar();
                                    }
                                }
                            }
                            break;
                        }

                        break;
                    }
                    case 16:
                        byte readByte42 = messageSupport.reader().readByte();
                        byte readByte43 = messageSupport.reader().readByte();
                        short readShort36 = messageSupport.reader().readShort();
                        Player.myCharz().updateDrainByType(readByte42, messageSupport.readShort());
                        Res.outz(1, "Nhận drain từ sv:" + ((int) readByte43) + "_time:" + ((int) readShort36));
                        Player.myCharz().stopFlyText(readByte42);
                        if (readByte43 == 0) {
                            Player.myCharz().stopFlyText(readByte42);
                            break;
                        } else {
                            Player.myCharz().startFlyText(readByte42, readByte43, readShort36);
                            break;
                        }
                    case 17:
                        int readInt38 = messageSupport.reader().readInt();
                        byte readByte44 = messageSupport.reader().readByte();
                        boolean readBoolean4 = messageSupport.reader().readBoolean();
                        Player findCharInMap11 = MapScr.findCharInMap(readInt38);
                        if (findCharInMap11 != null && readByte44 == 2) {
                            findCharInMap11.useCharka(readBoolean4, false);
                            break;
                        }
                        break;
                    case 19:
                        byte readByte45 = messageSupport.reader().readByte();
                        String readUTF12 = messageSupport.reader().readUTF();
                        if (readByte45 == 0) {
                            InputDialog inputDialog = new InputDialog();
                            MyCommand myCommand2 = new MyCommand(SupportTranslate.getTextLangue("ACCEPT"), 1, inputDialog);
                            myCommand2.subAction = readByte45;
                            inputDialog.startInputDlg(readUTF12, 0, myCommand2);
                            break;
                        } else if (readByte45 == 1 || readByte45 == 2) {
                            MyButton[] myButtonArr2 = new MyButton[2];
                            MessageDialog messageDialog = new MessageDialog();
                            MyCommand myCommand3 = new MyCommand("", -105, messageDialog);
                            FrameImage[] frameImageArr2 = LoadDataManager.myButtons;
                            myButtonArr2[0] = new MyButton(frameImageArr2[1], frameImageArr2[3], 55, 0, SupportTranslate.getTextLangue("YES"), 0, 0, myCommand3);
                            MyCommand myCommand4 = new MyCommand("", -106, messageDialog);
                            FrameImage[] frameImageArr3 = LoadDataManager.myButtons;
                            myButtonArr2[1] = new MyButton(frameImageArr3[1], frameImageArr3[3], myButtonArr2[0].w, 0, SupportTranslate.getTextLangue("NO"), 0, 0, myCommand4);
                            messageDialog.idTask = readByte45;
                            messageDialog.startMsgDialog(readUTF12, myButtonArr2);
                            break;
                        }
                        break;
                    case 20:
                        ZoneTabScr zoneTabScr = new ZoneTabScr();
                        if (messageSupport.reader().readByte() == 0) {
                            int readByte46 = messageSupport.reader().readByte();
                            for (int i37 = 0; i37 < readByte46; i37++) {
                                zoneTabScr.vecZones.add(new Zone(messageSupport.reader().readByte(), (readByte + 1) + "", messageSupport.reader().readByte()));
                            }
                            Res.outz("Server gửi số khu:" + zoneTabScr.vecZones.size());
                            zoneTabScr.show();
                            break;
                        }
                        break;
                    case 21:
                        byte readByte47 = messageSupport.reader().readByte();
                        int readInt39 = messageSupport.reader().readInt();
                        if (readByte47 == -17) {
                            Player.timeDelayChangingMap = mSystem.currentTimeMillis() + (readInt39 * 1000);
                            break;
                        }
                        break;
                    case 22:
                        byte readByte48 = messageSupport.reader().readByte();
                        if (readByte48 == 2) {
                            //PurchaseSupport.KEY = messageSupport.reader().readUTF();
                            break;
                        } else if (readByte48 == 0) {
                            int readByte49 = messageSupport.reader().readByte();
                            PurchaseInfo[] purchaseInfoArr = new PurchaseInfo[readByte49];
                            for (int i38 = 0; i38 < readByte49; i38++) {
                                short readShort37 = messageSupport.reader().readShort();
                                String readUTF13 = messageSupport.reader().readUTF();
                                int readShort38 = messageSupport.reader().readShort();
                                ProductPurchase[] productPurchaseArr = new ProductPurchase[readShort38];
                                for (int i39 = 0; i39 < readShort38; i39++) {
                                    productPurchaseArr[i39] = new ProductPurchase();
                                    productPurchaseArr[i39].productId = messageSupport.reader().readUTF();
                                    productPurchaseArr[i39].name = messageSupport.reader().readUTF();
                                    productPurchaseArr[i39].idIcon = messageSupport.reader().readShort();
                                    productPurchaseArr[i39].typeMoney = messageSupport.reader().readByte();
                                    productPurchaseArr[i39].price = messageSupport.reader().readUTF();
                                    int readByte50 = messageSupport.reader().readByte();
                                    productPurchaseArr[i39].giftDatas = new mGiftData[readByte50];
                                    for (int i40 = 0; i40 < readByte50; i40++) {
                                        productPurchaseArr[i39].giftDatas[i40] = new mGiftData(messageSupport.reader().readShort(), messageSupport.reader().readInt(), messageSupport.reader().readUTF());
                                    }
                                    System.out.println("Product ID " + i39 + ":" + productPurchaseArr[i39].productId);
                                }
                                purchaseInfoArr[i38] = new PurchaseInfo(readShort37, readUTF13, productPurchaseArr);
                            }
//                            if (NinjaWar.me.inAppPurchaseSystem != null) {
//                                Res.debugPurchase("Nhận cmd bắt đầu cài đặt purchase");
//                                TabPurchase.gI().startTabPurchase(purchaseInfoArr);
//                            }
                            break;
                        }
                        break;
                    case 23:
                        byte readByte51 = messageSupport.reader().readByte();
                        if (readByte51 == 0) {
                            byte readByte52 = messageSupport.reader().readByte();
                            short readShort39 = messageSupport.reader().readShort();
                            short readShort40 = messageSupport.reader().readShort();
                            Vector vector14 = new Vector();
                            for (int i41 = 0; i41 < readShort40; i41++) {
                                vector14.add(readItemShop(i41, messageSupport));
                            }
                            int readShort41 = messageSupport.reader().readShort();
                            Vector vector15 = new Vector();
                            for (int i42 = 0; i42 < readShort41; i42++) {
                                vector15.add(new Item(i42, messageSupport.reader().readShort()));
                            }
                            short readShort42 = messageSupport.reader().readShort();
                            Vector vector16 = new Vector();
                            for (int i43 = 0; i43 < readShort42; i43++) {
                                messageSupport.reader().readShort();
                                vector16.add(readItemShop(i43, messageSupport));
                            }
                            if (readByte52 == ShopController.SHOP_TIEM_MI) {
                                TabShopTiemMi tabShopTiemMi = new TabShopTiemMi();
                                Vector vector17 = new Vector();
                                tabShopTiemMi.vecItems = vector17;
                                vector17.addAll(vector14);
                                Vector vector18 = new Vector();
                                tabShopTiemMi.vecItemMes = vector18;
                                vector18.addAll(vector15);
                                Vector vector19 = new Vector();
                                tabShopTiemMi.vecItemsCanSell = vector19;
                                vector19.addAll(vector16);
                                tabShopTiemMi.startTabShop();
                                tabShopTiemMi.idNpc = readShort39;
                            } else if (readByte52 == ShopController.SHOP_THO_REN) {
                                TabShopThoRen.gI().vecItems = new Vector();
                                TabShopThoRen.gI().vecItems.addAll(vector14);
                                TabShopThoRen.gI().vecItemUsedInvens = new Vector();
                                TabShopThoRen.gI().vecItemUsedInvens.addAll(TabInventory.gI().vecItemUsedInvens);
                                TabShopThoRen.gI().vecItemsCanSell = new Vector();
                                TabShopThoRen.gI().vecItemsCanSell.addAll(vector16);
                                TabShopThoRen.gI().startTabShop();
                                TabShopThoRen.gI().idNpc = readShort39;
                            }
                        } else if (readByte51 != 1 && readByte51 == 2) {
                            Res.outz("List đồ cần sửa");
                        }
                        short readShort43 = messageSupport.reader().readShort();
                        Vector vector20 = new Vector();
                        for (int i44 = 0; i44 < readShort43; i44++) {
                            vector20.add(readItemIventory(i44, messageSupport));
                            TabShopThoRen.gI().vecFixingItems = new Vector();
                            TabShopThoRen.gI().vecFixingItems.addAll(vector20);
                            TabShopThoRen.gI().startTabShop();
                        }
                        break;
                    case 24:
                        Player findCharInMap12 = MapScr.findCharInMap(messageSupport.reader().readInt());
                        if (findCharInMap12 == null) {
                            return;
                        }
                        if (findCharInMap12.isMe()) {
                            Res.outz("DAY LA TOI VA TOI DA CHET");
                        }
                        findCharInMap12.cPk = messageSupport.reader().readByte();
                        Res.outz(1, "Chết và set lại PK là:" + ((int) findCharInMap12.cPk));
                        findCharInMap12.startDie(messageSupport.reader().readShort(), messageSupport.reader().readShort());
                        findCharInMap12.isStartEffectDie = true;
                        String readUTF14 = messageSupport.reader().readUTF();
                        int readByte53 = messageSupport.reader().readByte();
                        int[] iArr = new int[readByte53];
                        String[] strArr2 = new String[readByte53];
                        int[] iArr2 = new int[readByte53];
                        for (int i45 = 0; i45 < readByte53; i45++) {
                            iArr2[i45] = i45;
                            iArr[i45] = messageSupport.reader().readByte();
                            strArr2[i45] = messageSupport.reader().readUTF();
                            strArr2[i45] = strArr2[i45].replaceAll("\\(.*?\\)", "");
                            messageSupport.reader().readByte();
                            Res.outz("LOGGGGGG: " + strArr2[i45]);
                        }
                        if (findCharInMap12.isMe()) {
                            MessageDialog.gI().startMsgDialog(readUTF14, iArr, strArr2);
                            break;
                        }
                        break;
                    case 25:
                        CanvasNinja.isLoginDuplicate = true;
                        break;
                    case 38:
                        switch (messageSupport.reader().readByte()) {
                            case 2:
                                short readShort44 = messageSupport.reader().readShort();
                                int readByte54 = messageSupport.reader().readByte();
                                String[] strArr3 = new String[readByte54];
                                for (int i46 = 0; i46 < readByte54; i46++) {
                                    messageSupport.reader().readShort();
                                    messageSupport.reader().readShort();
                                    strArr3[i46] = messageSupport.reader().readUTF();
                                    Res.outz(1, "Talk to NPCcccccccc:" + strArr3[i46]);
                                }
                                String str = "";
                                int readByte55 = messageSupport.reader().readByte();
                                MyButton[] myButtonArr3 = new MyButton[readByte55];
                                for (int i47 = 0; i47 < readByte55; i47++) {
                                    byte readByte56 = messageSupport.reader().readByte();
                                    String readUTF15 = messageSupport.reader().readUTF();
                                    messageSupport.reader().readByte();
                                    MyCommand myCommand5 = new MyCommand(readUTF15, 1, NPCTalkQuestScreen.gI());
                                    myCommand5.subAction = i47;
                                    if (readUTF15.length() > str.length()) {
                                        str = readUTF15;
                                    }
                                    FrameImage frameImage = LoadDataManager.myButtons[readByte56];
                                    myButtonArr3[i47] = new MyButton(frameImage, frameImage, 55, 0, readUTF15, 0, 0, myCommand5);
                                }
                                if (readByte55 > 0) {
                                    Res.outz("NHAN POPUP");
                                    MyButton.updateW(myButtonArr3, Res.fixSizeTagFrameUp(6, MyButton.getWMaxBtns(myButtonArr3), LoadDataManager.myButtons[0]));
                                }
                                mNPC findNPCInMap2 = MapScr.findNPCInMap(readShort44);
                                if (findNPCInMap2 != null) {
                                    NPCTalkQuestScreen.gI().startNPCTalkQuest(strArr3, findNPCInMap2, 0, myButtonArr3);
                                    break;
                                }
                                break;
                            case 4:
                                byte readByte57 = messageSupport.reader().readByte();
                                int readByte58 = messageSupport.reader().readByte();
                                String[] strArr4 = new String[readByte58];
                                for (int i48 = 0; i48 < readByte58; i48++) {
                                    strArr4[i48] = messageSupport.reader().readUTF();
                                }
                                int readByte59 = messageSupport.reader().readByte();
                                MyButton[] myButtonArr4 = new MyButton[readByte59];
                                byte[] bArr3 = new byte[readByte59];
                                String str2 = "";
                                for (int i49 = 0; i49 < readByte59; i49++) {
                                    byte readByte60 = messageSupport.reader().readByte();
                                    String readUTF16 = messageSupport.reader().readUTF();
                                    bArr3[i49] = messageSupport.reader().readByte();
                                    MyCommand myCommand6 = new MyCommand(readUTF16, 1, NPCTalkQuestScreen.gI());
                                    myCommand6.subAction = i49;
                                    if (readUTF16.length() > str2.length()) {
                                        str2 = readUTF16;
                                    }
                                    FrameImage frameImage2 = LoadDataManager.myButtons[readByte60];
                                    myButtonArr4[i49] = new MyButton(frameImage2, frameImage2, 55, 0, readUTF16, 0, 0, myCommand6);
                                }
                                NPCTalkQuestScreen.gI().startXaPhuScr(readByte57, strArr4, MapScr.findNPCInMap((short) 5), 0, myButtonArr4, bArr3);
                                break;
                            case 5:
                                byte readByte61 = messageSupport.readByte();
                                Res.outz("BAT MINI GAME" + ((int) readByte61));
                                messageSupport.readByte();
                                if (readByte61 == 0) {
                                    AvatarMuzik.gI().switchToMe();
                                    break;
                                }
                                break;
                        }
                        break;
                    case 39:
                        byte readByte62 = messageSupport.reader().readByte();
                        if (readByte62 == 0) {
                            byte readByte63 = messageSupport.reader().readByte();
                            Res.outz(1, "numMenuPvp Player:" + ((int) readByte63));
                            String[] strArr5 = new String[readByte63 + 1];
                            byte[] bArr4 = new byte[readByte63 + 1];
                            InteractObjModel[] interactObjModelArr = new InteractObjModel[readByte63 + 1];
                            interactObjModelArr[0] = new InteractObjModel();
                            if (MapScr.gI().interactObjPaint != null) {
                                interactObjModelArr[0].name = MapScr.gI().interactObjPaint.nameFocus;
                                interactObjModelArr[0].indexIcon = -1;
                            }
                            if (interactObjModelArr[0].name == null || interactObjModelArr[0].name.equals("")) {
                                interactObjModelArr[0].name = ((Base) Player.myCharz().charFocus).cName;
                            }
                            for (int i50 = 0; i50 < readByte63; i50++) {
                                interactObjModelArr[i50 + 1] = new InteractObjModel();
                                strArr5[i50] = messageSupport.reader().readUTF();
                                bArr4[i50] = messageSupport.reader().readByte();
                                interactObjModelArr[i50 + 1].name = strArr5[i50];
                                interactObjModelArr[i50 + 1].indexIcon = bArr4[i50];
                                Res.outz(1, "idIconPvp[i]:" + ((int) bArr4[i50]));
                            }
                            bArr4[readByte63] = (byte) 0;
                            strArr5[readByte63] = "Close";
                            if (MapScr.gI().interactObjPaint != null) {
                                MapScr.gI().interactObjPaint.selectMenuPlayer(interactObjModelArr);
                                break;
                            }
                        } else if (readByte62 == 1) {
                            Player readCharInfo2 = readCharInfo(messageSupport);
                            int readByte64 = messageSupport.reader().readByte();
                            HashMap hashMap2 = new HashMap(readByte64);
                            for (int i51 = 0; i51 < readByte64; i51++) {
                                hashMap2.put(messageSupport.reader().readUTF(), messageSupport.reader().readUTF());
                            }
                            byte readByte65 = messageSupport.reader().readByte();
                            MyButton[] myButtonArr5 = new MyButton[readByte65 + 1];
                            for (int i52 = 0; i52 < readByte65; i52++) {
                                String readUTF17 = messageSupport.reader().readUTF();
                                byte readByte66 = messageSupport.reader().readByte();
                                Res.outz(1, "idIconPvp[i]:" + ((int) readByte66));
                                MyCommand myCommand7 = new MyCommand(readUTF17, readByte66, NPCTalkQuestScreen.gI());
                                FrameImage frameImage3 = LoadDataManager.myButtonSmalls[1];
                                myButtonArr5[i52] = new MyButton(frameImage3, frameImage3, 80, 0, readUTF17, 0, 0, myCommand7);
                            }
                            FrameImage frameImage4 = LoadDataManager.myButtonSmalls[1];
                            myButtonArr5[readByte65] = new MyButton(frameImage4, frameImage4, 80, 0, "Đóng", 0, 0, new MyCommand("Đóng", -1, NPCTalkQuestScreen.gI()));
                            PlayerTalkScreen.gI().startNPCTalkQuest(hashMap2, readCharInfo2, myButtonArr5);
                            break;
                        }
                        break;
                    case 47: {
                        byte action = messageSupport.reader().readByte();
                        Res.outz("sub ME_LOAD_QUEST: " + action);

                        if (action == 5) {
                            byte tutorialId = messageSupport.reader().readByte();
                            Res.outz("id SUB_ME_LOAD_TUTORIAL: " + tutorialId);
                            MapScr.gI().startTutorial(tutorialId);

                        } else if (action == 1) {
                            MapScr.removeStatusAllNpc();
                            int numNpcs = messageSupport.reader().readShort();
                            for (int i23 = 0; i23 < numNpcs; i23++) {
                                short npcId = messageSupport.reader().readShort();
                                byte typeStatus = messageSupport.reader().readByte();
                                mNPC npc = MapScr.findNPCInMap(npcId);
                                if (npc != null) {
                                    Res.outz("set type cho NPC: " + npcId);
                                    npc.setTypeStatus(typeStatus);
                                }
                            }

                        } else if (action == 2) {
                            Vector<MissionPaintDetail> missions = new Vector<>();
                            int size = messageSupport.reader().readByte();
                            for (int i45 = 0; i45 < size; i45++) {
                                MissionPaintDetail mission = new MissionPaintDetail();
                                mission.id = messageSupport.reader().readShort();
                                mission.type = messageSupport.reader().readByte();
                                mission.title = messageSupport.reader().readUTF();
                                mission.detailMission = messageSupport.reader().readUTF();
                                mission.detailMission2 = messageSupport.reader().readUTF();
                                mission.idNpc = messageSupport.reader().readShort();
                                mission.idMap = messageSupport.reader().readInt();

                                int lines = 2;
                                if (!mission.detailMission2.equals("")) {
                                    lines += 1;
                                }
                                mission.hPaint = (MissionPaint.fontPaintMission.getHeight() + 3) * lines;

                                missions.add(mission);
                            }
                            MapScr.gI().initMission(missions);

                        } else if (action == 3) {
                            Vector<MissionPaintDetail> singleMission = new Vector<>();
                            MissionPaintDetail mission = new MissionPaintDetail();
                            mission.id = messageSupport.reader().readShort();
                            mission.title = messageSupport.reader().readUTF();
                            mission.detailMission = messageSupport.reader().readUTF();
                            mission.numCur1 = messageSupport.reader().readInt();
                            mission.numMaxMons1 = messageSupport.reader().readInt();
                            mission.detailMission2 = messageSupport.reader().readUTF();
                            mission.numCur2 = messageSupport.reader().readInt();
                            mission.numMaxMons2 = messageSupport.reader().readInt();
                            mission.idNpc = messageSupport.reader().readShort();
                            mission.idMap = messageSupport.reader().readInt();

                            mission.hPaint = (MissionPaint.fontPaintMission.getHeight() + 3) * (mission.detailMission2.equals("") ? 2 : 3);

                            singleMission.add(mission);
                            MapScr.gI().initMission(singleMission);

                        } else if (action == 0) {
                            TabListQuest.vecMissions.removeAllElements();
                            byte sizeMission = messageSupport.reader().readByte();
                            Res.outz("sizeMission: " + sizeMission);

                            for (int i898 = 0; i898 < sizeMission; i898++) {
                                try {
                                    Res.outz("Processing mission " + i898);
                                    MissionPaintDetail mission = new MissionPaintDetail();
                                    Res.outz("Created MissionPaintDetail for mission " + i898);
                                    mission.id = messageSupport.reader().readShort();
                                    mission.type = messageSupport.reader().readByte();
                                    mission.title = messageSupport.reader().readUTF();

                                    byte sizeQuests = messageSupport.reader().readByte();
                                    Res.outz("size quest for mission " + i898 + ": " + sizeQuests);

                                    String progressStr = "";
                                    for (int j = 0; j < sizeQuests; j++) {
                                        String questDetail = messageSupport.reader().readUTF();
                                        Res.outz("NOI DUNG NV " + j + ": " + questDetail);
                                        byte status = messageSupport.reader().readByte();

                                        if (status == 1) {
                                            try {
                                                progressStr += questDetail.replace("%progress%", "(Xong)") + "\n";
                                            } catch (Exception e) {
                                                Res.outz("Error processing mission " + i898 + ": " + e.getMessage());
                                                e.printStackTrace();
                                                TabListQuest.gI().show();
                                                CanvasNinja.debug("SA92", 2);
                                            }
                                        } else if (status == -1) {
                                            progressStr += questDetail.replace("%progress%", "") + "\n";
                                        } else {
                                            progressStr += "%progress%\n";
                                        }
                                    }

                                    String detailMissionRaw = messageSupport.reader().readUTF();
                                    if (detailMissionRaw == null) {
                                        detailMissionRaw = "";
                                    }
                                    String replacedDetailMission = progressStr.replace("%progress%", detailMissionRaw);

                                    mission.detailMission = replacedDetailMission;
                                    mission.numCur1 = messageSupport.reader().readInt();
                                    mission.numMaxMons1 = messageSupport.reader().readInt();
                                    mission.detailMission2 = messageSupport.reader().readUTF();
                                    mission.numCur2 = messageSupport.reader().readInt();
                                    mission.numMaxMons2 = messageSupport.reader().readInt();
                                    mission.isOpen = messageSupport.reader().readBoolean();
                                    mission.idNpc = messageSupport.reader().readShort();
                                    mission.idMap = messageSupport.reader().readInt();

                                    byte itemCount = messageSupport.reader().readByte();
                                    for (int k = 0; k < itemCount; k++) {
                                        short itemId = messageSupport.reader().readShort();
                                        int itemNum = messageSupport.reader().readInt();
                                        String hsd = messageSupport.reader().readUTF();
                                        Item item = new Item(k, itemId);
                                        item.num = itemNum;
                                        item.hsd = hsd;
                                        mission.vecItems.add(item);
                                    }

                                    String description = messageSupport.reader().readUTF();
                                    Res.outz("MO TA NHIEM VU: " + description);
                                    mission.description = description;

                                    TabListQuest.vecMissions.add(mission);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            TabListQuest.gI().show();

                        } else if (action == 4) {
                            RewardDialog.vecItems.removeAllElements();
                            int sizeItems = messageSupport.reader().readByte();
                            for (int i11 = 0; i11 < sizeItems; i11++) {
                                short itemId = messageSupport.reader().readShort();
                                int num = messageSupport.reader().readInt();
                                String hsd = messageSupport.reader().readUTF();
                                Item item = new Item(i11, itemId);
                                item.num = num;
                                item.hsd = hsd;
                                RewardDialog.vecItems.add(item);
                            }
                            RewardDialog.gI().show();
                        }
                        break;
                    }

                    case 52:
                        CanvasNinja.debug("SA5", 2);
                        Player.isLockKey = false;
                        short readShort49 = messageSupport.reader().readShort();
                        short readShort50 = messageSupport.reader().readShort();
                        if (Player.myCharz().isAlive()) {
                            Player.myCharz().cx = readShort49;
                            Player.myCharz().cy = readShort50;
                        }
                        Res.outz(1, "cx:" + ((int) readShort49) + "_cy:" + ((int) readShort50));
                        break;
                    case 54:
                        mSystem.openUrl(messageSupport.readUTF());
                        break;
                    case 58:
                        byte readByte76 = messageSupport.readByte();
                        if (readByte76 == 0) {
                            MapScr.vNpc.addElement(new mNPC(messageSupport.readShort(), messageSupport.readShort(), messageSupport.readShort()));
                            break;
                        } else if (readByte76 == 1) {
                            MapScr.removeNPCInMap(messageSupport.readShort());
                            break;
                        } else if (readByte76 == 2) {
                            short readShort51 = messageSupport.readShort();
                            short readShort52 = messageSupport.readShort();
                            short readShort53 = messageSupport.readShort();
                            mNPC findNPCInMap4 = MapScr.findNPCInMap(readShort51);
                            if (findNPCInMap4 != null) {
                                findNPCInMap4.moveTo(readShort52, readShort53);
                            }
                            break;
                        }
                        break;
                    case 60:
                        byte readByte77 = messageSupport.reader().readByte();
                        if (readByte77 != 6) {
                            Res.outz(2, "MOB_UPDATE SUB:" + ((int) readByte77));
                        }
                        if (readByte77 == 9) {
                            messageSupport.readShort();
                            messageSupport.readShort();
                            messageSupport.readInt();
                            messageSupport.readShort();
                            messageSupport.readByte();
                        }
                        if (readByte77 == 8) {
                            short readShort54 = messageSupport.readShort();
                            short readShort55 = messageSupport.readShort();
                            short readShort56 = messageSupport.readShort();
                            int readInt42 = messageSupport.readInt();
                            Quai findMobInMap2 = MapScr.findMobInMap(readShort54);
                            findMobInMap2.pull(readShort55, readShort56);
                            findMobInMap2.doStun(readInt42);
                            break;
                        } else if (readByte77 == 7) {
                            Quai findMobInMap3 = MapScr.findMobInMap(messageSupport.readShort());
                            if (findMobInMap3 != null) {
                                ((Base) findMobInMap3).cvx = messageSupport.readByte();
                            }
                            break;
                        } else if (readByte77 == 6) {
                            short readShort57 = messageSupport.readShort();
                            short readShort58 = messageSupport.readShort();
                            short readShort59 = messageSupport.readShort();
                            Res.outz("Nhận quái move xMoveTo:" + ((int) readShort58) + "_yMoveTo:" + ((int) readShort59));
                            NewBoss findNewBossInMap = MapScr.findNewBossInMap(readShort57);
                            if (findNewBossInMap != null && readShort58 != findNewBossInMap.xTo) {
                                findNewBossInMap.move(readShort58, readShort59);
                            }
                            break;
                        } else if (readByte77 == 0) {
                            int readInt43 = messageSupport.reader().readInt();
                            short readShort60 = messageSupport.reader().readShort();
                            Player findCharInMap13 = MapScr.findCharInMap(readInt43);
                            int readShort61 = messageSupport.reader().readShort();
                            int i59 = 0;
                            while (true) {
                                short s3 = readShort60;
                                if (i59 >= readShort61) {
                                    break;
                                } else {
                                    short readShort62 = messageSupport.reader().readShort();
                                    int readInt44 = messageSupport.reader().readInt();
                                    int readInt45 = messageSupport.reader().readInt();
                                    Quai findMobInMapById = MapScr.findMobInMapById(readShort62);
                                    if (findMobInMapById.isDie) {
                                        Res.outz(1, "Mob đã chết");
                                    } else {
                                        findMobInMapById.hp = readInt45;
                                        if (findCharInMap13 != null && findCharInMap13.isMe() && MapScr.gI().tutorial != null && MapScr.gI().tutorial.isTutorialEnemy() && MapScr.gI().tutorial.mobFocus != null && MapScr.gI().tutorial.mobFocus.equals(findMobInMapById) && !findMobInMapById.isCheckDieTutorial) {
                                            findMobInMapById.isWaitingCheckTutorial = true;
                                        }
                                        if (findCharInMap13 != null && !findCharInMap13.isMe()) {
                                            findCharInMap13.unFocusAll();
                                            findCharInMap13.mobFocus = findMobInMapById;
                                            findCharInMap13.doFire(Skills.get(s3), false, findMobInMapById.x, findMobInMapById.y);
                                        }
                                        Res.outz("mob hitttttttttttt:" + readInt44 + "_mob.hp:" + findMobInMapById.hp);
                                        if (findCharInMap13 != null) {
                                            if (findCharInMap13.isTwoDamge()) {
                                                if (readInt44 == 0) {
                                                    TextFlyInfo textFlyInfo = new TextFlyInfo(SupportTranslate.getTextLangue("miss"), findMobInMapById.x, findMobInMapById.y - findMobInMapById.h, 0, -2, 4);
                                                    findCharInMap13.vecTextFlyInfos.add(textFlyInfo);
                                                    findCharInMap13.vecTextFlyInfos.add(textFlyInfo);
                                                } else {
                                                    if (findCharInMap13.isMe()) {
                                                        findMobInMapById.setShowHP();
                                                    }
                                                    findCharInMap13.vecTextFlyInfos.add(new TextFlyInfo("-" + (readInt44 / 2), findMobInMapById.x, findMobInMapById.y - findMobInMapById.h, 0, -2, 5));
                                                    findCharInMap13.vecTextFlyInfos.add(new TextFlyInfo("-" + (readInt44 - (readInt44 / 2)), findMobInMapById.x, findMobInMapById.y - findMobInMapById.h, 0, -2, 5));
                                                }
                                            } else if (readInt44 == 0) {
                                                MapScr.startFlyText(SupportTranslate.getTextLangue("miss"), findMobInMapById.x, findMobInMapById.y - findMobInMapById.h, 0, -2, 4);
                                            } else {
                                                if (findCharInMap13.isMe()) {
                                                    findMobInMapById.setShowHP();
                                                }
                                                if (!findCharInMap13.isMe()) {
                                                    findMobInMapById.startEffectHurt(findCharInMap13);
                                                }
                                                MapScr.startFlyText("-" + readInt44, findMobInMapById.x, findMobInMapById.y - findMobInMapById.h, 0, -2, 5);
                                            }
                                        }
                                    }
                                    readShort60 = s3;
                                    i59++;
                                }
                            }
                        } else if (readByte77 == 1) {
                            short readShort63 = messageSupport.reader().readShort();
                            int readInt46 = messageSupport.reader().readInt();
                            int readInt47 = messageSupport.reader().readInt();
                            int readInt48 = messageSupport.reader().readInt();
                            int readInt49 = messageSupport.reader().readInt();
                            messageSupport.reader().readShort();
                            messageSupport.reader().readByte();
                            Player findCharInMap14 = MapScr.findCharInMap(readInt46);
                            Quai findMobInMap4 = MapScr.findMobInMap(readShort63);
                            if (findCharInMap14 != null && findMobInMap4 != null && !findCharInMap14.isDie) {
                                findMobInMap4.dame = readInt47;
                                findMobInMap4.dameMp = readInt48;
                                findMobInMap4.dameSp = readInt49;
                                Res.outz(4, "QUái tấn công người:" + ((Base) findMobInMap4).mobId + "mob.dame:" + findMobInMap4.dame);
                                if (!findCharInMap14.isDie()) {
                                    findMobInMap4.setAttack(findCharInMap14, false);
                                }
                            }
                            break;
                        } else if (readByte77 == 2) {
                            Res.outz("Nhận CMD quái hồi sinh");
                            short readShort64 = messageSupport.reader().readShort();
                            byte readByte78 = messageSupport.reader().readByte();
                            byte readByte79 = messageSupport.reader().readByte();
                            int readInt50 = messageSupport.reader().readInt();
                            Quai findMobInMap5 = MapScr.findMobInMap(readShort64);
                            if (findMobInMap5 != null) {
                                findMobInMap5.hp = readInt50;
                                findMobInMap5.maxHp = readInt50;
                                findMobInMap5.updateHp_bar();
                                findMobInMap5.x = findMobInMap5.xFirst;
                                findMobInMap5.y = findMobInMap5.yFirst;
                                Res.outz("Quái hồi sinh HP:" + findMobInMap5.hp + "_mobX:" + findMobInMap5.x);
                                findMobInMap5.xSd = findMobInMap5.x;
                                findMobInMap5.ySd = findMobInMap5.y;
                                findMobInMap5.sys = readByte78;
                                findMobInMap5.level = (byte) readByte79;
                                findMobInMap5.isMobMe = false;
                                findMobInMap5.setAction((byte) 1, (byte) -2);
                                findMobInMap5.resetSetFromDie();
                            }
                            break;
                        } else if (readByte77 == 3) {
                            short readShort65 = messageSupport.reader().readShort();
                            int readInt51 = messageSupport.reader().readInt();
                            boolean readBoolean5 = messageSupport.reader().readBoolean();
                            Quai findMobInMap6 = MapScr.findMobInMap(readShort65);
                            if (findMobInMap6 != null) {
                                if (MapScr.gI().tutorial != null && MapScr.gI().tutorial.isTutorialEnemy() && MapScr.gI().tutorial.mobFocus != null && MapScr.gI().tutorial.mobFocus.equals(findMobInMap6) && (findMobInMap6.isCheckDieTutorial || findMobInMap6.isWaitingCheckTutorial)) {
                                    Rms.saveRMSInt(Rms.RMS_ATK_ENEMY + Player.myCharz().charID, 1);
                                    MapScr.gI().tutorial = null;
                                }
                                if (readInt51 != 0) {
                                    if (readBoolean5) {
                                        MapScr.startFlyText("- crit " + readInt51, findMobInMap6.x, findMobInMap6.y - findMobInMap6.h, 0, -2, 0);
                                    } else {
                                        MapScr.startFlyText("-" + readInt51, findMobInMap6.x, findMobInMap6.y - findMobInMap6.h, 0, -2, 5);
                                    }
                                }
                                findMobInMap6.startDie();
                                findMobInMap6.isDontMove = true;
                            }
                            break;
                        } else if (readByte77 == 4) {
                            short readShort66 = messageSupport.reader().readShort();
                            int readInt52 = messageSupport.reader().readInt();
                            messageSupport.reader().readInt();
                            messageSupport.reader().readBoolean();
                            Quai findMobInMapById2 = MapScr.findMobInMapById(readShort66);
                            findMobInMapById2.hp = readInt52;
                            findMobInMapById2.updateHp_bar();
                            break;
                        } else if (readByte77 == 5) {
                            short readShort67 = messageSupport.reader().readShort();
                            short readShort68 = messageSupport.reader().readShort();
                            byte readByte80 = messageSupport.reader().readByte();
                            byte readByte81 = messageSupport.reader().readByte();
                            int readInt53 = messageSupport.reader().readInt();
                            int readInt54 = messageSupport.reader().readInt();
                            int readInt55 = messageSupport.reader().readInt();
                            int readInt56 = messageSupport.reader().readInt();
                            Quai quai = new Quai();
                            ((Base) quai).mobId = readShort67;
                            Res.outz("mob.mobId âddddd:" + ((Base) quai).mobId);
                            quai.x = readInt54;
                            quai.y = readInt55;
                            quai.xSd = readInt54;
                            quai.ySd = readInt55;
                            quai.mobTemplate = QuaiTemplate.get(readShort68);
                            quai.hp = readInt53;
                            quai.sys = readByte80;
                            quai.level = readByte81;
                            quai.maxHp = readInt53;
                            if (readInt56 != -1) {
                                quai.isMobMe = false;
                            }
                            Res.outz("spawn quái id:" + ((Base) quai).mobId + "_hp:" + quai.hp);
                            quai.updateHp_bar();
                            MapScr.vMob.addElement(quai);
                            quai.setAction((byte) 8, (byte) ((Base) Player.myCharz()).cdir);
                            break;
                        }
                        break;
                    case 61:
                        int readInt57 = messageSupport.reader().readInt();
                        short readShort69 = messageSupport.reader().readShort();
                        Player findCharInMap15 = MapScr.findCharInMap(readInt57);
                        if (findCharInMap15 != null) {
                            if (findCharInMap15.isMe()) {
                                int readByte82 = messageSupport.reader().readByte();
                                for (int i60 = 0; i60 < readByte82; i60++) {
                                    int readInt58 = messageSupport.reader().readInt();
                                    int readInt59 = messageSupport.reader().readInt();
                                    Player findCharInMap16 = MapScr.findCharInMap(readInt58);
                                    if (findCharInMap16 != null) {
                                        if (readInt59 == 0) {
                                            findCharInMap16.doAction((byte) 10);
                                            MapScr.startFlyText(SupportTranslate.getTextLangue("miss"), findCharInMap16.cx, findCharInMap16.cy - ((Base) findCharInMap16).ch, 0, -2, 4);
                                        } else {
                                            findCharInMap16.doInjure(readInt59, 0, 0, false, false);
                                        }
                                    }
                                }
                                break;
                            } else {
                                int readByte83 = messageSupport.reader().readByte();
                                Res.outz(3, "sizeAttack 1111:" + readByte83);
                                for (int i61 = 0; i61 < readByte83; i61++) {
                                    int readInt60 = messageSupport.reader().readInt();
                                    int readInt61 = messageSupport.reader().readInt();
                                    Res.outz(1, "dmageHit khác đánh:" + readInt61 + "_người bị đánh id là:" + readInt60);
                                    Player findCharInMap17 = MapScr.findCharInMap(readInt60);
                                    if (findCharInMap17 != null) {
                                        findCharInMap17.doInjure(readInt61, 0, 0, false, false);
                                        findCharInMap15.unFocusAll();
                                        findCharInMap15.charFocus = findCharInMap17;
                                    }
                                }
                                findCharInMap15.doFire(Skills.get(readShort69));
                                break;
                            }
                        }
                        break;
                    case 62:
                        if (messageSupport.readByte() == 0) {
                            short readShort70 = messageSupport.readShort();
                            int readShort71 = messageSupport.readShort();
                            int[] iArr3 = new int[readShort71];
                            int[] iArr4 = new int[readShort71];
                            int[] iArr5 = new int[readShort71];
                            int[] iArr6 = new int[readShort71];
                            if (readShort70 == 0) {
                                Res.outz(2, "sizeTarget:" + readShort71);
                            }
                            for (int i62 = 0; i62 < readShort71; i62++) {
                                iArr3[i62] = messageSupport.readInt();
                                iArr4[i62] = messageSupport.readInt();
                                iArr5[i62] = messageSupport.readInt();
                                iArr6[i62] = messageSupport.readInt();
                            }
                            short readShort72 = messageSupport.readShort();
                            messageSupport.readByte();
                            NewBoss findNewBossInMap2 = MapScr.findNewBossInMap(readShort70);
                            if (findNewBossInMap2 != null) {
                                Player[] playerArr = new Player[readShort71];
                                int i63 = 0;
                                int i64 = 0;
                                int i65 = 0;
                                while (true) {
                                    int i66 = i65;
                                    if (i63 >= readShort71) {
                                        findNewBossInMap2.setAttack(playerArr, playerArr[i66], iArr4, readShort72, playerArr[i66] != null ? playerArr[i66].cx > ((Quai) findNewBossInMap2).x ? (byte) 1 : (byte) -1 : (byte) ((Quai) findNewBossInMap2).dir);
                                        break;
                                    } else {
                                        playerArr[i63] = MapScr.findCharInMap(iArr3[i63]);
                                        int i67 = i64;
                                        int i68 = i66;
                                        if (playerArr[i63] != null) {
                                            int abs = Res.abs(playerArr[i63].cx - ((Quai) findNewBossInMap2).x);
                                            i67 = i64;
                                            i68 = i66;
                                            if (abs > i64) {
                                                i68 = i63;
                                                i67 = abs;
                                            }
                                        }
                                        i63++;
                                        i64 = i67;
                                        i65 = i68;
                                    }
                                }
                            }
                        }
                        break;
                    case 82:
                        byte readByte84 = messageSupport.reader().readByte();
                        Res.outz(1, "sub party:" + ((int) readByte84));
                        if (readByte84 == 0) {
                            MapScr.resetColorNameChar();
                            boolean readBoolean6 = messageSupport.reader().readBoolean();
                            int readByte85 = messageSupport.reader().readByte();
                            boolean z2 = (TabParty.partyInfo == null && readByte85 > 0) || readByte85 == 5;
                            PartyInfo partyInfo = new PartyInfo();
                            TabParty.partyInfo = partyInfo;
                            partyInfo.isBlock = readBoolean6;
                            Res.outz("numMem party:" + readByte85);
                            for (int i69 = 0; i69 < readByte85; i69++) {
                                Player readCharInfo3 = readCharInfo(messageSupport);
                                if (readCharInfo3 != null) {
                                    Res.outz("ch.charID:zzzzzz:" + readCharInfo3.charID);
                                    Player findCharInMap18 = MapScr.findCharInMap(readCharInfo3.charID);
                                    if (findCharInMap18 != null) {
                                        findCharInMap18.updateColorName(3);
                                        if (i69 == 0) {
                                            findCharInMap18.isLeader = true;
                                        }
                                    }
                                    readCharInfo3.cHP = 100;
                                    TabParty.partyInfo.vChars.addElement(readCharInfo3);
                                }
                            }
                            TabScr tabScr = CanvasNinja.currentTab;
                            if (tabScr != null) {
                                if (tabScr instanceof TabCreateParty) {
                                    tabScr.show();
                                }
                                if (CanvasNinja.currentTab instanceof TabParty) {
                                    TabCreateParty.gI().show();
                                }
                            }
                            if (z2) {
                                TabCreateParty.gI().show();
                                SubTabScr subTabScr = CanvasNinja.subTab;
                                if (subTabScr != null && (subTabScr instanceof SubTabApplyParty)) {
                                    SubTabApplyParty.me = null;
                                    CanvasNinja.endSubTab();
                                }
                            }
                            MapScr.gI().initPartyPaint();
                            break;
                        } else if (readByte84 == 5) {
                            byte readByte86 = messageSupport.reader().readByte();
                            Res.outz("Trưởng nhóm pick mode:" + ((int) readByte86));
                            TabScr tabScr = CanvasNinja.currentTab;
                            if (tabScr != null && (tabScr instanceof TabCreateParty)) {
                                ((TabCreateParty) tabScr).setMode(readByte86);
                            }
                            break;
                        } else if (readByte84 == 1) {
                            int readInt62 = messageSupport.reader().readInt();
                            String readUTF23 = messageSupport.reader().readUTF();
                            MyButton[] myButtonArr6 = new MyButton[2];
                            MessageDialog messageDialog2 = new MessageDialog();
                            MyCommand myCommand8 = new MyCommand("", -101, messageDialog2);
                            myCommand8.subAction = readInt62;
                            FrameImage[] frameImageArr4 = LoadDataManager.myButtons;
                            myButtonArr6[0] = new MyButton(frameImageArr4[1], frameImageArr4[3], 55, 0, SupportTranslate.getTextLangue("YES"), 0, 0, myCommand8);
                            MyCommand myCommand9 = new MyCommand("", -102, messageDialog2);
                            myCommand9.subAction = readInt62;
                            FrameImage[] frameImageArr5 = LoadDataManager.myButtons;
                            myButtonArr6[1] = new MyButton(frameImageArr5[1], frameImageArr5[3], myButtonArr6[0].w, 0, SupportTranslate.getTextLangue("NO"), 0, 0, myCommand9);
                            messageDialog2.startMsgDialog(readUTF23, myButtonArr6);
                            break;
                        } else if (readByte84 == 3) {
                            messageSupport.reader().readInt();
                            messageSupport.reader().readUTF();
                            messageSupport.reader().readBoolean();
                            break;
                        } else if (readByte84 == 2) {
                            boolean isLeader = TabParty.isLeader();
                            MapScr.resetColorNameChar();
                            TabParty.partyInfo = null;
                            TabParty.vecPartySearchs.removeAllElements();
                            TabParty.vecPartySearchsTemp.removeAllElements();
                            TabScr tabScr5 = CanvasNinja.currentTab;
                            if (tabScr5 != null && ((tabScr5 instanceof TabCreateParty) || (tabScr5 instanceof TabParty))) {
                                TabCreateParty.me = null;
                                TabParty.me = null;
                                CanvasNinja.endTab();
                            }
                            SubTabScr subTabScr2 = CanvasNinja.subTab;
                            if (subTabScr2 != null && ((subTabScr2 instanceof SubTabApplyParty) || (subTabScr2 instanceof SubTabInviteParty))) {
                                SubTabApplyParty.me = null;
                                SubTabInviteParty.me = null;
                                CanvasNinja.endSubTab();
                            }
                            if (isLeader) {
                                CanvasNinja.startOKDlg(SupportTranslate.getTextLangue("outAllGroupSucess"));
                            } else {
                                CanvasNinja.startOKDlg(SupportTranslate.getTextLangue("youRemovedParty"));
                            }
                            MapScr.gI().closeParty();
                            break;
                        } else if (readByte84 == 6) {
                            String readUTF24 = messageSupport.reader().readUTF();
                            MyButton[] myButtonArr7 = new MyButton[2];
                            MessageDialog messageDialog3 = new MessageDialog();
                            MyCommand myCommand10 = new MyCommand("", -103, messageDialog3);
                            FrameImage[] frameImageArr6 = LoadDataManager.myButtons;
                            myButtonArr7[0] = new MyButton(frameImageArr6[1], frameImageArr6[3], 55, 0, SupportTranslate.getTextLangue("YES"), 0, 0, myCommand10);
                            MyCommand myCommand11 = new MyCommand("", -104, messageDialog3);
                            FrameImage[] frameImageArr7 = LoadDataManager.myButtons;
                            myButtonArr7[1] = new MyButton(frameImageArr7[1], frameImageArr7[3], myButtonArr7[0].w, 0, SupportTranslate.getTextLangue("NO"), 0, 0, myCommand11);
                            messageDialog3.startMsgDialog(readUTF24, myButtonArr7);
                            break;
                        } else if (readByte84 == 7) {
                            TabParty.vecPartySearchs.removeAllElements();
                            TabParty.vecPartySearchsTemp.removeAllElements();
                            int readByte87 = messageSupport.reader().readByte();
                            for (int i70 = 0; i70 < readByte87; i70++) {
                                new PartySearch();
                                Player readCharInfo4 = readCharInfo(messageSupport);
                                if (readCharInfo4 != null) {
                                    readCharInfo4.cHP = 100;
                                    TabParty.vecPartySearchsTemp.addElement(new PartySearch(readCharInfo4));
                                }
                            }
                            TabParty.vecPartySearchs.addAll(TabParty.vecPartySearchsTemp);
                            SubTabScr subTabScr = CanvasNinja.subTab;
                            if (subTabScr != null) {
                                if (subTabScr instanceof SubTabInviteParty) {
                                    ((SubTabInviteParty) subTabScr).show();
                                }
                                if (subTabScr instanceof SubTabApplyParty) {
                                    SubTabApplyParty subTabApplyParty = (SubTabApplyParty) subTabScr;
                                    subTabApplyParty.show();
                                }
                            }
                            break;
                        }
                        break;
                    case 83:
                        byte readByte88 = messageSupport.reader().readByte();
                        Res.outz(3, "SUB SUB SUB FRIENDLIST:" + ((int) readByte88));
                        switch (readByte88) {
                            case 0:
                                TabFriend.vecFriends.removeAllElements();
                                int readByte89 = messageSupport.reader().readByte();
                                for (int i71 = 0; i71 < readByte89; i71++) {
                                    Player readCharInfo5 = readCharInfo(messageSupport);
                                    if (readCharInfo5 != null) {
                                        TabFriend.vecFriends.add(readCharInfo5);
                                        readCharInfo5.isOffline = false;
                                    }
                                }
                                int readByte90 = messageSupport.reader().readByte();
                                for (int i72 = 0; i72 < readByte90; i72++) {
                                    Player readCharInfo6 = readCharInfo(messageSupport);
                                    if (readCharInfo6 != null) {
                                        TabFriend.vecFriends.add(readCharInfo6);
                                        readCharInfo6.isOffline = true;
                                    }
                                }
                                TabFriend.vecRequests.removeAllElements();
                                int readByte91 = messageSupport.reader().readByte();
                                for (int i73 = 0; i73 < readByte91; i73++) {
                                    Player readCharInfo7 = readCharInfo(messageSupport);
                                    if (readCharInfo7 != null) {
                                        TabFriend.vecRequests.add(readCharInfo7);
                                        readCharInfo7.isOffline = false;
                                    }
                                }
                                int readByte92 = messageSupport.reader().readByte();
                                for (int i74 = 0; i74 < readByte92; i74++) {
                                    Player readCharInfo8 = readCharInfo(messageSupport);
                                    if (readCharInfo8 != null) {
                                        TabFriend.vecRequests.add(readCharInfo8);
                                        readCharInfo8.isOffline = true;
                                    }
                                }
                                TabFriend.gI().show();
                                break;
                            case 3:
                                Player readCharInfo9 = readCharInfo(messageSupport);
                                if (readCharInfo9 != null) {
                                    Res.outz("TIM THAY BAN");
                                    TabFriend.vecPaints.removeAllElements();
                                    TabFriend.vecPaints.add(readCharInfo9);
                                    break;
                                }
                                break;
                        }
                        break;

                    default:
                        String sb = "[REC] CMD:" + cmd;
                        Res.outz(3, sb);
                        break;
                }


                CanvasNinja.debug("SA92", 2);
            } finally {
                messageSupport.cleanup();
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
    }

    /**
     * Main message dispatcher - handles 45+ different message types
     * Called when player is in lobby/menu (not in gameplay map)
     */
    public void messageNotMap(MessageSupport messageSupport) {
        try {
            // Debug logging
            CanvasNinja.debug("SA6", 2);

            // Read main command byte
            byte subCmd = messageSupport.reader().readByte();

            // Main switch dispatcher
            switch (subCmd) {

                case -105: // Skill icon images
                    byte skillIconVersion = messageSupport.reader().readByte();
                    LoadVersionManager.vcImageIconSkill = skillIconVersion;
                    getImage(messageSupport, (short) 21, Rms.RMS_ICON_SKILL_VERSION, Rms.RMS_ICON_SKILL_SIZE, skillIconVersion);
                    LoadVersionManager.doCheckData();
                    break;

                case -110: // Effect frame loading (L102)
                    byte effectFrameVersion = messageSupport.reader().readByte();
                    LoadVersionManager.vcImageEffectFrame = effectFrameVersion;
                    getImage(messageSupport, (short) 20, Rms.RMS_EFFECT_FRAME_VERSION, Rms.RMS_EFFECT_FRAME_SIZE, effectFrameVersion);
                    LoadVersionManager.doCheckData();
                    break;

                case -108: // Weapon data loading (L68)
                    byte weaponVersion = messageSupport.reader().readByte();
                    LoadVersionManager.vcImageWeapon = weaponVersion;
                    getImageAndData(messageSupport, (short) 13, (short) 14, Rms.RMS_WEAPON_VERSION, Rms.RMS_WEAPON_SIZE, weaponVersion);
                    LoadVersionManager.doCheckData();
                    break;

                case -107: // Weapon splash effect loading (L66)
                    byte weaponSplashVersion = messageSupport.reader().readByte();
                    LoadVersionManager.vcImageWeaponSplash = weaponSplashVersion;
                    LoadVersionManager.doCheckData();
                    break;

                case -119: // Item update data (L220)
                    Res.outz("GET UPDATE_ITEM " + messageSupport.reader().available() + " bytes");
                    messageSupport.reader().mark(100000);
                    createItem(messageSupport.reader());
                    messageSupport.reader().reset();

                    byte[] itemUpdateData = new byte[messageSupport.reader().available()];
                    messageSupport.reader().readFully(itemUpdateData);
                    Rms.saveRMS(Rms.RMS_ITEM, itemUpdateData);
                    Rms.saveRMSInt(Rms.RMS_ITEM_VERSION, LoadVersionManager.vcItem);
                    LoadVersionManager.isUpdateItem = false;
                    LoadVersionManager.doCheckData(true);
                    break;

                case -111: // Effect data loading (L104)
                    byte effectVersion = messageSupport.reader().readByte();
                    LoadVersionManager.vcImageEffect = effectVersion;
                    getImageAndData(messageSupport, (short) 3, (short) 2, Rms.RMS_EFFECT_VERSION, Rms.RMS_EFFECT_SIZE, effectVersion);
                    LoadVersionManager.doCheckData();
                    break;

                case -112: // Character part data loading (L106)
                    byte charVersion = messageSupport.reader().readByte();
                    LoadVersionManager.vcImageChar = charVersion;
                    getImageAndData(messageSupport, (short) 0, (short) 1, Rms.RMS_PART_VERSION, Rms.RMS_PART_SIZE, charVersion);
                    LoadVersionManager.doCheckData();
                    break;

                case -109: // Map template loading
                    Player.myCharz().lastMap = Player.myCharz().currentMap;
                    Player.isLoadingMap = true;
                    Res.outz("NHẬN REQUEST MAP TEMPLATE từ server");
                    CanvasNinja.isLoading = true;

                    // Clear existing map data and trigger GC
                    MyTile.maps = null;
                    MyTile.types = null;
                    System.gc();

                    CanvasNinja.debug("SA99", 2);

                    // Read map dimensions
                    MyTile.tmw = messageSupport.reader().readByte();
                    MyTile.tmh = messageSupport.reader().readByte();
                    MyTile.maps = new int[MyTile.tmw * MyTile.tmh];

                    Res.outz("Cmd.REQUEST_MAPTEMPLATE=10  w|H= " + MyTile.tmw + "|" + MyTile.tmh + "\n Dien Tich = " + (MyTile.tmw * MyTile.tmh));

                    // Read map tile data
                    for (int i = 0; i < MyTile.maps.length; i++) {
                        byte mapValue = messageSupport.reader().readByte();
                        int value = mapValue;
                        if (mapValue < 0) {
                            value = mapValue + 256;  // Convert signed byte to unsigned
                        }
                        MyTile.maps[i] = (char) value;
                    }

                    // Initialize types array and load map
                    MyTile.types = new int[MyTile.maps.length];
                    MyTile.loadMap(MyTile.tileID);

                    // Load additional map info
                    MessageSupport messWait = this.messWait;
                    try {
                        loadInfoMap(messWait);
                        SendMessage.gI().requestChangeMapDone();

                        // Set background based on map ID
                        int mapID = MyTile.mapID;
                        if (mapID == 14) {
                            CanvasNinja.loadBG(4);
                        } else if (mapID == 16) {
                            MyTile.bgID = 8;
                            CanvasNinja.loadBG(8);
                        } else {
                            CanvasNinja.loadBG(MyTile.bgID);
                        }

                        Player.myCharz().currentMap = MyTile.mapID;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case -117: // MOB image frames
                {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    DataOutputStream dataStream = new DataOutputStream(byteStream);
                    LoadVersionManager.vcImageMob = messageSupport.reader().readByte();
                    short mobCount = messageSupport.reader().readShort();
                    dataStream.writeShort(mobCount);

                    for (int i = 0; i < mobCount; i++) {
                        int mobId = messageSupport.reader().readInt();
                        dataStream.writeShort(mobId);
                        dataStream.writeByte(MobTemplates.get(mobId).numFrame);

                        int imageSize = messageSupport.reader().readInt();
                        if (imageSize > 0) {
                            Res.outz(1, "Tải ảnh quái:" + mobId);
                            byte[] imageData = new byte[imageSize];
                            for (int j = 0; j < imageSize; j++) {
                                imageData[j] = messageSupport.reader().readByte();
                            }
                            FileData fileData = new FileData((short) mobId, (short) 5, imageData);
                            LoadDataManager.vSaveRMSDelay.add(fileData);
                        }
                    }

                    DownLoadingScr.isSendClientOk = false;
                    Rms.saveRMSInt(Rms.RMS_MOB_IMAGE_VERSION, LoadVersionManager.vcImageMob);
                    Rms.saveRMS(Rms.RMS_MOB_IMAGE_SIZE, byteStream.toByteArray());
                    LoadVersionManager.isUpdateMob = true;
                    LoadVersionManager.doCheckData();
                }
                break;

                case -116: // Item image frames
                {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    DataOutputStream dataStream = new DataOutputStream(byteStream);
                    LoadVersionManager.vcImageItem = messageSupport.reader().readByte();
                    Res.outz(2, "debug version item server gửi:" + LoadVersionManager.vcImageItem);
                    short itemCount = messageSupport.reader().readShort();
                    dataStream.writeShort(itemCount);

                    for (int i = 0; i < itemCount; i++) {
                        int itemId = messageSupport.reader().readInt();
                        dataStream.writeShort(itemId);
                        dataStream.writeByte(ItemTemplates.get(itemId).numFrame);

                        int imageSize = messageSupport.reader().readInt();
                        if (imageSize > 0) {
                            byte[] imageData = new byte[imageSize];
                            for (int j = 0; j < imageSize; j++) {
                                imageData[j] = messageSupport.reader().readByte();
                            }
                            FileData fileData = new FileData((short) itemId, (short) 4, imageData);
                            LoadDataManager.vSaveRMSDelay.add(fileData);
                        }
                    }

                    DownLoadingScr.isSendClientOk = false;
                    Rms.saveRMSInt(Rms.RMS_ITEM_FRAME_VERSION, LoadVersionManager.vcImageItem);
                    Rms.saveRMS(Rms.RMS_ITEM_FRAME_SIZE, byteStream.toByteArray());
                    LoadVersionManager.isUpdateItemFrame = true;
                    LoadVersionManager.doCheckData();
                }
                break;

                case -115: // NPC image frames
                {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    DataOutputStream dataStream = new DataOutputStream(byteStream);
                    LoadVersionManager.vcImageNpc = messageSupport.reader().readByte();
                    short npcCount = messageSupport.reader().readShort();
                    dataStream.writeShort(npcCount);

                    for (int i = 0; i < npcCount; i++) {
                        int npcId = messageSupport.reader().readInt();
                        dataStream.writeShort(npcId);
                        dataStream.writeByte(NPCHashTables.get(npcId).numFrame);

                        int imageSize = messageSupport.reader().readInt();
                        if (imageSize > 0) {
                            byte[] imageData = new byte[imageSize];
                            for (int j = 0; j < imageSize; j++) {
                                imageData[j] = messageSupport.reader().readByte();
                            }
                            FileData.decrypt(imageData);
                            FileData fileData = new FileData((short) npcId, (short) 8, imageData);
                            LoadDataManager.vSaveRMSDelay.add(fileData);
                        }
                    }

                    DownLoadingScr.isSendClientOk = false;
                    Rms.saveRMSInt(Rms.RMS_NPC_FRAME_VERSION, LoadVersionManager.vcImageNpc);
                    Rms.saveRMS(Rms.RMS_NPC_FRAME_SIZE, byteStream.toByteArray());
                    LoadVersionManager.isUpdateNPCFrame = true;
                    LoadVersionManager.doCheckData();
                }
                break;

                case -114: // Tile image frames
                {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    DataOutputStream dataStream = new DataOutputStream(byteStream);
                    Res.outz(1, "TILE FRAMEEEEEEEEEEEEEEEEEEEE");
                    LoadVersionManager.vcImageTile = messageSupport.reader().readByte();
                    short tileCount = messageSupport.reader().readShort();
                    TileMaps.tileMaps = new TileMaps[tileCount];
                    dataStream.writeShort(tileCount);

                    for (int i = 0; i < tileCount; i++) {
                        int tileId = messageSupport.reader().readInt();
                        dataStream.writeInt(tileId);

                        TileMaps.tileMaps[i] = new TileMaps();
                        TileMaps.tileMaps[i].id = tileId;

                        int imageSize = messageSupport.reader().readInt();
                        if (imageSize > 0) {
                            byte[] imageData = new byte[imageSize];
                            for (int j = 0; j < imageSize; j++) {
                                imageData[j] = messageSupport.reader().readByte();
                            }
                            FileData fileData = new FileData((short) tileId, (short) 9, imageData);
                            LoadDataManager.vSaveRMSDelay.add(fileData);
                        }
                    }

                    DownLoadingScr.isSendClientOk = false;
                    Rms.saveRMSInt(Rms.RMS_TILE_FRAME_VERSION, LoadVersionManager.vcImageTile);
                    Rms.saveRMS(Rms.RMS_TILE_FRAME_SIZE, byteStream.toByteArray());
                    LoadVersionManager.isUpdateTileFrame = true;
                    LoadVersionManager.doCheckData();
                }
                break;

                case -121: // Map update
                    Res.outz("GET UPDATE_MAP " + messageSupport.reader().available() + " bytes");
                    messageSupport.reader().mark(100000);
                    createMap(messageSupport.reader());
                    messageSupport.reader().reset();

                    byte[] mapData = new byte[messageSupport.reader().available()];
                    messageSupport.reader().readFully(mapData);
                    Rms.saveRMS(Rms.RMS_MAP, mapData);
                    Rms.saveRMSInt(Rms.RMS_MAP_VERSION, LoadVersionManager.vcMap);
                    LoadVersionManager.isUpdateMap = false;
                    LoadVersionManager.doCheckData();
                    break;

                case -120: // Skill update
                    Res.outz("GET UPDATE_SKILL " + messageSupport.reader().available() + " bytes");
                    messageSupport.reader().mark(100000);
                    createSkill(messageSupport.reader());
                    messageSupport.reader().reset();

                    byte[] skillData = new byte[messageSupport.reader().available()];
                    messageSupport.reader().readFully(skillData);
                    Rms.saveRMS(Rms.RMS_SKILL, skillData);
                    Rms.saveRMSInt(Rms.RMS_SKILL_VERSION, LoadVersionManager.vcSkill);
                    LoadVersionManager.isUpdateSkill = false;
                    LoadVersionManager.doCheckData();
                    break;

                case -100: // Icon buff images
                    byte version = messageSupport.reader().readByte();
                    LoadVersionManager.vcImageIconBuff = version;
                    getImage(messageSupport, (short) 22, Rms.RMS_ICON_BUFF_VERSION, Rms.RMS_ICON_BUFF_SIZE, version);
                    LoadVersionManager.doCheckData();
                    break;

                case -99: // Item update (L20)
                    Res.outz( "GET UPDATE_ITEM " + messageSupport.reader().available() + " bytes");

                    messageSupport.reader().mark(100000);
                    createAtlas(messageSupport);
                    messageSupport.reader().reset();
                    LoadVersionManager.doCheckData(true);
                    break;

                case -98: // World map images
                    byte worldMapVersion = messageSupport.reader().readByte();
                    LoadVersionManager.vcImageWorldMap = worldMapVersion;
                    getImage(messageSupport, (short) 27, Rms.RMS_WORLD_MAP_VERSION, Rms.RMS_WORLD_MAP_SIZE, worldMapVersion);
                    LoadVersionManager.doCheckData();
                    break;

                case -104: // Sound data
                {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    DataOutputStream dataStream = new DataOutputStream(byteStream);
                    LoadVersionManager.vcSound = messageSupport.reader().readByte();
                    short soundCount = messageSupport.reader().readShort();
                    dataStream.writeShort(soundCount);

                    for (int i = 0; i < soundCount; i++) {
                        int soundId = messageSupport.reader().readInt();
                        dataStream.writeShort(soundId);
                        dataStream.writeByte(1);

                        int soundSize = messageSupport.reader().readInt();
                        if (soundSize > 0) {
                            byte[] soundData = new byte[soundSize];
                            for (int j = 0; j < soundSize; j++) {
                                soundData[j] = messageSupport.reader().readByte();
                            }
                            FileData fileData = new FileData((short) soundId, (short) 25, soundData);
                            LoadDataManager.vSaveRMSDelay.add(fileData);
                        }
                    }

                    DownLoadingScr.isSendClientOk = false;
                    Rms.saveRMSInt(Rms.RMS_SOUND_VERSION, LoadVersionManager.vcSound);
                    Rms.saveRMS(Rms.RMS_SOUND_TEMPLATE, byteStream.toByteArray());
                    LoadVersionManager.doCheckData();
                }
                break;

                case -103: // Music data
                {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    DataOutputStream dataStream = new DataOutputStream(byteStream);
                    LoadVersionManager.vcMusic = messageSupport.reader().readByte();
                    short musicCount = messageSupport.reader().readShort();
                    dataStream.writeShort(musicCount);

                    for (int i = 0; i < musicCount; i++) {
                        int musicId = messageSupport.reader().readInt();
                        dataStream.writeShort(musicId);
                        dataStream.writeByte(1);

                        int musicSize = messageSupport.reader().readInt();
                        if (musicSize > 0) {
                            byte[] musicData = new byte[musicSize];
                            for (int j = 0; j < musicSize; j++) {
                                musicData[j] = messageSupport.reader().readByte();
                            }
                            FileData fileData = new FileData((short) musicId, (short) 26, musicData);
                            LoadDataManager.vSaveRMSDelay.add(fileData);
                        }
                    }

                    DownLoadingScr.isSendClientOk = false;
                    Rms.saveRMSInt(Rms.RMS_MUSIC_VERSION, LoadVersionManager.vcSound);
                    Rms.saveRMS(Rms.RMS_MUSIC_TEMPLATE, byteStream.toByteArray());
                    LoadVersionManager.doCheckData();
                }
                break;

                case -113: // Object map frames
                {

                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    DataOutputStream dataStream = new DataOutputStream(byteStream);
                    LoadVersionManager.vcImageObjectMap = messageSupport.reader().readByte();
                    short objCount = messageSupport.reader().readShort();
                    dataStream.writeShort(objCount);

                    for (int i = 0; i < objCount; i++) {
                        int objId = messageSupport.reader().readInt();
                        dataStream.writeShort(objId);
                        dataStream.writeByte(1);

                        int imageSize = messageSupport.reader().readInt();
                        if (imageSize > 0) {
                            byte[] imageData = new byte[imageSize];
                            for (int j = 0; j < imageSize; j++) {
                                imageData[j] = messageSupport.reader().readByte();
                            }
                            FileData fileData = new FileData((short) objId, (short) 16, imageData);
                            LoadDataManager.vSaveRMSDelay.add(fileData);
                        }

                        int dataSize = messageSupport.reader().readInt();
                        if (dataSize > 0) {
                            byte[] objData = new byte[dataSize];
                            for (int j = 0; j < dataSize; j++) {
                                objData[j] = messageSupport.reader().readByte();
                            }
                            FileData fileData = new FileData((short) objId, (short) 15, objData);
                            LoadDataManager.vSaveRMSDelay.add(fileData);
                        }
                    }

                    DownLoadingScr.isSendClientOk = false;
                    Rms.saveRMSInt(Rms.RMS_OBJECT_MAP_FRAME_VERSION, LoadVersionManager.vcImageObjectMap);
                    Rms.saveRMS(Rms.RMS_OBJ_MAP_FRAME_SIZE, byteStream.toByteArray());
                    LoadVersionManager.isUpdateObjectMap = true;
                    LoadVersionManager.doCheckData();
                }
                break;

                case -126: // Character selection data
                    byte sizeChar = messageSupport.reader().readByte();
                    Res.outz("sizeChar:" + sizeChar);
                    Player[] chars = new Player[3];

                    for (int i = 0; i < sizeChar; i++) {
                        chars[i] = new Player();
                        chars[i].charID = messageSupport.reader().readInt();
                        chars[i].cgender = messageSupport.reader().readByte();
                        chars[i].cName = messageSupport.reader().readUTF();
                        chars[i].classId = messageSupport.reader().readByte();
                        chars[i].clevel = messageSupport.reader().readShort();

                        byte partCount = messageSupport.reader().readByte();
                        short[] parts = new short[partCount];
                        for (int j = 0; j < partCount; j++) {
                            parts[j] = messageSupport.reader().readShort();
                            Res.outz("id part SUB_NOT_MAP_SELECT_CHAR " + j + ":" + parts[j]);
                        }
                        chars[i].setPart(parts);
                    }

                    MyTile.tileID = 1;
                    CanvasNinja.loadBG(1);
                    ChooseCharScreen.gI().startChooseCharScreen(chars);
                    break;

                case -125: // Character creation data
                    byte maleCount = messageSupport.readByte();
                    byte femaleCount = messageSupport.readByte();

                    // Initialize 2D arrays for character creation
                    short[][] headIds = new short[2][];
                    short[][] bodyIds = new short[2][];
                    short[][] legIds = new short[2][];
                    String[][] headNames = new String[2][];
                    String[][] bodyNames = new String[2][];
                    String[][] legNames = new String[2][];

                    // Read data for both genders
                    for (int gender = 0; gender < 2; gender++) {
                        int count = (gender == 0) ? maleCount : femaleCount;

                        headIds[gender] = new short[count];
                        bodyIds[gender] = new short[count];
                        legIds[gender] = new short[count];
                        headNames[gender] = new String[count];
                        bodyNames[gender] = new String[count];
                        legNames[gender] = new String[count];

                        for (int i = 0; i < count; i++) {
                            headIds[gender][i] = messageSupport.readShort();
                            bodyIds[gender][i] = messageSupport.readShort();
                            legIds[gender][i] = messageSupport.readShort();
                            headNames[gender][i] = messageSupport.readUTF();
                            bodyNames[gender][i] = messageSupport.readUTF();
                            legNames[gender][i] = messageSupport.readUTF();
                        }
                    }

                    // Read class names
                    byte classCount = messageSupport.readByte();
                    String[] classNames = new String[classCount];
                    for (int i = 0; i < classCount; i++) {
                        classNames[i] = messageSupport.readUTF();
                    }

                    // Setup character creation screen
                    Player.myCharz().clearMe();
                    CreateCharScreen.gI().load(headIds, bodyIds, legIds, headNames, bodyNames, legNames, classNames);
                    CanvasNinja.loadCurrMap((byte) -2, -2);
                    CanvasNinja.loadBG(0);
                    CreateCharScreen.gI().switchToMe();
                    break;

                case -124: // Item background data
                    MyTile.vItemBg.removeAllElements();
                    short nItem = messageSupport.reader().readShort();
                    Res.outz("nItem= " + nItem);

                    for (int i = 0; i < nItem; i++) {
                        ObjMap obj = new ObjMap();
                        obj.id = i;
                        obj.idImage = messageSupport.reader().readShort();
                        obj.dx = messageSupport.reader().readShort();
                        obj.dy = messageSupport.reader().readShort();

                        byte tileCount = messageSupport.reader().readByte();
                        obj.tileX = new int[tileCount];
                        obj.tileY = new int[tileCount];

                        for (int j = 0; j < tileCount; j++) {
                            obj.tileX[i] = messageSupport.reader().readByte();
                            obj.tileY[i] = messageSupport.reader().readByte();
                        }

                        MyTile.vItemBg.addElement(obj);
                    }
                    break;

                case -123: // Version checking & sync (L227)
                    // Read version data from server
                    LoadVersionManager.vsData = messageSupport.reader().readInt();
                    LoadVersionManager.vsMap = messageSupport.reader().readInt();
                    LoadVersionManager.vsSkill = messageSupport.reader().readInt();
                    LoadVersionManager.vsItem = messageSupport.reader().readInt();
                    messageSupport.reader().readInt(); // Read and discard one int value
                    LoadVersionManager.vsImageMob = messageSupport.reader().readInt();

                    // Log DATA VERSION comparison
                    StringBuilder sb = new StringBuilder();
                    sb.append("****** DATA VERSION: Server ");
                    sb.append(LoadVersionManager.vsData);
                    sb.append(" Client ");
                    sb.append(LoadVersionManager.vcData);
                    Res.outz(2, sb.toString());

                    // Log MAP VERSION comparison
                    sb = new StringBuilder();
                    sb.append("****** MAP VERSION: Server ");
                    sb.append(LoadVersionManager.vsMap);
                    sb.append(" Client ");
                    sb.append(LoadVersionManager.vcMap);
                    Res.outz(2, sb.toString());

                    // Log SKILL VERSION comparison
                    sb = new StringBuilder();
                    sb.append("****** SKILL VERSION: Server ");
                    sb.append(LoadVersionManager.vsSkill);
                    sb.append(" Client ");
                    sb.append(LoadVersionManager.vcSkill);
                    Res.outz(2, sb.toString());

                    // Log ITEM VERSION comparison
                    sb = new StringBuilder();
                    sb.append("****** ITEM VERSION: Server ");
                    sb.append(LoadVersionManager.vsItem);
                    sb.append(" Client ");
                    sb.append(LoadVersionManager.vcItem);
                    Res.outz(2, sb.toString());

                    // Log MOB VERSION comparison
                    sb = new StringBuilder();
                    sb.append("****** MOB VERSION: Server ");
                    sb.append(LoadVersionManager.vsImageMob);
                    sb.append(" Client ");
                    sb.append(LoadVersionManager.vcImageMob);
                    Res.outz(2, sb.toString());

                    // Check if data update is needed
                    if (LoadVersionManager.vsData != LoadVersionManager.vcData) {
                        Res.outz("send update data");
                        LoadVersionManager.isUpdateData = true;
                        SendMessage.gI().updateData();
                    } else {
                        try {
                            LoadVersionManager.isUpdateData = true;
                        } catch (Exception e) {
                            LoadVersionManager.isUpdateData = false;
                            SendMessage.gI().updateData();
                            e.printStackTrace();
                        }
                    }

                    // Check sound resources
                    if (LoadVersionManager.vsMap != LoadVersionManager.vcSound) {
                        SendMessage.gI().updateResoucesBySub((byte) -104, 13);
                    } else {
                        try {
                            byte[] soundTemplate = Rms.loadRMS(Rms.RMS_SOUND_TEMPLATE);
                            if (soundTemplate == null) {
                                SendMessage.gI().updateResoucesBySub((byte) -104, 13);
                            } else {
                                ByteArrayInputStream byteStream = new ByteArrayInputStream(soundTemplate);
                                DataInputStream dataStream = new DataInputStream(byteStream);
                                short soundCount = dataStream.readShort();

                                for (int i = 0; i < soundCount; i++) {
                                    short soundId = dataStream.readShort();
                                    dataStream.readByte(); // Skip flag

                                    if (!Res.checkSoundExist(soundId, (short) 25, (short) -1)) {
                                        if (soundId >= 10000) {
                                            SendMessage.gI().updateResoucesBySub((byte) -104, 13);
                                            break;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            SendMessage.gI().updateResoucesBySub((byte) -104, 13);
                            e.printStackTrace();
                        }
                    }

                    // Check music resources
                    if (LoadVersionManager.vsMap != LoadVersionManager.vcMusic) {
                        SendMessage.gI().updateResoucesBySub((byte) -103, 14);
                    } else {
                        try {
                            byte[] musicTemplate = Rms.loadRMS(Rms.RMS_MUSIC_TEMPLATE);
                            if (musicTemplate == null) {
                                SendMessage.gI().updateResoucesBySub((byte) -103, 14);
                            } else {
                                ByteArrayInputStream byteStream = new ByteArrayInputStream(musicTemplate);
                                DataInputStream dataStream = new DataInputStream(byteStream);
                                short musicCount = dataStream.readShort();

                                for (int i = 0; i < musicCount; i++) {
                                    short musicId = dataStream.readShort();
                                    dataStream.readByte(); // Skip flag

                                    if (!Res.checkMusicExist(musicId, (short) 26, (short) -1)) {
                                        if (musicId >= 10000) {
                                            SendMessage.gI().updateResoucesBySub((byte) -103, 14);
                                            break;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            SendMessage.gI().updateResoucesBySub((byte) -103, 14);
                            e.printStackTrace();
                        }
                    }

                    // Check map data
                    if (LoadVersionManager.vsMap != LoadVersionManager.vcMap) {
                        LoadVersionManager.isUpdateMap = true;
                        SendMessage.gI().updateMap();
                    } else {
                        try {
                            byte[] mapData2 = Rms.loadRMS(Rms.RMS_MAP);
                            if (mapData2 == null) {
                                LoadVersionManager.isUpdateMap = true;
                                SendMessage.gI().updateMap();
                            } else {
                                ByteArrayInputStream byteStream = new ByteArrayInputStream(mapData2);
                                DataInputStream dataStream = new DataInputStream(byteStream);
                                createMap(dataStream);
                            }
                        } catch (Exception e) {
                            LoadVersionManager.isUpdateMap = true;
                            SendMessage.gI().updateMap();
                            e.printStackTrace();
                        }
                    }

                    // Check skill data
                    if (LoadVersionManager.vsSkill != LoadVersionManager.vcSkill) {
                        LoadVersionManager.isUpdateSkill = true;
                        SendMessage.gI().updateSkill();
                    } else {
                        try {
                            byte[] skillData2 = Rms.loadRMS(Rms.RMS_SKILL);
                            if (skillData2 == null) {
                                LoadVersionManager.isUpdateSkill = true;
                                SendMessage.gI().updateSkill();
                            } else {
                                DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(skillData2));
                                createSkill(dataStream);
                            }
                        } catch (Exception e) {
                            LoadVersionManager.isUpdateSkill = true;
                            SendMessage.gI().updateSkill();
                            e.printStackTrace();
                        }
                    }

                    // Initialize resource check flags
                    boolean checkObjectMap = true;
                    boolean checkTileFrame = true;
                    boolean checkIconBuff = true;

                    // Check object map resources
                    if (LoadVersionManager.vsMap != LoadVersionManager.vcImageObjectMap) {
                        LoadVersionManager.isUpdateObjectMap = false;
                        SendMessage.gI().updateObjMapFrame();
                    } else {
                        if (!LoadVersionManager.isCheckOnlyVersion) {
                            try {
                                byte[] objMapData = Rms.loadRMS(Rms.RMS_OBJ_MAP_FRAME_SIZE);
                                if (objMapData == null) {
                                    LoadVersionManager.isUpdateObjectMap = false;
                                    SendMessage.gI().updateObjMapFrame();
                                    checkObjectMap = false;
                                } else {
                                    DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(objMapData));
                                    short objCount = dataStream.readShort();

                                    for (int i = 0; i < objCount; i++) {
                                        short objId = dataStream.readShort();
                                        dataStream.readByte(); // Skip flag

                                        if (!Image.checkImageExist(objId, (short) 15)) {
                                            if (objId >= 10000) {
                                                checkObjectMap = false;
                                                LoadVersionManager.isUpdateObjectMap = false;
                                                SendMessage.gI().updateObjMapFrame();
                                                break;
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                LoadVersionManager.isUpdateObjectMap = false;
                                SendMessage.gI().updateObjMapFrame();
                                e.printStackTrace();
                            }
                        }
                    }

                    // Check tile frame resources
                    if (LoadVersionManager.vsMap != LoadVersionManager.vcImageTile) {
                        LoadVersionManager.isUpdateTileFrame = false;
                        SendMessage.gI().updateTileFrame();
                    } else {
                        if (!LoadVersionManager.isCheckOnlyVersion) {
                            try {
                                byte[] tileData = Rms.loadRMS(Rms.RMS_TILE_FRAME_SIZE);
                                if (tileData == null) {
                                    LoadVersionManager.isUpdateTileFrame = false;
                                    SendMessage.gI().updateTileFrame();
                                    checkTileFrame = false;
                                } else {
                                    DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(tileData));
                                    short tileCount = dataStream.readShort();

                                    for (int i = 0; i < tileCount; i++) {
                                        int tileId = dataStream.readInt();

                                        if (!Image.checkImageExist((short) i, (short) 9, (short) tileId)) {
                                            checkTileFrame = false;
                                            LoadVersionManager.isUpdateTileFrame = false;
                                            SendMessage.gI().updateTileFrame();
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                LoadVersionManager.isUpdateTileFrame = false;
                                SendMessage.gI().updateTileFrame();
                                e.printStackTrace();
                            }
                        }
                    }

                    // Check item data
                    if (LoadVersionManager.vsItem != LoadVersionManager.vcItem) {
                        LoadVersionManager.isUpdateItem = false;
                        SendMessage.gI().updateItem();
                    } else {
                        try {
                            byte[] itemData = Rms.loadRMS(Rms.RMS_ITEM);
                            if (itemData == null) {
                                LoadVersionManager.isUpdateItem = false;
                                SendMessage.gI().updateItem();
                            } else {
                                DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(itemData));
                                createItem(dataStream);
                                LoadVersionManager.isUpdateItem = true;
                            }
                        } catch (Exception e) {
                            LoadVersionManager.isUpdateItem = false;
                            SendMessage.gI().updateItem();
                            e.printStackTrace();
                        }
                    }

                    // Check character part data
                    if (LoadVersionManager.vsData != LoadVersionManager.vcImageChar) {
                        SendMessage.gI().updatePart();
                    } else {
                        if (!LoadVersionManager.isCheckOnlyVersion) {
                            try {
                                byte[] partData = Rms.loadRMS(Rms.RMS_PART_SIZE);
                                if (partData == null) {
                                    SendMessage.gI().updatePart();
                                } else {
                                    DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(partData));
                                    short partCount = dataStream.readShort();

                                    for (int i = 0; i < partCount; i++) {
                                        short partId = dataStream.readShort();
                                        dataStream.readByte(); // Skip flag

                                        Res.outz(1, "idTemplate part:" + partId);
                                        Image.checkImageExist(partId, (short) 1);

                                        if (!Image.checkImageExist(partId, (short) 0)) {
                                            SendMessage.gI().updatePart();
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                SendMessage.gI().updatePart();
                                e.printStackTrace();
                            }
                        }
                    }

                    // Check effect skill resources
                    if (LoadVersionManager.vsSkill != LoadVersionManager.vcImageEffect) {
                        SendMessage.gI().updateEffectSkill();
                    } else {
                        if (!LoadVersionManager.isCheckOnlyVersion) {
                            try {
                                byte[] effectData = Rms.loadRMS(Rms.RMS_EFFECT_SIZE);
                                if (effectData == null) {
                                    SendMessage.gI().updateEffectSkill();
                                } else {
                                    DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(effectData));
                                    short effectCount = dataStream.readShort();

                                    for (int i = 0; i < effectCount; i++) {
                                        short effectId = dataStream.readShort();
                                        dataStream.readByte(); // Skip flag

                                        Image.checkImageExist(effectId, (short) 2);
                                        if (!Image.checkImageExist(effectId, (short) 3)) {
                                            SendMessage.gI().updateEffectSkill();
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                SendMessage.gI().updateEffectSkill();
                                e.printStackTrace();
                            }
                        }
                    }

                    // Check world map resources
                    if (LoadVersionManager.vcMap != LoadVersionManager.vcImageWorldMap) {
                        SendMessage.gI().updateImageWorldMap();
                    } else {
                        if (!LoadVersionManager.isCheckOnlyVersion) {
                            try {
                                byte[] worldMapData = Rms.loadRMS(Rms.RMS_WORLD_MAP_SIZE);
                                if (worldMapData == null) {
                                    SendMessage.gI().updateImageWorldMap();
                                } else {
                                    DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(worldMapData));
                                    short worldMapCount = dataStream.readShort();

                                    for (int i = 0; i < worldMapCount; i++) {
                                        short worldMapId = dataStream.readShort();
                                        dataStream.readByte(); // Skip flag

                                        Image.checkImageExist(worldMapId, (short) 2);
                                        if (!Image.checkImageExist(worldMapId, (short) 27)) {
                                            SendMessage.gI().updateImageWorldMap();
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                SendMessage.gI().updateImageWorldMap();
                                e.printStackTrace();
                            }
                        }
                    }

                    // Check effect frame resources
                    if (LoadVersionManager.vsSkill != LoadVersionManager.vcImageEffectFrame) {
                        SendMessage.gI().updateEffectFrame();
                    } else {
                        if (!LoadVersionManager.isCheckOnlyVersion) {
                            try {
                                byte[] effectFrameData = Rms.loadRMS(Rms.RMS_EFFECT_FRAME_SIZE);
                                if (effectFrameData == null) {
                                    SendMessage.gI().updateEffectFrame();
                                } else {
                                    DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(effectFrameData));
                                    short effectFrameCount = dataStream.readShort();

                                    for (int i = 0; i < effectFrameCount; i++) {
                                        short effectFrameId = dataStream.readShort();
                                        dataStream.readByte(); // Skip flag

                                        if (!Image.checkImageExist(effectFrameId, (short) 20)) {
                                            SendMessage.gI().updateEffectFrame();
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                SendMessage.gI().updateEffectFrame();
                                e.printStackTrace();
                            }
                        }
                    }

                    // Check weapon resources
                    if (LoadVersionManager.vsData != LoadVersionManager.vcImageWeapon) {
                        SendMessage.gI().updateWeapon();
                    } else {
                        if (!LoadVersionManager.isCheckOnlyVersion) {
                            try {
                                byte[] weaponData = Rms.loadRMS(Rms.RMS_WEAPON_SIZE);
                                if (weaponData == null) {
                                    SendMessage.gI().updateWeapon();
                                } else {
                                    DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(weaponData));
                                    short weaponCount = dataStream.readShort();

                                    for (int i = 0; i < weaponCount; i++) {
                                        short weaponId = dataStream.readShort();
                                        dataStream.readByte(); // Skip flag

                                        Image.checkImageExist(weaponId, (short) 14);
                                        if (!Image.checkImageExist(weaponId, (short) 13)) {
                                            SendMessage.gI().updateWeapon();
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                SendMessage.gI().updateWeapon();
                                e.printStackTrace();
                            }
                        }
                    }

                    // Check weapon splash resources
                    if (LoadVersionManager.vsSkill != LoadVersionManager.vcImageWeaponSplash) {
                        SendMessage.gI().updateWeaponSplash();
                    } else {
                        if (!LoadVersionManager.isCheckOnlyVersion) {
                            try {
                                byte[] weaponSplashData = Rms.loadRMS(Rms.RMS_WEAPON_SPLASH_SIZE);
                                if (weaponSplashData == null) {
                                    SendMessage.gI().updateWeaponSplash();
                                } else {
                                    DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(weaponSplashData));
                                    short weaponSplashCount = dataStream.readShort();

                                    for (int i = 0; i < weaponSplashCount; i++) {
                                        short weaponSplashId = dataStream.readShort();
                                        dataStream.readByte(); // Skip flag

                                        Image.checkImageExist(weaponSplashId, (short) 11);
                                        if (!Image.checkImageExist(weaponSplashId, (short) 12)) {
                                            SendMessage.gI().updateWeaponSplash();
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                SendMessage.gI().updateWeaponSplash();
                                e.printStackTrace();
                            }
                        }
                    }

                    // Check status buff resources
                    if (LoadVersionManager.vsSkill != LoadVersionManager.vcImageStatusBuff) {
                        SendMessage.gI().updateStatusEffectBuff();
                    } else {
                        if (!LoadVersionManager.isCheckOnlyVersion) {
                            try {
                                byte[] statusBuffData = Rms.loadRMS(Rms.RMS_STT_BUFF_SIZE);
                                if (statusBuffData == null) {
                                    SendMessage.gI().updateStatusEffectBuff();
                                } else {
                                    DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(statusBuffData));
                                    short statusBuffCount = dataStream.readShort();

                                    for (int i = 0; i < statusBuffCount; i++) {
                                        short statusBuffId = dataStream.readShort();
                                        dataStream.readByte(); // Skip flag

                                        if (!Image.checkImageExist(statusBuffId, (short) 17)) {
                                            SendMessage.gI().updateStatusEffectBuff();
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                SendMessage.gI().updateStatusEffectBuff();
                                e.printStackTrace();
                            }
                        }
                    }

                    // Check icon buff resources
                    if (LoadVersionManager.vsItem != LoadVersionManager.vcImageIconBuff) {
                        SendMessage.gI().updateResoucesBySub((byte) -100, 12);
                    } else {
                        if (!LoadVersionManager.isCheckOnlyVersion) {
                            try {
                                byte[] iconBuffData = Rms.loadRMS(Rms.RMS_ICON_BUFF_SIZE);
                                if (iconBuffData == null) {
                                    SendMessage.gI().updateResoucesBySub((byte) -100, 12);
                                    checkIconBuff = false;
                                } else {
                                    DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(iconBuffData));
                                    short iconBuffCount = dataStream.readShort();

                                    for (int i = 0; i < iconBuffCount; i++) {
                                        short iconBuffId = dataStream.readShort();
                                        dataStream.readByte(); // Skip flag

                                        if (!Image.checkImageExist(iconBuffId, (short) 22)) {
                                            checkIconBuff = false;
                                            SendMessage.gI().updateResoucesBySub((byte) -100, 12);
                                            break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                SendMessage.gI().updateResoucesBySub((byte) -100, 12);
                                e.printStackTrace();
                            }
                        }
                    }

                    // Final data validation and loading check
                    LoadVersionManager.isLoadAllData = LoadVersionManager.isLoadAllData();
                    LoadVersionManager.doCheckData(checkIconBuff);
                    break;

                case -118: // Simple short read
                    short size = messageSupport.reader().readShort();

                    // Process atlas template data
                    for (int i = 0; i < size; i++) {
                        int versionData = messageSupport.reader().readInt();
                        short idAtlas = messageSupport.reader().readShort();
                        String name = messageSupport.reader().readUTF();
                        short numFrame = messageSupport.reader().readShort();

                        Res.outz(2, "version atlas sv:" + versionData + "_idAtlas:" + idAtlas);
                        AtlasTemplate atlas = new AtlasTemplate(versionData, idAtlas, name, numFrame);
                        AtlasTemplates.add(atlas);
                    }

                    LoadVersionManager.isHaveTemplateAtlas = true;
                    LoadVersionManager.doCheckData();
                    break;

                case -82: // Tile data loading (L3)
                    // Read number of tile types
                    byte tileTypeCount = messageSupport.reader().readByte();

                    // Initialize tile arrays
                    MyTile.tileIndex = new int[tileTypeCount][][];
                    MyTile.tileType = new int[tileTypeCount][];

                    // Process each tile type
                    for (int i = 0; i < tileTypeCount; i++) {
                        // Read number of tiles for this type
                        byte tileCount = messageSupport.reader().readByte();

                        // Initialize arrays for this tile type
                        MyTile.tileType[i] = new int[tileCount];
                        MyTile.tileIndex[i] = new int[tileCount][];

                        // Process each tile in this type
                        for (int j = 0; j < tileCount; j++) {
                            // Read tile type value
                            MyTile.tileType[i][j] = messageSupport.reader().readInt();

                            // Read number of indices for this tile
                            byte indexCount = messageSupport.reader().readByte();
                            MyTile.tileIndex[i][j] = new int[indexCount];

                            // Read all index values for this tile
                            for (int k = 0; k < indexCount; k++) {
                                MyTile.tileIndex[i][j][k] = messageSupport.reader().readShort();
                            }
                        }
                    }

                    Res.outz("===DONE TILE_SET====");
                    break;

                default:
                    // Log received command
                    Res.outz(3, "[REC] SUB CMD NOT_MAP:" + subCmd);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (messageSupport != null) {
                messageSupport.cleanup();
            }
        }
    }
}
