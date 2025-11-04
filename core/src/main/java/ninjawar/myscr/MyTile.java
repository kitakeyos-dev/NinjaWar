// Class Version: 8
package ninjawar.myscr;

import ninjawar.mymain.CanvasNinja;
import ninjawar.model.PointChangeMap;
import ninjawar.mylib.mGraphics;
import java.io.DataInputStream;
import ninjawar.mylib.LibSysCustom;
import java.io.PrintStream;
import ninjawar.message.Config;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ninjawar.mylib.ObjMap;
import ninjawar.model.mPoint;
import ninjawar.mylib.VectorCustom;
import ninjawar.mylib.FrameImage;
import ninjawar.model.FrameImageEffect;
import ninjawar.mylib.Image;

public class MyTile
{
    public static int bgID;
    public static Image[] bgItem;
    public static int countx;
    public static int county;
    public static FrameImageEffect[] frameTileEffectMore;
    public static FrameImage[] frameTileEffectMores;
    public static int gssx;
    public static int gssxe;
    public static int gssy;
    public static int gssye;
    public static int[] highterId;
    public static Image[] imgTile;
    public static int indexFrameEff;
    public static boolean isMapDouble;
    public static int lastBgID;
    public static byte lastPlanetId;
    public static int lastTileID;
    public static int lastType;
    public static int mapID;
    public static String mapName;
    public static String[] mapNames;
    public static int[] maps;
    public static int numMaxFrameEff;
    public static int[] offlineId;
    public static int pxh;
    public static int pxw;
    public static Image shadow;
    public static Image shadowQuai;
    public static Image shadowQuai2;
    public static Image shadowQuai3;
    public static byte size;
    public static int sizeMiniMap;
    public static int tileID;
    public static int[][][] tileIndex;
    public static int[][] tileType;
    public static int tmh;
    public static int tmw;
    public static int[] toOfflineId;
    public static int typeMap;
    public static int[] types;
    public static VectorCustom vCurrItem;
    public static VectorCustom vGo;
    public static VectorCustom vItemBg;
    public static VectorCustom vObject;
    public static byte versionMap;
    public static int yWater;
    public static int zoneID;

    static {
        MyTile.tileID = 1;
        MyTile.lastTileID = -1;
        MyTile.size = 32;
        MyTile.isMapDouble = false;
        MyTile.mapName = "";
        MyTile.versionMap = 1;
        MyTile.lastBgID = -1;
        MyTile.lastType = -1;
        MyTile.lastPlanetId = -1;
        MyTile.vGo = new VectorCustom("vGO");
        MyTile.vItemBg = new VectorCustom("vItemBg");
        MyTile.vCurrItem = new VectorCustom("vCurrItem");
        MyTile.bgItem = new Image[8];
        MyTile.shadow = Image.createImage("/normal/object/bong.png");
        MyTile.shadowQuai = Image.createImage("/normal/object/bong_mob.png");
        MyTile.shadowQuai2 = Image.createImage("/normal/object/bong_mob_2.png");
        MyTile.shadowQuai3 = Image.createImage("/normal/object/bong_mob_3.png");
        MyTile.vObject = new VectorCustom("vObject");
        MyTile.offlineId = new int[] { 21, 22, 23, 39, 40, 41 };
        MyTile.highterId = new int[] { 21, 22, 23, 24, 25, 26 };
        MyTile.toOfflineId = new int[] { 0, 7, 14 };
        MyTile.sizeMiniMap = 2;
        MyTile.yWater = 0;
    }

    public static final mPoint centerPointAtPixel(int size, int tmw) {
        final int indexAtPixel = indexAtPixel(size, tmw);
        tmw = MyTile.tmw;
        size = MyTile.size;
        return new mPoint(indexAtPixel % tmw * size + size / 2, indexAtPixel / tmw * size + size / 2);
    }

    public static int findYTileTop(final int n, final int n2) {
        final byte size = MyTile.size;
        for (int tmh = MyTile.tmh, i = n2; i <= size * tmh; i += MyTile.size) {
            if (tileTypeAt(n, i, 2)) {
                return tileYofPixel(i);
            }
        }
        return n2;
    }

