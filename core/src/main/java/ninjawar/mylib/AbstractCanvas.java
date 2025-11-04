// Class Version: 8
package ninjawar.mylib;

import com.teamobi.ninjawar.NinjaWar;
import java.io.PrintStream;

public abstract class AbstractCanvas
{
    public AbstractCanvas() {
        setFullScreenMode(true);
        this.checkZoomLevel(this.getWidthL(), this.getHeightL());
    }

    public static void setFullScreenMode(final boolean b) {
    }

    public void checkZoomLevel(final int n, final int n2) {
        final PrintStream out = System.out;
        final StringBuilder sb = new StringBuilder();
        sb.append(" mGraphics.zoomLevel ");
        sb.append(mGraphics.zoomLevel);
        out.println(sb.toString());
        mFont.init();
    }

    public int getHeightL() {
        return NinjaWar.getHeight();
    }

    public float getHeightz() {
        return mSystem.get_realHeight() / mGraphics.zoomLevel;
    }

    public int getWidthL() {
        return NinjaWar.getWidth();
    }

    public float getWidthz() {
        return mSystem.get_realWidth() / mGraphics.zoomLevel;
    }

    public boolean hasPointerEvents() {
        return true;
    }
}
