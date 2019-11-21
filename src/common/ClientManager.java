package common;

import client.SocketClient;

public class ClientManager {
    SocketClient client;

    public ClientManager(SocketClient pClient) {
        client = pClient;
    }

    public void sendMessage(int num) {
        client.sendMsg(new Message(num));
    }

}
