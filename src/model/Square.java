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

    public Team getActualTeam() {
        return actualTeam;
    }

    public void setActualTeam(Team actualTeam) {
        this.actualTeam = actualTeam;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
