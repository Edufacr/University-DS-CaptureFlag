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

    public void sendMessage(Message pMessage) {
        client.sendMsg(pMessage);
    }
    
    public void setPlayerTactics(ArrayList<ArrayList<Integer>> pCoordinates, ArrayList<String> pComposition) {
    	Message coordinates = new Message(SET_COORDINATES);
    	coordinates.addField2(TACTIC_COORDINATES, pCoordinates);
    	sendMessage(coordinates);
    	
    	Message composition = new Message(SET_COMPOSITION);
    	ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();
    	arr.add(pComposition);
    	composition.addField(TEAM_COMPOSITION, arr);
    	sendMessage(composition);
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

