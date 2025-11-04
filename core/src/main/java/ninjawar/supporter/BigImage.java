package ninjawar.supporter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import ninjawar.mylib.FileData;
import ninjawar.mylib.Image;
import ninjawar.mylib.mBytes;
import ninjawar.mylib.mGraphics;
import ninjawar.myscr.Res;
import ninjawar.small.FrameEff;
import ninjawar.small.mPartFrame;
import ninjawar.small.mySImg;

public class BigImage {
    int countCreateData = 0;
    public byte[] dataBig;
    FrameEff frameBot;
    public byte[][] frameChar = new byte[4][];
    FrameEff frameTop;
    public short idBig;
    public short[] idFolder;
    public Image imgBig;
    Image imgTemp;
    public int indexBig;
    public int indexSmallBot = -1;
    public int indexSmallTop = -1;
    public byte indexStartSkill;
    public boolean isLoadData;
    public boolean isOverSize;
    public boolean isWaitingData = false;
    public boolean isWaitingImg = false;
    public Vector<FrameEff> listFrame;
    Vector<mPartFrame> listPartBot;
    Vector<mPartFrame> listPartTop;
    mPartFrame partBot;
    mPartFrame partTop;
    public byte[] sequence;
    mySImg smallBot;
    public mySImg[] smallImage;
    mySImg smallTop;
    public byte typeBig;

    public void readDataFromTool(short idBig2, byte typeBig2, int indexBig2) throws IOException {
        this.idBig = idBig2;
        this.typeBig = typeBig2;
        this.indexBig = indexBig2;
        createDataBig();
    }

