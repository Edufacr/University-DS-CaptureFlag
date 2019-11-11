package main;

import client.SocketClient;
import common.ClientManager;
import common.Message;

public class ClientMain {
    public static void main(String[] args) {
        SocketClient client = new SocketClient("127.0.0.1");
        ClientManager manager = new ClientManager(client);
        manager.sendMessage(2);
        //client.sendMsg(new Message(1));
    }
}
