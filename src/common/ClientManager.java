package common;

import client.SocketClient;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ClientManager extends Observable implements Observer, IConstants{
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
    
    public void getPlayerTactics() {
    	
    }

    @Override
    public void update(Observable observable, Object pObject) {

        Message message = (Message) pObject;
       
        ArrayList<String> id;
        switch (message.getType()) {
        case UPDATE_POSITION:
        	ArrayList<ArrayList<String>> positions = message.getStringArrayValue(UPDATE_POSITION_KEY);
        	id = new ArrayList<String>();
        	id.add(UPDATE_POSITION_KEY.toString());
        	positions.add(0, id);
        	this.setChanged();
        	this.notifyObservers(positions);
        	break;
        case UPDATE_HP:
        	ArrayList<ArrayList<String>> hp = message.getStringArrayValue(UPDATE_HP_KEY);
        	id = new ArrayList<String>();
        	id.add(UPDATE_HP_KEY.toString());
        	hp.add(0, id);
        	this.setChanged();
        	this.notifyObservers(hp);
        	break;
        default:
        	break;
        }
    }
}