    public static ObjMap getBIById(final int n) {
        for (int i = 0; i < MyTile.vItemBg.size(); ++i) {
            final ObjMap objMap = (ObjMap)MyTile.vItemBg.elementAt(i);
            if (objMap.idImage == n) {
                return objMap;
            }
        }
        return null;
    }

    public static int getHMap() {
        return MyTile.size * MyTile.tmh;
    }

    public static int getHMapFromTileTopLast() {
        int n = -1;
        int size = MyTile.size;
        while (true) {
            final byte size2 = MyTile.size;
            if (size >= MyTile.tmw * size2) {
                break;
            }
            int n2;
            for (int i = size2 * MyTile.tmh; i >= 0; i -= MyTile.size, n = n2) {
                n2 = n;
                if (tileTypeAt(size, i, 2) && i > (n2 = n)) {
                    n2 = i;
                }
            }
            size += MyTile.size;
        }
        return n;
    }

    public static mPoint getPointAtIndex(final int n) {
        final int tmw = MyTile.tmw;
        final byte size = MyTile.size;
        return new mPoint(n % tmw * size + size / 2, n / tmw * size + size / 2);
    }

    public static void getTile() {
        (MyTile.imgTile = new Image[] { null })[0] = Image.createImage((short)MyTile.tileID, (short)9);
        final int n = MyTile.imgTile[0].getRWidth() / MyTile.size;
        final int n2 = MyTile.imgTile[0].getRHeight() / MyTile.size;
        MyTile.imgTile[0].tRegions = new TextureRegion[n * n2];
        for (byte b = 0; b < n2; ++b) {
            for (byte b2 = 0; b2 < n; ++b2) {
                final int n3 = b * n + b2;
                final StringBuilder sb = new StringBuilder();
                sb.append(n3);
                sb.append("_X:");
                sb.append(MyTile.size * b2);
                sb.append("_Y:");
                sb.append(MyTile.size * b);
                Res.outz(1, sb.toString());
                final Image image = MyTile.imgTile[0];
                final TextureRegion[] tRegions = image.tRegions;
                final Texture texture = image.texture;
                final byte size = MyTile.size;
                tRegions[n3] = new TextureRegion(texture, b2 * size, b * size, size, size);
                MyTile.imgTile[0].tRegions[n3].flip(false, true);
            }
        }
        final TextureRegion[] tRegions2 = MyTile.imgTile[0].tRegions;
        MyTile.frameTileEffectMore = new FrameImageEffect[tRegions2.length + 1];
        MyTile.frameTileEffectMores = new FrameImage[tRegions2.length + 1];
        MyTile.numMaxFrameEff = 5;
        if (MyTile.tileID == 3) {
            MyTile.numMaxFrameEff = 5;
        }
        for (int i = 0; i < MyTile.frameTileEffectMore.length; ++i) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("/tile/eff_tile/");
            sb2.append(MyTile.tileID);
            sb2.append("/");
            sb2.append(i);
            sb2.append(".png");
            final Image image2 = Image.createImage(sb2.toString());
            if (image2 != null) {
                MyTile.frameTileEffectMore[i] = new FrameImageEffect();
                MyTile.frameTileEffectMores[i] = new FrameImage(image2, MyTile.numMaxFrameEff);
                MyTile.frameTileEffectMore[i].frameImg = new FrameImage(image2, MyTile.numMaxFrameEff);
            }
        }
    }

    public static int getWMap() {
        return MyTile.size * MyTile.tmw;
    }

    public static final int indexAtPixel(final int n, final int n2) {
        final byte size = MyTile.size;
        return n2 / size * MyTile.tmw + n / size;
    }

    public static boolean isExistMoreOne(final int n) {
        if (n == 156 || n == 330 || n == 345 || n == 334) {
            return false;
        }
        final int mapID = MyTile.mapID;
        if (mapID != 54 && mapID != 55 && mapID != 56 && mapID != 57 && mapID != 58 && mapID != 59 && mapID != 103) {
            int n2 = 0;
            int n3;
            for (int i = 0; i < MyTile.vCurrItem.size(); ++i, n2 = n3) {
                n3 = n2;
                if (((ObjMap)MyTile.vCurrItem.elementAt(i)).id == n) {
                    n3 = n2 + 1;
                }
            }
            return n2 > 2;
        }
        return false;
    }

    public static boolean isTrainingMap() {
        return false;
    }

    public static final void killTileTypeAt(int n, final int n2, final int n3) {
        final int[] types = MyTile.types;
        final byte size = MyTile.size;
        n = n2 / size * MyTile.tmw + n / size;
        types[n] &= ~n3;
    }

    public static final mPoint leftPointAtPixel(int size, int tmw) {
        final int indexAtPixel = indexAtPixel(size, tmw);
        tmw = MyTile.tmw;
        size = MyTile.size;
        return new mPoint(indexAtPixel % tmw * size, indexAtPixel / tmw * size);
    }

    public static void loadMainTile() {
        System.gc();
        if (MyTile.lastTileID != MyTile.tileID) {
            getTile();
            MyTile.lastTileID = MyTile.tileID;
        }
    }

    public static void loadMap(final int n) {
        final int tmh = MyTile.tmh;
        final byte size = MyTile.size;
        MyTile.pxh = tmh * size;
        MyTile.pxw = MyTile.tmw * size;
        final PrintStream out = System.out;
        final StringBuilder sb = new StringBuilder();
        sb.append("load tile ID= ");
        sb.append(MyTile.tileID);
        out.println(sb);
        final boolean isOfflineMode = Config.isOfflineMode;
        if (isOfflineMode) {
            setTileOffline(0);
            return;
        }
        try {
            final int[][] tileType = MyTile.tileType;
            if (tileType != null && tileType.length > 0 && n > 0) {
                for (int i = 0; i < MyTile.tmw * MyTile.tmh; ++i) {
                    int n2 = 0;
                    while (true) {
                        final int[] array = MyTile.tileType[n];
                        if (n2 >= array.length) {
                            break;
                        }
                        setTile(i, MyTile.tileIndex[n][n2], array[n2]);
                        ++n2;
                    }
                }
//                for (int j = 0; j < MyTile.tileIndex[n][1].length; ++j) {
//                    String sb2 = "tileIndex[" + j + "]:" + MyTile.tileIndex[n][1][j];
//                    Res.outz(sb2);
//                }
            }
        }
        catch (Exception ex) {
            System.out.println("Error Load Map");
            ex.printStackTrace();
        }
    }

    public static void loadMapFromResource(int i) throws Exception {
        final StringBuilder sb = new StringBuilder();
        sb.append("Đọc map:");
        sb.append(i);
        Res.outz(1, sb.toString());
        final StringBuilder sb2 = new StringBuilder();
        sb2.append("/mymap/");
        sb2.append(i);
        final DataInputStream dataInputStream = new DataInputStream(LibSysCustom.getResourceAsStream(sb2.toString()));
        MyTile.tmw = (char)dataInputStream.read();
        MyTile.tmh = (char)dataInputStream.read();
        MyTile.maps = new int[dataInputStream.available()];
        for (i = 0; i < MyTile.tmw * MyTile.tmh; ++i) {
            MyTile.maps[i] = (char)dataInputStream.read();
        }
        MyTile.types = new int[MyTile.maps.length];
    }

    public static final void paintMiniTileNew(final mGraphics mGraphics, final int n, final int n2, int size, final int n3, final int n4, final int n5, final float n6) {
        try {
            final Image[] imgTile = MyTile.imgTile;
            if (imgTile == null) {
                return;
            }
            if (imgTile.length == 1) {
                final TextureRegion[] tRegions = imgTile[0].tRegions;
                if (size >= tRegions.length) {
                    return;
                }
                final TextureRegion textureRegion = tRegions[size];
                size = MyTile.size;
                mGraphics.drawRegionSpec(textureRegion, n3 * size + n, size * n4 + n2 + n5, n6);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static final void paintTileMap(final mGraphics mGraphics) {
        for (int i = MapScr.gssx; i < MapScr.gssxe; ++i) {
            for (int j = MapScr.gssy; j < MapScr.gssye; ++j) {
                if (i != 0) {
                    final int tmw = MyTile.tmw;
                    if (i != tmw - 1) {
                        final int n = MyTile.maps[tmw * j + i] - 1;
                        if (n != -1) {
                            int n2 = 0;
                            if ((tileTypeAt(i, j) & 0x200) == 0x200) {
                                n2 = 1;
                            }
                            paintTileNew(mGraphics, n, i, j, n2);
                        }
                    }
                }
            }
        }
    }

    public static final void paintTileMapSpec(final mGraphics mGraphics) {
        MapScr.gI().paintBgItem(mGraphics, 1);
        for (int i = 0; i < MyTile.tmw; ++i) {
            for (int j = 0; j < MyTile.tmh; ++j) {
                if (i != 0) {
                    final int n = MyTile.maps[MyTile.tmw * j + i] - 1;
                    if (n != -1) {
                        paintTileNew(mGraphics, n, i, j, 0);
                    }
                }
            }
        }
    }

    public static final void paintTileNew(final mGraphics mGraphics, int n, final int n2, final int n3, final int n4) {
        try {
            final Image[] imgTile = MyTile.imgTile;
            if (imgTile == null) {
                return;
            }
            final FrameImage frameImage = MyTile.frameTileEffectMores[n];
            if (frameImage != null) {
                final int indexFrameEff = MyTile.indexFrameEff;
                n = MyTile.size;
                frameImage.drawFrame(indexFrameEff, (float)(n2 * n), (float)(n * n3 + n4), mGraphics);
                return;
            }
            if (imgTile.length == 1) {
                final TextureRegion[] tRegions = imgTile[0].tRegions;
                if (n >= tRegions.length) {
                    return;
                }
                mGraphics.drawRegion(tRegions[n], MyTile.size * n2 - MapScr.cmx, MyTile.size * n3 - MapScr.cmy + n4);
            }
            else {
                final Image image = imgTile[n];
                n = MyTile.size;
                mGraphics.drawImage(image, (float)(n2 * n), (float)(n * n3 + n4), 0);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void paintWayPoint(final mGraphics mGraphics) {
        for (int i = 0; i < MyTile.vGo.size(); ++i) {
            ((PointChangeMap)MyTile.vGo.elementAt(i)).paint(mGraphics);
        }
    }

    public static final mPoint pointAtPixel(int tmw, int indexAtPixel) {
        indexAtPixel = indexAtPixel(tmw, indexAtPixel);
        tmw = MyTile.tmw;
        final byte size = MyTile.size;
        return new mPoint(indexAtPixel % tmw * size, indexAtPixel / tmw * size);
    }

    public static final mPoint rightPointAtPixel(int size, int tmw) {
        final int indexAtPixel = indexAtPixel(size, tmw);
        tmw = MyTile.tmw;
        size = MyTile.size;
        return new mPoint(indexAtPixel % tmw * size + size - 1, indexAtPixel / tmw * size);
    }

    public static void setTile(final int n, int[] types, final int n2) {
        for (int i = 0; i < types.length; ++i) {
            if (MyTile.maps[n] == types[i]) {
                types = MyTile.types;
                types[n] |= n2;
                return;
            }
        }
    }

    public static void setTileOffline(final int n) {
        for (int i = 0; i < MyTile.tmw * MyTile.tmh; ++i) {
            if (n == 1) {
                setTile(i, new int[] { 4, 5, 11, 15, 16, 20, 23, 29, 35, 37, 53, 55, 56, 57, 59, 68, 71, 84, 85, 86, 87, 135, 144, 145, 147, 149 }, 2);
                setTile(i, new int[] { 55, 56, 57, 135 }, 1024);
                setTile(i, new int[] { 37, 41, 42, 43, 44, 46, 166, 168 }, 8);
                setTile(i, new int[] { 35, 38, 39, 40, 45, 47, 48, 165, 167 }, 4);
                setTile(i, new int[] { 136, 137, 138, 139, 140, 169, 170 }, 16);
                setTile(i, new int[] { 64 }, 4096);
                setTile(i, new int[] { 66 }, 8192);
            }
            else {
                setTile(i, new int[] { 4, 5, 11, 15, 16, 20, 23, 29, 35, 37, 53, 55, 56, 57, 59, 68, 71, 84, 85, 86, 87, 135 }, 2);
                setTile(i, new int[] { 55, 56, 57, 135 }, 1024);
                setTile(i, new int[] { 37, 41, 42, 43, 44, 46 }, 8);
                setTile(i, new int[] { 35, 38, 39, 40, 45, 47 }, 4);
                setTile(i, new int[] { 136, 137, 138, 139, 140 }, 16);
            }
            if (n == 2) {
                setTile(i, new int[] { 4, 5, 13, 17, 31, 32, 33, 48, 53, 57, 58, 62 }, 2);
                setTile(i, new int[] { 31, 32, 33 }, 1024);
                setTile(i, new int[] { 13, 14, 15, 28, 29 }, 8);
                setTile(i, new int[] { 27, 26, 19, 17, 18 }, 4);
            }
            if (n == 3) {
                setTile(i, new int[] { 4, 5, 13, 20, 27, 29, 30, 32, 33, 41, 45, 46, 50, 74, 75, 83, 84, 86, 89, 92 }, 2);
                setTile(i, new int[] { 29, 30, 33 }, 1024);
                setTile(i, new int[] { 20, 21, 22, 23, 24, 25 }, 8);
                setTile(i, new int[] { 13, 14, 15, 16, 17, 18 }, 4);
                setTile(i, new int[] { 66, 67, 68, 74 }, 2048);
            }
            if (n == 4) {
                setTile(i, new int[] { 4, 5, 6, 7, 17, 22, 37, 41, 42, 43, 44, 50 }, 2);
                setTile(i, new int[] { 12, 17, 18, 19, 20, 32, 33, 139 }, 8);
                setTile(i, new int[] { 22, 23, 24, 25, 30, 31, 34, 136 }, 4);
                setTile(i, new int[] { 136, 137, 138, 139 }, 16);
            }
        }
    }

    public static final void setTileTypeAtPixel(int n, final int n2, final int n3) {
        final int[] types = MyTile.types;
        final byte size = MyTile.size;
        n = n2 / size * MyTile.tmw + n / size;
        types[n] |= n3;
    }

    public static final int tileTypeAt(int n, final int n2) {
        try {
            n = MyTile.types[MyTile.tmw * n2 + n];
            return n;
        }
        catch (Exception ex) {
            return 1000;
        }
    }

    public static final boolean tileTypeAt(int n, final int n2, final int n3) {
        boolean b = false;
        try {
            final int[] types = MyTile.types;
            final byte size = MyTile.size;
            n = types[n2 / size * MyTile.tmw + n / size];
            if ((n & n3) == n3) {
                b = true;
            }
            return b;
        }
        catch (Exception ex) {
            return false;
        }
    }

    public static final int tileTypeAtPixel(int n, final int n2) {
        try {
            final int[] types = MyTile.types;
            final byte size = MyTile.size;
            n = types[n2 / size * MyTile.tmw + n / size];
            return n;
        }
        catch (Exception ex) {
            return 1000;
        }
    }

    public static final int tileXofPixel(final int n) {
        final byte size = MyTile.size;
        return n / size * size;
    }

    public static final int tileYofPixel(final int n) {
        final byte size = MyTile.size;
        return n / size * size;
    }

    public static void updateEffTileNew() {
        if (CanvasNinja.gameTick % 3 == 0 && ++MyTile.indexFrameEff > MyTile.numMaxFrameEff - 1) {
            MyTile.indexFrameEff = 0;
        }
    }

    public static void updatePointerWayPoint() {
        for (int i = 0; i < MyTile.vGo.size(); ++i) {
            ((PointChangeMap)MyTile.vGo.elementAt(i)).updatePointer();
        }
    }
}
