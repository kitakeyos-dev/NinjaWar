package ninjawar.message;

public interface MessageInterface {
    void onConnectOK(boolean z);

    void onConnectionFail(boolean z);

    void onDisconnected(boolean z);

    void onMessage(MessageSupport messageSupport);
}
