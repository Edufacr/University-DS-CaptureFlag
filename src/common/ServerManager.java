package common;

import client.Server;
import client.SocketClient;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ServerManager implements Observer {
    private Server server;
    private ArrayList<SocketClient> clients;
    private GameManager gameManager;
    public ServerManager(GameManager pGameManager){
        server = new Server(this);
        clients = new ArrayList<SocketClient>();
        gameManager = pGameManager;
    }
    public void sendMessage(int pInt){
        for (SocketClient client:clients
             ) {
            //Aqui se envía el mensaje para cada client
            //client.sendMsg();
        }
    }
    @Override
    public void update(Observable observable, Object o) {
        //Aqui llegan notify de los SocketsClients
        SocketClient client = (SocketClient)observable;
        if(!clients.contains(client)){
            clients.add(client);
        }{

        }


       /*SocketClient client = (SocketClient)observable;
        Message message = (Message)o;
        clients.add(new ClientManager(client));
        System.out.println(message.getJSonString());*/
    }
}
