package ninjawar.mylib;

import java.util.Enumeration;
import java.util.Hashtable;

public class HashTableCustom {
    public Hashtable<Object, Object> htb = new Hashtable<>();

    public void clear() {
        this.htb.clear();
    }

    public boolean contains(Object obj) {
        return this.htb.contains(obj);
    }

    public Object get(String k) {
        return this.htb.get(k);
    }

    public int size() {
        return this.htb.size();
    }

    public void put(String k, Object v) {
        if (this.htb.containsKey(k)) {
            this.htb.remove(k);
        }
        this.htb.put(k, v);
    }

    public void remove(Object k) {
        this.htb.remove(k);
    }

    public Enumeration<Object> keys() {
        return this.htb.keys();
    }
}
