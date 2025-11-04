package ninjawar.mylib;

import java.util.Hashtable;

public class HTCustom extends Hashtable {
    String name;

    public HTCustom(String name2) {
        this.name = name2;
    }

    public synchronized void clear() {
        super.clear();
    }

    public synchronized Object put(Object arg0, Object arg1) {
        return super.put(arg0, arg1);
    }

    public synchronized Object remove(Object arg0) {
        return super.remove(arg0);
    }
}
