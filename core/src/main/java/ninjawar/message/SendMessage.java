package ninjawar.message;

import ninjawar.model.mCmd;
import ninjawar.mylib.MySession;
import ninjawar.mylib.VectorCustom;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.Player;
import ninjawar.myscr.Quai;
import ninjawar.myscr.Res;
import ninjawar.object.Base;
import ninjawar.screen.DownLoadingScr;
import ninjawar.screen.tab.TabInventory;
import ninjawar.screen.tab.TabShopThoRen;
import ninjawar.supporter.LoadVersionManager;

import java.io.IOException;

public class SendMessage extends mCmd {
    public static long curCheckController;
    public static long curCheckMap;
    protected static SendMessage instance;
    public static long lastTimeSend;
    public static long lastTimeSendNew;
    public static long timeCreateChar;
    public static long timeSendMove;
    protected MessageSupport m;
    ISession session = MySession.gI();
    boolean isSendUpdateItem = false;
    public boolean[] isSendAtlas = new boolean[20];

    public static SendMessage gI() {
        if (instance == null) {
            instance = new SendMessage();
        }
        return instance;
    }

    private void initNotMap(byte b) {
        init((byte) -28);
        writeByte(b);
    }

    public void acceptJoinParty(int i, boolean z) {
        init((byte) 82);
        writeByte(3);
        writeInt(i);
        writeBoolean(z);
        send();
    }

    public void acceptTeleportParty(boolean z) {
        init((byte) 82);
        writeByte(6);
        writeBoolean(z);
        send();
    }

