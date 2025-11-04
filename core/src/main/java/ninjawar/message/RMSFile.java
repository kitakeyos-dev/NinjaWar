package ninjawar.message;

public class RMSFile {
    public byte[] data;
    public String fileName = "";

    public RMSFile(byte[] data2, String fileName2) {
        this.data = data2;
        this.fileName = fileName2;
    }
}
