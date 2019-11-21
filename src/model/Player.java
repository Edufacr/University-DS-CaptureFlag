package model;

import model.characters.Team;

import java.util.ArrayList;

public class Player {
    private ArrayList<Team> teams;
    private int flagNodeNum;
    private int id;

    public Player(ArrayList<Team> pTeams,int pFlagNodeNum){
        teams = pTeams;
        flagNodeNum = pFlagNodeNum;
        id = 0;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public int getFlagNodeNum() {
        return flagNodeNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
