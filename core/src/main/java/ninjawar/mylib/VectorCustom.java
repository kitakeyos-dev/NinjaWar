package ninjawar.mylib;

import java.util.Vector;

public class VectorCustom {
    private Vector a;
    private String name;

    public VectorCustom() {
        this.a = new Vector();
        this.name = "No name";
    }

    public VectorCustom(String aa) {
        this.a = new Vector();
        this.name = aa;
    }

    public VectorCustom(Vector a2) {
        this.a = (Vector) a2.clone();
        this.name = "No Name";
    }

    public void addElement(Object o) {
        this.a.addElement(o);
    }

    public boolean contains(Object o) {
        if (this.a.contains(o)) {
            return true;
        }
        return false;
    }

    public int size() {
        Vector vector = this.a;
        if (vector == null) {
            return 0;
        }
        return vector.size();
    }

    public Object elementAt(int index) {
        if (index <= -1 || index >= this.a.size()) {
            return null;
        }
        return this.a.elementAt(index);
    }

    public void setElementAt(Object obj, int index) {
        if (index > -1 && index < this.a.size()) {
            this.a.setElementAt(obj, index);
        }
    }

    public void removeElementAt(int index) {
        if (index > -1 && index < this.a.size()) {
            this.a.removeElementAt(index);
        }
    }

    public boolean remove(Object obj) {
        return this.a.remove(obj);
    }

    public void set(int index, Object obj) {
        if (index > -1 && index < this.a.size()) {
            this.a.setElementAt(obj, index);
        }
    }

    public void removeElement(Object o) {
        this.a.removeElement(o);
    }

    public void removeAllElements() {
        this.a.removeAllElements();
    }

    public void add(Object c) {
        try {
            this.a.addElement(c);
        } catch (Exception e) {
        }
    }

    public void addAll(VectorCustom vObject) {
        for (int i = 0; i < vObject.size(); i++) {
            addElement(vObject.get(i));
        }
    }

    public Object get(int i) {
        return this.a.get(i);
    }

    public VectorCustom clone() {
        Vector vector = this.a;
        if (vector == null) {
            return new VectorCustom();
        }
        return new VectorCustom((Vector) vector.clone());
    }
}
