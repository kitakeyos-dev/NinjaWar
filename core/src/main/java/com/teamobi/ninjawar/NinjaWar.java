// Class Version: 8
package com.teamobi.ninjawar;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import ninjawar.input.KEY;
import ninjawar.message.SendMessage;
import ninjawar.mylib.*;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.oauth2.AppleAuth;
import ninjawar.oauth2.AppleLogin;
import ninjawar.oauth2.FacebookLogin;
import ninjawar.oauth2.GoogleLogin;
import ninjawar.screen.LoginScr;
import ninjawar.supporter.LoadDataManager;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class NinjaWar extends ApplicationAdapter {
    public static long FPS;
    public static long FPS_TIME;
    private static final float MIN_FRAME_LENGTH;
    public static AssetManager assets;
    public static int fps;
    public static NinjaMidlet gmidlet;
    public static boolean isDisconnect;
    public static boolean isPause;
    public static String mainThreadName;
    public static NinjaWar me;
    public static int num_update;
    public static short speedTime;
    public static long t;
    public static short tick;
    public static int update_per_second;
    public static int[] viewport;
    int amountOfThings;
    public AppleLogin appleLogin;
    int[] comboKeyPress;
    private long diff2;
    public FacebookLogin facebookLogin;
    private mGraphics g;
    public GoogleLogin googleLogin;
    public int hStandard;
    int indexPress;
    private InputMultiplexer inputMultiplexer;
    private MyInputProcessor inputProcessor;
    boolean isAndroidController;
    boolean[] isComboKeyPress;
    public boolean isIos78;
    ShapeRenderer shapeRenderer;
    private long start2;
    int thingsWidth;
    int[] touchDownTempX;
    int[] touchDownTempY;
    public int wStandard;
    public float zoom;
    public float zoomLevelGdx;

    static {
        NinjaWar.tick = 25;
        NinjaWar.t = mSystem.currentTimeMillis();
        NinjaWar.speedTime = 0;
        NinjaWar.FPS_TIME = 0L;
        NinjaWar.FPS = mSystem.currentTimeMillis();
        NinjaWar.viewport = new int[]{0, 0, 0, 0};
        NinjaWar.update_per_second = 0;
        NinjaWar.num_update = 40;
        NinjaWar.isDisconnect = false;
        NinjaWar.assets = new AssetManager();
        NinjaWar.fps = 30;
        MIN_FRAME_LENGTH = 1.0f / 30;
    }

    public NinjaWar() {
        this.wStandard = 640;
        this.hStandard = 360;
        this.zoomLevelGdx = 1.0f;
        this.zoom = 1.0f;
        this.isAndroidController = false;
        this.start2 = System.currentTimeMillis();
        this.thingsWidth = 4;
        this.amountOfThings = 3;
        this.indexPress = 0;
        this.isComboKeyPress = new boolean[]{false, false, false, false, false};
        this.comboKeyPress = new int[]{-1, -1, -1, -1, -1};
        this.touchDownTempX = new int[20];
        this.touchDownTempY = new int[20];
    }

    private void boardUpdate() {
        NinjaMidlet.timeSystems = System.currentTimeMillis();
        final BaseScreen currentScreen = CanvasNinja.currentScreen;
        if (currentScreen instanceof MapScr && Player.myCharz() != null && CanvasNinja.isCanUpdateCurrentScr()) {
            MapScr.gI().updateKeyMoveChar();
            Player.myCharz().updateSubDir();
        }
        final int gameTick = CanvasNinja.gameTick;
    }

    public static int getH() {
        if (NinjaMidlet.isIOS) {
            return Gdx.graphics.getBackBufferHeight();
        }
        return Gdx.graphics.getHeight();
    }

    public static int getHeight() {
        return CanvasNinja.h;
    }

    public static int getW() {
        if (NinjaMidlet.isIOS) {
            return Gdx.graphics.getBackBufferWidth();
        }
        return Gdx.graphics.getWidth();
    }

    public static int getWidth() {
        return CanvasNinja.w;
    }

    private void initaliseInputProcessors() {
        final InputMultiplexer inputMultiplexer = new InputMultiplexer();
        this.inputMultiplexer = inputMultiplexer;
        Gdx.input.setInputProcessor(inputMultiplexer);
        final MyInputProcessor inputProcessor = new MyInputProcessor();
        this.inputProcessor = inputProcessor;
        this.inputMultiplexer.addProcessor(inputProcessor);
    }

    private void paint(final mGraphics mGraphics) {
        mGraphics.begin();
        if (NinjaWar.gmidlet != null) {
            NinjaMidlet.gameCanvas.paint(mGraphics);
        }
        mGraphics.end();
        if (NinjaWar.gmidlet != null) {
            NinjaMidlet.gameCanvas.paintShape(mGraphics);
        }
    }

    private void update() {
        this.boardUpdate();
        NinjaMidlet.gameCanvas.update();
        MySession.gI().update();
        MySession.gI2().update();
    }

    public void calZoomLevelCanvas() {
        this.calZoomLevelCanvas(getW(), getH());
    }

    public void calZoomLevelCanvas(final int n, final int n2) {
        mGraphics.zoomLevel = this.getZoomLevel(n, n2);
        this.zoomLevelGdx = 1.0f;
    }

    public void create() {
        try {
            if (NinjaMidlet.isPC || NinjaMidlet.isIOS) {
                this.googleLogin = new GoogleLogin() {
                    @Override
                    public boolean isLoggedInGoogle() {
                        return false;
                    }

                    @Override
                    public void signInGoogle() {
                        final String textRandom = Res.getTextRandom(18);
                        final StringBuilder sb = new StringBuilder();
                        sb.append("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.profile&response_type=code&state=");
                        sb.append(textRandom);
                        sb.append("&redirect_uri=");
                        sb.append("https://ccng.teamobi.com/gg/check_login.php");
                        sb.append("&client_id=");
                        sb.append("1023844035097-g860h0bc7mh5lscm7fn0b57912s5fmtm.apps.googleusercontent.com");
                        Gdx.net.openURI(sb.toString());
                        LoginScr.gI().continueLoginOAuth2(textRandom, textRandom);
                    }

                    @Override
                    public void signOutGoogle() {
                    }
                };
                final Files files = Gdx.files;
                final StringBuilder sb = new StringBuilder();
                sb.append(LibSysCustom.res);
                sb.append("/AuthKeyIOS.p8");
                final FileHandle internal = files.internal(sb.toString());
                if (internal.exists()) {
                    this.appleLogin = new AppleAuth("X6DNZQDZ69", "6AP25N276A", "com.teamobi.oauth2", "https://applenaruto.teamobi.com", new DataInputStream(new ByteArrayInputStream(internal.readBytes())), null);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Gdx.graphics.setTitle("NinjaWar");
        this.initaliseInputProcessors();
        if (NinjaMidlet.isAndroid) {
            this.calZoomLevelCanvas();
        } else if (NinjaMidlet.isPC) {
            Gdx.graphics.setWindowedMode((int) (this.wStandard * 2.0f), (int) (this.hStandard * 2.0f));
            this.calZoomLevelCanvas();
        }
        if (NinjaMidlet.isIOS) {
            final float zoomLevel = mGraphics.zoomLevel;
            final double n = zoomLevel - (int) zoomLevel;
            int n2 = (int) zoomLevel;
            if (n > 0.75) {
                ++n2;
            }
            mGraphics.zoomLevel = (float) n2;
        }
        MapKey.load();
        this.shapeRenderer = new ShapeRenderer();
        final mGraphics g = new mGraphics(new SpriteBatch(), this.shapeRenderer);
        this.g = g;
        g.camera = new OrthographicCamera((float) getW(), (float) getH());
        this.g.camera.setToOrtho(true, (float) getW(), (float) getH());
        final OrthographicCamera camera = this.g.camera;
        camera.zoom = 1.0f / this.zoomLevelGdx;
        camera.position.set(getW() * this.g.camera.zoom / 2.0f, getH() * this.g.camera.zoom / 2.0f, 0.0f);
        this.g.camera.update();
        (this.g.cameraStage = new OrthographicCamera((float) getW(), (float) getH())).setToOrtho(false, (float) getW(), (float) getH());
        final OrthographicCamera cameraStage = this.g.cameraStage;
        cameraStage.zoom = 1.0f / this.zoomLevelGdx;
        cameraStage.position.set(getW() * this.g.camera.zoom / 2.0f, getH() * this.g.camera.zoom / 2.0f, 0.0f);
        this.g.cameraStage.update();
        NinjaWar.me = this;
        NinjaWar.gmidlet = new NinjaMidlet();
        final CanvasNinja gameCanvas = NinjaMidlet.gameCanvas;
        gameCanvas.g = this.g;
        gameCanvas.initGame();
        NinjaWar.gmidlet.initGame();
        NinjaWar.mainThreadName = Thread.currentThread().getName();
    }

    public void dispose() {
        AudioManager.disposeAll();
        final HashMap<String, Atlas> hashMapAtlasAll = LoadDataManager.hashMapAtlasAll;
        if (hashMapAtlasAll != null) {
            hashMapAtlasAll.clear();
        }
        SendMessage.gI().logOut();
        Gdx.app.exit();
    }

    public void doRender() {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(16384);
        final mGraphics g = this.g;
        g.g.setProjectionMatrix(g.camera.combined);
        this.paint(this.g);
        this.update();
        final OrthographicCamera camera = this.g.camera;
        if (camera != null) {
            camera.update();
        }
        this.sleep();
    }

    public float getZoomLevel(final int n, final int n2) {
        float n3 = 1.0f;
        if (n < n2) {
            final float n4 = n * 1.0f / this.wStandard;
            if (n4 > 1.0f) {
                n3 = n4;
            }
            return n3;
        }
        final float n5 = n2 * 1.0f / this.hStandard;
        if (n5 > 1.0f) {
            n3 = n5;
        }
        return n3;
    }

    public void pause() {
    }

    public void render() {
        this.doRender();
    }

    public void resize(int n, int cx) {
        Res.outz(1, "resizeeeeeeeeeeeee");
        if (NinjaMidlet.isIOS) {
            return;
        }
        float zoomLevel = this.getZoomLevel(n, cx);
        if (NinjaMidlet.isPC) {
            zoomLevel = 2.0f;
        }
        if (zoomLevel != mGraphics.zoomLevel) {
            this.calZoomLevelCanvas(n, cx);
            final OrthographicCamera camera = this.g.camera;
            camera.viewportWidth = (float) n;
            camera.viewportHeight = (float) cx;
            camera.zoom = 1.0f / this.zoomLevelGdx;
            camera.position.set(getW() * this.g.camera.zoom / 2.0f, getH() * this.g.camera.zoom / 2.0f, 0.0f);
            this.g.camera.update();
            final CanvasNinja gameCanvas = NinjaMidlet.gameCanvas;
            gameCanvas.g = this.g;
            gameCanvas.checkZoomLevel(n, cx);
            NinjaMidlet.gameCanvas.initGame();
            final boolean b = CanvasNinja.currentScreen instanceof MapScr;
            int n2 = -1;
            if (b) {
                n = 1;
            } else {
                n = -1;
            }
            if (n == 1) {
                cx = Player.myCharz().cx;
            } else {
                cx = -1;
            }
            if (n != 0) {
                n2 = 0;
            }
            MapScr.loadCamera(false, cx, n2);
            NinjaWar.gmidlet.resize(n);
        }
    }

    public void resume() {
        NinjaWar.isPause = false;
    }

    public void signInWithApple() {
        if (this.appleLogin != null) {
            System.out.println("call signIn");
            if (this.appleLogin.isLoggedInApple()) {
                this.appleLogin.signOutApple();
            } else {
                this.appleLogin.signInApple();
            }
        }
    }

    public void signInWithGooogle() {
        if (this.googleLogin != null) {
            System.out.println("call signIn");
            if (this.googleLogin.isLoggedInGoogle()) {
                this.googleLogin.signOutGoogle();
            } else {
                this.googleLogin.signInGoogle();
            }
        }
    }

    public void signOutWithGooogle() {
        final GoogleLogin googleLogin = this.googleLogin;
        if (googleLogin != null) {
            googleLogin.signOutGoogle();
        }
    }

    public void sleep() {
        final int fps = NinjaWar.fps;
        if (fps > 0) {
            final long n = (long) (1000.0f / fps);
            final long diff2 = System.currentTimeMillis() - this.start2;
            this.diff2 = diff2;
            if (diff2 < n) {
                try {
                    Thread.sleep(n - diff2);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            this.start2 = System.currentTimeMillis();
        }
    }

    class MyInputProcessor implements InputProcessor {
        int timeDelay;
        long timeStartTouchDown;

        MyInputProcessor() {
            this.timeDelay = 100;
        }

        public boolean keyDown(int keyCode) {
            final KEY map = MapKey.map(keyCode);
            if (map == null) {
                keyCode = 0;
            } else {
                keyCode = map.keyCode;
            }
            if (!Gdx.input.isKeyPressed(59)) {
                Gdx.input.isKeyPressed(60);
            }
            final int n = -1;
            if (Gdx.input.isKeyPressed(129) || Gdx.input.isKeyPressed(130)) {
                final NinjaWar this$0 = NinjaWar.this;
                this$0.indexPress = 0;
                this$0.comboKeyPress[0] = 0;
                this$0.indexPress = 0 + 1;
            }
            if (Gdx.input.isKeyPressed(29)) {
                final NinjaWar this$2 = NinjaWar.this;
                final int[] comboKeyPress = this$2.comboKeyPress;
                if (comboKeyPress[0] == 0) {
                    final int indexPress = this$2.indexPress;
                    comboKeyPress[1] = indexPress;
                    this$2.indexPress = indexPress + 1;
                }
            }
            if (Gdx.input.isKeyPressed(52)) {
                final NinjaWar this$3 = NinjaWar.this;
                final int[] comboKeyPress2 = this$3.comboKeyPress;
                if (comboKeyPress2[0] == 0) {
                    final int indexPress2 = this$3.indexPress;
                    comboKeyPress2[2] = indexPress2;
                    this$3.indexPress = indexPress2 + 1;
                }
            }
            if (Gdx.input.isKeyJustPressed(31)) {
                final NinjaWar this$4 = NinjaWar.this;
                final int[] comboKeyPress3 = this$4.comboKeyPress;
                if (comboKeyPress3[0] == 0) {
                    final int indexPress3 = this$4.indexPress;
                    comboKeyPress3[3] = indexPress3;
                    this$4.indexPress = indexPress3 + 1;
                }
            }
            if (Gdx.input.isKeyPressed(50)) {
                final NinjaWar this$5 = NinjaWar.this;
                final int[] comboKeyPress4 = this$5.comboKeyPress;
                if (comboKeyPress4[0] == 0) {
                    final int indexPress4 = this$5.indexPress;
                    comboKeyPress4[4] = indexPress4;
                    this$5.indexPress = indexPress4 + 1;
                }
            }
            int n2 = n;
            if (NinjaWar.this.comboKeyPress[0] == 0) {
                int n3 = 1;
                while (true) {
                    final int[] comboKeyPress5 = NinjaWar.this.comboKeyPress;
                    n2 = n;
                    if (n3 >= comboKeyPress5.length) {
                        break;
                    }
                    if (comboKeyPress5[0] == comboKeyPress5[n3] - 1) {
                        n2 = n3 - 1;
                        break;
                    }
                    ++n3;
                }
            }
            NinjaMidlet.gameCanvas.comboKeyPressed(n2);
            final CanvasNinja gameCanvas = NinjaMidlet.gameCanvas;
            String name;
            if (map != null) {
                name = map.name;
            } else {
                name = "";
            }
            gameCanvas.keyPressed(keyCode, name);
            return false;
        }

        public boolean keyTyped(final char c) {
            NinjaMidlet.gameCanvas.mapKeyTyped(c);
            return false;
        }

        public boolean keyUp(int keyCode) {
            NinjaWar.this.indexPress = -1;
            int n = 0;
            while (true) {
                final int[] comboKeyPress = NinjaWar.this.comboKeyPress;
                if (n >= comboKeyPress.length) {
                    break;
                }
                comboKeyPress[n] = -1;
                ++n;
            }
            NinjaMidlet.gameCanvas.comboKeyReleased();
            final KEY map = MapKey.map(keyCode);
            if (map == null) {
                keyCode = 0;
            } else {
                keyCode = map.keyCode;
            }
            NinjaMidlet.gameCanvas.keyReleased(keyCode);
            return false;
        }

        public boolean mouseMoved(final int n, final int n2) {
            final Vector3 vector3 = new Vector3((float) n, (float) n2, 0.0f);
            NinjaWar.this.g.camera.unproject(vector3);
            final int n3 = (int) vector3.x;
            final int n4 = (int) vector3.y;
            final float n5 = (float) (n + (n3 - n));
            final float zoomLevel = mGraphics.zoomLevel;
            NinjaMidlet.gameCanvas.pointerHover((int) (n5 / zoomLevel), (int) ((n2 + (n4 - n2)) / zoomLevel));
            return false;
        }

        public boolean scrolled(final float n, final float n2) {
            final int n3 = (int) n2;
            final NinjaWar me = NinjaWar.me;
            NinjaMidlet.gameCanvas.scrollWhell(n3 * 10);
            return false;
        }

        public boolean touchDown(int n, int n2, final int n3, final int n4) {
            this.timeStartTouchDown = System.currentTimeMillis() + this.timeDelay;
            int n5 = n;
            int n6 = n2;
            if (NinjaWar.this.isIos78) {
                final int rotation = Gdx.input.getRotation();
                if (rotation == 90) {
                    n6 = NinjaWar.getH() - n;
                    n5 = n2;
                } else {
                    n5 = n;
                    n6 = n2;
                    if (rotation == 270) {
                        n5 = NinjaWar.getW() - n2;
                        n6 = n;
                    }
                }
            }
            final Vector3 vector3 = new Vector3((float) n5, (float) n6, 0.0f);
            NinjaWar.this.g.camera.unproject(vector3);
            n2 = (int) vector3.x;
            n = (int) vector3.y;
            final float n7 = (float) (n5 + (n2 - n5));
            final float zoomLevel = mGraphics.zoomLevel;
            n2 = (int) (n7 / zoomLevel);
            n = (int) ((n6 + (n - n6)) / zoomLevel);
            if (n3 == 0) {
                NinjaMidlet.gameCanvas.pointerPressed(n2, n, n3);
                if (n4 == 1) {
                    CanvasNinja.clearAllPointer();
                    CanvasNinja.isPointerClickRight = true;
                }
            }
            if (n3 > 0) {
                NinjaMidlet.gameCanvas.pointerMultiPressed(n2, n, n3);
            }
            if (NinjaMidlet.isDebugMouse) {
                final PrintStream out = System.out;
                final StringBuilder sb = new StringBuilder();
                sb.append("debug touch down:");
                sb.append(n3);
                sb.append("_screenX:");
                sb.append(n2);
                out.println(sb.toString());
            }
            final NinjaWar this$0 = NinjaWar.this;
            CanvasNinja.pointerDown(this$0.touchDownTempX[n3] = n2, this$0.touchDownTempY[n3] = n, n3);
            return false;
        }

        public boolean touchDragged(int n, int n2, final int n3) {
            int n4 = n;
            int n5 = n2;
            if (NinjaWar.this.isIos78) {
                final int rotation = Gdx.input.getRotation();
                if (rotation == 90) {
                    n5 = NinjaWar.getH() - n;
                    n4 = n2;
                } else {
                    n4 = n;
                    n5 = n2;
                    if (rotation == 270) {
                        n4 = NinjaWar.getW() - n2;
                        n5 = n;
                    }
                }
            }
            final Vector3 vector3 = new Vector3((float) n4, (float) n5, 0.0f);
            NinjaWar.this.g.camera.unproject(vector3);
            n = (int) vector3.x;
            n2 = (int) vector3.y;
            final float n6 = (float) (n4 + (n - n4));
            final float zoomLevel = mGraphics.zoomLevel;
            n = (int) (n6 / zoomLevel);
            n2 = (int) ((n5 + (n2 - n5)) / zoomLevel);
            final int n7 = NinjaWar.this.touchDownTempY[n3];
            if (n7 != 0 && Res.distance(n7, n2) > 5) {
                if (n3 == 0) {
                    NinjaMidlet.gameCanvas.pointerDraggedY(n, n2);
                }
                if (n3 > 0) {
                    NinjaMidlet.gameCanvas.pointerMultiDraggedY(n, n2, n3);
                }
            }
            final int n8 = NinjaWar.this.touchDownTempX[n3];
            if (n8 == 0 || Res.distance(n8, n) <= 5) {
                final int n9 = NinjaWar.this.touchDownTempY[n3];
                if (n9 == 0 || Res.distance(n9, n2) <= 5) {
                    return false;
                }
            }
            if (NinjaMidlet.isDebugMouse) {
                final PrintStream out = System.out;
                final StringBuilder sb = new StringBuilder();
                sb.append("debug drag:");
                sb.append(n3);
                sb.append("_diss:");
                sb.append(Res.distance(NinjaWar.this.touchDownTempX[n3], n));
                out.println(sb.toString());
            }
            NinjaMidlet.gameCanvas.pointerDragged(n, n2, n3);
            return false;
        }

        public boolean touchUp(int n, int n2, final int n3, int n4) {
            int n5 = n;
            n4 = n2;
            if (NinjaWar.this.isIos78) {
                final int rotation = Gdx.input.getRotation();
                if (rotation == 90) {
                    n4 = NinjaWar.getH() - n;
                    n5 = n2;
                } else {
                    if (rotation == 270) {
                        n5 = NinjaWar.getW() - n2;
                        n4 = n;
                    }
                }
            }
            final Vector3 vector3 = new Vector3((float) n5, (float) n4, 0.0f);
            NinjaWar.this.g.camera.unproject(vector3);
            n2 = (int) vector3.x;
            n = (int) vector3.y;
            final float n6 = (float) (n5 + (n2 - n5));
            final float zoomLevel = mGraphics.zoomLevel;
            n2 = (int) (n6 / zoomLevel);
            n = (int) ((n4 + (n - n4)) / zoomLevel);
            if (n3 == 0) {
                NinjaMidlet.gameCanvas.pointerReleased(n2, n, n3);
            }
            if (n3 > 0) {
                NinjaMidlet.gameCanvas.pointerMultiReleased(n2, n, n3);
            }
            if (NinjaMidlet.isDebugMouse) {
                final PrintStream out = System.out;
                String sb = "debug release:" + n3;
                out.println(sb);
            }
            touchDownTempX[n3] = 0;
            touchDownTempY[n3] = 0;
            CanvasNinja.pointerUp(n2, n, n3);
            return false;
        }

        @Override
        public boolean touchCancelled(int i, int i1, int i2, int i3) {
            return false;
        }
    }
}
