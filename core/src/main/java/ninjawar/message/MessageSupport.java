package ninjawar.message;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MessageSupport {
    public byte command;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    public ByteArrayInputStream is = null;
    private ByteArrayOutputStream os = null;

    public MessageSupport(int command2) {
        this.command = (byte) command2;
        this.os = new ByteArrayOutputStream(1024);
        this.dos = new DataOutputStream(this.os);
    }

    public MessageSupport() {
    }

    public MessageSupport(byte command2) {
        this.command = command2;
        this.os = new ByteArrayOutputStream(1024);
        this.dos = new DataOutputStream(this.os);
    }

    public MessageSupport(byte command2, byte[] data) {
        this.command = command2;
        this.is = new ByteArrayInputStream(data);
        this.dis = new DataInputStream(this.is);
    }

    public byte[] getData() {
        return this.os.toByteArray();
    }

    public DataInputStream reader() {
        return this.dis;
    }

    public DataOutputStream writer() {
        return this.dos;
    }

    public void cleanup() {
        try {
            DataInputStream dataInputStream = this.dis;
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            DataOutputStream dataOutputStream = this.dos;
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
        } catch (IOException e) {
        }
    }

    public short readShort() throws IOException {
        return reader().readShort();
    }

    public int readInt() throws IOException {
        return reader().readInt();
    }

    public byte readByte() throws IOException {
        return reader().readByte();
    }

    public String readUTF() throws IOException {
        return reader().readUTF();
    }

    public float readFloat() throws IOException {
        return reader().readFloat();
    }

    public boolean readBoolean() throws IOException {
        return reader().readBoolean();
    }
}
