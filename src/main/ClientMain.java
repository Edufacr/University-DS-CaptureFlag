package main;

import client.SocketClient;
import common.ClientManager;
import common.Message;
import gui.MainWindow;

public class ClientMain {
    public static void main(String[] args) {
        MainWindow w = new MainWindow();
        /*SocketClient client = new SocketClient("192.168.0.14");
        ClientManager manager = new ClientManager(client);
        manager.sendMessage(2);
        //client.sendMsg(new Message(1));*/
    }
}