    public void buyItemShop(short s, int i, int i2, int i3, int i4) {
        MessageSupport messageSupport = new MessageSupport((byte) 23);
        try {
            messageSupport.writer().writeByte(i);
            messageSupport.writer().writeShort(s);
            messageSupport.writer().writeShort(i2);
            messageSupport.writer().writeShort(i3);
            messageSupport.writer().writeByte(i4);
            this.session.sendMessage(messageSupport);
        } catch (Exception e) {
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
        messageSupport.cleanup();
    }

    public void chatTo(int i, String str) {
        chatToDefault((byte) 0, (byte) 0, i, str, (byte) 5, -1);
    }

    public void chatToDefault(byte b, byte b2, int i, String str, byte b3, int i2) {
        init((byte) -23);
        try {
            this.m.writer().writeByte(b);
            this.m.writer().writeByte(b2);
            this.m.writer().writeInt(i);
            this.m.writer().writeUTF(str);
            this.m.writer().writeByte(b3);
            if (b2 == 1) {
                this.m.writer().writeInt(i2);
            }
        } catch (IOException e) {
        }
        send();
    }

    public void clientOk() {
        if (NinjaMidlet.isSendClientOK) {
            return;
        }
        Res.outz(1, "SEND CLIENT OK OK OK OK OK");
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -101);
                this.session.sendMessage(messageNotMap);
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                NinjaMidlet.isSendClientOK = true;
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void createChar(String str, int i, int i2, short[] sArr) {
        if (mSystem.currentTimeMillis() < timeCreateChar) {
            return;
        }
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -125);
                messageNotMap.writer().writeUTF(str);
                messageNotMap.writer().writeByte(i);
                messageNotMap.writer().writeByte(i2);
                messageNotMap.writer().writeByte(sArr.length);
                for (int i3 = 0; i3 < sArr.length; i3++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Tạo char part:");
                    sb.append((int) sArr[i3]);
                    Res.outz(sb.toString());
                    messageNotMap.writer().writeShort(sArr[i3]);
                }
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                timeCreateChar = mSystem.currentTimeMillis() + 800;
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
            this.session.sendMessage(messageSupport);
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void createParty() {
        init((byte) 82);
        writeByte(4);
        send();
    }

    public void deleteAccount() {
        init((byte) -29);
        writeByte(2);
        send();
    }

    public void getPlayersMatchingName(String str) {
        init((byte) 83);
        writeByte(3);
        writeUTF(str);
        send();
    }

    public void init(byte b) {
        this.m = new MessageSupport(b);
    }

    public void invitePlayerToParty(int i) {
        init((byte) 82);
        writeByte(1);
        writeInt(i);
        send();
    }

    public void loadInfoAuto() {
        init((byte) 7);
        writeByte(0);
        send();
    }

    public void logOut() {
        NinjaMidlet.isSendClientOK = false;
        DownLoadingScr.isSendClientOk = false;
        init((byte) -28);
        writeByte(-127);
        send();
    }

    public void login(String str, String str2, String str3, String str4, String str5, String str6, byte b) {
        init((byte) -29);
        writeByte(0);
        writeUTF(str);
        writeUTF(str2);
        writeUTF(str3);
        writeUTF(str4);
        writeUTF(str5);
        writeUTF(str6);
        writeByte(b);
        send();
    }

    public MessageSupport messageNotMap(byte b) throws IOException {
        MessageSupport messageSupport = new MessageSupport((byte) -28);
        messageSupport.writer().writeByte(b);
        return messageSupport;
    }

    public void moveItemToWareHouse(int i, int i2) {
        MessageSupport messageSupport = new MessageSupport((byte) 15);
        try {
            messageSupport.writer().writeByte(0);
            messageSupport.writer().writeByte(3);
            messageSupport.writer().writeByte(4);
            messageSupport.writer().writeShort(i);
            messageSupport.writer().writeInt(i2);
            this.session.sendMessage(messageSupport);
        } catch (Exception e) {
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
        messageSupport.cleanup();
    }

    public void moveWareHouseToInventory(int i, int i2) {
        MessageSupport messageSupport = new MessageSupport((byte) 15);
        try {
            messageSupport.writer().writeByte(0);
            messageSupport.writer().writeByte(4);
            messageSupport.writer().writeByte(3);
            messageSupport.writer().writeShort(i);
            messageSupport.writer().writeInt(i2);
            this.session.sendMessage(messageSupport);
        } catch (Exception e) {
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
        messageSupport.cleanup();
    }

    public void openInventory() {
        MessageSupport messageSupport = new MessageSupport((byte) 15);
        try {
            messageSupport.writer().writeByte(4);
            this.session.sendMessage(messageSupport);
        } catch (Exception e) {
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
        messageSupport.cleanup();
    }

    public void openUIFriend() {
        init((byte) 83);
        writeByte(0);
        send();
    }

    public void outGroupParty(int i) {
        init((byte) 82);
        writeByte(2);
        writeInt(i);
        send();
    }

    public void pickItem(int i) {
        init((byte) 6);
        writeByte(2);
        writeShort(i);
        Res.outz(1, "PICK ITEM:" + i);
        send();
    }

    public void privateMessage(String str, String str2) {
        init((byte) -23);
        try {
            this.m.writer().writeByte(4);
            this.m.writer().writeUTF(str);
            this.m.writer().writeUTF(str2);
        } catch (IOException e) {
        }
        send();
    }

    public void removeFriend(int i, String str, byte b, short s) {
        init((byte) 83);
        writeByte(2);
        writeInt(i);
        writeUTF(str);
        writeByte(b);
        writeShort(s);
        send();
    }

    public void removeItemInventory(byte b, int i, int i2) {
        MessageSupport messageSupport = new MessageSupport((byte) 15);
        try {
            messageSupport.writer().writeByte(3);
            messageSupport.writer().writeByte(b);
            messageSupport.writer().writeShort(i);
            messageSupport.writer().writeShort(i2);
            this.session.sendMessage(messageSupport);
        } catch (Exception e) {
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
        messageSupport.cleanup();
    }

    public void repairItem(int i) {
        MessageSupport messageSupport = new MessageSupport((byte) 23);
        try {
            messageSupport.writer().writeByte(3);
            messageSupport.writer().writeShort(i);
            this.session.sendMessage(messageSupport);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestAddFriend(int i, String str, byte b, short s) {
        init((byte) 83);
        writeByte(1);
        if (i == Player.myCharz().charID) {
            return;
        }
        writeInt(i);
        writeUTF(str);
        writeByte(b);
        writeShort(s);
        send();
    }

    public void requestAddSkill(int i, int i2) {
        init((byte) -30);
        writeByte(-126);
        writeByte(i);
        writeShort(i2);
        send();
    }

    public void requestAtlasData(int i) {
        if (this.isSendAtlas[i]) {
            return;
        }
        init((byte) -28);
        writeByte(-99);
        writeByte(i);
        send();
        Res.outz("REQUEST ATLAS:" + i);
        this.isSendAtlas[i] = true;
    }

    public void requestChangeMap() {
    }

    public void requestChangeMapDone() {
        MessageSupport messageSupport = new MessageSupport((byte) -18);
        this.session.sendMessage(messageSupport);
        Res.outz(">>>>>>>>>>>>>>>>>Gửi map doneeeeeee cho server<<<<<<<<<<<<");
        NinjaMidlet.isSendClientOK = false;
        CanvasNinja.timeDelayLoadingMap = mSystem.currentTimeMillis() + 500;
        messageSupport.cleanup();
    }

    public void requestCharInfo(int i, boolean z) {
        Res.outz("gửi request CharInfo");
        init((byte) -24);
        writeInt(i);
        writeBoolean(z);
        send();
    }

    public void requestConfirmIndexButton(int i) {
        init((byte) -26);
        writeByte(i);
        send();
    }

    public void requestCreateChar() {
        initNotMap((byte) 125);
        send();
    }

    public void requestDoneMiniGame() {
        MessageSupport messageSupport = new MessageSupport((byte) 38);
        try {
            messageSupport.writer().writeByte(5);
            this.session.sendMessage(messageSupport);
        } catch (Exception e) {
        }
    }

    public void requestIndexRewardItemGachaGem() {
        init((byte) 8);
        writeByte(1);
        writeByte(1);
        send();
    }

    public void requestIndexRewardItemGachaRyo() {
        init((byte) 8);
        writeByte(0);
        writeByte(1);
        send();
    }

    public void requestJump() {
        MessageSupport messageSupport = new MessageSupport((byte) 4);
        try {
            this.session.sendMessage(messageSupport);
        } catch (Exception e) {
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
        messageSupport.cleanup();
    }

    public void requestListItemGachaGem() {
        init((byte) 8);
        writeByte(1);
        writeByte(0);
        send();
    }

    public void requestListItemGachaRyo() {
        init((byte) 8);
        writeByte(0);
        writeByte(0);
        send();
    }

    public void requestListPurchase() {
        init((byte) 22);
        writeByte(0);
        send();
    }

    public void requestMaptemplate(int i) {
        Res.outz("=========> important requestMaptemplate");
        try {
            int[] iArr = CanvasNinja.countRequestLoadingMap;
            iArr[3] = iArr[3] + 1;
            MessageSupport messageNotMap = messageNotMap((byte) -109);
            messageNotMap.writer().writeByte(i);
            this.session.sendMessage(messageNotMap);
            messageNotMap.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestMenuPvP(int i, int i2) {
        Res.outz("gửi Menu PVP:" + i + "_index:" + i2);
        init((byte) 39);
        writeInt(i);
        writeByte(i2);
        if (i2 != 2) {
            send();
            return;
        }
        Res.outz("Nút mở UI thông tin nhân vật");
        requestCharInfo(i, true);
    }

    public void requestNextScreen(byte b, byte b2) {
        MessageSupport messageSupport = new MessageSupport((byte) 38);
        try {
            messageSupport.writer().writeByte(4);
            messageSupport.writer().writeByte(b);
            messageSupport.writer().writeByte(b2);
            this.session.sendMessage(messageSupport);
            Res.outz("Gửi request màn hình tiếp theo");
        } catch (Exception e) {
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
        messageSupport.cleanup();
    }

    public void requestOpenUIQuestAll() {
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageSupport3 = new MessageSupport((byte) 47);
                messageSupport3.writer().writeByte(0);
                messageSupport = messageSupport3;
                messageSupport2 = messageSupport3;
                this.session.sendMessage(messageSupport3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            messageSupport2.cleanup();
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
    }

    public void requestPremiumAuto() {
        init((byte) 7);
        writeByte(3);
        send();
    }

    public void requestPurchase(byte b, byte b2) {
        init((byte) 22);
        writeByte(b);
        writeByte(b2);
        send();
    }

    public void requestRevive(byte b) {
        Res.outz("Yêu cầu hồi sinh:" + ((int) b));
        init((byte) -30);
        writeByte(-110);
        writeByte(b);
        send();
    }

    public void requestSkillPoint() {
        init((byte) -30);
        writeByte(-128);
        send();
    }

    public void requestTabLearnSKill() {
        init((byte) 9);
        writeByte(0);
        send();
    }

    public void requestTabUpgrade(byte b) {
        init((byte) 14);
        writeByte(b);
        send();
    }

    public void requestTalkToEndNpc(short s, byte b, byte b2, byte b3) {
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageSupport3 = new MessageSupport((byte) 38);
                messageSupport3.writer().writeByte(b2);
                switch (b2) {
                    case 3:
                        Res.outz("gửi end NPC nói");
                        messageSupport3.writer().writeShort(s);
                        messageSupport3.writer().writeByte(b);
                        messageSupport3.writer().writeByte(b3);
                        break;
                }
                messageSupport = messageSupport3;
                messageSupport2 = messageSupport3;
                this.session.sendMessage(messageSupport3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            messageSupport2.cleanup();
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
    }

    public void requestTalkToNPC(int i, byte b) {
        Res.outz(3, "nc với NPC ID:" + i);
        init((byte) 38);
        try {
            Res.outz("talk to NPC:" + i);
            Res.outz("talk to sub:" + ((int) b));
            writeByte(b);
            writeShort(i);
        } catch (Exception e) {
        }
        send();
    }

    public void requestTele(short s, short s2) {
        init((byte) 7);
        writeByte(2);
        writeShort(s);
        writeShort(s2);
        send();
    }

    public void requestUpgradeSkill(int i) {
        init((byte) -30);
        writeByte(-127);
        writeShort(i);
        send();
    }

    public void requestUpgradeTN(int[] iArr) {
        init((byte) -30);
        writeByte(-109);
        writeByte(1);
        writeByte(5);
        for (int i = 0; i < 5; i++) {
            writeByte(TabInventory.gI().optionsInfos[0].itemOption[i].optionTemplate.id);
            switch (i) {
                case 0:
                    writeShort(iArr[i] - Player.myCharz().charInfo.pSTR);
                    break;
                case 1:
                    writeShort(iArr[i] - Player.myCharz().charInfo.pAGI);
                    break;
                case 2:
                    writeShort(iArr[i] - Player.myCharz().charInfo.pDEF);
                    break;
                case 3:
                    writeShort(iArr[i] - Player.myCharz().charInfo.pHP);
                    break;
                case 4:
                    writeShort(iArr[i] - Player.myCharz().charInfo.pMP);
                    break;
            }
        }
        send();
    }

    public void requestWorldMap(short s) {
        init((byte) -15);
        writeShort(s);
        send();
    }

    public void requestZone(int i, int i2) {
        init((byte) 20);
        writeByte(i);
        if (i == 1) {
            writeByte(i2);
        }
        send();
    }

    public void restoreItem(int i) {
        MessageSupport messageSupport = new MessageSupport((byte) 23);
        try {
            messageSupport.writer().writeByte(4);
            messageSupport.writer().writeShort(i);
            this.session.sendMessage(messageSupport);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resultNap(int i, String str, String str2, String str3) {
        init((byte) 22);
        writeByte(1);
        writeShort(i);
        writeUTF(str);
        writeUTF(str2);
        writeUTF(str3);
        send();
    }

    public void selectChar(int i) {
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -126);
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                messageNotMap.writer().writeByte(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            messageSupport.cleanup();
            this.session.sendMessage(messageSupport);
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void send() {
        byte[] data = this.m.getData();
        MessageSupport messageSupport = this.m;
        byte b = messageSupport.command;
        int length = data.length;
        if (length <= 255) {
            this.session.sendMessage(messageSupport);
            this.m.cleanup();
            return;
        }
        try {
            int length2 = data.length % 249 == 0 ? data.length / 249 : (data.length / 249) + 1;
            for (int i = 0; i < length2; i++) {
                MessageSupport messageSupport2 = new MessageSupport(-32);
                this.m = messageSupport2;
                messageSupport2.writer().writeByte(b);
                this.m.writer().writeInt(length);
                byte[] bArr = new byte[249];
                if (i == length2 - 1) {
                    bArr = new byte[length - (i * 249)];
                }
                for (int i2 = 0; i2 < bArr.length; i2++) {
                    this.m.writer().writeByte(data[(i * 249) + i2]);
                }
                Res.outz("Send messege nhỏ");
                this.session.sendMessage(this.m);
                this.m.cleanup();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCheckController() {
        MessageSupport messageSupport;
        MessageSupport messageSupport2 = null;
        MessageSupport messageSupport3 = null;
        try {
            messageSupport = new MessageSupport((byte) -120);
            messageSupport2 = messageSupport;
            messageSupport3 = messageSupport;
            this.session.sendMessage(messageSupport);
        } catch (Exception e) {
            messageSupport = messageSupport3;
        } catch (Throwable th) {
            curCheckController = mSystem.currentTimeMillis();
            messageSupport2.cleanup();
            throw th;
        }
        curCheckController = mSystem.currentTimeMillis();
        messageSupport.cleanup();
    }

    public void sendCheckMap() {
        MessageSupport messageSupport;
        MessageSupport messageSupport2 = null;
        MessageSupport messageSupport3 = null;
        try {
            messageSupport = new MessageSupport((byte) -121);
            messageSupport2 = messageSupport;
            messageSupport3 = messageSupport;
            this.session.sendMessage(messageSupport);
        } catch (Exception e) {
            messageSupport = messageSupport3;
        } catch (Throwable th) {
            curCheckMap = mSystem.currentTimeMillis();
            messageSupport2.cleanup();
            throw th;
        }
        curCheckMap = mSystem.currentTimeMillis();
        messageSupport.cleanup();
    }

    public void sendClientInfo() {
        Res.outz("send client info");
        init((byte) -29);
        writeByte(-125);
        writeByte(NinjaMidlet.getTypeDive());
        writeUTF(NinjaMidlet.getDeviceName());
        writeByte(NinjaMidlet.getLanguage());
        writeInt(NinjaMidlet.getProvider());
        writeUTF(NinjaMidlet.getAgentCode());
        send();
    }

    public boolean sendMove() {
        if (mSystem.currentTimeMillis() < timeSendMove && Player.myCharz().cactFirst == Player.myCharz().statusMe && Player.myCharz().cdirSend == ((Base) Player.myCharz()).cdir) {
            return false;
        }
        int i = Player.myCharz().cx - Player.myCharz().cxSend;
        int i2 = Player.myCharz().cy - Player.myCharz().cySend;
        if (i == 0 && i2 == 0) {
            Player.myCharz().isRun = false;
            return true;
        }
        Player.myCharz().isRun = true;
        MessageSupport messageSupport = new MessageSupport((byte) 1);
        try {
            try {
                Player.myCharz().cxSend = Player.myCharz().cx;
                Player.myCharz().cySend = Player.myCharz().cy;
                Player.myCharz().cdirSend = ((Base) Player.myCharz()).cdir;
                Player.myCharz().cactFirst = Player.myCharz().statusMe;
                messageSupport.writer().writeInt(Player.myCharz().cx);
                messageSupport.writer().writeInt(Player.myCharz().cy);
                messageSupport.writer().writeByte(((Base) Player.myCharz()).cdir);
                this.session.sendMessage(messageSupport);
                if (i < 50) {
                    Res.outz("_xxxxxxxxx:" + Player.myCharz().cx + "Khoảng cách dx:" + i + "_dy:" + i2 + "_timeSend:" + (mSystem.currentTimeMillis() - lastTimeSend) + "_y:" + Player.myCharz().cy + "_action:" + ((int) Player.myCharz().getAction()));
                } else {
                    Res.outz(1, "_xxxxxxxxx:" + Player.myCharz().cx + "Khoảng cách dx:" + i + "_dy:" + i2 + "_timeSend:" + (mSystem.currentTimeMillis() - lastTimeSend) + "_y:" + Player.myCharz().cy + "_action:" + ((int) Player.myCharz().getAction()));
                }
                timeSendMove = mSystem.currentTimeMillis() + 250;
                lastTimeSend = mSystem.currentTimeMillis();
                MapScr.tickMove++;
                messageSupport.cleanup();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                messageSupport.cleanup();
                return false;
            }
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
    }

    public void sendMoveNew() {
        MessageSupport messageSupport = new MessageSupport((byte) 5);
        try {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            if ((Player.myCharz().cactFirstNew != 0 || Player.myCharz().statusMe != 0) && ((Player.myCharz().cactFirstNew != 11 || Player.myCharz().statusMe != 11) && Player.myCharz().statusMe != 4 && Player.myCharz().statusMe != 37 && Player.myCharz().statusMe != 6 && Player.myCharz().statusMe != 20 && Player.myCharz().statusMe != 10 && Player.myCharz().statusMe != 14 && Player.myCharz().statusMe != 17 && Player.myCharz().statusMe != 35)) {
                if ((Player.myCharz().cactFirstNew != Player.myCharz().statusMe || lastTimeSendNew > mSystem.currentTimeMillis()) && Player.myCharz().cactFirstNew == Player.myCharz().statusMe && Player.myCharz().subActionSend == Player.myCharz().getSubAction() && Player.myCharz().cdirSendNew == ((Base) Player.myCharz()).cdir && Player.myCharz().statusMe != 7 && Player.myCharz().statusMe != 9) {
                    return;
                }
                messageSupport.writer().writeInt(Player.myCharz().cx);
                messageSupport.writer().writeInt(Player.myCharz().cy);
                messageSupport.writer().writeByte(Player.myCharz().cdir);
                byte action = Player.myCharz().getAction();
                if (Player.myCharz().cdirSendNew == Player.myCharz().cdir) {
                    action = Player.myCharz().getAction();
                } else if (Player.myCharz().cactFirstNew == 1) {
                    action = 1;
                }
                messageSupport.writer().writeByte(action);
                messageSupport.writer().writeByte(Player.myCharz().getSubAction());
                Player.myCharz().cactFirstNew = Player.myCharz().statusMe;
                Player.myCharz().cdirSendNew = Player.myCharz().cdir;
                Player.myCharz().subActionSend = Player.myCharz().getSubAction();
                Player.myCharz().cxSendNew = Player.myCharz().cx;
                Player.myCharz().cySendNew = Player.myCharz().cy;
                this.session.sendMessage(messageSupport);
                lastTimeSendNew = mSystem.currentTimeMillis() + 250;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            messageSupport.cleanup();
        }
    }

    public void sendOTP(int i, String str) {
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageSupport3 = new MessageSupport((byte) 19);
                messageSupport3.writer().writeByte(i);
                messageSupport3.writer().writeUTF(str);
                messageSupport = messageSupport3;
                messageSupport2 = messageSupport3;
                this.session.sendMessage(messageSupport3);
                messageSupport2 = messageSupport3;
            } catch (Exception e) {
                messageSupport = messageSupport2;
                e.printStackTrace();
            }
            messageSupport2.cleanup();
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
    }

    public void sendPK(int i) {
        Res.outz("gửi request PK:" + i);
        init((byte) -30);
        writeByte(-93);
        writeByte(i);
        send();
    }

    public void sendPlayerAttack(VectorCustom vectorCustom, VectorCustom vectorCustom2, int i) {
        MessageSupport messageSupport;
        MessageSupport messageSupport2 = null;
        MessageSupport messageSupport3 = null;
        MessageSupport messageSupport4 = null;
        try {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("SEND ATTACT vChar=  ");
                sb.append(vectorCustom2.size());
                sb.append("SEND ATTACT vMob:");
                sb.append(vectorCustom.size());
                sb.append("_id skill template:");
                sb.append(i);
                Res.outz(3, sb.toString());
                if (vectorCustom.size() > 0) {
                    messageSupport2 = new MessageSupport((byte) 60);
                    messageSupport2.writer().writeShort(i);
                    messageSupport2.writer().writeShort(vectorCustom.size());
                    for (int i2 = 0; i2 < vectorCustom.size(); i2++) {
                        messageSupport2.writer().writeShort(((Base) ((Quai) vectorCustom.elementAt(i2))).mobId);
                    }
                    this.session.sendMessage(messageSupport2);
                }
                messageSupport = messageSupport2;
                if (vectorCustom2.size() > 0) {
                    MessageSupport messageSupport5 = messageSupport2;
                    MessageSupport messageSupport6 = messageSupport2;
                    messageSupport = new MessageSupport((byte) 61);
                    messageSupport.writer().writeShort(i);
                    messageSupport.writer().writeShort(vectorCustom2.size());
                    for (int i3 = 0; i3 < vectorCustom2.size(); i3++) {
                        messageSupport.writer().writeInt(((Player) vectorCustom2.elementAt(i3)).charID);
                    }
                    messageSupport3 = messageSupport;
                    messageSupport4 = messageSupport;
                    this.session.sendMessage(messageSupport);
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (messageSupport4 == null) {
                    return;
                }
                messageSupport = messageSupport4;
            }
        } finally {
            if (messageSupport3 != null) {
                messageSupport3.cleanup();
            }
        }
    }

    public void sendYesNoInput(int i, boolean z) {
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        MessageSupport messageSupport3 = null;
        try {
            try {
                if (TabShopThoRen.gI().idItemSelected != -1) {
                    messageSupport = new MessageSupport((byte) 19);
                    messageSupport.writer().writeByte(i);
                    messageSupport.writer().writeShort(TabShopThoRen.gI().idItemSelected);
                    messageSupport.writer().writeBoolean(z);
                    messageSupport2 = messageSupport;
                    messageSupport3 = messageSupport;
                    this.session.sendMessage(messageSupport);
                }
            } catch (Exception e) {
                messageSupport2 = messageSupport3;
                e.printStackTrace();
                messageSupport = messageSupport3;
            }
        } finally {
            messageSupport2.cleanup();
        }
    }

    public void setModeParty(int i) {
        init((byte) 82);
        writeByte(5);
        Res.outz("send mode sv:" + i);
        writeByte(i);
        send();
    }

    public void setPassWareHouse(String str) {
        MessageSupport messageSupport = new MessageSupport((byte) 15);
        try {
            messageSupport.writer().writeByte(6);
            messageSupport.writer().writeUTF(str);
            this.session.sendMessage(messageSupport);
        } catch (Exception e) {
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
        messageSupport.cleanup();
    }

    public void teleportParty(int i) {
        init((byte) 82);
        writeByte(6);
        writeByte(i);
        send();
    }

    public void turnOffAuto() {
        init((byte) 7);
        writeByte(1);
        writeBoolean(false);
        send();
    }

    public void turnOnAuto() {
        init((byte) 7);
        writeByte(1);
        writeBoolean(true);
        send();
    }

    public void updateData() {
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -122);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                this.session = MySession.gI();
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void updateEffectFrame() {
        if (LoadVersionManager.doCheckSendRequest(7)) {
            return;
        }
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -110);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                this.session = MySession.gI();
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                LoadVersionManager.updateCount(7);
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void updateEffectSkill() {
        if (LoadVersionManager.doCheckSendRequest(6)) {
            return;
        }
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -111);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                this.session = MySession.gI();
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                LoadVersionManager.updateCount(6);
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void updateImageWorldMap() {
        if (LoadVersionManager.doCheckSendRequest(10)) {
            return;
        }
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -98);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                this.session = MySession.gI();
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                LoadVersionManager.updateCount(10);
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void updateItem() {
        if (this.isSendUpdateItem) {
            return;
        }
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -119);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                this.session = MySession.gI();
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                this.isSendUpdateItem = true;
                messageSupport = messageNotMap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void updateListInviteParty() {
        init((byte) 82);
        writeByte(7);
        send();
    }

    public void updateListParty() {
        init((byte) 82);
        writeByte(0);
        send();
    }

    public void updateMap() {
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -121);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                this.session = MySession.gI();
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void updateObjMapFrame() {
        if (LoadVersionManager.doCheckSendRequest(0)) {
            return;
        }
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -113);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                this.session = MySession.gI();
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                LoadVersionManager.updateCount(0);
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void updatePart() {
        if (LoadVersionManager.doCheckSendRequest(5)) {
            return;
        }
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -112);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                this.session = MySession.gI();
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                LoadVersionManager.updateCount(5);
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void updateResoucesBySub(byte b, int i) {
        if (LoadVersionManager.doCheckSendRequest(i)) {
            return;
        }
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap(b);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                this.session = MySession.gI();
                LoadVersionManager.updateCount(i);
                StringBuilder sb = new StringBuilder();
                sb.append("SEND SUB NOT MAP:");
                sb.append((int) b);
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                Res.outz(sb.toString());
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void updateSkill() {
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -120);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                this.session = MySession.gI();
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void updateStatusEffectBuff() {
        updateResoucesBySub((byte) -106, 10);
    }

    public void updateTileFrame() {
        if (LoadVersionManager.doCheckSendRequest(1)) {
            return;
        }
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -114);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                this.session = MySession.gI();
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                LoadVersionManager.updateCount(1);
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void updateWeapon() {
        if (LoadVersionManager.doCheckSendRequest(8)) {
            return;
        }
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -108);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                this.session = MySession.gI();
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                LoadVersionManager.updateCount(8);
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void updateWeaponSplash() {
        if (LoadVersionManager.doCheckSendRequest(9)) {
            return;
        }
        MessageSupport messageSupport = null;
        MessageSupport messageSupport2 = null;
        try {
            try {
                MessageSupport messageNotMap = messageNotMap((byte) -107);
                if (!MySession.gI2().isConnected() || MySession.gI2().connecting) {
                    this.session = MySession.gI();
                } else {
                    this.session = MySession.gI2();
                }
                this.session.sendMessage(messageNotMap);
                this.session = MySession.gI();
                messageSupport2 = messageNotMap;
                messageSupport = messageNotMap;
                LoadVersionManager.updateCount(9);
                messageSupport = messageNotMap;
            } catch (Exception e) {
                messageSupport2 = messageSupport;
                e.printStackTrace();
            }
            messageSupport.cleanup();
        } catch (Throwable th) {
            messageSupport2.cleanup();
            throw th;
        }
    }

    public void upgradeItem(byte b, int i) {
        init((byte) 14);
        writeByte(b);
        writeShort(i);
        Res.outz("Gửi upgrade sub:" + ((int) b) + "_index:" + i);
        send();
    }

    public void useDrain(byte b, boolean z) {
        init((byte) 59);
        writeShort(66);
        send();
    }

    public void useItemInventory(byte b, int i) {
        MessageSupport messageSupport = new MessageSupport((byte) 15);
        try {
            messageSupport.writer().writeByte(5);
            messageSupport.writer().writeByte(b);
            messageSupport.writer().writeShort(i);
            this.session.sendMessage(messageSupport);
        } catch (Exception e) {
        } catch (Throwable th) {
            messageSupport.cleanup();
            throw th;
        }
        messageSupport.cleanup();
    }

    protected void writeBoolean(boolean z) {
        try {
            this.m.writer().writeBoolean(z);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeByte(int i) {
        try {
            this.m.writer().writeByte(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeInt(int i) {
        try {
            this.m.writer().writeInt(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writeShort(int i) {
        try {
            this.m.writer().writeShort(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeUTF(String str) {
        try {
            this.m.writer().writeUTF(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
