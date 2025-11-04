package ninjawar.mylib;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.tools.bmfont.BitmapFontWriter;
import java.util.concurrent.ConcurrentHashMap;

public class SystemFont {
    public static int BLACK_COLOR = 255;
    public static int BLUE_COLOR = 8126463;
    public static int BROWN_COLOR = -2042223617;
    public static int BROWN_DV = 2018918399;
    public static int BROWN_DV_FOCUS = -7732993;
    public static int DARK_COLLOR = 255;
    public static int GRAY_COLOR = -2037475585;
    public static int GREEN_BTN_BORDER = 120586495;
    public static int GREEN_COLOR = 1524704767;
    public static int LIGHT_BLUE_COLOR = 954659583;
    public static int ORANGE_COLOR = -494062081;
    public static int PINK_TEAM_COLOR = -231166977;
    public static int PLACE_HOLDER_COLOR = -1690432001;
    public static int PURPLE_COLOR = -1707476225;
    public static int RED_COLOR = -16776961;
    public static int RED_SPEC_COLOR = -600488449;
    public static int TOC_HE_DETAIL = -1504301825;
    public static int TOC_HE_TITLE = -92471041;
    public static int WHITE_COLOR = -1;
    public static int YELLOW_COLOR = -65281;
    public static int YELLOW_TEAM_COLOR = -4126721;
    private static final ConcurrentHashMap<String, BitmapFont> fontCache = new ConcurrentHashMap<>();
    private final BitmapFont bf;
    private final GlyphLayout layout = new GlyphLayout();

