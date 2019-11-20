package model;

import model.characters.Team;

public class Square {
    int id;
    Team actualTeam;


    public Square(int pId){
        id = pId;
        actualTeam = null;
    }
}
