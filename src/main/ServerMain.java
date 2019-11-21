package main;

import client.Server;
import common.GameManager;
import common.ServerManager;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        GameManager manager = new GameManager();
        /*ServerManager manager = new ServerManager();
        int i = 0;
        try{
             i=System.in.read();
        }
        catch (IOException e){

        }
        manager.sendMessage(i);*/
    }
}
