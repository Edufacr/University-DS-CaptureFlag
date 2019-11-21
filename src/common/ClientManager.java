package common;

import client.SocketClient;

import java.util.Observable;
import java.util.Observer;

public class ClientManager extends Observable implements Observer {
    private final String ip = "127.0.0.1";
    private SocketClient client;

    public ClientManager(SocketClient pClient) {
        client = pClient;
    }

    public ClientManager(Observer pObserver){
        client = new SocketClient(ip);
        addObserver(pObserver);
    }

    public void sendMessage(int num) {
        client.sendMsg(new Message(num));
    }

    @Override
    public void update(Observable observable, Object o) {
        //Aqui llega el notify del SocketClient(Server)
        //Para comunicarse con la gui usar notifyObservers

        //Object = Message que tiene un json adentro
        //
        /*
            json{
            Personajes[]{
            Player1[]{
            String{"Marine: 100",0}

            }
            Player2[]{
            String{"Marine: 100",0}
            }
            }
            Posiciones[]{
            {(1,3),(1,2)},
            {(3,2),(2,3)},
            }
            }
            }
         */
    }
}

