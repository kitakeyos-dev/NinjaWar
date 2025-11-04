package ninjawar.model;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import ninjawar.message.SendMessage;
import ninjawar.supporter.LoadVersionManager;
import ninjawar.template.MonsTemplates;

public class MonsTemplate {
    public int[][] FRAME;
    public int dx;
    public int dy;
    public short[] idParts;
    public short idTemplate;
    public byte type;
    public byte typeBeforeAfter;

    public MonsTemplate(int idTemplate2, short[] idParts2, int[][] FRAME2, int type2, int typeBeforeAfter2, int dx2, int dy2) {
        this.idTemplate = (short) idTemplate2;
        this.idParts = idParts2;
        this.FRAME = FRAME2;
        this.type = (byte) type2;
        this.typeBeforeAfter = (byte) typeBeforeAfter2;
        this.dx = (byte) dx2;
        this.dy = (byte) dy2;
    }

    public static void readDataTemplateFromBytes(int idTemplate2, byte[] data) {
        try {
            DataInputStream d = new DataInputStream(new ByteArrayInputStream(data));
            int sizePart = d.readByte();
            short[] idParts2 = new short[sizePart];
            for (int i = 0; i < sizePart; i++) {
                idParts2[i] = d.readShort();
            }
            int sizeFrame = d.readByte();
            int[][] FRAME2 = new int[sizeFrame][];
            for (int i2 = 0; i2 < sizeFrame; i2++) {
                int sizeOneAction = d.readByte();
                FRAME2[i2] = new int[sizeOneAction];
                for (int k = 0; k < sizeOneAction; k++) {
                    FRAME2[i2][k] = d.readByte();
                }
            }
            MonsTemplates.add(new MonsTemplate(idTemplate2, idParts2, FRAME2, d.readByte(), d.readByte(), d.readByte(), d.readByte()));
        } catch (Exception e) {
            LoadVersionManager.isUpdateItem = false;
            SendMessage.gI().updateItem();
            e.printStackTrace();
        }
    }
}
