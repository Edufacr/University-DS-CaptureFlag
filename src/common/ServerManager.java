package common;

import client.Server;
import client.SocketClient;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ServerManager implements Observer {
    private Server server;
    private ArrayList<ClientManager> clients;
    public ServerManager(){
        server = new Server(this);
        clients = new ArrayList<ClientManager>();
    }
    public void sendMessage(int num){
        for (ClientManager client:
             clients) {
            client.sendMessage(num);
        }
    }
    @Override
    public void update(Observable observable, Object o) {
        SocketClient client = (SocketClient)observable;
        Message message = (Message)o;
        clients.add(new ClientManager(client));
        System.out.println(message.getJSonString());
    }
}
