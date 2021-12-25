package game.common;

import game.protocol.Message;
import java.io.IOException;

public interface Connection {
    Information getInformation();

    void send(Message message) throws IOException;
    void close();
    int getId();
    boolean isConnected();
}
