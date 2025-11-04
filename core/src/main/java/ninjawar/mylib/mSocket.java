package ninjawar.mylib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class mSocket {
    public Socket s;

    public mSocket(String host, int port) {
        try {
            this.s = Gdx.net.newClientSocket(Net.Protocol.TCP, host, port, (SocketHints) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            Socket socket = this.s;
            if (socket != null) {
                socket.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setKeepAlive(boolean isAlive) {
    }

    public DataOutputStream getOutputStream() {
        try {
            if (this.s != null) {
                return new DataOutputStream(this.s.getOutputStream());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public DataInputStream getInputStream() {
        try {
            if (this.s != null) {
                return new DataInputStream(this.s.getInputStream());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
