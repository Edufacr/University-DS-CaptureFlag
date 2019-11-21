package common;

import client.Server;
import client.SocketClient;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ServerManager implements Observer, IConstants {
    private Server server;
    private ArrayList<SocketClient> clients;
    private GameManager gameManager;
    public ServerManager(GameManager pGameManager){
        server = new Server(this);
        clients = new ArrayList<SocketClient>();
        gameManager = pGameManager;
    }
    public void sendMessage(int pInt, Object message){
        Message data;
        	
        switch (pInt) {
        case UPDATE_POSITION:
        	data = new Message(UPDATE_POSITION);
        	data.addField(UPDATE_POSITION_KEY, new ArrayList<ArrayList<String>>());
       		break;
       	case UPDATE_HP:
       		data = new Message(UPDATE_HP);
       		data.addField(UPDATE_HP_KEY, new ArrayList<ArrayList<String>>());
       		break;
       	default:
       		data = null;
       		break;
       	}
        	
        for (SocketClient client:clients) {
       		client.sendMsg(data);
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
