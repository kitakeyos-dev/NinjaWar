package ninjawar.mylib;

import com.teamobi.ninjawar.NinjaWar;
import ninjawar.message.GetMessage;
import ninjawar.message.ISession;
import ninjawar.message.MessageInterface;
import ninjawar.message.MessageSupport;
import ninjawar.model.mCmd;
import ninjawar.mymain.CanvasNinja;
import ninjawar.myscr.Res;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Vector;

public class MySession implements ISession {
    public static int count;
    public static int countRead = 0;
    protected static MySession instance = new MySession();
    protected static MySession instance2 = new MySession();
    public static boolean isCancel;
    public static boolean isDisconnect;
    public static VectorCustom recieveMsg = new VectorCustom();
    public Thread collectorThread;
    public boolean connected;
    public boolean connecting;
    int countMsg = 0;
    private byte curR;
    private byte curW;
    public DataInputStream dis;
    /* access modifiers changed from: private */
    public DataOutputStream dos;
    boolean getKeyComplete;
    public Thread initThread;
    public boolean isHaveKey;
    public boolean isMainSession = true;
    public byte[] key = null;
    public MessageInterface messageHandler;
    public int recvByteCount;
    public mSocket sc;
    public int sendByteCount;
    public Thread sendThread;
    /* access modifiers changed from: private */
    public final Sender sender = new Sender();
    public String strRecvByteCount = "";
    long timeConnected;
    long timeWaitConnect = 0;

    public static MySession gI() {
        MySession mySession = instance;
        return mySession != null ? mySession : new MySession();
    }

    public static MySession gI2() {
        return instance2 != null ? instance : new MySession();
    }

    public boolean isConnected() {
        if (this.connected) {
            final mSocket sc = this.sc;
            return sc != null && sc.s != null && this.dis != null;
        }
        return false;
    }

    public void setHandler(MessageInterface messageHandler2) {
        this.messageHandler = messageHandler2;
    }

    public void connect(String host, int port) {
        if (!this.connected && !this.connecting && mSystem.currentTimeMillis() >= this.timeWaitConnect) {
            PrintStream printStream = System.out;
            printStream.println("Connect to : ip = " + host + "-- port = " + port);
            this.timeWaitConnect = mSystem.currentTimeMillis() + 500;
            this.getKeyComplete = false;
            this.isHaveKey = false;
            Thread thread = new Thread(new NetworkInit(host, port));
            this.initThread = thread;
            thread.start();
        }
    }

    class NetworkInit implements Runnable {
        private final String host;
        int port;

        NetworkInit(String host2, int port2) {
            this.host = host2;
            this.port = port2;
        }

