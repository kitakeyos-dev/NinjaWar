package ninjawar.mylib;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import java.io.InputStream;

public class LibSysCustom {
    public static String font = "fonts/x";
    public static String res = "res";

    public static InputStream getResourceAsStream(String path) {
        Files files = Gdx.files;
        InputStream in = files.internal(res + path).read();
        if (in != null) {
            return in;
        }
        throw new IllegalArgumentException("InputStream cannot found path= " + path);
    }
}