    public SystemFont(String fontNameFile, int size, int color, boolean useBorder, int colorBorder, int sizeBorder, boolean useShadow, int colorShadow) {
        BitmapFont computeIfAbsent = fontCache.computeIfAbsent(generateCacheKey(fontNameFile, size, color, useBorder, colorBorder, useShadow, colorShadow), new SystemFont$$ExternalSyntheticLambda0(this, fontNameFile, size, color, useBorder, colorBorder, sizeBorder, useShadow, colorShadow));
        this.bf = computeIfAbsent;
        computeIfAbsent.getData().markupEnabled = true;
        Texture texture = computeIfAbsent.getRegion().getTexture();
        Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
        texture.setFilter(textureFilter, textureFilter);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ BitmapFont lambda$new$0(String fontNameFile, int size, int color, boolean useBorder, int colorBorder, int sizeBorder, boolean useShadow, int colorShadow, String key) {
        return createFont(fontNameFile, size, color, useBorder, colorBorder, sizeBorder, useShadow, colorShadow);
    }

    private String generateCacheKey(String fontNameFile, int size, int color, boolean useBorder, int colorBorder, boolean useShadow, int colorShadow) {
        StringBuilder sb = new StringBuilder(fontNameFile);
        sb.append(size);
        sb.append("_");
        StringBuilder key = sb.append(color);
        if (useBorder) {
            key.append("-b-");
            key.append(colorBorder);
        }
        if (useShadow) {
            key.append("-s-");
            key.append(colorShadow);
        }
        return key.toString();
    }

    private BitmapFont createFont(String fontNameFile, int size, int color, boolean useBorder, int colorBorder, int sizeBorder, boolean useShadow, int colorShadow) {
        String str = fontNameFile;
        String name = generateCacheKey(fontNameFile, size, color, useBorder, colorBorder, useShadow, colorShadow);
        FileHandle fileFnt = getFontFileHandle(name, ".fnt");
        FileHandle fileImg = getFontFileHandle(name, ".png");
        if (fileFnt.exists() && fileImg.exists()) {
            return new BitmapFont(fileFnt, fileImg, true);
        }
        Files files = Gdx.files;
        FileHandle fontFile = files.internal(LibSysCustom.res + "/normal/font/ttf/" + str + ".ttf");
        if (!fontFile.exists()) {
            Files files2 = Gdx.files;
            fontFile = files2.internal(LibSysCustom.res + "/normal/font/ttf/" + str + ".TTF");
        }
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.setMaxTextureSize(2048);
        parameter.size = size;
        parameter.color = new Color(color);
        if (useBorder) {
            parameter.borderWidth = (float) sizeBorder;
            parameter.borderColor = new Color(colorBorder);
        } else {
            int i = colorBorder;
            int i2 = sizeBorder;
        }
        if (useShadow) {
            parameter.shadowOffsetX = 3;
            parameter.shadowOffsetY = 3;
            parameter.shadowColor = new Color(colorShadow);
        } else {
            int i3 = colorShadow;
        }
        parameter.characters = "\u0000ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890\"!`?'.,;:()[]{}<>|/@\\^$€-%+=#_&~* ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890\"!`?'.,;:()[]{}<>|/@\\^$€-%+=#_&~* ¡¢£¤¥¦§¨©ª«¬­®¯°±²³´µ¶·¸¹º»¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõö÷øùúûüýþÿáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ₫";
        parameter.flip = true;
        parameter.mono = true;
        int sizePack = Math.min(2048, determineTextureSize());
        parameter.packer = new PixmapPacker(sizePack, sizePack, Pixmap.Format.RGBA8888, 2, false, new PixmapPacker.SkylineStrategy());
        FreeTypeFontGenerator.FreeTypeBitmapFontData data = generator.generateData(parameter);
        BitmapFontWriter.FontInfo info = new BitmapFontWriter.FontInfo();
        info.padding = new BitmapFontWriter.Padding(1, 1, 1, 1);
        String[] strArr = {"font/" + name + ".png"};
        Files files3 = Gdx.files;
        BitmapFontWriter.writeFont(data, strArr, files3.local("font/" + name + ".fnt"), info, sizePack, sizePack);
        BitmapFontWriter.writePixmaps(parameter.packer.getPages(), Gdx.files.local("font"), name);
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    private FileHandle getFontFileHandle(String name, String extension) {
        Files files = Gdx.files;
        FileHandle file = files.internal(LibSysCustom.res + "/bitmap-fonts/" + name + extension);
        if (file.exists()) {
            return file;
        }
        Files files2 = Gdx.files;
        return files2.local("font/" + name + extension);
    }

    private int determineTextureSize() {
        float zoomLevel = mGraphics.zoomLevel;
        if (zoomLevel >= 3.0f) {
            return 2048;
        }
        if (zoomLevel >= 2.0f) {
            return 1024;
        }
        if (zoomLevel >= 1.5f) {
            return 512;
        }
        return 256;
    }

    public void drawString(mGraphics g, String st, int x, int y, int align) {
        drawString(g, st, (float) x, y, align, false, 0, 100);
    }

    public void drawString(mGraphics g, String st, float x, int y, int align, boolean useClip, int angle, int percentOpacity) {
        int al = 8;
        int yMore = 0;
        switch (align) {
            case 1:
                al = 16;
                break;
            case 2:
                al = 1;
                break;
            case 3:
                al = 1;
                yMore = (-getHeight()) / 2;
                break;
            case 4:
                yMore = (-getHeight()) / 2;
                break;
        }
        g.drawString(st, (float) ((int) x), (float) (y + yMore), this.bf, al, useClip, angle, percentOpacity);
    }

    public VectorCustom splitFontVector(String src, int lineWidth) {
        VectorCustom lines = new VectorCustom("vLine");
        if (lineWidth <= 0) {
            lines.add(src);
            return lines;
        }
        String line = "";
        long timeLoop = mSystem.currentTimeMillis() + 200;
        int i = 0;
        while (true) {
            if (i >= src.length()) {
                break;
            } else if (mSystem.currentTimeMillis() > timeLoop) {
                System.out.println("Break bug");
                break;
            } else {
                if (src.charAt(i) == 10 || src.charAt(i) == 8) {
                    lines.addElement(line);
                    line = "";
                } else {
                    line = line + src.charAt(i);
                    if (getWidth(line) > lineWidth) {
                        int j = line.length() - 1;
                        while (j >= 0 && line.charAt(j) != ' ') {
                            j--;
                        }
                        if (j < 0) {
                            j = line.length();
                        }
                        lines.addElement(line.substring(0, j));
                        line = "";
                        i = (i - (line.length() - j)) + 1;
                    }
                    if (i == src.length() - 1 && !line.trim().equals("")) {
                        lines.addElement(line);
                    }
                }
                i++;
            }
        }
        return lines;
    }

    public VectorCustom splitFontVector2(String src, int lineWidth) {
        VectorCustom lines = new VectorCustom("vLine");
        if (lineWidth <= 0) {
            lines.add(src);
            return lines;
        }
        String line = "";
        long timeLoop = mSystem.currentTimeMillis() + 200;
        int i = 0;
        while (true) {
            if (i >= src.length()) {
                break;
            } else if (mSystem.currentTimeMillis() > timeLoop) {
                System.out.println("Break bug");
                break;
            } else {
                if (src.charAt(i) == 10 || src.charAt(i) == 8) {
                    lines.addElement(line);
                    line = "";
                } else {
                    line = line + src.charAt(i);
                    if (getWidth(line) > lineWidth) {
                        int j = line.length() - 1;
                        while (j >= 0 && line.charAt(j) != ' ') {
                            j--;
                        }
                        if (j < 0) {
                            j = line.length();
                        }
                        lines.addElement(line.substring(0, j));
                        i -= line.length() - j;
                        line = "";
                    }
                    if (i == src.length() - 1 && !line.trim().equals("")) {
                        lines.addElement(line);
                    }
                }
                i++;
            }
        }
        return lines;
    }

    public int getWidth(String st) {
        try {
            this.layout.setText(this.bf, st);
            float wF = this.layout.width / mGraphics.zoomLevel;
            int wI = (int) wF;
            if (wF - ((float) wI) >= 0.4f) {
                return wI + 1;
            }
            return wI;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public float getWidthF(String st) {
        this.layout.setText(this.bf, st);
        return this.layout.width / mGraphics.zoomLevel;
    }

    public int getHeight() {
        this.layout.setText(this.bf, "A");
        float hFloat = this.layout.height / mGraphics.zoomLevel;
        int hInt = (int) hFloat;
        return hFloat - ((float) hInt) > 0.0f ? hInt + 1 : hInt;
    }

    public int getHeight(String st) {
        this.layout.setText(this.bf, st);
        float hFloat = this.layout.height / mGraphics.zoomLevel;
        int hInt = (int) hFloat;
        return hFloat - ((float) hInt) > 0.0f ? hInt + 1 : hInt;
    }

    public int getHeightH(String st) {
        int result = 0;
        for (String line : st.split("\n")) {
            result += getHeight(line);
        }
        return (int) (((double) result) * 1.7d);
    }
}