        @Override
        public void run() {
            MySession.isCancel = false;
            new Thread(() -> {
                try {
                    Thread.sleep(20000L);
                } catch (InterruptedException ignored) {
                }
                if (connecting) {
                    try {
                        sc.close();
                    } catch (Exception ignored) {
                    }
                    MySession.isCancel = true;
                    connecting = false;
                    connected = false;
                    messageHandler.onConnectionFail(isMainSession);
                }
            }).start();
            connecting = true;
            connected = true;
            try {
                this.doConnect(this.host, this.port);
                messageHandler.onConnectOK(isMainSession);
            } catch (Exception ex) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException ignored) {
                }
                Res.err("      3 =====>  Die ngang ");
                if (MySession.isCancel) {
                    return;
                }
                if (messageHandler != null) {
                    messageHandler.onConnectionFail(isMainSession);
                }
            }
        }

        public void doConnect(String host2, int port2) {
            try {
                sc = new mSocket(host2, port2);
                sc.setKeepAlive(true);
                dos = sc.getOutputStream();
                dis = sc.getInputStream();
                sendThread = new Thread(sender);
                sendThread.start();
                collectorThread = new Thread(new MessageCollector());
                collectorThread.start();
                timeConnected = mSystem.currentTimeMillis();
                doSendMessage(new MessageSupport((byte) -27));
                CanvasNinja.IP_d = host2;
                CanvasNinja.Port_d = port2 + "";
                connecting = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onRecieveMsg(MessageSupport msg) {
        if (Objects.equals(Thread.currentThread().getName(), NinjaWar.mainThreadName)) {
            this.messageHandler.onMessage(msg);
        } else {
            recieveMsg.addElement(msg);
        }
    }

    public void sendMessage(MessageSupport message) {
        count++;
        mCmd.printlnSendCommand(message.command);
        this.sender.AddMessage(message);
    }

    public synchronized void doSendMessage(MessageSupport m) throws IOException {
        byte[] data = m.getData();
        try {
            if (this.getKeyComplete) {
                this.dos.writeByte(writeKey(m.command));
            } else {
                DataOutputStream dataOutputStream = this.dos;
                if (dataOutputStream != null) {
                    dataOutputStream.writeByte(m.command);
                }
            }
            if (data != null) {
                int size = data.length;
                if (this.getKeyComplete) {
                    this.dos.writeByte(writeKey((byte) (size >> 8)));
                    this.dos.writeByte(writeKey((byte) (size & 255)));
                } else {
                    DataOutputStream dataOutputStream2 = this.dos;
                    if (dataOutputStream2 != null) {
                        dataOutputStream2.writeShort(size);
                    }
                }
                if (this.getKeyComplete) {
                    for (int i = 0; i < data.length; i++) {
                        data[i] = writeKey(data[i]);
                    }
                }
                DataOutputStream dataOutputStream3 = this.dos;
                if (dataOutputStream3 != null) {
                    dataOutputStream3.write(data);
                }
                this.sendByteCount += data.length + 5;
            } else {
                DataOutputStream dataOutputStream4 = this.dos;
                if (dataOutputStream4 != null) {
                    dataOutputStream4.writeShort(0);
                }
                this.sendByteCount += 5;
            }
            DataOutputStream dataOutputStream5 = this.dos;
            if (dataOutputStream5 != null) {
                dataOutputStream5.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
            e2.getCause();
            Res.err("      =====>  Die ngang ");
        }
    }

    public void update() {
        while (recieveMsg.size() > 0) {
            MessageSupport msg = (MessageSupport) recieveMsg.elementAt(0);
            if (!GetMessage.isStopReadMessage) {
                if (msg == null) {
                    recieveMsg.removeElementAt(0);
                    return;
                } else {
                    this.messageHandler.onMessage(msg);
                    recieveMsg.removeElementAt(0);
                }
            } else {
                return;
            }
        }
    }

    public byte readKey(byte b) {
        byte[] bArr = this.key;
        byte b2 = this.curR;
        byte b3 = (byte) (b2 + 1);
        this.curR = b3;
        byte i = (byte) ((bArr[b2] & 255) ^ (b & 255));
        if (b3 >= bArr.length) {
            this.curR = (byte) (b3 % bArr.length);
        }
        return i;
    }

    public byte writeKey(byte b) {
        byte[] bArr = this.key;
        byte b2 = this.curW;
        byte b3 = (byte) (b2 + 1);
        this.curW = b3;
        byte i = (byte) ((bArr[b2] & 255) ^ (b & 255));
        if (b3 >= bArr.length) {
            this.curW = (byte) (b3 % bArr.length);
        }
        return i;
    }

    private class Sender implements Runnable {
        public final Vector<MessageSupport> sendingMessage = new Vector<>();

        public Sender() {
        }

        public void AddMessage(MessageSupport message) {
            this.sendingMessage.addElement(message);
        }

        public void run() {
            while (true) {
                try {
                    if (connected) {
                        if (getKeyComplete) {
                            while (!this.sendingMessage.isEmpty()) {
                                MessageSupport messageSupport = this.sendingMessage.remove(0);
                                doSendMessage(messageSupport);
                            }
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                    } else {
                        return;
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }
    }

    class MessageCollector implements Runnable {
        MessageCollector() {
        }

        public void run() {
            while (isConnected()) {
                try {
                    MessageSupport message = readMessage();
                    try {
                        if (message.command == -27) {
                            getKey(message);
                        } else {
                            onRecieveMsg(message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e2) {
                    break;
                }
            }
            if (connected) {
                if (messageHandler != null) {
                    long currentTimeMillis = mSystem.currentTimeMillis();
                    if (currentTimeMillis - timeConnected > 500) {
                        MySession.isDisconnect = true;
                        messageHandler.onDisconnected(isMainSession);
                    }
                }
                if (sc != null) {
                    cleanNetwork();
                }
            }
        }

        private void getKey(MessageSupport message) throws IOException {
            int keySize = message.reader().readByte();
            key = new byte[keySize];
            for (int i = 0; i < keySize; i++) {
                key[i] = message.reader().readByte();
            }
            int i2 = 0;
            while (true) {
                byte[] bArr = key;
                if (i2 < bArr.length - 1) {
                    int i3 = i2 + 1;
                    bArr[i3] = (byte) (bArr[i3] ^ bArr[i2]);
                    i2++;
                } else {
                    getKeyComplete = true;
                    isHaveKey = true;
                    return;
                }
            }
        }

        private MessageSupport readMessage() throws Exception {
            int size;
            byte cmd = dis.readByte();
            if (getKeyComplete) {
                cmd = readKey(cmd);
            }
            if (cmd == -32 || cmd == -66 || cmd == 11 || cmd == -67 || cmd == -74 || cmd == -87) {
                Res.outz(3, "vo cmd đac bietttttttttttttttttt:" + cmd);
                return readMessage2(cmd);
            }
            if (getKeyComplete) {
                size = sumBytes2(dis);
            } else {
                size = dis.readInt();
            }
            byte[] data = new byte[size];
            int len = 0;
            int byteRead = 0;
            while (len != -1 && byteRead < size) {
                len = dis.read(data, byteRead, size - byteRead);
                if (len > 0) {
                    byteRead += len;
                    recvByteCount += byteRead + 5;
                    int Kb = MySession.gI().recvByteCount + MySession.gI().sendByteCount;
                    strRecvByteCount = (Kb / 1024) + "." + ((Kb % 1024) / 102) + "Kb";
                }
            }
            if (getKeyComplete) {
                for (int i = 0; i < data.length; i++) {
                    data[i] = readKey(data[i]);
                }
            }
            return new MessageSupport(cmd, data);
        }
    }

    /* access modifiers changed from: private */
    public MessageSupport readMessage2(byte cmd) throws Exception {
        int subCmd = readKey(this.dis.readByte());
        int size = sumBytes(this.dis);
        byte[] data = new byte[size];
        int len = 0;
        int byteRead = 0;
        while (len != -1 && byteRead < size) {
            len = this.dis.read(data, byteRead, size - byteRead);
            if (len > 0) {
                byteRead += len;
                this.recvByteCount += byteRead + 5;
                int Kb = gI().recvByteCount + gI().sendByteCount;
                this.strRecvByteCount = (Kb / 1024) + "." + ((Kb % 1024) / 102) + "Kb";
            }
        }
        if (this.getKeyComplete) {
            for (int i = 0; i < data.length; i++) {
                data[i] = readKey(data[i]);
            }
        }
        return new MessageSupport((byte) subCmd, data);
    }

    private int sumBytes(DataInputStream in) {
        return sumBytes2(in);
    }

    /* access modifiers changed from: private */
    public int sumBytes2(DataInputStream in) {
        try {
            return ((readKey(in.readByte()) & 255) << 24) | ((readKey(in.readByte()) & 255) << 16) | ((readKey(in.readByte()) & 255) << 8) | ((readKey(in.readByte()) & 255) << 0);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void close() {
        cleanNetwork();
    }

    /* access modifiers changed from: private */
    public void cleanNetwork() {
        this.key = null;
        this.curR = 0;
        this.curW = 0;
        try {
            this.connected = false;
            this.connecting = false;
            mSocket msocket = this.sc;
            if (!(msocket == null || msocket.s == null)) {
                msocket.close();
            }
            this.sc = null;
            DataOutputStream dataOutputStream = this.dos;
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            this.dos = null;
            DataInputStream dataInputStream = this.dis;
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            this.dis = null;
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        Res.err(" =====>     close Connnect  thanh cong");
    }
}
