// Class Version: 8
package ninjawar.mylib;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import ninjawar.mymain.CanvasNinja;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import java.util.Enumeration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class mGraphics
{
    public static HashTableCustom cachedTextures;
    public static float zoomGdx;
    public static float zoomLevel;
    private float a;
    int angle;
    private float b;
    public OrthographicCamera camera;
    public OrthographicCamera cameraStage;
    public float clipH;
    public float clipW;
    public float clipX;
    public float clipY;
    Color colorCircle;
    public SpriteBatch g;
    private float gl;
    public boolean isCircle;
    public boolean isClip;
    public boolean isRorate;
    public boolean isStartRunVideo;
    public boolean isStopRunVideo;
    public boolean isTranslate;
    public boolean isVideoRunning;
    Label label;
    private float r;
    public float rotation;
    public ShapeRenderer shapeRenderer;
    Stage stage;
    Label.LabelStyle style;
    float translateX;
    float translateY;
    public float xRotate;
    public float yRotate;

    static {
        mGraphics.zoomLevel = 1.0f;
        mGraphics.zoomGdx = 1.0f;
        mGraphics.cachedTextures = new HashTableCustom();
    }

    public mGraphics() {
        this.isStartRunVideo = false;
        this.isVideoRunning = false;
        this.isStopRunVideo = false;
        this.angle = 0;
    }

    public mGraphics(final SpriteBatch g, final ShapeRenderer shapeRenderer) {
        this.isStartRunVideo = false;
        this.isVideoRunning = false;
        this.isStopRunVideo = false;
        this.angle = 0;
        this.g = g;
        this.shapeRenderer = shapeRenderer;
        this.stage = new Stage(new ScreenViewport());
    }

    private void _drawRegion(final Texture texture, final float n, final float n2, final float n3, final float n4, final int n5, final float n6, final float n7, final int n8, final boolean b, final boolean b2) {
        this._drawRegion(texture, n, n2, n3, n4, n5, n6, n7, n8, b, b2, false, 0);
    }

    private void _drawRegion(final Texture texture, final float n, final float n2, final float n3, final float n4, final int n5, final float n6, final float n7, final int n8, final boolean b, final boolean b2, final boolean b3, final int n9) {
        this._drawRegion(texture, n, n2, n3, n4, n5, n6, n7, n8, b, b2, b3, n9, false);
    }

    private void _drawRegion(final Texture texture, final float n, final float n2, final float n3, final float n4, final int n5, final float n6, final float n7, final int n8, final boolean b, final boolean b2, final boolean b3, final int n9, final boolean b4) {
        this._drawRegion(texture, n, n2, n3, n4, n5, n6, n7, n8, b, b2, b3, n9, b4, mGraphics.zoomLevel);
    }

    private void cache(final String s, final Texture texture) {
        if (mGraphics.cachedTextures.size() > 500) {
            final Enumeration<Object> keys = mGraphics.cachedTextures.keys();
            while (keys.hasMoreElements()) {
                final String s2 = (String) keys.nextElement();
                final Texture texture2 = (Texture)mGraphics.cachedTextures.get(s2);
                mGraphics.cachedTextures.remove(s2);
                mGraphics.cachedTextures.remove(texture2);
                texture2.dispose();
            }
            mGraphics.cachedTextures.clear();
            System.gc();
        }
        mGraphics.cachedTextures.put(s, texture);
    }

    public static int getImageHeight(final Image image) {
        int rHeight;
        if (image != null) {
            rHeight = image.getRHeight();
        }
        else {
            rHeight = 0;
        }
        return rHeight;
    }

    public static int getImageWidth(final Image image) {
        int rWidth;
        if (image != null) {
            rWidth = image.getRWidth();
        }
        else {
            rWidth = 0;
        }
        return rWidth;
    }

    public void _drawRegion(final Texture texture, final float n, final float n2, final float n3, final float n4, int n5, float n6, float n7, int n8, final boolean b, final boolean b2, final boolean b3, int n9, final boolean b4, final float n10) {
        if (texture == null) {
            return;
        }
        float n11;
        float n12;
        if (this.isTranslate) {
            n11 = n6 + this.translateX;
            n12 = n7 + this.translateY;
        }
        else {
            n12 = n7;
            n11 = n6;
        }
        switch (n8) {
            default: {
                n6 = 0.0f;
                n7 = 0.0f;
                break;
            }
            case 40: {
                if (n5 != 4 && n5 != 7 && n5 != 6 && n5 != 5) {
                    n6 = n3;
                    n7 = n4;
                    break;
                }
                n6 = n4;
                n7 = n3;
                break;
            }
            case 36: {
                if (n5 != 4 && n5 != 7 && n5 != 6 && n5 != 5) {
                    n6 = 0.0f;
                    n7 = n4;
                    break;
                }
                n6 = 0.0f;
                n7 = n3;
                break;
            }
            case 33: {
                n6 = n3 / 2.0f;
                if (n5 != 4 && n5 != 7 && n5 != 6 && n5 != 5) {
                    n7 = n4;
                    break;
                }
                n6 = n4 / 2.0f;
                n7 = n3;
                break;
            }
            case 24: {
                n6 = n3;
                if (n5 == 4 || n5 == 6 || n5 == 5 || n5 == 7) {
                    n6 = n4;
                }
                n7 = 0.0f;
                break;
            }
            case 17: {
                n6 = n3 / 2.0f;
                if (n5 == 4 || n5 == 6 || n5 == 5 || n5 == 7) {
                    n6 = n4 / 2.0f;
                }
                n7 = 0.0f;
                break;
            }
            case 10: {
                n7 = n4 / 2.0f;
                if (n5 != 4 && n5 != 7 && n5 != 6 && n5 != 5) {
                    n6 = n3;
                    break;
                }
                n7 = n3 / 2.0f;
                n6 = n4;
                break;
            }
            case 6: {
                n7 = n4 / 2.0f;
                if (n5 != 4 && n5 != 7 && n5 != 6 && n5 != 5) {
                    n6 = 0.0f;
                    break;
                }
                n7 = n3 / 2.0f;
                n6 = 0.0f;
                break;
            }
            case 3: {
                n6 = n3 / 2.0f;
                n7 = n4 / 2.0f;
                if (n5 != 4 && n5 != 7 && n5 != 6 && n5 != 5) {
                    break;
                }
                n6 = n4 / 2.0f;
                n7 = n3 / 2.0f;
                break;
            }
            case 0:
            case 20: {
                n6 = 0.0f;
                n7 = 0.0f;
                break;
            }
        }
        final float n13 = n11 - n6 * n10;
        final float n14 = n12 - n7 * n10;
        boolean b5 = false;
        boolean b6 = false;
        switch (n5) {
            default: {
                b5 = false;
                b6 = true;
                n8 = 1065353216;
                n6 = 0.0f;
                n7 = 0.0f;
                break;
            }
            case 7: {
                n7 = -n3;
                b5 = true;
                b6 = false;
                n8 = -1082130432;
                n6 = 0.0f;
                n5 = 270;
                break;
            }
            case 6: {
                n6 = -n4;
                n7 = -n3;
                b5 = true;
                b6 = true;
                n8 = -1082130432;
                n7 += n3;
                n5 = 270;
                break;
            }
            case 5: {
                n6 = -n4;
                n7 = -n3;
                b5 = true;
                b6 = true;
                n8 = -1082130432;
                n7 += n3;
                n5 = 90;
                break;
            }
            case 4: {
                n6 = -n4;
                n7 = -n3;
                b5 = true;
                b6 = false;
                n8 = -1082130432;
                n5 = 90;
                break;
            }
            case 3: {
                n7 = -n4;
                n6 = -n3;
                b5 = false;
                b6 = true;
                n8 = 1065353216;
                n5 = 180;
                break;
            }
            case 2: {
                b5 = true;
                b6 = true;
                n8 = 1065353216;
                n6 = 0.0f;
                n7 = 0.0f;
                n5 = 0;
                break;
            }
            case 1: {
                b5 = false;
                b6 = false;
                n8 = 1065353216;
                n6 = 0.0f;
                n7 = 0.0f;
                n5 = 0;
                break;
            }
        }
        float n15 = (float)n5;
        float n18;
        float n19;
        if (this.isRorate) {
            final float n16 = -n13;
            final float xRotate = this.xRotate;
            final float n17 = -n14;
            final float yRotate = this.yRotate;
            n15 += this.rotation;
            n18 = n16 + xRotate * n10;
            n19 = n17 + yRotate * n10;
        }
        else {
            n18 = 0.0f;
            n19 = 0.0f;
        }
        if (this.isClip && b) {
            this.beginClip();
        }
        if (b3) {
            final SpriteBatch g = this.g;
            g.setColor(g.getColor().r, this.g.getColor().g, this.g.getColor().b, n9 / 100.0f);
        }
        if (b4) {
            this.g.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        }
        n9 = (int)n;
        n5 = (int)n2;
        n8 = (int)n3;
        this.g.draw(texture, n13 - n6 * n10, n14 - n7 * n10, n18, n19, n3, n4, n10, n10, n15, n9, n5, n8, (int)n4, b5, b6);
        if (this.isClip && b) {
            this.endClip0();
        }
        if (b3 || b4) {
            this.resetColor();
        }
    }

    public void begin() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(16384);
        this.g.begin();
    }

    public void beginClip() {
        if (this.isClip) {
            Gdx.gl.glEnable(3089);
            Gdx.gl.glScissor((int)this.clipX, (int)this.clipY, (int)this.clipW, (int)this.clipH);
        }
    }

    public void beginShape() {
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }

    public void drawImage(final Image image, final float n, final float n2) {
        final float zoomLevel = mGraphics.zoomLevel;
        this._drawRegion(image.texture, 0.0f, 0.0f, (float)image.getRWidth(), (float)image.getRHeight(), 0, n * zoomLevel, zoomLevel * n2, 0, false, false);
    }

    public void drawImage(final Image image, final float n, final float n2, final int n3) {
        final float zoomLevel = mGraphics.zoomLevel;
        this._drawRegion(image.texture, 0.0f, 0.0f, (float)image.getRWidth(), (float)image.getRHeight(), 0, n * zoomLevel, zoomLevel * n2, n3, false, false);
    }

    public void drawImage(final Image image, final float n, final float n2, final int n3, final int n4) {
        final float zoomLevel = mGraphics.zoomLevel;
        this._drawRegion(image.texture, 0.0f, 0.0f, (float)image.getRWidth(), (float)image.getRHeight(), n4, n * zoomLevel, zoomLevel * n2, n3, false, false);
    }

    public void drawImage(final Image image, final float n, final float n2, final int n3, final boolean b) {
        final float zoomLevel = mGraphics.zoomLevel;
        this._drawRegion(image.texture, 0.0f, 0.0f, (float)image.getRWidth(), (float)image.getRHeight(), 0, n * zoomLevel, zoomLevel * n2, n3, b, false);
    }

    public void drawImage(final Image image, final float n, final float n2, final int n3, final boolean b, final int n4) {
        final float zoomLevel = mGraphics.zoomLevel;
        this._drawRegion(image.texture, 0.0f, 0.0f, (float)image.getRWidth(), (float)image.getRHeight(), 0, n * zoomLevel, zoomLevel * n2, n3, false, false, b, n4);
    }

    public void drawImage(final Image image, final float n, final float n2, final boolean b, final int n3) {
        final float zoomLevel = mGraphics.zoomLevel;
        this._drawRegion(image.texture, 0.0f, 0.0f, (float)image.getRWidth(), (float)image.getRHeight(), 0, n * zoomLevel, zoomLevel * n2, 0, false, false, b, n3);
    }

    public void drawImage(final Image image, final float n, final float n2, final boolean b, final boolean b2, final int n3) {
        final float zoomLevel = mGraphics.zoomLevel;
        this._drawRegion(image.texture, 0.0f, 0.0f, (float)image.getRWidth(), (float)image.getRHeight(), 0, n * zoomLevel, zoomLevel * n2, 0, b, false, b2, n3);
    }

    public void drawImage(final Image image, final int n, final int n2, final boolean b) {
        final float n3 = (float)n;
        final float zoomLevel = mGraphics.zoomLevel;
        this._drawRegion(image.texture, 0.0f, 0.0f, (float)image.getRWidth(), (float)image.getRHeight(), 0, n3 * zoomLevel, zoomLevel * n2, 0, b, false);
    }

    public void drawImageSpecZoom(final Image image, final float n, final float n2, final float n3, final boolean b) {
        this.drawImageSpecZoom(image, n, n2, n3, b, false, 0, false, true);
    }

    public void drawImageSpecZoom(final Image image, final float n, final float n2, final float n3, final boolean b, final boolean b2, final int n4, final boolean b3, final boolean b4) {
        final float zoomLevel = mGraphics.zoomLevel;
        float n5 = n * zoomLevel;
        float n6 = n2 * zoomLevel;
        if (b4) {
            final float n7 = (float)image.getRWidth();
            final float n8 = (float)image.getRWidth();
            final float zoomLevel2 = mGraphics.zoomLevel;
            n5 = zoomLevel * n + (n7 - n8 * n3) * zoomLevel2 / 2.0f;
            n6 = n2 * zoomLevel2 + (image.getRHeight() - image.getRHeight() * n3) * mGraphics.zoomLevel / 2.0f;
        }
        this._drawRegion(image.texture, 0.0f, 0.0f, (float)image.getRWidth(), (float)image.getRHeight(), 0, n5, n6, 0, b, false, b2, n4, b3, mGraphics.zoomLevel * n3);
    }

    public void drawRect(final int n, final int n2, final int n3, final int n4) {
        this.fillRect(n, n2, n3, 1, false);
        this.fillRect(n, n2, 1, n4, false);
        this.fillRect(n + n3, n2, 1, n4 + 1, false);
        this.fillRect(n, n2 + n4, n3 + 1, 1, false);
    }

    public void drawRegion(final TextureRegion textureRegion, int n, int n2) {
        final float n3 = (float)n;
        final float zoomLevel = mGraphics.zoomLevel;
        n = (int)(n3 * zoomLevel);
        n2 *= (int)zoomLevel;
        this.g.draw(textureRegion, (float)n, (float)n2, mGraphics.zoomLevel * textureRegion.getRegionWidth(), mGraphics.zoomLevel * textureRegion.getRegionHeight());
    }

    public void drawRegion(final Image image, final float n, final float n2, final float n3, final float n4, final int n5, final float n6, final float n7, final int n8) {
        if (image == null) {
            return;
        }
        final float zoomLevel = mGraphics.zoomLevel;
        this._drawRegion(image.texture, n, n2, n3, n4, n5, n6 * zoomLevel, zoomLevel * n7, n8, false, false);
    }

    public void drawRegion(final Image image, final float n, final float n2, final float n3, final float n4, final int n5, final float n6, final float n7, final int n8, final boolean b) {
        if (image == null) {
            return;
        }
        final float zoomLevel = mGraphics.zoomLevel;
        this._drawRegion(image.texture, n, n2, n3, n4, n5, n6 * zoomLevel, zoomLevel * n7, n8, b, false);
    }

    public void drawRegion(final Image image, final float n, final float n2, final float n3, final float n4, final int n5, final float n6, final float n7, final int n8, final boolean b, final boolean b2, final int n9) {
        if (image == null) {
            return;
        }
        final float zoomLevel = mGraphics.zoomLevel;
        this._drawRegion(image.texture, n, n2, n3, n4, n5, n6 * zoomLevel, zoomLevel * n7, n8, b2, false, b, n9);
    }

    public void drawRegion(final Image image, final float n, final float n2, final int n3, final int n4, final int n5, final float n6, final float n7, final int n8, final boolean b, final boolean b2, final int n9, final boolean b3) {
        if (image == null) {
            return;
        }
        final float zoomLevel = mGraphics.zoomLevel;
        this._drawRegion(image.texture, n, n2, (float)n3, (float)n4, n5, n6 * zoomLevel, zoomLevel * n7, n8, b, false, b2, n9, b3);
    }

    public void drawRegionSpec(final TextureRegion textureRegion, final int n, final int n2, final float n3) {
        this.drawRegionSpec(textureRegion, n, n2, n3, false);
    }

    public void drawRegionSpec(final TextureRegion textureRegion, final int n, final int n2, final float n3, final boolean b) {
        final float n4 = (float)n;
        final float zoomLevel = mGraphics.zoomLevel;
        float n5 = n4 * zoomLevel * n3;
        float n6 = n2 * zoomLevel * n3;
        if (b) {
            final float n7 = (float)n;
            final float n8 = (float)textureRegion.getRegionWidth();
            final float n9 = (float)textureRegion.getRegionWidth();
            final float zoomLevel2 = mGraphics.zoomLevel;
            n5 = n7 * zoomLevel + (n8 - n9 * n3) * zoomLevel2 / 2.0f;
            n6 = n2 * zoomLevel2 + (textureRegion.getRegionHeight() - textureRegion.getRegionHeight() * n3) * mGraphics.zoomLevel / 2.0f;
        }
        this.g.draw(textureRegion, n5, n6, textureRegion.getRegionWidth() * mGraphics.zoomLevel * n3, textureRegion.getRegionHeight() * mGraphics.zoomLevel * n3);
    }

    public void drawSprite(final Sprite sprite, final float n, final float n2, final int n3, final int n4, final boolean b, final boolean b2, final int n5, final boolean b3) {
        this.drawSprite(sprite, n, n2, n3, n4, b, b2, n5, b3, mGraphics.zoomLevel);
    }

    public void drawSprite(final Sprite sprite, float n, float n2, int n3, final int n4, final boolean b, final boolean b2, final int n5, final boolean b3, float n6) {
        final float zoomGdx = mGraphics.zoomGdx;
        final float zoomLevel = mGraphics.zoomLevel;
        n *= zoomGdx * zoomLevel;
        float n7;
        n2 = (n7 = zoomGdx * zoomLevel * n2);
        float n8 = n;
        if (this.isTranslate) {
            n8 = n + this.translateX;
            n7 = n2 + this.translateY;
        }
        final float n9 = sprite.getWidth() * mGraphics.zoomGdx * n6;
        final float n10 = sprite.getHeight() * mGraphics.zoomGdx * n6;
        boolean b4 = false;
        n2 = 0.0f;
        n = 0.0f;
        switch (n3) {
            case 40: {
                n2 = n9;
                n = n10;
                if (n4 == 4 || n4 == 7 || n4 == 6 || n4 == 5) {
                    n2 = n10;
                    n = n9;
                    break;
                }
                break;
            }
            case 36: {
                final float n11 = 0.0f;
                n = n10;
                if (n4 != 4 && n4 != 7 && n4 != 6) {
                    n2 = n11;
                    if (n4 != 5) {
                        break;
                    }
                }
                n = n9;
                n2 = n11;
                break;
            }
            case 33: {
                n2 = n9 / 2.0f;
                n = n10;
                if (n4 == 4 || n4 == 7 || n4 == 6 || n4 == 5) {
                    n2 = n10 / 2.0f;
                    n = n9;
                    break;
                }
                break;
            }
            case 24: {
                n2 = n9;
                if (n4 == 4 || n4 == 6 || n4 == 5 || n4 == 7) {
                    n2 = n10;
                }
                n = 0.0f;
                break;
            }
            case 17: {
                n2 = n9 / 2.0f;
                if (n4 == 4 || n4 == 6 || n4 == 5 || n4 == 7) {
                    n2 = n10 / 2.0f;
                }
                n = 0.0f;
                break;
            }
            case 10: {
                n2 = n9;
                n = n10 / 2.0f;
                if (n4 == 4 || n4 == 7 || n4 == 6 || n4 == 5) {
                    n2 = n10;
                    n = n9 / 2.0f;
                    break;
                }
                break;
            }
            case 6: {
                final float n12 = 0.0f;
                n = n10 / 2.0f;
                if (n4 != 4 && n4 != 7 && n4 != 6) {
                    n2 = n12;
                    if (n4 != 5) {
                        break;
                    }
                }
                n = n9 / 2.0f;
                n2 = n12;
                break;
            }
            case 3: {
                n2 = n9 / 2.0f;
                n = n10 / 2.0f;
                if (n4 == 4 || n4 == 7 || n4 == 6 || n4 == 5) {
                    n2 = n10 / 2.0f;
                    n = n9 / 2.0f;
                    break;
                }
                break;
            }
            case 0:
            case 20: {
                n2 = 0.0f;
                n = 0.0f;
                break;
            }
        }
        n2 = n8 - n2 * n6;
        n6 = n7 - n * n6;
        switch (n4) {
            default: {
                n3 = n4;
                break;
            }
            case 7: {
                b4 = true;
                n = -n9;
                n3 = 270;
                break;
            }
            case 6: {
                b4 = true;
                n = -n10;
                n = -n9;
                n3 = 270;
                break;
            }
            case 5: {
                b4 = true;
                n = -n10;
                n = -n9;
                n3 = 90;
                break;
            }
            case 4: {
                b4 = true;
                n = -n10;
                n = -n9;
                n3 = 90;
                break;
            }
            case 3: {
                n = -n10;
                n = -n9;
                n3 = 180;
                break;
            }
            case 2: {
                n3 = 0;
                b4 = true;
                break;
            }
            case 1: {
                n3 = 0;
                break;
            }
        }
        n = (float)n3;
        if (this.isRorate) {
            final float xRotate = this.xRotate;
            final float yRotate = this.yRotate;
            n += this.rotation;
        }
        if (b4) {
            if (!sprite.isFlipX()) {
                sprite.setFlip(b4, true);
            }
        }
        else {
            sprite.setFlip(false, true);
        }
        sprite.setPosition(n2, n6);
        if (b2) {
            sprite.setAlpha(n5 / 100.0f);
        }
        else {
            sprite.setAlpha(1.0f);
        }
        if (b3) {
            this.g.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        }
        if (this.isClip && b) {
            this.beginClip();
        }
        sprite.draw(this.g);
        if (this.isClip && b) {
            this.endClip0();
        }
    }

    public void drawSprite(final Sprite sprite, final float n, final float n2, final boolean b, final boolean b2, final int n3) {
        this.drawSprite(sprite, n, n2, 0, 0, b, b2, n3, false);
    }

    public void drawSprite(final mSprite mSprite, final float n, final float n2, final int n3, final int n4, final boolean b, final boolean b2, final int n5, final boolean b3) {
        this.drawSprite(mSprite.sprite, n, n2, n3, n4, b, b2, n5, b3, mGraphics.zoomLevel);
    }

    public void drawSprite(final mSprite mSprite, final float n, final float n2, final int n3, final boolean b, final int n4) {
        this.drawSprite(mSprite.sprite, n, n2, n3, 0, false, b, n4, false);
    }

    public void drawSprite(final mSprite mSprite, final float n, final float n2, final boolean b) {
        this.drawSprite(mSprite.sprite, n, n2, 0, 0, b, false, 100, false);
    }

    public void drawSpriteSpecZoom(final Sprite sprite, final float n, final float n2, final float n3, final boolean b, final boolean b2, final int n4, final boolean b3, final boolean b4) {
        this.drawSprite(sprite, n, n2, 0, 0, b, b2, n4, b3, mGraphics.zoomLevel * n3);
    }

    public void drawSpriteSpecZoom(final mSprite mSprite, final float n, final float n2, final float n3, final boolean b) {
        this.drawSpriteSpecZoom(mSprite.sprite, n, n2, n3, b, false, 0, false, true);
    }

    public void drawString(final String s, float n, float n2, final BitmapFont bitmapFont, final int n3, final boolean b, final int n4, final int n5) {
        bitmapFont.getData().markupEnabled = true;
        this.style = new Label.LabelStyle(bitmapFont, null);
        this.label = new Label(s, this.style);
        if (this.isClipWithWHZero()) {
            return;
        }
        final float zoomLevel = mGraphics.zoomLevel;
        n *= zoomLevel;
        n2 *= zoomLevel;
        if (this.isTranslate) {
            final float translateX = this.translateX;
            n2 += this.translateY;
            n += translateX;
        }
        if (this.isClip && b) {
            this.beginClip();
        }
        if (n5 > 0) {
            bitmapFont.setColor(this.g.getColor().r, this.g.getColor().g, this.g.getColor().b, n5 / 100.0f);
        }
        final SpriteBatch g = this.g;
        final Matrix4 matrix4 = new Matrix4();
        float n6;
        if (n4 != 0) {
            n6 = 1.0f;
        }
        else {
            n6 = 0.0f;
        }
        g.setTransformMatrix(matrix4.setToRotation(n, n2, n6, (float)n4));
        bitmapFont.draw(this.g, s, n, n2, 0.0f, n3, false);
        if (n4 != 0) {
            this.g.setTransformMatrix(new Matrix4().setToRotation(0.0f, 0.0f, 0.0f, 0.0f));
        }
        if (this.isClip && b) {
            this.endClip0();
        }
    }

    public void end() {
        this.isCircle = false;
        this.isClip = false;
        this.translateX = 0.0f;
        this.translateY = 0.0f;
        this.isTranslate = false;
        this.g.end();
    }

    public void endClip0() {
        this.g.flush();
        Gdx.gl.glDisable(3089);
    }

    public void endShape() {
        this.shapeRenderer.end();
    }

    public void fillRect(float n, float n2, float n3, float n4, final boolean b, final boolean b2, final int n5) {
        final float zoomLevel = mGraphics.zoomLevel;
        final float n6 = n * zoomLevel;
        final float n7 = n2 * zoomLevel;
        n3 *= zoomLevel;
        n4 *= zoomLevel;
        if (n3 >= 0.0f && n4 >= 0.0f) {
            n2 = n6;
            n = n7;
            if (this.isTranslate) {
                n2 = n6 + this.translateX;
                n = n7 + this.translateY;
            }
            final StringBuilder sb = new StringBuilder();
            sb.append("fr");
            sb.append(this.r);
            sb.append(this.gl);
            sb.append(this.b);
            sb.append(this.a);
            final String string = sb.toString();
            Texture texture = (Texture)mGraphics.cachedTextures.get(string);
            if (texture == null) {
                final Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
                pixmap.setColor(this.r, this.b, this.gl, this.a);
                pixmap.drawPixel(0, 0);
                texture = new Texture(pixmap);
                pixmap.dispose();
                this.cache(string, texture);
            }
            if (this.isClip && b) {
                this.beginClip();
            }
            if (b2) {
                final SpriteBatch g = this.g;
                g.setColor(g.getColor().r, this.g.getColor().g, this.g.getColor().b, n5 / 100.0f);
            }
            this.g.draw(texture, n2, n, 0.0f, 0.0f, 1.0f, 1.0f, n3, n4, 0.0f, 0, 0, 1, 1, false, false);
            if (this.isClip && b) {
                this.endClip0();
            }
        }
    }

    public void fillRect(final int n, final int n2, final int n3, final int n4) {
        this.fillRect(n, n2, n3, n4, false);
    }

    public void fillRect(final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.fillRect((float)n, (float)n2, (float)n3, (float)n4, b, false, 0);
    }

    public float getTranslateX() {
        return this.translateX / (mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    public float getTranslateY() {
        return this.translateY / (mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    public boolean isClipWithWHZero() {
        return this.isClip && (this.clipH == 0.0f || this.clipW == 0.0f);
    }

    public void paintCircle(int n, int n2, final int n3, final int n4) {
        final float n5 = (float)n;
        final float zoomLevel = mGraphics.zoomLevel;
        n = (int)(n5 * zoomLevel);
        n2 *= (int)zoomLevel;
        final float n6 = (float)CanvasNinja.h;
        final float zoomLevel2 = mGraphics.zoomLevel;
        n2 = (int)(n6 * zoomLevel2 - n2);
        n2 = (int)(n3 * zoomLevel2);
        this.beginShape();
        final Color color = new Color(n4);
        this.colorCircle = color;
        this.shapeRenderer.setColor(color);
        this.shapeRenderer.circle((float)n, 0.0f, (float)n2);
        this.endShape();
    }

    public void resetColor() {
        this.g.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void restoreCanvas() {
    }

    public void setClip(int n, int n2, int n3, int n4) {
        int n5 = n3;
        if (n3 <= 0) {
            n5 = 1;
        }
        if ((n3 = n4) <= 0) {
            n3 = 1;
        }
        final float n6 = (float)n;
        final float zoomLevel = mGraphics.zoomLevel;
        n4 = (int)(n6 * zoomLevel);
        n = (int)(n2 * zoomLevel);
        n2 = (int)(n5 * zoomLevel);
        final int n7 = (int)(n3 * zoomLevel);
        final float n8 = (float)n4;
        final float zoomGdx = mGraphics.zoomGdx;
        n4 = (int)(n8 * zoomGdx);
        n3 = (int)(n * zoomGdx);
        final int n9 = (int)(n2 * zoomGdx);
        final int n10 = (int)(n7 * zoomGdx);
        n2 = n4;
        n = n3;
        if (this.isTranslate) {
            n2 = (int)(n4 + this.translateX);
            n = (int)(n3 + this.translateY);
        }
        this.clipX = (float)n2;
        this.clipW = (float)n9;
        this.clipH = (float)n10;
        this.clipY = CanvasNinja.h * mGraphics.zoomLevel * mGraphics.zoomGdx - n - n10;
        this.isClip = true;
    }

    public void setColor(final int n) {
        final float n2 = (float)(n >> 16 & 0xFF);
        final float n3 = (float)(n >> 8 & 0xFF);
        final float n4 = (float)(n & 0xFF);
        this.b = n3 / 256.0f;
        this.gl = n4 / 256.0f;
        this.r = n2 / 256.0f;
        this.a = 1.0f;
    }

    public void setColor(final int n, final float a) {
        final float n2 = (float)(n >> 16 & 0xFF);
        final float n3 = (float)(n >> 8 & 0xFF);
        final float n4 = (float)(n & 0xFF);
        this.b = n3 / 256.0f;
        this.gl = n4 / 256.0f;
        this.r = n2 / 256.0f;
        this.a = a;
    }

    public void setColor(final int n, final int n2, final int n3) {
        this.r = (float)n;
        this.gl = (float)n2;
        this.b = (float)n3;
    }

    public void setShader(final ShaderProgram shader) {
        this.g.setShader(shader);
    }

    public void translate(float translateX, float translateY) {
        final float zoomLevel = mGraphics.zoomLevel;
        translateX = this.translateX + translateX * zoomLevel;
        this.translateX = translateX;
        translateY = this.translateY + translateY * zoomLevel;
        this.translateY = translateY;
        this.isTranslate = true;
        if (translateX == 0.0f && translateY == 0.0f) {
            this.isTranslate = false;
        }
    }

    public void translate(int n, int n2) {
        final float n3 = (float)n;
        final float zoomLevel = mGraphics.zoomLevel;
        n = (int)(n3 * zoomLevel);
        n2 *= (int)zoomLevel;
        final float translateX = this.translateX + n;
        this.translateX = translateX;
        final float translateY = this.translateY + n2;
        this.translateY = translateY;
        this.isTranslate = true;
        if (translateX == 0.0f && translateY == 0.0f) {
            this.isTranslate = false;
        }
    }
}