    public void loadData() throws IOException {
        this.listFrame = new Vector<>();
        byte[] bArr = this.dataBig;
        if (bArr != null && bArr.length > 0) {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(this.dataBig));
            int nSmallImage = dis.readByte();
            this.smallImage = new mySImg[nSmallImage];
            this.imgBig.tRegions = new TextureRegion[nSmallImage];
            for (int i = 0; i < nSmallImage; i++) {
                if (!this.isOverSize) {
                    this.smallImage[i] = new mySImg(dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte());
                } else {
                    this.smallImage[i] = new mySImg(dis.readShort(), dis.readShort(), dis.readShort(), dis.readShort(), dis.readShort());
                }
                Image image = this.imgBig;
                TextureRegion[] textureRegionArr = image.tRegions;
                Texture texture = image.texture;
                mySImg mysimg = this.smallImage[i];
                textureRegionArr[i] = new TextureRegion(texture, (int) mysimg.x, (int) mysimg.y, (int) mysimg.w, (int) mysimg.h);
                this.imgBig.tRegions[i].flip(false, true);
            }
            int nframe = dis.readShort();
            for (int i2 = 0; i2 < nframe; i2++) {
                byte npart = dis.readByte();
                Vector<mPartFrame> listParttop = new Vector<>();
                Vector<mPartFrame> listPartbottom = new Vector<>();
                for (int j = 0; j < npart; j++) {
                    mPartFrame part = new mPartFrame(dis.readShort(), dis.readShort(), dis.readByte());
                    Res.outz("PART i vi tri: " + part.dx + " " + part.dy);
                    part.imgBig = this.imgBig;
                    part.flip = dis.readByte();
                    part.onTop = dis.readByte();
                    part.typePart = (byte) this.idBig;
                    part.index = getIndexSmallImageById((short) part.idSmallImg);
                    if (part.onTop == 0) {
                        listParttop.addElement(part);
                    } else {
                        listPartbottom.addElement(part);
                    }
                }
                this.listFrame.addElement(new FrameEff(listParttop, listPartbottom));
            }
            int len = (short) dis.readUnsignedByte();
            this.sequence = new byte[len];
            for (int i3 = 0; i3 < len; i3++) {
                this.sequence[i3] = (byte) dis.readShort();
            }
            int i4 = this.idBig;
            this.indexStartSkill = dis.readByte();
            int len2 = (short) dis.readByte();
            this.frameChar[0] = new byte[len2];
            for (int i5 = 0; i5 < len2; i5++) {
                this.frameChar[0][i5] = dis.readByte();
            }
            int len3 = (short) dis.readByte();
            this.frameChar[1] = new byte[len3];
            for (int i6 = 0; i6 < len3; i6++) {
                this.frameChar[1][i6] = dis.readByte();
            }
            int len4 = (short) dis.readByte();
            this.frameChar[3] = new byte[len4];
            for (int i7 = 0; i7 < len4; i7++) {
                this.frameChar[3][i7] = dis.readByte();
            }
            this.isLoadData = true;
        }
        this.listFrame.size();
    }

    public void paint(mGraphics g, int x, int y, int frame, byte direct, byte subDirect, int opacityPercent, boolean isOpacity, boolean useClip) {
        paint(g, x, y, frame, direct, subDirect, opacityPercent, isOpacity, useClip, false);
    }

    public void paint(mGraphics g, int x, int y, int frame, byte direct, byte subDirect, int opacityPercent, boolean isOpacity, boolean useClip, boolean isSharigan) {
        paintBottom(g, x, y, frame, direct, subDirect, isOpacity ? opacityPercent : 0, useClip, isSharigan);
        paintTop(g, x, y, frame, direct, subDirect, isOpacity ? opacityPercent : 0, useClip, isSharigan);
    }

    public int getIndexSmallImageById(short id) {
        int i = 0;
        for (mySImg si : this.smallImage) {
            if (si.id == id) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void paintTop(mGraphics g, int x, int y, int mFrame, int rotale, int subRotale, int percentOpacity, boolean useClip, boolean isBlack) {
        int rota;
        int rota2;
        BigImage bigImage = this;
        int i = mFrame;
        int i2 = rotale;
        int i3 = subRotale;
        Vector<FrameEff> vector = bigImage.listFrame;
        if (vector != null && i < vector.size()) {
            FrameEff elementAt = bigImage.listFrame.elementAt(i);
            bigImage.frameTop = elementAt;
            try {
                bigImage.listPartTop = elementAt.listPartTop;
                int i4 = 0;
                while (i4 < bigImage.listPartTop.size()) {
                    mPartFrame elementAt2 = bigImage.listPartTop.elementAt(i4);
                    bigImage.partTop = elementAt2;
                    byte b = elementAt2.idSmallImg;
                    mySImg[] mysimgArr = bigImage.smallImage;
                    if (b < mysimgArr.length) {
                        if (b >= 0) {
                            mySImg mysimg = mysimgArr[b];
                            bigImage.smallTop = mysimg;
                            Image image = bigImage.imgBig;
                            if (image != null) {
                                int dx = elementAt2.dx;
                                int dy = elementAt2.dy;
                                int w = mysimg.w;
                                int h = mysimg.h;
                                int xx = mysimg.x;
                                int yy = mysimg.y;
                                int wImg = image.getRWidth();
                                int hImg = bigImage.imgBig.getRHeight();
                                if (xx > wImg) {
                                    xx = 0;
                                }
                                if (yy > hImg) {
                                    yy = 0;
                                }
                                if (xx + w > wImg) {
                                    w = wImg - xx;
                                }
                                if (yy + h > hImg) {
                                    h = hImg - yy;
                                }
                                mPartFrame mpartframe = bigImage.partTop;
                                int rota3 = mpartframe.flip == 1 ? 2 : 0;
                                if (i3 == -1 && (i2 == 2 || i2 == 6)) {
                                    if (rota3 == 2) {
                                        rota2 = 0;
                                    } else {
                                        rota2 = 2;
                                    }
                                    dx = -(dx + w);
                                }
                                if (i3 == 5) {
                                    rota3 = 5;
                                    dy = -(mpartframe.dx + w);
                                    dx = -(mpartframe.dy + h);
                                }
                                if (i3 == 7) {
                                    rota3 = 7;
                                    dy = mpartframe.dx;
                                    dx = -(mpartframe.dy + h);
                                }
                                if (i3 == 4) {
                                    rota3 = 4;
                                    dy = -(mpartframe.dx + (w * 2));
                                    dx = mpartframe.dy;
                                }
                                if (i3 == 6) {
                                    dy = mpartframe.dx + w;
                                    dx = mpartframe.dy - h;
                                    rota = 6;
                                } else {
                                    rota = rota3;
                                }
                                int i5 = rota;
                                g.drawRegion(bigImage.imgBig, (float) xx, (float) yy, w, h, i3 == -1 ? rota : i3, (float) (x + dx), (float) (y + dy), 0, useClip, percentOpacity > 0, percentOpacity, isBlack);
                            }
                        }
                    }
                    i4++;
                    bigImage = this;
                    int i6 = mFrame;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottom(mGraphics g, int x, int y, int mFrame, int rotale, int subRotale, int percentOpacity, boolean useClip, boolean isBlack) {
        int rota;
        int rota2;
        BigImage bigImage = this;
        int i = mFrame;
        int i2 = rotale;
        int i3 = subRotale;
        Vector<FrameEff> vector = bigImage.listFrame;
        if (vector != null && i < vector.size()) {
            FrameEff elementAt = bigImage.listFrame.elementAt(i);
            bigImage.frameBot = elementAt;
            try {
                bigImage.listPartBot = elementAt.listPartBottom;
                int i4 = 0;
                while (i4 < bigImage.listPartBot.size()) {
                    mPartFrame elementAt2 = bigImage.listPartBot.elementAt(i4);
                    bigImage.partBot = elementAt2;
                    byte b = elementAt2.idSmallImg;
                    mySImg[] mysimgArr = bigImage.smallImage;
                    if (b < mysimgArr.length) {
                        if (b >= 0) {
                            mySImg mysimg = mysimgArr[b];
                            bigImage.smallBot = mysimg;
                            Image image = bigImage.imgBig;
                            if (image != null) {
                                int dx = elementAt2.dx;
                                int dy = elementAt2.dy;
                                int w = mysimg.w;
                                int h = mysimg.h;
                                int xx = mysimg.x;
                                int yy = mysimg.y;
                                int wImg = image.getRWidth();
                                int hImg = bigImage.imgBig.getRHeight();
                                if (xx > wImg) {
                                    xx = 0;
                                }
                                if (yy > hImg) {
                                    yy = 0;
                                }
                                if (xx + w > wImg) {
                                    w = wImg - xx;
                                }
                                if (yy + h > hImg) {
                                    h = hImg - yy;
                                }
                                mPartFrame mpartframe = bigImage.partBot;
                                int rota3 = mpartframe.flip == 1 ? 2 : 0;
                                if (i3 == -1 && (i2 == 2 || i2 == 6)) {
                                    if (rota3 == 2) {
                                        rota2 = 0;
                                    } else {
                                        rota2 = 2;
                                    }
                                    dx = -(dx + w);
                                }
                                if (i3 == 5) {
                                    rota3 = 5;
                                    dy = -(mpartframe.dx + w);
                                    dx = -(mpartframe.dy + h);
                                }
                                if (i3 == 7) {
                                    rota3 = 7;
                                    dy = mpartframe.dx;
                                    dx = -(mpartframe.dy + h);
                                }
                                if (i3 == 4) {
                                    rota3 = 4;
                                    dy = -(mpartframe.dx + (w * 2));
                                    dx = mpartframe.dy;
                                }
                                if (i3 == 6) {
                                    dy = mpartframe.dx + w;
                                    dx = mpartframe.dy - h;
                                    rota = 6;
                                } else {
                                    rota = rota3;
                                }
                                int i5 = rota;
                                g.drawRegion(bigImage.imgBig, (float) xx, (float) yy, w, h, i3 == -1 ? rota : i3, (float) (x + dx), (float) (y + dy), 0, useClip, percentOpacity > 0, percentOpacity, isBlack);
                            }
                        }
                    }
                    i4++;
                    bigImage = this;
                    int i6 = mFrame;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getKey() {
        return this.typeBig + "" + this.idBig;
    }

    public void createBigAll() {
        if (this.idBig != Short.MIN_VALUE) {
            BigImageInfo bigImageInfo = BigImageInfos.get(getKey());
            Image createImage = bigImageInfo == null ? Image.createImage(this.idBig, this.idFolder[1]) : bigImageInfo.imgBig;
            this.imgTemp = createImage;
            boolean isDataDefault = false;
            if (createImage != null) {
                this.imgBig = createImage;
            } else {
                isDataDefault = true;
                if (this.indexBig == 0) {
                    this.imgBig = Image.createImage((short) -1, this.idFolder[1]);
                }
                if (this.indexBig == 1) {
                    this.imgBig = Image.createImage((short) -2, this.idFolder[1]);
                }
                if (this.indexBig == 5) {
                    this.imgBig = Image.createImage((short) -3, this.idFolder[1]);
                }
            }
            byte[] data = bigImageInfo == null ? mBytes.createData(this.idBig, this.idFolder[0]) : bigImageInfo.dataBig;
            if (data == null || isDataDefault) {
                if (this.indexBig == 0) {
                    this.dataBig = mBytes.createData((short) -1, this.idFolder[0]);
                }
                if (this.indexBig == 1) {
                    this.dataBig = mBytes.createData((short) -2, this.idFolder[0]);
                }
                if (this.indexBig == 5) {
                    this.dataBig = mBytes.createData((short) -3, this.idFolder[0]);
                }
            } else {
                this.dataBig = data;
            }
            if (!BigImageInfos.bigImgInfos.containsKey(getKey())) {
                BigImageInfos.add(new BigImageInfo(this.imgBig, this.dataBig, this.idBig, this.typeBig));
            }
        }
    }

    public void createDataBig() throws IOException {
        switch (this.typeBig) {
            case 0:
                this.idFolder = new short[]{FileData.TYPE_SMALL_DATA, FileData.TYPE_SMALL_IMG};
                break;
            case 1:
                this.idFolder = new short[]{FileData.TYPE_EFFECT_DATA, FileData.TYPE_EFFECT_IMG};
                break;
            case 2:
                this.idFolder = new short[]{FileData.TYPE_EFFECT_CHAR_DATA, FileData.TYPE_EFFECT_CHAR_IMG};
                break;
            case 3:
                this.idFolder = new short[]{FileData.TYPE_WEAPON_SPLASH_DATA, FileData.TYPE_WEAPON_SPLASH_IMG};
                break;
            case 4:
                this.idFolder = new short[]{FileData.TYPE_WEAPON_DATA, FileData.TYPE_WEAPON_IMG};
                break;
            case 5:
                this.idFolder = new short[]{FileData.TYPE_MAP_BACKGROUND_DATA, FileData.TYPE_MAP_BACKGROUND_IMG};
                break;
        }
        createBigAll();
        if (this.dataBig != null && this.imgBig != null && this.idBig != Short.MIN_VALUE) {
            loadData();
        }
    }
}
