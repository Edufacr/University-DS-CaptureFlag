package model;

import model.characters.Team;

public class Square {
    private int id;
    private Team actualTeam;


    public Square(int pId){
        id = pId;
        actualTeam = null;
    }

    public int getId() {
        return id;
    }
}
